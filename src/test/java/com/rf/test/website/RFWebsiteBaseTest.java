package com.rf.test.website;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.storeFront.StoreFrontAccountInfoPage;
import com.rf.pages.website.storeFront.StoreFrontAccountTerminationPage;
import com.rf.pages.website.storeFront.StoreFrontBillingInfoPage;
import com.rf.pages.website.storeFront.StoreFrontCartAutoShipPage;
import com.rf.pages.website.storeFront.StoreFrontConsultantPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.pages.website.storeFront.StoreFrontOrdersPage;
import com.rf.pages.website.storeFront.StoreFrontPCUserPage;
import com.rf.pages.website.storeFront.StoreFrontRCUserPage;
import com.rf.pages.website.storeFront.StoreFrontReportOrderComplaintPage;
import com.rf.pages.website.storeFront.StoreFrontReportProblemConfirmationPage;
import com.rf.pages.website.storeFront.StoreFrontShippingInfoPage;
import com.rf.pages.website.storeFront.StoreFrontUpdateCartPage;
import com.rf.test.base.RFBaseTest;

/**
 * @author ShubhamMathur RFBillingShippingPageWebsiteBaseTest is the super class for all
 *         desktop Test classes initializes the driver is used for execution.
 *
 */
public class RFWebsiteBaseTest extends RFBaseTest {
	StringBuilder verificationErrors = new StringBuilder();
	protected String password = null;
	protected String countryId = null;
	protected boolean runBaseURLOrLogoutExecutionCode = true;
	protected String sponsor = null;
	private Actions actions;
	protected String countryDDValue = null;
	protected String line1 = null;

	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(RFWebsiteBaseTest.class.getName());

	protected StoreFrontHomePage storeFrontHomePage;
	protected StoreFrontConsultantPage storeFrontConsultantPage;
	protected StoreFrontBillingInfoPage storeFrontBillingInfoPage;
	protected StoreFrontOrdersPage storeFrontOrdersPage;
	protected StoreFrontCartAutoShipPage storeFrontCartAutoShipPage;
	protected StoreFrontUpdateCartPage storeFrontUpdateCartPage;
	protected StoreFrontPCUserPage storeFrontPCUserPage;
	protected StoreFrontAccountInfoPage storeFrontAccountInfoPage;
	protected StoreFrontAccountTerminationPage storeFrontAccountTerminationPage;
	protected StoreFrontRCUserPage storeFrontRCUserPage;;
	protected StoreFrontShippingInfoPage storeFrontShippingInfoPage;
	protected StoreFrontReportOrderComplaintPage storeFrontReportOrderComplaintPage;
	protected StoreFrontReportProblemConfirmationPage storeFrontReportProblemConfirmationPage;

	protected String csCommisionAdminUserName=null;
	protected String csCommisionAdminPassword=null;
	protected String csSalesSupervisoryUserName=null;
	protected String csSalesSupervisoryPassword=null;
	protected String kitName = null;
	protected String regimenName = null;
	protected String enrollmentType = null;
	protected String addressLine1 = null;
	protected String city = null;
	protected String postalCode = null;
	protected String phoneNumber = null;
	protected String country = null;
	protected String state = null;
	protected String env = null;
	protected String RFO_DB = null;
	protected String cardNumber = null;
	protected String cardExpMonth = null;
	protected String cardExpYear = null;
	protected String CVV = null;

	protected String consultantFirstName = TestConstants.CONSULTANT_FIRST_NAME;
	protected String consultantLastName = null;

	protected String pcFirstName = TestConstants.PC_FIRST_NAME;
	protected String pcLastName = null;

	protected String rcFirstName = TestConstants.RC_FIRST_NAME;
	protected String rcLastName = null;

	public RFWebsiteBaseTest() {
		storeFrontHomePage = new StoreFrontHomePage(driver);	
		storeFrontConsultantPage = new StoreFrontConsultantPage(driver);
		storeFrontBillingInfoPage = new StoreFrontBillingInfoPage(driver);	
		storeFrontOrdersPage = new StoreFrontOrdersPage(driver);
		storeFrontCartAutoShipPage = new StoreFrontCartAutoShipPage(driver);
		storeFrontUpdateCartPage = new StoreFrontUpdateCartPage(driver);
		storeFrontPCUserPage = new StoreFrontPCUserPage(driver);
		storeFrontAccountInfoPage = new StoreFrontAccountInfoPage(driver);
		storeFrontAccountTerminationPage = new StoreFrontAccountTerminationPage(driver);
		storeFrontRCUserPage = new StoreFrontRCUserPage(driver);
		storeFrontShippingInfoPage = new StoreFrontShippingInfoPage(driver);
		storeFrontReportOrderComplaintPage = new StoreFrontReportOrderComplaintPage(driver);
		storeFrontReportProblemConfirmationPage =  new StoreFrontReportProblemConfirmationPage(driver);
	}

