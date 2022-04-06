package com.rf.pages.website.heirloom;

import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;

public class StoreFrontHeirloomConsultantPage extends StoreFrontHeirloomWebsiteBasePage{

	private static final By I_WANT_MY_FIRST_CRP_ORDER_TO_SHIP_NEXT_MONTH_LOC=By.xpath("//*[contains(text(),'I want my first CRP order to ship next month.')]/preceding::input[1]");
	private static final By CONTINUE_BUTTON_LOC=By.xpath("//input[contains(@value,'Continue')]");
	private static final By ADD_FIRST_PRODUCT_TO_CART_LOC=By.xpath("//div[@class='FloatCol']/descendant::a[contains(text(),'Add to Bag')][1]");
	private static final By CONTINUE_BUTTON_ON_CART_PAGE_LOC=By.xpath("//input[contains(@id,'uxReplenishmentSetup_uxContinue2')]");
	private static final By CRP_POLICY_AND_PROCEDURE_CHECKBOX_LOC=By.xpath("//input[contains(@id,'uxAgreePolicies')]");
	private static final By CONTINUE_BUTTON_ON_CRP_ENROLLMENT_PAGE_LOC=By.xpath("//*[contains(text(),'Continue')]");
	private static final By COMPLETE_CRP_ENROLLMENT_BUTTON=By.xpath("//*[contains(@value,'Complete CRP Enrollment')]");
	private static final By CRP_ENROLLMENT_CONGRATULATION_MESSAGE_LOC=By.xpath("//*[contains(text(),'Congratulations on committing yourself to success with CRP.')]");

	private static final By CHECK_MY_PULSE_LOC=By.xpath(".//*[@id='myaccountoptions']//a[contains(text(),'Check My Pulse')]");
	private static final By SUBSCRIBE_TO_PULSE_PRO_BUTTON_LOC=By.id("enroll-in-pulse-pro");
	private static final By LEGACY_PULSE_IMAGE_LOC=By.xpath("//span[contains(@class,'PulseScreen')]");

	private static final By CANCEL_MY_CRP_BUTTON_LOC=By.xpath("//a[contains(text(),'Cancel my CRP')]");
	private static final By CANCEL_CRP_POPUP_WITH_TEXT_LOC=By.xpath(".//*[@id='cancel-crp-overlay']//p[contains(text(),'Are you sure you want to deactivate your CRP account?')]");
	private static final By CONFIRM_CANCEL_CRP_BUTTON_LOC=By.id("cancelCRP");
	private static final By CRP_TERMS_AND_CONDITION_LOC=By.xpath("//a[contains(text(),'CRP Terms & Conditions')]");
	private static final By CRP_OPTIONAL_TEXT_LOC=By.xpath("//p[contains(text(),'CRP is an optional monthly subscription service')]");
	private static final By ENROLL_IN_CRP_BUTTON_LOC=By.id("enroll-in-crp");

	private static final By ALERT_NO_DEACTIVATE_CRP_BTN=By.xpath("//button[@id='cancelCRP']/preceding-sibling::button[@class='cancel-button']");
	private static final By CANCEL_MY_PULSE_PRO_SUBSCRIPTION_LINK=By.id("cancel-pulse");
	private static final By CANCEL_PULSE_SUBSCRIPTION_POPUP_WITH_TEXT_LOC=By.xpath("//*[@id='cancel-pulse-overlay']//p[contains(text(),'Are you sure you want to cancel the PULSE Subscription?')]");
	private static final By ALERT_YES_DEACTIVATE_PULSE_SUBSCRIPTION_BTN=By.id("cancelPulse");
	private static final By SUBSCRIBE_TO_PULSE_PRO_BTN=By.id("enroll-in-pulse-pro");

	private static final By ALERT_NO_DEACTIVATE_PULSE_SUBSCRIPTION_BTN=By.xpath("//button[@id='cancelPulse']/preceding-sibling::button[@class='cancel-button']");
	private static final By I_WANT_MY_FIRST_ORDER_TO_SHIP_IMMEDIATELY_CHkBox=By.xpath("//input[contains(@id,'uxTodayRadio')]");
	private static final By I_WANT_MY_FIRST_ORDER_TO_SHIP_NEXT_MONTH_CHkBox=By.xpath("//input[contains(@id,'uxNextMonthRadio')]");
	private static final By SUBSCRIBE_TO_PULSE_PRO_PULSE_INTRO_PAGE=By.xpath("//p[contains(text(),'Would you like to subscribe now?')]/following-sibling::p[@class='FormButtons']");
	private static final By EDIT_YOUR_PWS_TEXT_ON_EDITPWS_PAGE=By.xpath("//div[@id='Container']//h1[text()='You are Now Editing Your PWS']");

	private static final By NEXT_BILLING_SHIP_DATE=By.xpath("//p[contains(text(),'Next Billing Ship Date:')]/parent::div/following-sibling::div/p");
	private static final By VIEW_DETAILS_LINK=By.id("view-details");

