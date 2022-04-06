package com.rf.test.website.LSD;

import org.testng.annotations.Test;

import com.rf.core.website.constants.TestConstants;
import com.rf.test.website.RFLSDWebsiteBaseTest;

public class SmokeTests extends RFLSDWebsiteBaseTest{
	
	@Test
	public void TotalNumberOfAllPCs_3349q(){
		String countOfAllPCFromDB = null;
		String subLinkCustomers = "Customers";
		String countOfAllPCFromUI = null;
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickViewAllCustomers();
		
	}
	
	@Test
	public void testVerifyOrderReceiptForProcessedPersonalAdhocOrders_3425q(){
		String subLinkOrders = "Orders";
		String orderTypePersonal = "Personal";
		String orderStatusProcessed = "Processed";
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderTypePersonal);
		lsdOrderPage.clickOrderCategoryFilter(orderStatusProcessed);
		lsdOrderPage.clickApplyFilterBtn();
		lsdOrderPage.clickFirstProcessedOrder();
	}
	
	
	@Test
	public void testVerifyOrderReceiptForFailedPCOrders_3430q(){
		String subLinkOrders = "Orders";
		String orderType = "Preferred";
		String orderStatus = "Failed";
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderType);
		lsdOrderPage.clickOrderCategoryFilter(orderStatus);
		lsdOrderPage.clickApplyFilterBtn();
		lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		lsdOrderPage.clickFirstFailedOrder();
	}
	
	@Test(enabled=true)
	public void testVerifyNumberOfConsultantsPromotedToEC_3234(){
		String currentUrl = null;
		String urlToAssert = "team";
		String consultantPromotedToEC=null;

		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		consultantPromotedToEC=lsdHomePage.getCountOfConsultantPromotedToEC();
	}
	
	@Test(enabled=true)
	public void testVerifyNumberOfConsultantsGettingThere_3278(){
		String currentUrl = null;
		String urlToAssert = "team";
		String consultantNotMatchingECCriteria=null;

		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
	}	
}
