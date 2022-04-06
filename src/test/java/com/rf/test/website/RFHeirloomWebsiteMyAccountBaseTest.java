
package com.rf.test.website;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.ExcelReader;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.pages.website.heirloom.StoreFrontHeirloomConsultantPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomEditPCPerksPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomHomePage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomPCUserPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomPulsePage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomRCUserPage;
import com.rf.pages.website.nscore.NSCore4HomePage;
import com.rf.pages.website.nscore.NSCore4OrdersTabPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomAccountDetailsPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomBillingAndShippingProfilePage;
import com.rf.test.base.RFBaseTest;

/**
 * @author ShubhamMathur RFLegacyStoreFrontWebsiteBaseTest is the super class for all
 *         Legacy desktop Test classes initializes the driver is used for execution.
 *
 */
public class RFHeirloomWebsiteMyAccountBaseTest extends RFBaseTest {
	StringBuilder verificationErrors = new StringBuilder();
	protected String password = null;
	protected String countryId = null;
	protected String prefix=null;
	protected String CVV  = TestConstantsRFL.SECURITY_CODE;

	protected StoreFrontHeirloomHomePage storeFrontHeirloomHomePage;
	protected StoreFrontHeirloomConsultantPage storeFrontHeirloomConsultantPage;
	protected StoreFrontHeirloomPCUserPage storeFrontHeirloomPCUserPage;
	protected StoreFrontHeirloomRCUserPage storeFrontHeirloomRCUserPage;
	protected StoreFrontHeirloomPulsePage storeFrontHeirloomPulsePage;
	protected StoreFrontHeirloomBillingAndShippingProfilePage storeFrontHeirloomBillingAndShippingProfilePage;
	protected StoreFrontHeirloomAccountDetailsPage storeFrontHeirloomAccountDetailsPage;
	protected StoreFrontHeirloomEditPCPerksPage storeFrontHeirloomEditPCPerksPage;
	protected NSCore4HomePage nscore4HomePage;
	protected NSCore4OrdersTabPage nscore4OrdersTabPage;

	protected String RFL_DB = null;
	protected String RFO_DB = null;
	protected String billingName = null;
	protected String cardNumber = null;
	protected String nameOnCard = null;
	protected String expMonth = null;
	protected String expYear = null;
	protected String addressLine1 =  null;
	protected String postalCode = null;
	protected String phnNumber = null;
	protected String regimen = null;
	protected String kitName = null;
	protected String enrollmentType = null;
	protected String phnNumber1 = null;
	protected String phnNumber2 = null;
	protected String phnNumber3 = null;
	protected String firstName =null;
	protected String lastName =null;
	protected String shippingProfileFirstName = null;
	protected String shippingProfileLastName = null;
	protected String billingProfileFirstName = null;
	protected String billingProfileLastName = null;
	protected String gender = null;
	//	protected String consultantEmailID = null;
	protected String userEmailId = null;
	protected boolean shouldFetchDataFromExcelForTokeniation = false;

	private static final By USERNAME_TXT_FIELD_NSCORE4 = By.id("username");
	private static final By PASSWORD_TXT_FIELD_NSCORE4 = By.id("password");
	private static final By USERNAME_TXT_FIELD_NSCORE3 = By.id("txtUsername");
	private static final By PASSWORD_TXT_FIELD_NSCORE3 = By.id("txtPassword");
	private static final By LOGIN_BTN = By.id("btnLogin");


	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(RFHeirloomWebsiteMyAccountBaseTest.class.getName());

