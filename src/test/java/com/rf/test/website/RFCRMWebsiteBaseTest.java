package com.rf.test.website;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.utils.QueryUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.crm.CRMAccountDetailsPage;
import com.rf.pages.website.crm.CRMContactDetailsPage;
import com.rf.pages.website.crm.CRMHomePage;
import com.rf.pages.website.crm.CRMLoginPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.test.base.RFBaseTest;

/**
 * @author ShubhamMathur RFBillingShippingPageWebsiteBaseTest is the super class for all
 *         desktop Test classes initializes the driver is used for execution.
 *
 */
public class RFCRMWebsiteBaseTest extends RFBaseTest {
	StringBuilder verificationErrors = new StringBuilder();
	protected String password = null;
	protected String countryId = null;
	protected String CRMUserName=null;
	protected String CRMPassword=null;
	protected String addressLine = null;
	protected String city = null;
	protected String postal = null;
	protected String province = null;
	protected String phoneNumber = null;
	protected String countryName = null;
	protected String RFO_DB = null;
	protected String consultantEmailID = null;
	protected String pcEmailID = null;
	protected String pcUsername = null;
	protected String accountID = null;
	protected String rcEmailID = null;
	protected String rcUsername = null;
	protected String pcAccountID = null;
	protected String rcAccountID = null;
	protected CRMLoginPage crmLoginpage;
	protected CRMHomePage crmHomePage;
	protected CRMAccountDetailsPage crmAccountDetailsPage; 
	protected CRMContactDetailsPage crmContactDetailsPage;
	protected StoreFrontHomePage storeFrontHomePage;
	protected String CRMSalesSupportCoordinatorUserName=null;
	protected String CRMSalesSupportCoordinatorPassword=null;
	protected String CRMAUSalesSupportCoordinatorUserName=null;
	protected String CRMAUSalesSupportCoordinatorPassword=null;
	protected String CRMComplianceUserName=null;
	protected String CRMCompliancePassword=null;
	protected String CRMSalesSupportManagerUserName=null;
	protected String CRMSalesSupportManagerPassword=null;
	protected String CRMRFConnectionUserName=null;
	protected String CRMRFConnectionPassword=null;
	protected String CRMSalesSupportLeadUserName=null;
	protected String CRMSalesSupportLeadPassword=null;
	protected String CRMTeletechCoordinatorUserName=null;
	protected String CRMTeletechCoordinatorPassword=null;
	protected String CRMTheConnectionUserName=null;
	protected String CRMTheConnectionPassword=null;
	private static List<Map<String, Object>> randomConsultantList =  null;
	private static List<Map<String, Object>> randomConsultantUsernameList=  null;
	private static List<Map<String, Object>> randomPCUserList=  null;
	private static List<Map<String, Object>> randomPCUsernameList=  null;
	private static List<Map<String, Object>> randomRCUserList=  null;
	private static List<Map<String, Object>> randomRCUsernameList=  null;

	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(RFCRMWebsiteBaseTest.class.getName());

	private final By LOGIN_BOX_LOCATION = By.id("login_form");
	private final By USERNAME_TXTFLD_LOC = By.id("username");
	private final By PASSWORD_TXTFLD_LOC = By.id("password");
	private final By LOGIN_BTN_LOC = By.id("Login");

	/**
	 * @throws Exception
	 *             setup function loads the driver and environment and baseUrl
	 *             for the tests
	 */
	@BeforeSuite(alwaysRun=true)
	public void setUp() throws Exception {
		driver.loadApplication();                               
		logger.info("Application loaded");                                                            
		driver.setDBConnectionString();                
	}

