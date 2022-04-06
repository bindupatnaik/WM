package com.rf.test.website.nscore4;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.pages.website.nscore.NSCore4AdminPage;
import com.rf.pages.website.nscore.NSCore4HomePage;
import com.rf.pages.website.nscore.NSCore4LoginPage;
import com.rf.pages.website.nscore.NSCore4MobilePage;
import com.rf.pages.website.nscore.NSCore4OrdersTabPage;
import com.rf.pages.website.nscore.NSCore4ProductsTabPage;
import com.rf.pages.website.nscore.NSCore4SitesTabPage;
import com.rf.test.website.RFNSCoreWebsiteBaseTest;

public class DelayPCAutoshipTest  extends RFNSCoreWebsiteBaseTest{
	private NSCore4HomePage nscore4HomePage;
	String RFL_DB = null;
	List<Map<String, Object>> randomRCAccountList =  null;
	List<Map<String, Object>> randomConsultantAccountList =  null;
	List<Map<String, Object>> randomPCAccountList =  null;

	public DelayPCAutoshipTest() {
		nscore4HomePage = new NSCore4HomePage(driver);
	}

	@Test(description="NSC4 > PC delay 60 days")
	public void testPCDealy60Days_3429(){
		List<Map<String, Object>> randomPCAccountList =  null;
		RFL_DB = driver.getDBNameRFL();
		randomPCAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_DETAILS_RFL,RFL_DB);
		String emailID = (String) getValueFromQueryResult(randomPCAccountList, "EmailAddress");
		String accountNumber = (String) getValueFromQueryResult(randomPCAccountList, "AccountNumber");
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPCBiMonthlyReplenishmentDelay();
		String nextDueDate = nscore4HomePage.selectDelayPeriodAndGetNextDate("60");
		nscore4HomePage.clickChangeAutoshipDate();
		nscore4HomePage.clickBackButtonOnAutoshipDateChangeConfirmationPage();
		nscore4HomePage.clickPCBiMonthlyReplenishmentEdit();
		s_assert.assertTrue(nscore4HomePage.isAutoshipTemplateContainsNextDueDate(nextDueDate),"Next Due date is NOT as expected");
		s_assert.assertAll();
		
	}
	
	@Test(description="NSC4 > PC delay 30 days")
	public void testPCDealy30Days_3430(){
		List<Map<String, Object>> randomPCAccountList =  null;
		RFL_DB = driver.getDBNameRFL();
		randomPCAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_DETAILS_RFL,RFL_DB);
		String emailID = (String) getValueFromQueryResult(randomPCAccountList, "EmailAddress");
		String accountNumber = (String) getValueFromQueryResult(randomPCAccountList, "AccountNumber");
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPCBiMonthlyReplenishmentDelay();
		String nextDueDate = nscore4HomePage.selectDelayPeriodAndGetNextDate("30");
		nscore4HomePage.clickChangeAutoshipDate();
		nscore4HomePage.clickBackButtonOnAutoshipDateChangeConfirmationPage();
		nscore4HomePage.clickPCBiMonthlyReplenishmentEdit();
		s_assert.assertTrue(nscore4HomePage.isAutoshipTemplateContainsNextDueDate(nextDueDate),"Next Due date is NOT as expected");
		s_assert.assertAll();
		
	}
	

