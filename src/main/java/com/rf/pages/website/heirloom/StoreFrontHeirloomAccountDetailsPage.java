package com.rf.pages.website.heirloom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.rf.core.driver.website.RFWebsiteDriver;

public class StoreFrontHeirloomAccountDetailsPage extends StoreFrontHeirloomWebsiteBasePage{

	public StoreFrontHeirloomAccountDetailsPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LogManager
			.getLogger(StoreFrontHeirloomBillingAndShippingProfilePage.class.getName());
	
	private static final By FIRST_NAME_ON_ACCOUNT_DETAILS_PAGE_LOC=By.id("firstName");
	private static final By LAST_NAME_ON_ACCOUNT_DETAILS_PAGE_LOC=By.id("lastName");
	private static final By ADDRESS1_ON_ACCOUNT_DETAILS_PAGE_LOC=By.id("address1");
	private static final By ADDRESS2_ON_ACCOUNT_DETAILS_PAGE_LOC=By.id("address2");
	private static final By ADDRESS3_ON_ACCOUNT_DETAILS_PAGE_LOC=By.id("address3");
	private static final By ZIPCODE_ON_ACCOUNT_DETAILS_PAGE_LOC=By.id("zipCode");
	private static final By CITY_ON_ACCOUNT_DETAILS_PAGE_LOC=By.xpath(".//*[@id='city']/option");
	private static final By STATE_ON_ACCOUNT_DETAILS_PAGE_LOC=By.xpath(".//*[@id='state']/option");
	private static final By PHONE_NUMBER_ON_ACCOUNT_DETAILS_PAGE_LOC=By.id("phoneNumber");
	private static final By MOBILE_NUMBER_ON_ACCOUNT_DETAILS_PAGE_LOC=By.id("mobileNumber");
	private static final By BIRTH_MONTH_ON_ACCOUNT_DETAILS_PAGE_LOC=By.xpath("//*[@id='birthMonth']/option");
	private static final By BIRTH_DAY_ON_ACCOUNT_DETAILS_PAGE_LOC=By.xpath(".//*[@id='birthDay']/option");
	private static final By BIRTH_YEAR_ON_ACCOUNT_DETAILS_PAGE_LOC=By.xpath(".//*[@id='birthYear']/option");
	private static final By MALE_CHECKBOX_LOC=By.xpath("//input[@id='male']");
	private static final By FEMALE_CHECKBOX_LOC=By.xpath("//input[@id='female']");
	private static final By EMAIL_ADDRESS_FIELD_LOC=By.id("emailAddress");
	private static final By CURRENT_USERNAME_FIELD_LOC=By.id("currentUsername");
	private static final By OLD_PASSWORD_FIELD_LOC=By.id("currentPassword");
	private static final By NEW_PASSWORD_FIELD_LOC=By.id("newPassword");
	private static final By CONFIRM_NEW_PASSWORD_FIELD_LOC=By.id("confirmPassword");
	private static final By SAVE_ACCOUNT_INFO_BUTTON_LOC=By.id("saveAccountInfo");
	private static final By SUGGESTED_ADDRESS_ON_QAS_POPUP_LOC=By.xpath("//div[@class='QAS_MultPick']//a[contains(@class,'QAS_StepIn')]");
	private static final By SHIPPING_ON_FOOTER_LINKS_LOC=By.xpath("//span[contains(text(),'Shipping')]");
	
	public void editAccountdetails(String firstName, String lastName, String address1, String zipCode) {
		driver.type(FIRST_NAME_ON_ACCOUNT_DETAILS_PAGE_LOC, firstName);
		driver.type(LAST_NAME_ON_ACCOUNT_DETAILS_PAGE_LOC, lastName);
		driver.type(ADDRESS1_ON_ACCOUNT_DETAILS_PAGE_LOC, address1);
		driver.type(ZIPCODE_ON_ACCOUNT_DETAILS_PAGE_LOC, zipCode);
		driver.click(SAVE_ACCOUNT_INFO_BUTTON_LOC, "Save Button");
	}
	
	public void editEmailAndPasswordDetails(String email,String password) {
		driver.type(EMAIL_ADDRESS_FIELD_LOC, email);
		driver.type(OLD_PASSWORD_FIELD_LOC, password);
		driver.type(NEW_PASSWORD_FIELD_LOC, password);
		driver.type(CONFIRM_NEW_PASSWORD_FIELD_LOC, password);
		driver.click(SAVE_ACCOUNT_INFO_BUTTON_LOC, "Save Button");
		driver.pauseExecutionFor(4000);
		driver.waitForPageLoad();
	}
	
