package com.rf.pages.website.heirloom;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;

public class StoreFrontHeirloomBillingAndShippingProfilePage extends StoreFrontHeirloomWebsiteBasePage{

	public StoreFrontHeirloomBillingAndShippingProfilePage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LogManager
			.getLogger(StoreFrontHeirloomBillingAndShippingProfilePage.class.getName());


	private static final By ADD_NEW_SHIPPING_ADDRESS_MANDATORY_FIELDS_ERRORS=By.xpath("//form[@id='addressForm']//input[contains(@placeholder,'*')]/following-sibling::p/span[contains(@style,'color: red;')]");
	private static final By USE_THIS_ADDRESS_FOR_ALL_FUTURE_ORDERS_INCLUDING_CRP_CHECKBOX_LOC=
			By.xpath("//div[contains(text(),'Use this')]/preceding-sibling::input[@id='isDefault']");
	private static final By SHIPPING_PROFILE_UPDATED_SUCCESSFULLY_MSG=By.id("confirmationMessage");
	private static final By ADD_NEW_SHIPPING_ADDRESS_MANDATORY_FIELDS=By.xpath("//form[@id='addressForm']//input[contains(@placeholder,'*')]");
	private static final By ADD_NEW_SHIPPING_ADDRESS_NON_MANDATORY_FIELDS=By.xpath("//form[@id='addressForm']//input[not(contains(@placeholder,'*'))]");
	private static final By CITY_LOC=By.xpath("//select[@id='city']");
	private static final By STATE_LOC=By.xpath("//select[@id='state']");
	private static final By HEADER_TEXT_ON_ALERT_WHEN_SELECT_SHIPPING_DETAILS_AS_DEFAULT=By.xpath("//div[@id='update-auto-ship-overlay' and contains(@style,'block')]/div[text()='Update Default Shipping Address']");
	private static final By DEFAULT_SHIPPING_PROFILE_POPUP_TEXT_LOC=By.xpath("//div[@id='update-auto-ship-overlay' and contains(@style,'block')]//p[contains(text(),'This will automatically update the shipping address for all your future orders')]");

