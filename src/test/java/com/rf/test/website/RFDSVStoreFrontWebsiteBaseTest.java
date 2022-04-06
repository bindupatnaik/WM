
package com.rf.test.website;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.test.base.RFBaseTest;
import com.rf.test.website.storeFront.hybris.dsv.StorefrontHybrisDSVTest;

/**
 * @author ShubhamMathur RFDSVStoreFrontWebsiteBaseTest is the super class for all
 *         DSV StoreFront desktop Test classes initializes the driver is used for execution.
 *
 */
public class RFDSVStoreFrontWebsiteBaseTest extends RFBaseTest {
	StringBuilder verificationErrors = new StringBuilder();
	private String password = null;
	private String countryId = null;
	private String comPWS = null;	
	protected String consultantEmailId = null;
	private String pcEmailId = null;
	private String rcEmailId = null;	
	protected String consultantPassword = null;
	private String pcPassword = null;
	private String rcPassword = null;

	protected String pwsSuffix = null;
	protected String cardNumber = null;
	protected String expMonth = null;
	protected String expYear= null;
	protected String CVV= null;
	protected String addressLine1= null;
	protected String city= null;
	protected String postalCode= null;
	protected String phoneNumber= null;
	protected String sponsorWithPWS = null;
	protected String sponsorWithPWS_US = null;
	protected String country = null;
	protected String editPhoneNumber = null;
	protected String state=null;
	protected int randomNum=0000;

	private static final By LOGIN_LINK = By.xpath("//li[@id='log-in-button']/a");
	private static final By USERNAME_TXTFIELD = By.id("username");
	private static final By PASSWORD_TXTFIELD = By.id("password");
	private static final By LOGIN_BTN = By.xpath("//input[@value='SIGN IN']");

	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(RFDSVStoreFrontWebsiteBaseTest.class.getName());

	/**
	 * @throws MalformedURLException 
	 * @throws Exception
	 *             setup function loads the driver and environment and baseUrl
	 *             for the tests
	 */
	@BeforeSuite(alwaysRun=true)
	public void setUp() throws Exception {
		country = driver.getCountry();
		sponsorWithPWS_US = TestConstants.DSV_SPONSOR_WITH_PWS_US;
		if(country.equalsIgnoreCase("ca")){
			countryId = "40";
			consultantEmailId = TestConstants.DSV_CONSULTANT_EMAILID_CA;
			pcEmailId = TestConstants.DSV_PC_EMAILID_CA;
			rcEmailId = TestConstants.DSV_RC_EMAILID_CA;
			consultantPassword= TestConstants.DSV_CONSULTANT_PASSWORD_CA;
			pcPassword=TestConstants.DSV_PC_PASSWORD_CA;
			rcPassword = TestConstants.DSV_RC_PASSWORD_CA;
			pwsSuffix = TestConstants.DSV_PWS_SUFFIX_CA;
			cardNumber = TestConstants.DSV_CARD_NUMBER_CA;
			expMonth = TestConstants.DSV_EXPIRY_MONTH_CA;
			expYear = TestConstants.DSV_EXPIRY_YEAR_CA;
			CVV = TestConstants.DSV_SECURITY_CODE_CA;
			addressLine1 = TestConstants.DSV_ADDRESS_LINE_1_CA;
			city = TestConstants.DSV_CITY_CA;
			postalCode = TestConstants.DSV_POSTAL_CODE_CA;
			phoneNumber = TestConstants.DSV_PHONE_NUMBER_CA;
			sponsorWithPWS = TestConstants.DSV_SPONSOR_WITH_PWS_CA;
			editPhoneNumber = TestConstants.DSV_PHONE_NUMBER_FOR_EDIT_CA;
			state = TestConstants.DSV_STATE_CA;

		}
		else if(country.equalsIgnoreCase("au")){
			countryId = "14";	
			consultantEmailId = TestConstants.DSV_CONSULTANT_EMAILID_AU;
			pcEmailId = TestConstants.DSV_PC_EMAILID_AU;
			rcEmailId = TestConstants.DSV_RC_EMAILID_AU;
			consultantPassword= TestConstants.DSV_CONSULTANT_PASSWORD_AU;
			pcPassword=TestConstants.DSV_PC_PASSWORD_AU;
			rcPassword = TestConstants.DSV_RC_PASSWORD_AU;
			pwsSuffix = TestConstants.DSV_PWS_SUFFIX_AU;
			cardNumber = TestConstants.DSV_CARD_NUMBER_AU;
			expMonth = TestConstants.DSV_EXPIRY_MONTH_AU;
			expYear = TestConstants.DSV_EXPIRY_YEAR_AU;
			CVV = TestConstants.DSV_SECURITY_CODE_AU;
			addressLine1 = TestConstants.DSV_ADDRESS_LINE_1_AU;
			city = TestConstants.DSV_CITY_AU;
			postalCode = TestConstants.DSV_POSTAL_CODE_AU;
			phoneNumber = TestConstants.DSV_PHONE_NUMBER_AU;
			sponsorWithPWS = TestConstants.DSV_SPONSOR_WITH_PWS_AU;
			editPhoneNumber = TestConstants.DSV_PHONE_NUMBER_FOR_EDIT_AU;
			state = TestConstants.DSV_STATE_AU;
		}
	}

