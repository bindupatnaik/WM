package com.rf.test.website.storeFront.heirloom.comPWS.myAccount;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ManageCRPAndPulseInactiveUITest extends RFHeirloomWebsiteBaseTest {

	String comPWS = null;
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2943
	 */
	@Test(alwaysRun=true,description = "Pulse Pro Modal TOC link navigation (COM)")
	public void testPulseProModalTOCLinkNavigation_COM_2943() {
		String consultantEmailID = null;
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String currentUrl=null;
		String urlString="pulse-terms-conditions";
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

		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkDisplayed(),"Learn More About Pulse Pro Link not displayed on page");
		storeFrontHeirloomConsultantPage.clickLearnMoreAboutPulseProLink();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkPopupDisplayed(),"Learn More About Pulse Pro Link popup not displayed on page");		
		storeFrontHeirloomConsultantPage.clickPulseTermsAndConditionInModalPopUpAndVerifyOpenInAnotherTab(urlString);
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2942
	 */
	@Test(alwaysRun=true,description = "Learn More About Pulse Pro modal (COM)")
	public void testLearnMoreAboutPulseProModal_COM_2942() {
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
		s_assert.assertTrue(currentUrl.contains("manage-crp-pulse-pro"), "User is not redirected to manage-crp-pulse-pro page");

		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkDisplayed(),"Learn More About Pulse Pro Link not displayed on page");
		storeFrontHeirloomConsultantPage.clickLearnMoreAboutPulseProLink();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isLearnMoreAboutPulseProLinkPopupDisplayed(),"Learn More About Pulse Pro Link popup not displayed on page");		
		s_assert.assertAll();		
	}
		
}