	private static final By PROFILE1_NAME_LOC=By.id("profileNameLabel0");
	private static final By PROFILE1_ADDRESS1_LOC=By.id("addressLabel0");
	private static final By CANCEL_BUTTON_LOC=By.id("cancelButton");
	private static final By EDIT_NON_DEFAULT_SHIPPING_PROFILE_LOC=By.xpath(".//*[@id='addressList']//a[contains(text(),'Edit')]/following::input[not(contains(@checked,'checked'))][1]/preceding::a[contains(text(),'Edit')][1]");
	private static final By EDIT_BUTTON_FIRST_SHIPPING_PROFILE=By.xpath(".//*[@id='addressList']/descendant::a[contains(text(),'Edit')][1]");
	private static final By ADD_NEW_SHIPPING_ADDRESS_LINK_LOC=By.xpath("//a[contains(text(),'Add a new shipping address')]");
	private static final By PROFILE_NAME_LOC=By.id("profileName");
	private static final By FIRST_NAME_LOC=By.id("firstName");
	private static final By LAST_NAME_LOC=By.id("lastName");
	private static final By ADDRESS1_LOC=By.id("address1");
	private static final By ZIP_CODE_LOC=By.id("zipCode");
	private static final By PHONE_NUMBER_LOC=By.id("phoneNumber");
	private static final By SAVE_BUTTON_LOC=By.id("saveShippingAddress");
	private static final By NUMBER_OF_Billing_PROFILES_LOC=By.xpath("//div[contains(text(),'Default billing address')]");
	private static final By FIRST_NON_DEFAULT_BILLING_OR_SHIPPING_PROFILE_NAME_LOC=By.xpath("//div[@id='profileList' or @id='addressList']/descendant::*[contains(@id,'isDefaultCheckbox')][not(contains(@checked,'checked'))][1]/preceding::span[contains(@id,'profileNameLabel')][1]");
	private static final By SHIPPING_PROFILES_LOC=By.xpath("//*[@id='addressList']/descendant::div[1]");
	private static final By NUMBER_OF_SHIPPING_PROFILES_LOC=By.xpath(".//*[@id='addressList']/descendant::div[contains(text(),'Default')]");
	private static final By DEFAULT_SHIPPING_OR_BILLING_CHECKBOX_LOC=By.xpath("//*[contains(@id,'isDefaultCheckbox')]/self::input[not(contains(@checked,'checked'))]");
	private static final By SAVE_BUTTON_ON_OVERLAY_LOC=By.xpath("//button[contains(text(),'Save')]");
	private static final By HEADER_TEXT_ON_BILLING_PAGE_LOC=By.xpath("//h4[contains(text(),'Billing Details')]");
	private static final By SUB_HEADER_TEXT_ON_BILLING_PAGE_LOC=By.xpath("//h4[contains(text(),'Billing Profiles')]");
	private static final By ADD_NEW_BILLING_PROFILE_LINK_LOC=By.xpath("//a[contains(text(),'Add a new billing profile')]");
	private static final By CARD_HOLDER_NAME_LOC=By.id("cardHolderName");
	private static final By CREDIT_CARD_NAME_LOC=By.id("creditCardNumber");
	private static final By MONTH_NAME_LOC=By.id("expMonth");
	private static final By CVV_LOC=By.id("cvv");
	private static final By EXPIRY_YEAR_LOC=By.id("expYear");
	private static final By CHECKBOX_FOR_NON_SUBSCRIBERS_ORDERS_LOC=By.xpath("//div[contains(text(),'Use this billing profile for future non-subscription orders')]");
	private static final By CHECKBOX_FOR_SUBSCRIBERS_ORDERS_LOC=By.xpath("//div[contains(text(),'Use this billing profile for future subscription orders (CRP)')]");
	private static final By CHECKBOX_FOR_PULSE_PRO_SUBSCRIPTION_LOC=By.xpath("//*[contains(text(),'User this billing profile for my monthly PULSE Pro subscription')]");
	private static final By SAVE_BILLING_ADDRESS_BUTTON_LOC=By.id("saveBillingAddress");
	private static final By CANCEL_BILLING_ADDRESS_BUTTON_LOC=By.id("cancelButton");
	private static final By FULL_NAME_FIRST_SHIPPING_PROFILE=By.id("attentionLabel0");
	private static final By PHONE_NUMBER=By.id("phoneNumberLabel0");
	private static final By EDIT_BUTTON_FIRST_BILLING_PROFILE =By.xpath(".//*[@id='profileList']/descendant::a[contains(text(),'Edit')][1]");
	private static final By BILLING_PROFILE_EDIT = By.className("editButton");
	private static final By BILLING_PROFILE_UPDATION_MESSAGE_LOC=By.id("confirmationMessage");
	private static final By CREDIT_CARD_NUMBER_EXISTING_BILLING_PROFILE_LOC=By.xpath(".//*[@id='profileList']/descendant::span[contains(@id,'creditCardNumberLabel')][1]");
	private static final By EXPIRATION_DATE_ON_EXISTING_BILLING_PROFILE_LOC=By.xpath(".//*[@id='profileList']/descendant::span[contains(@id,'expDateLabel')][1]");
	private static final By USE_THIS_SHIPPING_ADDRESS_FOR_ALL_MY_FUTURE_ORDERS_INCLUDING_CRP_CHECKBOX_LOC=By.xpath("//div[contains(text(),'Use this shipping address for all my future orders, including CRP')]/preceding-sibling::input[@id='isDefault']");
	private static final By CANCEL_BUTTON_ON_OVERLAY_LOC=By.xpath("//button[contains(text(),'Cancel')]");
	private static final By FIRST_NON_DEFAULT_SHIPPING_OR_BILLING_PROFILE_NAME_LOC=By.xpath("//*[contains(@id,'isDefaultCheckbox')]/self::input[not(contains(@checked,'checked'))]/preceding-sibling::p/span[1]");
	private static final By DEFAULT_SHIPPING_OR_BILLING_PROFILE_NAME_LOC=By.xpath("//*[contains(@id,'isDefaultCheckbox')][@checked='checked']/preceding::*[contains(@id,'profileNameLabel')][1]");
	private static final By CHECKBOX_FOR_BILLING_PROFILE=By.xpath("//div[contains(text(),'Use this billing profile for all my future orders')]");	
	private static final By CARD_NUMBER_NON_EDITABLE=By.xpath("//input[@id='creditCardNumber'][@readonly='readonly']");
	private static final By BILLING_ADDRESS_1_LOC=By.id("address1");
	private static final By EXPIRY_MONTH_ERROR_LOC=By.id("expMonthError");
	private static final By EXPIRY_YEAR_ERROR_LOC=By.id("expYearError");
	private static final By ZIP_CODE_ERROR_LOC=By.id("zipCodeError");
	private static final By ADD_NEW_BILLING_ADDRESS_MANDATORY_FIELDS_ERRORS=By.xpath("//form[@id='addressForm']//input[contains(@placeholder,'*')]/following-sibling::p/span[contains(@style,'color: red;')]");
	private static final By SHIPPING_DETAILS_PROFILE1_EDIT_LINK=By.xpath("//div[@id='addressList']//a[@name='editButton']");	

	
	private String defaultBillingRadioButton = "//*[contains(text(),'%s')]/following::input[not(contains(@checked,'checked'))][1]";		
	private String expirationDateOfBillingOrShippingProfile = "//*[@id='profileList']/descendant::span[contains(@id,'profileNameLabel')][contains(text(),'%s')]/following::*[contains(@id,'expDateLabel')][1]";

	public int getTotalNumberofCheckBoxesOnAddBillingForm(){
		driver.waitForElementToBeVisible(By.xpath("//input[@type='checkbox']"), 10);
		return driver.findElements(By.xpath("//input[@type='checkbox']")).size();
	}

	public boolean isShippingProfilesPresent() {
		driver.waitForElementPresent(SHIPPING_PROFILES_LOC);
		return driver.isElementPresent(SHIPPING_PROFILES_LOC);
	}

	public int getShippingProfilesCount() {
		driver.waitForElementPresent(NUMBER_OF_SHIPPING_PROFILES_LOC);
		int totalShippingProfiles= driver.findElements(NUMBER_OF_SHIPPING_PROFILES_LOC).size();
		logger.info("total shipping profiles="+totalShippingProfiles);
		return totalShippingProfiles;
	}

	public void selectShippingOrBillingProfileAsDefault(String profileName) {
		driver.pauseExecutionFor(1000);
		driver.click(By.xpath(String.format(defaultBillingRadioButton, profileName)), "Default shipping or billing profile checkbox of profile ="+profileName);
	}

