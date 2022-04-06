package com.rf.test.website.storeFront.heirloom.bizPWS.myAccount;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class EditPCPerksCartTest extends RFHeirloomWebsiteMyAccountBaseTest{

	String bizPWS=null;
	
	@BeforeMethod
	public void refreshEditPCPerksPageBiz(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(bizPWS+"/replenishment/review.aspx");
	}
	
	@BeforeGroups("myAccountEditPCPerksBiz")
	public void loginWithPCBizAndNavigateToEditPCPerks(){
		logout();
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToEditPCPerksPage();
	}

	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3225
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksBiz",description = "Verify that MY  account link is not available on Replenishment overview page under Edit PC perks cart page(Biz)")
	public void testVerifyMyAccountIsNotAvailableOnReplenishmentOverviewPageBizSite_3225() {
		String linkName = "Overview";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnReplenishmentReviewPage(),"User is not navigated to Replenishment review page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3226
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksBiz",description = "Verify that MY  account link is not available on Order items page under Edit PC perk cart page(Biz)")
	public void testVerifyMyAccountIsNotAvailableOnOrderItemPageBizSite_3226() {
		String linkName = "Order Items";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnOrderItemPage(),"User is not navigated to order item page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3227
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksBiz",description = "Verify that MY  account link is not available on Shipping page under Edit PC perk cart page(Biz)")
	public void testVerifyMyAccountIsNotAvailableOnShippingPageBizSite_3227() {
		String linkName = "Shipping";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnShippingPage(),"User is not navigated to shipping page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
	/**
	 * Jira Story Id: MAIN-8727
	 * qTest Id: TC-3228
	 */
	@Test(alwaysRun=true,groups="myAccountEditPCPerksBiz",description = "Verify that MY  account link is not available on Billing page under Edit PC perk cart page(Biz)")
	public void testVerifyMyAccountIsNotAvailableOnBillingPageBizSite_3228() {
		String linkName = "Billing";
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickLinkUnderReplenishmentOrder(linkName);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isUserIsOnBillingPage(),"User is not navigated to Billing page");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isMyAccountLinkPresent(),"My Account link is present above Replenishment Order management");
		s_assert.assertAll();
	}
}
