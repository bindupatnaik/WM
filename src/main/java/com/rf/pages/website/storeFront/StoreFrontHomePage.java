package com.rf.pages.website.storeFront;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;
import com.rf.core.website.constants.TestConstants;


public class StoreFrontHomePage extends StoreFrontRFWebsiteBasePage {
	private static final Logger logger = LogManager
			.getLogger(StoreFrontHomePage.class.getName());
	private Actions actions;

	private final By SIGN_UP_LINK_LOC=By.xpath("//div[@id='header']//a[contains(text(),'Sign up now')]");
	private final By FIELD_SPONSOR_LINK_LOC = By.xpath("//a[contains(text(),'Don�t Have an R+F Sponsor?')]");
	private final By CONFIRMATION_MESSAGE_LOC = By.xpath("//div[@id='sponsorPopup']/div/h2");
	private final By FORGOT_PASSWORD_LOC=By.xpath("//div[@id='header']//a[@id='show-recover-pass']");
	private final By POLICY_AND_PROCEDURE_LINK=By.xpath("//div[@id='disclaimer']//a[contains(text(),'Policies and Procedures')]");
	private static final By CHECKOUT_BTN = By.xpath("//span[text()='Checkout']");
	private static final By CLICK_HERE_LINK_FOR_RC = By.xpath("//a[contains(@id,'RetailLink')]");
	private static final By CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC = By.xpath("//*[contains(@id,'uxContinue')]");
	private static final By FIRST_NAME_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxFirstName')]");
	private static final By LAST_NAME_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxLastName')]");
	private static final By EMAIL_ADDRESS_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxEmailAddress')]");
	private static final By CREATE_PASSWORD_FOR_PC_AND_RC = By.xpath("//div[@class='form-group']//input[contains(@id,'uxPassword')]");
	private static final By CONFIRM_PASSWORD_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxConfirmPassword')]");
	private static final By PHONE_NUMBER_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxPhoneNumber')]");	
	private static final By SPONSOR_RADIO_BTN = By.xpath("//div[@class='DashRow']/input");
	private static final By ID_NUMBER_AS_SPONSOR = By.xpath("//input[contains(@id,'uxSponsorID')]");
	private static final By BEGIN_SEARCH_BTN = By.xpath("//a[contains(@id,'uxSearchByName')]");
	private static final By ADDRESS_NAME_FOR_SHIPPING_PROFILE = By.xpath("//input[contains(@id,'uxAddressName')]");
	private static final By ATTENTION_FIRST_NAME = By.xpath("//div[@class='form-group']//input[contains(@id,'uxAttentionFirstName')][1]");
	private static final By ATTENTION_LAST_NAME = By.xpath("//div[@class='form-group']//input[contains(@id,'uxAttentionLastName')][1]");
	private static final By ADDRESS_LINE_1 = By.xpath("//input[contains(@id,'uxAddressLine1')]");
	private static final By ZIP_CODE = By.xpath("//input[contains(@id,'uxZipCode')]");
	private static final By CITY_DD = By.xpath("//input[contains(@id,'uxCityDropDown_Input')]/following::a[text()='select']");
	private static final By FIRST_VALUE_OF_CITY_DD = By.xpath("//div[contains(@id,'uxCityDropDown_DropDown')]//ul[@class='rcbList']/li");
	private static final By COUNTRY_DD = By.xpath("//input[contains(@id,'uxCountyDropDown_Input')]/following::a[text()='select']");
	private static final By FIRST_VALUE_OF_COUNTRY_DD = By.xpath("//div[contains(@id,'uxCountyDropDown_DropDown')]//ul[@class='rcbList']/li");
	private static final By PHONE_NUMBER_SHIPPING_PROFILE_PAGE = By.xpath("//input[contains(@id,'uxShippingEditor_AppPhone')]");
	private static final By USE_AS_ENTERED_BTN_LOC = By.xpath(".//*[@id='QAS_AcceptOriginal']");
	private static final By CONTINUE_BTN_PREFERRED_AUTOSHIP_CART_PAGE_LOC = By.xpath("//a[contains(@id,'uxContinue')]");
	private static final By COMPLETE_ORDER_BTN = By.xpath("//input[contains(@id,'uxSubmitOrder')]");
	private static final By ORDER_CONFIRMATION_THANK_YOU_TXT = By.xpath("//h2[contains(text(),'Thank')]");
	private static final By BILLING_NAME_FOR_BILLING_PROFILE = By.xpath("//input[contains(@id,'uxBillingProfileName')]");
	private static final By NAME_ON_CARD = By.xpath("//input[contains(@id,'uxNameOnCard')]");
	private static final By CREDIT_CARD_NUMBER_INPUT_FIELD = By.xpath("//input[contains(@id,'uxCreditCardNumber')]");
	private static final By CVV_FIELD = By.xpath("//input[contains(@id,'uxCreditCardCvv')]");
	private static final By EXPIRATION_DATE_MONTH_DD = By.xpath("//select[contains(@id,'uxMonthDropDown')]");
	private static final By EXPIRATION_DATE_YEAR_DD = By.xpath("//select[contains(@id,'uxYearDropDown')]");
	private static final By PHONE_NUMBER_BILLING_PROFILE_PAGE = By.xpath("//input[contains(@id,'uxPhone')]");
	private static final By BUSINESS_LINK_LOC = By.cssSelector("a[id='corp-opp']"); 
	private static final By ENROLL_NOW_LINK_LOC = By.cssSelector("a[title='Enroll Now']");
	private static final By ALL_PRODUCT_NAME_IN_CART = By.xpath("//div[@id='shopping-wrapper']//h3");
	private static String addToBagButtonForProductNameLoc = "//a[contains(text(),'%s')]/following::button[contains(text(),'ADD TO BAG')][1]";
	private static String shadeDDLoc = "//a[contains(text(),'%s')]/following::select[contains(@id,'displayCodes')][1]";
	private static String addToCRPButtonForProductNameLoc = "//a[contains(text(),'%s')]/following::button[contains(text(),'ADD TO CRP')][1]";
	private static String removeLinkLoc="//div[@class='cart-items']/div[%s]//a[text()='Remove']";
	private static String genderLocForPCAndRC= "//label[text()='%s']/preceding::input[1]";
	private static String expiryMonthLoc= "//select[contains(@id,'uxMonthDropDown')]//option[@value='%s']";
	private static String expiryYearLoc= "//select[contains(@id,'uxYearDropDown')]//option[@value='%s']";
	private static String checkVariantProductInCart = "//div[@class='cart-items' or @class='cartItems']//h3[contains(text(),'%s - %s%s')]";
	private static String removeButtonLinkOfVariantProduct  = "//div[@class='cart-items' or @class='cartItems']//h3[contains(text(),'%s - %s%s')]/ancestor::div[2]//a[contains(text(),'Remove')]";

	private String addressLine1=null;
	private String city=null;
	private String postalCode=null;

	public StoreFrontHomePage(RFWebsiteDriver driver) {
		super(driver);		
	}

	public void openConsultantPWS(String pwsURL){
		if(driver.isUserFetchedFromDB().equalsIgnoreCase("false")){
			driver.get(" https://"+TestConstants.PREFIX_AU+".myrfo"+driver.getEnvironment()+".biz/"+driver.getCountry()+"/");	
		}
		else{
			logger.info("User PWS is "+pwsURL);
			driver.get(pwsURL);
		}
		driver.waitForPageLoad();
	}

	public boolean isCurrentURLShowsError() {
		driver.waitForPageLoad();
		logger.info("Curremt URL is "+driver.getCurrentUrl());
		return driver.getCurrentUrl().contains("login?error=true");
	}

	//	public void searchCID() {
	//		driver.type(By.xpath("//*[@id='sponserparam' or @id='sponsor-name-id']"), TestConstants.CID);
	//		driver.click(By.xpath("//*[@id='search-sponsor-button' or @value='Search']"),"");
	//		driver.waitForLoadingImageToDisappear();
	//		logger.info("Sponsor entered as "+TestConstants.CID+"  and search button clicked");
	//		driver.waitForLoadingImageToDisappear();
	//	}


	public boolean isNoSearchResultMsg(){
		return driver.getText(By.xpath("//div[@id='sponsorPage']/descendant::span[1]")).contains("No result found for");
	}

	public void searchCIDOnSelectASponsorPopUp(String cid){
		driver.waitForElementPresent(By.id("sponsorparam"));
		driver.type(By.id("sponsorparam"),cid);
		driver.waitForElementPresent(By.xpath("//input[@value='Search']"));
		driver.click(By.xpath("//input[@value='Search']"),"Search sponsor button");
		logger.info("Sponsor entered as "+cid+" and search button clicked");
		driver.waitForLoadingImageToDisappear();
	}

	public String clickOnSelectAndContinueOnSelectASponsorPopUp(){
		driver.waitForElementPresent(By.xpath("//div[contains(@class,'the-sponsor-results')]/div[1]//input[@value='Select & Continue']"));
		String sponsorName = getSponsorNameFromSelectAndContinueSection();
		driver.click(By.xpath("//div[contains(@class,'the-sponsor-results')]/div[1]//input[@value='Select & Continue']"),"");
		logger.info("select and continue button clicked");
		driver.pauseExecutionFor(1000);
		driver.waitForPageLoad();
		return sponsorName;
	}

	public String getCIDFromUIAfterSponsorSearch(){
		return driver.getText(By.xpath("//div[@class='the-search-results']/div[1]//ul/li[2]"));
	}

	public void mouseHoverSponsorDataAndClickContinue() {
		
		if (!isMobile()){
			driver.moveToELement(By.xpath("//div[contains(@id,'search-results')]/descendant::*[contains(@value,'Select')][1]"));
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[contains(@id,'search-results')]/descendant::*[contains(@value,'Select')][1]"),"first sponsor result");
		}else {
			driver.click(By.xpath("//div[@id='search-results']/descendant::li[1]"), "First Search result");
			driver.findElement(By.xpath("//div[@id='search-results']/descendant::li[1]")).submit();
		}
		driver.waitForPageLoad();
	}

	public void mouseHoverSponsorInResultAndClickContinue(){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.waitForElementPresent(By.xpath("//div[@class='sponsorDataDiv']"));
		for(int i=1;i<=3;i++){
			try{
				actions.moveToElement(driver.findElements(By.xpath("//div[@class='sponsorDataDiv']")).get(0)).build().perform();
				driver.pauseExecutionFor(1000);
				driver.click(By.xpath("//*[contains(@value,'Select') or contains(text(),'Select & Continue')]"),"");
				driver.waitForLoadingImageToDisappear();
				break;
			}catch(Exception e){
				driver.pauseExecutionFor(1000);
				continue;
			}
		}

	}