	public void selectShippingOrBillingProfileAsDefault() {
		driver.click(DEFAULT_SHIPPING_OR_BILLING_CHECKBOX_LOC, "Default shipping or billing profile checkbox of profile");
	}

	public boolean isOverlayPresentWithSpecifiedText() {
		return driver.isElementPresent(DEFAULT_SHIPPING_PROFILE_POPUP_TEXT_LOC);
	}

	public void clickSaveButtonOnOverlay() {
		driver.click(SAVE_BUTTON_ON_OVERLAY_LOC, "Save Button");
		driver.waitForElementToBeInVisible(By.id("loadingDiv"), 10);
		driver.pauseExecutionFor(1000);
	}

	public boolean isHeaderContainsBillingDetailsText() {
		return driver.isElementVisible(HEADER_TEXT_ON_BILLING_PAGE_LOC);
	}

	public boolean isSubHeaderContainsBillingDetailsText() {
		return driver.isElementVisible(SUB_HEADER_TEXT_ON_BILLING_PAGE_LOC);
	}

	public void clickAddNewBillingProfileLink() {
		driver.scrollDownIntoView(ADD_NEW_BILLING_PROFILE_LINK_LOC);
		driver.click(ADD_NEW_BILLING_PROFILE_LINK_LOC, "Add New Billing Profile");
	}

	public boolean isBillingProfileNamePresentOnPage() {
		return driver.isElementVisible(PROFILE_NAME_LOC);
	}

	public boolean isCardHolderNamePresentOnBillingPage() {
		return driver.isElementVisible(CARD_HOLDER_NAME_LOC);
	}

	public boolean isCreditCardPresentOnBillingPage() {
		return driver.isElementVisible(CREDIT_CARD_NAME_LOC);
	}

	public boolean isMonthNamePresentOnBillingPage() {
		return driver.isElementVisible(MONTH_NAME_LOC);
	}

	public boolean isCVVPresentOnBillingPage() {
		return driver.isElementVisible(CVV_LOC);
	}

	public boolean isExpiryYearPresentOnBillingPage() {
		return driver.isElementVisible(EXPIRY_YEAR_LOC);
	}

	public boolean isCheckboxForNonSubscriptionOrdersPresentOnBillingPage() {
		return driver.isElementVisible(CHECKBOX_FOR_NON_SUBSCRIBERS_ORDERS_LOC);
	}

	public boolean isCheckboxForSubscriptionOrdersPresentOnBillingPage() {
		return driver.isElementVisible(CHECKBOX_FOR_SUBSCRIBERS_ORDERS_LOC);
	}

	public boolean isCheckboxForPulseProSubscriptionOrdersPresentOnBillingPage() {
		return driver.isElementVisible(CHECKBOX_FOR_PULSE_PRO_SUBSCRIPTION_LOC);
	}

	public boolean isSaveButtonPresentOnBillingPage() {
		return driver.isElementVisible(SAVE_BILLING_ADDRESS_BUTTON_LOC);
	}

	public boolean isCancelButtonPresentOnBillingPage() {
		return driver.isElementVisible(CANCEL_BILLING_ADDRESS_BUTTON_LOC);
	}

	public boolean isCheckboxForBillingProfileForFutureOrdersPresentOnBillingPage() {
		return driver.isElementVisible(CHECKBOX_FOR_BILLING_PROFILE);
	}

	public void clickCheckboxForFutureAutoshipsAndOrdersBillingProfile(){
		driver.click(CHECKBOX_FOR_BILLING_PROFILE, "Checkbox For Future Autoships And Orders Billing Profile");
	}

	public void addBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(String profileName,String cardHolderName,String creditCardNumber, String monthName, String yearName, String CVV, String firstName, String lastName, String address1, String zipCode, String phoneNumber) {	
		checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox();
		addBillingDetailsAndSave(profileName, cardHolderName, creditCardNumber, monthName, yearName, CVV, firstName, lastName, address1, zipCode, phoneNumber);
	}

	public boolean isCheckboxForFutureOrdersDisplayed(){
		return driver.isElementVisible(USE_THIS_ADDRESS_FOR_ALL_FUTURE_ORDERS_INCLUDING_CRP_CHECKBOX_LOC, 10);
	}

	public void enterBillingProfileNameAndExpDateAndSave(String profileName,String monthName, String yearName){
		Select expMonth = new Select(driver.findElement(By.id("expMonth")));
		Select expYear = new Select(driver.findElement(By.id("expYear")));
		driver.type(PROFILE_NAME_LOC, profileName,""+profileName);
		expMonth.selectByValue(monthName);
		expYear.selectByVisibleText(yearName);
		driver.scrollDownIntoView(SAVE_BILLING_ADDRESS_BUTTON_LOC);
		driver.clickByJS(RFWebsiteDriver.driver,SAVE_BILLING_ADDRESS_BUTTON_LOC, "Save Button");
		driver.waitForPageLoad();
	}

	public int getBillingProfilesCount() {
		int totalBillingProfiles= driver.findElements(NUMBER_OF_Billing_PROFILES_LOC).size();
		logger.info("total billing profiles present on page ="+totalBillingProfiles);
		return totalBillingProfiles;
	}

