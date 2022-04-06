package com.rf.pages.website.cscockpit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;

public class CSCockpitCustomerSearchTabPage extends CSCockpitRFWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(CSCockpitCustomerSearchTabPage.class.getName());

	private static String customerTypeDDLoc = "//span[contains(text(),'Customer Type')]/select/option[text()='%s']";
	private static String countryDDLoc = "//span[contains(text(),'Country')]/select/option[text()='%s']";
	private static String accountStatusDDLoc = "//span[contains(text(),'Account Status')]/select/option[text()='%s']";
	private static String customerEmailIdInSearchResultsLoc = "//div[@class='csListboxContainer']/descendant::table[2]/tbody[2]/tr[%s]/td[4]//span";
	private static String emailIDInSearchResultsLoc = "//div[@class='csListboxContainer']/descendant::table[2]/tbody[2]/tr[%s]/td[4]//span";
	private static String customeraccountStatusInSearchResultsLoc = "//div[@class='csListboxContainer']/descendant::table[2]/tbody[2]/tr[%s]/td[6]//span";
	private static String getcustomerTypeInSearchResultLoc = "//div[@class='z-listbox-header']/following::div[@class='z-listbox-body'][1]//tbody[2]/tr[%s]/td[5]//span";
	private static String customerFirstNameInSearchResultsLoc = "//div[@class='csListboxContainer']/descendant::table[2]/tbody[2]/tr[%s]/td[2]//span";
	public static String customerCIDInSearchResultsLoc = "//div[@class='csListboxContainer']/descendant::table[2]//tr[contains(@class,'csSearchResultRow')][%s]//a";
	public static String customerCIDLinkLoc = "//div[@class='csListboxContainer']/descendant::table//a[text()='%s']";  

	private static final By CUSTOMER_NAME_CID_FIELD_CUSTOMER_SEARCH = By.xpath("//span[text()='Customer Name or CID']/following-sibling::input[1]");
	private static final By SEARCH_BTN = By.xpath("//td[text()='SEARCH']");
	private static final By TOTAL_CUSTOMERS_FROM_RESULT_FIRST_PAGE = By.xpath("//div[@class='csListboxContainer']/descendant::table[2]/tbody[2]/tr");
	private static final By ENTER_EMAIL_ID = By.xpath("//span[contains(text(),'Email Address')]/following::input[1]");
	private static final By FIND_CUSTOMER_LBL = By.xpath("//span[text()='Find Customer']");
	private static final By FIND_ORDER = By.xpath("//a[contains(text(),'Find Order')]");
	private static final By CUSTOMER_TYPE_LOC = By.xpath("//span[@class='customerSearchType onethird']");
	private static final By CUSTOMER_COUNTRY_LOC = By.xpath("//span[@class='countryValues onethird']");
	private static final By CUSTOMER_ACCOUNT_STATUS = By.xpath("//span[@class='rfaccountstatus onethird']");
	private static final By CUSOMER_NAME_CID_FIELD_LOC = By.xpath("//span[text()='Customer Name or CID']");
	private static final By POST_CODE_FIELD_LOC = By.xpath("//span[text()='Postcode']");
	private static final By EMAIL_ADD_FIELD_LOC = By.xpath("//span[text()='Email Address']//following::input[1]");
	private static final By COMMIT_TAX_TAB_LOC = By.xpath("//span[text()='Commit Tax']");
	private static final By AUTOSHIP_ID_HAVING_TYPE_AS_CRP_AUTOSHIP = By.xpath("//span[text()='Autoship Templates']/following::div[1]//div/span[text()='crpAutoship']/../../preceding-sibling::td//a");
	private static final By TOTAL_NUMBER_OF_PAGES = By.xpath("//div[@class='csToolbar']/div/table/tbody//td[6]//span");
	private static final By CID_LINK_SECTION_LOCATOR = By.xpath("//div[contains(@class,'listbox-body')]/table/tbody[2]/tr[1]/td[1]//a");
	private static final By SEARCH_AUTOSHIP_BTN = By.xpath("//td[text()='Search Autoship']");
	private static final By CUSTOMER_TYPE_DD_LOC = By.xpath("//span[contains(text(),'Customer Type')]//select");
	private static final By ACCOUNT_STATUS_DD_LOC = By.xpath("//span[contains(text(),'Account Status')]/select");
	private static final By COUNTRY_DD_LOC = By.xpath("//span[contains(text(),'Country')]/select");


	protected RFWebsiteDriver driver;

	public CSCockpitCustomerSearchTabPage(RFWebsiteDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isCustomerSearchPageDisplayed(){
		driver.waitForElementPresent(FIND_CUSTOMER_LBL);
		return driver.IsElementVisible(driver.findElement(FIND_CUSTOMER_LBL));
	}


	public void selectCustomerTypeFromDropDownInCustomerSearchTab(String customerType){
		driver.waitForElementToBeVisible(CUSTOMER_TYPE_DD_LOC, 20);
		driver.click(CUSTOMER_TYPE_DD_LOC, "Customer type DD icon");
		driver.click(By.xpath(String.format(customerTypeDDLoc, customerType.toUpperCase())),"customer type as "+customerType);
	}

	public void selectCountryFromDropDownInCustomerSearchTab(String country){
		driver.waitForElementToBeVisible(COUNTRY_DD_LOC, 20);
		driver.click(COUNTRY_DD_LOC, "Country DD icon");
		driver.click(By.xpath(String.format(countryDDLoc, country)),"country as "+country);
	}

	public void selectAccountStatusFromDropDownInCustomerSearchTab(String accountStatus){
		driver.waitForElementToBeClickable(ACCOUNT_STATUS_DD_LOC, 20);
		driver.click(ACCOUNT_STATUS_DD_LOC, "Account status DD icon");
		driver.click(By.xpath(String.format(accountStatusDDLoc, accountStatus)),"account status as "+accountStatus);
	}
	
	public String getEmailIdOfTheCustomerInCustomerSearchTab(String customerSequenceNumber){
		driver.waitForElementPresent(By.xpath(String.format(customerEmailIdInSearchResultsLoc, customerSequenceNumber)));
		String customerEmailId = driver.findElement(By.xpath(String.format(customerEmailIdInSearchResultsLoc, customerSequenceNumber))).getText();
		logger.info("Selected Cutomer email Id is = "+customerEmailId);
		return customerEmailId;
	}

	public int getTotalResultsInCustomerSearchOnCustomerSearchTab(){
		driver.waitForElementPresent(TOTAL_CUSTOMERS_FROM_RESULT_FIRST_PAGE);
		return driver.findElements(TOTAL_CUSTOMERS_FROM_RESULT_FIRST_PAGE).size();
	}

	public String getCIDNumberInCustomerSearchTab(String customerSequenceNumber){
		driver.waitForElementPresent(By.xpath(String.format(customerCIDInSearchResultsLoc, customerSequenceNumber)));
		return driver.findElement(By.xpath(String.format(customerCIDInSearchResultsLoc, customerSequenceNumber))).getText();
	}

	public void enterEmailIdInSearchFieldInCustomerSearchTab(String emailId){
		driver.type(ENTER_EMAIL_ID, emailId,"email ID");
		driver.waitForCSCockpitLoadingImageToDisappear();
	}

	public String getfirstNameOfTheCustomerInCustomerSearchTab(String customerSequenceNumber){
		driver.waitForElementPresent(By.xpath(String.format(customerFirstNameInSearchResultsLoc, customerSequenceNumber)));
		String firstname = driver.findElement(By.xpath(String.format(customerFirstNameInSearchResultsLoc, customerSequenceNumber))).getText();
		logger.info("Selected Cutomer first Name is = "+firstname);
		return firstname;
	}

	public void clickOnFindOrderInCustomerSearchTab(){
		driver.waitForElementPresent(FIND_ORDER);
		driver.click(FIND_ORDER,"");
		driver.waitForCSCockpitLoadingImageToDisappear();
	}

	public boolean verifyCustomerTypePresenceOnPage() {
		driver.quickWaitForElementPresent(CUSTOMER_TYPE_LOC);
		return driver.isElementPresent(CUSTOMER_TYPE_LOC);
	}

	public boolean verifyCustomerCountryPresenceOnPage(){ 
		driver.quickWaitForElementPresent(CUSTOMER_COUNTRY_LOC);
		return driver.isElementPresent(CUSTOMER_COUNTRY_LOC);
	}
	public boolean verifyAccountStatusPresenceOnPage(){
		driver.quickWaitForElementPresent(CUSTOMER_ACCOUNT_STATUS);
		return driver.isElementPresent(CUSTOMER_ACCOUNT_STATUS);
	}

	public boolean verifyCustomerNameFieldPresenceOnPage() {
		driver.quickWaitForElementPresent(CUSOMER_NAME_CID_FIELD_LOC);
		return driver.isElementPresent(CUSOMER_NAME_CID_FIELD_LOC);
	}
	public boolean verifyPostcodeFieldPresenceOnPage(){
		driver.quickWaitForElementPresent(POST_CODE_FIELD_LOC);
		return driver.isElementPresent(POST_CODE_FIELD_LOC);
	}

	public boolean verifyEmailAddressFieldPresenceOnPage() {
		driver.quickWaitForElementPresent(EMAIL_ADD_FIELD_LOC);
		return driver.isElementPresent(EMAIL_ADD_FIELD_LOC);
	}	

	public void clickCommitTaxTab() {
		driver.waitForElementPresent(COMMIT_TAX_TAB_LOC);
		driver.click(COMMIT_TAX_TAB_LOC,"");
		driver.waitForCSCockpitLoadingImageToDisappear();
	}

	public String getAndClickAutoshipIDHavingTypeAsCRPAutoshipInCustomerTab(){
		driver.waitForElementPresent(AUTOSHIP_ID_HAVING_TYPE_AS_CRP_AUTOSHIP);
		String autoshipID = driver.findElement(AUTOSHIP_ID_HAVING_TYPE_AS_CRP_AUTOSHIP).getText();
		logger.info("Autoship id from CS cockpit UI Is"+autoshipID);
		driver.click(AUTOSHIP_ID_HAVING_TYPE_AS_CRP_AUTOSHIP,"");
		driver.waitForCSCockpitLoadingImageToDisappear();
		return autoshipID;
	}

	public String getTotalNumberOfPages(){
		driver.waitForElementPresent(TOTAL_NUMBER_OF_PAGES);
		return driver.findElement(TOTAL_NUMBER_OF_PAGES).getText().split("\\ ")[1].trim();
	}

	public String[] getEmailIDInCustomerSearchTab(){
		String[] emailId = new String[20];
		for(int i=1;i<=15;i++){
			emailId[i]= driver.findElement(By.xpath(String.format(emailIDInSearchResultsLoc,i))).getText();
		}
		logger.info("1st emailId is "+emailId[1]);
		logger.info("2nd emailId is "+emailId[2]);
		return emailId;
	}

	public void selectCustomerTypeFromDropDownAsSelectInCustomerSearchTab(String customerType){
		driver.waitForElementPresent(By.xpath(String.format(customerTypeDDLoc, customerType)));
		driver.click(By.xpath(String.format(customerTypeDDLoc, customerType)),"");
		driver.waitForCSCockpitLoadingImageToDisappear();
	}

	public void clearEmailAddressFieldInOrderSearchTab(){
		driver.waitForElementPresent(ENTER_EMAIL_ID);
		driver.clear(ENTER_EMAIL_ID);    
	}

	public boolean verifyCIDSectionIsPresentWithClickableLinks(){
		driver.waitForElementPresent(CID_LINK_SECTION_LOCATOR);
		return driver.isElementPresent(CID_LINK_SECTION_LOCATOR);
	}

	public String getAccountStatusOfTheCustomerInCustomerSearchTab(int size){
		String status=null;
		for(int i=1;i<=size;i++){
			driver.waitForElementPresent(By.xpath(String.format(customeraccountStatusInSearchResultsLoc, i)));
			status = driver.findElement(By.xpath(String.format(customeraccountStatusInSearchResultsLoc,i))).getText();
			if(status=="ACTIVE"){
				break;
			}}
		logger.info("Selected Customer status is = "+status);
		return status.trim();
	}

	public String getCIDNumberInCustomerSearchTab(int size){
		String cid=null;
		String status=null;
		for(int i=1;i<=size;i++){
			driver.waitForElementPresent(By.xpath(String.format(customeraccountStatusInSearchResultsLoc, i)));
			status = driver.findElement(By.xpath(String.format(customeraccountStatusInSearchResultsLoc,i))).getText();
			if(status.trim().equals("ACTIVE")){
				driver.waitForElementPresent(By.xpath(String.format(customerCIDInSearchResultsLoc,i)));
				cid= driver.findElement(By.xpath(String.format(customerCIDInSearchResultsLoc, i))).getText();
				break;
			}}
		logger.info("Fetched cid is = "+cid);
		return cid.trim();
	}

	public void clickSearchAutoshipBtn(){
		driver.waitForElementPresent(SEARCH_AUTOSHIP_BTN);
		driver.click(SEARCH_AUTOSHIP_BTN,"");
		logger.info("Search Autoship button clicked");
		driver.waitForCSCockpitLoadingImageToDisappear();
		driver.pauseExecutionFor(2000);
	}

	public String getAccountStatusOfTheCustomerInCustomerSearchTab(String custmerSequenceNumber){
		String status=null;
		driver.waitForElementPresent(By.xpath(String.format(customeraccountStatusInSearchResultsLoc,custmerSequenceNumber)));
		status = driver.findElement(By.xpath(String.format(customeraccountStatusInSearchResultsLoc,custmerSequenceNumber))).getText();
		logger.info("Selected Customer status is = "+status);
		return status.trim();
	}

	public String getCustomerTypeFromSearchResult(String rowNumber){
		driver.waitForElementPresent(By.xpath(String.format(getcustomerTypeInSearchResultLoc, rowNumber)));
		String customerType = driver.findElement(By.xpath(String.format(getcustomerTypeInSearchResultLoc, rowNumber))).getText();
		logger.info("Customer type in search result is :"+customerType);
		return customerType;
	}

	public void enterCIDInSearchFieldInCustomerSearchTab(String cid){
		driver.type(CUSTOMER_NAME_CID_FIELD_CUSTOMER_SEARCH, cid,"Customer ID");
		driver.waitForCSCockpitLoadingImageToDisappear();
	}

	public boolean isSearchResultsDisplayed(){
		driver.waitForElementPresent(By.xpath("//tr[contains(@class,'csSearchResultRow')]"));
		return driver.findElements(By.xpath("//tr[contains(@class,'csSearchResultRow')]")).size()>0;
	}
	
	public void clickSearchBtn(){
		driver.pauseExecutionFor(2000);
		driver.click(SEARCH_BTN,"Search button");
		driver.waitForCSCockpitLoadingImageToDisappear(50);
	}
	
	public void clickCIDNumberInCustomerSearchTab(String number){
		String cid=null;
		if(Integer.parseInt(number)>20){
			cid=number;
		}
		else{
			cid= getCIDNumberInCustomerSearchTab(number);
			driver.pauseExecutionFor(1000);	
		}
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(By.xpath(String.format(customerCIDLinkLoc, cid)), 30);
		driver.click(By.xpath(String.format(customerCIDLinkLoc, cid)),"CID sequence number as "+cid);
		//driver.waitForCSCockpitLoadingImageToDisappear(100);
		while(driver.isCSCockpitLoadingImageDisplay()) {
			driver.waitForCSCockpitLoadingImageToDisappear();
		}
	}
}