package com.rf.test.website.storeFront.hybris.billingAndShipping;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.RFWebsiteBaseTest;

public class AddEditBillingTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(AddEditBillingTest.class.getName());

	private int randomNum; 	
	List<Map<String, Object>> randomConsultantList =  null;
	String consultantEmailID = null;
	String accountID = null;

	@BeforeClass
	public void setupDataForAddBilling() throws InterruptedException{	
		navigateToStoreFrontBaseURL();
		while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
			accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			logger.info("Account Id of the user is "+accountID);
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			boolean isError = driver.getCurrentUrl().contains("error");
			if(isError){
				logger.info("Login error for the user "+consultantEmailID);
				driver.get(driver.getURL()+"/"+driver.getCountry());				
			}
			else{
				storeFrontConsultantPage.clickOnWelcomeDropDown();
				if(storeFrontHomePage.isEditCRPLinkPresent()==true){
					break;
				}
				else{
					driver.get(driver.getURL()+"/"+driver.getCountry());					
				}
			}

		}
		logger.info("login is successful");
	}

	// Hybris Phase 2-2042 :: Version : 1 :: Add billing profile during checkout
	// Hybris Phase 2-2048:Edit billing profile during checkout
	@Test(priority=4)
	public void testAddAndEditBillingProfileDuringCheckout_2042_2048() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		if(country.equalsIgnoreCase("au")){
			storeFrontHomePage.clickOnWelcomeDropDown();
			storeFrontBillingInfoPage = storeFrontHomePage.clickBillingInfoLinkPresentOnWelcomeDropDown();
//			if(storeFrontShippingInfoPage.isMoreThanOneShippingOrBillingProfilePresent()==false){
				storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
				storeFrontBillingInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
				storeFrontBillingInfoPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
				storeFrontBillingInfoPage.selectNewBillingCardExpirationDate(TestConstants.CARD_EXPIRY_MONTH, TestConstants.CARD_EXP_YEAR);
				storeFrontBillingInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
				storeFrontBillingInfoPage.selectNewBillingCardAddress();
				storeFrontBillingInfoPage.clickOnSaveBillingProfile();
				storeFrontBillingInfoPage.clickOnRodanAndFieldsLogo();
//			}

		}
		storeFrontConsultantPage.hoverOnShopLinkAndClickAllProductsLinks();
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontUpdateCartPage.clickOnCheckoutButton();
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		if(country.equalsIgnoreCase("ca")){
			storeFrontUpdateCartPage.clickAddNewBillingProfileLink();
			storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
			storeFrontUpdateCartPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
			storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
			storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
			storeFrontUpdateCartPage.selectNewBillingCardAddress();
			storeFrontUpdateCartPage.clickOnSaveBillingProfile();
			s_assert.assertTrue(storeFrontHomePage.isTheBillingAddressPresentOnPage(newBillingProfileName),"Newly edited Billing profile is NOT listed on Adhoc cart while add the billing profile");
			s_assert.assertTrue(storeFrontHomePage.isBillingProfileIsSelectedByDefault(newBillingProfileName),"Already Default billing profile is not DEFAULT selected on adhoc cart while add the billing profile");
		}
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;
		storeFrontUpdateCartPage.clickOnEditOfNonDefaultBillingProfile();
		storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontUpdateCartPage.enterNewBillingNameOnCard(editedBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontUpdateCartPage.selectNewBillingCardAddress();
		storeFrontUpdateCartPage.clickOnSaveBillingProfile();
		s_assert.assertTrue(storeFrontHomePage.isTheBillingAddressPresentOnPage(editedBillingProfileName),"Newly edited Billing profile is NOT listed on Adhoc cart while edit the billing profile");
		s_assert.assertTrue(storeFrontHomePage.isBillingProfileIsSelectedByDefault(editedBillingProfileName),"Already Default billing profile is not DEFAULT selected on adhoc cart while edit the billing profile");
		if(country.equalsIgnoreCase("au"))
			storeFrontUpdateCartPage.selectDefaultBillingProfile(editedBillingProfileName);
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickBillingEditAfterSave();
		if(country.equalsIgnoreCase("ca"))
			s_assert.assertTrue(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefaultAfterClickOnEdit(newBillingProfileName),"New Billing Profile is not selected by default on CRP cart page");
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		storeFrontConsultantPage = storeFrontUpdateCartPage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontBillingInfoPage = storeFrontConsultantPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontBillingInfoPage.verifyBillingInfoPageIsDisplayed(),"Billing Info page has not been displayed");
		//--------------- Verify that Newly added Billing profile is listed in the Billing profiles section-----------------------------------------------------------------------------------------------------
		if(country.equalsIgnoreCase("ca"))
			s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(newBillingProfileName),"Newly added Billing profile is NOT listed on the page");
		else
			s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly edited Billing profile is NOT listed on the page");
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		//--------------- Verify that Newly added Billing profile is default selected in the Billing profiles section-----------------------------------------------------------------------------------------------------
		if(country.equalsIgnoreCase("ca"))
			s_assert.assertTrue(storeFrontBillingInfoPage.isDefaultAddressRadioBtnSelected(newBillingProfileName),"Newly added Billing profile is NOT default on the page");
		else
			s_assert.assertFalse(storeFrontBillingInfoPage.isDefaultAddressRadioBtnSelected(editedBillingProfileName),"Newly edited Billing profile is default on the page");
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		s_assert.assertAll();

	}

	//Hybris Phase 2-2043:Add billing profile in autoship template
	//Hybris Phase 2-2049 :: Version : 1 :: Edit billing profile in autoship template
	@Test(priority=5)
	public void testAddAndEditBillingAutoshipCartFutureCheckboxSelected_2043_2049() throws InterruptedException{ 
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontCartAutoShipPage = storeFrontConsultantPage.clickEditCrpLinkPresentOnWelcomeDropDown();
		storeFrontUpdateCartPage = storeFrontCartAutoShipPage.clickUpdateMoreInfoLink();
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		storeFrontUpdateCartPage.clickAddNewBillingProfileLink();
		storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontUpdateCartPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontUpdateCartPage.selectNewBillingCardAddress();
		storeFrontUpdateCartPage.selectUseThisBillingProfileFutureAutoshipChkbox();
		storeFrontUpdateCartPage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		s_assert.assertTrue(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefault(newBillingProfileName),"New Billing Profile is not selected by default on CRP cart page while add a new billing profile");
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;;
		storeFrontUpdateCartPage.clickOnEditOfBillingProfile(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontUpdateCartPage.enterNewBillingNameOnCard(editedBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontUpdateCartPage.selectNewBillingCardAddress();
		storeFrontUpdateCartPage.selectUseThisBillingProfileFutureAutoshipChkbox();
		storeFrontUpdateCartPage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		s_assert.assertTrue(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefault(editedBillingProfileName),"Edited Billing Profile is not selected by default on CRP cart page while edit billing profile");
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickUpdateCartBtn();
		storeFrontConsultantPage = storeFrontUpdateCartPage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontBillingInfoPage = storeFrontConsultantPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontBillingInfoPage.verifyBillingInfoPageIsDisplayed(),"Billing Info page has not been displayed");

		//--------------- Verify that Newly added Billing profile is listed in the Billing profiles section-----------------------------------------------------------------------------------------------------
		s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly added/Edited Billing profile is NOT listed on the page");

		//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		//--------------- Verify that Newly added Billing profile is NOT default selected in the Billing profiles section-----------------------------------------------------------------------------------------------------

		s_assert.assertFalse(storeFrontBillingInfoPage.isDefaultAddressRadioBtnSelected(editedBillingProfileName),"Newly added/Edited Billing profile is NOT default on the page");

		//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		s_assert.assertAll();
	}

	//Hybris Project-4467 ADD a billing profile from AUTOSHIP CART page, having "Use this billing profile for your future auto-ship" check box NOT CHECKED
	@Test(priority=6)
	public void testAddAndEditBillingAutoshipCartFutureCheckboxNotSelected_4467() throws InterruptedException{  
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontCartAutoShipPage = storeFrontConsultantPage.clickEditCrpLinkPresentOnWelcomeDropDown();
		storeFrontUpdateCartPage = storeFrontCartAutoShipPage.clickUpdateMoreInfoLink();
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		storeFrontUpdateCartPage.clickAddNewBillingProfileLink();
		storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontUpdateCartPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontUpdateCartPage.selectNewBillingCardAddress();
		storeFrontUpdateCartPage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		s_assert.assertFalse(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefault(newBillingProfileName),"New Billing Profile is selected by default on CRP cart page while we add a new billing profile");
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;
		storeFrontUpdateCartPage.clickOnEditOfBillingProfile(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontUpdateCartPage.enterNewBillingNameOnCard(editedBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontUpdateCartPage.selectNewBillingCardAddress();
		storeFrontUpdateCartPage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		s_assert.assertFalse(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefault(editedBillingProfileName),"Edited Billing Profile is selected by default on CRP cart page while we edit billing profile");
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickUpdateCartBtn();
		storeFrontConsultantPage = storeFrontUpdateCartPage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontBillingInfoPage = storeFrontConsultantPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontBillingInfoPage.verifyBillingInfoPageIsDisplayed(),"Billing Info page has not been displayed");

		//--------------- Verify that Newly added Billing profile is listed in the Billing profiles section-----------------------------------------------------------------------------------------------------

		s_assert.assertTrue(storeFrontBillingInfoPage.isTheBillingAddressPresentOnPage(editedBillingProfileName),"Newly added/Edited Billing profile is NOT listed on the page");

		//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		//--------------- Verify that Newly added Billing profile is default selected in the Billing profiles section-----------------------------------------------------------------------------------------------------

		s_assert.assertFalse(storeFrontBillingInfoPage.isDefaultAddressRadioBtnSelected(editedBillingProfileName),"Newly added/Edited Billing profile is NOT default on the page");

		//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		s_assert.assertAll();
	}

	// Hybris Project-4468:EDIT a billing profile from AD-HOC CHECKOUT page, having "Use this billing profile for your future use" NOT Selected
	@Test(priority=7)
	public void testEditBillingAdhocCheckoutFutureChecboxNotSelected_4468() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		storeFrontConsultantPage.hoverOnShopLinkAndClickAllProductsLinks();		
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontUpdateCartPage.clickOnCheckoutButton();
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnEditDefaultBillingProfile();
		storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontUpdateCartPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontUpdateCartPage.selectNewBillingCardAddress();
		storeFrontUpdateCartPage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickBillingEditAfterSave();
		s_assert.assertTrue(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefaultAfterClickOnEdit(newBillingProfileName),"New Billing Profile is not selected by default on CRP cart page");
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		storeFrontConsultantPage = storeFrontUpdateCartPage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontBillingInfoPage = storeFrontConsultantPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		//--------------- Verify that Newly added Billing profile is listed in the Billing profiles section-----------------------------------------------------------------------------------------------------
		s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(newBillingProfileName),"Newly edited default Billing profile is NOT listed on the page");
		s_assert.assertTrue(storeFrontBillingInfoPage.isBillingProfileIsSelectedByDefault(newBillingProfileName),"Newly edited default Billing profile is not selected as default profile");
		s_assert.assertAll();
	}

	//Hybris Project-2389:Verify that QAS validation DO NOT get perform anytime user adds a billing address.
	@Test(priority=8)
	public void testQASValidationDoNotPerformAnyTimeUserAddsABillingAddress_2389() throws InterruptedException	{
		if(country.equalsIgnoreCase("ca")){
			randomNum = CommonUtils.getRandomNum(10000, 1000000);
			String lastName = "lN";
			String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			storeFrontConsultantPage.hoverOnShopLinkAndClickAllProductsLinks();  
			storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
			storeFrontUpdateCartPage.clickOnCheckoutButton();
			//click Next on Shipping Address Section
			storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
			//Add a New Billing Address
			storeFrontUpdateCartPage.clickAddNewBillingProfileLink();
			storeFrontUpdateCartPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
			storeFrontUpdateCartPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
			storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
			storeFrontUpdateCartPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
			storeFrontUpdateCartPage.selectNewBillingCardAddress();
			storeFrontUpdateCartPage.clickOnSaveBillingProfile();
			//validate QAS Validation PopUp is Not Displayed after Adding a Billing Profile
			s_assert.assertFalse(storeFrontUpdateCartPage.validateQASValidationPopUpIsDisplayed(),"QAS Validation PopUp is Displayed After Adding A Biling Profile");
			s_assert.assertAll();	
		}
		
	}

	//Hybris Project-2046 :: Version : 1 :: Add billing profile during CRP enrollment through my account 
	//Hybris Project-2052:Edit billing profile during CRP enrollment through my account
	@Test(priority=9)
	public void testAddAndEditNewBillingProfileDuringCRPEnrollment_2046_2052() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			String lastName = "lN";
			String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNumber;
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
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
			storeFrontHomePage.clickOnCRPCheckout();
			storeFrontHomePage.clickOnUpdateCartShippingNextStepBtnDuringEnrollment();
			storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
			randomNum = CommonUtils.getRandomNum(10000, 1000000);
			newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;
			storeFrontBillingInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
			storeFrontBillingInfoPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
			storeFrontBillingInfoPage.selectNewBillingCardExpirationDate(TestConstants.CARD_EXPIRY_MONTH, TestConstants.CARD_EXP_YEAR);
			storeFrontBillingInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
			storeFrontBillingInfoPage.selectNewBillingCardAddress();
			storeFrontBillingInfoPage.clickOnSaveBillingProfile();
			s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(newBillingProfileName),"Newly Created Billing Address not present on page");
			s_assert.assertTrue(storeFrontBillingInfoPage.isDefaultAddressRadioBtnSelected(newBillingProfileName),"Radio button is not selected for billing address");
			storeFrontHomePage.clickOnEditOfBillingProfile(newBillingProfileName);
			randomNum = CommonUtils.getRandomNum(10000, 1000000);
			String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNumber;
			storeFrontHomePage.enterEditedCardNumber(TestConstants.CARD_NUMBER);
			storeFrontHomePage.enterNewBillingNameOnCard(editedBillingProfileName+" "+lastName);
			storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
			storeFrontHomePage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
			storeFrontHomePage.selectNewBillingCardAddress();
			storeFrontHomePage.clickOnSaveBillingProfile();
			s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly Edited Billing Address not present on page");
			storeFrontHomePage.clickOnBillingNextStepBtn();

			storeFrontHomePage.clickOnSetupCRPAccountBtn();
			s_assert.assertTrue(storeFrontHomePage.verifyOrderConfirmation(), "Order Confirmation Message has not been displayed");
			storeFrontHomePage.clickOnGoToMyAccountToCheckStatusOfCRP();
			storeFrontHomePage.clickOnYourAccountDropdown();
			storeFrontAccountInfoPage.clickOnAutoShipStatus();
			s_assert.assertTrue(storeFrontAccountInfoPage.verifyCurrentCRPStatus(), "Current CRP Status has not been Enrolled");
			storeFrontConsultantPage.clickOnWelcomeDropDown();
			storeFrontBillingInfoPage =storeFrontConsultantPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
			s_assert.assertTrue(storeFrontBillingInfoPage.verifyBillingInfoPageIsDisplayed(),"billing info page has not been displayed");
			s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly Created/Edited Billing Address not present on Billing info page");
			//s_assert.assertTrue(storeFrontBillingInfoPage.isAutoshipOrderAddressTextPresent(newBillingProfileName),"AutoShip order text is not present under billing address");
			s_assert.assertFalse(storeFrontBillingInfoPage.isDefaultAddressRadioBtnSelected(editedBillingProfileName),"Radio button is selected for newly created/Edited billing address");
			s_assert.assertAll();
		}else{
			logger.info("This is canada specific test");
		}
	}

	//Hybris Project-2044:Add billing profile during PC user or Retail user registration
	//Hybris Project-2050 :: Version : 1 :: Edit billing profile during PC user or Retail user registration
	@Test(priority=10)
	public void testAddAndEditBillingProfileDuringPCRegistration_2044_2050() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;
		String lastName = "lN"+randomNum;;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String emailAddress=firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();
		//Select a product with the price less than $80 and proceed to buy it
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		//Click on Check out
		storeFrontHomePage.clickOnCheckoutButton();
		//Enter the User information and DO NOT check the "Become a Preferred Customer" checkbox and click the create account button
		storeFrontHomePage.enterNewPCDetails(firstName,lastName,emailAddress, password);
		//Enter the Main account info and DO NOT check the "Become a Preferred Customer" and click next
		storeFrontHomePage.enterMainAccountInfo(addressLine1, city, state, postalCode, phoneNumber);
		logger.info("Main account details entered");
		storeFrontHomePage.clickOnContinueWithoutSponsorLink();
		storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
		storeFrontHomePage.clickOnShippingAddressNextStepBtn();
		//Enter Billing Profile
		storeFrontHomePage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.selectNewBillingCardAddress();
		storeFrontHomePage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickBillingEditAfterSave();
		s_assert.assertTrue(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefaultAfterClickOnEdit(newBillingProfileName),"New Billing Profile is not selected by default on CRP cart page while we edit the billing profile");

		storeFrontUpdateCartPage.clickOnEditOfBillingProfile(newBillingProfileName+" "+lastName);
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;
		storeFrontHomePage.enterEditedCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNewBillingNameOnCard(editedBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.selectNewBillingCardAddress();
		storeFrontHomePage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickBillingEditAfterSave();
		s_assert.assertTrue(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefaultAfterClickOnEdit(editedBillingProfileName),"Edit Billing Profile is not selected by default on CRP cart page while we edit the billing profile");

		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontHomePage.clickOnPCPerksTermsAndConditionsCheckBoxes();
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		storeFrontUpdateCartPage.clickOnRodanAndFieldsLogo();
		storeFrontPCUserPage.clickOnWelcomeDropDown();
		storeFrontBillingInfoPage = storeFrontPCUserPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontBillingInfoPage.verifyBillingInfoPageIsDisplayed(),"Billing Info page has not been displayed");

		//--------------- Verify that Newly added Billing profile is listed in the Billing profiles section-----------------------------------------------------------------------------------------------------

		s_assert.assertTrue(storeFrontBillingInfoPage.isTheBillingAddressPresentOnPage(editedBillingProfileName),"Newly added/Edited Billing profile is NOT listed on the page");
		storeFrontPCUserPage.clickOnWelcomeDropDown();
		storeFrontPCUserPage.clickEditPCPerksLinkPresentOnWelcomeDropDown();
		storeFrontCartAutoShipPage.clickUpdateMoreInfoLink();
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();

		//------------------ Verify that CRP/PC cart contains the newly created billing profile address as selected ------------------------------------------------------------

		s_assert.assertTrue(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefault(editedBillingProfileName),"Newly/Edited Billing Profile is not selected by default on update cart page");

		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		s_assert.assertAll();  
	}

	//QTest ID TC-428 Edit billing profile during consultant enrollment
	// Hybris Project-2045 :: Version : 1 :: Add billing address during consultant enrollment 
	// Hybris Project-2051:Edit billing address during consultant enrollment
	@Test(priority=11)
	public void testAddAndEditBillingAddressConsultantEnrollment_2045_428q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		storeFrontHomePage.openPWSSite(country, env);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, TestConstants.FIRST_NAME+randomNum, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state,postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		//storeFrontHomePage.acceptTheVerifyYourShippingAddressPop();		
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.clickOnEditBillingOnReviewAndConfirmPage();
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;
		storeFrontHomePage.enterEditedCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNewBillingNameOnCard(editedBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.selectNewBillingCardAddress();
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		s_assert.assertTrue(storeFrontHomePage.isTheTermsAndConditionsCheckBoxDisplayed(), "Terms and Conditions checkbox is not visible");
		storeFrontHomePage.checkThePoliciesAndProceduresCheckBox();
		storeFrontHomePage.checkTheIAcknowledgeCheckBox();		
		storeFrontHomePage.checkTheIAgreeCheckBox();
		storeFrontHomePage.clickOnEnrollMeBtn();
		s_assert.assertTrue(storeFrontHomePage.verifyPopUpForTermsAndConditions(), "PopUp for terms and conditions is not visible");
		storeFrontHomePage.checkTheTermsAndConditionsCheckBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontOrdersPage = storeFrontConsultantPage.clickOrdersLinkPresentOnWelcomeDropDown();
		storeFrontOrdersPage.clickAutoshipOrderNumber();

		//------------------ Verify that autoship template contains the newly created billing profile ------------------------------------------------------------  

		s_assert.assertTrue(storeFrontOrdersPage.isPaymentMethodContainsName(editedBillingProfileName),"Autoship Template Payment Method doesn't contains the newly/Edited billing profile even when future autoship checkbox is selected");

		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontBillingInfoPage = storeFrontConsultantPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontBillingInfoPage.verifyBillingInfoPageIsDisplayed(),"Billing Info page has not been displayed");
		//--------------- Verify that Newly added Billing profile is listed in the Billing profiles section-----------------------------------------------------------------------------------------------------
		s_assert.assertTrue(storeFrontBillingInfoPage.isTheBillingAddressPresentOnPage(editedBillingProfileName),"Newly added/Edited Billing profile is NOT listed on the page");
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		s_assert.assertAll();
	}	

}