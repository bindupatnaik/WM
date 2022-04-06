package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class InactiveCRPAndPulseMyAccountTest extends RFHeirloomWebsiteBaseTest {

	/**
	 * Jira Story Id: MAIN-7403
	 * qTest Id: TC-2825
	 */
	@Test(alwaysRun=true,description = "Delete existing and  enter invalid email address on account details  page")
	public void testDeleteExistingAndEnterInvalidEmailAddressOnAccountDetailsPage_2825() {
		String consultantEmailID = null;
		String accountDetailsLink="Account Details";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;
		RFL_DB = driver.getDBNameRFL();
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(accountDetailsLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("my-account"), "User is not redirected to Account Details page");		
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountEmailAddressTxtFieldDisplayed());
		storeFrontHeirloomConsultantPage.clearMyAccountEmailAddressTxtField().enterTextInMyAccountEmailAddressTxtField("QWERTY").clickOnSaveButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountPleaseEnterAValidEmailAddressErrorLabelDisplayed());
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-7403
	 * qTest Id: TC-2824
	 */
	@Test(alwaysRun=true,description = "Delete existing email address on account details  page")
	public void testDeleteExistingEmailAddressOnAccountDetailsPage_2824() {
		String consultantEmailID = null;
		String accountDetailsLink="Account Details";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;
		RFL_DB = driver.getDBNameRFL();
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(accountDetailsLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("my-account"), "User is not redirected to Account Details page");		
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountEmailAddressTxtFieldDisplayed());
		storeFrontHeirloomConsultantPage.clearMyAccountEmailAddressTxtField().clickOnSaveButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountEmailIsRequiredErrorLabelDisplayed());
		s_assert.assertAll();		
	}
	

/**
	 * Jira Story Id: MAIN-7994
	 * qTest Id: TC-2944
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "Enroll in CRP navigation from Manage CRP & PULSE page")
	public void testEnrollInCRPNavigationFromManageCRPandPULSEPage_2944() {
		String currentUrl=null;
		String consultantEmailID = null;
		RFL_DB = driver.getDBNameRFL();
		List<Map<String, Object>> randomConsultantList =  null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		storeFrontHeirloomConsultantPage.clickEnrollInCRPButton();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		assertTrue(currentUrl.contains("Replenishment/ConsultantEnroll/InitialOrder.aspx"), "User is not redirected to CRP Enrollment page after clicking Enroll In CRP Button");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isIWantMyFirstOrderToShipImmediatelyChkBoxDisplayed(),"I Want My First Order To Ship Immediately CheckBox is not displayed");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isIWantMyFirstOrderToShipNextMonthChkBoxDisplayed(),"I Want My First Order To Ship Next Month CheckBox is not displayed");
		s_assert.assertAll();	
	} 	
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2914
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "Learn More About Pulse Pro modal (CORP)")
	public void testLearnMoreAboutPulseProModal_CORP_2914() {
		String consultantEmailID = null;
		RFL_DB = driver.getDBNameRFL();
		List<Map<String, Object>> randomConsultantList =  null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkDisplayed(),"Learn More About Pulse Pro Link not displayed on page");
		storeFrontHeirloomConsultantPage.clickLearnMoreAboutPulseProLink();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkPopupDisplayed(),"Learn More About Pulse Pro Link popup not displayed on page");		
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2915
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "Pulse Pro Modal TOC link navigation (CORP)")
	public void testPulseProModalTOCLinkNavigation_CORP_2915() {
		String urlString="pulse-terms-conditions";
		String consultantEmailID = null;
		RFL_DB = driver.getDBNameRFL();
		List<Map<String, Object>> randomConsultantList =  null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkDisplayed(),"Learn More About Pulse Pro Link not displayed on page");
		storeFrontHeirloomConsultantPage.clickLearnMoreAboutPulseProLink();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkPopupDisplayed(),"Learn More About Pulse Pro Link popup not displayed on page");		
		storeFrontHeirloomConsultantPage.clickPulseTermsAndConditionInModalPopUpAndVerifyOpenInAnotherTab(urlString);
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2904
	 */
	@Test(alwaysRun=true,description = "Next Billing Ship Date value for CRP (CORP)")
	public void testNextBillingShipDateValueForCRPCORP_2904() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String nextOrderDateFromUI = null;
		String categoryName=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isEnrollInCRPButtonDisplayed(),"Enroll In CRP Button is not displayed on page");		
		storeFrontHeirloomConsultantPage.clickEnrollInCRPButton();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		assertTrue(currentUrl.contains("Replenishment/ConsultantEnroll/InitialOrder.aspx"), "User is not redirected to CRP Enrollment page after clicking Enroll In CRP Button");
		storeFrontHeirloomConsultantPage.clickIWantMyFirstCRPOrderToShipNextMonthChkbox();
		storeFrontHeirloomConsultantPage.clickContinueButton();
		storeFrontHeirloomConsultantPage.selectCategory(categoryName);
		storeFrontHeirloomConsultantPage.clickAddFirstProductToCart();
		driver.pauseExecutionFor(3000);
		storeFrontHeirloomConsultantPage.clickContinueButtonOnCartPage();
		driver.pauseExecutionFor(3000);
		storeFrontHeirloomConsultantPage.clickCRPPolicyAndProcedureChkBox();
		storeFrontHeirloomConsultantPage.clickContinueButton();
		storeFrontHeirloomConsultantPage.clickContinueButtonOnCRPEnrollmentPage();
		storeFrontHeirloomConsultantPage.clickContinueButtonOnCRPEnrollmentPage();
		//storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		//storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomConsultantPage.clickCompleteCRPEnrollmentButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPEnrollmentCongratsMessageDisplayed(),"CRP is not Enrolled successfully");
		storeFrontHeirloomConsultantPage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isEnrollInCRPButtonDisplayed(),"Enroll In CRP Button is still displayed on page");
		nextOrderDateFromUI = storeFrontHeirloomConsultantPage.createNextOrderDateInSpecifiedFormatAfterOneMonthForCRPAndPulse();
		s_assert.assertEquals(nextOrderDateFromUI, storeFrontHeirloomConsultantPage.getNextBillingShipDateFromUI(),"Next Billing Ship Date Mismatch");
		s_assert.assertAll();
	} 

}
