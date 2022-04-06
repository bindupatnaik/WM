package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.DBUtil;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ManageCRPAndPulseUITest extends RFHeirloomWebsiteMyAccountBaseTest {


	@BeforeMethod
	public void refreshPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(driver.getURL()+"/my-account/manage-crp-pulse-pro");
	}
	
	@BeforeGroups("myAccountManageCRPPulseCorp")
	public void loginWithConsultantCorpAndNavigateToManageCRPAndPulse(){
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToManageCRPAndPulse();
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2903
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "CRP Terms & Conditions navigation (CORP)")
	public void testCRPTermsAndConditionsNavigation_CORP_2903() {
		String urlString="crp-terms-conditions";
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPTermsAndConditionsDisplayedOnPage(),"CRP Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.clickCRPTermsAndConditionAndVerifyOpenInAnotherTab(urlString),"CRP Terms and Conditions not displayed on page");	
		s_assert.assertAll();	
	} 
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2906
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "View Details navigation for CRP (CORP)")
	public void testViewDetailsNavigationForCRP_CORP_2906() {
		List<WebElement> headerColumnItemsInViewDetailsPage;
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isViewDetailsLinkDisplayed(),"View Details Link is not displayed on page");
		storeFrontHeirloomConsultantPage.clickViewDetailsLink();
		headerColumnItemsInViewDetailsPage=storeFrontHeirloomConsultantPage.isHeaderColumnItemsDisplayedInViewDetailsPage();
		s_assert.assertTrue(headerColumnItemsInViewDetailsPage.get(0).getText().equalsIgnoreCase("Overview"),"Header Column Item Overview is not displayed");
		s_assert.assertTrue(headerColumnItemsInViewDetailsPage.get(1).getText().equalsIgnoreCase("Order Items"),"Header Column Item order Items is not displayed");
		s_assert.assertTrue(headerColumnItemsInViewDetailsPage.get(2).getText().equalsIgnoreCase("Shipping"),"Header Column Item Shipping is not displayed");
		s_assert.assertTrue(headerColumnItemsInViewDetailsPage.get(3).getText().equalsIgnoreCase("Billing"),"Header Column Item Billing is not displayed");		
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2911
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "Asterisk copy for CRP and Pulse Pro on Manage CRP & Pulse page (CORP)")
	public void testAsterikCopyForCRPAndPulseProOnManageCRPAndPulsePage_CorpSite_2911() {
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isAsterikCopyForCRPAndPULSEProOnManageCRPAndPulsePageDisplayed(),"Asterik Copy For CRP And Pulse Pro On Manage CRP and Pulse Page is not displayed with specified text");
		s_assert.assertAll();	
	} 
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2902
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "CRP information when enrolled (CORP)")
	public void testCRPInformationWhenEnrolled_CORP_2902() {
		String crpAndPulsePageHeaderName ="Consultant Replenishment Program (CRP)*";
		String status = "Enrolled";
		String nextBillingShipDate=null;
		String shipDate[]=null;
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderContainsSpecifiedCategory(crpAndPulsePageHeaderName),"Page Header doesnot contains: "+crpAndPulsePageHeaderName);
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPTermsAndConditionsDisplayedOnPage(),"CRP Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCurrentCRPStatusDisplayed(status),"CRP Current Status not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCancelMyCRPLinkDisplayed(),"Cancel My CRP Link not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isNextBillingShipDateDisplayed(),"Next Billing Ship Date not displayed on page");
		nextBillingShipDate=storeFrontHeirloomConsultantPage.getNextBillingShipDate();
		shipDate=nextBillingShipDate.split(" ");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.doesNextBillingShipDateContainsRequiredMonthInFormat(shipDate[0].trim()));
		shipDate=nextBillingShipDate.split(",");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.doesNextBillingShipDateContainsRequiredYearInFormat(shipDate[1].trim()));
		shipDate=shipDate[0].split(" ");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.doesNextBillingShipDateContainsRequiredDayInFormat(shipDate[1].trim()));	
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isViewDetailsLinkDisplayed(),"View Details Link is not displayed on page");
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2908
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "Pulse Pro Terms & Conditions navigation (CORP)")
	public void testPulseProTermsAndConditionsNavigation_CORP_2908() {
		String urlString="pulse-terms-conditions";
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isPulseProTermsAndConditionsDisplayedOnPage(),"Pulse Pro Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.clickPulseProTermsAndConditionAndVerifyOpenInAnotherTab(urlString),"Pulse Pro Terms and Conditions not displayed on page");
		s_assert.assertAll();	
	} 
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2909
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCorp",description = "Next Billing Ship Date value for Pulse Pro (CORP)")
	public void testNextBillingShipDateValueForPulsePro_CORP_2909() {
		
		String nextBillingShipDate=null;
		String shipDate[]=null;
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
	

}
