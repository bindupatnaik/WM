package com.rf.test.website.storeFront.heirloom.comPWS.myAccount;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ManageCRPAndPulseUITest extends RFHeirloomWebsiteMyAccountBaseTest {

	String comPWS = null;
	
	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(comPWS+"/my-account/manage-crp-pulse-pro");
	}
	
	@BeforeGroups("myAccountManageCRPPulseCOM")
	public void loginWithConsultantCOMAndNavigateToManageCRPAndPulse(){
		comPWS = openCOMSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToManageCRPAndPulse();
	}
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2931
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCOM",description = "CRP Terms & Conditions navigation (COM)")
	public void testCRPTermsAndConditionsNavigation_COM_2931() {
		String urlString="crp-terms-conditions";
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCRPTermsAndConditionsDisplayedOnPage(),"CRP Terms and Conditions not displayed on page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.clickCRPTermsAndConditionAndVerifyOpenInAnotherTab(urlString),"CRP Terms and Conditions not displayed on page");	
		s_assert.assertAll();	
	} 	
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2934
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCOM",description = "View Details navigation for CRP (COM)")
	public void testViewDetailsNavigationForCRP_COM_2934() {
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
	 * qTest Id: TC-2939
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCOM",description = "Asterisk copy for CRP and Pulse Pro on Manage CRP & Pulse page (COM)")
	public void testAsterikCopyForCRPAndPulseProOnManageCRPAndPulsePage_COMSite_2939() {
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isAsterikCopyForCRPAndPULSEProOnManageCRPAndPulsePageDisplayed(),"Asterik Copy For CRP And Pulse Pro On Manage CRP and Pulse Page is not displayed with specified text");
		s_assert.assertAll();	
	} 	
	
	/**
	 * Jira Story Id: MAIN-7982
	 * qTest Id: TC-2930
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCOM",description = "CRP information when enrolled (COM)")
	public void testCRPInformationWhenEnrolled_COM_2930() {
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
	 * qTest Id: TC-2932
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCOM",description = "Next Billing Ship Date value for CRP (COM)")
	public void testNextBillingShipDateValueForCRP_COM_2932() {
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
	 * qTest Id: TC-2937
	 */
	@Test(alwaysRun=true,groups="myAccountManageCRPPulseCOM",description = "Next Billing Ship Date value for Pulse Pro (COM)")
	public void testNextBillingShipDateValueForPulsePro_COM_2937() {
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
