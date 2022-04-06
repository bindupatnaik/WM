package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.TimeZone;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ManageMyAccountTest extends RFHeirloomWebsiteMyAccountBaseTest {

	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(driver.getURL()+"/myoffice/pcperksstatus");
	}

	@BeforeGroups("ManageMyAccountCorp")
	public void loginWithPCCorpAndNavigateToEditPCPerks(){
		int randomNum = CommonUtils.getRandomNum(99999, 100000);
		String billingProfileName= billingName+randomNum;
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		navigateToManageMyAccountPage();
	}

	@BeforeGroups("ManageMyAccountAndLoginWithPC")
	public void loginWithPCHavingInvalidCardCorpAndNavigateToEditPCPerks(){
		logout();
		driver.get(driver.getURL());
		getPCUserHasBillingProfileWithInvalidCardAndLogin();
		navigateToManageMyAccountPage();
	}


	/**
	 * Jira Story Id: MAIN-7944
	 * qTest Id: TC-3090
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "MAIN-7944 :  Editing Autoship Date - PC Perk Status Page - Display(corp)")
	public void testEditingAutoshipDatePCPerksStatusPageDisplayFromCorp_3090() {
		String currentUrl = null;
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNextOrderDateTextDisplayed(), "Next order date text is not displayed");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderDateBtnDisplayed(), "Edit order btn is not displayed");
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		currentUrl = storeFrontHeirloomEditPCPerksPage.getCurrentURL();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisplayed() &&  currentUrl.contains("/PcDelayAutoship"), "User is not redirected to PCPerks Dealy page, Expected url should contains /PcDelayAutoship but actual on UI is "+currentUrl);
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-7946
	 * qTest Id: TC-3092
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "Verify change Date Picker to next 61 days max")
	public void testVerifyChangeDatePickerTONext61DaysMax_3092() {
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickDateFieldOrCalendarIcon();
		storeFrontHeirloomEditPCPerksPage.clickNextIconOfCalendar();
		storeFrontHeirloomEditPCPerksPage.clickNextIconOfCalendar();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNextIconDisabledOfCalendar(), "Next button is not enabled after 61 days");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isDateBetween21To30Or31GrayedOut(),
				"Between 21 to 30/31 dates are not grayed after 61 days");
		storeFrontHeirloomEditPCPerksPage.clickBackIconOfCalendar();
		storeFrontHeirloomEditPCPerksPage.selectValidPCPerkdDate("3");
		storeFrontHeirloomEditPCPerksPage.clickChangeDateBtn();
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisplayed(),"User is not able to select a valid date");
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-7947
	 * qTest Id: TC-3457
	 * description = "Editing Autoship Date - Order Processing Select Invalid CC Billing"
	 */
	/**
	 * Jira Story Id: MAIN-7946
	 * qTest Id: TC-3095
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountAndLoginWithPC",description = "Verify Autoship date with failed default credit card")
	public void testVerifyAutoshipDateWithFailedDefaultCreditCard_3095_3457() {
		String errorMsg = "We had an issue with your default credit card";
		int randomNum = CommonUtils.getRandomNum(99999, 100000);
		String billingProfileName= billingName+randomNum;
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisabled(),"Buy now btn is not disabled with invalid credit card profile");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isChangeDateBtnDisabled(),"Change date btn is not disabled with invalid credit card profile");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isOrderMessageTextVisible(errorMsg),"Error message "+errorMsg+" is not getting displayed");
		navigateToBillingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewBillingProfileLink();
		storeFrontHeirloomBillingAndShippingProfilePage.addBillingDetailsCheckTheCheckboxForFutureOrdersAndSave(billingProfileName, nameOnCard, cardNumber, expMonth, expYear, CVV, billingProfileFirstName, billingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedBillingProfilePresent(billingProfileName),"Newly created billing profile name not present on billing page");
		navigateToManageMyAccountPage();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisabled(),"Buy now btn is disabled with valid credit card profile");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isOrderMessageTextVisible(errorMsg),"Error message "+errorMsg+" is getting displayed with valid credit card profile");
		storeFrontHeirloomEditPCPerksPage.clickDateFieldOrCalendarIcon();
		storeFrontHeirloomEditPCPerksPage.clickNextIconOfCalendar();
		storeFrontHeirloomEditPCPerksPage.selectValidPCPerkdDate("3");
		storeFrontHeirloomEditPCPerksPage.clickChangeDateBtn();
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisplayed(),"User is not able to select a valid date");
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-8146
	 * qTest Id: TC-3310
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "MAIN-8146 : Verify Change Date Button is present")
	public void testVerifyChangeDateButtonIsNotPresent_3310() {
		String addressName = "Home";
		String currentURL = null;
		TimeZone timeZone = TimeZone.getTimeZone("US/Pacific");//"US/Pacific"
		String currenDate = CommonUtils.getCurrentDate("MMMM dd, yyyy", timeZone);
		String nextTwoMonth = null;
		String currentUrl = null;
		String firstThreeDigitOfMonth = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum1 = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum1;
		String shippingProfileName=shippingProfileFirstName+randomNum;
		String billingProfileFirstName = "newFName"+randomNum1;
		String monthCount = null;
		String monthFullName = null;
		int day = storeFrontHeirloomEditPCPerksPage.getCurrentDayFromCurrentDate();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		currentURL = storeFrontHeirloomEditPCPerksPage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("PcAutoshipBuyNowReview"), "Expected url should contains PcAutoshipBuyNowReview but actual on UI is "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isConfirmBtnDisplayedForBuynow(),"Confirm button is not getting displayed");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isCancelBtnDisplayedForBuynow(),"Cancel button is not getting displayed");
		String txtMsg = storeFrontHeirloomEditPCPerksPage.getTextMsgOfBuyNowReviewPage();
		s_assert.assertTrue(txtMsg.contains("your pc perks subscription order will be processed on"), "Expected message should contains  Your PC Perks subscription order will be processed on but actual on UI is "+txtMsg);
		s_assert.assertTrue(txtMsg.toLowerCase().contains(currenDate.toLowerCase()), "Expected message should contains current date i.e. "+currenDate+" but actual on UI is "+txtMsg);
		nextTwoMonth = storeFrontHeirloomEditPCPerksPage.getNextTwoMonthDateFromDate();
		s_assert.assertTrue(txtMsg.contains(nextTwoMonth.toLowerCase()), "1. Expected message should contains next two month date i.e"+nextTwoMonth+" but actual on UI is "+txtMsg);
		firstThreeDigitOfMonth = storeFrontHeirloomEditPCPerksPage.getFirstThreeDigitOfMonth(nextTwoMonth);
		monthCount = storeFrontHeirloomHomePage.getMonthCountFromMonthName(firstThreeDigitOfMonth);
		monthFullName = storeFrontHeirloomEditPCPerksPage.getMonthFullnameFromCount(monthCount);
		String dayCount = storeFrontHeirloomEditPCPerksPage.convertDayInDoubleDigit(day);
		if(day<20)
			s_assert.assertTrue(txtMsg.contains(monthFullName+" "+dayCount), "2. Expected message should contains next two month date i.e"+monthFullName+" "+dayCount+" but actual on UI is "+txtMsg);
		else
			s_assert.assertTrue(txtMsg.contains(monthFullName+" "+20), "2. Expected message should contains next two month date i.e"+monthFullName+" "+20+" but actual on UI is "+txtMsg);

		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isViewDetailsLinkDisplayed(),"Order details page is not getting displayed");
		navigateToManageMyAccountPage();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickChangeLinkUnderShippingForBuyNow();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNewlyCreatedProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		storeFrontHeirloomEditPCPerksPage.clickChangeLinkUnderBillingForBuyNow();
		storeFrontHeirloomHomePage.enterBillingInfoForEditCRP(billingProfileName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNewlyCreatedProfilePresent(billingProfileFirstName),"Newly Created Billing profile not present on page");
		storeFrontHeirloomEditPCPerksPage.clickOnCancel();
		currentUrl = storeFrontHeirloomEditPCPerksPage.getCurrentURL();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisplayed() &&  currentUrl.contains("/PcDelayAutoship"), "User is not redirected to PCPerks Dealy page, Expected url should contains /PcDelayAutoship but actual on UI is "+currentUrl);
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-7946
	 * qTest Id: TC-3096
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "Verify default date between 21st and 31st")
	public void testVerifyDefaultDateBetween21stAnd31st_3096(){
		String monthFromUI = null;
		int day = storeFrontHeirloomEditPCPerksPage.getCurrentDayFromCurrentDate();
		String currentMonth = storeFrontHeirloomEditPCPerksPage.getCurrentMonthFromCurrentDate();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickDateFieldOrCalendarIcon();
		monthFromUI = storeFrontHeirloomEditPCPerksPage.getMonthFromCalendar();
		if(day<=19){
			s_assert.assertTrue(monthFromUI.equalsIgnoreCase(currentMonth), "Expected default month in calendar is "+currentMonth+" Actual on UI is "+monthFromUI);
			s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isDayEnabledInCalendar("20"), "Expexted day 20 is not enbaled for current month");
		}else{
			currentMonth = storeFrontHeirloomEditPCPerksPage.getNextMonthFromCurrentDate();
			s_assert.assertTrue(monthFromUI.toLowerCase().contains(currentMonth), "Expected default month in calendar is "+currentMonth+" Actual on UI is "+monthFromUI);
			s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isDayEnabledInCalendar("1"), "Expexted day 1 is not enbaled for next month");
		}
		s_assert.assertAll();		
	}	

	/**
	 * Jira Story Id: MAIN-7947
	 * qTest Id: TC-3149
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "MAIN-7947 : Editing Autoship Date - Order Processing Cancel")
	public void testEditingAutoshipDateOrderProcessingCancel_3149() {
		String beforeNextOrderDate = null;
		String afterNextOrderDate = null;
		beforeNextOrderDate = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickOnCancel();
		navigateToManageMyAccountPage();
		afterNextOrderDate = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		s_assert.assertTrue(afterNextOrderDate.equalsIgnoreCase(beforeNextOrderDate), "Expected order date is "+beforeNextOrderDate+" but actual on UI is "+afterNextOrderDate);
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-7948
	 * qTest Id: TC-3458
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "Verify PC Autoship Change date and BuyNow")
	public void testVerifyPCAutoshipChangeDateAndBuyNow_3458() {
		String nextOrderDateFromUI = null;
		String afterNextOrderDate = null;
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickDateFieldOrCalendarIcon();
		storeFrontHeirloomEditPCPerksPage.clickNextIconOfCalendar();
		storeFrontHeirloomEditPCPerksPage.selectValidPCPerkdDate("3");
		storeFrontHeirloomEditPCPerksPage.clickChangeDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickBackToMyAccountBtn();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		if(storeFrontHeirloomEditPCPerksPage.isLatestOrderDisplayed())
		navigateToManageMyAccountPage();
		afterNextOrderDate = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		nextOrderDateFromUI = storeFrontHeirloomEditPCPerksPage.createNextOrderDateAfterTwoMonth();
		s_assert.assertTrue(afterNextOrderDate.equalsIgnoreCase(nextOrderDateFromUI), "Expected order date is "+nextOrderDateFromUI+" but actual on UI is "+afterNextOrderDate);
		s_assert.assertAll();		
	}
	
	/**
	 * Jira Story Id: MAIN-8978
	 * qTest Id: TC-3469
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "MAIN-8978 : Verifynext scheduled date is correct when buy is clicked")
	public void testVerifyNextScheduledDateIsCorrectWhenBuyNowClicked_3469() {
		String nextOrderDateFromUI = null;
		String afterNextOrderDate = null;
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		navigateToManageMyAccountPage();
		afterNextOrderDate = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		nextOrderDateFromUI = storeFrontHeirloomEditPCPerksPage.createNextOrderDateAfterTwoMonth();
		s_assert.assertTrue(afterNextOrderDate.equalsIgnoreCase(nextOrderDateFromUI), "Expected order date is "+nextOrderDateFromUI+" but actual on UI is "+afterNextOrderDate);
		s_assert.assertAll();	
	}

	/**
	 * Jira Story Id: MAIN-7946
	 * qTest Id: TC-3111
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "Verify default date to select should be always Tomorrow")
	public void testVerifyDefaultDateToSelectShouldBeAlwaysTomorrow_3111() {
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickDateFieldOrCalendarIcon();
		int day = storeFrontHeirloomEditPCPerksPage.getCurrentDayFromCurrentDate();
		if(day<=20 && day>1){
			s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isPreviousDaysDisbaledFromCurrentDay(day),"Previous days are not disabled of current month from current day");
		}
		if(day<=19){
			s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isFollowingDaysEnabledFromCurrentDayTill20(day),"Following days are not enabled of current month from current day");
		}
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-7947
	 * qTest Id: TC-3456
	 * description ="Editing Autoship Date - Order Processing change Shipping date"
	 */
	/**
	 * Jira Story Id: MAIN-8146
	 * qTest Id: TC-3146
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "MAIN-8146 : Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now and confirm")
	public void testEditingAutoshipDateBuyNowAndConfirm_3146_3456() {
		String addressName = "Home";
		String currentURL = null;
		TimeZone timeZone = TimeZone.getTimeZone("US/Pacific");//"US/Pacific"
		String currenDate = CommonUtils.getCurrentDate("MMMM dd, yyyy", timeZone);
		String nextTwoMonth = null;
		String firstThreeDigitOfMonth = null;
		String monthCount = null;
		String monthFullName = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum1 = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName= billingName+randomNum1;
		String shippingProfileName=shippingProfileFirstName+randomNum;
		String billingProfileFirstName = "newFName"+randomNum1;
		int day = storeFrontHeirloomEditPCPerksPage.getCurrentDayFromCurrentDate();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		currentURL = storeFrontHeirloomEditPCPerksPage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("PcAutoshipBuyNowReview"), "Expected url should contains PcAutoshipBuyNowReview but actual on UI is "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isConfirmBtnDisplayedForBuynow(),"Confirm button is not getting displayed");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isCancelBtnDisplayedForBuynow(),"Cancel button is not getting displayed");
		String txtMsg = storeFrontHeirloomEditPCPerksPage.getTextMsgOfBuyNowReviewPage();
		s_assert.assertTrue(txtMsg.contains("your pc perks subscription order will be processed on"), "Expected message should contains  Your PC Perks subscription order will be processed on but actual on UI is "+txtMsg);
		s_assert.assertTrue(txtMsg.toLowerCase().contains(currenDate.toLowerCase()), "Expected message should contains current date i.e. "+currenDate+" but actual on UI is "+txtMsg);
		nextTwoMonth = storeFrontHeirloomEditPCPerksPage.getNextTwoMonthDateFromDate();
		s_assert.assertTrue(txtMsg.contains(nextTwoMonth.toLowerCase()), "Expected message should contains next two month date i.e"+nextTwoMonth+" but actual on UI is "+txtMsg);
		firstThreeDigitOfMonth = storeFrontHeirloomEditPCPerksPage.getFirstThreeDigitOfMonth(nextTwoMonth);
		monthCount = storeFrontHeirloomHomePage.getMonthCountFromMonthName(firstThreeDigitOfMonth);
		monthFullName = storeFrontHeirloomEditPCPerksPage.getMonthFullnameFromCount(monthCount);
		String dayCount = storeFrontHeirloomEditPCPerksPage.convertDayInDoubleDigit(day);
		if(day<20)
			s_assert.assertTrue(txtMsg.contains(monthFullName+" "+dayCount), "Expected message should contains next two month date i.e"+monthFullName+" "+dayCount+" but actual on UI is "+txtMsg);
		else
			s_assert.assertTrue(txtMsg.contains(monthFullName+" "+20), "Expected message should contains next two month date i.e"+monthFullName+" "+20+" but actual on UI is "+txtMsg);

		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isViewDetailsLinkDisplayed(),"Order details page is not getting displayed");
		navigateToManageMyAccountPage();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickChangeLinkUnderShippingForBuyNow();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNewlyCreatedProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		storeFrontHeirloomEditPCPerksPage.clickChangeLinkUnderBillingForBuyNow();
		storeFrontHeirloomHomePage.enterBillingInfoForEditCRP(billingProfileName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNewlyCreatedProfilePresent(billingProfileFirstName),"Newly Created Billing profile not present on page");
		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		storeFrontHeirloomHomePage.clickViewDetailsLinkOfFirstOrderForPC();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNewlyCreatedProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on order details page");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNewlyCreatedProfilePresent(billingProfileFirstName),"Newly Created Billing profile not present on order details page");
		s_assert.assertAll();		
	}


	/**
	 * Jira Story Id: MAIN-7947
	 * qTest Id: TC-3455
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "MAIN-7947 : Editing Autoship Date - Order Processing")
	public void testVerifyEditingAutoshipDateOrderProcessing_3455() {
		String afterOrderNumber = null;
		String expectedOrderType = "PC Auto-ship";
		String orderTypeFromUI = null;
		String nextOrderDateFromUI = null;
		String afterNextOrderDate = null;
		String beforeOrderNumber = null;
		navigateToOrderHistory();
		beforeOrderNumber = storeFrontHeirloomEditPCPerksPage.getFirstOrderNumber();
		navigateToManageMyAccountPage();
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isOrderPrecessingBtnPresent(), "Order processing btn is not present");
		afterOrderNumber = storeFrontHeirloomEditPCPerksPage.getFirstOrderNumber();
		s_assert.assertFalse(afterOrderNumber.equalsIgnoreCase(beforeOrderNumber), "Order is not getting placed");
		navigateToManageMyAccountPage();
		afterNextOrderDate = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		nextOrderDateFromUI = storeFrontHeirloomEditPCPerksPage.createNextOrderDateAfterTwoMonth();
		s_assert.assertTrue(afterNextOrderDate.equalsIgnoreCase(nextOrderDateFromUI), "Expected order date is "+nextOrderDateFromUI+" but actual on UI is "+afterNextOrderDate);
		storeFrontHeirloomHomePage.createAndGetNSC4Url();
		loginToNSCApplication();
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.enterOrderIDInInputField(afterOrderNumber);
		orderTypeFromUI = nscore4OrdersTabPage.getOrderType();
		s_assert.assertTrue(orderTypeFromUI.contains(expectedOrderType), "Expected order type is "+expectedOrderType+" but actual on UI is "+orderTypeFromUI);
		s_assert.assertAll();		
	}

	/**
	 * Jira Story Id: MAIN-7948 
	 * qTest Id: TC-3306
	 */
	@Test(alwaysRun = true, groups = "ManageMyAccountCorp", description = "Verify PC Autoship Order Processing between 21st to 31st")
	public void testPCAutoshipOrderProcessing_3306() {
		String nextOrderDateFromUI = null;
		String afterNextOrderDate = null;
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		s_assert.assertTrue(driver.getCurrentUrl().contains("MyOffice/PcDelayAutoship"),
				"User is not navigated to PcDelayAutoship page");

		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isLatestOrderDisplayed(),
				"Lastest Order On Order History Page is Not Diplayed");

		navigateToManageMyAccountPage();
		s_assert.assertTrue(
				storeFrontHeirloomEditPCPerksPage.getNextOrderDate()
				.equals(storeFrontHeirloomEditPCPerksPage.getNextAutoShipOrderDate()),
				"Updated Next Order Date Did Not Matched.");
		afterNextOrderDate = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		nextOrderDateFromUI = storeFrontHeirloomEditPCPerksPage.createNextOrderDateAfterTwoMonth();
		s_assert.assertTrue(afterNextOrderDate.equalsIgnoreCase(nextOrderDateFromUI), "Expected order date is "+nextOrderDateFromUI+" but actual on UI is "+afterNextOrderDate);

		s_assert.assertAll();
	}


	/**
	 * Jira Story Id: MAIN-7947 
	 * qTest Id: TC-3454
	 */
	@Test(alwaysRun = true, groups = "ManageMyAccountCorp", description = "Editing Autoship Date - Order Processing change Billing date")
	public void testAutoshipOrderProcessingChangeBillingDate_3454() {
		int randomNum = CommonUtils.getRandomNum(9999, 10000000);
		String billingProfileName = billingName + randomNum;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNum;
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		s_assert.assertTrue(driver.getCurrentUrl().contains("MyOffice/PcDelayAutoship"),
				"User is not navigated to PcDelayAutoship page");
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		s_assert.assertTrue(
				storeFrontHeirloomEditPCPerksPage.getTextMsgOfBuyNowReviewPage()
				.contains("your pc perks subscription order will be processed on"),
				"PC Perks Order Confirmation message did NOT match on PcAutoshipBuyNowReview Page.");

		storeFrontHeirloomEditPCPerksPage.clickChangeLinkUnderBillingForBuyNow();
		storeFrontHeirloomHomePage.enterBillingInfoForEditCRP(billingProfileName, billingProfileFirstName,
				billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,
				CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNewlyCreatedProfilePresent(billingProfileFirstName),
				"Newly Created Billing profile not present on page");
		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		storeFrontHeirloomHomePage.clickViewDetailsLinkOfFirstOrderForPC();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNewlyCreatedProfilePresent(billingProfileFirstName),"Newly Created Billing profile not present on order details page");
		s_assert.assertAll();
	}


	/**
	 * Jira Story Id: MAIN-7945
	 * qTest Id: TC-3091
	 */
	@Test(alwaysRun = true, groups = "ManageMyAccountCorp", description = "Editing Autoship Date - PC Perks Delay Autoship Page - Display")
	public void testPCAutoshipOrderProcessing_3091() {
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
	 * qTest Id: TC-3233 
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "Verify the text on the PC Perks status page at corp site")
	public void testVerifyTheTextOnThePCPerkStatusPage_CORPSite_3233() {
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isRequiredTextInManageMyAccountPageDisplayed(), "Required text is not displayed in Manage My Account Page");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isCancelPCPerksAccountLinkInManageMyAccountPageDisplayed(),"Cancel PC Perks Account Link not displayed");
		storeFrontHeirloomEditPCPerksPage.clickLinkClickHereInManageMyAccountPage();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.getCurrentURL().contains("http://rodanandfieldsresults.com"),"On clicking Click Here Link User not redirected to Results Page");
		s_assert.assertAll();

	}

	/**
	 * Jira Story Id: MAIN-8764
	 * qTest Id: TC-3234 
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "Verify the header on the page at corp site")
	public void testVerifyTheHeaderOnThePage_CORPSite_3234() {
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isManageMyAccountHeaderTextDisplayed(), "Manage My Account Header Text is not being Displayed");
		s_assert.assertAll();

	}

	/**
	 * Jira Story Id: MAIN-8764
	 * qTest Id: TC-3235
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "Verify the label \"Cancel PC Perks Account\" at Corp Site")
	public void testVerifyTheLabelCancelPCPerksAccount_CORPSite_3235() {
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

	/**
	 * Jira Story Id: MAIN-7946
	 * qTest Id: TC-3112
	 */
	@Test(alwaysRun = true, groups = "ManageMyAccountCorp", description = "Verify Buy Now to shop Today")
	public void testVerifyBuyNowShopToday_3112() {
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.getLatestOrderDate()
				.equals(CommonUtils.getCurrentDate("M/d/YYYY", TimeZone.getTimeZone("US/Pacific"))),
				"Latest Order Date did NOT match.");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-7947
	 * qTest Id: TC-3460
	 */
	@Test(alwaysRun = true, groups = "ManageMyAccountCorp", description = "Edit PC Perks Cart - BuyNow Cancel")
	public void testCancelBuyNow_3460() {
		String dateBeforeCancel = null;
		String dateAfterCancel = null;
		dateBeforeCancel = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		navigateToEditPCPerksPage();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		s_assert.assertTrue(driver.getCurrentUrl().contains("PcAutoshipBuyNowReview"),
				"Actual URL is not as Expected.");
		storeFrontHeirloomEditPCPerksPage.clickOnCancel();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisplayed(),
				"Buy Now Button is NOT display after clicking on Cancel Button.");

		storeFrontHeirloomEditPCPerksPage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory("Manage My Account");
		dateAfterCancel = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isNextOrderDateTextDisplayed(),
				"Next order date text is not displayed");
		s_assert.assertTrue(!storeFrontHeirloomEditPCPerksPage.getNextOrderDate().isEmpty(),
				"Next Order Date value is NOT Null/Empty ");
		s_assert.assertTrue(dateAfterCancel.equalsIgnoreCase(dateBeforeCancel), "Expected order date after cancel is "+dateAfterCancel+" but actual on UI is "+dateAfterCancel);
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-9023
	 * qTest Id: TC-3461
	 */
	@Test(alwaysRun = true, groups = "ManageMyAccountCorp", description = "verify Change link on PcAutoshipBuyNowReview Page")
	public void testVerifyChangeLinkONPCAutoshipBuyNow_3461() {
		String orderUpdateMsg = "Replenishment Order items successfully updated!";
		navigateToEditPCPerksPage();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isEditOrderReplenishmentPageDisplayed(),
				"User is not navigated to Replenishment order management page");
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		s_assert.assertTrue(driver.getCurrentUrl().contains("PcAutoshipBuyNowReview"),
				"Actual URL is not as Expected.");
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isChangeLinkForTotalAmountDisplayed(),
				"Change Link For Total Amount is NOT display after clicking on Buy Now Button.");
		storeFrontHeirloomEditPCPerksPage.clickOnChangeLinkForTotal();
		s_assert.assertTrue(driver.getCurrentUrl().contains("OrderItems"),"Current URL is Not as expected");
		storeFrontHeirloomHomePage.clickAddToCartBtnForEditOrder();
		storeFrontHeirloomHomePage.handleAlertAfterUpdateOrder();
		storeFrontHeirloomHomePage.clickOnUpdateOrder();
		storeFrontHeirloomHomePage.handleAlertAfterUpdateOrder();

		s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderUpdateMessage().equalsIgnoreCase(orderUpdateMsg),
				"Order Update Error Message did NOT match.");

		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8146
	 * qTest Id: TC-3147
	 */
	@Test(alwaysRun = true, groups = "ManageMyAccountCorp", description = "Editing Autoship Date - PC Perks Delay Autoship Page - Buy Now and cancel")
	public void testEditAutoshipDate_3147() {
		navigateToEditPCPerksPage();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.getNextReplenishmentOrderMsg()
				.contains("You can adjust your next order through"), "Next Replenishment Order Message did NOT match.");
		storeFrontHeirloomEditPCPerksPage.clickOnChangeDateReplenishmentButton();
		s_assert.assertTrue(driver.getCurrentUrl().contains("MyOffice/PcPerksStatus"),
				"User is not navigated to PcPerksStatus page");
		storeFrontHeirloomEditPCPerksPage.clickEditOrderDateBtn();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isBuyNowBtnDisplayed(),
				"Buy Now Button Is NOT Displayed.");
		storeFrontHeirloomEditPCPerksPage.clickDateFieldOrCalendarIcon();
		storeFrontHeirloomEditPCPerksPage.selectValidPCPerkdDate("20");
		s_assert.assertFalse(storeFrontHeirloomEditPCPerksPage.isChangeDateBtnDisabled(),
				"Change Date Button Is Disabled,Expected enabled.");

		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7947
	 * qTest Id: TC-3459
	 */
	@Test(alwaysRun=true,groups="ManageMyAccountCorp",description = "MAIN-7947 : Edit PC Perks Cart - BuyNow")
	public void testVerifyEditPCPerksCartBuyNow_3459() {
		String afterOrderNumber = null;
		String expectedOrderType = "PC Auto-ship";
		String orderTypeFromUI = null;
		String nextOrderDateFromUI = null;
		String afterNextOrderDate = null;
		String beforeOrderNumber = null;
		storeFrontHeirloomEditPCPerksPage.clickOnRodanAndFieldsLogo();
		navigateToOrderHistory();
		beforeOrderNumber = storeFrontHeirloomEditPCPerksPage.getFirstOrderNumber();
		navigateToEditPCPerksPage();
		storeFrontHeirloomEditPCPerksPage.clickOnBuyNow();
		storeFrontHeirloomEditPCPerksPage.clickOnConfirmBuyNow();
		s_assert.assertTrue(storeFrontHeirloomEditPCPerksPage.isOrderPrecessingBtnPresent(), "Order processing btn is not present");
		afterOrderNumber = storeFrontHeirloomEditPCPerksPage.getFirstOrderNumber();
		s_assert.assertFalse(afterOrderNumber.equalsIgnoreCase(beforeOrderNumber), "Order is not getting placed");
		navigateToManageMyAccountPage();
		afterNextOrderDate = storeFrontHeirloomEditPCPerksPage.getNextOrderDate();
		nextOrderDateFromUI = storeFrontHeirloomEditPCPerksPage.createNextOrderDateAfterTwoMonth();
		s_assert.assertTrue(afterNextOrderDate.equalsIgnoreCase(nextOrderDateFromUI), "Expected order date is "+nextOrderDateFromUI+" but actual on UI is "+afterNextOrderDate);
		storeFrontHeirloomHomePage.createAndGetNSC4Url();
		loginToNSCApplication();
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.enterOrderIDInInputField(afterOrderNumber);
		orderTypeFromUI = nscore4OrdersTabPage.getOrderType();
		s_assert.assertTrue(orderTypeFromUI.contains(expectedOrderType), "Expected order type is "+expectedOrderType+" but actual on UI is "+orderTypeFromUI);
		s_assert.assertAll();
	}
}