	public String getFirstNonDefaultProfileName() {
		return driver.getText(FIRST_NON_DEFAULT_BILLING_OR_SHIPPING_PROFILE_NAME_LOC,"Non Default profile name");
	}

	/***
	 * This method is used to click on Edit button in Non-default shipping profile
	 * 
	 */
	public StoreFrontHeirloomBillingAndShippingProfilePage clickEditNonDefaultShippingProfile() {
		driver.click(EDIT_NON_DEFAULT_SHIPPING_PROFILE_LOC, "Edit Shipping Profile");
		return this;
	}

	/***
	 * This method is used to enter first and last name in existing shipping profile and click on save button
	 * 
	 * return StoreFrontHeirloomBillingAndShippingProfilePage class object
	 */
	public StoreFrontHeirloomBillingAndShippingProfilePage editFirstAndLastNameForExistingShippingProfile(String firstName, String lastName) {
		driver.type(FIRST_NAME_LOC, firstName,""+firstName);
		driver.type(LAST_NAME_LOC, lastName,""+lastName);
		driver.click(SAVE_BUTTON_LOC, "Save Button");
		driver.waitForPageLoad();
		return this;
	}

	/***
	 * This method is used to verify newly edited crp shipping address present on page or not
	 * 
	 * return boolean value
	 */
	public boolean isNewlyEditedShippingProfileAndMarkedAsDefaultCRPAddressPresentOnPage(String shippingFirstAndLastName) {
		driver.waitForElementPresent(By.xpath("//*[contains(text(),'Next CRP')]/following-sibling::span[contains(text(),'"+shippingFirstAndLastName+"')]"),20);
		return driver.isElementPresent(By.xpath("//*[contains(text(),'Next CRP')]/following-sibling::span[contains(text(),'"+shippingFirstAndLastName+"')]"));
	}

	/***
	 * This method is used to click on Edit button in shipping profile
	 * 
	 */
	public void clickOnEditBtnForFirstShippingProfile(){
		driver.click(EDIT_BUTTON_FIRST_SHIPPING_PROFILE, "Edit Button for first shipping profile");
	} 

	public List<String> getShippingDetailsForProfile1() {
		driver.waitForElementPresent(SHIPPING_DETAILS_PROFILE1_EDIT_LINK);	
		List<String> list=new ArrayList<String>();
		list.add(driver.getText(PROFILE1_NAME_LOC,"Profile name"));
		list.add(driver.getText(FULL_NAME_FIRST_SHIPPING_PROFILE,"First name and Last Name"));	
		list.add(driver.getText(PROFILE1_ADDRESS1_LOC,"Address"));
		return list;
	}

	public void editShippingDetailsForProfile1AndCancel(String profileName,String firstName, String lastName, String address1){
		driver.waitForElementPresent(SHIPPING_DETAILS_PROFILE1_EDIT_LINK);	
		driver.click(SHIPPING_DETAILS_PROFILE1_EDIT_LINK, "In Shipping details profile1 edit link has been clicked ");
		driver.waitForElementPresent(PROFILE_NAME_LOC);
		driver.type(PROFILE_NAME_LOC, profileName,"profileName");
		driver.type(FIRST_NAME_LOC, firstName,"firstName");
		driver.type(LAST_NAME_LOC, lastName,"lastName");
		driver.type(ADDRESS1_LOC, address1,"address1");
		driver.click( CANCEL_BUTTON_LOC, "Cancel Button");
		driver.waitForPageLoad();
	}

	/***
	 * This method is used get the first Name in the first ShippingProfile
	 * 
	 * 
	 */
	public String getFullNameInShippingProfile() {		
		return driver.getText(FULL_NAME_FIRST_SHIPPING_PROFILE);
	}

	public String getFullNameOfFirstNonDefaultShippingProfileName(){
		return driver.getText(FIRST_NON_DEFAULT_BILLING_OR_SHIPPING_PROFILE_NAME_LOC);
	}

	public String getBillingProfileName() {
		String profileName=driver.getText(PROFILE1_NAME_LOC);
		return profileName;
	}
	public Boolean clickEditButton() {
		String profileName=getBillingProfileName();
		driver.click(EDIT_BUTTON_FIRST_BILLING_PROFILE, "Edit Button first billing");
		String profileNameBilling=driver.getText(PROFILE_NAME_LOC);
		return profileName.equalsIgnoreCase(profileNameBilling);
	}

	/***
	 * This method is used to tell billing profile page is display or not
	 * 
	 * @return boolean 
	 */
	public boolean isOnBillingProfileDisplay() {		
		return driver.isElementVisible(SUB_HEADER_TEXT_ON_BILLING_PAGE_LOC);
	}

	/***
	 * This method is used to click on Edit button in billing profile
	 * 
	 */
	public void clickOnEditBtnInBillingProfiles(){
		driver.click(BILLING_PROFILE_EDIT, "Billing Edit Button");
	}

	public void clickEditForFirstBillingProfile() {
		driver.click(EDIT_BUTTON_FIRST_BILLING_PROFILE, "Edit Button first Billing Profile");
	}

	public boolean isCardNumberFieldNonEditable(){
		return driver.isElementPresent(CARD_NUMBER_NON_EDITABLE);
	}

	public void enterCreditCardNumber(String creditCardNumber) {
		driver.type(CREDIT_CARD_NAME_LOC, creditCardNumber);
	}

