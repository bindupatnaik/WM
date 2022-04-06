
package com.rf.test.website;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.base.RFBaseTest;

/**
 * @author ShubhamMathur RFLegacyStoreFrontWebsiteBaseTest is the super class for all
 *         Legacy desktop Test classes initializes the driver is used for execution.
 *
 */
public class RFNSCoreWebsiteBaseTest extends RFBaseTest {
	StringBuilder verificationErrors = new StringBuilder();
	protected String password = null;
	protected String countryId = null;
	private static final By LOGIN_BTN = By.id("btnLogin");
	private static final By USERNAME_TXT_FIELD_NSCORE4 = By.id("username");
	private static final By PASSWORD_TXT_FIELD_NSCORE4 = By.id("password");
	private static final By USERNAME_TXT_FIELD_NSCORE3 = By.id("txtUsername");
	private static final By PASSWORD_TXT_FIELD_NSCORE3 = By.id("txtPassword");
	private static final By LOGOUT_LINK = By.xpath("//a[contains(text(),'Logout')]") ;

	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(RFNSCoreWebsiteBaseTest.class.getName());

	/**
	 * @throws Exception
	 *             setup function loads the driver and environment and baseUrl
	 *             for the tests
	 */
	@BeforeSuite(alwaysRun=true)
	public void setUp() throws Exception {
		logger.info("In the Before suite..");
		driver.loadApplication();		
		logger.info("Application loaded");				
		driver.setDBConnectionString();
		driver.get(driver.getURL());
		loginToNSC4();
		logger.info("out of Before suite..");
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(){
		logger.info("In Before method..");
		s_assert = new SoftAssert();
		if(driver.getURL().contains("nsc4")) 
			driver.get(driver.getURL());
		else
			driver.get(driver.getURL()+"/Home.aspx"); 
	}
	
	public void loginToNSC4(){
		if(driver.getEnvironment().toLowerCase().contains("stg3"))
			login("autologin", "111Maiden$");
		else			
			login("admin", "skin123!");
	}
 
	public void login(String userName,String password){ 
		if(driver.getCurrentUrl().contains("nsc4")) {
			driver.quickWaitForElementPresent(USERNAME_TXT_FIELD_NSCORE4,30);
			if(driver.isElementPresent(USERNAME_TXT_FIELD_NSCORE4)) {
				driver.type(USERNAME_TXT_FIELD_NSCORE4, userName+"\t","username");
				driver.quickWaitForElementPresent(PASSWORD_TXT_FIELD_NSCORE4);
				driver.type(PASSWORD_TXT_FIELD_NSCORE4, password,"pass");
				driver.click(LOGIN_BTN,"login");
				driver.waitForCSCockpitLoadingImageToDisappear();
			}

		}else if(driver.getCurrentUrl().contains("nsc3")) {
			if(driver.isElementPresent(USERNAME_TXT_FIELD_NSCORE3)) {
				driver.type(USERNAME_TXT_FIELD_NSCORE3, userName+"\t","username"); 
				driver.type(PASSWORD_TXT_FIELD_NSCORE3, password,"pass");
				driver.click(LOGIN_BTN,"login");
			}

		} 
	}

	/**
	 * @throws Exception
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		try{
			driver.quickWaitForElementPresent(LOGOUT_LINK);
			driver.click(LOGOUT_LINK,"logout");
			driver.waitForPageLoad();
		}catch(Exception e){

		}
		new HtmlLogger().createHtmlLogFile();		
		driver.quit();		
	}

	public void setStoreFrontPassword(String pass){
		password=pass;
	}

	public void openCOMSite(){			
		String RFL_DB = driver.getDBNameRFL();
		String comPWS = null;
		boolean isActiveCOMPWSFound = false;
		for(int i=1;i<=15;i++){			
			List<Map<String, Object>> randomConsultantPrefix = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_PREFIX,RFL_DB);
			comPWS = (String) getValueFromQueryResult(randomConsultantPrefix, "URL");
			comPWS=comPWS.replaceAll(".biz", ".com");
			driver.get(comPWS);
			if(driver.getCurrentUrl().contains("Error")||driver.getCurrentUrl().contains("error")||driver.getCurrentUrl().contains("SiteNotActive")){
				continue;
			}
			else{
				isActiveCOMPWSFound=true;
				comPWS = (String) getValueFromQueryResult(randomConsultantPrefix, "URL");
				comPWS=comPWS.replaceAll(".biz", ".com");
				break;
			}
		}		
		driver.waitForPageLoad();
	}	

	public void openBIZSite(){			
		String RFL_DB = driver.getDBNameRFL();
		String bizPWS = null;
		boolean isActiveBIZPWSFound = false;
		for(int i=1;i<=15;i++){			
			List<Map<String, Object>> randomConsultantPrefix = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_PREFIX,RFL_DB);
			bizPWS = (String) getValueFromQueryResult(randomConsultantPrefix, "URL");
			bizPWS=bizPWS.replaceAll(".com", ".biz");
			driver.get(bizPWS);
			if(driver.getCurrentUrl().contains("Error")||driver.getCurrentUrl().contains("error")||driver.getCurrentUrl().contains("SiteNotActive")){
				continue;
			}
			else{
				isActiveBIZPWSFound=true;
				bizPWS = (String) getValueFromQueryResult(randomConsultantPrefix, "URL");
				bizPWS=bizPWS.replaceAll(".com", ".biz");
				break;
			}
		}		
		driver.waitForPageLoad();
	}	
	
	// This assertion for the UI Texts
	public void assertTrue(String message, boolean condition) {
		if (!condition) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");			
			Assert.fail(message);				
		}

	}

	//This assertion for the Database validations
	public boolean assertTrueDB(String message, boolean condition,String dbName) {
		if (!condition) {
			logger.info("[DATABASE ASSERTION FAILURE -  "+dbName+" ----------- " +message + "]");
			if(!dbName.equals(driver.getDBNameRFL())){
				Assert.fail(message);
			}
			else{
				return false;
			}
		}
		return true;
	}

	public void assertTrue(boolean condition, String message) {

		if (!condition) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public void assertEquals(Object obj1, Object obj2, String message) {
		if (!obj1.equals(obj2)) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public boolean assertEqualsDB(Object obj1, Object obj2, String message,String dbName) {
		if (!obj1.equals(obj2)) {
			logger.info("[DATABASE ASSERTION FAILURE -  "+dbName+" ----------- " +message + "]");
			if(!dbName.equals(driver.getDBNameRFL())){
				Assert.fail(message);
			}	
			else{
				return false;
			}
		}
		return true;		
	}

	public boolean assertEqualsDB(String message, int num1,int num2,String dbName) {
		if (!(num1==num2)) {
			logger.info("[RFL DATABASE ASSERTION FAILURE -  "+message + "]");
			if(!dbName.equals(driver.getDBNameRFL())){
				Assert.fail(message);
			}
			else{
				return false;
			}
		}
		return true;
	}

	public void assertEquals(String message, int num1,int num2) {
		if (!(num1==num2)) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public void assertFalse(boolean condition, String message) {

		if (condition) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public void assertFalse(String message, boolean condition) {

		if (condition) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public void assertEquals(String message, float num1,float num2) {
		if (!(num1==num2)) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public Object getValueFromQueryResult(List<Map<String, Object>> userDataList,String column){
		Object value = null;
		for (Map<String, Object> map : userDataList) {
			value = map.get(column);			
		}
		logger.info("Data returned by query: "+ value);
		return value;
	}

	public List<String> getValuesFromQueryResult(List<Map<String, Object>> userDataList,String column){
		List<String> allReturnedValuesFromQuery = new ArrayList<String>();
		Object value = null;
		for (Map<String, Object> map : userDataList) {
			logger.info("query result:" + map.get(column));
			value = map.get(column);
			allReturnedValuesFromQuery.add(String.valueOf(value));
		}
		return allReturnedValuesFromQuery;
	}

}