	@BeforeSuite(alwaysRun=true)
	public void setUp() throws Exception {
		driver.loadApplication();
		logger.info("Application loaded");                                                            
		driver.setDBConnectionString();
		if(driver.getURL().contains("cscockpit") && driver.getEnvironment().equalsIgnoreCase("stg2")){
			csCommisionAdminUserName=TestConstants.CS_COMMISION_ADMIN_STG2_USERNAME;
			csCommisionAdminPassword=TestConstants.CS_AGENT_PASSWORD;
			csSalesSupervisoryUserName=TestConstants.CS_SALES_SUPERVISORY_STG2_USERNAME;
			csSalesSupervisoryPassword=TestConstants.CS_AGENT_PASSWORD;
		}
	}

	public void navigateToStoreFrontBaseURL(){
		if(country.equalsIgnoreCase("au")){
			driver.get(driver.getURL()+"."+country);
		}
		else
			driver.get(driver.getURL()+"/"+country);
	}

	public void logoutFromAustralia(){
		if(driver.isElementPresent(By.xpath("//a[contains(text(),'RETURN TO ME PAGE')]"))){
			driver.click(By.xpath("//a[contains(text(),'RETURN TO ME PAGE')]"),"");
			driver.waitForPageLoad();
		}
		if(driver.isElementPresent(By.xpath("//div[@class='profile-image']"))){
			driver.moveToELement(By.xpath("//div[@class='profile-image']"));
			logger.info("hover on profile name");
			driver.click(By.xpath("//div[contains(@class,'profile-header')]//a[text()='Sign Out']"),"sign out link under profile name");
			driver.waitForPageLoad();
			driver.pauseExecutionFor(1000);
		}
	}