	private static final By PULSE_PRO_TERMS_AND_CONDITION_LOC=By.xpath("//a[contains(text(),'PULSE Pro Terms and Conditions') or contains(text(),'Pulse Pro Terms and Conditions')]");
	private static final By HEADER_COLUMN_ITEMS_IN_VIEW_DETAILS_PAGE=By.xpath("//div[@id='HeaderCol']//a");	
	private static final By NEXT_BILL_DATE=By.xpath("//p[contains(text(),'Next Bill Date:')]/parent::div/following-sibling::div/p");
	private static final By ASTERIK_COPY_ON_MANAGE_CRP_AND_PULSE_PRO_PAGE=By.xpath("//div[@id='subscription-status']/following-sibling::p[normalize-space('*As a participant in CRP, you understand and agree that your credit card will be charged a recurring monthly amount equal to the sum of the prices of the products in your order, plus shipping charges.In order to remain in CRP, your monthly automatic orders must each include a minimum of 100 Qualifying Volume (QV).If you are subscribed to PULSE Pro, you must include a minimum of 80 QV to remain in CRP.You may cancel or edit your CRP order up to one day prior to shipment by logging into your account.**By subscribing to PULSE Pro, you understand and agree that your credit card will be charged a recurring monthly fee of $24.95 unless and until you cancel your subscription. You understand that you may cancel your PULSE Pro subscription up to one day prior to your scheduled billing date by logging into your account.')]");
	private static final By PULSE_PRO_OPTIONAL_TEXT_LOC=By.xpath("//div[@id='subscription-status']/following-sibling::div//p[@class='enrollment-text-spacing' and normalize-space('Take charge of your business with PULSE, our Consultant business management suite.PULSE is a web-based tool accessible from desktop browsers and mobile devices.')]");
	private static final By LEARN_MORE_ABOUT_PULSE_PRO_LINK=By.id("learn-about-pulse-pro");
	private static final By LEARN_MORE_ABOUT_PULSE_PRO_LINK_MODAL_HEADLINE=By.xpath("//div[@id='learn-about-pulse-pro-overlay']//h4[normalize-space('PULSE PRO, the Rodan + Fields web-based advanced business management suite, provides you with tools to manage and build your business on the go.')]");
	private static final By LEARN_MORE_ABOUT_PULSE_PRO_LINK_MODAL_BODY_LINK=By.xpath("//div[@id='learn-about-pulse-pro-overlay']//div[@class='overlay-body']/p/a[text()='PULSE Terms & Conditions']");
	private static final By LEARN_MORE_ABOUT_PULSE_PRO_LINK_MODAL_CLOSE_ICON=By.xpath("//div[@id='learn-about-pulse-pro-overlay']/span");

	private static final By MY_ACCOUNT_EMAIL_ADDRESS_TXTFIELD=By.id("emailAddress");	
	private static final By MY_ACCOUNT_SAVE_BTN=By.id("saveAccountInfo");
	private static final By MY_ACCOUNT_EMAIL_IS_REQUIRED_ERROR_LABEL=By.xpath("//span[@id='emailAddressError' and text()='* Email is required.']");
	private static final By MY_ACCOUNT_PLEASE_ENTER_A_VALID_EMAIL_ADDRESS_ERROR_LABEL=By.xpath("//span[@id='emailAddressError' and text()='* Please enter a valid e-mail address']");

	private static final By MY_ACCOUNT_OLD_PASSWORD_TXTFIELD=By.id("currentPassword");
	private static final By MY_ACCOUNT_PLEASE_ENTER_YOUR_CURRENT_PASSWORD_ERROR_LABEL=By.xpath("//span[@id='currentPasswordError' and text()='* Please enter your current password']");
	private static final By MY_ACCOUNT_NEW_PASSWORD_TXTFIELD=By.id("newPassword");
	private static final By MY_ACCOUNT_NEW_PASSWORD_IS_REQUIRED_ERROR_LABEL=By.xpath("//span[@id='newPasswordError' and text()='* New password is required.']");

	private static final By MY_ACCOUNT_CONFIRM_NEW_PASSWORD_TXTFIELD=By.id("confirmPassword");
	private static final By MY_ACCOUNT_PLEASE_REENTER_YOUR_NEW_PASSWORD_ERROR_LABEL=By.xpath("//span[@id='confirmPasswordError' and text()='* Please re-enter your new password']");
	private static final By PULSE_PRO_SUBSCRIPTION_STATUS = By.xpath(".//*[@id='subscription-status']/h4");

	public StoreFrontHeirloomConsultantPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LogManager
			.getLogger(StoreFrontHeirloomConsultantPage.class.getName());


	public void hoverOnBeAConsultantAndClickLinkOnEnrollMe(){
		driver.pauseExecutionFor(2000);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[text()= 'BECOME A CONSULTANT']"))).build().perform();
		logger.info("hover on Products link now as shop skincare");
		driver.click(By.xpath("//span[text()= 'Enroll Now']"),"Enroll Now");
		// logger.info("Clicked "+link+" link is clicked after hovering shop skincare.");
		driver.waitForPageLoad();
	}

