package com.rf.test.website;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.pages.website.crm.dsv.DSVCRMHomePage;
import com.rf.pages.website.crm.dsv.DSVCRMRFWebsiteBasePage;
import com.rf.test.base.RFBaseTest;

public class RFDSVCRMWebsiteBaseTest extends RFBaseTest{
	StringBuilder verificationErrors = new StringBuilder();
	private String countryId = null;
	protected String consultantEmailId = null;
	protected String pcEmailId = null;
	protected String rcEmailId = null;	
	protected String consultantPassword = null;

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
	protected String countryShortName =TestConstants.DSV_COUNTRY_SHORT_NAME_CA;
	protected String state=null;
	protected int randomNum=0000;
	protected String stateShortName=null;
	protected DSVCRMRFWebsiteBasePage dsvCRMRFWebsiteBasePage;
	protected DSVCRMHomePage dsvCRMHomePage;
	protected String accountNameForConsultant=null;
	protected String shippingFirstName=null;
	protected String shippingLastName=null;
	protected String shippingProfileName=null;
	protected String shippingProfileNameUpdated=null;
	protected String accountNameForRC=null;
	protected String accountNameForPC=null;
	protected int randomNum1 = 0;
	protected int randomNum2 = 0;
	
	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(RFDSVCRMWebsiteBaseTest.class.getName());
	
	private final By LOGIN_BOX_LOCATION = By.id("login_form");
	private final By USERNAME_TXTFLD_LOC = By.id("username");
	private final By PASSWORD_TXTFLD_LOC = By.id("password");
	private final By LOGIN_BTN_LOC = By.id("Login");
	private final By SEARCH_TEXT_BOX_LOC = By.id("phSearchInput");
	
	 public RFDSVCRMWebsiteBaseTest() {
		// TODO Auto-generated constructor stub
		 dsvCRMRFWebsiteBasePage = new DSVCRMRFWebsiteBasePage(driver);
		 dsvCRMHomePage = new DSVCRMHomePage(driver);
	}
	 
	
	/**
	 * @throws MalformedURLException 
	 * @throws Exception
	 *             setup function loads the driver and environment and baseUrl
	 *             for the tests
	 */
	@BeforeSuite(alwaysRun=true)
	public void setUp() throws Exception {
		country = driver.getCountry();
		randomNum1 = CommonUtils.getRandomNum(1000, 9999);
		randomNum2 = CommonUtils.getRandomNum(1000, 9999);
		accountNameForConsultant="RFTestC"+randomNum1+" "+"RFTestU"+randomNum2+"CRM";
		shippingFirstName=TestConstants.DSV_FIRST_NAME_CRM;
		shippingLastName=""+randomNum1;
		shippingProfileName = shippingFirstName+" "+shippingLastName;
		shippingProfileNameUpdated = TestConstants.DSV_FIRST_NAME_CRM+" "+randomNum2;
		accountNameForRC="RFTestR"+randomNum+" "+"RFTestC"+randomNum2+"CRM";
		accountNameForPC="RFTestP"+randomNum1+" "+"RFTestC"+randomNum2+"CRM";
		
		sponsorWithPWS_US = TestConstants.DSV_SPONSOR_WITH_PWS_US;
		if(country.equalsIgnoreCase("ca")){
			countryId = "40";
			consultantEmailId = TestConstants.DSV_CONSULTANT_EMAILID_CA;
			pcEmailId = TestConstants.DSV_PC_EMAILID_CA;
			rcEmailId = TestConstants.DSV_RC_EMAILID_CA;
			cardNumber = TestConstants.DSV_CARD_NUMBER_CA;
			expMonth = TestConstants.DSV_EXPIRY_MONTH_CA;
			expYear = TestConstants.DSV_EXPIRY_YEAR_CA;
			CVV = TestConstants.DSV_SECURITY_CODE_CA;
			addressLine1 = TestConstants.DSV_ADDRESS_LINE_1_CA;
			city = TestConstants.DSV_CITY_CA;
			postalCode = TestConstants.DSV_POSTAL_CODE_CA;
			phoneNumber = TestConstants.DSV_PHONE_NUMBER_CA;
			editPhoneNumber = TestConstants.DSV_PHONE_NUMBER_FOR_EDIT_CA;
			state = TestConstants.DSV_STATE_CA;
			countryShortName =TestConstants.DSV_COUNTRY_SHORT_NAME_CA;
			stateShortName=TestConstants.DSV_STATE_CA_SHORT_NAME;

		}
		else if(country.equalsIgnoreCase("au")){
			countryId = "14";	
			consultantEmailId = TestConstants.DSV_CONSULTANT_EMAILID_AU;
			pcEmailId = TestConstants.DSV_PC_EMAILID_AU;
			rcEmailId = TestConstants.DSV_RC_EMAILID_AU;
			cardNumber = TestConstants.DSV_CARD_NUMBER_AU;
			expMonth = TestConstants.DSV_EXPIRY_MONTH_AU;
			expYear = TestConstants.DSV_EXPIRY_YEAR_AU;
			CVV = TestConstants.DSV_SECURITY_CODE_AU;
			addressLine1 = TestConstants.DSV_ADDRESS_LINE_1_AU;
			city = TestConstants.DSV_CITY_AU;
			postalCode = TestConstants.DSV_POSTAL_CODE_AU;
			phoneNumber = TestConstants.DSV_PHONE_NUMBER_AU;
			editPhoneNumber = TestConstants.DSV_PHONE_NUMBER_FOR_EDIT_AU;
			state = TestConstants.DSV_STATE_AU;
			countryShortName =TestConstants.DSV_COUNTRY_SHORT_NAME_AU;
			stateShortName=TestConstants.DSV_STATE_SHORT_AU;
		}
	}
	
