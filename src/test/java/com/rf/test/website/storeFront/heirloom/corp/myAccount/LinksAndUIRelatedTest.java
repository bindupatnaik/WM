package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class LinksAndUIRelatedTest extends RFHeirloomWebsiteBaseTest{

	/**
	 * Jira Story Id: MAIN-5261
	 * qTest Id: TC-2821
	 */
	@Test(alwaysRun=true,description = "Updated the Primary Account information - CORP")
	public void testUpdatedThePrimaryAccountInformationCORP_2821() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String consultantEmailID = null;
		String accountDetailsLink="Account Details";
		String accountDetailsSectionName="Account Details";
		String changeYourSettingsSectionName="Change your settings";
		String contactUsFooterLinkName="Contact Us";
		String dermRFFooterLinkName="Derm RF"; 
		String pressRoomFooterLinkName="Press Room"; 
		String careersFooterLinkName="Careers"; 
		String satisfactionGuaranteeFooterLinkName="Satisfaction Guarantee"; 
		String termsAndConditionFooterLinkName="Terms & Conditions"; 
		String privacyPolicyFooterLinkName="Privacy Policy"; 
		String disclaimerFooterLinkName="Disclaimer";
		String CSCAFooterLinkName="California Supply Chains Act";
		String firstName=TestConstantsRFL.FIRST_NAME;
		String lastName=TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress=firstName+""+lastName+""+TestConstantsRFL.EMAIL_SUFFIX;
		String accountInfoUpdationMessage ="Your account info has been updated successfully.";
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}

		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(accountDetailsLink);
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isHeaderPresentOnPage(accountDetailsSectionName),"Account Details section is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isHeaderPresentOnPage(changeYourSettingsSectionName),"Change your settings section is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(contactUsFooterLinkName),"Contact Us footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(dermRFFooterLinkName),"Derm RF footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(pressRoomFooterLinkName),"Press Room footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(careersFooterLinkName),"Careers footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isShippingLinkPresentOnFooter(),"Shipping footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(satisfactionGuaranteeFooterLinkName),"Satisfaction Guarantee footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(termsAndConditionFooterLinkName),"Terms & Conditions footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(privacyPolicyFooterLinkName),"Privacy Policy footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(disclaimerFooterLinkName),"Disclaimer footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(CSCAFooterLinkName),"California Supply Chains Act footer link is not present on page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFirstNamePresentOnAccountDetailsPage(),"First Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isLastNamePresentOnAccountDetailsPage(),"Last Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress1PresentOnAccountDetailsPage(),"Address 1 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress2PresentOnAccountDetailsPage(),"Address 2 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress3PresentOnAccountDetailsPage(),"Address 3 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isZipCodePresentOnAccountDetailsPage(),"Zip Code is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isCityPresentOnAccountDetailsPage(),"City Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isStatePresentOnAccountDetailsPage(),"State is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isPhoneNumberPresentOnAccountDetailsPage(),"Phone Number is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isMobileNumberPresentOnAccountDetailsPage(),"Mobile Number is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthMonthPresentOnAccountDetailsPage(),"Birth Month is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthDayPresentOnAccountDetailsPage(),"Birth day is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthYearPresentOnAccountDetailsPage(),"Birth Year is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isMaleCheckboxPresentOnAccountDetailsPage(),"Male Checkbox is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFeMaleCheckboxPresentOnAccountDetailsPage(),"FeMale Checkbox is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isEmailAddressFieldPresentOnPage(),"Email Address Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isCurrentUsernamePresentOnPage(),"Current Username is not Displayed on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isOldPasswordFieldPresentOnPage(),"Old Password Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isNewPasswordFieldPresentOnPage(),"New Password Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isConfirmNewPasswordFieldPresentOnPage(),"Confirm New Password Field Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isSaveButtonPresentOnPage(),"Save Button is not Displayed on Page");

		storeFrontHeirloomAccountDetailsPage.editAccountdetails(firstName, lastName, addressLine1, postalCode);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
	//	s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(accountInfoUpdationMessage),"Account info updation message not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getFirstName().toLowerCase().contains(firstName.toLowerCase()),"First name is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getLastName().toLowerCase().contains(lastName.toLowerCase()),"Last name is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getAddress1().toLowerCase().contains(addressLine1.toLowerCase()),"Address line1 is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getZipCode().toLowerCase().contains(postalCode.toLowerCase()),"zipcode is not updated");

		storeFrontHeirloomAccountDetailsPage.editEmailAndPasswordDetails(emailAddress, password);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
	//	s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(accountInfoUpdationMessage),"Account info updation message not displayed on page after email address updation");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getEmailAddress().toLowerCase().contains(emailAddress.toLowerCase()),"Email Address is not updated");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5261
	 * qTest Id: TC-2822
	 */
	@Test(alwaysRun=true,description = "Updated the Primary Account information - BIZ")
	public void testUpdatedThePrimaryAccountInformationBiz_2822() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String consultantEmailID = null;
		String accountDetailsLink="Account Details";
		String accountDetailsSectionName="Account Details";
		String changeYourSettingsSectionName="Change your settings";
		String contactUsFooterLinkName="Contact Us";
		String dermRFFooterLinkName="Derm RF"; 
		String pressRoomFooterLinkName="Press Room"; 
		String careersFooterLinkName="Careers"; 
		String satisfactionGuaranteeFooterLinkName="Satisfaction Guarantee"; 
		String termsAndConditionFooterLinkName="Terms & Conditions"; 
		String privacyPolicyFooterLinkName="Privacy Policy"; 
		String disclaimerFooterLinkName="Disclaimer";
		String CSCAFooterLinkName="California Supply Chains Act";
		String firstName=TestConstantsRFL.FIRST_NAME;
		String lastName=TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress=firstName+""+lastName+""+TestConstantsRFL.EMAIL_SUFFIX;
		String accountInfoUpdationMessage ="Your account info has been updated successfully.";
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}

		openBIZSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(accountDetailsLink);
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isHeaderPresentOnPage(accountDetailsSectionName),"Account Details section is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isHeaderPresentOnPage(changeYourSettingsSectionName),"Change your settings section is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(contactUsFooterLinkName),"Contact Us footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(dermRFFooterLinkName),"Derm RF footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(pressRoomFooterLinkName),"Press Room footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(careersFooterLinkName),"Careers footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isShippingLinkPresentOnFooter(),"Shipping footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(satisfactionGuaranteeFooterLinkName),"Satisfaction Guarantee footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(termsAndConditionFooterLinkName),"Terms & Conditions footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(privacyPolicyFooterLinkName),"Privacy Policy footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(disclaimerFooterLinkName),"Disclaimer footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(CSCAFooterLinkName),"California Supply Chains Act footer link is not present on page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFirstNamePresentOnAccountDetailsPage(),"First Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isLastNamePresentOnAccountDetailsPage(),"Last Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress1PresentOnAccountDetailsPage(),"Address 1 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress2PresentOnAccountDetailsPage(),"Address 2 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress3PresentOnAccountDetailsPage(),"Address 3 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isZipCodePresentOnAccountDetailsPage(),"Zip Code is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isCityPresentOnAccountDetailsPage(),"City Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isStatePresentOnAccountDetailsPage(),"State is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isPhoneNumberPresentOnAccountDetailsPage(),"Phone Number is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isMobileNumberPresentOnAccountDetailsPage(),"Mobile Number is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthMonthPresentOnAccountDetailsPage(),"Birth Month is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthDayPresentOnAccountDetailsPage(),"Birth day is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthYearPresentOnAccountDetailsPage(),"Birth Year is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isMaleCheckboxPresentOnAccountDetailsPage(),"Male Checkbox is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFeMaleCheckboxPresentOnAccountDetailsPage(),"FeMale Checkbox is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isEmailAddressFieldPresentOnPage(),"Email Address Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isCurrentUsernamePresentOnPage(),"Current Username is not Displayed on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isOldPasswordFieldPresentOnPage(),"Old Password Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isNewPasswordFieldPresentOnPage(),"New Password Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isConfirmNewPasswordFieldPresentOnPage(),"Confirm New Password Field Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isSaveButtonPresentOnPage(),"Save Button is not Displayed on Page");

		storeFrontHeirloomAccountDetailsPage.editAccountdetails(firstName, lastName, addressLine1, postalCode);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
	//	s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(accountInfoUpdationMessage),"Account info updation message not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getFirstName().toLowerCase().contains(firstName.toLowerCase()),"First name is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getLastName().toLowerCase().contains(lastName.toLowerCase()),"Last name is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getAddress1().toLowerCase().contains(addressLine1.toLowerCase()),"Address line1 is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getZipCode().toLowerCase().contains(postalCode.toLowerCase()),"zipcode is not updated");

		storeFrontHeirloomAccountDetailsPage.editEmailAndPasswordDetails(emailAddress, password);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		//s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(accountInfoUpdationMessage),"Account info updation message not displayed on page after email address updation");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getEmailAddress().toLowerCase().contains(emailAddress.toLowerCase()),"Email Address is not updated");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5261
	 * qTest Id: TC-2823
	 */
	@Test(alwaysRun=true,description = "Updated the Primary Account information - COM")
	public void testUpdatedThePrimaryAccountInformationCOM_2823() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String consultantEmailID = null;
		String accountDetailsLink="Account Details";
		String accountDetailsSectionName="Account Details";
		String changeYourSettingsSectionName="Change your settings";
		String contactUsFooterLinkName="Contact Us";
		String dermRFFooterLinkName="Derm RF"; 
		String pressRoomFooterLinkName="Press Room"; 
		String careersFooterLinkName="Careers"; 
		String satisfactionGuaranteeFooterLinkName="Satisfaction Guarantee"; 
		String termsAndConditionFooterLinkName="Terms & Conditions"; 
		String privacyPolicyFooterLinkName="Privacy Policy"; 
		String disclaimerFooterLinkName="Disclaimer";
		String CSCAFooterLinkName="California Supply Chains Act";
		String firstName=TestConstantsRFL.FIRST_NAME;
		String lastName=TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress=firstName+""+lastName+""+TestConstantsRFL.EMAIL_SUFFIX;
		String accountInfoUpdationMessage ="Your account info has been updated successfully.";
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}

		openCOMSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(accountDetailsLink);
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isHeaderPresentOnPage(accountDetailsSectionName),"Account Details section is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isHeaderPresentOnPage(changeYourSettingsSectionName),"Change your settings section is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(contactUsFooterLinkName),"Contact Us footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(dermRFFooterLinkName),"Derm RF footer link is not present on page");
		//s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(pressRoomFooterLinkName),"Press Room footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(careersFooterLinkName),"Careers footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isShippingLinkPresentOnFooter(),"Shipping footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(satisfactionGuaranteeFooterLinkName),"Satisfaction Guarantee footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(termsAndConditionFooterLinkName),"Terms & Conditions footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(privacyPolicyFooterLinkName),"Privacy Policy footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(disclaimerFooterLinkName),"Disclaimer footer link is not present on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFooterLinksPresentOnPage(CSCAFooterLinkName),"California Supply Chains Act footer link is not present on page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFirstNamePresentOnAccountDetailsPage(),"First Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isLastNamePresentOnAccountDetailsPage(),"Last Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress1PresentOnAccountDetailsPage(),"Address 1 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress2PresentOnAccountDetailsPage(),"Address 2 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isAddress3PresentOnAccountDetailsPage(),"Address 3 is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isZipCodePresentOnAccountDetailsPage(),"Zip Code is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isCityPresentOnAccountDetailsPage(),"City Name is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isStatePresentOnAccountDetailsPage(),"State is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isPhoneNumberPresentOnAccountDetailsPage(),"Phone Number is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isMobileNumberPresentOnAccountDetailsPage(),"Mobile Number is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthMonthPresentOnAccountDetailsPage(),"Birth Month is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthDayPresentOnAccountDetailsPage(),"Birth day is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isBirthYearPresentOnAccountDetailsPage(),"Birth Year is not present on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isMaleCheckboxPresentOnAccountDetailsPage(),"Male Checkbox is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isFeMaleCheckboxPresentOnAccountDetailsPage(),"FeMale Checkbox is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isEmailAddressFieldPresentOnPage(),"Email Address Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isCurrentUsernamePresentOnPage(),"Current Username is not Displayed on Page");

		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isOldPasswordFieldPresentOnPage(),"Old Password Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isNewPasswordFieldPresentOnPage(),"New Password Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isConfirmNewPasswordFieldPresentOnPage(),"Confirm New Password Field Field is not Displayed on Page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.isSaveButtonPresentOnPage(),"Save Button is not Displayed on Page");

		storeFrontHeirloomAccountDetailsPage.editAccountdetails(firstName, lastName, addressLine1, postalCode);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
	//	s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(accountInfoUpdationMessage),"Account info updation message not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getFirstName().toLowerCase().contains(firstName.toLowerCase()),"First name is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getLastName().toLowerCase().contains(lastName.toLowerCase()),"Last name is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getAddress1().toLowerCase().contains(addressLine1.toLowerCase()),"Address line1 is not updated");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getZipCode().toLowerCase().contains(postalCode.toLowerCase()),"zipcode is not updated");

		storeFrontHeirloomAccountDetailsPage.editEmailAndPasswordDetails(emailAddress, password);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		//s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(accountInfoUpdationMessage),"Account info updation message not displayed on page after email address updation");
		s_assert.assertTrue(storeFrontHeirloomAccountDetailsPage.getEmailAddress().toLowerCase().contains(emailAddress.toLowerCase()),"Email Address is not updated");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7401
	 * qTest Id: TC-2764
	 */
	@Test(alwaysRun=true,description = "Cart icon placement in eyebrow")
	public void testCartIconPlacementInEyebrow_2764() {
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCountryDropdownIconPresent()," Country Dropdown Icon is NOT present on Corp Site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCartIconPresent()," Cart Icon is NOT present on Corp Site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginIconPresent()," Login Icon Dropdown is NOT present on Corp Site");
		openBIZSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCountryDropdownIconPresent()," Country Dropdown Icon is NOT present on Bizz Site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCartIconPresent()," Cart Icon is NOT present on Bizz Site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginIconPresent()," Login Icon Dropdown is NOT present on Bizz Site");
		openCOMSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCountryDropdownIconPresent()," Country Dropdown Icon is NOT present on Com Site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCartIconPresent()," Cart Icon is NOT present on Com Site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginIconPresent()," Login Icon Dropdown is NOT present on Com Site");
		s_assert.assertAll();

	}	


	/**
	 * Jira Story Id: Main-7401
	 * qTest Id: TC-2765
	 */
	@Test(alwaysRun=true,description = "R+F Indpendent Consultant' after Sponsor name")
	public void testOrderSponsorNameCommaIndependantConsultant_2765() {	
		openBIZSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.fetchWelcomeUserLink().contains(", \nIndependent Consultant"),"Welcome User Link is not in Order in Biz Site");
		openCOMSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.fetchWelcomeUserLink().contains(", \nIndependent Consultant"),"Welcome User Link is not in Order in Com Site");
		s_assert.assertAll();
	}


	/**
	 * Jira Story Id: MAIN-7401
	 * qTest Id: TC-2770
	 * @throws InterruptedException 
	 */
	@Test(alwaysRun=true,description = "Eyebrow panel hides and reappears through scrolling")
	public void testEyebrowPanelHidesAndReappearsThroughScrolling_2770() throws InterruptedException {
		String currentURL=null;	
		String meetOurCommunity="Meet Our Community";
		String programAndIncentives="Programs and Incentives";
		s_assert.assertFalse(storeFrontHeirloomHomePage.isEyebrowPanelDisplayedAfterScrollingToBottom(),"Eyebrowpanel still displayed after scrolling to bottom");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyebrowPanelDisplayedAfterScrollingToTopHeader(),"Eyebrowpanel is not displayed after scrolling to Top Header");
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink(meetOurCommunity);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("Stories"), "Meet our community link didn't work");
		s_assert.assertFalse(storeFrontHeirloomHomePage.isEyebrowPanelDisplayedAfterScrollingToBottom(),"Eyebrowpanel still displayed after scrolling on meet our community page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyebrowPanelDisplayedAfterScrollingToTopHeader(),"Eyebrowpanel is not displayed after scrolling to Top Header from Meet Our Community page");
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink(programAndIncentives);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("Programs"), "Programs and Incentives link didn't work");
		s_assert.assertFalse(storeFrontHeirloomHomePage.isEyebrowPanelDisplayedAfterScrollingToBottom(),"Eyebrowpanel still displayed after scrolling on program and incentives page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyebrowPanelDisplayedAfterScrollingToTopHeader(),"Eyebrowpanel is not displayed after scrolling to Top Header from program and incentive page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7401
	 * qTest Id: TC-2771
	 */
	@Test(alwaysRun=true,description = "Persistence of eyebrow panel through account management")
	public void testPersistenceOfEyebrowPanelThroughAccountManagement_2771() {
		String consultantEmailID = null;
		String ordersLink="Order History";
		String accountDetailsLink="Account Details";
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String editPWSLink="Edit PWS";
		String shippingDetailsLink="Shipping Details";
		String billingDetailsLink="Billing Details";
		String checkMyPulseLink="Check My Pulse";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		//Verify orders link
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(ordersLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("orderhistory"),"User is not redirected to order history page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(),"Eyebrow section is not visible on UI at orders page");

		// Verify Account Details link
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(accountDetailsLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("my-account"), "User is not redirected to my-account page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(),"Eyebrow section is not visible on UI at My-Account page");

		// Verify Manage CRP & Pulse link
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(),"Eyebrow section is not visible on UI at Manage CRP and Pulse page");

		// Verify Manage Edit PWS link
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(editPWSLink);
		storeFrontHeirloomHomePage.clickRenewLater();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("Options"),"User is not redirected to Edit PWS page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(),"Eyebrow section is not visible on UI at Edit PWS page");

		// Verify Shipping Details link
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(shippingDetailsLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("address-book"), "User is not redirected to Shipping Details page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(),"Eyebrow section is not visible on UI at shipping details page");

		// Verify Billing Details link
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(billingDetailsLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("payment-details"), "User is not redirected to payment-details page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(),"Eyebrow section is not visible on UI at Billing Details page");

		// Verify Pulse link
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(checkMyPulseLink);
		storeFrontHeirloomHomePage.dismissPulsePopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("pulse"), "User is not redirected to pulse page");
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(),"Eyebrow section is not visible on UI at Check My Pulse page");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8050
	 * qTest Id: TC-2854
	 */
	@Test(alwaysRun=true,description = "RC User login via Storefront and verify account management options into the My account header dropdown")
	public void testRCUserLoginViaStorefrontAndVerifyAccountManagementOptionsIntoTheMyAccountHeaderDropdown_2854() {
		String rcEmailID = null;
		String ordersLink="Order History";
		String shippingDetailsLink="Shipping Details";
		String billingDetailsLink="Billing Details";
		String logoutLink="Log Out";
		String orderPageHeaderName ="Order History";
		String currentUrl=null;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}

		storeFrontHeirloomHomePage.loginAsRCUser(rcEmailID, password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSponsorNameContainsRFIndependentConsultant(),"Sponsor name contains Independent Consultant in the Header");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(ordersLink),"Orders Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(shippingDetailsLink),"Shipping Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(billingDetailsLink),"Billing Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(logoutLink),"Log Out Link is not present in My Account Dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(ordersLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("orderhistory"),"User is not redirected to order history page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(orderPageHeaderName),"Page Header doesnot contains: "+orderPageHeaderName);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(shippingDetailsLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("address-book"),"User is not redirected to shipping details page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(shippingDetailsLink),"Page Header doesnot contains: "+orderPageHeaderName);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(billingDetailsLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("payment-details"),"User is not redirected to Billing details page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(billingDetailsLink),"Page Header doesnot contains: "+billingDetailsLink);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(logoutLink);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserLogoutSuccessfully(),"User is not logout successfully");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8050
	 * qTest Id: TC-2855
	 */
	@Test(alwaysRun=true,description = "RC User login via PWS .BIZ and verify account management options into the My account header dropdown")
	public void testRCUserLoginViaPWSBIZAndVerifyAccountManagementOptionsIntoTheMyAccountHeaderDropdown_2855() {
		String rcEmailID = null;
		String ordersLink="Order History";
		String shippingDetailsLink="Shipping Details";
		String billingDetailsLink="Billing Details";
		String logoutLink="Log Out";
		String orderPageHeaderName ="Order History";
		String currentUrl=null;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		openBIZSite();
		storeFrontHeirloomHomePage.loginAsRCUser(rcEmailID, password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSponsorNameContainsRFIndependentConsultant(),"Sponsor name does not contains Independent Consultant in the Header");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(ordersLink),"Orders Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(shippingDetailsLink),"Shipping Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(billingDetailsLink),"Billing Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(logoutLink),"Log Out Link is not present in My Account Dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(ordersLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("orderhistory"),"User is not redirected to order history page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(orderPageHeaderName),"Page Header doesnot contains: "+orderPageHeaderName);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(shippingDetailsLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("address-book"),"User is not redirected to shipping details page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(shippingDetailsLink),"Page Header doesnot contains: "+orderPageHeaderName);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(billingDetailsLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("payment-details"),"User is not redirected to Billing details page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(billingDetailsLink),"Page Header doesnot contains: "+billingDetailsLink);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(logoutLink);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserLogoutSuccessfully(),"User is not logout successfully");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8050
	 * qTest Id: TC-2856
	 */
	@Test(alwaysRun=true,description = "RC User login via PWS .COM and verify account management options into the My account header dropdown")
	public void testRCUserLoginViaPWSCOMAndVerifyAccountManagementOptionsIntoTheMyAccountHeaderDropdown_2856() {
		String rcEmailID = null;
		String ordersLink="Order History";
		String shippingDetailsLink="Shipping Details";
		String billingDetailsLink="Billing Details";
		String logoutLink="Log Out";
		String orderPageHeaderName ="Order History";
		String currentUrl=null;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		openCOMSite();
		storeFrontHeirloomHomePage.loginAsRCUser(rcEmailID, password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSponsorNameContainsRFIndependentConsultant(),"Sponsor name does not contains Independent Consultant in the Header");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(ordersLink),"Orders Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(shippingDetailsLink),"Shipping Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(billingDetailsLink),"Billing Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(logoutLink),"Log Out Link is not present in My Account Dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(ordersLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("orderhistory"),"User is not redirected to order history page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(orderPageHeaderName),"Page Header doesnot contains: "+orderPageHeaderName);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(shippingDetailsLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("address-book"),"User is not redirected to shipping details page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(shippingDetailsLink),"Page Header doesnot contains: "+orderPageHeaderName);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(billingDetailsLink);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("payment-details"),"User is not redirected to Billing details page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(billingDetailsLink),"Page Header doesnot contains: "+billingDetailsLink);

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(logoutLink);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserLogoutSuccessfully(),"User is not logout successfully");
		s_assert.assertAll();
	}
}
