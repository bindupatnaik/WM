package com.rf.test.website.storeFront.heirloom.comPWS.myAccount;

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
	 * qTest Id: TC-2955
	 */
	@Test(alwaysRun=true,description = "Verify user is able to Cancel CRP(Com site)")
	public void testVerifyUserIsAbleToCancelCRP_ComSite_2955() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String crpAndPulsePageHeaderName ="Enroll In Our Consultant Replenishment Program (CRP)*";
		String urlString="crp-terms-conditions";

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
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
	 * qTest Id: TC-2956
	 */
	@Test(alwaysRun=true,description = "Verify when user selects 'No' while cancelling CRP(Com site)")
	public void testWhenUserSelectsNoWhileCancellingCRP_ComSite_2956() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
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
	 * qTest Id: TC-2957
	 */
	@Test(alwaysRun=true,description = "Verify user is able to Cancel Pulse(Com site)")
	public void testUserIsAbleToCancelPulse_ComSite_2957() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
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
	 * qTest Id: TC-2958
	 */
	@Test(alwaysRun=true,description = "Verify when user selects 'No'  while cancelling Pulse(Com site)")
	public void testWhenUserSelectsNoWhileCancellingPulse_ComSite_2958() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
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
	 * qTest Id: TC-2959
	 */
	@Test(alwaysRun=true,description = "Verify when user selects enroll in CRP(Com site)")
	public void testWhenUserSelectsEnrollInCRP_ComSite_2959() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

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
		openCOMSite();
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
	 * qTest Id: TC-2960
	 */
	@Test(alwaysRun=true,description = "Verify when user selects Subscribe to Pulse Pro(Com site)")
	public void testWhenUserSelectsSubscribeToPulsePro_ComSite_2960() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

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
		openCOMSite();
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
	 * qTest Id: TC-2933
	 */
	@Test(alwaysRun=true,description = "Cancel my CRP navigation (COM)")
	public void testCancelMyCRPNavigation_COMSite_2933() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
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

	//Note:TC-2904,TC-2909,TC-2918,TC-2923,TC-2932,TC-2937 are to be automated later

	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2940
	 */
	@Test(alwaysRun=true,description = "CRP information when not enrolled (COM)")
	public void testCRPInformationWhenNotEnrolled_COM_2940() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String crpAndPulsePageHeaderName ="Enroll In Our Consultant Replenishment Program (CRP)*";
		List<Map<String, Object>> randomConsultantList =  null;

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
		openCOMSite();
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
	 * qTest Id: TC-2938
	 */
	@Test(alwaysRun=true,description = "Cancel my Pulse Pro subscription navigation (COM)")
	public void testCancelMyPulseProSubscriptionNavigation_COMSite_2938() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
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
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2936
	 */
	@Test(alwaysRun=true,description = "Pulse Pro Terms & Conditions navigation (COM)")
	public void testPulseProTermsAndConditionsNavigation_COM_2936() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String urlString="pulse-terms-conditions";
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(manageCRPAndPulseLink);
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isPulseProTermsAndConditionsDisplayedOnPage(),"Pulse Pro Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.clickPulseProTermsAndConditionAndVerifyOpenInAnotherTab(urlString),"Pulse Pro Terms and Conditions not displayed on page");
		s_assert.assertAll();	
	} 


	/**
	 * Jira Story Id: Main-8051
	 * qTest Id: TC-2996
	 */
	@Test(alwaysRun=true,description = "PC User login via PWS .COM and verify account management options into the My account header dropdown")
	public void testPCUserLoginViaPWSCOMAndVerifyAccountManagementOptions_COM_2996() {		
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
			for(int i=1;i<=5;i++){
				try{
					pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
		storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.fetchWelcomeUserLink().contains(", \nIndependent Consultant"),"Welcome User Link is not displayed");
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
	 * qTest Id: TC-2998
	 */
	@Test(alwaysRun=true,description = "Verify user should be land on home page after login with PC on .com PWS site")
	public void testVerifyUserShouldLandOnHomePageAfterLoggingWithPC_COMPWS_2998(){		
		String pcEmailID = null;
		String currentUrl=null;	
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
		storeFrontHeirloomHomePage.loginAsPCUser(pcEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEyeBrowSectionVisible(), "User not landed on home page");
		s_assert.assertAll();	
	}
	

/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2935
	 */
	@Test(alwaysRun=true,description = "Pulse Pro subscription information when enrolled (COM)")
	public void testPulseProSubscriptionInformationWhenEnrolled_COM_2935() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String crpAndPulsePageHeaderName ="PULSE Pro Subscription Status ($24.95/Month)**";
		String status = "Enrolled";
		String nextBillingShipDate=null;
		String shipDate[]=null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
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
	 * qTest Id: TC-2941
	 */
	@Test(alwaysRun=true,description = "Pulse Pro subscription information when not enrolled (COM)")
	public void testPulseProSubscriptionInformationWhenNotEnrolled_COM_2941() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String crpAndPulsePageHeaderName ="PULSE Pro Subscription Status ($24.95/Month)**";
		List<Map<String, Object>> randomConsultantList =  null;

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
		openCOMSite();
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
