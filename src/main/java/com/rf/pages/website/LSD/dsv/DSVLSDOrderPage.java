package com.rf.pages.website.LSD.dsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.rf.core.driver.website.RFWebsiteDriver;

public class DSVLSDOrderPage extends DSVLSDRFWebsiteBasePage {

	private static final Logger logger = LogManager
			.getLogger(DSVLSDOrderPage.class.getName());

	public DSVLSDOrderPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static String filterNameLoc= "//button[contains(text(),'%s')]";
	private static String previousMonthDateLoc= "//ul[contains(@class,'sort-options')]//li[%s]";
	private static String getOrderStatus= "//div[@id='sub-stage']/descendant::order-card[@class='au-target'][%s]//ul[contains(@class,'data-point-list')]/li[1]/span[1]";
	private static String getOrderType = "//div[@id='sub-stage']/descendant::order-card[@class='au-target'][1]//ul[contains(@class,'data-point-list')]/li[2]/span";
	
	private static final By FIRST_PROCESSED_CRP_AUTOSHIP_ORDER_LOC = By.xpath("//div[@id='sub-stage']/descendant::span[text()='Processed'][1]/following::span[text()='CRP'][1]");
	private static final By FIRST_PROCESSED_CONSULTANT_ADHOC_ORDER_LOC = By.xpath("//div[@id='sub-stage']/descendant::span[text()='Processed'][1]/following::span[text()='Consultant Order'][1]") ;
	private static final By FIRST_PROCESSED_CONSULTANT_SOO_ORDER_LOC = By.xpath("//div[@id='sub-stage']/descendant::span[text()='Processed'][1]/following::span[text()='Sales Override Order'][1]") ;
	private static final By FIRST_PROCESSED_PULSE_SUBSCRIPTION_ORDER_LOC = By.xpath("//div[@id='sub-stage']/descendant::span[text()='Processed'][1]/following::span[text()='Pulse Subscription'][1]") ;
	private static final By FIRST_NAME_ORDER_DETAIL_PAGE_HEADER = By.xpath("//div[contains(@class,'f-gradient-header')]/descendant::h4");
	private static final By ORDER_TYPE_AND_COUNTRY_CODE_FROM_ORDER_DETAIL_PAGE_HEADER = By.xpath("//div[contains(@class,'f-gradient-header')]/descendant::h4/following::div[1]");
	private static final By SV_AND_PSQV_VALUE_ORDER_DETAIL_PAGE_HEADER = By.xpath("//div[contains(@class,'f-gradient-header')]/descendant::h4/following::div[2]");
	private static final By ORDER_FILTER_LOC = By.xpath("//div[@id='sub-stage']/descendant::img[contains(@class,'media-top')][2]");
	private static final By APPLY_FILTER_BTN_LOC = By.xpath("//button[contains(text(),'Apply')]");
	private static final By FIRST_ORDER_LOC = By.xpath("//div[@id='sub-stage']/descendant::order-card[@class='au-target'][1]") ;
	private static final By ALL_DATE_FILTER_LOC = By.xpath("//ul[contains(@class,'sort-options')]//li");
	private static final By DATE_FILTER_LOC = By.xpath("//div[@id='sub-stage']/descendant::img[contains(@class,'media-top')][1]");
	private static final By ALL_ORDERS = By.xpath("//div[@id='sub-stage']/descendant::order-card[@class='au-target']");
	private static final By FIRST_APPLIED_FILTER_LOC = By.xpath("//li[contains(text(),'Filters applied')]/following-sibling::li[1]") ;
	private static final By FIRST_ORDER_USERNAME_LOC = By.xpath("//div[@id='sub-stage']/descendant::order-card[@class='au-target'][1]//div[contains(@class,'padding-by')]/div[1]");
	private static final By FIRST_ORDER_PSQV_LOC = By.xpath("//div[@id='sub-stage']/descendant::order-card[@class='au-target'][1]//ul[contains(@class,'data-point-list')]/li[3]/span");
	private static final By FIRST_ORDER_DATE= By.xpath("//div[@id='sub-stage']/descendant::order-card[@class='au-target'][1]//ul[contains(@class,'data-point-list')]/li[1]/span[2]");
	private static final By FIRST_PROCESSED_ORDER_LOC = By.xpath("//div[@id='sub-stage']/descendant::span[text()='Processed'][1]") ;

	public void clickOrderFilter(){
		driver.click(ORDER_FILTER_LOC, "order filter");
	}