	@BeforeClass
	public void beforeClassSetupApplication() throws MalformedURLException {
		driver.loadApplication();	
		driver.get(driver.getURL());
		loginUser(TestConstants.DSV_CRM_USERNAME, TestConstants.DSV_CRM_PASSWORD);
		driver.waitForPageLoad();
	}
	
	@BeforeMethod
	public void beforeMethodSetupData() {
		 s_assert = new SoftAssert();
		closeAllOpenedSubTabs();
	}
	
	@BeforeGroups(groups = { "consultant"})
	public void beforeGroupConsultant() throws MalformedURLException{
		enterTextInSearchFieldAndHitEnter(consultantEmailId);
		clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
	}

	@BeforeGroups(groups = { "pc" })
	public void beforeGroupPC() throws MalformedURLException{
		closeAllOpenedTabs();
		enterTextInSearchFieldAndHitEnter(pcEmailId);
		clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
	}

	@BeforeGroups(groups = {"rc" })
	public void beforeGroupRC() throws MalformedURLException{
		closeAllOpenedTabs();
		enterTextInSearchFieldAndHitEnter(rcEmailId);
		clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
	}	
	
	/**
	 * @throws Exception
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		new HtmlLogger().createHtmlLogFile();		
		driver.quit();
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
	
	public void closeAllOpenedSubTabs() {
		driver.switchTo().defaultContent();
		int totalOpenedTabs = 0;
		totalOpenedTabs = driver.findElements(By.xpath("//li[contains(@class,'customnotabTab')]")).size();
		for(int count=1;count<=3;count++){
			logger.info("total opened tabs = "+totalOpenedTabs);
			Actions actions = new Actions(RFWebsiteDriver.driver);
			for(int i=totalOpenedTabs;i>=1;i--){
				//driver.waitForElementPresent(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]["+i+"]/descendant::a[@class='x-tab-strip-close']"));
				actions.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'customnotabTab')]["+i+"]/a[contains(@class,'x-tab-strip-close')]"))).pause(500).click().build().perform();
				driver.pauseExecutionFor(1000);
				/*if(driver.isElementPresent(By.xpath("//button[text()='Save']/following::button[1]"))){
					driver.click(By.xpath("//button[text()='Save']/following::button[1]"),"Don't save button");
				}
				driver.pauseExecutionFor(1000);*/
			}
			totalOpenedTabs = driver.findElements(By.xpath("//li[contains(@class,'customnotabTab')]")).size();
			if(totalOpenedTabs==0){
				break;
			}else{
				continue;
			}
		}
		logger.info("All Sub tabs closed");
	}
	
	public void enterTextInSearchFieldAndHitEnter(String text){
		driver.switchTo().defaultContent();
		driver.type(SEARCH_TEXT_BOX_LOC,text,"search text");
		driver.pauseExecutionFor(1000);
		driver.findElement(SEARCH_TEXT_BOX_LOC).sendKeys(Keys.RETURN);
		driver.pauseExecutionFor(1000);
		driver.waitForPageLoad();
		driver.waitForCRMLoadingImageToDisappear();
		try{
			driver.click(By.xpath("//a[@id='phSearchClearButton']"),"");	
		}catch(Exception e){

		}
		driver.pauseExecutionFor(1000);
	}
	
	public void clickAnyTypeOfActiveCustomerInSearchResult(String customer){
		switchToExtFrame(1);
		driver.waitForElementPresent(By.xpath("//div[@id='Account_body']/descendant::*[text()='"+customer+"'][1]/following::*[text()='Active'][1]/preceding::a[1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='Account_body']/descendant::*[text()='"+customer+"'][1]/following::*[text()='Active'][1]/preceding::a[1]"),"");
	}
	
	public void switchToExtFrame(int i){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/descendant::iframe[contains(@class,'x-border-panel')]["+i+"]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[contains(@class,'x-border-panel')]["+i+"]")));
		logger.info("Switched to ext frame index = "+i);
	}

}
