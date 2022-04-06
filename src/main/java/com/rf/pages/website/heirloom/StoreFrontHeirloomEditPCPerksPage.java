package com.rf.pages.website.heirloom;

import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;

public class StoreFrontHeirloomEditPCPerksPage extends StoreFrontHeirloomWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(StoreFrontHeirloomEditPCPerksPage.class.getName());

	public StoreFrontHeirloomEditPCPerksPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final By REPLENISHMENT_ORDER_PAGE_LOC=By.xpath("//*[text()='Replenishment Order Management']");
	private static final By MY_ACCOUNT_LINK_LOC = By.xpath("//div[@id='menuDiv']//span[text()='my account']/..|//div[@id='accountDiv']/descendant::a[text()='My Account']");
	private static final By NEXT_ORDER_DATE_TXT_LOC=By.xpath("//div[contains(text(),'Next Order Date')]");
	private static final By EDIT_ORDER_DATE_BTN_LOC=By.xpath("//a[contains(text(),'Edit Order Date')]");
	private static final By BUY_NOW_BTN_LOC = By.id("BtnBuyNow");
	private static final By DATE_FIELD_LOC = By.id("dueDate");
	private static final By NEXT_ICON_OF_Calendar = By.xpath("//a[@title='Next']");
	private static final By ALL_DISBALED_DAYS_LOC = By.xpath("//td[contains(@class,'disabled undefined')]");
	private static final By CHANGE_DATE_LOC = By.id("BtnChange");
	private static final By DISABLED_CHANGE_DATE_BTN_LOC = By.xpath("//button[@id='BtnChange' and @disabled='']");
	private static final By DISABLED_BUY_NOW_BTN_LOC = By.xpath("//button[@id='BtnBuyNow' and @disabled='disabled']");
	private static final By MONTH_IN_CALENDAR = By.xpath("//span[contains(@class,'month')]");
	private static final By CONFIRM_BUY_NOW_BTN_LOC = By.id("BtnConfirmUp"); 
	private static final By CANCEL_BTN_LOC = By.id("BtnCancelUp");
	private static final By TEXT_MSG_OF_BUY_NOW_REVIEW = By.xpath("//div[@id='Container']/descendant::p[1]");
	private static final By CHANGE_LINK_OF_SHIPPING_FOR_BUY_NOW = By.xpath("//div[@id='billingContent']//a");
	private static final By CHANGE_LINK_OF_BILLING_FOR_BUY_NOW = By.xpath("//h4[contains(text(),'Billing to')]/following::a[contains(text(),'Change')][1]");
	private static final By NEXT_ORDER_DATE_VALUE = By.xpath("//div[contains(text(),'Next Order Date')]/following-sibling::div");
	private static final By CHANGE_TOTAL_LINK = By.xpath("//p[@class='ChangeItemsLink']/a");
	private static final By LATEST_ORDER_DATE =By.xpath("//div[@class='divTableBody']/descendant::div[@class='divTableRow'][1]/div[2]");
	private static final By BACK_TO_MY_ACCOUNT_BTN_LOC = By.id("BtnBack");
	private static final By NEXT_REPLENISHMNET_ORDER_TXT_MSG = By.xpath("//p[contains(text(),'Your next replenishment order')]");	
	private static final By CHANGE_DATE_REPLENISHMENT_BUTTON = By.id("BtnChangeDate");
	private static final By ORDER_PROCESSING_BTN =By.id("BtnConfirmUp");
	private static final By FIRST_ORDER_NUMBER =By.xpath("//div[@class='divTableBody']/descendant::div[@class='divTableRow'][1]/div[1]");
	private static final By MANAGE_PERKS_SUBSCRIPTION_TXT = By.id("RFContent");
	private static final By CHOOSE_DATE_TXT = MANAGE_PERKS_SUBSCRIPTION_TXT;
	private static final By MANAGE_MY_ACCOUNT_TEXT_LOC=By.xpath("//div[@id='RFContent']/h2[contains(text(),'Manage My Account')]/following-sibling::div//p[contains(text(),'Share your results on your journey to great skin.')]");
	private static final By CLICK_HERE_LINK_IN_MANAGE_MY_ACCOUNT_PAGE_LOC = By.xpath("//a[text()='Click here']");
	private static final By CANCEL_PC_PERKS_ACCOUNT_LINK_IN_MANAGE_MY_ACCOUNT_PAGE_LOC = By.xpath("//a[@id='A1' and contains(text(),'Edit Order Date')]/parent::div/following-sibling::div/a[@id='PcCancelLink']");
	private static final By MANAGE_MY_ACCOUNT_HEADER_TEXT_LOC = By.xpath("//div[@id='RFContent']/h2[contains(text(),'Manage My Account')]");
	private static final By CANCEL_PC_PERK_Account_REASON_DROPDOWN_LOC = By.id("CancellationReasonId");
	private static final By SEND_EMAIL_TO_CANCEL_BUTTON_LOC = By.id("BtnSendEmail");
	private static final By CANCEL_PC_PERKS_CONFIRMATION_MESSAGE_LOC = By.id("//div[@id='RFContent']/p[contains(text(),'Your PC Perks account has been canceled')]");
	private static final By GO_TO_RF_HOME_BUTTON_LOC = By.id("BtnGoHome");
	private static final By BACK_ICON_OF_Calendar = By.xpath("//a[@title='Prev']");
	
	
	
	private static String disabledDay = "//table[contains(@class,'datepicker-calendar')]/descendant::td[contains(@class,'disabled undefined')][%s]/span";
	private static String dayInCalendar = "//a[text()='%s']";
	private static String dateBoxLoc = "//*[text()='%s']/..";
	private static String orderTextMsgLoc = "//div[@class='OrderMessage' and contains(text(),'%s')]";
	private static String enabledDay = "//td[contains(@onclick,'dueDate')]//a[text()='%s']";
	private static String disbaledDayWithText = "//table[contains(@class,'datepicker-calendar')]/descendant::td[contains(@class,'disabled undefined')]/span[text()='%s']";
	

	private static String linkUnderReplenishmentOrder = "//ul[@class='PageLinks']/descendant::a[text()='%s']";

	public boolean isEditOrderReplenishmentPageDisplayed(){
		return driver.isElementVisible(REPLENISHMENT_ORDER_PAGE_LOC);
	}
	public void clickLinkUnderReplenishmentOrder(String linkName){
		driver.click(By.xpath(String.format(linkUnderReplenishmentOrder, linkName)),linkName);
		driver.waitForPageLoad();
	}
	public boolean isUserIsOnReplenishmentReviewPage(){
		return driver.getCurrentUrl().contains("/replenishment/Review.aspx");
	}
	public boolean isUserIsOnOrderItemPage(){
		return driver.getCurrentUrl().contains("/replenishment/OrderItems.aspx");
	}
	public boolean isUserIsOnShippingPage(){
		return driver.getCurrentUrl().contains("/replenishment/Shipping.aspx");
	}
	public boolean isUserIsOnBillingPage(){
		return driver.getCurrentUrl().contains("/replenishment/Billing.aspx");
	}
	public boolean isMyAccountLinkPresent(){
		return driver.isElementVisible(MY_ACCOUNT_LINK_LOC);
	}
	
	public boolean isNextOrderDateTextDisplayed(){
		return driver.isElementVisible(NEXT_ORDER_DATE_TXT_LOC);
	}
	
	public boolean isEditOrderDateBtnDisplayed(){
		return driver.isElementVisible(EDIT_ORDER_DATE_BTN_LOC);
	}
	
	public void clickEditOrderDateBtn(){
		driver.click(EDIT_ORDER_DATE_BTN_LOC,"Edit order date btn");
	}
	
	public boolean isBuyNowBtnDisplayed(){
		return driver.isElementVisible(BUY_NOW_BTN_LOC);
	}
	
	public void clickDateFieldOrCalendarIcon(){
		driver.click(DATE_FIELD_LOC,"Calendar icon");
	}
	
	public void clickNextIconOfCalendar(){
		driver.click(NEXT_ICON_OF_Calendar,"Next icon of Calendar");
	}
	
	public boolean isNextIconDisabledOfCalendar(){
		String value = driver.getAttribute(NEXT_ICON_OF_Calendar, "class");
		logger.info("Class attribute value "+value);
		return value.contains("disabled");
	}
	
	public void selectValidPCPerkdDate(String day){
		driver.click(By.xpath(String.format(dayInCalendar, day)),day+" selected as day in calendar");
	}
	
	public void clickChangeDateBtn(){
		driver.click(CHANGE_DATE_LOC,"Change Date btn");
		driver.waitForPageLoad();
	}
	
	public boolean isBuyNowBtnDisabled(){
		return driver.isElementVisible(DISABLED_BUY_NOW_BTN_LOC);
	}
	
	public boolean isChangeDateBtnDisabled(){
		return driver.isElementVisible(DISABLED_CHANGE_DATE_BTN_LOC);
	}
	
	public boolean isOrderMessageTextVisible(String text){
		return driver.isElementVisible(By.xpath(String.format(orderTextMsgLoc, text)));
	}
	
	public String getMonthFromCalendar(){
		return driver.getText(MONTH_IN_CALENDAR,"Month name");
	}
	
	public boolean isDayEnabledInCalendar(String day){
		String value =  driver.getAttribute(By.xpath(String.format(dateBoxLoc, day)), "onclick");
		return value.contains("#dueDate");
	}
	
	public void clickOnBuyNow() {
		driver.click(BUY_NOW_BTN_LOC, "Buy Now Button");
	} 
	
	public void clickOnConfirmBuyNow() {
		driver.click(CONFIRM_BUY_NOW_BTN_LOC, "Confirm Buy Now PCAutoship Button");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(2000);
	} 
	
	public void clickOnCancel() {
		driver.click(CANCEL_BTN_LOC, "Cancel Button");
	} 
	
	public boolean isConfirmBtnDisplayedForBuynow(){
		return driver.isElementVisible(CONFIRM_BUY_NOW_BTN_LOC);
	}
	
	public boolean isCancelBtnDisplayedForBuynow(){
		return driver.isElementVisible(CANCEL_BTN_LOC);
	}
	
	public String getTextMsgOfBuyNowReviewPage(){
		return driver.getText(TEXT_MSG_OF_BUY_NOW_REVIEW,"Buy Now review page").toLowerCase();
	}
	
	public String getFirstThreeDigitOfMonth(String month){
		return month.substring(0,3);
	}
	
	
	public void clickChangeLinkUnderShippingForBuyNow(){
		driver.click(CHANGE_LINK_OF_SHIPPING_FOR_BUY_NOW,"Change Link under shipping for buy now");
		driver.waitForPageLoad();
	}
	
	public void clickChangeLinkUnderBillingForBuyNow(){
		driver.click(CHANGE_LINK_OF_BILLING_FOR_BUY_NOW,"Change Link under Billing for buy now");
		driver.waitForPageLoad();
	}
	
	public boolean isNewlyCreatedProfilePresent(String profileName) {
		return driver.isElementVisible(By.xpath("//*[contains(text(),'"+profileName+"')]"));
	}
	
	public String getNextOrderDate() {
		return driver.getText(NEXT_ORDER_DATE_VALUE, "Next Order Date Value");
	}
	
	public boolean isChangeLinkForTotalAmountDisplayed(){
		return driver.isElementVisible(CHANGE_TOTAL_LINK);
	}
	public void clickOnChangeLinkForTotal(){
		driver.click(CHANGE_TOTAL_LINK, "Change Link For Total Amount.");
	}
	
	public String getLatestOrderDate() {
		return driver.getText(LATEST_ORDER_DATE, "Get Latest ORder Date on Order History");
	}
	
	
	public boolean isPreviousDaysDisbaledFromCurrentDay(int currentDay){
		boolean isDayDisabled = false;
		for(int count = currentDay; count>=1; count--){
			isDayDisabled = false;
			if(driver.isElementVisible(disbaledDayWithText,""+count)){
				isDayDisabled=true;
			}else{
				isDayDisabled=false;
			}
		}
		return isDayDisabled;
	}

	public boolean isFollowingDaysEnabledFromCurrentDayTill20(int currentDay){
		int firstEnabledDay = currentDay+1;
		boolean isDayEnabled = false;
		for(int count=firstEnabledDay; count<=20; count++ ){
			isDayEnabled = false;
			if(driver.isElementVisible(enabledDay,""+count)){
				isDayEnabled=true;
			}else{
				isDayEnabled=false;
			}
		}
		return isDayEnabled;

	}

	public void clickBackToMyAccountBtn(){
		driver.click(BACK_TO_MY_ACCOUNT_BTN_LOC,"Back to my account btn");
		driver.waitForPageLoad();
	}

	/**
	 * This method is used to get Your Next Replenishment Order Msg
	 * return String
	 */
	public String getNextReplenishmentOrderMsg() {
		return driver.getText(NEXT_REPLENISHMNET_ORDER_TXT_MSG, "Next Replenishment Order Text Message");
	}
	
	/**
	 * This method is used to Click on Change Date Button
	 * return object of StoreFrontHeirloomEditPCPerksPage
	 */
	public StoreFrontHeirloomEditPCPerksPage clickOnChangeDateReplenishmentButton() {
		driver.click(CHANGE_DATE_REPLENISHMENT_BUTTON, "Change Date Button on Replenishment Page");
		return this;
	}
	
	public boolean isLatestOrderDisplayed() {
		return driver.isElementVisible(LATEST_ORDER_DATE);
	}
	
	public boolean isOrderPrecessingBtnPresent() {
		return driver.isElementVisible(ORDER_PROCESSING_BTN);
	}
	
	public String getFirstOrderNumber(){
		return driver.getText(FIRST_ORDER_NUMBER,"First order number is ");
	}
	
	/**
	 * This method is used to get Only Current Date i.e. 05
	 * @return Integer date
	 */
	public int getCurrentDate() {
		String currentDate = CommonUtils.getCurrentDate("MMM d, YYYY", TimeZone.getTimeZone("US/Pacific"));
		return Integer.parseInt(currentDate.substring(currentDate.indexOf(" ") + 1, currentDate.lastIndexOf(",")));
	}
	
	/**
	 * This method is used to get Subscription Order Text
	 * @return String
	 */
	public String getManagePCPerksSubscriptionOrderTxt() {
		return driver.getText(MANAGE_PERKS_SUBSCRIPTION_TXT, "Manage PC Perks Subscription Order Text Message");
	}
	
	/**
	 * This method is used to get Choose Date Text
	 * @return String
	 */
	public String getChooseDateTxt() {
		return driver.getText(CHOOSE_DATE_TXT, "Choose Date Text");
	}
	
	/**
	 * This method is used to verify if placeholder for due date is in format 
	 * or not
	 * @return boolean
	 */
	public boolean vefiryPlaceHolderValueForDueDate() {
		if (driver.getAttribute(DATE_FIELD_LOC, "placeholder").equals("MM/DD/YYYY")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isGoToRFHomeButtonDisplayed(){
		
		return driver.isElementVisible(GO_TO_RF_HOME_BUTTON_LOC);
	}
	
	public boolean isCancelPCPerksConfirmationMessageDisplayed(){
		driver.waitForElementPresent(CANCEL_PC_PERKS_CONFIRMATION_MESSAGE_LOC);
		return driver.isElementVisible(CANCEL_PC_PERKS_CONFIRMATION_MESSAGE_LOC);
	}
	
	public void clickSendEmailToCancelButton(){
		driver.click(SEND_EMAIL_TO_CANCEL_BUTTON_LOC,"Click Send Email To Cancel Button");
		driver.waitForPageLoad();
	}
		
	public void selectReasonForCancellingPCPerkAccount(int index){
		Select s=new Select(driver.findElement(CANCEL_PC_PERK_Account_REASON_DROPDOWN_LOC));
		s.selectByIndex(index);
	}	
	
	public boolean isRequiredTextInManageMyAccountPageDisplayed(){
		return driver.isElementVisible(MANAGE_MY_ACCOUNT_TEXT_LOC);
	}
	
	public void clickLinkClickHereInManageMyAccountPage(){
		driver.click(CLICK_HERE_LINK_IN_MANAGE_MY_ACCOUNT_PAGE_LOC,"Click Here Link");
		driver.waitForPageLoad();
	}
	
	public boolean isCancelPCPerksAccountLinkInManageMyAccountPageDisplayed(){
		return driver.isElementVisible(CANCEL_PC_PERKS_ACCOUNT_LINK_IN_MANAGE_MY_ACCOUNT_PAGE_LOC);
	}
	
	public boolean isManageMyAccountHeaderTextDisplayed(){
		return driver.isElementVisible(MANAGE_MY_ACCOUNT_HEADER_TEXT_LOC);
	}
	
	public void clickCancelPCPerksAccountLinkInManageMyAccountPages(){
		driver.click(CANCEL_PC_PERKS_ACCOUNT_LINK_IN_MANAGE_MY_ACCOUNT_PAGE_LOC,"Click Cancel PC Perks Account Link");
		driver.waitForPageLoad();
	}
	
	public String createNextOrderDateAfterTwoMonth(){
		int day = getCurrentDayFromCurrentDate();
		String month = getNextTwoMonthDateFromDate();
		String nextOrderDate = null;
		String monthCount = getMonthCountFromMonthName(month);
		String year = getYearAfterTwoMonthFromCurrentDate();
		String day1 = "";
		if(day<10){
			day1 = "0"+day;
		}
		if(Integer.parseInt(day1)<=20){
			nextOrderDate = monthCount+"/"+day1+"/"+year;
		}else{
			nextOrderDate = monthCount+"/20/"+year;
		}
		logger.info("Next order date is "+nextOrderDate);
		return nextOrderDate;
	}
	

	/**
	 * This method is used to calculate Next Autoship Order Date
	 * @return String date
	 */
	public String getNextAutoShipOrderDate() {
		String nextTwoMonth = getMonthCountFromMonthName(getNextTwoMonthDateFromDate());

		if (getCurrentDate() >= 20) {

			return CommonUtils.getCurrentDate(nextTwoMonth + "/" + 20 + "/YYYY", TimeZone.getTimeZone("US/Pacific"));
		} else {
			return CommonUtils.getCurrentDate(nextTwoMonth + "/dd/YYYY", TimeZone.getTimeZone("US/Pacific"));
		}
	}
	
	/**
	 * This method is used to click on Previous button of Calendar
	 * @return void
	 */
	public void clickBackIconOfCalendar() {
		driver.click(BACK_ICON_OF_Calendar, "Back icon of Calendar");
	}
	
	public boolean isDateBetween21To30Or31GrayedOut() {
		int totalDisabledNoOfDays = driver.findElements(ALL_DISBALED_DAYS_LOC).size();
		boolean isDayMatched = false;
		int expectedDay = 21;
		int lastDate = Integer.parseInt(driver.getText(By.xpath(String.format(disabledDay, totalDisabledNoOfDays))));
		int index = totalDisabledNoOfDays - (lastDate - expectedDay);

		for (int count = index; count <= totalDisabledNoOfDays; count++) {
			String text = driver.getText(By.xpath(String.format(disabledDay, count)), "Disabled day is");

			if (text.contains("" + expectedDay)) {
				isDayMatched = true;
			} else {
				isDayMatched = false;
				break;
			}
			expectedDay = expectedDay + 1;
		}
		return isDayMatched;
	}
}