	/***
	 * This method is used to verify Legacy Pulse image is displayed on Page or not
	 * 
	 *  return boolean value
	 */
	public boolean isLegacyPulseImageDisplayedOnPulseSubscriptionPage() {
		return driver.isElementVisible(LEGACY_PULSE_IMAGE_LOC);
	}	

	/***
	 * This method is used to click on 'Cancel My CRP' Button
	 * 
	 *  return StoreFrontHeirloomConsultantPage class object
	 */
	public StoreFrontHeirloomConsultantPage clickCancelMyCRPButton() {
		driver.waitForElementPresent(CANCEL_MY_CRP_BUTTON_LOC);
		driver.click(CANCEL_MY_CRP_BUTTON_LOC, "Cancel My CRP Button");
		driver.pauseExecutionFor(3000);
		return this;
	}

	public boolean isCancelCRPPopupDisplayed() {
		driver.waitForElementPresent(CANCEL_CRP_POPUP_WITH_TEXT_LOC);
		return driver.isElementVisible(CANCEL_CRP_POPUP_WITH_TEXT_LOC);
	}

	public boolean isCRPTermsAndConditionsDisplayedOnPage() {
		return driver.isElementVisible(CRP_TERMS_AND_CONDITION_LOC);
	}

	public boolean clickCRPTermsAndConditionAndVerifyOpenInAnotherTab(String urlString) {
		String currentURL=null;
		Boolean flag;
		String parentWindow=driver.getWindowHandle();
		driver.click(CRP_TERMS_AND_CONDITION_LOC, "CRP Terms and Condition");
		driver.pauseExecutionFor(5000);
		driver.switchToChildWindow(parentWindow);
		driver.pauseExecutionFor(5000);
		currentURL = driver.getCurrentUrl();
		flag=currentURL.contains(urlString);
		closeChildAndSwitchToParentWindow(parentWindow);
		driver.pauseExecutionFor(3000);
		return flag;
	}	 

	public boolean isCRPOptionalTextDisplayed() {
		driver.waitForElementPresent(CRP_OPTIONAL_TEXT_LOC);
		return driver.isElementVisible(CRP_OPTIONAL_TEXT_LOC);
	}

	public boolean isEnrollInCRPButtonDisplayed() {
		driver.waitForElementPresent(ENROLL_IN_CRP_BUTTON_LOC);
		return driver.isElementVisible(ENROLL_IN_CRP_BUTTON_LOC);
	}

	/***
	 * This method is used to verify whether Subscribe To Pulse Pro Button is displayed or not
	 * 
	 *  return true if Subscribe To Pulse Pro Button is displayed else false
	 */
	public boolean isSubscribeToPulseProButtonDisplayed() {
		driver.waitForElementPresent(SUBSCRIBE_TO_PULSE_PRO_BTN);
		return driver.isElementVisible(SUBSCRIBE_TO_PULSE_PRO_BTN);
	}

	/***
	 * This method is used to verify whether Cancel Pulse Subscription Alert is displayed or not
	 * 
	 *  return true if Cancel Pulse Subscription Alert is displayed else false
	 */
	public boolean isCancelPulseSubscriptionPopupDisplayed() {
		driver.waitForElementPresent(CANCEL_PULSE_SUBSCRIPTION_POPUP_WITH_TEXT_LOC);
		return driver.isElementVisible(CANCEL_PULSE_SUBSCRIPTION_POPUP_WITH_TEXT_LOC);
	}

	/***
	 * This method is used to verify whether 'Cancel My CRP' Link is displayed or not
	 * 
	 *  return true if 'Cancel My CRP' Link is displayed
	 */
	public boolean isCancelMyCRPLinkDisplayed() {
		driver.waitForElementPresent(CANCEL_MY_CRP_BUTTON_LOC);
		driver.pauseExecutionFor(3000);
		return driver.isElementVisible(CANCEL_MY_CRP_BUTTON_LOC);
	}

	/***
	 * This method is used to click No Button in the Cancel My CRP Alert
	 * 
	 *  return void
	 */
	public void clickNoOnCancelCRPPopup() {
		driver.waitForElementPresent(ALERT_NO_DEACTIVATE_CRP_BTN);
		driver.click(ALERT_NO_DEACTIVATE_CRP_BTN, "No Button On Cancel CRP Popup");
	}

	/***
	 * This method is used to verify whether 'Subscribe To Pulse Pro'(Pulse Intro) Page is dispalyed
	 * 
	 *  return true if 'Subscribe To Pulse Pro'(Pulse Intro) Page is dispalyed else false
	 */
	public boolean isPulseIntroPageDisplayed() {
		driver.waitForElementPresent(SUBSCRIBE_TO_PULSE_PRO_PULSE_INTRO_PAGE);
		return driver.isElementVisible(SUBSCRIBE_TO_PULSE_PRO_PULSE_INTRO_PAGE);
	}


