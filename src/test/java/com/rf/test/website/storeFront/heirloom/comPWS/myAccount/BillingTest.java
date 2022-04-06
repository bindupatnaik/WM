package com.rf.test.website.storeFront.heirloom.comPWS.myAccount;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class BillingTest extends RFHeirloomWebsiteMyAccountBaseTest{

	String comPWS=null; 
	
	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(comPWS+"/my-account/payment-details");
	}
	
	@BeforeGroups("myAccountBillingCOMCons")
	public void loginWithConsultantCOMAndNavigateToBillingProfile(){
		logout();
		comPWS = openCOMSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToBillingProfile();
	}

	@BeforeGroups("myAccountBillingCOMPC")
	public void loginWithPCCOMAndNavigateToBillingProfile(){
		logout();
		comPWS = openCOMSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToBillingProfile();
	}

	@BeforeGroups("myAccountBillingCOMRC")
	public void loginWithRCCOMAndNavigateToBillingProfile(){
		logout();
		comPWS = openCOMSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_RC);
		navigateToBillingProfile();
	}
	
	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2797
	 */
	@Test(priority=1,alwaysRun=true,groups="myAccountBillingCOMCons",description = "Editing card details gets saved(com site)")
	public void testEditingCardDetailsGetsSavedComSite_2797() {
		int randomNum = CommonUtils.getRandomNum(99999, 100000);
		String billingProfileName= billingName+randomNum;
		String billingProfileUpdationMessage="Your billing profile has been updated sucessfully.";
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.enterBillingProfileNameAndExpDateAndSave(billingProfileName, expMonth, expYear);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(billingProfileUpdationMessage),"Expected Billing profile updation message is not visible on UI");
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile name is NOT edited on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getExpirationDateFromExistingBillingProfile(billingProfileName).contains(expMonth+"/"+expYear),"Updated Expiry Month and Year is not present on UI");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2861
	 */
	@Test(priority=2,alwaysRun=true,groups="myAccountBillingCOMCons",description = "PWS .COM Consultant,Display the following checkboxes on Billing Details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddBillingForCons_2861() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	} 
	
	/**
	 * Jira Story Id: MAIN-8318
	 * qTest Id: TC-2895
	 */
	@Test(priority=3,alwaysRun=true,groups="myAccountBillingCOMCons",description = "PWS .COM Consultant selecting a new Default Billing Address Functionality.")
	public void testVerifyDefaultBillingFunctionalityForCons_2895() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String myAccountCategory=TestConstantsRFL.EDIT_CRP_CART_CATEGORY;
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		int randomNum1 = CommonUtils.getRandomNum(9999, 10000000);
		billingProfileFirstName = billingProfileFirstName+randomNum1;
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsAndCancel(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile should not be created after clicking cancel instead of save");
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on billing page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, billingProfileName,"the billing name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on adhoc cart");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileFirstName),"Newly created and default billing profile name not present on autoship cart");
		s_assert.assertAll();
	} 
	
	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3164
	 */
	@Test(priority=4,alwaysRun=true,groups="myAccountBillingCOMCons",description = "Verify Consolidated checbox for add new billing address (Com/Cons)")
	public void testTotalCheckboxesWhileAddBillingCons_3164() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3173
	 */
	@Test(priority=5,alwaysRun=true,groups="myAccountBillingCOMCons",description = "Verify Consolidated checbox for edit existing billing address (Com/Cons)")
	public void testTotalCheckboxesWhileEditBillinCons_3173() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2795
	 */
	@Test(priority=6,alwaysRun=true,groups="myAccountBillingCOMCons",description = "Not selecting the checkbox to use billing for future autoships and save(com site)")
	public void testNotSelectingFutureAutoshipCheckboxBillingEdit_2795() {
		int randomNum = CommonUtils.getRandomNum(99999, 100000);
		String billingProfileName= billingName+randomNum;
		String billingProfileUpdationMessage="Your billing profile has been updated sucessfully.";
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editBillingDetailsWithoutCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, CVV);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile name is NOT edited on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(billingProfileUpdationMessage),"Expected Billing profile updation message is not visible on UI");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2862
	 */
	@Test(priority=7,alwaysRun=true,groups="myAccountBillingCOMCons",description = "PWS .COM Consultant,Display the following checkboxes on Billing Details Page for edit existing address option. .")
	public void testVerifyCheckboxOnEditBillingForCons_2862() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		String myAccountCategory=TestConstantsRFL.EDIT_CRP_CART_CATEGORY;
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, CVV);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on billing page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, billingProfileName,"the billing name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on adhoc cart");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		driver.pauseExecutionFor(3000);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on autoship cart");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8508
	 * qTest Id: TC-3364
	 */
	@Test(priority=8,alwaysRun=true,groups="myAccountBillingCOMCons",description = "Verify while editing the billing profile, CVV and credit card fields are not editable(Com)")
	public void testVerifyWhileEditBillingCardDetailsNonEditableForCons_3364() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCardNumberFieldNonEditable(),"Card number field should NOT be editable");
	}
	
	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2802
	 */
	@Test(priority=9,alwaysRun=true,groups="myAccountBillingCOMCons",description = "verify fields on existing billing profile page(com site)")
	public void testVerifyFieldsOnExistingBillingProfilePage_COM_2802() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();	
		driver.pauseExecutionFor(5000);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isBillingAddress1Displayed(),"Billing Address not displayed");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingAddress1Displayed().length()>0,"Billing Address text is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCreditCardPresentOnBillingPage()," Credit card is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getCreditCardPresentOnBillingPage().length()==16,"credit Card info is not present on Page_1");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getCreditCardPresentOnBillingPage().contains("************"),"credit Card info is not present on Page_2");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isMonthNamePresentOnBillingPage(),"Expiry Month Name is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getMonthNamePresentOnBillingPage().getText().length()>0,"Expiry Month Text is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isExpiryYearPresentOnBillingPage(),"Expiry Year is not present on page");	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getExpiryYearPresentOnBillingPage().getText().length()>0,"Expiry Year text is not present on Page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");		
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCVVPresentOnBillingPage(),"CVV is not present on page");	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getCVVPresentOnBillingPage().length()==0,"CVV text is present on page");
		s_assert.assertAll();
		
	}
	
	
	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2867
	 */
	@Test(priority=10,alwaysRun=true,groups="myAccountBillingCOMPC",description = "PWS .COM PC,Display the following checkboxes on Billing Details Page for Add New existing address option. ")
	public void testVerifyCheckboxOnAddBillingForPC_2867() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8318
	 * qTest Id: TC-2898
	 */
	@Test(priority=11,alwaysRun=true,groups="myAccountBillingCOMPC",description = "PWS .COM PC selecting a new Default Billing Address Functionality. ")
	public void testVerifyDefaultBillingFunctionalityForPC_2898() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsAndCancel(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile should not be created after clicking cancel instead of save");
		
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on billing page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, billingProfileName,"the billing name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on adhoc cart");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2868
	 */
	@Test(priority=12,alwaysRun=true,groups="myAccountBillingCOMPC",description = "PWS .COM PC,Display the following checkboxes on Billing Details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditBillingForPC_2868() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, CVV);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on billing page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, billingProfileName,"the billing name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on adhoc cart");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3165
	 */
	@Test(priority=13,alwaysRun=true,groups="myAccountBillingCOMPC",description = "Verify Consolidated checbox for add new billing address (Com/PC)")
	public void testTotalCheckboxesWhileAddBillingPC_3165() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3174
	 */
	@Test(priority=14,alwaysRun=true,groups="myAccountBillingCOMPC",description = "Verify Consolidated checbox for edit existing billing address (Com/PC)")
	public void testTotalCheckboxesWhileEditBillingPC_3174() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2873
	 */
	@Test(priority=15,alwaysRun=true,groups="myAccountBillingCOMRC",description = "PWS .COM RC,Display the following checkboxes on Billing Details Page for Add New existing address option. ")
	public void testVerifyCheckboxOnAddBillingForRC_2873() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8318
	 * qTest Id: TC-2901
	 */
	@Test(priority=16,alwaysRun=true,groups="myAccountBillingCOMRC",description = "PWS .COM RC selecting a new Default Billing Address Functionality.")
	public void testSelectingANewDefaultBillingAddressFunctionalityPWSCOMRC_2901() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsAndCancel(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile should not be created after clicking cancel instead of save");
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on billing page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, billingProfileName,"the billing name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on adhoc cart");
		s_assert.assertAll();
	}
	
	
	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2874
	 */
	@Test(priority=17,alwaysRun=true,groups="myAccountBillingCOMRC",description = "PWS .COM RC,Display the following checkboxes on Billing Details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditBillingForRC_2874() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, CVV);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on billing page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, billingProfileName,"the billing name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on adhoc cart");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3166
	 */
	@Test(priority=18,alwaysRun=true,groups="myAccountBillingCOMRC",description = "Verify Consolidated checbox for add new billing address (Com/RC)")
	public void testTotalCheckboxesWhileAddBillingRC_3166() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3175
	 */
	@Test(priority=19,alwaysRun=true,groups="myAccountBillingCOMRC",description = "Verify Consolidated checbox for edit existing billing address (Com/RC)")
	public void testTotalCheckboxesWhileEditBillingRC_3175() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	

}