	@BeforeClass
	public void beforeClass(){
		crmHomePage = new CRMHomePage(driver);
		if(driver.getCountry().equalsIgnoreCase("ca")){
			addressLine = TestConstants.ADDRESS_LINE_1_CA;
			city = TestConstants.CITY_CA;
			postal = TestConstants.POSTAL_CODE_CA;
			province = TestConstants.PROVINCE_ALBERTA;
			phoneNumber = TestConstants.PHONE_NUMBER_CA;
			countryName = TestConstants.COUNTRY_DD_VALUE_CA;
		}else{
			addressLine = TestConstants.ADDRESS_LINE_1_AU;
			city = TestConstants.CITY_AU;
			postal = TestConstants.POSTAL_CODE_AU;
			province = TestConstants.STATE_AU;
			phoneNumber = TestConstants.PHONE_NUMBER_AU;
			countryName = TestConstants.COUNTRY_VALUE_AU;
		}
		driver.get(driver.getURL());
		if(driver.getEnvironment().equalsIgnoreCase("stg3")){
			CRMUserName=TestConstants.CRM_LOGIN_USERNAME_STG3;
			CRMPassword = TestConstants.CRM_LOGIN_PASSWORD_STG3;  
		}
		if(driver.getEnvironment().equalsIgnoreCase("stg2")){
			CRMUserName=TestConstants.CRM_LOGIN_USERNAME_STG2;
			CRMPassword = TestConstants.CRM_LOGIN_PASSWORD_STG2;
			CRMSalesSupportCoordinatorUserName = TestConstants.CRM_SALES_SUPPORT_COORDINATOR_USERNAME_STG2;
			CRMSalesSupportCoordinatorPassword = TestConstants.CRM_SALES_SUPPORT_COORDINATOR_PASSWORD_STG2;
			CRMAUSalesSupportCoordinatorUserName = TestConstants.CRM_AU_SALES_SUPPORT_COORDINATOR_USERNAME_STG2;
			CRMAUSalesSupportCoordinatorPassword = TestConstants.CRM_AU_SALES_SUPPORT_COORDINATOR_PASSWORD_STG2;
			CRMComplianceUserName = TestConstants.CRM_COMPLIANCE_USERNAME_STG2;
			CRMCompliancePassword = TestConstants.CRM_COMPLIANCE_PASSWORD_STG2;
			CRMSalesSupportManagerUserName = TestConstants.CRM_SALES_SUPPORT_MANAGER_USERNAME_STG2;
			CRMSalesSupportManagerPassword = TestConstants.CRM_SALES_SUPPORT_MANAGER_PASSWORD_STG2;
			CRMRFConnectionUserName = TestConstants.CRM_RF_CONNECTION_USERNAME_STG2;
			CRMRFConnectionPassword = TestConstants.CRM_RF_CONNECTION_PASSWORD_STG2;
			CRMSalesSupportLeadUserName = TestConstants.CRM_SALES_SUPPORT_LEAD_USERNAME_STG2;
			CRMSalesSupportLeadPassword = TestConstants.CRM_SALES_SUPPORT_LEAD_PASSWORD_STG2;
			CRMTeletechCoordinatorUserName = TestConstants.CRM_TELETECH_COORDINATOR_USERNAME_STG2;
			CRMTeletechCoordinatorPassword = TestConstants.CRM_TELETECH_COORDINATOR_PASSWORD_STG2;
			CRMTheConnectionUserName = TestConstants.CRM_THE_CONNECTION_USERNAME_STG2;
			CRMTheConnectionPassword = TestConstants.CRM_THE_CONNECTION_PASSWORD_STG2;
		}
		//loginUser(CRMUserName, CRMPassword);
		String country = driver.getCountry();               
		if(country.equalsIgnoreCase("ca"))
			countryId = "40";
		else if(country.equalsIgnoreCase("us"))
			countryId = "236";    
		else if(country.equalsIgnoreCase("au"))
			countryId = "14"; 
		setStoreFrontPassword(driver.getStoreFrontPassword());
	}


	@BeforeMethod
	public void initiateSoftAssertObject(){
		s_assert = new SoftAssert();
		try{
			Alert alert = driver.switchTo().alert();
		}catch(Exception e){

		}
		crmAccountDetailsPage = new CRMAccountDetailsPage(driver);
		crmAccountDetailsPage.closeAllOpenedTabs();
		driver.navigate().refresh();
		driver.pauseExecutionFor(2000);
		logger.info("page refreshed");
	}

