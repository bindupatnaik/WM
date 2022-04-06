package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class AccountDetailsTest extends RFHeirloomWebsiteMyAccountBaseTest {

	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(driver.getURL()+"/my-account");
	}
	
	@BeforeGroups("myAccountDetailsCorpCons")
	public void loginWithConsultantCorpAndNavigateToAccountDetails(){
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToAccountDetails();		
	}
	
	/**
	 * Jira Story Id: MAIN-7403
	 * qTest Id: TC-2828
	 */
	@Test(alwaysRun=true,groups="myAccountDetailsCorpCons",description = "Verify error is displayed when user Does  not enter confirm new password while resetting password")
	public void testErrorIsDisplayedWhenUserDoesNotEnterConfirmNewPasswordWhileResettingPasswordOnAccountDetailsPage_2828() {
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountEmailAddressTxtFieldDisplayed());
		storeFrontHeirloomConsultantPage.enterTextInMyAccountNewPasswordTxtField("QWERTY").clickOnSaveButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountPleaseReEnterYourNewPasswordErrorLabelDisplayed());
		s_assert.assertAll();		
	}
		
	/**
	 * Jira Story Id: MAIN-7403
	 * qTest Id: TC-2827
	 */
	@Test(alwaysRun=true,groups="myAccountDetailsCorpCons",description = "Verify error is displayed when user Does  not enter new password while resetting password")
	public void testErrorIsDisplayedWhenUserDoesNotEnterNewPasswordWhileResettingPasswordOnAccountDetailsPage_2827() {
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountEmailAddressTxtFieldDisplayed());
		storeFrontHeirloomConsultantPage.enterTextInMyAccountOldPasswordTxtField("QWERTY").enterTextInMyAccountConfirmNewPasswordTxtField("QWERTY").clickOnSaveButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountNewPasswordIsRequiredErrorLabelDisplayed());
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-7403
	 * qTest Id: TC-2826
	 */
	@Test(alwaysRun=true,groups="myAccountDetailsCorpCons",description = "Verify error is displayed when user Does not enter old password while resetting password")
	public void testErrorIsDisplayedWhenUserDoesNotEnterOldPasswordWhileResettingPasswordOnAccountDetailsPage_2826() {
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountEmailAddressTxtFieldDisplayed());
		storeFrontHeirloomConsultantPage.clearMyAccountOldPasswordTxtField().enterTextInMyAccountNewPasswordTxtField("QWERTY").enterTextInMyAccountConfirmNewPasswordTxtField("QWERTY").clickOnSaveButton();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isMyAccountPleaseEnterYourCurrentPasswordErrorLabelDisplayed());
		s_assert.assertAll();		
	}
	
	
}
