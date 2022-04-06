package com.rf.test.website.storeFront.hybris;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.storeFront.StoreFrontCartAutoShipPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.pages.website.storeFront.StoreFrontShippingInfoPage;
import com.rf.pages.website.storeFront.StoreFrontUpdateCartPage;
import com.rf.test.website.RFWebsiteBaseTest;

public class PC_AutoshipStatusValidationTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(PC_AutoshipStatusValidationTest.class.getName());

	/**
	 * AU
	 * Test Summary: EPS 569-Editing Autoship Date - PC Perks Delay Autoship Page - Future Subscriptions-Later date
	 * Assertions:-
	 * All text on autoship status page
	 * Verify selected date should be displayed same as next order date
	 * @throws InterruptedException
	 */
	/**
	 * CA
	 * Test Summary: EPS-540 : Editing Autoship Date - PC Perks Delay Autoship Page - Display - CA
	 * Assertions:-
	 * Verify date format in date picker field
	 * @throws InterruptedException
	 */
	/**
	 * CA
	 * Test Summary: EPS-543 : PC Perks Delay Autoship Page - Future Subscriptions - Later date
	 * Assertions:-
	 * All text on autoship status page
	 * Verify selected date should be displayed same as next order date
	 * @throws InterruptedException
	 */
	//Hybris Project-569 :: EPS 569-Editing Autoship Date - PC Perks Delay Autoship Page - Future Subscriptions-Later date
	//Hybris Project-3018 :: EPS-540 : Editing Autoship Date - PC Perks Delay Autoship Page - Display - CA
	//Hybris Project-3014 :: EPS-543 : PC Perks Delay Autoship Page - Future Subscriptions - Later date
	@Test 
	public void testPCPerksDelayFutureSubscriptionsLaterDate_3014_3018_569() throws InterruptedException {
		String day = null;;
		String monthFromCalendar = null;
		String yearFromCalendar = null;
		String nextOrderDateFromUI = null;
		String nextOrderDate = null;
		String managePCPerksOrderText = "Manage PC Perks Order";
		String youCanUpdateOrDelayText = "You can update or delay your PC Perks order by up to 60 days";
		String ifYouDelayYourOrderText = "that if you delay your order";
		String youStillEnjoyText = "you still enjoy the associated benefits";
		String includingSavingsAndShippingText = "including up to 10% savings on all R+F products and free shipping";
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");	
		s_assert.assertTrue(storeFrontPCUserPage.isNextPCPerksMiniCartDisplayed(), "next PC Perks Mini cart SHOULD be displayed");
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontHomePage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
		storeFrontBillingInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontBillingInfoPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontBillingInfoPage.selectNewBillingCardExpirationDate(TestConstants.CARD_EXPIRY_MONTH, TestConstants.CARD_EXP_YEAR);
		storeFrontBillingInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontBillingInfoPage.selectNewBillingCardAddress();
		storeFrontBillingInfoPage.selectUseThisBillingProfileFutureAutoshipChkbox();
		storeFrontBillingInfoPage.clickOnSaveBillingProfile();
		storeFrontBillingInfoPage.clickOnRodanAndFieldsLogo();
		storeFrontHomePage.clickPCPerksStatusLinkPresentOnWelcomeDropDown();
		storeFrontPCUserPage.clickEditOrderDateBtn();
		s_assert.assertTrue(storeFrontPCUserPage.isNextOrderDateTextDisplayed(), "Next order date text should be displayed");
		s_assert.assertTrue(storeFrontPCUserPage.isTextVisibleOnPCAutoshipStatusPage(managePCPerksOrderText), "Expected message "+managePCPerksOrderText+" is not getting displayed on UI");
		s_assert.assertTrue(storeFrontPCUserPage.isTextVisibleOnPCAutoshipStatusPage(youCanUpdateOrDelayText), "Expected message "+youCanUpdateOrDelayText+" is not getting displayed on UI");
		s_assert.assertTrue(storeFrontPCUserPage.isTextVisibleOnPCAutoshipStatusPage(ifYouDelayYourOrderText), "Expected message "+ifYouDelayYourOrderText+" is not getting displayed on UI");
		s_assert.assertTrue(storeFrontPCUserPage.isTextVisibleOnPCAutoshipStatusPage(youStillEnjoyText), "Expected message "+youStillEnjoyText+" is not getting displayed on UI");
		s_assert.assertTrue(storeFrontPCUserPage.isTextVisibleOnPCAutoshipStatusPage(includingSavingsAndShippingText), "Expected message "+includingSavingsAndShippingText+" is not getting displayed on UI");
		storeFrontPCUserPage.clickChangeMyAutoshipDateButton();
		s_assert.assertTrue(storeFrontPCUserPage.isCalendarOrTxtBoxForDateDisplayed(), "Calendar icon or text box for date is not getting displayed");
		s_assert.assertTrue(storeFrontPCUserPage.isDatePresentInFormat(), "Date is not present in MMM DD, YYYY format");
		storeFrontPCUserPage.clickCalendarIcon();
		monthFromCalendar = storeFrontPCUserPage.getMonthNameFromCalendar();
		yearFromCalendar = storeFrontPCUserPage.getYearFromCalendar();
		day = storeFrontPCUserPage.getAndCreateDayForCalendar();
		storeFrontPCUserPage.selectDayInCalendar(day);
		storeFrontPCUserPage.clickConfirmDateChangeBtn();
		nextOrderDateFromUI = storeFrontPCUserPage.getNextOrderDate();
		if(country.equalsIgnoreCase("ca")){
		nextOrderDate = monthFromCalendar+" "+day+", "+yearFromCalendar;
		}else{
			nextOrderDate = day+" "+monthFromCalendar+" "+yearFromCalendar;
		}
		s_assert.assertTrue(nextOrderDateFromUI.toLowerCase().contains(nextOrderDate.toLowerCase()), "Expected next order date is "+nextOrderDate+" but actual on UI is "+nextOrderDateFromUI);
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Verify the label "Cancel PC Perks Account" at Corp Site
	 * Assertions:-
	 * Verify user is terminated and not able to login
	 * @throws InterruptedException
	 */
	//Hybris Project-3235 :: Verify the label "Cancel PC Perks Account" at Corp Site
	@Test 
	public void testVerifyCancelPCPerksAccountAtCorpSite_3235() throws InterruptedException {
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");	
		s_assert.assertTrue(storeFrontPCUserPage.isNextPCPerksMiniCartDisplayed(), "next PC Perks Mini cart SHOULD be displayed");
		storeFrontHomePage.clickPCPerksStatusLinkPresentOnWelcomeDropDown();
		storeFrontPCUserPage.clickEditOrderDateBtn();
		s_assert.assertTrue(storeFrontPCUserPage.isCancelPCPerksLinkDisplayed(), "Cancel PC Perks link is not getting displayed");
		storeFrontPCUserPage.clickDelayOrCancelPCPerks();
		storeFrontPCUserPage.cancelMyPCPerksAct();
		storeFrontHomePage.loginAsConsultant(pcUserEmailID, password);
		s_assert.assertTrue(storeFrontHomePage.isCurrentURLShowsError(),"Inactive User doesn't get Login failed");
		s_assert.assertAll();
	}
	
	
	/**
	 * CA
	 * Test Summary: EPS-608-Test Case Covers Editing Autoship Date - PC Perks Delay Autoship Page - "Buy Now" Button on Edit Pag
	 * Assertions:-
	 * Verify order schedule and process date
	 * @throws InterruptedException
	 */
	//Hybris Project-3044 :: EPS-608-Test Case Covers Editing Autoship Date - PC Perks Delay Autoship Page - "Buy Now" Button on Edit Pag
	@Test
	public void testVerifyBuyNowFunctionalityAtAutoshipCart_3044() throws InterruptedException {
		String orderProcessedDateAtOrderReviewPageFromUI = null;
		String orderScheduledDateAtOrderReviewPageFromUI = null;
		String orderProcessedDateAtOrderReviewPage = null;
		String orderScheduledDate = null;
		String orderScheduleDateAtAutoshipTemplateFromUI = null;
		String shipAndProcessDate = null;
		String dateAtAutoshipTemplate = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");	
		s_assert.assertTrue(storeFrontPCUserPage.isNextPCPerksMiniCartDisplayed(), "next PC Perks Mini cart SHOULD be displayed");
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontHomePage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
		storeFrontBillingInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontBillingInfoPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontBillingInfoPage.selectNewBillingCardExpirationDate(TestConstants.CARD_EXPIRY_MONTH, TestConstants.CARD_EXP_YEAR);
		storeFrontBillingInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontBillingInfoPage.selectNewBillingCardAddress();
		storeFrontBillingInfoPage.selectUseThisBillingProfileFutureAutoshipChkbox();
		storeFrontBillingInfoPage.clickOnSaveBillingProfile();
		storeFrontBillingInfoPage.clickOnRodanAndFieldsLogo();
		storeFrontHomePage.clickOnAutoshipCart();
		storeFrontOrdersPage.clickBuyNowBtn();
		orderProcessedDateAtOrderReviewPageFromUI = storeFrontPCUserPage.getOrderProcessedDateAtOrderReview();
		orderScheduledDateAtOrderReviewPageFromUI = storeFrontPCUserPage.getOrderScheduledDateAtOrderReview();
		orderProcessedDateAtOrderReviewPage = storeFrontPCUserPage.createAndGetOrderProcessingDateForOrderReview();
		s_assert.assertTrue(orderProcessedDateAtOrderReviewPageFromUI.contains(orderProcessedDateAtOrderReviewPage), "Expected order process date is "+orderProcessedDateAtOrderReviewPage+" but actual on UI is "+orderProcessedDateAtOrderReviewPageFromUI);
		orderScheduledDate = storeFrontPCUserPage.createAndGetOrderScheduledDate();
		s_assert.assertTrue(orderScheduledDateAtOrderReviewPageFromUI.contains(orderScheduledDate), "Expected order Scheduled date is "+orderScheduledDate+" but actual on UI is "+orderScheduledDateAtOrderReviewPageFromUI);
		storeFrontPCUserPage.clickConfirmBtnOfBuyNow();
		storeFrontOrdersPage.refrehPageUntilAutoshipTemplateStatusAsPending();
		orderScheduleDateAtAutoshipTemplateFromUI = storeFrontOrdersPage.getScheduleDateOfAutoshipTemplate();
		if(country.equalsIgnoreCase("ca")){
		s_assert.assertTrue(orderScheduledDate.contains(orderScheduleDateAtAutoshipTemplateFromUI), "Expected order scheduled date at autoship template is "+orderScheduledDate+" but actual on UI is "+orderScheduleDateAtAutoshipTemplateFromUI);
		}else{
			dateAtAutoshipTemplate = storeFrontPCUserPage.createAndGetOrderScheduledDateForAU();
			s_assert.assertTrue(dateAtAutoshipTemplate.contains(orderScheduleDateAtAutoshipTemplateFromUI), "Expected order scheduled date at autoship template is "+dateAtAutoshipTemplate+" but actual on UI is "+orderScheduleDateAtAutoshipTemplateFromUI);
		}
		storeFrontOrdersPage.clickOnAutoshipCart();
		shipAndProcessDate = storeFrontCartAutoShipPage.getShipAndProcessDate();
		s_assert.assertTrue(shipAndProcessDate.contains(orderScheduledDate), "Expected order process date at cart page is "+orderScheduledDate+" but actual on UI is "+shipAndProcessDate);
		s_assert.assertAll();
	}

	/**
	 * AU
	 * Test Summary: EPS-609-Editing Autoship Date - PC Perks Delay Autoship Page - "Buy Now" Button on Order History Page - AUS
	 * Assertions:-
	 * Verify order schedule and process date
	 * @throws InterruptedException
	 */
	/**
	 * CA
	 * Test Summary: Editing Autoship Date - PC Perks Delay Autoship Page - "Buy Now" Button on Order History Page - CA
	 * Assertions:-
	 * Verify order schedule and process date
	 * @throws InterruptedException
	 */
	//Hybris Project-573 :: EPS-609-Editing Autoship Date - PC Perks Delay Autoship Page - "Buy Now" Button on Order History Page - AUS
	//Hybris Project-3045 :: EPS- 610-Test Case Covers Editing Autoship Date - PC Perks Delay Autoship Page - "Buy Now" Button on Order History Page - CA
	@Test 
	public void testVerifyBuyNowBtnFunctionalityAndDateAtOrderHistoryPage_3045_573() throws InterruptedException {
		String orderProcessedDateAtOrderReviewPageFromUI = null;
		String orderScheduledDateAtOrderReviewPageFromUI = null;
		String orderProcessedDateAtOrderReviewPage = null;
		String orderScheduledDate = null;
		String orderScheduleDateAtAutoshipTemplateFromUI = null;
		String shipAndProcessDate = null;
		String dateAtAutoshipTemplate = null;
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");	
		s_assert.assertTrue(storeFrontPCUserPage.isNextPCPerksMiniCartDisplayed(), "next PC Perks Mini cart SHOULD be displayed");
		storeFrontHomePage.clickOrdersLinkPresentOnWelcomeDropDown();
		storeFrontOrdersPage.clickBuyNowBtn();
		orderProcessedDateAtOrderReviewPageFromUI = storeFrontPCUserPage.getOrderProcessedDateAtOrderReview();
		orderScheduledDateAtOrderReviewPageFromUI = storeFrontPCUserPage.getOrderScheduledDateAtOrderReview();
		orderProcessedDateAtOrderReviewPage = storeFrontPCUserPage.createAndGetOrderProcessingDateForOrderReview();
		s_assert.assertTrue(orderProcessedDateAtOrderReviewPageFromUI.contains(orderProcessedDateAtOrderReviewPage), "Expected order process date is "+orderProcessedDateAtOrderReviewPage+" but actual on UI is "+orderProcessedDateAtOrderReviewPageFromUI);
		orderScheduledDate = storeFrontPCUserPage.createAndGetOrderScheduledDate();
		s_assert.assertTrue(orderScheduledDateAtOrderReviewPageFromUI.contains(orderScheduledDate), "Expected order process date is "+orderScheduledDate+" but actual on UI is "+orderScheduledDateAtOrderReviewPageFromUI);
		storeFrontPCUserPage.clickConfirmBtnOfBuyNow();
		storeFrontOrdersPage.refrehPageUntilAutoshipTemplateStatusAsPending();
		orderScheduleDateAtAutoshipTemplateFromUI = storeFrontOrdersPage.getScheduleDateOfAutoshipTemplate();
		if(country.equalsIgnoreCase("ca")){
			s_assert.assertTrue(orderScheduledDate.contains(orderScheduleDateAtAutoshipTemplateFromUI), "Expected order scheduled date at autoship template is "+orderScheduledDate+" but actual on UI is "+orderScheduleDateAtAutoshipTemplateFromUI);
			}else{
				dateAtAutoshipTemplate = storeFrontPCUserPage.createAndGetOrderScheduledDateForAU();
				s_assert.assertTrue(dateAtAutoshipTemplate.contains(orderScheduleDateAtAutoshipTemplateFromUI), "Expected order scheduled date at autoship template is "+dateAtAutoshipTemplate+" but actual on UI is "+orderScheduleDateAtAutoshipTemplateFromUI);
			}
		storeFrontOrdersPage.clickOnAutoshipCart();
		shipAndProcessDate = storeFrontCartAutoShipPage.getShipAndProcessDate();
		s_assert.assertTrue(shipAndProcessDate.contains(orderScheduledDate), "Expected order process date at cart page is "+orderScheduledDate+" but actual on UI is "+shipAndProcessDate);
		s_assert.assertAll();
	}

	
	/**
	 * AU
	 * Test Summary: EPS-565	Dev Only - Editing Autoship Date - PC Perks Delay Autoship Page - Date Pick
	 * Assertions:-
	 * Verify enabled and disabled calendar days
	 * @throws InterruptedException
	 */
	/**
	 * CA
	 * Test Summary: Editing Autoship Date - PC Perks Delay Autoship Page - Date Picking Logic - CA - Valid Credit Card
	 * Assertions:-
	 * Verify enabled and disabled calendar days
	 * @throws InterruptedException
	 */
	//Hybris Project-564 :: EPS-565	Dev Only - Editing Autoship Date - PC Perks Delay Autoship Page - Date Pick
	//Hybris Project-3016 :: EPS-541 : Editing Autoship Date - PC Perks Delay Autoship Page - Date Picking Logic - CA - Valid Credit Card
	@Test 
	public void testVerifyDatePickingLogicWithValidCreditCard_3016_564() throws InterruptedException {
		int currentDay = storeFrontHomePage.getCurrentDayFromCurrentDate();
		String nextMonth = storeFrontHomePage.getNextMonthFromCurrentDate();
		String monthFromUI = null;
		String dateAfter60Days = null;
		int dayAfterAddDays = 0;
		String monthAfterAddDays = null;
		String monthNameAfterTwoMonthFromCurrentDay = null;
		int startingDayForEnabled = 1;
		int startingDayForDisabled = 18;
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");	
		s_assert.assertTrue(storeFrontPCUserPage.isNextPCPerksMiniCartDisplayed(), "next PC Perks Mini cart SHOULD be displayed");
		storeFrontHomePage.clickPCPerksStatusLinkPresentOnWelcomeDropDown();
		storeFrontPCUserPage.clickEditOrderDateBtn();
		storeFrontPCUserPage.clickChangeMyAutoshipDateButton();
		storeFrontPCUserPage.clickCalendarIcon();
		currentDay = storeFrontPCUserPage.getNextDayFromCureentDay(currentDay);
		monthFromUI = storeFrontPCUserPage.getMonthNameFromCalendar();
		if(currentDay<=17){
			s_assert.assertTrue(storeFrontPCUserPage.isValidDateSelectedByDefault(currentDay),"Valid date is not selected by default, date between 1 to 17");
		}else{
			s_assert.assertTrue(monthFromUI.toLowerCase().contains(nextMonth),"Expected month is "+nextMonth+" but actual on UI is "+monthFromUI);
			s_assert.assertTrue(storeFrontPCUserPage.isValidDateSelectedByDefault(1),"Valid date is not selected by default, date between 1 to 17");
		}
		if(currentDay<=17){
			s_assert.assertTrue(storeFrontPCUserPage.isValidDaysEnabledOfMonth(currentDay),"Valid days is not enabled of current month");
			storeFrontPCUserPage.clickNextIconOfCalendar();
			s_assert.assertTrue(storeFrontPCUserPage.isValidDaysEnabledOfMonth(1),"Valid days is not enabled of next month");
			storeFrontPCUserPage.clickNextIconOfCalendar();
			dateAfter60Days = storeFrontPCUserPage.getDateAfterAddingTheDays(61);
			dayAfterAddDays = storeFrontPCUserPage.getDayFromDate(dateAfter60Days);
			monthAfterAddDays = storeFrontPCUserPage.getMonthFromDate(dateAfter60Days);
			monthNameAfterTwoMonthFromCurrentDay = storeFrontPCUserPage.getMonthNameFromCalendar();
			s_assert.assertTrue(storeFrontPCUserPage.isValidDaysEnabledTillDay(dayAfterAddDays),"Valid days is not enabled of the month "+monthAfterAddDays);
			s_assert.assertTrue(storeFrontPCUserPage.isValidDaysDisabledTillEnd(dayAfterAddDays),"Valid days is not disbaled of the month "+monthAfterAddDays);
			s_assert.assertTrue(monthNameAfterTwoMonthFromCurrentDay.toLowerCase().contains(monthAfterAddDays), "Expected month name after added 60 days is "+monthNameAfterTwoMonthFromCurrentDay+" but actual on UI is "+monthAfterAddDays);
			storeFrontPCUserPage.clickNextIconOfCalendar();
			s_assert.assertTrue(storeFrontPCUserPage.isAllDaysDisabledOfMonth(), "All days are not disabled of the month");
		}else{
			s_assert.assertTrue(storeFrontPCUserPage.isValidDaysEnabledTillDay(startingDayForEnabled),"Valid days is not enabled of the current month for date after 17");
			s_assert.assertTrue(storeFrontPCUserPage.isValidDaysDisabledTillEnd(startingDayForDisabled),"Valid days is not disbaled of the current month for date after 17h");
			storeFrontPCUserPage.clickNextIconOfCalendar();
			s_assert.assertTrue(storeFrontPCUserPage.isValidDaysEnabledTillDay(startingDayForEnabled),"Valid days is not enabled of the next month for date after 17");
			s_assert.assertTrue(storeFrontPCUserPage.isValidDaysDisabledTillEnd(startingDayForDisabled),"Valid days is not disbaled of the next month for date after 17h");
			storeFrontPCUserPage.clickNextIconOfCalendar();
			s_assert.assertTrue(storeFrontPCUserPage.isAllDaysDisabledOfMonth(), "All days are not disabled of the month");
		}
		s_assert.assertAll();
	}
	
	/**
	 * AU
	 * Test Summary: EPS-584-Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now - AUS- CONFIRM Order
	 * Assertions:-
	 * Verify order schedule and process date and next order date
	 * @throws InterruptedException
	 */
	/**
	 * CA
	 * Test Summary: EPS-585-Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now - CA
	 * Assertions:-
	 * Verify order schedule and process date and next order date
	 * @throws InterruptedException
	 */
	
	//Hybris Project-3043 :: EPS-585-Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now - CA
	//Hybris Project-571 :: EPS-584-Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now - AUS- CONFIRM Order
	@Test 
	public void testVerifyBuyNowFunctionalityAtPCPerksDealyAutoshipPage_3043_571() throws InterruptedException {
		String orderProcessedDateAtOrderReviewPageFromUI = null;
		String orderScheduledDateAtOrderReviewPageFromUI = null;
		String orderProcessedDateAtOrderReviewPage = null;
		String orderScheduledDate = null;
		String orderScheduleDateAtAutoshipTemplateFromUI = null;
		String shipAndProcessDate = null;
		String dateAtAutoshipTemplate = null;
		String completeOrderScheduledDate = null;
		String nextOrderDate = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");	
		s_assert.assertTrue(storeFrontPCUserPage.isNextPCPerksMiniCartDisplayed(), "next PC Perks Mini cart SHOULD be displayed");
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontHomePage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
		storeFrontBillingInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontBillingInfoPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontBillingInfoPage.selectNewBillingCardExpirationDate(TestConstants.CARD_EXPIRY_MONTH, TestConstants.CARD_EXP_YEAR);
		storeFrontBillingInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontBillingInfoPage.selectNewBillingCardAddress();
		storeFrontBillingInfoPage.selectUseThisBillingProfileFutureAutoshipChkbox();
		storeFrontBillingInfoPage.clickOnSaveBillingProfile();
		storeFrontBillingInfoPage.clickOnRodanAndFieldsLogo();
		storeFrontHomePage.clickPCPerksStatusLinkPresentOnWelcomeDropDown();
		storeFrontPCUserPage.clickEditOrderDateBtn();
		storeFrontPCUserPage.clickChangeMyAutoshipDateButton();
		storeFrontOrdersPage.clickBuyNowBtn();
		orderProcessedDateAtOrderReviewPageFromUI = storeFrontPCUserPage.getOrderProcessedDateAtOrderReview();
		orderScheduledDateAtOrderReviewPageFromUI = storeFrontPCUserPage.getOrderScheduledDateAtOrderReview();
		completeOrderScheduledDate = storeFrontPCUserPage.getAndCreateOrderScheduledDateAtOrderReview();
		orderProcessedDateAtOrderReviewPage = storeFrontPCUserPage.createAndGetOrderProcessingDateForOrderReview();
		s_assert.assertTrue(orderProcessedDateAtOrderReviewPageFromUI.contains(orderProcessedDateAtOrderReviewPage), "Expected order process date is "+orderProcessedDateAtOrderReviewPage+" but actual on UI is "+orderProcessedDateAtOrderReviewPageFromUI);
		orderScheduledDate = storeFrontPCUserPage.createAndGetOrderScheduledDate();
		s_assert.assertTrue(orderScheduledDateAtOrderReviewPageFromUI.contains(orderScheduledDate), "Expected order process date is "+orderScheduledDate+" but actual on UI is "+orderScheduledDateAtOrderReviewPageFromUI);
		storeFrontPCUserPage.clickConfirmBtnOfBuyNow();
		storeFrontOrdersPage.refrehPageUntilAutoshipTemplateStatusAsPending();
		orderScheduleDateAtAutoshipTemplateFromUI = storeFrontOrdersPage.getScheduleDateOfAutoshipTemplate();
		if(country.equalsIgnoreCase("ca")){
			s_assert.assertTrue(orderScheduledDate.contains(orderScheduleDateAtAutoshipTemplateFromUI), "Expected order scheduled date at autoship template is "+orderScheduledDate+" but actual on UI is "+orderScheduleDateAtAutoshipTemplateFromUI);
			}else{
				dateAtAutoshipTemplate = storeFrontPCUserPage.createAndGetOrderScheduledDateForAU();
				s_assert.assertTrue(dateAtAutoshipTemplate.contains(orderScheduleDateAtAutoshipTemplateFromUI), "Expected order scheduled date at autoship template is "+dateAtAutoshipTemplate+" but actual on UI is "+orderScheduleDateAtAutoshipTemplateFromUI);
			}
		storeFrontHomePage.clickPCPerksStatusLinkPresentOnWelcomeDropDown();
		nextOrderDate = storeFrontPCUserPage.getNextOrderDate();
		s_assert.assertTrue(nextOrderDate.toLowerCase().contains(completeOrderScheduledDate), "Expected next order date is "+nextOrderDate+" but actual on UI is "+completeOrderScheduledDate);
		storeFrontOrdersPage.clickOnRodanAndFieldsLogo();
		storeFrontOrdersPage.clickOnAutoshipCart();
		shipAndProcessDate = storeFrontCartAutoShipPage.getShipAndProcessDate();
		s_assert.assertTrue(shipAndProcessDate.contains(orderScheduledDate), "Expected order process date at cart page is "+orderScheduledDate+" but actual on UI is "+shipAndProcessDate);
		s_assert.assertAll();
	}
	
	/**
	 * AU
	 * Test Summary: EPS-584-Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now - AUS- CANCEL Order
	 * Assertions:-
	 * Verify cancel button functionality at buy now review page
	 * @throws InterruptedException
	 */
	/**
	 * CA
	 * Test Summary: EPS-585-Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now Cancel - CA
	 * Assertions:-
	 * Verify cancel button functionality at buy now review page
	 * @throws InterruptedException
	 */
	//Hybris Project-572 :: EPS-584-Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now - AUS- CANCEL Order
	//Hybris Project-3078 :: EPS-585-Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now Cancel - CA
	@Test 
	public void testCancelBtnFunctionalityAtPCPerksDelayAutoshipPage_3078_572() throws InterruptedException {
		String nextOrderDateBeforeCancel = null;
		String nextOrderDateAfterCancel = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		String currentURL = null;
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");	
		s_assert.assertTrue(storeFrontPCUserPage.isNextPCPerksMiniCartDisplayed(), "next PC Perks Mini cart SHOULD be displayed");
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontHomePage.clickBillingInfoLinkPresentOnWelcomeDropDown();
		storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
		storeFrontBillingInfoPage.enterNewBillingCardNumber(TestConstants.CARD_NUMBER);
		storeFrontBillingInfoPage.enterNewBillingNameOnCard(newBillingProfileName+" "+lastName);
		storeFrontBillingInfoPage.selectNewBillingCardExpirationDate(TestConstants.CARD_EXPIRY_MONTH, TestConstants.CARD_EXP_YEAR);
		storeFrontBillingInfoPage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontBillingInfoPage.selectNewBillingCardAddress();
		storeFrontBillingInfoPage.selectUseThisBillingProfileFutureAutoshipChkbox();
		storeFrontBillingInfoPage.clickOnSaveBillingProfile();
		storeFrontBillingInfoPage.clickOnRodanAndFieldsLogo();
		storeFrontHomePage.clickPCPerksStatusLinkPresentOnWelcomeDropDown();
		nextOrderDateBeforeCancel = storeFrontPCUserPage.getNextOrderDate();
		storeFrontPCUserPage.clickEditOrderDateBtn();
		storeFrontPCUserPage.clickChangeMyAutoshipDateButton();
		storeFrontPCUserPage.clickBuyNowBtn();
		storeFrontPCUserPage.clickCancelButton();
		currentURL = storeFrontPCUserPage.getCurrentURL();
		nextOrderDateAfterCancel = storeFrontPCUserPage.getNextOrderDate();
		s_assert.assertTrue(nextOrderDateAfterCancel.contains(nextOrderDateBeforeCancel), "Expected next order date is "+nextOrderDateBeforeCancel+" but actual on UI is "+nextOrderDateAfterCancel);
		s_assert.assertTrue(currentURL.contains("autoship-status"), "Expected url should contains autoship-status but actual on UI is "+currentURL);
		//Verifying Cancel button functionality through autoship cart page
		currentURL = null;
		storeFrontPCUserPage.clickOnRodanAndFieldsLogo();
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontHomePage.clickEditPCPerksLinkPresentOnWelcomeDropDown();
		nextOrderDateBeforeCancel = storeFrontCartAutoShipPage.getShipAndProcessDate();
		storeFrontPCUserPage.clickBuyNowBtn();
		storeFrontPCUserPage.clickCancelButton();
		currentURL = storeFrontPCUserPage.getCurrentURL();
		nextOrderDateAfterCancel = storeFrontCartAutoShipPage.getShipAndProcessDate();
		s_assert.assertTrue(nextOrderDateAfterCancel.contains(nextOrderDateBeforeCancel), "Expected next order date is "+nextOrderDateBeforeCancel+" but actual on UI is through autoship cart "+nextOrderDateAfterCancel);
		s_assert.assertTrue(currentURL.contains("/cart/autoship"), "Expected url should contains /cart/autoship but actual on UI is through autoship cart "+currentURL);
		//Verifying Cancel button functionality through orders page
		currentURL = null;
		storeFrontPCUserPage.clickOnWelcomeDropDown();
		storeFrontPCUserPage.clickOrdersLinkPresentOnWelcomeDropDown();
		nextOrderDateBeforeCancel = storeFrontOrdersPage.getScheduleDateOfAutoshipTemplate();
		storeFrontPCUserPage.clickBuyNowBtn();
		storeFrontPCUserPage.clickCancelButton();
		currentURL = storeFrontPCUserPage.getCurrentURL();
		nextOrderDateAfterCancel = storeFrontOrdersPage.getScheduleDateOfAutoshipTemplate();
		s_assert.assertTrue(nextOrderDateAfterCancel.contains(nextOrderDateBeforeCancel), "Expected next order date is "+nextOrderDateBeforeCancel+" but actual on UI is through orders page "+nextOrderDateAfterCancel);
		s_assert.assertTrue(currentURL.contains("/orders"), "Expected url should contains /orders but actual on UI is through orders page "+currentURL);
		s_assert.assertAll();
	}

	/**
	 * AU
	 * Test Summary: EPS-760 : (UAT Feedback) Add Change Date button to Edit Subscription Cart Page - AUS
	 * Assertions:-
	 * Verify navigation of Chnage date button at autoship cart
	 * @throws InterruptedException
	 */
	/**
	 * CA
	 * Test Summary: EPS-761 : (UAT Feedback) Add Change Date button to Edit Subscription Cart Page - CA
	 * Assertions:-
	 * Verify navigation of Chnage date button at autoship cart
	 * @throws InterruptedException
	 */
	//Hybris Project-601 :: EPS-760 : (UAT Feedback) Add Change Date button to Edit Subscription Cart Page - AUS
	//Hybris Project-3105 :: EPS-761 : (UAT Feedback) Add Change Date button to Edit Subscription Cart Page - CA
	@Test 
	public void testVerifyChangeButtonToAutoshipCartPage_3105_601() throws InterruptedException {
		String currentURL = null;
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		storeFrontHomePage.loginAsPCUser(pcUserEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application from PC");	
		storeFrontHomePage.clickOnWelcomeDropDown();
		storeFrontHomePage.clickEditPCPerksLinkPresentOnWelcomeDropDown();
		s_assert.assertTrue(storeFrontPCUserPage.isChangeDateBtnDisplayed(), "Change date button is not getting displayed at autoship cart page");
		storeFrontPCUserPage.clickChangeDateButton();
		currentURL = storeFrontPCUserPage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("autoship-status"), "Expected url should contains autoship-status but actual on UI is "+currentURL);
		s_assert.assertTrue(storeFrontPCUserPage.isEditOrderDateBtnDisplayed(), "Edit order date button is not getting displayed");
		s_assert.assertAll();
	}

}