	public void selectExpiryMonthYearAndEnterCVV(String monthName, String yearName, String CVV) {
		Select expMonth = new Select(driver.findElement(By.id("expMonth")));
		Select expYear = new Select(driver.findElement(By.id("expYear")));
		expMonth.selectByValue(monthName);
		expYear.selectByVisibleText(yearName);
		driver.type(CVV_LOC, CVV,""+CVV);
	}

	public void clickSaveBillingAddressButton() {
		driver.clickByJS(RFWebsiteDriver.driver,SAVE_BILLING_ADDRESS_BUTTON_LOC, "Save Button");
		driver.pauseExecutionFor(2000);
		driver.waitForElementToBeInVisible(By.id("loadingDiv"), 10);
	}

	public String getBillingProfileUpdationMessage() {
		driver.waitForElementToBeVisible(BILLING_PROFILE_UPDATION_MESSAGE_LOC,10);
		return driver.getText(BILLING_PROFILE_UPDATION_MESSAGE_LOC);
	}

	public String getCreditCardNumberFromExistingBillingProfileName() {
		driver.waitForElementToBeVisible(CREDIT_CARD_NUMBER_EXISTING_BILLING_PROFILE_LOC,10);
		return driver.getText(CREDIT_CARD_NUMBER_EXISTING_BILLING_PROFILE_LOC);
	}

	public String getExpirationDateFromExistingBillingProfile(String profileName) {
		return driver.getText(By.xpath(String.format(expirationDateOfBillingOrShippingProfile, profileName)),"exp date of profile ="+profileName);
	}	

	public List<WebElement> clickAddANewShippingAddressLinkAndGetAllMandatoryFields(){
		driver.pauseExecutionFor(3000);
		driver.click(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC, "Add New Shipping Address");
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(SAVE_BUTTON_LOC);
		List<WebElement> listOfMandatoryFieldsInAddNewShippingAddress=driver.findElements(ADD_NEW_SHIPPING_ADDRESS_MANDATORY_FIELDS);		
		return listOfMandatoryFieldsInAddNewShippingAddress;		
	}

	public List<WebElement> getAllNonMandatoryFieldsInShippingDetailsPage(){

		List<WebElement> listOfNonMandatoryFieldsInAddNewShippingAddress=driver.findElements(ADD_NEW_SHIPPING_ADDRESS_NON_MANDATORY_FIELDS);
		return listOfNonMandatoryFieldsInAddNewShippingAddress;

	}

	public void fillZipCodeAndClickOnSaveBtn(String zipCode) {
		driver.waitForElementPresent(ZIP_CODE_LOC);
		driver.type(ZIP_CODE_LOC, zipCode,""+zipCode);
		driver.click(SAVE_BUTTON_LOC, "Save Button");
		driver.pauseExecutionFor(5000);
		driver.waitForPageLoad();

	}

	public List<WebElement> getCityAndState() {
		List<WebElement> listOfCityStateName=driver.findElements(ADD_NEW_SHIPPING_ADDRESS_NON_MANDATORY_FIELDS);
		driver.waitForElementPresent(CITY_LOC);
		driver.waitForElementPresent(STATE_LOC);
		listOfCityStateName.add(0,driver.findElement(CITY_LOC));
		listOfCityStateName.add(1,driver.findElement(STATE_LOC));	
		return listOfCityStateName;	
	}

	public boolean isSaveAndCancelBTNDisplayed() {			
		return driver.isElementVisible(SAVE_BUTTON_LOC) && driver.isElementVisible(CANCEL_BUTTON_LOC);
	}


	public void fillNewShippingAddressDetails(String profileName,String firstName, String lastName, String address1,String zipCode, String phoneNumber) {
		driver.waitForElementPresent(PROFILE_NAME_LOC);
		driver.type(PROFILE_NAME_LOC, profileName,"profileName");
		driver.type(FIRST_NAME_LOC, profileName,"firstName");
		driver.type(LAST_NAME_LOC, profileName,"lastName");
		driver.type(ADDRESS1_LOC, address1,"address1");
		driver.type(ZIP_CODE_LOC, zipCode,"zipCode");
		driver.click(PHONE_NUMBER_LOC, "phoneNumber");
		driver.pauseExecutionFor(3000);
		driver.type(PHONE_NUMBER_LOC, phoneNumber,""+phoneNumber);
	}

	public void clickSaveButtonInShippingPage(){
		driver.clickByJS(RFWebsiteDriver.driver,SAVE_BUTTON_LOC, "Save button");
		driver.waitForPageLoad();
	}

	public void clickCancelButtonInShippingPage(){
		driver.click(CANCEL_BUTTON_LOC, "Cancel button");
		driver.waitForPageLoad();
	}

	public void clickAddNewShippingAddressLink(){
		driver.click(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC, "Add New Shipping Address");
	}	

	/***
	 * This method is used to verify whether Shipping profile updated Successfully message is present or not
	 * returns true if Shipping profile updated Successfully message is present
	 */	
	public boolean isShippingProfileUpdatedSuccessfullyMessagePresent(){
		driver.waitForElementPresent(SHIPPING_PROFILE_UPDATED_SUCCESSFULLY_MSG);
		return driver.isElementVisible(SHIPPING_PROFILE_UPDATED_SUCCESSFULLY_MSG);
	}

