package com.rf.test.website.storeFront.hybris.billingAndShipping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.RFWebsiteBaseTest;

public class AddEditShippingTest extends RFWebsiteBaseTest{

	private static final Logger logger = LogManager
			.getLogger(AddEditShippingTest.class.getName());

	private int randomNum; 	
	String consultantEmailID = null;

	//qTest ID - 438 Verify that QAS validation gets perform everytime user adds a shipping address.
	//qTest ID - 447 Verify that QAS validation gets perform everytime user edits a shipping address.
	//qTest ID - 432 Add shipping address during checkout 
	//qTest ID - 444 Edit shipping address during checkout
	@Test(priority=2)
	public void testAddAndEditShippingAddressDuringCheckout_432q_444q_438q_447q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstants.FIRST_NAME;
		String lastName = "lN";
		String defaultSelectedShippingAddressNameOnShippingInfo =null;
		//storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
	
			storeFrontShippingInfoPage.clickOnWelcomeDropDown();
			storeFrontShippingInfoPage = storeFrontShippingInfoPage.clickShippingLinkPresentOnWelcomeDropDown();
//			if(storeFrontShippingInfoPage.isMoreThanOneShippingOrBillingProfilePresent()==false){
			if(country.equalsIgnoreCase("AU")) {
			storeFrontShippingInfoPage.clickAddNewShippingProfileLink();
				storeFrontShippingInfoPage.enterNewShippingAddressName(firstName+" "+lastName);
				storeFrontShippingInfoPage.enterNewShippingAddressCity(city);
				storeFrontShippingInfoPage.enterNewShippingAddressLine1(addressLine1);
				storeFrontShippingInfoPage.enterNewShippingAddressPostalCode(postalCode);
				storeFrontShippingInfoPage.enterNewShippingAddressPhoneNumber(phoneNumber);
				storeFrontShippingInfoPage.selectNewShippingAddressState(state);
				storeFrontShippingInfoPage.clickOnSaveShippingProfile();
				s_assert.assertTrue(storeFrontShippingInfoPage.verifyConfirmationMessagePresentOnUI(),"Your profile has been updated message not present");
//			}
		}
//		storeFrontShippingInfoPage.clickOnWelcomeDropDown();
//		storeFrontBillingInfoPage = storeFrontShippingInfoPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
//		storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
//		storeFrontBillingInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
//		storeFrontBillingInfoPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
//		storeFrontBillingInfoPage.selectNewBillingCardExpirationDate(TestConstants.CARD_EXPIRY_MONTH, TestConstants.CARD_EXP_YEAR);
//		storeFrontBillingInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
//		storeFrontBillingInfoPage.selectNewBillingCardAddress();
//		storeFrontBillingInfoPage.selectUseThisBillingProfileFutureAutoshipChkbox();
//		storeFrontBillingInfoPage.clickOnSaveBillingProfile();
//		storeFrontBillingInfoPage.makeBillingProfileDefault(newBillingProfileName);
//		s_assert.assertTrue(storeFrontBillingInfoPage.validateBillingProfileUpdated(),"Billing Profile is not updated!!");
//		if(country.equalsIgnoreCase("CA")) {
//			storeFrontConsultantPage.clickOnWelcomeDropDown();
//			storeFrontShippingInfoPage = storeFrontConsultantPage.clickShippingLinkPresentOnWelcomeDropDown();
//			s_assert.assertTrue(storeFrontShippingInfoPage.verifyShippingInfoPageIsDisplayed(),"shipping info page has not been displayed");
			defaultSelectedShippingAddressNameOnShippingInfo = storeFrontShippingInfoPage.getDefaultSelectedShippingAddress();
//		}
		storeFrontConsultantPage = storeFrontHomePage.clickOnRodanAndFieldsLogo();
		//Continue with checkout page
		storeFrontConsultantPage.hoverOnShopLinkAndClickAllProductsLinks();		
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontUpdateCartPage.clickOnCheckoutButton();
		//Edit shipping address
		String newShippingAddressName = TestConstants.ADDRESS_NAME+randomNum;
		int randomNum1 = CommonUtils.getRandomNum(10000, 1000000);
		String editShippingAddressName = TestConstants.ADDRESS_NAME+randomNum1;
		if(country.equalsIgnoreCase("CA")) {
			storeFrontUpdateCartPage.clickOnAddANewShippingAddress();
			storeFrontUpdateCartPage.enterNewShippingAddressName(newShippingAddressName+" "+lastName);
			storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
			storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
			storeFrontUpdateCartPage.selectNewShippingAddressStateOnCartPage(state);
			storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
			storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
			s_assert.assertTrue(storeFrontUpdateCartPage.clickOnSaveShippingProfileAfterEdit(),"QAS popup Not present after shipping address add");
			s_assert.assertTrue(storeFrontUpdateCartPage.verifyNewShippingAddressIsSelectedByDefaultOnAdhocCart(newShippingAddressName), "Newly added Address has not got associated with the billing profile");
			storeFrontUpdateCartPage.clickOnEditOfShippingProfile(newShippingAddressName);	
		}else{
			storeFrontUpdateCartPage.clickOnEditOnNonDefaultAddress();
		}
		storeFrontUpdateCartPage.enterNewShippingAddressName(editShippingAddressName+" "+TestConstants.LAST_NAME);
		storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
		storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontUpdateCartPage.clickOnSaveShippingProfileAfterEdit();
//		s_assert.assertTrue(storeFrontUpdateCartPage.clickOnSaveShippingProfileAfterEdit(),"QAS popup Not present after shipping address edit");
		s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(editShippingAddressName), "Edited Shipping address NOT added to update cart");
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyOrderPlacedConfirmationMessage(),"order is not placed successfully");
		s_assert.assertTrue(storeFrontUpdateCartPage.getUpdatedAddressPresentOnOrderConfirmationPage().toLowerCase().contains(editShippingAddressName.toLowerCase()),"Edited shipping address name expected is"+editShippingAddressName+"while shippig address coming on order confirmation page is while edit the address "+storeFrontUpdateCartPage.getUpdatedAddressPresentOnOrderConfirmationPage());
		storeFrontConsultantPage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontShippingInfoPage = storeFrontConsultantPage.clickShippingLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(editShippingAddressName), "Newly/Edited Shipping address is not listed on Shipping profile page");
		String defaultSelectedShippingAddressNameAfterAddAShippingAddress = storeFrontShippingInfoPage.getDefaultSelectedShippingAddress();
		s_assert.assertTrue(storeFrontShippingInfoPage.verifyOldDefaultSelectAddress(defaultSelectedShippingAddressNameOnShippingInfo, defaultSelectedShippingAddressNameAfterAddAShippingAddress), "Newly/Edited Address has not got associated with the shipping profile");
		s_assert.assertAll();
	}

	//qTest ID-434 Add shipping address in autoship template 
	//qTest ID-445 Edit shipping address in autoship template
	@Test(priority=3)
	public void testAddAndEditShippingAddressInAutoshipTemplate_434q_445q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		String newShippingAddressName = TestConstants.ADDRESS_NAME+randomNum;
		//storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontOrdersPage.clickOnWelcomeDropDown();
		storeFrontCartAutoShipPage = storeFrontOrdersPage.clickEditCrpLinkPresentOnWelcomeDropDown();
		storeFrontUpdateCartPage = storeFrontCartAutoShipPage.clickUpdateMoreInfoLink();
		storeFrontUpdateCartPage.clickOnEditShipping();
		if(country.equalsIgnoreCase("ca")){
			storeFrontUpdateCartPage.clickAddNewShippingProfileLink();
			storeFrontUpdateCartPage.enterNewShippingAddressName(newShippingAddressName+" "+lastName);
			storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
			storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
			storeFrontUpdateCartPage.selectNewShippingAddressState(state);
			storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
			storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
			storeFrontUpdateCartPage.clickOnSaveShippingProfile();
			//---------------Verify that the new added shipping address is displayed in 'Shipment' section on update autoship cart page------------------------------------------------------------------------
			s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(newShippingAddressName), "Newly added Shipping address NOT selected in update cart under shipping section");
			s_assert.assertTrue(storeFrontUpdateCartPage.isNewlyCreatedShippingProfileIsSelectedByDefault(newShippingAddressName), "Newly added Shipping address NOT selected as default in update cart under shipping section");
		}
		
		//Edit shipping address
		int randomNum1 = CommonUtils.getRandomNum(10000, 1000000);
		String editShippingAddressName = TestConstants.ADDRESS_NAME+randomNum1;
		if(country.equalsIgnoreCase("au")){
			storeFrontUpdateCartPage.clickOnEditForDefaultShippingAddress();
		}
		else if(country.equalsIgnoreCase("ca")){
			storeFrontUpdateCartPage.clickOnEditOfShippingProfile(newShippingAddressName);			
		}
	
		storeFrontUpdateCartPage.enterNewShippingAddressName(editShippingAddressName+" "+TestConstants.LAST_NAME);
		storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
		storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontUpdateCartPage.clickOnSaveShippingProfile();
		s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(editShippingAddressName),"Edited Shipping Address is not on cart page");
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyNewlyEditedShippingAddressIsSelected(editShippingAddressName),"newly edited shipping address is not choosen by default");
		storeFrontUpdateCartPage.clickOnUpdateCartShippingNextStepBtn();
		storeFrontUpdateCartPage.clickOnNextStepBtn();
		storeFrontUpdateCartPage.clickUpdateCartBtn();
		storeFrontUpdateCartPage.clickOnRodanAndFieldsLogo();
		storeFrontUpdateCartPage.clickOnWelcomeDropDown();
		storeFrontShippingInfoPage = storeFrontUpdateCartPage.clickShippingLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(editShippingAddressName), "Newly added/edited Shipping address is not listed on Shipping profile page");
