package com.rf.test.website.storeFront.hybris.standardEnrollment.corp;

import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.test.website.RFWebsiteBaseTest;

public class PersonalResultsKitTest extends RFWebsiteBaseTest{

	//Hybris Project-50 CORP:Standard Enroll USD 395 Personal Results Kit, Personal Regimen REVERSE REGIMEN
	@Test
	public void testStandardEnrollmentPersonalKitReverseRegimen_50() throws InterruptedException{
		//-------------------------local variables---------------------------------------------------------
		String randomNum = CommonUtils.getCurrentTimeStamp();
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String enrollmentType = TestConstants.STANDARD_ENROLLMENT;
		String regimenName = TestConstants.REGIMEN_NAME_REVERSE;
		String kitName = TestConstants.KIT_NAME_PERSONAL;
		//-------------------------------------------------------------------------------------------------

		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		//TODO Workaround for "Non secure connection" warnings. Remove once solved
		if (storeFrontHomePage.isMobileIOS()) {
			storeFrontHomePage.searchCID("177044");
			} else {
			storeFrontHomePage.searchCID(sponsor);
			}
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, consultantFirstName, consultantLastName+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(consultantFirstName);
		storeFrontHomePage.selectNewBillingCardExpirationDate();
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(consultantFirstName);
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		
		//TODO Workaround for billing issue with iOS
		if (storeFrontHomePage.isMobileIOS()) {
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		}
		
		storeFrontHomePage.checkPulseAndCRPEnrollment();
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		storeFrontHomePage.selectProductForCRPAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.clickOnNextBtnAfterAddingProductAndQty();
		s_assert.assertTrue(storeFrontHomePage.isTheTermsAndConditionsCheckBoxDisplayed(), "Terms and Conditions checkbox is not visible");
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertAll();		
	}


	//QTest ID TC-461 CORP> Standard > RFx Biz Kit > REDEFINE REGIMEN (CRP:Yes (SHIP THIS MONTH), Pulse:No)
	@Test
	public void testStandardEnrollmentPersonalKitRedefineRegimenCRPYesPulseNo_461q() throws InterruptedException{
		//-------------------------local variables---------------------------------------------------------
		String randomNum = CommonUtils.getCurrentTimeStamp();
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String enrollmentType = TestConstants.STANDARD_ENROLLMENT;
		String regimenName = TestConstants.REGIMEN_NAME_REVERSE;
		String kitName = TestConstants.KIT_NAME_PERSONAL;
		//-------------------------------------------------------------------------------------------------

		storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();
		storeFrontHomePage.searchCID(sponsor);
		storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
		storeFrontHomePage.enterUserInformationForEnrollment(kitName, regimenName, enrollmentType, consultantFirstName, consultantLastName+randomNum, password, addressLine1, city,state, postalCode, phoneNumber);
		storeFrontHomePage.clickNextButton();
		storeFrontHomePage.enterCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNameOnCard(consultantFirstName);
		storeFrontHomePage.selectNewBillingCardExpirationDate();
		storeFrontHomePage.enterSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
		storeFrontHomePage.enterNameAsItAppearsOnCard(consultantFirstName);
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		storeFrontHomePage.checkPulseAndCRPEnrollment();
		storeFrontHomePage.uncheckPulseCheckBox();
		storeFrontHomePage.clickNextButtonWithoutPopUp();
		storeFrontHomePage.selectProductForCRPAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontHomePage.clickOnNextBtnAfterAddingProductAndQty();
		s_assert.assertTrue(storeFrontHomePage.isTheTermsAndConditionsCheckBoxDisplayed(), "Terms and Conditions checkbox is not visible");
		storeFrontHomePage.selectAllTermsAndConditionsChkBox();
		storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
		storeFrontHomePage.clickOnConfirmAutomaticPayment();
		s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
		s_assert.assertAll();		
	}

}
