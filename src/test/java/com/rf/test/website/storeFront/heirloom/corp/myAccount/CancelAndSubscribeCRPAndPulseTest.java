package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class CancelAndSubscribeCRPAndPulseTest extends RFHeirloomWebsiteBaseTest {

	/**
	 * Jira Story Id: MAIN-7232
	 * qTest Id: TC-2949
	 */
	@Test(alwaysRun=true,description = "Verify user is able to Cancel CRP(Corp site)")
	public void testVerifyUserIsAbleToCancelCRPCorpSite_2949() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String crpAndPulsePageHeaderName ="Enroll In Our Consultant Replenishment Program (CRP)*";
		String urlString="crp-terms-conditions";
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		storeFrontHeirloomConsultantPage.clickCancelMyCRPButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelCRPPopupDisplayed(),"Cancel CRP Popup is not displayed with specified text");
		storeFrontHeirloomConsultantPage.clickYesOnCancelCRPPopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page after cancelling CRP");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(crpAndPulsePageHeaderName),"Page Header doesnot contains: "+crpAndPulsePageHeaderName);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPTermsAndConditionsDisplayedOnPage(),"CRP Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.clickCRPTermsAndConditionAndVerifyOpenInAnotherTab(urlString),"CRP Terms and Conditions not displayed on page");	
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPOptionalTextDisplayed(),"CRP Optional Text is not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isEnrollInCRPButtonDisplayed(),"Enroll In CRP Button is not displayed on page");		
		s_assert.assertAll();	
	} 

	/**
	 * Jira Story Id: MAIN-7232
	 * qTest Id: TC-2950
	 */
	@Test(alwaysRun=true,description = "Verify when user selects 'No' while cancelling CRP(Corp site)")
	public void testWhenUserSelectsNoWhileCancellingCRP_CorpSite_2950() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		storeFrontHeirloomConsultantPage.clickCancelMyCRPButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelCRPPopupDisplayed(),"Cancel CRP Popup is not displayed with specified text");
		storeFrontHeirloomConsultantPage.clickNoOnCancelCRPPopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page after clicking No on cancel CRP PopUp");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isCancelCRPPopupDisplayed(),"Cancel CRP Popup is displayed with specified text");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelMyCRPLinkDisplayed(),"Cancel My CRP link is not displayed");
		s_assert.assertAll();	
	} 

	/**
	 * Jira Story Id: MAIN-7232
	 * qTest Id: TC-2951
	 */
	@Test(alwaysRun=true,description = "Verify user is able to Cancel Pulse(Corp site)")
	public void testUserIsAbleToCancelPulse_CorpSite_2951() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		storeFrontHeirloomConsultantPage.clickCancelMyPULSEProSubscriptionLink();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelPulseSubscriptionPopupDisplayed(),"Cancel Pulse Subscription Popup is not displayed with specified text");
		storeFrontHeirloomConsultantPage.clickYesOnCancelPulseSubscriptionPopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page after cancelling Pulse Pro Subscription");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isSubscribeToPulseProButtonDisplayed(),"Subscribe To Pulse Pro Button is not displayed on page");		
		s_assert.assertAll();	
	} 

	/**
	 * Jira Story Id: MAIN-7232
	 * qTest Id: TC-2952
	 */
	@Test(alwaysRun=true,description = "Verify when user selects 'No'  while cancelling Pulse(Corp site)")
	public void testWhenUserSelectsNoWhileCancellingPulse_CorpSite_2952() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		storeFrontHeirloomConsultantPage.clickCancelMyPULSEProSubscriptionLink();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelPulseSubscriptionPopupDisplayed(),"Cancel Pulse Subscription Popup is not displayed with specified text");
		storeFrontHeirloomConsultantPage.clickNoOnCancelPulseSubscriptionPopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page after clicking No on cancel Pulse Subscription PopUp");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isCancelPulseSubscriptionPopupDisplayed(),"Cancel Pulse Subscription Popup is displayed with specified text");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelMyPULSEProSubscriptionLinkDisplayed(),"Cancel Pulse Pro Subscription link is not displayed");
		s_assert.assertAll();	
	} 

	/**
	 * Jira Story Id: MAIN-7232
	 * qTest Id: TC-2953
	 */
	@Test(alwaysRun=true,description = "Verify when user selects enroll in CRP(Corp site)")
	public void testWhenUserSelectsEnrollInCRP_CorpSite_2953() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
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
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isIWantMyFirstOrderToShipImmediatelyChkBoxDisplayed(),"I Want My First Order To Ship Immediately CheckBox is not displayed");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isIWantMyFirstOrderToShipNextMonthChkBoxDisplayed(),"I Want My First Order To Ship Next Month CheckBox is not displayed");
		s_assert.assertAll();	
	} 

	/**
	 * Jira Story Id: MAIN-7232
	 * qTest Id: TC-2954
	 */
	@Test(alwaysRun=true,description = "Verify when user selects Subscribe to Pulse Pro(Corp site)")
	public void testWhenUserSelectsSubscribeToPulsePro_CorpSite_2954() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
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
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isSubscribeToPulseProButtonDisplayed(),"Subscribe To Pulse Pro Button is not displayed on page");		
		storeFrontHeirloomConsultantPage.clickSubscribeToPulseProButton();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		assertTrue(currentUrl.contains("Pulse/Intro"), "User is not redirected to Pulse Subscription page after clicking Subscribe to Pulse Pro Button");
		assertTrue(storeFrontHeirloomConsultantPage.isPulseIntroPageDisplayed(),"Pulse Intro Page is not displayed");
		s_assert.assertAll();	
	} 

	/**
	 * Jira Story Id: MAIN-7994
	 * qTest Id: TC-2945
	 */
	@Test(alwaysRun=true,description = "Subscribe to PULSE Pro navigation from Manage CRP & PULSE page")
	public void testSubscribeToPULSEProNavigationFromManageCRPandPULSEPage_2945() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
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
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isSubscribeToPulseProButtonDisplayed(),"Subscribe To Pulse Pro Button is not displayed on page");		
		storeFrontHeirloomConsultantPage.clickSubscribeToPulseProButton();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		assertTrue(currentUrl.contains("Pulse/Intro"), "User is not redirected to Pulse Subscription page after clicking Subscribe to Pulse Pro Button");
		assertTrue(storeFrontHeirloomConsultantPage.isPulseIntroPageDisplayed(),"Pulse Intro Page is not displayed");
		s_assert.assertAll();	
	} 	

	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2905
	 */
	@Test(alwaysRun=true,description = "Cancel my CRP navigation (CORP)")
	public void testCancelMyCRPNavigation_CorpSite_2905() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		RFL_DB=driver.getDBNameRFL();
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		storeFrontHeirloomConsultantPage.clickCancelMyCRPButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelCRPPopupDisplayed(),"Cancel CRP Popup is not displayed with specified text");
		storeFrontHeirloomConsultantPage.clickNoOnCancelCRPPopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page after clicking No on cancel CRP PopUp");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isCancelCRPPopupDisplayed(),"Cancel CRP Popup is displayed with specified text");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelMyCRPLinkDisplayed(),"Cancel My CRP link is not displayed");		

		storeFrontHeirloomConsultantPage.clickCancelMyCRPButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelCRPPopupDisplayed(),"Cancel CRP Popup is not displayed with specified text");
		storeFrontHeirloomConsultantPage.clickYesOnCancelCRPPopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page after cancelling CRP");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isCancelCRPPopupDisplayed(),"Cancel CRP Popup is displayed with specified text");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPOptionalTextDisplayed(),"CRP Optional Text is not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isEnrollInCRPButtonDisplayed(),"Enroll In CRP Button is not displayed on page");		
		s_assert.assertAll();	
	} 

	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2912
	 */
	@Test(alwaysRun=true,description = "CRP information when not enrolled (CORP)")
	public void testCRPInformationWhenNotEnrolled_CORP_2912() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		RFL_DB=driver.getDBNameRFL();
		String crpAndPulsePageHeaderName ="Enroll In Our Consultant Replenishment Program (CRP)*";
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
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(crpAndPulsePageHeaderName),"Page Header doesnot contains: "+crpAndPulsePageHeaderName);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPTermsAndConditionsDisplayedOnPage(),"CRP Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isEnrollInCRPButtonDisplayed(),"Enroll in CRP Button not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPOptionalTextDisplayed(),"CRP Optional Text not displayed on page");		
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2910
	 */
	@Test(alwaysRun=true,description = "Cancel my Pulse Pro subscription navigation (CORP)")
	public void testCancelMyPulseProSubscriptionNavigation_CorpSite_2910() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		RFL_DB=driver.getDBNameRFL();
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");

		storeFrontHeirloomConsultantPage.clickCancelMyPULSEProSubscriptionLink();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelPulseSubscriptionPopupDisplayed(),"Cancel Pulse Subscription Popup is not displayed with specified text");
		storeFrontHeirloomConsultantPage.clickNoOnCancelPulseSubscriptionPopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page after clicking No on cancel Pulse Subscription PopUp");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isCancelPulseSubscriptionPopupDisplayed(),"Cancel Pulse Subscription Popup is displayed with specified text");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelMyPULSEProSubscriptionLinkDisplayed(),"Cancel Pulse Pro Subscription link is not displayed");	

		storeFrontHeirloomConsultantPage.clickCancelMyPULSEProSubscriptionLink();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelPulseSubscriptionPopupDisplayed(),"Cancel Pulse Subscription Popup is not displayed with specified text");
		storeFrontHeirloomConsultantPage.clickYesOnCancelPulseSubscriptionPopup();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page after cancelling Pulse Pro Subscription");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isCancelCRPPopupDisplayed(),"Cancel CRP Popup is displayed with specified text");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isSubscribeToPulseProButtonDisplayed(),"Subscribe To Pulse Pro Button is not displayed on page");		
		s_assert.assertAll();	
	} 

	/**
	 * Jira Story Id: Main-8051
	 * qTest Id: TC-2994
	 */
	@Test(description = "PC User login via Storefront and verify account management options into the My account header dropdown")
	public void testPCUserLoginViaStorefrontAndVerifyAccountManagementOptions_CORP_2994() {		
		String pcEmailID = null;
		String currentUrl=null;	
		String orderHistoryLink="Order History";	
		String editPCPerkCartLink="Edit PC Perks Cart";	
		String manageMyAccountLink="Manage My Account";	
		String shippingDetailsLink="Shipping Details";	
		String billingDetailsLink="Billing Details";	
		String pcPerksFAQsLink="PC Perks FAQs";	
		String logOutLink="Log Out";			
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.fetchWelcomeUserLink().contains(",\nIndependent Consultant"),"Welcome User Link is displayed");//Uncomment this after bugId MAIN-8895 have been resolved
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(orderHistoryLink),"Orders History Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(editPCPerkCartLink),"Edit PC Perk Cart Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(manageMyAccountLink),"Manage My Account Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(shippingDetailsLink),"Shipping Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(billingDetailsLink),"Billing Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(pcPerksFAQsLink),"pc Perks FAQs Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(logOutLink),"LogOut Link is not present in My Account Dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(orderHistoryLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("orderhistory"), "User is not redirected to Order History Page");		
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(editPCPerkCartLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("replenishment/review.aspx"), "User is not redirected to Edit PC Perk Cart Page");
		driver.navigate().back();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageMyAccountLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("myoffice/pcperksstatus"), "User is not redirected to Manage My Account Page");
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(shippingDetailsLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("my-account/address-book"), "User is not redirected to Shipping Details Page");
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(billingDetailsLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("my-account/payment-details"), "User is not redirected to Billing Details Page");
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(pcPerksFAQsLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("images/archives/PC-Perks-FAQs.pdf"), "User is not redirected to PC Perks FAQ Page");
		driver.navigate().back();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(logOutLink);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserLogoutSuccessfully(),"User is not logged out successfully");	
		s_assert.assertAll();	
	}	


	/**
	 * Jira Story Id: Main-8051
	 * qTest Id: TC-3000
	 */
	@Test(description = "Verify the sponsor name after loggin with PC at CORP site")
	public void testVerifyTheSponsorNameAfterLoggingWithPC_CORP_3000() {		
		String pcEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.fetchWelcomeUserLink().contains("Independent Consultant"),"Welcome User Link is displayed");//Umcomment this after bugId MAIN-8895 have been resolved
		s_assert.assertAll();	
	}	

	/**
	 * Jira Story Id: Main-8051
	 * qTest Id: TC-2997
	 */
	@Test(alwaysRun=true,description = "Verify user should be land on home page after login with PC on corp site")
	public void testVerifyUserShouldLandOnHomePageAfterLoggingWithPC_CORP_2997() {		
		String pcEmailID = null;
		String currentUrl=null;	
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(), "User not landed on home page");
		s_assert.assertAll();	
	}	

	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2907
	 */
	@Test(alwaysRun=true,description = "Pulse Pro subscription information when enrolled (CORP)")
	public void testPulseProSubscriptionInformationWhenEnrolled_CORP_2907() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String crpAndPulsePageHeaderName ="PULSE Pro Subscription Status ($24.95/Month)**";
		String status = "Enrolled";
		String nextBillingShipDate=null;
		String shipDate[]=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");

		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(crpAndPulsePageHeaderName),"Page Header does not contains: "+crpAndPulsePageHeaderName);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelMyPULSEProSubscriptionLinkDisplayed(),"Cancel Pulse Pro Subscription link is not displayed");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isPulseProTermsAndConditionsDisplayedOnPage(),"Pulse Pro Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCurrentPulseProSubscriptionStatusDisplayed(status),"Pulse Pro Current Status not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isNextBillDateDisplayed(),"Next Bill Date not displayed on page");
		nextBillingShipDate=storeFrontHeirloomConsultantPage.getNextBillDate();
		shipDate=nextBillingShipDate.split(" ");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.doesNextBillingShipDateContainsRequiredMonthInFormat(shipDate[0].trim()));
		shipDate=nextBillingShipDate.split(",");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.doesNextBillingShipDateContainsRequiredYearInFormat(shipDate[1].trim()));
		shipDate=shipDate[0].split(" ");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.doesNextBillingShipDateContainsRequiredDayInFormat(shipDate[1].trim()));	
		s_assert.assertAll();		

	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2913
	 */
	@Test(alwaysRun=true,description = "Pulse Pro subscription information when not enrolled (CORP)")
	public void testPulseProSubscriptionInformationWhenNotEnrolled_CORP_2913() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String crpAndPulsePageHeaderName ="PULSE Pro Subscription Status ($24.95/Month)**";
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
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(crpAndPulsePageHeaderName),"Page Header doesnot contains: "+crpAndPulsePageHeaderName);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isSubscribeToPulseProButtonDisplayed(),"Subscribe to Pulse pro Button not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isPulseProOptionalTextDisplayed(),"Pulse Pro Optional Text not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkDisplayed(),"Learn More About Pulse Pro Link not displayed on page");
		s_assert.assertAll();		
	}
}
