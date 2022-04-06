package com.rf.test.website.storeFront.heirloom.bizPWS.myAccount;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ManageMyAccountTest extends RFHeirloomWebsiteMyAccountBaseTest {

	String bizPWS=null;
	
	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(bizPWS+"/myoffice/pcperksstatus");
	}
	
	@BeforeGroups("ManageMyAccountBIZ")
	public void loginWithPCCorpAndNavigateToEditPCPerks(){
		logout();
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToManageMyAccountPage();
	}
	
	/**
	 * Jira Story Id: MAIN-7944
	 * qTest Id: TC-3097
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountBIZ",description = "MAIN-7944 :  Editing Autoship Date - PC Perk Status Page - Display(biz)")
	public void testEditingAutoshipDatePCPerksStatusPageDisplayFromBIZ_3097() {
		String currentUrl = null;
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNextOrderDateTextDisplayed(), "Next order date text is not displayed");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderDateBtnDisplayed(), "Edit order btn is not displayed");
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		currentUrl = storeFrontHeirloomEditPCPerksPage.getCurrentURL();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisplayed() &&  currentUrl.contains("/PcDelayAutoship"), "User is not redirected to PCPerks Dealy page, Expected url should contains /PcDelayAutoship but actual on UI is "+currentUrl);
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-7945
	 * qTest Id: TC-3094
	 */
	@Test(alwaysRun = true, groups = "ManageMyAccountBIZ", description = "Editing Autoship Date - PC Perks Delay Autoship Page - Display on .biz PWS")
	public void testPCAutoshipOrderProcessing_3094() {
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		s_assert.assertTrue(driver.getCurrentUrl().contains("MyOffice/PcDelayAutoship"),
				"User is not navigated to PcDelayAutoship page");
		s_assert.assertTrue(
				storeFrontHeirloomEditPCPerksPage.getManagePCPerksSubscriptionOrderTxt()
						.contains("Manage your PC Perks Subscription Order below:"),
				"Manage PC Perks Subscription Order Message did NOT match.");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisplayed(),
				"Buy Now Button Is NOT displayed.");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.getChooseDateTxt().contains("Choose date"),
				"Choose Date Text Did NOT match.");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.vefiryPlaceHolderValueForDueDate(),
				"PlaceHoder value for Due Date Did NOT match.");
		storeFrontHeirloomEditPCPerksPage.clickDateFieldOrCalendarIcon();
		
		s_assert.assertTrue(
				storeFrontHeirloomEditPCPerksPage.getManagePCPerksSubscriptionOrderTxt()
						.contains(
								" You can move your autoship date up to 60 days from today, and any date between the 1st & 20th."),
				"AutoShip Date Message did NOT match.");
		
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isChangeDateBtnDisabled(),
				"Change Date Button Is Enabled,Expected Disabled.");
		storeFrontHeirloomEditPCPerksPage.selectValidPCPerkdDate("20");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isChangeDateBtnDisabled(),
				"Change Date Button Is Disabled,Expected enabled.");
		
		s_assert.assertAll();
	}
	
	
	/**
	 * Jira Story Id: MAIN-8764
	 * qTest Id: TC-3239
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountBIZ",description = "Verify the text on the PC Perks status page at Biz PWS site")
	public void testVerifyTheTextOnThePCPerkStatusPage_BIZPWSSite_3239() {
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isRequiredTextInManageMyAccountPageDisplayed(), "Required text is not displayed in Manage My Account Page");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isCancelPCPerksAccountLinkInManageMyAccountPageDisplayed(),"Cancel PC Perks Account Link not displayed");
		storeFrontHeirloomEditPCPerksPage.clickLinkClickHereInManageMyAccountPage();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.getCurrentURL().contains("http://rodanandfieldsresults.com"),"On clicking Click Here Link User not redirected to Results Page");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8764
	 * qTest Id: TC-3240
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountBIZ",description = "Verify the header on the page at Biz PWS site")
	public void testVerifyTheHeaderOnThePage_BIZPWSSite_3240() {
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isManageMyAccountHeaderTextDisplayed(), "Manage My Account Header Text is not being Displayed");
		s_assert.assertAll();
		
	}
	
	/**
	 * Jira Story Id: MAIN-8764
	 * qTest Id: TC-3241
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountBIZ",description = "Verify the label \"Cancel PC Perks Account\" at Biz PWS site")
	public void testVerifyTheLabelCancelPCPerksAccount_BIZPWSSite_3241() {
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isCancelPCPerksAccountLinkInManageMyAccountPageDisplayed(),"Cancel PC Perks Account Link not displayed");
		storeFrontHeirloomEditPCPerksPage.clickCancelPCPerksAccountLinkInManageMyAccountPages();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.getCurrentURL().contains("PcCancelPcPerksAccount"),"User not redirected to Cancel PC Perks Account page");
		storeFrontHeirloomEditPCPerksPage.selectReasonForCancellingPCPerkAccount(5);
		storeFrontHeirloomEditPCPerksPage.clickSendEmailToCancelButton();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.getCurrentURL().contains("PcCancelPcPerksConfirmation"),"User not redirected to Cancel PC Perks Confirmation page");	
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isGoToRFHomeButtonDisplayed(),"Go To R+F Home Button is not displayed");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginButtonPresent(),"Login button is not present");
		s_assert.assertAll();
		
	}
	
}
