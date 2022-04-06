package com.rf.pages.website.nscore;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.rf.core.driver.website.RFWebsiteDriver;

public class NSCore3HomePage extends NSCore3RFWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(NSCore3HomePage.class.getName());

	public NSCore3HomePage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static String selectDDValuesInSearchByDD= "//select[contains(@id,'OrderSearchField')]//option[contains(text(),'%s')]"; 
	private static String showColumnSelectionValue= "//label[text()='%s']/preceding-sibling::input[@checked='checked'][1]";
	private static String showColumnSelectionValueChkBox= "//label[text()='%s']/preceding::input[1]";
	private static String cellValueLoc = "//td[@id='ContentCol']/table[contains(@id,'dgOrders')]//tr[%s]/td[%s]";
	private static String customerColumnHeaderLoc = "//td[@id='ContentCol']/table[contains(@id,'dgOrders')]//tr[1]/td[%s]/a";
	private static String detailsBtn = "//td[@id='ContentCol']/table[contains(@id,'dgOrders')]//tr[%s]//input[contains(@id,'btnDetails')]";
	private static String loginPageTabsLoc  = "//ul[@class='GlobalNav']//span[text()='%s']";
	private static String selectDDValue= "//select[contains(@id,'uxSearchFieldList')]//option[contains(text(),'%s')]";
	private static String customerColumnHeaderOnDistributorPageLoc = "//td[@id='ContentCol']//tr[@class='grid-header']/td[%s]/a";
	private static String firstRowValueAsPerColumnNameOnDistributorPage = "//td[@id='ContentCol']//tr[contains(@class,'GridView')]/td[%s]/a";
	private static String cellValueOnDistributorPage  = "//td[@id='ContentCol']//tr[%s]/td[%s]/a";
	private static String userTypeInConsultantDropdownOnDistributorPage = "//select[contains(@id,'_uxAccountTypes')]/option[text()='%s']";
	private static String FIRST_NAME_DISTRIBUTOR_TAB_ON_BASIS_OF_ACCOUNT_NUMBER_LOC="//a[contains(text(),'%s')]/following::a[contains(@id,'FirstName')]";
	private static String LAST_NAME_DISTRIBUTOR_TAB_ON_BASIS_OF_ACCOUNT_NUMBER_LOC="//a[contains(text(),'%s')]/following::a[contains(@id,'LastName')]";
	private static String ACCOUNT_STATUS_DISTRIBUTOR_TAB_ON_BASIS_OF_ACCOUNT_NUMBER_LOC="//a[contains(text(),'%s')]/following::a[contains(@id,'Active')]";
	private static String treeIconFromAccountNumberLoc="//td[contains(text(),'%s')]/following::img[contains(@title,'View Child Orders')][1]/..";
	private static String allOrderNumberUnderTree="//td[contains(text(),'%s')]/following::tr[1]/descendant::tr";
	private static String orderNumberUnderTree="//td[contains(text(),'%s')]/following::tr[1]/descendant::tr[%s]/td[2]";

	