	/***
	 * This method is used to verify whether 'I Want My First Order To Ship Immediately' CheckBox is present
	 * 
	 *  return true if 'I Want My First Order To Ship Immediately' CheckBox is present else false
	 */
	public boolean isIWantMyFirstOrderToShipImmediatelyChkBoxDisplayed() {
		driver.waitForElementPresent(I_WANT_MY_FIRST_ORDER_TO_SHIP_IMMEDIATELY_CHkBox);
		return driver.isElementVisible(I_WANT_MY_FIRST_ORDER_TO_SHIP_IMMEDIATELY_CHkBox);
	}

	/***
	 * This method is used to verify whether 'I Want My First Order To Ship Immediately' CheckBox is present
	 * 
	 *  return true if 'I Want My First Order To Ship Immediately' CheckBox is present else false
	 */
	public boolean isIWantMyFirstOrderToShipNextMonthChkBoxDisplayed() {
		driver.waitForElementPresent(I_WANT_MY_FIRST_ORDER_TO_SHIP_NEXT_MONTH_CHkBox);
		return driver.isElementVisible(I_WANT_MY_FIRST_ORDER_TO_SHIP_NEXT_MONTH_CHkBox);
	}	

	/***
	 * This method is used to click on 'Yes' button in the Cancel Pulse Subscription Alert
	 * 
	 *  return void
	 */
	public void clickNoOnCancelPulseSubscriptionPopup() {
		driver.click(ALERT_NO_DEACTIVATE_PULSE_SUBSCRIPTION_BTN, "No Button On Cancel Pulse Subscription Popup");
	}

	/***
	 * This method is used to verify whether 'Cancel My CRP' Link is displayed or not
	 * 
	 *  return true if 'Cancel My CRP' Link is displayed
	 */
	public boolean isCancelMyPULSEProSubscriptionLinkDisplayed() {
		driver.waitForElementPresent(CANCEL_MY_PULSE_PRO_SUBSCRIPTION_LINK);
		driver.pauseExecutionFor(3000);
		return driver.isElementVisible(CANCEL_MY_PULSE_PRO_SUBSCRIPTION_LINK);
	}

	/***
	 * This method is used to click Enroll in CRP Button
	 *  return void
	 */
	public void clickEnrollInCRPButton() {
		driver.waitForElementPresent(ENROLL_IN_CRP_BUTTON_LOC);
		driver.click(ENROLL_IN_CRP_BUTTON_LOC,"Enroll In CRP Btn");
		driver.pauseExecutionFor(5000);
	}

	/***
	 * This method is used to verify whether Edit PWS Page is Displayed or Not
	 * 
	 *  return true if 'YOU ARE NOW EDITING YOUR PWS' Text is being displayed
	 */
	public boolean isEditPWSPageDisplayed() {

		driver.pauseExecutionFor(5000);
		return driver.isElementVisible(EDIT_YOUR_PWS_TEXT_ON_EDITPWS_PAGE);
	}

	/***
	 * This method is used to verify whether View Details Link is displayed on Page or not
	 * 
	 *  return boolean
	 */
	public boolean isViewDetailsLinkDisplayed() {
		return driver.isElementVisible(VIEW_DETAILS_LINK);
	}	

	public void clickYesOnCancelCRPPopup() {
		driver.click(CONFIRM_CANCEL_CRP_BUTTON_LOC, "Yes Button On Cancel CRP Popup");
		driver.pauseExecutionFor(5000);
	}


	/***
	 * This method is used to verify whether the given Current CRP Status is displayed on Page or not
	 * 
	 *  return boolean value
	 */
	public boolean isCurrentCRPStatusDisplayed(String status) {
		return driver.isElementVisible(By.xpath("//p[contains(text(),'Current CRP Status:')]/parent::div/following-sibling::div/p[contains(text(),'"+status+"')]"));
	}	

	/***
	 * This method is used to verify whether the next billing ship date is displayed on Page or not
	 * 
	 *  return boolean value
	 */
	public boolean isNextBillingShipDateDisplayed() {
		return driver.isElementVisible(NEXT_BILLING_SHIP_DATE);
	}	

	/***
	 * This method is used to get the next billing ship date 
	 * 
	 *  return a String containing the next Billing Ship date
	 */
	public String getNextBillingShipDate() {
		return driver.getText(NEXT_BILLING_SHIP_DATE);
	}	

	/***
	 * This method is used to verify whether the next billing ship date contains month in required format or not
	 * 
	 *  return boolean value
	 */
	public boolean doesNextBillingShipDateContainsRequiredMonthInFormat(String month) {		
		switch(month) {
		case "January":					 
			return true;				  
		case "February":				
			return true;				  
		case "March":				
			return true;			  
		case "April":				
			return true;			  
		case "May":				
			return true;			  
		case "June":				
			return true;			  
		case "July":				
			return true;			  
		case "August":				
			return true;			  
		case "September":				
			return true;			  
		case "October":				
			return true;
		case "November":				
			return true;
		case "December":				
			return true;
		default:
			return false;		
		}
	}	