	/***
	 * This method is used to fetch Error Messages For All the mandatory fields from the form(Shipping details Page)
	 * returns A List of WebElements containing Error messages For All the mandatory fields from the form
	 */	
	public List<WebElement> getErrorMessagesForAllMandatoryFields(){
		driver.pauseExecutionFor(3000);
		driver.click(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC, "Add New Shipping Address");
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(SAVE_BUTTON_LOC);
		List<WebElement> listOfMandatoryFieldsInAddNewShippingAddressErrors=driver.findElements(ADD_NEW_SHIPPING_ADDRESS_MANDATORY_FIELDS_ERRORS);		
		return listOfMandatoryFieldsInAddNewShippingAddressErrors;		
	}	

	/***
	 * This method is used to verify whether newly created shipping profile is present or not
	 * returns true if newly created shipping profile is present 
	 */		
	public boolean isNewlyCreatedShippingProfilePresentOnEditCRPCartLinkPage(String shippingProfileName) {
		return driver.isElementVisible(By.xpath("//p[contains(text(),'"+shippingProfileName+"')]"));
	}

	public boolean isCRPAndNonCRPCheckBoxWithRequiredTextDisplayed(){
		return driver.isElementVisible(USE_THIS_SHIPPING_ADDRESS_FOR_ALL_MY_FUTURE_ORDERS_INCLUDING_CRP_CHECKBOX_LOC);
	}

	/***
	 * This method is used to enter first and last name in shipping profile and select checkbox for future autoship CRP
	 * 
	 * return StoreFrontHeirloomBillingAndShippingProfilePage class object
	 */
	public StoreFrontHeirloomBillingAndShippingProfilePage editFirstAndLastNameInShippingProfileAndCheckForFutureAutoshipCRP(String firstName, String lastName) {
		driver.type(FIRST_NAME_LOC, firstName,""+firstName);
		driver.type(LAST_NAME_LOC, lastName,""+lastName);
		driver.waitForElementPresent(USE_THIS_SHIPPING_ADDRESS_FOR_ALL_MY_FUTURE_ORDERS_INCLUDING_CRP_CHECKBOX_LOC);
		driver.click(USE_THIS_SHIPPING_ADDRESS_FOR_ALL_MY_FUTURE_ORDERS_INCLUDING_CRP_CHECKBOX_LOC, "Use this shipping address for future orders including CRP Checkbox");
		driver.clickByJS(RFWebsiteDriver.driver,SAVE_BUTTON_LOC, "Save Button");
		driver.waitForPageLoad();
		return this;
	}

	/***
	 * This method is used to get the default shipping/Billing profile name 
	 * 
	 * returns String containing Default shipping/Billing profile name  
	 */
	public String getDefaultShippingOrBillingProfileName() {
		return driver.getText(DEFAULT_SHIPPING_OR_BILLING_PROFILE_NAME_LOC,"default billing/shipping profile name");
	}

	/***
	 * This method is used to get the 1st non default shippping profile name 
	 * 
	 * returns String containing Non Default shipping profile name  
	 */
	public String getFirstNonDefaultShippingProfileName() {
		return driver.getText(FIRST_NON_DEFAULT_SHIPPING_OR_BILLING_PROFILE_NAME_LOC);
	}

	/***
	 * This method is used to verify whether save button is present or not on the alert when a shipping detail is selected as default 
	 * 
	 * returns true when save button is present on the alert 
	 */
	public boolean isSaveButtonOnOverlayDisplayed() {
		return driver.isElementPresent(SAVE_BUTTON_ON_OVERLAY_LOC);
	}

	/***
	 * This method is used to verify whether cancel button is present or not on the alert when a shipping detail is selected as default 
	 * 
	 * returns true when cancel button is present on the alert 
	 */
	public boolean isCancelButtonOnOverlayDisplayed() {
		return driver.isElementPresent(CANCEL_BUTTON_ON_OVERLAY_LOC);
	}

	/***
	 * This method is used to click cancel button on the alert when a shipping detail is selected as default 
	 * 
	 * returns void
	 */
	public void clickCancelButtonOnOverlay() {
		driver.click(CANCEL_BUTTON_ON_OVERLAY_LOC,"Cancel button On Overlay");
		driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
	}

	/***
	 * This method is used to verify whether given header text is present or not on the alert when a shipping detail is selected as default 
	 * 
	 * returns true when the given header text is present on the alert 
	 */
	public boolean isHeaderTextOnAlertWhenSelectingShippingDetailsAsDefaultDisplayed() {
		return driver.isElementVisible(HEADER_TEXT_ON_ALERT_WHEN_SELECT_SHIPPING_DETAILS_AS_DEFAULT);
	}

	/***
	 * This method is used to click Use this  address for all future orders including CRP checkbox in the form
	 * returns this
	 */	
	public StoreFrontHeirloomBillingAndShippingProfilePage checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox(){
		driver.pauseExecutionFor(1000);
		//driver.scrollUpIntoView(SAVE_SHIPPING_ADDRESS_BUTTON_LOC);
		driver.click(USE_THIS_ADDRESS_FOR_ALL_FUTURE_ORDERS_INCLUDING_CRP_CHECKBOX_LOC, "Use This Address For All Future Orders Including CRP ChkBox");	
		return this;	
	} 

	public boolean isNextCRPShippingAddressUpdated(String shippingProfileName) {

		return driver.isElementVisible(By.xpath("//strong[contains(text(),'Next CRP')]/following-sibling:://span[contains(text(),'"+shippingProfileName+"')]"));
	}



