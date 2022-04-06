package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class EditPCPerksCartTest extends RFHeirloomWebsiteMyAccountBaseTest {
	
	@BeforeMethod
	public void refreshEditPCPerksPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(driver.getURL()+"/replenishment/review.aspx");
	}

	@BeforeGroups("myAccountEditPCPerksCorp")
	public void loginWithPCCorpAndNavigateToEditPCPerks(){
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToEditPCPerksPage();
	}

	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3221
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksCorp",description = "Verify that MY  account link is not available on Replenishment overview page under Edit PC perks cart page(Corp)")
	public void testVerifyMyAccountIsNotAvailableOnReplenishmentOverviewPage_3221() {
		String linkName = "Overview";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnReplenishmentReviewPage(),"User is not navigated to Replenishment review page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3222
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksCorp",description = "Verify that MY  account link is not available on Order items page under Edit PC perk cart page(Corp)")
	public void testVerifyMyAccountIsNotAvailableOnOrderItemPage_3222() {
		String linkName = "Order Items";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnOrderItemPage(),"User is not navigated to order item page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3223
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksCorp",description = "Verify that MY  account link is not available on Shipping page under Edit PC perk cart page(Corp)")
	public void testVerifyMyAccountIsNotAvailableOnShippingPage_3223() {
		String linkName = "Shipping";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnShippingPage(),"User is not navigated to shipping page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3224
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksCorp",description = "Verify that MY  account link is not available on Billing page under Edit PC perk cart page(Corp)")
	public void testVerifyMyAccountIsNotAvailableOnBillingPage_3224() {
		String linkName = "Billing";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnBillingPage(),"User is not navigated to Billing page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}

}