//		s_assert.assertFalse(storeFrontShippingInfoPage.verifyRadioButtonNotSelectedByDefault(editShippingAddressName), "Newly added/edited shipping address on autoship cart is selected by default");
		s_assert.assertAll();
	} 

	//qTest ID- 437 Add shipping address during CRP enrollment through my account 
	//qTest ID- 446 Edit shipping address during CRP enrollment through my account
	@Test(priority=7)
	public void testAddAndEditShippingAddressDuringCRPEnrollment_437q_446q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		String firstName = TestConstants.FIRST_NAME;
		String newShippingAddressName = TestConstants.FIRST_NAME+randomNum;
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		if(country.equalsIgnoreCase("AU")) {
			if(storeFrontShippingInfoPage.isMoreThanOneShippingOrBillingProfilePresent()==false){
				storeFrontShippingInfoPage.clickOnWelcomeDropDown();
				storeFrontShippingInfoPage = storeFrontShippingInfoPage.clickShippingLinkPresentOnWelcomeDropDown();
				storeFrontShippingInfoPage.clickAddNewShippingProfileLink();
				storeFrontShippingInfoPage.enterNewShippingAddressName(firstName+" "+lastName);
				storeFrontShippingInfoPage.enterNewShippingAddressCity(city);
				storeFrontShippingInfoPage.enterNewShippingAddressLine1(addressLine1);
				storeFrontShippingInfoPage.enterNewShippingAddressPostalCode(postalCode);
				storeFrontShippingInfoPage.enterNewShippingAddressPhoneNumber(phoneNumber);
				storeFrontShippingInfoPage.selectNewShippingAddressState(state);
				storeFrontShippingInfoPage.clickOnSaveShippingProfile();
				s_assert.assertTrue(storeFrontShippingInfoPage.verifyConfirmationMessagePresentOnUI(),"Your profile has been updated message not present");
			}
		}
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"shipping info page has not been displayed");
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		if(storeFrontAccountInfoPage.verifyCRPCancelled()==false){
			storeFrontAccountInfoPage.clickOnCancelMyCRP();
		}
		storeFrontAccountInfoPage.clickOnEnrollInCRP();
		storeFrontHomePage.selectProductForCRPAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontAccountInfoPage.clickOnCRPCheckout();
		int randomNum1 = CommonUtils.getRandomNum(10000, 1000000);
		String editedShippingName = newShippingAddressName+randomNum1;
		String newShippingName = newShippingAddressName+randomNum;
		if(country.equalsIgnoreCase("ca")){
			storeFrontUpdateCartPage.clickAddNewShippingProfileLink();
			storeFrontUpdateCartPage.enterNewShippingAddressName(newShippingName+" "+lastName);
			storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
			storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
			storeFrontUpdateCartPage.selectNewShippingAddressStateForDuringCRPEnrollment(state);
			storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
			storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
			storeFrontUpdateCartPage.clickOnSaveCRPShippingInfo();
			s_assert.assertTrue(storeFrontUpdateCartPage.verifyNewlyCreatedShippingAddressIsSelectedByDefault(newShippingName), "Newly created shipping address is not selected by default");
			storeFrontUpdateCartPage.clickOnEditOfShippingProfile(newShippingName);
		}else{
			storeFrontUpdateCartPage.clickOnEditOnNonDefaultAddress();
		}
		storeFrontUpdateCartPage.enterNewShippingAddressName(editedShippingName+" "+lastName);
		storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
		storeFrontUpdateCartPage.selectNewShippingAddressStateForDuringCRPEnrollment(state);
		storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontUpdateCartPage.clickOnSaveCRPShippingInfo();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyNewlyCreatedShippingAddressIsSelectedByDefault(editedShippingName), "Newly edited shipping address is not selected by default while edit the address");
		storeFrontUpdateCartPage.clickOnUpdateCartShippingNextStepBtnDuringEnrollment();
		//storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		storeFrontUpdateCartPage.clickAddNewBillingProfileLink();
		storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		int randomNum2 = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum2;
		storeFrontUpdateCartPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate();
		storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontUpdateCartPage.selectNewBillingCardAddress();
		storeFrontUpdateCartPage.clickOnSaveBillingProfile();
		storeFrontHomePage.clickOnBillingNextStepButtonWhileEnrollingCRP();
		storeFrontUpdateCartPage.clickOnSetupCRPAccountBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyOrderConfirmation(), "Order Confirmation Message has not been displayed");
		// verify on shipping info page
		storeFrontUpdateCartPage.clickOnWelcomeDropDown();
		storeFrontShippingInfoPage = storeFrontUpdateCartPage.clickShippingLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontShippingInfoPage.verifyShippingInfoPageIsDisplayed(),"shipping info page has not been displayed");
		s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(editedShippingName), "Newly/Edited Shipping address is not listed on Shipping profile page");
		s_assert.assertFalse(storeFrontShippingInfoPage.verifyRadioButtonIsSelectedByDefault(editedShippingName), "Shipping address was given in main account info is selected by default");
		s_assert.assertAll();	
	}

	//qTest ID - 436 Add shipping address during PC user or Retail user registration		
	//qTest ID - 438  Edit shipping address during PC user or Retail user registration
	@Test(priority=8)
	public void testAddShippingAddressDuringPCRegistration_436q_438q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);		
		String lastName = "lN";
		String firstName=TestConstants.FIRST_NAME+randomNum;
		storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.clickOnCheckoutButton();
		String pcEmailID = firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		storeFrontHomePage.enterNewPCDetails(firstName, TestConstants.LAST_NAME+randomNum, pcEmailID,password);
		storeFrontHomePage.enterMainAccountInfo(addressLine1,city, state, postalCode, phoneNumber);
		storeFrontHomePage.clickOnContinueWithoutSponsorLink();
		storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
		storeFrontHomePage.clickOnShippingAddressNextStepBtn();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontHomePage.clickOKBtnOfJavaScriptPopUp();
		storeFrontHomePage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickOnEditShipping();
		storeFrontUpdateCartPage.clickOnAddANewShippingAddress();
		String newShippingAddressName = TestConstants.ADDRESS_NAME+randomNum;
		storeFrontUpdateCartPage.enterNewShippingAddressName(newShippingAddressName+" "+lastName);
		storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
		storeFrontUpdateCartPage.selectNewShippingAddressStateOnCartPage(state);
		storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontUpdateCartPage.clickOnSaveShippingProfileAfterEdit();
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnEditShipping();
		s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(newShippingAddressName), "Newly added Shipping address NOT selected in update cart under shipping section");
		int randomNum1 = CommonUtils.getRandomNum(10000, 1000000);
		String editShippingAddressName = TestConstants.ADDRESS_NAME+randomNum1;
		storeFrontUpdateCartPage.clickOnEditOfShippingProfile(newShippingAddressName);	
		storeFrontUpdateCartPage.enterNewShippingAddressName(editShippingAddressName+" "+TestConstants.LAST_NAME);
		storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
		storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontUpdateCartPage.clickOnSaveShippingProfileAfterEdit();
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnEditShipping();
		s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(editShippingAddressName), "Edited Shipping address NOT selected in update cart under shipping section");
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		s_assert.assertTrue(storeFrontHomePage.verifyShippingAddressOnOrderPage(editShippingAddressName),"Newly/Edited Shipping address on order page is not the edited address");
		storeFrontHomePage.clickOnPCPerksTermsAndConditionsCheckBoxes();
		storeFrontHomePage.clickPlaceOrderBtn();
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		s_assert.assertTrue(storeFrontHomePage.verifyWelcomeDropdownToCheckUserRegistered(), "User NOT registered successfully");
		storeFrontUpdateCartPage.clickOnWelcomeDropDown();
		storeFrontShippingInfoPage = storeFrontUpdateCartPage.clickShippingLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontShippingInfoPage.verifyShippingInfoPageIsDisplayed(),"shipping info page has not been displayed");
		s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(editShippingAddressName), "Newly added/edited Shipping address is not listed on Shipping profile page");
		s_assert.assertFalse(storeFrontShippingInfoPage.verifyRadioButtonNotSelectedByDefault(editShippingAddressName), "Newly added/edited created shipping address is selected by default");
		s_assert.assertTrue(storeFrontShippingInfoPage.verifyRadioButtonIsSelectedByDefault(firstName), "Shipping address was given in main account info is not selected by default");
		storeFrontUpdateCartPage.clickOnAutoshipCart();
		storeFrontUpdateCartPage.clickOnUpdateMoreInfoButton();
		s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(editShippingAddressName), "Newly added/edited Shipping address NOT listed in update cart under shipping section");
		s_assert.assertAll();
	}

	//qTest ID - 435 Add shipping address during consultant enrollment
	//qTest ID - 449 Edit shipping address during consultant enrollment
	@Test(priority=9)
	public void testAddAndEditShippingAddressDuringConsultantEnrollment_435q_449q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String  consultantEmailAddress=TestConstants.FIRST_NAME+randomNum+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String lastName = "lN"+randomNum;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		storeFrontHomePage.openPWSSite(country, env);
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.enterUserInformationForEnrollmentWithEmail(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME,consultantEmailAddress, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		s_assert.assertTrue(storeFrontHomePage.isShippingAddressPresentAtOrderAndReviewPage(firstName), "Newly added address is not present at order and revie confirm page");
		//Edit the shipping address
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String newShippingAddressName = TestConstants.FIRST_NAME+randomNumber;
		storeFrontAccountInfoPage=storeFrontHomePage.clickOnEditShippingOnReviewAndConfirmPage();
		storeFrontHomePage.editFirstName(newShippingAddressName);
		storeFrontHomePage.enterPassword(password);
		storeFrontHomePage.enterConfirmPassword(password);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontOrdersPage =  storeFrontHomePage.clickOrdersLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontOrdersPage.verifyOrdersPageIsDisplayed(),"Orders page has not been displayed");
		// Get Order Number
		String orderHistoryNumber = storeFrontOrdersPage.getFirstOrderNumberFromOrderHistory();
		storeFrontOrdersPage.clickOrderNumber(orderHistoryNumber);
		s_assert.assertTrue(storeFrontOrdersPage.getShippingAddressFromAdhocTemplate().contains(newShippingAddressName.toLowerCase()), "Adhoc Order template newly/edited shipping address on RFO is"+newShippingAddressName+" and on UI is "+storeFrontOrdersPage.getShippingAddressFromAdhocTemplate());
		// verify on shipping info page
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontShippingInfoPage = storeFrontHomePage.clickShippingLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontShippingInfoPage.verifyShippingInfoPageIsDisplayed(),"shipping info page has not been displayed");
		s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(newShippingAddressName), "Newly added/edited Shipping address is not listed on Shipping profile page");
		s_assert.assertTrue(storeFrontShippingInfoPage.verifyRadioButtonIsSelectedByDefault(newShippingAddressName), "newly added/edited Shipping address was given in main account info is selected by default");
		s_assert.assertAll();
	}

	//qTest ID-584 Add New Shipping Billing Address on Edit CRP Template
	@Test(enabled=false)//covered in testAddAndEditShippingAddressInAutoshipTemplate_434q_445q
	public void testAddNewShippingBillingAddressOnEditCRPTemplate_584q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newShippingAddressName = TestConstants.FIRST_NAME+randomNum;
		//storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontHomePage.clickOnAutoshipCart();
		storeFrontUpdateCartPage.clickOnUpdateMoreInfoButton();
		storeFrontUpdateCartPage.clickOnEditShipping();
		storeFrontUpdateCartPage.clickAddNewShippingProfileLink();
		String lastName = "In";
		String newShippingName = newShippingAddressName+randomNum;
		storeFrontUpdateCartPage.enterNewShippingAddressName(newShippingName+" "+lastName);
		storeFrontUpdateCartPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontUpdateCartPage.enterNewShippingAddressCity(city);
		storeFrontUpdateCartPage.selectNewShippingAddressStateForDuringCRPEnrollment(state);
		storeFrontUpdateCartPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontUpdateCartPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontUpdateCartPage.clickOnSaveCRPShippingInfo();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyNewlyCreatedShippingAddressIsSelectedByDefault(newShippingName), "Newly created shipping address is not selected by default");
		storeFrontUpdateCartPage.clickOnNextStepBtnShippingAddress();
		storeFrontUpdateCartPage.clickAddNewBillingProfileLink();
		storeFrontBillingInfoPage.enterBillingDetails(cardNumber, newShippingName,cardExpMonth, cardExpYear, CVV);
		storeFrontUpdateCartPage.clickAddANewAddressLink();
		int randomNum1 = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum1;
		storeFrontUpdateCartPage.enterNewBillingAddressName(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.enterNewBillingAddressLine1(addressLine1);
		storeFrontUpdateCartPage.enterNewBillingAddressCity(city);
		storeFrontUpdateCartPage.selectNewBillingAddressState();
		storeFrontUpdateCartPage.enterNewBillingAddressPostalCode(postalCode);
		storeFrontUpdateCartPage.enterNewBillingAddressPhoneNumber(phoneNumber);
		storeFrontUpdateCartPage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickUpdateCartBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyCartUpdateMessage(), "cart is not updated!! ");
		s_assert.assertAll();
	}

}