	/***
	 * This method is used to fetch Error Messages For All the mandatory fields from the form(Billing details Page)
	 * returns A List of WebElements containing Error messages For All the mandatory fields from the form
	 */	
	public List<WebElement> getErrorMessagesForAllMandatoryFieldsInBilling(){

		List<WebElement> listOfMandatoryFieldsInAddNewBillingAddressErrors=driver.findElements(ADD_NEW_BILLING_ADDRESS_MANDATORY_FIELDS_ERRORS);		
		return listOfMandatoryFieldsInAddNewBillingAddressErrors;		
	}	


	public boolean isBillingAddress1Displayed(){
		return driver.isElementVisible(BILLING_ADDRESS_1_LOC, 10);
	}

	public String getBillingAddress1Displayed() {
		return driver.getAttribute(BILLING_ADDRESS_1_LOC,"value");

	}

	public String getCreditCardPresentOnBillingPage() {
		return driver.getAttribute(CREDIT_CARD_NAME_LOC,"value");
	}

	public WebElement getMonthNamePresentOnBillingPage() {
		Select s=new Select(driver.findElement(MONTH_NAME_LOC));
		return s.getFirstSelectedOption();
	}

	public WebElement getExpiryYearPresentOnBillingPage() {
		Select s=new Select(driver.findElement(EXPIRY_YEAR_LOC));
		return s.getFirstSelectedOption();

	}	

	public String getCVVPresentOnBillingPage() {
		return driver.getAttribute(CVV_LOC,"value");
	}

	public boolean isErrorMessagesForMonthFieldPresent(){
		return driver.isElementVisible(EXPIRY_MONTH_ERROR_LOC);		
	}	

	public boolean isErrorMessagesForYearFieldPresent(){
		return driver.isElementVisible(EXPIRY_YEAR_ERROR_LOC);	
	}	

	public boolean isErrorMessagesForZIPCodePresent(){
		return driver.isElementVisible(ZIP_CODE_ERROR_LOC);	
	}	
	

