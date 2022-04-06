package com.rf.pages.website.crm.dsv;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;
import com.rf.pages.website.dsv.DSVStoreFrontShippingInfoPage;

public class DSVCRMHomePage extends DSVCRMRFWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(DSVCRMHomePage.class.getName());
	
	public DSVCRMHomePage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final By ACCOUNT_NAME_LOC=By.xpath("//label[contains(text(),'Account Name')]/following::input[1]");
	private static final By SAVE_ACCOUNT_BUTTON_LOC=By.xpath("//td[@id='topButtonRow']/input[@title='Save']");
	private static final By ACCOUNT_PROFILE_NAME_LOC=By.xpath("//td[contains(text(),'Account Name')]/following-sibling::td[1]");
	private static final By AUTOSHIP_LOC=By.xpath("//span[contains(text(),'Autoships')]");
	private static final By FIRST_AUTOSHIP_ID_LOC=By.xpath("//h3[contains(text(),'Autoships')]/following::tr[2]/th[1]//a");
	private static final By PERFORMANCE_KPI_NAME_LOC=By.xpath("//h3[contains(text(),'Performance KPIs')]/following::th[text()='Period']/following::tr[1]/th/a");
	private static final By PERFORMANCE_KPI_INFORMATION_LOC=By.xpath("//h3[text()='Performance KPI Information']");
	private static final By PERFORMANCE_KPI_EDIT_BUTTON_LOC=By.xpath("//h3[contains(text(),'Performance KPIs')]/following::div[1]//tr[2]/td/a[text()='Edit']");
	private static final By NUMBER_OF_BILLING_PROFILES_LOC=By.xpath("//h3[contains(text(),'Billing')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]");
	private static final By DEFAULT_BILLING_PROFILES_LOC=By.xpath("//h3[contains(text(),'Billing')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]//img[@title='Checked']");
	private static final By CREDIT_CARD_INFO_LOC=By.xpath("//h3[contains(text(),'Billing Profile Detail')]/following::table[@class='detailList'][1]//td[text()='CC#(Last 4 Digits)']/following::td[1]");
	private static final By EXPIRY_YEAR_DETAILS_LOC=By.xpath("//h3[contains(text(),'Billing Profile Detail')]/following::table[@class='detailList'][1]//td[text()='Valid Thru']/following::td[1]");
	private static final By SHIPPING_PROFILES_LOC=By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]");
	private static final By DEFAULT_SHIPPING_PROFILE_LOC=By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]//img[@title='Checked']");
	private static final By ADD_NEW_SHIPPING_PROFILE_LINK_LOC=By.xpath("//input[@value='New Shipping Profile']");
	private static final By SHIPPING_PROFILE_NAME=By.xpath("//label[contains(text(),'Profile')]/following::input[1]");
	private static final By SAVE_BUTTON_FOR_SHIPPING_ADDRESS_LOC=By.xpath("//div[@class='pbHeader']//input[@title='Save'] | //div[@class='pbHeader']//input[@value='Save']");
	private static final By ADDRESS_LINE1_LOC=By.xpath("//label[contains(text(),'Shipping Address')]/following::textarea[1]"); 
	private static final By COUNTRY_LOC=By.xpath("//span[@class='lookupInput']/input");
	private static final By CITY_LOC=By.xpath("//label[contains(text(),'Locale')]/following::input[1]");
	private static final By STATE_LOC=By.xpath(".//label[contains(text(),'Region')]/following::input[1]");
	private static final By POSTAL_CODE_LOC=By.xpath("//label[contains(text(),'Postal')]/following::input[1]");
	private static final By PHONE_NUMBER_LOC=By.xpath("//label[contains(text(),'Phone')]/following::input[1]");
	private static final By EDIT_SHIPPING_PROFILE_LOC=By.xpath("//h2[contains(text(),'Shipping Profile Detail')]/following::input[contains(@value,'Edit')][1]");
	private static final By DELETE_SHIPPING_PROFILE_LOC=By.xpath("//h2[contains(text(),'Shipping Profile Detail')]/following::input[contains(@value,'Delete')][1]");
	private static final By WELCOME_DROP_DOWN = By.xpath("//div[contains(@id,'account-info')]/a/span[3]");
	private static final By SHIPPING_INFO_LINK_WELCOME_DROP_DOWN = By.xpath("//div[contains(@id,'account-info')]//a[text()='Shipping Info']");
	private static final By ACCOUNT_INFO_FIRST_NAME_lOC=By.id("first-name");
	private static final By ACCOUNT_INFO_LAST_NAME_lOC=By.id("last-name");
	
	
	public void clickAccountDetailsButton(String buttonName){
		driver.waitForPageLoad();
		switchToExtFrame(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='AccountButtons']")));  
		driver.click(By.xpath("//input[@value='"+buttonName+"']"),"");
		try{
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logger.info("Ok button of java Script popup is clicked.");
		}catch(Exception e){
			logger.info("No Alert.");
		}
		driver.waitForLoadingImageToDisappear();
	}

	public void updateAccountNameField(String AccountName){
		switchToExtFrame(2);
		driver.type(ACCOUNT_NAME_LOC, AccountName);
	}
	
	public void clickSaveBtnUnderAccountDetail(){
		switchToExtFrame(2);
		driver.click(SAVE_ACCOUNT_BUTTON_LOC,"Save Account button");
		driver.waitForPageLoad();
	}
	
	public String getAccountName(){
		switchToExtFrame(2);
		String accountName = driver.findElement(ACCOUNT_PROFILE_NAME_LOC).getText();
		String accountNameee[] = accountName.split("\\[");
		return accountNameee[0].trim();
	}
	
	public boolean isMouseHoverAutoshipSectionPresentOfFields(String field){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		switchToExtFrame(2);
		actions.moveToElement(driver.findElement(AUTOSHIP_LOC)).build().perform();
		return driver.isElementPresent(By.xpath("//h3[text()='Autoships']/following::th[text()='"+field+"']"));
	}
	
	public String getCountAutoships(){
		switchToExtFrame(2);
		driver.waitForElementPresent(AUTOSHIP_LOC);
		String countAutoshipText = driver.findElement(By.xpath("//span[contains(text(),'Autoships')]/span")).getText();
		String countAutoship = countAutoshipText.substring(1, countAutoshipText.length()-1);
		logger.info("AutoShips count from UI "+countAutoship);
		return countAutoship;
	}
	
	public boolean isLabelUnderAutoshipNumberPresent(String label){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[text()='Autoship Template Details']/following::td[text()='"+label+"'][1]"));
	}
	
	public boolean isLabelUnderPendingAutoshipBreakdownPresent(String label){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[text()='Pending Autoship Breakdown']/following::td[text()='"+label+"'][1]"));
	}
	
	public void clickAutoships(){
		switchToExtFrame(2);
		driver.waitForElementPresent(AUTOSHIP_LOC);
		driver.findElement(AUTOSHIP_LOC).click();
		driver.waitForCRMLoadingImageToDisappear();
	}
	
	public String getCountAutoshipNumber(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Autoships')]/following::div[@class='pbBody'][1]//tr[contains(@class,'dataRow')]"));
		List<WebElement> autoshipElements = driver.findElements(By.xpath("//h3[contains(text(),'Autoships')]/following::div[@class='pbBody'][1]//tr[contains(@class,'dataRow')]"));
		int countAutoship = autoshipElements.size();
		String countAutoshipNumber = Integer.toString(countAutoship);
		logger.info("AutoShips count from Account Details Page "+countAutoship);
		return countAutoshipNumber;
	}
	
	
	public void clickFirstAutoshipID(){
		switchToExtFrame(2);
		driver.waitForElementPresent(FIRST_AUTOSHIP_ID_LOC);
		driver.click(FIRST_AUTOSHIP_ID_LOC,"First Autoship ID");
		driver.waitForPageLoad();
	}
	
	
	public boolean isLabelOnPerformanceKPIsSectionPresent(String label){
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Performance KPIs')]/following::table[@class='list'][1]//th[text()='"+label+"']"));
	}
	
	public void clickPerformanceKPIsName(){
		switchToExtFrame(2);
		driver.click(PERFORMANCE_KPI_NAME_LOC,"Performance KPI's Name");
	}
	
	public boolean isPerformanceKPIsDetailsPresent() {
		switchToExtFrame(3);
		return driver.isElementPresent(PERFORMANCE_KPI_INFORMATION_LOC);
	}	
	
	public boolean isLabelPresentUnderPerformanceKPIsInformation(String label) {
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Performance KPI Information')]/following::table[@class='detailList'][1]//td[text()='"+label+"']"));
	}
	
	public boolean isLabelPresentUnderPerformanceKPIsDetails(String label) {
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Performance KPI Details')]/following::table//tr//td[text()='"+label+"']"));
	}
	
	public boolean verifyActionItemsOnlyViewable() {
		return driver.isElementPresent(PERFORMANCE_KPI_EDIT_BUTTON_LOC);
	}
	
	public boolean isLabelOnBillingAddressSectionPresent(String label){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Billing')]/following::table[@class='list'][1]//th[text()='"+label+"']"));
	}
	
	public String getBillingProfilesCount(){
		switchToExtFrame(2);
		return String.valueOf(driver.findElements(NUMBER_OF_BILLING_PROFILES_LOC).size());		
	}
	
	public String getCountDisplayedWithLink(String link){
		String count=null;
		switchToExtFrame(2);	
		String completeCountString = driver.findElement(By.xpath("//span[@class='listTitle'][contains(text(),'"+link+"')]/span[@class='count']")).getText();
		count= completeCountString.substring(1, completeCountString.length()-1);	
		if(count.contains("+")) {
			count.replace("+", "");
		}
		return count;
	}

	public boolean isLabelOnBillingAddressSectionOnNewTabPresent(String label){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Billing Address')]/following::table[@class='detailList'][1]//td[text()='"+label+"']"));
	}
	
	public boolean isLabelOnBillingProfileDetailSectionOnNewTabPresent(String label){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Billing Profile Detail')]/following::table[@class='detailList'][1]//td[text()='"+label+"']"));
	}
	
	public boolean isCreditCardOnBillingProfileDetailSectionOnNewTabIsSixteenEncryptedDigit(){
		switchToExtFrame(3);
		String creditCardNumber = driver.findElement(CREDIT_CARD_INFO_LOC).getText();
		if(creditCardNumber.contains("************")&&creditCardNumber.length()==16){
			return true;
		}
		return false;
	}
	
	public boolean isExpiryYearOnBillingProfileDetailSectionOnNewTabIsInYYYYFormat(){
		switchToExtFrame(3);
		String expiryYear = driver.findElement(EXPIRY_YEAR_DETAILS_LOC).getText();
		if(expiryYear.split("/")[1].length()==4){
			return true;
		}
		return false;
	}
	
	public void clickRandomBillingProfile(){
		int totalBillingProfile = Integer.parseInt(getBillingProfilesCount());
		int randomBillingProfileNumber = CommonUtils.getRandomNum(1, totalBillingProfile);
		switchToExtFrame(2);
		driver.click(By.xpath("//h3[contains(text(),'Billing')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]["+randomBillingProfileNumber+"]//a"),"Random Billing profile number:"+randomBillingProfileNumber);
		driver.switchTo().defaultContent();
		driver.waitForCSCockpitLoadingImageToDisappear();
	}
	
	public boolean isLabelOnShippingAddressSectionPresent(String label){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//th[text()='"+label+"']"));
	}

	public String getShippingProfilesCount(){
		switchToExtFrame(2);
		return String.valueOf(driver.findElements(SHIPPING_PROFILES_LOC).size());		
	}
	private static final By SHIPPING_PROFILE_SHOW_MORE_LOC=By.xpath("//div[contains(@class,'pShowMore')]//a[1]");
	public boolean isOnlyOneShippingProfileIsDefault(){
		if(driver.isElementPresent(SHIPPING_PROFILE_SHOW_MORE_LOC)) {
			driver.click(SHIPPING_PROFILE_SHOW_MORE_LOC, "Show More");	
		}
		int defaultShippingProfilesCount = driver.findElements(DEFAULT_SHIPPING_PROFILE_LOC).size();
		if(defaultShippingProfilesCount==1){
			return true;
		}
		return false;
	}	
	
	
	public void clickAddNewShippingProfileBtn(){
		switchToExtFrame(2);
		driver.click(ADD_NEW_SHIPPING_PROFILE_LINK_LOC,"Add New Shipping Profile");
		driver.waitForPageLoad();
	}	
	
	public void updateShippingProfileName(String profileName){
		switchToExtFrame(3);
		driver.findElement(SHIPPING_PROFILE_NAME).sendKeys(profileName);
		logger.info("profile name entered is:"+profileName);
	}
	
	public void enterShippingAddress(String addressLine1, String city, String state, String postalCode, String phoneNumber, String country){
		switchToExtFrame(3);
		driver.type(ADDRESS_LINE1_LOC, addressLine1);
		driver.type(COUNTRY_LOC, country);
		driver.type(CITY_LOC, city);
		driver.type(STATE_LOC, state);
		driver.type(POSTAL_CODE_LOC, postalCode);
		driver.type(PHONE_NUMBER_LOC, phoneNumber);
	}
	
	public void clickSaveBtnAfterEditShippingAddress(){
		switchToExtFrame(3);
		driver.click(SAVE_BUTTON_FOR_SHIPPING_ADDRESS_LOC,"Save Button");
		driver.waitForPageLoad();
		driver.waitForCRMLoadingImageToDisappear();
}

	public boolean isProfileNameValueOfDefaultShippingProfilesPresent(String profileName){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping Address')]/following::td[text()='"+profileName+"']"));
	}
	
	public boolean isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(String addressType, String addressLocaleRegionPostalCode){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//div[@class='pbSubsection']//td[text()='"+addressType+"']/following::td[text()='"+addressLocaleRegionPostalCode+"']"));
	}	
	
	public void clickEditFirstShippingProfile(){
		switchToExtFrame(3);
		driver.click(EDIT_SHIPPING_PROFILE_LOC,"EDIT Shipping Profile");
		driver.waitForCRMLoadingImageToDisappear();
	}
	
	public void deleteShippingProfile() {
		switchToExtFrame(3);
		driver.click(DELETE_SHIPPING_PROFILE_LOC, "Delete Shipping profile");
		driver.waitForCRMLoadingImageToDisappear();
	}
	
	public void clickOKOnDeleteDefaultShippingProfilePopUp(){
		driver.pauseExecutionFor(1000);
		try{
		Alert alt=driver.switchTo().alert();
		alt.accept();
		}catch (Exception e) {
			
		}
		driver.waitForPageLoad();
	}
	
	public void clickMyAccountButton(String buttonName){
		driver.waitForPageLoad();
		switchToExtFrame(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='AccountButtons']")));  
		driver.click(By.xpath("//input[@value='"+buttonName+"']"),buttonName);
	}
	
	
	public void clickWelcomeDropDown(){
		driver.waitForLoadingImageToDisappear();
		driver.quickWaitForElementPresent(WELCOME_DROP_DOWN);
		driver.click(WELCOME_DROP_DOWN,"Welcome DD");
		driver.waitForPageLoad();
	}
	
	public void clickShippingInfoLinkFromWelcomeDropDown(){
		driver.quickWaitForElementPresent(SHIPPING_INFO_LINK_WELCOME_DROP_DOWN);
		driver.click(SHIPPING_INFO_LINK_WELCOME_DROP_DOWN,"Shipping Info Link");
	}
	
	public boolean isShippingProfilePresentonPage(String shippingFirstName, String shippingLastName){
		driver.quickWaitForElementPresent(By.xpath(".//*[@id='multiple-billing-profiles']//descendant::span[starts-with(text(),'"+shippingFirstName+"') and contains(text(),'"+shippingLastName+"')]"));
		return driver.isElementPresent(By.xpath(".//*[@id='multiple-billing-profiles']//descendant::span[starts-with(text(),'"+shippingFirstName+"') and contains(text(),'"+shippingLastName+"')]"));		
	}
	
	public boolean isNonDefaultShippingProfileDeleted(String profileName){
		boolean isShippingProfileDeleted = true;
		switchToExtFrame(2);
		List<WebElement> allProfiles= driver.findElements(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[1]/descendant::td[contains(@class,'cellCol2')]"));
		for(WebElement e: allProfiles){
			if(e.getText().toLowerCase().contains(profileName.toLowerCase())){
				isShippingProfileDeleted=false; 
			}
		}
		return isShippingProfileDeleted;
	}
	
	public String getAccountFirstNameFromSF() {
		return driver.getAttribute(ACCOUNT_INFO_FIRST_NAME_lOC, "value");
	}
	
	public String getAccountLastNameFromSF() {
		return driver.getAttribute(ACCOUNT_INFO_LAST_NAME_lOC, "value");
	}
	private static final By DISMISS_PULSE_LOC=By.xpath("//span[contains(text(),'Dismiss')]");
	private static final By USER_NAME_IN_PULSE_HOMEPAGE_LOC=By.xpath("//div[contains(@class,'UserInfo')]/descendant::span[1]");
	private static final By NEW_PULSE_HEADER_LOC=By.xpath("//div[contains(text(),'Hello,')]");
	public void clickDismissPulseButton() {
		driver.click(DISMISS_PULSE_LOC, "Dismiss pulse button");
	}
	public String getUserNameFromPulse() {
		return driver.getText(USER_NAME_IN_PULSE_HOMEPAGE_LOC);
	}
	
	public boolean isUserSuccessfullyLoggedInToNewPulse() {
		driver.waitForPageLoad();
		driver.waitForElementPresent(NEW_PULSE_HEADER_LOC, 10);
		//driver.quickWaitForElementPresent(NEW_PULSE_HEADER_LOC);
		return driver.isElementPresent(NEW_PULSE_HEADER_LOC);
	}
	
	public boolean isOnlyOneBillingProfileIsDefault(){
		driver.quickWaitForElementPresent(DEFAULT_BILLING_PROFILES_LOC,20);
		int defaultBillingProfilesCount = driver.findElements(DEFAULT_BILLING_PROFILES_LOC).size();
		if(defaultBillingProfilesCount==1){
			return true;
		}
		return false;
	}	
	
}