	/***
	 * This method is used to verify whether the next billing ship date contains day in required format or not
	 * 
	 *  return boolean value
	 */
	public boolean doesNextBillingShipDateContainsRequiredDayInFormat(String day) {	
		if(day.length()==2) {
			return true;
		}else {
			return false;
		}

	}	

	/***
	 * This method is used to verify whether the next billing ship date contains year in required format or not
	 * 
	 *  return boolean value
	 */
	public boolean doesNextBillingShipDateContainsRequiredYearInFormat(String year) {	
		if(year.length()==4) {
			return true;
		}else {
			return false;
		}

	}	

	public boolean clickPulseTermsAndConditionInModalPopUpAndVerifyOpenInAnotherTab(String urlString) {
		String currentURL=null;
		Boolean flag;
		String parentWindow=driver.getWindowHandle();
		driver.click(LEARN_MORE_ABOUT_PULSE_PRO_LINK_MODAL_BODY_LINK, "Pulse Pro Terms and Conditions ");
		driver.pauseExecutionFor(5000);
		driver.switchToChildWindow(parentWindow);
		driver.pauseExecutionFor(5000);
		currentURL = driver.getCurrentUrl();
		flag=currentURL.contains(urlString);
		closeChildAndSwitchToParentWindow(parentWindow);
		driver.pauseExecutionFor(3000);
		return flag;
	}	 

	/***
	 * This method is used to click on 'Cancel My Pulse pro Subscription' Link
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage clickCancelMyPULSEProSubscriptionLink() {
		driver.waitForElementPresent(CANCEL_MY_PULSE_PRO_SUBSCRIPTION_LINK);
		driver.clickByJS(RFWebsiteDriver.driver,CANCEL_MY_PULSE_PRO_SUBSCRIPTION_LINK, "Cancel My Pulse Pro Subscription Link");
		driver.pauseExecutionFor(3000);
		return this;
	}	

	/***
	 * This method is used to verify whether Learn More About Pulse Pro Link Popup is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isLearnMoreAboutPulseProLinkPopupDisplayed() {
		driver.waitForElementPresent(LEARN_MORE_ABOUT_PULSE_PRO_LINK_MODAL_HEADLINE);
		driver.pauseExecutionFor(5000);
		driver.isElementPresent(LEARN_MORE_ABOUT_PULSE_PRO_LINK_MODAL_HEADLINE);
		return driver.isElementPresent(LEARN_MORE_ABOUT_PULSE_PRO_LINK_MODAL_BODY_LINK);
	}

	/***
	 * This method is used to click on Learn More About Pulse Pro Link 
	 * 
	 *  return void
	 */
	public void clickLearnMoreAboutPulseProLink() {
		driver.scrollUpIntoView(PULSE_PRO_SUBSCRIPTION_STATUS);
		driver.click(LEARN_MORE_ABOUT_PULSE_PRO_LINK,"Learn More About Pulse Pro Link");
	} 

	/***
	 * This method is used to verify whether Learn More About Pulse Pro Link is displayed or not
	 * 
	 *  return boolean Value
	 */
	public boolean isLearnMoreAboutPulseProLinkDisplayed() {
		driver.waitForElementPresent(LEARN_MORE_ABOUT_PULSE_PRO_LINK);
		driver.pauseExecutionFor(5000);
		return driver.isElementVisible(LEARN_MORE_ABOUT_PULSE_PRO_LINK);
	}

	public boolean isPulseProOptionalTextDisplayed() {
		driver.waitForElementPresent(PULSE_PRO_OPTIONAL_TEXT_LOC);
		return driver.isElementVisible(PULSE_PRO_OPTIONAL_TEXT_LOC);
	}

	/***
	 * This method is used to verify whether Asterik Copy For CRP And Pulse Pro On Manage CRP and Pulse Page is displayed or not
	 * 
	 *  return boolean Value
	 */
	public boolean isAsterikCopyForCRPAndPULSEProOnManageCRPAndPulsePageDisplayed() {
		driver.waitForElementPresent(ASTERIK_COPY_ON_MANAGE_CRP_AND_PULSE_PRO_PAGE);
		driver.pauseExecutionFor(5000);
		return driver.isElementVisible(ASTERIK_COPY_ON_MANAGE_CRP_AND_PULSE_PRO_PAGE);
	}	

	/***
	 * This method is used to click on 'Yes' button in the Cancel Pulse Subscription Alert
	 * 
	 *  return void
	 */
	public void clickYesOnCancelPulseSubscriptionPopup() {
		driver.click(ALERT_YES_DEACTIVATE_PULSE_SUBSCRIPTION_BTN, "Yes Button On Cancel Pulse Subscription Popup");
		driver.pauseExecutionFor(5000);
	}

