package com.rf.test.website.storeFront.heirloom;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BizPWSTest extends RFHeirloomWebsiteBaseTest{

	private static final Logger logger = LogManager
			.getLogger(BizPWSTest.class.getName());

	/**
	 * Test Summary: Open BIZ , Login with PC, navigate to my account and verify the following links are present and accessbile:-
	 * Order History
	 * Edit Order
	 * Change my PC Perks Status
	 * PC Perks FAQs
	 * 
	 * Assertions:
	 * Visibility of the above links and if they are navigating to respective pages
	 */
	//QTest ID TC-397 My account options as PC customer from BIZ
	@Test(priority=1)
	public void testMyAccountOptionsAsPCCustomerFromBIZ_397(){
		if(driver.getDevice().equalsIgnoreCase("desktop")){
			String userEmailId = null;
			String orderHistory = "Order History";
			String editOrder = "Edit Order";
			String changeMyPCPerksStatus = "Change my PC Perks Status";
			String PCPerksFAQs = "PC Perks FAQs";
			openBIZSite();
			for(int i=1;i<=5;i++){
				if(shouldFetchDataFromExcelForTokeniation){
					userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
				}
				else{
					userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
					storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
					if(storeFrontHeirloomHomePage.isLoginFailed()==false){
						break;
					}
					else
						continue;
				}
			}
			storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(orderHistory), "Order History link is not present in order management");
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(editOrder), "Edit order link is not present in order management");
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(changeMyPCPerksStatus), "Change my pc perks status link is not present in order management");
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(PCPerksFAQs), "PC Perks FAQs link is not present in order management");
			storeFrontHeirloomPCUserPage.clickOrderManagementSublink(orderHistory);
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderNumberPresentAtOrderHistoryPage(), "Order number is not present at order history page");
			storeFrontHeirloomPCUserPage.navigateToBackPage();
			storeFrontHeirloomPCUserPage.clickOrderManagementSublink(editOrder);
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isEditOrderPagePresent(), "Edit Order button Not present on Edit Order page");
			storeFrontHeirloomPCUserPage.navigateToBackPage();
			storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isDelayOrCancelPCPerksLinkPresent(), "Delay or cancel PC perks link is not present");
			storeFrontHeirloomPCUserPage.navigateToBackPage();
			storeFrontHeirloomPCUserPage.clickOrderManagementSublink(PCPerksFAQs);
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isPCPerksFAQsLinkRedirectingToAppropriatePage("PC-Perks-FAQs.pdf"), "PC Perks FAQs link is not redirecting to appropriate page");
			s_assert.assertAll();
		}else {
			logger.info("This TC Executes only on Desktop device");
		}
	}

	//QTest ID TC-398 My account- As a consultant customer
	@Test(priority=2)
	public void testMyAccountAsAConsultantCustomer_398(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String enrollemntType = "Express";
		String editMyPWS = "edit my pws";
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName).selectRegimenForConsultant(regimen).clickNextBtnAfterSelectRegimen();
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName).clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Enrollment not completed successfully");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCheckMyPulseLinkDisplayed(),"Check my pulse link not displayed after consultant enrollment");
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin(editMyPWS);
		storeFrontHeirloomHomePage.clickEditMyPhotoLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUploadANewPhotoButtonPresent(),"Upload a new photo button is not present");
		s_assert.assertAll();
	}

	// QTest ID TC-399  PC User termination from BIZ
	@Test(priority=3)
	public void testPCUserTerminationFromBIZ_399(){
		String userEmailId = null;
		String changeMyPCPerksStatus = "Change my PC Perks Status";
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account").clickOrderManagementSublink(changeMyPCPerksStatus);
		assertTrue(storeFrontHeirloomPCUserPage.isDelayOrCancelPCPerksLinkPresent(), "Delay or cancel PC perks link is not present");
		storeFrontHeirloomPCUserPage.clickCancelPCPerksLink().clickChangedMyMindBtn();
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.getCurrentURL().contains("Dashboard"), "User is not redirecting to dashboard after clicked on changed my mind button");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		storeFrontHeirloomPCUserPage.clickCancelPCPerksLink();
		storeFrontHeirloomPCUserPage.selectReasonForPCTermination().enterMessageForPCTermination().clickSendEmailToCancelBtn();
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.getEmailConfirmationMsgAfterPCTermination().contains("you will be receiving a confirmation e-mail shortly"), "Expected email confirmation message is: you will be receiving a confirmation e-mail shortly, but actual on UI is: "+storeFrontHeirloomPCUserPage.getEmailConfirmationMsgAfterPCTermination());
		storeFrontHeirloomHomePage = storeFrontHeirloomPCUserPage.clickGoToRFHomeButton();
		storeFrontHeirloomHomePage.loginAsInvalidPCUser(userEmailId,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isInvalidLoginPresent(),"Terminated User is able to Login");
		s_assert.assertAll();
	}

	//QTest ID TC-400 PC Perks Delay - 30 days from BIZ
	@Test(priority=4)
	public void testPCPerksDelay30DaysFromBIZ_400(){
		String userEmailId = null;
		String changeMyPCPerksStatus = "Change my PC Perks Status";
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		storeFrontHeirloomPCUserPage.clickDelayPCPerksLink();
		storeFrontHeirloomPCUserPage.clickChangeAutohipDateBtn();
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.verifyConfirmationMessage(),"confirmation msg not present as expected");
		storeFrontHeirloomPCUserPage.clickBackToMyAccountBtn();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessageInOrders(),"Confirmation msg not present at orders page as expected");
		s_assert.assertAll();
	}

	//QTest ID TC-401 PC Perks Delay - 60 days
	@Test(priority=5)
	public void testPCPerksDelay60Days_401(){
		String userEmailId =null;
		String changeMyPCPerksStatus = "Change my PC Perks Status";
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		storeFrontHeirloomPCUserPage.clickDelayPCPerksLink();
		storeFrontHeirloomHomePage.selectSecondRadioButton();
		storeFrontHeirloomPCUserPage.clickChangeAutohipDateBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessage(),"confirmation msg not present as expected");
		storeFrontHeirloomHomePage.clickBackToMyAccountBtn();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessageInOrders(),"Confirmation msg not present at orders page as expected");
		s_assert.assertAll();
	}

	//QTest ID TC-402 PC Perks Template Update - Add/modify products from BIZ
	@Test(priority=6)
	public void testPCPerksTemplateUpdateFromBIZ_402(){
		String userEmailId = null;
		if(shouldFetchDataFromExcelForTokeniation){
			userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		openBIZSite();
		storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("my account");
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

	/**
	 * Test Summary: Open BIZ, Login with PC, goto My account and click on Edit order link, enter new shipping  address and verify
	 * Assertions:
	 * Newly added shipping address is present
	 * @throws Exception 
	 */
	//QTest ID TC-403  PC Perks Template - Shipping Address from BIZ
	@Test(priority=7)
	public void testPCPerksTemplateShippingAddressUpdateFromBIZ_403q(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String userEmailId = null;
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME+randomNumber;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickChangeLinkUnderShippingToOnPWS();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getShippingAddressName().contains(shippingProfileFirstName),"Shipping address name expected is "+shippingProfileFirstName+" While on UI is "+storeFrontHeirloomHomePage.getShippingAddressName());
		s_assert.assertAll();
	}

	//QTest ID TC-404 PC Perks Template -  Billing Profile from BIZ
	@Test(priority=8)
	public void testPCPerksTemplateBillingAddressUpdateFromBIZ_404(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String userEmailId = null;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumber;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickChangeBillingInformationLinkUnderBillingTabOnPWS();
		storeFrontHeirloomHomePage.enterBillingInfoForPWS(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		//s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderBillingDetailsUpdateMessage().contains("Order billing information successfully updated!"), "Expected order Billing update message is Replenishment Order billing information successfully updated! but actual on UI is: "+storeFrontHeirloomHomePage.getOrderBillingDetailsUpdateMessage());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		s_assert.assertAll();
	}

	//QTest ID TC-405 Registering the consultant using existing consultant's email id from BIZ
	@Test(priority=9)
	public void RegisterConsultantUsingExistingConsultantEmailIdFromBIZ_405() {
		String consultantEmailID = null;
		String enrollmentType = "Express";
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName).selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollmentType);
		//Enter SetUp Account Information and validate existing consultant?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExistingConsultantPopUp(consultantEmailID),"Existing Consultant Pop Up is not displayed!!");
		s_assert.assertAll();
	}

	//QTest ID TC-406 Registering the consultant using preffered customer's email id from BIZ
	@Test(priority=10)
	public void RegisterConsultantUsingExistingPrefferedCustomerEmailIdFromBIZ_406(){
		String userEmailId = null;
		String enrollmentType = "Express";
		if(shouldFetchDataFromExcelForTokeniation){
			userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollmentType);
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExistingPCPopUp(userEmailId),"Existing PC Pop Up is not displayed!!");
		s_assert.assertAll();
	}

	//QTest ID TC-408 Registering the consultant using existing CA consultant email id (Cross County Sponsor) from BIZ
	@Test(priority=12)
	public void RegisterConsultantUsingExistingCrossCountryConsultantEmailIdFromBIZ_408(){
		String dbIP2 = driver.getDBIP2();
		String kitName = "Big Business Launch Kit";
		String regimen = "Redefine";
		String enrollemntType = "Express";
		String countryID ="40";
		List<Map<String, Object>> randomConsultantList =  null;
		List<Map<String, Object>> randomEmailIdList =  null;
		String consultantEmailID = null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryID), RFO_DB, dbIP2);
		String accountID= String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
		//Get email id from account id
		randomEmailIdList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID), RFO_DB,dbIP2);
		consultantEmailID = (String) getValueFromQueryResult(randomEmailIdList, "EmailAddress");
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		//Enter SetUp Account Information and validate existing consultant?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExistingConsultantPopUpCrossCountry(consultantEmailID),"Existing Consultant Pop Up is not displayed!!");
		s_assert.assertAll();
	}

	//QTest ID TC-409 Registering the consultant using existing consultant's email id which terminated from BIZ
	@Test(priority=13)
	public void testRegisterUsingExistingCustomerEmailIdFromBIZ_409(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String enrollemntType = "Express";
		List<Map<String, Object>> randomConsultantList =  null;
		String consultantEmailID = null;
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getInActiveUserFromExcel("Consultant");	
			}
			else{
				randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_INACTIVE_CONSULTANT_EMAILID,RFL_DB);
				consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
			}
			storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
			if(storeFrontHeirloomHomePage.isInvalidLoginPresent()==true)
				break;
		}
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterEmailAddress(consultantEmailID);
		//storeFrontHeirloomHomePage.clickSetUpAccountNextButton();
		//s_assert.assertTrue(driver.getCurrentUrl().contains("SetupAccount"), "Set up account page is not present");
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, consultantEmailID, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickBillingInfoNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Enrollment is not successful using terminated consultant email id.");
		s_assert.assertAll();
	}

	//QTest ID TC-504 Consultant enrollment-Sign up
	@Test(priority=14)
	public void testConsultantEnrollmentSignUp_504q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String emailAddress = firstName+randomNum+TestConstantsRFL.EMAIL_SUFFIX;
		String enrollmentType = "Express";
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenForConsultant(regimen);
		storeFrontHeirloomHomePage.clickNextBtnAfterSelectRegimen();
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollmentType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName+randomNum, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		String bizPWS = storeFrontHeirloomHomePage.getBizPWSBeforeEnrollment();
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Congratulations message NOT appeared,Enrollment not completed successfully");
		s_assert.assertTrue(driver.getCurrentUrl().contains(bizPWS.split("\\//")[1]), "Expected biz PWS is: "+bizPWS.split("\\//")[1]+" but Actual on UI is: "+driver.getCurrentUrl());
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open BIZ, Login with consultant, add a product and place order
	 * Assertions:
	 * Thank you message after placing order
	 * Message "You will receive an email confirmation shortly" after placing order
	 */
	//QTest ID TC-511 Checkout as Consultant from BIZ
	@Test(priority=15)
	public void testCheckoutAsConsultantFromBIZ_511(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String consultantEmailID = null;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		openBIZSite();
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
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from biz site for Consultant user.");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}

	//*QTest ID TC-731 Registering the consultant using Retail customer's email id from BIZ
	@Test(priority=16)
	public void testRegisterConsultantUsingExistingRetailCustomerEmailIdFromBIZ_731(){
		String enrollemntType = "Express";
		String rcEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		openBIZSite();	
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExistingRCPopUp(rcEmailID),"Existing RC Pop Up is not displayed!!");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate Satisfaction Guarantee-link from biz
	 */ 
	//*QTest ID TC-734 Satisfaction Guarantee-link should be redirecting properly 
	@Test(priority=17)
	public void testSatisfactionGuaranteeLinkShouldBeRedirectionProperly_734(){
		openBIZSite();
		storeFrontHeirloomHomePage.clickSatisfactionGuaranteeLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSatisfactionGuaranteePagePresent(), "Satisfaction guarantee page is not present after clicked on privacy policy link");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("guarantee"), "Expected url having guarantee but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate Disclaimer-link from biz
	 */	
	//*QTest ID TC-735 Disclaimer-link should be redirecting properly
	@Test(priority=18)
	public void testDisclaimerLinkShouldBeRedirectedProperly_735(){
		openBIZSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateDisclaimerLinkInFooter(),"'Disclaimer Link' doesn't redirect to disclaimer page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate Contact us-link from biz
	 */	
	//*QTest ID TC-736 Contact us-link should be redirecting properly
	@Test(priority=19)
	public void testContactUsLinkShouldBeRedirectingProperly_736(){
		openBIZSite();
		storeFrontHeirloomHomePage.clickContactUsAtFooter();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("contact"), "URL does not contain Contact Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifylinkIsRedirectedToContactUsPage(),"link is not redirected to contact us page");
		s_assert.assertAll();
	}

	//*QTest ID TC-742 Enroll a consultant using existing Prefix From BIZ
	@Test(priority=20)
	public void testEnrollAConsultantUsingExistingPrefixFromBIZ_742(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(00, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String enrollemntType = "Express";
		String url=null;
		String prefix =null;
		List<Map<String, Object>> randomConsultant = null;
		randomConsultant = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_EXISTING_CONSULTANT_SITE_URL, RFL_DB);
		url = (String) getValueFromQueryResult(randomConsultant, "URL");
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		prefix = storeFrontHeirloomHomePage.getSplittedPrefixFromConsultantUrl(url);
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.enterUserPrefixInPrefixField(prefix);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForBiz().contains("unavailable"),"Expected message is unavailable for .biz but actual on UI is: "+storeFrontHeirloomHomePage.getPrefixMessageForBiz());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForCom().contains("unavailable"),"Expected message is unavailable for .com but actual on UI is: "+storeFrontHeirloomHomePage.getPrefixMessageForCom());
		//			s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForEmail().contains("unavailable"),"Expected message is unavailable for email but actual on UI is: "+storeFrontHeirloomHomePage.getPrefixMessageForEmail());
		s_assert.assertAll();
	}

	//*QTest ID TC-743 Enroll a consultant using special characters in the prefix field from BIZ
	@Test(priority=21)
	public void testEnrollConsultantUsingSpecialCharsInPrefixFieldFromBIZ_743(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String PWSSpclChars = TestConstantsRFL.PWS_SPCLCHARS;
		String enrollemntType = "Express";
		String phnNumber1 = "415";
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		//storeFrontHeirloomHomePage.clickSearchResults();
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.enterSpecialCharacterInWebSitePrefixField(PWSSpclChars);
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isValidationMessagePresentForPrefixField(),"WebSite Prefix Field accepts Special Character");
		s_assert.assertAll();
	}

	// QTest ID TC-775 Shop Skincare
	@Test(enabled=false)
	public void testShopSkincare_775() {
		//Duplicate test as 2358
	}

	/***
	 * Test Summary:- placed an adhoc order with consultant only buy only products
	 *  and validate orders details like subtotal & product name from biz site
	 */
	//*QTest ID TC-778 Shop Skincare-Consultants Only -buy only products (enhancements micro dermabrasion paste packets) 
	@Test(priority=22)
	public void consultantsOnlyBuyOnlyProducts_778(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openBIZSite();
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
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_PRODUCT);
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate product details before and after placing an order from biz
	 */	
	//*QTest ID TC-822 Checkout as Consultant
	@Test(priority=23) 
	public void testCheckoutAsConsultant_822(){
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
		openBIZSite();
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
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(), "User is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
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

	//QTest ID TC-828 Footer-Terms & Conditions link should redirecting to the appropriate page
	@Test(priority=24) 
	public void testFooterTermsAndConditionsLinkShouldRedirectingToTheAppropriatePage_828(){
		openBIZSite();
		storeFrontHeirloomHomePage.clickTermsAndConditionsLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("terms"), "Expected url having terms but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	//QTest ID TC-831 Footer- Privacy Policy link should be redirecting to the appropriate page
	@Test(priority=25) 
	public void testFooterPrivacyPolicyLinkShouldBeRedirectingToTheAppropriatePage_831(){
		openBIZSite();
		storeFrontHeirloomHomePage.clickPrivacyPolicyLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("privacy"), "Expected url having privacy but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	//QTest ID TC-860 My account options as RC customer
	@Test(priority=26) 
	public void testMyAccountOptionsAsRCCustomer_860(){
		String rcEmailID =null;
		String orderHistory = "Order History";
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
			}
			else{
				rcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
				storeFrontHeirloomHomePage.loginAsRCUser(rcEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomRCUserPage.clickHeaderLinkAfterLogin("my account");
		s_assert.assertTrue(storeFrontHeirloomRCUserPage.isOrderManagementSublinkPresent(orderHistory), "Order History link is not present in order management");
		storeFrontHeirloomRCUserPage.clickOrderManagementSublink(orderHistory);
		s_assert.assertTrue(storeFrontHeirloomRCUserPage.isOrderNumberPresentAtOrderHistoryPage(), "Order number is not present at order history page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the redefine category links and their sublinks(Results and FAQs) under shopskincare from biz site
	 *  
	 */
	//*QTest ID TC-879:Shop Skincare-menu navigation REDEFINE (PWS.biz)
	@Test(priority=27) 
	public void testShopSkincareMenuNavigationRedefineFromBiz_879(){
		String category = "REDEFINE";
		String subLinkResults = "Results";
		String subLinkFAQ = "FAQS";
		String currentURL = null;
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCategoryNamePresent(category) && currentURL.contains(category),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category, subLinkResults);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(category),"user is not on results page");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category,subLinkFAQ);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is CommonQuestions".toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(category),"user is not on FAQs page");
		s_assert.assertAll();
	}	

	/***
	 * Test Summary:- validate the REVERSE category links and their sublinks(Results and FAQs) under shopskincare from biz site
	 *  
	 */
	//*QTest ID TC-880:Shop Skincare-menu navigation REVERSE (PWS.biz)
	@Test(priority=28) 
	public void testShopSkincareMenuNavigationReverseFromBiz_880(){
		String category = "REVERSE";
		String subLinkResults = "Results";
		String subLinkFAQ = "FAQ";
		String currentURL = null;
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCategoryNamePresent(category) && currentURL.contains(category),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category, subLinkResults);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(category),"user is not on results page");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category,subLinkFAQ);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is CommonQuestions".toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(category),"user is not on FAQs page");
		s_assert.assertAll();
	}


	//*QTest ID TC-881:Shop Skincare-menu navigation UNBLEMISH (PWS.biz)
	@Test(priority=29) 
	public void testShopSkincareMenuNavigationUnblemishFromBiz_881(){
		String category = "UNBLEMISH";
		String subLinkResults = "Results";
		String subLinkFAQ = "FAQ";
		String currentURL = null;
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL().toLowerCase();
		s_assert.assertTrue(currentURL.contains(category.toLowerCase()),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category, subLinkResults);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(category),"user is not on results page");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category,subLinkFAQ);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is CommonQuestions".toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(category),"user is not on FAQs page");
		s_assert.assertAll();
	} 

	/***
	 * Test Summary:- validate the SOOTHE category links and their sublinks(Results and FAQs) under shopskincare from biz site
	 *  
	 */
	//*QTest ID TC-882:Shop Skincare-menu navigation SOOTHE (PWS.biz)
	@Test(priority=30) 
	public void testShopSkincareMenuNavigationSootheFromBiz_882(){
		String category = "SOOTHE";
		String subLinkResults = "Results";
		String subLinkFAQ = "FAQ";
		String currentURL = null;
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL().toLowerCase();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isExactCategoryNamePresent(category) && currentURL.contains(category.toLowerCase()),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category, subLinkResults);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(category),"user is not on results page");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category,subLinkFAQ);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is CommonQuestions".toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(category),"user is not on FAQs page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the ENHANCEMENTS category links under shopskincare from biz site
	 *  
	 */
	//*QTest ID TC-883:Shop Skincare-menu navigation ENHANCEMENTS (PWS.biz)
	@Test(priority=31)
	public void testShopSkincareMenuNavigationEnhancementsFromBiz_883(){
		String category = "ENHANCEMENTS";
		String currentURL = null;
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL().toLowerCase();
		s_assert.assertTrue(currentURL.contains(category.toLowerCase()),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the ESSENTIALS category links under shopskincare from biz site
	 *  
	 */
	//*QTest ID TC-884:Shop Skincare-menu navigation ESSENTIALS (PWS.biz)
	@Test(priority=32)
	public void testShopSkincareMenuNavigationEssentialsFromBiz_884(){
		String category = "ESSENTIALS";
		String currentURL = null;
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL().toLowerCase();
		s_assert.assertTrue(currentURL.contains(category.toLowerCase()),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate BIZ: Shop Skincare> Featured
	 * Assertions:
	 * Page URL should contain 'Promotions'
	 * 'FEATURED' should be visible on page Header
	 */	
	//*QTest ID TC-885 BIZ: Shop Skincare> Featured
	@Test(priority=33)
	public void testShopSkincareFeaturedFromBizPWS_885(){
		String promotions="Promotions";
		String currentURL=null;
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(TestConstantsRFL.REGIMEN_NAME_FEATURED);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(promotions),"Expected URL should contain"+promotions+" But actual on UI is: "+currentURL);
		//s_assert.assertTrue(storeFrontHeirloomHomePage.isUserRedirectedToFeaturedPage()," User is not redirected to Promotions Act Page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- placed an adhoc order with consultant only buy event support pack
	 *  and validate orders details like subtotal & product name from biz site
	 */
	//*QTest ID TC-903 Consultants Only -buy event support pack 
	@Test(priority=34)
	public void consultantsOnlyBuyEventSupportPack_903(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openBIZSite();
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
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyEventSupportProduct();
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- placed an adhoc order with consultant only buy product promotion
	 * 
	 */
	//*QTest ID TC-905 Consultants Only -buy product promotion 
	@Test(priority=35)
	public void consultantsOnlyBuyProductPromotion_905(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openBIZSite();
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
		s_assert.assertAll();
	}	

	/***
	 * Test Summary:- validate the sublink of About R+F from biz site
	 */
	//*QTest ID TC-907 Company-Links should be present (Execute team, our history, carrers, PFC foundation, press room, contact us) 
	@Test(priority=36)
	public void testVerfiyCompanyLinks_907(){
		String whoWeAre = "WHO WE ARE";
		String meetTheDoctors = "MEET THE DOCTORS";
		String executiveTeam = "EXECUTIVE TEAM";
		String givingBack = "GIVING BACK";
		String currentURL = null;
		openBIZSite();
		//verify company careers Link?
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Company");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("Company"),"Company link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(whoWeAre),whoWeAre+" link text is not present");

		// assert meet the doctors link
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Meet the Doctors");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("MeetTheDoctors"),"MeetTheDoctors link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(meetTheDoctors),meetTheDoctors+" link text is not present");

		// assert Executive Team link
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Executive Team");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("ExecutiveTeam"),"Executive Team link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(executiveTeam),executiveTeam+" link text is not present");

		// assert Giving Back link
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Giving Back");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("GivingBack"),"Giving Back link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(givingBack),givingBack+" link text is not present");
		s_assert.assertAll();
	}

	//*QTest ID TC-913 Company-Press room link
	@Test(priority=37)
	public void testCompanyPressRoomLink_913(){
		String pageName="PRESS ROOM";
		String expectedURL="PressRoom";
		openBIZSite();
		storeFrontHeirloomHomePage.clickCompanyPressRoomLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderVisible(pageName),"User is not redirected to PressRoom Page");
		s_assert.assertAll();
	}

	//*QTest ID TC-916 Company-Carrers link
	@Test(priority=38)
	public void testCompanyCarrersLinkBiz_916(){
		String pageName="CAREERS";
		String expectedURL="careers";
		openBIZSite();
		storeFrontHeirloomHomePage.clickCareersLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderVisible(pageName),"User is not redirected to CAREERS Page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- Open Biz PWS, Login with PC User, and verify
	 * Assertions:
	 * Verify sponsor name on the top of the page
	 */ 
	//QTest ID TC-2152 Sponsor infor_PWS.biz
	@Test(priority=39)
	public void testSponsorInfo_MyAccountFromBizPWS_2152(){
		String userEmailId = null;
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
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

	/**
	 * Test Summary: Open BIZ, Enroll Consultant User, Enroll them into CRP and Pulse and Verify
	 * Assertions:
	 * User is Enrolled Successfully
	 */
	//QTest ID TC-2347 Consultant Standard Enrollment (Biz PWS)
	@Test(priority=40)
	public void testConsultantStandardEnrollmentBizPWS_2347(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(11, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollmentType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear, CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.clickBillingInfoNextBtn();
		storeFrontHeirloomHomePage.clickYesSubscribeToPulseNow();
		storeFrontHeirloomHomePage.clickYesEnrollInCRPNow();
		storeFrontHeirloomHomePage.clickAutoShipOptionsNextBtn();
		storeFrontHeirloomHomePage.selectProductToAddToCart();
		storeFrontHeirloomHomePage.setQuantityOfProductInCRPCart("10","1");
		//		if(Integer.parseInt(storeFrontHeirloomHomePage.getTotalSV())<80){
		//			storeFrontHeirloomHomePage.clickAddMoreButton();
		//			storeFrontHeirloomHomePage.selectSecondProductToAddToCart();
		//			storeFrontHeirloomHomePage.setQuantityOfProductInCRPCart("10","2");
		//		}
		storeFrontHeirloomHomePage.clickYourCRPOrderPopUpNextBtn();
		storeFrontHeirloomHomePage.clickChargeMyCardAndEnrollMeWithOutConfirmAutoship();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForConsultant(), "Error message is not present for consultant for terms & conditions");
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Congratulations Message not appeared");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open BIZ, Enroll Consultant User using Express Enrollment, and Verify
	 * Assertions:
	 * User is Enrolled Successfully
	 * User is navigating to BizPWS after Enrollment
	 */
	//QTest ID TC-2348 Consultant enrollment-Sign up (Biz PWS)
	@Test(priority=41)
	public void testConsultantEnrollmentSignUpOnBizPWS_2348(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(10, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String kitName = TestConstantsRFL.CONSULTANT_RFX_EXPRESS_BUSINESS_KIT;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String enrollemntType = TestConstantsRFL.EXPRESS_ENROLLMENT;
		String bizPWS=null;
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenForConsultant(regimen);
		storeFrontHeirloomHomePage.clickNextBtnAfterSelectRegimen();
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		bizPWS = storeFrontHeirloomHomePage.getBizPWSBeforeEnrollment();
		storeFrontHeirloomHomePage.clickCompleteAccountNextBtn();
		storeFrontHeirloomHomePage.clickTermsAndConditions();
		storeFrontHeirloomHomePage.chargeMyCardAndEnrollMe();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCongratulationsMessageAppeared(),"Enrollment not completed successfully");
		s_assert.assertTrue(driver.getCurrentUrl().contains(bizPWS.split("\\//")[1]), "Expected biz PWS is: "+bizPWS.split("\\//")[1]+" but Actual on UI is: "+driver.getCurrentUrl());
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open BIZ, initiate RC enrollment
	 * Assertions:
	 * RC user enrolled successfully
	 */
	//QTest ID TC-2350  RC Enrollment from BIZ site
	@Test(priority=42)
	public void testRCEnrollmentFromBIZ_2350(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String emailAddress = firstName+randomNum+TestConstantsRFL.EMAIL_SUFFIX;
		String addressName = "Home";
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickMyShoppingBagLink();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickClickHereLinkForRC();
		s_assert.assertTrue(driver.getCurrentUrl().contains("Retail"), "After clicking click here link for RC not navigated to RC Enrollment page.");
		storeFrontHeirloomHomePage.enterProfileDetailsForPCAndRC(firstName,lastName+randomNum,emailAddress,password,phnNumber,gender);
		storeFrontHeirloomHomePage.clickCreateMyAccountBtnOnCreateRetailAccountPage();
		storeFrontHeirloomHomePage.enterShippingProfileDetails(addressName, shippingProfileFirstName,shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName, billingProfileLastName+randomNum, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Enrollment is not completed successfully");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open BIZ, initiate PC enrollment
	 * Assertions:
	 * PC user enrolled successfully
	 */
	//* QTest ID TC-2351 PC Enrollment
	@Test(priority=43)
	public void testPCEnrollment_2351() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String emailAddress = firstName+randomNum+TestConstantsRFL.EMAIL_SUFFIX;
		String addressName = "Home";
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickClickHereLinkForPC();
		s_assert.assertTrue(driver.getCurrentUrl().contains("PCProgram"),"After clicking click here link for PC not navigated to PC Enrollment page.");
		storeFrontHeirloomHomePage.clickEnrollNowBtnForPCAndRC();
		storeFrontHeirloomHomePage.enterProfileDetailsForPCAndRC(firstName, lastName+randomNum, emailAddress, password,phnNumber, gender);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForPCAndRC(),"Terms and candition error message not present for PC User.");
		storeFrontHeirloomHomePage.checkTermsAndConditionChkBoxForPCAndRC();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickContinueBtnOnAutoshipSetupPageForPC();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetails(addressName, shippingProfileFirstName,shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName,billingProfileLastName+randomNum, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteEnrollmentBtn();
		storeFrontHeirloomHomePage.clickOKBtnOfJavaScriptPopUp();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPCEnrollmentCompletedSuccessfully(),"PC enrollment is not completed successfully");
		s_assert.assertAll(); 
	}

	@Test(priority=44)
	public void testPaymentErrorMessageWhilePlacingAnAdhocOrderThroughPCFromBIZ_2353(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String cardNumber = TestConstantsRFL.INVALID_CARD_NUMBER;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String errorMessageFromUI = null;
		String errorMessage = TestConstantsRFL.ERROR_MSG_FOR_INVALID_CC;
		String pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		System.out.println(pcEmailID);
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		//storeFrontHeirloomHomePage.selectRegimen(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(pcEmailID, password);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
	/*	storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();*/
		errorMessageFromUI = storeFrontHeirloomHomePage.getErrorMessageForInvalidCreditCard();
		s_assert.assertTrue(errorMessageFromUI.contains(errorMessage), "Expected error message is "+errorMessage+"actual on UI is "+errorMessageFromUI);
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- Open biz url, initiate consultant enrollment, add billing profile with INVALID credit card and complete enrollment
	 * Assertions:
	 * Error message should be displayed for INVALID credit card after complete the enrollment
	 */	
	//QTest ID TC-2354 Payment error message - Enrollment
	@Test(priority=45)
	public void testPaymentErrorMessageFromBiz_2354(){
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
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
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
	 * Test Summary: Open BIZ, Login with RC and place an adhoc order
	 * Assertions:
	 * Order is placed successfully
	 */
	// QTest ID TC-2358 Adhoc Order- RC
	@Test(priority=46)
	public void testAdhocOrderThroughRCFromBIZ_2358() {
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumber;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String rcEmailID = null;
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
			}
			else{
				rcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
				storeFrontHeirloomHomePage.loginAsRCUser(rcEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedInOnCorpSite(),"RC user is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName)||storeFrontHeirloomHomePage.getBillingAddress().contains(addressLine1), "Existing billing profile is not selected for new order.");
		storeFrontHeirloomHomePage.chooseShippingMethod("FedEx Grnd");
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}

	//*QTest ID TC-2359 Adhoc Order - Consultant Only Products from Biz site
	//>100 QV product is not avaialbe
	@Test(priority=47)
	public void testAdhocOrderConsultantsOnlyProductsBiz_2359(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openBIZSite();
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
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_PRODUCT);
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.chooseShippingMethod("FedEx Grnd");
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertAll();
	}

	// QTest ID TC-2360 Add Product to the Cart - Checkout as PC from BIZ
	@Test(priority=48)
	public void testAddProductToTheCartCheckoutAsPCFromBIZ_2360() {
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String pcEmailID = null;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumber;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(pcEmailID, password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "PC user not logged in successfully");
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName)||storeFrontHeirloomHomePage.getBillingAddress().contains(addressLine1), "Existing billing profile is not selected for new order.");
		storeFrontHeirloomHomePage.chooseShippingMethod("FedEx Grnd");
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open BIZ, Login with consultant
	 * Assertions:
	 * User is able to logged in successfully
	 */
	//QTest ID TC-2361 Log in as an existing consultant from BIZ
	@Test(priority=49)
	public void testLoginAsAnExistingConsultantFromBIZ_2361(){
		String consultantEmailID = null;
		openBIZSite();
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
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"Consultant user is not logged in successfully");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open BIZ, Login with PC
	 * Assertions:
	 * User is able to logged in successfully
	 */
	//QTest ID TC-2362: Log in as valid PC customer from BIZ
	@Test(priority=50)
	public void testLoginAsExistingPCFromBIZ_2362(){
		String userEmailId = null;
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
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

	//QTest ID TC-2363: Log in with a valid RC customer from BIZ
	@Test(priority=51)
	public void testLoginAsExistingRCFromBIZ_2363(){
		String rcEmailID = null;
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
			}
			else{
				rcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
				storeFrontHeirloomHomePage.loginAsRCUser(rcEmailID,password);
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

	//QTest ID TC-2364 log out with a valid user on Biz PWS
	@Test(priority=52)
	public void testLogoutWithAValidUserOnBizPWS_2364(){
		String consultantEmailID = null;
		openBIZSite();
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
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		logout();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isForgotPasswordLinkPresent(),"User is not logout successfully");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate RF Connection Link From Biz PWS
	 * Assertions:
	 * Page URL should contain 'rfconnection'
	 * RF Connection should be visible on page Header
	 */	
	//*QTest ID TC-2366 RF Connection From Biz PWS Site
	@Test(priority=53)
	public void testRFConnectionFromBizPWS_2366(){
		String rfConnection="RF Connection";
		String parentWindow=null;
		openBIZSite();
		parentWindow=storeFrontHeirloomHomePage.getParentWindowHandle();
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(rfConnection);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserRedirectedToExpectedPage(parentWindow, "rfconnection"),"Expected URL does not contain 'rfconnection'");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRFConnectionPageHeaderVisible()," User is not redirected to RF Connection Page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate DERM RF Link From Biz PWS Site
	 * Assertions:
	 * Page URL should contain 'dermrf'
	 * DERM RF should be visible on page Header
	 */	
	//*QTest ID TC-2367 Derm Connection from Biz PWS Site
	@Test(priority=54)
	public void testDermConnectionFromBizPWS_2367(){
		String dermRF="Derm RF";
		String parentWindow=null;
		openBIZSite();
		parentWindow=storeFrontHeirloomHomePage.getParentWindowHandle();
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(dermRF);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserRedirectedToExpectedPage(parentWindow, "dermrf"),"Expected URL does not contain 'dermrf'");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isDERMRFPageHeaderVisible()," User is not redirected to DERM RF Page");
		s_assert.assertAll();
	}	

	/***
	 * Test Summary:- validate Shipping Link From Biz PWS
	 * Assertions:
	 * Page URL should contain 'Shipping'
	 * Shipping should be visible on page Header
	 */	
	//*QTest ID TC-2370 Shipping from Biz PWS
	@Test(priority=55)
	public void testShippingFromBizPWS_2370(){
		String shipping="Shipping";
		String currentURL=null;
		openBIZSite();
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(shipping);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("Shipping"),"Expected URL should contain 'shipping' But actual on UI is: "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isShippingPageHeaderVisible()," User is not redirected to Shipping Page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate California Supply Chains Act Link From Biz PWS
	 * Assertions:
	 * Page URL should contain 'california-supply-chains-act'
	 * 'CALIFORNIA TRANSPARENCY IN SUPPLY CHAINS ACT' should be visible on page Header
	 */	
	//*QTest ID TC-2375 California Supply Chains Act From Biz PWS
	@Test(priority=56)
	public void testCaliforniaSupplyChainsActFromBizPWS_2375(){
		String califSupplyChainsAct="California Supply Chains Act";
		String currentURL=null;
		openBIZSite();
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(califSupplyChainsAct);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("california-supply-chains-act"),"Expected URL should contain 'california-supply-chains-act' But actual on UI is: "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCaliforniaSupplyChainsActPageHeaderVisible()," User is not redirected to 'California Supply Chains' Act Page");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open BIZ, click forgot password and enter email to recover password
	 * Assertions:
	 * Change password message popup
	 * Change password email has been sent successfully
	 */
	//QTest ID TC-2376 Forgot Password from BIZ
	@Test(priority=57)
	public void testForgotPasswordFromBIZ_2376(){
		String consultantEmailID = null;
		openBIZSite();

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		//click 'forgot password' on biz home page
		storeFrontHeirloomHomePage.clickForgotPasswordLinkOnBizHomePage();
		//verify a message prompt to change the password displayed?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateChangePasswordMessagePrompt(),"Message not prompted for 'change password'");
		//verify user is able to change the password and email is being sent?
		storeFrontHeirloomHomePage.enterEmailAddressToRecoverPassword(consultantEmailID);
		storeFrontHeirloomHomePage.clickSendEmailButton();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validatePasswordChangeAndEmailSent(),"resetting your password mail is not displayed!!");
		s_assert.assertAll();
	}

	@Test(priority=58)
	public void testAdhocOrderWithNewlyCreatedShippingAddressFromBIZ_2480(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber1 = CommonUtils.getRandomNum(10000, 1000000);
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME+randomNumber1;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		String firstName = TestConstantsRFL.FIRST_NAME;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openBIZSite();
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
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		//storeFrontHeirloomHomePage.selectRegimen(regimen);
		System.out.print("1");
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		System.out.print("2");
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		System.out.print("3");
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
		s_assert.assertTrue(storeFrontHeirloomHomePage.getShippingAddressName().contains(shippingProfileFirstName),"Shipping address name expected is "+shippingProfileFirstName+" While on UI is "+storeFrontHeirloomHomePage.getShippingAddressName());
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open BIZ  PWS, login with PC User, place adhoc order with new billing address
	 * Assertions:
	 * Newly created billing profile at order confirmation page 
	 */
	//QTest ID TC-2481 Adhoc Order with Newly created billing address
	@Test(priority=59)
	public void testAdhocOrderWithNewlyCreatedBillingAddressFrom_2481(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String nameOnCard = firstName;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String userEmailId = null;
		openBIZSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomPCUserPage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickCheckoutBtn().clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from biz PWS site.");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}

	//*QTest ID TC-2378 BIZ > Donation order 
	@Test(priority=60)
	public void testDonationOrderFromBiz_2378(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openBIZSite();
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
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		//storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		//storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_EVENT_SUPPORT);
		storeFrontHeirloomHomePage.clickDonationProduct();
		//storeFrontHeirloomHomePage.addDonationProductToBag();
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


	//QTest ID TC-766 Register the consultant using existing consultant's SSN from BIZ
	@Test(enabled=false)//check this test case
	public void testRegisterTheConsultantUsingExistingConsultantSSNFromBIZ_766(){
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
		String addressLine1 =  TestConstantsRFL.ADDRESS_LINE1;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String expMonth = TestConstantsRFL.EXP_MONTH;
		String expYear = TestConstantsRFL.EXP_YEAR;
		String kitName = TestConstantsRFL.CONSULTANT_RFX_EXPRESS_BUSINESS_KIT;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		String enrollemntType = "Express";
		String phnNumber1 = "415";
		String phnNumber2 = "780";
		String phnNumber3 = "9099";
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
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
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
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
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		/*storeFrontLegacyHomePage.clickEnrollNowBtnAtWhyRFPage();*/
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


	//RC -  Shipping Address
	@Test(enabled=false)
	public void testRCUserShippingAddressUpdate(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME+randomNumber;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		String addressLine1 =  TestConstantsRFL.ADDRESS_LINE1;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String phnNumber = TestConstantsRFL.NEW_ADDRESS_PHONE_NUMBER_US;

		List<Map<String, Object>> randomRCList =  null;
		String rcEmailID = null;
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
		storeFrontHeirloomHomePage.clickChangeShippingAddressBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getShippingAddressName().contains(shippingProfileFirstName),"Shipping address name expected is "+shippingProfileFirstName+" While on UI is "+storeFrontHeirloomHomePage.getShippingAddressName());
		s_assert.assertAll();
	}

	//RC - Billing Profile
	@Test(enabled=false)
	public void testRCUserBillingProfileUpdate(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String expMonth = TestConstantsRFL.EXP_MONTH;
		String expYear = TestConstantsRFL.EXP_YEAR;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String phnNumber = TestConstantsRFL.NEW_ADDRESS_PHONE_NUMBER_US;
		String addressLine1 =  TestConstantsRFL.ADDRESS_LINE1;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		List<Map<String, Object>> randomRCList =  null;
		String rcEmailID = null;
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
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		s_assert.assertAll();
	}

	//QTest ID TC-911 PWS.biz R+FInTheNews
	@Test(enabled=false)
	public void testPWSbizRPlusFInTheNews_911(){
		String expectedURL = "Company/PR";
		openBIZSite();
		storeFrontHeirloomHomePage.clickCompanyPressRoomLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- placed an adhoc order with consultant only buy only products
	 *  and validate orders details like subtotal & product name from biz site
	 */
	//*QTest ID TC-769 Enroll a consultant using existing CA Prefix (Cross Country Sponsor) 
	@Test(enabled=false)//not available in qtest Automation, need to remove
	public void testEnrollAConsultantUsingExistingCAPrefix_769(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(00, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String enrollemntType = "Express";
		List<Map<String, Object>> randomConsultant = null;
		randomConsultant = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_EXISTING_CONSULTANT_SITE_URL, RFL_DB);
		String url = (String) getValueFromQueryResult(randomConsultant, "URL");
		openBIZSite();
		String prefix = storeFrontHeirloomHomePage.getSplittedPrefixFromConsultantUrl(url);
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
		storeFrontHeirloomHomePage.selectEnrollmentKit(kitName);
		storeFrontHeirloomHomePage.selectRegimenAndClickNext(regimen);
		storeFrontHeirloomHomePage.selectEnrollmentType(enrollemntType);
		storeFrontHeirloomHomePage.enterSetUpAccountInformation(firstName, lastName, emailAddress, password, addressLine1, postalCode, phnNumber1, phnNumber2, phnNumber3);
		storeFrontHeirloomHomePage.clickSetUpAccountNextBtn();
		storeFrontHeirloomHomePage.enterBillingInformation(cardNumber, nameOnCard, expMonth, expYear,CVV);
		storeFrontHeirloomHomePage.enterAccountInformation(ssnRandomNum1, ssnRandomNum2, ssnRandomNum3, firstName);
		storeFrontHeirloomHomePage.enterUserPrefixInPrefixField(prefix);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForBiz().contains("unavailable"),"Expected message is unavailable for .biz but actual on UI is: "+storeFrontHeirloomHomePage.getPrefixMessageForBiz());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getPrefixMessageForCom().contains("unavailable"),"Expected message is unavailable for .com but actual on UI is: "+storeFrontHeirloomHomePage.getPrefixMessageForCom());
		s_assert.assertFalse(storeFrontHeirloomHomePage.getPrefixMessageForEmail().contains("unavailable"),"Expected message is unavailable for email but actual on UI is: "+storeFrontHeirloomHomePage.getPrefixMessageForEmail());
		s_assert.assertAll();
	}

	//*QTest ID TC-769 Enroll a consultant using existing CA Prefix (Cross Country Sponsor)
	@Test(enabled=false)//Not in qTest for automation , need to remove 
	public void testEnrollConsultantUsingExistingCAPrefixCrossCountry_769(){
		String dbIP2 = driver.getDBIP2();
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int ssnRandomNum1 = CommonUtils.getRandomNum(100, 999);
		int ssnRandomNum2 = CommonUtils.getRandomNum(00, 99);
		int ssnRandomNum3 = CommonUtils.getRandomNum(1000, 9999);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String enrollemntType = "Express";
		String countryID ="40";
		String country = "ca";
		List<Map<String, Object>> randomConsultantList =  null;
		List<Map<String, Object>> sitePrefixList=null;
		String sitePrefix=null;

		//Fetch cross country Email address from database.
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,driver.getEnvironment()+".biz",country,countryID), RFO_DB, dbIP2);
		String accountID= String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));

		sitePrefixList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_ALREADY_EXISTING_SITE_PREFIX_RFO,countryID,accountID),RFO_DB,dbIP2);
		sitePrefix=String.valueOf(getValueFromQueryResult(sitePrefixList, "SitePrefix"));
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverBeAConsultantAndClickLink("Enroll Now");
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

	/**
	 * Test Summary: Open biz, Click on Country Toggle Select AUS and verify
	 * Assertions:
	 * AUS Should be present in Country Toggle
	 * User should redirected to AUS Country after clicking on 'AUS' from country toggle
	 */
	//QTest ID TC-926 PWS.biz> Verify the User is redirected to the Corporate Australian Home Page
	@Test(enabled=false)//not in qTest Automation, need to remove
	public void testVerifyTheUserIsRedirectedToTheCorporateAustralianHomePageBizPWS_926(){
		String countryName="AUS";
		String url="rodanandfields.com.au";
		String currentUrl=null;
		openBIZSite();
		storeFrontHeirloomHomePage.clickCountryToggle();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCountryPresentInCountryToggle(countryName)," Expected country: "+countryName+" not present in country toggle");
		storeFrontHeirloomHomePage.selectCountry(countryName);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(url),"User is not redirected to expected country:"+countryName+" Url should contains: "+url+" But actual on UI is: "+currentUrl);
		s_assert.assertAll();
	}


	//QTest ID TC-2427 Carousel should be continuous
	@Test(enabled=false)//should be manual
	public void testCarouselShouldBeContinuous_2427(){
		String category = "REVERSE";
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.isYouMayAlsoFindTheseProductsSectionVisible();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCarouselIsContinous(),"Carousel is not continous");

		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		storeFrontHeirloomHomePage.isYouMayAlsoFindTheseProductsSectionVisibleBiz();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCarouselIsContinousBiz(),"Carousel is not continous");

		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		storeFrontHeirloomHomePage.isYouMayAlsoFindTheseProductsSectionVisibleBiz();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCarouselIsContinousBiz(),"Carousel is not continous");

		s_assert.assertAll();
	}


}