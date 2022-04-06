package com.rf.test.website.storeFront.heirloom.bizPWS.myAccount;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class BillingTest extends RFHeirloomWebsiteMyAccountBaseTest{

	String bizPWS=null;

	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(bizPWS+"/my-account/payment-details");
	}

	@BeforeGroups("myAccountBillingBIZCons")
	public void loginWithConsultantBIZAndNavigateToBillingProfile(){
		logout();
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToBillingProfile();
	}

	@BeforeGroups("myAccountBillingBIZPC")
	public void loginWithPCBIZAndNavigateToBillingProfile(){
		logout();
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToBillingProfile();
	}

	@BeforeGroups("myAccountBillingBIZRC")
	public void loginWithRCBIZAndNavigateToBillingProfile(){
		logout();
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_RC);
		navigateToBillingProfile();
	}

	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2796
	 */
	@Test(priority=1,alwaysRun=true,groups="myAccountBillingBIZCons",description = "Editing card details gets saved(biz site)")
	public void testEditingCardDetailsGetsSavedBizSite_2796() {
		int randomNum = CommonUtils.getRandomNum(99999, 100000);
		String billingProfileName= billingName+randomNum;
		String billingProfileUpdationMessage="Your billing profile has been updated sucessfully.";
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.enterBillingProfileNameAndExpDateAndSave(billingProfileName, expMonth, expYear);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(billingProfileUpdationMessage),"Expected Billing profile updation message is not visible on UI");
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile name is NOT present on page after edit");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getExpirationDateFromExistingBillingProfile(billingProfileName).contains(expMonth+"/"+expYear),"Updated Expiry Month and Year is not present on UI");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2794
	 */
	@Test(priority=2,alwaysRun=true,groups="myAccountBillingBIZCons",description = "Not selecting the checkbox to use billing for future autoships and save(biz site)")
	public void testNotSelectingFutureAutoshipCheckboxBillingEdit_2794() {
		int randomNum = CommonUtils.getRandomNum(99999, 100000);
		String billingProfileName= billingName+randomNum;
		String billingProfileUpdationMessage="Your billing profile has been updated sucessfully.";
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editBillingDetailsWithoutCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, CVV);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile name is NOT edited on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(billingProfileUpdationMessage),"Expected Billing profile updation message is not visible on UI");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2859
	 */
	@Test(priority=3,alwaysRun=true,groups="myAccountBillingBIZCons",description = "PWS .BIZ Consultant,Display the following checkboxes on Billing Details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddBillingForCons_2859() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	} 

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3161
	 */
	@Test(priority=4,alwaysRun=true,groups="myAccountBillingBIZCons",description = "Verify Consolidated checbox for add new billing address (Biz/Cons)")
	public void testTotalCheckboxesWhileAddBillingCons_3161() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3170
	 */
	@Test(priority=5,alwaysRun=true,groups="myAccountBillingBIZCons",description = "Verify Consolidated checbox for edit existing billing address (Biz/Cons)")
	public void testTotalCheckboxesWhileEditBillinCons_3170() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8318
	 * qTest Id: TC-2894
	 */
	@Test(priority=6,alwaysRun=true,groups="myAccountBillingBIZCons",description = "PWS .BIZ Consultant selecting a new Default Billing Address Functionality. ")
	public void testVerifyDefaultBillingFunctionalityForCons_2894() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String myAccountCategory=TestConstantsRFL.EDIT_CRP_CART_CATEGORY;
		String billingProfileName= "TestProfile"+randomNum;
		String defaultProfileName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsAndCancel(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile should not be created after clicking cancel instead of save");
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
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
		billingProfileName=billingProfileFirstName+" "+billingProfileLastName+randomNum;
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on autoship cart");
		s_assert.assertAll();
	} 

	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2860
	 */
	@Test(priority=7,alwaysRun=true,groups="myAccountBillingBIZCons",description = "PWS .BIZ Consultant,Display the following checkboxes on Billing Details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditBillingForCons_2860() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		String myAccountCategory=TestConstantsRFL.EDIT_CRP_CART_CATEGORY;
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, CVV);
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile name is NOT edited on page");
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
	 * qTest Id: TC-3365
	 */
	@Test(priority=8,alwaysRun=true,groups="myAccountBillingBIZCons",description = "Verify while editing the billing profile, CVV and credit card fields are not editable(Biz)")
	public void testVerifyWhileEditBillingCardDetailsNonEditableForCons_3365() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCardNumberFieldNonEditable(),"Card number field should NOT be editable");
	}
	
	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2801
	 */
	@Test(priority=9,alwaysRun=true,groups="myAccountBillingBIZCons",description = "verify fields on existing billing profile page(biz site)")
	public void testVerifyFieldsOnExistingBillingProfilePage_BIZ_2801() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();				
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
	 * qTest Id: TC-2865
	 */
	@Test(priority=10,alwaysRun=true,groups="myAccountBillingBIZPC",description = "PWS .BIZ PC,Display the following checkboxes on Billing Details Page for Add new existing address option. .")
	public void testVerifyCheckboxOnAddBillingForPC_2865() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8318
	 * qTest Id: TC-2897
	 */
	@Test(priority=11,alwaysRun=true,groups="myAccountBillingBIZPC",description = "PWS .BIZ PC selecting a new Default Billing Address Functionality. .")
	public void testVerifyDefaultBillingFunctionalityForPC_2897() {
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
	 * qTest Id: TC-2866
	 */
	@Test(priority=12,alwaysRun=true,groups="myAccountBillingBIZPC",description = "PWS .BIZ PC,Display the following checkboxes on Billing Details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditBillingForPC_2866() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, CVV);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile name is NOT edited on page");
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
	 * qTest Id: TC-3162
	 */
	@Test(priority=13,alwaysRun=true,groups="myAccountBillingBIZPC",description = "Verify Consolidated checbox for add new billing address (Biz/PC)")
	public void testTotalCheckboxesWhileAddBillingPC_3162() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3171
	 */
	@Test(priority=14,alwaysRun=true,groups="myAccountBillingBIZPC",description = "Verify Consolidated checbox for edit existing billing address (Biz/PC)")
	public void testTotalCheckboxesWhileEditBillingPC_3171() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2871
	 */
	@Test(priority=15,alwaysRun=true,groups="myAccountBillingBIZRC",description = "PWS .BIZ RC,Display the following checkboxes on Billing Details Page for Add new existing address option. ")
	public void testVerifyCheckboxOnAddBillingForRC_2871() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8318
	 * qTest Id: TC-2900
	 */
	@Test(priority=16,alwaysRun=true,groups="myAccountBillingBIZRC",description = "PWS .BIZ RC selecting a new Default Billing Address Functionality. ")
	public void testVerifyDefaultBillingFunctionalityRC_2900() {
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
	 * qTest Id: TC-2872
	 */
	@Test(priority=17,alwaysRun=true,groups="myAccountBillingBIZRC",description = "PWS .BIZ RC,Display the following checkboxes on Billing Details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditBillingForRC_2872() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3163
	 */
	@Test(priority=18,alwaysRun=true,groups="myAccountBillingBIZRC",description = "Verify Consolidated checbox for add new billing address (Biz/RC)")
	public void testTotalCheckboxesWhileAddBillingRC_3163() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3172
	 */
	@Test(priority=19,alwaysRun=true,groups="myAccountBillingBIZRC",description = "Verify Consolidated checbox for edit existing billing address (Biz/RC)")
	public void testTotalCheckboxesWhileEditBillingRC_3172() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
}