	public boolean clickPulseProTermsAndConditionAndVerifyOpenInAnotherTab(String urlString) {
		String currentURL=null;
		Boolean flag;
		String parentWindow=driver.getWindowHandle();
		driver.click(PULSE_PRO_TERMS_AND_CONDITION_LOC, "Pulse Pro Terms and Conditions");
		driver.pauseExecutionFor(5000);
		driver.switchToChildWindow(parentWindow);
		driver.pauseExecutionFor(5000);
		currentURL = driver.getCurrentUrl();
		flag=currentURL.contains(urlString);
		closeChildAndSwitchToParentWindow(parentWindow);
		driver.pauseExecutionFor(3000);
		return flag;
	}	 

	/***
	 * This method is used to get the next bill date 
	 * 
	 *  return a String containing the next Bill date
	 */
	public String getNextBillDate() {
		return driver.getText(NEXT_BILL_DATE);
	}	

	/***
	 * This method is used to verify whether the next bill date is displayed on Page or not
	 * 
	 *  return boolean value
	 */
	public boolean isNextBillDateDisplayed() {
		return driver.isElementVisible(NEXT_BILL_DATE);
	}	

	/***
	 * This method is used to click on View Details Link 
	 * return void
	 */
	public void clickViewDetailsLink() {
		driver.click(VIEW_DETAILS_LINK, "View Details Link");
		driver.pauseExecutionFor(5000);

	}	

	/***
	 * This method is used to fetch the Header Column Items List in the View Details page
	 * return List of WebElements containing the Header Items
	 */
	public List<WebElement> isHeaderColumnItemsDisplayedInViewDetailsPage() {
		List<WebElement> headerColumnItems=driver.findElements(HEADER_COLUMN_ITEMS_IN_VIEW_DETAILS_PAGE);
		return headerColumnItems;

	}	

	public boolean isPulseProTermsAndConditionsDisplayedOnPage() {
		return driver.isElementVisible(PULSE_PRO_TERMS_AND_CONDITION_LOC);
	}

	/***
	 * This method is used to verify whether the given Current Pulse Pro Subscription Status is displayed on Page or not
	 * 
	 *  return boolean value
	 */
	public boolean isCurrentPulseProSubscriptionStatusDisplayed(String status) {
		return driver.isElementVisible(By.xpath("//p[contains(text(),'Current Subscription Status:')]/parent::div/following-sibling::div/p[contains(text(),'"+status+"')]"));
	}

