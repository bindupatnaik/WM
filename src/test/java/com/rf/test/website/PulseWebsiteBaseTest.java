package com.rf.test.website;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.LSD.LSDCustomerPage;
import com.rf.pages.website.LSD.LSDFeedbackPage;
import com.rf.pages.website.LSD.LSDHomePage;
import com.rf.pages.website.LSD.LSDLoginPage;
import com.rf.pages.website.LSD.LSDOrderPage;
import com.rf.test.base.RFBaseTest;

/**
 * @author ShubhamMathur PulseWebsiteBaseTest is the super class for all
 *         desktop Test classes initializes the driver is used for execution.
 *
 */
public class PulseWebsiteBaseTest extends RFBaseTest {
	StringBuilder verificationErrors = new StringBuilder();
	protected String password = null;
	protected String countryId = null;
	protected String accountId = null;
	protected LSDLoginPage lsdLoginPage;
	protected LSDHomePage lsdHomePage;
	protected LSDOrderPage lsdOrderPage;
	protected LSDCustomerPage lsdCustomerPage;
	protected LSDFeedbackPage lsdFeedbackPage;
	protected List<Map<String, Object>> randomConsultantList;
	protected List<Map<String, Object>> randomConsultantListCommi;
	
	protected Actions actions;
	protected String country=null;
	protected String RFO_DB_IP =null;
	protected String RFO_DBName =null;
	protected String Commisions_DB_IP =null;
	protected String commDBName =null;
	protected String dbUsername =null;
	protected String dbPassword =null;
	protected String dbDomain =null;
	protected String authentication =null;
	protected String userName = null;
	public List<Map<String, Object>> allPCCustomerInfo = null;

	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(PulseWebsiteBaseTest.class.getName());

	private static final By LOGIN_BTN = By.id("button-login");

	public PulseWebsiteBaseTest() {
		lsdLoginPage = new LSDLoginPage(driver);
		lsdHomePage = new LSDHomePage(driver);
		lsdOrderPage = new LSDOrderPage(driver);
		lsdCustomerPage = new LSDCustomerPage(driver);
		lsdFeedbackPage = new LSDFeedbackPage(driver) ;
	}

	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception{
		RFO_DB_IP = driver.getDBIP();
		RFO_DBName = driver.getDBNameRFO();
		Commisions_DB_IP = driver.getDBIP2();
		commDBName = driver.getDBNameCom();
		dbUsername = driver.getDBUsername();
		dbPassword = driver.getDBPassword();
		dbDomain = driver.getDBDomain();
		authentication = driver.getDBAuthentication();
		country = driver.getCountry();
		password= driver.getStoreFrontPassword();
		driver.setDBConnectionString(RFO_DB_IP,dbUsername,dbPassword,dbDomain,authentication);   

		if(country.equalsIgnoreCase("ca"))
			countryId="40";
		else if(country.equalsIgnoreCase("au"))
			countryId="14";
		
		List<Map<String, Object>> randomConsultantListForEmail; 
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_CONSULTANT_COMMISSION,commDBName,Commisions_DB_IP); 
		System.out.println(randomConsultantListCommi);	
		accountId = getAccountId(randomConsultantListCommi);
		System.out.println(accountId);
			randomConsultantListForEmail = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
			userName = (String) getValueFromQueryResult(randomConsultantListForEmail, "EmailAddress"); 
			System.out.println(userName);
			
			driver.waitForLSDJustAMomentImageToDisappear();
			driver.waitForElementToBeVisible(By.id("username"), 40);
			//temp code to take screenshot
			//driver.takeSnapShotAndRetPath(RFWebsiteDriver.driver, "pulseLoginSshot", true);
			lsdLoginPage.enterUsername(userName);
			lsdLoginPage.enterPassword(password);
			lsdLoginPage.clickLoginBtn();
			driver.pauseExecutionFor(3000);
	} 
	
	protected String getUserNmae(List<Map<String, Object>> randomConsultantList2) {
		String userName = null;
		userName = (String) getValueFromQueryResult(randomConsultantList2, "UserName");
		logger.info("userName:"+randomConsultantList2);
		return userName;
	}

	protected String getAccountId(List<Map<String, Object>> randomConsultantList) {
		String accountId = null;
		logger.info("randomConsultantDetailsList:"+randomConsultantList);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
		return accountId;
	} 

	//this call make sure we hit the DB only once to get all Customer page information
	public List<Map<String, Object>> getAllCustomersInfo() {
		if (allPCCustomerInfo == null) {
			allPCCustomerInfo = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ALL_PC_CUSTOMER_INFO,accountId),RFO_DBName,RFO_DB_IP);
		}

		return allPCCustomerInfo;
	}

	/**
	 * @throws Exception
	 *             setup function loads the driver and environment and baseUrl
	 *             for the tests
	 */
	@BeforeSuite(alwaysRun=true)
	public void setUp() throws Exception {
		System.out.println("*****************SUITE");
		driver.loadApplication(); 
		driver.get(driver.getURL());
		logger.info("Application loaded");    
	}

	public void checkAndCloseMoreThanOneWindows(){
		Set<String> allWindows = driver.getWindowHandles();
		logger.info("Total windows opened are "+allWindows.size());
		String currentWin = driver.getWindowHandle();
		for(String win:allWindows){
			if(win.equals(currentWin)==false && allWindows.size()>1){
				driver.switchTo().window(win);
				driver.close();
				driver.switchTo().window(currentWin);
				allWindows = driver.getWindowHandles();       
			} 
		}
	}

	public void clickRFLogo(){
//		try{
		driver.click(By.xpath("//div[@id='rf-nav-wrapper']/descendant::img[2]"), "R+F logo");
		driver.waitForPageLoad();
		driver.waitForElementToBeVisible(By.xpath("//div[@id='rf-nav-wrapper']/descendant::span[text()='Pulse'][1]"), 10);
//		}catch(Exception e){
//			try {
//				driver.takeSnapShotAndRetPath(RFWebsiteDriver.driver, "PulseSkipScreenshot", false);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='rf-nav-wrapper']/descendant::img[2]"), "R+F logo by JS");
		}
	

	public boolean isLoginBtnPresent(){
		//driver.waitForElementPresent(LOGIN_BTN,5);
		return driver.isElementPresent(LOGIN_BTN);

	}

	public void enterCredentialsInHTTPAuthentication(String username,String password) throws AWTException{
		driver.pauseExecutionFor(1000);
		StringSelection selec= new StringSelection(username);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selec, selec);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		driver.pauseExecutionFor(1000);
		selec= new StringSelection(password);
		clipboard.setContents(selec, selec);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		driver.pauseExecutionFor(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		driver.pauseExecutionFor(3000);
	}

	/**
	 * @throws Exception
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		new HtmlLogger().createHtmlLogFile();                 
	
				driver.quit();
		
	}

	public void setStoreFrontPassword(String pass){
		password=pass;
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

	//            public void assertTrue(boolean condition) {
	//
	//                            try {
	//                                            assertTrue(condition);
	//
	//                            } catch (Exception e) {
	//                                            logger.trace(e.getMessage());
	//                            }
	//            }

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

			//logger.info("query result:" + map.get(column));

			//            logger.info("query result:" + map.get(column));

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
