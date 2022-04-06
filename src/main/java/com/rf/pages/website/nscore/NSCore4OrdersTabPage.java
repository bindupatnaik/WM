package com.rf.pages.website.nscore;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;

public class NSCore4OrdersTabPage extends NSCore4RFWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(NSCore4OrdersTabPage.class.getName());

	public NSCore4OrdersTabPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static String productsInfoTableLoc = "//td[contains(text(),'%s')]/following-sibling::td";
	private static String FirstNameLoc = "//table[@id='orders']//tr[@class='GridColHead']/th[2]//following::tbody/tr[%s]//td[2]";
	private static String LastNameLoc = "//table[@id='orders']//tr[@class='GridColHead']/th[2]//following::tbody/tr[%s]//td[3]";
	private static String orderStatusLoc = "//table[@id='orders']//tr[@class='GridColHead']/th[2]//following::tbody/tr[%s]//td[4]";
	private static String orderTypeLoc = "//table[@id='orders']//tr[@class='GridColHead']/th[2]//following::tbody/tr[%s]//td[5]";
	private static String suggestionStartOrderLoc = "//div[contains(@class,'resultItem')]//p[contains(text(),'%s')]";
	private static String skuBasedProductPricePopupLoc="//table[@id='overrideProducts']//following::td[text()='%s']/following::input[1]";
	private static String skuBasedProductCVPopupLoc="//table[@id='overrideProducts']//following::td[text()='%s']/following::input[1]";
	private static String skuBasedProductQVPopupLoc="//table[@id='overrideProducts']//following::td[text()='%s']/following::input[1]";
	private static String skuBasedProductPriceOrderCreationPageLoc="//table[@class='FormTable Section']/descendant::td[text()='%s']/following::td[4]";
	private static String skuBasedProductCVOrderCreationPageLoc="//table[@class='FormTable Section']/descendant::td[text()='%s']/following::td[5]";
	private static String skuBasedProductQVOrderCreationPageLoc="//table[@class='FormTable Section']/descendant::td[text()='%s']/following::td[6]";
	private static String productSKUFromCartLoc =".//*[@id='products']/descendant::tr[%s]//td[2]";

	private static final By PAYMENT_LINK_ORDER_DETAILS_PAGE_LOC=By.xpath("//td[contains(text(),'Payment')]/following::tbody[2]//a");	
	private static final By RESTOCKING_AMOUNT_LOC=By.id("restockingAmount");
	private static final By RETURN_ORDER_LINK_LOC=By.id("returnOrder");
	private static final By VT_RETURN_LINK_LOC=By.id("vtReturnOrder");
	private static final By SELECTED_SHIPPING_METHOD_LOC = By.xpath("//td[@id='shippingMethods']/descendant::input[@checked='checked']/following-sibling::b");
	private static final By FEDEX_GROUND_SHIPPING_METHOD_LOC = By.xpath("//td[@id='shippingMethods']/descendant::b[text()='FedEx Grnd']/preceding-sibling::input");
	private static final By FEDEX_2DAY_SHIPPING_METHOD_LOC = By.xpath("//td[@id='shippingMethods']/descendant::b[text()='FedEx 2 day']/preceding-sibling::input");
	private static final By FEDEX_1DAY_SHIPPING_METHOD_LOC = By.xpath("//td[@id='shippingMethods']/descendant::b[text()='FedEx 1 day']/preceding-sibling::input");
	private static final By SHIPPING_AMOUNT_FROM_ORDER_CREATION_PAGE_LOC=By.xpath("//*[@id='totalBar']//span[contains(@class,'shippingTotal')]");
	private static final By ADD_SHIPPING_ADDRESS_ZIPCODE_LOC = By.xpath("//input[@id='zip']");
	private static final By SHIPPING_PROFILE_ADDRESS_LOC=By.xpath("//div[@id='shippingAddressContainer']/div[1]");
	private static final By STATE_DD_LOC  = By.id("state");
	private static final By STATE_DD_OPTION_LOC  = By.xpath("//select[@id='state']/option[2]");
	private static final By TOTAL_AMT_TO_BE_REFUNDED_LOC = By.xpath("//b[contains(text(),'Total Amount To Be Refunded')]/following-sibling::b");
	private static final By REFUNDED_SHIPPING_LOC = By.xpath("//input[@id='shippingRefunded']");
	private static final By REFUNDED_HANDLING_LOC = By.xpath("//input[@id='handlingRefunded']");
	private final static By CANCEL_ORDER_LINK_LOC = By.xpath("//a[@id='cancelOrder']");
	private final static By ORDER_STATUS_LOC = By.xpath("//div[@class='Content']//td[contains(text(),'Status')]/following::b[1]");
	private final static By ORDER_ID_INPUT_FIELD_LOC = By.xpath("//input[@id='txtSearch']");
	private static final By GO_SEARCH_BTN = By.xpath("//a[@id='btnGo']/img[@alt='Go']");
	private static final By DROP_DOWN_LOC = By.xpath("//select[@id='cboSearchCol']");
	private static final By FIRST_NAME  = By.xpath("//table[@id='orders']//tr[1]/td[2]/a");
	private static final By LAST_NAME  = By.xpath("//table[@id='orders']//tr[1]/td[3]/a");	 
	private static final By ADVANCED_SEARCH_INPUT_FIELD_LOC = By.xpath("//input[@id='txtAdvancedSearch']");
	private static final By ADVANCED_SEARCH_GO_BTN = By.xpath("//a[@id='btnAdvancedGo']");
	private static final By TOTAL_ROWS_SEARCH_RESULT_LOC = By.xpath("//table[@id='orders']/tbody/tr");
	private static final By START_A_NEW_ORDER_LINK_LOC = By.xpath("//span[text()='Start a New Order']");
	private static final By TXT_FIELD_START_ORDER_LOC = By.id("txtCustomerSuggest");
	private static final By SUGGESTION_START_ORDER_LOC = By.xpath("//div[@class='resultItem odd hover']");
	private static final By START_ORDER_BTN_LOC = By.xpath("//a[@id='btnStartOrder']");
	private final static By BROWSE_ORDERS_LINK_LOC = By.xpath("//a[text()='Browse Orders']");
	private final static By SELECT_STATUS_DD_LOC = By.xpath("//select[@id='statusFilter']");
	private final static By SELECT_TYPE_DD_LOC = By.xpath("//select[@id='typeFilter']");
	private static final By GO_SEARCH_BTN_BROWSE_ORDERS_LOC = By.xpath("//a[@id='btnSearchAccounts']/img");
	private static final By PAYMENT_APPLY_LINK_LOC = By.xpath("//a[text()='Apply']");
	private static final By RETURN_ORDER_LOC  = By.id("returnOrder");
	private static final By UPDATE_LINK_LOC  = By.id("btnUpdate");
	private static final By SUBMIT_RETURN_BTN_LOC  = By.id("btnSubmit");
	private static final By GET_ORDER_TYPE  = By.xpath("//p[contains(text(),'Order Type')]");
	private static final By RETURNED_ORDER_CHKBOX_LOC = By.xpath("//input[@class='returned']");
	private static final By RETURN_QUANTITY_INPUT_TXT_LOC = By.xpath("//input[contains(@class,'returnQuantity')]");
	private static final By RETURN_QUANTITY_LOC = By.xpath("//td[contains(text(),'Products')]/following::td[1]//tr[@class='GridRow']/td[4]");
	private static final By RESTOCKING_FEE_TEXT_LOC = By.xpath("//td[contains(text(),'Products')]/following::td[1]//td[contains(text(),'Restocking Fee')]");
	private static final By RESTOCKING_FEE_PERCENT_TXT_BOX_LOC  = By.id("restockingPercent");
	private static final By FIRST_NAME_IN_BROWSE_ORDERS_LOC=By.id("input_0");
	private static final By LAST_NAME_IN_BROWSE_ORDERS_LOC=By.id("input_1");
	private static final By SEARCH_ICON_LOC=By.id("searchIcon");
	private static final By CID_IN_BROWSE_ORDER_LOC=By.id("input_2");
	private static final By FROM_DATE_IN_BROWSE_ORDER_LOC=By.id("input_4");
	private static final By PERFORM_OVERRIDES_BUTTON_LOC=By.id("btnPerformOverrides");
	private static final By OVERRIDE_TAX_LOC=By.id("txtOverrideTax");
	private static final By OVERRIDE_SHIPPING_LOC=By.id("txtOverrideShipping");
	private static final By SUBTOTAL_ON_OVERRIDE_ORDER_LOC=By.xpath("//*[@id='productTotalBar']/following::span[contains(@class,'subtotal')][2]");
	private static final By RECALCULATE_TAX_LOC=By.xpath("//a[contains(text(),'Recalculate Tax')]");
	private static final By DEPARTMENT_ON_OVERRIDE_REASONS_LOC=By.id("optDept");
	private static final By TYPE_ON_OVERRIDE_REASONS_LOC=By.id("optType");
	private static final By REASONS_ON_OVERRIDE_REASONS_LOC=By.id("optReason");
	private static final By SAVE_BUTTON_ON_PERFORM_OVERRIDE_LOC=By.id("btnSaveOverride");
	private static final By PAYMENTS_APPLIED_ON_ORDER_DETAILS_PAGE_LOC=By.xpath("//div[@class='SectionHeader']/following::table[7]/descendant::td[starts-with(normalize-space(text()),'Payments Applied:')]/following-sibling::td/b[1]");
	private static final By SHIPPING_ON_ORDER_CREATION_PAGE_LOC=By.xpath("//span[contains(@class,'shippingTotal')]");
	private static final By ORDER_TOTAL_FROM_ORDER_CREATION_PAGE_LOC=By.xpath("//span[contains(@class,'grandTotal')]");
	private static final By TAX_FROM_ORDER_CREATION_PAGE_LOC=By.xpath("//span[contains(@class,'taxTotal')]");
	private static final By SUBTOTAL_AMOUNT_FROM_ORDER_CREATION_PAGE_LOC=By.xpath("//*[@id='totalBar']//span[contains(@class,'subtotal')]");
	private static final By COMMISSIONABLE_VALUE_LOC=By.xpath("//*[@id='overrideProducts']/descendant::*[contains(@id,'cvAmount')][1]");
	private static final By PRODUCT_PRICE_ERROR_MESSAGE_LOC=By.xpath("//div[@id='overridesModal']//div[@id='message2']");
	private static final By OVERRIDE_PRICE_LOC=By.xpath("//input[@name='overridePrices']");
	private static final By OVERRIDE_CV_LOC=By.xpath("//input[@name='overrideCVAmount']");
	private static final By OVERRIDE_QV_LOC=By.xpath("//input[@name='overrideQVAmount']");
	private static final By TO_DATE_IN_BROWSE_ORDER_LOC=By.id("input_6");
	private static final By ORDERS_SEARCH_RESULTS = By.xpath("//span[text()='Order Number']/following::div[@class='ng-isolate-scope']");
	private static final By CUSTOMER_NAME_FROM_ORDER_DETAILS_PAGE_LOC = By.xpath("//div[@class='CustomerLabel']/a");
	private static final By UPDATE_CART_LINK_LOC=By.id("btnUpdateCart");
	private static final By CALENDAR_NEXT_MONTH_BUTTON_LOC=By.xpath("//span[contains(text(),'Next')]");
	private static final By TAX_ORDERTOTAL_SUBTOTAL_FROM_ORDER_DETAILS_PAGE_LOC=By.xpath("//td[contains(text(),'Payment')]/following::tr[@class='GridTotalBar']/td[contains(text(),'Subtotal')]/following-sibling::td");
	private static final By ORDER_STATUS_FROM_ORDER_DETAILS_PAGE_LOC=By.xpath("//div[@class='CustomerLabel']/following-sibling::p");
	private static final By COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC=By.xpath("//input[@id='commissionDate']");
	private static final By PAYMENT_DETAIL_POPUP_ON_ORDER_DETAILS_PAGE_LOC=By.id("paymentInfoModal");
	private static final By PAYMENT_DETAIL_POPUP_ON_ORDER_DETAILS_PAGE_CLOSE_LOC=By.xpath(".//*[@id='paymentInfoModal']/div/a[text()='Close']");

	private static final String completeNameSearchResultLoc="//a[contains(text(),'%s')]";
	private static String skuBasedProductPriceSOOPopupLoc="//table[@id='overrideProducts']//following::td[text()='%s']/following::input[1]";
	private static final String productQuantityLoc="//td[contains(text(),'%s')]/following::input[@class='quantity']";
	private static String productCVBasedOnSKULoc="//table[@id='overrideProducts']//following::td[text()='%s']/following::input[contains(@id,'cvAmount')]";
	private static String returnProductCheckboxBasedOnSKU="//td[contains(text(),'%s')]/preceding::input[2]";
	private static String returnQtyBasedOnSKU="//td[contains(text(),'%s')]/following::span[@class='quantity']";
	private static String returnQtyTextboxBasedOnSKU="//td[contains(text(),'%s')]/following::input[contains(@class,'returnQuantity ')]";
	private static String returnProductSKUOnOrderDetails="//table[@class='DataGrid']/descendant::td[contains(text(),'%s')]";
	private static String commisionDateFromCalendarLoc="//a[contains(text(),'%s')]";
	private static String productQVBasedOnSKULoc="//table[@class='FormTable Section']/descendant::td[text()='%s']/following::td[6]";
	private static String skuBasedProductQVSOOPopupLoc="//table[@id='overrideProducts']//following::td[text()='%s']/following::input[3]";

	public void clickCancelOrderBtn(){
		driver.click(CANCEL_ORDER_LINK_LOC,"");
		driver.waitForPageLoad();
	}

	public boolean validateOrderStatus() {
		driver.waitForElementToBeVisible(By.xpath("//div[@class='Content']//td[contains(text(),'Status')]/following::b[contains(text(),'Cancelled')]"), 20);
		String status = driver.getText(ORDER_STATUS_LOC,"order status");
		if(status.equalsIgnoreCase("Cancelled")){
			return true;
		}return false;
	}

	public void enterOrderIDInInputField(String orderId) {
		driver.quickWaitForElementPresent(ORDER_ID_INPUT_FIELD_LOC);
		driver.clear(ORDER_ID_INPUT_FIELD_LOC);
		driver.type(ORDER_ID_INPUT_FIELD_LOC, orderId);
		logger.info("orderId field enterd as: "+orderId);
		driver.click(GO_SEARCH_BTN,"");
		logger.info("Search Go button clicked");
		driver.waitForPageLoad();
	}

	public boolean isOrderDetailPagePresent() {
		String url = driver.getCurrentUrl();
		return url.contains("Orders/Details");

	}

	public boolean isOrderInformationPresent(String InfoSection) {
		return driver.isElementPresent(By.xpath(String.format(productsInfoTableLoc,InfoSection)));
	}

	public void selectDropDownAdvancedSearch(String text) {
		Select dropDown = new Select(driver.findElement(DROP_DOWN_LOC));
		dropDown.selectByVisibleText(text);
		logger.info("drop down selected value is: "+text);
	}

	public void enterValueInAdvancedSearchInputField(String value) {
		driver.quickWaitForElementPresent(ADVANCED_SEARCH_INPUT_FIELD_LOC);
		driver.clear(ADVANCED_SEARCH_INPUT_FIELD_LOC);
		driver.type(ADVANCED_SEARCH_INPUT_FIELD_LOC, value);
		logger.info("advanced search field enterd as: "+value);
		driver.click(ADVANCED_SEARCH_GO_BTN,"");
		logger.info("Advanced Search Go button clicked");
		driver.waitForPageLoad();
	}

	public boolean isSearchResultFirstName(String firstName){
		boolean status = false;
		driver.waitForElementPresent(TOTAL_ROWS_SEARCH_RESULT_LOC);
		int totalSearchResult = driver.findElements(TOTAL_ROWS_SEARCH_RESULT_LOC).size();
		for(int i=1;i<=totalSearchResult;i++){
			String firstNameUI = driver.findElement(By.xpath(String.format(FirstNameLoc,i))).getText();
			if(firstNameUI.equalsIgnoreCase(firstName)){
				status = true;
				continue;
			}
			else{
				status = false;
				break;
			}

		}
		return status;
	}

	public boolean isSearchResultLastName(String lastName){
		boolean status = false;
		driver.waitForElementPresent(TOTAL_ROWS_SEARCH_RESULT_LOC);
		int totalSearchResult = driver.findElements(TOTAL_ROWS_SEARCH_RESULT_LOC).size();
		for(int i=1;i<=totalSearchResult;i++){
			String lastNameUI = driver.findElement(By.xpath(String.format(LastNameLoc,i))).getText();
			if(lastNameUI.equalsIgnoreCase(lastName)){
				status = true;
				continue;
			}
			else{
				status = false;
				break;
			}
		}
		return status;
	}

	public void clickStartANewOrderLink() {
		driver.quickWaitForElementPresent(START_A_NEW_ORDER_LINK_LOC);
		driver.click(START_A_NEW_ORDER_LINK_LOC,"");
		logger.info("start a new order link in order tab clicked");

	}

	public void enterAccountNameAndClickStartOrder(String accountName) {
		driver.quickWaitForElementPresent(TXT_FIELD_START_ORDER_LOC);
		driver.type(TXT_FIELD_START_ORDER_LOC, accountName);
		logger.info("start order text field entered by: "+accountName);
		driver.waitForElementPresent(SUGGESTION_START_ORDER_LOC);
		driver.click(SUGGESTION_START_ORDER_LOC,"");
		driver.click(START_ORDER_BTN_LOC,"");
		logger.info("start button is clicked after entered account name");
	}

	public void clickBrowseOrdersLlink(){
		driver.waitForElementPresent(BROWSE_ORDERS_LINK_LOC);
		driver.click(BROWSE_ORDERS_LINK_LOC,"");
		logger.info("Browse Orders link is clicked");
		driver.waitForPageLoad();
	}

	public void selectStatusDD(String Status){
		Select sel =new Select(driver.findElement(SELECT_STATUS_DD_LOC));
		sel.selectByVisibleText(Status);
	}

	public void selectTypeDD(String Type){
		Select sel =new Select(driver.findElement(SELECT_TYPE_DD_LOC));
		sel.selectByVisibleText(Type);
	}

	public void clickGoSearchBtn(){
		driver.waitForElementPresent(GO_SEARCH_BTN_BROWSE_ORDERS_LOC);
		driver.click(GO_SEARCH_BTN_BROWSE_ORDERS_LOC,"");
		logger.info("'GO' Search Btn is clicked");
		driver.pauseExecutionFor(3000);
		driver.waitForPageLoad();
	}

	public boolean validateOrderStatusRow(){
		boolean status = true;
		driver.waitForElementPresent(TOTAL_ROWS_SEARCH_RESULT_LOC);
		int totalSearchResult = driver.findElements(TOTAL_ROWS_SEARCH_RESULT_LOC).size();
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		for(int i=2;i<=totalSearchResult;i++){
			String actualOrderStatus= driver.findElement(By.xpath(String.format(orderStatusLoc,i))).getText();
			if(actualOrderStatus.equalsIgnoreCase("Pending")){
				continue;
			}else{
				status = false;
				break;
			}
		}
		return status;
	}

	public boolean validateOrderTypeRow(){
		boolean status = true;
		driver.waitForElementPresent(TOTAL_ROWS_SEARCH_RESULT_LOC);
		int totalSearchResult = driver.findElements(TOTAL_ROWS_SEARCH_RESULT_LOC).size();
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		for(int i=2;i<=totalSearchResult;i++){
			String actualOrderType= driver.findElement(By.xpath(String.format(orderTypeLoc,i))).getText();
			if(actualOrderType.equalsIgnoreCase("PC")){
				continue;
			}else{
				status = false;
				break;
			}
		}
		return status;
	}

	public void clickPaymentApplyLink() {
		driver.quickWaitForElementPresent(PAYMENT_APPLY_LINK_LOC);
		driver.click(PAYMENT_APPLY_LINK_LOC,"");
		logger.info("payment apply link is clicked");
	}

	public boolean validateOrderStatusAfterSubmitOrder(){
		String status = driver.findElement(ORDER_STATUS_LOC).getText();
		if(status.equalsIgnoreCase("Submitted")){
			return true;
		}return false;
	}

	public void clickReturnOrderLink(){
		driver.waitForElementPresent(RETURN_ORDER_LOC);
		driver.click(RETURN_ORDER_LOC,"");
		logger.info("Return order link clicked");
		driver.waitForPageLoad();
	}

	public String getOrderType(){
		driver.waitForElementPresent(GET_ORDER_TYPE);
		String orderType = driver.findElement(GET_ORDER_TYPE).getText();
		logger.info("Order type is "+orderType);
		return orderType;
	}

	public void enableReturnedChkBox(){
		driver.quickWaitForElementPresent(RETURNED_ORDER_CHKBOX_LOC);
		driver.click(RETURNED_ORDER_CHKBOX_LOC,"");
		logger.info("Returned Order CheckBox is Enabled");
		driver.pauseExecutionFor(2000);
	}

	public void enterReturnQuantity(String quantity){
		driver.waitForElementPresent(RETURN_QUANTITY_INPUT_TXT_LOC);
		driver.type(RETURN_QUANTITY_INPUT_TXT_LOC, quantity);
		logger.info("Return quantity entered as: "+quantity);
	}

	public String getReturnQuantityOfProduct(){
		driver.waitForElementPresent(RETURN_QUANTITY_LOC);
		String quantity = driver.findElement(RETURN_QUANTITY_LOC).getText();
		logger.info("Return quantity of product is: "+quantity);
		return quantity;
	}

	public boolean isRestockingFeeTxtPresent(){
		driver.isElementPresent(RESTOCKING_FEE_TEXT_LOC);
		return driver.isElementPresent(RESTOCKING_FEE_TEXT_LOC);
	}

	public void enterRestockingFeeInPercent(String fee){
		driver.waitForElementPresent(RESTOCKING_FEE_PERCENT_TXT_BOX_LOC);
		driver.type(RESTOCKING_FEE_PERCENT_TXT_BOX_LOC, fee);
		logger.info("Restocking fee in % entered as: "+fee);
	}

	public void enterAccountNameAndClickStartOrder(String accountName, String accountNumber) {
		driver.quickWaitForElementPresent(TXT_FIELD_START_ORDER_LOC);
		driver.type(TXT_FIELD_START_ORDER_LOC, accountName);
		logger.info("start order text field entered by: "+accountName);
		driver.waitForElementPresent(By.xpath(String.format(suggestionStartOrderLoc, accountNumber)));
		driver.click(By.xpath(String.format(suggestionStartOrderLoc, accountNumber)),"");
		driver.click(START_ORDER_BTN_LOC,"");
		logger.info("start button is clicked after entered account name");
	}

	public String getCompleteNameFromFirstRow(){
		driver.waitForElementPresent(FIRST_NAME);
		String firstName = driver.findElement(FIRST_NAME).getText();
		logger.info("First name is: "+firstName);
		String lastName = driver.findElement(LAST_NAME).getText();
		String completeName = firstName+" "+lastName;
		logger.info("Last name is: "+lastName);
		logger.info("Complete name is: "+completeName);
		return completeName;
	}


	public boolean isTotalAmountToBeRefundedPresent(){
		driver.quickWaitForElementPresent(TOTAL_AMT_TO_BE_REFUNDED_LOC);
		return driver.isElementPresent(TOTAL_AMT_TO_BE_REFUNDED_LOC);
	}

	public double getRefundedShippingChargesValue(){
		driver.quickWaitForElementPresent(REFUNDED_SHIPPING_LOC);
		//double value=Double.parseDouble(driver.findElement(REFUNDED_SHIPPING_LOC).getAttribute("value"));
		double value=Double.parseDouble(driver.findElement(REFUNDED_SHIPPING_LOC).getText());
		return value;
	}

	public double getRefundedHandlingChargesValue(){
		driver.quickWaitForElementPresent(REFUNDED_HANDLING_LOC);
		//double value=Double.parseDouble(driver.findElement(REFUNDED_HANDLING_LOC).getAttribute("value"));
		double value=Double.parseDouble(driver.findElement(REFUNDED_HANDLING_LOC).getText());
		return value;
	}

	//	public void updateRefundedShippingChargesLowerValue(String value){
	//		driver.waitForElementPresent(REFUNDED_SHIPPING_LOC);
	//		driver.clear(REFUNDED_SHIPPING_LOC);
	//		driver.findElement(REFUNDED_SHIPPING_LOC).sendKeys(value);
	//		driver.pauseExecutionFor(1000);
	//		driver.findElement(REFUNDED_SHIPPING_LOC).sendKeys("\t");
	//		driver.pauseExecutionFor(1000);
	//		logger.info("Refunded_Shipping value updated as"+value);
	//	}

	public void updateRefundedShippingChargesLowerValue(String value){
		driver.waitForElementPresent(REFUNDED_SHIPPING_LOC);
		JavascriptExecutor myExecutor = ((JavascriptExecutor) RFWebsiteDriver.driver);
		while(true){
			try{
				while(driver.findElement(REFUNDED_SHIPPING_LOC).getAttribute("value").length() > 0) {
					driver.findElement(REFUNDED_SHIPPING_LOC).sendKeys(Keys.BACK_SPACE);
				}
				myExecutor.executeScript("arguments[0].value='00';", driver.findElement(REFUNDED_SHIPPING_LOC));
				driver.pauseExecutionFor(1000);
				//driver.findElement(REFUNDED_HANDLING_LOC).sendKeys("\t");
				driver.findElement(By.xpath("//b[contains(text(),'Total Amount To')]")).click();
				driver.pauseExecutionFor(1000);
				logger.info("Refunded Shipping value updated as"+value);
				break;
			}catch(Exception e){
				continue;
			}
		}
	}

	public void updateRefundedHandlingChargesLowerValue(String value){
		driver.waitForElementPresent(REFUNDED_HANDLING_LOC);
		JavascriptExecutor myExecutor = ((JavascriptExecutor) RFWebsiteDriver.driver);
		while(true){
			try{
				while(driver.findElement(REFUNDED_HANDLING_LOC).getAttribute("value").length() > 0) {
					driver.findElement(REFUNDED_HANDLING_LOC).sendKeys(Keys.BACK_SPACE);
				}
				myExecutor.executeScript("arguments[0].value='00';", driver.findElement(REFUNDED_HANDLING_LOC));
				driver.pauseExecutionFor(1000);
				//driver.findElement(REFUNDED_HANDLING_LOC).sendKeys("\t");
				driver.findElement(By.xpath("//b[contains(text(),'Total Amount To')]")).click();
				driver.pauseExecutionFor(1000);
				logger.info("Refunded handling value updated as"+value);
				break;
			}catch(Exception e){
				continue;
			}
		}
	}

	public void updateRefundedShippingChargesValue(String value){
		driver.waitForElementPresent(REFUNDED_SHIPPING_LOC);
		driver.findElement(REFUNDED_SHIPPING_LOC).sendKeys(value);
		driver.pauseExecutionFor(1000);
		driver.findElement(REFUNDED_SHIPPING_LOC).sendKeys("\t");
		driver.pauseExecutionFor(1000);
		logger.info("Refunded_Shipping value updated as"+value);
	}

	public void updateRefundedHandlingChargesValue(String value){
		driver.waitForElementPresent(REFUNDED_HANDLING_LOC);
		driver.findElement(REFUNDED_HANDLING_LOC).sendKeys(value);
		driver.pauseExecutionFor(1000);
		driver.findElement(REFUNDED_HANDLING_LOC).sendKeys("\t");
		driver.pauseExecutionFor(1000);
		logger.info("Refunded_Handling value updated as"+value);  
	}

	public void enterFirstAndLastNameInBrowseOrders(String firstName,String lastName) {
		driver.type(FIRST_NAME_IN_BROWSE_ORDERS_LOC, firstName);
		logger.info("first name entered is:"+firstName);
		driver.type(LAST_NAME_IN_BROWSE_ORDERS_LOC, lastName);
		logger.info("first name entered is:"+lastName);
	}

	public void clickSearchIcon() {
		driver.click(SEARCH_ICON_LOC,"");
		logger.info("clicked on search icon button");
	}

	public boolean isSearchResultPresent(String completeName) {
		return driver.isElementPresent(By.xpath(String.format(completeNameSearchResultLoc, completeName)));
	}

	public void enterCIDInBrowseOrders(String accountNumber) {
		driver.type(CID_IN_BROWSE_ORDER_LOC, accountNumber);
		logger.info("CID entered is: "+accountNumber);
	}

	public boolean isFromDateInBrowseOrdersEditable(String Date) {
		try {
			driver.type(FROM_DATE_IN_BROWSE_ORDER_LOC, Date);
			logger.info("date entered is:"+Date);
		}catch(Exception e) {
			logger.info("Exception Occurred");
			return false;
		}
		return true;
	}

	public String getFromDateInBrowseOrders() {
		return driver.getText(FROM_DATE_IN_BROWSE_ORDER_LOC);
	}

	public String getOrderTotalFromOrderCreationPage() {
		return driver.getText(ORDER_TOTAL_FROM_ORDER_CREATION_PAGE_LOC);
	}

	public boolean isOrderTotalVisibleOnOrderCreationPage() {
		return driver.isElementVisible(ORDER_TOTAL_FROM_ORDER_CREATION_PAGE_LOC)&&driver.getText(ORDER_TOTAL_FROM_ORDER_CREATION_PAGE_LOC).contains("$");
	}

	public String getSubtotalFromOrderCreationPage() {
		return driver.getText(SUBTOTAL_AMOUNT_FROM_ORDER_CREATION_PAGE_LOC);
	}

	public boolean isSubtotalVisibleOnOrderCreationPage() {
		return  driver.isElementVisible(SUBTOTAL_AMOUNT_FROM_ORDER_CREATION_PAGE_LOC)&&driver.getText(SUBTOTAL_AMOUNT_FROM_ORDER_CREATION_PAGE_LOC).contains("$");
	}

	public void clickPerformOverridesButton() {
		driver.clickByJS(RFWebsiteDriver.driver, PERFORM_OVERRIDES_BUTTON_LOC,"");
		logger.info("Clicked on Perform overrides button");
	}

	public void enterShippingInOverrideOrderWindow(String shipping) {
		driver.pauseExecutionFor(2000);
		driver.findElement(OVERRIDE_SHIPPING_LOC).sendKeys(Keys.CONTROL + "a");
		driver.type(OVERRIDE_SHIPPING_LOC, shipping);
		logger.info("shipping entered is:"+shipping);
	}

	public void clickRecalculateTax() {
		driver.pauseExecutionFor(2000);
		driver.clickByJS(RFWebsiteDriver.driver, RECALCULATE_TAX_LOC,"");
		logger.info("clicked on Re-calculate Tax button");
	}

	public String getTaxFromOverrideOrderWindow() {
		return driver.getText(OVERRIDE_TAX_LOC);
	}

	public void clickSaveButtonOnPerformOverrideWindow() {
		driver.pauseExecutionFor(2000);
		driver.clickByJS(RFWebsiteDriver.driver, SAVE_BUTTON_ON_PERFORM_OVERRIDE_LOC,"");
		logger.info("clicked on Save Button on Perform Override order Window");
		driver.waitForNSCore4ProcessImageToDisappear();
		driver.pauseExecutionFor(2000);
	}

	public String getPaymentsAppliedFromOrderDetailsPage() {
		driver.waitForElementPresent(PAYMENTS_APPLIED_ON_ORDER_DETAILS_PAGE_LOC);
		return driver.getText(PAYMENTS_APPLIED_ON_ORDER_DETAILS_PAGE_LOC);
	}

	public void enterTaxInOverrideOrderWindow(String tax) {
		driver.findElement(OVERRIDE_TAX_LOC).sendKeys(Keys.CONTROL + "a");
		driver.type(OVERRIDE_TAX_LOC, tax);
		logger.info("Tax entered is:"+tax);
		driver.pauseExecutionFor(2000);
	}


	public void enterCVInOverrideOrderWindow(String commissionableValue) {
		driver.pauseExecutionFor(2000);
		driver.findElement(COMMISSIONABLE_VALUE_LOC).sendKeys(Keys.CONTROL + "a");
		driver.type(COMMISSIONABLE_VALUE_LOC, commissionableValue);
		logger.info("CV entered is:"+commissionableValue);
	}

	public void enterPriceInOverrideOrderWindow(String price,String SKU) {
		driver.pauseExecutionFor(2000);
		driver.findElement(By.xpath(String.format(skuBasedProductPriceSOOPopupLoc, SKU))).sendKeys(Keys.CONTROL + "a");
		driver.type(By.xpath(String.format(skuBasedProductPriceSOOPopupLoc, SKU)), price);
		logger.info("price entered is:"+price);
	}

	public String getErrorMessageForgreaterProductPrice() {
		driver.waitForElementPresent(PRODUCT_PRICE_ERROR_MESSAGE_LOC);
		return driver.getText(PRODUCT_PRICE_ERROR_MESSAGE_LOC);
	}

	public String getShippingValueFromOverrideOrderWindow() {
		return driver.getText(OVERRIDE_SHIPPING_LOC);

	}

	public void changePriceValueOnOverrideOrderWindow(String priceValue) {
		driver.type(OVERRIDE_PRICE_LOC, priceValue);
		logger.info("override price value chnaged to 0");
	}

	public String updateQVForProduct(String orignalQV,String updateByValue,String operation){
		double updatedQV = 0.0;
		double originalValue = Double.parseDouble(orignalQV);
		double updateByPrice = Double.parseDouble(updateByValue);
		if(operation.equalsIgnoreCase("Add")) {
			updatedQV = originalValue+updateByPrice;
		}else if(operation.equalsIgnoreCase("Subtraction")) {
			updatedQV = originalValue-updateByPrice;
		}
		String finalQV = Double.toString(updatedQV);
		logger.info("Updated price for product is "+finalQV);
		return finalQV;
	}

	public void changePriceCVAndQVValuesToZero(String price, String CV, String QV) {
		driver.type(OVERRIDE_PRICE_LOC, "0");
		logger.info("override price value chnaged to 0");
		driver.type(OVERRIDE_CV_LOC, "0");
		logger.info("override CV value chnaged to 0");
		driver.type(OVERRIDE_QV_LOC, "0");
		logger.info("override QV value chnaged to 0");
	}

	public void changePriceCVAndQVValues(String priceValue) {
		driver.type(OVERRIDE_PRICE_LOC, priceValue);
		logger.info("override price value chnaged to 0");
		driver.type(OVERRIDE_CV_LOC, priceValue);
		logger.info("override CV value chnaged to 0");
		driver.type(OVERRIDE_QV_LOC, priceValue);
		logger.info("override QV value chnaged to 0");
	}

	public void enterQVValueInOverrideOrderWindow(String QV,String SKU) {
		driver.pauseExecutionFor(2000);
		driver.findElement(By.xpath(String.format(skuBasedProductQVSOOPopupLoc, SKU))).sendKeys(Keys.CONTROL + "a");
		driver.type(By.xpath(String.format(skuBasedProductQVSOOPopupLoc, SKU)), QV);
		logger.info("price entered is:"+QV);
	}

	public void updateShippingMethod(String updateValue){
		String selectedShippingMethod = driver.getText(SELECTED_SHIPPING_METHOD_LOC,"selected shipping method");
		if(selectedShippingMethod.contains("FedEx Grnd") && updateValue.contains("Higher")){
			driver.click(FEDEX_2DAY_SHIPPING_METHOD_LOC,"shipping method radio button");
			logger.info("Selected shipping method 'FedEx 2 day'");
		}else if(selectedShippingMethod.contains("FedEx 2 day") && updateValue.contains("Higher")){
			driver.click(FEDEX_1DAY_SHIPPING_METHOD_LOC, "shipping method radio button");
			logger.info("Selected shipping method 'FedEx 1 day'");
		}else if(selectedShippingMethod.contains("FedEx 1 day") && updateValue.contains("Higher")){
			logger.info("Higher Shipping method is already selected.");
		}else if(selectedShippingMethod.contains("FedEx Grnd") && updateValue.contains("Lower")){
			logger.info("Lowest shipping method already selected");
		}else if(selectedShippingMethod.contains("FedEx 2 day") && updateValue.contains("Lower")){
			driver.click(FEDEX_GROUND_SHIPPING_METHOD_LOC,"shipping method radio button");
			logger.info("Selected shipping method 'FedEx Grnd'");
		}else if(selectedShippingMethod.contains("FedEx 1 day") && updateValue.contains("Lower")){
			driver.click(FEDEX_2DAY_SHIPPING_METHOD_LOC,"shipping method radio button");
			logger.info("Selected shipping method 'FedEx 2 day'");
		}else{
			logger.info("None of the matching condition found");
		}
	}
	public String getShippingFromOrderCreationPage() {
		return driver.getText(SHIPPING_AMOUNT_FROM_ORDER_CREATION_PAGE_LOC);
	}

	public void UpdateShippingProfile(String zipCode){
		driver.type(ADD_SHIPPING_ADDRESS_ZIPCODE_LOC, zipCode+"\t");//55044-5014
		driver.waitForNSCore4LoadingImageToDisappear();
		driver.click(STATE_DD_LOC,"");
		driver.click(STATE_DD_OPTION_LOC,"");
		driver.pauseExecutionFor(2000);	
	}

	public void enterShippingAttentionName(String name){
		driver.type(By.id("attention"), name,"attentionName");
	}

	public String getShippingProfileAddress() {
		driver.pauseExecutionFor(2000);
		String shippingAddress = driver.getText(SHIPPING_PROFILE_ADDRESS_LOC);
		logger.info("Shipping profile text "+shippingAddress);
		return shippingAddress;
	}

	public void enterCVValueInOverrideOrderWindow(String CV,String SKU) {
		driver.pauseExecutionFor(2000);
		driver.findElement(By.xpath(String.format(productCVBasedOnSKULoc, SKU))).sendKeys(Keys.CONTROL + "a");
		driver.type(By.xpath(String.format(productCVBasedOnSKULoc, SKU)), CV);
		logger.info("CV price entered is:"+CV);
	}

	//	public void selectItemAndClickUpdateUnderRefundSection(String orderSequesnce) {
	//		driver.click(By.xpath("//table[@id='products']//tr["+orderSequesnce+"]//input[2]"),"");
	//		logger.info("first item from the grid selected");
	//		driver.click(By.id("btnUpdate"),"");
	//		logger.info("update button under refund section clicked");
	//		driver.waitForLoadingImageToDisappear();
	//
	//	}

	public void clickReturnOrderOrderDetailsPage() {
		driver.pauseExecutionFor(5000);
		driver.waitForElementPresent(RETURN_ORDER_LINK_LOC);
		driver.click(RETURN_ORDER_LINK_LOC,"");
		logger.info("return order link clicked");
	}

	public void goToOriginalOrder() {
		driver.click(By.xpath("//h1[contains(text(),'Original Order:')]/following::tr[2]//a"),"");
		logger.info("original order link clicked");
		driver.waitForPageLoad();
	}

	public void clickVTReturnOrder() {
		driver.pauseExecutionFor(5000);
		driver.waitForElementPresent(VT_RETURN_LINK_LOC);
		driver.clickByJS(RFWebsiteDriver.driver,VT_RETURN_LINK_LOC,"");
		logger.info("VT return link clicked");
		driver.waitForPageLoad();
	}

	public String getPriceDetailsByLabel(String labelForPrice) {
		int countOfItemsInPriceDetails = driver.findElements(By.xpath("//th[contains(text(),'Original Totals')]/following::tr[1]/td[1]/br")).size();

		String labelForPriceDetails = driver.findElement(By.xpath("//th[contains(text(),'Original Totals')]/following::tr[1]/td[1]")).getText();
		logger.info("count of Items for PRice Detials: "+countOfItemsInPriceDetails);
		String[] labelDetailsByIndex = new String[countOfItemsInPriceDetails+1]; 
		labelDetailsByIndex=labelForPriceDetails.split("\n");

		String priceForPriceDetails = driver.findElement(By.xpath("//th[contains(text(),'Original Totals')]/following::tr[1]/td[2]")).getText();
		String[] priceDetailsByIndex = new String[countOfItemsInPriceDetails+1];
		priceDetailsByIndex=priceForPriceDetails.split("\n");

		HashMap<String, String> orderPriceDetailsMap = new HashMap<>();
		for(int i = 0;i<=countOfItemsInPriceDetails;i++) {
			orderPriceDetailsMap.put(labelDetailsByIndex[i], priceDetailsByIndex[i].replace("$", ""));
		}
		return orderPriceDetailsMap.get(labelForPrice);
	}

	public void enterRefundedShipping(String price) {
		driver.pauseExecutionFor(3000);
		driver.type(By.id("shippingRefunded"), price);
	}

	public void selectCheckboxForReturnTax() {
		driver.click(By.id("shippingTax"),"");
	}

	public String getorderNumber() {
		String orderNumber=driver.getText(By.xpath("//h1[contains(text(),'Original Order:')]/following::tr[2]//a"));
		logger.info("order number is:"+ orderNumber);
		return orderNumber;
	}

	public String isVTReturnLinksDisabled() {
		return driver.findElement(VT_RETURN_LINK_LOC).getAttribute("href") ;
	}

	public String isReturnLinksDisabled() {
		return driver.findElement(RETURN_ORDER_LOC).getAttribute("href") ;
	}

	public void clearShippingAndHandlingRefundedAmountsAndUpdate() {
		//driver.type(By.id("shippingRefunded"), "0");
		driver.clear(By.id("shippingRefunded"));
		logger.info("shipping refunded amount set to 0");
		driver.waitForLoadingImageToDisappear();
		//driver.type(By.id("handlingRefunded"), "0");
		driver.clear(By.id("handlingRefunded"));
		logger.info("handling refunded amount set to 0");
		driver.pauseExecutionFor(1000);
		driver.click(By.id("btnUpdate"),"");
		logger.info("update button under refund section clicked");

	}

	public void changePriceCVAndQVValuesToZero(String price, String CV, String QV, String SKU) {
		driver.type(By.xpath(String.format(skuBasedProductPricePopupLoc, SKU)), "0");
		logger.info("override price value chnaged to 0");
		driver.type(By.xpath(String.format(skuBasedProductCVPopupLoc, SKU)), "0");
		logger.info("override CV value chnaged to 0");
		driver.type(By.xpath(String.format(skuBasedProductQVPopupLoc, SKU)), "0");
		logger.info("override QV value chnaged to 0");
	}

	public void clickRestockingAmount() {
		driver.click(RESTOCKING_AMOUNT_LOC,"");
		logger.info("clicked on Re-stocking Amount link");
	}

	public String getShippingTotalFromOrderCreationPage() {
		driver.waitForElementToBeVisible(SHIPPING_ON_ORDER_CREATION_PAGE_LOC, 5000);
		return driver.getText(SHIPPING_ON_ORDER_CREATION_PAGE_LOC).split("\\$")[1];
	}

	public String verifyPriceOfProductIsSetToZero(String SKU) {
		return driver.findElement(By.xpath(String.format(skuBasedProductPricePopupLoc, SKU))).getAttribute("value") ;
	}

	public String verifyPriceOfQVIsSetToZero(String SKU) {
		return driver.findElement(By.xpath(String.format(skuBasedProductQVPopupLoc, SKU))).getAttribute("value") ;
	}

	public String verifyPriceOfCVIsSetToZero(String SKU) {
		return driver.findElement(By.xpath(String.format(skuBasedProductCVPopupLoc, SKU))).getAttribute("value") ;
	}

	public String isPriceOfProductIsSetToZeroOrderCreationPage(String SKU) {
		return driver.getText(By.xpath(String.format(skuBasedProductPriceOrderCreationPageLoc, SKU)));
	}

	public String isCVOfProductIsSetToZeroOrderCreationPage(String SKU) {
		return driver.getText(By.xpath(String.format(skuBasedProductCVOrderCreationPageLoc, SKU)));
	}

	public String isQVOfProductIsSetToZeroOrderCreationPage(String SKU) {
		return driver.getText(By.xpath(String.format(skuBasedProductQVOrderCreationPageLoc, SKU)));
	}

	public void enterFromDateAndToDate(String Date,String CurDate) {
		driver.type(FROM_DATE_IN_BROWSE_ORDER_LOC, Date);
		logger.info("date entered is:"+Date);
		driver.type(TO_DATE_IN_BROWSE_ORDER_LOC, CurDate);
		logger.info("date entered is:"+CurDate);

	}

	public String getCustomerNameFromOrderDetailsPage(){
		String name = driver.getText(CUSTOMER_NAME_FROM_ORDER_DETAILS_PAGE_LOC, "Customer Name");
		return name;
	}

	public void updateQuantityOfProductOnBasisOfProductSKU(String SKU, String quantity) {
		driver.type(By.xpath(String.format(productQuantityLoc, SKU)), quantity);
		driver.click(UPDATE_CART_LINK_LOC,"Update Button");
	}

	public String getRefundTotal() {
		String refundTotal=	driver.getText(By.xpath("//td[contains(text(),'Total:')]/following::td[1]")).split("\\$")[1].replace(",", "");
		return refundTotal;
	}


	public String getOriginalTotalOrderAmount() {
		String orderTotal=driver.getText(By.xpath("//td[@class='grandTotal']")).split("\\$")[1].replace(",", "");
		logger.info("order total is:"+ orderTotal);
		return orderTotal;
	}	


	public void clickUpdateLink(){
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(UPDATE_LINK_LOC);
		driver.click(UPDATE_LINK_LOC,"");
		logger.info("Update link clicked");
		driver.waitForNSCore4LoadingImageToDisappear();
		driver.pauseExecutionFor(4000);
	}	

	public void changeDateToNextTwoMonth(String date) {
		driver.click(COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC,"Commision date calendar");
		driver.click(CALENDAR_NEXT_MONTH_BUTTON_LOC,"");
		driver.click(CALENDAR_NEXT_MONTH_BUTTON_LOC,"");
		driver.click(By.xpath(String.format(commisionDateFromCalendarLoc, date)),"commision date");
		logger.info("Commission date changed");
		driver.pauseExecutionFor(2000);
	}

	public String clickAndGetCommissionDateFromOrderDetailsPage() {
		driver.waitForElementPresent(COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC);
		String commissionDate =  driver.getAttribute(COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC,"value");
		logger.info("CommissionDate from order details page "+commissionDate);
		driver.click(COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC,"");
		return commissionDate;
	}

	public String getTaxOrderTotalAndSubtotalAppliedFromOrderDetailsPage() {
		driver.waitForElementPresent(TAX_ORDERTOTAL_SUBTOTAL_FROM_ORDER_DETAILS_PAGE_LOC);
		String tax =  driver.getText(TAX_ORDERTOTAL_SUBTOTAL_FROM_ORDER_DETAILS_PAGE_LOC);
		return tax;
	}
	public String getOrderStatusFromOrderDetailsPage() {
		driver.waitForElementPresent(ORDER_STATUS_FROM_ORDER_DETAILS_PAGE_LOC);
		String orderStatus =  driver.getText(ORDER_STATUS_FROM_ORDER_DETAILS_PAGE_LOC);
		logger.info("order status from order details page "+orderStatus);
		return orderStatus;
	}

	public void clickPaymentLink() {
		driver.click(PAYMENT_LINK_ORDER_DETAILS_PAGE_LOC,"");
		logger.info("Payment Link clicked in payment info.");
		driver.pauseExecutionFor(3000);
	}
	public boolean isPaymentDetailPopupPresent() {
		driver.waitForElementPresent(PAYMENT_DETAIL_POPUP_ON_ORDER_DETAILS_PAGE_LOC);
		return driver.isElementPresent(PAYMENT_DETAIL_POPUP_ON_ORDER_DETAILS_PAGE_LOC);
	}
	public void clickCloseOfPaymentDetailPopup() {
		driver.click(PAYMENT_DETAIL_POPUP_ON_ORDER_DETAILS_PAGE_CLOSE_LOC,"");
		logger.info("Payment detail popup close link clicked");
		driver.pauseExecutionFor(1000);
	}
	public void enableReturnedChkBox(String SKU){
		driver.quickWaitForElementPresent(By.xpath(String.format(returnProductCheckboxBasedOnSKU, SKU)));
		driver.click(By.xpath(String.format(returnProductCheckboxBasedOnSKU, SKU)),"");
		logger.info("Return product CheckBox is clicked for SKU"+SKU);
		driver.pauseExecutionFor(2000);
	}


	public String getReturnQuantityBasedOnSKU(String SKU) {
		driver.waitForElementPresent(By.xpath(String.format(returnQtyBasedOnSKU, SKU)));
		String returnQty =  driver.getText(By.xpath(String.format(returnQtyBasedOnSKU, SKU)));
		logger.info("Return Qty fetched from order details page "+returnQty);
		return returnQty;
	}
	public void enterReturnQuantity(String SKU,String quantity){
		driver.waitForElementPresent(By.xpath(String.format(returnQtyTextboxBasedOnSKU, SKU)));
		driver.type(By.xpath(String.format(returnQtyTextboxBasedOnSKU, SKU)), quantity);
		logger.info("Return quantity entered as: "+quantity);
	}
	public boolean isProductSKUPresentOnOrderDetailPage(String SKU) {
		driver.waitForElementPresent(By.xpath(String.format(returnProductSKUOnOrderDetails, SKU)));
		return driver.isElementPresent(By.xpath(String.format(returnProductSKUOnOrderDetails, SKU)));
	}

	public String getProductSKUFromCart(int rowNumber) {
		return driver.getText(By.xpath(String.format(productSKUFromCartLoc, rowNumber)));
	}

	public int getRowCountFromSearchResults() {
		driver.waitForPageLoad();
		driver.waitForElementPresent(ORDERS_SEARCH_RESULTS);
		return driver.findElements(ORDERS_SEARCH_RESULTS).size();
	}

	public void selectReturnType() {
		driver.waitForElementPresent(By.id("sReturnType"), 10);
		Select select = new Select(driver.findElement(By.id("sReturnType")));
		select.selectByVisibleText("Customer Return");
		logger.info("Return type selected is: 'Customer Return'");
	}

	public void selectReturnReason() {
		Select select = new Select(driver.findElement(By.id("sReturnReason")));
		select.selectByVisibleText("Test");
		logger.info("Return reason selected is: 'Test'");
	}

	public String getTaxFromOrderCreationPage() {
		return driver.getText(TAX_FROM_ORDER_CREATION_PAGE_LOC);
	}

	public boolean isTaxVisibleOnOrderCreationPage() {
		return driver.isElementVisible(TAX_FROM_ORDER_CREATION_PAGE_LOC)&&driver.getText(TAX_FROM_ORDER_CREATION_PAGE_LOC).contains("$");
	}


	public void selectOverrideReasons() {
		Select dept = new Select(driver.findElement(DEPARTMENT_ON_OVERRIDE_REASONS_LOC));
		Select type = new Select(driver.findElement(TYPE_ON_OVERRIDE_REASONS_LOC));
		Select reason = new Select(driver.findElement(REASONS_ON_OVERRIDE_REASONS_LOC));
		dept.selectByIndex(1);
		logger.info("dept select by index 1");
		driver.pauseExecutionFor(2000);
		type.selectByIndex(4);
		logger.info("type select by index 4");
		driver.pauseExecutionFor(2000);
		reason.selectByIndex(1);
		driver.pauseExecutionFor(2000);
		logger.info("type select by index 1");
	}

	public void clickSubmitReturnBtn(){
		driver.waitForElementPresent(SUBMIT_RETURN_BTN_LOC);
		driver.click(SUBMIT_RETURN_BTN_LOC,"");
		logger.info("submit return btn clicked");
		driver.waitForNSCore4ProcessImageToDisappear();
		driver.waitForPageLoad();
	}

}