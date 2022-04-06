package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class BillingTest extends RFHeirloomWebsiteMyAccountBaseTest{

	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(driver.getURL()+"/my-account/payment-details");
	}

	@BeforeGroups("myAccountBillingCorpCons")
	public void loginWithConsultantCorpAndNavigateToBillingProfile(){
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToBillingProfile();
	}

	@BeforeGroups("myAccountBillingCorpPC")
	public void loginWithPCCorpAndNavigateToBillingProfile(){
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToBillingProfile();
	}

	@BeforeGroups("myAccountBillingCorpRC")
	public void loginWithRCCorpAndNavigateToBillingProfile(){
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_RC);
		navigateToBillingProfile();
	}

	/**
	 * Jira Story Id: MAIN-7402
	 * qTest Id: TC-2810
	 */
	@Test(priority=1,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Billing Details page verificaiton - Tokenization")
	public void testBillingDetailsPageVerificaitonTokenization_2810() {
		int randomNum = CommonUtils.getRandomNum(99999, 100000);
		String billingProfileName= billingName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on page");
		//	s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getCardNumberOfTheBillingProfile(billingName).contains("************"+cardNumber.substring(cardNumber.length()-5, cardNumber.length())),"card number displayed is not as expected");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7402
	 * qTest Id: TC-2811
	 */
	@Test(priority=2,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Billing Details page verificaiton - Default selection")
	public void testBillingDetailsPageVerificationDefaultSelection_2811() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		int billingProfilesCount=0;
		String nonDefaultProfileName=null;
		String defaultProfileName=null;
		billingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfilesCount();
		if(billingProfilesCount<2) {
			storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
			storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on page");
		}
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		nonDefaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getFirstNonDefaultProfileName();
		storeFrontHeirloomBillingAndShippingProfilePage.selectShippingOrBillingProfileAsDefault(nonDefaultProfileName);
		storeFrontHeirloomBillingAndShippingProfilePage.clickSaveButtonOnOverlay();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyBillingOrShippingProfileSelectedAsDefault(nonDefaultProfileName),"Newly selected billing profile as default, Not present on page");
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyBillingOrShippingProfileSelectedAsDefault(defaultProfileName),"Previously selected default billing profile is still default");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3158
	 */
	@Test(priority=3,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Verify Consolidated checbox for add new billing address (Corp/consultant)")
	public void testTotalCheckboxesWhileAddBillingCons_3158() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3167
	 */
	@Test(priority=4,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Verify Consolidated checbox for edit existing billing address (Corp/consultant)")
	public void testTotalCheckboxesWhileEditBillingCons_3167() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7402
	 * qTest Id: TC-2808
	 */
	@Test(priority=5,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Billing Details page verificaiton - Basic")
	public void testBillingDetailsPageVerificaitonBasic_2808() {
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isHeaderContainsBillingDetailsText(),"Header doesn't contains 'Billing Details'");	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isSubHeaderContainsBillingDetailsText(),"Sub Header doesn't contains 'Billing Profiles'");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7402
	 * qTest Id: TC-2809
	 */
	@Test(priority=6,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Billing Details page verificaiton - Fields and Controls")
	public void testBillingDetailsPageVerificaitonFieldsAndControls_2809() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isBillingProfileNamePresentOnPage(),"Billing Profile Name is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCardHolderNamePresentOnBillingPage(),"Card Holder Name is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCreditCardPresentOnBillingPage()," Credit card is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isMonthNamePresentOnBillingPage(),"Month Name is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCVVPresentOnBillingPage(),"CVV Name is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isExpiryYearPresentOnBillingPage(),"Expiry Year is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForBillingProfileForFutureOrdersPresentOnBillingPage(),"Checkbox for Billing Profile For Future Order is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isSaveButtonPresentOnBillingPage(),"Save Button is not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCancelButtonPresentOnBillingPage(),"Cancel Button is not present on page");
		//add all assertions for Billing Address
		s_assert.assertAll();
	} 

	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2792
	 */
	@Test(priority=7,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Editing card details gets saved(corp site)")
	public void testEditingCardDetailsGetsSavedCorpSite_2792() {
		int randomNum = CommonUtils.getRandomNum(99999, 100000);
		String billingProfileName= billingName+randomNum;
		String billingProfileUpdationMessage="Your billing profile has been updated sucessfully.";
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.enterBillingProfileNameAndExpDateAndSave(billingProfileName, expMonth, expYear);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfileUpdationMessage().contains(billingProfileUpdationMessage),"Expected Billing profile updation message is not visible on UI");
		//s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getCreditCardNumberFromExistingBillingProfileName().contains("************"+""+cardNumberLast4Digit),"Expected Credit card is not present on UI");
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Billing profile name is NOT edited on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getExpirationDateFromExistingBillingProfile(billingProfileName).contains(expMonth+"/"+expYear),"Updated Expiry Month and Year is not present on UI");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2791
	 */
	@Test(priority=8,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Not selecting the checkbox to use billing for future autoships and save(corp site)")
	public void testNotSelectingFutureAutoshipCheckboxBillingEdit_2791() {
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
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2793
	 */
	@Test(priority=9,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Selecting another billing address as default(corp site)")
	public void testSelectAnotherBillingAdddressCorp_2793() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		int billingProfilesCount=0;
		String nonDefaultProfileName=null;
		billingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getBillingProfilesCount();
		if(billingProfilesCount<2) {
			storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
			storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on page");
		}
		nonDefaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getFirstNonDefaultProfileName();
		storeFrontHeirloomBillingAndShippingProfilePage.selectShippingOrBillingProfileAsDefault(nonDefaultProfileName);
		storeFrontHeirloomBillingAndShippingProfilePage.clickSaveButtonOnOverlay();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyBillingOrShippingProfileSelectedAsDefault(nonDefaultProfileName),"Newly selected billing profile as default, Not present on page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2857
	 */
	@Test(priority=10,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Storefront Consultant,Display the following checkboxes on Billing Details Page for Add new Address option.")
	public void testVerifyCheckboxOnAddBillingForCons_2857() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	} 

	/**
	 * Jira Story Id: MAIN-8318
	 * qTest Id: TC-2893
	 */
	@Test(priority=11,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Storefront Consultant selecting a new Default Billing Address Functionality.")
	public void testVerifyDefaultBillingFunctionalityForCons_2893() {
		String myAccountCategory=TestConstantsRFL.EDIT_CRP_CART_CATEGORY;
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		int randomNum1 = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		billingProfileFirstName = billingProfileFirstName+randomNum1;
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
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileFirstName),"Newly created and default billing profile name not present on autoship cart");
		s_assert.assertAll();
	} 

	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2858
	 */
	@Test(priority=12,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Storefront Consultant,Display the following checkboxes on Billing Details Page for edit existing address option.")
	public void testVerifyCheckboxOnEditBillingForCons_2858() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum;
		String defaultProfileName=null;
		String myAccountCategory=TestConstantsRFL.EDIT_CRP_CART_CATEGORY;
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
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		driver.pauseExecutionFor(3000);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created and default billing profile name not present on autoship cart");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8508
	 * qTest Id: TC-3363
	 */
	@Test(priority=13,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Verify while editing the billing profile, CVV and credit card fields are not editable(Corp)")
	public void testVerifyWhileEditBillingCardDetailsNonEditableForCons_3363() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCardNumberFieldNonEditable(),"Card number field should NOT be editable");
	}

	/**
	 * Jira Story Id: MAIN-5265
	 * qTest Id: TC-2800
	 */
	@Test(priority=14,alwaysRun=true,groups="myAccountBillingCorpCons",description = "verify fields on existing billing profile page(corp site)")
	public void testVerifyFieldsOnExistingBillingProfilePage_CORP_2800() {
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
	 * Jira Story Id: MAIN-5266
	 * qTest Id: TC-2805
	 */
	@Test(priority=15,alwaysRun=true,groups="myAccountBillingCorpCons",description = "Verify error message for not entering mandatory fields on billing profile page")
	public void testVerifyErrorMessageForNotEnteringMandatoryFieldsOnBillingProfilePage_2805() {
		List<WebElement> listOfMandatoryFieldsInBillingDetailsPageErrors;		
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.clickSaveBillingAddressButton();
		listOfMandatoryFieldsInBillingDetailsPageErrors=storeFrontHeirloomBillingAndShippingProfilePage.getErrorMessagesForAllMandatoryFieldsInBilling();
		s_assert.assertTrue(listOfMandatoryFieldsInBillingDetailsPageErrors.get(0).getAttribute("id").contains("profileNameError"),"Profile Name field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInBillingDetailsPageErrors.get(1).getAttribute("id").contains("cardHolderNameError"),"Card Holder Name field  Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInBillingDetailsPageErrors.get(2).getAttribute("id").contains("creditCardNumberError"),"Credit Card Number field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInBillingDetailsPageErrors.get(3).getAttribute("id").contains("cvvError"),"cvv field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInBillingDetailsPageErrors.get(4).getAttribute("id").contains("firstNameError"),"first Name field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInBillingDetailsPageErrors.get(5).getAttribute("id").contains("lastNameError"),"last Name field Error is not displayed");	
		s_assert.assertTrue(listOfMandatoryFieldsInBillingDetailsPageErrors.get(6).getAttribute("id").contains("address1Error"),"Address1 field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInBillingDetailsPageErrors.get(7).getAttribute("id").contains("phoneNumberError"),"Phone Number field Error is not displayed");	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isErrorMessagesForMonthFieldPresent(),"Month field Error is not displayed");	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isErrorMessagesForYearFieldPresent(),"Year field Error is not displayed");	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isErrorMessagesForZIPCodePresent(),"ZIP field Error is not displayed");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8424
	 * qTest Id: TC-2863
	 */
	@Test(priority=16,alwaysRun=true,groups="myAccountBillingCorpPC",description = "Storefront PC,Display the following checkboxes on Billing Details Page for Add new Address option.")
	public void testVerifyCheckboxOnAddBillingForPC_2863() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8318
	 * qTest Id: TC-2896
	 */
	@Test(priority=17,alwaysRun=true,groups="myAccountBillingCorpPC",description = "Storefront PC selecting a new Default Billing Address Functionality.")
	public void testVerifyDefaultBillingFunctionalityForPC_2896() {
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
	 * qTest Id: TC-2864
	 */
	@Test(priority=18,alwaysRun=true,groups="myAccountBillingCorpPC",description = "Storefront PC,Display the following checkboxes on Billing Details Page for edit existing address option.")
	public void testVerifyCheckboxOnEditBillingForPC_2864() {
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
	 * qTest Id: TC-3159
	 */
	@Test(priority=19,alwaysRun=true,groups="myAccountBillingCorpPC",description = "Verify Consolidated checbox for add new billing address (Corp/PC)")
	public void testTotalCheckboxesWhileAddBillingPC_3159() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3168
	 */
	@Test(priority=20,alwaysRun=true,groups="myAccountBillingCorpPC",description = "Verify Consolidated checbox for edit existing billing address (Corp/PC)")
	public void testTotalCheckboxesWhileEditBillingPC_3168() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 *Jira Story Id: MAIN-8424
	 * qTest Id: TC-2869
	 */
	@Test(priority=21,alwaysRun=true,groups="myAccountBillingCorpRC",description = "Storefront RC,Display the following checkboxes on Billing Details Page for Add new Address option.")
	public void testVerifyCheckboxOnAddBillingForRC_2869() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCheckboxForFutureOrdersDisplayed(), "Checkbox for future autoship NOT dislpayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8639
	 * qTest Id: TC-3169
	 */
	@Test(priority=22,alwaysRun=true,groups="myAccountBillingCorpRC",description = "Verify Consolidated checbox for edit existing billing address (Corp/RC)")
	public void testTotalCheckboxesWhileEditBillinRC_3169() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditForFirstBillingProfile();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 *Jira Story Id: MAIN-8318
	 * qTest Id: TC-2899
	 */
	@Test(priority=23,alwaysRun=true,groups="myAccountBillingCorpRC",description = "Storefront RC selecting a new Default Billing Address Functionality.")
	public void testVerifyDefaultBillingFunctionalityForRC_2899() {
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
	 *Jira Story Id: MAIN-8424
	 * qTest Id: TC-2870
	 */
	@Test(priority=24,alwaysRun=true,groups="myAccountBillingCorpRC",description = "Storefront RC,Display the following checkboxes on Billing Details Page for edit existing address option.")
	public void testVerifyCheckboxOnEditBillingForRC_2870() {
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
	 * qTest Id: TC-3160
	 */
	@Test(priority=25,alwaysRun=true,groups="myAccountBillingCorpRC",description = "Verify Consolidated checbox for add new billing address (Corp/RC)")
	public void testTotalCheckboxesWhileAddBillingRC_3160() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

}
