package com.rf.test.website.LSD;

import java.text.ParseException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.rf.core.website.constants.TestConstants;
import com.rf.test.website.RFLSDWebsiteBaseTest;

public class LSDOrdersTests extends RFLSDWebsiteBaseTest{

	@AfterClass
	public void afterLSDOrderLogout(){
		lsdHomePage.clickLogout();
	}

	//TC-3425 Verify Order Receipt for Processed Personal Adhoc Orders
	@Test(alwaysRun=true)
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
		s_assert.assertTrue(lsdOrderPage.isViewReceiptDetailsLinkVisible(), "View receipt link is not visible for personal processed orders");
		s_assert.assertAll();
	}

	//TC-3430 Verify Order Receipt for Failed PC Orders
	@Test(alwaysRun=true)
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
		s_assert.assertFalse(lsdOrderPage.isViewReceiptDetailsLinkVisible(), "View receipt link is visible for PC Failed orders");
		s_assert.assertAll();
	}


	//TC-2834 Order Type - Order Results based on Order Filter
	@Test(alwaysRun=true)
	public void testOrderTypeOrderResultsBasedOnOrderFilter_2834q(){
		String subLinkOrders = "Orders";
		String orderType = "Consultant";
		boolean isOrderPresent = false;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderType);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderTypeFilterAppliedSuccessfullyForConsultant(), "Orders type "+orderType+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		//order type Preferred
		orderType = "Preferred";
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderType);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderTypeFilterAppliedSuccessfullyForPC(), "Orders type "+orderType+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		//order type Personal
		orderType = "Personal";
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderType);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderTypeFilterAppliedSuccessfullyForPersonal(), "Orders type "+orderType+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		//order type 	Retail
		orderType = "Retail";
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderType);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderTypeFilterAppliedSuccessfullyForRetailOrder(), "Orders type "+orderType+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		s_assert.assertAll();
	}


	//TC-2835 Order Status - Order Results based on Order Filter
	@Test(alwaysRun=true)
	public void testOrderStatusOrderResultsBasedOnOrderFilter_2835q(){
		String subLinkOrders = "Orders";
		String orderStatus = "Pending";
		boolean isOrderPresent = false;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderStatus);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		//Order status Processed
		orderStatus = "Processed";
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderStatus);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		//Order status Failed
		orderStatus = "Failed";
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderStatus);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		//Order status Partially Shipped
		orderStatus = "Partially";
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderStatus);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		//Order status 	Returned
		orderStatus = "Returned";
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderStatus);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(lsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		lsdOrderPage.removeAllAppliedFilters();

		s_assert.assertAll();
	}

	//TC-2838 CRP orders - Order Details on Order Cards
	@Test(alwaysRun=true)
	public void testVerifyCRPOrderDetailsOnOrderCards_2838q(){
		String subLinkOrders = "Orders";
		String orderTypePersonal = "Personal";
		String orderStatusProcessed = "Processed";
		String firstOrderNameFromUI = null;
		String firstOrderTypeFromUI = null;
		String firstOrderStatusFromUI = null;
		String firstOrderSVFromUI = null;
		boolean isOrderPresent = false;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderTypePersonal);
		lsdOrderPage.clickOrderCategoryFilter(orderStatusProcessed);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		firstOrderNameFromUI = lsdOrderPage.getFirstOrderUserName();
		firstOrderStatusFromUI = lsdOrderPage.getOrderStatus(1);
		firstOrderTypeFromUI = lsdOrderPage.getOrderType(1);
		firstOrderSVFromUI = lsdOrderPage.getFirstOrderPSQVValue();
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderNameFromUI), "Order name is not present on UI");
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderSVFromUI), "Order SV value is not present on UI");
		s_assert.assertTrue(firstOrderStatusFromUI.toLowerCase().contains("processed"), "Expected order status is processed but actual on UI "+firstOrderStatusFromUI);
		s_assert.assertTrue(firstOrderTypeFromUI.toLowerCase().contains("pulse") || firstOrderTypeFromUI.toLowerCase().contains("crp"), "Expected order type is personal but actual on UI "+firstOrderTypeFromUI);
		s_assert.assertTrue(lsdOrderPage.isFirstOrderDatePresent(), "Order date format is not present on UI");
		s_assert.assertAll();
	}

	//TC-2855 CRP orders - Order Details on Order Cards
	@Test(alwaysRun=true)
	public void testVerifyCRPOrderDetailsWithConsultantPersonalOnOrderCards_2855q(){
		String subLinkOrders = "Orders";
		String orderType = "Consultant";
		String orderStatusProcessed = "Processed";
		String firstOrderNameFromUI = null;
		String firstOrderTypeFromUI = null;
		String firstOrderStatusFromUI = null;
		String firstOrderSVFromUI = null;
		boolean isOrderPresent = false;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderType);
		lsdOrderPage.clickOrderCategoryFilter(orderStatusProcessed);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		firstOrderNameFromUI = lsdOrderPage.getFirstOrderUserName();
		firstOrderStatusFromUI = lsdOrderPage.getOrderStatus(1);
		firstOrderTypeFromUI = lsdOrderPage.getOrderType(1);
		firstOrderSVFromUI = lsdOrderPage.getFirstOrderPSQVValue();
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderNameFromUI), "Order name is not present on UI");
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderSVFromUI), "Order SV value is not present on UI");
		s_assert.assertTrue(firstOrderStatusFromUI.toLowerCase().contains("processed"), "Expected order status is processed but actual on UI "+firstOrderStatusFromUI);
		s_assert.assertTrue(firstOrderTypeFromUI.toLowerCase().contains("pulse") || firstOrderTypeFromUI.toLowerCase().contains("crp"), "Expected order type is personal but actual on UI "+firstOrderTypeFromUI);
		s_assert.assertTrue(lsdOrderPage.isFirstOrderDatePresent(), "Order date format is not present on UI");
		s_assert.assertAll();
	}

	//TC-2841 Retail Adhoc - order details on Order Cards
	@Test(alwaysRun=true)
	public void testRetailAdhocOrderDetailsOnOrderCards_2841q(){
		String subLinkOrders = "Orders";
		String orderTypePersonal = "Retail";
		String firstOrderNameFromUI = null;
		String firstOrderTypeFromUI = null;
		String firstOrderStatusFromUI = null;
		String firstOrderSVFromUI = null;
		boolean isOrderPresent = false;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderTypePersonal);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		firstOrderNameFromUI = lsdOrderPage.getFirstOrderUserName();
		firstOrderStatusFromUI = lsdOrderPage.getOrderStatus(1);
		firstOrderTypeFromUI = lsdOrderPage.getOrderType(1);
		firstOrderSVFromUI = lsdOrderPage.getFirstOrderPSQVValue();
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderNameFromUI), "Order name is not present on UI");
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderSVFromUI), "Order SV value is not present on UI");
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderStatusFromUI), "Order status value is not present on UI");
		s_assert.assertTrue(firstOrderTypeFromUI.toLowerCase().contains("retail"), "Expected order type is retail but actual on UI "+firstOrderTypeFromUI);
		s_assert.assertTrue(lsdOrderPage.isFirstOrderDatePresent(), "Order date format is not present on UI");
		s_assert.assertAll();
	}

	//TC-2848 Consultant Adhoc - Order details on Order Cards
	@Test(alwaysRun=true)
	public void testConsultantAdhocOrderDetailsOnOrderCards_2848q(){
		String subLinkOrders = "Orders";
		String orderType = "Consultant";
		String orderStatusProcessed = "Partially";
		String firstOrderNameFromUI = null;
		String firstOrderTypeFromUI = null;
		String firstOrderStatusFromUI = null;
		String firstOrderSVFromUI = null;
		boolean isOrderPresent = false;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderType);
		lsdOrderPage.clickOrderCategoryFilter(orderStatusProcessed);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		firstOrderNameFromUI = lsdOrderPage.getFirstOrderUserName();
		firstOrderStatusFromUI = lsdOrderPage.getOrderStatus(1);
		firstOrderTypeFromUI = lsdOrderPage.getOrderType(1);
		firstOrderSVFromUI = lsdOrderPage.getFirstOrderPSQVValue();
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderNameFromUI), "Order name is not present on UI");
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderSVFromUI), "Order SV value is not present on UI");
		s_assert.assertTrue(firstOrderStatusFromUI.toLowerCase().contains("partially"), "Expected order status is partially but actual on UI "+firstOrderStatusFromUI);
		s_assert.assertTrue(firstOrderTypeFromUI.toLowerCase().contains("retail"), "Expected order type is retail but actual on UI "+firstOrderTypeFromUI);
		s_assert.assertTrue(lsdOrderPage.isFirstOrderDatePresent(), "Order date format is not present on UI");
		s_assert.assertAll();
	}

	//TC-2843 Pulse Subscription - Order Details on Order Cards
	@Test(alwaysRun=true)
	public void testPulseSubscriptionOrderDetailsOnOrderCards_2843q(){
		String subLinkOrders = "Orders";
		String orderTypePersonal = "Personal";
		String orderStatusProcessed = "Processed";
		String firstOrderNameFromUI = null;
		String firstOrderTypeFromUI = null;
		String firstOrderStatusFromUI = null;
		String firstOrderSVFromUI = null;
		boolean isOrderPresent = false;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderTypePersonal);
		lsdOrderPage.clickOrderCategoryFilter(orderStatusProcessed);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		firstOrderNameFromUI = lsdOrderPage.getFirstOrderUserName();
		firstOrderStatusFromUI = lsdOrderPage.getOrderStatus(1);
		firstOrderTypeFromUI = lsdOrderPage.getOrderType(1);
		firstOrderSVFromUI = lsdOrderPage.getFirstOrderPSQVValue();
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderNameFromUI), "Order name is not present on UI");
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderSVFromUI), "Order SV value is not present on UI");
		s_assert.assertTrue(firstOrderStatusFromUI.toLowerCase().contains("processed"), "Expected order status is processed but actual on UI "+firstOrderStatusFromUI);
		s_assert.assertTrue(firstOrderTypeFromUI.toLowerCase().contains("pulse") || firstOrderTypeFromUI.toLowerCase().contains("crp"), "Expected order type is personal but actual on UI "+firstOrderTypeFromUI);
		s_assert.assertTrue(lsdOrderPage.isFirstOrderDatePresent(), "Order date format is not present on UI");
		s_assert.assertAll();
	}

	//TC-2844 PC Adhoc- Order Details on Order Cards
	@Test(alwaysRun=true)
	public void testPCAdhocOrderDetailsOnOrderCards_2844q(){
		String subLinkOrders = "Orders";
		String orderType = "Preferred";
		String orderStatusProcessed = "Processed";
		String firstOrderNameFromUI = null;
		String firstOrderTypeFromUI = null;
		String firstOrderStatusFromUI = null;
		String firstOrderSVFromUI = null;
		boolean isOrderPresent = false;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickOrderFilter();
		lsdOrderPage.clickOrderCategoryFilter(orderType);
		lsdOrderPage.clickOrderCategoryFilter(orderStatusProcessed);
		lsdOrderPage.clickApplyFilterBtn();
		if(lsdOrderPage.isOrderPresent()==isOrderPresent)
			lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		firstOrderNameFromUI = lsdOrderPage.getFirstOrderUserName();
		firstOrderStatusFromUI = lsdOrderPage.getOrderStatus(1);
		firstOrderTypeFromUI = lsdOrderPage.getOrderType(1);
		firstOrderSVFromUI = lsdOrderPage.getFirstOrderPSQVValue();
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderNameFromUI), "Order name is not present on UI");
		s_assert.assertTrue(lsdOrderPage.isValueEmpty(firstOrderSVFromUI), "Order SV value is not present on UI");
		s_assert.assertTrue(firstOrderStatusFromUI.toLowerCase().contains("processed"), "Expected order status is processed but actual on UI "+firstOrderStatusFromUI);
		s_assert.assertTrue(firstOrderTypeFromUI.toLowerCase().contains("pc") || firstOrderTypeFromUI.toLowerCase().contains("perks"), "Expected order type is personal but actual on UI "+firstOrderTypeFromUI);
		s_assert.assertTrue(lsdOrderPage.isFirstOrderDatePresent(), "Order date format is not present on UI");
		s_assert.assertAll();
	}

	//TC-2820 Display up to 13 months
	//TC-2821 Loading Orders screen
	@Test(alwaysRun=true)
	public void testLoadingOrdersScreen_2821q_2820q() throws ParseException{
		String subLinkOrders = "Orders";
		String selectedDate = null;
		int totalCountOfPreviousMonth = 0;
		lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		lsdOrderPage.clickDateFilter();
		totalCountOfPreviousMonth = lsdOrderPage.getTotalCountOfCommissionMenu();
		s_assert.assertTrue(totalCountOfPreviousMonth == 13, "Expected count of commission menu is 13 and actual on UI is "+totalCountOfPreviousMonth);
		s_assert.assertTrue(lsdOrderPage.verifyTheCommissionMonthsValue(), "Commissions values are not matching for 13 months");
		lsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		selectedDate = lsdOrderPage.getSelectedDate();
		selectedDate = lsdOrderPage.getMonthAndYearFromDate(selectedDate);
		s_assert.assertTrue(lsdOrderPage.isDateFilterAppliedSuccessfully(selectedDate), "Date filter is not applied successfully");
		s_assert.assertAll();
	}

}