	public void hoverOnBecomeAConsultantAndClickEnrollNowLink(){
		if(driver.getDevice().equalsIgnoreCase("mobile")){
			driver.click(By.xpath("//div[@id='header']//span[contains(@class,'icon-menu')]"),"left icon");
			driver.click(By.xpath("//div[contains(@class,'mobile-nav')]/descendant::li[@id='BusinessSystemBar']"),"Become a consultant link");
			driver.pauseExecutionFor(500);
			
			if (isMobile()){
			driver.click(By.xpath("//div[contains(@class,'mobile-nav')]/descendant::li[@id='BusinessSystemBar']"),"Become a consultant link");
			}
			
			driver.click(By.xpath("//div[contains(@class,'mobile-nav')]/descendant::li[@id='BusinessSystemBar'][1]//a[contains(text(),'Enrol')]"),"enroll Now");
		} else{
			for(int i=1;i<=3;i++){
				try{
					WebElement becomeAConsultantLink = driver.findElement(By.xpath("//div[@id='header']/descendant::li[@id='BusinessSystemBar'][1]"));
					Actions actions = new Actions(RFWebsiteDriver.driver);
					actions.moveToElement(becomeAConsultantLink).click(becomeAConsultantLink).build().perform();
					driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='header']/descendant::li[@id='BusinessSystemBar'][1]//a[contains(text(),'Enrol')]"), "Enroll now");
					break;
				}catch(Exception e){}
			}
		}
	}
	
	public void mouseHoverOtherSponsorDataAndClickContinue() {
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='the-search-results']/form[3]/div[@class='sponsorDataDiv']"))).click(driver.findElement(By.cssSelector("input[value='Select & Continue']"))).build().perform();
		logger.info("Second result of sponsor has been clicked");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean isRegimenPresent(String regimenName){
		boolean regimenPresent = false;
		try{
			driver.turnOffImplicitWaits();
			regimenPresent =  driver.isElementPresent(By.xpath("//span[@class='regimen-name' and contains(.,'"+regimenName+"')]"));
		}finally{
			driver.turnOnImplicitWaits();
		}
		return regimenPresent;
	}

	public void selectEnrollmentKitPage(String kitName){
		driver.waitForElementPresent(By.xpath("//img[@title='"+kitName+"']"));
		driver.click(By.xpath("//div[contains(text(),'"+kitName+"')]"),"kitname="+kitName);
	}

	public void selectPortfolioEnrollmentKitPage(String kitName){
		driver.waitForLoadingImageToDisappear();
		kitName =  kitName.toUpperCase();
		driver.click(By.xpath("//img[@title='"+kitName+"']"),"");
	}

	public void chooseEnrollmentOption(String option){
		option = option.toUpperCase();
		if(option.equalsIgnoreCase("EXPRESS ENROLLMENT")){
			driver.click(By.id("express-enrollment"),option);
		}
		else{
			driver.click(By.id("standard-enrollment"),option);
		}
		driver.click(By.cssSelector("input[value='Next']"),"Next button after selecting enrolmnt type");
	}

	public void enterFirstName(String firstName){
		driver.type(By.id("first-name"), firstName,"First Name");
	}

	public void enterLastName(String lastName){
		driver.type(By.id("last-name"),lastName,"Last Name");
	}

	public void closePopUp(){
		driver.click(By.cssSelector("a[title='Close']"),"");
		driver.pauseExecutionFor(2000);
	}	

	public Boolean checkExistenceOfEmailAddress() {
		driver.type(By.id("email-account"),"\t","tab key")	;
		return driver.isElementPresent(By.xpath("//div[text()='Please fix this field.']"), 10);
	}

	public void enterPassword(String password){
		driver.type(By.id("new-password-account"),password,"password");
	}

	public void enterConfirmPassword(String password){
		driver.type(By.id("new-password-account2"),password,"confirm password");
	}

	public void enterCity(String city){
		driver.type(By.id("city"),city,"city");
	}

	public void selectProvince(String province){ 
		driver.waitForElementPresent(By.id("state"), 5);
		if(driver.findElements(By.id("state")).size()==1){
			driver.clickByJS(RFWebsiteDriver.driver, By.id("state"),"state dropdown");
			driver.click(By.xpath(String.format("//*[@id='state']//option[contains(text(),'%s')]", province)),province);
		}			
		else{
			driver.click(By.xpath("//*[@id='deliveryAddressForm']//*[@id='state']"),"state dropdown");
			driver.click(By.xpath(String.format("//*[@id='deliveryAddressForm']//*[@id='state']//option[contains(text(),'%s')]", province)),province);
		}
	}

	public boolean verifyQuebecProvinceIsDisabled(){
		driver.clickByJS(RFWebsiteDriver.driver, By.id("state"), "state drop down");
		driver.waitForElementPresent(By.xpath("//select[@id='state']/option[contains(text(),'"+TestConstants.PROVINCE_QUEBEC+"')]"));
		return !(driver.findElement(By.xpath("//select[@id='state']/option[contains(text(),'"+TestConstants.PROVINCE_QUEBEC+"')]")).isEnabled());
	}

	public void enterPostalCode(String postalCode){
		driver.type(By.id("postcode"),postalCode,"postal code");
	}

	public void enterPhoneNumber(String phnNum){
		driver.type(By.id("phonenumber"),phnNum,"Phone number");
	}

	public boolean verifySubsribeToPulseCheckBoxIsSelected() {  
		return driver.isElementPresent(By.xpath("//input[@id='pulse-check']/ancestor::div[contains(@class,'checked')]"));
	}

	public boolean verifySubsribeToPulseCheckBoxIsNotSelected(){
		return !driver.isElementPresent(By.xpath("//input[@id='pulse-check']/ancestor::div[contains(@class,'checked')]"));
	}

	public boolean verifyEnrollToCRPCheckBoxIsSelected(){
		return driver.isElementPresent(By.xpath("//input[@id='CRP-check']/ancestor::div[@class='repaired-checkbox checked']"));
	}

	public boolean verifyEnrollToCRPCheckBoxIsNotSelected(){
		return !driver.isElementPresent(By.xpath("//input[@id='CRP-check']/ancestor::div[@class='repaired-checkbox checked']"));
	}

	public void uncheckPulseAndCRPEnrollment() {
		if(verifySubsribeToPulseCheckBoxIsSelected())	{
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='pulse-check']/.."),"Yes,Subscribe me to pulse checkbox ");
		}
		if(verifyEnrollToCRPCheckBoxIsSelected()) {
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@id='CRP-check']/.."),"Yes,enroll me in CRP checkbox");
		}  
	}

	public void checkPulseAndCRPEnrollment() {
		if(verifySubsribeToPulseCheckBoxIsNotSelected()){
			driver.click(By.xpath("//input[@id='pulse-check']/.."),"Yes,Subscribe me to pulse checkbox ");
		}
		if(verifyEnrollToCRPCheckBoxIsNotSelected()){
			driver.click(By.xpath("//input[@id='CRP-check']/.."),"Yes,enroll me in CRP checkbox");
		}
	}

	public boolean verifySuggesstionsForEnteredAddressPop(){
		try{
			driver.waitForLoadingImageToDisappear();
			driver.quickWaitForElementPresent(By.xpath("//*[@id='QAS_RefineBtn']"));
			driver.click(By.xpath("//*[@id='QAS_RefineBtn']"),"");
			driver.waitForLoadingImageToDisappear();
			return true;
		}catch(NoSuchElementException e){
			try{
				driver.waitForElementPresent(By.id("QAS_AcceptOriginal"));
				driver.click(By.id("QAS_AcceptOriginal"),"");
				driver.waitForLoadingImageToDisappear();
				return true;
			}catch(NoSuchElementException e1){
				return false;
			}
		}

	}

	public boolean verifyAndClickAcceptOnQASPopup(){
		try{
			driver.waitForLoadingImageToDisappear();
			driver.quickWaitForElementPresent(By.xpath("//*[@id='QAS_RefineBtn']"));
			driver.click(By.xpath("//*[@id='QAS_RefineBtn']"),"QAS accept button");
			driver.waitForLoadingImageToDisappear();
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean isQuebecNotEligibleAsConsultantErrorDisplayed(){
		return driver.isElementPresent(By.xpath("//span[@id='addressForm.stateIso.errors']"));
	}

	public void enterNameOnCard(String nameOnCard){
		driver.type(By.id("card-name"),nameOnCard,"name on card");
	}

	public boolean validateEmptyCreditCardMessage(){
		return driver.isElementVisible(By.xpath("//input[@id='card-nr']/following::label[1][contains(text(),'This field is required')]"), 15);
	}

	public void clearCreditCardNumber(){
		driver.clear(By.id("card-nr"));
		logger.info("credit card number box cleared");
	}

	public void selectExpirationDate(String month,String year){
		driver.waitForElementPresent(By.id("expiryMonth"), 10);
		Select monthDD = new Select(driver.findElement(By.id("expiryMonth")));
		Select yearDD = new Select(driver.findElement(By.id("expiryYear")));
		monthDD.selectByVisibleText(month.toUpperCase());
		yearDD.selectByVisibleText(year);		
	}

	public void enterSecurityCode(String securityCode){
		driver.type(By.id("security-code"),securityCode,"CVV");
	}

	public void enterNameAsItAppearsOnCard(String nameOnCard){
		if(driver.getCountry().equalsIgnoreCase("ca")){
			driver.type(By.id("name-on-card"),nameOnCard+" last","name on card");
		}
	}
	public void checkThePoliciesAndProceduresCheckBox() {
		driver.waitForElementPresent(By.xpath("//input[@id='policies-check']/.."));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='policies-check']/.."),"Policies And Procedures CheckBox");
		driver.pauseExecutionFor(1000);
	}

	public void checkTheIAcknowledgeCheckBox(){
		driver.waitForElementPresent(By.xpath("//input[@id='acknowledge-check']/.."));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='acknowledge-check']/.."),"I Acknowledge CheckBox");
		driver.pauseExecutionFor(1000);
	}

	public String getErrorMessageForInvalidSSN(){
		driver.waitForElementPresent(By.xpath("//input[@id='S-S-N']/following::label[1]"));
		String errorMessage=driver.findElement(By.xpath("//input[@id='S-S-N']/following::label[1]")).getText();
		return errorMessage;
	}

	public String getInvalidPasswordMessage(){
		driver.waitForElementPresent(By.xpath("//input[@id='new-password-account']/following::label[1]"));
		String errorMessage=driver.findElement(By.xpath("//input[@id='new-password-account']/following::label[1]")).getText();
		return errorMessage;
	}

	public String getInvalidPasswordNotmatchingMessage(){
		driver.waitForElementPresent(By.xpath("//input[@id='new-password-account2']/following::label[1]"));
		String errorMessage=driver.findElement(By.xpath("//input[@id='new-password-account2']/following::label[1]")).getText();
		return errorMessage;
	}

	public boolean isTheTermsAndConditionsCheckBoxDisplayed(){
		driver.waitForElementPresent(By.xpath("//input[@id='terms-check']/.."));
		return driver.IsElementVisible(driver.findElement(By.xpath("//input[@id='terms-check']/..")));
	}

	public void checkTheTermsAndConditionsCheckBox(){
		driver.waitForElementPresent(By.xpath("//input[@id='terms-check']/.."));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='terms-check']/.."),"erms And Conditions CheckBox");
		driver.pauseExecutionFor(1000);
	}

	public void clickOnEnrollmentNextButton(){
		driver.waitForElementPresent(By.id("enrollment-next-button"));
		driver.click(By.id("enrollment-next-button"),""); 
		logger.info("enrollment next button clicked");
	}
	public boolean verifyPopUpForPoliciesAndProcedures() {
		boolean isPopForTermsAndConditionsVisible = false;		
		driver.waitForElementPresent(By.xpath("//div[@class='popup-standard']/h2[text()='Please review and accept all policies and procedures.']"));
		isPopForTermsAndConditionsVisible = driver.IsElementVisible(driver.findElement(By.xpath("//div[@class='popup-standard']/h2[text()='Please review and accept all policies and procedures.']")));
		if(isPopForTermsAndConditionsVisible==true){
			driver.click(By.xpath("//div[@class='popup-standard']/h2[text()='Please review and accept all policies and procedures.']/following::a[@title='Close']"),"");
			driver.pauseExecutionFor(1000);
			return true;
		}
		return false;
	}

	public boolean verifyPopUpForTermsAndConditions() {
		boolean isPopForTermsAndConditionsVisible = false;  
		driver.waitForElementPresent(By.xpath("//div[@class='popup-standard tcpopup']//p"));
		isPopForTermsAndConditionsVisible = driver.IsElementVisible(driver.findElement(By.xpath("//div[@class='popup-standard tcpopup']//p")));
		if(isPopForTermsAndConditionsVisible==true){
			driver.click(By.xpath("//div[@class='popup-standard tcpopup']//p/following::a[@title='Close']"),"");
			driver.pauseExecutionFor(2000);
			return true;
		}
		return false;
	}

	public void checkTheIAgreeCheckBox(){
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='electronically-check']/.."),"I Agree checkbox");
		driver.pauseExecutionFor(1000);
	}


	public void clickOnChargeMyCardAndEnrollMeBtn() {
		if(driver.getCountry().equalsIgnoreCase("au")){
			driver.clickByJS(RFWebsiteDriver.driver, By.id("enroll-button"),"Charge and Enroll me button");
		}else{
			driver.clickByJS(RFWebsiteDriver.driver, By.id("enroll-button"),"Charge and Enroll me button");
		}
	}

	public void clickOnEnrollMeBtn() {
		clickOnChargeMyCardAndEnrollMeBtn();
	}

	public void clickOnConfirmAutomaticPayment() {
		try{
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@id='enroll']"),"Automatic payment confirmation button");
			driver.waitForLoadingImageToDisappear();
		}catch(NoSuchElementException e){
			logger.info("Confirmation Automatic Payment popup not present.");
		}
	}

	public boolean verifyCongratsMessage(){
		driver.waitForElementPresent(By.id("Congrats"));
		return driver.isElementVisible(By.id("Congrats"));
	}

	public void clickOnGoToMyAccountToCheckStatusOfCRP(){
		driver.waitForElementPresent(By.xpath("//div[@id='order-confirm']/a"));
		driver.click(By.xpath("//div[@id='order-confirm']/a"),"");	
		logger.info("Go to my account to check status of CRP button clicked");
	}

	public void clickOnAccountInfoLinkLeftSidePannel(){
		driver.waitForElementPresent(By.xpath("//div[@id='left-menu']//a[text()='ACCOUNT INFO']"));
		driver.click(By.xpath("//div[@id='left-menu']//a[text()='ACCOUNT INFO']"),"");
		logger.info("account info link on side panel clicked");
		driver.pauseExecutionFor(1000);
	}

	public boolean validateErrorMessageWithoutSelectingAllCheckboxes(){
		driver.waitForElementPresent(By.xpath("//div[@class='popup-standard tcpopup']//p"));
		return driver.findElement(By.xpath("//div[@class='popup-standard tcpopup']//p")).isDisplayed();
	}

	public void acceptTheProvideAccessToSpousePopup(){
		try{
			driver.quickWaitForElementPresent(By.id("acceptSpouse"));
			driver.click(By.id("acceptSpouse"),"");
		}
		catch(Exception e){

		}
	}

	public void clickSwitchToExpressEnrollmentLink(){
		driver.waitForElementPresent(By.xpath("//a[@id='lnk-switch']"));
		driver.click(By.xpath("//a[@id='lnk-switch']"),"");
		driver.pauseExecutionFor(3000);
	}

	public void checkPulseCheckBox(){
		driver.click(By.xpath("//input[@id='pulse-check']/.."),"pulse checkbox");
	}

	public void checkCRPCheckBox(){
		driver.click(By.xpath("//input[@id='CRP-check']/.."),"CRP checkbox");
	}


	public void clickNextOnCRPCartPage(){
		driver.clickByJS(RFWebsiteDriver.driver, By.id("submitForm"),"Next button On CRP Cart");
		driver.waitForLoadingImageToDisappear();
	}

	public void clickSwitchToExpressEnrollmentOnRecurringMonthlyChargesSection(){
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Switch to Express')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Switch to Express')]")).click();
	}

	public void enterUserInformationForEnrollment(String kitName,String regimenName,String enrollmentType,String firstName,String lastName,String emailaddress,String password,String addressLine1,String city,String province,String postalCode,String phoneNumber){
		selectEnrollmentKitPage(kitName, regimenName);  
		chooseEnrollmentOption(enrollmentType);
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmailAddress(emailaddress);
		enterPassword(password);
		enterConfirmPassword(password);
		enterAddressLine1(addressLine1);
		enterCity(city);
		selectNewShippingAddressState(province);
		enterPostalCode(postalCode);
		enterPhoneNumber(phoneNumber);
		//enterEmailAddress(emailaddress);
	}

	// method overloaded, parameter for province is there
	public void enterUserInformationForEnrollment(String kitName,String regimenName,String enrollmentType,String firstName,String lastName,String password,String addressLine1,String city,String province,String postalCode,String phoneNumber){
		selectEnrollmentKitPage(kitName, regimenName); 
		if(!kitName.equalsIgnoreCase(TestConstants.KIT_NAME_PORTFOLIO)){
			chooseEnrollmentOption(enrollmentType);
		}
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmailAddress(firstName+"."+lastName+TestConstants.EMAIL_ADDRESS_SUFFIX);
		enterPassword(password);
		enterConfirmPassword(password);
		enterAddressLine1(addressLine1);
		enterCity(city);
		selectNewShippingAddressState(province);
		enterPostalCode(postalCode);
		enterPhoneNumber(phoneNumber);  
	}

	public void enterUserInformationForEnrollmentWithEmail(String kitName,String regimenName,String enrollmentType,String firstName,String lastName,String emailaddress,String password,String addressLine1,String city,String state,String postalCode,String phoneNumber){
		selectEnrollmentKitPage(kitName, regimenName);  
		chooseEnrollmentOption(enrollmentType);
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmailAddress(emailaddress);
		enterPassword(password);
		enterConfirmPassword(password);
		enterAddressLine1(addressLine1);
		enterCity(city);
		selectNewShippingAddressState(state);
		enterPostalCode(postalCode);
		enterPhoneNumber(phoneNumber);
	}

	//Method Overloaded without Kit and Regimen
	public void enterUserInformationForEnrollment(String firstName,String lastName,String password,String addressLine1,String city,String state,String postalCode,String phoneNumber){
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmailAddress(firstName+"."+lastName+TestConstants.EMAIL_ADDRESS_SUFFIX);
		enterPassword(password);
		enterConfirmPassword(password);
		enterAddressLine1(addressLine1);
		enterCity(city);
		selectNewShippingAddressState(state);
		enterPostalCode(postalCode);
		enterPhoneNumber(phoneNumber);
	}

	public void enterUserInformationForEnrollmentWithEmailWithoutKit(String firstName,String lastName,String email, String password,String addressLine1,String city,String state,String postalCode,String phoneNumber){
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmailAddress(email);
		enterPassword(password);
		enterConfirmPassword(password);
		enterAddressLine1(addressLine1);
		enterCity(city);
		selectNewShippingAddressState(state);
		enterPostalCode(postalCode);
		enterPhoneNumber(phoneNumber);
	}

	//method overloaded,no need for enrollment type if kit is portfolio
	public void enterUserInformationForEnrollment(String kitName,String regimenName,String firstName,String lastName,String password,String addressLine1,String city,String state,String postalCode,String phoneNumber){
		selectEnrollmentKitPage(kitName, regimenName);		
		enterFirstName(firstName);
		enterLastName(lastName);
		enterPassword(password);
		enterConfirmPassword(password);
		enterAddressLine1(addressLine1);
		enterCity(city);
		selectNewShippingAddressState(state);
		enterPostalCode(postalCode);
		enterPhoneNumber(phoneNumber);
		enterEmailAddress(firstName+"."+lastName+TestConstants.EMAIL_ADDRESS_SUFFIX);
	}

	public void enterUserInformationForEnrollment(String kitName,String firstName,String lastName,String password,String addressLine1,String city,String state,String postalCode,String phoneNumber){
		selectEnrollmentKitPage(kitName);  
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmailAddress(firstName+"."+lastName+TestConstants.EMAIL_ADDRESS_SUFFIX);
		enterPassword(password);
		enterConfirmPassword(password);
		enterAddressLine1(addressLine1);
		enterCity(city);
		selectNewShippingAddressState(state);
		enterPostalCode(postalCode);
		enterPhoneNumber(phoneNumber);

	}

	public void clickOnSwitchToStandardEnrollmentLink(){
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Switch to Standard Enrol')]"));
		driver.click(By.xpath("//a[contains(text(),'Switch to Standard Enrol')]"),"");
		driver.waitForPageLoad();
		//driver.pauseExecutionFor(2000);
	}

	public void clickOnSwitchToExpressEnrollmentLink(){
		driver.click(By.xpath("//a[contains(text(),'Switch to Express')]"),"Switch to express enrollment link");
		driver.waitForPageLoad();
	}

	public boolean verifyPopUpForExistingActiveConsultant() {
		try{
			driver.findElement(By.xpath("//a[contains(@class, 'fancybox-close')]")).click();
			driver.pauseExecutionFor(500);
			return true;
		}catch(NoSuchElementException ex){
			return false;
		}		
	}

	public boolean verifyPopUpForExistingInactiveCC180Days() {
		boolean isPopForExistingAccountVisible = false;
		isPopForExistingAccountVisible = driver.findElement(By.xpath("//div[@id='inactiveConsultant180Popup']/div/div")).isDisplayed();
		if(isPopForExistingAccountVisible==true){
			driver.click(By.xpath("//a[contains(@class, 'fancybox-close')]"),"");
			return true;
		}else{
			return false;
		}
	}

	public boolean validateIncorrectLogin(){
		return driver.findElement(By.xpath("//p[text()='Your username or password was incorrect.']")).isDisplayed();
	}

	public boolean verifySwitchPCToUnderDifferentConsultant(){
		boolean flag = false;
		if(driver.findElement(By.id("inactivePc90Form")).isDisplayed()){
			flag = true;
			return flag;
		}else{
			return flag;
		}
	}

	public void mouseHoverSponsorDataAndClickContinueForPC() {
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@id='the-search-results']/div[1]/div[@class='result-inner shadow']"))).click(driver.findElement(By.cssSelector("input[value='Select']"))).build().perform();
		logger.info("First result of sponsor has been clicked");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
		//driver.pauseExecutionFor(2000);
	}

	public void enterSponsorIdDuringCreationOfPC(String sponsorID){
		driver.waitForElementPresent(By.id("sponsor-name-id"));
		driver.type(By.id("sponsor-name-id"),sponsorID);
		driver.click(By.xpath("//input[contains(@class,'submitSponser')]"),"");
	}

	public boolean isAdhocMiniCartDisplayed() {
		actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.id("mini-shopping-button"))).build().perform();
		driver.waitForLoadingImageToDisappear();
		return driver.isElementVisible(By.xpath("//*[@id='mini-shopping-button']//*[@id='mini-shopping']"));
	}	

	public String getNumberOfProductsDisplayedOnMiniCart(){
		return driver.findElement(By.xpath("//div[@id='shopping-bag']//span[@class='cart-count']")).getText();
	}

	public boolean clickMiniCartAndValidatePreaddedProductsOnCartPage(){
		driver.waitForElementPresent(By.xpath("//a[@id='shopping-cart']"));
		driver.click(By.xpath("//a[@id='shopping-cart']"),"");
		driver.waitForPageLoad();
		return driver.findElement(By.xpath("//div[@id='left-shopping']")).isDisplayed();
	}

	public boolean isCartPageDisplayed(){
		driver.pauseExecutionFor(2000);
		return driver.getCurrentUrl().contains("/cart") && driver.findElement(By.xpath("//div[@id='left-shopping']")).isDisplayed();
	}

	public void clickOnReviewAndConfirmShippingEditBtn(){
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Shipping info')]/a[text()='Edit']"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//h3[contains(text(),'Shipping info')]/a[text()='Edit']"),"Edit button on Review and Confirm");
		driver.pauseExecutionFor(2000);
	}

	public boolean isReviewAndConfirmPageContainsShippingAddress(String shippingAddress){
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']//div[contains(@class,'third-list-module')][1]//span[contains(@class,'font-bold')]/.."));
		return driver.findElement(By.xpath("//div[@id='summarySection']//div[contains(@class,'third-list-module')][1]//span[contains(@class,'font-bold')]/..")).getText().contains(shippingAddress);
	}

	public boolean isReviewAndConfirmPageContainsFirstAndLastName(String name){
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']//div[contains(@class,'third-list-module')][1]//span[contains(@class,'font-bold')]"));
		return driver.findElement(By.xpath("//div[@id='summarySection']//div[contains(@class,'third-list-module')][1]//span[contains(@class,'font-bold')]")).getText().toLowerCase().contains(name.toLowerCase());
	}

	public boolean validateUserLandsOnPWSbizSite(){
		return driver.getCurrentUrl().contains("biz");
	}

	public boolean validatePWS(){
		driver.waitForElementPresent(By.xpath("//div[@id='header-logo']"));
		return driver.findElement(By.xpath("//div[@id='header-logo']")).isDisplayed();
	}

	public void clickOnCountryAtWelcomePage(){
		String country = driver.getCountry();
		if(country.equalsIgnoreCase("ca")){
			driver.waitForElementPresent(By.xpath("//h2[text()='Select your location']/following::a[1]"));
			driver.click(By.xpath("//h2[text()='Select your location']/following::a[1]"),"select your location");

		}else if(country.equalsIgnoreCase("us")){
			driver.waitForElementPresent(By.xpath("//h2[text()='Select your location']/following::a[2]"));
			driver.click(By.xpath("//h2[text()='Select your location']/following::a[2]"),"select your location");
		}else if(country.equalsIgnoreCase("au")){
			driver.waitForElementPresent(By.xpath("//h2[text()='Select your location']/following::a[3]"));
			driver.click(By.xpath("//h2[text()='Select your location']/following::a[3]"),"select your location");
		}
	}

	public String navigateToCommercialWebsite(String bizURL){
		return bizURL.replaceFirst("biz", "com");
	}

	public boolean validateShippingMethodDisclaimersForUPSGroundHD(){
		return driver.findElement(By.xpath("//select[@id='selectDeliveryModeForCRP']/option[3]")).getText().contains("Overnight");
	}

	public boolean validatePCPerksCheckBoxIsDisplayed(){
		return driver.findElement(By.xpath("//div[@id='pc-customer2-div-order-summary']")).isDisplayed();		
	}

	public void clickNotYourSponsorLinkOnKitPage(){
		driver.waitForElementPresent(By.xpath("//a[text()='Not your sponsor?']"));
		driver.click(By.xpath("//a[text()='Not your sponsor?']"),"");
		driver.waitForLoadingImageToDisappear();

	}

	public boolean validateInvalidSponsor(){
		return driver.isElementPresent(By.xpath("//span[contains(text(),'No result found')]"));
	}

	/**
	 * This method clicks on the search again link below Main account info while enrolling PC/RC
	 */
	public void clickSearchAgain(){
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath("//a[@id='sponsor_search_again']"));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//a[@id='sponsor_search_again']"),"Search again link");
	}

	public void checkPCPerksCheckBox(){
		driver.waitForPageLoad();
		driver.waitForElementPresent(By.xpath("//input[@id='pc-customer2']/.."));
		driver.click(By.xpath("//input[@id='pc-customer2']/.."),"");
		//driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
		//driver.pauseExecutionFor(2000);
	}

	public void searchCIDForPCAndRC() {
		driver.waitForElementPresent(By.id("sponsor-name-id"));
		driver.type(By.id("sponsor-name-id"),"mary");
		driver.waitForElementPresent(By.xpath("//input[@value='Search']"));
		driver.click(By.xpath("//input[@value='Search']"),"");
		logger.info("Sponsor entered as 'mary' and search button clicked");
		driver.waitForLoadingImageToDisappear();
	}

	/**
	 * This method click on the Select and Continue button of first sponsor result while enrolling PC/RC
	 * @
	 */
	public void mouseHoverSponsorDataAndClickContinueForPCAndRC() {
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='the-search-results']/div[1]/div[1]//input[@value='Select']"), "Select and Continue button of first sponsor result");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean validateExpiredDateMessage(){
		return driver.isElementPresent(By.xpath("//div[@class='tipsy-inner'][contains(text(),'Must be a valid Expiration Date')]"));
	}

	public void clickEnrollmentNextButtonWithoutPopupHandled() {
		driver.waitForElementPresent(By.id("enrollment-next-button"));
		//driver.pauseExecutionFor(2000);
		driver.click(By.id("enrollment-next-button"),"");
		logger.info("EnrollmentTest Next Button clicked");
		driver.waitForLoadingImageToDisappear();
		//driver.pauseExecutionFor(2000);
	}

	public boolean validateSetUpAccountPageIsDisplayed(){
		return driver.findElement(By.xpath("//h1[contains(text(),'Set Up Account')]")).getText().contains("SET UP ACCOUNT");
	}

	public boolean validateUpdatedMainAccountInfo(){
		return driver.findElement(By.xpath(".//div[@id='summarySection']/div[4]/div[3]/p/br[1]")).getText().contains(addressLine1)
				&&  driver.findElement(By.xpath(".//div[@id='summarySection']/div[4]/div[3]/p/br[2]")).getText().contains(city)
				&& driver.findElement(By.xpath(".//div[@id='summarySection']/div[4]/div[3]/p/br[2]")).getText().contains(postalCode);
	}

	public void reEnterContactInfoAndPassword(){
		String country = driver.getCountry();
		if(country.equalsIgnoreCase("ca")){
			driver.clear(By.xpath("//input[@id='address-1']"));
			driver.type(By.xpath("//input[@id='address-1']"),TestConstants.RE_ENTER_ADDRESS_LINE_1);
			driver.clear(By.xpath("//input[@id='city']"));
			driver.type(By.xpath("//input[@id='city']"),TestConstants.RE_ENTER_CITY);
			driver.clear(By.xpath("//input[@id='postcode']"));
			driver.type(By.xpath("//input[@id='postcode']"),TestConstants.RE_ENTER_POSTALCODE);
		}else{
			driver.clear(By.xpath("//input[@id='address-1']"));
			driver.type(By.xpath("//input[@id='address-1']"),TestConstants.NEW_ADDRESS_LINE_1_US);
			driver.clear(By.xpath("//input[@id='city']"));
			driver.type(By.xpath("//input[@id='city']"),TestConstants.CITY_US);
			driver.clear(By.xpath("//input[@id='postcode']"));
			driver.type(By.xpath("//input[@id='postcode']"),TestConstants.POSTAL_CODE_US);
		}
		addressLine1=driver.findElement(By.xpath("//input[@id='address-1']")).getText();
		city=driver.findElement(By.xpath("//input[@id='city']")).getText();
		postalCode=driver.findElement(By.xpath("//input[@id='postcode']")).getText();
		driver.type(By.xpath("//input[@id='new-password-account']"),driver.getStoreFrontPassword());
		driver.type(By.xpath("//input[@id='new-password-account2']"),driver.getStoreFrontPassword());
		clickNextButton();
		driver.waitForLoadingImageToDisappear();
	}

	public boolean validateUpdateCartPageIsDisplayed(){
		driver.pauseExecutionFor(1000);
		return driver.getCurrentUrl().contains("autoship");
	}

	public boolean validateRemoveProductsTillErrorMsgIsDisplayed(){
		boolean flag=false;
		while(true){
			try{
				driver.waitForElementPresent(By.xpath("//a[text()='Remove']"));
				driver.click(By.xpath("//a[text()='Remove']"),"");
				driver.waitForPageLoad();
				if(driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).getText().equalsIgnoreCase(TestConstants.AUTOSHIP_TEMPLATE_ERROR_MSG_CONSULTANT)){
					flag=true;
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(flag==true){
			return true;
		}else
			return false;

	}

	public boolean validateErrorMsgAfterRemovingProductsFromPcCart(){
		boolean flag=false;
		while(true){
			try{
				driver.waitForElementPresent(By.xpath("//a[text()='Remove']"));
				driver.click(By.xpath("//a[text()='Remove']"),"");				
				if(driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).getText().equalsIgnoreCase(TestConstants.AUTOSHIP_TEMPLATE_ERROR_MSG_PC)){
					flag=true;
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(flag==true){
			return true;
		}else
			return false;

	}

	public boolean validateAutoshipTemplateUpdatedMsgAfterIncreasingQtyOfProducts(){
		driver.waitForElementPresent(By.xpath(".//div[@id='globalMessages']//p"));
		return driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).getText().contains(TestConstants.AUTOSHIP_TEMPLATE_UPDATE_CART_MSG_AFTER_UPDATING_PRODUCT_QTY);
	}

	public void deleteTheOnlyAddedProductInTheCart(){
		driver.click(By.linkText("Delete"),"");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public String getMessageFromTheCart(){
		driver.waitForElementPresent(By.xpath(".//div[@id='globalMessages']//p"));
		return driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).getText().toLowerCase().trim();
	}

	public boolean isCartEmpty(){
		return driver.findElement(By.xpath("//div[@id='left-shopping']/h1")).getText().contains("0 item");
	}

	public void clickOnUserName(){
		driver.waitForElementPresent(By.xpath("//div[@id='header-middle-top']//a"));
		driver.click(By.xpath("//div[@id='header-middle-top']//a"),"");
		driver.waitForPageLoad();
	}

	public boolean verifyJoinMyTeamLinkPresent(){
		driver.waitForPageLoad();
		if(driver.isElementPresent(By.xpath("//a[@class='joinMe']"))){
			return true;
		}else
			return false;
	}

	public void clickOnJoinMyTeamBtn(){
		driver.click(By.xpath("//a[@class='joinMe']"),"");
		driver.pauseExecutionFor(1000);
		driver.waitForPageLoad();
	}

	public void openPWS(String pws){
		if(driver.isUserFetchedFromDB().equalsIgnoreCase("false")){
			driver.get(" https://"+TestConstants.PREFIX_AU+".myrfo"+driver.getEnvironment()+".biz/"+driver.getCountry()+"/");	
		}
		else{
			driver.get(pws);
			driver.waitForPageLoad();
			while(true){
				if(driver.getCurrentUrl().contains("sitenotfound")){
					pws = getBizPWS(driver.getCountry(), driver.getEnvironment());
					//bizPWS = convertBizSiteToComSite(bizPWS);
					driver.get(pws);
					logger.info("Opened pws is "+pws);
					driver.waitForPageLoad();
				}else{
					break;
				}
			}
		}
	}

	public void openPWSSite(String pws){
		if(driver.isUserFetchedFromDB().equalsIgnoreCase("false")){
			driver.get(" https://"+TestConstants.PREFIX_AU+".myrfo"+driver.getEnvironment()+".biz/"+driver.getCountry()+"/");	
		}
		else{
			driver.get(pws);		
		}
		driver.waitForPageLoad();
	}

	public String convertBizToComPWS(String pws){
		String com  = "com";
		String biz ="biz";
		if(pws.contains(biz)){
			String comPws = pws.replaceAll("biz",com);
			return comPws;
		}else{
			String bizPws = pws.replaceAll("com",biz);
			return bizPws;
		}
	}

	public String convertBizSiteToComSite(String pws){
		String com  = "com";
		String biz ="biz";
		if(pws.contains(biz))
			pws = pws.replaceAll(biz,com);
		return pws;
	}

	public String convertComSiteToBizSite(String pws){
		String com  = "com";
		String biz ="biz";
		if(pws.contains(com))
			pws = pws.replaceAll(com,biz);
		return pws;		
	}

	public boolean verifyContinueWithoutSponserLinkPresent(){
		return driver.isElementPresent(By.id("continue-no-sponsor"));
	}

	public boolean isContinueWithoutSponserLinkDisplayed(){
		try{
			driver.turnOffImplicitWaits();
			return driver.isElementVisible(By.id("continue-no-sponsor"));
			//return true;
		}catch(Exception e){
			return false;
		}finally{
			driver.turnOnImplicitWaits();
		}
	}

	public boolean verifyRequestASponsorBtn(){
		return driver.isElementPresent(By.xpath("//input[@value='Request a sponsor']"));
	}

	//	public void searchCIDForSponserHavingPulseSubscribed(String sponserName){
	//		driver.waitForElementPresent(By.id("sponsor-name-id"));
	//		driver.type(By.id("sponsor-name-id"),sponserName);
	//		driver.waitForElementPresent(By.xpath("//input[@value='Search']"));
	//		driver.clickByJS(RFWebsiteDriver.driver, driver.findElement(By.xpath("//input[@value='Search']")));
	//		logger.info("Sponsor entered as 'test' and search button clicked");
	//		driver.waitForLoadingImageToDisappear();
	//	}

	public boolean verifyPWSAfterSuccessfulEnrollment(String actualPWS,String newPWS){
		if(actualPWS.contains(newPWS)){
			return true;
		}
		else
			return false;
	}

	public boolean verifyPCUserIsOnSponserPWSAfterSuccessfulEnrollment(String sponserPWS,String newPWS){
		if(newPWS.contains(sponserPWS)){
			return true;
		}
		else
			return false;
	}

	public boolean verifyPCUserIsOnCorpSiteAfterSuccessfulEnrollment(String corpUrl,String newPWS){
		if(newPWS.contains(corpUrl)){
			return true;
		}
		else
			return false;
	}

	public String getSponsorNameFromSelectAndContinueSection(){
		driver.waitForElementPresent(By.xpath("//div[contains(@class,'the-sponsor-results')]/div[1]//li[@class='font-bold']"));
		return driver.findElement(By.xpath("//div[contains(@class,'the-sponsor-results')]/div[1]//li[@class='font-bold']")).getText();
	}

	public String getSponsorNameFromHeaderKitPage(){
		driver.waitForElementPresent(By.xpath("//div[@id='header']//div[contains(@class,'consultant-info')]//a"));
		return driver.findElement(By.xpath("//div[@id='header']//div[contains(@class,'consultant-info')]//a")).getText();	
	}

	public boolean isSponsorPresentInSearchList(){
		driver.waitForElementPresent(By.xpath("//div[@class='sponsorDataDiv']"));
		return driver.isElementPresent(By.xpath("//div[@class='sponsorDataDiv']"));
	}

	/**
	 * This method goto Products page, add a product to cart and do checkout
	 */
	public void addAProductToCartAndCheckout(){
		hoverOnShopLinkAndClickAllProductsLinks();
		selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		clickOnCheckoutButton();
	}

	public double getSubTotalOfSecondProduct(){
		driver.waitForElementPresent(By.xpath("//input[@id='quantity1']"));
		String qty=driver.findElement(By.xpath("//input[@id='quantity1']")).getAttribute("value");
		Integer.parseInt(qty);
		String total=driver.findElement(By.xpath("//div[@class='cartItems']/div[2]//span[@class='special-price']")).getText();
		String[] totalvalue= total.split("\\$+");
		Double.parseDouble(totalvalue[1].trim());
		//Integer.parseInt(totalvalue[1].trim());
		logger.info("Subtotal of second product "+Integer.parseInt(qty)*Double.parseDouble(totalvalue[1].trim()));
		return Integer.parseInt(qty)*Double.parseDouble(totalvalue[1].trim());
	}

	public boolean validateSubTotal(double subtotal1,double subtotal2){
		driver.waitForElementPresent(By.xpath("//div[@class='col-xs-12']//div[3]/span"));
		String subTotalVal=driver.findElement(By.xpath("//div[@class='col-xs-12']//div[3]/span")).getText();
		String[] totalvalue= subTotalVal.split("\\$+");
		double subTotalValue=Double.parseDouble(totalvalue[1].trim());
		if(subTotalValue==(subtotal1+subtotal2)){
			return true;
		}else{
			return false;
		}
	}

	public void removefirstProductFromTheCart(){
		driver.waitForElementPresent(By.xpath("//a[@href='javascript:submitRemove(1);']"));
		driver.click(By.xpath("//a[@href='javascript:submitRemove(1);']"),"");
		driver.pauseExecutionFor(1500);
		driver.waitForPageLoad();
	}

	public String getProductRemovedAutoshipTemplateUpdatedMsg(){
		driver.quickWaitForElementPresent(By.xpath(".//div[@id='globalMessages']//p"));
		return driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).getText();
	}

	public String getThresholdMessageIsDisplayed(){
		driver.pauseExecutionFor(3000);
		try{
			driver.quickWaitForElementPresent(By.xpath(".//div[@id='globalMessages']//p"));
			return driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).getText();
		}catch(NoSuchElementException e){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='globalMessages']//div[@class='information_message negative']"));
			return driver.findElement(By.xpath("//div[@id='globalMessages']//div[@class='information_message negative']")).getText();
		}
	}

	public void clickOnBelowFieldsSponsorLink(){
		driver.waitForPageLoad();
		driver.waitForElementPresent(FIELD_SPONSOR_LINK_LOC);
		driver.click(FIELD_SPONSOR_LINK_LOC,"");
		driver.waitForPageLoad();
		logger.info("field sponsor link has been clicked");
	}

	public void enterDetailsInRequestASponsorForm(String firstName,String lastName,String emailId,String postalCode){
		driver.waitForElementPresent(By.id("firstName"));
		driver.type(By.id("firstName"),firstName );
		driver.type(By.id("lastName"),lastName);
		driver.type(By.id("email"),emailId);
		driver.findElement(By.id("zipcode")).sendKeys(postalCode+"\t");
		driver.waitForLoadingImageToDisappear();
		driver.click(By.xpath("//input[@value='Submit']"),"");
		logger.info("form submitted");
		driver.waitForLoadingImageToDisappear();
	}

	public boolean verifyConfirmationMessageAfterSubmission(){
		if(driver.findElement(CONFIRMATION_MESSAGE_LOC).isDisplayed()){
			String confirmationMessage = driver.findElement(CONFIRMATION_MESSAGE_LOC).getText();
			logger.info("message=="+confirmationMessage);
			if(confirmationMessage.equalsIgnoreCase("YOUR REQUEST HAS BEEN SUBMITTED")){
				return true;
			}
			else{
				return false;
			}

		}
		return false;
	}

	public void logout(){
		driver.quickWaitForElementPresent(By.id("account-info-button"));
		driver.click(By.id("account-info-button"),"");
		driver.waitForElementPresent(By.linkText("Log out"));
		driver.click(By.linkText("Log out"),"");
		logger.info("Logout");
	}	

	public void removeFirstProductFromTheCart(){
		driver.quickWaitForElementPresent(By.xpath("//a[@href='javascript:submitRemove(0);']"));
		driver.click(By.xpath("//a[@href='javascript:submitRemove(0);']"),"");
		driver.pauseExecutionFor(1500);
		driver.waitForPageLoad();
		driver.pauseExecutionFor(3000);
	}

	public void removeSecondProductFromTheCart(){
		driver.quickWaitForElementPresent(By.xpath("//a[@href='javascript:submitRemove(1);']"));
		driver.click(By.xpath("//a[@href='javascript:submitRemove(1);']"),"");
		driver.pauseExecutionFor(1500);
		driver.waitForPageLoad();
	}

	public boolean validateEmptyShoppingCartPageIsDisplayed(){
		driver.waitForElementPresent(By.xpath("//div[@id='left-shopping']//span"));
		return driver.findElement(By.xpath("//div[@id='left-shopping']//span")).getText().contains(TestConstants.AUTOSHIP_TEMPLATE_EMPTY_SHOPPING_CART_MSG);
	}

	public void clickOnContinueShoppingLinkOnEmptyShoppingCartPage(){
		driver.waitForElementPresent(By.xpath(".//div[@id='left-shopping']/a[contains(text(),'Continue shopping')]"));
		driver.click(By.xpath(".//div[@id='left-shopping']/a[contains(text(),'Continue shopping')]"),"");
		driver.waitForPageLoad();
	}

	public void clickOnAddMoreItemsBtn(){
		driver.waitForElementPresent(By.xpath("//*[@value='ADD MORE ITEMS']"));
		driver.click(By.xpath("//*[@value='ADD MORE ITEMS']"),"");
		driver.waitForPageLoad();
	}

	public String createBizToCom(String bizUrl){
		if(bizUrl.contains("biz")){
			String pws = bizUrl.replaceAll("biz","com");
			return pws;
		}else
			return bizUrl;
	}

	public void clickOnCnacelEnrollment(){
		driver.waitForElementPresent(By.xpath("//form[@id='inactiveConsultant180Form']/input[@value='Cancel Enrollment']"));
		//  driver.click(By.xpath("//form[@id='inactiveConsultant180Form']/input[@class='cancelEnrollment']"));
		driver.click(By.xpath("//form[@id='inactiveConsultant180Form']/input[@value='Cancel Enrollment']"),"");
	}

	public void clickCancelRegistrationRCBtn(){
		driver.click(By.xpath("//div[@id='activeRetailPopup']//input[@value='Cancel Registration']"),"");
		logger.info("Clicked on Cancel Registration button");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);//Deliberately added
	}
	public void enterPasswordAfterTermination(){
		driver.type(By.xpath("//input[@class='password-field']"), driver.getStoreFrontPassword());
	}

	public boolean verifyReactiveYourPCAccountPopup(){
		driver.waitForElementPresent(By.xpath("//h2[contains(text(),'REACTIVATE YOUR PC ACCOUNT')]"));
		if(driver.isElementPresent(By.xpath("//h2[contains(text(),'REACTIVATE YOUR PC ACCOUNT')]"))){
			return true;
		}else
			return false;
	}

	public void clickOnLoginToReactiveMyAccount(){
		//driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(By.xpath("//input[@value='Log In to Reactivate My Account']"));
		driver.click(By.xpath("//input[@value='Log In to Reactivate My Account']"),"");
	}

	public void clickOnCnacelEnrollmentForPC(){
		driver.waitForElementPresent(By.xpath("//div[@id='terminate-log-in']/following::input[1]"));
		driver.click(By.xpath("//div[@id='terminate-log-in']/following::input[1]"),"");
		driver.pauseExecutionFor(2000);
	}

	public String fetchingUserName(){
		return driver.findElement(By.xpath("//div[@id='account-info-button']//span[2]")).getText();
	}

	public boolean verifyUserNameAfterLoginAgain(String oldUserNameOnUI,String newUserNameOnUI){
		if(oldUserNameOnUI.equals(newUserNameOnUI)){
			return true;
		}      
		return  false;
	}

	public void uncheckCRPCheckBox(){
		//driver.waitForElementPresent(By.xpath("//li[text()='Yes, enroll me in CRP']/preceding::div[1]/input"));
		if(driver.isElementPresent(By.xpath("//input[@checked='checked']"))){
			driver.click(By.xpath("//input[@id='CRP-check']/.."),"");
		}
	}

	public boolean validateTermsAndConditionsForConsultantApplicationPulse(){
		driver.waitForElementPresent(By.xpath("//strong[contains(text(),'I have read and accepted all Terms and Conditions for the Consultant Application, Pulse')]"));
		return driver.isElementPresent(By.xpath("//strong[contains(text(),'I have read and accepted all Terms and Conditions for the Consultant Application, Pulse')]"));
	}

	public void uncheckPulseCheckBox(){
		//driver.waitForElementPresent(By.xpath("//li[contains(text(),'Yes, subscribe me to Pulse')]/preceding::div[1]/input/.."));
		if(driver.isElementPresent(By.xpath("//input[@id='pulse-check' and @checked='checked']"))){
			driver.click(By.xpath("//input[@id='pulse-check']/.."),"");
		}
	}

	public boolean validateTermsAndConditionsForConsultantApplicationCRP(){
		driver.waitForElementPresent(By.xpath("//strong[contains(text(),'I have read and accepted all Terms and Conditions for the Consultant Application, CRP')]"));
		return driver.isElementPresent(By.xpath("//strong[contains(text(),'I have read and accepted all Terms and Conditions for the Consultant Application, CRP')]"));
	}

	public boolean isCheckboxForCRPAndPulseTermsAndConditionsDisplayed(){
		return driver.isElementVisible(By.xpath("//div[@class='enroll-checkarea']//*[contains(text(),'I have read and accept the terms and conditions')]"));
	}

	public void clickOnEnrollUnderLastUpline(){
		try{
			driver.waitForElementPresent(By.id("enrollUnderLastUpline"));
			driver.pauseExecutionFor(1000);
			driver.findElement(By.id("enrollUnderLastUpline")).click();;
		}catch(Exception e){
			try{
				driver.waitForElementPresent(By.xpath("//form[@id='inactiveConsultant180Form']//input[@id='enrollUnderLastUpline']"));
				driver.pauseExecutionFor(1000);
				driver.findElement(By.xpath("//form[@id='inactiveConsultant180Form']//input[@id='enrollUnderLastUpline']")).click();
			}catch(Exception e2){
				driver.waitForElementPresent(By.xpath("//form[@id='inactivePc90Form']//input[@id='enrollUnderLastUpline']"));
				driver.pauseExecutionFor(1000);
				driver.findElement(By.xpath("//form[@id='inactivePc90Form']//input[@id='enrollUnderLastUpline']")).click();
			}
		}
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public void enterPasswordForReactivationForConsultant(){
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Reactivate My Account')]/following::input[2]"));
		driver.type(By.xpath("//h3[contains(text(),'Reactivate My Account')]/following::input[2]"), driver.getStoreFrontPassword());
	}

	public void clickOnLoginToReactiveMyAccountForConsultant(){
		////driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Reactivate My Account')]/following::a[2]/input"));
		driver.click(By.xpath("//h3[contains(text(),'Reactivate My Account')]/following::a[2]/input"),"");
		driver.waitForPageLoad();
	}

	public boolean verifyTerminatedConsultantIsNotInSponsorList(){
		driver.waitForPageLoad();
		if(driver.isElementPresent(By.xpath("//span[contains(text(),'No result found')]"))){
			return true;
		}else
			return false;
	}

	public boolean verifyTerminatedConsultantPresentInSponsorList(){
		driver.waitForPageLoad();
		if(driver.isElementPresent(By.xpath("//div[@class='sponsorDataDiv']"))){
			return true;
		}else
			return false;
	}

	public boolean isFirstNamePrepopulated(){
		driver.waitForElementPresent(By.id("first-name"));
		return driver.findElement(By.id("first-name")).getAttribute("value").length()>0;

	}

	public boolean isLastNamePrepopulated(){
		return driver.findElement(By.id("last-name")).getAttribute("value").length()>0;
	}

	public boolean isEmailAddressPrepopulated(){  
		return driver.findElement(By.id("email-account")).getAttribute("value").length()>0;

	}

	public boolean isAddressLine1Prepopulated(){
		return driver.findElement(By.id("address-1")).getAttribute("value").length()>0;
	}

	public boolean isCityPrepopulated(){
		return driver.findElement(By.id("city")).getAttribute("value").length()>0;
	}

	public boolean isSelectProvincePrepopulated(){
		return driver.findElement(By.xpath("//select[@id='state']/option[@selected='selected'][2]")).getText().length()>0;
	}

	public boolean isEnterPostalCodePrepopulated(){
		return driver.findElement(By.id("postcode")).getAttribute("value").length()>0;
	}

	public boolean isPhoneNumberPrepopulated(){
		return driver.findElement(By.id("phonenumber")).getAttribute("value").length()>0;
	}

	public void clickOnCrossIconForAddressPopup(){
		driver.waitForElementPresent(By.xpath("span[@class='icon-close']"));
		driver.click(By.xpath("//span[@class='icon-close']/.."),"");
	}

	public void clearAddressLine1(){
		driver.waitForElementPresent(By.id("address-1"));
		driver.clear(By.id("address-1"));
	}

	public boolean verifyEnterValueForMandatoryFieldPopup(){
		driver.waitForElementPresent(By.xpath("//input[@id='address-1']/following::label[1][contains(text(),'This field is required')]"));
		return driver.findElement(By.xpath("//input[@id='address-1']/following::label[1][contains(text(),'This field is required')]")).getText().contains("This field is required.");
	}

	public void clickEnrollmentNextBtnWithoutClickOnUseAsEnteredAddress() {
		driver.waitForElementPresent(By.id("enrollment-next-button"));
		//driver.pauseExecutionFor(2000);
		driver.click(By.id("enrollment-next-button"),"");
		logger.info("EnrollmentTest Next Button clicked");
		driver.waitForLoadingImageToDisappear();
		//driver.pauseExecutionFor(2000);
	}

	public void clickOnReviewAndConfirmBillingEditBtn(){
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Billing info')]/a"));
		//driver.click(By.xpath("//h3[contains(text(),'Billing info')]/a"));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//h3[contains(text(),'Billing info')]/a"),"Edit Billing on Review and Confirm");
	}

	public boolean isEnterNameOnCardPrepopulated(){
		return driver.findElement(By.id("card-name")).getAttribute("value").length()>0;
	}

	public boolean isEnterCardNumberPrepopulated(){
		driver.waitForElementPresent(By.id("card-nr"));
		return driver.findElement(By.id("card-nr")).getAttribute("value").length()>0;
	}

	public void clickEnrollmentNextBtnWithoutHandlingPopUP(){
		driver.waitForElementPresent(By.id("enrollment-next-button"));
		//driver.pauseExecutionFor(2000);
		driver.click(By.id("enrollment-next-button"),"");
		logger.info("EnrollmentTest Next Button clicked");
		driver.waitForLoadingImageToDisappear();
	}

	public String getDotComPWS(){
		driver.waitForElementPresent(By.xpath("//p[@id='prefix-validation']/span[1]"));
		String pwsUnderPulse = driver.findElement(By.xpath("//p[@id='prefix-validation']/span[1]")).getText();
		String[] pws = pwsUnderPulse.split("/");
		String comPwsString = pws[0]+"/"+pws[1]+"/"+pws[2]+"/"+pws[3];
		logger.info("Com Pws String from UI is "+comPwsString);
		return comPwsString;
	}

	public String getDotBizPWS(){
		driver.waitForElementPresent(By.xpath("//p[@id='prefix-validation']/span[2]"));
		driver.pauseExecutionFor(2000);
		String pwsUnderPulse = driver.findElement(By.xpath("//p[@id='prefix-validation']/span[2]")).getText();
		String[] pws = pwsUnderPulse.split("/");
		String bizPwsString = pws[0]+"/"+pws[1]+"/"+pws[2]+"/"+pws[3];
		logger.info("Biz Pws String from UI is "+bizPwsString);
		return bizPwsString;
	}

	public boolean verifyCurrentUrlContainComSite(){  
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("com");
	}

	public boolean verifyCurrentUrlContainBizSite(){  
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("biz");
	}

	public boolean verifyCreateAccountpageIsDisplayed(){
		return driver.findElement(By.xpath("//h1[contains(text(),'Set Up Account')]")).isDisplayed();
	}

	public boolean verifyCRPSelectionpageIsDisplayed(){
		return driver.findElement(By.xpath("//div[@id='main-content']//div[contains(text(),'Consultant Replenishment Program')]")).isDisplayed();
	}

	public void selectInvalidNewBillingCardExpirationDate(){
		driver.click(By.id("expiryMonth"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='expiryMonth']/option[10]"));
		driver.click(By.xpath("//select[@id='expiryMonth']/option[10]"),"");
		driver.click(By.id("expiryYear"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='expiryYear']/option[2]"));
		driver.click(By.xpath("//select[@id='expiryYear']/option[2]"),"");
		logger.info("Invalid expiration date is selected");
	}

	public boolean validateInvalidCreditCardExpiryDate(){
		if(driver.findElement(By.xpath("//input[@id='monthYear']/following::label[1][contains(text(),'Must be a valid Expiration Date')]")).isDisplayed()){
			return true;
		}
		else{
			return false;
		}
	}

	public void selectKitAndEnrollmentType(String kitName,String regimenName,String enrollmentType){
		selectEnrollmentKitPage(kitName, regimenName);  
		chooseEnrollmentOption(enrollmentType);
	}

	public String getErrorMessageForInvalidSponser(){
		String errorMessage=driver.findElement(By.xpath("//div[@id='sponsorPage']//span[contains(text(),'No result found for')]")).getText();
		return errorMessage;
	}

	/***
	 *  Verify if search not found message displayed or not for sponsor search while PC/RC enrollment
	 * @return trur/false based on whether No Result found message displayed or not
	 */
	public boolean isNoResultFoundMsgDisplayedForRCPCSponsorSearch(){
		driver.pauseExecutionFor(1000);
		return driver.isElementVisible(By.xpath("//span[contains(text(),'No result found')]"));	
	}

	public void clickOnEnrollUnderLastUplineProcessToPopupDisappear(String consultantEmailID) {
		while(true){
			if(!driver.findElement(By.id("inactiveConsultant180Popup")).getCssValue("display").contains("none")){
				clickOnEnrollUnderLastUpline();
				hoverOnShopLinkAndClickAllProductsLinks();
				selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
				clickOnCheckoutButton();
				enterEmailAddress(consultantEmailID);
			}else{
				break;
			}
		}
	}

	public int getQuantityValueForTheFirstProduct(){
		driver.waitForElementPresent(By.xpath("//input[@id='quantity0']"));
		String qty=driver.findElement(By.xpath("//input[@id='quantity0']")).getAttribute("value");
		return Integer.parseInt(qty);
	}

	public int getQuantityValueForTheSecondProduct(){
		driver.waitForElementPresent(By.xpath("//input[@id='quantity1']"));
		String qty=driver.findElement(By.xpath("//input[@id='quantity1']")).getAttribute("value");
		return Integer.parseInt(qty);
	}

	public void enterNewShippingAddressNameDuringEnrollment(String name){
		driver.waitForElementPresent(By.id("attention"));
		driver.clear(By.id("attention"));
		driver.type(By.id("attention"),name);
		logger.info("New Shipping Address name is "+name);
	}

	public void enterNewShippingAddressCityDuringEnrollment(String city){
		driver.clear(By.id("city"));
		driver.type(By.id("city"),city);
		logger.info("New Shipping City is "+city);
	}

	public void selectNewShippingAddressStateDuringEnrollment(){
		driver.click(By.id("state"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='state']//option[2]"));
		driver.click(By.xpath("//select[@id='state']//option[2]"),"");
		logger.info("State/Province selected");
	}

	public void clickEditShipping(){
		//driver.waitForElementPresent(By.xpath("//div[@id='multiple-addresses-summary']/div/div[2]/a"));
		driver.click(By.xpath("//div[@id='multiple-addresses-summary']/div/div[2]/a"),"");
		logger.info("Edit shipping link clicked.");
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOnSaveShippingProfileAfterEdit() {
		//driver.waitForElementPresent(By.id("saveShippingAddreessId"));
		driver.click(By.id("saveShippingAddreessId"),""); 
		driver.waitForLoadingImageToDisappear();
	}

	public boolean verifyUpdatedShippingAddress(String address){
		try{
			driver.findElement(By.xpath("//div[@id='multiple-addresses-summary']/div//span[contains(text(),'"+address+"')]"));
			return true;
		}catch(NoSuchElementException e){
			String word = Character.toUpperCase(address.charAt(0)) + address.substring(1);
			if(driver.isElementPresent(By.xpath("//div[@id='multiple-addresses-summary']/div//span[contains(text(),'"+word+"')]"))){
				return true;
			}else{
				return false;
			}
		}
	}

	public boolean isDefaultShippingAddressRadioBtnSelected(String defaultAddressFirstName) {
		try{
			driver.findElement(By.xpath("//span[contains(text(),'"+defaultAddressFirstName+"')]/following::span/input"));
			return driver.findElement(By.xpath("//span[contains(text(),'"+defaultAddressFirstName+"')]/following::span/input")).isSelected();
		}catch(NoSuchElementException e){
			String word = Character.toUpperCase(defaultAddressFirstName.charAt(0)) + defaultAddressFirstName.substring(1);
			if(driver.isElementPresent(By.xpath("//span[contains(text(),'"+word+"')]/following::span/input"))){
				return driver.findElement(By.xpath("//span[contains(text(),'"+word+"')]/following::span/input")).isSelected();
			}else {
				return false;
			}
		}
	}

	public boolean verifyShippingAddressOnOrderPage(String address){
		try{
			driver.findElement(By.xpath("//div[@id='checkout_summary_deliverymode_div']//span[contains(text(),'"+address+"')]"));
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
	}

	public boolean verifyShippingAddressIsPresentOnReviewPage(String name){
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']/descendant::span[@class='font-bold' and contains(text(),'"+name+"')][1]"));
		return driver.isElementPresent(By.xpath("//div[@id='summarySection']/descendant::span[@class='font-bold' and contains(text(),'"+name+"')][1]"));
	}

	public StoreFrontAccountInfoPage clickOnEditShippingOnReviewAndConfirmPage(){
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']/div[4]//a[contains(text(),'Edit')]"));
		driver.click(By.xpath("//div[@id='summarySection']/div[4]//a[contains(text(),'Edit')]"),"Edit shipping link on review and confirm page");
		return new StoreFrontAccountInfoPage(driver);
	}

	public void editFirstName(String firstName){
		driver.waitForElementPresent(By.id("first-name"));
		driver.type(By.id("first-name"), firstName);
		logger.info("first name entered as "+firstName);
	}

	public void clickOnEditBillingProfile() {
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-billing-profiles']//a[text()='Edit']"));
		driver.click(By.xpath("//div[@id='multiple-billing-profiles']//a[text()='Edit']"),"Edit billing profile link");
	}

	public String getProductName(){
		if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath(".//div[@id='main-content']/div[4]/div[2]/div[1]/h3/a"));
			String productName=driver.findElement(By.xpath(".//div[@id='main-content']/div[4]/div[2]/div[1]/h3/a")).getText();
			logger.info("fetched product name is "+productName);
			return productName;
		}
		else {
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::h3[1]/a"));
			String productName=driver.findElement(By.xpath("//div[@id='main-content']/descendant::h3[1]/a")).getText();
			logger.info("fetched product name is "+productName);
			return productName;
		}		
	}


	public boolean verifyNumberOfProductsInMiniCart(){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.waitForElementPresent(By.xpath("//a[@id='shopping-cart']")); 
		WebElement allProducts = driver.findElement(By.xpath("//a[@id='shopping-cart']"));
		actions.moveToElement(allProducts).build().perform();
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath("//ul[@id='subtotal']/li/span[1]")); 
		String productCount=driver.findElement(By.xpath("//ul[@id='subtotal']/li/span[1]")).getText();
		//		Boolean bCount;
		//		if productCoun
		return productCount!="0";
	}

	public boolean isProductImageExist(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='big-picture']/img"));
		return driver.isElementPresent(By.xpath("//div[@id='big-picture']/img"));
	}

	public boolean verifyProductName(String ProductName){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='product-details']/div[1]/div/h1"));
		String name=driver.findElement(By.xpath("//div[@id='product-details']/div[1]/div/h1")).getText();
		return name.contains(ProductName);
	}

	public boolean isModalWindowExists(){
		driver.quickWaitForElementPresent(By.id("popup-quickinfo"));
		return driver.isElementPresent(By.id("popup-quickinfo"));
	}

	public void updateProductQuantityOnModalWindowAndProceedToBuy(String qty){
		driver.waitForElementPresent(By.id("quickinfo-quantity"));
		driver.clear(By.id("quickinfo-quantity"));
		driver.type(By.id("quickinfo-quantity"),qty);
		logger.info("quantity added is "+qty);
		driver.click(By.xpath("//form[@id='productDetailFormQuickInfo']/input[3]"),"");
		driver.pauseExecutionFor(5500);
		logger.info("Update button clicked after adding quantity");
		driver.waitForPageLoad();
	}

	public void clickMiniCart(){
		driver.waitForElementPresent(By.xpath("//a[@id='shopping-cart']"));
		driver.click(By.xpath("//a[@id='shopping-cart']"),"");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public double getSubTotalOnShoppingCartPage(){
		driver.waitForElementPresent(By.xpath("//div[@id='subtotal-shopping']//span"));
		String total = driver.findElement(By.xpath("//div[@id='subtotal-shopping']//span")).getText();
		String total1[] = total.split("\\$");
		return Double.parseDouble(total1[1]);
	}


	public boolean verifySubTotalAccordingToQuantity(String qty,double subTotalOfAddedProduct,double subTotalOfAfterUpdate){
		double quantity = Double.parseDouble(qty);
		double totalAfterUpdate = (quantity*subTotalOfAddedProduct);
		if(totalAfterUpdate == (subTotalOfAfterUpdate)){
			return true;}
		else{return false;}
	}

	public void clickProductLinkForProductDetail(){
		driver.waitForElementPresent(By.xpath("//div[@id='mini-shopping']/div[1]/div/div[2]/div[1]/a"));
		driver.click(By.xpath("//div[@id='mini-shopping']/div[1]/div/div[2]/div[1]/a"),"");
		driver.waitForPageLoad();
	}

	public boolean validateQuickShopScreen(){
		driver.pauseExecutionFor(1500);
		return driver.getCurrentUrl().contains("quickShop");
	}

	public boolean verifyAddToBagButton() {
		if(driver.getCountry().equalsIgnoreCase("CA") || driver.getCountry().equalsIgnoreCase("AU")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::button[contains(text(),'ADD TO BAG')][1]"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::button[contains(text(),'ADD TO BAG')][1]"));
		}
		else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div[1]//form[@id='productDetailForm']/button"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div[1]//form[@id='productDetailForm']/button"));

		}
		return false;

	}

	public void clickAddToBagButtonOnQuickInfoPopup(){
		driver.quickWaitForElementPresent(By.xpath("//form[@id='productDetailFormQuickInfo']/input[3]"));
		driver.click(By.xpath("//form[@id='productDetailFormQuickInfo']/input[3]"),"");
		logger.info("Add To Bag button clicked from Quick Info Screen");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();

	}

	public boolean verifyAddToBagButtonOnQuickInfoPopup() {
		return driver.isElementVisible(By.xpath("//form[@id='productDetailFormQuickInfo']/input[3]"));

	}

	public void clickViewProductDetailLink(){
		driver.quickWaitForElementPresent(By.id("quickinfo-view-details"));
		driver.click(By.xpath("//p[@id='quickinfo-view-details']/a"),"");
		logger.info("view product detail link clicked");
		driver.waitForPageLoad();
	}

	public boolean verifyAddToBagButtonOnProductDetailPage(){
		return driver.isElementVisible(By.xpath("//*[contains(@id,'shop')]//input[@value='ADD TO BAG']"));
	}

	public boolean verifyProductPrice(String priceFromProductPage){
		driver.quickWaitForElementPresent(By.xpath("//p[@id='your-price']//span"));
		String price=driver.findElement(By.xpath("//p[@id='your-price']//span")).getText();
		return priceFromProductPage.contains(price)||price.contains(priceFromProductPage);
	}

	public void enterSponsorNameAndClickOnSearchForPCAndRC(){
		driver.waitForElementPresent(By.xpath("//input[@id='sponsor-name-id']"));
		driver.type(By.xpath("//input[@id='sponsor-name-id']"), "mary");
		driver.click(By.xpath("//input[@value='Search']"),"");
	}

	public boolean verifyPCPerksCheckBoxIsSelected(){
		return driver.isElementPresent(By.xpath("//input[@id='pc-customer2'][@class='checked']"));
	}

	public boolean validateRFConnectionLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'RF Connection')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'RF Connection')]"),"");
		driver.pauseExecutionFor(5000);
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		String parentWindow=it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
		boolean status= driver.getCurrentUrl().contains("rfconnection");
		driver.close();
		driver.switchTo().window(parentWindow);
		return status;
	}

	public boolean validateCareersLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Careers')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'Careers')]"),"");
		driver.pauseExecutionFor(4000);
		return driver.getCurrentUrl().contains("careers");
	}

	public boolean validateDisclaimerLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Disclaimer')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'Disclaimer')]"),"");
		driver.pauseExecutionFor(5000);
		return driver.getCurrentUrl().contains("disclaimer");
	}

	public boolean validatePrivacyPolicyLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Privacy Policy')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'Privacy Policy')]"),"");
		driver.pauseExecutionFor(5500);
		return driver.getCurrentUrl().contains("privacy-policy");
	}

	public boolean validateTermsConditionsLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Terms')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'Terms')]"),"");
		driver.pauseExecutionFor(3000);
		return driver.getCurrentUrl().contains("terms");
	}

	public boolean validatePressRoomLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Press Room')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'Press Room')]"),"");
		driver.pauseExecutionFor(5000);
		return driver.getCurrentUrl().contains("pressroom");
	}

	public boolean validateDermRFLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Derm RF')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'Derm RF')]"),"");
		driver.pauseExecutionFor(8000);
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		String parentWindow=it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
		boolean status= driver.getCurrentUrl().contains("dermrf");
		driver.close();
		driver.switchTo().window(parentWindow);
		return status;
	}

	public boolean validateCountryFlagDropDownBtn(){
		return driver.isElementPresent(By.xpath("//div[@class='hidden-xs footer-content']//button")) ;
	}

	public boolean validateLoginFunctionality(){
		driver.waitForElementPresent(LOGIN_LINK_LOC);
		driver.click(LOGIN_LINK_LOC,"");
		logger.info("login link clicked");
		driver.pauseExecutionFor(1500);
		return driver.isElementPresent(USERNAME_TXTFLD_LOC) && driver.isElementPresent(PASSWORD_TXTFLD_LOC) 
				&&  driver.isElementPresent(LOGIN_BTN_LOC)  && driver.isElementPresent(FORGOT_PASSWORD_LOC);
	}

	public boolean validateTopNavigationMenuElements(){
		driver.pauseExecutionFor(1000);
		return driver.isElementPresent(By.xpath("//div[@id='header']//li[@id='CompanyBar']")) &&
				driver.isElementPresent(By.xpath("//div[@id='header']//li[@id='BusinessSystemBar']"))  &&
				driver.isElementPresent(By.xpath("//div[@id='header']//li[@id='ShopOurProductsBar']"));
	}

	public boolean verifyRedefineCategory(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']//h2"));
		return driver.isElementPresent(By.xpath("//div[@id='main-content']//h2"));
	}

	public boolean verifyProductsInRedefineCategory(){
		if(driver.getCountry().equalsIgnoreCase("US")){
			driver.waitForElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div"));
		}else if(driver.getCountry().equalsIgnoreCase("CA")){
			driver.waitForElementPresent(By.xpath("//div[@id='main-content']/div[5]/div"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[5]/div"));
		}
		return false;
	}

	public boolean verifyReverseCategory(){
		driver.quickWaitForElementPresent(By.xpath("//h2[text()='REVERSE']"));
		return driver.isElementPresent(By.xpath("//h2[text()='REVERSE']"));
	}

	public boolean verifyProductsInReverseCategory(){
		driver.waitForElementPresent(By.xpath("//h2[text()='REVERSE']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
		return driver.isElementPresent(By.xpath("//h2[text()='REVERSE']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
	}

	public boolean verifySootheCategory(){
		driver.quickWaitForElementPresent(By.xpath("//h2[text()='SOOTHE']"));
		return driver.isElementPresent(By.xpath("//h2[text()='SOOTHE']"));
	}

	public boolean verifyProductsInSootheCategory(){
		driver.waitForElementPresent(By.xpath("//h2[text()='SOOTHE']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
		return driver.isElementPresent(By.xpath("//h2[text()='SOOTHE']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
	}

	public boolean verifyUnblemishCategory(){
		driver.quickWaitForElementPresent(By.xpath("//h2[text()='UNBLEMISH']"));
		return driver.isElementPresent(By.xpath("//h2[text()='UNBLEMISH']"));
	}

	public boolean verifyProductsInUnblemishCategory(){
		driver.waitForElementPresent(By.xpath("//h2[text()='UNBLEMISH']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
		return driver.isElementPresent(By.xpath("//h2[text()='UNBLEMISH']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
	}

	public boolean verifyEssentialCategory(){
		driver.quickWaitForElementPresent(By.xpath("//h2[text()='ESSENTIALS']"));
		return driver.isElementPresent(By.xpath("//h2[text()='ESSENTIALS']"));
	}

	public boolean verifyProductsInEssentialCategory(){
		driver.waitForElementPresent(By.xpath("//h2[text()='ESSENTIALS']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
		return driver.isElementPresent(By.xpath("//h2[text()='ESSENTIALS']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
	}

	public boolean verifyEnhancementCategory(){
		driver.quickWaitForElementPresent(By.xpath("//h2[text()='ENHANCEMENTS']"));
		return driver.isElementPresent(By.xpath("//h2[text()='ENHANCEMENTS']"));
	}

	public boolean verifyProductsInEnhancementCategory(){
		driver.waitForElementPresent(By.xpath("//h2[text()='ENHANCEMENTS']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
		return driver.isElementPresent(By.xpath("//h2[text()='ENHANCEMENTS']/../following-sibling::div[contains(@class,'quick-product-wrapper')]/div"));
	}

	public boolean verifyAddToBagButton(int number) {
		logger.info("Random Selected product for detail assertion is "+number);
		if(driver.getCountry().equalsIgnoreCase("CA")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/div[2]/div[1]//button"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/div[2]/div[1]//button"));
		}
		else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div["+number+"]/div[2]/div[1]//button"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div["+number+"]/div[2]/div[1]//button"));

		}
		return false;

	}

	public boolean verifyRetailPriceOfProduct(int number) {
		if(driver.getCountry().equalsIgnoreCase("CA")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/p/span[1]"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/p/span[1]"));
		}
		else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div["+number+"]/p/span[1]"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div["+number+"]/p/span[1]"));

		}
		return false;

	}

	public boolean verifyMyPriceOfProduct(int number) {
		if(driver.getCountry().equalsIgnoreCase("CA")){
			driver.quickWaitForElementPresent(By.xpath(".//div[@id='main-content']/div[5]/div["+number+"]/p/span[2]"));
			return driver.isElementPresent(By.xpath(".//div[@id='main-content']/div[5]/div["+number+"]/p/span[2]"));
		}
		else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div["+number+"]/p/span[2]"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div["+number+"]/p/span[2]"));

		}
		return false;

	}

	public boolean verifyAddToCRPButton(int number) {
		if(driver.getCountry().equalsIgnoreCase("CA")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/div[2]/div[2]//form[@id='productDetailForm']"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/div[2]/div[2]//form[@id='productDetailForm']"));
		}
		else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div["+number+"]/div[2]/div[2]//button"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div["+number+"]/div[2]/div[2]//button"));

		}
		return false;

	}

	public boolean verifySVValue(int number) {
		if(driver.getCountry().equalsIgnoreCase("CA")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/p"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/p"));
		}
		else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div["+number+"]/p"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div["+number+"]/p"));

		}
		return false;

	}

	public boolean verifyAddToPCPerksButton(int number) {
		if(driver.getCountry().equalsIgnoreCase("CA")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/div[2]/div[2]//input[2]"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[5]/div["+number+"]/div[2]/div[2]//input[2]"));
		}
		else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div["+number+"]/div[2]/div[2]//input[2]"));
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div["+number+"]/div[2]/div[2]//input[2]"));
		}
		return false;
	}

	public boolean verifyUrlAfterplacedAnAdhocOrder(String urlBeforeOrderPlaced, String urlAfterOrderPlaced){
		return urlBeforeOrderPlaced.equalsIgnoreCase(urlAfterOrderPlaced);
	}

	public String getProductName(int num){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]/preceding::a[1]"));
		String productName=driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]/preceding::a[1]")).getText();
		logger.info("fetched product name is "+productName);
		return productName;	
	}

	public String getProductPrice(int num){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]"));
		String productName=driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]")).getText();
		logger.info("fetched product price is "+productName);
		return productName;

	}

	public void mouseHoverProductAndClickQuickInfo(int num){  
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.waitForElementPresent(By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]/preceding::img[1]")); 
		WebElement allProducts = driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]/preceding::img[1]"));
		//actions.moveToElement(allProducts).build().perform();
		JavascriptExecutor js = (JavascriptExecutor)(RFWebsiteDriver.driver); 
		String strJavaScript = "var element = arguments[0];"
				+ "var mouseEventObj = document.createEvent('MouseEvents');"
				+ "mouseEventObj.initEvent( 'mouseover', true, true );"
				+ "element.dispatchEvent(mouseEventObj);";
		js.executeScript(strJavaScript, allProducts);
		logger.info("mouse hover operation performed");
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]/preceding::input[contains(@value,'Quick View')][1]"));
		//driver.click(By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]/preceding::input[contains(@value,'Quick View')][1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='main-content']/descendant::span[contains(@class,'price')]["+num+"]/preceding::input[contains(@value,'Quick View')][1]"),"");
		driver.pauseExecutionFor(5000);
	}

	public boolean verifyAddToPCPerksButtonOnQuickInfoPopup(){
		driver.quickWaitForElementPresent(By.xpath("//form[@id='productDetailForm']/input[3]"));
		return driver.isElementPresent(By.xpath("//form[@id='productDetailForm']/input[3]"));
	}

	public boolean verifyAddToPCPerksButtonOnProductDetailPage(){
		driver.quickWaitForElementPresent(By.xpath("//input[@value='Add to PC Perks']"));
		return driver.isElementPresent(By.xpath("//input[@value='Add to PC Perks']"));
	}

	public boolean verifyProductLongDescription(){
		driver.quickWaitForElementPresent(By.id("description-box"));
		return driver.isElementPresent(By.id("description-box"));
	}

	public boolean verifyProductDescriptionBox(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='product-description']//li[contains(text(),'Description')]"));
		return driver.isElementPresent(By.xpath("//div[@id='product-description']//li[contains(text(),'Description')]"));
	}

	public boolean verifyProductUsageNotesBox(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='product-description']//li[contains(text(),'Usage Notes')]"));
		return driver.isElementPresent(By.xpath("//div[@id='product-description']//li[contains(text(),'Usage Notes')]"));
	}

	public boolean verifyProductIngredientsBox(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='product-description']//li[contains(text(),'Ingredients')]"));
		return driver.isElementPresent(By.xpath("//div[@id='product-description']//li[contains(text(),'Ingredients')]"));
	}

	public boolean verifyAddToCRPButtonOnQuickInfoPopup(){
		return driver.isElementVisible(By.xpath("//*[contains(@class,'popup-quickinfo')]//input[@value='Add to crp']"));
	}

	public boolean verifyAddToCRPButtonOnProductDetailPage(){
		return driver.isElementVisible(By.xpath("//*[contains(@id,'shop')]//input[@value='Add to crp']"));
	}

	public boolean verifyPriceFromLowToHigh(){
		String firstProductPrice = null;
		String secondProductPrice = null;
		String thirdProductPrice = null;
		driver.waitForElementPresent(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][1]"));
		firstProductPrice = driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][1]")).getText().split("\\$")[1].trim();
		try{
			secondProductPrice = driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][2]")).getText().split("\\$")[1].trim();
		}catch(Exception e){
			secondProductPrice = driver.findElement(By.xpath("//section[contains(@class,'productCatPage')]/div[2]/descendant::span[@class='your-price'][1]")).getText().split("\\$")[1].trim();
		}
		try{
			thirdProductPrice = driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][3]")).getText().split("\\$")[1].trim();
		}catch(Exception e){
			thirdProductPrice = driver.findElement(By.xpath("//section[contains(@class,'productCatPage')]/div[2]/descendant::span[@class='your-price'][2]")).getText().split("\\$")[1].trim();
		}
		logger.info("price of first product "+firstProductPrice);
		logger.info("price of second product "+secondProductPrice);
		logger.info("price of third product "+thirdProductPrice);

		if(Double.parseDouble(thirdProductPrice)>Double.parseDouble(secondProductPrice)){
			if(Double.parseDouble(secondProductPrice)>Double.parseDouble(firstProductPrice)){
				return true;
			}else{
				return false;
			}
		}return false;
	}

	public boolean verifyPriceFromHighTolow(){
		String firstProductPrice = null;
		String secondProductPrice = null;
		String thirdProductPrice = null;
		driver.waitForElementPresent(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][1]"));
		firstProductPrice = driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][1]")).getText().split("\\$")[1].trim();
		secondProductPrice = driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][2]")).getText().split("\\$")[1].trim();
		thirdProductPrice = driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][3]")).getText().split("\\$")[1].trim();
		logger.info("price of first product "+firstProductPrice);
		logger.info("price of second product "+secondProductPrice);
		logger.info("price of third product "+thirdProductPrice);

		if(Double.parseDouble(firstProductPrice)>Double.parseDouble(secondProductPrice)){
			if(Double.parseDouble(secondProductPrice)>Double.parseDouble(thirdProductPrice)){
				return true;
			}else{
				return false;
			}
		}return false;
	}

	public boolean verifyPriceAfterDeselectThefilter(String priceBeforeApplyFilter){
		return driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][1]")).getText().split("\\$")[1].trim().contains(priceBeforeApplyFilter);
	}

	public String getProductNameBeforeApplyProductFilter(){
		driver.waitForElementPresent(By.xpath("//div[@id='main-content']//div[@class='quick-shop-section-header']/h2"));
		return driver.findElement(By.xpath("//div[@id='main-content']//div[@class='quick-shop-section-header']/h2")).getText();
	}

	public int getSizeOfProductFilter(){  
		driver.waitForElementPresent(By.xpath("//input[@value='- Product(s) -']"));
		driver.click(By.xpath("//input[@value='- Product(s) -']"),"");
		driver.pauseExecutionFor(4000);
		int sizeOfProduct = driver.findElements(By.xpath("//ul[contains(@class,'refine-products')][contains(@style,'display: block;')]/li")).size();
		driver.click(By.xpath("//input[@class='refine-products-button']"),"");
		return sizeOfProduct;
	}

	public boolean verifyProductFilterIsApply(int i){
		driver.waitForElementPresent(By.xpath("//input[@value='- Product(s) -']"));
		driver.click(By.xpath("//input[@value='- Product(s) -']"),"");
		driver.waitForElementPresent(By.xpath("//ul[contains(@class,'refine-products')][contains(@style,'display: block;')]/li[1]"));
		String productNameFromfilter = driver.findElement(By.xpath("//ul[contains(@class,'refine-products')][contains(@style,'display: block;')]/li["+i+"]//div[contains(@class,'dropdown-items text')]")).getText().trim();
		driver.click(By.xpath("//ul[contains(@class,'refine-products')][contains(@style,'display: block;')]/li["+i+"]//div[@class='pull-right']//input/.."),"");
		driver.waitForPageLoad();
		String productNameFromUI = driver.findElement(By.xpath("//div[@class='quick-shop-section-header']/h2")).getText().trim();
		driver.click(By.xpath("//a[contains(text(),'Clear All')]"),"");
		driver.waitForPageLoad();
		System.out.println(productNameFromUI+"  "+productNameFromfilter+"  "+i);
		return productNameFromUI.contains(productNameFromfilter);
	}

	public void clickOnClearAll(){
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Clear All')]"));
		driver.click(By.xpath("//a[contains(text(),'Clear All')]"),"");
		driver.waitForPageLoad();
	}

	public boolean verifyProductNameAfterRemoveProductFilter(String productNameBeforeApplyFilter){
		driver.waitForElementPresent(By.xpath("//div[@class='quick-shop-section-header']/h2"));
		return driver.findElement(By.xpath("//div[@class='quick-shop-section-header']/h2")).getText().trim().contains(productNameBeforeApplyFilter.trim());
	}

	public void clickEditShippingInShipmentOnCheckoutPage(){
		//	driver.waitForElementPresent(By.xpath("//div[@id='checkout_summary_deliverymode_div']/div[1]/a[text()='Edit']"));
		driver.click(By.xpath("//div[@id='checkout_summary_deliverymode_div']/div[1]/a[text()='Edit']"),"");
		logger.info("Edit For shipping address clicked.");
	}

	public String getPCPerksMessageFromModalPopup(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='quickinfo-shop']/div[contains(@class,'pc-perks')]/p"));
		return driver.findElement(By.xpath("//div[@id='quickinfo-shop']/div[contains(@class,'pc-perks')]/p")).getText();
	}

	public boolean verifyPCPerksTermsAndConditionsPopupPresent() {
		boolean isPCPerksTermsAndConditionsPopup = false;
		driver.waitForElementPresent(By.xpath("//div[@id='pcperktermsconditions']//input[@value='Ok']"));
		isPCPerksTermsAndConditionsPopup = driver.IsElementVisible(driver.findElement(By.xpath("//div[@id='pcperktermsconditions']//input[@value='Ok']")));
		if(isPCPerksTermsAndConditionsPopup==true){
			return true;
		}
		return false;
	}


	public String getPCPerksTermsAndConditionsPopupText(){
		String textFromUI=driver.findElement(By.xpath("//div[@id='pcperktermsconditions']//p[1]")).getText();
		logger.info("terms and candition text from UI is "+textFromUI);
		return textFromUI;
	}


	public void clickPCPerksTermsAndConditionPopupOkay(){
		driver.click(By.xpath("//div[@id='pcperktermsconditions']//input[@value='Ok']"),"");
		logger.info("PC Perks terms and candition popup okay button clicked");
	}

	public boolean validateDiscountForEnrollingAsPCUser(String discountText){
		driver.quickWaitForElementPresent(By.xpath("//span[contains(text(),'"+discountText+"')]/preceding-sibling::div/input[@class='checked']"));
		if(driver.isElementPresent(By.xpath("//span[contains(text(),'"+discountText+"')]/preceding-sibling::div/input[@class='checked']"))){
			return true;
		}else{
			return false;
		}
	}

	public void clickOnPCPerksLearnMoreLink() {
		driver.waitForElementPresent(By.xpath("//div[@id='shopping-wrapper']//a[text()='Learn more']"));
		driver.click(By.xpath("//div[@id='shopping-wrapper']//a[text()='Learn more']"),"");
		driver.waitForLoadingImageToDisappear();
	}

	public boolean verifyModalWindowIsPresent() {
		driver.waitForElementPresent(By.xpath("//div[@id='popup-pcperks']"));
		if(driver.isElementPresent(By.xpath("//div[@id='popup-pcperks']"))){
			return true;}
		else{
			return false;
		}
	}

	public void clickOnModalWindowCloseIcon() {
		driver.click(By.xpath("//span[@class='icon-close']"),"");
	}

	public void clickOnOrderSummaryPCPerksPromoLink() {
		driver.waitForElementPresent(By.xpath("//div[@id='shopping-wrapper']//div[@class='mg-tp-5']/following::a/span"));
		driver.click(By.xpath("//div[@id='shopping-wrapper']//div[@class='mg-tp-5']/following::a/span"),"");		  
	}

	public String getPCPerksTermsAndConditionsPopupHeaderText(){
		driver.waitForElementPresent(By.xpath("//div[@id='pcperktermsconditions']//h2"));
		String textFromUI=driver.findElement(By.xpath("//div[@id='pcperktermsconditions']//h2")).getText();
		logger.info("terms and candition Header text from UI is "+textFromUI);
		return textFromUI;
	}

	public boolean verifyNotYourSponsorLinkPresent() {
		driver.waitForElementPresent(By.xpath("//a[@id='not-your-sponsor']"));
		if(driver.isElementPresent(By.xpath("//a[@id='not-your-sponsor']"))){
			logger.info("NOT YOUR SPONSOR LINK PRESENT");
			return true;
		}
		return false;
	}


	public boolean isNotYourSponsorLinkPresent() {
		return driver.isElementVisible(By.xpath("//a[@id='not-your-sponsor']"));
	}

	public boolean verifyContinueWithoutSponsorLinkIsPresent() {
		return driver.isElementPresent(By.xpath("//a[@id='continue-no-sponsor']"));
	}

	public void clickOnSponsorName(){
		driver.waitForElementPresent(By.xpath("//div[@id='header-middle-top']//a"));
		driver.click(By.xpath("//div[@id='header-middle-top']//a"),"");
	}

	public boolean verifyContactBoxIsPresent(){
		driver.waitForElementPresent(By.xpath("//div[@class='contactBox']"));
		return driver.isElementPresent(By.xpath("//div[@class='contactBox']"));
	}

	public boolean verifyEmailIdIsPresentInContactBox(){
		driver.waitForElementPresent(By.xpath("//a[@id='txtContactMe']"));
		return driver.isElementPresent(By.xpath("//a[@id='txtContactMe']"));
	}

	public boolean verifyPhoneNumberIsPresentInContactBox(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//span[@class='icon-phone iconMsg']/following::a[1]"));
	}

	//	public void clickOnEditMyPWS(){
	//		driver.waitForElementPresent(By.xpath("//a[contains(text(),'EDIT MY PWS')]"));
	//		driver.click(By.xpath("//a[contains(text(),'EDIT MY PWS')]"));
	//	}

	public void enterPhoneNumberOnEditPWS(String number){
		driver.waitForElementPresent(By.id("phone"));
		driver.type(By.id("phone"), number);
	}

	public void clickCancelBtnOnEditConsultantInfoPage(){
		driver.quickWaitForElementPresent(By.xpath("//div[contains(@class,'top-save')]//a[text()='CANCEL']"));
		driver.click(By.xpath("//div[contains(@class,'top-save')]//a[text()='CANCEL']"),"");
		driver.pauseExecutionFor(1000);
	}

	public boolean validateSubmissionGuideLinesLink(){
		//click Submission Guidelines Link..
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Submission Guidelines')]"));
		driver.click(By.xpath("//a[contains(text(),'Submission Guidelines')]"),"");
		driver.pauseExecutionFor(4000);
		String parentWindowID=driver.getWindowHandle();
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		boolean status=false;
		while(it.hasNext()){
			String childWindowID=it.next();
			if(!parentWindowID.equalsIgnoreCase(childWindowID)){
				driver.switchTo().window(childWindowID);
				if(driver.getCountry().equalsIgnoreCase("CA")){
					if(driver.getCurrentUrl().toLowerCase().contains("pws_profile_guidelines_can.pdf")){
						status=true;
					}
				}else if(driver.getCountry().equalsIgnoreCase("US")){
					if(driver.getCurrentUrl().toLowerCase().contains("pws_profile_guidelines_us.pdf")){
						status=true;
					}
				}
				else{
					if(driver.getCurrentUrl().toLowerCase().contains("au.pdf")){
						status=true;
					}
				}
				driver.close();
				driver.switchTo().window(parentWindowID);
				return status;

			}
		}
		return status;
	}

	public void clickSaveBtnOnEditConsultantInfoPage(){
		driver.quickWaitForElementPresent(By.xpath("//div[contains(@class,'top-save')]//input[@class='edit-meet-your-consultant btn btn-primary']"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[contains(@class,'top-save')]//input[@class='edit-meet-your-consultant btn btn-primary']"),"");
		driver.pauseExecutionFor(1000);
		driver.waitForLoadingImageToDisappear();
	}

	public void enterUserInformationForEnrollmentWithTerminatedEmail(String kitName,String regimenName,String enrollmentType,String firstName,String lastName,String emailaddress,String password,String addressLine1,String city,String postalCode,String phoneNumber){
		// Actions actions = new Actions(RFWebsiteDriver.driver);
		selectEnrollmentKitPage(kitName, regimenName);  
		chooseEnrollmentOption(enrollmentType);
		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmailAddress(emailaddress);
		// actions.sendKeys(Keys.TAB).perform();
		driver.waitForElementPresent(By.id("enrollUnderLastUpline"));
		clickOnEnrollUnderLastUpline();
		driver.pauseExecutionFor(6000);
	}

	public boolean verifyBizUrlAfterEnrollment(String pws){
		driver.waitForPageLoad();
		return driver.getCurrentUrl().trim().contains(pws.trim());
	}

	public boolean verifyNotYourSponsorLinkIsPresent(){
		return driver.isElementPresent(By.id("not-your-sponsor"));
	}

	public boolean validateConsultantNameOnTopRightCorner(){
		driver.waitForElementPresent(By.xpath("//div[contains(@class,'consultant-info')]"));
		return driver.isElementPresent(By.xpath("//div[contains(@class,'consultant-info')]"));
	}

	public boolean verifyHeroBannerOnLoginPage(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[1]/div/img"));
		if(driver.isElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[1]/div/img"))){
			return true;
		}else{
			return false;
		}
	}

	public boolean verifyPageTitle(String expectedTitle){
		if(driver.getTitle().contains(expectedTitle)){
			logger.info("Title of page is "+driver.getTitle());
			return true;
		}else{
			return false;
		}
	}

	public boolean verifyPCSubscribeBenefitsContentBlockOnCom(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[2]/div/img"));
		if(driver.isElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[2]/div/img"))){
			return true;
		}else{
			return false;
		}
	}

	public boolean verifySolutionToolContentBlockOnCom(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[3]/div/img"));
		if(driver.isElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[3]/div/img"))){
			return true;
		}else{
			return false;
		}
	}

	public boolean verifyProductGuaranteeContentBlockOnCom(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[4]/div/img"));
		if(driver.isElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[4]/div/img"))){
			return true;
		}else{
			return false;
		}
	}

	public void clickShopNowLinkOnHeroBanner(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[1]/div/div/div/a"));
		driver.click(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[1]/div/div/div/a"),"");
		driver.waitForPageLoad();
		logger.info("Shop now link is clicked in Hero Banner block");
	}

	public boolean verifyShopNowPageAfterClickingShopNowLinkOfHeroBanner(){
		if(driver.getCurrentUrl().toLowerCase().contains("Skincare-for-Expression-Lines".toLowerCase())&&
				driver.getTitle().contains("ACUTE CARE� Skincare for Expression Lines- Rodan + Fields")){
			return true;
		}else{
			return false;
		}
	}

	public void clickLearnMoreLinkOnPCSubscribeBenefitsContentBlockOnCom(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[2]/div/div/div/a"));
		driver.click(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[2]/div/div/div/a"),"");
		driver.waitForPageLoad();
		logger.info("Learn More link is clicked in PC Subscribed Benefits content block");
	}

	public void clickShopNowLinkOnProductGuaranteeContentBlockOnCom(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[4]/div/div/div/a"));
		driver.click(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[4]/div/div/div/a"),"");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(3000);
		logger.info("Shop Now link is clicked in Product guarantee content block");
	}

	public boolean verifyProductGuaranteePageAfterClickingShopNowLinkOnCom(){
		driver.pauseExecutionFor(3000);
		logger.info("title of product guarantee page "+driver.getTitle());
		if(driver.getCurrentUrl().toLowerCase().contains("products".toLowerCase())&&
				driver.getTitle().contains("REDEFINE Products")){
			logger.info("title of product guarantee page "+driver.getTitle());
			return true;
		}else{
			return false;
		}

	}

	public void clickLearnMoreLinkOnVerifySolutionToolContentBlockOnCom(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[3]/div/div/div/a"));
		driver.click(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[3]/div/div/div/a"),"");
		driver.waitForPageLoad();
		logger.info("Learn More link is clicked in Solution tool content block");
	}

	public boolean verifySolutionToolPageAfterClickingLearnMoreLinkOnCom(){
		if(driver.getCurrentUrl().toLowerCase().contains("solutiontool".toLowerCase())&&
				driver.getTitle().contains("Rodan + Fields Solution Tool")){
			logger.info("title of Solution tool page "+driver.getTitle());
			return true;
		}else{
			return false;
		}
	}

	public boolean verifyPCSubscribeBenefitsPageAfterClickingLearnMoreLinkOnCom(){
		if(driver.getCurrentUrl().toLowerCase().contains("pc-perks".toLowerCase())&&
				driver.getTitle().contains("PC Perks | com PWS - CA")){
			logger.info("title of PC Subscribe benefits page "+driver.getTitle());
			return true;
		}else{
			return false;
		}
	}

	public boolean verifyMeetYourCommunityContentBlock(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[2]/div/img"));
		if(driver.isElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[2]/div/img"))){
			return true;
		}else{
			return false;
		}
	}

	public boolean verifyStartYourJourneyContentBlock(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[3]/div/img"));
		if(driver.isElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[3]/div/img"))){
			return true;
		}else{
			return false;
		}
	}

	public boolean verifyAttendAnEventContentBlock(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[4]/div/img"));
		if(driver.isElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[4]/div/img"))){
			return true;
		}else{
			return false;
		}
	}
	public boolean verifyEnrollNowPageAfterClickingEnrollNowLinkOfHeroBanner(){
		if(driver.getCurrentUrl().toLowerCase().contains("why-rf".toLowerCase())&&
				driver.getTitle().contains("Why R+F? | biz PWS - CA")){
			return true;
		}else{
			return false;
		}
	}

	public void clickLearnMoreLinkOnMeetOurCommunityContentBlock(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[2]/div/div/div/a"));
		driver.click(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[2]/div/div/div/a"),"");
		driver.waitForPageLoad();
		logger.info("Learn More link is clicked in meet our community content block");
	}

	public boolean verifyMeetOurCommunityPageAfterClickingLearnMoreLink(){
		if(driver.getCurrentUrl().toLowerCase().contains("meet-our-community".toLowerCase())&&
				driver.getTitle().contains("Meet Our Community- Rodan + Fields")){
			return true;
		}else{
			return false;
		}
	}

	public void clickLearnMoreLinkOnStartYourJourneyContentBlock(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[3]/div/div/div/a"));
		driver.click(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[3]/div/div/div/a"),"");
		driver.waitForPageLoad();
		logger.info("Learn More link is clicked in Start your journey content block");
	}

	public boolean verifyStartYourJourneyPageAfterClickingLearnMoreLink(){
		if(driver.getCurrentUrl().toLowerCase().contains("programs-incentives".toLowerCase())&&
				driver.getTitle().contains("Programs & Incentives")){
			return true;
		}else{
			return false;
		}
	}

	public void clickLearnMoreLinkOnAttendAnEventContentBlock(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[4]/div/div/div/a"));
		driver.click(By.xpath("//div[@id='corp-start-boxes']/div[1]/div[4]/div/div/div/a"),"");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(3000);
		logger.info("Learn more link is clicked in Attend An Event content block");
	}

	public boolean verifyAttendAnEventPageAfterClickingLearnMoreLink(){
		if(driver.getCurrentUrl().toLowerCase().contains("events".toLowerCase())&&
				driver.getTitle().contains("Events")){
			return true;
		}else{
			return false;
		}
	}

	public String getOneMonthExtendAutoshipDateFromCurrentDate(String splittedDateForMonth) throws Exception{
		Date pstInMonthFrom=new Date(splittedDateForMonth); 
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		String[] datePST = splittedDateForMonth.split(" ");
		String dateForExtend = sm.format(pstInMonthFrom);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(Integer.parseInt(datePST[0])<=17){
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(dateForExtend));
			c.add(Calendar.MONTH, 1);
			dateForExtend = sdf.format(c.getTime());
			String extendedDate =  convertDBDateFormatToUIFormatForPulseSubscription(dateForExtend);
			return extendedDate;
		}else{
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(dateForExtend));
			c.add(Calendar.MONTH, 1);
			dateForExtend = sdf.format(c.getTime());
			String[] splitedDateForAfterSeventeen = dateForExtend.split("-");
			String dateForAfterSeventeen = "17"+"-"+splitedDateForAfterSeventeen[1]+"-"+splitedDateForAfterSeventeen[2];
			String extendedDate = convertDBDateFormatToUIFormatForPulseSubscription(dateForAfterSeventeen);
			return extendedDate;
		}
	}

	public String convertDBDateFormatToUIFormatForPulseSubscription(String DBDate){
		String UIMonth=null;
		String[] monthInWords = DBDate.split("-");
		String date = monthInWords[0];
		String day = ""+date.charAt(0);
		if(day.equals("0")){
			date = date.substring(1);
		}
		String month =  monthInWords[1];
		String year = monthInWords[2];
		switch (Integer.parseInt(month)) {  
		case 1:
			UIMonth="January";
			break;
		case 2:
			UIMonth="February";
			break;
		case 3:
			UIMonth="March";
			break;
		case 4:
			UIMonth="April";
			break;
		case 5:
			UIMonth="May";
			break;
		case 6:
			UIMonth="June";
			break;
		case 7:
			UIMonth="July";
			break;
		case 8:
			UIMonth="August";
			break;
		case 9:
			UIMonth="September";
			break;
		case 10:
			UIMonth="October";
			break;
		case 11:
			UIMonth="November";
			break;
		case 12:
			UIMonth="December";
			break;  
		}
		return UIMonth+" "+date+", "+year;
	}

	public boolean verifyConsultantSinceOnMeetYourConsultantPage(){
		return driver.isElementPresent(By.xpath("//span[contains(text(),'Consultant since')]"));
	}

	public void updateEmailOnMeetYourConsultantPage(String email){
		logger.info("Email id updated is "+email);
		driver.type(By.id("contactEmail"), email);
	}

	public String getPhoneNumberFromContactBox(){
		String phoneNumber =  driver.findElement(By.xpath("//span[@class='icon-phone iconMsg']/following::a[1]")).getText();
		String[] number = phoneNumber.split("\\.");
		String finalNumber = number[0]+number[1]+number[2];
		return finalNumber;
	}

	public boolean verifyPhoneNumberIsPresentInContactBox(String number){
		driver.waitForPageLoad();
		String updatedPhoneNumber = getPhoneNumberFromContactBox();
		return number.contains(updatedPhoneNumber.trim());
	}

	public boolean verifyEmailIdIsPresentInContactBoxAfterUpdate(String email){
		driver.waitForPageLoad();
		return driver.findElement(By.xpath("//*[@id='txtContactMe']")).getText().trim().contains(email.trim());
	}

	public boolean verifyEnterYourNameFunctionalityIsPresentOnMeetMyConsultantPage(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.id("name"));
	}

	public boolean verifyEnterYourEmailFunctionalityIsPresentOnMeetMyConsultantPage(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.id("senderEmailId"));
	}

	public boolean verifyEnterYourMessageFunctionalityIsPresentOnMeetMyConsultantPage(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.id("message"));
	}

	public boolean verifySubmitButtonIsPresentOnMeetMyConsultantPage(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//textarea[@id='message']/following::input[1]"));
	}

	public boolean verifyRFCorporateSponsorPresent() {
		driver.waitForElementPresent(By.xpath("//div[@id='sponsorInfo']/span[contains(text(),'Corporate')]"));
		return driver.isElementPresent(By.xpath("//div[@id='sponsorInfo']/span[contains(text(),'Corporate')]"));
	}

	public boolean verifyShipImmediatelyRadioButtinIsSelected(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//input[@id='immediately' and @checked='checked']"));
	}

	public boolean validateTermsAndConditions(){
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(By.xpath("//*[@id='reviewOrderDiv']/div[2]"));
		return driver.isElementPresent(By.xpath("//*[@id='reviewOrderDiv']/div[2]"));
	}

	public void selectNewBillingCardExpirationDateAfterEdit(){
		driver.pauseExecutionFor(1000);
		driver.click(By.id("expiryMonth"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='expiryMonth']/option[8]"));
		driver.click(By.xpath("//select[@id='expiryMonth']/option[8]"),"");
		driver.click(By.id("expiryYear"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='expiryYear']/option[8]"));
		driver.click(By.xpath("//select[@id='expiryYear']/option[8]"),"");
		logger.info("expiration date is selected after edit");
	}

	public boolean verifyNewAddressPresentInMainAccountInfo(String newAddressLine1) {
		try{
			if(driver.findElement(By.xpath("//div[@id='summarySection']/div[2]/div[6]/div[3]")).getText().contains(newAddressLine1)){
				return true;
			}
			return false;
		}catch(Exception e){
			driver.waitForElementPresent(By.xpath("//div[@id='summarySection']/div[4]/div[3]/p"));
			if(driver.findElement(By.xpath("//div[@id='summarySection']/div[4]/div[3]/p")).getText().contains(newAddressLine1)){
				return true;
			}
			return false;
		}
	}


	public boolean validateNewShippingAddressPresentOnReviewPage(String newShippingAddName) {
		System.out.println(newShippingAddName);
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']//span[contains(text(),'"+newShippingAddName+"')]"));
		if(driver.isElementPresent(By.xpath("//div[@id='summarySection']//span[contains(text(),'"+newShippingAddName+"')]"))){
			return true;
		}
		return false;
	}

	public void addQuantityOfProductTillThresholdPopupDisappear(String qty) {
		if(driver.isElementPresent(By.xpath("//div[@id='popup-content']//input"))){
			driver.click(By.xpath("//div[@id='popup-content']//input"),"");
			while(true){
				addQuantityOfProduct(qty);
				int updateQtyValue=Integer.parseInt(qty)+Integer.parseInt(qty);
				qty = Integer.toString(updateQtyValue);
				if(driver.isElementPresent(By.xpath("//div[@id='popup-content']//input"))){
					driver.click(By.xpath("//div[@id='popup-content']//input"),"");
					continue;
				}else{
					break;
				}
			}
		}else{
			logger.info("quantity has been Updated successfully");
		}
	}

	public void enterMainAccountInfoAndClearPreviousField(){
		driver.pauseExecutionFor(5000);
		if(driver.getCountry().equalsIgnoreCase("CA")){
			driver.clear(By.id("address.line1"));
			driver.type(By.id("address.line1"),TestConstants.ADDRESS_LINE_1_CA);
			logger.info("Address Line 1 entered is "+TestConstants.ADDRESS_LINE_1_CA);
			driver.clear(By.id("address.townCity"));
			driver.type(By.id("address.townCity"),TestConstants.CITY_CA);
			logger.info("City entered is "+TestConstants.CITY_CA);
			try{
				driver.click(By.xpath("//form[@id='addressForm']/div[@class='row'][1]//select[@id='state']"),"");
				driver.waitForElementPresent(By.xpath("//form[@id='addressForm']/div[@class='row'][1]//select[@id='state']/option[contains(text(),'"+TestConstants.PROVINCE_CA+"')]"));
				driver.click(By.xpath("//form[@id='addressForm']/div[@class='row'][1]//select[@id='state']/option[contains(text(),'"+TestConstants.PROVINCE_CA+"')]"),"");
			}catch(Exception e){
				Select select=new Select(driver.findElement(By.id("state")));
				select.selectByVisibleText(TestConstants.PROVINCE_CA);
			} 
			logger.info("state selected");
			driver.clear(By.id("address.postcode"));
			driver.type(By.id("address.postcode"),TestConstants.POSTAL_CODE_CA);
			logger.info("postal code entered is "+TestConstants.POSTAL_CODE_CA);
			driver.clear(By.id("address.phonenumber"));
			driver.type(By.id("address.phonenumber"),TestConstants.PHONE_NUMBER);
			logger.info("phone number entered is "+TestConstants.PHONE_NUMBER);
		}
		else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.clear(By.id("address.line1"));
			driver.type(By.id("address.line1"),TestConstants.ADDRESS_LINE_1_US);
			logger.info("Address line 1 entered is "+TestConstants.ADDRESS_LINE_1_US);
			driver.clear(By.id("address.townCity"));
			driver.type(By.id("address.townCity"),TestConstants.CITY_US);
			driver.click(By.id("state"),"");
			driver.waitForElementPresent(By.xpath("//select[@id='state']/option[2]"));
			driver.click(By.xpath("//select[@id='state']/option[2]"),"");
			logger.info("state selected");
			driver.clear(By.id("address.postcode"));
			driver.type(By.id("address.postcode"),TestConstants.POSTAL_CODE_US);
			logger.info("postal code entered is "+TestConstants.POSTAL_CODE_US);
			driver.clear(By.id("address.phonenumber"));
			driver.type(By.id("address.phonenumber"),TestConstants.PHONE_NUMBER_US);
			logger.info("phone number entered is "+TestConstants.PHONE_NUMBER_US);
		}

	}

	public boolean validateBillingAddressOnMainAccountInfo(String addressLine12) {
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']/div[5]/div[3]/p"));
		if(driver.findElement(By.xpath("//div[@id='summarySection']/div[5]/div[3]/p")).getText().contains(addressLine12)){
			return true;
		}
		return false;
	}

	public boolean verifyPhoneNumberIsPresentInAccountInfo() {
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']/div[4]/div[3]/p/br[3]"));
		if(driver.isElementPresent(By.xpath("//div[@id='summarySection']/div[4]/div[3]/p/br[3]"))){
			return true;
		}
		return false;
	}

	public void clickOnLiveInNorthDakotaLink() {
		driver.quickWaitForElementPresent(By.xpath("//a[@class='north-dakota-link']"));
		driver.click(By.xpath("//a[@class='north-dakota-link']"),"");
		logger.info("I live in north dakota link is clicked");
	}

	public void enterUserInformationOnAccountInfo(String firstName, String password,String addressLine1,String city,String postalCode,String phoneNumber,String province) {
		enterFirstName(firstName);
		enterLastName(TestConstants.LAST_NAME);
		enterPassword(password);
		enterConfirmPassword(password);
		enterAddressLine1(addressLine1);
		enterCity(city);
		selectProvince(province);
		enterPostalCode(postalCode);
		enterPhoneNumber(phoneNumber);
		enterEmailAddress(firstName+TestConstants.EMAIL_ADDRESS_SUFFIX);
	}

	public boolean verifyingMessageForNextDakotaPresent() {
		driver.waitForElementPresent(By.xpath("//div[@id='globalMessages']//p"));
		return driver.isElementPresent(By.xpath("//div[@id='globalMessages']//p"));
	}

	public boolean verifySignUpLinkIsPresent(){
		return driver.isElementPresent(By.xpath("//span[@class='icons icon-person']"));
	}

	public boolean validateProductPricingDetailOnSumaaryPage(){
		return !StringUtils.isEmpty(driver.getText(By.xpath(".//*[@id='module-total']/div[2]/span")));
	}

	public boolean validateRetailProductProcesAttachedToProduct(){
		driver.waitForElementPresent(By.xpath("//p[@class='prices']"));
		return driver.isElementVisible(By.xpath("//p[@class='prices']"));
	}

	public void hitBrowserBackBtn(){
		driver.pauseExecutionFor(1000);
		driver.navigate().back();
		driver.waitForPageLoad();
	}

	public boolean verifyPresenceOfNorthDakotaLink() {
		return driver.isElementPresent(By.xpath("//a[@class='north-dakota-link']"));
	}

	public boolean verifyPresenceOfPopUpForExistingPC() {
		driver.waitForElementPresent(By.xpath("//h2[contains(text(),'Existing Preferred Customer')]"));
		// return driver.findElement(By.xpath("//h2[contains(text(),'Existing Preferred Customer')]")).isDisplayed();
		return driver.isElementPresent(By.xpath("//h2[contains(text(),'Existing Preferred Customer')]"));
	}
	public void selectCountryUsToCan() {
		if(driver.getCountry().equalsIgnoreCase("us")){
			driver.waitForElementPresent(By.xpath("//button/img"));
			driver.click(By.xpath("//button/img"),"");
			driver.waitForElementPresent(By.xpath("//a[contains(text(),'CAN')]"));
			driver.click(By.xpath("//a[contains(text(),'CAN')]"),"");
			logger.info("country changed us to canada");
		}
	}

	public boolean validateContinueWithoutSponsorLinkPresentOnUI(){
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(By.xpath("//a[@id='continue-no-sponsor']"));
		return driver.findElement(By.xpath("//a[@id='continue-no-sponsor']")).isDisplayed();
	}

	public double getProductPricingOnSummaryPage(){
		double productpricing=0.00;
		driver.quickWaitForElementPresent(By.xpath("//div[@id='module-subtotal']/div[contains(text(),'Subtotal')]/following-sibling::div/span"));
		String productPricing=driver.findElement(By.xpath("//div[@id='module-subtotal']/div[contains(text(),'Subtotal')]/following-sibling::div/span")).getText();
		if(driver.getCountry().equalsIgnoreCase("au")){
			productPricing.replace("AUD $", "");
			productpricing=Double.parseDouble(productPricing.replace("AUD $", ""));
		}else{
			productPricing.replace("CAD$", "");
			productpricing=Double.parseDouble(productPricing.replace("CAD$", ""));
		}
		return productpricing;
	}

	public void checkYesIWantToJoinPCPerksCBOnSummaryPage(){
		driver.waitForElementPresent(By.xpath("//input[@id='pc-customer2' and @disabled='']/.."));
		if(driver.isElementPresent(By.xpath("//input[@id='pc-customer2' and @disabled='']/.."))){
			driver.click(By.xpath("//input[@id='pc-customer2' and @disabled='']/.."),"");
			driver.waitForPageLoad();
			driver.pauseExecutionFor(1500);
		}
	}

	public double getProductPricingOnCartPage(){
		double productpricing=0.00;
		driver.quickWaitForElementPresent(By.xpath("//p[@id='cart-price-reatil']/span"));
		String productPricing=driver.findElement(By.xpath("//p[@id='cart-price-reatil']/span")).getText();
		if(driver.getCountry().equalsIgnoreCase("au")){
			productPricing.replace("AUD $", "");
			productpricing=Double.parseDouble(productPricing.replace("AUD $", ""));
		}else{
			productPricing.replace("CAD$", "");
			productpricing=Double.parseDouble(productPricing.replace("CAD$", ""));
		}
		return productpricing;
	}

	public boolean verifyNumberOfProductsInAutoshipCart(String numberOfProductsInCart){
		try{
			driver.waitForElementPresent(By.xpath("//div[@id='shopping-wrapper']/div[3]/div[1]/h1/span"));
			return driver.findElement(By.xpath("//div[@id='shopping-wrapper']/div[3]/div[1]/h1/span")).getText().contains(numberOfProductsInCart);
		}catch(Exception e){
			try{
				driver.waitForElementPresent(By.xpath("//div[@id='shopping-wrapper']/div[2]/div[1]/h1/span"));
				return driver.findElement(By.xpath("//div[@id='shopping-wrapper']/div[2]/div[1]/h1/span")).getText().contains(numberOfProductsInCart);
			}
			catch(NoSuchElementException e2){
				driver.waitForElementPresent(By.xpath("//div[@id='shopping-wrapper']/div[1]/div[1]/h1/span"));
				return driver.findElement(By.xpath("//div[@id='shopping-wrapper']/div[1]/div[1]/h1/span")).getText().contains(numberOfProductsInCart);

			}
		}

	}

	public String getSubTotalOfAddedProduct() {
		driver.waitForElementPresent(By.xpath("//div[@id='subtotal-shopping']/div[3]/span"));
		return driver.findElement(By.xpath("//div[@id='subtotal-shopping']/div[3]/span")).getText();

	}

	public boolean validateSubTotalAfterQuantityIncreased(String subtotalOfAddedProduct,String quantity) {
		int quantityAdded = Integer.parseInt(quantity);
		String subtotalOfTwoProduct = driver.findElement(By.xpath("//div[@id='subtotal-shopping']/div[3]/span")).getText();
		System.out.println(subtotalOfTwoProduct+"subtotal");
		String []subTtlOfTwoProduct = subtotalOfTwoProduct.split("\\$");
		System.out.println("subTtlAfterAddSpliteed"+subTtlOfTwoProduct[1]);
		double finalSub1 = Double.parseDouble(subTtlOfTwoProduct[1]);
		String []subTtlBeforeAdd = subtotalOfAddedProduct.split("\\$");
		double subBeforeAdd = Double.parseDouble(subTtlBeforeAdd[1]);
		double finalSub = subBeforeAdd*quantityAdded;

		if(finalSub==finalSub1){
			return true;
		}
		return false;
	}

	public String convertCountryInPWS(String pws){
		String ca  = "ca";
		String us ="us";
		if(pws.contains(us)){
			pws = pws.replaceAll(us,ca);
		}else if(pws.contains(ca)){
			pws = pws.replaceAll(ca,us);
		}
		return pws;
	}

	public String selectDifferentCountry() {
		String country=null;
		if(driver.getCurrentUrl().contains("us")){
			driver.waitForElementPresent(By.xpath("//button/img"));
			driver.click(By.xpath("//button/img"),"");
			driver.waitForElementPresent(By.xpath("//a[contains(text(),'CAN')]"));
			driver.click(By.xpath("//a[contains(text(),'CAN')]"),"");
			logger.info("country changed us to canada");
			country="ca";
			return country;
		}
		else if(driver.getCurrentUrl().contains("ca")){
			driver.waitForElementPresent(By.xpath("//button/img"));
			driver.click(By.xpath("//button/img"),"");
			driver.waitForElementPresent(By.xpath("//a[contains(text(),'USA')]"));
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//a[contains(text(),'USA')]"),"country changed Canada to US");
			country="us";
			return country;
		}
		return country;
	}

	public boolean verifyAlreadyExistingPrefixIsNotAllowed(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='pulsesubscription']//label[contains(text(),'Please fix this field')]"));
		if(driver.isElementVisible(By.xpath("//div[@id='pulsesubscription']//label[contains(text(),'Please fix this field')]"))){
			return true;
		}else
			return false;
	}

	public String getExistingWebsitePrefixName(){
		driver.waitForElementPresent(By.id("webSitePrefix"));
		return driver.findElement(By.id("webSitePrefix")).getAttribute("value");
	}

	public void clickSignUpnowOnbizSite(){
		driver.waitForElementPresent(LOGIN_LINK_LOC);
		driver.click(LOGIN_LINK_LOC,"");
		driver.waitForElementPresent(SIGN_UP_LINK_LOC);
		driver.click(SIGN_UP_LINK_LOC,"");
	}

	public String getExpirationMonth() {
		driver.waitForElementPresent(By.xpath("//select[@id='expiryMonth']/option[@selected='selected']"));
		return driver.findElement(By.xpath("//select[@id='expiryMonth']/option[@selected='selected']")).getText();

	}

	public String getExpirationYear() {
		driver.waitForElementPresent(By.xpath("//select[@id='expiryYear']/option[@selected='selected']"));
		return driver.findElement(By.xpath("//select[@id='expiryYear']/option[@selected='selected']")).getText();
	}

	public boolean validateBillingExpirationDate(String expirationYear) {
		String text = driver.findElement(By.xpath("//div[@id='summarySection']/div[5]/div[2]/p")).getText();
		String[] splittedYear = text.split("\\/");
		if(splittedYear[1].contains(expirationYear)){
			return true;
		}
		return false;
	}

	public boolean validateBillingInfoUpdated(String expirationMonth,String expirationYear) {
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']/div[5]/div[2]/p"));
		return (driver.findElement(By.xpath("//div[@id='summarySection']/div[5]/div[2]/p"))).getText().contains("4747");
	}

	public String replaceAllHTTPWithHTTPS(String pws){
		if(pws.contains("http"))
			pws = pws.replaceAll("http","https");
		return pws;
	}

	public void clickCloseOnExistingUserPopUp(){
		driver.waitForElementPresent(By.xpath("//span[@class='icon-close']"));
		driver.click(By.xpath("//span[@class='icon-close']"),"");
		driver.pauseExecutionFor(1000);
	}

	public void clearUserEmailField(){
		driver.clear(By.id("email-account"));
	}

	public boolean isKitProductPresent(){
		boolean isKitProductPresent = false;
		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='product-picture']/following::h3"));
		for(WebElement e:allProducts){
			if(e.getText().toLowerCase().contains("kit")){
				isKitProductPresent=true;
				break;
			}
		}

		return isKitProductPresent;

	}

	public boolean verifyProductPriceAsPerCountry(String country){
		if(country.equalsIgnoreCase("us")){
			return getProductPrice().toLowerCase().contains("$".toLowerCase()) && !getProductPrice().toLowerCase().contains("CAD$".toLowerCase()) && !getProductPrice().toLowerCase().contains("AUD $".toLowerCase());
		}else if(country.equalsIgnoreCase("ca")){
			return getProductPrice().toLowerCase().contains("CAD$".toLowerCase());
		}else{
			return getProductPrice().toLowerCase().contains("AUD $".toLowerCase());
		}
	}


	public boolean validateExistingConsultantPopup(){
		driver.waitForElementToBeVisible(By.xpath("//div[@class='fancybox-inner']"),2);
		return driver.isElementPresent(By.xpath("//div[@class='fancybox-inner']"));
	}

	public void clickCreateActBtnOnChkOutPage(){
		driver.waitForElementPresent(By.xpath("//input[@id='next-button']"));
		driver.click(By.xpath("//input[@id='next-button']"),"");  
		logger.info("Create New Account button clicked");  
	}

	public boolean validateErrorMessagesForNewCustomerFields(){
		driver.waitForElementPresent(By.xpath("//form[@id='registerForm']//label"));
		return driver.isElementPresent(By.xpath("//form[@id='registerForm']//label"));
	}

	public boolean validateErrorMessagesForMainActInfoFields(){
		driver.waitForElementPresent(By.xpath("//div[@id='create_update_address_form_container_div']"));
		return driver.isElementPresent(By.xpath("//div[@id='create_update_address_form_container_div']//label"));
	}

	public boolean validateErrorMessagesForAddNewBillingInfoFields(){
		driver.waitForElementPresent(By.xpath("//div[@id='add-new-billing']//label"));
		return driver.isElementPresent(By.xpath("//div[@id='add-new-billing']//label"));
	}

	public void enterSponserNameAndClickSearchAndContinue(String sponsor) {
		searchCID(sponsor);
		mouseHoverSponsorDataAndClickContinuePCAndRC(1);
		driver.waitForLoadingImageToDisappear();			

	}

	public void enterSponserNameAndClickSearchAndContinue() {
		for(int i=1;i<=2;i++){
			if(i>1){
				clickOnNotYourSponsorLink();
			}
			enterSponsorNameAndClickOnSearchForPCAndRC();
			mouseHoverSponsorDataAndClickContinuePCAndRC(i);
			driver.waitForLoadingImageToDisappear();
			if(driver.getCurrentUrl().contains("corp")){
				break;
			}else
				continue;
		}
	}

	public void mouseHoverSponsorDataAndClickContinuePCAndRC(int i) {
		JavascriptExecutor js = (JavascriptExecutor)(RFWebsiteDriver.driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='the-search-results']/div["+i+"]/div[1]//input[@value='Select']")));
		logger.info("First result of sponsor has been clicked");
		//		driver.waitForLoadingImageToDisappear();
		//		driver.waitForPageLoad();
	}

	public boolean validateTermsAndConditionsForRC(){
		if(driver.getCountry().trim().equalsIgnoreCase("us")){
			driver.waitForElementPresent(By.xpath("//p[contains(text(),'you confirm that you have read and accepted')]"));
			return driver.isElementPresent(By.xpath("//p[contains(text(),'you confirm that you have read and accepted')]"));
		}else{
			driver.waitForElementPresent(By.xpath("//p[contains(text(),'I confirm that I have read and accept')]"));
			return driver.isElementPresent(By.xpath("//p[contains(text(),'I confirm that I have read and accept')]"));
		}
	}

	public String selectShippingMethodUPSStandardOvernightUnderShippingSectionAndGetName(){
		driver.waitForElementPresent(By.xpath("//div[@id='start-shipping-method1']/div[3]//input/.. | //div[@id='start-shipping-method1']//span[2]"));
		driver.click(By.xpath("//div[@id='start-shipping-method1']/div[3]//input/.. | //div[@id='start-shipping-method1']//span[2]"),"");
		driver.pauseExecutionFor(1500);
		logger.info("UPS Standard Overnight shipping method is selected"); 
		return driver.findElement(By.xpath("//div[@id='start-shipping-method1']//span[2]")).getText();
	}

	public String selectShippingMethodUPS2DayUnderShippingSectionAndGetName(){
		driver.waitForElementPresent(By.xpath("//div[@id='start-shipping-method1']/div[2]//input/.."));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='start-shipping-method1']/div[2]//input/.."),"UPS Standard Overnight shipping method");
		return driver.findElement(By.xpath("//div[@id='start-shipping-method1']/div[2]//span[2]")).getText();
	}

	public boolean verifyEnrolledUserStatus(String statusID){
		int a=Integer.parseInt(statusID);
		if(a==1){
			logger.info("status of enrolled user is active in database");
			return true;
		}else{
			logger.info("status of enrolled user is not active in database");
			return true;
		}
	}

	public boolean verifyILiveInNorthDakotaLinkIsNotPresentForCA() {
		return driver.isElementPresent(By.xpath("//a[contains(text(),'continue without purchasing a Business Portfolio or enrollment kit.')]"));
	}

	public void clickPolicyAndProcedureLink(){
		driver.waitForElementPresent(POLICY_AND_PROCEDURE_LINK);
		driver.click(POLICY_AND_PROCEDURE_LINK,"");
		//driver.pauseExecutionFor(2000);
	}

	public boolean verifyFollowMeSectionIsPresent(){
		return driver.isElementPresent(By.xpath("//div[@class='contactBox']//div/a"));
	}

	public boolean verifyPrivacyPolicyLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Privacy Policy')]"));
		return driver.isElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Privacy Policy')]"));
	}

	public boolean verifySatisfactionGuaranteeLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Satisfaction Guarantee')]"));
		return driver.isElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Satisfaction Guarantee')]"));
	}

	public boolean verifyDisclaimerLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Disclaimer')]"));
		return driver.isElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Disclaimer')]"));
	}

	public boolean verifyContactUsLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Contact Us')]"));
		return driver.isElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Contact Us')]"));
	}

	public String getHandlingChargesOnReviewOrderPage(){
		driver.waitForElementPresent(By.xpath("//span[@id='handlingCost']"));
		String value= driver.findElement(By.xpath("//span[@id='handlingCost']")).getText();
		return value;
	}

	public String getShippingChargesOnReviewOrderPage(){
		driver.waitForElementPresent(By.xpath("//span[@id='deliveryCost']"));
		String value= driver.findElement(By.xpath("//span[@id='deliveryCost']")).getText();
		return value;
	}


	public boolean verifyAddToPCPerksButtonInProductInfo() {
		if(driver.getCountry().equalsIgnoreCase("ca") || driver.getCountry().equalsIgnoreCase("au"))
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::input[contains(@value,'ADD to PC Perks')][1]"));
		else
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div[1]//input[@value='ADD to PC Perks']"));
	}

	public String getSocialInsuranceNumberTxtFldPlaceHolderValue(){
		driver.waitForElementPresent(By.xpath("//input[@id='S-S-N']"));
		return driver.findElement(By.xpath("//input[@id='S-S-N']")).getAttribute("placeholder").trim();
	}

	public void addFollowMeSection(String link){
		if(driver.isElementPresent(By.xpath("//div[@class='contactBox']//div/a"))==false){
			clickOnPersonalizeMyProfileLink();
			driver.waitForPageLoad();
			driver.type(By.id("consultant-facebook"), link);
			clickSaveBtnOnEditConsultantInfoPage();
		}
	}

	public void clickFindAConsultantLinkOnHomePage(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='header-middle-top']/a"));
		driver.click(By.xpath("//div[@id='header-middle-top']/a"),"");
	}

	public boolean validateConsultantSearchFieldPresent(){
		driver.quickWaitForElementPresent(By.xpath("//input[@id='sponserparam']"));
		return driver.isElementPresent(By.xpath("//input[@id='sponserparam']"));
	}

	public void clickConnectUnderConnectWithAConsultantSection(){
		if(driver.getCountry().equalsIgnoreCase("au")){
			driver.waitForElementPresent(By.xpath("//div[@id='corp_content']/descendant::div[contains(text(),'Solution Tool')][1]/following::a[1]"));
			driver.click(By.xpath("//div[@id='corp_content']/descendant::div[contains(text(),'Solution Tool')][1]/following::a[1]"),"");
			logger.info("Clicked on get started button of solution tool");
			driver.waitForPageLoad();
			driver.click(By.xpath("//a[contains(text(),'Connect with a Consultant')]"),"");
			logger.info("Clicked on connect with a consultant");
		}else{
			driver.quickWaitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/div[1]//h3[contains(text(),'CONNECT WITH A CONSULTANT')]//following-sibling::a"));
			WebElement element = driver.findElement(By.xpath("//div[@id='corp-start-boxes']/div[1]//h3[contains(text(),'CONNECT WITH A CONSULTANT')]//following-sibling::a"));
			((JavascriptExecutor) RFWebsiteDriver.driver).executeScript("arguments[0].click();", element);
		}
	}

	public boolean verifySponsorDetailsPresent() {
		driver.waitForElementPresent(By.xpath("//div[@class='sponsorDataDiv']"));
		return driver.isElementPresent(By.xpath("//div[@class='sponsorDataDiv']"));
	}

	public boolean verifySponsorFullNamePresent() {
		return driver.isElementPresent(By.xpath("//div[@class='sponsorDataDiv']//li[1]"));
	}

	public boolean verifySponsorFullNamePresent(String fullName) {
		return driver.findElement(By.xpath("//div[@class='sponsorDataDiv']//li[1]")).getText().toLowerCase().contains(fullName.toLowerCase());		
	}

	public boolean verifySponsorIdPresent(String sponsorId) {
		return driver.findElement(By.xpath("//div[@class='sponsorDataDiv']//li[2]")).getText().toLowerCase().contains(sponsorId.toLowerCase());		
	}

	public boolean verifySponsorZipCodePresent(){
		return driver.isElementPresent(By.xpath("//div[@class='sponsorDataDiv']//li[2]"));
	}

	public boolean verifySponsorCityPresent(){
		return driver.isElementPresent(By.xpath("//div[@class='sponsorDataDiv']//li[3]"));

	}

	public boolean verifySponsorPWSComSitePresent(){
		return driver.isElementPresent(By.xpath("//div[@class='sponsorDataDiv']//li[4]"));
	}

	public boolean verifyNotFoundMsgPresent() {
		driver.waitForElementPresent(By.xpath("//div[@id='sponsorPage']//span[contains(text(),'No result found')]"));
		return driver.isElementPresent(By.xpath("//div[@id='sponsorPage']//span[contains(text(),'No result found')]"));
	}

	public boolean verifyUserRedirectingToComSite() {
		String url = driver.getCurrentUrl();
		if(url.contains("myrfo"+driver.getEnvironment().toLowerCase()+".com"))
			return true;
		else
			return false;
	}

	public String getDefaultSponsorLastNameWhileEnrollingPCUser(){
		driver.waitForElementPresent(By.xpath("//div[@id='sponsorInfo']/span"));
		String sponserName =driver.findElement(By.xpath("//div[@id='sponsorInfo']/span")).getText();
		logger.info("Default Sponser Name from UI is "+sponserName);
		String sponsorTitle=sponserName.split("\\s")[1];
		return sponsorTitle;
	}

	public void postContactMeRequestOnMeetYourConsultantPage(String consMailID){
		driver.waitForElementPresent(By.xpath("//input[@id='name']"));
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("NewConsultant User");
		driver.findElement(By.xpath("//input[@id='senderEmailId']")).sendKeys(consMailID);
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Automation Test Comments");
		//click send
		driver.findElement(By.xpath("//input[@value='SEND']")).click();
		driver.waitForPageLoad();
	}

	public void addSocialNetworkingURLUnderFollowMeSection(){
		driver.waitForElementPresent(By.xpath("//input[@id='consultant-facebook']"));
		driver.findElement(By.xpath("//input[@id='consultant-facebook']")).sendKeys("https://www.facebook.com/");
		driver.findElement(By.xpath("//input[@id='consultant-twitter']")).sendKeys("https://twitter.com/?lang=en");
		driver.findElement(By.xpath("//input[@id='consultant-pinterest']")).sendKeys("https://www.pinterest.com/");
		driver.findElement(By.xpath("//input[@id='consultant-instagram']")).sendKeys("https://www.instagram.com/?hl=en");
	}

	public void clearSocialNetworkingURLUnderFollowMeSection(){
		driver.waitForElementPresent(By.xpath("//input[@id='consultant-facebook']"));
		driver.findElement(By.xpath("//input[@id='consultant-facebook']")).clear();
		driver.findElement(By.xpath("//input[@id='consultant-twitter']")).clear();
		driver.findElement(By.xpath("//input[@id='consultant-pinterest']")).clear();
		driver.findElement(By.xpath("//input[@id='consultant-instagram']")).clear();
	}

	public boolean isUserNamePresentOnDropDown(){
		driver.waitForElementPresent(By.id("account-info-button"));
		return driver.isElementPresent(By.id("account-info-button"));
	}

	public String getConsultantStoreFrontInfo(String label){
		driver.waitForElementPresent(By.id(label));
		return driver.findElement(By.id(label)).getAttribute("value").trim().toLowerCase();
	}

	public String convertUSBizPWSToCA(String PWS){
		return PWS.replaceAll("/us","/ca");
	}

	public String getOrderNumberAfterPlacingOrder(){
		driver.waitForElementPresent(By.xpath("//div[@id='confirm-left-shopping']//h4"));
		String orderNumber =driver.findElement(By.xpath("//div[@id='confirm-left-shopping']//h4")).getText();
		String  orderNum=orderNumber.split("\\#")[1];
		logger.info("Order Number from UI after Placing order is "+orderNum);
		return orderNum;
	}

	public void clickAddNewAddressLink(){
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Add new address')]"));
		driver.click(By.xpath("//a[contains(text(),'Add new address')]"),"");
	}

	public void enterNewBillingAddressNameDuringEnrollment(String name){
		driver.type(By.id("billingAddressattention"),name);
		logger.info("New Billing Address name is "+name);
	}

	public void enterNewBillingAddressLine1DuringEnrollment(String addressLine1){
		driver.type(By.id("billingAddressline1"),addressLine1);
		logger.info("New Billing Address is "+addressLine1);
	}

	public void enterNewBillingAddressCityDuringEnrollment(String city){
		driver.type(By.id("billingAddresstownCity"),city);
		logger.info("New Billing City is "+city);
	}

	public void selectNewBillingAddressStateDuringEnrollment(){
		driver.clickByJS(RFWebsiteDriver.driver, By.id("addressState"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='addressState']//option[2]"));
		driver.click(By.xpath("//select[@id='addressState']//option[2]"),"State/Province");
	}

	public void enterNewBillingAddressZipCodeDuringEnrollment(String code){
		driver.type(By.id("billingAddresspostcode"),code);
		logger.info("New Billing code is "+code);
	}

	public boolean validateNewBillingAddressPresentOnReviewPage(String newBillingAddName) {
		driver.waitForElementPresent(By.xpath("//div[@id='summarySection']//span[contains(text(),'"+newBillingAddName+"')]"));
		if(driver.isElementPresent(By.xpath("//div[@id='summarySection']//span[contains(text(),'"+newBillingAddName+"')]"))){
			return true;
		}
		return false;
	}

	public String validate2ProductsAreDisplayedInReviewOrderSection(){
		driver.waitForElementPresent(By.xpath("//div[@id='shopping-wrapper']/div[2]/div/div[1]/div//div[contains(text(),'Items in Order')]/ancestor::div[1]/following-sibling::div"));
		return String.valueOf(driver.findElements(By.xpath("//div[@id='shopping-wrapper']/div[2]/div/div[1]/div//div[contains(text(),'Items in Order')]/ancestor::div[1]/following-sibling::div")).size());
	}

	public boolean validateReleventTextPresentOnFindASponsorPopup(){
		driver.waitForElementPresent(By.xpath("//div[@id='find-sponsor-right']/p[contains(text(),'To make your experience as personal as possible, we highly recommend connecting with a Rodan + Fields Consultant')]"));
		return driver.isElementPresent(By.xpath("//div[@id='find-sponsor-right']/p[contains(text(),'To make your experience as personal as possible, we highly recommend connecting with a Rodan + Fields Consultant')]"));
	}

	public void mouseHoverSponsorDataAndClickContinuePCAndRC() {
		JavascriptExecutor js = (JavascriptExecutor)(RFWebsiteDriver.driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='the-search-results']/div[1]/div[1]//input[@value='Select']")));
		logger.info("First result of sponsor has been clicked");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean verifySponserSearchResult(String accountNumber){
		return driver.isElementPresent(By.xpath("//span[text()='No result found for "+accountNumber+"']"));
	}

	public boolean validateSearchFunctionalityWorking(){
		driver.waitForElementPresent(By.xpath("//div[@id='search-results']"));
		return driver.isElementPresent(By.xpath("//div[@id='search-results']"));
	}

	public void selectShipNextMonthOnCRPSummaryPage(){
		driver.waitForElementPresent(By.xpath("//input[@id='next-month']"));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='next-month']"),"");
		driver.pauseExecutionFor(1500);
	}

	public String getPriceInformationOfProductsOnProductsPage(){
		driver.quickWaitForElementPresent(By.xpath("//p[@class='prices']"));
		return driver.findElement(By.xpath("//p[@class='prices']")).getText();
	}

	public boolean isPwsOwnerNamePresent(){
		driver.waitForElementPresent(By.xpath("//div[@class='content-header-filled txtConsultantName']"));
		return driver.isElementPresent(By.xpath("//div[@class='content-header-filled txtConsultantName']"));
	}

	public boolean verifyNameFieldPresent() {
		return driver.isElementPresent(By.id("name"));
	}
	public boolean verifyEmailIdFieldPresent(){
		return driver.isElementPresent(By.id("senderEmailId"));
	}
	public boolean verifyMessageFieldPresent(){
		return driver.isElementPresent(By.id("message"));
	}
	public boolean verifySendButtonPresent(){
		return driver.isElementPresent(By.xpath("//form[@id='contactConsultantForm']//input[@value='SEND']"));
	}

	public boolean verifyFaceboobTwitterPinInterestlinkPresent() {

		return driver.isElementPresent(By.xpath("//form[@id='consultantInfoForm']//p[@class='follow-consultant']/input"));
	}

	public boolean verifyHomeTownFieldPresent() {
		return driver.isElementPresent(By.id("homeTown"));
	}

	public boolean verifyFooterOptionOnThePOage() {
		return driver.isElementPresent(By.xpath("//div[@class='footer-sections']"));
	}

	public boolean verifyAddToBagButtonPresent() {
		return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::button[contains(text(),'ADD TO BAG')][1]"));
	}

	public boolean isPersonalizeMyProfileLinkNotPresent() {
		return driver.isElementPresent(By.xpath("//a[contains(text(),'Personalize my Profile')]"));
	}

	public void clickOnCancelEnrollmentOnExistingConsultantPopUp(){
		driver.waitForElementPresent(By.xpath("//div[@class='fancybox-inner']//input[3]"));
		driver.click(By.xpath("//div[@class='fancybox-inner']//input[3]"),"");
		driver.waitForPageLoad();
	}

	public String convertCABizPWSToUS(String PWS){
		return PWS.replaceAll("/ca","/us");
	}

	public String getpreferredCustomerStoreFrontInfo(String label){
		driver.waitForElementPresent(By.id(label));
		return driver.findElement(By.id(label)).getAttribute("value").trim().toLowerCase();
	}

	public String convertPhoneNumber(String number){
		String phoneNumberFromUI = null;
		if(number.contains(".")){
			String[] phoneNumber = number.split("\\.");
			phoneNumberFromUI = phoneNumber[0]+phoneNumber[1]+phoneNumber[2];
		}else if(number.contains(".")){
			String[] phoneNumber = number.split("\\ ");
			phoneNumberFromUI = phoneNumber[0]+phoneNumber[1]+phoneNumber[2];
		}else{
			phoneNumberFromUI = number;
		}
		logger.info("Converted phone number is: "+number);
		return phoneNumberFromUI;
	}

	public boolean isSolutionToolContentBlockPresent() {
		driver.waitForElementPresent(By.xpath("//div[@id='corp-start-boxes']/descendant::*[contains(text(),'SOLUTION TOOL') or contains(text(),'Solution Tool')][1]"));///div[@id='corp-start-boxes']/div[1]/div[3]//h3[text()='SOLUTION TOOL']"));
		return driver.isElementPresent(By.xpath("//div[@id='corp-start-boxes']/descendant::*[contains(text(),'SOLUTION TOOL') or contains(text(),'Solution Tool')][1]"));
	}

	public boolean isAccessSolutionToolPresent() {
		boolean status = false;
		driver.get(driver.getCurrentUrl()+"/dynamic/url/solutionTool");
		//		driver.waitForElementPresent(By.xpath("//div[@id='corp_content']/div/div[1]/div[3]/descendant::a"));
		//		driver.click(By.xpath("//div[@id='corp_content']/div/div[1]/div[3]/descendant::a"));
		driver.waitForPageLoad();
		if(driver.getCurrentUrl().contains("solutiontool") && driver.isElementPresent(By.id("mirror"))){
			status = true;
		}
		return status;
	}

	public boolean isHeaderPresent(){
		driver.waitForElementPresent(By.id("header"));
		return driver.isElementPresent(By.id("header"));
	}

	public boolean isHeroBannerPresent(){
		driver.waitForElementPresent(By.xpath("//div[@class='pagebanner']/img[1]"));
		return driver.isElementPresent(By.xpath("//div[@class='pagebanner']/img[1]"));
	}

	public String getConsultantAndPWSSiteOwnerName(){
		driver.waitForElementPresent(By.xpath("//div[contains(@class,'consultant-info')]//a"));
		String name = driver.findElement(By.xpath("//div[contains(@class,'consultant-info')]//a")).getText();
		logger.info("PWS site owner name is: "+name);
		return name;
	}

	public boolean isContactBoxPresent(){
		driver.waitForElementPresent(By.xpath("//div[@class='contactBox']"));
		return driver.isElementPresent(By.xpath("//div[@class='contactBox']"));
	}

	public boolean isFooterPresent(){
		driver.waitForElementPresent(By.xpath("//div[contains(@class,'footer-content')]"));
		return driver.isElementPresent(By.xpath("//div[contains(@class,'footer-content')]"));
	}

	public boolean isEnrollUnderLastUplinePresentForPC(){
		driver.waitForElementPresent(By.xpath("//h2[contains(text(),'you already have a Preferred Customer')]"));
		return driver.isElementPresent(By.xpath("//h2[contains(text(),'you already have a Preferred Customer')]"));
	}

	public boolean isShippingChargesPresentOnReviewOrderPage(){
		driver.waitForElementPresent(By.xpath("//span[@id='deliveryCost'] | //select[@id='selectDeliveryMode']//option[@value='Standard Shipping' and @selected='selected']"));
		String value= driver.findElement(By.xpath("//span[@id='deliveryCost'] | //select[@id='selectDeliveryMode']//option[@value='Standard Shipping' and @selected='selected']	")).getText();
		return !(value==null);
	}

	public String getNameFromContactBox(){
		String fullname =  driver.findElement(By.xpath("//div[@class='content-header-filled txtConsultantName']")).getText();
		return fullname;
	}

	public void clickNextButtonWithUseAsEnteredButton(){
		driver.waitForElementPresent(By.id("enrollment-next-button"));
		driver.click(By.id("enrollment-next-button"),"");
		logger.info("EnrollmentTest Next Button clicked");
		driver.waitForLoadingImageToDisappear();
		try{
			driver.turnOffImplicitWaits();
			driver.quickWaitForElementPresent(By.xpath("//*[@id='QAS_AcceptOriginal']"));
			//driver.pauseExecutionFor(2000);
			driver.click(By.xpath("//*[@id='QAS_AcceptOriginal']"),"");
			logger.info("Use As Entered button clicked");
		}
		catch(Exception e){
			logger.info("Use As Entered pop up was NOT present");
		}
		finally{
			driver.turnOnImplicitWaits();
		}
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOnNotYourCountryLink(){
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//a[@id='notYourCountryId']"), "Not your country link");
		driver.waitForLoadingImageToDisappear();
	}

	public String getCountryNameFromNotYourCountryPopUp(){
		driver.waitForElementPresent(By.xpath("//*[@id='popup-content']/div/div/ul/li/a"));
		String errorMessage=driver.findElement(By.xpath("//*[@id='popup-content']/div/div/ul/li/a")).getText();
		return errorMessage;
	}

	public void clickOnCancelButtonOfNotYourCountryPopUp(){
		driver.quickWaitForElementPresent(By.xpath("//span[@class='icon-close']"));
		driver.click(By.xpath("//span[@class='icon-close']"),"");
	}

	public void clickOnAcceptSecurityCertificate(){
		//		if(driver.getBrowser().equalsIgnoreCase("ie")){
		//			try{
		//				driver.waitForPageLoad();
		//				driver.get("javascript:document.getElementById('overridelink').click();");
		//				driver.waitForPageLoad();
		//			}catch(Exception e){
		//				logger.info("No Certificate error");
		//			}
		//		}
	}

	public void selectProductCategory(String category){
		driver.waitForElementPresent(By.xpath("//input[@value='- Product(s) -']"));
		driver.click(By.xpath("//input[@value='- Product(s) -']"),"");
		driver.pauseExecutionFor(2000);
		driver.click(By.xpath(String.format("//input[@value='- Product(s) -']/following-sibling::ul//div[text()='%s']/following-sibling::div/div[contains(@class,'repaired-checkbox')]",category)),"");
		driver.waitForPageLoad();
	}

	public boolean isProductCategoryPageSelected(String category){
		driver.waitForElementPresent(By.xpath(String.format("//h2[text()='%s']/ancestor::div[contains(@class,'quick')]/descendant::form[@id='productDetailForm'][1]", category)));
		return driver.isElementPresent(By.xpath(String.format("//h2[text()='%s']", category))) && driver.isElementPresent(By.xpath(String.format("//h2[text()='%s']/ancestor::div[contains(@class,'quick')]/descendant::form[@id='productDetailForm'][1]", category)));
	}

	public boolean verifyProductPriceForRandomProduct(String priceRange){
		String valueToCompared = priceRange.split("TO")[1].split("\\$")[1];
		logger.info("Value from filter to compared is "+valueToCompared);
		Double comparableValue = Double.parseDouble(valueToCompared);
		int totalNumberOfProduct = driver.findElements(By.xpath("//div[@id='main-content']/descendant::button[contains(text(),'ADD TO BAG')]")).size();
		logger.info("Total number of add to bag btn "+totalNumberOfProduct);
		String randomProductNumber = Integer.toString(CommonUtils.getRandomNum(1,totalNumberOfProduct));
		String priceOfRandomProduct = driver.findElement(By.xpath(String.format("//div[@id='main-content']/descendant::p[contains(text(),'Retail:')][%s]/span", randomProductNumber))).getText().split("\\$")[1];
		logger.info("Price for random product"+randomProductNumber+" is :"+priceOfRandomProduct);
		Double priceOfRandomProductToBeCompared = Double.parseDouble(priceOfRandomProduct);
		if(comparableValue>=priceOfRandomProductToBeCompared){
			return true;
		}else{
			return false;
		}
	}

	public boolean isHighToLowProductPriceFilterIsAppliedSuccessfully(){
		String priceOfFirstProduct = driver.findElement(By.xpath("//div[@id='main-content']/descendant::p[contains(text(),'Retail:')][1]/span")).getText().split("\\$")[1]; 
		Double firstPrice = Double.parseDouble(priceOfFirstProduct);
		String priceOfSecondProduct = driver.findElement(By.xpath("//div[@id='main-content']/descendant::p[contains(text(),'Retail:')][2]/span")).getText().split("\\$")[1];
		Double secondPrice = Double.parseDouble(priceOfSecondProduct);
		String priceOfThirdProduct = driver.findElement(By.xpath("//div[@id='main-content']/descendant::p[contains(text(),'Retail:')][3]/span")).getText().split("\\$")[1];
		Double thirdPrice = Double.parseDouble(priceOfThirdProduct);
		if(firstPrice>=secondPrice && secondPrice>=thirdPrice){
			return true;
		}else{
			return false;
		}
	}

	public boolean isLowToHighProductPriceFilterIsAppliedSuccessfully(){
		String priceOfFirstProduct = driver.findElement(By.xpath("//div[@id='main-content']/descendant::p[contains(text(),'Retail:')][1]/span")).getText().split("\\$")[1]; 
		Double firstPrice = Double.parseDouble(priceOfFirstProduct);
		String priceOfSecondProduct = driver.findElement(By.xpath("//div[@id='main-content']/descendant::p[contains(text(),'Retail:')][2]/span")).getText().split("\\$")[1];
		Double secondPrice = Double.parseDouble(priceOfSecondProduct);
		String priceOfThirdProduct = driver.findElement(By.xpath("//div[@id='main-content']/descendant::p[contains(text(),'Retail:')][3]/span")).getText().split("\\$")[1];
		Double thirdPrice = Double.parseDouble(priceOfThirdProduct);
		if(firstPrice<=secondPrice && secondPrice<=thirdPrice){
			return true;
		}else{
			return false;
		}
	}


	public String getProductPrice(){
		if(driver.getCountry().equalsIgnoreCase("CA") || driver.getCountry().equalsIgnoreCase("au")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::div[contains(@class,'product-catalogue')][1]//span[@class='your-price']"));
			String productprice=driver.findElement(By.xpath("//div[@id='main-content']/descendant::div[contains(@class,'product-catalogue')][1]//span[@class='your-price']")).getText();
			logger.info("fetched product price is "+productprice);
			return productprice;
		}else if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div[1]/p/span[1]"));
			String productprice=driver.findElement(By.xpath("//div[@id='main-content']/div[4]/div[2]/div[1]/p/span[1]")).getText();
			logger.info("fetched product price is "+productprice);
			return productprice;
		}
		return null;
	}


	public boolean verifyProductInfoPresentOnQuikShopProducts() {
		if(driver.getCountry().equalsIgnoreCase("ca") || driver.getCountry().equalsIgnoreCase("au"))
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::div[contains(@class,'product-catalogue')][1]//h3/a"));

		else
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div[1]/h3/a"));
	}


	public boolean verifyRetailPricePresentInProductInfo() {
		if(driver.getCountry().equalsIgnoreCase("ca")|| driver.getCountry().equalsIgnoreCase("au"))
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::div[contains(@class,'product-catalogue')][1]//p[contains(text(),'Retail:')]"));
		else
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div[1]/p[contains(text(),'Retail:')]"));
	}

	public boolean verifyPCPricePresentInProductInfo(){
		if(driver.getCountry().equalsIgnoreCase("ca") || driver.getCountry().equalsIgnoreCase("au"))
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::div[contains(@class,'product-catalogue')][1]//span[contains(text(),'Your')]"));
		else
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[4]/div[2]/div[1]/p[contains(text(),'Retail:')]/span[contains(text(),'Your')]"));
	}

	public boolean verifyBuyNowButtonPresentInProductInfo(){
		if(driver.getCountry().equalsIgnoreCase("ca") || driver.getCountry().equalsIgnoreCase("au"))
			return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::div[contains(@class,'product-catalogue')][1]/descendant::button[contains(text(),'ADD TO BAG')]"));
		else
			try{
				return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div[1]/div[2]/div[1]//button"));
			}catch(NoSuchElementException ne){
				return driver.isElementPresent(By.xpath("//div[@id='main-content']/div[contains(@class,'quickshop-section')]/div[2]/div[1]/div[2]/div[1]//button"));
			}
	}

	public void addSecondProduct(){
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Continue shopping')]"));
		driver.click(By.xpath("//a[contains(text(),'Continue shopping')]"),"");
		logger.info("Continue shopping link clicked");
		driver.waitForPageLoad();
		driver.click(By.xpath("//div[@id='main-content']/descendant::button[contains(text(),'ADD TO BAG')][3]"),"");
		logger.info("Buy Now button clicked and another product selected");
		driver.waitForPageLoad();
	}

	public void clickCheckoutBtn(){
		driver.waitForElementPresent(CHECKOUT_BTN);
		driver.click(CHECKOUT_BTN,"");
		logger.info("Checkout button clicked");
		driver.waitForPageLoad();
	}
	public void clickClickHereLinkForRC(){
		driver.quickWaitForElementPresent(CLICK_HERE_LINK_FOR_RC);
		driver.click(CLICK_HERE_LINK_FOR_RC,"");
		logger.info("Click here link for RC User is clicked.");
		driver.waitForPageLoad();
	}
	public void enterProfileDetailsForPCAndRC(String firstName,String lastName,String emailAddress,String password,String phnNumber,String gender){
		driver.type(FIRST_NAME_FOR_PC_AND_RC, firstName);
		logger.info("first name entered as: "+firstName);
		driver.type(LAST_NAME_FOR_PC_AND_RC, lastName);
		logger.info("last name entered as: "+lastName);
		driver.type(EMAIL_ADDRESS_FOR_PC_AND_RC, emailAddress);
		logger.info("email address entered as: "+emailAddress);
		driver.type(CREATE_PASSWORD_FOR_PC_AND_RC, password);
		logger.info("password entered as: "+password);
		driver.type(CONFIRM_PASSWORD_FOR_PC_AND_RC, password);
		logger.info("confirm password entered as: "+password);
		driver.type(PHONE_NUMBER_FOR_PC_AND_RC,phnNumber);
		logger.info("Phone number entered as: "+phnNumber);
		driver.click(By.xpath(String.format(genderLocForPCAndRC, gender)),"");
		logger.info("Gender selected is: "+gender);
	}
	public void clickCreateMyAccountBtnOnCreateRetailAccountPage(){
		driver.quickWaitForElementPresent(CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC);
		driver.click(CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC,"");
		logger.info("Create my account button clicked");
		try{
			clickOKBtnOfJavaScriptPopUp();
		}catch(Exception e){
			logger.info("No alert present");
		}
		driver.waitForPageLoad();
	}
	public void enterIDNumberAsSponsorForPCAndRC(String sponsorID){
		driver.quickWaitForElementPresent(ID_NUMBER_AS_SPONSOR);
		driver.type(ID_NUMBER_AS_SPONSOR, sponsorID);
		logger.info("Sponsor ID entered is: "+sponsorID);
	}
	public void clickBeginSearchBtn(){
		driver.quickWaitForElementPresent(BEGIN_SEARCH_BTN);
		driver.click(BEGIN_SEARCH_BTN,"");
		logger.info("Begin search button clicked");
	}
	public void selectSponsorRadioBtn(){
		driver.pauseExecutionFor(5000);
		driver.quickWaitForElementPresent(SPONSOR_RADIO_BTN);
		JavascriptExecutor executor = (JavascriptExecutor)(RFWebsiteDriver.driver);
		executor.executeScript("arguments[0].click();", driver.findElement(SPONSOR_RADIO_BTN)); 
		logger.info("Radio button of sponsor is selected");
	}

	public void clickSelectAndContinueBtnForPCAndRC(){
		driver.quickWaitForElementPresent(CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC);
		driver.click(CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC,"");
		logger.info("Select and Continue button clicked");
		driver.waitForPageLoad();		
	}

	public void enterShippingProfileDetails(String addressName, String firstName,String lastName,String addressLine1,String postalCode,String phnNumber){
		driver.type(ADDRESS_NAME_FOR_SHIPPING_PROFILE, addressName);
		logger.info("Address name entered as: "+addressName);
		driver.type(ATTENTION_FIRST_NAME, firstName);
		logger.info("Attention first name entered as: "+firstName);
		driver.type(ATTENTION_LAST_NAME, lastName);
		logger.info("Attention last name entered as: "+lastName);
		driver.type(ADDRESS_LINE_1, addressLine1);
		logger.info("Address line 1 entered as: "+addressLine1);
		driver.type(ZIP_CODE, postalCode+"\t");
		logger.info("Postal code entered as: "+postalCode);
		driver.pauseExecutionFor(3000);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(CITY_DD)).click().build().perform();
		logger.info("City dropdown clicked");
		driver.waitForElementPresent(FIRST_VALUE_OF_CITY_DD);
		driver.pauseExecutionFor(2000);
		driver.click(FIRST_VALUE_OF_CITY_DD,"");
		logger.info("City selected");
		actions.moveToElement(driver.findElement(COUNTRY_DD)).click().build().perform();
		//driver.click(CITY_DD);
		logger.info("Country dropdown clicked");
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(FIRST_VALUE_OF_COUNTRY_DD);
		driver.click(FIRST_VALUE_OF_COUNTRY_DD,"");
		logger.info("Country selected");
		driver.type(PHONE_NUMBER_SHIPPING_PROFILE_PAGE,phnNumber);
		logger.info("Phone number entered as: "+phnNumber);
	}

	public void clickContinueBtnForPCAndRC(){
		driver.waitForElementPresent(CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC);
		driver.clickByJS(RFWebsiteDriver.driver, CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC,"Continue button");
		driver.waitForPageLoad();
	}

	public void clickUseAsEnteredBtn(){
		driver.quickWaitForElementPresent(USE_AS_ENTERED_BTN_LOC);
		if(driver.isElementPresent(USE_AS_ENTERED_BTN_LOC)==true){			
			driver.findElement(USE_AS_ENTERED_BTN_LOC).click();
			logger.info("use as entered button clicked");
			driver.waitForPageLoad();
		}
	}

	public void enterBillingInfoDetails(String billingName, String firstName,String lastName,String cardName,String cardNumer,String month,String year,String addressLine1,String postalCode,String phnNumber,String CVV){
		driver.pauseExecutionFor(2000);
		driver.type(BILLING_NAME_FOR_BILLING_PROFILE, billingName);
		logger.info("Billing profile name entered as: "+billingName);
		driver.type(ATTENTION_FIRST_NAME, firstName);
		logger.info("Attention first name entered as: "+firstName);
		driver.type(ATTENTION_LAST_NAME, lastName);
		logger.info("Attention last name entered as: "+lastName);
		driver.type(NAME_ON_CARD, cardName);
		logger.info("Card Name entered as: "+cardName);
		driver.type(CREDIT_CARD_NUMBER_INPUT_FIELD, cardNumer);
		logger.info("Card number entered as: "+cardNumer);
		driver.waitForElementPresent(CVV_FIELD);
		driver.type(CVV_FIELD, CVV);
		logger.info("CVV entered as "+CVV);
		driver.click(EXPIRATION_DATE_MONTH_DD,"");
		logger.info("Expiration month dropdown clicked");
		driver.click(By.xpath(String.format(expiryMonthLoc, month)),"");
		logger.info("Expiry month selected is: "+month);
		driver.click(EXPIRATION_DATE_YEAR_DD,"");
		logger.info("Expiration year dropdown clicked");
		driver.click(By.xpath(String.format(expiryYearLoc, year)),"");
		logger.info("Expiry year selected is: "+year);
		driver.type(ADDRESS_LINE_1, addressLine1);
		logger.info("Billing street address entered as: "+addressLine1);
		driver.type(ZIP_CODE, postalCode+"\t");
		logger.info("Postal code entered as: "+postalCode);
		driver.pauseExecutionFor(3000);
		driver.findElement(By.xpath("//input[contains(@id,'uxCityDropDown_Input')]")).click();
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
		//driver.pauseExecutionFor(5000);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(CITY_DD)).click().build().perform();
		//driver.click(CITY_DD);
		logger.info("City dropdown clicked");
		driver.waitForElementPresent(FIRST_VALUE_OF_CITY_DD);
		try{
			driver.click(FIRST_VALUE_OF_CITY_DD,"");
		}catch(Exception e){
			driver.findElement(By.xpath("//input[contains(@id,'uxCityDropDown_Input')]")).click();
			driver.waitForElementPresent(FIRST_VALUE_OF_CITY_DD);
			driver.click(FIRST_VALUE_OF_CITY_DD,""); 
		}
		logger.info("City selected");
		driver.type(PHONE_NUMBER_BILLING_PROFILE_PAGE,phnNumber);
		logger.info("Phone number entered as: "+phnNumber);
	}

	public void clickContinueBtn(){
		driver.quickWaitForElementPresent(CONTINUE_BTN_PREFERRED_AUTOSHIP_CART_PAGE_LOC);
		driver.click(CONTINUE_BTN_PREFERRED_AUTOSHIP_CART_PAGE_LOC,"");
		logger.info("Continue button clicked on Autoship cart page");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(2000);
	}

	public void clickCompleteOrderBtn(){
		driver.quickWaitForElementPresent(COMPLETE_ORDER_BTN);
		driver.click(COMPLETE_ORDER_BTN,"");
		logger.info("Complete order button clicked");
		driver.waitForPageLoad();
	}

	public boolean isThankYouTextPresentAfterOrderPlaced(){
		driver.waitForElementPresent(ORDER_CONFIRMATION_THANK_YOU_TXT);
		return driver.isElementPresent(ORDER_CONFIRMATION_THANK_YOU_TXT);
	}

	public void clickOnOurBusinessLink(){
		driver.waitForElementPresent(BUSINESS_LINK_LOC);
		driver.click(BUSINESS_LINK_LOC,"");
		logger.info("Our business Link clicked");
	}

	public StoreFrontEnrollNowPage clickOnOurEnrollNowLink(){
		driver.waitForElementPresent(ENROLL_NOW_LINK_LOC);
		driver.click(ENROLL_NOW_LINK_LOC,"");
		logger.info("Enroll Now Link clicked");
		driver.waitForLoadingImageToDisappear();
		return new StoreFrontEnrollNowPage(driver);
	}

	public String createConsultant(String sponsorID, String kitName,String regimenName,String enrollmentType,String firstName,String lastName,String emailID, String password,String addressLine1,String city,String postalCode,String phoneNumber, String cardNumber, String nameOnCard, String socialInsuranceNumber, String productQuantity) {
		searchCID(sponsorID);
		mouseHoverSponsorDataAndClickContinue();
		enterUserInformationForEnrollmentWithEmail(kitName, regimenName, enrollmentType, firstName, lastName, emailID, password, addressLine1, city, TestConstants.PROVINCE_CA, postalCode, phoneNumber);
		clickNextButton();
		enterCardNumber(cardNumber);
		enterNameOnCard(nameOnCard);//---
		selectNewBillingCardExpirationDate();
		enterSecurityCode(TestConstants.SECURITY_CODE);
		enterSocialInsuranceNumber(socialInsuranceNumber);
		enterNameAsItAppearsOnCard(firstName);
		clickNextButton();
		checkPulseAndCRPEnrollment();
		clickNextButton();
		selectProductForCRPAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		addQuantityOfProduct(productQuantity);
		clickOnNextBtnAfterAddingProductAndQty();
		checkThePoliciesAndProceduresCheckBox();
		checkTheIAcknowledgeCheckBox();  
		checkTheIAgreeCheckBox();
		checkTheTermsAndConditionsCheckBox();
		clickOnChargeMyCardAndEnrollMeBtn();
		clickOnConfirmAutomaticPayment();
		return emailID;
	}

	//	public String createPC(String firstName, String lastName, String password, String emailID, String sponsorID, String newBillingProfileName) {
	//		clickOnShopLink();
	//		clickOnAllProductsLink();
	//		selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
	//		clickOnCheckoutButton();
	//		enterNewPCDetails(firstName, lastName, password, emailID);
	//		enterMainAccountInfo();
	//		enterSponsorIdDuringCreationOfPC(sponsorID);
	//		mouseHoverSponsorDataAndClickContinueForPC();
	//		clickOnNextButtonAfterSelectingSponsor();
	//		clickOnShippingAddressNextStepBtn();
	//		enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
	//		enterNewBillingNameOnCard(newBillingProfileName);
	//		selectNewBillingCardExpirationDate();
	//		enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
	//		selectNewBillingCardAddress();
	//		clickOnSaveBillingProfile();
	//		clickOnBillingNextStepBtn();
	//		clickOnPCPerksTermsAndConditionsCheckBoxes();
	//		clickPlaceOrderBtn();
	//		return emailID;
	//	}

	public boolean verifyWelcomeDropdownPresentForCheckUserRegistered(){  
		driver.waitForElementPresent(By.xpath("//li[@id='account-info-button']/a[contains(text(),'Welcome')]"));
		return driver.isElementPresent(By.xpath("//li[@id='account-info-button']/a[contains(text(),'Welcome')]"));
	}

	public void applyPriceFilterHighToLow() {
		if (isMobile()) {driver.click(By.id("sortOptions"), "Filter selector");}
		Select select = new Select(driver.findElement(By.id("sortOptions")));
		select.selectByVisibleText("Price: High to Low");
		logger.info("filter done for high to low price");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public String getCountryNameFromNotYourCountryPopUp(String countryNumber){
		driver.waitForElementPresent(By.xpath("//div[@id='notYourCountryPopupId']/descendant::a["+countryNumber+"]"));
		String errorMessage=driver.findElement(By.xpath("//div[@id='notYourCountryPopupId']/descendant::a["+countryNumber+"]")).getText();
		return errorMessage;
	}

	public boolean isAutoshipOrderDatePresentForCRP(){
		String autoShipOrderDate = null;
		driver.waitForElementPresent(By.xpath("//div[@id='pending-autoship-orders-table']/descendant::div[text()='PENDING'][1]/preceding::div[2]"));
		autoShipOrderDate = driver.findElement(By.xpath("//div[@id='pending-autoship-orders-table']/descendant::div[text()='PENDING'][1]/preceding::div[2]")).getText();
		return  (!autoShipOrderDate.isEmpty());
	}

	public boolean isAutoshipOrderDatePresentForPulse(){
		String autoShipOrderDate = null;
		driver.waitForElementPresent(By.xpath("//div[@id='pending-autoship-orders-table']/descendant::div[text()='PENDING'][2]/preceding::div[2]"));
		autoShipOrderDate = driver.findElement(By.xpath("//div[@id='pending-autoship-orders-table']/descendant::div[text()='PENDING'][2]/preceding::div[2]")).getText();
		return  (!autoShipOrderDate.isEmpty());
	}

	public void hoverOnShopLinkAndClickCategoryLink(String categoryName){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.waitForElementPresent(By.id("our-products")); 
		WebElement shopSkinCare = driver.findElement(By.id("our-products"));
		actions.moveToElement(shopSkinCare).pause(1000).click().build().perform();
		WebElement categoryLink = driver.findElement(By.xpath("//ul[@id='dropdown-menu' and @style='display: block;']//a[text()='"+categoryName+"']"));
		actions.moveToElement(categoryLink).pause(1000).build().perform();
		while(true){
			try{
				driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//ul[@id='dropdown-menu' and @style='display: block;']//a[text()='"+categoryName+"']"),"");
				break;
			}catch(Exception e){
				actions.moveToElement(shopSkinCare).pause(1000).click().build().perform();
			}
		}
		logger.info(categoryName+" link clicked");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public boolean isAlertPresentForVariantProductsAdHoc(String productName){
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath(String.format(addToBagButtonForProductNameLoc, productName)));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(addToBagButtonForProductNameLoc, productName)),"");
		try{
			driver.switchTo().alert().accept();
			logger.info("Variant Product alert was accepted.");
			return true;
		}catch(Exception e){
			logger.info("Alert for Variant Product was not displayed");
			return false;
		}
	}

	public boolean isVariantProductAvailableInCart(String productName){
		boolean flag=false;
		List<WebElement> lis = driver.findElements(ALL_PRODUCT_NAME_IN_CART);
		for (WebElement wb : lis) {
			if (wb.getText().toLowerCase().trim().contains(productName.toLowerCase())) {
				flag= true;
				break;
			}
		}
		return flag;
	}

	public void removeProductFromCart(String productName){
		if(isVariantProductAvailableInCart(productName)==true){
			List<WebElement> lis = driver.findElements(ALL_PRODUCT_NAME_IN_CART);
			while(true){
				boolean flag=false;
				int i = 1;
				for (WebElement wb : lis) {
					if (wb.getText().toLowerCase().contains(productName.toLowerCase())) {
						break;
					}
					i++;
				}
				driver.click(By.xpath(String.format(removeLinkLoc, i)),"");
				if(isVariantProductAvailableInCart(productName)==true){
					flag = true;
					break;
				}
				if(flag==false){
					break;
				}
			}
		}
	}

	public boolean isEnrollUnderLastUplinePopupDisplayed(String userType){
		if(userType.equalsIgnoreCase("consultant")){
			driver.waitForElementToBeVisible(By.xpath("//form[@id='inactiveConsultant180Form']//input[@id='enrollUnderLastUpline']"), 10);
			return driver.isElementVisible(By.xpath("//form[@id='inactiveConsultant180Form']//input[@id='enrollUnderLastUpline']"));
		}else{
			driver.waitForElementToBeVisible(By.xpath("//form[@id='inactivePc90Form']//input[@id='enrollUnderLastUpline']"), 10);
			return driver.isElementVisible(By.xpath("//form[@id='inactivePc90Form']//input[@id='enrollUnderLastUpline']"));
		}
	}

	public void enterInvalidPassword(String password){
		driver.type(By.id("new-password-account"),password+"\t","password");
	}

	public void enterInvalidConfirmPassword(String password){
		driver.type(By.id("new-password-account2"),password+"\t","confirm password");
	}

	public void selectVariantAndAddProductToAdHocCart(String productName , String shade){
		logger.info("Waiting for Variant Product");
		driver.waitForElementPresent(By.xpath(String.format(addToBagButtonForProductNameLoc, productName)));
		Select selectVarient=new Select(driver.findElement(By.xpath(String.format(shadeDDLoc, productName))));
		logger.info("Variant Product is available. Selecting value from dropdown.");
		selectVarient.selectByVisibleText(shade);
		driver.click(By.xpath(String.format(addToBagButtonForProductNameLoc, productName)),"");
		logger.info("Variant Product"+productName+ "added to Ad-hoc cart");
		driver.waitForPageLoad();
	}

	public boolean isVariantProductAvailableInCart(String firstHalfName, String secondHalfName, String shade){
		if(driver.isElementPresent(By.xpath(String.format(checkVariantProductInCart,firstHalfName, shade, secondHalfName)))){
			return true;
		}else{
			return false;
		}
	}

	public void removeProductFromCart(String firstHalfName, String secondHalfName, String shade){
		driver.waitForLoadingImageToDisappear();
		if(isVariantProductAvailableInCart(firstHalfName,secondHalfName, shade)){
			logger.info("Varient product is in already in cart, trying to Remove!");
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(removeButtonLinkOfVariantProduct,firstHalfName, shade, secondHalfName)),"");
		}
	}

	public boolean isAlertPresentForVariantProductInAutoship(String productName){
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath(String.format(addToCRPButtonForProductNameLoc, productName)));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(addToCRPButtonForProductNameLoc, productName)),"");
		try{
			driver.switchTo().alert().accept();
			logger.info("Variant Product alert was accepted.");
			return true;
		}catch(Exception e){
			logger.info("Alert for Variant Product was not displayed");
			return false;
		}
	}

	public void selectVariantAndAddProductToAutoshipCart(String productName , String shade){
		logger.info("Waiting for Variant Product");
		driver.waitForElementPresent(By.xpath(String.format(addToCRPButtonForProductNameLoc, productName)));
		Select selectVarient=new Select(driver.findElement(By.xpath(String.format(shadeDDLoc, productName))));
		logger.info("Variant Product is available. Selecting value from dropdown.");
		selectVarient.selectByVisibleText(shade);
		driver.click(By.xpath(String.format(addToCRPButtonForProductNameLoc, productName)),"");
		logger.info("Variant Product"+productName+ "added to autoship cart");
		driver.waitForPageLoad();
	}

	public boolean isErrorMessagePresentForInvalidSSN(){
		driver.quickWaitForElementPresent(By.xpath("//input[@id='S-S-N']/following::label[1]"),10);
		return driver.isElementPresent(By.xpath("//input[@id='S-S-N']/following::label[1]"));
	}

	public String getSplittedPWSFromURL() {
		String currentUrl=null;
		String splitPWS=null;
		String pws=null;
		currentUrl=driver.getCurrentUrl();
		splitPWS=currentUrl.split("//")[1];
		pws=splitPWS.split(".myrfo")[0];
		logger.info("splitted PWS from url is: "+pws);
		return pws;
	}

	public void searchCID(String CID){
		Actions action = new Actions(RFWebsiteDriver.driver);
		if (!isMobile()) {
		action.doubleClick(driver.findElement(By.xpath("//*[@id='sponserparam' or @id='sponsor-name-id']"))).build().perform();
		
		}else {
			driver.click(By.xpath("//*[@id='sponserparam' or @id='sponsor-name-id']"), "Sponsor Search field");
		}
		
		driver.type(By.xpath("//*[@id='sponserparam' or @id='sponsor-name-id']"), CID,"sponsor search");
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//*[@id='search-sponsor-button' or @value='Search']"),"sponsor search button");
		driver.waitForLoadingImageToDisappear();
		//		  if(driver.isElementVisible(By.xpath("//div[contains(@id,'search-results')]/descendant::*[contains(@value,'Select')][1]"))==false){
		//		   driver.type(By.xpath("//*[@id='sponserparam' or @id='sponsor-name-id']"),CID,"sponsor search");
		//		   driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//*[@id='search-sponsor-button' or @value='Search']"),"sponsor search button");
		//		   driver.waitForLoadingImageToDisappear(); 
		//		  }
	}	


	public boolean isSponsorPresentInSearchResult(){
		driver.waitForElementPresent(By.xpath("//*[@id='search-results']//input[contains(@value,'Select & Continue')] | //*[@id='search-results']/div[1]/div[1]//a[contains(text(),'Select')]"));
		return driver.isElementPresent(By.xpath("//*[@id='search-results']//input[contains(@value,'Select & Continue')] | //*[@id='search-results']/div[1]/div[1]//a[contains(text(),'Select')]"));
	} 

	public boolean validateInvalidCreditCardMessage(){
		if(driver.getCountry().equalsIgnoreCase("CA")) {
			driver.waitForElementPresent(By.xpath("//div[@id='add-new-billing']/descendant::label[@class='error']"));
			if(driver.isElementPresent(By.xpath("//div[@id='add-new-billing']/descendant::label[@class='error']"))){
				return true;
			}
			else{
				return false;
			}
		}else {
			return driver.isElementPresent(By.xpath("//*[@id='globalMessages']//span[contains(text(),'Your credit card could not be processed. It was declined by the processor')] | //div[@class='col-lg-12 col-md-12 col-xs-12 ommit-padding-lr spacer']/div[@class='mat-div']/span/label[contains(text(),'Please enter a valid credit card number.')]"));
		}
	}	

	public void enterSocialInsuranceNumber(String sin) {
		if(driver.getCountry().equalsIgnoreCase("ca")){
			driver.type(By.id("S-S-N"),sin,"Social Insurance Number");
			driver.findElement(By.xpath("//input[@id='S-S-N']")).sendKeys(Keys.TAB);
		}
	}

	public boolean verifySponserSearchFieldIsPresent() {
		return driver.isElementVisible(By.id("sponsor-name-id"));
	}


	public boolean verifyPopUpForExistingInactivePC90Days() {
		boolean status = false;
		try {
			// driver.findElement(By.xpath("//a[contains(@class,
			// 'fancybox-close')]")).click();
			// driver.pauseExecutionFor(500);
			// return true;

			status = driver.isElementVisible(By.xpath("//a[contains(@class, 'fancybox-close')]"));
			driver.click(By.xpath("//a[contains(@class, 'fancybox-close')]"), "90 Days Popup");
			return status;
		} catch (NoSuchElementException ex) {
			return status;
		}
	}

	public boolean verifyPopUpForExistingActivePC() {
		boolean status= false;
		try{
			/*driver.findElement(By.xpath("//a[contains(@class, 'fancybox-close')]")).click();
			driver.pauseExecutionFor(500);
			return true;*/

			status = driver.isElementVisible(By.xpath("//a[contains(@class, 'fancybox-close')]"));
			driver.click(By.xpath("//a[contains(@class, 'fancybox-close')]"), "PopUp for existing Active PC");
			return status;
		}catch(NoSuchElementException ex){
			return status;
		}		
	}


	public boolean verifyPopUpForExistingActiveCCLessThan6Month() {
		boolean status= false;
		try{
			/*driver.findElement(By.xpath("//a[contains(@class, 'fancybox-close')]")).click();
			driver.pauseExecutionFor(500);
			return true;*/
			status = driver.isElementVisible(By.xpath("//a[contains(@class, 'fancybox-close')]"));
			driver.click(By.xpath("//a[contains(@class, 'fancybox-close')]"), "PopUp for existing Active PC");
			return status;
		}catch(NoSuchElementException ex){
			return status;
		}		
	}

	public boolean validateUserAbleToSerachSponsorAndContinueFlow(){
		return driver.isElementPresent(By.xpath("//div[@id='the-search-results'][@class='row'] | //div[contains(@id,'search-results')]") );
	}

	public boolean verifyPopUpForExistingActiveRC() {
		boolean status = false;
		try{
			/*driver.findElement(By.xpath("//a[contains(@class, 'fancybox-close')]")).click();
			driver.pauseExecutionFor(500);
			return true;*/
			status = driver.isElementVisible(By.xpath("//a[contains(@class, 'fancybox-close')]"));
			driver.click(By.xpath("//a[contains(@class, 'fancybox-close')]"), "PopUp for existing Active RC");
			return status;
		}catch(NoSuchElementException ex){
			return status;
		}		
	}

	public void clickForgotYourPaswordLinkOnExistingUserPopup(String userType){
		if(userType.equalsIgnoreCase("pc")){
			driver.waitForElementPresent(By.xpath("//div[@id='activePCPopup']//a[@class='resetPasswordEmail']"));
			driver.click(By.xpath("//div[@id='activePCPopup']//a[@class='resetPasswordEmail']"), "forgot password link of existing consultant popup");
		}else{
			driver.waitForElementPresent(By.xpath("//div[@id='activeRetailPopup']//a[@class='resetPasswordEmail']"));
			driver.click(By.xpath("//div[@id='activeRetailPopup']//a[@class='resetPasswordEmail']"), "forgot password link of existing consultant popup");
		}
		driver.pauseExecutionFor(3000);
		driver.waitForPageLoad();
	}

	/**
	 * This method clicks on the Not your sponsor link on Main account info page while
	 * enrolling PC/RC
	 */
	public void clickOnNotYourSponsorLink(){
		//driver.waitForElementPresent(By.xpath("//a[@id='not-your-sponsor']"));
		//driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[@id='not-your-sponsor']"),"Not your sponsor link");

		if(driver.isElementVisible(By.xpath("//a[@id='not-your-sponsor']"))) {
			driver.click(By.xpath("//a[@id='not-your-sponsor']"), "Not your sponsor link");	
		}else {
			driver.click(By.xpath("//*[@id='sponsor_search_again']"), "Sponsor search again");	
		}

	}

	public void enterEmailAddress(String emailAddress){ 
		driver.waitForElementPresent(By.id("email-account"), 10);
		driver.type(By.id("email-account"), emailAddress+"\t","Email Address");
		driver.pauseExecutionFor(5000);		
	}

	public boolean verifyInvalidSponsorPopupIsPresent(){
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(By.id("inactiveConsultant180Form"));
		if(driver.isElementPresent(By.id("inactiveConsultant180Form"))){
			return true;
		}else
			return false;
	}

	public String getProductName(String productNumber){
		if(driver.getCountry().equalsIgnoreCase("US")){
			driver.quickWaitForElementPresent(By.xpath(".//div[@id='main-content']/div[4]/div[2]/div[1]/h3/a"));
			String productName=driver.findElement(By.xpath(".//div[@id='main-content']/div[4]/div[2]/div[1]/h3/a")).getText();
			logger.info("fetched product name is "+productName);
			return productName;
		}
		else {
			driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::h3["+productNumber+"]/a"));
			String productName=driver.findElement(By.xpath("//div[@id='main-content']/descendant::h3["+productNumber+"]/a")).getText();
			logger.info("fetched product name is "+productName);
			return productName;
		}		
	}	

	public void selectEnrollmentKitPage(String kitName,String regimenName){
		regimenName = regimenName.toUpperCase();
		driver.click(By.xpath("//div[@class='enrollment-kits row']/descendant::*[contains(text(),'"+kitName+"')]"),"kitname= "+kitName);
		if(kitName.equalsIgnoreCase(TestConstants.KIT_NAME_PORTFOLIO)==false){
			driver.pauseExecutionFor(2000);
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//span[@id='regimen-name' and contains(text(),'"+regimenName+"')]"),"regimen= "+regimenName);//(By.xpath("//span[@id='regimen-name' and contains(text(),'"+regimenName+"')]"),"regimen= "+regimenName);
			driver.click (By.id("next-button"),"Next button after selecting regimen"); 
		}
	}

	public double getSubTotalOfFirstProduct(){
		driver.waitForElementPresent(By.xpath("//input[@id='quantity0']"));
		String qty=driver.findElement(By.xpath("//input[@id='quantity0']")).getAttribute("value");
		Integer.parseInt(qty);
		String total=driver.findElement(By.xpath("//div[@class='cartItems']/div[1]//span[@class='special-price']")).getText();
		String[] totalvalue= total.split("\\$+");
		Double.parseDouble(totalvalue[1].trim().replace(",", ""));
		//Integer.parseInt(totalvalue[1].trim());
		logger.info("Subtotal of first product "+Integer.parseInt(qty)*Double.parseDouble(totalvalue[1].trim()));
		return Integer.parseInt(qty)*Double.parseDouble(totalvalue[1].trim());
	}

	public void enterCardNumber(String cardNumber){
		driver.pauseExecutionFor(5000);
		driver.type(By.id("card-nr"),cardNumber,"card number");
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//*[@id='expiryYear']"),"expiry year");
		driver.type(By.id("security-code"),TestConstants.SECURITY_CODE);
	}

	public void enterAddressLine1(String addressLine1){
		driver.pauseExecutionFor(3000);
		driver.type(By.id("address-1"),addressLine1,"addressLine 1");
	}

	public void clickOnCheckoutButton(){
		driver.click(By.xpath("//input[@value='CHECKOUT']"),"checkout");
		if(driver.isElementPresent(By.xpath("//input[@onclick='checkOutNow();']"))){
			driver.click(By.xpath("//input[@onclick='checkOutNow();']"),"");
		}
		driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
		dismissPolicyPopup();
	} 

	public void selectRandomProductCategory(int randomNumber){
		String number = Integer.toString(randomNumber);
		driver.waitForElementPresent(By.xpath("//input[@value='- Product(s) -']"));
		driver.scrollDownIntoView(By.xpath("//input[@value='- Product(s) -']"));
		driver.click(By.xpath("//input[@value='- Product(s) -']"),"");
		driver.pauseExecutionFor(2000);
		String selectedProductCategory = driver.findElement(By.xpath(String.format("//input[@value='- Product(s) -']/following-sibling::ul/li[%s]//div[@class='dropdown-items']/div[1]", number))).getText();
		driver.click(By.xpath(String.format("//input[@value='- Product(s) -']/following-sibling::ul/li[%s]//div/div[contains(@class,'repaired-checkbox')]",number)),"Random selected product category is "+selectedProductCategory);
		driver.waitForPageLoad();
	}

	public void clickEditShoppingCartLink(){
		driver.waitForElementPresent(By.xpath("//a[@class='gray-anchor']"));
		driver.scrollDownIntoView(By.xpath("//a[@class='gray-anchor']"));
		driver.click(By.xpath("//a[@class='gray-anchor']"),"");
		logger.info("edit shopping cart link clicked");
		driver.waitForPageLoad();
	}

	public void mouseHoverProductAndClickQuickInfo(){
		driver.waitForElementPresent(By.xpath("//div[@id='main-content']/descendant::div[@class='product-picture'][1]//img")); 
		WebElement allProducts = driver.findElement(By.xpath("//div[@id='main-content']/descendant::div[@class='product-picture'][1]//img|//div[@id='main-content']/descendant::div[@class='product-picture'][2]//img"));
		//actions.moveToElement(allProducts).build().perform();
		JavascriptExecutor js = (JavascriptExecutor)(RFWebsiteDriver.driver); 
		String strJavaScript = "var element = arguments[0];"
				+ "var mouseEventObj = document.createEvent('MouseEvents');"
				+ "mouseEventObj.initEvent( 'mouseover', true, true );"
				+ "element.dispatchEvent(mouseEventObj);";
		js.executeScript(strJavaScript, allProducts);
		logger.info("mouse hover operation performed");
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::div[@class='product-picture'][1]//input"));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='main-content']/descendant::div[@class='product-picture'][1]//input"),"");
		driver.pauseExecutionFor(5000);
	}

	public void clickOnEditBillingOnReviewAndConfirmPage(){
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Billing info')]/a[text()='Edit']"));
		driver.scrollDownIntoView(By.xpath("//h3[contains(text(),'Billing info')]/a[text()='Edit']"));
		driver.click(By.xpath("//h3[contains(text(),'Billing info')]/a[text()='Edit']"),"Edit billing link on review and confirm page");
		driver.waitForPageLoad();
	} 	

	public boolean validateSatisfactionGuaranteeLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Satisfaction Guarantee')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'Satisfaction Guarantee')]"),"");
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("satisfaction-guarantee");
	}


	public boolean validateContactUsLink(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-sections']//a[contains(text(),'Contact Us')]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='footer-sections']//a[contains(text(),'Contact Us')]"),"");
		driver.pauseExecutionFor(4000);
		return driver.getCurrentUrl().contains("contact");
	}

	public boolean isProcedurePageIsDisplayedAfterClickPolicyAndProcedureLink(){
		String parentWindowID = driver.getWindowHandle();
		driver.waitForElementPresent(POLICY_AND_PROCEDURE_LINK);
		driver.clickByJS(RFWebsiteDriver.driver,POLICY_AND_PROCEDURE_LINK,"");
		driver.pauseExecutionFor(3000);
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		boolean status=false;
		while(it.hasNext()){
			String childWindowID=it.next();
			if(!parentWindowID.equalsIgnoreCase(childWindowID)){
				driver.switchTo().window(childWindowID);
				if(driver.getCurrentUrl().contains("pdf")){
					status=true;
				}
			}
		}
		driver.close();
		driver.switchTo().window(parentWindowID);
		return status;
	}

	public void clickLearnMoreLinkUnderSolutionToolAndSwitchControl(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp_content']/descendant::a[contains(text(),'GET STARTED')][1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='corp_content']/descendant::a[contains(text(),'GET STARTED')][1]"),"");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
		/*String parentWindowID=driver.getWindowHandle();
	  Set<String> set=driver.getWindowHandles();
	  Iterator<String> it=set.iterator();
	  while(it.hasNext()){
	   String childWindowID=it.next();
	   if(!parentWindowID.equalsIgnoreCase(childWindowID)){
	    driver.switchTo().window(childWindowID);
	   }
	  }*/
	}

	public void clickEnrollNowFromWhyRFPage(){
		driver.waitForElementPresent(By.xpath("//h2[contains(text(),'BECOME A CONSULTANT')]/following::a[contains(text(),'Enroll Now') or contains(text(),'Enrol Now')][1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//h2[contains(text(),'BECOME A CONSULTANT')]/following::a[contains(text(),'Enroll Now') or contains(text(),'Enrol Now')][1]"),"");
		driver.waitForPageLoad();
	}

	public void clickEnrollNowFromBizHomePage(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp_content']/descendant::a[contains(@href,'why-rf') and contains(text(),'LEARN MORE') or contains(text(),'Learn More')][1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='corp_content']/descendant::a[contains(@href,'why-rf') and contains(text(),'LEARN MORE') or contains(text(),'Learn More')][1]"),"");
		driver.waitForPageLoad();
	}

	public void clickOnSaveAfterEditPWS(){
		driver.waitForElementPresent(By.xpath("//div[@class='editphotosmode']//input"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='editphotosmode']//input"),"");
	}


	public void checkEmailFieldCBOnEditConsultantInfoPage(){
		driver.waitForPageLoad();
		driver.waitForElementPresent(By.xpath("//input[@id='onlyShowContactMeForm']/.."));
		if(driver.isElementPresent(By.xpath("//input[@id='onlyShowContactMeForm' and @class='checked']"))==false){
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@id='onlyShowContactMeForm']/.."),"");
		}
	}

	public void clickOnPersonalizeMyProfileLink(){
		if(driver.getCountry().equalsIgnoreCase("au")){
			driver.waitForElementPresent(By.xpath("//a[contains(text(),'Personalise my Profile')]"));
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[contains(text(),'Personalise my Profile')]"),"");
		}else{
			driver.waitForElementPresent(By.xpath("//a[contains(text(),'Personalize my Profile')]"));
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[contains(text(),'Personalize my Profile')]"),"");
		}
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOnUpdateCartShippingNextStepBtnDuringEnrollment() {
		driver.waitForElementPresent(By.xpath("//input[@class='use_address btn btn-primary']"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@class='use_address btn btn-primary']"),"");
		logger.info("Next button on shipping update cart clicked"); 
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(3000);
	}


	public boolean verifyAnotherConsultantPrefixIsNotAllowed(String sitePrefix){
		//		driver.quickWaitForElementPresent(By.xpath("//p[@id='prefix-validation']/span[contains(text(),'"+sitePrefix+".myrfo"+driver.getEnvironment()+".com/"+driver.getCountry()+"/ is not available')]"));
		//		if(driver.isElementPresent(By.xpath("//p[@id='prefix-validation']/span[contains(text(),'"+sitePrefix+".myrfo"+driver.getEnvironment()+".com/"+driver.getCountry()+"/ is not available')]"))){
		//			return true;
		//		}else
		//			return false;
		driver.quickWaitForElementPresent(By.xpath("//label[@for='webSitePrefix' and @class='error']"));
		driver.pauseExecutionFor(5000);
		return driver.isElementVisible(By.xpath("//label[@for='webSitePrefix' and @class='error']"));
	}


	public boolean verifyCurrentUrlContainCorp(){  
		driver.waitForPageLoad();
		driver.pauseExecutionFor(2000);
		System.out.println("current url is "+driver.getCurrentUrl());
		return driver.getCurrentUrl().toLowerCase().contains("corp");
	}

	public boolean isTheMessageOfNoPWSDisplayed(){
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(By.xpath("//*[contains(text(),'The Consultant you searched for does not have a personal Rodan + Fields website.')]"));
	}

	public void searchProduct(String productName){
		driver.waitForPageLoad();
		logger.info("Searching for Varient Product");
		driver.pauseExecutionFor(1000);
		driver.click(By.xpath("//main[@class='container-fluid']/descendant::span[@class='icon-search'][2]"),"search icon for enter product name");
		driver.type(By.xpath("//main[@class='container-fluid']/descendant::input[@id='search-input'][1]"),productName ,"product name");
		driver.findElement(By.xpath("//main[@class='container-fluid']/descendant::input[@id='search-input'][1]")).sendKeys(Keys.ENTER);
		//	driver.click(By.xpath("//div[@id='header']/descendant::input[@id='search-input'][1]")., "search icon after enter product name");

	}

	/**
	 * This method goto Products page, add a product to cart and do checkout
	 */
	public void addAProductToCartForSearchedProduct(String catalogID){
		driver.waitForPageLoad();
		driver.waitForElementPresent(By.xpath("//div[@class='product-picture']//a[contains(@href,'"+catalogID+"')]/ancestor::div[contains(@class,'product')][2]//button[contains(text(),'ADD TO BAG')]"),"Waiting for Product");
		driver.click(By.xpath("//div[@class='product-picture']//a[contains(@href,'"+catalogID+"')]/ancestor::div[contains(@class,'product')][2]//button[contains(text(),'ADD TO BAG')]"),"");
		driver.waitForLoadingImageToDisappear();
		logger.info("Variant Product added to Ad-hoc cart");
		driver.waitForPageLoad();
	}

	/**
	 * This method goto Products page, add a product to autoship and do checkout
	 */
	public void addAProductToAutoshipForSearchedProduct(String catalogID){
		driver.waitForPageLoad();
		driver.waitForElementPresent(By.xpath("//div[@class='product-picture']//a[contains(@href,'"+catalogID+"')]/ancestor::div[contains(@class,'product')][2]//button[contains(text(),'ADD TO CRP')]"),"Waiting for Product");
		driver.click(By.xpath("//div[@class='product-picture']//a[contains(@href,'"+catalogID+"')]/ancestor::div[contains(@class,'product')][2]//button[contains(text(),'ADD TO CRP')]"),"");
		driver.waitForLoadingImageToDisappear();
		logger.info("Variant Product added to Ad-hoc cart");
		driver.waitForPageLoad();
	}

	/**
	 * This method goto Products page, add a product to autoship and do checkout
	 */
	public void addAProductToPCPerksForSearchedProduct(String catalogID){
		driver.waitForPageLoad();
		driver.waitForElementPresent(By.xpath("//div[@class='product-picture']//a[contains(@href,'"+catalogID+"')]/following::input[@value='ADD to PC Perks'][1]"),"Waiting for Product");
		driver.click(By.xpath("//div[@class='product-picture']//a[contains(@href,'"+catalogID+"')]/following::input[@value='ADD to PC Perks'][1]"),"");
		driver.waitForLoadingImageToDisappear();
		logger.info("Variant Product added to Ad-hoc cart");
		driver.waitForPageLoad();
	} 

	 public boolean validateErrorMessagePleaseFixThisFieldDisplayed(){
			driver.quickWaitForElementPresent(By.xpath("//label[@for='webSitePrefix' and @class='error']|//*[@id=\"webSitePrefixForm\"]/div/div/span/label"));
			driver.pauseExecutionFor(3000);
			return  driver.isElementVisible(By.xpath("//label[@for='webSitePrefix' and @class='error']|//*[@id=\"webSitePrefixForm\"]/div/div/span/label"));	
		}
				
		public String verifyAnotherConsultantPrefixIsNotAllowedErrorMessageFixThisField(){
			driver.quickWaitForElementPresent(By.xpath("//label[@for='webSitePrefix' and @class='error']|//*[@id=\"webSitePrefixForm\"]/div/div/span/label"));
			driver.pauseExecutionFor(3000);
			 driver.isElementVisible(By.xpath("//label[@for='webSitePrefix' and @class='error']|//*[@id=\"webSitePrefixForm\"]/div/div/span/label"));
			String errorMessage = driver.getText(By.xpath("//*[@id=\\\"webSitePrefixForm\\\"]/div/div/span/label"));
			return errorMessage;
		}
		
	public String verifyAnotherConsultantPrefixIsNotAllowedErrorMessageNotAvailable(){
			driver.quickWaitForElementPresent(By.xpath("//*[@id=\"prefix-validation\"]/span[1]"));
			driver.pauseExecutionFor(3000);
			 driver.isElementVisible(By.xpath("//*[@id=\"prefix-validation\"]/span[1]"));
			String errorMessage = driver.getText(By.xpath("//*[@id=\"prefix-validation\"]/span[1]"));
			return errorMessage;
		}
	
	public String selectAndGetRandomProductPriceFilter(int randomNumProductprice){
		String randomProductPriceNumber= Integer.toString(randomNumProductprice);
		driver.scrollDownIntoView(By.xpath("//input[@value='- Price -']"));
		driver.waitForElementToBeVisible(By.xpath("//input[@value='- Price -']"), 10);
		driver.click(By.xpath("//input[@value='- Price -']"),"Price drop down");
		driver.pauseExecutionFor(2000);
		driver.waitForElementToBeVisible(By.xpath(String.format("//input[@value='- Price -']/following::li[%s]//div[@class='dropdown-items']/div[1]", randomProductPriceNumber)), 10);
		String randomPriceRange = driver.findElement(By.xpath(String.format("//input[@value='- Price -']/following::li[%s]//div[@class='dropdown-items']/div[1]", randomProductPriceNumber))).getText();
		driver.scrollDownIntoView(By.xpath(String.format("//input[@value='- Price -']/following::li[%s]//div[@class='repaired-checkbox']/input[1]",randomProductPriceNumber)));
		driver.waitForElementToBeVisible(By.xpath(
				String.format("//input[@value='- Price -']/following::li[%s]//div[@class='repaired-checkbox']",
						randomProductPriceNumber)),
				10);
		driver.click(By.xpath(
				String.format("//input[@value='- Price -']/following::li[%s]//div[@class='repaired-checkbox']",
						randomProductPriceNumber)),
				"Random selected product Price is " + randomPriceRange);
		driver.waitForPageLoad();
		logger.info("price range is "+randomPriceRange);
		return randomPriceRange;
	}

}
