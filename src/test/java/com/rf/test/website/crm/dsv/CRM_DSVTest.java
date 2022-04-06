package com.rf.test.website.crm.dsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.test.website.RFDSVCRMWebsiteBaseTest;

public class CRM_DSVTest  extends RFDSVCRMWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(CRM_DSVTest.class.getName());

	//Verify Consultant detail view page for consultant
	@Test(groups = { "consultant" },priority=1)
	public void testVerifyConsultantDetailViewPageForConsultant() {
		s_assert.assertTrue(dsvCRMRFWebsiteBasePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertAll();	
	}

	// Edit Consultant Account details for consultant
	@Test(groups = { "consultant" }, priority = 2)
	public void testEditConsultantAccountDetailsForConsultant() {
		dsvCRMHomePage.clickAccountDetailsButton("Edit Account");
		dsvCRMHomePage.updateAccountNameField(accountNameForConsultant);
		//save Account information
		dsvCRMHomePage.clickSaveBtnUnderAccountDetail();
		s_assert.assertTrue(dsvCRMHomePage.getAccountName().contains(accountNameForConsultant),"Äccount name is not updated!!");
		s_assert.assertAll();
	}

	 // Verify Display of Autoship details for a Consultant
	 @Test(groups = { "consultant" }, priority = 3)
	 public void testVerifyDisplayOfAutoshipDetailsForAConsultant() {
	  s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Name"),"Name mouse hover section is not displayed in Autoships section in account details page");
	  s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Autoship Type"),"Autoship Type mouse hover section is not displayed in Autoships section in account details page");
	  s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Autoship Status"),"Autoship Status mouse hover section is not displayed in Autoships section in account details page");
	  s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Status"),"Status mouse hover section is not displayed in Autoships section in account details page");
	  s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Last OrderDate"),"Last OrderDate mouse hover section is not displayed in Autoships section in account details page");
	  s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Next Order Date"),"Next Order Date mouse hover section is not displayed in Autoships section in account details page");
	  s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Total"),"Total mouse hover section is not displayed in Autoships section in account details page");
	  s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("QV"),"QV mouse hover section is not displayed in Autoships section in account details page");
	  dsvCRMHomePage.getCountAutoships();
	  dsvCRMHomePage.clickAutoships();
	  s_assert.assertTrue(dsvCRMHomePage.getCountAutoships().contains(dsvCRMHomePage.getCountAutoshipNumber()), "Autoships Numbers are not equal");
	  dsvCRMHomePage.clickFirstAutoshipID();
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Autoship Type"),"Autoship Type is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Autoship Status"),"Autoship Status is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Do Not Ship"),"Autoship Do not ship is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Start Date"),"Autoship Start Date is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("End Date"),"Autoship Status is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Completion Date"),"Autoship Completion Date is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Account"),"Autoship Account is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Last OrderDate"),"Autoship Last Order Date is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Next Order Date"),"Autoship Next Order Date is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Is Tax Exempt"),"In Pending Autoship Breakdown Tax Exempt is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("QV"),"In Pending Autoship Breakdown QV is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("CV"),"In Pending Autoship Breakdown CV is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Total Discount"),"In Pending Autoship Breakdown Total Discount is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Sub Total"),"In Pending Autoship Breakdown Sub Total is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Taxable Total"),"In Pending Autoship Breakdown Taxable Total is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Total"),"In Pending Autoship Breakdown Total is not Present");
	  s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Total Tax"),"In Pending Autoship Breakdown Total Tax is not Present");  
	  s_assert.assertAll();
	 }

	// Verify Display of KPI details for a Consultant
	@Test(groups = { "consultant" }, priority = 4)
	public void testVerifyDisplayOfKPIDetailsForAConsultant() {
		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnPerformanceKPIsSectionPresent("Recognized Title"),"Recognized Title label is not present in PerformanceKPIs section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnPerformanceKPIsSectionPresent("Qualification Title"),"Qualification Title label is not present in PerformanceKPIs section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnPerformanceKPIsSectionPresent("Sales Volume"),"Sales Volume label is not present in PerformanceKPIs section");

		s_assert.assertTrue(dsvCRMHomePage.isLabelOnPerformanceKPIsSectionPresent("PSQV"),"PSQV label is not present in PerformanceKPIs section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnPerformanceKPIsSectionPresent("EC Legs"),"EC Legs label is not present in PerformanceKPIs section");
		s_assert.assertFalse(dsvCRMHomePage.verifyActionItemsOnlyViewable(),"Action Item Editable and deletable");
		dsvCRMHomePage.clickPerformanceKPIsName();
		s_assert.assertTrue(dsvCRMHomePage.isPerformanceKPIsDetailsPresent(),"Performance KPIs Detail is not present on UI as expected");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsInformation("Recognized Title"),"Recognized Title label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsInformation("Qualification Title"),"Qualification Title label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsInformation("Sales Volume"),"Sales Volume label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsInformation("Estimated Sales Volume"),"Estimated Sales Volume label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsInformation("PSQV"),"PSQV label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsInformation("Estimated PSQV"),"Estimated PSQV label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsInformation("Period Closed Date"),"Period Closed Date label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsDetails("EC Leg Current"),"Period label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsDetails("EC Leg Prior Month"),"EC Leg Prior Month label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelPresentUnderPerformanceKPIsDetails("LV EC Legs"),"LV EC Legs label is not present under PerformanceKPIs Details Section");
		s_assert.assertAll();
	}	

	// View Billing profile for a consultant
	@Test(groups = { "consultant" }, priority = 5)
	public void testViewBillingProfileForAConsultant() {
		String billingProfilesCount = null;
		String countDisplayedWithBillingLink = null;
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Name"),"Name label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Profile Name"),"Profile Name label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Default"),"Default label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Name On Card"),"Name On Card label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Valid Thru"),"Valid Thru label is not present in Billing profiles section");

		billingProfilesCount = dsvCRMHomePage.getBillingProfilesCount();
		countDisplayedWithBillingLink = dsvCRMHomePage.getCountDisplayedWithLink("Billing");
		s_assert.assertTrue(countDisplayedWithBillingLink.contains(billingProfilesCount), "billing profiles count = "+billingProfilesCount+"while count Displayed With billing Link = "+countDisplayedWithBillingLink);
		s_assert.assertTrue(dsvCRMHomePage.isOnlyOneBillingProfileIsDefault(),"default billing profiles is not one");

		dsvCRMHomePage.clickRandomBillingProfile();
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Profile Name"),"Profile Name label is not present in Billing profile detail section on");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Account"),"Account label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Name On Card"),"Name On Card label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Type"),"Payment Type label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Vendor"),"Payment Vendor label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Default"),"Default label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Valid Thru"),"Valid Thru label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("End Date"),"End Date label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Start Date"),"Start Date label is not present in Billing profile detail section");

		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 1"),"Address Line 1 label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 2"),"Address Line 2 label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 3"),"Address Line 3 label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Locale"),"Locale label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Sub Region"),"Sub Region label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Region"),"Region label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Postal code"),"Postal code label is not present in Billing Address section");

		s_assert.assertTrue(dsvCRMHomePage.isCreditCardOnBillingProfileDetailSectionOnNewTabIsSixteenEncryptedDigit(),"credit card number on billing profile detail section is either not of 16 digit or not encrypted");
		s_assert.assertTrue(dsvCRMHomePage.isExpiryYearOnBillingProfileDetailSectionOnNewTabIsInYYYYFormat(),"expiry year format on billing profile detail section is not yyyy");
		s_assert.assertAll();
	}

	//Verify View Shipping Profile for Consultant
	@Test(groups = { "consultant" },priority=6)
	public void testVerifyViewShippingProfileForConsultant() {
		String shippingProfilesCount  = null;
		String countDisplayedWithShippingLink = null;
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Action"),"Action label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Name"),"Name label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("ProfileName"),"ProfileName label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Default"),"Default label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Address Line 1"),"Address Line 1 label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Address Line 2"),"Address Line 2 label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Locale"),"Locale label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Region"),"Region label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Postal code"),"Postal code label is not present in Shipping address section");
		shippingProfilesCount = dsvCRMHomePage.getShippingProfilesCount();
		countDisplayedWithShippingLink = dsvCRMHomePage.getCountDisplayedWithLink("Shipping Profiles");
		// Verify shipping profile count 
		s_assert.assertTrue(countDisplayedWithShippingLink.contains(shippingProfilesCount), "shipping profiles count = "+shippingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithShippingLink);
		s_assert.assertTrue(dsvCRMHomePage.isOnlyOneShippingProfileIsDefault(),"default shipping profiles is not one");
		s_assert.assertAll();
	}	


	//Verify Add/Edit Shipping Profile for Consultant
	@Test(groups = { "consultant" },priority=7)
	public void testVerifyAddEditShippingProfileForConsultant() {
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypePostalCode = "Postal code";
		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		// Add new Shipping profile
		dsvCRMHomePage.clickAddNewShippingProfileBtn();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileName);
		dsvCRMHomePage.enterShippingAddress(addressLine1, city,stateShortName, postalCode, phoneNumber, countryShortName);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileName), "Newly Added Profile Name Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine1), "Address Line Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postalCode), "Postal Not Matched");

		//verify Edit Shipping profile
		dsvCRMHomePage.clickEditFirstShippingProfile();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileNameUpdated);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileNameUpdated), "Profile Name Not Matched after Editing");
		s_assert.assertAll();
	}

	//Verify the Proxy to my account and pulse for a Consultant
	 @Test(groups = { "consultant" },priority=8)
	 public void testProxyToMyAccountAndPulseForAConsultant() {
	  String parentWindowHandle=null;
	  String accountInfoFirstName=null;
	  String accountInfoLastName=null;
	  String accountFullName=null;
	  String userNameFromPulse=null;
	  //Navigate to Storefront URL
	  dsvCRMHomePage.closeAllOpenedTabs();
	  parentWindowHandle = driver.getWindowHandle();
	  enterTextInSearchFieldAndHitEnter(consultantEmailId);
	  clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
	  dsvCRMHomePage.clickMyAccountButton("My Account");
	  dsvCRMHomePage.switchToChildWindow(parentWindowHandle);
	  accountInfoFirstName=dsvCRMHomePage.getAccountFirstNameFromSF();
	  accountInfoLastName=dsvCRMHomePage.getAccountLastNameFromSF();
	  accountFullName=accountInfoFirstName+" "+accountInfoLastName;
	  s_assert.assertTrue(accountFullName.contains(accountNameForConsultant),"Expected Account name should be: "+accountNameForConsultant+" But on UI is:"+accountFullName);
	  dsvCRMHomePage.clickWelcomeDropDown();
	  dsvCRMHomePage.clickShippingInfoLinkFromWelcomeDropDown();
	  s_assert.assertTrue(dsvCRMHomePage.isShippingProfilePresentonPage(shippingFirstName,""+randomNum2)," shipping profile is not added on the page");
	  dsvCRMHomePage.closeChildAndSwitchToParentWindow(parentWindowHandle);
	  if(driver.getCountry().equalsIgnoreCase("CA")) {
	   dsvCRMHomePage.clickMyAccountButton("Pulse");
	   dsvCRMHomePage.switchToChildWindow(parentWindowHandle);
	   dsvCRMHomePage.clickDismissPulseButton();
	   userNameFromPulse=dsvCRMHomePage.getUserNameFromPulse();
	   s_assert.assertTrue(userNameFromPulse.contains(accountNameForConsultant),"Expected UserName should be: "+accountNameForConsultant+" But actual on UI is: "+userNameFromPulse);
	   dsvCRMHomePage.closeChildAndSwitchToParentWindow(parentWindowHandle);
	  }
	  dsvCRMHomePage.clickMyAccountButton("New Pulse");
	  dsvCRMHomePage.switchToChildWindow(parentWindowHandle);
	  s_assert.assertTrue(dsvCRMHomePage.isUserSuccessfullyLoggedInToNewPulse(),"User is not logged in to 'New Pulse'");
	  dsvCRMHomePage.closeChildAndSwitchToParentWindow(parentWindowHandle);
	  s_assert.assertAll();  
	 }

	//Delete Shipping Profile for Consultant
	@Test(groups = { "consultant" },priority=9)
	public void testDeleteShippingProfileForConsultant() {
		int randomNum = CommonUtils.getRandomNum(1000, 9999);
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypePostalCode = "Postal code";
		String shippingProfileName = TestConstants.DSV_FIRST_NAME_CRM+" "+randomNum;

		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");

		// Add new Shipping profile
		dsvCRMHomePage.clickAddNewShippingProfileBtn();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileName);
		dsvCRMHomePage.enterShippingAddress(addressLine1, city,stateShortName, postalCode, phoneNumber, countryShortName);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileName), "Newly Added Profile Name Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine1), "Address Line Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postalCode), "Postal Not Matched");

		//verify Delete Shipping profile
		dsvCRMHomePage.deleteShippingProfile();
		dsvCRMHomePage.clickOKOnDeleteDefaultShippingProfilePopUp();
		s_assert.assertTrue(dsvCRMHomePage.isNonDefaultShippingProfileDeleted(shippingProfileName),"Shipping profile still present after deleting!!!");
		s_assert.assertAll();
	}

	//Verify PC detail view page
	@Test(groups = { "pc" },priority=10)
	public void testVerifyDetailViewPageForPC() {
		s_assert.assertTrue(dsvCRMRFWebsiteBasePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertAll();	
	}


	// Edit PC Account details
	@Test(groups = { "pc" }, priority = 11)
	public void testEditAccountDetailsForPC() {
		dsvCRMHomePage.clickAccountDetailsButton("Edit Account");
		dsvCRMHomePage.updateAccountNameField(accountNameForPC);
		//save Account information
		dsvCRMHomePage.clickSaveBtnUnderAccountDetail();
		s_assert.assertTrue(dsvCRMHomePage.getAccountName().contains(accountNameForPC),"Äccount name is not updated!!");
		s_assert.assertAll();
	}

	// Verify Display of Autoship details for a PC
	@Test(groups = { "pc" }, priority = 12)
	public void testVerifyDisplayOfAutoshipDetailsForAPC() {
		s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Name"),"Name mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Autoship Type"),"Autoship Type mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Autoship Status"),"Autoship Status mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Status"),"Status mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("Total"),"Total mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(dsvCRMHomePage.isMouseHoverAutoshipSectionPresentOfFields("QV"),"QV mouse hover section is not displayed in Autoships section in account details page");
		dsvCRMHomePage.getCountAutoships();
		dsvCRMHomePage.clickAutoships();
		s_assert.assertTrue(dsvCRMHomePage.getCountAutoships().equals(dsvCRMHomePage.getCountAutoshipNumber()), "Autoships Numbers are not equal");
		dsvCRMHomePage.clickFirstAutoshipID();
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Autoship Number"),"Autoship Number is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Autoship Type"),"Autoship Type is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Autoship Status"),"Autoship Status is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Do Not Ship"),"Autoship Do not ship is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Start Date"),"Autoship Start Date is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("End Date"),"Autoship Status is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderAutoshipNumberPresent("Completion Date"),"Autoship Completion Date is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("QV"),"In Pending Autoship Breakdown QV is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("CV"),"In Pending Autoship Breakdown CV is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Total Discount"),"In Pending Autoship Breakdown Total Discount is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Sub Total"),"In Pending Autoship Breakdown Sub Total is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Taxable Total"),"In Pending Autoship Breakdown Taxable Total is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Total"),"In Pending Autoship Breakdown Total is not Present");
		s_assert.assertTrue(dsvCRMHomePage.isLabelUnderPendingAutoshipBreakdownPresent("Total Tax"),"In Pending Autoship Breakdown Total Tax is not Present");  
		s_assert.assertAll();
	}

	// Verify View Billing profile for a PC
	@Test(groups = { "pc" }, priority = 13)
	public void testVerifyViewBillingProfileForAPC() {
		String billingProfilesCount = null;
		String countDisplayedWithBillingLink = null;
		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Name"),"Name label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Profile Name"),"Profile Name label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Default"),"Default label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Name On Card"),"Name On Card label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Valid Thru"),"Valid Thru label is not present in Billing profiles section");
		billingProfilesCount = dsvCRMHomePage.getBillingProfilesCount();
		countDisplayedWithBillingLink = dsvCRMHomePage.getCountDisplayedWithLink("Billing");
		s_assert.assertTrue(countDisplayedWithBillingLink.contains(billingProfilesCount), "billing profiles count = "+billingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithBillingLink);
		dsvCRMHomePage.clickRandomBillingProfile();
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Profile Name"),"Profile Name label is not present in Billing profile detail section on");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Name On Card"),"Name On Card label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Type"),"Payment Type label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Default"),"Default label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isCreditCardOnBillingProfileDetailSectionOnNewTabIsSixteenEncryptedDigit(),"credit card number on billing profile detail section is either not of 16 digit or not encrypted");
		s_assert.assertTrue(dsvCRMHomePage.isExpiryYearOnBillingProfileDetailSectionOnNewTabIsInYYYYFormat(),"expiry year format on billing profile detail section is not yyyy");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 1"),"Address Line 1 label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 3"),"Address Line 3 label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Locale"),"Locale label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Region"),"Region label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Postal code"),"Postal code label is not present in Billing Address section");
		s_assert.assertAll();
	}

	//Verify View Shipping Profile for PC
	@Test(groups = { "pc" },priority=14)
	public void testVerifyViewShippingProfileForPC() {
		String shippingProfilesCount  = null;
		String countDisplayedWithShippingLink = null;
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Action"),"Action label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Name"),"Name label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("ProfileName"),"ProfileName label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Default"),"Default label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Address Line 1"),"Address Line 1 label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Address Line 2"),"Address Line 2 label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Locale"),"Locale label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Region"),"Region label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Postal code"),"Postal code label is not present in Shipping address section");
		shippingProfilesCount = dsvCRMHomePage.getShippingProfilesCount();
		countDisplayedWithShippingLink = dsvCRMHomePage.getCountDisplayedWithLink("Shipping Profiles");
		s_assert.assertTrue(countDisplayedWithShippingLink.contains(shippingProfilesCount), "shipping profiles count = "+shippingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithShippingLink);
		s_assert.assertTrue(dsvCRMHomePage.isOnlyOneShippingProfileIsDefault(),"default shipping profiles is not one");
		s_assert.assertAll();
	}	

	//Verify Add/Edit Shipping Profile for PC
	@Test(groups = { "pc" },priority=15)
	public void testVerifyAddEditShippingProfileForPC() {
		int randomNum = CommonUtils.getRandomNum(1000, 9999);
		int randomNum2 = CommonUtils.getRandomNum(1000, 9999);
		String parentWindowHandle=null;
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypePostalCode = "Postal code";
		String shippingProfileName = TestConstants.DSV_FIRST_NAME_CRM+" "+randomNum;
		String shippingProfileNameUpdated = TestConstants.DSV_FIRST_NAME_CRM+" "+randomNum2;
		String shippingFirstName=TestConstants.DSV_FIRST_NAME_CRM;
		String shippingLastName=""+randomNum2;

		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");

		// Add new Shipping profile
		dsvCRMHomePage.clickAddNewShippingProfileBtn();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileName);
		dsvCRMHomePage.enterShippingAddress(addressLine1, city,stateShortName, postalCode, phoneNumber, countryShortName);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileName), "Newly Added Profile Name Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine1), "Address Line Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postalCode), "Postal Not Matched");

		//verify Edit Shipping profile for PC
		dsvCRMHomePage.clickEditFirstShippingProfile();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileNameUpdated);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileNameUpdated), "Profile Name Not Matched after Editing");

		//Navigate to Storefront and verify newly created shipping profile
		dsvCRMHomePage.closeAllOpenedTabs();
		parentWindowHandle = driver.getWindowHandle();
		enterTextInSearchFieldAndHitEnter(pcEmailId);
		clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		dsvCRMHomePage.clickMyAccountButton("My Account");
		dsvCRMHomePage.switchToChildWindow(parentWindowHandle);
		dsvCRMHomePage.clickWelcomeDropDown();
		dsvCRMHomePage.clickShippingInfoLinkFromWelcomeDropDown();
		s_assert.assertTrue(dsvCRMHomePage.isShippingProfilePresentonPage(shippingFirstName,shippingLastName)," shipping profile is not added on the page");
		dsvCRMHomePage.closeChildAndSwitchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	//Delete Shipping Profile for PC
	@Test(groups = { "pc" },priority=16)
	public void testDeleteShippingProfileForPC() {
		int randomNum = CommonUtils.getRandomNum(1000, 9999);
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypePostalCode = "Postal code";
		String shippingProfileName = TestConstants.DSV_FIRST_NAME_CRM+" "+randomNum;

		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");

		// Add new Shipping profile
		dsvCRMHomePage.clickAddNewShippingProfileBtn();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileName);
		dsvCRMHomePage.enterShippingAddress(addressLine1, city,stateShortName, postalCode, phoneNumber, countryShortName);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileName), "Newly Added Profile Name Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine1), "Address Line Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postalCode), "Postal Not Matched");

		//verify Delete Shipping profile
		dsvCRMHomePage.deleteShippingProfile();
		dsvCRMHomePage.clickOKOnDeleteDefaultShippingProfilePopUp();
		s_assert.assertTrue(dsvCRMHomePage.isNonDefaultShippingProfileDeleted(shippingProfileName),"Shipping profile still present after deleting!!!");
		s_assert.assertAll();
	}

	//Verify the Proxy to my account for a PC
	@Test(groups = { "pc" },priority=17)
	public void testProxyToMyAccountForAPC() {
		String parentWindowHandle=null;
		String accountInfoFirstName=null;
		String accountInfoLastName=null;
		String accountFullName=null;
		//Navigate to Storefront URL
		dsvCRMHomePage.closeAllOpenedTabs();
		parentWindowHandle = driver.getWindowHandle();
		enterTextInSearchFieldAndHitEnter(pcEmailId);
		clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		dsvCRMHomePage.clickMyAccountButton("My Account");
		dsvCRMHomePage.switchToChildWindow(parentWindowHandle);
		accountInfoFirstName=dsvCRMHomePage.getAccountFirstNameFromSF();
		accountInfoLastName=dsvCRMHomePage.getAccountLastNameFromSF();
		accountFullName=accountInfoFirstName+" "+accountInfoLastName;
		s_assert.assertTrue(accountFullName.contains(accountNameForPC),"Expected Account name should be: "+accountNameForPC+" But on UI is:"+accountFullName);
		dsvCRMHomePage.closeChildAndSwitchToParentWindow(parentWindowHandle);
		s_assert.assertAll();		
	} 

	//Verify detail view page for RC
	@Test(groups = { "rc" },priority=18)
	public void testVerifyDetailViewPageForRC() {
		s_assert.assertTrue(dsvCRMRFWebsiteBasePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertAll();	
	}

	// Edit RC Account details
	@Test(groups = { "rc" }, priority = 19)
	public void testEditAccountDetailsForRC() {
		dsvCRMHomePage.clickAccountDetailsButton("Edit Account");
		dsvCRMHomePage.updateAccountNameField(accountNameForRC);
		//save Account information
		dsvCRMHomePage.clickSaveBtnUnderAccountDetail();
		s_assert.assertTrue(dsvCRMHomePage.getAccountName().contains(accountNameForRC),"Äccount name is not updated!!");
		s_assert.assertAll();
	}

	// Verify View Billing profile for a RC
	@Test(groups = { "rc" }, priority = 20)
	public void testVerifyViewBillingProfileForARC() {
		String billingProfilesCount = null;
		String countDisplayedWithBillingLink = null;
		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Profile Name"),"Profile Name label is not present in Billing profiles section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionPresent("Name On Card"),"Name On Card label is not present in Billing profiles section");
		billingProfilesCount = dsvCRMHomePage.getBillingProfilesCount();
		countDisplayedWithBillingLink = dsvCRMHomePage.getCountDisplayedWithLink("Billing");
		s_assert.assertTrue(countDisplayedWithBillingLink.contains(billingProfilesCount), "billing profiles count = "+billingProfilesCount+"while count Displayed With Billing profiles Link = "+countDisplayedWithBillingLink);
		dsvCRMHomePage.clickRandomBillingProfile();
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Profile Name"),"Profile Name label is not present in Billing profile detail section on");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Account"),"Account label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Default"),"Default label is not present in Billing profile detail section");
		s_assert.assertTrue(dsvCRMHomePage.isCreditCardOnBillingProfileDetailSectionOnNewTabIsSixteenEncryptedDigit(),"credit card number on billing profile detail section is either not of 16 digit or not encrypted");
		s_assert.assertTrue(dsvCRMHomePage.isExpiryYearOnBillingProfileDetailSectionOnNewTabIsInYYYYFormat(),"expiry year format on billing profile detail section is not yyyy");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 1"),"Address Line 1 label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 3"),"Address Line 3 label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Locale"),"Locale label is not present in Billing Address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnBillingAddressSectionOnNewTabPresent("Region"),"Region label is not present in Billing Address section");
		s_assert.assertAll();
	}	

	//Verify View Shipping Profile for RC
	@Test(groups = { "rc" },priority=21)
	public void testVerifyViewShippingProfileForRC() {
		String shippingProfilesCount  = null;
		String countDisplayedWithShippingLink = null;
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Action"),"Action label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Name"),"Name label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("ProfileName"),"ProfileName label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Default"),"Default label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Address Line 1"),"Address Line 1 label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Address Line 2"),"Address Line 2 label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Locale"),"Locale label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Region"),"Region label is not present in Shipping address section");
		s_assert.assertTrue(dsvCRMHomePage.isLabelOnShippingAddressSectionPresent("Postal code"),"Postal code label is not present in Shipping address section");
		shippingProfilesCount = dsvCRMHomePage.getShippingProfilesCount();
		countDisplayedWithShippingLink = dsvCRMHomePage.getCountDisplayedWithLink("Shipping Profiles");
		countDisplayedWithShippingLink = countDisplayedWithShippingLink.split("\\+")[0];
		s_assert.assertTrue(countDisplayedWithShippingLink.contains(shippingProfilesCount), "shipping profiles count = "+shippingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithShippingLink);
		s_assert.assertAll();
	}	

	//Verify Add/Edit Shipping Profile for RC
	@Test(groups = { "rc" },priority=22)
	public void testVerifyAddEditShippingProfileForRC() {
		int randomNum = CommonUtils.getRandomNum(1000, 9999);
		int randomNum2 = CommonUtils.getRandomNum(1000, 9999);
		String parentWindowHandle=null;
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypePostalCode = "Postal code";
		String shippingProfileName = TestConstants.DSV_FIRST_NAME_CRM+" "+randomNum;
		String shippingProfileNameUpdated = TestConstants.DSV_FIRST_NAME_CRM+" "+randomNum2;
		String shippingFirstName=TestConstants.DSV_FIRST_NAME_CRM;
		String shippingLastName=""+randomNum2;

		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");

		// Add new Shipping profile
		dsvCRMHomePage.clickAddNewShippingProfileBtn();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileName);
		dsvCRMHomePage.enterShippingAddress(addressLine1, city,stateShortName, postalCode, phoneNumber, countryShortName);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileName), "Newly Added Profile Name Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine1), "Address Line Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postalCode), "Postal Not Matched");

		//verify Edit Shipping profile for RC
		dsvCRMHomePage.clickEditFirstShippingProfile();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileNameUpdated);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileNameUpdated), "Profile Name Not Matched after Editing");

		//Navigate to Storefront URL
		dsvCRMHomePage.closeAllOpenedTabs();
		parentWindowHandle = driver.getWindowHandle();
		enterTextInSearchFieldAndHitEnter(rcEmailId);
		clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		dsvCRMHomePage.clickMyAccountButton("My Account");
		dsvCRMHomePage.switchToChildWindow(parentWindowHandle);
		dsvCRMHomePage.clickWelcomeDropDown();
		dsvCRMHomePage.clickShippingInfoLinkFromWelcomeDropDown();
		s_assert.assertTrue(dsvCRMHomePage.isShippingProfilePresentonPage(shippingFirstName,shippingLastName)," shipping profile is not added on the page");
		dsvCRMHomePage.closeChildAndSwitchToParentWindow(parentWindowHandle);		
		s_assert.assertAll();
	}

	//Delete Shipping Profile for RC
	@Test(groups = { "rc" },priority=23)
	public void testDeleteShippingProfileForRC() {
		int randomNum = CommonUtils.getRandomNum(1000, 9999);
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypePostalCode = "Postal code";
		String shippingProfileName = TestConstants.DSV_FIRST_NAME_CRM+" "+randomNum;

		s_assert.assertTrue(dsvCRMHomePage.isAccountDetailsPagePresent(),"Account Details page has not displayed");

		// Add new Shipping profile
		dsvCRMHomePage.clickAddNewShippingProfileBtn();
		dsvCRMHomePage.updateShippingProfileName(shippingProfileName);
		dsvCRMHomePage.enterShippingAddress(addressLine1, city,stateShortName, postalCode, phoneNumber, countryShortName);
		dsvCRMHomePage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(dsvCRMHomePage.isProfileNameValueOfDefaultShippingProfilesPresent(shippingProfileName), "Newly Added Profile Name Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine1), "Address Line Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(dsvCRMHomePage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postalCode), "Postal Not Matched");

		//verify Delete Shipping profile
		dsvCRMHomePage.deleteShippingProfile();
		dsvCRMHomePage.clickOKOnDeleteDefaultShippingProfilePopUp();
		s_assert.assertTrue(dsvCRMHomePage.isNonDefaultShippingProfileDeleted(shippingProfileName),"Shipping profile still present after deleting!!!");
		s_assert.assertAll();
	}

	//Verify the Proxy to my account for a RC
	@Test(groups = { "rc" },priority=24)
	public void testProxyToMyAccountForARC() {
		String parentWindowHandle=null;
		String accountInfoFirstName=null;
		String accountInfoLastName=null;
		String accountFullName=null;

		//Navigate to Storefront URL
		dsvCRMHomePage.closeAllOpenedTabs();
		parentWindowHandle = driver.getWindowHandle();
		enterTextInSearchFieldAndHitEnter(rcEmailId);
		clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		dsvCRMHomePage.clickMyAccountButton("My Account");
		dsvCRMHomePage.switchToChildWindow(parentWindowHandle);
		accountInfoFirstName=dsvCRMHomePage.getAccountFirstNameFromSF();
		accountInfoLastName=dsvCRMHomePage.getAccountLastNameFromSF();
		accountFullName=accountInfoFirstName+" "+accountInfoLastName;
		s_assert.assertTrue(accountFullName.contains(accountNameForRC),"Expected Account name should be: "+accountNameForRC+" But on UI is:"+accountFullName);
		dsvCRMHomePage.closeChildAndSwitchToParentWindow(parentWindowHandle);
		s_assert.assertAll();		
	} 

}