	/***
	 * This method is used to verify whether My Account confirm New Password TextField is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountConfirmNewPasswordTxtFieldDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_CONFIRM_NEW_PASSWORD_TXTFIELD);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_CONFIRM_NEW_PASSWORD_TXTFIELD);
	}
	/***
	 * This method is used to clear the My Account Confirm New Password TextField  
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage clearMyAccountConfirmNewPasswordTxtField() {
		driver.waitForElementPresent(MY_ACCOUNT_CONFIRM_NEW_PASSWORD_TXTFIELD);
		driver.clear(MY_ACCOUNT_CONFIRM_NEW_PASSWORD_TXTFIELD);
		return this;
	}

	/***
	 * This method is used to enter text in the My Account Confirm New Password TextField  
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage enterTextInMyAccountConfirmNewPasswordTxtField(String confirmNewPassword) {
		driver.waitForElementPresent(MY_ACCOUNT_CONFIRM_NEW_PASSWORD_TXTFIELD);
		driver.type(MY_ACCOUNT_CONFIRM_NEW_PASSWORD_TXTFIELD, confirmNewPassword);
		return this;
	}	

	/***
	 * This method is used to verify whether My Account New Password TextField is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountNewPasswordTxtFieldDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_NEW_PASSWORD_TXTFIELD);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_NEW_PASSWORD_TXTFIELD);
	}
	/***
	 * This method is used to clear the My Account New Password TextField  
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage clearMyAccountNewPasswordTxtField() {
		driver.waitForElementPresent(MY_ACCOUNT_NEW_PASSWORD_TXTFIELD);
		driver.clear(MY_ACCOUNT_NEW_PASSWORD_TXTFIELD);
		return this;
	}

	/***
	 * This method is used to enter text in the My Account New Password TextField  
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage enterTextInMyAccountNewPasswordTxtField(String newPassword) {
		driver.waitForElementPresent(MY_ACCOUNT_NEW_PASSWORD_TXTFIELD);
		driver.type(MY_ACCOUNT_NEW_PASSWORD_TXTFIELD, newPassword);
		return this;
	}	

	/***
	 * This method is used to verify whether My Account Old Password TextField is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountOldPasswordTxtFieldDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_OLD_PASSWORD_TXTFIELD);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_OLD_PASSWORD_TXTFIELD);
	}
	/***
	 * This method is used to clear the My Account Old Password TextField  
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage clearMyAccountOldPasswordTxtField() {
		driver.waitForElementPresent(MY_ACCOUNT_OLD_PASSWORD_TXTFIELD);
		driver.clear(MY_ACCOUNT_OLD_PASSWORD_TXTFIELD);
		return this;
	}

	/***
	 * This method is used to enter text in the My Account Old Password TextField  
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage enterTextInMyAccountOldPasswordTxtField(String oldPassword) {
		driver.waitForElementPresent(MY_ACCOUNT_OLD_PASSWORD_TXTFIELD);
		driver.type(MY_ACCOUNT_OLD_PASSWORD_TXTFIELD, oldPassword);
		return this;
	}

	/***
	 * This method is used to verify whether My Account Please ReEnter Your New Password  Error Label is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountPleaseReEnterYourNewPasswordErrorLabelDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_PLEASE_REENTER_YOUR_NEW_PASSWORD_ERROR_LABEL);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_PLEASE_REENTER_YOUR_NEW_PASSWORD_ERROR_LABEL);
	}


	/***
	 * This method is used to verify whether My Account New Password Is Required Error Label is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountNewPasswordIsRequiredErrorLabelDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_NEW_PASSWORD_IS_REQUIRED_ERROR_LABEL);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_NEW_PASSWORD_IS_REQUIRED_ERROR_LABEL);
	}

	/***
	 * This method is used to verify whether My Account Please Enter Your Current Password Error Label is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountPleaseEnterYourCurrentPasswordErrorLabelDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_PLEASE_ENTER_YOUR_CURRENT_PASSWORD_ERROR_LABEL);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_PLEASE_ENTER_YOUR_CURRENT_PASSWORD_ERROR_LABEL);
	}

	/***
	 * This method is used to verify whether My Account Please Enter A Valid Email Address Error Label is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountPleaseEnterAValidEmailAddressErrorLabelDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_PLEASE_ENTER_A_VALID_EMAIL_ADDRESS_ERROR_LABEL);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_PLEASE_ENTER_A_VALID_EMAIL_ADDRESS_ERROR_LABEL);
	}


	/***
	 * This method is used to verify whether My Account Email Is Required Error Label is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountEmailIsRequiredErrorLabelDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_EMAIL_IS_REQUIRED_ERROR_LABEL);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_EMAIL_IS_REQUIRED_ERROR_LABEL);
	}

	/***
	 * This method is used to verify whether My Account Email Address TextField is displayed or not 
	 * 
	 *  return boolean
	 */
	public boolean isMyAccountEmailAddressTxtFieldDisplayed() {
		driver.waitForElementPresent(MY_ACCOUNT_EMAIL_ADDRESS_TXTFIELD);
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(MY_ACCOUNT_EMAIL_ADDRESS_TXTFIELD);
	}
	/***
	 * This method is used to clear the My Account Email Address TextField  
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage clearMyAccountEmailAddressTxtField() {
		driver.waitForElementPresent(MY_ACCOUNT_EMAIL_ADDRESS_TXTFIELD);
		driver.clear(MY_ACCOUNT_EMAIL_ADDRESS_TXTFIELD);
		return this;
	}

	/***
	 * This method is used to enter text in the My Account Email Address TextField  
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage enterTextInMyAccountEmailAddressTxtField(String emailAddress) {
		driver.waitForElementPresent(MY_ACCOUNT_EMAIL_ADDRESS_TXTFIELD);
		driver.type(MY_ACCOUNT_EMAIL_ADDRESS_TXTFIELD, emailAddress);
		return this;
	}

	/***
	 * This method is used to click on the Save Button 
	 * 
	 *  return A self reference
	 */
	public StoreFrontHeirloomConsultantPage clickOnSaveButton() {
		driver.waitForElementPresent(MY_ACCOUNT_SAVE_BTN);
		driver.click(MY_ACCOUNT_SAVE_BTN,"Save Button");
		return this;
	}

	/***
	 * This method is used to click on Subscribe to Pulse Pro Button
	 * 
	 *  return StoreFrontHeirloomConsultantPage class object
	 */
	public StoreFrontHeirloomConsultantPage clickSubscribeToPulseProButton() {
		driver.scrollUpIntoView(PULSE_PRO_SUBSCRIPTION_STATUS);
		driver.click(SUBSCRIBE_TO_PULSE_PRO_BUTTON_LOC, "Subscribe to pulse pro button");
		driver.pauseExecutionFor(3000);
		return this;
	} 

	public boolean isCheckMyPulsePresent() {
		driver.quickWaitForElementPresent(CHECK_MY_PULSE_LOC);
		return driver.isElementVisible(CHECK_MY_PULSE_LOC);
	} 

	public String getNextBillingShipDateFromUI() {
		driver.pauseExecutionFor(3000);
		return driver.getText(NEXT_BILLING_SHIP_DATE);
	}

