package com.rf.test.website.storeFront.heirloom.comPWS.myAccount;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class EditPCPerksCartTest extends RFHeirloomWebsiteMyAccountBaseTest {

	String comPWS = null;
	
	@BeforeMethod
	public void refreshEditPCPerksPageCom(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(comPWS+"/replenishment/review.aspx");
	}
	
	@BeforeGroups("myAccountEditPCPerksCom")
	public void loginWithPCComAndNavigateToEditPCPerks(){
		logout();
		comPWS = openCOMSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToEditPCPerksPage();
	}
	
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3229
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksCom",description = "Verify that MY  account link is not available on Replenishment overview page under Edit PC perks cart page(Com)")
	public void testVerifyMyAccountIsNotAvailableOnReplenishmentOverviewPageComSite_3229() {
		String linkName = "Overview";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnReplenishmentReviewPage(),"User is not navigated to Replenishment review page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3230
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksCom",description = "Verify that MY  account link is not available on Order items page under Edit PC perk cart page(Com)")
	public void testVerifyMyAccountIsNotAvailableOnOrderItemPageComSite_3230() {
		String linkName = "Order Items";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnOrderItemPage(),"User is not navigated to order item page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3231
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksCom",description = "Verify that MY  account link is not available on Shipping page under Edit PC perk cart page(Com)")
	public void testVerifyMyAccountIsNotAvailableOnShippingPageComSite_3231() {
		String linkName = "Shipping";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnShippingPage(),"User is not navigated to shipping page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3232
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksCom",description = "Verify that MY  account link is not available on Billing page under Edit PC perk cart page(Com)")
	public void testVerifyMyAccountIsNotAvailableOnBillingPageComSite_3232() {
		String linkName = "Billing";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnBillingPage(),"User is not navigated to Billing page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
}