	public void editBillingDetailsWithoutCheckTheCheckboxForFutureOrdersAndSave(String profileName, String CVV) {	
		driver.type(PROFILE_NAME_LOC, profileName,"profileName");
		driver.type(FIRST_NAME_LOC, profileName,"firstName");
		driver.type(LAST_NAME_LOC, profileName,"lastName");
		Select expMonth = new Select(driver.findElement(By.id("expMonth")));
		Select expYear = new Select(driver.findElement(By.id("expYear")));
		expMonth.selectByIndex(4);
		expYear.selectByIndex(4);
		//driver.type(CVV_LOC, CVV,""+CVV);
		driver.clickByJS(RFWebsiteDriver.driver,SAVE_BILLING_ADDRESS_BUTTON_LOC, "Save Button");
		driver.waitForPageLoad();
	}
	
public void editBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(String profileName, String CVV) {	
		checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox();
		driver.type(PROFILE_NAME_LOC, profileName,"profileName");
		driver.type(FIRST_NAME_LOC, profileName,"firstName");
		driver.type(LAST_NAME_LOC, profileName,"lastName");
		Select expMonth = new Select(driver.findElement(By.id("expMonth")));
		Select expYear = new Select(driver.findElement(By.id("expYear")));
		expMonth.selectByIndex(4);
		expYear.selectByIndex(4);
	//	driver.type(CVV_LOC, CVV,""+CVV);
		driver.clickByJS(RFWebsiteDriver.driver,SAVE_BILLING_ADDRESS_BUTTON_LOC, "Save Button");
		driver.waitForPageLoad();
	}	
	
public boolean isNewlyBillingOrShippingProfileSelectedAsDefault(String profileName) {
	driver.pauseExecutionFor(2000);
	return driver.isElementVisible(By.xpath("//span[contains(text(),'"+profileName+"')]/following::*[contains(@id,'isDefaultCheckbox')][@checked='checked']"));
}	


public void addNewShippingAddressDetailsFromShippingPageWithCheckboxCheckedAndSave(String profileName,String firstName, String lastName, String address1,String zipCode, String phoneNumber) {
	driver.scrollDownIntoView(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC);
	driver.click(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC, "Add New Shipping Address");
	driver.type(PROFILE_NAME_LOC, profileName,"profileName");
	driver.type(FIRST_NAME_LOC, firstName,"firstName");
	driver.type(LAST_NAME_LOC, lastName,"lastName");
	driver.type(ADDRESS1_LOC, address1,"address1");
	driver.type(ZIP_CODE_LOC, zipCode+"\t","zipCode");
	driver.click(PHONE_NUMBER_LOC, "phoneNumber");
	driver.type(PHONE_NUMBER_LOC, phoneNumber,"phoneNumber");
	checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox();
	driver.clickByJS(RFWebsiteDriver.driver,SAVE_BUTTON_LOC, "Save Button");
	driver.waitForPageLoad();
	driver.pauseExecutionFor(3000);
}


public void addNewShippingAddressDetailsFromShippingPageAndCancel(String profileName,String firstName, String lastName, String address1,String zipCode, String phoneNumber) {
	driver.scrollDownIntoView(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC);
	driver.click(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC, "Add New Shipping Address");
	driver.type(PROFILE_NAME_LOC, profileName,"profileName");
	driver.type(FIRST_NAME_LOC, firstName,"firstName");
	driver.type(LAST_NAME_LOC,lastName,"lastName");
	driver.type(ADDRESS1_LOC, address1,"address1");
	driver.type(ZIP_CODE_LOC, zipCode+"\t","zipCode");
	driver.click(PHONE_NUMBER_LOC, "phoneNumber");
	driver.type(PHONE_NUMBER_LOC, phoneNumber,"phoneNumber");
	driver.click(CANCEL_BILLING_ADDRESS_BUTTON_LOC, "cancel Button");
	driver.waitForPageLoad();
}

public boolean isNewlyCreatedShippingProfilePresent(String shippingProfileName) {
	return driver.isElementVisible(By.xpath("//span[contains(text(),'"+shippingProfileName+"')] | //p[contains(text(),'"+shippingProfileName+"')]"));
}


public void addBillingDetailsAndSave(String profileName,String cardHolderName,String creditCardNumber, String monthName, String yearName, String CVV, String firstName, String lastName, String address1, String zipCode, String phoneNumber) {	
	Select expMonth = new Select(driver.findElement(By.id("expMonth")));
	Select expYear = new Select(driver.findElement(By.id("expYear")));
	driver.type(PROFILE_NAME_LOC, profileName,"profileName");
	driver.type(CARD_HOLDER_NAME_LOC, cardHolderName,"cardHolderName");
	driver.type(CREDIT_CARD_NAME_LOC, creditCardNumber,"creditCardNumber");
	expMonth.selectByValue(monthName);
	expYear.selectByVisibleText(yearName);
	driver.type(CVV_LOC, CVV,""+CVV);
	driver.type(FIRST_NAME_LOC, firstName,"firstName");
	driver.type(LAST_NAME_LOC, lastName,"lastName");
	driver.type(ADDRESS1_LOC, address1,"address1");
	driver.type(ZIP_CODE_LOC, zipCode,"zipCode");
	driver.pauseExecutionFor(3000);
	driver.clickByJS(RFWebsiteDriver.driver,PHONE_NUMBER_LOC, "");
	driver.pauseExecutionFor(3000);
	driver.type(PHONE_NUMBER_LOC, phoneNumber,"phoneNumber");
	driver.pauseExecutionFor(1000);
	driver.clickByJS(RFWebsiteDriver.driver,SAVE_BILLING_ADDRESS_BUTTON_LOC, "Save Button");
	driver.waitForElementToBeInVisible(SAVE_BILLING_ADDRESS_BUTTON_LOC, 10);
	driver.pauseExecutionFor(1000);
	driver.waitForPageLoad();
} 

public void addBillingDetailsAndCancel(String profileName,String cardHolderName,String creditCardNumber, String monthName, String yearName, String CVV, String firstName, String lastName, String address1, String zipCode, String phoneNumber) {	
	Select expMonth = new Select(driver.findElement(By.id("expMonth")));
	Select expYear = new Select(driver.findElement(By.id("expYear")));
	driver.type(PROFILE_NAME_LOC, profileName,"profileName");
	driver.type(CARD_HOLDER_NAME_LOC, cardHolderName,"cardHolderName");
	driver.type(CREDIT_CARD_NAME_LOC, creditCardNumber,"creditCardNumber");
	expMonth.selectByValue(monthName);
	expYear.selectByVisibleText(yearName);
	driver.type(CVV_LOC, CVV,""+CVV);
	driver.type(FIRST_NAME_LOC, firstName,"firstName");
	driver.type(LAST_NAME_LOC, lastName,"lastName");
	driver.type(ADDRESS1_LOC, address1,"address1");
	driver.type(ZIP_CODE_LOC, zipCode,"zipCode");
	driver.pauseExecutionFor(1000);
	driver.scrollUpIntoView(PHONE_NUMBER_LOC);
	driver.clickByJS(RFWebsiteDriver.driver,PHONE_NUMBER_LOC, "");
	driver.pauseExecutionFor(3000);
	driver.type(PHONE_NUMBER_LOC, phoneNumber,"phoneNumber");
	driver.click(CANCEL_BILLING_ADDRESS_BUTTON_LOC, "cancel Button");
	driver.waitForPageLoad();
} 

public void addNewShippingAddressDetailsFromShippingPageAndSave(String profileName,String firstName, String lastName, String address1,String zipCode, String phoneNumber) {
	driver.scrollDownIntoView(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC);
	driver.click(ADD_NEW_SHIPPING_ADDRESS_LINK_LOC, "Add New Shipping Address");
	driver.type(PROFILE_NAME_LOC, profileName,"profileName");
	driver.type(FIRST_NAME_LOC, firstName,"firstName");
	driver.type(LAST_NAME_LOC, lastName,"lastName");
	driver.type(ADDRESS1_LOC, address1,"address1");
	driver.type(ZIP_CODE_LOC, zipCode+"\t","zipCode");
	driver.click(PHONE_NUMBER_LOC, "phoneNumber");
	driver.type(PHONE_NUMBER_LOC, phoneNumber,"phoneNumber");
	driver.pauseExecutionFor(3000);
	driver.clickByJS(RFWebsiteDriver.driver,SAVE_BUTTON_LOC, "Save Button");
	driver.waitForPageLoad();
	driver.pauseExecutionFor(3000);
}



}
