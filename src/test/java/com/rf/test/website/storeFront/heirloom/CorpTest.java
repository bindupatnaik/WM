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
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.heirloom.StoreFrontHeirloomHomePage;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class CorpTest extends RFHeirloomWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(CorpTest.class.getName());

	//QTest ID TC-505 Consultant Express Enrollment from Corp
	@Test(priority=3)//smoke
	public void testConsultantExpressEnrollment_505(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress =lastName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String enrollemntType = "Express";
		String sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickChargeMyCardAndEnrollMeWithOutConfirmAutoship();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForConsultant(), "Error message is not present for consultant for terms & conditions");
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Consultant erollment is NOT successful");
		s_assert.assertAll();
	}

	//QTest ID TC-503 Consultant Standard Enrollment
	@Test(priority=4)//smoke
	public void testConsultantStandardEnrollment_503(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(11, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress =lastName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String enrollemntType = "Standard";
		String sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");		
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickBillingInfoNextBtn();
		storeFrontHeirloomHomePage.clickYesSubscribeToPulseNow();
		storeFrontHeirloomHomePage.clickYesEnrollInCRPNow();
		storeFrontHeirloomHomePage.clickAutoShipOptionsNextBtn();
		storeFrontHeirloomHomePage.selectProductToAddToCart();
		storeFrontHeirloomHomePage.clickYourCRPOrderPopUpNextBtn();
		storeFrontHeirloomHomePage.clickChargeMyCardAndEnrollMeWithOutConfirmAutoship();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForConsultant(), "Error message is not present for consultant for terms & conditions");
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Congratulations Message not appeared");
		s_assert.assertAll();
	}

	//QTest ID TC-394 My account options as RC customer
	@Test(priority=5)
	public void testMyAccountOptionAsRCCustomer_394(){
		String rcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				rcEmailId=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
			}
			else{
				rcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
				storeFrontHeirloomHomePage.loginAsRCUser(rcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedInOnCorpSite(),"RC user is not logged in successfully");
		storeFrontHeirloomHomePage.clickMyAccountLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyLinkPresentUnderMyAccount("Order History"),"order history link is not present");
		storeFrontHeirloomHomePage.clickOrderManagementSublink("Order History");
		int orderNumber =storeFrontHeirloomHomePage.clickViewDetailsForOrderAndReturnOrderNumber();
		if(orderNumber!=0){
			s_assert.assertTrue(storeFrontHeirloomHomePage.isOrderDetailsPopupPresent(),"Order details popup not present after clicking view order details link.");
			storeFrontHeirloomHomePage.clickCloseOfOrderDetailsPopup();
		}
		s_assert.assertAll();
	}

	//Search for a Sponsor
	@Test(enabled=false)
	public void testSearchForASponser(){
		String sponsorID = null;
		String PWS = null;
		sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.clickFindAConsultantImageLink();
		storeFrontHeirloomHomePage.enterIDNumberAsSponsorForPCAndRC(sponsorID);
		storeFrontHeirloomHomePage.clickBeginSearchBtn();
		PWS = storeFrontHeirloomHomePage.clickAndReturnPWSFromFindConsultantPage();
		s_assert.assertTrue(driver.getCurrentUrl().contains(PWS.split("//")[1]), "User has not been navigated to pws site for searched consultant Expected: "+PWS.split("//")[1]+" While actual: "+driver.getCurrentUrl());
		s_assert.assertAll();
	}

	//QTest ID TC-2339 Crop > My account options as PC customer
	@Test(priority=6)
	public void testMyAccountOptionsAs_PC_Customer_2339(){
		String pcEmailID = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickMyAccountLink();
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.verifyLinkPresentUnderMyAccount("Order History"),"order history link is not present");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.verifyLinkPresentUnderMyAccount("Edit Order"),"Edit Order link is not present");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.verifyLinkPresentUnderMyAccount("Change my PC Perks Status"),"Change my PC Perks Status link is not present");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.verifyLinkPresentUnderMyAccount("PC Perks FAQs"),"PC Perks FAQs Status link is not present");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink("Order History");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.verifyOrderHistoryPresent("Order History"),"section order history not present");
		storeFrontHeirloomPCUserPage.navigateToBackPage();
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink("Edit Order");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isEditOrderPagePresent(),"Edit order page is not present");
		storeFrontHeirloomPCUserPage.navigateToBackPage();
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink("Change my PC Perks Status");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.verifyCurrentPage("PcPerksStatus"),"URL does not contain pcPerksStatus");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isPcPerksStatusLinkPresent(),"Delay or Cancel PC Perks link is not present");
		storeFrontHeirloomHomePage.clickBackToMyAccountBtn();
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink("PC Perks FAQs");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.verifyFaqPagePresent(),"This is not faq's page");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open Corp , go to my account , click edit order link and change billing profile 
	 * Assertions:
	 * changed billing profile should ne present 
	 */
	//QTest TC-2345:PC Perks Template - Billing Profile
	@Test(enabled=true)
	public void testPCPerksTemplateBillingAddressUpdateFromCorp_2345(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumber;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String pcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickMyAccountLink();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickChangeBillingInformationLinkUnderBillingTabOnPWS();
		storeFrontHeirloomHomePage.enterBillingInfoForPWS(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open Corp and initiate consultant express enrollment 
	 * Assertions:
	 * Consultant user enrolled successfully
	 */
	//QTest ID TC-2349 Consultant Express Enrollment
	@Test
	public void testConsultantExpressEnrollmentFromCorp_2349(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String enrollemntType = TestConstantsRFL.EXPRESS_ENROLLMENT;
		String sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		//storeFrontHeirloomHomePage.enterPWS(firstName+lastName+randomNum);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickChargeMyCardAndEnrollMeWithOutConfirmAutoship();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForConsultant(), "Error message is not present for consultant for terms & conditions");
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open Corp Site, Search for Sponsor id and Existing Consultant Prefix, Start Enrolling Consultant using Express Enrollment and enter existing user prefix and verify
	 * Assertions:
	 * prefix for com is unavailable
	 * prefix for biz is unavailable
	 */
	//*QTest ID TC-874 Enroll a consultant using existing CA Prefix (Cross Country Sponsor) on Corporate Site
	@Test(enabled=true) 
	public void testEnrollConsultantUsingExistingCAPrefixCrossCountryOnCorp_874(){
		String dbIP2 = driver.getDBIP2();
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(00, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String enrollemntType = TestConstantsRFL.EXPRESS_ENROLLMENT;
		String countryID ="40";
		List<Map<String, Object>> sitePrefixList=null;
		String sitePrefix=null;
		String sponsorID=null;
		sponsorID = getRandomSponsorFromDB();
		//Fetch cross country Email address from database.
		sitePrefixList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_ALREADY_EXISTING_SITE_PREFIX_RFO,countryID,sponsorID),RFO_DB,dbIP2);
		sitePrefix=String.valueOf(getValueFromQueryResult(sitePrefixList, "SitePrefix"));
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.enterUserPrefixInPrefixField(sitePrefix);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForBiz().contains("unavailable"),"Expected message is unavailable for .biz but actual on UI is: "+storeFrontHeirloomHomePage.getPrefixMessageForBiz());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForCom().contains("unavailable"),"Expected message is unavailable for .com but actual on UI is: "+storeFrontHeirloomHomePage.getPrefixMessageForCom());
		s_assert.assertAll();
	}			


	/***
	 * Test Summary:- Open corp url, initiate consultant enrollment, add billing profile with INVALID credit card and complete enrollment
	 * Assertions:
	 * Error message should be displayed for INVALID credit card after complete the enrollment
	 */	
	//QTest ID TC-1945 Payment error message - Enrollment
	@Test
	public void testPaymentErrorMessageFromCorp_1945(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String cardNumber = TestConstantsRFL.INVALID_CARD_NUMBER;
		String enrollemntType = "Express";
		String errorMessageFromUI = null;
		String errorMessage = TestConstantsRFL.ERROR_MSG_FOR_INVALID_CC;
		String sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		/*storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();*/
		errorMessageFromUI = storeFrontHeirloomHomePage.getErrorMessageForInvalidCreditCard();
		s_assert.assertTrue(errorMessageFromUI.contains(errorMessage), "Expected error message is "+errorMessage+"actual on UI is "+errorMessageFromUI);
		s_assert.assertAll();
	}	

	/**
	 * Test Summary: Open Corp, Fetch Inactive Consultant User from DB, Enroll with inactive consultant user and verify
	 * Assertions:
	 * User is Enrolled Successfully
	 * User is navigating to their own Biz PWS site
	 * Verify Account status id from DB
	 */
	//QTest ID TC-868 Registering the consultant using existing consultant's email id which terminated from Corp
	@Test (enabled=true)
	public void testRegisterUsingExistingCustomerEmailIdFromCorp_868(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String enrollemntType = TestConstantsRFL.EXPRESS_ENROLLMENT;
		List<Map<String, Object>> randomInactiveConsultantList =  null;
		List<Map<String, Object>> accountStatusIDList =  null;
		String consultantEmailID = null;
		String sponsorID=null;
		String statusID = null;
		sponsorID = getRandomSponsorFromDB();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getInActiveUserFromExcel("Consultant");	
			}
			else{
				randomInactiveConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_INACTIVE_CONSULTANT_EMAILID,RFL_DB);
				consultantEmailID = (String) getValueFromQueryResult(randomInactiveConsultantList, "EmailAddress");
			}
			//Checking user is inactive or not
			storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
			if(storeFrontHeirloomHomePage.isLoginFailed()==true)
				break;
			else
				continue;
		}
		storeFrontHeirloomHomePage.refreshThePage();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterEmailAddress(consultantEmailID);
		//		storeFrontHeirloomHomePage.clickSetUpAccountNextButton();
		//		s_assert.assertTrue(driver.getCurrentUrl().contains("SetupAccount"), "Set up account page is not present");
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, consultantEmailID, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickBillingInfoNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Enrollment is not successful using terminated consultant email id.");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("biz")," User is not redirecting to Own PWS after Enrollment");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open Corp, Enroll a consultant user with existing SSN Number and click on cancel enrollment repeat enrollment with same SSN and click on 'send emil to reset password' and verify
	 * Assertions:
	 * Existing user popup should present
	 * User should redirect to home page after clicking on Cancel Enrollment
	 * Email verification message should present on UI After clicking on click 'send to reset password'
	 */
	//QTest ID TC-869 Register the consultant using existing consultant's SSN from Corp
	@Test 
	public void testRegisterTheConsultantUsingExistingConsultantSSNFromCorp_869(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum2 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum3 = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String lastNameForReEnrollment = TestConstantsRFL.LAST_NAME+randomNum2;
		String emailAddressForReEnrollment = firstName+randomNum2+"@rodanandfields.com";
		String lastName2 = TestConstantsRFL.LAST_NAME+randomNum3;
		String emailAddress2 = firstName+randomNum3+"@rodanandfields.com";
		String kitName = TestConstantsRFL.CONSULTANT_RFX_EXPRESS_BUSINESS_KIT;
		String enrollemntType = "Express";
		String sponsorID=null;
		sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenForConsultant(regimen);
		storeFrontHeirloomHomePage.clickNextBtnAfterSelectRegimen();
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Enrollment not completed successfully");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		logout();
		storeFrontHeirloomHomePage.hoverOnBeAConsultantAndClickLinkOnEnrollMe();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenForConsultant(regimen);
		storeFrontHeirloomHomePage.clickNextBtnAfterSelectRegimen();
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastNameForReEnrollment, emailAddressForReEnrollment, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isExistingConsultantPopupPresent(), "Existing consultant popup is not present");
		storeFrontHeirloomHomePage.clickCancelEnrollmentBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginButtonPresent(),"User is not redirected to home page after clicked on cancel enrollment button");
		storeFrontHeirloomHomePage.hoverOnBeAConsultantAndClickLinkOnEnrollMe();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenForConsultant(regimen);
		storeFrontHeirloomHomePage.clickNextBtnAfterSelectRegimen();
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName2, emailAddress2, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isExistingConsultantPopupPresent(), "Existing consultant popup is not present");
		storeFrontHeirloomHomePage.clickSendEmailToResetMyPassword();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEmailVerificationTextPresent(), "Email verification message is not present");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open Corp, Enroll a consultant User with special character in Prefix and verify 
	 * Assertions: 
	 * Website prefix field should not accept special character
	 */
	// *QTest ID TC-872 Enroll a consultant using special characters in the prefix
	// field from Corp
	@Test(enabled = true)
	public void testEnrollConsultantUsingSpecialCharsInPrefixFieldFromCorp_872() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME + randomNum;
		String emailAddress = firstName + randomNum + "@rodanandfields.com";
		String PWSSpclChars = TestConstantsRFL.PWS_SPCLCHARS;
		String enrollemntType = TestConstantsRFL.EXPRESS_ENROLLMENT;
		String sponsorID = null;
		sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password,addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.enterSpecialCharacterInWebSitePrefixField(PWSSpclChars);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isValidationMessagePresentForPrefixField(),"WebSite Prefix Field accepts Special Character");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open Corp, Enroll a consultant User with Existing Prefix and verify
	 * Assertions: 
	 * Website prefix for Biz pws is unavailable
	 * Website prefix for COM pws is unavailable
	 */
	// *QTest ID TC-873 Enroll a consultant using existing Prefix for Corp
	@Test(enabled = true)
	public void testEnrollAConsultantUsingExistingPrefixFromCorp_873() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(00, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME + randomNum;
		String emailAddress = firstName + randomNum + "@rodanandfields.com";
		String enrollemntType = TestConstantsRFL.EXPRESS_ENROLLMENT;
		String url = null;
		String prefix = null;
		List<Map<String, Object>> randomConsultant = null;
		randomConsultant = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_EXISTING_CONSULTANT_SITE_URL, RFL_DB);
		url = (String) getValueFromQueryResult(randomConsultant, "URL");
		String sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		prefix = storeFrontHeirloomHomePage.getSplittedPrefixFromConsultantUrl(url);
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password,addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.enterUserPrefixInPrefixField(prefix);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForBiz().contains("unavailable"),"Expected message is unavailable for .biz but actual on UI is: "+ storeFrontHeirloomHomePage.getPrefixMessageForBiz());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForCom().contains("unavailable"),"Expected message is unavailable for .com but actual on UI is: "+ storeFrontHeirloomHomePage.getPrefixMessageForCom());
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open Corp Site, Start Enrolling consultant Using GUAM Address and verify
	 * Assertions:
	 * Shipping price on CRP Review and Confirmation page should be 30$
	 * Verify shipping price from DB
	 */
	//QTest ID TC-1001 Corp> Guam consultant express enrollment
	@Test(enabled=false) //* Incomplete DB Assertions
	public void testGuamConsultantExpressEnrollmentCorp_1001(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String enrollmentType = TestConstantsRFL.EXPRESS_ENROLLMENT;
		String shippingPrice="30.00";
		String shippingFromUI=null;
		String sponsorID = getRandomSponsorFromDB();

		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollmentType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, TestConstantsRFL.GUAM_ADDRESS_LINE1, TestConstantsRFL.GUAM_POSTAL_CODE, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		shippingFromUI=storeFrontHeirloomHomePage.getShippingPriceFromCRPReviewAndConfirmPage();
		s_assert.assertTrue(shippingPrice.contains(shippingFromUI),"Shipping price should be:"+shippingPrice+" But from UI is:"+shippingFromUI);
		storeFrontHeirloomHomePage.clickChargeMyCardAndEnrollMeWithOutConfirmAutoship();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForConsultant(), "Error message is not present for consultant for terms & conditions");
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Congratulations message is not appeared");
		s_assert.assertAll();
	}


	/**
	 * Test Summary: Open Corp Site, Start Enrolling consultant Using Puerto Rico Address and verify
	 * Assertions:
	 * Shipping price on CRP Review and Confirmation page should be 30$
	 * Verify shipping price from DB
	 */
	//QTest ID TC-1002 Corp: Puerto Rico consultant standard enrollment
	@Test(enabled=false) //* Incomplete DB Assertions
	public void testPuertoRicoConsultantStandardEnrollmentCorp_1002(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String shippingPrice="30.00";
		String shippingFromUI=null;
		String sponsorID = getRandomSponsorFromDB();

		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollmentType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, TestConstantsRFL.PUERTO_RICO_ADDRESS_LINE1, TestConstantsRFL.PUERTO_RICO_POSTAL_CODE, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredForGuamAndPuertoRicoAddress();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickBillingInfoNextBtn();
		storeFrontHeirloomHomePage.clickYesSubscribeToPulseNow();
		storeFrontHeirloomHomePage.clickYesEnrollInCRPNow();
		storeFrontHeirloomHomePage.clickAutoShipOptionsNextBtn();
		storeFrontHeirloomHomePage.selectProductToAddToCart();
		storeFrontHeirloomHomePage.clickYourCRPOrderPopUpNextBtn();
		shippingFromUI=storeFrontHeirloomHomePage.getShippingPriceFromCRPReviewAndConfirmPage();
		s_assert.assertTrue(shippingPrice.contains(shippingFromUI),"Shipping price should be:"+shippingPrice+" But from UI is:"+shippingFromUI);
		storeFrontHeirloomHomePage.clickChargeMyCardAndEnrollMeWithOutConfirmAutoship();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForConsultant(), "Error message is not present for consultant for terms & conditions");
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Congratulations Message not appeared");
		s_assert.assertAll();
	}

	//QTest ID TC-343 Products-Redefine-Regimen-Links should be redirecting to the appropriate page
	@Test
	public void testProductsRedefineRegimenLinksRedirectingToTheAppropriatePage_343(){
		String subLinkRegimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		String subLinkProducts = "Products";
		String subLinkResults = "Results";
		String subLinkFAQ = "FAQs";
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(subLinkRegimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkRegimen.toLowerCase()), "Expected regimen name is "+subLinkRegimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkProducts.toLowerCase()), "Expected sublink in url is "+subLinkProducts.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(subLinkRegimen, subLinkResults);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(subLinkRegimen),"user is not on results page");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(subLinkRegimen,subLinkFAQ);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is "+"CommonQuestions".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(subLinkRegimen),"user is not on FAQs page");
		s_assert.assertAll();
	}

	//QTest ID TC-346 Reverse Products-links should be working properly (products, testimonials, in the news, FAQ's, advice, glossary)
	@Test
	public void testReverseProductsLinksWorkingProperly_346(){
		String subLinkRegimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String subLinkProducts = "Products";
		String subLinkFAQ = "FAQs";
		String subLinkResults = "Results";
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(subLinkRegimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkRegimen.toLowerCase()), "Expected regimen name is "+subLinkRegimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkProducts.toLowerCase()), "Expected sublink in url is "+subLinkProducts.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(subLinkRegimen,subLinkFAQ);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is "+"CommonQuestions".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(subLinkRegimen),"user is not on FAQs page");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(subLinkRegimen, subLinkResults);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(subLinkRegimen),"user is not on results page");
		s_assert.assertAll();
	}

	//QTest ID TC-349 Unblemish Products-links should be working properly (products, testimonials, in the news, FAQ's, advice, glossary)
	@Test
	public void testUnblemishProductsLinksWorkingProperly_349(){
		String subLinkRegimen = TestConstantsRFL.REGIMEN_NAME_UNBLEMISH;
		String subLinkProducts = "Products";
		String subLinkFAQ = "FAQs";
		String subLinkResults = "Results";
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(subLinkRegimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkRegimen.toLowerCase()), "Expected regimen name is "+subLinkRegimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkProducts.toLowerCase()), "Expected sublink in url is "+subLinkProducts.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(subLinkRegimen,subLinkFAQ);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is "+"CommonQuestions".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(subLinkRegimen),"user is not on FAQs page");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(subLinkRegimen, subLinkResults);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(subLinkRegimen),"user is not on results page");
		s_assert.assertAll();
	}

	//QTest ID TC-350 Soothe Products-links should be working properly (products, testimonials, in the news, FAQ's, advice, glossary)
	@Test
	public void testSootheProductsLinksWorkingProperly_350(){
		String subLinkRegimen = TestConstantsRFL.REGIMEN_NAME_SOOTHE;
		String subLinkProducts = "Products";
		String subLinkFAQ = "FAQs";
		String subLinkResults = "Results";
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(subLinkRegimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkRegimen.toLowerCase()), "Expected regimen name is "+subLinkRegimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkProducts.toLowerCase()), "Expected sublink in url is "+subLinkProducts.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(subLinkRegimen,subLinkFAQ);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is "+"CommonQuestions".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(subLinkRegimen),"user is not on FAQs page");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(subLinkRegimen, subLinkResults);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(subLinkRegimen),"user is not on results page");
		s_assert.assertAll();
	}

	//product philosophy link should be working
	@Test(enabled=false)//needs updation
	public void testProductsPhilosophyLinkShouldWorkingProper(){
		storeFrontHeirloomHomePage.clickShopSkinCareBtn();
		//verify Product Philosophy link working?
		storeFrontHeirloomHomePage.clickProductPhilosophyLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateProductPhilosohyPageDisplayed(),"Product Philosophy page is not displayed!!");
		s_assert.assertAll();
	}

	//Digital Product Catalog- Links should be displayed the information properly
	@Test(enabled=false)//needs updation
	public void testDigitalProductCatalogLinkShouldDisplayInformationProperly(){
		storeFrontHeirloomHomePage.clickShopSkinCareBtn();
		//verify Digital Product Catalog- Link should be displayed the information properly?
		storeFrontHeirloomHomePage.clickDigitalProductCatalogLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateRealResultsLink(),"Real Results link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateSolutionToolLink(),"Solution Tool link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validatePCPerksLink(),"PC Perks link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateDigitalProductCatalogLink(),"Digital Product link didn't work");
		s_assert.assertAll();
	}

	//Company Links Should be Present
	@Test(enabled=false)//needs updation
	public void testCompanyLinksShouldBePresent(){
		storeFrontHeirloomHomePage.clickAboutRFBtn();
		//verify company links is present?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExecutiveTeamLinkPresent(),"Executive Team Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateContactUsLinkPresent(),"Contact Us Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateWhoWeAreLinkPresent(),"WhoWeAre/Our History Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validatePressRoomLinkPresent(),"Press Room Link is not present");
		s_assert.assertAll();
	}

	//QTest ID TC-368 Footer- Privacy Policy link should be redirecting to the appropriate page
	@Test
	public void testFooterPrivacyPolicyLinkShouldRedirectionToAppropriatePage_368(){
		storeFrontHeirloomHomePage.clickPrivacyPolicyLink();
		/*s_assert.assertTrue(storeFrontHeirloomHomePage.isPrivacyPolicyPagePresent(), "Privacy policy page is not present after clicked on privacy policy link");*/
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("privacy"), "Expected url having privacy but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	//QTest ID TC-367 Footer-Terms & Conditions link should redirecting to the appropriate page
	@Test
	public void testFooterTermsAndConditionLinkShouldRedirectionToAppropriatePage_367(){
		storeFrontHeirloomHomePage.clickTermsAndConditionsLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("terms"), "Expected url having terms but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	//QTest ID TC-369 Satisfaction Guarantee-link should be redirecting properly 
	@Test
	public void testSatisfactionGuaranteeLinkShouldBeRedirectionProperly_369(){
		storeFrontHeirloomHomePage.clickSatisfactionGuaranteeLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSatisfactionGuaranteePagePresent(), "Satisfaction guarantee page is not present after clicked on privacy policy link");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("guarantee"), "Expected url having guarantee but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	//QTest ID TC-353 Real results products- links should be redirecting to the appropriate page
	//QTest ID TC-352 Enhancements Products-links should be working properly (Real results, PC perks, solution tool, digital product catalog)
	//QTest ID TC-351 Essentials Products-links should be working properly
	@Test
	public void testProductsLinkShouldBeRedirectionToAppropriatePage_353_351_352(){
		//For REDEFINE
		String regimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(regimen.toLowerCase()), "Expected regimen name is "+regimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresentAfterClickedOnRegimen(regimen), "regimen name i.e.: "+regimen+" not present");
		//For REVERSE
		regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(regimen.toLowerCase()), "Expected regimen name is "+regimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresentAfterClickedOnRegimen(regimen), "regimen name i.e.: "+regimen+" not present");
		//For UNBLEMISH

		regimen = TestConstantsRFL.REGIMEN_NAME_UNBLEMISH;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(regimen.toLowerCase()), "Expected regimen name is "+regimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresentAfterClickedOnRegimen(regimen), "regimen name i.e.: "+regimen+" not present");
		//For SOOTHE

		regimen = TestConstantsRFL.REGIMEN_NAME_SOOTHE;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(regimen.toLowerCase()), "Expected regimen name is "+regimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresentAfterClickedOnRegimen(regimen), "regimen name i.e.: "+regimen+" not present");
		//For PROMOTIONS

		regimen = TestConstantsRFL.REGIMEN_NAME_PROMOTIONS;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(TestConstantsRFL.REGIMEN_NAME_FEATURED);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(regimen.toLowerCase()), "Expected regimen name is "+regimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresentAfterClickedOnRegimen(regimen), "regimen name i.e.: "+regimen+" not present");
		//For ENHANCEMENTS

		regimen = TestConstantsRFL.REGIMEN_NAME_ENHANCEMENTS;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(regimen.toLowerCase()), "Expected regimen name is "+regimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresentAfterClickedOnRegimen(regimen), "regimen name i.e.: "+regimen+" not present");
		//For ESSENTIALS

		regimen = TestConstantsRFL.REGIMEN_NAME_ESSENTIALS;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(regimen.toLowerCase()), "Expected regimen name is "+regimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresentAfterClickedOnRegimen(regimen), "regimen name i.e.: "+regimen+" not present");
		s_assert.assertAll();
	}

	//QTest ID TC-372 Log in as an existen consultant
	@Test
	public void testLoginAsExistingConsultant_372(){
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		s_assert.assertAll();
	}

	//QTest ID TC-373 Log in as valid PC customer
	@Test
	public void testLoginAsExistingPC_373(){
		String pcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"PC user is not logged in successfully");
		s_assert.assertAll();
	}

	//QTest ID TC-374 Log in with a valid RC customer
	@Test
	public void testLoginAsExistingRC_374(){
		String rcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				rcEmailId=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
			}
			else{
				rcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
				storeFrontHeirloomHomePage.loginAsRCUser(rcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"RC user is not logged in successfully");
		s_assert.assertAll();
	}

	//* QTest ID TC-354 Solution Tool-Find a Rodan + Fields consultant should be working properly
	@Test(enabled=false)//fix it
	public void testSolutionToolFindARodanFieldsConsultantShouldBeWorkingProperly_354(){
		String sponsorID = TestConstantsRFL.CID_CONSULTANT;
		String fetchedPWS = null;
		storeFrontHeirloomHomePage.clickSolutionToolHomePage();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySolutionToolPage(),"Solution tool page is displayed");
		storeFrontHeirloomHomePage.clickConnectWithAConsultant();
		storeFrontHeirloomHomePage.enterIDNumberAsSponsorForPCAndRC(sponsorID);
		storeFrontHeirloomHomePage.clickBeginSearchBtn();
		fetchedPWS = storeFrontHeirloomHomePage.getPWSFromFindConsultantPage();
		storeFrontHeirloomHomePage.selectSponsorRadioBtnOnFindConsultantPage();
		storeFrontHeirloomHomePage.clickSelectAndContinueBtnForPCAndRC();
		s_assert.assertFalse(storeFrontHeirloomHomePage.getCurrentURL().contains(fetchedPWS),"Expected pws is: "+fetchedPWS +"While actual on UI: "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertAll(); 
	}

	//QTest ID TC-342 Redefine-Sub links should be displayed properly(regimen, products, results, testimonials, in the news, FAQs, Advice, Glossary)
	@Test
	public void testVerifyRedefineRegimenLinksDisplayedProperly_342(){
		String subSectionResults = "Results";
		String subSectionFAQ = "FAQs";
		storeFrontHeirloomHomePage.mouseHoverShopSkinCare();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyRedefineRegimenSections(subSectionResults),"Redefine regimen section Results is not displayed");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyRedefineRegimenSections(subSectionFAQ),"Redefine regimen section FAQ is not displayed");
		s_assert.assertAll();
	}

	//QTest ID TC-375 log out with a valid user
	@Test
	public void testLogoutWithAValidUser_375(){
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		logout();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isForgotPasswordLinkPresent(),"User is not logout successfully");
		s_assert.assertAll();
	}

	//Corporate_BUsinessSystem_ Direct Selling
	@Test(enabled=false)
	public void testCorporateBusinessSystemDirectSelling(){
		storeFrontHeirloomHomePage.clickAboutRFBtn();
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Who We Are");
		storeFrontHeirloomHomePage.clickClickhereLinkToLearnDirectSelling();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isClickHereLinkRedirectinToAppropriatePage("directselling.org"), "Click here link of business system is not redirecting to http://directselling.org/ page");
		s_assert.assertAll();
	}

	//Corporate_BUsinessSystem_GettingStarted 
	@Test(enabled=false)//needs updation
	public void testCorporateBusinessSystemGettingStarted(){
		String gettingStarted = "Getting Started";
		String whyRF = "Why R+F";
		String redefineYourFuture = "Redefine Your Future";
		String enrollNow = "Enroll Now";
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(00, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String addressLine1 =  TestConstantsRFL.ADDRESS_LINE1;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String expMonth = TestConstantsRFL.EXP_MONTH;
		String expYear = TestConstantsRFL.EXP_YEAR;
		String CID = TestConstantsRFL.CID_CONSULTANT;
		String kitName = "Big Business Launch Kit";
		String regimen = "Redefine";
		String enrollemntType = "Express";
		String phnNumber1 = "415";
		String phnNumber2 = "780";
		String phnNumber3 = "9099";

		storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(whyRF);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSublinkOfBusinessSystemPresent(gettingStarted), "Getting Started link is not present under Why R+F for business system");
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(gettingStarted);
		storeFrontHeirloomHomePage.clickClickhereLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isClickHereLinkRedirectinToAppropriatePage("PP_11th_Edition.pdf"), "Click here link of business system is not redirecting to PP_11th_Edition.pdf page");
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(redefineYourFuture);
		storeFrontHeirloomHomePage.clickDetailsLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isClickHereLinkRedirectinToAppropriatePage("REDEFINE-Your-Future-with-BBL-020813.pdf"), "Details link of redefine your future is not redirecting to REDEFINE-Your-Future-with-BBL-020813.pdf");
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(enrollNow);
		storeFrontHeirloomHomePage.enterCID(CID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Congratulations Message not appeared");
		s_assert.assertAll();
	}

	//Corporate_BUsinessSystem_ SuccessStories
	@Test(enabled=false)//No more visible on UI
	public void testCorporateBusinessSystemSuccessStories(){
		String meetOurCommunity = "Meet Our Community";
		String rfxCircleAG = "RFXcircleAG";
		String rfxCircleHZ = "RFXcircleHZ";
		String eliteVAL = "EliteVAL";
		String eliteVMZ = "EliteVMZ";
		String carAchieversAC= "CarAchieversAC";
		String carAchieversDE = "CarAchieversDE";
		String carAchieversFG = "CarAchieversFG";
		String carAchieversHL = "CarAchieversHL";
		String carAchieversMR = "CarAchieversMR";
		String carAchieversSZ = "CarAchieversSZ";

		//storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		//storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(meetOurCommunity);
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink(meetOurCommunity);
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(rfxCircleAG);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("RFXcircleAG"), "Expected url contains is: RFxcircleAG but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(rfxCircleHZ);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("RFXcircleHZ"), "Expected url contains is: RFxcircleHZ but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(eliteVAL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("EliteVAL"), "Expected url contains is: EliteVAL but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(eliteVMZ);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("EliteVMZ"), "Expected url contains is: EliteVMZ but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(carAchieversAC);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("CarAchieversAC"), "Expected url contains is: CarAchieversAC but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(carAchieversDE);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("CarAchieversDE"), "Expected url contains is: CarAchieversDE but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(carAchieversFG);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("CarAchieversFG"), "Expected url contains is: CarAchieversFG but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(carAchieversHL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("CarAchieversHL"), "Expected url contains is: CarAchieversHL but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(carAchieversMR);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("CarAchieversMR"), "Expected url contains is: CarAchieversMR but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(carAchieversSZ);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("CarAchieversSZ"), "Expected url contains is: CarAchieversSZ but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertAll();
	}


	//QTest ID TC-380 The Compensation Plan section Programs and Incentives is displayed
	@Test//(enabled=false)//needs updation
	public void testCompensationPlanProgramsAndIncentivesIsDisplayed_380(){
		String firstSubSectionUnderBusinessSystem = "Why R+F";
		String secondSubSectionUnderBusinessSystem = "Programs and Incentives";
		//String thirdSubSectionUnderBusinessSystem = "Income Illustrator";
		String fourthSubSectionUnderBusinessSystem = "Events";
		String fifthSubSectionUnderBusinessSystem = "Meet Our Community";
		String sixthSubSectionUnderBusinessSystem = "Enroll Now";

		//String firstSubSectionUnderProgramsAndIncentives = "Compensation Plan";
		String secondSubSectionUnderProgramsAndIncentives = "Programs & Incentives";
		String thirdSubSectionUnderProgramsAndIncentives = "Enroll Now";

		//storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultant();
		//s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Business".toLowerCase()), "URL does not contain Business Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(firstSubSectionUnderBusinessSystem),""+firstSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(secondSubSectionUnderBusinessSystem),""+secondSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		//s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(thirdSubSectionUnderBusinessSystem),""+thirdSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(fourthSubSectionUnderBusinessSystem),""+fourthSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(fifthSubSectionUnderBusinessSystem),""+fifthSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(sixthSubSectionUnderBusinessSystem),""+sixthSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		storeFrontHeirloomHomePage.clickSubSectionUnderBusinessSystem(secondSubSectionUnderBusinessSystem);
		//s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtProgramsAndIncentives(firstSubSectionUnderProgramsAndIncentives),""+firstSubSectionUnderProgramsAndIncentives+" subTitle not present under Programs and Incentives page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtProgramsAndIncentives(secondSubSectionUnderProgramsAndIncentives),""+secondSubSectionUnderProgramsAndIncentives+" subTitle not present under Programs and Incentives page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtProgramsAndIncentives(thirdSubSectionUnderProgramsAndIncentives),""+thirdSubSectionUnderProgramsAndIncentives+" subTitle not present under Programs and Incentives page");
		s_assert.assertAll();
	}

	//The Compensation Plan section Enroll Now is displayed
	@Test(enabled=false)//needs updation
	public void testCompensationPlanSectionEnrollNowIsDisplayed(){
		String firstSubSectionUnderBusinessSystem = "Why R+F";
		String secondSubSectionUnderBusinessSystem = "Programs and Incentives";
		String thirdSubSectionUnderBusinessSystem = "Income Illustrator";
		String fourthSubSectionUnderBusinessSystem = "Events";
		String fifthSubSectionUnderBusinessSystem = "Meet Our Community";
		String sixthSubSectionUnderBusinessSystem = "Enroll Now";
		String firstSubSectionUnderProgramsAndIncentives = "Compensation Plan";
		String secondSubSectionUnderProgramsAndIncentives = "Programs and Incentives";
		String thirdSubSectionUnderProgramsAndIncentives = "Enroll Now";
		storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Business".toLowerCase()), "URL does not contain Business Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(firstSubSectionUnderBusinessSystem),""+firstSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(secondSubSectionUnderBusinessSystem),""+secondSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(thirdSubSectionUnderBusinessSystem),""+thirdSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(fourthSubSectionUnderBusinessSystem),""+thirdSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(fifthSubSectionUnderBusinessSystem),""+fifthSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtBusinessSystemPage(sixthSubSectionUnderBusinessSystem),""+sixthSubSectionUnderBusinessSystem+" subTitle not present under business system page");
		storeFrontHeirloomHomePage.clickSubSectionUnderBusinessSystem(secondSubSectionUnderBusinessSystem);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtProgramsAndIncentives(firstSubSectionUnderProgramsAndIncentives),""+firstSubSectionUnderProgramsAndIncentives+" subTitle not present under Programs and Incentives page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtProgramsAndIncentives(secondSubSectionUnderProgramsAndIncentives),""+secondSubSectionUnderProgramsAndIncentives+" subTitle not present under Programs and Incentives page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifySubSectionPresentAtProgramsAndIncentives(thirdSubSectionUnderProgramsAndIncentives),""+thirdSubSectionUnderProgramsAndIncentives+" subTitle not present under Programs and Incentives page");
		storeFrontHeirloomHomePage.clickSubSectionUnderBusinessSystem(thirdSubSectionUnderProgramsAndIncentives);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains("NewEnrollment/SearchSponsor"),"current url is not a valid and Expected url");
		s_assert.assertAll();
	}

	//QTest ID TC-371 Contact us-link should be redirecting properly
	@Test
	public void testContactUsLinkShouldBeRedirectingProperly_371(){
		storeFrontHeirloomHomePage.clickContactUsAtFooter();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Contact".toLowerCase()), "URL does not contain Contact Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifylinkIsRedirectedToContactUsPage(),"link is not redirected to contact us page");
		s_assert.assertAll();
	}

	//Essentials Products-links should be working properly (Real results, PC perks, solution tool, digital product catalog)
	@Test(enabled=false)//needs updation
	public void testVerifyEssentialRegimenProductLinksWorkingProperly(){
		String regimen = TestConstantsRFL.REGIMEN_NAME_ESSENTIALS;
		String subSectionLinkRealResults = "Real Results";
		String subSectionLinkPCPerks = "PC PERKS";
		String subSectionSolutionTool = "Solution Tool";
		String subSectionDigitalProductCatalogue = "Digital Product Catalog";
		storeFrontHeirloomHomePage.clickShopSkinCareBtn();
		storeFrontHeirloomHomePage.selectRegimen(regimen);

		//Verify visibility of Essentials regimen Sections.
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyEssentialsRegimenSections(subSectionLinkRealResults),"Essential regimen section real results is not displayed");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyEssentialsRegimenSections(subSectionLinkPCPerks),"Essential regimen section PC Perks is not displayed");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyEssentialsRegimenSections(subSectionSolutionTool),"Essential regimen section Solution tool is not displayed");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyEssentialsRegimenSections(subSectionDigitalProductCatalogue),"Essential regimen section Digital product catalogue is not displayed");

		//Verify Visibility of Essentials Regimen Real Results subSections.	
		storeFrontHeirloomHomePage.clickSubSectionUnderRegimen(subSectionLinkRealResults);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Results".toLowerCase()), "Expected regimen name is "+"Results".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToRealResultsPage(),"user is not on Real results page");
		storeFrontHeirloomHomePage.navigateToBackPage();

		//Verify Visibility of Essentials Regimen PC Perks subSections.		
		storeFrontHeirloomHomePage.clickSubSectionUnderRegimen(subSectionLinkPCPerks);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("PC".toLowerCase()), "Expected regimen name is "+"PCPerks".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());		
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToPCPerksPage(),"user is not on PC Perks page");
		storeFrontHeirloomHomePage.navigateToBackPage();

		//Verify Visibility of Essentials Regimen Solution tool subSections.	
		storeFrontHeirloomHomePage.clickSubSectionUnderRegimen(subSectionSolutionTool);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Solution".toLowerCase()), "Expected regimen name is "+"SolutionTool".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToSolutionToolPage(),"user is not on Solution tool page");
		storeFrontHeirloomHomePage.navigateToBackPage();

		//Verify Visibility of Essentials Regimen Digital product catalogue subSections.	
		storeFrontHeirloomHomePage.clickSubSectionUnderRegimen(subSectionDigitalProductCatalogue);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Digimag".toLowerCase()), "Expected regimen name is "+"Digimag".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToDigitalProductCataloguePage(),"user is not on Digital product catalogue page");
		storeFrontHeirloomHomePage.navigateToBackPage();
		s_assert.assertAll();
	}

	//Enhancements Products-links should be working properly (Real results, PC perks, solution tool, digital product catalog)
	@Test(enabled=false)//needs updation
	public void testVerifyEnhancementsRegimenProductLinksWorkingProperly(){
		String regimen = TestConstantsRFL.REGIMEN_NAME_ENHANCEMENTS;
		String subSectionLinkRealResults = "Real Results";
		String subSectionLinkPCPerks = "PC PERKS";
		String subSectionSolutionTool = "Solution Tool";
		String subSectionDigitalProductCatalogue = "Digital Product Catalog";


		storeFrontHeirloomHomePage.clickShopSkinCareBtn();
		storeFrontHeirloomHomePage.selectRegimen(regimen);

		//Verify visibility of Essentials regimen Sections.
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyEnhancementsRegimenSections(subSectionLinkRealResults),"Enhancements regimen section real results is not displayed");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyEnhancementsRegimenSections(subSectionLinkPCPerks),"Enhancements regimen section PC Perks is not displayed");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyEnhancementsRegimenSections(subSectionSolutionTool),"Enhancements regimen section Solution tool is not displayed");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyEnhancementsRegimenSections(subSectionDigitalProductCatalogue),"Enhancements regimen section Digital product catalogue is not displayed");

		//Verify Visibility of Essentials Regimen Real Results subSections.	
		storeFrontHeirloomHomePage.clickSubSectionUnderRegimen(subSectionLinkRealResults);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Results".toLowerCase()), "Expected regimen name is "+"Results".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToRealResultsPage(),"user is not on Real results page");
		storeFrontHeirloomHomePage.navigateToBackPage();

		//Verify Visibility of Essentials Regimen PC Perks subSections.		
		storeFrontHeirloomHomePage.clickSubSectionUnderRegimen(subSectionLinkPCPerks);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("PC".toLowerCase()), "Expected regimen name is "+"PCPerks".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());		
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToPCPerksPage(),"user is not on PC Perks page");
		storeFrontHeirloomHomePage.navigateToBackPage();

		//Verify Visibility of Essentials Regimen Solution tool subSections.	
		storeFrontHeirloomHomePage.clickSubSectionUnderRegimen(subSectionSolutionTool);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Solution".toLowerCase()), "Expected regimen name is "+"SolutionTool".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToSolutionToolPage(),"user is not on Solution tool page");
		storeFrontHeirloomHomePage.navigateToBackPage();

		//Verify Visibility of Essentials Regimen Digital product catalogue subSections.	
		storeFrontHeirloomHomePage.clickSubSectionUnderRegimen(subSectionDigitalProductCatalogue);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Digimag".toLowerCase()), "Expected regimen name is "+"Digimag".toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToDigitalProductCataloguePage(),"user is not on Digital product catalogue page");
		storeFrontHeirloomHomePage.navigateToBackPage();
		s_assert.assertAll();
	}

	//Corporate_ FindAConsultant
	@Test(enabled=false)
	public void testCorporateFindAConsultant() throws InterruptedException{
		String expectedURL = "LocatePWS.aspx?fromHome=1";
		storeFrontHeirloomHomePage = new StoreFrontHeirloomHomePage(driver);
		storeFrontHeirloomHomePage.clickConnectWithAConsultant();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertAll();
	}

	//Corporate_BUsinessSystem_CompensationPlan
	@Test(enabled=false)//needs updation
	public void testCorporateBusinessCompensationPlan(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(00, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String CID = TestConstantsRFL.CID_CONSULTANT;
		String enrollemntType = "Express";
		String secondSubSectionUnderBusinessSystem = "Programs and Incentives";
		String firstSubSectionUnderProgramsAndIncentives = "Compensation Plan";
		String secondSubSectionUnderProgramsAndIncentives = "Programs and Incentives";

		storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		storeFrontHeirloomHomePage.clickSubSectionUnderBusinessSystem(secondSubSectionUnderBusinessSystem);
		storeFrontHeirloomHomePage.clickSubSectionUnderBusinessSystem(firstSubSectionUnderProgramsAndIncentives);
		//Verify Compensation page
		s_assert.assertTrue(storeFrontHeirloomHomePage.getSelectedHighlightLinkName().contains("Compensation Plan"), "Expected selected and highlight link name is: "+"Compensation Plan"+" Actual on UI: "+storeFrontHeirloomHomePage.getSelectedHighlightLinkName());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Compensation".toLowerCase()), "Expected url should contain: "+"Compensation"+" Actual on UI: "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.clickToReadIncomeDisclosure();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("RF-Income-Disclosure-Statement.pdf"),"current url is not a valid and Expected url");
		//Click program and incentives link
		storeFrontHeirloomHomePage.clickSubSectionUnderBusinessSystem(secondSubSectionUnderProgramsAndIncentives);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("ProgramsIncentives".toLowerCase()), "Expected url should contain: "+"ProgramsIncentives"+" Actual on UI: "+storeFrontHeirloomHomePage.getCurrentURL());
		//click details link under fast start program section
		storeFrontHeirloomHomePage.clickDetailsLinkUnderProgramsIncentivePage("Fast Start Program");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("Fast_Start_Flyer_2013_Secured.pdf"),"current url is not as expected under detail page of fast start program section page");
		//click details link under Road to RFx car incentive program section
		storeFrontHeirloomHomePage.clickDetailsLinkUnderProgramsIncentivePage("Road to RF");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("Road-to-RFx-Car-Incentive-Program-Flyer-09.24.11.pdf"),"current url is not as expected under detail page of road to rfx car incentive program section page");
		//click details link under Elite V  program section
		storeFrontHeirloomHomePage.clickDetailsLinkUnderProgramsIncentivePage("Elite V Program");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("Elite_V_Flyer_Hawaii_2016.pdf"),"current url is not as expected under detail page of Elite V  program section page");
		//click details link under RFx Circle program section
		storeFrontHeirloomHomePage.clickDetailsLinkUnderProgramsIncentivePage("RF");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("2016_RFx_Circle_TCs.pdf"),"current url is not as expected under detail page of RFx Circle  program section page");
		storeFrontHeirloomHomePage.navigateToBackPage();
		storeFrontHeirloomHomePage.navigateToBackPage();
		//complete enroll flow.
		storeFrontHeirloomHomePage.clickEnrollNowBtnOnBusinessPage();
		storeFrontHeirloomHomePage.enterCID(CID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"");
		s_assert.assertAll();
	}

	//Corporate_BUsinessSystem_ IncomeIllustrator  
	@Test(enabled=false)//needs updation
	public void corporateBusinessSystemIncomeIllustrator(){
		String incomeIllustrator = "Income Illustrator";
		storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		storeFrontHeirloomHomePage.clickSublinkOfBusinessSystem(incomeIllustrator);
		storeFrontHeirloomHomePage.clickStartNowBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isStartNowBtnRedirectinToAppropriatePage("IncomeIllustrator/index.html"), "start now btn of income illustrator is not redirecting to IncomeIllustrator/index.html");
		s_assert.assertAll();

	}

	//The Getting Started section Redefine Your Future is displayed
	@Test(enabled=false)
	public void testGettingStartedSectionRedefineYourFutureIsDisplayed(){
		storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		//verify the sub-menu title under the title business system?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateEnrollNowLinkPresent(),"Enroll Now Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateMeetOurCommunityLinkPresent(),"Meet Our Community is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateEventsLinkPresent(),"Events Link is not present");
		//s_assert.assertTrue(storeFrontHeirloomHomePage.validateIncomeIllustratorLinkPresent(),"Income Illustrator Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateProgramsAndIncentivesLinkPresent(),"ProgramIncentives Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateWhyRFLinkPresent(),"Why RF Link is not present");
		//Navigate to Redefine Your Future section-
		storeFrontHeirloomHomePage.clickWhyRFLinkUnderBusinessSystem();
		storeFrontHeirloomHomePage.clickRedefineYourFutureLinkUnderWhyRF();
		//verify url for 'Redefine Your Future'
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateRedefineYourFuturePageDisplayed(),"'Redefine Your Future' Page Is not displayed");
		s_assert.assertAll();
	}

	//The Getting Started Section Business Kit is displayed
	@Test(enabled=false)
	public void testGettingStartedSectionBusinessKitDisplayed(){
		storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		//verify the sub-menu title under the title business system?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateEnrollNowLinkPresent(),"Enroll Now Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateMeetOurCommunityLinkPresent(),"Meet Our Community is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateEventsLinkPresent(),"Events Link is not present");
		//s_assert.assertTrue(storeFrontHeirloomHomePage.validateIncomeIllustratorLinkPresent(),"Income Illustrator Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateProgramsAndIncentivesLinkPresent(),"ProgramIncentives Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateWhyRFLinkPresent(),"Why RF Link is not present");
		//Navigate to business kit section-
		storeFrontHeirloomHomePage.clickWhyRFLinkUnderBusinessSystem();
		storeFrontHeirloomHomePage.clickBusinessKitsUnderWhyRF();
		//verify that the 'Business Kits' Section displays the information?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateBusinessKitSectionIsDisplayed(),"Business Kit Section is not displayed with the Information");
		s_assert.assertAll();
	}

	//Company Contact us Link should be displayed properly
	@Test(enabled=false)
	public void testCompanyContactUsLink(){
		storeFrontHeirloomHomePage.clickAboutRFBtn();
		//verify company Contact Us Link?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateCompanyContactUsLink(),"Company Contact Us link didn't work");
		s_assert.assertAll();
	}

	//Company Press Room Link should be displayed properly
	@Test(enabled=false)
	public void testCompanyPressRoomLink(){
		storeFrontHeirloomHomePage.clickAboutRFBtn();
		//verify company Press Room Link?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateCompanyPressRoomLink(),"Company Press Room link didn't work");
		s_assert.assertAll();
	}

	//Company Careers Link should be displayed properly
	@Test(enabled=false)
	public void testCompanyCareersLink(){
		storeFrontHeirloomHomePage.clickAboutRFBtn();
		//verify company careers Link?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateCompanyCareersLink(),"Company careers link didn't work");
		s_assert.assertAll();
	}

	//Company Our Story Link should be displayed properly
	@Test(enabled=false)
	public void testCompanyOurStoryLink(){
		storeFrontHeirloomHomePage.clickAboutRFBtn();
		//verify Our Story Link?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateWhoWeAreLink(),"Who We Are link didn't work");
		s_assert.assertAll();
	}

	//Company Execute Link should be displayed properly
	@Test(enabled=false)
	public void testCompanyExecuteTeamLink(){
		storeFrontHeirloomHomePage.clickAboutRFBtn();
		//verify Execute Team Link?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExecuteTeamLink(),"Execute Team link didn't work");
		s_assert.assertAll();
	}

	//The Getting Started section Enroll Now is displayed
	@Test(enabled=false)//needs updation
	public void testGettingStartedSectionEnrollNowIsDisplayed(){
		storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		//verify the sub-menu title under the title business system?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateEnrollNowLinkPresent(),"Enroll Now Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateMeetOurCommunityLinkPresent(),"Meet Our Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateEventsLinkPresent(),"Events Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateIncomeIllustratorLinkPresent(),"Income Illustrator Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateProgramsAndIncentivesLinkPresent(),"ProgramIncentives Link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateWhyRFLinkPresent(),"Why RF Link is not present");
		//Navigate to Redefine Your Future section-
		storeFrontHeirloomHomePage.clickWhyRFLinkUnderBusinessSystem();
		storeFrontHeirloomHomePage.clickEnrollNowLinkUnderWhyRF();
		//verify url for 'Enroll Now'
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateSearchSponsorPageDisplayed(),"'Search Sponsor' Page Is not displayed");
		s_assert.assertAll();
	}

	//QTest ID TC-370 Disclaimer-link should be redirecting properly
	@Test
	public void testDisclaimerLinkShouldBeRedirectedProperly_370(){
		//verify Disclaimer in footer should redirect properly?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateDisclaimerLinkInFooter(),"'Disclaimer Link' doesn't redirect to disclaimer page");
		s_assert.assertAll();
	}

	//Company-PFC Foundation link should be redirecting properly 
	@Test(enabled=false)//needs updation
	public void testCompanyPFCFoundationLinkShouldRedirectProperly(){
		storeFrontHeirloomHomePage.clickAboutRFBtn();
		//verify company PFC Foundation link?
		storeFrontHeirloomHomePage.clickGivingBackLinkUnderAboutRF();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateCompanyPFCFoundationPageDisplayed(),"'Company PFC Foundation' page is not displayed");
		s_assert.assertAll();
	}

	//QTest ID TC-392 Corporate_ R+FInTheNews
	@Test(enabled=true)
	public void testCorporateRFInTheNews_392(){
		String expectedURL = "Company/PR";
		//storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Press Room");
		storeFrontHeirloomHomePage.clickCompanyPressRoomLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertAll();
	}

	//TC-696 Shopping bag icon appear on all sites regardless product added or not(corp,com,pws)
	@Test(enabled=false)///not working on jenkins
	public void ShoppingBagIconAppearOnAllSitesRegardlessProductAddedOrNot_696() throws InterruptedException {
		String regimen=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		//corp site
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyShoppingBagAppears(), "shopping bag doesnot appear");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("1"), "product quantity not updated on corp site");
		logger.info("1 product is successfully added to the cart");
		//update qty to 3 of the first product
		storeFrontHeirloomHomePage.addQuantityOfProduct("3"); 
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("3"), "product quantity not increased on corp site");
		storeFrontHeirloomHomePage.clickContinueShoppingButton();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("4"), "product quantity not increased on adding product on corp site");
		openBIZSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyShoppingBagAppears(), "shopping bag doesnot appear");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("1"), "product quantity not updated on biz site");
		logger.info("1 product is successfully added to the cart");
		//update qty to 3 of the first product
		storeFrontHeirloomHomePage.addQuantityOfProduct("3"); 
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("3"), "product quantity not increased on biz site");
		storeFrontHeirloomHomePage.clickContinueShoppingButton();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("4"), "product quantity not increased on adding product on biz site");
		openCOMSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyShoppingBagAppears(), "shopping bag doesnot appear");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("1"), "product quantity not updated on com site");
		logger.info("1 product is successfully added to the cart");
		//update qty to 3 of the first product
		storeFrontHeirloomHomePage.addQuantityOfProduct("3"); 
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("3"), "product quantity not increased on com site");
		storeFrontHeirloomHomePage.clickContinueShoppingButton();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("4"), "product quantity not increased on adding product on xom site");
		s_assert.assertAll();
	}

	//*TC-697 Login user should able to view Shopping bag icon appear on all sites regardless product added or not(corp,com,pws)
	@Test(enabled=false)///not working on jenkins
	public void LoginUserShouldAbleToViewShoppingBagIconAppearOnAllSitesRegardlessProductAddedOrNot_697() throws InterruptedException {
		String regimen=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		String consultantEmailID = null;

		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
			}
			else{
				consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}

		//corp site
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyShoppingBagAppears(), "shopping bag doesnot appear");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("1"), "product quantity not updated on corp site");
		logger.info("1 product is successfully added to the cart");
		//update qty to 3 of the first product
		storeFrontHeirloomHomePage.addQuantityOfProduct("3"); 
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("3"), "product quantity not increased on corp site");
		storeFrontHeirloomHomePage.clickContinueShoppingButton();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("4"), "product quantity not increased on adding product on corp site");
		openBIZSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyShoppingBagAppears(), "shopping bag doesnot appear");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("1"), "product quantity not updated on biz site");
		logger.info("1 product is successfully added to the cart");
		//update qty to 3 of the first product
		storeFrontHeirloomHomePage.addQuantityOfProduct("3"); 
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("3"), "product quantity not increased on biz site");
		storeFrontHeirloomHomePage.clickContinueShoppingButton();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("4"), "product quantity not increased on adding product on biz site");
		openCOMSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyShoppingBagAppears(), "shopping bag doesnot appear");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("1"), "product quantity not updated on com site");
		logger.info("1 product is successfully added to the cart");
		//update qty to 3 of the first product
		storeFrontHeirloomHomePage.addQuantityOfProduct("3"); 
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("3"), "product quantity not increased on com site");
		storeFrontHeirloomHomePage.clickContinueShoppingButton();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getNumberOfItemsFromShoppingBag().contains("4"), "product quantity not increased on adding product on xom site");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the sublink of About R+F from Corporate site
	 */
	//*QTest ID TC-360 Company-Links should be present (Execute team, our history, carrers, PFC foundation, press room, contact us) 
	@Test(enabled=true)
	public void testVerfiyCompanyLinksOnCorp_360(){
		String whoWeAre = "WHO WE ARE";
		String meetTheDoctors = "MEET THE DOCTORS";
		String executiveTeam = "EXECUTIVE TEAM";
		String givingBack = "GIVING BACK";
		String currentURL = null;

		//verify company careers Link?
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Who We Are");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("Company"),"Company link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(whoWeAre),whoWeAre+" link text is not present");

		// assert meet the doctors link
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Meet the Doctors");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("meet-the-doctors"),"MeetTheDoctors link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(meetTheDoctors),meetTheDoctors+" link text is not present");

		// assert Executive Team link
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Executive Team");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("Executives"),"Executive Team link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(executiveTeam),executiveTeam+" link text is not present");

		// assert Giving Back link
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Giving Back");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("Giving-Back"),"Giving Back link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(givingBack),givingBack+" link text is not present");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate product details before and after placing an order from Corporate site
	 */	
	//*QTest ID TC-2352 Checkout as Consultant
	@Test(enabled=true)
	public void testCheckoutAsConsultant_2352(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String subTotalFromOrderReview = null;
		String shippingFromOrderReview = null;
		String taxFromOrderReview = null;
		String grandTotalFromOrderReview = null;
		String quantityFromOrderReview = null;
		String SKUFromOrderReview = null;
		String itemNameFromOrderReview = null;
		String priceFromOrderReview = null;
		String totalFromOrderReview = null;
		String quantityFromOrderConfirmation = null;
		String SKUFromOrderConfirmation = null;
		String itemNameFromOrderConfirmation = null;
		String priceFromOrderConfirmation = null;
		String totalFromOrderConfirmation = null;
		String subTotalFromOrderConfirmation = null;
		String shippingFromOrderConfirmation = null;
		String taxFromOrderConfirmation = null;
		String grandTotalFromOrderConfirmation = null;
		String category = "REDEFINE";
		String consultantEmailID = null;
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
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		subTotalFromOrderReview = storeFrontHeirloomHomePage.getSubtotalFromOrderConfirmation();
		shippingFromOrderReview = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		taxFromOrderReview = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		grandTotalFromOrderReview = storeFrontHeirloomHomePage.getGrandTotalFromOrderConfirmationPage();
		quantityFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","1");
		SKUFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","2");
		itemNameFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","3");
		priceFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","5");
		totalFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","6");

		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		// get details from order confirmation
		subTotalFromOrderConfirmation = storeFrontHeirloomHomePage.getSubtotalFromOrderConfirmation();
		shippingFromOrderConfirmation = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		taxFromOrderConfirmation = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		grandTotalFromOrderConfirmation = storeFrontHeirloomHomePage.getGrandTotalFromOrderConfirmationPage();
		quantityFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","1");
		SKUFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","2");
		itemNameFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","3");
		priceFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","5");
		totalFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","6");

		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from biz site for Consultant user.");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		//assert all product details

		s_assert.assertTrue(quantityFromOrderConfirmation.equalsIgnoreCase(quantityFromOrderReview), "Expected qunatity of product is "+quantityFromOrderReview+ "but actual on UI "+quantityFromOrderConfirmation);
		s_assert.assertTrue(SKUFromOrderConfirmation.contains(SKUFromOrderReview), "Expected SKU value of product is "+SKUFromOrderReview+ "but actual on UI "+SKUFromOrderConfirmation);
		s_assert.assertTrue(itemNameFromOrderConfirmation.contains(itemNameFromOrderReview), "Expected name of product is "+itemNameFromOrderReview+ "but actual on UI "+itemNameFromOrderConfirmation);
		s_assert.assertTrue(priceFromOrderConfirmation.contains(priceFromOrderReview), "Expected price of product is "+priceFromOrderReview+ "but actual on UI "+priceFromOrderConfirmation);
		s_assert.assertTrue(totalFromOrderConfirmation.contains(totalFromOrderReview), "Expected total value of product is "+totalFromOrderReview+ "but actual on UI "+totalFromOrderConfirmation);
		s_assert.assertTrue(subTotalFromOrderConfirmation.contains(subTotalFromOrderReview), "Expected subtotal of product is "+subTotalFromOrderReview+ "but actual on UI "+subTotalFromOrderConfirmation);
		s_assert.assertTrue(shippingFromOrderConfirmation.contains(shippingFromOrderReview), "Expected shipping value of product is "+shippingFromOrderReview+ "but actual on UI "+shippingFromOrderConfirmation);
		s_assert.assertTrue(taxFromOrderConfirmation.contains(taxFromOrderReview), "Expected tax of product is "+taxFromOrderReview+ "but actual on UI "+taxFromOrderConfirmation);
		s_assert.assertTrue(grandTotalFromOrderConfirmation.contains(grandTotalFromOrderReview), "Expected grand total of product is "+grandTotalFromOrderReview+ "but actual on UI "+grandTotalFromOrderConfirmation);
		s_assert.assertAll();
	}


	/**
	 * Test Summary: Open BIZ, Login with PC, goto My account and click on Edit order link and verify
	 * Assertions:
	 * Autoship date is changed or not
	 * Confirmation msg at Orders page
	 */
	//*QTest ID TC-2342 PC Perks Delay - 30 days Corp Site
	@Test(enabled=true)
	public void testPCPerksDelay30DaysCorp_2342(){
		String changeMyPCPerksStatus = "Change my PC Perks Status";
		String pcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickMyAccountLink();
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		storeFrontHeirloomPCUserPage.clickDelayPCPerksLink();
		storeFrontHeirloomPCUserPage.clickChangeAutohipDateBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessage(),"confirmation msg not present as expected");
		storeFrontHeirloomHomePage.clickBackToMyAccountBtn();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessageInOrders(),"Confirmation msg not present at orders page as expected");
		s_assert.assertAll();
	}	

	/**
	 * Test Summary: Login with PC User, goto My account and click on Edit order link, enter new shipping  address and verify
	 * Assertions:
	 * Newly added shipping address is present
	 */
	//*QTest ID TC-2344  PC Perks Template - Shipping Address (Corp Site)
	@Test(enabled=true)
	public void testPCPerksTemplateShippingAddressUpdateOnCorpSite_2344(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME+randomNumber;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		String pcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickMyAccountLink();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickChangeLinkUnderShippingToOnPWS();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getShippingAddressName().contains(shippingProfileFirstName),"Shipping address name expected is "+shippingProfileFirstName+" While on UI is "+storeFrontHeirloomHomePage.getShippingAddressName());
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate RF Connection Link From Corporate Site
	 * Assertions:
	 * Page URL should contain 'rfconnection'
	 * RF Connection should be visible on page Header
	 */	
	//*QTest ID TC-2332 RF Connection From Corp Site
	@Test(enabled=true)
	public void testRFConnectionFromCorp_2332(){
		String rfConnection="RF Connection";
		String currentURL=null;
		String parentWindow=null;
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(rfConnection);
		if(storeFrontHeirloomHomePage.getTotalWindows()==2){
			parentWindow=storeFrontHeirloomHomePage.switchToChildWindow();
		}
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("rfconnection"),"Expected URL should contain 'rfconnection' But actual on UI is: "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRFConnectionPageHeaderVisible()," User is not redirected to RF Connection Page");
		if(parentWindow!=null)
			storeFrontHeirloomHomePage.switchToParentWindow(parentWindow);
		s_assert.assertAll();
	}


	/***
	 * Test Summary:- validate DERM RF Link From Corporate Site
	 * Assertions:
	 * Page URL should contain 'dermrf'
	 * DERM RF should be visible on page Header
	 */	
	//*QTest ID TC-2333 Derm Connection from Corporate Site
	@Test(enabled=true)
	public void testDermConnectionFromCorp_2333(){
		String dermRF="Derm RF";
		String currentURL=null;
		String parentWindow=null;
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(dermRF);
		if(storeFrontHeirloomHomePage.getTotalWindows()==2){
			parentWindow=storeFrontHeirloomHomePage.switchToChildWindow();
		}
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("dermrf"),"Expected URL should contain 'dermrf' But actual on UI is: "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isDERMRFPageHeaderVisible()," User is not redirected to DERM RF Page");
		if(parentWindow!=null)
			storeFrontHeirloomHomePage.switchToParentWindow(parentWindow);
		s_assert.assertAll();
	}


	/***
	 * Test Summary:- validate Shipping Link From Corporate Site
	 * Assertions:
	 * Page URL should contain 'shipping'
	 * Shipping should be visible on page Header
	 */	
	//*QTest ID TC-2336 Shipping from Corporate Site
	@Test(enabled=true)
	public void testShippingFromCorp_2336(){
		String shipping="Shipping";
		String currentURL=null;
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(shipping);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("shipping"),"Expected URL should contain 'shipping' But actual on UI is: "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isShippingPageHeaderVisible()," User is not redirected to Shipping Page");
		s_assert.assertAll();
	}

	//* QTest ID TC-361 About R+F > Execute team link should be displayed properly on Corporate site
	@Test(enabled=true)
	public void AboutRFExecuteTeamLinkShouldBeDisplayedProperly_361() {
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Executive Team");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExecutiveTeamLinkPresent(), "Executive team link is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExecuteTeamLink(), "Not navigated to Executive team link");
		s_assert.assertAll(); 
	}

	//* QTest ID TC-363 Carrers on Corporate site
	@Test(enabled=true)
	public void Carrers_363() {
		storeFrontHeirloomHomePage.clickCareersLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCareersLinkURL().contains("careers"), "career link is not present");
		s_assert.assertAll(); 
	}

	//* QTest ID TC-379 BecomeAConsultantProgramsIncentivesCompensationPlanHereLink on Corporate site
	@Test(enabled=true)
	public void BecomeAConsultantProgramsIncentivesCompensationPlanHereLink_379() {  
		//storeFrontHeirloomHomePage.val
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Programs and Incentives");
		storeFrontHeirloomHomePage.clickCompensationPlanHereLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("Comp-PlanForUS_")|| storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("ProgramsIncentives")||storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("compensation"), "compansation plan link is not clicked");
		storeFrontHeirloomHomePage.clickToReadIncomeDisclosure();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentUrlOpenedWindow().contains("Income-Disclosure-Statement.pdf"), "Income disclosure plan link is not clicked");
		s_assert.assertAll(); 
	}

	//* QTest ID TC-383 The Events section Leadership Summit is displayed Corporate site
	@Test(enabled=true)
	public void TheEventsSectionLeadershipSummitIsDisplayed_383() {	
		String parentWinId = null;
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Events");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyBusinessPresentationSectionUnderEvents(), "Business Presentation Section is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyRFConventionSectionUnderEvents(), "RF Convention Section is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyBussinessRedefinedLearningSectionUnderEvents(), "Bussiness Redefined Learning Section is not present");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConsultantLedEventsSectionUnderEvents(), "Consultant Led Events Section is not present");
		storeFrontHeirloomHomePage.clickBusinessPresentationEventsCalendarLink();
		s_assert.assertEquals(storeFrontHeirloomHomePage.verifyTextCalendarPageisDisplyed().toLowerCase(),"Event Calendar".toLowerCase(), "calendar page is not displayed");
		storeFrontHeirloomHomePage.clickBusinessPresentationEventsCalendarLink();
		parentWinId=storeFrontHeirloomHomePage.switchToChildWindow();
		s_assert.assertTrue(storeFrontHeirloomHomePage.eventsCalendarDetails(), "event calendar details is not present");
		storeFrontHeirloomHomePage.closeChildAndSwitchToParentWindow(parentWinId);
		s_assert.assertAll(); 
	}	

	//* QTest ID TC-381 Corp> Programs And Incentives Page displaying "Enroll Now" button
	@Test(enabled=false)//fix
	public void testCorpProgramsAndIncentivesPageDisplayingEnrollNowButton_381() {		
		String expectedURL="SearchSponsor";
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Programs and Incentives");
		storeFrontHeirloomHomePage.clickEnrollNowUnderStartYourJourney();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertAll();
	}

	//* QTest ID TC-382 Corp> Become a consultant user > Program And Incentives > RF Events link
	@Test(enabled=true)
	public void testBecomeAConsultantUserProgramAndIncentivesRFEventsLink_382() {
		String expectedURL="Events";
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Programs and Incentives");
		storeFrontHeirloomHomePage.clickRFEventslinkUnderLearningAndLeadershipSection();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertAll(); 
	}

	//* QTest ID TC-390 Corp> Become A Consultant > Meet Our Community
	@Test(enabled=true)
	public void testBecomeAConsultantMeetOurCommunity_390() {
		String fb="Facebook" ;
		String twitter="Twitter" ;
		String insta="Instagram" ;
		String dermrf="DermRF" ;
		String pinterest="Pinterest" ;
		String youtube="YouTube" ;

		String expectedURL="SearchSponsor";
		String expectedURLFB="facebook";
		String expectedURLtwitter="twitter";
		String expectedURLinstagram="instagram";
		String expectedURLdermrf="dermrf";
		String expectedURLpinterest="pinterest";
		String expectedURLyoutube="youtube";

		String parentWindowHandle = null;

		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Meet Our Community");
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyMeetOurCommunityPage(),"Meet Our Community page is NOT displayed");
		parentWindowHandle = storeFrontHeirloomHomePage.clickOnReadTheirInspiringStoriesLink();
		//		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyReadTheirInspiringStoriespage(), "read their inspiring stories page is not displayed");
		storeFrontHeirloomHomePage.switchToParentWindow(parentWindowHandle);
		parentWindowHandle = storeFrontHeirloomHomePage.clickEnrollNowBtnOnMeetOurCommunityPage();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCurrentURLContainsExpectedString(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.switchToParentWindow(parentWindowHandle);
		parentWindowHandle = storeFrontHeirloomHomePage.clickLinksOnMeetourCommunityPage(fb);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCurrentURLContainsExpectedString(expectedURLFB), "Current url expected is: "+expectedURLFB+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.switchToParentWindow(parentWindowHandle);
		storeFrontHeirloomHomePage.clickLinksOnMeetourCommunityPage(twitter);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCurrentURLContainsExpectedString(expectedURLtwitter), "Current url expected is: "+expectedURLtwitter+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.switchToParentWindow(parentWindowHandle);
		storeFrontHeirloomHomePage.clickLinksOnMeetourCommunityPage(insta);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCurrentURLContainsExpectedString(expectedURLinstagram), "Current url expected is: "+expectedURLinstagram+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.switchToParentWindow(parentWindowHandle);
		storeFrontHeirloomHomePage.clickLinksOnMeetourCommunityPage(dermrf);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCurrentURLContainsExpectedString(expectedURLdermrf), "Current url expected is: "+expectedURLdermrf+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.switchToParentWindow(parentWindowHandle);
		storeFrontHeirloomHomePage.clickLinksOnMeetourCommunityPage(pinterest);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCurrentURLContainsExpectedString(expectedURLpinterest), "Current url expected is: "+expectedURLpinterest+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.switchToParentWindow(parentWindowHandle);
		storeFrontHeirloomHomePage.clickLinksOnMeetourCommunityPage(youtube);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCurrentURLContainsExpectedString(expectedURLyoutube), "Current url expected is: "+expectedURLyoutube+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		storeFrontHeirloomHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll(); 
	}

	//QTest ID TC-2438 CORP > Forgot Password
	@Test
	public void testCORPForgotPassword_2438(){
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomHomePage.clickForgotPasswordLinkOnBizHomePage();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateChangePasswordMessagePrompt(),"Message not prompted for 'change password'");
		storeFrontHeirloomHomePage.enterEmailAddressToRecoverPassword(consultantEmailID);
		storeFrontHeirloomHomePage.clickSendEmailButton();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validatePasswordChangeAndEmailSent(),"resetting your password mail is not displayed!!");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open Corp, Click on Country Toggle Select AUS and verify
	 * Assertions:
	 * AUS Should be present in Country Toggle
	 * User should redirected to AUS Country after clicking on 'AUS' from country toggle
	 */
	//QTest ID TC-925 Corp> Verify the User is redirected to the Corporate Australian Home Page
	@Test
	public void testVerifyTheUserIsRedirectedToTheCorporateAustralianHomePageCorp_925(){
		String countryName="AUS";
		String url="rodanandfields.com.au";
		String currentUrl=null;

		storeFrontHeirloomHomePage.clickCountryToggle();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCountryPresentInCountryToggle(countryName)," Expected country: "+countryName+" not present in country toggle");
		storeFrontHeirloomHomePage.selectCountry(countryName);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(url),"User is not redirected to expected country:"+countryName+" Url should contains: "+url+" But actual on UI is: "+currentUrl);
		s_assert.assertAll();
	}


	/**
	 * Test Summary: Open Corp, click WHY R+F link , click Enroll now button and verify search sponsor page
	 * Assertions:
	 * verify search sponsor page is getting displayed
	 */
	//QTest ID TC-391 Corporate> Why R+F
	@Test
	public void testCorporateWhyRF_391(){
		storeFrontHeirloomHomePage.clickBeAConsultantBtn();
		storeFrontHeirloomHomePage.clickWhyRFLinkUnderBusinessSystem();
		storeFrontHeirloomHomePage.clickEnrollNowBtnOnWhyRFPage();
		//verify url for 'Enroll Now'
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateSearchSponsorPageDisplayed(),"'Search Sponsor' Page Is not displayed");
		s_assert.assertAll();
	}

	//QTest ID TC-2428 selects a product from the carousel System will direct the user to the product's PDP & Opens in same tab(corp, biz)
	@Test(enabled=false)//should be tested manually
	public void testSelectsAProductFromTheCarouselSystemWillDirectTheUserToTheProductPDPAndOpensInSameTab_2428(){
		String prodname= null;
		String prodname1=null;
		String regimen = TestConstants.REGIMEN_NAME_REVERSE;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.isYouMayAlsoFindTheseProductsSectionVisible();
		prodname=storeFrontHeirloomHomePage.selectProductFromCarouselcorp();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserRedirectedToCarouselProductPDPcorp(prodname),"user not redirected to products PDP page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCarouselProductOpenedInTheSameTab(),"Carousel product page not opened");
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isYouMayAlsoFindTheseProductsSectionVisibleBiz(), "you may also find this section is not present");
		prodname1=storeFrontHeirloomHomePage.selectProductFromCarousel();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserRedirectedToCarouselProductPDP(prodname1),"user not redirected to products PDP page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCarouselProductOpenedInTheSameTab(),"Carousel product page not opened");
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isYouMayAlsoFindTheseProductsSectionVisibleBiz(), "you may also find this section is not present");
		prodname1=storeFrontHeirloomHomePage.selectProductFromCarousel();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserRedirectedToCarouselProductPDP(prodname1),"user not redirected to products PDP page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCarouselProductOpenedInTheSameTab(),"Carousel product page not opened");
		s_assert.assertAll();
	}

	//Consultants Only - buy business promotion 
	@Test(enabled=false)//test no longer valid
	public void testConsultantsOnlyBuyBusinessPromotion(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		openCOMSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink("Promotions");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("promotions"), "Expected regimen name is: promotions Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}

	//QTest ID TC-2479 Adhoc Order with Newly created billing address
	//QTest ID TC-509 Adhoc Order - Consultant Only Products
	@Test(enabled=false)//duplicate test
	public void testAdhocOrderConsultantsOnlyProducts_509_2479(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomConsultantPage = storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_PRODUCT_PROMOTION);
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}

	//Variant Product Add to Ad-hoc and Autoship cart From Category Page for Consultant 
	@Test
	public void testVariantProductFromCategoryForConsultant(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int productPosition=0;
		String parentWindowHandle=null;
		String productName = TestConstantsRFL.VARIANT_PRODUCT_NAME;
		String fullProductName = TestConstantsRFL.VARIANT_PRODUCT_FULL_NAME;
		String shade = "Medium";
		String firstName = TestConstantsRFL.FIRST_NAME;
		String nameOnCard = firstName;
		String expMonth = TestConstantsRFL.EXP_MONTH;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		boolean flag=false;
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomConsultantPage = storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		String regimen = TestConstantsRFL.REGIMEN_NAME_ENHANCEMENTS;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains(regimen.toLowerCase()), "Expected regimen name is "+regimen.toLowerCase()+" Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		storeFrontHeirloomHomePage.selectVariantOfProduct(fullProductName, shade);
		storeFrontHeirloomHomePage.addProductToAdHocCart(productName);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isProductAvailableInCart(productName),"Variant Product is not available on Ad-hoc cart page");
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomPulsePage =storeFrontHeirloomHomePage.clickCheckMyPulse();
		storeFrontHeirloomPulsePage.dismissPulsePopup();
		parentWindowHandle=storeFrontHeirloomPulsePage.getParentWindowHandle();
		storeFrontHeirloomPulsePage.clickMyAccountLinkOnPulsePage();
		storeFrontHeirloomPulsePage.clickEditCRP();
		if(storeFrontHeirloomPulsePage.isEnrollInCRPButtonDisplayed()==false){
			flag=storeFrontHeirloomPulsePage.isProductPresentOnCRPOverViewPage(fullProductName);
			if(flag){
				productPosition=storeFrontHeirloomPulsePage.getPositionOfProductInCRPOverViewPage(fullProductName);
				storeFrontHeirloomPulsePage.clickEditReplenishmentOrder();
				storeFrontHeirloomPulsePage.removeProductFromAutoshipCart(productPosition);
				storeFrontHeirloomPulsePage.clickUpdateOrder();
			}
			storeFrontHeirloomPulsePage.clickEditReplenishmentOrder();
			storeFrontHeirloomPulsePage.clickLinksInHeader(regimen);
			storeFrontHeirloomPulsePage.selectVariantOfAProduct(fullProductName, shade);
			storeFrontHeirloomPulsePage.addProductToAutoshipCart(fullProductName);
			storeFrontHeirloomPulsePage.clickUpdateOrder();
			s_assert.assertTrue(storeFrontHeirloomPulsePage.isProductPresentOnCRPOverViewPage(fullProductName),"Expected Product "+fullProductName+" is not present at CRP Overview Page");
		}

		storeFrontHeirloomPulsePage.closeChildAndSwitchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- Open corp url, Login with RC, start to place an adhoc order, at checkout page select FedEx 1 day and FedEx 2 Day shipping method and verify shipping charges respectively 
	 * Assertions:
	 * Shipping charges for FedEx 1 day and FedEx 2 Day shipping method respectively  
	 */	
	//QTest ID TC-653 Verify 1-2 day shipping ad-hoc US RC order on Corporate site
	@Test
	public void testAdhocOrderConsultantsOnlyProductsFromCorp_653(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String rcEmailID = null;
		String shippingMethodFedEx1Day = "FedEx 1 day";
		String shippingMethodFedEx2Day = "FedEx 2 day";
		String shippingChargesForFedEx1Day = "25.00";
		String shippingChargesForFedEx2Day = "15.00";
		String shippingChargesFromUI = null;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(rcEmailID, password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "RC user not logged in successfully");
		s_assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("consultantlocator"), "RC user not redirected to consultant locator after login");
		storeFrontHeirloomHomePage.clickContinueWithoutConsultantLink();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.selectShippingMethod(shippingMethodFedEx1Day);
		shippingChargesFromUI = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		s_assert.assertTrue(shippingChargesFromUI.contains(shippingChargesForFedEx1Day), "Expected shipping charges are"+shippingChargesForFedEx1Day+" for shipping method "+shippingMethodFedEx1Day+" but actual on UI is "+shippingChargesFromUI);
		storeFrontHeirloomHomePage.selectShippingMethod(shippingChargesForFedEx2Day);
		shippingChargesFromUI = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		s_assert.assertTrue(shippingChargesFromUI.contains(shippingChargesForFedEx2Day), "Expected shipping charges are"+shippingChargesForFedEx2Day+" for shipping method "+shippingMethodFedEx2Day+" but actual on UI is "+shippingChargesFromUI);
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from com site for RC user.");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- Open corp url, Login with PC, select a product(price<$80) for adhoc, at checkout page verify shipping charges respectively 
	 * Assertions:
	 * verify shipping charges for a product(price<$80)  
	 */	
	//QTest ID TC-669 Verify shipping fee for ad-hoc US PC order < $80
	@Test
	public void testVerifyShippingFeeForAdhocPCOrderLessThan80FromCorp_669(){
		String regimen = TestConstantsRFL.REGIMEN_NAME_ENHANCEMENTS;
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String pcEmailId = null;
		String shippingChargesFromUI = null;
		String shippingCharges = "9.95";
		int productPosition = 0;
		double productPrice = 80.00;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		productPosition = storeFrontHeirloomHomePage.getPositionOfProductForLessPrice(productPrice);
		storeFrontHeirloomHomePage.addProductToAdHocCartThroughProductNumber(""+productPosition);
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		shippingChargesFromUI = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		s_assert.assertTrue(shippingChargesFromUI.equalsIgnoreCase(shippingCharges), "Expected shipping charges are"+shippingCharges+" but actual on UI is "+shippingChargesFromUI);
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- Open corp url, Login with PC, select a product(price>$80) for adhoc, at checkout page verify shipping charges respectively 
	 * Assertions:
	 * verify shipping charges for a product(price>$80)  
	 */	
	//QTest ID TC-668 Verify shipping fee for ad-hoc US PC order > $80
	@Test
	public void testVerifyShippingFeeForAdhocPCOrderMoreThan80FromCorp_668(){
		String regimen = TestConstantsRFL.REGIMEN_NAME_ENHANCEMENTS;
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String pcEmailId = null;
		String shippingChargesFromUI = null;
		String shippingCharges = "FREE";
		int productPosition = 0;
		double productPrice = 80.00;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		productPosition = storeFrontHeirloomHomePage.getPositionOfProductForMorePrice(productPrice);
		storeFrontHeirloomHomePage.addProductToAdHocCartThroughProductNumber(""+productPosition);
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		shippingChargesFromUI = storeFrontHeirloomHomePage.getShippingTotalFromOrderHistoryPageForPC();
		s_assert.assertTrue(shippingChargesFromUI.equalsIgnoreCase(shippingCharges), "Expected shipping charges are"+shippingCharges+" but actual on UI is "+shippingChargesFromUI);
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- Open corp url, Login with Consultant, select a product(SV price<$100) for adhoc, at checkout page verify shipping charges respectively 
	 * Assertions:
	 * verify shipping charges for a product(SV price<$100)  
	 */	
	//QTest ID TC-672 Verify shipping fee for ad-hoc US Consultant order < $100
	@Test
	public void testVerifyShippingFeeForAdhocConsultantOrderLessThan100FromCorp_672(){
		int productPosition = 0;
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		double productPrice = 100.00;
		String shippingChargesFromUI = null;
		String shippingCharges = "11.95";
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		String regimen=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomConsultantPage = storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		productPosition = storeFrontHeirloomHomePage.getPositionOfProductForLessSVPrice(productPrice);
		storeFrontHeirloomHomePage.addProductToAdHocCartThroughProductNumber(""+productPosition);
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		shippingChargesFromUI = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		s_assert.assertTrue(shippingChargesFromUI.equalsIgnoreCase(shippingCharges), "Expected shipping charges are"+shippingCharges+" but actual on UI is "+shippingChargesFromUI);
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}	


	// QTest ID TC-2341  Corp: PC User termination
	@Test 
	public void testPCUserTermination_2341(){
		List<Map<String, Object>> accountStatusIDList= null;
		String statusID= null;
		String pcEmailId = null;
		String changeMyPCPerksStatus = "Change my PC Perks Status";  
		pcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickMyAccountLink();
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isDelayOrCancelPCPerksLinkPresent(), "Delay or cancel PC perks link is not present");
		storeFrontHeirloomPCUserPage.clickCancelPCPerksLink();
		storeFrontHeirloomPCUserPage.clickChangedMyMindBtn();
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.getCurrentURL().contains("Dashboard"), "User is not redirecting to dashboard after clicked on changed my mind button");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		storeFrontHeirloomPCUserPage.clickCancelPCPerksLink();
		storeFrontHeirloomPCUserPage.selectReasonForPCTermination();
		storeFrontHeirloomPCUserPage.enterMessageForPCTermination();
		storeFrontHeirloomPCUserPage.clickSendEmailToCancelBtn();
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.getEmailConfirmationMsgAfterPCTermination().contains("you will be receiving a confirmation e-mail shortly"), "Expected email confirmation message is: you will be receiving a confirmation e-mail shortly, but actual on UI is: "+storeFrontHeirloomPCUserPage.getEmailConfirmationMsgAfterPCTermination());
		storeFrontHeirloomPCUserPage.clickGoToRFHomeButton();
		storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isInvalidLoginPresent(),"Terminated User is able to Login");
		//verify Account status
		accountStatusIDList =  DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_ACCOUNT_STATUS_ID_OF_INACTIVE_PC, pcEmailId), RFL_DB);
		statusID = String.valueOf(getValueFromQueryResult(accountStatusIDList, "StatusID"));
		s_assert.assertTrue(statusID.contains("2"), "Account status is active");
		s_assert.assertAll();
	}

	//QTest ID TC-2379 Donation order 
	@Test(enabled=true)//should be tested manually
	public void testDonationOrderFromCorp_2379(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomConsultantPage = storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		//storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		//storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_EVENT_SUPPORT);
		storeFrontHeirloomHomePage.clickDonationProduct();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from biz site for Consultant user.");
		s_assert.assertAll();
	}	

	/***
	 * Test Summary:- Open corp url, Login with Consultant, Add a product to cart, Add new shipping address and complete place order and verify
	 * Assertions:
	 * verify Verify that the order was placed properly with newly created shipping profile
	 */ 
	//QTest ID TC-2478 Adhoc Order with Newly created shipping address
	@Test
	public void testAdhocOrderWithNewlyCreatedShippingAddressFromCorp_2478(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME+randomNumber;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		String shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName;
		String shippingName=null;
		String shippingAddress=null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomConsultantPage = storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickChangeShippingAddressBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		shippingName=storeFrontHeirloomHomePage.getShippingNameFromOrderConfirmationPage();
		s_assert.assertTrue(shippingProfileName.contains(shippingName),"Shipping Profile is not the same as entered");
		shippingAddress=storeFrontHeirloomHomePage.getAddressFromOrderConfirmationPage();
		s_assert.assertTrue(shippingAddress.contains(addressLine1.toUpperCase()),"Shipping Address is not the same as entered");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- Open corp url, Login with PC User, and verify
	 * Assertions:
	 * Verify sponsor name on the top of the page
	 */ 
	//QTest ID TC-2151 Sponsor Info_My account
	@Test
	public void testSponsorInfo_MyAccountFromCorp_2151(){
		String pcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickMyAccountLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSponsorInfoPresentOnTopOfPage(),"Sponsor Info not Available on Top of Page");
		s_assert.assertAll();
	}

	//QTest ID TC-358 Consultants Only -buy product promotion 
	@Test(enabled=true)//needs updation
	public void testconsultantsOnlyBuyProductPromotion_358(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomConsultantPage = storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_PRODUCT_PROMOTION);
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}

	//QTest ID TC-407 Registering the consultant using Retail customer's email id
	@Test(priority=11)
	public void RegisterConsultantUsingExistingRetailCustomerEmailId_407(){
		String enrollemntType = "Express";
		String rcEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		String sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		//storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExistingRCPopUp(rcEmailID),"Existing RC Pop Up is not displayed!!");
		s_assert.assertAll();
	} 

	/**
	 * Test Summary: Open BIZ, Login with PC USer, goto My account and Remove or add products and verify
	 * Assertions:
	 * Status message after removing products
	 * Replenishment Order items successfully updated!
	 * verify order total at Overview page
	 */
	//QTest ID TC-2343 PC Perks Template Update - Add/modify products 
	@Test(enabled=true)
	public void testPCPerksTemplateUpdate_2343(){
		String userEmailId = null;
		if(shouldFetchDataFromExcelForTokeniation){
			userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("My Account");
		storeFrontHeirloomHomePage.clickEditOrderLink().clickEditOrderBtn();
		storeFrontHeirloomHomePage.clickRemoveLinkAboveTotal();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isStatusMessageDisplayed(),"status message is not displayed as expected");
		//storeFrontHeirloomHomePage.clickAddToCartBtnForLowPriceItems();
		storeFrontHeirloomHomePage.clickAddToCartBtnForHighPriceItems();
		storeFrontHeirloomHomePage.clickOnUpdateOrderBtn();
		storeFrontHeirloomHomePage.handleAlertAfterUpdateOrder();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getConfirmationMessage().contains("Replenishment Order items successfully updated!"),"No Message appearing after adding products to CRP cart");
		String updatedOrderTotal = storeFrontHeirloomHomePage.getOrderTotal();
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomHomePage.clickEditOrderLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderTotalAtOverview().contains(updatedOrderTotal),"order total is not updated at overview page");
		s_assert.assertAll();
	}

	//*TC-702 Shopping bag shouldn't show number after purchasing product
	@Test(enabled=true)
	public void ShoppingBagShouldnotShowNumberAfterPurchasingProduct_702() throws InterruptedException {
		String regimen=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		String rcEmailID = null;
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		//storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickMyShoppingBagLink();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsRCUserCorpSite(rcEmailID, password);
		storeFrontHeirloomHomePage.clickContinueWithoutConsultantLink();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Enrollment is not completed successfully");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		//verify that the shopping bag doesnot show the number
		s_assert.assertFalse(storeFrontHeirloomHomePage.verifyShoppingBagDoesnotShowNumber(), "Cart is not empty");
		//verify that the shopping cart doesnot contain any product
		s_assert.assertFalse(storeFrontHeirloomHomePage.verifyIfProductIsPresent(), "product is not present");
		logger.info("Cart is empty");
		s_assert.assertAll(); 
	}


	//PC Adhoc Order – Adding new billing profile with existing user
	@Test
	public void testAddNewBillingProfileDuringPCAdhocOrderFromCorp(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String pcEmailID =  null;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		//storeFrontHeirloomHomePage.clickShopSkinCareHeader();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		/*	storeFrontHeirloomHomePage.selectRegimen(regimen);
			storeFrontHeirloomHomePage.clickAddToCartBtn();*/
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(pcEmailID, password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "PC user not logged in successfully");
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Newly created billing profile is not selected for new order.");
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}


	/***
	 * Test Summary:- placed an adhoc order with consultant only buy event support pack
	 *  and validate orders details like subtotal & product name from biz site
	 */
	//*QTest ID TC-357 Consultants Only -buy event support pack 
	@Test(enabled=true)
	public void testConsultantsOnlyBuyEventSupportPack_357(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomConsultantPage = storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_EVENT_SUPPORT);
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}


	//PC Adhoc Order – with existing billing profile
	@Test
	public void testPCAdhocOrderFromCorpWithExistingBillingProfile(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String pcEmailID = null;
		String firstName = TestConstantsRFL.FIRST_NAME;
		String cardNumber = TestConstantsRFL.CARD_NUMBER_SECOND;
		String nameOnCard = firstName;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumber;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		//storeFrontHeirloomHomePage.clickShopSkinCareHeader();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		//storeFrontHeirloomHomePage.selectRegimen(regimen);
		//storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(pcEmailID, password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "PC user not logged in successfully");
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName)||storeFrontHeirloomHomePage.getBillingAddress().contains(addressLine1), "Existing billing profile is not selected for new order.");
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}


	//QTest ID TC-510 Add Product to the Cart - Checkout as PC
	@Test(priority=1)
	public void testPCAdhocOrderFromCorp_510(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String pcEmailID = null;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		/*storeFrontHeirloomHomePage.selectRegimen(regimen);
			storeFrontHeirloomHomePage.clickAddToCartBtn();*/
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		//storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(pcEmailID, password);
		//s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "PC user not logged in successfully");
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}

	//QTest ID TC-507 PC Enrollment From Corp site
	@Test(priority=2)//smoke
	public void testPCEnrollment_507(){
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
		String addressName = "Home";
		sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		//storeFrontHeirloomHomePage.clickAddToCartBtn();
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
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteEnrollmentBtn();
		storeFrontHeirloomHomePage.clickOKBtnOfJavaScriptPopUp();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPCEnrollmentCompletedSuccessfully(), "PC enrollment is not completed successfully");
		s_assert.assertAll();
	}


	/***
	 * Test Summary:- Open corp url, Login with PC, start to place an adhoc order, add billing profile with INVALID credit card and place the order
	 * Assertions:
	 * Error message should be displayed for INVALID credit card after complete the enrollment
	 */	
	//QTest ID TC-1944 Payment error message - CORP
	@Test
	public void testPaymentErrorMessageWhilePlacingAnAdhocOrderThroughPCFromCorp_1944(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String cardNumber = TestConstantsRFL.INVALID_CARD_NUMBER;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String errorMessageFromUI = null;
		String pcEmailID = null;
		String errorMessage = TestConstantsRFL.ERROR_MSG_FOR_INVALID_CC;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(pcEmailID, password);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		errorMessageFromUI = storeFrontHeirloomHomePage.getErrorMessageForInvalidCreditCard();
		s_assert.assertTrue(errorMessageFromUI.contains(errorMessage), "Expected error message is "+errorMessage+"actual on UI is "+errorMessageFromUI);
		s_assert.assertAll();
	}

	//QTest ID TC-508 Adhoc order - RC corp
	@Test(priority=2)
	public void testPlaceAdhocOrderFromCorpSiteForRC_508(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String rcEmailId = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				rcEmailId=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
			}
			else{
				rcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
				storeFrontHeirloomHomePage.loginAsRCUser(rcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		//storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		//storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(rcEmailID, password);
		//s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "RC user not logged in successfully");
		//s_assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("consultantlocator"), "RC user not redirected to consultant locator after login");
		//storeFrontHeirloomHomePage.clickContinueWithoutConsultantLink();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from com site for RC user.");
		s_assert.assertAll();
	}

	//QTest ID TC-506  RC Enrollment from corp site.
	@Test (priority=1)//smoke
	public void testRCEnrollment_506(){
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
		String addressName = "Home";
		sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		//storeFrontHeirloomHomePage.clickAddToCartBtn();
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
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Enrollment is not completed successfully");
		s_assert.assertAll(); 
	}	

	/***
	 * Test Summary:- validate California Supply Chains Act Link From Corporate Site
	 * Assertions:
	 * Page URL should contain 'california-supply-chains-act'
	 * 'CALIFORNIA TRANSPARENCY IN SUPPLY CHAINS ACT' should be visible on page Header
	 */	
	//*QTest ID TC-2338 California Supply Chains Act from Corporate Site
	@Test(enabled=true)
	public void testCaliforniaSupplyChainsActFromCorp_2338(){
		String califSupplyChainsAct="California Supply Chains Act";
		String currentURL=null;
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(califSupplyChainsAct);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		assertTrue(storeFrontHeirloomHomePage.isCurrentURLContainsExpectedString("california-supply-chains-act"),"Expected URL should contain 'california-supply-chains-act' But actual on UI is: "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCaliforniaSupplyChainsActPageHeaderVisible()," User is not redirected to 'California Supply Chains' Act Page");
		s_assert.assertAll();
	}	


	/**
	 * Test Summary: Open Corp Site, Enroll consultant, goto My account and click on Edit My PWS link and verify
	 * Assertions:
	 * User is Enrolled Successfully
	 * Upload a new photo button is present on UI or not
	 */
	//QTest ID TC-2340 My account- As a consultant customer for Corp Site
	@Test(enabled=true)
	public void testMyAccountAsAConsultantCustomerCorp_2340(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String kitName = TestConstantsRFL.CONSULTANT_RFX_EXPRESS_BUSINESS_KIT;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		String enrollemntType = TestConstantsRFL.EXPRESS_ENROLLMENT;
		String editMyPWS = "Edit PWS";
		String sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.enterCID(sponsorID);
		storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenForConsultant(regimen);
		storeFrontHeirloomHomePage.clickNextBtnAfterSelectRegimen();
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Enrollment not completed successfully");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin(editMyPWS);
		storeFrontHeirloomHomePage.clickEditMyPhotoLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUploadANewPhotoButtonPresent(),"Upload a new photo button is not present");
		s_assert.assertAll();
	}


	/***
	 * Test Summary:- Open corp url, Login with Consultant, select a product(SV price>$100) for adhoc, at checkout page verify shipping charges respectively 
	 * Assertions:
	 * verify shipping charges for a product(SV price>$100)  
	 */	
	//QTest ID TC-671 Verify shipping fee for ad-hoc US Consultant order > $100
	@Test
	public void testVerifyShippingFeeForAdhocConsultantOrderMoreThan100FromCorp_671(){
		int productPosition = 0;
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		double productPrice = 100.00;
		String shippingChargesFromUI = null;
		String shippingCharges = "9.95";
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		storeFrontHeirloomConsultantPage = storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		productPosition = storeFrontHeirloomHomePage.getPositionOfProductForMoreSVPrice(productPrice);
		storeFrontHeirloomHomePage.addProductToAdHocCartThroughProductNumber(""+productPosition);
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		shippingChargesFromUI = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		s_assert.assertTrue(shippingChargesFromUI.equalsIgnoreCase(shippingCharges), "Expected shipping charges are"+shippingCharges+" but actual on UI is "+shippingChargesFromUI);
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}	

	/***
	 * qTest Id - 3501
	 */
	@Test(description="Terminate RC user and enroll as PC user with the same email id")
	public void testTerminateRCAndEnrollItAsPC_3501(){
		String accountNumber = null;
		String emailID = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumbers;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String sponsorID = null;
		String addressName = "Home";
		logger.info("DB is " + RFL_DB);
		List<Map<String, Object>> randomRCAccountList =  null;
		randomRCAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS,RFL_DB);
		emailID = (String) getValueFromQueryResult(randomRCAccountList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomRCAccountList, "AccountNumber");
		storeFrontHeirloomHomePage.createAndGetNSC4Url();
		loginToNSCApplication();
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickStatusLink();
		nscore4HomePage.changeStatusDD("Inactive");
		logger.info("Inactive status selected");
		nscore4HomePage.clickSaveStatusBtn();
		String afterStatus=nscore4HomePage.getStatus();
		assertTrue(afterStatus.equalsIgnoreCase("Inactive"),"account sttatus not changed succesfully");
		nscore4HomePage.clickLogoutLink();
		driver.get(driver.getURL());
		sponsorID = getRandomSponsorFromDB();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		//storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickClickHereLinkForPC();
		s_assert.assertTrue(driver.getCurrentUrl().contains("PCPerks"), "After clicking click here link for PC not navigated to PC Enrollment page.");
		storeFrontHeirloomHomePage.clickEnrollNowBtnForPCAndRC();
		storeFrontHeirloomHomePage.enterProfileDetailsForPCAndRC(firstName,lastName,emailID,password,phnNumber,gender);
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
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteEnrollmentBtn();
		storeFrontHeirloomHomePage.clickOKBtnOfJavaScriptPopUp();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPCEnrollmentCompletedSuccessfully(), "PC enrollment is not completed successfully");
		storeFrontHeirloomHomePage.createAndGetNSC4Url();
		loginToNSCApplication();
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.isSearchResultDisplayedWithExpectedValues(emailID, "Active", "Preferred Customer");
		nscore4HomePage.clickLogoutLink();
		s_assert.assertAll();

	}

}