//	private static String consultantOptionONAutoshipOrderLoc="//label[contains(text(),'%s')]/preceding::input[1]";
//	private static String changeDueDateLOC="//td[contains(text(),'%s')]/following::img[contains(@title,'Change Due Date')]";
//	private static String changeDueDateTextboxLoc="//td[contains(text(),'%s')]/following::input[contains(@id,'uxDueDate')]";
//	private static String updateChangeDueDateLoc="//td[contains(text(),'%s')]/following::img[contains(@title,'Change Due Date')]/following::a[1]";
//	private static String runAutoshipTemplateLoc="//td[contains(text(),'%s')]/following::input[contains(@id,'uxRun')]";
//	private static String succeededTextLoc="//td[contains(text(),'%s')]/following::td[contains(text(),'Succeeded!')]";
	
	private static String changeDueDateLOC="//td[text()='Yes']/preceding::td[contains(text(),'%s')][1]/following::img[contains(@title,'Change Due Date')]";
	private static String changeDueDateTextboxLoc="//td[text()='Yes']/preceding::td[contains(text(),'%s')][1]/following::input[contains(@id,'uxDueDate')]";
	private static String updateChangeDueDateLoc="//td[text()='Yes']/preceding::td[contains(text(),'%s')][1]/following::img[contains(@title,'Change Due Date')]/following::a[1]";
	private static String runAutoshipTemplateLoc="//td[text()='Yes']/preceding::td[contains(text(),'%s')][1]/following::input[contains(@id,'uxRun')]";
	private static String succeededTextLoc="//td[text()='Yes']/preceding::td[contains(text(),'%s')][1]/following::td[contains(text(),'Succeeded!')]";
	private static final By ADMIN_LINK=By.xpath("//a[text()='Admin']");
	private static final By CREATE_NEW_USER_LINK=By.xpath(".//a[contains(@id,'lnkNew')]");
	private static final By NEW_USER_FISRT_NAME_LOC= By.xpath(".//input[contains(@id,'txtFirstName')]");
	private static final By NEW_USER_LAST_NAME_LOC=By.xpath(".//input[contains(@id,'txtLastName')]");
	private static final By NEW_USER_EMAIL_ADDRESS_LOC=By.xpath(".//input[contains(@id,'txtEmail')]");
	private static final By NEW_USER_USER_NAME_LOC=By.xpath(".//input[contains(@id,'txtUsername')]");
	private static final By NEW_USER_PASSWORD_LOC=By.xpath(".//span[contains(text(),'Password')]/following::input[1]");
	private static final By NEW_USER_REENTER_PASSWORD_LOC=By.xpath(".//span[contains(text(),'Re-enter')]/following::input[1]"); 
	private static final By NEW_USER_SAVE_CHANGES_LOC=By.xpath(".//span[contains(text(),'Save Changes')]");
	private static final By RUN_SELECTED_BUTTON_LOC=By.xpath("//a[contains(text(),'Run Selected')]");
	private static final By FAILED_TEMPLATE_MESSAGE_LOC=By.xpath("//span[contains(@class,'errorMsg')]");
	private static final By SEARCH_BY_ON_AUTOSHIP_ORDER_LOC=By.xpath("//*[contains(@id,'uxSearchByList')]");
	private static final By SEARCH_NAME_TEXTBOX_LOC=By.xpath("//*[contains(@id,'uxSearchName')]");
	private static final By BEGIN_SEARCH_LOC=By.xpath("//a[contains(text(),'Begin Search')]");
	private static final By GO_TO_AUTOSHIP_ORDERS_LINK_LOC=By.xpath("//a[contains(text(),'Autoship Orders')]");
	private static final By CITY_STATE_LOC_ON_DISTRIBUTOR_TAB_PAGE=By.xpath("//td[@id='ContentCol']//tr[contains(@class,'GridView')]//a[contains(@id,'CityState')]");
	private static final By ORDERS_LINK = By.xpath("//a[text()='Orders']") ;
	private static final By SEARCH_BY_DD = By.xpath("//select[contains(@id,'OrderSearchField')]") ;
	private static final By SEARCH_LINK = By.xpath("//a[text()='Search']");
	private static final By SEARCH_INPUT_TEXT = By.xpath("//input[contains(@id,'tbSearchVal')]");
	private static final By SHOW_COLUMN_SELECTION = By.xpath("//a[contains(@id,'lnkShowColumns')]");
	private static final By TOTAL_NO_OF_ROWS = By.xpath("//table[contains(@id,'dgOrders')]//tr[contains(@class,'GridView')]");
	private static final By SAVE_CHANGES_LINK = By.xpath("//input[contains(@id,'btnColumns_Save')]");
	private static final By TOTAL_NO_OF_COLUMNS = By.xpath("//tr[@class='grid-header']//a");
	private static final By ORDER_NUMBER = By.xpath("//span[contains(@id,'uxOrderNumber')]");
	private static final By CUSTOMER_TYPE = By.xpath("//select[contains(@id,'uxOrderCustomerDropDown')]//option");
	private static final By SUBTOTAL_LOC = By.xpath("//span[contains(@id,'uxCustomerSubtotal')]");
	private static final By GRAND_TOTAL_LOC = By.xpath("//span[contains(@id,'uxCustomerGrandTotal')]");
	private static final By ACCOUNT_NUMBER_LOC = By.xpath("//span[contains(@id,'uxCustomerAccount')]");
	private static final By SEARCH_BY_NAME_DD = By.xpath("//select[contains(@id,'uxSearchFieldList')]") ;
	private static final By SEARCH_FOR_TXT_BOX = By.xpath("//input[contains(@id,'_uxSearchValue')][@type='text']") ;
	private static final By SEARCH_BTN = By.xpath("//input[contains(@id,'_uxSearch')][@type='button']");
	private static final By TOTAL_NO_OF_ROWS_DISTRIBUTOR_PAGE = By.xpath("//td[@id='ContentCol']//tr[contains(@class,'GridView')]");
	private static final By CONSULTANT_DROPDOWN = By.xpath("//select[contains(@id,'_uxAccountTypes')]");
	private static final By NEXT_PAGE_LINK_ON_DISTRIBUTOR_PAGE = By.xpath("//table[contains(@id,'uxDistributorGrid')]//tr[1]//a[contains(text(),'Next')]");
	private static String consultantOptionONAutoshipOrderLoc="//label[contains(text(),'%s')]/preceding::input[1]"; 
	
	public void clickOrdersLink(){
		driver.click(ORDERS_LINK,"");
		logger.info("Orders link clicked");
		driver.waitForPageLoad();
	}
	
	public void clickDashboardTab(){
		driver.click(By.xpath("//*[text()='Dashboard']"), "DashBoard Tab");
	}

	public void clickSearchByDD(){
		driver.click(SEARCH_BY_DD,"");
		logger.info("Search By dropdown clicked");
		driver.waitForPageLoad();
	}

	public void selectValueFromSearchByDD(String value){
		driver.click(By.xpath(String.format(selectDDValuesInSearchByDD, value)),"");
		logger.info("Dropdown value selected as: "+value);
		driver.waitForPageLoad();
	}

	public void clickSearch(){
		driver.click(SEARCH_LINK,"");
		logger.info("Search button clicked");
		driver.waitForPageLoad();
	}

	public void enterSearchValue(String searchValue){
		driver.type(SEARCH_INPUT_TEXT, searchValue);
		logger.info("search text entered as: "+searchValue);
	}

	public void clickShowColumnSlection(){
		driver.click(SHOW_COLUMN_SELECTION,"");
		logger.info("Show column selection clicked");
		driver.waitForPageLoad();
	}

	public void isSelectionColumnValueChecked(String value){
		driver.waitForElementPresent(By.xpath(String.format(showColumnSelectionValue, value)));
		if(driver.isElementPresent(By.xpath(String.format(showColumnSelectionValue, value)))==true){
			logger.info("Column value i.e.: "+value+" already checked");
		}else{
			driver.click(By.xpath(String.format(showColumnSelectionValueChkBox, value)),"");
			logger.info("Column value i.e.: "+value+" checked");
		}
		driver.waitForPageLoad();
	}

	public void clickSaveChangesLink(){
		driver.click(SAVE_CHANGES_LINK,"");
		logger.info("Save changes link clicked");
		driver.waitForPageLoad();
	}

	public int getRowNumberWithExpectedName(String name, int columnNumber){
		int i=0;
		driver.waitForElementPresent(TOTAL_NO_OF_ROWS, 10);
		int rows = driver.findElements(TOTAL_NO_OF_ROWS).size();
		for(i=2;i<=rows+1;i++){
			String nameFromUI= driver.getText(By.xpath(String.format(cellValueLoc,i,columnNumber)));
			if(nameFromUI.equals(name)){
				logger.info("return row number is: "+(i));
				return i;
			}
		} 
		logger.info("return row number with no match is: "+i);
		return i;
	}

	public boolean isExpectedValuePresentInColumn(String name, int columnNumber){
		int i=0;
		driver.waitForElementPresent(TOTAL_NO_OF_ROWS, 10);
		int rows = driver.findElements(TOTAL_NO_OF_ROWS).size();
		for(i=2;i<=rows+1;i++){
			String nameFromUI= driver.getText(By.xpath(String.format(cellValueLoc,i,columnNumber)));
			if(nameFromUI.equals(name)){
				logger.info(name+" FOUND in search result");
				return true;
			}
		} 
		logger.info(name+" NOT found in search result");
		return false;
	}

	public int getColumnNumberHavingExpectedColumnName(String columnName){
		driver.waitForElementPresent(TOTAL_NO_OF_COLUMNS, 10);
		int noOfColumns = driver.findElements(TOTAL_NO_OF_COLUMNS).size();
		int i =1;
		for(i=1; i<=noOfColumns; i++){
			String nameFromUI= driver.getText(By.xpath(String.format(customerColumnHeaderLoc,i)));
			if(nameFromUI.equals(columnName)){
				logger.info("return column number is: "+(i));
				return i;
			}
		} 
		logger.info("return column number with no match is: "+i);
		return i;
	}	

	public String getCellValue(int rowNumber, int columnNumber){
		String cellValue= driver.getText(By.xpath(String.format(cellValueLoc,rowNumber,columnNumber)));
		logger.info("Cell value is: "+cellValue);
		return cellValue;
	}

	public void clickDetails(int rowNumber){
		driver.click(By.xpath(String.format(detailsBtn,rowNumber)),"");
		logger.info("Details btn clicked at row number: "+rowNumber);
		driver.waitForPageLoad();
	}

	public String getOrderNumberFromOrderDetails(){
		String orderNumber = driver.getText(ORDER_NUMBER);
		logger.info("Order number at order details: "+orderNumber);
		return orderNumber;
	}

	public boolean isColumnNamePresent(String columnName){
		driver.waitForElementPresent(TOTAL_NO_OF_COLUMNS, 10);
		int noOfColumns = driver.findElements(TOTAL_NO_OF_COLUMNS).size();
		int i =1;
		for(i=1; i<=noOfColumns; i++){
			String nameFromUI= driver.getText(By.xpath(String.format(customerColumnHeaderLoc,i)));
			if(nameFromUI.equals(columnName)){
				return true;
			}
		} 
		return false;
	}

	public String getCustomerTypeFromOrderDetails(){
		String customerType = driver.getText(CUSTOMER_TYPE);
		logger.info("Customer type at order details: "+customerType);
		return customerType;
	}

	public String getSubTotalFromOrderDetails(){
		String subTotal = driver.getText(SUBTOTAL_LOC);
		logger.info("Subtotal at order details: "+subTotal);
		return subTotal;
	}

	public String getgrandTotalFromOrderDetails(){
		String grandTotal = driver.getText(GRAND_TOTAL_LOC);
		logger.info("Grand total at order details: "+grandTotal);
		return grandTotal;
	}

	public void clickShowColumnSelection(){
		driver.click(SHOW_COLUMN_SELECTION,"");
		logger.info("Show column selection clicked");
		driver.waitForPageLoad();
	}

	public String getAccountNumberFromOrderDetails() {
		String accountNumber = driver.getText(ACCOUNT_NUMBER_LOC);
		logger.info("Account number at order details: "+accountNumber);
		return accountNumber;
	}

	public void clickTab(String tabName){
		driver.click(By.xpath(String.format(loginPageTabsLoc, tabName)),"");
		logger.info("Tab: "+tabName+" is clicked");
		driver.waitForPageLoad();
	}

	public void selectValueFromNameDDOnDistributorTab(String value){
		driver.click(SEARCH_BY_NAME_DD,"");
		logger.info("Name dropdown is clicked");
		driver.click(By.xpath(String.format(selectDDValue, value)),"");
		logger.info("Dropdown value selected as: "+value);
		driver.waitForPageLoad();
	}

	public void enterSearchForFieldOnDistributorTab(String inputTxt){
		driver.type(SEARCH_FOR_TXT_BOX, inputTxt);
		logger.info("Search for text entered as: "+inputTxt);
		driver.waitForPageLoad();
	}

	public int getColumnNumberHavingExpectedColumnNameOnDistributorPage(String columnName){
		driver.waitForElementPresent(TOTAL_NO_OF_COLUMNS, 10);
		int noOfColumns = driver.findElements(TOTAL_NO_OF_COLUMNS).size();
		int i =1;
		for(i=1; i<=noOfColumns; i++){
			String nameFromUI= driver.getText(By.xpath(String.format(customerColumnHeaderOnDistributorPageLoc,i)));
			if(nameFromUI.equals(columnName)){
				logger.info("return column number is: "+(i));
				return i;
			}
		} 
		logger.info("return column number with no match is: "+i);
		return i;
	}

	public String getFirstRowCellValueAsPerColumnName(int columnNumber){
		String cellValue= driver.getText(By.xpath(String.format(firstRowValueAsPerColumnNameOnDistributorPage,columnNumber)));
		logger.info("Cell value is: "+cellValue);
		return cellValue;
	}

	public boolean isAllRowsContainsTheCompleteName(String completeName){
		int i=0;
		boolean flag =false;
		driver.waitForElementPresent(TOTAL_NO_OF_ROWS_DISTRIBUTOR_PAGE, 10);
		int rows = driver.findElements(TOTAL_NO_OF_ROWS_DISTRIBUTOR_PAGE).size();
		int firstNameColNum = getColumnNumberHavingExpectedColumnNameOnDistributorPage("First Name");
		int lastNameColNum = getColumnNumberHavingExpectedColumnNameOnDistributorPage("Last Name");
		for(i=3;i<rows+3;i++){
			String firstName = driver.getText(By.xpath(String.format(cellValueOnDistributorPage,i,firstNameColNum)));
			String lastName = driver.getText(By.xpath(String.format(cellValueOnDistributorPage,i,lastNameColNum)));
			String completeNameString = firstName+" "+lastName;
			if(completeName.trim().equalsIgnoreCase(completeNameString.trim())){
				flag =true;
			}else{
				logger.info("complete name on UI is different from database expected:"+completeName+" Actual: "+completeNameString);
				flag=false;
				break;
			}
		}
		return flag;
	}

	public boolean isRowsContainsTheCompleteName(String completeName){
		int i=3;
		boolean flag =false;
		int firstNameColNum = getColumnNumberHavingExpectedColumnNameOnDistributorPage("First Name");
		int lastNameColNum = getColumnNumberHavingExpectedColumnNameOnDistributorPage("Last Name");
		String firstName = driver.getText(By.xpath(String.format(cellValueOnDistributorPage,i,firstNameColNum)));
		String lastName = driver.getText(By.xpath(String.format(cellValueOnDistributorPage,i,lastNameColNum)));
		String completeNameString = firstName+" "+lastName;
		if(completeName.trim().equalsIgnoreCase(completeNameString.trim())){
			flag =true;
		}else{
			logger.info("complete name on UI is different from database expected:"+completeName+" Actual: "+completeNameString);
			flag=false;
		}
		return flag;
	}

	public void selectUserTypeFromDDOnDistributorTab(String UserType){
		driver.click(CONSULTANT_DROPDOWN,"");
		logger.info("Consultant dropdown is clicked");
		driver.click(By.xpath(String.format(userTypeInConsultantDropdownOnDistributorPage, UserType)),"");
		logger.info("Dropdown value selected User as: "+UserType);
		driver.waitForPageLoad();
	}

	public boolean isAllRowsContainsTheAccountType(String accountType){
		int i=0;
		boolean flag =false;
		driver.waitForElementPresent(TOTAL_NO_OF_ROWS_DISTRIBUTOR_PAGE, 10);
		int rows = driver.findElements(TOTAL_NO_OF_ROWS_DISTRIBUTOR_PAGE).size();
		int accountTypeColNum = getColumnNumberHavingExpectedColumnNameOnDistributorPage("Account Type");
		for(i=3;i<rows+3;i++){
			String accountTypeValue = driver.getText(By.xpath(String.format(cellValueOnDistributorPage,i,accountTypeColNum)));
			if(accountType.trim().equalsIgnoreCase(accountTypeValue.trim())){
				flag =true;
			}else{
				logger.info("Account type on UI is different from database expected:"+accountType+" Actual: "+accountTypeValue);
				flag=false;
				break;
			}
		}
		return flag;
	}

	public String getCellValueOnDistributorPage(int rowNumber, int columnNumber){
		String cellValue= driver.getText(By.xpath(String.format(cellValueOnDistributorPage,rowNumber,columnNumber)));
		logger.info("Cell value is: "+cellValue);
		return cellValue;
	}

	public boolean isAllRowsContainsTheLocationName(String locationName){
		boolean flag=false;
		String location=null;
		logger.info("location name supplies:"+locationName);
		driver.pauseExecutionFor(10000);
		driver.waitForElementPresent(CITY_STATE_LOC_ON_DISTRIBUTOR_TAB_PAGE);
		List<WebElement> locationNameElements=driver.findElements(CITY_STATE_LOC_ON_DISTRIBUTOR_TAB_PAGE);
		for(WebElement element:locationNameElements) {
			location=element.getText();
			logger.info("Location name from UI is :"+location);
			if(location.contains(locationName)) {
				flag= true;
			}
		}
		return flag;
	}

	public String getFirstNameOnDistributorTabThroughAccountNumber(String accountNumber) {
		return driver.getText(By.xpath(String.format(FIRST_NAME_DISTRIBUTOR_TAB_ON_BASIS_OF_ACCOUNT_NUMBER_LOC, accountNumber)));
	}

	public String getLastNameOnDistributorTabThroughAccountNumber(String accountNumber) {
		return driver.getText(By.xpath(String.format(LAST_NAME_DISTRIBUTOR_TAB_ON_BASIS_OF_ACCOUNT_NUMBER_LOC, accountNumber)));
	}

	public String getAccountStatusOnDistributorTabThroughAccountNumber(String accountNumber) {
		return driver.getText(By.xpath(String.format(ACCOUNT_STATUS_DISTRIBUTOR_TAB_ON_BASIS_OF_ACCOUNT_NUMBER_LOC, accountNumber)));
	}

	public void selectSearchByCategoryOptionOnAutoshipOrdersPage(String category) {
		Select select = new Select(driver.findElement(SEARCH_BY_ON_AUTOSHIP_ORDER_LOC));
		select.selectByVisibleText(category);
		logger.info("Selected category is:"+category);
	}
	public void clickGoToAutoshipOrdersLink() {
		driver.click(GO_TO_AUTOSHIP_ORDERS_LINK_LOC,"");
		logger.info("Clicked on Goto Autoship Orders Link");
	}

	public void selectAutoshipOrderType(String types) {
		driver.click(By.xpath(String.format(consultantOptionONAutoshipOrderLoc, types)),"");
		logger.info("Autoship order type selected is:"+types);
	}

	public void enterSearchNameAndClickBeginSearch(String searchName) {
		driver.type(SEARCH_NAME_TEXTBOX_LOC, searchName);
		logger.info("Entered text is:"+searchName);
		driver.click(BEGIN_SEARCH_LOC,"");
		logger.info("clicked on 'Begin Search'");
	}

	public void changeDueDate(String accountNumber) {
		String todayDate=null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		todayDate=dateFormat.format(date);
		driver.click(By.xpath(String.format(changeDueDateLOC, accountNumber)),"");
		logger.info("clicked on change due date loc");
		driver.type(By.xpath(String.format(changeDueDateTextboxLoc, accountNumber)), todayDate);
		driver.click(By.xpath(String.format(updateChangeDueDateLoc, accountNumber)),"");
		logger.info("clicked on update due date button");
	}

	public void selectRunCheckbox(String accountNumber) {
		driver.navigate().refresh();
		driver.click(By.xpath(String.format(runAutoshipTemplateLoc, accountNumber)),"");
		logger.info("checked Run Checkbox");
	}
	public void runAutoshipOrder() {
		if(driver.isElementVisible(RUN_SELECTED_BUTTON_LOC)) {
			driver.click(RUN_SELECTED_BUTTON_LOC,"");
			logger.info("clicked on 'Run Selected' Button");
		}else {
			driver.navigate().refresh();
			driver.click(RUN_SELECTED_BUTTON_LOC,"");
			logger.info("clicked on 'Run Selected' Button");
		}

	}

	public boolean isAutoshipOrderSucceeded(String accountNumber) {
		boolean flag=false;
		driver.waitForElementToBeVisible(By.xpath(String.format(succeededTextLoc, accountNumber)), 5);
		if(driver.isElementVisible(By.xpath(String.format(succeededTextLoc, accountNumber)))) {
			flag=true;
			logger.info("succeeded message visible");
		}
		return flag;
	}

	public String getFailedTemplateMessage() {
		return driver.getText(FAILED_TEMPLATE_MESSAGE_LOC);
	}

	public void clickAdminLink() {
		driver.click(ADMIN_LINK,"");
		logger.info("Admin link clicked");
		driver.waitForPageLoad();

	}

	public void clickCreateNewUser() {
		driver.click(CREATE_NEW_USER_LINK,"");
		logger.info("Create new user link clicked");
		driver.waitForPageLoad();
	}

	public void enterNewUserDetails(String UserName, String FirstName,String Lastname, String Email, String Password) {
		driver.type(NEW_USER_FISRT_NAME_LOC, FirstName);
		logger.info("First name entered as: "+FirstName);
		driver.type(NEW_USER_LAST_NAME_LOC, Lastname);
		logger.info("Last name entered as: "+Lastname);
		driver.type(NEW_USER_USER_NAME_LOC, UserName);
		logger.info("User name entered as: "+UserName);
		driver.type(NEW_USER_PASSWORD_LOC, Password);
		logger.info("Password entered as: "+Password);
		driver.type(NEW_USER_REENTER_PASSWORD_LOC, Password);
		logger.info("ReEnter Password entered as: "+Password);
		driver.type(NEW_USER_EMAIL_ADDRESS_LOC, Email);
		logger.info("Email Address entered as: "+Email);	

	}

	public void SelectRole() {
		driver.click(By.xpath(".//label[contains(text(),'Standard User')]/preceding::input[1]"),"");
		logger.info("Role selected");
	}


	public void clickSaveChangesLinkOnCreateNewUser() {
		driver.click(NEW_USER_SAVE_CHANGES_LOC,"");
		logger.info("Save Changes link clicked");
		driver.waitForPageLoad();

	}

	public void selectSiteAccess() {
		driver.click(By.xpath(".//label[contains(text(),'Has access to all sites')]/preceding::input[1]"),"");
		logger.info("Site Access selected");
	}

	public void enterLoginMessage() {
		driver.type(By.xpath(".//textarea[contains(@id,'txtLoginMessage')]"), "New User Created");
		logger.info("New User Created");
	}

	public boolean verifyAdminTabIsDisplayed() {
		return driver.isElementVisible(By.xpath(".//h1[contains(text(),'Administration')]"));
	}

	public void searchNewUser(String UserName) {
		driver.type(By.xpath(".//input[contains(@id,'txtSearchVal')]"), UserName);
		driver.click(By.xpath(".//input[contains(@id,'btnSearch')]"),"");
		logger.info("user searched");
	}

	public String verifyUsernameOfNewlyCreatedUser() {
		String username=driver.getText(By.xpath(".//span[contains(@id,'lblUsername')]"));
		logger.info(username);
		return username;
	}

	public String verifyEmailIdOfNewlyCreatedUser() {
		String email=driver.getText(By.xpath(".//span[contains(@id,'lblEmail')]"));
		logger.info(email);
		return email;
	}

	public String getRowNumberHavingTheCompleteName(String completeName){
		driver.pauseExecutionFor(5000);
		int i=0;
		boolean flag =false;
		while(true){
			driver.waitForElementPresent(TOTAL_NO_OF_ROWS_DISTRIBUTOR_PAGE, 10);
			int rows = driver.findElements(TOTAL_NO_OF_ROWS_DISTRIBUTOR_PAGE).size();
			int firstNameColNum = getColumnNumberHavingExpectedColumnNameOnDistributorPage("First Name");
			int lastNameColNum = getColumnNumberHavingExpectedColumnNameOnDistributorPage("Last Name");
			for(i=3;i<rows+3;i++){
				flag= false;
				String firstName = driver.getText(By.xpath(String.format(cellValueOnDistributorPage,i,firstNameColNum)));
				String lastName = driver.getText(By.xpath(String.format(cellValueOnDistributorPage,i,lastNameColNum)));
				String completeNameString = firstName+" "+lastName;
				if(completeName.trim().equalsIgnoreCase(completeNameString.trim())){
					logger.info("complete name from UI and db match for row "+(i-2));
					break;
				}else{
					flag=true;
				}
			}if(flag == false){
				break;
			}else{
				if(driver.isElementPresent(NEXT_PAGE_LINK_ON_DISTRIBUTOR_PAGE)==true){
					driver.click(NEXT_PAGE_LINK_ON_DISTRIBUTOR_PAGE,"");
					driver.waitForNSCore4LoadingImageToDisappear();
					logger.info("Next button clicked");
				}else{
					logger.info("None of rows on all pages contains the complete name string.");
					break;
				}
			}
		}
		logger.info("Row number is: "+(i-2));
		logger.info("return value of i is: "+i);
		return ""+i;
	}


