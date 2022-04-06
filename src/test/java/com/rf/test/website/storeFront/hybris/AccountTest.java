package com.rf.test.website.storeFront.hybris;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.RFWebsiteBaseTest;

public class AccountTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(AccountTest.class.getName());

	/**
	 * Login: Consultant
	 * Validations:
	 * * Verify Accept, Cancel and X working fine for allow my spouse option on account info
	 * @throws InterruptedException
	 */
	// qTest ID -454: Verify editing Spouse details under Account Info
	@Test(priority=1)
	public void testEditAllowMySpouseInMyAccount_454q() throws InterruptedException {
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"Account Info page has not been displayed");
		storeFrontAccountInfoPage.checkAllowMySpouseCheckBox();
		s_assert.assertTrue(storeFrontAccountInfoPage.validateEnterSpouseDetailsAndAccept(),"Accept button not working in the popup");
		storeFrontAccountInfoPage.checkAllowMySpouseCheckBox();
		storeFrontAccountInfoPage.checkAllowMySpouseCheckBox();
		s_assert.assertTrue(storeFrontAccountInfoPage.validateClickCancelOnProvideAccessToSpousePopup(),"Cancel button not working in the popup");
		storeFrontAccountInfoPage.checkAllowMySpouseCheckBox();
		s_assert.assertTrue(storeFrontAccountInfoPage.validateClickXOnProvideAccessToSpousePopup(),"X button not working in the popup");
		s_assert.assertAll();
	}

	//qTest ID-498 Verify Consultant can choose a custom PWS prefix upon enrolling in Pulse
	@Test (enabled=false,priority=3)//Test needs fix
	public void testVerifyConsultantCanChooseCustomPWSPrefixUponEnrollingInPulse_498q() throws InterruptedException{
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		storeFrontAccountInfoPage.clickOnCancelMyCRP();
		storeFrontAccountInfoPage.clickOnCancelMyPulseSubscription();
		storeFrontAccountInfoPage.clickOnOnlySubscribeToPulseBtn();
		int randomNum = CommonUtils.getRandomNum(1000, 100000);
		String websitePrefixName = TestConstants.CONSULTANT_USERNAME_PREFIX+randomNum;
		storeFrontHomePage.enterWebsitePrefixName(websitePrefixName);
		storeFrontAccountInfoPage.waitForPWSSuggestion(websitePrefixName);
		//storeFrontAccountInfoPage.clickOnNextDuringPulseSubscribtion();
		//		s_assert.assertTrue(storeFrontAccountInfoPage.verifyWebsitePrefixSuggestionIsPresent(), "There are no suggestions for website prefix");
		storeFrontAccountInfoPage.clickOnNextDuringPulseSubscribtion();
		storeFrontUpdateCartPage.clickOnAccountInfoNextButton();
		storeFrontUpdateCartPage.clickOnSubscribePulseTermsAndConditionsChkbox();
		storeFrontUpdateCartPage.clickOnSubscribeBtn();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		s_assert.assertTrue(storeFrontAccountInfoPage.validateSubscribeToPulse(),"pulse is not subscribed for the user");
		s_assert.assertAll();
	}

	//qTest ID 499:Consultant can choose a default PWS prefix upon enrolling in Pulse
	@Test (priority=4)
	public void testConsultantCanChooseDefaultPWSPrefixUponEnrollingInPulse_499q() throws InterruptedException{
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		storeFrontAccountInfoPage.clickOnCancelMyPulseSubscription();
		storeFrontAccountInfoPage.clickOnOnlySubscribeToPulseBtn();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyWebsitePrefixSuggestionIsPresent(), "There are no suggestions for website prefix");
		String pwsPrefix = storeFrontAccountInfoPage.getPWSPrefixFromPrefixAvailableString();
		storeFrontAccountInfoPage.clickOnNextDuringPulseSubscribtion();
		storeFrontUpdateCartPage.clickOnAccountInfoNextButton();
		storeFrontUpdateCartPage.clickOnSubscribePulseTermsAndConditionsChkbox();
		storeFrontUpdateCartPage.clickOnSubscribeBtn();
		assertTrue(storeFrontConsultantPage.getCurrentURL().contains(pwsPrefix),"The prefix of pws is NOT the default subscribed");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		s_assert.assertTrue(storeFrontAccountInfoPage.validateSubscribeToPulse(),"pulse is not subscribed for the user");
		s_assert.assertAll();
	}

	// qTest ID TC-571 : Verify that user can cancel CRP subscription from .com site through my account
	@Test(priority=5)
	public void testVerifyUserCanCancelCRPSubscriptionThroughMyAccountFromBiz_571q() throws InterruptedException {
		storeFrontHomePage.openBizPWSSite(country, env);
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB,DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO, countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO, countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		storeFrontAccountInfoPage.clickOnCancelMyCRP();
		// validate CRP has been cancelled..
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyCRPCancelled(), "CRP has not been cancelled");
		s_assert.assertAll();
	}

	//qTest ID TC-572 :Verify that user can cancel CRP subscription from .com site through my account
	@Test(priority=6)
	public void testVerifyUserCanCancelCRPSubscriptionThroughMyAccountFromCOM_572q() throws InterruptedException { 
		storeFrontHomePage.openComPWSSite(country, env);
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage=storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		storeFrontAccountInfoPage.clickOnCancelMyCRP();
		//validate CRP has been cancelled..
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyCRPCancelled(), "CRP has not been cancelled");
		s_assert.assertAll();
	}

	//qTest ID TC-574 :Verify that user can enroll in CRP through my account from .Biz site
	@Test(priority=7)
	public void testVerifyUserCanEnrollInCRPThroughMyAccountFromBIZ_574q() throws InterruptedException { 
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		storeFrontHomePage.openBizPWSSite(country, env);
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		if(country.equalsIgnoreCase("AU")) {
			storeFrontShippingInfoPage.clickOnWelcomeDropDown();
			storeFrontBillingInfoPage = storeFrontShippingInfoPage.clickBillingInfoLinkPresentOnWelcomeDropDown();
			storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
			storeFrontBillingInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
			storeFrontBillingInfoPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
			storeFrontBillingInfoPage.selectNewBillingCardExpirationDate(TestConstants.CARD_EXPIRY_MONTH, TestConstants.CARD_EXP_YEAR);
			storeFrontBillingInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
			storeFrontBillingInfoPage.selectNewBillingCardAddress();
			storeFrontBillingInfoPage.clickOnSaveBillingProfile();
			storeFrontBillingInfoPage.makeBillingProfileDefault(newBillingProfileName);
			s_assert.assertTrue(storeFrontBillingInfoPage.validateBillingProfileUpdated(),"Billing Profile is not updated!!");
		}
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage=storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		storeFrontAccountInfoPage.clickOnCancelMyCRP();
		//validate CRP has been cancelled..
		Assert.assertTrue(storeFrontAccountInfoPage.verifyCRPCancelled(), "CRP has not been cancelled");
		storeFrontAccountInfoPage.clickOnEnrollInCRP();
		//storeFrontAccountInfoPage.clickOnEnrollInCRP();
		storeFrontHomePage.selectProductForCRPAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.clickOnCRPCheckout();
		storeFrontHomePage.clickOnShippingNextStepButton();
		if(country.equalsIgnoreCase("AU"))
			storeFrontHomePage.clickBillToCardForSpecifiedProfile(newBillingProfileName+" "+lastName);
		storeFrontHomePage.clickOnBillingNextStepButtonWhileEnrollingCRP();
		storeFrontHomePage.clickOnSetupCRPAccountBtn();
		Assert.assertTrue(storeFrontHomePage.verifyOrderConfirmation(), "Order Confirmation Message has not been displayed");
		storeFrontHomePage.clickOnGoToMyAccountToCheckStatusOfCRP();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		Assert.assertTrue(storeFrontAccountInfoPage.verifyCurrentCRPStatus(), "Current CRP Status has not been Enrolled");
		s_assert.assertAll();
	}

	/**
	 * Login: PC
	 * Validations:
	 * * User should not be able to login on cancelling PC perks
	 * @throws InterruptedException
	 */
	//qTest ID 607:Verify that user can cancel PC Perks subscription through my account
	//qTest ID 768 :Verify PC account termination through my account
	@Test(priority=8)
	public void testPCUserCancelPcPerks_607q_768q() throws InterruptedException{
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");
		storeFrontPCUserPage.clickOnWelcomeDropDown();
		storeFrontPCUserPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontPCUserPage.clickOnYourAccountDropdown();
		storeFrontPCUserPage.clickOnPCPerksStatus();
		storeFrontPCUserPage.clickEditOrderDateBtn();
		storeFrontPCUserPage.clickDelayOrCancelPCPerks();
		storeFrontPCUserPage.clickPleaseCancelMyPcPerksActBtn();
		storeFrontPCUserPage.cancelMyPCPerksAct();
		storeFrontHomePage.loginAsConsultant(pcUserEmailID, password);
		s_assert.assertTrue(storeFrontHomePage.isCurrentURLShowsError(),"Inactive User doesn't get Login failed");
		s_assert.assertAll();
	}

	/**
	 * Login: PC
	 * Validations:
	 * * User should not be able to login on cancelling PC perks from .com site
	 * @throws InterruptedException
	 */
	//qTest ID TC-608 Verify that user can cancel PC Perks subscription from .com site through my account.
	@Test(priority=9)
	public void testPCUserCancelPcPerksFromComSite_608q() throws InterruptedException{
		storeFrontHomePage.openBizPWSSite(country, env);
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");
		storeFrontPCUserPage.clickOnWelcomeDropDown();
		storeFrontPCUserPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontPCUserPage.clickOnYourAccountDropdown();
		storeFrontPCUserPage.clickOnPCPerksStatus();
		storeFrontPCUserPage.clickEditOrderDateBtn();
		storeFrontPCUserPage.clickDelayOrCancelPCPerks();
		storeFrontPCUserPage.clickPleaseCancelMyPcPerksActBtn();
		storeFrontPCUserPage.cancelMyPCPerksAct();
		storeFrontHomePage.loginAsConsultant(pcUserEmailID, password);
		s_assert.assertTrue(storeFrontHomePage.isCurrentURLShowsError(),"Inactive User doesn't get Login failed");
		s_assert.assertAll();
	}

	//qTest ID 610:Enroll in pulse from my account - enrolling from 1st till 17th
	@Test(priority=10)
	public void testEnrollInPulseFromMyAccountEnrolligFrom1stTill17th_610q() throws InterruptedException{
		String accountId = null;
		List<Map<String, Object>> randomConsultantList =  null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String billingProfileName = TestConstants.BILLING_ADDRESS_NAME+randomNum;
		String userName = null;
		for(int i=0;i<TestConstants.LOOP_COUNT;i++) {
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITHOUT_PULSE_RFO,countryId),RFO_DB);
			userName = (String) getValueFromQueryResult(randomConsultantList, "UserEmail"); 
			//accountId = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			logger.info("Account Id of the user is "+accountId);
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(userName, password);
			boolean isError = driver.getCurrentUrl().contains("error");
			if(isError){
				logger.info("login error for the user "+userName);
				driver.get(driver.getURL()+"/"+country);
			}
			else
				break;
		}  
		logger.info("login is successful");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontConsultantPage.clickOnYourAccountDropdown();
		storeFrontConsultantPage.clickOnAutoshipStatusLink();
		storeFrontAccountInfoPage.clickOnCancelMyPulseSubscription();
		storeFrontAccountInfoPage.clickOnOnlySubscribeToPulseBtn();
		storeFrontAccountInfoPage.clickOnNextDuringPulseSubscribtion();
		//Enter Billing Profile
		storeFrontAccountInfoPage.clickAddNewBillingProfileLink();
		storeFrontAccountInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontAccountInfoPage.enterNewBillingNameOnCard(billingProfileName);
		storeFrontAccountInfoPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontAccountInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontAccountInfoPage.selectNewBillingCardAddress();
		storeFrontAccountInfoPage.clickOnSaveBillingProfile();
		storeFrontUpdateCartPage.clickOnAccountInfoNextButton();
		storeFrontUpdateCartPage.clickOnSubscribePulseTermsAndConditionsChkbox();
		storeFrontUpdateCartPage.clickOnSubscribeBtn();
		s_assert.assertTrue(storeFrontAccountInfoPage.isOrderPlacedSuccessfully(), "Order is not placed successfully");
		storeFrontAccountInfoPage.clickOnWelcomeDropDown();
		storeFrontOrdersPage = storeFrontAccountInfoPage.clickOrdersLinkPresentOnWelcomeDropDown();
		//Verify Status of CRP autoship template
		s_assert.assertTrue(storeFrontOrdersPage.getStatusOfSecondAutoshipTemplateID().toLowerCase().contains("pending"), "Expected status of second pulse autoship id is: pending and actual on UI is: "+storeFrontOrdersPage.getStatusOfSecondAutoshipTemplateID().toLowerCase());
		String pulseAutoshipID = storeFrontOrdersPage.getPulseAutoshipOrderNumber();
		String autoshipDate = storeFrontOrdersPage.getPulseAutoshipOrderDate();
		s_assert.assertTrue(storeFrontOrdersPage.validateSameDatePresentForAutoship(autoshipDate),"Same date is not present");
		s_assert.assertAll();
	}

	/**
	 * Login: Consultant
	 * Validations:
	 * * Cancel Subscription and verify
	 * * Subscribe Pulse and verify
	 */
	//qTest ID TC-613 To verify cancel Pulse
	// Hybris Project-2233:Verify that user can cancel Pulse subscription through my account.
	//Hybris Project-2134:EC-789- To Verify subscribe to pulse
	@Test(priority=11)
	public void testVerifyUserCanCancelPulseSubscriptionThroughMyAccount_613q_2233_2134() throws InterruptedException {
		String consultantEmailID = QueryUtils.getRandomActiveConsultantWithPWSFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,env,".com",country,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantWithPWSLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,env,".com",country,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage=storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		if(storeFrontAccountInfoPage.validatePulseCancelled()==true){
			storeFrontAccountInfoPage.clickOnSubscribeToPulseBtn();
			storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
			storeFrontUpdateCartPage.clickOnSubscribePulseTermsAndConditionsChkbox();
			storeFrontUpdateCartPage.clickOnSubscribeBtn();
			storeFrontConsultantPage.clickOnWelcomeDropDown();
			storeFrontAccountInfoPage.clickOnYourAccountDropdown();
			storeFrontAccountInfoPage.clickOnAutoShipStatus();
		}
		storeFrontAccountInfoPage.cancelPulseSubscription();
		s_assert.assertTrue(storeFrontAccountInfoPage.validatePulseCancelled(),"pulse subscription is not cancelled for the user");
		storeFrontAccountInfoPage.clickOnSubscribeToPulseBtn();
		storeFrontUpdateCartPage.clickOnAccountInfoNextButton();
		storeFrontUpdateCartPage.clickOnSubscribePulseTermsAndConditionsChkbox();
		storeFrontUpdateCartPage.clickOnSubscribeBtn();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		s_assert.assertTrue(storeFrontAccountInfoPage.validateSubscribeToPulse(),"Cancel subscribtion button should be preesent");
		s_assert.assertFalse(storeFrontAccountInfoPage.validatePulseCancelled(),"Subscribe pulse button should not be present");
		s_assert.assertAll();
	}	

	//qTest ID TC-617 :Verify that user can cancel Pulse subscription from .Biz site through my account
	@Test(priority=12)
	public void testVerifyUserCanCancelPulseSubscriptionThroughMyAccountFromBIZ_617q() throws InterruptedException { 
		storeFrontHomePage.openBizPWSSite(country, env);
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage=storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		storeFrontAccountInfoPage.clickOnCancelMyPulseSubscription();
		//validate Pulse has been cancelled..
		s_assert.assertTrue(storeFrontAccountInfoPage.validatePulseCancelled(), "Pulse have been not cancled");
		s_assert.assertAll();
	}

	//QTest ID TC-618 Verify subscribing to Pulse from My Account under .biz site
	@Test(priority=13)
	public void testSubscribingPulseMyAccount_618q() throws InterruptedException{
		// covered in 499
	}

	//qTest ID 623:VErify 2 fields for emails address and USerName is present
	@Test(priority=14)
	public void testVerifyUserNameAndEmailAddressFieldsValidation_623q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 100000);
		String userName="WestCoast"+randomNum;
		String userNameAU="WestCoast"+randomNum+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String EmailAddress=TestConstants.FIRST_NAME+randomNum+TestConstants.NEW_EMAIL_ADDRESS_SUFFIX;
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage= storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		if(country.equalsIgnoreCase("AU")){
			storeFrontAccountInfoPage.enterUserName(userNameAU);
		}else{
			storeFrontAccountInfoPage.enterUserName(userName);
			storeFrontAccountInfoPage.enterEmailAddress(EmailAddress);
		}
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalUpdateMessageDisplayed(),"Profile updation message not appear for correct username");
		logout();
		if(country.equalsIgnoreCase("US")||country.equalsIgnoreCase("CA")){
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(EmailAddress, password);
			s_assert.assertTrue(storeFrontConsultantPage.verifyConsultantPage(),"login is not successful");
			logout();
		}
		if(country.equalsIgnoreCase("AU")){
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(userNameAU, password);
		}else{
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(userName, password);
		}
		s_assert.assertTrue(storeFrontConsultantPage.verifyConsultantPage(),"login is not successful");
		s_assert.assertAll();
	}

	//qTest ID 624:verify Uniqueness of Username
	@Test(priority=15)
	public void testVerifyUniqnessOfUsername_624q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		List<Map<String, Object>> randomConsultantList =  null;
		String crossCountryConsultantEmailID = null;
		String existingConsultantEmailID = null;
		String multipleWordsUserName="username1 usernamet"+randomNum;
		String cid=null;
		if(country.equalsIgnoreCase("ca")){
			cid="14";
		}else if(country.equalsIgnoreCase("au")){
			cid="40";
		}
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,cid),RFO_DB);
		crossCountryConsultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		existingConsultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage= storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.enterUserName(existingConsultantEmailID);
		//storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isUsernameValidationErrorMsgPresent(),"existing consultant username should not be saved");
		storeFrontAccountInfoPage.enterUserName(crossCountryConsultantEmailID);
		//storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isUsernameValidationErrorMsgPresent(),"cross country consultant username should not be saved");
		storeFrontAccountInfoPage.enterUserName(multipleWordsUserName);
		//storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isUsernameValidationErrorMsgPresent(),"multi word consultant username should not be saved");
		storeFrontAccountInfoPage.enterUserName(consultantEmailID);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertFalse(storeFrontAccountInfoPage.isUsernameValidationErrorMsgPresent(),"valid consultant username should be saved");
		s_assert.assertAll();
	}

	// qTest ID -625: Version : 1 :: Username validations.
	@Test (priority=16)
	public void testUsernameValidations_625q() throws InterruptedException {
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"Account Info page has not been displayed");
		storeFrontAccountInfoPage.enterUserName(TestConstants.CONSULTANT_USERNAME_BELOW_6_DIGITS);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalErrorMessageDisplayed()||storeFrontAccountInfoPage.isUsernameValidationErrorMsgPresent(),"Validation for username less than 6  characters IS NOT PRESENT");
		storeFrontAccountInfoPage.enterUserName(TestConstants.CONSULTANT_USERNAME_MORE_THAN_6_DIGITS);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalErrorMessageDisplayed()||storeFrontAccountInfoPage.isUsernameValidationErrorMsgPresent(),"Validation for username more than 6 characters with space IS NOT PRESENT");
		storeFrontAccountInfoPage.enterUserName(TestConstants.CONSULTANT_USERNAME_MORE_THAN_6_SPECIAL_CHARS);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalErrorMessageDisplayed()||storeFrontAccountInfoPage.isUsernameValidationErrorMsgPresent(),"Validation for username more than 6 special characters IS NOT PRESENT");
		storeFrontAccountInfoPage.enterUserName(TestConstants.CONSULTANT_USERNAME_MORE_THAN_6_ALPHANUMERIC_CHARS_WITH_SPCLCHAR);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalErrorMessageDisplayed()||storeFrontAccountInfoPage.isUsernameValidationErrorMsgPresent(),"Validation for username more than 6 alphanumeric characters IS NOT PRESENT");
		s_assert.assertAll();
	}


	//qTest ID 629:Update USer name and Upgrade USer
	@Test(priority=3)
	public void testUpdateUserNameAndUpgradeUser_629() {
		//-------------------------local variables---------------------------------------------------------
		String randomNum = CommonUtils.getCurrentTimeStamp();
		String lastName = "lN"+randomNum;;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String emailAddress=firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		//-------------------------------------------------------------------------------------------------
		storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.clickOnCheckoutButton();
		storeFrontHomePage.enterNewRCDetails(firstName,lastName,emailAddress, password);
		storeFrontHomePage.enterMainAccountInfo(addressLine1,city, state, postalCode, phoneNumber);
		storeFrontHomePage.clickOnContinueWithoutSponsorLink();		
		storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
		storeFrontHomePage.clickOnShippingAddressNextStepBtn();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontHomePage.clickOnBillingNextStepBtn();
		storeFrontHomePage.clickPlaceOrderBtn();
		assertTrue(storeFrontHomePage.isOrderPlacedSuccessfully(), "RC User not enrolled successfully");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"Account Info page has not been displayed");
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountTerminationPage = storeFrontAccountInfoPage.clickTerminateMyAccount();
		storeFrontAccountTerminationPage.selectTerminationReason();
		storeFrontAccountTerminationPage.enterTerminationComments();
		storeFrontAccountTerminationPage.selectCheckBoxForVoluntarilyTerminate();
		storeFrontAccountTerminationPage.clickSubmitToTerminateAccount();
		storeFrontAccountTerminationPage.clickOnConfirmTerminationPopup();
		storeFrontHomePage.loginWithTerminatedUser(emailAddress,password);
		assertTrue(storeFrontHomePage.isCurrentURLShowsError(),"Terminated RC  User doesn't get Login failed");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.clickOnCheckoutButton();
		storeFrontHomePage.enterNewPCDetails(firstName, TestConstants.LAST_NAME+randomNum, emailAddress,password);
		storeFrontHomePage.enterMainAccountInfo(addressLine1, city, state, postalCode, phoneNumber);
		storeFrontHomePage.clickOnContinueWithoutSponsorLink();
		storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
		storeFrontHomePage.clickOnShippingAddressNextStepBtn();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontHomePage.clickOnBillingNextStepBtn();
		storeFrontHomePage.clickOnPCPerksTermsAndConditionsCheckBoxes();
		storeFrontHomePage.clickPlaceOrderBtn();
		assertTrue(storeFrontHomePage.isPCEnrolledCongratsMessagePresent(), "'Welcome to Rodan + Fields PC Perks' message has not appeared");
		logout();
		String rcUserEmailID = QueryUtils.getRandomActiveRCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId);
		storeFrontHomePage.loginAsRCUser(rcUserEmailID, password);
		assertTrue(storeFrontHomePage.hasRCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId),"Not able to login to the application from RC");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"Account Info page has not been displayed");
		String newUsername = firstName+randomNum;
		storeFrontAccountInfoPage.enterUserName(newUsername);
		storeFrontAccountInfoPage.clickSaveAccountBtn();
		storeFrontAccountInfoPage.clickOnRodanAndFieldsLogo();
		storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();  
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.clickOnCheckoutButton();
		storeFrontHomePage.clickYesIWantToJoinPCPerksCB();
		storeFrontHomePage.clickOnContinueWithoutSponsorLink();
		storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
		storeFrontHomePage.clickOnShippingAddressNextStepBtn();
		storeFrontHomePage.clickOnBillingNextStepBtn();
		storeFrontHomePage.checkIAcknowledgePCAccountCheckBox();
		storeFrontHomePage.checkPCPerksTermsAndConditionsCheckBox();
		storeFrontHomePage.clickPlaceOrderBtn();
		s_assert.assertTrue(storeFrontHomePage.isWelcomePCPerksMessageDisplayed(), "welcome PC perks message should be displayed");
		s_assert.assertAll(); 
	}

	/**
	 * Login: Consultant
	 * Validations:
	 * * Change password from my account and verify login working for the new password(then reset back)
	 * 
	 * @throws InterruptedException
	 */
	// qTest ID -630:Reset the password from the storefront and check login with new password
	@Test (priority=18)
	public void testResetPasswordFromStorefrontAndRecheckLogin_630q() throws InterruptedException {
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"Account Info page has not been displayed");
		storeFrontAccountInfoPage.enterOldPassword(password);
		storeFrontAccountInfoPage.enterNewPassword(TestConstants.CONSULTANT_NEW_PASSWORD);
		storeFrontAccountInfoPage.enterConfirmedPassword(TestConstants.CONSULTANT_NEW_PASSWORD);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		logout();
		storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID,TestConstants.CONSULTANT_NEW_PASSWORD);   
		s_assert.assertTrue(storeFrontConsultantPage.verifyConsultantPage(),"Consultant Page doesn't contain Welcome User Message");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"Account Info page has not been displayed");
		storeFrontAccountInfoPage.enterOldPassword(TestConstants.CONSULTANT_NEW_PASSWORD);
		storeFrontAccountInfoPage.enterNewPassword(password);
		storeFrontAccountInfoPage.enterConfirmedPassword(password);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertAll(); 
	}

	/**
	 * login by: Consultant
	 * Validations: 
	 * MyAccount Phone Numbers
	 * * show error for 11 digit phone number if starts with other than 1
	 *  *accept 11 digit phone number if starts with 1
	 *  *accept 10 digit phone number
	 * @throws InterruptedException
	 */
	// qTest ID -631: Verify the various field validations
	@Test(priority=19)
	public void testPhoneNumberFieldValidationForConsultant_631q() throws InterruptedException{
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"Account Info page has not been displayed");
		storeFrontAccountInfoPage.enterMainPhoneNumber(TestConstants.CONSULTANT_INVALID_11_DIGIT_MAIN_PHONE_NUMBER);
		if(driver.getCountry().equalsIgnoreCase("AU")){
			s_assert.assertTrue(storeFrontAccountInfoPage.verifyValidationMessageOfPhoneNumber(TestConstants.CONSULTANT_VALIDATION_MESSAGE_OF_MAIN_PHONE_NUMBER_AU),"Validation Message has not been displayed ");
		}else{
			s_assert.assertTrue(storeFrontAccountInfoPage.verifyValidationMessageOfPhoneNumber(TestConstants.CONSULTANT_VALIDATION_MESSAGE_OF_MAIN_PHONE_NUMBER),"Validation Message has not been displayed ");
		}
		storeFrontAccountInfoPage.enterMainPhoneNumber(TestConstants.CONSULTANT_VALID_11_DIGITMAIN_PHONE_NUMBER);
		s_assert.assertFalse(storeFrontAccountInfoPage.verifyValidationMessageOfPhoneNumber(TestConstants.CONSULTANT_VALIDATION_MESSAGE_OF_MAIN_PHONE_NUMBER),"Validation Message has been displayed For correct Phone Number");
		storeFrontAccountInfoPage.enterMainPhoneNumber(TestConstants.CONSULTANT_VALID_10_DIGIT_MAIN_PHONE_NUMBER);
		s_assert.assertFalse(storeFrontAccountInfoPage.verifyValidationMessageOfPhoneNumber(TestConstants.CONSULTANT_VALIDATION_MESSAGE_OF_MAIN_PHONE_NUMBER),"Validation Message has been displayed for ten digit phone number");
		s_assert.assertAll();
	}

	//QTest ID TC-634 Verify that user can change the information in 'my account info'
	@Test(priority=20)
	public void testAccountInformationForUpdate_634q() throws InterruptedException{
		int randomNumPhone = CommonUtils.getRandomNum(10000, 99999);
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String city = null;
		String stateName = null;
		String postalCode = null;
		String phoneNumber = null;
		String addressLine1 = null;
		if(country.equalsIgnoreCase("CA")){
			city = TestConstants.CONSULTANT_CITY_FOR_ACCOUNT_INFORMATION_CA;
			postalCode = TestConstants.CONSULTANT_POSTAL_CODE_FOR_ACCOUNT_INFORMATION_CA;
			phoneNumber = "99999"+randomNumPhone;
			addressLine1 =  TestConstants.CONSULTANT_ADDRESS_LINE1_FOR_ACCOUNT_INFORMATION_CA;
			stateName = TestConstants.CONSULTANT_STATE_FOR_ACCOUNT_INFORMATION_CA;
		}else if(country.equalsIgnoreCase("US")){
			city = TestConstants.CONSULTANT_CITY_FOR_ACCOUNT_INFORMATION_US;
			postalCode = TestConstants.CONSULTANT_POSTAL_CODE_FOR_ACCOUNT_INFORMATION_US;
			phoneNumber = "99999"+randomNumPhone;
			addressLine1 =  TestConstants.CONSULTANT_ADDRESS_LINE1_FOR_ACCOUNT_INFORMATION_US;
			stateName = TestConstants.CONSULTANT_STATE_FOR_ACCOUNT_INFORMATION_US;
		}else{
			city = TestConstants.CITY_AU;
			postalCode = TestConstants.POSTAL_CODE_AU;
			phoneNumber = TestConstants.PHONE_NUMBER_AU;
			addressLine1 =  TestConstants.ADDRESS_LINE_1_AU;
			stateName = "South Australia";
		}
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(), "Account Info page has not been displayed");
		String firstName = TestConstants.CONSULTANT_FIRST_NAME_FOR_ACCOUNT_INFORMATION+randomNum;
		String lastName = TestConstants.CONSULTANT_LAST_NAME_FOR_ACCOUNT_INFORMATION+randomNum;
		storeFrontAccountInfoPage.updateFirstName(firstName);
		storeFrontAccountInfoPage.updateLastName(lastName);
		storeFrontAccountInfoPage.updateAddressWithCityAndPostalCode(addressLine1, city, postalCode);
		String state = storeFrontAccountInfoPage.updateStateAndReturnName(stateName);
		storeFrontAccountInfoPage.updateMainPhnNumber(phoneNumber);
		storeFrontAccountInfoPage.updateDateOfBirthAndGender();
		storeFrontAccountInfoPage.uncheckSpouseCheckBox();
		storeFrontAccountInfoPage.clickSaveAccountBtn();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyFirstNameFromUIForAccountInfo(firstName), "First Name on UI is not updated");
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyLasttNameFromUIForAccountInfo(lastName), "Last Name on UI is not updated");
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyCityFromUIForAccountInfo(city), "City on UI is not updated");
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyProvinceFromUIForAccountInfo(state), "State/Province on UI is not updated");
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyPostalCodeFromUIForAccountInfo(postalCode), "Postal Code on UI is not updated");
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyMainPhoneNumberFromUIForAccountInfo(phoneNumber), "Phone Number on UI is not updated");
		s_assert.assertAll();
	}

	//QTest ID TC-635 Email and USer NAme has different values
	@Test(priority=21)
	public void testVerifyUserNameAndEmailAddressFieldsValidationWithDifferentValues_635q() throws InterruptedException {
		if(country.equalsIgnoreCase("ca")){
			int randomNum = CommonUtils.getRandomNum(10000, 100000);
			String userNameAU="WestCoast"+randomNum+TestConstants.EMAIL_ADDRESS_SUFFIX;
			String userName="WestCoast"+randomNum;
			String EmailAddress=TestConstants.FIRST_NAME+randomNum+TestConstants.NEW_EMAIL_ADDRESS_SUFFIX;
			String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
			storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
			storeFrontConsultantPage.clickOnWelcomeDropDown();
			storeFrontAccountInfoPage= storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
			if(country.equalsIgnoreCase("AU")){
				storeFrontAccountInfoPage.enterUserName(userNameAU);
			}else{
				storeFrontAccountInfoPage.enterUserName(userName);
				storeFrontAccountInfoPage.enterEmailAddress(EmailAddress);
			}
			storeFrontAccountInfoPage.clickSaveAccountPageInfo();
			s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalUpdateMessageDisplayed(),"Profile updation message not appear for correct username");
			logout();
			if(country.equalsIgnoreCase("US")||country.equalsIgnoreCase("CA")){
				storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(EmailAddress, password);
				s_assert.assertTrue(storeFrontConsultantPage.verifyConsultantPage(),"login is not successful");
				logout();
			}
			if(country.equalsIgnoreCase("AU")){
				storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(userNameAU, password);
			}else{
				storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(userName, password);
			}
			s_assert.assertTrue(storeFrontConsultantPage.verifyConsultantPage(),"login is not successful");
			s_assert.assertAll();
		}
		else{
			logger.info("not a CA related test");
		}
	}

	/**
	 * Login: RC (from com PWS)
	 * Validations: verify login from the updated username
	 * @throws InterruptedException
	 */
	//qTest ID 638: Change the username of RC user and Login with updated username
	@Test(priority=22)
	public void testchangeUsernameOfRcUserWithUpdatedUserName_638q() throws InterruptedException{
		int randomNumber =  CommonUtils.getRandomNum(10000, 1000000);
		String newUserName = TestConstants.NEW_RC_USER_NAME+randomNumber+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String rcUserEmailID = QueryUtils.getRandomActiveRCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId);
		storeFrontAccountInfoPage.openComPWSSite(country, env);
		storeFrontHomePage.loginAsRCUser(rcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasRCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId),"Not able to login to the application from RC");
		String oldUserNameOnUI = storeFrontHomePage.fetchingUserName();
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontRCUserPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.enterPhoneNumberAndPostalCode();
		storeFrontAccountInfoPage.enterNewUserNameAndClicKOnSaveButton(newUserName);
		s_assert.assertTrue(storeFrontAccountInfoPage.getUsernameFromAccount().equalsIgnoreCase(newUserName),"Your Profile has not been Updated");
		logout();
		storeFrontHomePage.loginAsRCUser(newUserName, password);
		String newUserNameOnUI = storeFrontHomePage.fetchingUserName();
		s_assert.assertTrue(storeFrontHomePage.verifyUserNameAfterLoginAgain(oldUserNameOnUI,newUserNameOnUI),"Login is not successful with new UserName");
		s_assert.assertAll();
	}

	//qTest ID 640: Modify name of the users
	@Test(priority=23)
	public void testModifyNameOfUser_640q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String lastName=TestConstants.LAST_NAME+randomNum;
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.enterNameOfUser(firstName,lastName);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalUpdateMessageDisplayed(),"Profile updation message not present on UI");
		s_assert.assertTrue(storeFrontAccountInfoPage.getFirstNameFromAccountInfo().equalsIgnoreCase(firstName),"First name was not updated");
		s_assert.assertTrue(storeFrontAccountInfoPage.getLastNameFromAccountInfo().equalsIgnoreCase(lastName),"Last name was not updated");
		s_assert.assertAll();
	}

	/**
	 * Login: Consultant
	 * Validations:
	 * * Success message on saving Main Phone Number, Mobile Number, Gender, Allow Spouse details
	 * @throws InterruptedException
	 */
	//qTest ID: 643:Add Birthday and gender and allow my sponsor information
	//qTest ID: 642:Modify Main Phone Number and mobile phone information
	@Test(priority=24)
	public void testAddBirthDayAndGenderAndAllowMySponsorInformation_642q_643q() throws InterruptedException{
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.enterMainPhoneNumber(phoneNumber);
		s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalUpdateMessageDisplayed(),"Profile updation message not present on UI");
		storeFrontAccountInfoPage.enterMobileNumber(phoneNumber);
		storeFrontAccountInfoPage.enterBirthDateOnAccountInfoPage(TestConstants.CONSULTANT_DAY_OF_BIRTH,TestConstants.CONSULTANT_MONTH_OF_BIRTH,TestConstants.CONSULTANT_YEAR_OF_BIRTH);
		storeFrontAccountInfoPage.clickOnGenderRadioButton(TestConstants.CONSULTANT_GENDER);
		storeFrontAccountInfoPage.clickOnAllowMySpouseOrDomesticPartnerCheckbox();
		storeFrontAccountInfoPage.enterSpouseFirstName(TestConstants.SPOUSE_FIRST_NAME);
		storeFrontAccountInfoPage.enterSpouseLastName(TestConstants.SPOUSE_LAST_NAME);
		storeFrontAccountInfoPage.clickOnSaveAfterEnterSpouseDetails();
		s_assert.assertTrue(storeFrontAccountInfoPage.isGlobalUpdateMessageDisplayed(),"Profile updation message not present on UI");	
		s_assert.assertAll();
	}

	/**
	 * Login: RC(from .biz site)
	 * Validations: 
	 * * Verify login with the updated username
	 * @throws InterruptedException
	 */
	//qTest ID 645:Change the username of RC user and Login with updated username
	@Test(priority=25)
	public void testChangeTheUserNameOfRCandLoginWithUpdateUsername_645q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newUserName = TestConstants.FIRST_NAME+randomNum+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String pws = storeFrontHomePage.getBizPWS(country,env);
		storeFrontHomePage.openPWSSite(pws);
		String rcUserEmailID = QueryUtils.getRandomActiveRCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId);
		storeFrontHomePage.loginAsRCUser(rcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasRCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId),"Not able to login to the application from RC"); 
		storeFrontRCUserPage.clickOnWelcomeDropDown();
		storeFrontRCUserPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontRCUserPage.enterNewUserNameAndClickSaveButton(newUserName);
		logout();
		storeFrontHomePage.loginAsRCUser(newUserName, password);
		s_assert.assertTrue(storeFrontHomePage.verifyWelcomeDropdownToCheckUserRegistered(), "User NOT registered successfully");
		s_assert.assertAll();
	}

	/**
	 * Login: Consultant with pulse
	 * Validation:
	 * * Verify that pulse application is opening in separate tab while clicking on Check My Pulse form account dropdown 
	 * @throws InterruptedException
	 */
	// qTest ID -652:Navigation to Consultant Pulse page from My Account
	@Test(priority=26)
	public void testAutoshipModuleCheckMyPulseUI_652q() throws InterruptedException  {
		String consultantEmailID = QueryUtils.getRandomActiveConsultantWithPWSFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,env,".biz",country,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantWithPWSLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,env,".biz",country,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontConsultantPage.clickCheckMyPulseLinkPresentOnWelcomeDropDown();
		//pass driver control to child window
		storeFrontConsultantPage.switchToChildWindow();
		//validate home page for pulse
		s_assert.assertTrue(storeFrontConsultantPage.validatePulseHomePage(),"Home Page for pulse is not displayed");
		storeFrontConsultantPage.switchToPreviousTab();
		s_assert.assertAll();
	}

	/**
	 * Login: Consultant and PC
	 * validations:
	 * * Welcome dropdown
	 * @throws InterruptedException
	 */
	// qTest ID -653: verify with Valid credentials and Logout
	@Test(priority=27)
	public void testVerifyLogoutwithValidCredentials_653q() throws InterruptedException{
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		s_assert.assertTrue(storeFrontHomePage.isWelcomeDropDownPresent(),"Consultant not logged in successfully on corp");
		logout();
		s_assert.assertFalse(storeFrontHomePage.isWelcomeDropDownPresent(),"Consultant not logged out successfully on corp");
		storeFrontHomePage.openBizPWSSite(country, env);
		storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		s_assert.assertTrue(storeFrontHomePage.isWelcomeDropDownPresent(),"Consultant not logged in successfully on biz");
		logout();
		s_assert.assertFalse(storeFrontHomePage.isWelcomeDropDownPresent(),"Consultant not logged out successfully on biz");
		storeFrontHomePage.openComPWSSite(country, env);		
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfullyOnPWS(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,country,countryId,env,TestConstants.SITE_TYPE_COM),"Not able to login to the application from PC");	
		s_assert.assertTrue(storeFrontHomePage.isWelcomeDropDownPresent(),"PC not logged in successfully on com");
		logout();
		s_assert.assertFalse(storeFrontHomePage.isWelcomeDropDownPresent(),"PC not logged out successfully on com");
		storeFrontHomePage.openBizPWSSite(country, env);
		storeFrontPCUserPage = storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		s_assert.assertTrue(storeFrontHomePage.isWelcomeDropDownPresent(),"PC not logged in successfully on biz");
		logout();
		s_assert.assertFalse(storeFrontHomePage.isWelcomeDropDownPresent(),"PC not logged out successfully on biz");
		s_assert.assertAll();
	}

	//qTest ID TC-767 : Verify Consultant Account Termination under PWS [.biz/.com]
	@Test(priority=28)
	public void testConsultantAccountTerminationUnderPWS_767q() throws InterruptedException{
		List<Map<String, Object>> randomConsultantList =  null;
		List<Map<String, Object>> randomConsultantEmailList =  null;
		String consultantEmailID = null;
		String accountID = null;
		String currentURL =null;
		storeFrontHomePage.openBizPWSSite(country, env);
		while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
			//consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");		
			accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			randomConsultantEmailList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID),RFO_DB);
			consultantEmailID = String.valueOf(getValueFromQueryResult(randomConsultantEmailList, "EmailAddress"));
			logger.info("Account Id of the user is "+accountID);
			logger.info("consultantEmailID of the user is "+consultantEmailID);
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			boolean isLoginError = driver.getCurrentUrl().contains("error");
			if(isLoginError){
				logger.info("Login error for the user "+consultantEmailID);
				if(driver.getCountry().equalsIgnoreCase("ca")||driver.getCountry().equalsIgnoreCase("au")){
					driver.get(driver.getURL()+"/"+driver.getCountry());
				}else{
					driver.get(driver.getURL());
				}
			}
			else
				break;
		}
		logger.info("login is successful");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountTerminationPage = storeFrontAccountInfoPage.clickTerminateMyAccount();
		storeFrontAccountTerminationPage.fillTheEntriesAndClickOnSubmitDuringTermination();
		s_assert.assertTrue(storeFrontAccountTerminationPage.verifyAccountTerminationIsConfirmedPopup(), "Account still exist");
		storeFrontAccountTerminationPage.clickConfirmTerminationBtn();
		storeFrontAccountTerminationPage.clickOnCloseWindowAfterTermination();
		storeFrontHomePage.clickOnCountryAtWelcomePage();
		currentURL = storeFrontHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("corprfo") , "User is not redirecting to Corp after terminate the consultant");
		s_assert.assertAll();
	}

	//qTest ID 906:Look up with Terminated Preffered consultant's Full name/ Account ID
	@Test(priority=29)
	public void testLookUpWithTerminatedPCFullNameOrAccountID_906q() throws InterruptedException{
		List<Map<String, Object>> randomPCUserList =  null;
		String pcUserEmailID = null;
		String accountId = null;
		for(int i=0;i<TestConstants.LOOP_COUNT;i++) {
			randomPCUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_AUTOSHIPS_RFO,countryId),RFO_DB);
			pcUserEmailID = (String) getValueFromQueryResult(randomPCUserList, "UserName");  
			accountId = String.valueOf(getValueFromQueryResult(randomPCUserList, "AccountID"));
			logger.info("Account Id of the user is "+accountId);
			storeFrontPCUserPage = storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
			boolean isError = driver.getCurrentUrl().contains("error");
			if(isError){
				logger.info("Login error for the user "+pcUserEmailID);
				if(driver.getCountry().equalsIgnoreCase("ca")||driver.getCountry().equalsIgnoreCase("au")){
					driver.get(driver.getURL()+"/"+driver.getCountry());
				}else{
					driver.get(driver.getURL());
				}
			}
			else
				break;
		} 
		logger.info("login is successful");
		storeFrontPCUserPage.clickOnWelcomeDropDown();
		storeFrontPCUserPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontPCUserPage.clickOnYourAccountDropdown();
		storeFrontPCUserPage.clickOnPCPerksStatus();
		storeFrontPCUserPage.clickEditOrderDateBtn();
		storeFrontPCUserPage.clickDelayOrCancelPCPerks();
		storeFrontPCUserPage.clickPleaseCancelMyPcPerksActBtn();
		storeFrontPCUserPage.cancelMyPCPerksAct();
		navigateToStoreFrontBaseURL();
		storeFrontHomePage.clickConnectUnderConnectWithAConsultantSection();
		storeFrontHomePage.searchCID(accountId);
		s_assert.assertTrue(storeFrontHomePage.validateInvalidSponsor(),"Terminated PC User is Present!!!");
		s_assert.assertAll(); 
	}

	//qTest ID 1430:Verify that user can cancel CRP subscription through my account.
	@Test(priority=31)
	public void testVerifyUserCanCancelCRPSubscriptionThroughMyAccount_1430q() throws InterruptedException { 
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage=storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		storeFrontAccountInfoPage.clickOnCancelMyCRP();
		//validate CRP has been cancelled..
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyCRPCancelled(), "CRP has not been cancelled");
		s_assert.assertAll();
	}

	//qTest ID TC-2775 :Reactivate a Soft-terminated Consultant account
	@Test(priority=32)
	public void testReactivateASoftTerminatedConsultantAccount_2775q() throws InterruptedException {
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage=storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountTerminationPage = storeFrontAccountInfoPage.clickTerminateMyAccount();
		storeFrontAccountTerminationPage.fillTheEntriesAndClickOnSubmitDuringTermination();
		s_assert.assertTrue(storeFrontAccountTerminationPage.validateConfirmAccountTerminationPopUp(), "confirm account termination pop up is not displayed");
		storeFrontAccountTerminationPage.clickConfirmTerminationBtn();
		s_assert.assertFalse(storeFrontAccountTerminationPage.validateConfirmAccountTerminationPopUp(), "confirm account termination pop up is still displayed");
		storeFrontAccountTerminationPage.clickOnCloseWindowAfterTermination();
		storeFrontHomePage.clickOnCountryAtWelcomePage();
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);  
		storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
		storeFrontHomePage.enterEmailAddress(consultantEmailID);
		storeFrontHomePage.clickOnEnrollUnderLastUpline();
		// For enroll the same consultant
		storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);  
		storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
		storeFrontHomePage.enterEmailAddress(consultantEmailID);
		storeFrontHomePage.enterPasswordForReactivationForConsultant();
		storeFrontHomePage.clickOnLoginToReactiveMyAccountForConsultant();
		s_assert.assertTrue(storeFrontHomePage.verifyWelcomeDropdownToCheckUserRegistered(), "User NOT registered successfully");
		s_assert.assertAll();
	}

	@Test(priority=33)
	public void testCheckAnotherConsultantSitePrefixWhileEnrollingToPulse_2811q() throws InterruptedException{
		String sitePrefix=null;
		List<Map<String, Object>> randomConsultantList=null;
		String consultantEmailID = null;
		for(int i=0;i<TestConstants.LOOP_COUNT;i++) {
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,driver.getEnvironment()+".biz",country,countryId),RFO_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
			String accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			List<Map<String, Object>> sitePrefixList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_ALREADY_EXISTING_SITE_PREFIX_RFO,countryId,accountID),RFO_DB);
			sitePrefix=String.valueOf(getValueFromQueryResult(sitePrefixList, "SitePrefix"));
			logger.info("Account Id of the user is "+accountID);
			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			boolean isLoginError = driver.getCurrentUrl().contains("error");
			if(isLoginError){
				logger.info("Login error for the user "+consultantEmailID);
				driver.get(driver.getURL()+"/"+country);
			}
			else
				break;
		}
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage=storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontHomePage.clickOnYourAccountDropdown();
		storeFrontHomePage.clickOnAutoshipStatusLink();
		if(storeFrontAccountInfoPage.validateSubscribeToPulse()){
			storeFrontHomePage.cancelPulseSubscription();
			s_assert.assertTrue(storeFrontAccountInfoPage.validatePulseCancelled(),"pulse subscription is not cancelled for the user");
		}
		storeFrontAccountInfoPage.clickOnOnlySubscribeToPulseBtn();
		//s_assert.assertTrue(storeFrontAccountInfoPage.getWebsitePrefixName().contains(sitePrefix),"While subscribing to pulse the same website prefix is not suggested for user");
		//		s_assert.assertFalse(storeFrontHomePage.verifyAnotherConsultantPrefixIsNotAllowed(sitePrefix),"Same Consultant site prefix is not present");
		//Get Another consultant site prefix.
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,driver.getEnvironment()+".biz",country,countryId),RFO_DB);
		String accountIdUsedToGetAnotherConsultantSitePrefix = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
		List<Map<String, Object>> AnotherConsultantSitePrefixList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_ALREADY_EXISTING_SITE_PREFIX_RFO,countryId,accountIdUsedToGetAnotherConsultantSitePrefix),RFO_DB);
		String sitePrefixOfAnotherConsultant=String.valueOf(getValueFromQueryResult(AnotherConsultantSitePrefixList, "SitePrefix"));
		logger.info("Another Consultant website prefix is "+sitePrefixOfAnotherConsultant);
		
		//Validate already existing site prefix allows consultant enrollment.
		storeFrontHomePage.enterWebsitePrefixName(sitePrefixOfAnotherConsultant);
		//s_assert.assertTrue(storeFrontHomePage.verifyAnotherConsultantPrefixIsNotAllowed(sitePrefixOfAnotherConsultant),"Another consultant site prefix is accepted");
		if(storeFrontHomePage.validateErrorMessagePleaseFixThisFieldDisplayed()){
			s_assert.assertTrue(storeFrontHomePage.verifyAnotherConsultantPrefixIsNotAllowedErrorMessageFixThisField().contains("Please fix this field"),"");
		}
		else
		{
			s_assert.assertTrue(storeFrontHomePage.verifyAnotherConsultantPrefixIsNotAllowedErrorMessageNotAvailable().contains("is not available"),"");
		}
				
		s_assert.assertAll();
	}

	//qTest ID 2872 Verify creating crp autoship from My Account under .biz site 
	@Test (priority=34)
	public void testCreatingCRPAutoshipUnderBizSite_2872q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String enrollmentType = TestConstants.STANDARD_ENROLLMENT;
		storeFrontHomePage.openPWSSite(country, env);
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, TestConstants.FIRST_NAME+randomNum, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state,postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(TestConstants.FIRST_NAME+randomNum);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.uncheckPulseAndCRPEnrollment();
		s_assert.assertTrue(storeFrontHomePage.verifySubsribeToPulseCheckBoxIsNotSelected(), "Subscribe to pulse checkbox selected after uncheck");
		s_assert.assertTrue(storeFrontHomePage.verifyEnrollToCRPCheckBoxIsNotSelected(), "Enroll to CRP checkbox selected after uncheck");
		storeFrontHomePage.clickNextButton();
		s_assert.assertTrue(storeFrontHomePage.isTheTermsAndConditionsCheckBoxDisplayed(), "Terms and Conditions checkbox is not visible");
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnEnrollMeBtn();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		s_assert.assertTrue(storeFrontHomePage.verifyWelcomeDropdownToCheckUserRegistered(), "User NOT registered successfully");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"shipping info page has not been displayed");
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		storeFrontAccountInfoPage.clickOnEnrollInCRP();;
		storeFrontHomePage.selectProductForCRPAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.clickOnCRPCheckout();
		storeFrontHomePage.clickOnUpdateCartShippingNextStepBtnDuringEnrollment();
		storeFrontHomePage.clickOnBillingNextStepBtn();
		storeFrontHomePage.clickOnSetupCRPAccountBtn();
		s_assert.assertTrue(storeFrontHomePage.verifyOrderConfirmation(), "Order Confirmation Message has not been displayed");
		storeFrontHomePage.clickOnGoToMyAccountToCheckStatusOfCRP();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoShipStatus();
		s_assert.assertTrue(storeFrontAccountInfoPage.verifyCurrentCRPStatus(), "Current CRP Status has not been Enrolled");
		s_assert.assertAll();
	}

	//qTest ID 2874 Terminate User and Login with User Name
	@Test (priority=35)
	public void terminateUserAndLoginWithSameUsername_2874q() throws InterruptedException{
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String userName="user"+randomNumber+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		Assert.assertTrue(storeFrontAccountInfoPage.verifyAccountInfoPageIsDisplayed(),"Account Info page has not been displayed");
		storeFrontAccountInfoPage.enterUserName(userName);
		//		storeFrontAccountInfoPage.enterSpouseFirstName(TestConstants.SPOUSE_FIRST_NAME);
		//		storeFrontAccountInfoPage.enterSpouseLastName(TestConstants.SPOUSE_LAST_NAME);
		storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountTerminationPage = storeFrontAccountInfoPage.clickTerminateMyAccount();
		storeFrontAccountTerminationPage.fillTheEntriesAndClickOnSubmitDuringTermination();
		s_assert.assertTrue(storeFrontAccountTerminationPage.validateConfirmAccountTerminationPopUp(), "confirm account termination pop up is not displayed");
		storeFrontAccountTerminationPage.clickConfirmTerminationBtn();
		s_assert.assertFalse(storeFrontAccountTerminationPage.validateConfirmAccountTerminationPopUp(), "confirm account termination pop up is still displayed");
		storeFrontAccountTerminationPage.clickOnCloseWindowAfterTermination();
		storeFrontHomePage.clickOnCountryAtWelcomePage();
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.isCurrentURLShowsError(),"Terminated User doesn't get Login failed using email ID");  
		navigateToStoreFrontBaseURL();
		storeFrontHomePage.loginAsConsultant(userName, password);
		s_assert.assertTrue(storeFrontHomePage.isCurrentURLShowsError(),"Terminated User doesn't get Login failed using username");  
		s_assert.assertAll();
	}

	/**
	 * Login: Consultant
	 * Validations:
	 * * Change username from account info page, logout and verify login success from username
	 * *  Verify saved username comes prepopulated on account info page
	 * @throws InterruptedException
	 */
	// Hybris Project-4260 :: Version : 1 :: UserName Field: Edit & Login 
	// Hybris Project-5000:Modify Email Address for User
	//Hybris Project-4274:UserName Field Validation
	// Hybris Project-4279:Change User Name Multiple Times and SAve
	@Test(priority=36)
	public void testVerifyChangeUserNameFieldsMultipleTimeAndValidateLoginWithLastChange_4279_4274_5000_4260() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 100000);
		String userName="cons"+randomNum;
		int i=0;
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage= storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		for(i=1;i<=3;i++){
			storeFrontAccountInfoPage.enterUserName(userName+i+TestConstants.EMAIL_ADDRESS_SUFFIX);
			storeFrontAccountInfoPage.clickSaveAccountPageInfo();
		}
		logout();
		storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(userName+(i-1)+TestConstants.EMAIL_ADDRESS_SUFFIX, password);
		s_assert.assertTrue(storeFrontConsultantPage.verifyConsultantPage(),"login is not successful");
		s_assert.assertAll();
	}

}