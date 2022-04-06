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
import com.rf.pages.website.storeFront.StoreFrontConsultantPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.pages.website.storeFront.StoreFrontOrdersPage;
import com.rf.pages.website.storeFront.StoreFrontPCUserPage;
import com.rf.pages.website.storeFront.StoreFrontShippingInfoPage;
import com.rf.pages.website.storeFront.StoreFrontUpdateCartPage;
import com.rf.test.website.RFWebsiteBaseTest;

public class ExpressEnrollmentValidationTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(ExpressEnrollmentValidationTest.class.getName());


	//qTest ID-2881 Enroll as consultant using invalid card numbers
	@Test
	public void testEnrollAsConsultantUsingInvalidCardNumbers_2881q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String firstName=TestConstants.FIRST_NAME + randomNum;
		String lastName=TestConstants.LAST_NAME + randomNum;
		storeFrontHomePage.openBizPWSSite(country, env);
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.selectKitAndEnrollmentType(kitName, regimenName, enrollmentType);
		storeFrontHomePage.enterUserInformationForEnrollment(firstName, lastName, password, addressLine1, city, state,postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		s_assert.assertTrue(storeFrontHomePage.recurringMonthlyChargesSection(), "Recurring Monthly Charges Section should be displayed ");
		s_assert.assertTrue(storeFrontHomePage.pulseSubscriptionTextbox(), "user can enter their PWS prefix");
		storeFrontHomePage.enterCardNumber(TestConstants.INVALID_CARD_NUMBER_15DIGITS);
		s_assert.assertTrue(storeFrontHomePage.validateInvalidCreditCardMessage(), "Please enter a valid credit card message is displayed");
		storeFrontHomePage.clearCreditCardNumber();
		storeFrontHomePage.enterCardNumber(TestConstants.INVALID_CARD_NUMBER_17DIGITS);
		s_assert.assertTrue(storeFrontHomePage.validateInvalidCreditCardMessage(), "Please enter a valid credit card message is displayed");
		storeFrontHomePage.clearCreditCardNumber();
		storeFrontHomePage.enterCardNumber(TestConstants.INVALID_CARD_NUMBER_15DIGITS_WITH_SPECIAL_CHAR);
		s_assert.assertTrue(storeFrontHomePage.validateInvalidCreditCardMessage(), "Please enter a valid credit card message is displayed");
		s_assert.assertAll();

	}

	// qTest ID-475 Express enrollment -fields validation
	@Test
	public void testExpressEnrollmentFieldsValidationWithInvalidCardNumbers_475q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String accountNumber=null;
		String firstName=TestConstants.FIRST_NAME + randomNum;
		String lastName=TestConstants.LAST_NAME + randomNum;
		List<Map<String, Object>> randomConsultantAccountList =  null;
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");	
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String invalidSocialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(10000000, 99999999));
		String firstCountryNameFromPopup = null;
		String secondCountryNameFromPopup = null;
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		//Search Invalid sponsor by ID
		storeFrontHomePage.searchCID(TestConstants.SPONSOR_ID_US);
		s_assert.assertTrue(storeFrontHomePage.getErrorMessageForInvalidSponser().contains("No result found"),"Error message for invalid sponser id does not visible");
		//Search Invalid sponsor by Name	
		storeFrontHomePage.searchCID(TestConstants.INVALID_SPONSOR_NAME);
		s_assert.assertTrue(storeFrontHomePage.getErrorMessageForInvalidSponser().contains("No result found"),"Error message for invalid sponser Name does not visible");
		//Search valid sponsor by ID
		storeFrontHomePage.searchCID(accountNumber);
		//Search valid sponsor by Name
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.selectKitAndEnrollmentType(kitName, regimenName, enrollmentType);
		storeFrontHomePage.enterInvalidPassword(TestConstants.PASSWORD_BELOW_6CHARS);
		s_assert.assertTrue(storeFrontHomePage.getInvalidPasswordMessage().contains("Please enter 6-24 characters, with at least 1 number and 1 letter."),"Error message for invalid password does not visible");
		storeFrontHomePage.enterPassword(password);
		storeFrontHomePage.enterInvalidConfirmPassword(TestConstants.PASSWORD_BELOW_6CHARS);
		s_assert.assertTrue(storeFrontHomePage.getInvalidPasswordNotmatchingMessage().contains("Your passwords do not match"),"Error message for invalid confirm password does not visible");
		storeFrontHomePage.enterUserInformationForEnrollment(firstName,lastName, password, addressLine1, city, state, postalCode, phoneNumber);
		storeFrontHomePage.clickOnNotYourCountryLink();
		firstCountryNameFromPopup = storeFrontHomePage.getCountryNameFromNotYourCountryPopUp("1");
		secondCountryNameFromPopup = storeFrontHomePage.getCountryNameFromNotYourCountryPopUp("2");
		if (driver.getCountry().equalsIgnoreCase("AU")) {
			s_assert.assertTrue(firstCountryNameFromPopup.contains("Switch to Canada")&& secondCountryNameFromPopup.contains("Switch to United States"),"Not Your Country Popup should display country as Canada and United States");
		} else if (driver.getCountry().equalsIgnoreCase("CA")) {
			s_assert.assertTrue(firstCountryNameFromPopup.contains("Switch to Australia")&& secondCountryNameFromPopup.contains("Switch to United States"),"Not Your Country Popup should display country as Australia and United States");
		} else if (driver.getCountry().equalsIgnoreCase("US")) {
			s_assert.assertTrue(firstCountryNameFromPopup.contains("Switch to Australia")&& secondCountryNameFromPopup.contains("Switch to Canada"),"Not Your Country Popup should display country as Australia and canada");
		}
		storeFrontHomePage.clickOnCancelButtonOfNotYourCountryPopUp();
		storeFrontHomePage.clickNextButton();
		s_assert.assertTrue(storeFrontHomePage.recurringMonthlyChargesSection(),"Recurring Monthly Charges Section should be displayed ");
		s_assert.assertTrue(storeFrontHomePage.pulseSubscriptionTextbox(), "user can enter their PWS prefix");
		if (driver.getCountry().equalsIgnoreCase("CA")) {
			storeFrontHomePage.enterSocialInsuranceNumber(invalidSocialInsuranceNumber);
			s_assert.assertTrue(storeFrontHomePage.isErrorMessagePresentForInvalidSSN(),"Error message for invalid SSN does not visible");
		}
		storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName,cardExpMonth, cardExpYear, CVV);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		s_assert.assertTrue(storeFrontHomePage.isTheTermsAndConditionsCheckBoxDisplayed(),"Terms and Conditions checkbox is not visible");
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		s_assert.assertTrue(storeFrontHomePage.validateErrorMessageWithoutSelectingAllCheckboxes(),"A proper error message should be displayed when continuining withoutselecting all the checkboxes");
		storeFrontHomePage.closePopUp();
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertAll();

	}

	//qTest ID-453 Allow my Spouse through EnrollmentTest
	@Test(priority=2)
	public void testAllowMySpouseThroughEnrollment_453q() throws InterruptedException {
		//-------------------------local variables---------------------------------------------------------
		String randomNum = CommonUtils.getCurrentTimeStamp();
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
		String kitName = TestConstants.KIT_NAME_PERSONAL;
		String lastName = "lN"+randomNum;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String spouseFirstName=TestConstants.SPOUSE_FIRST_NAME+randomNum;
		String spouseLastName=TestConstants.SPOUSE_LAST_NAME+randomNum;
		//-------------------------------------------------------------------------------------------------

		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();  
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName,regimenName,enrollmentType, consultantFirstName, consultantLastName+randomNum,  password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(consultantFirstName);
		storeFrontHomePage.addMySpouse(spouseFirstName, spouseLastName);
		storeFrontHomePage.clickXSpousePopup();
		s_assert.assertFalse(storeFrontHomePage.isSpousePopupDisplayed(), "Spouse popup not closed after clicking on X button");
		storeFrontHomePage.addMySpouse(spouseFirstName, spouseLastName);
		storeFrontHomePage.clickCancelSpousePopup();
		s_assert.assertFalse(storeFrontHomePage.isSpousePopupDisplayed(), "Spouse popup not closed after clicking on X button");
		storeFrontHomePage.addMySpouse(spouseFirstName, spouseLastName);
		storeFrontHomePage.clickAcceptSpousePopup();
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontAccountInfoPage.isSpouseFirstNamePresent(spouseFirstName),"Spouse firstname is not added after enrollment on account info page");
		s_assert.assertAll();
	}

	//qTest ID-2831  Check the shipping method disclaimers for " UPS Standard Overnight/FedEx Standard Overnight" 
	@Test(enabled=true)
	public void testCheckShippingMethodDisclaimersForUPSStandardOvernight_2831q() throws InterruptedException{
		if(country.equalsIgnoreCase("ca")){
			int randomNum = CommonUtils.getRandomNum(10000, 1000000);
			String lastName = "lN";
			String firstName=TestConstants.FIRST_NAME+randomNum;
			String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
			storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
			storeFrontConsultantPage.hoverOnShopLinkAndClickAllProductsLinks();
			storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
			storeFrontUpdateCartPage.clickOnCheckoutButton();
			storeFrontUpdateCartPage.selectShippingMethodUPSStandardOverNightForAdhocOrder();
			String selectedShippingMethodName = storeFrontUpdateCartPage.getSelectedShippingMethodName();
			storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
			storeFrontUpdateCartPage.clickOnDefaultBillingProfileEdit();
			storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
			storeFrontUpdateCartPage.clickPlaceOrderBtn();
			s_assert.assertTrue(storeFrontUpdateCartPage.verifyOrderPlacedConfirmationMessage(), "Order has been not placed successfully");
			s_assert.assertTrue(storeFrontUpdateCartPage.verifyShippingAddressContainsShippingMethodNameAfterPlaceOrder(selectedShippingMethodName),"Thanku for your order page doesn't contain shipping method name "+selectedShippingMethodName);
			String shippingChargesOnOrderConfirmationPage = storeFrontUpdateCartPage.getShippingChargesAtOrderConfirmationPage();
			s_assert.assertTrue(selectedShippingMethodName.toLowerCase().contains(shippingChargesOnOrderConfirmationPage.toLowerCase()), "Expected shipping method cost is on order confirmation page "+selectedShippingMethodName+" Actual on UI is "+shippingChargesOnOrderConfirmationPage);
			String orderNumber = storeFrontUpdateCartPage.getOrderNumberAfterPlaceOrder();
			storeFrontUpdateCartPage.clickOnWelcomeDropDown();
			storeFrontOrdersPage = storeFrontUpdateCartPage.clickOrdersLinkPresentOnWelcomeDropDown();
			storeFrontOrdersPage.clickOrderNumber(orderNumber);
			s_assert.assertTrue(storeFrontOrdersPage.verifyShippingMethodOnTemplateAfterAdhocOrderPlaced(selectedShippingMethodName),"Adhoc order page doesn't contain shipping method name "+selectedShippingMethodName);
			String shippingChargesFromOrderDetails = storeFrontOrdersPage.getShippingChargesFormOrderDetailsPage();
			s_assert.assertTrue(selectedShippingMethodName.toLowerCase().contains(shippingChargesFromOrderDetails.toLowerCase()), "Expected shipping method cost is on order details page "+selectedShippingMethodName+" Actual on UI is "+shippingChargesFromOrderDetails);
			s_assert.assertAll();
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}

	//Hybris Project-93 :: Version : 1 :: Express Enrollment Billing Profile Main Account Info - Edit 
	@Test(enabled=false)// Covered in TC-2202
	public void testExpressEnrollmentBillingProfileMainAccountInfoEdit_93() throws InterruptedException	{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		storeFrontHomePage.openPWSSite(country, env);
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, TestConstants.FIRST_NAME+randomNum, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(TestConstants.FIRST_NAME+randomNum);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		//click edit next to main account info and validate setup account page is displayed
		storeFrontHomePage.clickOnReviewAndConfirmShippingEditBtn();
		s_assert.assertTrue(storeFrontHomePage.validateSetUpAccountPageIsDisplayed(), "SetUp account page is not displayed");
		//Edit contact Info and re-enter password
		storeFrontHomePage.reEnterContactInfoAndPassword();
		//validate updated Account Info Details on review and confirmation page
		s_assert.assertTrue(storeFrontHomePage.validateUpdatedMainAccountInfo());
		storeFrontHomePage.checkThePoliciesAndProceduresCheckBox();
		storeFrontHomePage.checkTheIAcknowledgeCheckBox();  
		storeFrontHomePage.checkTheIAgreeCheckBox();
		storeFrontHomePage.checkTheTermsAndConditionsCheckBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertAll(); 
	}

	//qTest ID-2830 Consultant who cancels the pulse subscription should have their prefix active (only for the month)
	@Test
	public void testConsultantCancelPulseSubscriptionPrefixActive_2830q() throws InterruptedException  {
		//-------------------------local variables------------------------------------------------------------------------------------------------------------
		String randomNum = CommonUtils.getCurrentTimeStamp();
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
		String kitName = TestConstants.KIT_NAME_PERSONAL;
		String lastName = "lN"+randomNum;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		//------------------------------------------------------------------------------------------------------------------------------------------------------------
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, addressLine1, city, state,postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertTrue(storeFrontHomePage.validateUserLandsOnPWSbizSite(), "user didn't land on PWS .biz site");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		String currentPWSUrl=driver.getCurrentUrl();
		logger.info("PWS of the user is "+currentPWSUrl);
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontConsultantPage.clickOnYourAccountDropdown();
		storeFrontConsultantPage.clickOnAutoshipStatusLink();
		storeFrontConsultantPage.cancelPulseSubscription();
		logout();
		driver.get(currentPWSUrl);
		s_assert.assertTrue(storeFrontHomePage.isRodanAndFieldsLogoDisplayed(), "Dot biz PWS is not active");   
		String currentComUrl= storeFrontHomePage.createBizToCom(currentPWSUrl);
		driver.get(currentComUrl);
		s_assert.assertTrue(storeFrontHomePage.validatePWS(), "Dot com PWS is not active"); 
		s_assert.assertAll();
	}

	//qTest ID-2829 CCS CA consultant Express Enrollment for Yukon province with US sponsor
	@Test
	public void ccsCAConsultantExpressEnrollmentWithUSSponsor_2829q() throws InterruptedException	{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			//-------------------------local variables------------------------------------------------------------------------------------------------------------
			String randomNum = CommonUtils.getCurrentTimeStamp();
			String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
			String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
			String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
			String kitName = TestConstants.KIT_NAME_PERSONAL;
			String lastName = "lN"+randomNum;
			String firstName=TestConstants.FIRST_NAME+randomNum;
			String countryId = "236";
			String CCS = null;
			List<Map<String, Object>> sponsorIdList =  null;
			//------------------------------------------------------------------------------------------------------------------------------------------------------------

			sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_SPONSOR_ID,countryId),RFO_DB);
			CCS = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
			storeFrontHomePage.searchCID(CCS);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, addressLine1, TestConstants.YUKON_CITY, TestConstants.PROVINCE_YUKON, TestConstants.YUKON_POSTAL_CODE, phoneNumber);
			storeFrontHomePage.clickNextButton();
			storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);		
			storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
			storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
			storeFrontHomePage.clickNextButtonWithoutPopUp();
			storeFrontHomePage.selectAllTermsAndConditionsChkBox();
			storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
			storeFrontHomePage.clickOnConfirmAutomaticPayment();
			s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
			s_assert.assertAll();
		}
		else {
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}

	// qTest ID-2825 During enrollment process, switch from Express Enrollment to standard Enrollment
	@Test
	public void testDuringEnrollmentSwitchFromExpressToStandardEnrollment_2825q() throws InterruptedException {
		//-------------------------local variables------------------------------------------------------------------------------------------------------------
		String randomNum = CommonUtils.getCurrentTimeStamp();
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
		String kitName = TestConstants.KIT_NAME_PERSONAL;
		String lastName = "lN"+randomNum;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, TestConstants.FIRST_NAME+randomNum, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		storeFrontHomePage.clickOnSwitchToStandardEnrollmentLink(); 
		storeFrontHomePage.checkPulseAndCRPEnrollment();
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		storeFrontHomePage.selectProductForCRPAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.addQuantityOfProduct("5");
		storeFrontHomePage.clickOnNextBtnAfterAddingProductAndQty();
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertAll(); 
	}

	//Hybris Project-96:Express Enrollment Billing Info Shipping Info - New
	@Test(enabled=false)// Covered in TC-62
	public void testExpressEnrollmentBillingInfoShippingInfo_New_96() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		country = driver.getCountry();
		String newAddressLine1 = null;
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName = TestConstants.REGIMEN_NAME_REVERSE;
		String firstName=TestConstants.FIRST_NAME+randomNum;

		storeFrontHomePage = new StoreFrontHomePage(driver);
		storeFrontHomePage.openPWSSite(country, driver.getEnvironment());
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(TestConstants.FIRST_NAME+randomNum);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		//storeFrontHomePage.checkPulseAndCRPEnrollment();
		//storeFrontHomePage.uncheckCRPCheckBox();
		//storeFrontHomePage.uncheckPulseCheckBox();
		storeFrontHomePage.clickOnReviewAndConfirmShippingEditBtn();
		storeFrontHomePage.enterPassword(password);
		storeFrontHomePage.enterConfirmPassword(password);
		storeFrontHomePage.enterNewShippingAddressLine1DuringEnrollment(newAddressLine1);
		storeFrontHomePage.clickNextButton();
		s_assert.assertTrue(storeFrontHomePage.verifyNewAddressPresentInMainAccountInfo(newAddressLine1),"new address not present in main account info");
		s_assert.assertTrue(storeFrontHomePage.validateBillingAddressOnMainAccountInfo(addressLine1),"Billing address is not present as expected");
		s_assert.assertTrue(storeFrontHomePage.isTheTermsAndConditionsCheckBoxDisplayed(), "Terms and Conditions checkbox is not visible");
		storeFrontHomePage.checkThePoliciesAndProceduresCheckBox();
		storeFrontHomePage.checkTheIAcknowledgeCheckBox();  
		storeFrontHomePage.checkTheIAgreeCheckBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		s_assert.assertTrue(storeFrontHomePage.verifyPopUpForTermsAndConditions(), "PopUp for policies and procedures is not visible");
		storeFrontHomePage.checkTheTermsAndConditionsCheckBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		s_assert.assertTrue(storeFrontHomePage.verifyWelcomeDropdownToCheckUserRegistered(), "User NOT registered successfully");
		s_assert.assertAll();
	}

	//Hybris Project-95:Express Enrollment Billing Profile Main Account Info - New
	@Test(enabled=false)// Covered in TC-62
	public void testExpressEnrollmentBillingProfileMainAccountInfo_New_95() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		country = driver.getCountry();
		String newAddressLine1 = null;
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName = TestConstants.REGIMEN_NAME_REVERSE;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		storeFrontHomePage.openPWSSite(country, driver.getEnvironment());
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(TestConstants.FIRST_NAME+randomNum);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		//storeFrontHomePage.checkPulseAndCRPEnrollment();
		//storeFrontHomePage.uncheckCRPCheckBox();
		//storeFrontHomePage.uncheckPulseCheckBox();
		storeFrontHomePage.clickOnReviewAndConfirmShippingEditBtn();
		storeFrontHomePage.enterPassword(password);
		storeFrontHomePage.enterConfirmPassword(password);
		storeFrontHomePage.enterNewShippingAddressLine1DuringEnrollment(newAddressLine1);
		storeFrontHomePage.clickNextButton();
		s_assert.assertTrue(storeFrontHomePage.verifyNewAddressPresentInMainAccountInfo(newAddressLine1),"new address not present in main account info");
		s_assert.assertTrue(storeFrontHomePage.verifyPhoneNumberIsPresentInAccountInfo(),"Phone number is not present");
		s_assert.assertTrue(storeFrontHomePage.isTheTermsAndConditionsCheckBoxDisplayed(), "Terms and Conditions checkbox is not visible");
		storeFrontHomePage.checkThePoliciesAndProceduresCheckBox();
		storeFrontHomePage.checkTheIAcknowledgeCheckBox();  
		storeFrontHomePage.checkTheIAgreeCheckBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		s_assert.assertTrue(storeFrontHomePage.verifyPopUpForTermsAndConditions(), "PopUp for policies and procedures is not visible");
		storeFrontHomePage.checkTheTermsAndConditionsCheckBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		s_assert.assertTrue(storeFrontHomePage.verifyWelcomeDropdownToCheckUserRegistered(), "User NOT registered successfully");
		s_assert.assertAll();
	}

	//Hybris Project-5137:Global Sponsorship : Cross Country Sponsor
	@Test(enabled=false)//covered in TC-2251 Express
	public void testGlobalSponsorshipCrossCountrySponsor_5137() throws InterruptedException{
		RFO_DB = driver.getDBNameRFO(); 
		List<Map<String, Object>> randomConsultantList =  null;
		String accountID = null;

		country = driver.getCountry();
		String sponsorID = null;
		storeFrontHomePage = new StoreFrontHomePage(driver);

		// find cross country sponsor
		if(driver.getCountry().equalsIgnoreCase("us")){
			countryId = "40";
		}else{
			countryId = "236";
		}

		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
		accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
		logger.info("Account Id of the user is "+accountID);

		// Get Account Number
		List<Map<String, Object>>sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FOR_PWS,accountID),RFO_DB);
		sponsorID = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		s_assert.assertTrue(storeFrontHomePage.verifyFindYourSponsorPage(), "Find your sponsor page is not present");


		// assert for CID
		storeFrontHomePage.searchCID(sponsorID);
		s_assert.assertTrue(storeFrontHomePage.verifySponsorListIsPresentAfterClickOnSearch(), "sponsor list is not present when serach by CID");
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();

		String currentPWS = driver.getCurrentUrl();
		s_assert.assertFalse(currentPWS.contains("corp"), "After select sponsor current url does  contain corp");

		//complete the checkout process
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		country = driver.getCountry();
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName = TestConstants.REGIMEN_NAME_REVERSE;
		String firstName=TestConstants.FIRST_NAME+randomNum;

		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(TestConstants.FIRST_NAME+randomNum);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.checkThePoliciesAndProceduresCheckBox();
		storeFrontHomePage.checkTheIAcknowledgeCheckBox();  
		storeFrontHomePage.checkTheIAgreeCheckBox();
		storeFrontHomePage.checkTheTermsAndConditionsCheckBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertAll();
	}

	//qTest ID-502 Verify if already existing prefix is allowed during consultant enrollment.
	@Test
	public void testVerifyAlreadyExistingPrefixDuringConsultantEnrollment_502q() throws InterruptedException{
		List<Map<String, Object>> randomConsultantList=null;
		List<Map<String, Object>> sitePrefixList=null;

		//-------------------------local variables------------------------------------------------------------------------------------------------------------
		String randomNum = CommonUtils.getCurrentTimeStamp();
		String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
		String kitName = TestConstants.KIT_NAME_PERSONAL;
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, TestConstants.FIRST_NAME+randomNum, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,driver.getEnvironment()+".biz",driver.getCountry(),countryId),RFO_DB);
		String accountIdUsedToGetSitePrefix = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
		sitePrefixList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_ALREADY_EXISTING_SITE_PREFIX_RFO,countryId,accountIdUsedToGetSitePrefix),RFO_DB);
		String sitePrefix=String.valueOf(getValueFromQueryResult(sitePrefixList, "SitePrefix"));
		logger.info("existing website prefix is "+sitePrefix);
		storeFrontHomePage.enterWebsitePrefixName(sitePrefix);
		s_assert.assertTrue(storeFrontHomePage.verifyAlreadyExistingPrefixIsNotAllowed(),"Error message for already existing prefix is not present");
		s_assert.assertAll(); 
	}

	//Hybris Project-94:Express Enrollment Billing Profile Billing Info - Edit
	@Test(enabled=false)// Covered in TC-2202
	public void testExpressEnrollmentBillingProfileBillingInfo_Edit_94() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		country = driver.getCountry();
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName = TestConstants.REGIMEN_NAME_REVERSE;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		storeFrontHomePage.openPWSSite(country, driver.getEnvironment());
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(TestConstants.FIRST_NAME+randomNum);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.clickOnReviewAndConfirmBillingEditBtn();
		storeFrontHomePage.enterEditedCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		String expirationMonth = storeFrontHomePage.getExpirationMonth();
		String expirationYear = storeFrontHomePage.getExpirationYear();
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.clickNextButton();
		s_assert.assertTrue(storeFrontHomePage.validateBillingExpirationDate(expirationYear),"billling date is not as expected");
		s_assert.assertTrue(storeFrontHomePage.validateBillingInfoUpdated(expirationMonth,expirationYear),"billing info is not updated as expected");
		s_assert.assertAll();
	}

	//qTest ID - 843 Express Enrollment for Quebec province - not allowed
	@Test
	public void testExpressEnrollmentForQuebecProvince_notAllowed_843q() throws InterruptedException{
		if(driver.getCountry().trim().equalsIgnoreCase("CA")){
			String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
			String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
			String kitName = TestConstants.KIT_NAME_PERSONAL;    
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);  
			storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
			s_assert.assertTrue(storeFrontHomePage.verifyQuebecProvinceIsDisabled(),"Quebec province is enabled");
			s_assert.assertAll();
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}	

	//qTest ID - 2824 Express Enrollment for Yukon province
	@Test
	public void testExpressEnrollmentForYukonProvince_2824() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			//-------------------------local variables---------------------------------------------------------
			String randomNum = CommonUtils.getCurrentTimeStamp();
			String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
			String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
			String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
			String kitName = TestConstants.KIT_NAME_PERSONAL;
			String lastName = "lN"+randomNum;
			String firstName=TestConstants.FIRST_NAME+randomNum;
			String city = TestConstants.YUKON_CITY;
			String state = TestConstants.PROVINCE_YUKON;
			String postalCode = TestConstants.YUKON_POSTAL_CODE;
			//-------------------------------------------------------------------------------------------------
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();  
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.enterUserInformationForEnrollment(kitName,regimenName,enrollmentType, consultantFirstName, consultantLastName+randomNum,  password, addressLine1, city,state, postalCode, phoneNumber);
			storeFrontHomePage.clickNextButton();
			storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
			storeFrontHomePage.enterNameAsItAppearsOnCard(consultantFirstName);
			storeFrontHomePage.clickNextButtonWithoutPopUp();
			storeFrontHomePage.selectAllTermsAndConditionsChkBox();
			storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
			storeFrontHomePage.clickOnConfirmAutomaticPayment();
			s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}

	//Hybris Project-4770:pulse cancellation
	@Test(enabled=false)//ALready covered in Accounts TC-2233
	public void testPulseCancellation_4770() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String firstName = TestConstants.FIRST_NAME+randomNum;
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, 
				addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(TestConstants.FIRST_NAME+randomNum);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		//validate on the confirmation page user lands on .biz site
		//s_assert.assertTrue(storeFrontHomePage.validateUserLandsOnPWSbizSite(), "user didn't land on PWS .biz site");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		//Fetch the PWS url
		// String currentPWSUrl=driver.getCurrentUrl();
		//logger.info("PWS of the user is "+currentPWSUrl);
		s_assert.assertTrue(storeFrontHomePage.verifyWelcomeDropdownToCheckUserRegistered(), "User NOT registered successfully");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage=storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountInfoPage.clickOnAutoshipStatusLink();
		storeFrontAccountInfoPage.cancelPulseSubscription();
		s_assert.assertTrue(storeFrontAccountInfoPage.validatePulseCancelled(),"pulse has not been cancelled for consultant");
		s_assert.assertAll();
	}

	//qTest ID-488 : Consultant trying to enroll under invalid sponsor then follow 'select upline' flow and complete enro
	@Test
	public void testConsultantReenrollmentUnderInvalidSponsorThenSelectUplineFlowAndCompleteEnrollment_488q() throws InterruptedException{
		String consultantEmailID = null;
		List<Map<String, Object>> sponserList = null;
		List<Map<String, Object>> sponsorIdList = null;
		String sponserHavingPulse = null;
		String sponsorId = null;
		consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from consultant");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountTerminationPage = storeFrontAccountInfoPage.clickTerminateMyAccount();
		storeFrontAccountTerminationPage.fillTheEntriesAndClickOnSubmitDuringTermination();   
		s_assert.assertTrue(storeFrontAccountTerminationPage.verifyAccountTerminationIsConfirmedPopup(), "Account still exist");
		storeFrontAccountTerminationPage.clickOnConfirmTerminationPopup();
		storeFrontAccountTerminationPage.clickOnCloseWindowAfterTermination();
		storeFrontHomePage.clickOnCountryAtWelcomePage();
		sponserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_RANDOM_CONSULTANT_WITH_PWS_RFO,env,country,countryId),RFO_DB);
		sponserHavingPulse = String.valueOf(getValueFromQueryResult(sponserList, "AccountID"));
		sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FOR_PWS,sponserHavingPulse),RFO_DB);
		sponsorId = String.valueOf(getValueFromQueryResult(sponsorIdList, "AccountNumber"));
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsorId);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);  
		storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
		storeFrontHomePage.enterEmailAddress(consultantEmailID);
		s_assert.assertTrue(storeFrontHomePage.verifyInvalidSponsorPopupIsPresent(), "Invalid Sponsor popup is not present");
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

	//qTest ID - 762 Verify if inactive sponsor can be searched on Search Sponsor screen.
	@Test
	public void testInactiveSponsorSearch_762q() {
		List<Map<String, Object>> randomInactiveConsultantList =  null;
		List<Map<String, Object>> randomInactiveConsultantAccountNumberList =  null;
		String accountID = null;
		String accountNumber = null;
		randomInactiveConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_CONSULTANT_INACTIVE_RFO_4179,countryId),RFO_DB);
		accountID = String.valueOf(getValueFromQueryResult(randomInactiveConsultantList, "AccountID"));
		randomInactiveConsultantAccountNumberList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FOR_PWS,accountID),RFO_DB);
		accountNumber = String.valueOf(getValueFromQueryResult(randomInactiveConsultantAccountNumberList, "AccountNumber"));
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(accountNumber);
		s_assert.assertTrue(storeFrontHomePage.isNoSearchResultMsg(), "No such result message is not found for inactive account Number "+accountNumber);
		s_assert.assertAll();
	}

	// Hybris Project-3966:Use email id of existing inactive consultant (more than 180 days) during consultant enrollment under different sponsor's .biz site.
	@Test(enabled=false)//Query giving null values
	public void testUseEmailIdOfExistingInactiveConsultantDuringEnrollmentUnderDifferentBizSite_3966() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		List<Map<String, Object>> randomConsultantList =  null;
		List<Map<String, Object>> randomInactiveConsultantList =  null;
		String consultantPWS = null;
		String consultantEmailID = null;
		while (true) {
			randomConsultantList = DBUtil.performDatabaseQuery(
					DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,
							driver.getEnvironment() + ".biz", country, countryId),RFO_DB);

			consultantPWS = (String) getValueFromQueryResult(randomConsultantList, "URL");
			for(int i=1;i<=5;i++){
				randomInactiveConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(
						DBQueries_RFO.GET_RANDOM_CONSULTANTS_INACTIVE_MORE_THAN_180_DAYS, countryId), RFO_DB);
				consultantEmailID = (String) getValueFromQueryResult(randomInactiveConsultantList, "EmailAddress");
				if(consultantEmailID==null){
					continue;
				}
				else 
					break;				
			}

			driver.get(consultantPWS);
			boolean isLoginError = driver.getCurrentUrl().contains("sitenotfound");
			if (isLoginError) {
				logger.info("Site not found for the consultant PWS " + consultantPWS);
			} else
				break;
		}

		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, TestConstants.FIRST_NAME+randomNum, TestConstants.LAST_NAME+randomNum, consultantEmailID, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();

		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(TestConstants.FIRST_NAME+randomNum);
		storeFrontHomePage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButton();
		s_assert.assertTrue(storeFrontHomePage.isTheTermsAndConditionsCheckBoxDisplayed(), "Terms and Conditions checkbox is not visible");
		storeFrontHomePage.checkThePoliciesAndProceduresCheckBox();
		storeFrontHomePage.checkTheIAcknowledgeCheckBox();  
		storeFrontHomePage.checkTheIAgreeCheckBox();
		storeFrontHomePage.checkTheTermsAndConditionsCheckBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertAll(); 
	}

	//Hybris Project-4309 :: Version : 1 :: Soft-Terminated Consultant Cancel Reactivation
	@Test (enabled=false)//Covered in TC 4308
	public void testTerminateConsultantAndCancelReactivation_4309() throws InterruptedException{
		RFO_DB = driver.getDBNameRFO(); 
		List<Map<String, Object>> randomConsultantList =  null;
		String consultantEmailID = null;
		String accountID = null;
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName =  TestConstants.REGIMEN_NAME_REDEFINE;
		storeFrontHomePage = new StoreFrontHomePage(driver);
		country = driver.getCountry();

		while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
			accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			logger.info("Account Id of the user is "+accountID);
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
		/*s_assert.assertTrue(storeFrontAccountTerminationPage.verifyAccountTerminationIsConfirmedPopup(), "Account still exist");
				  storeFrontAccountTerminationPage.clickOnCloseWindowAfterTermination();*/
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
		s_assert.assertTrue(storeFrontHomePage.verifyInvalidSponsorPopupIsPresent(), "Invalid Sponsor popup is not present");
		storeFrontHomePage.clickOnCnacelEnrollment();

		//verify that user is inactive
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		s_assert.assertTrue(storeFrontHomePage.isCurrentURLShowsError(),"Terminated User doesn't get Login failed");  
		s_assert.assertAll();
	}

	//Hybris Project-4310 :: Version : 1 :: Soft-Terminate Consultant is not available for Sponsor's search
	@Test (enabled=false)//Covered in TC 4308
	public void testTerminatedConsultantIsNotAvailableAsSponsor_4310() throws InterruptedException{
		RFO_DB = driver.getDBNameRFO(); 
		List<Map<String, Object>> randomConsultantList =  null;
		String consultantEmailID = null;
		String accountID = null;
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName =  TestConstants.REGIMEN_NAME_REDEFINE;
		storeFrontHomePage = new StoreFrontHomePage(driver);
		country = driver.getCountry();
		String CCS = null;
		while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
			accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			logger.info("Account Id of the user is "+accountID);

			// Get Account Number
			List<Map<String, Object>>sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FOR_PWS,accountID),RFO_DB);
			CCS = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");

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
		/*s_assert.assertTrue(storeFrontAccountTerminationPage.verifyAccountTerminationIsConfirmedPopup(), "Account still exist");
					  storeFrontAccountTerminationPage.clickOnCloseWindowAfterTermination();*/
		s_assert.assertTrue(storeFrontAccountTerminationPage.validateConfirmAccountTerminationPopUp(), "confirm account termination pop up is not displayed");
		storeFrontAccountTerminationPage.clickConfirmTerminationBtn();
		s_assert.assertFalse(storeFrontAccountTerminationPage.validateConfirmAccountTerminationPopUp(), "confirm account termination pop up is still displayed");
		storeFrontAccountTerminationPage.clickOnCloseWindowAfterTermination();
		storeFrontHomePage.clickOnCountryAtWelcomePage();
		// search consultant as sponsor
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(CCS);
		s_assert.assertTrue(storeFrontHomePage.verifyTerminatedConsultantIsNotInSponsorList(), "Terminated Consultant is present in sponsor's list");
		s_assert.assertAll();
	}

	//Hybris Project-4311 :: Version : 1 :: Reactivated Soft-Terminated Consultant should be in the search for Sponsor list
	@Test (enabled=false)//Covered in TC 4308
	public void ReactivateTerminatedConsultantAndSearchInSponsorList_4311() throws InterruptedException{
		RFO_DB = driver.getDBNameRFO(); 
		List<Map<String, Object>> randomConsultantList =  null;
		String consultantEmailID = null;
		String accountID = null;
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName =  TestConstants.REGIMEN_NAME_REDEFINE;
		storeFrontHomePage = new StoreFrontHomePage(driver);
		country = driver.getCountry();
		String  CCS = null;

		while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
			accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			logger.info("Account Id of the user is "+accountID);

			// Get Account Number
			List<Map<String, Object>>sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FOR_PWS,accountID),RFO_DB);
			CCS = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");
			logger.info("Account number of the consultant is "+CCS);
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

		//s_assert.assertTrue(storeFrontConsultantPage.verifyConsultantPage(),"Consultant Page doesn't contain Welcome User Message");
		logger.info("login is successful");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontAccountInfoPage = storeFrontConsultantPage.clickAccountInfoLinkPresentOnWelcomeDropDown();
		storeFrontAccountInfoPage.clickOnYourAccountDropdown();
		storeFrontAccountTerminationPage = storeFrontAccountInfoPage.clickTerminateMyAccount();
		storeFrontAccountTerminationPage.fillTheEntriesAndClickOnSubmitDuringTermination();
		s_assert.assertTrue(storeFrontAccountTerminationPage.verifyAccountTerminationIsConfirmedPopup(), "Account still exist");
		storeFrontAccountTerminationPage.clickOnConfirmTerminationPopup();
		storeFrontAccountTerminationPage.clickOnCloseWindowAfterTermination();
		storeFrontHomePage.clickOnCountryAtWelcomePage();

		driver.get(driver.getURL()+"/"+driver.getCountry());
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
		accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
		logger.info("Account Id of the user is "+accountID);

		// Get Account Number
		List<Map<String, Object>>sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FOR_PWS,accountID),RFO_DB);
		CCS = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");


		storeFrontHomePage.searchCID(CCS);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);  
		storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
		storeFrontHomePage.enterEmailAddress(consultantEmailID);
		s_assert.assertTrue(storeFrontHomePage.verifyInvalidSponsorPopupIsPresent(), "Invalid Sponsor popup is not present");
		storeFrontHomePage.clickOnEnrollUnderLastUpline();

		// For enroll the same consultant
		storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);  
		storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
		storeFrontHomePage.enterEmailAddress(consultantEmailID);
		storeFrontHomePage.enterPasswordForReactivationForConsultant();
		storeFrontHomePage.clickOnLoginToReactiveMyAccountForConsultant();
		logout();
		driver.get(driver.getURL()+"/"+driver.getCountry());
		// search the same consultant in sponsor's list 
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(CCS);
		s_assert.assertTrue(storeFrontHomePage.verifyTerminatedConsultantPresentInSponsorList(), "Terminated Consultant is not present in sponsor's list");
		s_assert.assertAll();
	}

	//qTest ID - 2823 Express Enrollment for Nunavut province and tryto ship adhoc order at quebec address. 
	@Test(enabled=true)
	public void testExpressEnrollmentNunavutProvince_2823q() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			//-------------------------local variables---------------------------------------------------------
			String randomNum = CommonUtils.getCurrentTimeStamp();
			String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
			String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
			String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
			String kitName = TestConstants.KIT_NAME_PERSONAL;
			String lastName = "lN"+randomNum;
			String firstName=TestConstants.FIRST_NAME+randomNum;
			String addressLine1 = TestConstants.ADDRESS_LINE_1_NUNAVUT;
			String city = TestConstants.CITY_NUNAVUT;
			String state = TestConstants.PROVINCE_NUNAVUT;
			String postalCode = TestConstants.POSTAL_CODE_NUNAVUT;
			//-------------------------------------------------------------------------------------------------
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();  
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.enterUserInformationForEnrollment(kitName,regimenName,enrollmentType, consultantFirstName, consultantLastName+randomNum,  password, addressLine1, city,state, postalCode, phoneNumber);
			storeFrontHomePage.clickNextButton();
			storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
			storeFrontHomePage.enterNameAsItAppearsOnCard(consultantFirstName);
			storeFrontHomePage.clickNextButtonWithoutPopUp();
			storeFrontHomePage.selectAllTermsAndConditionsChkBox();
			storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
			storeFrontHomePage.clickOnConfirmAutomaticPayment();
			s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
			storeFrontHomePage.clickOnRodanAndFieldsLogo();
			storeFrontConsultantPage.clickOnWelcomeDropDown();
			storeFrontShippingInfoPage = storeFrontConsultantPage.clickShippingLinkPresentOnWelcomeDropDown();
			s_assert.assertTrue(storeFrontShippingInfoPage.verifyShippingInfoPageIsDisplayed(),"shipping info page has not been displayed");
			storeFrontShippingInfoPage.clickAddNewShippingProfileLink();
			s_assert.assertTrue(storeFrontHomePage.verifyQuebecProvinceIsDisabled(),"Quebec province in the province drop down is not disabled");
			s_assert.assertAll();
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}

	}

	//qTest ID - 2822 Express Enrollment for Northwest territories province and adding qubec address and make it default.
	@Test(enabled=true)
	public void testExpressEnrollmentNorthWestProvince_2822q() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			//-------------------------local variables---------------------------------------------------------
			String randomNum = CommonUtils.getCurrentTimeStamp();
			String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
			String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
			String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
			String kitName = TestConstants.KIT_NAME_PERSONAL;
			String lastName = "lN"+randomNum;
			String firstName=TestConstants.FIRST_NAME+randomNum;
			String addressLine1 = TestConstants.ADDRESS_LINE_1_NT;
			String city = TestConstants.CITY_NT;
			String state = TestConstants.PROVINCE_NORTHWEST_TERRITORIES;
			String postalCode = TestConstants.POSTAL_CODE_NT;
			//-------------------------------------------------------------------------------------------------
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();  
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.enterUserInformationForEnrollment(kitName,regimenName,enrollmentType, consultantFirstName, consultantLastName+randomNum,  password, addressLine1, city,state, postalCode, phoneNumber);
			storeFrontHomePage.clickNextButton();
			storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
			storeFrontHomePage.enterNameAsItAppearsOnCard(consultantFirstName);
			storeFrontHomePage.clickNextButtonWithoutPopUp();
			storeFrontHomePage.selectAllTermsAndConditionsChkBox();
			storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
			storeFrontHomePage.clickOnConfirmAutomaticPayment();
			s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
			storeFrontHomePage.clickOnRodanAndFieldsLogo();
			storeFrontConsultantPage.clickOnWelcomeDropDown();
			storeFrontShippingInfoPage = storeFrontConsultantPage.clickShippingLinkPresentOnWelcomeDropDown();
			s_assert.assertTrue(storeFrontShippingInfoPage.verifyShippingInfoPageIsDisplayed(),"shipping info page has not been displayed");
			storeFrontShippingInfoPage.clickAddNewShippingProfileLink();
			s_assert.assertTrue(storeFrontHomePage.verifyQuebecProvinceIsDisabled(),"Quebec province in the province drop down is not disabled");
			s_assert.assertAll();
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}

	//qTest ID - 2821 Customer living in Quebec cannot be enrolled as consultant. 
	@Test
	public void testCustomerLivingInQuebecCannotEnroll_2821q() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			//-------------------------local variables---------------------------------------------------------
			String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
			String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
			String kitName = TestConstants.KIT_NAME_PERSONAL;
			//-------------------------------------------------------------------------------------------------
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();  
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);		
			storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
			s_assert.assertTrue(storeFrontHomePage.verifyQuebecProvinceIsDisabled(),"Quebec province in the province drop down is not disabled");
			s_assert.assertAll();
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}

	// qTest ID -856 Enrolling RC living in Quebec 
	@Test
	public void testQuebecRCEnrollment_856q() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			int randomNum = CommonUtils.getRandomNum(10000, 1000000);		
			String lastName = "lN"+randomNum;;
			String firstName=TestConstants.FIRST_NAME+randomNum;
			String emailAddress=firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
			String addressLine1 = TestConstants.ADDRESS_LINE_1_QUEBEC;
			String city = TestConstants.CITY_QUEBEC;
			String postalCode = TestConstants.POSTAL_CODE_QUEBEC;
			phoneNumber = TestConstants.PHONE_NUMBER_CA;
			storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();
			storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
			storeFrontHomePage.clickOnCheckoutButton();
			storeFrontHomePage.enterNewRCDetails(firstName,lastName,emailAddress, password);
			storeFrontHomePage.enterMainAccountInfo(addressLine1, city, TestConstants.PROVINCE_QUEBEC, postalCode, phoneNumber);
			storeFrontHomePage.clickOnContinueWithoutSponsorLink();
			storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
			storeFrontHomePage.clickOnShippingAddressNextStepBtn();
			storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.clickOnBillingNextStepBtn();
			storeFrontHomePage.clickPlaceOrderBtn();
			s_assert.assertTrue(storeFrontHomePage.isOrderPlacedSuccessfully(), "Order Not placed successfully");
			s_assert.assertAll();	
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}

	//qTest ID -855 Enrolling PC living in Quebec 
	@Test
	public void testQuebecPCEnrollment_855q() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			int randomNum = CommonUtils.getRandomNum(10000, 1000000);		
			String lastName = "lN"+randomNum;;
			String firstName=TestConstants.FIRST_NAME+randomNum;
			String emailAddress=firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
			String addressLine1 = TestConstants.ADDRESS_LINE_1_QUEBEC;
			String city = TestConstants.CITY_QUEBEC;
			String postalCode = TestConstants.POSTAL_CODE_QUEBEC;
			phoneNumber = TestConstants.PHONE_NUMBER_CA;
			storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();
			storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
			storeFrontHomePage.clickOnCheckoutButton();
			storeFrontHomePage.enterNewPCDetails(firstName,lastName,emailAddress, password);
			storeFrontHomePage.enterMainAccountInfo(addressLine1, city, TestConstants.PROVINCE_QUEBEC, postalCode, phoneNumber);
			storeFrontHomePage.clickOnContinueWithoutSponsorLink();
			storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
			storeFrontHomePage.clickOnShippingAddressNextStepBtn();
			storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.clickOnBillingNextStepBtn();
			storeFrontHomePage.clickOnPCPerksTermsAndConditionsCheckBoxes();
			storeFrontHomePage.clickPlaceOrderBtn();
			s_assert.assertTrue(storeFrontHomePage.isPCEnrolledCongratsMessagePresent(), "'Welcome to Rodan + Fields PC Perks' message has not appeared");
			s_assert.assertAll();	
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}

	// qTest ID - 2820 Verify if QAS gives quebec address suggestions for address entered during consultant enrollment.
	@Test
	public void testQASGivesSuggestionsForQuebecAddress_2820q() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("ca")){
			int randomNum = CommonUtils.getRandomNum(10000, 1000000);
			String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
			String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
			String kitName = TestConstants.KIT_NAME_PERSONAL;    
			String  addressLine1 = TestConstants.ADDRESS_LINE_1_QUEBEC;
			String city = TestConstants.CITY_QUEBEC;
			String postalCode = TestConstants.POSTAL_CODE_QUEBEC;
			phoneNumber = TestConstants.PHONE_NUMBER_CA;
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, TestConstants.FIRST_NAME+randomNum, TestConstants.LAST_NAME+randomNum, password, addressLine1, city,TestConstants.PROVINCE_NUNAVUT, postalCode, phoneNumber);
			storeFrontHomePage.clickEnrollmentNextBtnWithoutHandlingPopUP();
			s_assert.assertTrue(storeFrontHomePage.verifyAndClickAcceptOnQASPopup(), "QAS pop up with Accept button for Quebec address suggestions has NOT appeared");
			s_assert.assertTrue(storeFrontHomePage.isQuebecNotEligibleAsConsultantErrorDisplayed(),"Quebec residents are ineligible to become a Consultant. message not displayed");
			s_assert.assertAll();
		}else{
			logger.info("NOT EXECUTED...Test is ONLY for CANADA env");
		}
	}

	// qTest ID - 2819 Email field validation for Active/Inactive users
	@Test(enabled=false)//waiting for query to fix this
	public void testEmailValidationsDuringEnroll_2819q() throws InterruptedException{
		if(country.equalsIgnoreCase("CA")) {
			List<Map<String, Object>> randomConsultantList =  null;
			List<Map<String, Object>> randomPCList =  null;
			List<Map<String, Object>> randomConsultantEmailList =  null;
			List<Map<String, Object>> randomPCEmailList =  null;
			List<Map<String, Object>> randomRCEmailList =  null;
			List<Map<String, Object>> randomRCUserList =  null;
			String terminatedConsultantEmailLessThan6month = null;
			String terminatedConsultantEmailMoreThan6month = null;
			String existingPCEmailID= null;
			String terminatedPCEmailLessThan90Days= null;
			String terminatedPCEmailMoreThan90Days= null;
			String rcUserEmailID = null;
			String consultantAccountID = null;
			String pcAccountId = null;

			String activeRCAccountId = null;
			//get terminated consultant user less than 6 month.
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_INACTIVE_CONSULTANT_LESS_THAN_6_MONTH_RFO,countryId),RFO_DB);
			consultantAccountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			randomConsultantEmailList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,consultantAccountID),RFO_DB);
			terminatedConsultantEmailLessThan6month = String.valueOf(getValueFromQueryResult(randomConsultantEmailList, "EmailAddress"));

			//get terminated consultant user more than 6 month.
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_INACTIVE_CONSULTANT_MORE_THAN_6_MONTH_RFO,countryId),RFO_DB);
			consultantAccountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
			randomConsultantEmailList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,consultantAccountID),RFO_DB);
			terminatedConsultantEmailMoreThan6month = String.valueOf(getValueFromQueryResult(randomConsultantEmailList, "EmailAddress"));

			//get existing PC email
			randomPCList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_AUTOSHIPS_RFO,countryId),RFO_DB);
			pcAccountId = String.valueOf(getValueFromQueryResult(randomPCList, "AccountID"));
			randomPCEmailList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,pcAccountId),RFO_DB);
			existingPCEmailID = String.valueOf(getValueFromQueryResult(randomPCEmailList, "EmailAddress"));

			//get terminated PC user less than 90 days 
			randomPCList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_INACTIVE_PC_LESS_THAN_90_DAYS_RFO,countryId),RFO_DB);
			pcAccountId = String.valueOf(getValueFromQueryResult(randomPCList, "AccountID"));
			randomPCEmailList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,pcAccountId),RFO_DB);
			terminatedPCEmailLessThan90Days = String.valueOf(getValueFromQueryResult(randomPCEmailList, "EmailAddress"));

			//get terminated PC user more than 90 days 
			randomPCList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_INACTIVE_PC_MORE_THAN_90_DAYS_RFO,countryId),RFO_DB);
			pcAccountId = String.valueOf(getValueFromQueryResult(randomPCList, "AccountID"));
			randomPCEmailList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,pcAccountId),RFO_DB);
			terminatedPCEmailMoreThan90Days = String.valueOf(getValueFromQueryResult(randomPCEmailList, "EmailAddress"));

			//get existing RC email
			randomRCUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId),RFO_DB);
			activeRCAccountId = String.valueOf(getValueFromQueryResult(randomRCUserList, "AccountID"));
			randomRCEmailList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,activeRCAccountId),RFO_DB);
			rcUserEmailID = String.valueOf(getValueFromQueryResult(randomRCEmailList, "EmailAddress"));

			//initiate consultant enrollment
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);  
			storeFrontHomePage.chooseEnrollmentOption(TestConstants.STANDARD_ENROLLMENT);

			// assertion for Active PC
			storeFrontHomePage.enterEmailAddress(existingPCEmailID);
			s_assert.assertTrue(storeFrontHomePage.verifyPopUpForExistingActivePC() , "Existing Active PC User email id should not be acceptable");

			// assertion for Inactive PC less than 90 days
			storeFrontHomePage.enterEmailAddress(terminatedPCEmailLessThan90Days);
			s_assert.assertTrue(storeFrontHomePage.verifyPopUpForExistingInactivePC90Days() , "Inactive pc less than 90 days User email id should not be acceptable");

			// assertion for Inactive PC more than 90 days
			storeFrontHomePage.enterEmailAddress(terminatedPCEmailMoreThan90Days);
			s_assert.assertFalse(storeFrontHomePage.verifyPopUpForExistingInactivePC90Days() , "Inactive pc more than 90 days User email id should be acceptable");

			// assertion for terminated consultant less than 180 days
			storeFrontHomePage.enterEmailAddress(terminatedConsultantEmailLessThan6month);
			s_assert.assertTrue(storeFrontHomePage.verifyPopUpForExistingActiveCCLessThan6Month() , "Inactive Consultant less than 180 days User email id should not be acceptable");

			// assertion for terminated consultant more than 180 days
			storeFrontHomePage.enterEmailAddress(terminatedConsultantEmailMoreThan6month);
			s_assert.assertFalse(storeFrontHomePage.verifyPopUpForExistingActiveCCLessThan6Month() , "Inactive Consultant more than 180 days User email id should be acceptable");

			// assertion for Active RC
			storeFrontHomePage.enterEmailAddress(rcUserEmailID);
			s_assert.assertTrue(storeFrontHomePage.verifyPopUpForExistingActiveRC() , "Existing Active RC User email id should not be acceptable");
			s_assert.assertAll();
		}else {
			logger.info("Canada specific TC not executed on AU");
		}
	}

	//QTest ID TC-469 : CORP> EXPRESS > Personal Resultss KIT > Personal Regimen: REDEFINE REGIMEN 
	@Test 
	public void testCorpExpressEnrollmentPersonalResultsKitRedefineRegimen_469q() throws InterruptedException{
		//-------------------------local variables---------------------------------------------------------
		String randomNum = CommonUtils.getCurrentTimeStamp();
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
		String kitName = TestConstants.KIT_NAME_PERSONAL;
		String emailID = null;
		String sponsorID = null;
		List<Map<String, Object>> sponsorIdList =  null;
		List<Map<String, Object>> randomConsultantList =  null;
		//-------------------------------------------------------------------------------------------------

		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITHOUT_PULSE_RFO,countryId),RFO_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserEmail");
		sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FROM_EMAIL_ADDRESS,emailID),RFO_DB);
		sponsorID = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsorID);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, consultantFirstName, consultantLastName+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(consultantFirstName);
		storeFrontHomePage.selectNewBillingCardExpirationDate();
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertAll(); 
	}

	//qTest ID-2599 CA Corp: CA consultant enrollment with AU sponsor with pws.
	@Test
	public void consultantExpressEnrollmentWithCrossSponsor_2599q() throws InterruptedException {
		if(driver.getCountry().equalsIgnoreCase("CA")) {
			//-------------------------local variables------------------------------------------------------------------------------------------------------------
			String randomNum = CommonUtils.getCurrentTimeStamp();
			String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
			String lastName = "lN"+randomNum;
			String countryId = null;
			String firstName=TestConstants.FIRST_NAME+randomNum;
			if(country.equalsIgnoreCase("ca"))
				countryId = "14";
			else
				countryId = "40";
			String CCS = null;
			String currentURL = null;
			List<Map<String, Object>> sponsorIdList =  null;
			//------------------------------------------------------------------------------------------------------------------------------------------------------------

			sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_SPONSOR_ID,countryId),RFO_DB);
			CCS = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
			storeFrontHomePage.searchCID(CCS);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			currentURL = storeFrontHomePage.getCurrentURL();
			s_assert.assertTrue(currentURL.contains("corprfo") , "User is redirecting to PWS after selecting to US sponsor");
			storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, addressLine1, TestConstants.YUKON_CITY, TestConstants.PROVINCE_YUKON, TestConstants.YUKON_POSTAL_CODE, phoneNumber);
			storeFrontHomePage.clickNextButton();
			storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);  
			storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
			storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
			storeFrontHomePage.clickNextButtonWithoutPopUp();
			storeFrontHomePage.selectAllTermsAndConditionsChkBox();
			storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
			storeFrontHomePage.clickOnConfirmAutomaticPayment();
			s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
			s_assert.assertAll();
		}else {
			logger.info("CA Specific TC");
		}
	}

	//qTest ID-839 CA Corp: CA consultant enrollment with US sponsor with pws.
	@Test
	public void ccsCAConsultantExpressEnrollmentWithUSSponsor_839q() throws InterruptedException {
		if(driver.getCountry().equalsIgnoreCase("CA")) {
			//-------------------------local variables------------------------------------------------------------------------------------------------------------
			String randomNum = CommonUtils.getCurrentTimeStamp();
			String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
			String lastName = "lN"+randomNum;
			String firstName=TestConstants.FIRST_NAME+randomNum;
			String countryId = "236";
			String CCS = null;
			String currentURL = null;
			List<Map<String, Object>> sponsorIdList =  null;
			//------------------------------------------------------------------------------------------------------------------------------------------------------------

			sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_SPONSOR_ID,countryId),RFO_DB);
			CCS = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
			storeFrontHomePage.searchCID(CCS);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			currentURL = storeFrontHomePage.getCurrentURL();
			s_assert.assertTrue(currentURL.contains("corprfo") , "User is redirecting to PWS after selecting to US sponsor");
			storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, firstName, TestConstants.LAST_NAME+randomNum, password, addressLine1, TestConstants.YUKON_CITY, TestConstants.PROVINCE_YUKON, TestConstants.YUKON_POSTAL_CODE, phoneNumber);
			storeFrontHomePage.clickNextButton();
			storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);  
			storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
			storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
			storeFrontHomePage.clickNextButtonWithoutPopUp();
			storeFrontHomePage.selectAllTermsAndConditionsChkBox();
			storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
			storeFrontHomePage.clickOnConfirmAutomaticPayment();
			s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
			s_assert.assertAll();
		}else {
			logger.info("CA specific TC not Executing on other country");
		}
	}

	//qTest ID- 757 Reactivated Soft-Terminated Consultant should be in the search for Sponsor list
	//qTest ID- 756 Soft-Terminated Consultant Cancel Reactivation
	//qTest ID- 2880 Soft-Terminated Consultant reactivates his account and perform Ad Hoc order
	//qTest ID- 765 Soft-Terminate Consultant is not available for Sponsor's search
	//qTest ID- 2879 Consultant Account Termination
	//qTest ID- 2878 Consultant Termination
	@Test 
	public void testTerminateConsultantAndReactivateAndPerformAdhocOrder_2880q_756q_765q_757q_2878q_2879q()  throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		List<Map<String, Object>> randomConsultantList =  null;
		List<Map<String, Object>> randomConsultantEmailList =  null;
		String consultantEmailID = null;
		String accountID = null;
		String lastName = "lN"+randomNum;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName =  TestConstants.REGIMEN_NAME_REDEFINE;
		while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
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
		// Get Account Number
		List<Map<String, Object>>sponsorIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FOR_PWS,accountID),RFO_DB);
		String CID = (String) getValueFromQueryResult(sponsorIdList, "AccountNumber");
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
		navigateToStoreFrontBaseURL();
		//verify that user is inactive
		storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		s_assert.assertTrue(storeFrontHomePage.isCurrentURLShowsError(),"Terminated User doesn't get Login failed");
		navigateToStoreFrontBaseURL();
		// search consultant as sponsor
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(CID);
		s_assert.assertTrue(storeFrontHomePage.verifyTerminatedConsultantIsNotInSponsorList(), "Terminated Consultant is present in sponsor's list");
		navigateToStoreFrontBaseURL();
		//Again enroll the consultant with same email id for cancel reactivation
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);  
		storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
		storeFrontHomePage.enterEmailAddress(consultantEmailID);
		s_assert.assertTrue(storeFrontHomePage.verifyInvalidSponsorPopupIsPresent(), "Invalid Sponsor popup is not present");
		storeFrontHomePage.clickOnCnacelEnrollment();
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		//storeFrontHomePage.searchCID(sponsor);
		//storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);		
		storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
		storeFrontHomePage.enterEmailAddress(consultantEmailID);
		s_assert.assertTrue(storeFrontHomePage.verifyInvalidSponsorPopupIsPresent(), "Invalid Sponsor popup is not present");
		storeFrontHomePage.clickOnEnrollUnderLastUpline();
		storeFrontHomePage.selectEnrollmentKitPage(kitName, regimenName);		
		storeFrontHomePage.chooseEnrollmentOption(enrollmentType);
		storeFrontHomePage.enterEmailAddress(consultantEmailID);
		storeFrontHomePage.enterPasswordForReactivationForConsultant();
		storeFrontHomePage.clickOnLoginToReactiveMyAccountForConsultant();
		logout();
		navigateToStoreFrontBaseURL();
		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(CID);
		s_assert.assertTrue(storeFrontHomePage.verifyTerminatedConsultantPresentInSponsorList(), "Terminated Consultant is not present in sponsor's list");
		navigateToStoreFrontBaseURL();
		storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		storeFrontConsultantPage.hoverOnShopLinkAndClickAllProductsLinks();
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontUpdateCartPage.clickOnCheckoutButton();
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnDefaultBillingProfileEdit();
		storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyOrderPlacedConfirmationMessage(), "Order has been not placed successfully");
		s_assert.assertAll();
	}

}