	public void clickOrderCategoryFilter(String filterCategory){
		driver.click(By.xpath(String.format(filterNameLoc, filterCategory)), "order filter category");
	}
	public void clickApplyFilterBtn(){
		driver.click(APPLY_FILTER_BTN_LOC, "Apply filter btn");
	}
	public boolean isOrderPresent(){
		driver.waitForElementPresent(FIRST_ORDER_LOC);
		return driver.isElementPresent(FIRST_ORDER_LOC);
	}
	public void clickDateFilter(){
		driver.click(DATE_FILTER_LOC, "Date filter");
	}
	public void selectPreviousMonthDateUntilOrderPresent(){
		int previousMonthCount= 1;
		int sizeOfPreviousMonth = 0;
		clickDateFilter();
		driver.waitForElementPresent(By.xpath(String.format(previousMonthDateLoc, previousMonthCount)));
		driver.click(By.xpath(String.format(previousMonthDateLoc, previousMonthCount)),"previous month select");
		sizeOfPreviousMonth = driver.findElements(ALL_DATE_FILTER_LOC).size();
		for(int count=2; count<=sizeOfPreviousMonth; count++){
			if(driver.isElementPresent(FIRST_ORDER_LOC)){
				break;
			}else{
				clickDateFilter();
				driver.waitForElementPresent(By.xpath(String.format(previousMonthDateLoc, count)));
				driver.click(By.xpath(String.format(previousMonthDateLoc, count)),"previous month select");
			}
		}

	}
	public int getCountOfTotalOrders(){
		driver.waitForElementPresent(ALL_ORDERS);
		int countOfOrders = driver.findElements(ALL_ORDERS).size();
		logger.info("Count of Orders: "+countOfOrders);
		return countOfOrders;
	}

	public String getOrderStatus(int orderNumber){
		driver.waitForElementPresent(By.xpath(String.format(getOrderStatus, orderNumber)));
		String orderStatus = driver.findElement(By.xpath(String.format(getOrderStatus, orderNumber))).getText();
		logger.info("Order status for order number"+orderNumber+"th is: "+orderStatus);
		return orderStatus;
	}
	public boolean isOrderStatusFilterAppliedSuccessfully(String orderStatus){
		boolean orderStatusFlag = true;
		String orderStatusFromUI = null;
		int totalNoOfOrders = getCountOfTotalOrders();
		for(int count=1; count<=totalNoOfOrders; count++){
			orderStatusFromUI = getOrderStatus(count);
			if(orderStatusFromUI.toLowerCase().contains(orderStatus.toLowerCase())){
				continue;
			}else{
				orderStatusFlag = false;
			}
		}
		return orderStatusFlag;
	}
	public void removeAllAppliedFilters(){
		int totalAppliedFilters = 0;
		totalAppliedFilters = driver.findElements(By.xpath("//li[contains(text(),'Filters applied')]/following-sibling::li")).size();
		for(int count=1; count<=totalAppliedFilters; count++){
			driver.click(FIRST_APPLIED_FILTER_LOC, "first applied filter");
		}
	}
	public String getOrderType(int orderNumber){
		driver.waitForElementPresent(By.xpath(String.format(getOrderType, orderNumber)));
		String orderType = driver.findElement(By.xpath(String.format(getOrderType, orderNumber))).getText();
		logger.info("Order type for order number"+orderNumber+"th is: "+orderType);
		return orderType;
	}
	public boolean isOrderTypeFilterAppliedSuccessfullyForConsultant(){
		boolean orderTypeFlag = true;
		String orderTypeFromUI = null;
		int totalNoOfOrders = getCountOfTotalOrders();
		for(int count=1; count<=totalNoOfOrders; count++){
			orderTypeFromUI = getOrderType(count);
			if(orderTypeFromUI.toLowerCase().contains("crp") || orderTypeFromUI.toLowerCase().contains("pulse")){
				continue;
			}else{
				orderTypeFlag = false;
			}
		}
		return orderTypeFlag;
	}

	public boolean isOrderTypeFilterAppliedSuccessfullyForPC(){
		boolean orderTypeFlag = true;
		String orderTypeFromUI = null;
		int totalNoOfOrders = getCountOfTotalOrders();
		for(int count=1; count<=totalNoOfOrders; count++){
			orderTypeFromUI = getOrderType(count);
			if(orderTypeFromUI.toLowerCase().contains("perks")){
				continue;
			}else{
				orderTypeFlag = false;
			}
		}
		return orderTypeFlag;
	}