	public boolean isHeaderPresentOnPage(String headerName) {
		return driver.isElementVisible(By.xpath("//h4[contains(text(),'"+headerName+"')]"));
	}
	
	public boolean isShippingLinkPresentOnFooter() {
		return driver.isElementVisible(SHIPPING_ON_FOOTER_LINKS_LOC);
	}
	
	public boolean isFooterLinksPresentOnPage(String footerLinkName) {
		driver.waitForElementPresent(By.xpath("//*[contains(text(),'"+footerLinkName+"')]"));
		return driver.isElementVisible(By.xpath("//*[contains(text(),'"+footerLinkName+"')]"));
	}
	
	public boolean isFirstNamePresentOnAccountDetailsPage() {
		return driver.isElementPresent(FIRST_NAME_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isLastNamePresentOnAccountDetailsPage() {
		return driver.isElementPresent(LAST_NAME_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isAddress1PresentOnAccountDetailsPage() {
		return driver.isElementPresent(ADDRESS1_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isAddress2PresentOnAccountDetailsPage() {
		return driver.isElementPresent(ADDRESS2_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isAddress3PresentOnAccountDetailsPage() {
		return driver.isElementPresent(ADDRESS3_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isZipCodePresentOnAccountDetailsPage() {
		return driver.isElementPresent(ZIPCODE_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isCityPresentOnAccountDetailsPage() {
		return driver.isElementPresent(CITY_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isStatePresentOnAccountDetailsPage() {
		return driver.isElementPresent(STATE_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isPhoneNumberPresentOnAccountDetailsPage() {
		return driver.isElementPresent(PHONE_NUMBER_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isMobileNumberPresentOnAccountDetailsPage() {
		return driver.isElementPresent(MOBILE_NUMBER_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isBirthMonthPresentOnAccountDetailsPage() {
		return driver.isElementPresent(BIRTH_MONTH_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isBirthDayPresentOnAccountDetailsPage() {
		return driver.isElementPresent(BIRTH_DAY_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isBirthYearPresentOnAccountDetailsPage() {
		return driver.isElementPresent(BIRTH_YEAR_ON_ACCOUNT_DETAILS_PAGE_LOC);
	}
	
	public boolean isMaleCheckboxPresentOnAccountDetailsPage() {
		return driver.isElementVisible(MALE_CHECKBOX_LOC);
	}
	
	public boolean isFeMaleCheckboxPresentOnAccountDetailsPage() {
		return driver.isElementVisible(FEMALE_CHECKBOX_LOC);
	}
	
	public boolean isEmailAddressFieldPresentOnPage() {
		return driver.isElementVisible(EMAIL_ADDRESS_FIELD_LOC);
	}
	
	public boolean isCurrentUsernamePresentOnPage() {
		return driver.isElementVisible(CURRENT_USERNAME_FIELD_LOC);
	}
	
	public boolean isOldPasswordFieldPresentOnPage() {
		return driver.isElementVisible(OLD_PASSWORD_FIELD_LOC);
	}
	
	public boolean isNewPasswordFieldPresentOnPage() {
		return driver.isElementVisible(NEW_PASSWORD_FIELD_LOC);
	}
	
	public boolean isConfirmNewPasswordFieldPresentOnPage() {
		return driver.isElementVisible(CONFIRM_NEW_PASSWORD_FIELD_LOC);
	}
	
	public boolean isSaveButtonPresentOnPage() {
		return driver.isElementVisible(SAVE_ACCOUNT_INFO_BUTTON_LOC);
	}
	
	public boolean isQASPopupContainsSuggestedAddress() {
		return driver.isElementVisible(SUGGESTED_ADDRESS_ON_QAS_POPUP_LOC);
	}
	
	public String getFirstName() {
		return driver.getAttribute(FIRST_NAME_ON_ACCOUNT_DETAILS_PAGE_LOC, "value");
	}
	
	public String getLastName() {
		return driver.getAttribute(LAST_NAME_ON_ACCOUNT_DETAILS_PAGE_LOC, "value");
	}
	
	public String getAddress1() {
		return driver.getAttribute(ADDRESS1_ON_ACCOUNT_DETAILS_PAGE_LOC, "value");
	}
	
	public String getZipCode() {
		return driver.getAttribute(ZIPCODE_ON_ACCOUNT_DETAILS_PAGE_LOC, "value");
	}
	
	public String getEmailAddress() {
		return driver.getAttribute(EMAIL_ADDRESS_FIELD_LOC, "value");
	}
}