@Test(enabled=true,description="NSC4 > Cancelling Pulse from site subscription tab – by clicking on the “Cancel” link")
	public void testCancelPulseFromSiteSubscription_3431(){//  In progress
		List<Map<String, Object>> randomConsultantAccountList =  null;
		String pulseStatus = "Submitted Template";
		String SKU = "PULSE01";
		
		RFL_DB = driver.getDBNameRFL();
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_CONSULTANT_HAS_CRP_HAS_PULSE_SUBMITTED_ORDERS_ACTIVE_RFL_4191,RFL_DB);
		String emailID = (String) getValueFromQueryResult(randomConsultantAccountList, "EmailAddress");
		String accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickOnSiteSubscriptionLink();
		nscore4HomePage.clickOnCancelLink();
		nscore4HomePage.acceptAlert();
		s_assert.assertTrue(nscore4HomePage.isAutoshipCancelledOnSiteSubscriptionPage(),"Autoship is not cancelled on Site subscription page");
		
		nscore4HomePage.clickOverviewLink();
		nscore4HomePage.clickCreatePulseTemplate();
		nscore4HomePage.selectTemplateStatus(pulseStatus);
		nscore4HomePage.enterSKUValues(SKU);
		nscore4HomePage.clickFirstSKUSearchResultOfAutoSuggestion();
		nscore4HomePage.clickAddToOrder();
		nscore4HomePage.clickSaveTemplate();
		nscore4HomePage.clickConsultantNameOnOrderDetailsPage();
		s_assert.assertTrue(nscore4HomePage.isEditPresentForPulseMonthlySubscriptionPresent(),"Create link for Pulse is present after enroll in Pulse template.");
		
		nscore4HomePage.clickOnSiteSubscriptionLink();
		nscore4HomePage.clickOnCancelLink();
		nscore4HomePage.acceptAlert();
		s_assert.assertTrue(nscore4HomePage.isReactivateLinkPresentOnSiteSubscriptionPage(),"Re-activate link is not present on Site subscription page");
		nscore4HomePage.clickOverviewLink();
		s_assert.assertTrue(nscore4HomePage.isOnAutoshipsSectionOnOverviewExpectedTextVisible("Pulse Monthly Subscription (Cancelled)"), "Pulse Monthly Subscription (Cancelled)	text not visible"); 
	
		nscore4HomePage.clickOnSiteSubscriptionLink();
		nscore4HomePage.clickOnReactivateLinkOnSiteSubscriptionPage();
		s_assert.assertTrue(nscore4HomePage.isAutoshipActiveOnSiteSubscriptionPage(),"Autoship is showing as cancelled on Site subscription page");
		
		nscore4HomePage.clickOverviewLink();
		s_assert.assertFalse(nscore4HomePage.isOnAutoshipsSectionOnOverviewExpectedTextVisible("Pulse Monthly Subscription (Cancelled)"), "Pulse Monthly Subscription (Cancelled) text visible on UI after Reactivation."); 
		s_assert.assertAll();
		
	}
	
	@Test(description="NSC4 > Cancelling the Pulse when CRP is inactive")
	public void testCancelPulseWhenCRPInactive_3432(){
		List<Map<String, Object>> randomConsultantAccountList =  null;
		String RFO_DB = driver.getDBNameRFO();
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_ACTIVE_CONSULTANT_WHOSE_CRP_IS_INACTIVE,RFO_DB);
		String emailID = (String) getValueFromQueryResult(randomConsultantAccountList, "EmailAddress");
		String accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditPulseTemplate();
		nscore4HomePage.selectTemplateStatus("Cancelled");
		nscore4HomePage.clickSaveTemplate();
		s_assert.assertTrue(nscore4HomePage.isCRPOrPulseTemplateStatusPresent("Cancelled"),"Pulse of user is NOT cancelled successfully.");
		nscore4HomePage.clickOnUsernameLinkLeftPanel();
		s_assert.assertTrue(nscore4HomePage.isOnAutoshipsSectionOnOverviewExpectedTextVisible("Pulse Monthly Subscription (Cancelled)"), "Pulse Monthly Subscription (Cancelled)	text not visible");
		s_assert.assertAll();
		
	}
	
	@Test(description="NSC4 > Verify the PULSE EMAIL from the Site Subscriptions tab")
	public void testVerifyPulseEmailDomainSuffixOnSiteSubscription_3433(){
		List<Map<String, Object>> randomConsultantAccountList =  null;
		String RFO_DB = driver.getDBNameRFO();
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_FOR_PULSE_CANCELLATION_FROM_SITE_SUBSCRIPTION,RFO_DB);
		String emailID = (String) getValueFromQueryResult(randomConsultantAccountList, "EmailAddress");
		String accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickOnSiteSubscriptionLink();
		s_assert.assertTrue(nscore4HomePage.getPulseEmailDomainSuffix().contains("@myrandf.com"), "pulse email domain suffix is NOT as expected");
		s_assert.assertAll();
		
	}

}