public void clickSearchOnDistributorTab(){
		driver.click(SEARCH_BTN,"");
		logger.info("Search button clicked");
		driver.waitForPageLoad();
		driver.waitForElementPresent(TOTAL_NO_OF_ROWS_DISTRIBUTOR_PAGE);
		driver.pauseExecutionFor(20000);
	}

public int getTotalSearchResultDisplayed(){
	driver.waitForElementPresent(By.xpath("//table[@class='GridViewStyle']//tr[contains(@class,'RowStyle')]"), 10);
	return driver.findElements(By.xpath("//table[@class='GridViewStyle']//tr[contains(@class,'RowStyle')]")).size();
}

public void clickTreeIconOfAccountNumber(String accountNumber){
	driver.click(By.xpath(String.format(treeIconFromAccountNumberLoc, accountNumber)),"Clicked on tree icon");
}

public String getTheLatestOrderNumberUnderTreeIcon(String accountNumber){
	driver.waitForElementToBeVisible(By.xpath(String.format(orderNumberUnderTree, accountNumber,1)), 10);
	int count = driver.findElements(By.xpath(String.format(allOrderNumberUnderTree, accountNumber))).size();
	String orderNumber = driver.getText(By.xpath(String.format(orderNumberUnderTree, accountNumber,count)));
	return orderNumber;
}

}