	public String createNextOrderDateAfterOneMonthForCRPAndPulse(){
		int day = getCurrentDayFromCurrentDate();
		System.out.println("Day===: "+day);
		String month = getNextMonthFromCurrentDate();
		month=getMonthInFormatAsOnUI(month);
		System.out.println(month);
		String nextOrderDate = null;
		String year = getYearAfterOneMonthFromCurrentDate();
		System.out.println(year);
		String day1 = ""+day;
		if(day1.substring(0,1).contains("0")){
			day = Integer.parseInt("0"+day);
		}
		if(day<=20){
			nextOrderDate = month+" "+day+", "+year;
		}else{
			nextOrderDate = month+" "+"20, "+year;
		}
		logger.info("Next order date is "+nextOrderDate);
		return nextOrderDate;
	}

	public String getMonthInFormatAsOnUI(String month) {
		switch(month) {
		case "jan": return "January";
		case "feb": return "February";
		case "mar": return "March";
		case "apr": return "April";
		case "may": return "May";
		case "jun": return "June";
		case "jul": return "July";
		case "aug": return "August";
		case "sep": return "September";
		case "oct": return "October";
		case "nov": return "November";
		case "dec": return "December";
		default:return "";
		}

	}

	public void clickIWantMyFirstCRPOrderToShipNextMonthChkbox() {
		driver.click(I_WANT_MY_FIRST_CRP_ORDER_TO_SHIP_NEXT_MONTH_LOC, "I want my first CRP order to ship next month");
	}

	public void clickContinueButton() {
		driver.click(CONTINUE_BUTTON_LOC, "Continue button");
	}

	public void selectCategory(String categoryName) {
		driver.click(By.xpath("//ul[contains(@class,'PageLinks')]//a[contains(text(),'"+categoryName+"')]"),""+categoryName);
	}

	public void clickAddFirstProductToCart() {
		driver.click(ADD_FIRST_PRODUCT_TO_CART_LOC, "Add to bag");
		driver.pauseExecutionFor(3000);
	}

	public void clickContinueButtonOnCRPEnrollmentPage() {
		driver.click(CONTINUE_BUTTON_ON_CRP_ENROLLMENT_PAGE_LOC, "Continue Button on CRP Enrollment page");
	}

	public void clickCompleteCRPEnrollmentButton() {
		driver.click(COMPLETE_CRP_ENROLLMENT_BUTTON, "Complete CRP Enrollment");
	}

	public boolean isCRPEnrollmentCongratsMessageDisplayed() {
		return driver.isElementVisible(CRP_ENROLLMENT_CONGRATULATION_MESSAGE_LOC);
	}

	public String getNextMonthFromCurrentDate(){
		Calendar originalDate = Calendar.getInstance();
		logger.info("The original Date is : " + originalDate.getTime());
		Calendar nextMonthDate = (Calendar) originalDate.clone();
		nextMonthDate.add(Calendar.MONTH, 1);
		String nextMonth = ""+nextMonthDate.getTime();
		logger.info("The Next month date is: " + nextMonth);
		String month = nextMonth.split(" ")[1];
		logger.info("next month is "+month);
		return month.toLowerCase();
	}

	public String getYearAfterOneMonthFromCurrentDate(){
		Calendar originalDate = Calendar.getInstance();
		logger.info("The original Date is : " + originalDate.getTime());
		Calendar nextMonthDate = (Calendar) originalDate.clone();
		nextMonthDate.add(Calendar.MONTH, 1);
		String nextMonth = ""+nextMonthDate.getTime();
		logger.info("The Next 2nd month date is: " + nextMonth);
		String year = nextMonth.split(" ")[5];
		logger.info("next year is "+year);
		return year.toLowerCase();
	}

	public void clickCRPPolicyAndProcedureChkBox() {
		driver.waitForElementPresent(CRP_POLICY_AND_PROCEDURE_CHECKBOX_LOC);
		driver.pauseExecutionFor(3000);
		driver.click(CRP_POLICY_AND_PROCEDURE_CHECKBOX_LOC, "CRP Policy and Procedure CheckBox");
	}



	public void clickContinueButtonOnCartPage() {
		driver.waitForElementTobeEnabled(CONTINUE_BUTTON_ON_CART_PAGE_LOC);
		driver.pauseExecutionFor(3000);
		driver.clickByJS(RFWebsiteDriver.driver,CONTINUE_BUTTON_ON_CART_PAGE_LOC, "Continue button");//(CONTINUE_BUTTON_ON_CART_PAGE_LOC, "Continue button");
	}


	public String createNextOrderDateInSpecifiedFormatAfterOneMonthForCRPAndPulse(){
		String day1 = getCurrentDayAsStringFromCurrentDate();
		String month = getNextMonthFromCurrentDate();
		month=getMonthInFormatAsOnUI(month);
		System.out.println(month);
		String nextOrderDate = null;
		String year = getYearAfterOneMonthFromCurrentDate();
		System.out.println(year);
		int day=Integer.parseInt(day1);
		if(day<=20){
			nextOrderDate = month+" "+day1+", "+year;
		}else{
			nextOrderDate = month+" "+"20, "+year;
		}
		logger.info("Next order date is "+nextOrderDate);
		return nextOrderDate;
	}


}