	@BeforeClass
	public void beforeClass(){
		country = driver.getCountry();
		env= driver.getEnvironment();
		RFO_DB = driver.getDBNameRFO();
		if(country.equalsIgnoreCase("CA")){     
			addressLine1 = TestConstants.ADDRESS_LINE_1_CA;
			city = TestConstants.CITY_CA;
			postalCode = TestConstants.POSTAL_CODE_CA;
			phoneNumber = TestConstants.PHONE_NUMBER_CA;
			state = TestConstants.PROVINCE_CA;
			countryId = "40";
			countryDDValue= TestConstants.COUNTRY_DD_VALUE_CA;
			line1 = "Line 1";
		}else if(country.equalsIgnoreCase("US")){   
			addressLine1 = TestConstants.ADDRESS_LINE_1_US;
			city = TestConstants.CITY_US;
			state = TestConstants.STATE_US;
			postalCode = TestConstants.NEW_ADDRESS_POSTAL_CODE_US;
			phoneNumber = TestConstants.NEW_ADDRESS_PHONE_NUMBER_US;
			countryId = "236";
			countryDDValue= TestConstants.COUNTRY_DD_VALUE_US;
		} else if(country.equalsIgnoreCase("AU")){   
			addressLine1 = TestConstants.ADDRESS_LINE_1_AU;
			city = TestConstants.CITY_AU;
			state = TestConstants.STATE_AU;
			postalCode = TestConstants.POSTAL_CODE_AU;
			phoneNumber = TestConstants.PHONE_NUMBER_AU;
			countryId = "14";
			countryDDValue= TestConstants.COUNTRY_DD_VALUE_AU;
			line1 = "Address 1";
		}

		consultantLastName = driver.getEnvironment();
		pcLastName = driver.getEnvironment()+driver.getCountry();
		rcLastName = driver.getEnvironment()+driver.getCountry();	
		setStoreFrontPassword(driver.getStoreFrontPassword());
		sponsor= getSponsorFromDatabase();
		regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
		kitName = TestConstants.KIT_NAME_PERSONAL;
		enrollmentType= TestConstants.EXPRESS_ENROLLMENT;
		cardNumber=TestConstants.CARD_NUMBER;
		cardExpMonth=TestConstants.CARD_EXPIRY_MONTH;
		cardExpYear=TestConstants.CARD_EXP_YEAR;
		CVV=TestConstants.SECURITY_CODE;
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(){
		s_assert = new SoftAssert();
		String country = driver.getCountry();
		if(driver.getURL().contains("cscockpit") && country.equalsIgnoreCase("au")){
			String url=driver.getURL().replaceAll("\\.com","\\.com.au");
			driver.get(url);
		}
		else if(driver.getURL().contains("cscockpit")||driver.getURL().contains("salesforce")==true){                 
			driver.get(driver.getURL());
		}
		else{
			if(country.equalsIgnoreCase("ca")){
				driver.get(driver.getURL()+"/"+country);
			}else{
				if(country.equalsIgnoreCase("au")){
					driver.get(driver.getURL()+"."+country);
				}
			}
		}
		if(driver.getURL().contains("cscockpit")==true||driver.getURL().contains("salesforce")==true){  

		}else{
			if(isLogoutBtnPresent())
				logout();
		} 
		if(country.equalsIgnoreCase("ca"))
			countryId = "40";
		else if(country.equalsIgnoreCase("us"))
			countryId = "236";
		else if(country.equalsIgnoreCase("au"))
			countryId = "14";            
		if(driver.getURL().contains("cscockpit")==false && (driver.getURL().contains("salesforce")==false && driver.getCurrentUrl().contains(country)==false)){
			try{
				driver.selectCountry(country);
			}catch(Exception e){

			}
		}
		setStoreFrontPassword(driver.getStoreFrontPassword());
	}


	@AfterMethod
	public void tearDownAfterMethod(){
		driver.manage().deleteAllCookies();
		if(driver.getURL().contains("salesforce")==true){
			try{
				crmLogout();
				Alert alert = driver.switchTo().alert();
				alert.dismiss();

			}catch(NoAlertPresentException Ex){

			}
		}else{
			if(driver.getURL().contains("au")==true){
				logoutFromAustralia();
			}
		}
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

	public void crmLogout(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.id("userNavLabel"));
		driver.click(By.id("userNavLabel"),"");
		driver.waitForElementPresent(By.id("app_logout"));
		driver.click(By.id("app_logout"),"logout");
		driver.pauseExecutionFor(3000);
	}

	public void crmLogoutFromHome(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.id("userNavLabel"));
		driver.click(By.id("userNavLabel"),"");
		driver.waitForElementPresent(By.xpath("//a[@title='Logout']"));
		driver.click(By.xpath("//a[@title='Logout']"),"logout");
	}

	public String getRandomABN(){
		while (true) {
			long numb = (long)(Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 11 digit number.
			if (String.valueOf(numb).length() == 11)
				return String.valueOf(numb);
		}

	}

	public boolean isLogoutBtnPresent(){
		int listSize = driver.findElements(By.id("account-info-button")).size();
		if(listSize>0)
			return true;
		else
			return false;
	}

	public void retryLoginWithOtherUsers(){

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
			logger.info("The sie of map is "+map.size());
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

	public String getSponsorFromDatabase(){
		RFO_DB = driver.getDBNameRFO();
		List<Map<String, Object>> randomConsultantList=null;
		String accountNumber=null;
		boolean flag=false;
		while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);  
			accountNumber = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountNumber"));
			if(StringUtils.isEmpty(accountNumber)|| accountNumber==null ){
				randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);  
				accountNumber = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountNumber"));
			}
			else
				break;
		}
		return accountNumber;
	}

	public void logout(){
		StoreFrontHomePage storeFrontHomePage = new StoreFrontHomePage(driver);
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		try	{
			driver.waitForElementPresent(By.id("account-info-button"));
			driver.clickByJS(RFWebsiteDriver.driver, By.id("account-info-button"),"Your account info");//(By.id("account-info-button"),"Your account info");
			//driver.waitForElementPresent(By.linkText("Log out"));
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[contains(@id,'account-info')]//a[text()='Log out']"),"logout");
			driver.waitForPageLoad();
			driver.pauseExecutionFor(5000);
		}
		catch(Exception e)	{
		}
	}

}