	@BeforeGroups(groups = { "consultant"})
	public void beforeGroupConsultant() throws MalformedURLException{
		randomNum = CommonUtils.getRandomNum(5000, 9999);
		driver.loadApplication();		
		driver.get(driver.getURL()+"/"+country);		
		driver.quickWaitForElementPresent(LOGIN_LINK);
		driver.click(LOGIN_LINK,"login");
		driver.quickWaitForElementPresent(USERNAME_TXTFIELD);
		driver.type(USERNAME_TXTFIELD, consultantEmailId,"username");
		driver.type(PASSWORD_TXTFIELD, consultantPassword,"pass");
		driver.click(LOGIN_BTN,"login");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
		comPWS = driver.getCurrentUrl();
	}

	@BeforeGroups(groups = { "pc" })
	public void beforeGroupPC() throws MalformedURLException{
		randomNum = CommonUtils.getRandomNum(5000, 9999);
		driver.loadApplication();		
		driver.get(driver.getURL()+"/"+country);
		driver.quickWaitForElementPresent(LOGIN_LINK);
		driver.click(LOGIN_LINK,"login");
		driver.quickWaitForElementPresent(USERNAME_TXTFIELD);
		driver.type(USERNAME_TXTFIELD, pcEmailId,"username");
		driver.type(PASSWORD_TXTFIELD, pcPassword,"pass");
		driver.click(LOGIN_BTN,"login");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	@BeforeGroups(groups = {"rc" })
	public void beforeGroupRC() throws MalformedURLException{
		randomNum = CommonUtils.getRandomNum(5000, 9999);
		driver.loadApplication();		
		driver.get(driver.getURL()+"/"+country);
		driver.quickWaitForElementPresent(LOGIN_LINK);
		driver.click(LOGIN_LINK,"login");
		driver.quickWaitForElementPresent(USERNAME_TXTFIELD);
		driver.type(USERNAME_TXTFIELD, rcEmailId,"username");
		driver.type(PASSWORD_TXTFIELD,rcPassword,"pass");
		driver.click(LOGIN_BTN,"login");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	@BeforeGroups(groups = {"nonLogin" })
	public void beforeGroupnonLogin() throws MalformedURLException{
		randomNum = CommonUtils.getRandomNum(5000, 9999);
		driver.loadApplication();		
		logger.info("Application loaded");	
		driver.get(driver.getURL()+"/"+country);
	}

	@AfterGroups(groups = { "consultant"})
	public void afterGroupCons(){
		logout();
		driver.close();
	}

	@AfterGroups(groups = {"pc"})
	public void afterGroupPC(){
		logout();
		driver.close();
	}

	@AfterGroups(groups = {"rc" })
	public void afterGroupRC(){
		logout();
		driver.close();
	}

	@AfterGroups(groups = {"nonLogin"})
	public void afterGroupNonLogin(){
		logout();
		driver.close();
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(Method m) throws MalformedURLException{
		s_assert = new SoftAssert();
		if(driver.getWindowHandles().size()<1){
			driver.loadApplication();		
			logger.info("Application loaded");	
			driver.get(driver.getURL()+"/"+country);	
			Test t = m.getAnnotation(Test.class);
			if(t.groups()[0].contains("consultant"))
				beforeGroupConsultant();
			else if(t.groups()[0].contains("pc"))
				beforeGroupPC();
			else if(t.groups()[0].contains("rc"))
				beforeGroupRC();
		}

	}

	@AfterMethod
	public void tearDownAfterMethod(){

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

	public String getComPWS(){
		return comPWS;
	}

	public void logout(){
		try{
			driver.quickWaitForElementPresent(By.id("account-info-button"));
			driver.findElement(By.id("account-info-button")).click();
			driver.waitForElementPresent(By.linkText("Log out"));
			driver.findElement(By.linkText("Log out")).click();
			logger.info("Logout");		
			driver.pauseExecutionFor(3000);
		}catch(Exception e){

		}
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