	public RFHeirloomWebsiteMyAccountBaseTest() {
		storeFrontHeirloomHomePage = new StoreFrontHeirloomHomePage(driver);
		storeFrontHeirloomConsultantPage = new StoreFrontHeirloomConsultantPage(driver);
		storeFrontHeirloomPCUserPage = new StoreFrontHeirloomPCUserPage(driver);
		storeFrontHeirloomRCUserPage = new StoreFrontHeirloomRCUserPage(driver);
		storeFrontHeirloomPulsePage = new StoreFrontHeirloomPulsePage(driver);
		storeFrontHeirloomBillingAndShippingProfilePage = new StoreFrontHeirloomBillingAndShippingProfilePage(driver);
		storeFrontHeirloomAccountDetailsPage = new StoreFrontHeirloomAccountDetailsPage(driver);
		storeFrontHeirloomEditPCPerksPage = new StoreFrontHeirloomEditPCPerksPage(driver);
		nscore4HomePage = new NSCore4HomePage(driver);
		nscore4OrdersTabPage = new NSCore4OrdersTabPage(driver); 

		billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		cardNumber = TestConstantsRFL.CARD_NUMBER;
		nameOnCard = TestConstantsRFL.FIRST_NAME;
		expMonth = TestConstantsRFL.EXP_MONTH;
		expYear = TestConstantsRFL.EXP_YEAR;
		addressLine1 =  TestConstantsRFL.ADDRESS_LINE1;
		postalCode = TestConstantsRFL.POSTAL_CODE;
		phnNumber = TestConstantsRFL.NEW_ADDRESS_PHONE_NUMBER_US;
		regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		kitName = TestConstantsRFL.KIT_NAME_BIG_BUSINESS;
		enrollmentType = TestConstantsRFL.STANDARD_ENROLLMENT;
		phnNumber1 = TestConstantsRFL.PHONE_NUMBER_1;
		phnNumber2 = TestConstantsRFL.PHONE_NUMBER_2;
		phnNumber3 = TestConstantsRFL.PHONE_NUMBER_3;
		firstName = TestConstantsRFL.FIRST_NAME;
		gender=TestConstantsRFL.GENDER_MALE;
		lastName=TestConstantsRFL.LAST_NAME;
		shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		billingProfileLastName = TestConstantsRFL.DSV_BILLING_PROFILE_LAST_NAME;
	}

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
		RFL_DB = driver.getDBNameRFL();
		RFO_DB = driver.getDBNameRFO();
		String country = driver.getCountry();
		if(country.equalsIgnoreCase("ca"))
			countryId = "40";
		else if(country.equalsIgnoreCase("us"))
			countryId = "236";	
	}

	public void navigateToEditPCPerksPage(){
		String myAccountCategory="Edit PC Perks Cart";
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("/replenishment/review.aspx") ,"Not navigated successfully to Edit PC Perks page");
	}

	public void getUserAndLogin(String user){
		setStoreFrontPassword(driver.getStoreFrontPassword());
		for(int i=1;i<=5;i++){
			if(user.equalsIgnoreCase(TestConstantsRFL.USER_TYPE_CONSULTANT)){
				userEmailId = getRandomUserFromDB(TestConstants.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(userEmailId,password);
			}
			else if(user.equalsIgnoreCase(TestConstantsRFL.USER_TYPE_PC)){
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);					
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
			}

			else if(user.equalsIgnoreCase(TestConstantsRFL.USER_TYPE_RC)){
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);		
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
			}
			if(storeFrontHeirloomHomePage.isLoginFailed()){
				logger.info("Login failed for user="+userEmailId);
				if(driver.isElementVisible(By.id("logindropdownlink"),5)) {
					driver.click(By.id("logindropdownlink"), "Login DropDown");
				}
				continue;
			}
			else
				break;
		}

	}

	public void navigateToBillingProfile(){
		String myAccountCategory="Billing Details";
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("my-account/payment-details") ,"Not navigated successfully to billing profile page");
	}

	public void navigateToManageCRPAndPulse(){
		String myAccountCategory="Manage CRP & PULSE";
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("my-account/manage-crp-pulse-pro") ,"Not navigated successfully to Manage CRP & PULSE page");
	}

	public void navigateToAccountDetails(){
		String myAccountCategory="Account Details";
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("/my-account") ,"Not navigated successfully to account details page");
	}

	public void navigateToShippingProfile(){
		String myAccountCategory="Shipping Details";
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("my-account/address-book") ,"Not navigated successfully to shipping profile page");
	}

	public void checkAndCloseMoreThanOneWindows(){
		Set<String> allWindows = driver.getWindowHandles();
		logger.info("Total windows opened = "+allWindows.size());
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

	@AfterMethod
	public void tearDownAfterMethod(){

	}

	public String getActiveUserFromExcel(String userType){
		String activeUser = null;
		try{
			String sheetName = "Sheet1";
			String filePath = "src/test/resources/com/rf/test/website/storeFront/brandRefresh/USTST4TokenizedCID.xlsx";
			activeUser= ExcelReader.getActiveEmailIdFromExcelSheet(userType,filePath,sheetName);

		}catch(Exception e){
			e.printStackTrace();
		}
		return activeUser;
	}

	public String getInActiveUserFromExcel(String userType){
		String activeUser = null;
		try{
			String sheetName = "Sheet1";
			String filePath = "src/test/resources/com/rf/test/website/storeFront/brandRefresh/USTST4TokenizedCID.xlsx";
			activeUser= ExcelReader.getInActiveEmailIdFromExcelSheet(userType,filePath,sheetName);

		}catch(Exception e){
			e.printStackTrace();
		}
		return activeUser;
	}

	@BeforeClass(alwaysRun=true)
	public void beforeClass(){
		try{
			shouldFetchDataFromExcelForTokeniation = driver.shouldFetchDataFromExcelForTokeniation(); 
		}catch(Exception e){

		}
	}

	/**
	 * @throws Exception
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		try{
			new HtmlLogger().createHtmlLogFile();		
			driver.quit();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setStoreFrontPassword(String pass){
		password=pass;
	}

	public void logout(){
		driver.get(driver.getURL());
		if(driver.isElementVisible(By.id("myaccountdropdownlink"), 3)){
			logger.info("My account drop down visible");			 
			driver.click(By.id("myaccountdropdownlink"), "my account drop down");
			driver.pauseExecutionFor(1000);
			driver.click(By.xpath("//a[text()='Log-Out' or text()='Log Out']"),"Logout");
			driver.pauseExecutionFor(1000);
			driver.waitForPageLoad(); 
		}
		else if(driver.isElementVisible(By.xpath("//a[text()='Log-Out' or text()='Log Out']"))){
			logger.info("My account drop down NOT visible");			 
			driver.click(By.xpath("//a[text()='Log-Out' or text()='Log Out']"),"Logout");
			driver.pauseExecutionFor(1000);
			driver.waitForPageLoad(); 
		}
		else{
			logger.info("user is already logged out");
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

	public String openBIZSite(){			
		RFL_DB = driver.getDBNameRFL();
		RFO_DB = driver.getDBNameRFO();
		String bizPWS=null;
		for(int i=1;i<=15;i++){			
			List<Map<String, Object>> randomConsultantPrefix = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_PREFIX,RFL_DB);
			bizPWS = (String) getValueFromQueryResult(randomConsultantPrefix, "URL");
			bizPWS=bizPWS.replaceAll(".com", ".biz");
			driver.get(bizPWS);
			if(driver.getCurrentUrl().contains("Error")||driver.getCurrentUrl().contains("error")||driver.getCurrentUrl().contains("SiteNotActive")){
				continue;
			}
			else{
				bizPWS = (String) getValueFromQueryResult(randomConsultantPrefix, "URL");
				bizPWS=bizPWS.replaceAll(".com", ".biz");
				break;
			}
		}	
		driver.waitForPageLoad();
		return bizPWS;
	}

	public String openCOMSite(){			
		RFL_DB = driver.getDBNameRFL();
		RFO_DB = driver.getDBNameRFO();
		String comPWS=null;
		//		if(isActiveCOMPWSFound==false){
		for(int i=1;i<=15;i++){			
			List<Map<String, Object>> randomConsultantPrefix = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_PREFIX,RFL_DB);
			comPWS = (String) getValueFromQueryResult(randomConsultantPrefix, "URL");
			comPWS=comPWS.replaceAll(".biz", ".com");
			driver.get(comPWS);
			if(driver.getCurrentUrl().contains("Error")||driver.getCurrentUrl().contains("error")||driver.getCurrentUrl().contains("SiteNotActive")){
				continue;
			}
			else{
				comPWS = (String) getValueFromQueryResult(randomConsultantPrefix, "URL");
				comPWS=comPWS.replaceAll(".biz", ".com");
				break;
			}
		}	
		driver.waitForPageLoad();
		return comPWS;
	}

	public String getRandomUserFromDB(String userType){
		RFL_DB = driver.getDBNameRFL();
		RFO_DB = driver.getDBNameRFO();
		List<Map<String, Object>> randomUserList = null;
		if(userType.equals(TestConstantsRFL.USER_TYPE_CONSULTANT)){
			randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_CONSULTANT_HAS_CRP_HAS_PULSE_SUBMITTED_ORDERS_ACTIVE_RFL_4191, RFL_DB); 
			return (String) getValueFromQueryResult(randomUserList, "EmailAddress");
		}
		else if(userType.equals(TestConstantsRFL.USER_TYPE_PC)){
			randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
			return (String) getValueFromQueryResult(randomUserList, "UserName");
		}
		else if(userType.equals(TestConstantsRFL.USER_TYPE_RC)){
			randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS, RFL_DB);
			return (String) getValueFromQueryResult(randomUserList, "UserName");
		}
		return null;

	}

	public String getRandomSponsorFromDB(){
		List<Map<String, Object>> randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_EMAILID,RFL_DB);
		return  String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountNumber"));
	}

	public void navigateToManageMyAccountPage(){
		String myAccountCategory="Manage My Account";
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("/myoffice/pcperksstatus") ,"Not navigated successfully to Manage My Account page");
	}

	public String getPCUserHasBillingProfileWithInvalidCardAndLogin(){
		RFL_DB = driver.getDBNameRFL();
		RFO_DB = driver.getDBNameRFO();
		List<Map<String, Object>> randomUserList = null;
		setStoreFrontPassword(driver.getStoreFrontPassword());
		randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_PC_HAS_BILLING_PROFILE_WITH_INVALID_CARD, RFL_DB);
		String emailID = (String) getValueFromQueryResult(randomUserList, "EmailAddress");
		storeFrontHeirloomHomePage.loginAsPCUser(emailID,password);
		return emailID;
	}

	public void navigateToOrderHistory(){
		String myAccountCategory="Order History";
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("/orderhistory") ,"Not navigated successfully to order history page");
	}

	public void loginToNSCApplication(){
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


}
