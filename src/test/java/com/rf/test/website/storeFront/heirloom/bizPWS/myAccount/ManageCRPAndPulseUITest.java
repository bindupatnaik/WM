package com.rf.test.website.storeFront.heirloom.bizPWS.myAccount;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ManageCRPAndPulseUITest extends RFHeirloomWebsiteMyAccountBaseTest {

	String bizPWS = null;
	
	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(bizPWS+"/my-account/manage-crp-pulse-pro");
	}
	
	@BeforeGroups("myAccountManageCRPPulseBIZ")
	public void loginWithConsultantBIZAndNavigateToManageCRPAndPulse(){
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToManageCRPAndPulse();
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2917
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseBIZ",description = "CRP Terms & Conditions navigation (BIZ)")
	public void testCRPTermsAndConditionsNavigation_BIZ_2917() {
		String urlString="crp-terms-conditions";
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPTermsAndConditionsDisplayedOnPage(),"CRP Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.clickCRPTermsAndConditionAndVerifyOpenInAnotherTab(urlString),"CRP Terms and Conditions not displayed on page");	
		s_assert.assertAll();	
	} 
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2920
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseBIZ",description = "View Details navigation for CRP (BIZ)")
	public void testViewDetailsNavigationForCRP_BIZ_2920() {
		List<WebElement> headerColumnItemsInViewDetailsPage;
		String currentUrl=null;
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isViewDetailsLinkDisplayed(),"View Details Link is not displayed on page");
		storeFrontHeirloomConsultantPage.clickViewDetailsLink();
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("Replenishment/Overview"), "User is not redirected to View Details page");
		headerColumnItemsInViewDetailsPage=storeFrontHeirloomConsultantPage.isHeaderColumnItemsDisplayedInViewDetailsPage();
		s_assert.assertTrue(headerColumnItemsInViewDetailsPage.get(0).getText().equalsIgnoreCase("Overview"),"Header Column Item Overview is not displayed");
		s_assert.assertTrue(headerColumnItemsInViewDetailsPage.get(1).getText().equalsIgnoreCase("Order Items"),"Header Column Item order Items is not displayed");
		s_assert.assertTrue(headerColumnItemsInViewDetailsPage.get(2).getText().equalsIgnoreCase("Shipping"),"Header Column Item Shipping is not displayed");
		s_assert.assertTrue(headerColumnItemsInViewDetailsPage.get(3).getText().equalsIgnoreCase("Billing"),"Header Column Item Billing is not displayed");		
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2925
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseBIZ",description = "Asterisk copy for CRP and Pulse Pro on Manage CRP & Pulse page (BIZ)")
	public void testAsterikCopyForCRPAndPulseProOnManageCRPAndPulsePage_BIZSite_2925() {
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isAsterikCopyForCRPAndPULSEProOnManageCRPAndPulsePageDisplayed(),"Asterik Copy For CRP And Pulse Pro On Manage CRP and Pulse Page is not displayed with specified text");
		s_assert.assertAll();	
	} 
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2916
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseBIZ",description = "CRP information when enrolled (BIZ)")
	public void testCRPInformationWhenEnrolled_BIZ_2916() {
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
	 * qTest Id: TC-2918
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseBIZ",description = "Next Billing Ship Date value for CRP (BIZ)")
	public void testNextBillingShipDateValueForCRP_BIZ_2918() {
		
		String nextBillingShipDate=null;
		String shipDate[]=null;
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isNextBillingShipDateDisplayed(),"Next Billing Ship Date not displayed on page");
		nextBillingShipDate=storeFrontHeirloomConsultantPage.getNextBillingShipDate();
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
	 * qTest Id: TC-2923
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseBIZ",description = "Next Billing Ship Date value for Pulse Pro (BIZ)")
	public void testNextBillingShipDateValueForPulsePro_BIZ_2923() {
		
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