	@AfterClass(alwaysRun = true)
	public void logoutAfterClass() throws Exception {
		crmLogout();
	}

	public void loginUser(String username, String password){
		driver.waitForElementPresent(LOGIN_BOX_LOCATION);
		driver.type(USERNAME_TXTFLD_LOC, username,"username");
		driver.type(PASSWORD_TXTFLD_LOC, password,"password");		
		logger.info("login username is: "+username);
		logger.info("login password is: "+password);
		driver.click(LOGIN_BTN_LOC,"login button");
		closeAllOpenedTabs();
	}

	public void closeAllOpenedTabs(){
		driver.switchTo().defaultContent();
		int totalOpenedTabs = 0;
		totalOpenedTabs = driver.findElements(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]")).size();
		for(int count=1;count<=3;count++){
			logger.info("total opened tabs = "+totalOpenedTabs);
			Actions actions = new Actions(RFWebsiteDriver.driver);
			for(int i=totalOpenedTabs;i>=1;i--){
				//driver.waitForElementPresent(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]["+i+"]/descendant::a[@class='x-tab-strip-close']"));
				actions.moveToElement(driver.findElement(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]["+i+"]/descendant::a[@class='x-tab-strip-close']"))).pause(500).click().build().perform();
				//driver.click(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]["+i+"]/descendant::a[@class='x-tab-strip-close']"));
				driver.pauseExecutionFor(1000);
				if(driver.isElementPresent(By.xpath("//button[text()='Save']/following::button[1]"))){
					driver.click(By.xpath("//button[text()='Save']/following::button[1]"),"Don't save button");
				}
				driver.pauseExecutionFor(1000);
			}
			totalOpenedTabs = driver.findElements(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]")).size();
			if(totalOpenedTabs==0){
				break;
			}else{
				continue;
			}
		}
		logger.info("All tabs closed");
	}

	public String getActiveEmailIdFromDB(String userType){
		for(int i=1;i<=5;i++){
			crmAccountDetailsPage = new CRMAccountDetailsPage(driver);
			crmAccountDetailsPage.closeAllOpenedTabs();
			if(userType.equalsIgnoreCase("Consultant")){
				accountID = QueryUtils.getRandomActiveConsultantAccountIdFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
				//accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
				randomConsultantUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID),RFO_DB);
				consultantEmailID = String.valueOf(getValueFromQueryResult(randomConsultantUsernameList, "EmailAddress"));
				crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
				crmHomePage.switchToExtFrame(1);
				if(driver.isElementPresent(By.xpath("//div[@id='Account_body']/descendant::*[text()='"+userType+"'][1]/following::*[text()='Active'][1]/preceding::a[1]"))==true){
					logger.info("Consultant is "+consultantEmailID);
					return consultantEmailID;
				}
			}
			else if(userType.equalsIgnoreCase("Preferred Customer")){
				randomPCUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_AUTOSHIPS_RFO,countryId),RFO_DB);
				pcUsername = (String) getValueFromQueryResult(randomPCUserList, "UserName");
				pcAccountID = String.valueOf(getValueFromQueryResult(randomPCUserList, "AccountID"));
				randomPCUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,pcAccountID),RFO_DB);
				pcEmailID = String.valueOf(getValueFromQueryResult(randomPCUsernameList, "EmailAddress"));
				crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
				crmHomePage.switchToExtFrame(1);
				if(driver.isElementPresent(By.xpath("//div[@id='Account_body']/descendant::*[text()='"+userType+"'][1]/following::*[text()='Active'][1]/preceding::a[1]"))==true){
					logger.info("PC is "+pcEmailID);
					return pcEmailID;
				}
			}

			else if(userType.equalsIgnoreCase("Retail Customer")){
				randomRCUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId),RFO_DB);
				rcUsername = (String) getValueFromQueryResult(randomRCUserList, "UserName");
				rcAccountID = String.valueOf(getValueFromQueryResult(randomRCUserList, "AccountID"));
				randomRCUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,rcAccountID),RFO_DB);
				rcEmailID = String.valueOf(getValueFromQueryResult(randomRCUsernameList, "EmailAddress"));
				crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
				crmHomePage.switchToExtFrame(1);
				if(driver.isElementPresent(By.xpath("//div[@id='Account_body']/descendant::*[text()='"+userType+"'][1]/following::*[text()='Active'][1]/preceding::a[1]"))==true){
					logger.info("RC is "+rcEmailID);
					return rcEmailID;
				}
			}

		}
		return null;

	}

	public String getActiveEmailIdHavingContactsFromDB(String userType){
		for(int i=1;i<=5;i++){
			crmAccountDetailsPage = new CRMAccountDetailsPage(driver);
			crmAccountDetailsPage.closeAllOpenedTabs();
			if(userType.equalsIgnoreCase("Consultant")){
				accountID = QueryUtils.getRandomActiveConsultantAccountIdFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
				//accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
				randomConsultantUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID),RFO_DB);
				consultantEmailID = String.valueOf(getValueFromQueryResult(randomConsultantUsernameList, "EmailAddress"));
				crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
				crmHomePage.switchToExtFrame(1);
				if(driver.isElementPresent(By.xpath("//div[@id='Contact_body']//tr[@class='headerRow']//a[contains(text(),'Account Name')]/following::tr[1]/th/a"))==true){
					logger.info("Consultant is "+consultantEmailID);
					return consultantEmailID;
				}
			}
			else if(userType.equalsIgnoreCase("Preferred Customer")){
				randomPCUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_AUTOSHIPS_RFO,countryId),RFO_DB);
				pcUsername = (String) getValueFromQueryResult(randomPCUserList, "UserName");
				pcAccountID = String.valueOf(getValueFromQueryResult(randomPCUserList, "AccountID"));
				randomPCUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,pcAccountID),RFO_DB);
				pcEmailID = String.valueOf(getValueFromQueryResult(randomPCUsernameList, "EmailAddress"));
				crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
				crmHomePage.switchToExtFrame(1);
				if(driver.isElementPresent(By.xpath("//div[@id='Contact_body']//tr[@class='headerRow']//a[contains(text(),'Account Name')]/following::tr[1]/th/a"))==true){
					logger.info("PC is "+pcEmailID);
					return pcEmailID;
				}
			}

			else if(userType.equalsIgnoreCase("Retail Customer")){
				randomRCUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId),RFO_DB);
				rcUsername = (String) getValueFromQueryResult(randomRCUserList, "UserName");
				rcAccountID = String.valueOf(getValueFromQueryResult(randomRCUserList, "AccountID"));
				randomRCUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,rcAccountID),RFO_DB);
				rcEmailID = String.valueOf(getValueFromQueryResult(randomRCUsernameList, "EmailAddress"));
				crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
				crmHomePage.switchToExtFrame(1);
				if(driver.isElementPresent(By.xpath("//div[@id='Contact_body']//tr[@class='headerRow']//a[contains(text(),'Account Name')]/following::tr[1]/th/a"))==true){
					logger.info("RC is "+rcEmailID);
					return rcEmailID;
				}
			}

		}
		return null;

	}

	/**
	 * @throws Exception
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		//crmLogout();
		new HtmlLogger().createHtmlLogFile();                 
		driver.quit();

	}

	public void setStoreFrontPassword(String pass){
		password=pass;
	}

	public void crmLogout(){
		driver.switchTo().defaultContent();
		driver.click(By.id("userNavLabel"),"");
		driver.click(By.id("app_logout"),"Logout");
	}

	public void crmLogoutFromHome(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.id("userNavLabel"));
		driver.click(By.id("userNavLabel"),"");
		driver.click(By.xpath("//a[@title='Logout']"),"Logout");
	}

	public void logout(){
		StoreFrontHomePage storeFrontHomePage = new StoreFrontHomePage(driver);
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		driver.click(By.id("account-info-button"),"Your account info");
		driver.click(By.linkText("Log out"),"Logout");
		driver.pauseExecutionFor(3000);
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
