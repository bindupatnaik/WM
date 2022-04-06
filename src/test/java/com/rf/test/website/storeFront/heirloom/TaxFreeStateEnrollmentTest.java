package com.rf.test.website.storeFront.heirloom;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.pages.website.nscore.NSCore3HomePage;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class TaxFreeStateEnrollmentTest extends RFHeirloomWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(BizPWSTest.class.getName());

	//QTest ID TC-3285 Con with Non Tax state
	@Test
	public void testEnrollConsultantWithNonTaxState_3285(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(11, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress =lastName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String enrollemntType = "Standard";
		String sponsorID = getRandomSponsorFromDB();
		String taxForKitFromUI = null;
		String taxForCRPFromUI = null;
		String addressLine1 = TestConstantsRFL.NON_TAX_ADDRESS_LINE_1_US;
		String postalCode = TestConstantsRFL.NON_TAX_POSTAL_CODE_US;
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");		
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickBillingInfoNextBtn();
		storeFrontHeirloomHomePage.clickYesSubscribeToPulseNow();
		storeFrontHeirloomHomePage.clickYesEnrollInCRPNow();
		storeFrontHeirloomHomePage.clickAutoShipOptionsNextBtn();
		storeFrontHeirloomHomePage.selectProductToAddToCart();
		storeFrontHeirloomHomePage.clickYourCRPOrderPopUpNextBtn();
		taxForKitFromUI = storeFrontHeirloomHomePage.getTaxForKitFromOrderReviewAndConfirmPage();
		taxForCRPFromUI = storeFrontHeirloomHomePage.getTaxForcrpFromOrderReviewAndConfirmPage();
		s_assert.assertTrue(taxForKitFromUI.equalsIgnoreCase("0.00"), "Expected tax amount for kit is 0.00 for non tax state but actual on UI is "+taxForKitFromUI);
		s_assert.assertTrue(taxForCRPFromUI.equalsIgnoreCase("0.00"), "Expected tax amount for CRP is 0.00 for non tax state but actual on UI is "+taxForCRPFromUI);
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Congratulations Message not appeared");
		s_assert.assertAll();
	}

	//QTest ID TC-3281 Enroll PC user without Promotional sku with tax free state
	@Test
	public void testEnrollPCUserWithTaxFreeState_3281(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String emailAddress =lastName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumbers;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String sponsorID = null;
		String addressLine1 = TestConstantsRFL.NON_TAX_ADDRESS_LINE_1_US;
		String postalCode = TestConstantsRFL.NON_TAX_POSTAL_CODE_US;
		String addressName = "Home";
		String taxFromUI = null;
		sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickClickHereLinkForPC();
		s_assert.assertTrue(driver.getCurrentUrl().contains("PCPerks"), "After clicking click here link for PC not navigated to PC Enrollment page.");
		storeFrontHeirloomHomePage.clickEnrollNowBtnForPCAndRC();
		storeFrontHeirloomHomePage.enterProfileDetailsForPCAndRC(firstName,lastName,emailAddress,password,phnNumber,gender);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.enterIDNumberAsSponsorForPCAndRC(sponsorID);
		storeFrontHeirloomHomePage.clickBeginSearchBtn();
		storeFrontHeirloomHomePage.selectSponsorRadioBtn();
		storeFrontHeirloomHomePage.clickSelectAndContinueBtnForPCAndRC();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForPCAndRC(), "Terms and candition error message not present for PC User.");
		storeFrontHeirloomHomePage.checkTermsAndConditionChkBoxForPCAndRC();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickContinueBtnOnAutoshipSetupPageForPC();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetails(addressName, shippingProfileFirstName,shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		taxFromUI = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		s_assert.assertTrue(taxFromUI.equalsIgnoreCase("0.00"), "Expected tax amount at order review page is 0.00 for non tax state but actual on UI is "+taxFromUI);
		storeFrontHeirloomHomePage.clickCompleteEnrollmentBtn();
		storeFrontHeirloomHomePage.clickOKBtnOfJavaScriptPopUp();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPCEnrollmentCompletedSuccessfully(), "PC enrollment is not completed successfully");
		s_assert.assertAll(); 
	}


	//QTest ID TC-3288 RC with Non Tax state
	@Test
	public void testEnrollRCWithNonTaxState_3288(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String emailAddress =lastName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumbers;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String sponsorID = null;
		String addressLine1 = TestConstantsRFL.NON_TAX_ADDRESS_LINE_1_US;
		String postalCode = TestConstantsRFL.NON_TAX_POSTAL_CODE_US;
		String addressName = "Home";
		String taxFromUI = null;
		sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickClickHereLinkForRC();
		s_assert.assertTrue(driver.getCurrentUrl().contains("Retail"), "After clicking click here link for RC not navigated to RC Enrollment page.");
		storeFrontHeirloomHomePage.enterProfileDetailsForPCAndRC(firstName,lastName,emailAddress,password,phnNumber,gender);
		storeFrontHeirloomHomePage.clickCreateMyAccountBtnOnCreateRetailAccountPage();
		storeFrontHeirloomHomePage.enterIDNumberAsSponsorForPCAndRC(sponsorID);
		storeFrontHeirloomHomePage.clickBeginSearchBtn();
		storeFrontHeirloomHomePage.selectSponsorRadioBtn();
		storeFrontHeirloomHomePage.clickSelectAndContinueBtnForPCAndRC();
		storeFrontHeirloomHomePage.enterShippingProfileDetails(addressName, shippingProfileFirstName,shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		taxFromUI = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		s_assert.assertTrue(taxFromUI.equalsIgnoreCase("0.00"), "Expected tax amount at order review is 0.00 for non tax state but actual on UI is "+taxFromUI);
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Enrollment is not completed successfully");
		taxFromUI = null;
		taxFromUI = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		s_assert.assertTrue(taxFromUI.equalsIgnoreCase("0.00"), "Expected tax amount is 0.00 for non tax state but actual on UI is "+taxFromUI);
		s_assert.assertAll(); 
	}


	//QTest ID TC-3292 Place adhoc order with tax free state for Con user
	@Test
	public void testPlaceAdhocOrderWithTaxFreeStateForCon_3292(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String taxFromOrderReview = null;
		String taxFromOrderConfirmation = null;
		String category = "REDEFINE";
		String consultantEmailID = null;
		String addressLine1 = TestConstantsRFL.NON_TAX_ADDRESS_LINE_1_US;
		String postalCode = TestConstantsRFL.NON_TAX_POSTAL_CODE_US;
		String addressName = "Home";
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(), "User is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(category);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickChangeShippingAddressBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		// get details from order confirmation
		taxFromOrderReview = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(taxFromOrderReview.equalsIgnoreCase("0.00"), "Expected tax amount is 0.00 for non tax state at order review but actual on UI is "+taxFromOrderReview);
		// get details from order confirmation
		taxFromOrderConfirmation = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		s_assert.assertTrue(taxFromOrderConfirmation.equalsIgnoreCase("0.00"), "Expected tax amount is 0.00 for non tax state at order confirmation but actual on UI is "+taxFromOrderConfirmation);
		s_assert.assertAll();
	}

	//QTest ID TC-3298 Place adhoc order combination with tax free state for PC user
	@Test
	public void testPlaceAdhocOrderWithTaxFreeStateForPC_3298(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String taxFromOrderReview = null;
		String category = "REDEFINE";
		String pcEmailID = null;
		String addressLine1 = TestConstantsRFL.NON_TAX_ADDRESS_LINE_1_US;
		String postalCode = TestConstantsRFL.NON_TAX_POSTAL_CODE_US;
		String addressName = "Home";
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(), "User is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(category);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickChangeShippingAddressBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		// get details from order confirmation
		taxFromOrderReview = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		s_assert.assertTrue(taxFromOrderReview.equalsIgnoreCase("0.00"), "Expected tax amount is 0.00 for non tax state at order review but actual on UI is "+taxFromOrderReview);
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully for PC.");
		s_assert.assertAll();
	} 

	//QTest ID TC-3295 Place adhoc order with tax free state for RC user
	@Test
	public void testPlaceAdhocOrderWithTaxFreeStateForRC_3295(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String taxFromOrderReview = null;
		String taxFromOrderConfirmation = null;
		String category = "REDEFINE";
		String rcEmailId = null;
		String addressLine1 = TestConstantsRFL.NON_TAX_ADDRESS_LINE_1_US;
		String postalCode = TestConstantsRFL.NON_TAX_POSTAL_CODE_US;
		String addressName = "Home";
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailId=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		storeFrontHeirloomHomePage.loginAsRCUser(rcEmailId,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(), "User is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(category);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickChangeShippingAddressBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
		// get details from order confirmation
		taxFromOrderReview = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(taxFromOrderReview.equalsIgnoreCase("0.00"), "Expected tax amount is 0.00 for non tax state at order review but actual on UI is "+taxFromOrderReview);
		// get details from order confirmation
		taxFromOrderConfirmation = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		s_assert.assertTrue(taxFromOrderConfirmation.equalsIgnoreCase("0.00"), "Expected tax amount is 0.00 for non tax state at order confirmation but actual on UI is "+taxFromOrderConfirmation);
		s_assert.assertAll();
	}

	//QTest ID TC-3300 Update PC Perks template with tax free state and run template
		@Test
		public void testUpdatePCPerksTemplateWithTaxFreeStateAndRunTemplate_3300(){
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
			String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
			String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
			String taxFromOrderReview = null;
			String pcEmailID = null;
			String addressLine1 = TestConstantsRFL.NON_TAX_ADDRESS_LINE_1_US;
			String postalCode = TestConstantsRFL.NON_TAX_POSTAL_CODE_US;
			String addressName = "Home";
			String customerName = null;
			String accountNumber = null;
			String firstName = null;
			String lastName = null;
			String autoshipOrderType = "Preferred Customer";
			List<Map<String, Object>> randomAccountList =  null;
			String categoryType="Name";
			String orderDetails=null;

			randomAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_DETAILS_RFL,RFL_DB);
			pcEmailID = (String) getValueFromQueryResult(randomAccountList, "EmailAddress");
			accountNumber = (String) getValueFromQueryResult(randomAccountList, "AccountNumber");	
			firstName = (String) getValueFromQueryResult(randomAccountList, "FirstName");	
			lastName = (String) getValueFromQueryResult(randomAccountList, "LastName");	
			customerName = firstName.trim() +" "+ lastName.trim();
			storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
			s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(), "User is not logged in successfully");
			if(storeFrontHeirloomHomePage.isMyAccountDropdownDisplayed()==false)
				storeFrontHeirloomHomePage.clickMyAccountLink();
			storeFrontHeirloomHomePage.clickEditOrderLink();
			// Adding the shipping profile
			storeFrontHeirloomHomePage.clickChangeLinkUnderShippingToOnPWS();
			storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
			storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
			s_assert.assertTrue(storeFrontHeirloomHomePage.getShippingAddressName().contains(shippingProfileFirstName),"Shipping address name expected is "+shippingProfileFirstName+" While on UI is "+storeFrontHeirloomHomePage.getShippingAddressName());
			// Adding the Billing profile
			storeFrontHeirloomHomePage.clickChangeBillingInformationLinkUnderBillingTabOnPWS();
			storeFrontHeirloomHomePage.enterBillingInfoForPWS(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
			storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
			storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
			s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
			storeFrontHeirloomHomePage.clickBuyNowBtn();
			storeFrontHeirloomHomePage.clickConfirmBtnForPCPerks();
			storeFrontHeirloomHomePage.clickViewDetailsLinkOfFirstOrderForPC();
			taxFromOrderReview = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
			s_assert.assertTrue(taxFromOrderReview.equalsIgnoreCase("0.00"), "Expected tax amount is 0.00 for non tax state at order review but actual on UI is "+taxFromOrderReview);

			// Verifying and Run the template through NSC3'
			storeFrontHeirloomHomePage.createAndGetNSC3Url();
			loginToNSCApplication();
			nscore3HomePage.clickOrdersLink();
			nscore3HomePage.clickGoToAutoshipOrdersLink();
			nscore3HomePage.selectAutoshipOrderType(autoshipOrderType);
			nscore3HomePage.selectSearchByCategoryOptionOnAutoshipOrdersPage(categoryType);
			nscore3HomePage.enterSearchNameAndClickBeginSearch(customerName);
			nscore3HomePage.changeDueDate(accountNumber);
			nscore3HomePage.selectRunCheckbox(accountNumber);
			nscore3HomePage.runAutoshipOrder();
			s_assert.assertTrue(nscore3HomePage.isAutoshipOrderSucceeded(accountNumber),"Autoship order is not succeeded");
			nscore3HomePage.enterSearchNameAndClickBeginSearch(customerName);
			nscore3HomePage.clickTreeIconOfAccountNumber(accountNumber);
			String orderNumber = nscore3HomePage.getTheLatestOrderNumberUnderTreeIcon(accountNumber);
			storeFrontHeirloomHomePage.createAndGetNSC4Url();
			loginToNSCApplication();
			nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
			nscore4OrdersTabPage.enterOrderIDInInputField(orderNumber);
			orderDetails=nscore4OrdersTabPage.getTaxOrderTotalAndSubtotalAppliedFromOrderDetailsPage();
			String taxFromUI=orderDetails.split("\n")[1];
			s_assert.assertTrue(taxFromUI.equalsIgnoreCase("$0.00"), "Expected tax amount is 0.00 for non tax state at NSC4 but actual on UI is "+taxFromUI);
			s_assert.assertAll();
		}


		//QTest ID TC-3301 Update CRP template with tax free state and run template
		@Test
		public void testUpdateCRPTemplateWithTaxFreeStateAndRunTemplate_3301(){
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
			String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
			String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
			String consultantEmail = null;
			String addressLine1 = TestConstantsRFL.NON_TAX_ADDRESS_LINE_1_US;
			String postalCode = TestConstantsRFL.NON_TAX_POSTAL_CODE_US;
			String addressName = "Home";
			String customerName = null;
			String accountNumber = null;
			String firstName = null;
			String lastName = null;
			String autoshipOrderType = "Consultant";
			List<Map<String, Object>> randomAccountList =  null;
			String categoryType="Name";
			String orderDetails=null;
			String parentWindowHandle =null;
			randomAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmail = (String) getValueFromQueryResult(randomAccountList, "UserName");
			accountNumber = (String) getValueFromQueryResult(randomAccountList, "AccountNumber");	
			firstName = (String) getValueFromQueryResult(randomAccountList, "FirstName");	
			lastName = (String) getValueFromQueryResult(randomAccountList, "LastName");	
			customerName = firstName.trim() +" "+ lastName.trim();
			storeFrontHeirloomHomePage.loginAsConsultant(consultantEmail,password);
			s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(), "User is not logged in successfully");
			if(storeFrontHeirloomHomePage.isMyAccountDropdownDisplayed()==false){
				storeFrontHeirloomPulsePage = storeFrontHeirloomHomePage.clickCheckMyPulse();
				storeFrontHeirloomPulsePage.dismissPulsePopup();
				storeFrontHeirloomPulsePage.clickMyAccountLinkOnPulsePage();
				parentWindowHandle =storeFrontHeirloomPulsePage.getParentWindowHandle();
			}
			storeFrontHeirloomPulsePage.clickEditCRP();
			storeFrontHeirloomPulsePage.clickChangeLinkOfShippingAtCRPPage();
			// Adding the shipping profile
			storeFrontHeirloomHomePage.enterShippingProfileDetailsForEditCRP(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
			storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
			String confirmationMsg = storeFrontHeirloomPulsePage.getConfirmationMessageOfShippingAndBillingUpdation();
			s_assert.assertTrue(confirmationMsg.contains("successfully updated!"), "Expected confirmation message should contains successfully updated! but actual on UI is "+confirmationMsg);
			// Adding the Billing profile
			storeFrontHeirloomPulsePage.clickBillingLinkAtCRPPage();
			storeFrontHeirloomPulsePage.clickChangeLinkOfBillingAtCRPPage();
			storeFrontHeirloomHomePage.enterBillingInfoForEditCRP(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
			storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
			storeFrontHeirloomHomePage.clickAcceptBtnAtQASPopup();
			confirmationMsg = null;
			confirmationMsg = storeFrontHeirloomPulsePage.getConfirmationMessageOfShippingAndBillingUpdation();
			s_assert.assertTrue(confirmationMsg.contains("successfully updated!"), "Expected confirmation message of billing should contains successfully updated! but actual on UI is "+confirmationMsg);
			// Verifying and Run the template through NSC3'
			storeFrontHeirloomHomePage.createAndGetNSC3Url();
			loginToNSCApplication();
			nscore3HomePage.clickOrdersLink();
			nscore3HomePage.clickGoToAutoshipOrdersLink();
			nscore3HomePage.selectAutoshipOrderType(autoshipOrderType);
			nscore3HomePage.selectSearchByCategoryOptionOnAutoshipOrdersPage(categoryType);
			nscore3HomePage.enterSearchNameAndClickBeginSearch(customerName);
			nscore3HomePage.changeDueDate(accountNumber);
			nscore3HomePage.selectRunCheckbox(accountNumber);
			nscore3HomePage.runAutoshipOrder();
			s_assert.assertTrue(nscore3HomePage.isAutoshipOrderSucceeded(accountNumber),"Autoship order is not succeeded");
			nscore3HomePage.enterSearchNameAndClickBeginSearch(customerName);
			nscore3HomePage.clickTreeIconOfAccountNumber(accountNumber);
			String orderNumber = nscore3HomePage.getTheLatestOrderNumberUnderTreeIcon(accountNumber);
			storeFrontHeirloomHomePage.createAndGetNSC4Url();
			loginToNSCApplication();
			nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
			nscore4OrdersTabPage.enterOrderIDInInputField(orderNumber);
			orderDetails=nscore4OrdersTabPage.getTaxOrderTotalAndSubtotalAppliedFromOrderDetailsPage();
			String taxFromUI=orderDetails.split("\n")[1];
			s_assert.assertTrue(taxFromUI.equalsIgnoreCase("$0.00"), "Expected tax amount is $0.00 for non tax state at NSC4 but actual on UI is "+taxFromUI);
			nscore4HomePage.closeChildAndSwitchToParentWindow(parentWindowHandle);
			s_assert.assertAll();
		}

}