	public boolean isOrderTypeFilterAppliedSuccessfullyForPersonal(){
		boolean orderTypeFlag = true;
		String orderTypeFromUI = null;
		int totalNoOfOrders = getCountOfTotalOrders();
		for(int count=1; count<=totalNoOfOrders; count++){
			orderTypeFromUI = getOrderType(count);
			if(orderTypeFromUI.toLowerCase().contains("consultant order") || orderTypeFromUI.toLowerCase().contains("pulse subscription")){
				continue;
			}else{
				orderTypeFlag = false;
			}
		}
		return orderTypeFlag;
	}

	public boolean isOrderTypeFilterAppliedSuccessfullyForRetailOrder(){
		boolean orderTypeFlag = true;
		String orderTypeFromUI = null;
		int totalNoOfOrders = getCountOfTotalOrders();
		for(int count=1; count<=totalNoOfOrders; count++){
			orderTypeFromUI = getOrderType(count);
			if(orderTypeFromUI.toLowerCase().contains("retail")){
				continue;
			}else{
				orderTypeFlag = false;
			}
		}
		return orderTypeFlag;
	}
	public String getFirstOrderUserName(){
		driver.waitForElementPresent(FIRST_ORDER_USERNAME_LOC);
		String name = driver.findElement(FIRST_ORDER_USERNAME_LOC).getText();
		logger.info("First order's username is: "+name);
		return name;
	}
	public String getFirstOrderPSQVValue(){
		driver.waitForElementPresent(FIRST_ORDER_PSQV_LOC);
		String PSQV = driver.findElement(FIRST_ORDER_PSQV_LOC).getText();
		logger.info("First order's status is: "+PSQV);
		return PSQV;
	}
	public boolean isValueEmpty(String value){
		return (!value.isEmpty());
	}
	public boolean isFirstOrderDatePresent(){
		driver.waitForElementPresent(FIRST_ORDER_DATE);
		return driver.IsElementVisible(driver.findElement(FIRST_ORDER_DATE));
	}

	public String getFirstNameFromOrderDetailPageHeader(){
		driver.waitForElementPresent(FIRST_NAME_ORDER_DETAIL_PAGE_HEADER);
		return driver.getText(FIRST_NAME_ORDER_DETAIL_PAGE_HEADER,"First Name order detail from page");
	}
	public String getOrderTypeAndCountryCodeFromOrderDetailPageHeader(){
		driver.waitForElementPresent(ORDER_TYPE_AND_COUNTRY_CODE_FROM_ORDER_DETAIL_PAGE_HEADER);
		return driver.getText(ORDER_TYPE_AND_COUNTRY_CODE_FROM_ORDER_DETAIL_PAGE_HEADER,"Order type and country Code");
	}
	public String getSVAndPSQVFromOrderDetailPageHeader(){
		driver.waitForElementPresent(SV_AND_PSQV_VALUE_ORDER_DETAIL_PAGE_HEADER);
		return driver.getText(SV_AND_PSQV_VALUE_ORDER_DETAIL_PAGE_HEADER,"SV value order detail page");
	}
	public void clickFirstProcessedCRPAutoshipOrder(){
		driver.waitForElementPresent(FIRST_PROCESSED_CRP_AUTOSHIP_ORDER_LOC);
		driver.click(FIRST_PROCESSED_CRP_AUTOSHIP_ORDER_LOC,"");
	}
	public void clickFirstProcessedConsultantAdhocOrder(){
		driver.waitForElementPresent(FIRST_PROCESSED_CONSULTANT_ADHOC_ORDER_LOC);
		driver.click(FIRST_PROCESSED_CONSULTANT_ADHOC_ORDER_LOC,"");
	}
	public void clickFirstProcessedConsultantSOOOrder(){
		driver.waitForElementPresent(FIRST_PROCESSED_CONSULTANT_SOO_ORDER_LOC);
		driver.click(FIRST_PROCESSED_CONSULTANT_SOO_ORDER_LOC,"");
	}
	public void clickFirstProcessedPulseSubscriptionOrder(){
		driver.waitForElementPresent(FIRST_PROCESSED_PULSE_SUBSCRIPTION_ORDER_LOC);
		driver.click(FIRST_PROCESSED_PULSE_SUBSCRIPTION_ORDER_LOC,"");
	}

}
