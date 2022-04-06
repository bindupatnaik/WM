package com.rf.pages.website.LSD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.rf.core.driver.RFDriver;
import com.rf.core.driver.website.RFWebsiteDriver;

public class LSDCustomerPage extends LSDRFWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(LSDCustomerPage.class.getName());

	public LSDCustomerPage(RFWebsiteDriver driver) {
		super(driver);
	}

	private String qvValueLoc = "//div[@id='sub-stage']/descendant::span[contains(text(),'QV')][@class='au-target'][not(contains(text(),'PSQV'))][%s]";
	private String detailsOfFirstOrderLoc = "//div[@id='sub-stage']/descendant::a[1]/descendant::span[%s]";
	private String userListLoc = "//div[@id='sub-stage']/descendant::a[%s]";

	private static final By ALL_QV_COUSTOMERS_LOC = By.xpath("//div[@id='sub-stage']/descendant::span[contains(text(),'QV')][@class='au-target'][not(contains(text(),'PSQV'))]");
	private static final By COUNT_OF_PC_ORDERS_FOR_FURTHER_OUT_LOC = By.xpath("//div[contains(@class,'white-fb')]/descendant::a[3]/descendant::span[2]");
	private static final By CONTACT_ICON_AT_PC_CARD_UNDER_CUSTOMER_LOC = By.xpath("//div[@id='sub-stage']/descendant::section[contains(@id,'pc-card')][1]//span[contains(@class,'icon-contact')]");
	private static final By TWO_INITIAL_ICON_AT_PC_CARD_UNDER_TEAM_LOC = By.xpath("//div[@id='sub-stage']/descendant::section[contains(@id,'pc-card')][1]//div[contains(@class,'rf-circle-bg-active')]");
	private static final By COUNT_OF_DEACTIVATED_PC_AT_HEADER_LOC = By.xpath("//h1[contains(text(),'Deactivated PC')]/../descendant::span[1]");

	private static final By CUSTOMERS_PAGE = By.xpath("//div[@class='customers']");
	private static String orderValuesInUpcomingOrderSection = "//section[@class='pc-order-upcoming']//span[text()='%s']";
	private static String orderValuesOfFirstOrderInOrderHistorySection = "//section[@class='pc-order-history']//pc-order-history-card[contains(@class,'pc-order-history-card')][1]//th[text()='%s']";
	private static final By PENDING_AUTOSHIP_ORDERS_LOC = By.xpath("//div[@id='sub-stage']/descendant::a//span[contains(text(),'Pending')]");
	private static final By NAME_OF_FIRST_DEACTIVATED_PC_LOC = By.xpath("//div[@id='sub-stage']/descendant::a[1]/descendant::span[1]");
	private static final By CANCELLED_DATE_OF_FIRST_DEACTIVATED_PC_LOC = By.xpath("//div[@id='sub-stage']/descendant::a[1]/descendant::span[contains(text(),'Cancelled')][1]/following::span[1]");
	private static final By ENROLLED_DATE_OF_FIRST_DEACTIVATED_PC_LOC = By.xpath("//div[@id='sub-stage']/descendant::a[1]/descendant::span[contains(text(),'Enrolled')][1]/following::span[1]");
	private static final By QV_OF_FIRST_DEACTIVATED_PC_LOC = By.xpath("//div[@id='sub-stage']/descendant::a[1]/descendant::p[contains(text(),'QV')]");
	private static final By TOTAL_ORDERS_LOC = By.xpath("//div[@id='sub-stage']/descendant::a");
	private static final By PROCESSED_AUTOSHIP_ORDERS_LOC = By.xpath("//div[@id='sub-stage']/descendant::a//span[contains(text(),'Processed')]");
	private static final By EXPAND_AND_MINIMIZE_BTN_OF_THIS_MONTH = By.xpath("//h2[text()='This Month']/preceding::div[1]/button");
	private static final By EXPAND_AND_MINIMIZE_BTN_OF_NEXT_MONTH = By.xpath("//h2[text()='Next Month']/preceding::div[1]/button");
	private static final By EXPAND_AND_MINIMIZE_BTN_OF_FURTHER_OUT = By.xpath("//h2[text()='Further Out']/preceding::div[1]/button");
	private static final By CUSTOMER_AUTOSHIP_ORDER_SECTION = By.xpath("//div[@id='sub-stage']//section//div[@class='pc-order-cards']");
	private static final By ORDER_CUSTOMER_NAME = By.xpath("//div[@class='pc-order-cards']//pc-order-card[@class='au-target'][1]//h3");
	private static final By ORDER_TYPE = By.xpath("//div[@class='pc-order-cards']//pc-order-card[@class='au-target'][1]//h4");
	private static final By ORDER_STATUS = By.xpath("//div[@class='pc-order-cards']//pc-order-card[@class='au-target'][1]//div[@class='data']/div[2]/div[1]");
	private static final By ORDER_DATE = By.xpath("//div[@class='pc-order-cards']//pc-order-card[@class='au-target'][1]//div[@class='data']/div[1]/div[2]");
	private static final By FIRST_PROCESSED_ORDER = By.xpath("//div[@class='pc-order-cards']/descendant::div[text()='Processed'][1]");
	private static final By FIRST_FAILED_ORDER = By.xpath("//div[@class='pc-order-cards']/descendant::div[text()='Failed'][1]");
	private static final By FIRST_SCHEDULED_ORDER = By.xpath("//div[@class='pc-order-cards']/descendant::div[text()='Scheduled'][1]");
	private static final By PC_USERNAME_FROM_ORDER = By.xpath("//div[@class='pc-profile-content']/h2");
	private static final By ENROLLED_ON_TXT = By.xpath("//th[text()='Enrolled on:']");
	private static final By NEXT_SHIPMENT_TXT = By.xpath("//th[text()='Enrolled on:']");
	private static final By UPCOMING_ORDER_SECTION = By.xpath("//section[@class='pc-order-upcoming']");
	private static final By ORDER_HISTORY_SECTION = By.xpath("//section[@class='pc-order-history']");
	private static final By DATE_AND_TIME_STAMP_IN_UPCOMING_ORDER_SECTION = By.xpath("//section[@class='pc-order-upcoming']//p[@class='commission-date']/span[1]");
	private static final By ORDER_ITEMS_IN_UPCOMING_ORDER_SECTION_ = By.xpath("//section[@class='pc-order-upcoming']//div[@class='items-container']");
	private static final By ORDER_NOTE = By.xpath("//section[@class='dark-grey tiny-text footnote']/p");
	private static final By DATE_AND_TIME_STAMP_IN_ORDER_HISTORY_SECTION_FOR_FIRST_ORDER = By.xpath("//section[@class='pc-order-history']//pc-order-history-card[contains(@class,'pc-order-history-card')][1]//p[@class='commission-date']");
	private static final By ORDER_ITEMS_OF_FIRST_ORDER_IN_ORDER_HISTORY_SECTION = By.xpath("//section[@class='pc-order-history']//pc-order-history-card[contains(@class,'pc-order-history-card')][1]//div[@class='items-container']");
	private static final By BACK_ARROW_ICON = By.xpath("//section[@id='pc-profile-modal']/div[@class='icon-arrow-back pointer']");
	private static final By NEW_LABEL_LOC = By.xpath("//div[@class='pc-order-cards']//pc-order-card[@class='au-target'][1]//div[text()='New']");
	private static final By NEW_LABEL_IN_ORDER_LOC = By.xpath("//div[@class='pc-profile-content']//div[text()='New']");
	private static final By CONTACT_NAME_AT_FOOTER_FROM_CUSTOMER_PAGE = By.xpath("//section[@id='pc-profile-modal']//div[@class='shadow-card-button-container']//span[@class='label']");
	private static final By COUNT_OF_PC_AUTOSHIP_ORDERS_FOR_THIS_MONTH_LOC = By.xpath("//div[contains(@class,'white-fb')]/descendant::a[1]/descendant::span[2]");
	private static final By COUNT_OF_PC_AUTOSHIP_ORDERS_FOR_THIS_MONTH_AT_HEADER_LOC = By.xpath("//h1[contains(text(),'have orders this month')]/preceding::span[1]");
	private static final By PC_ORDERS_FOR_THIS_MONTH_LINK_LOC = By.xpath("//div[contains(@class,'white-fb')]/descendant::a[1]/descendant::span[1]");
	private static final By COUNT_OF_NEW_PC_LOC = By.xpath("//h1[contains(text(),'View Your Customers')]/../descendant::span[2]");
	private static final By COUNT_OF_NEW_PC_AT_HEADER_LOC = By.xpath("//h1[contains(text(),'new Customers')]/preceding::span[1]");
	private static final By NEW_PC_LINK_LOC = By.xpath("//h1[contains(text(),'View Your Customers')]/../descendant::span[1]");
	private static final By COUNT_OF_PC_ORDERS_FOR_NEXT_MONTH_LOC = By.xpath("//div[contains(@class,'white-fb')]/descendant::a[2]/descendant::span[2]");
	private static final By COUNT_OF_PC_ORDERS_FOR_NEXT_MONTH_AT_HEADER_LOC = By.xpath("//h1[contains(text(),'orders next month')]/preceding::span[1]");
	private static final By PC_ORDERS_FOR_NEXT_MONTH_LINK_LOC = By.xpath("//div[contains(@class,'white-fb')]/descendant::a[2]/descendant::span[1]");
	private static final By VIEW_All_PC_CUSTOMERS = By.xpath("//h1[contains(text(),'Build Customer Loyalty')]/../preceding::span[1]");
	private static final By COUNT_OF_ALL_PC_CUSTOMERS = By.xpath("//h1[contains(text(),'All Customers')]//preceding::span[1]");

	private String subCategoryOfCustomerLoc = "//h1[contains(text(),'Build')]/following::div[1]/descendant::a[%s]/span";
	private String subCategoryOfCustomerUnderViewCustomerLoc = "//h1[contains(text(),'View Your Customers')]/following::span[%s]";


	public boolean isCustomerPagePresent(){
		driver.waitForElementPresent(CUSTOMERS_PAGE);
		return driver.isElementPresent(CUSTOMERS_PAGE);
	}

	public void clickExpandAndMinimizeButtonOfThisMonth(){
		driver.waitForElementPresent(EXPAND_AND_MINIMIZE_BTN_OF_THIS_MONTH);
		driver.click(EXPAND_AND_MINIMIZE_BTN_OF_THIS_MONTH,"");
		logger.info("Expand and minimize Button clicked of this month");
	}

	public void clickExpandAndMinimizeButtonOfNextMonth(){
		driver.waitForElementPresent(EXPAND_AND_MINIMIZE_BTN_OF_NEXT_MONTH);
		driver.click(EXPAND_AND_MINIMIZE_BTN_OF_NEXT_MONTH,"");
		logger.info("Expand and minimize Button clicked of next month");
	}

	public void clickExpandAndMinimizeButtonOfFurtherOut(){
		driver.waitForElementPresent(EXPAND_AND_MINIMIZE_BTN_OF_FURTHER_OUT);
		driver.click(EXPAND_AND_MINIMIZE_BTN_OF_FURTHER_OUT,"");
		logger.info("Expand and minimize Button clicked of further out");
	}

	public boolean isAutoshipOrderSectionPresentAfterExpand(){
		driver.waitForElementPresent(CUSTOMER_AUTOSHIP_ORDER_SECTION);
		return driver.isElementPresent(CUSTOMER_AUTOSHIP_ORDER_SECTION);
	}

	public String getCustomerNamePresentInFirstOrder(){
		driver.waitForElementPresent(ORDER_CUSTOMER_NAME);
		String customerName =  driver.findElement(ORDER_CUSTOMER_NAME).getText();
		logger.info("First order's customer name is: "+customerName);
		return customerName;
	}

	public String getOrderTypeOfFirstOrder(){
		driver.waitForElementPresent(ORDER_TYPE);
		String orderType =  driver.findElement(ORDER_TYPE).getText();
		logger.info("First order's order type is: "+orderType);
		return orderType;
	}

	public String getOrderStatusOfFirstOrder(){
		driver.waitForElementPresent(ORDER_STATUS);
		String orderStatus =  driver.findElement(ORDER_STATUS).getText();
		logger.info("First order's order status is: "+orderStatus);
		return orderStatus;
	}

	public String getOrderDateOfFirstOrder(){
		driver.waitForElementPresent(ORDER_DATE);
		String orderDate = driver.findElement(ORDER_DATE).getText();
		logger.info("First order's order date is: "+orderDate);
		return orderDate;
	}

	public String getClassNameOfFirstProcessedOrder(){
		driver.waitForElementPresent(FIRST_PROCESSED_ORDER);
		String className = driver.findElement(FIRST_PROCESSED_ORDER).getAttribute("class");
		logger.info("class name of prcessed order is: "+className);
		return className;
	}

	public String getClassNameOfFirstFailedOrder(){
		driver.waitForElementPresent(FIRST_FAILED_ORDER);
		String className = driver.findElement(FIRST_FAILED_ORDER).getAttribute("class");
		logger.info("class name of failed order is: "+className);
		return className;
	}

	public String getClassNameOfFirstScheduledOrder(){
		driver.waitForElementPresent(FIRST_SCHEDULED_ORDER);
		String className = driver.findElement(FIRST_SCHEDULED_ORDER).getAttribute("class");
		logger.info("class name of Scheduled order is: "+className);
		return className;
	}

	public void clickFirstProcessedOrderUnderCustomerSection(){
		driver.waitForElementPresent(FIRST_PROCESSED_ORDER);
		driver.click(FIRST_PROCESSED_ORDER,"");
		logger.info("First processed order clicked in customer section");
	}

	public String getPCUserNamePresentInOrder(){
		driver.waitForElementPresent(PC_USERNAME_FROM_ORDER);
		String customerName =  driver.findElement(PC_USERNAME_FROM_ORDER).getText();
		logger.info("Order's customer name is: "+customerName);
		return customerName;
	}

	public boolean isEnrolledOnTxtPresent(){
		driver.waitForElementPresent(ENROLLED_ON_TXT);
		return driver.isElementPresent(ENROLLED_ON_TXT);
	}

	public boolean isNextShipmentTxtPresent(){
		driver.waitForElementPresent(NEXT_SHIPMENT_TXT);
		return driver.isElementPresent(NEXT_SHIPMENT_TXT);
	}

	public boolean isUpcomingOrderSectionPresent(){
		driver.pauseExecutionFor(5000);
		driver.waitForElementToBeVisible(UPCOMING_ORDER_SECTION, 3000);
		return driver.isElementPresent(UPCOMING_ORDER_SECTION);
	}

	public boolean isOrderHistorySectionPresent(){
		driver.waitForElementPresent(ORDER_HISTORY_SECTION);
		return driver.isElementPresent(ORDER_HISTORY_SECTION);
	}

	public String getOrderDateAndTimeStampInUpcomingOrderSection(){
		driver.waitForElementPresent(DATE_AND_TIME_STAMP_IN_UPCOMING_ORDER_SECTION);
		String orderDateAndTime =  driver.findElement(DATE_AND_TIME_STAMP_IN_UPCOMING_ORDER_SECTION).getText();
		logger.info("Order's date and time is: "+orderDateAndTime);
		return orderDateAndTime;
	}

	public boolean isOrderDetailsPresentInOrderUpcomingSection(String orderDetail){
		driver.waitForElementPresent(By.xpath(String.format(orderValuesInUpcomingOrderSection, orderDetail)));
		return driver.isElementPresent(By.xpath(String.format(orderValuesInUpcomingOrderSection, orderDetail)));
	}

	public boolean isOrderItemsPresentInUpcomingOrderSection(){
		driver.waitForElementPresent(ORDER_ITEMS_IN_UPCOMING_ORDER_SECTION_);
		return driver.isElementPresent(ORDER_ITEMS_IN_UPCOMING_ORDER_SECTION_);
	}

	public String getOrderNote(){
		driver.waitForElementPresent(ORDER_NOTE);
		String note = driver.findElement(ORDER_NOTE).getText();
		logger.info("Order note is: "+note);
		return note;
	}

	public String getOrderDateAndTimeStampOfFirstOrderInOrderHistorySection(){
		driver.waitForElementPresent(DATE_AND_TIME_STAMP_IN_ORDER_HISTORY_SECTION_FOR_FIRST_ORDER);
		String orderDateAndTime =  driver.findElement(DATE_AND_TIME_STAMP_IN_ORDER_HISTORY_SECTION_FOR_FIRST_ORDER).getText();
		logger.info("Order's date and time of first order in order history section is: "+orderDateAndTime);
		return orderDateAndTime;
	}

	public boolean isOrderDetailsPresentInOrderHistorySectionOfFirstOrder(String orderDetail){
		driver.waitForElementPresent(By.xpath(String.format(orderValuesOfFirstOrderInOrderHistorySection, orderDetail)));
		return driver.isElementPresent(By.xpath(String.format(orderValuesOfFirstOrderInOrderHistorySection, orderDetail)));
	}

	public boolean isOrderItemsPresentInOrderHistorySectionOfFirstOrder(){
		driver.waitForElementPresent(ORDER_ITEMS_OF_FIRST_ORDER_IN_ORDER_HISTORY_SECTION);
		return driver.isElementPresent(ORDER_ITEMS_OF_FIRST_ORDER_IN_ORDER_HISTORY_SECTION);
	}

	public void clickBackArrowIcon(){
		driver.click(BACK_ARROW_ICON,"");
		logger.info("Back arrow icon clicked");
	}

	public boolean isNewLabelPresentForFirstOrder(){
		driver.waitForElementPresent(NEW_LABEL_LOC);
		return driver.isElementPresent(NEW_LABEL_LOC);
	}

	public boolean isNewLabelPresentInFirstOrder(){
		driver.waitForElementPresent(NEW_LABEL_IN_ORDER_LOC);
		return driver.isElementPresent(NEW_LABEL_IN_ORDER_LOC);
	}

	public String getContactNameFromContactButtonInCustomerPage(){
		driver.waitForElementPresent(CONTACT_NAME_AT_FOOTER_FROM_CUSTOMER_PAGE);
		String name =  driver.findElement(CONTACT_NAME_AT_FOOTER_FROM_CUSTOMER_PAGE).getText();
		logger.info("Contact name is: "+name);
		return name;
	}

	public String getCountOfPCAutoshipOrdersForThisMonth(){
		driver.waitForSpinImageToDisappearPulse();
		return driver.getText(COUNT_OF_PC_AUTOSHIP_ORDERS_FOR_THIS_MONTH_LOC, "count of pc autoship orders for this month");
	}

	public String getCountOfPCAutoshipOrdersForThisMonthFromHeader(){
		driver.waitForElementPresent(COUNT_OF_PC_AUTOSHIP_ORDERS_FOR_THIS_MONTH_AT_HEADER_LOC);
		return driver.getText(COUNT_OF_PC_AUTOSHIP_ORDERS_FOR_THIS_MONTH_AT_HEADER_LOC, "count of pc autoship orders for this month at header");
	}

	public String getCountOfNewPC(){
		driver.waitForSpinImageToDisappearPulse();
		return driver.getText(COUNT_OF_NEW_PC_LOC, "count of new pc");
	}

	public void clickNewPC(){
		driver.click(NEW_PC_LINK_LOC, "New PC");
	}

	public String getCountOfNewPCFromHeader(){
		driver.waitForElementPresent(COUNT_OF_NEW_PC_AT_HEADER_LOC);
		return driver.getText(COUNT_OF_NEW_PC_AT_HEADER_LOC, "count of new pc at header");
	}

	public String getCountOfPCOrdersForNextMonth(){
		driver.waitForSpinImageToDisappearPulse();
		return driver.getText(COUNT_OF_PC_ORDERS_FOR_NEXT_MONTH_LOC, "count of pc autoship orders for next month");
	}

	public void clickSubCategoryOnCustomerPage(String categoryCount){
		driver.waitForElementToBeVisible(By.xpath(String.format(subCategoryOfCustomerLoc, categoryCount)), 10);
		driver.click(By.xpath(String.format(subCategoryOfCustomerLoc, categoryCount)), categoryCount+" category clicked");
	}

	public String getCancelledDateOfFirstDeactivatePC(){
		return driver.getText(CANCELLED_DATE_OF_FIRST_DEACTIVATED_PC_LOC, "cancelled date of first deactivated pc");
	}

	public String getQVOfFirstDeactivatePC(){
		return driver.getText(QV_OF_FIRST_DEACTIVATED_PC_LOC, "QV value of first deactivated pc");
	}

	public String getYearFromDate(String date){
		return "20"+date.split("\\/")[2];
	}

	public String getQVValue(String QV){
		return QV.trim().split("\\ ")[0];
	}

	public String getTotalNumberOfProcessedAutoshipOrders(){
		int count = driver.findElements(PROCESSED_AUTOSHIP_ORDERS_LOC).size();
		logger.info("total count of processed autoship"+count);
		return ""+count;
	}

	public String getTotalNumberOrders(){
		int count = driver.findElements(TOTAL_ORDERS_LOC).size();
		logger.info("total count of orders"+count);
		return ""+count;
	}

	public void clickPCOrderForThisMonth(){
		driver.pauseExecutionFor(7000);
		driver.click(PC_ORDERS_FOR_THIS_MONTH_LINK_LOC, "have orders for this month");
		if(driver.isElementVisible(PC_ORDERS_FOR_THIS_MONTH_LINK_LOC)){
			driver.clickByJS(RFWebsiteDriver.driver, PC_ORDERS_FOR_THIS_MONTH_LINK_LOC, "");
		}
	}

	public void clickPCOrderForNextMonth(){
		driver.waitForSpinImageToDisappearPulse();
		driver.click(PC_ORDERS_FOR_NEXT_MONTH_LINK_LOC, "have orders for this month");
		driver.waitForLSDLoaderAnimationImageToDisappear();
	}

	public String getTotalNumberOfPendingAutoshipOrders(){
		int count = driver.findElements(PENDING_AUTOSHIP_ORDERS_LOC).size();
		logger.info("total count of pending autoship"+count);
		return ""+count;
	}

	public void clickViewAllCustomers() {
		driver.waitForSpinImageToDisappearPulse(30);
		driver.waitForElementPresent(VIEW_All_PC_CUSTOMERS);
		driver.click(VIEW_All_PC_CUSTOMERS, "count of all pc customers");
		logger.info("clicked in view PC customer link");
	}

	public boolean isSortFilterQVHighToLowAppliedSuccessfully(){
		int totalNumberOfPCHavingQV = driver.findElements(ALL_QV_COUSTOMERS_LOC).size();
		boolean isFilterApplied = true;
		for(int count=1; count<(totalNumberOfPCHavingQV-2); count++){
			String firstQVValueFromUI = driver.findElement(By.xpath(String.format(qvValueLoc, count))).getText();
			String secondQVValueFromUI = driver.findElement(By.xpath(String.format(qvValueLoc, count+1))).getText();
			firstQVValueFromUI = getQVValue(firstQVValueFromUI);
			secondQVValueFromUI = getQVValue(secondQVValueFromUI);
			int firstValue = Integer.parseInt(firstQVValueFromUI);
			int secondValue = Integer.parseInt(secondQVValueFromUI);
			if(!(secondValue>=firstValue))
				isFilterApplied = false;
		}
		return isFilterApplied;
	}

	public boolean isCountGreaterThanTwo(String count){
		int finalCount = Integer.parseInt(count);
		return finalCount>=2;
	}

	public String getCountOfPCOrdersForFurtherOut(){
		driver.waitForSpinImageToDisappearPulse();
		return driver.getText(COUNT_OF_PC_ORDERS_FOR_FURTHER_OUT_LOC, "count of pc autoship orders for further out");
	}

	public boolean isContactIconPresentAtPCOrderCardUnderCustomer(){
		return 	driver.isElementPresent(CONTACT_ICON_AT_PC_CARD_UNDER_CUSTOMER_LOC);
	}

	public boolean isTwoInitialIconPresentAtPCOrderCardUnderCustomer(){
		return 	!(driver.getText(TWO_INITIAL_ICON_AT_PC_CARD_UNDER_TEAM_LOC).isEmpty());
	}

	public void clickUserAccordingToCount(String count){
		driver.click((By.xpath(String.format(userListLoc, count))),"clicked on user");
	}

	public String getCountOfPCUnderViewCustomerSection(String categoryCount){
		driver.waitForSpinImageToDisappearPulse(20);
		return driver.getText(By.xpath(String.format(subCategoryOfCustomerUnderViewCustomerLoc, categoryCount)),"count of deactivated pcs");
	}

	public String getCountOfDeactivatedPCFromHeader(){
		driver.waitForElementPresent(COUNT_OF_DEACTIVATED_PC_AT_HEADER_LOC);
		return driver.getText(COUNT_OF_DEACTIVATED_PC_AT_HEADER_LOC, "count of deactivated pc at header");
	}

	public String getCountOfPCOrdersForNextMonthFromHeader(){
		driver.waitForSpinImageToDisappearPulse();
		driver.waitForElementPresent(COUNT_OF_PC_ORDERS_FOR_NEXT_MONTH_AT_HEADER_LOC);
		return driver.getText(COUNT_OF_PC_ORDERS_FOR_NEXT_MONTH_AT_HEADER_LOC, "count of pc orders for next month at header");
	}

	public String getCountOfAllPCForThisMonth(){
		driver.waitForElementPresent(COUNT_OF_ALL_PC_CUSTOMERS,10000);
		driver.waitForSpinImageToDisappearPulse(30000);
		driver.pauseExecutionFor(10000);
		return driver.getText(COUNT_OF_ALL_PC_CUSTOMERS, "count of all PC customer for this month and next month");
	}

	public boolean isFirstOrderDetailsPresent(String value){
		driver.pauseExecutionFor(5000);
		driver.waitForElementPresent(By.xpath(String.format(detailsOfFirstOrderLoc, value)));
		return (!driver.getText(By.xpath(String.format(detailsOfFirstOrderLoc, value))).isEmpty());
	}

	public void clickSubCategoryOnCustomerPageUnderViewOurCustomer(String categoryCount){
		driver.waitForElementToBeVisible(By.xpath(String.format(subCategoryOfCustomerUnderViewCustomerLoc, categoryCount)), 10);
		driver.click(By.xpath(String.format(subCategoryOfCustomerUnderViewCustomerLoc, categoryCount)), categoryCount+" category clicked");
		driver.pauseExecutionFor(5000);
	}


	public String getEnrolledDateOfFirstDeactivatePC(){
		driver.pauseExecutionFor(1000);
		driver.waitForElementToBeVisible(ENROLLED_DATE_OF_FIRST_DEACTIVATED_PC_LOC, 100);
		return driver.getText(ENROLLED_DATE_OF_FIRST_DEACTIVATED_PC_LOC, "enrolled date of first deactivated pc");
	}

	public String getNameOfFirstDeactivatePC(){
		driver.waitForElementToBeVisible(NAME_OF_FIRST_DEACTIVATED_PC_LOC, 100);
		return driver.getText(NAME_OF_FIRST_DEACTIVATED_PC_LOC, "name of first deactivated pc");
	}

}