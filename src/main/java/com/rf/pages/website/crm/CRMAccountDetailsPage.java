package com.rf.pages.website.crm;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;

public class CRMAccountDetailsPage extends CRMRFWebsiteBasePage {
	private static final Logger logger = LogManager
			.getLogger(CRMAccountDetailsPage.class.getName());

	private static String userEnteredAddress = "//label[contains(text(),'%s')]/../input";
	private static String addressFieldLoc = "//label[contains(text(),'%s')]/following::input[1]";

	public CRMAccountDetailsPage(RFWebsiteDriver driver) {
		super(driver);
	}

	public boolean isAccountDetailsPagePresent(){
		switchToExtFrame(2);
		driver.waitForElementPresent(By.xpath("//h2[contains(@class,'mainTitle')]"));
		return driver.findElement(By.xpath("//h2[contains(@class,'mainTitle')]")).getText().contains("Account Detail");		
	}
	
	public boolean isAccountTypeFieldDisplayedAndNonEmpty(){
		switchToExtFrame(2);
		return !(driver.findElement(By.xpath("//td[text()='Account Type']/following::td[1]")).getText().isEmpty());
	}

	public boolean isAccountNumberFieldDisplayedAndNonEmpty(){
		switchToExtFrame(2);
		return !(driver.findElement(By.xpath("//span[text()='CID']/following::td[1]")).getText().isEmpty());
	}
	
	public String getAccountNumberFromAccountDetailsPage(){
		switchToExtFrame(2);
		return driver.findElement(By.xpath("//span[text()='CID']/following::td[1]")).getText();
	}

	public boolean isAccountStatusFieldDisplayedAndNonEmpty(){
		switchToExtFrame(2);
		return !(driver.findElement(By.xpath("//td[text()='Account Status']/following::td[1]")).getText().isEmpty());
	}

	public boolean isCountryFieldDisplayedAndNonEmpty(){
		switchToExtFrame(2);
		return !(driver.findElement(By.xpath("//td[text()='Country']/following::td[1]")).getText().isEmpty());
	}

	public boolean isPlacementSponsorFieldDisplayed(){
		switchToExtFrame(2);
		return !(driver.findElement(By.xpath("//div[text()='Placement Sponsor']")).getText().isEmpty());
	}

	public boolean isEnrollmentDateFieldDisplayedAndNonEmpty(){
		switchToExtFrame(2);
		return !(driver.findElement(By.xpath("//td[text()='Enrollment Date']/following::td[1]")).getText().isEmpty());
	}

	public boolean isMainPhoneFieldDisplayedAndNonEmpty(){
		switchToExtFrame(2);
		return !(driver.findElement(By.xpath("//td[text()='Main Phone']/following::td[1]")).getText().isEmpty());
	}

	public boolean isEmailAddressFieldDisplayedAndNonEmpty(){
		switchToExtFrame(2);
		return !(driver.findElement(By.xpath("//td[text()='Email Address']/following::td[1]")).getText().isEmpty());
	}
	
	public boolean isAccountDetailsSectionPresent(){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//h2[@class='mainTitle']/following::div[@class='pbBody'][1]"));
	}

	public boolean isMainAddressSectionPresent(){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Main Address')]/following::table[@class='detailList'][1]"));
	}

	public boolean isTaxInformationSectionPresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		driver.click(By.xpath("//h3[contains(text(),'Tax Information')]/preceding::img[1]"),"");
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Tax Information')]/following::table[@class='detailList']/ancestor::div[1][@style='display: block;']"));
	}

	public boolean isAccountDetailsButtonEnabled(String buttonName){
		switchToExtFrame(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='AccountButtons']")));		
		return driver.findElement(By.xpath("//input[@value='"+buttonName+"']")).isEnabled();
	}

	public void clickAccountDetailsButton(String buttonName){
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
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


	public boolean isSVSectionPresentOnPulsePage(){
		try{
			driver.waitForElementPresent(By.xpath("//span[contains(text(),'Dismiss')]"));
			driver.click(By.xpath("//span[contains(text(),'Dismiss')]"),"");
			System.out.println("Dismiss button clicked");
		}catch(Exception e){
			System.out.println("no dismiss button");
		}
		try{
			driver.waitForElementPresent(By.xpath("//span[@class='Number']"));
			return driver.isElementPresent(By.xpath("//span[@class='Number']"));
		}
		finally{			
			switchToPreviousTab();
		}
	}

	public boolean isLabelOnAccountDetailsSectionPresent(String label){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//td[text()='"+label+"']"));
	}

	public boolean isLabelOnMainAddressSectionPresent(String label){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Main Address')]/following::table[@class='detailList'][1]//td[text()='"+label+"']"));
	}

	public boolean isLabelOnShippingAddressSectionPresent(String label){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//th[text()='"+label+"']"));
	}

	public boolean isLabelOnBillingAddressSectionPresent(String label){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Billing')]/following::table[@class='list'][1]//th[text()='"+label+"']"));
	}

	public void clickRandomBillingProfile(){
		int totalBillingProfile = Integer.parseInt(getBillingProfilesCount());
		int randomBillingProfileNumber = CommonUtils.getRandomNum(1, totalBillingProfile);
		switchToExtFrame(2);
		driver.click(By.xpath("//h3[contains(text(),'Billing')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]["+randomBillingProfileNumber+"]//a"),"");
		driver.switchTo().defaultContent();
		driver.waitForCSCockpitLoadingImageToDisappear();
	}

	public boolean isLabelOnBillingProfileDetailSectionOnNewTabPresent(String label){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Billing Profile Detail')]/following::table[@class='detailList'][1]//td[text()='"+label+"']"));
	}

	public boolean isLabelOnBillingAddressSectionOnNewTabPresent(String label){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Billing Address')]/following::table[@class='detailList'][1]//td[text()='"+label+"']"));
	}

	public boolean isCreditCardOnBillingProfileDetailSectionOnNewTabIsSixteenEncryptedDigit(){
		switchToExtFrame(3);
		String creditCardNumber = driver.findElement(By.xpath("//h3[contains(text(),'Billing Profile Detail')]/following::table[@class='detailList'][1]//td[text()='CC#(Last 4 Digits)']/following::td[1]")).getText();
		if(creditCardNumber.contains("************")&&creditCardNumber.length()==16){
			return true;
		}
		return false;
	}

	public boolean isExpiryYearOnBillingProfileDetailSectionOnNewTabIsInYYYYFormat(){
		switchToExtFrame(3);
		String expiryYear = driver.findElement(By.xpath("//h3[contains(text(),'Billing Profile Detail')]/following::table[@class='detailList'][1]//td[text()='Valid Thru']/following::td[1]")).getText();
		if(expiryYear.split("/")[1].length()==4){
			return true;
		}
		return false;
	}

	public String getShippingProfilesCount(){
		switchToExtFrame(2);
		return String.valueOf(driver.findElements(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]")).size());		
	}

	public String getBillingProfilesCount(){
		switchToExtFrame(2);
		return String.valueOf(driver.findElements(By.xpath("//h3[contains(text(),'Billing')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]")).size());		
	}

	public String getCountDisplayedWithLink(String link){
		switchToExtFrame(2);	
		String completeCountString = driver.findElement(By.xpath("//span[@class='listTitle'][contains(text(),'"+link+"')]/span[@class='count']")).getText();
		return completeCountString.substring(1, completeCountString.length()-1);		
	}

	public boolean isOnlyOneShippingProfileIsDefault(){
		int defaultShippingProfilesCount = driver.findElements(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]//img[@title='Checked']")).size();
		if(defaultShippingProfilesCount==1){
			return true;
		}
		return false;
	}

	public boolean isOnlyOneBillingProfileIsDefault(){
		int defaultBillingProfilesCount = driver.findElements(By.xpath("//h3[contains(text(),'Billing')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]//img[@title='Checked']")).size();
		if(defaultBillingProfilesCount==1){
			return true;
		}
		return false;
	}

	public boolean isLabelUnderTaxInformationSectionPresent(String label){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Tax Information')]/following::table[@class='detailList']//td[text()='"+label+"']"));
	}

	public boolean isLinkOnAccountSectionPresent(String link){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//span[@class='listTitle'][contains(text(),'"+link+"')]"));
	}


	public boolean isMouseHoverSectionPresentOfLink(String link){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		switchToExtFrame(2);
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='listTitle'][contains(text(),'"+link+"')]"))).build().perform();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='RLPanelFrame']")));		
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'"+link+"')]"));
	}

	public boolean isListItemsWithBlueLinePresent(String item){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//div[contains(@class,'bRelatedList')]//h3[contains(text(),'"+item+"')]"));
	}

	public boolean isLogAccountActivitySectionIsPresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//div[@id='accountNoteId:noteFrm:pnlPage']/div[@class='header']")).getText().contains("Log Account Notes");  
	}

	public boolean isAccountDropdownOnAccountDetailPagePresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Account')]/following::span[1]/input"));  
	}

	public boolean isAccountDropdownSearchOnAccountDetailPagePresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Account')]/following::span[1]/a"));  
	}

	public boolean isChannelDropdownOnAccountDetailPagePresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Channel')]/following::select[1]"));  
	}

	public boolean isChannelDropdownOptionsPresent(String option){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Channel')]/following::select[1]/option[@value='"+option+"']"));
	}

	public boolean isValueCallDisplayedOnChannelOptionsPresent(String label){
		boolean flag = false;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		WebElement mySelectElm = driver.findElement(By.xpath("//div[@id='mainDiv']//div[contains(text(),'"+label+"')]/following::div[1]/select"));
		Select mySelect= new Select(mySelectElm);
		WebElement option = mySelect.getFirstSelectedOption();
		String dropdownSelectedValue = option.getText();
		if(dropdownSelectedValue.equals("--None--")){
			flag = true;
		}
		return flag;
		//return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Channel')]/following::div/span[text()='Call']"));
	}

	public boolean isReasonDropdownOnAccountDetailPagePresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Reason')]/following::select[1]"));  
	}

	public boolean isReasonDropdownOptionsPresent(String option){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Reason')]/following::select[1]/option[@value='"+option+"']"));
	}

	public boolean isDetailsDropdownOnAccountDetailPagePresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Detail')]/following::select[1]"));  
	}

	public boolean isNoteSectionOnAccountDetailPagePresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Notes')]/following::textarea"));  
	}

	public void selectChannelDropdown(String dropdownValue){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		driver.click(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Channel')]/following::div[1]/select"),"");
		driver.waitForElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Channel')]/following::div[1]/select/option[@value='"+dropdownValue+"']"));
		driver.click(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Channel')]/following::div[1]/select/option[@value='"+dropdownValue+"']"),"");
	}

	public void selectReasonDropdown(String dropdownValue){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		driver.click(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Reason')]/following::select[1]"),"");
		driver.waitForElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Reason')]/following::select[1]/option[@value='"+dropdownValue+"']"));
		driver.click(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Reason')]/following::select[1]/option[@value='"+dropdownValue+"']"),"");
	}

	public void selectDetailDropdown(String dropdownValue){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		driver.click(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Detail')]/following::select[1]"),"");
		driver.waitForElementPresent(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Detail')]/following::select[1]/option[@value='"+dropdownValue+"']"));
		driver.click(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Detail')]/following::select[1]/option[@value='"+dropdownValue+"']"),"");
	}

	public void enterNote(String orderNote){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		driver.type(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Notes')]/following::textarea"), orderNote);
		//driver.findElement(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Notes')]/following::textarea")).sendKeys(orderNote);
		logger.info("Entered order note is "+orderNote);
	}

	public void clickOnSaveAfterEnteringNote(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		driver.waitForElementPresent(By.xpath("//a[@id='accountNoteId:noteFrm:save']"));
		driver.click(By.xpath("//a[@id='accountNoteId:noteFrm:save']"),"");
		driver.waitForCRMLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean isAccountDropdownOnAccountDetailPageIsEnabled(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Account')]/following::span[1]/input")).isEnabled();
	}

	public boolean isChannelDropdownOnAccountDetailPageIsEnabled(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Channel')]/following::select[1]")).isEnabled();
	}

	public boolean isDetailDropdownOnAccountDetailPageIsEnabled(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//div[@id='mainDiv']//div[contains(text(),'Detail')]/following::select[1]")).isEnabled();
	}

	public String getNoteFromUIOnAccountDetailPage(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/div/div/div/iframe"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/div/div/div/iframe")));
		String nodeText= driver.findElement(By.xpath("//h3[text()='Account Notes']/following::tr/th[text()='Notes']/following::td[3]")).getText();
		return nodeText;
	}

	public void updateRecognitionNameField(String recognitionName){
		switchToExtFrame(2);
		driver.findElement(By.xpath("//*[contains(text(),'Recognition Name')]/following::input[1]")).sendKeys(recognitionName);
	}

	public void clickSaveBtnUnderAccountDetail(){
		switchToExtFrame(2);
		driver.click(By.xpath("//td[@id='topButtonRow']/input[@title='Save']"),"Save Account button");
		driver.waitForPageLoad();
	}

	public String getRecognitionName(){
		switchToExtFrame(2);
		return driver.findElement(By.xpath("//td[contains(text(),'Recognition Name')]/following-sibling::td[1]")).getText();
	}

	public void updateAddressLine3Field(String AddressLine3){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		driver.findElement(By.xpath("//label[contains(text(),'Address Line 3')]/following::input[1]")).clear();
		driver.type(By.xpath("//label[contains(text(),'Address Line 3')]/following::input[1]"), AddressLine3);
	}

	public String getMainAddressLine3(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//td[contains(text(),'Address Line 3')]/following-sibling::td[1]")).getText();
	}

	public boolean verifyShippingProfileCountIsEqualOrGreaterThanOne(String noOfShippingProfile){
		int noOfProfile = Integer.parseInt(noOfShippingProfile);
		return noOfProfile>=1;
	}

	public void closeSubTabOfEditShippingProfile(){
		driver.switchTo().defaultContent();
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'SP')]/preceding::a[@class='x-tab-strip-close'][1]"))).pause(1000).click().build().perform();
	}

	public String getFirstShippingProfileName(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[2]/td[2]")).getText();
	}

	public void updateAccountNameField(String AccountName){
		switchToExtFrame(2);
		driver.type(By.xpath("//label[contains(text(),'Account Name')]/following::input[1]"), AccountName);
	}

	public String getAccountName(){
		switchToExtFrame(2);
		String accountName = driver.findElement(By.xpath("//td[contains(text(),'Account Name')]/following-sibling::td[1]")).getText();
		String accountNameee[] = accountName.split("\\[");
		return accountNameee[0].trim();
	}


	public boolean isMouseHoverAutoshipSectionPresentOfFields(String field){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		switchToExtFrame(2);
		actions.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Autoships')]"))).build().perform();
		return driver.isElementPresent(By.xpath("//h3[text()='Autoships']/following::th[text()='"+field+"']"));
	}

	public String getCountAutoships(){
		switchToExtFrame(2);
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'Autoships')]"));
		String countAutoshipText = driver.findElement(By.xpath("//span[contains(text(),'Autoships')]/span")).getText();
		String countAutoship = countAutoshipText.substring(1, countAutoshipText.length()-1);
		logger.info("AutoShips count from UI "+countAutoship);
		return countAutoship;
	}

	public void clickAutoships(){
		switchToExtFrame(2);
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'Autoships')]"));
		driver.findElement(By.xpath("//span[contains(text(),'Autoships')]")).click();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public String getCountAutoshipNumber(){
		driver.pauseExecutionFor(4000);
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
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Autoships')]/following::tr[2]/th[1]//a"));
		driver.click(By.xpath("//h3[contains(text(),'Autoships')]/following::tr[2]/th[1]//a"),"");
		driver.pauseExecutionFor(5000);
	}

	public boolean isLabelUnderAutoshipNumberPresent(String label){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[text()='Autoship Template Details']/following::td[text()='"+label+"'][1]"));
	}

	public boolean isLabelUnderPendingAutoshipBreakdownPresent(String label){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[text()='Pending Autoship Breakdown']/following::td[text()='"+label+"'][1]"));
	}

	public void clickShippingProfiles(){
		switchToExtFrame(2);
		driver.waitForElementPresent(By.xpath("//span[text()='Shipping Profiles']"));
		driver.findElement(By.xpath("//span[text()='Shipping Profiles']")).click();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public boolean verifyErrorMessageForPWSNotFound(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		driver.waitForElementPresent(By.xpath("//td[@class='messageCell']//span/h4"));
		return driver.isElementPresent(By.xpath("//td[@class='messageCell']//span/h4"));
	}

	public String getOldSitePrefixWithCompleteSiteBeforeEdit(){
		switchToExtFrame(3);
		String oldSitePrefix = driver.findElement(By.xpath("//div[@class='item1']/../../td[2]//input")).getAttribute("value");
		String siteSuffix = driver.findElement(By.xpath("//div[@class='item1']/div[1]")).getText();
		String siteUrlBeforeEdit = oldSitePrefix+siteSuffix;
		return "http://"+siteUrlBeforeEdit;
	}

	public String getOldSitePrefixBeforeEdit(){
		switchToExtFrame(3);
		String oldSitePrefix = driver.findElement(By.xpath("//div[@class='item1']/../../td[2]//input")).getAttribute("value");
		return "http://"+oldSitePrefix;
	}

	public String getNewSitePrefixWithCompleteSiteAfterEdit(){
		switchToExtFrame(3);
		String sitePrefix = driver.findElement(By.xpath("//div[@class='item1']/../../td[2]//input")).getAttribute("value");
		String siteSuffix = driver.findElement(By.xpath("//div[@class='item1']/div[1]")).getText();
		String siteUrlAfterEdit = sitePrefix+"-"+siteSuffix;
		return "http://"+siteUrlAfterEdit;
	}

	public void enterRandomSitePrefixName(String randomSitePrefixName){
		switchToExtFrame(3);
		driver.type(By.xpath("//input[@type='text']"+"\t"), randomSitePrefixName);
	}

	public void clickCheckAvailabilityButton(){
		switchToExtFrame(3);
		//driver.waitForElementPresent(By.xpath("//a[Text()='Check Availability']"));
		driver.pauseExecutionFor(5000);
//		JavascriptExecutor js = (JavascriptExecutor)(RFWebsiteDriver.driver);
//		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Check Availability']")));
		driver.click(By.xpath("//a[Text()='Check Availability']"),"");
		driver.switchTo().defaultContent();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public void clickPWSSaveButton(){
		switchToExtFrame(3);
		driver.waitForElementPresent(By.xpath("//a[text()='Save']"));
		driver.click(By.xpath("//a[text()='Save']"),"");
		driver.waitForCRMLoadingImageToDisappear();
		driver.pauseExecutionFor(5000);
	}

	public String getCheckAvailabilityMessage(){
		String checkAvailabilityMessage=null;
		switchToExtFrame(3);
		checkAvailabilityMessage = driver.findElement(By.xpath("//div[@class='page pws']/table[2]//span")).getText();
		return checkAvailabilityMessage;
	}

	public String getInfoUnderAccountDetailSection(String label){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		switchToExtFrame(2);
		driver.waitForElementPresent(By.xpath("//table[@class='detailList']//td[text()='"+label+"']/following-sibling::td[1]"));
		return driver.findElement(By.xpath("//table[@class='detailList']//td[text()='"+label+"']/following-sibling::td[1]")).getText().toLowerCase().trim();
	}

	public boolean validateMainAddressIsSavedAsShippingProfile(){
		int beforeCount=getCountOfAccountMainMenuOptions("Shipping Profiles");
		clickSaveAsShippingLinkUnderMainAddress();
		driver.pauseExecutionFor(2000);
		int afterCount=getCountOfAccountMainMenuOptions("Shipping Profiles");
		if(beforeCount<afterCount){
			return true;
		}else{
			return false;
		}
	}

	public int getShippingProfilesRowCount(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.findElements(By.xpath("//body//div[9]//div/div//table[@class='list']//tr")).size();
	}

	public void clickSaveAsShippingLinkUnderMainAddress(){
		driver.switchTo().defaultContent();
		switchToExtFrame(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//td[@class='dataCol last col02']//iframe")));
		driver.click(By.xpath("//a[text()='Save As Shipping']"),"");
		driver.pauseExecutionFor(6000);
	}

	public void updateShippingProfilePostalCode(String postalCode){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
//		driver.waitForElementPresent(By.xpath("//label[contains(text(),'Postal code')]/following::input[1]"));
//		driver.clear(By.xpath("//label[contains(text(),'Postal Code')]/following::input[1]"));
		switchToExtFrame(3);
		driver.type(By.xpath("//label[contains(text(),'Postal Code')]/following::input[1]"), postalCode);
	}

	public void clickAccountMainMenuOptions(String label){
		switchToExtFrame(2);
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'"+label+"')]"));
		driver.findElement(By.xpath("//span[text()='"+label+"']")).click();
		try{
			driver.pauseExecutionFor(2000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}catch(Exception e){

		}
		driver.waitForCRMLoadingImageToDisappear();
	}

	public void clickNewContactButtonUnderContactSection(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		switchToExtFrame(2);
		driver.findElement(By.xpath("//div[@class='listRelatedObject contactBlock']//table[1]//h3[text()='Contacts']/../../td[2]/input")).click();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public boolean verifyNewContactButtonUnderContactSectionNotAvaliable(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return !driver.isElementPresent(By.xpath("//div[@class='listRelatedObject contactBlock']//table[1]//h3[text()='Contacts']/../../td[2]/input"));
	}


	public void enterFirstAndLastNameInCreatingNewContactForSpouse(String firstName , String lastName){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		switchToExtFrame(3);
		driver.type(By.id("name_firstcon2"), firstName);
		driver.type(By.id("name_lastcon2"), lastName);
	}

	public String enterBirthdateInCreatingNewContactForSpouse(){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		driver.click(By.id("con7"),"");
		actions.moveToElement(driver.findElement(By.xpath("//td[contains(@class,'weekday todayDate')]"))).click().build().perform();
		return driver.findElement(By.id("con7")).getAttribute("value");
	}

	public void clickSaveButtonForNewContactSpouse(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		switchToExtFrame(3);
		driver.findElement(By.xpath("//td[@id='topButtonRow']/input[@title='Save']")).click();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public void closeFrameAfterSavingDetailsForNewContactSpouse(String firstName){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'"+firstName+"')]"));
		actions.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'"+firstName+"')]"))).build().perform();
		actions.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'"+firstName+"')]/preceding::a[@class='x-tab-strip-close'][1]"))).click().build().perform();
	}

	public void clickOnSpouseForNewContact(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		driver.waitForElementPresent(By.xpath("//td[text()='Spouse']/preceding::th[1]"));
		driver.findElement(By.xpath("//td[text()='Spouse']/preceding::th[1]")).click();
		driver.waitForCRMLoadingImageToDisappear();
	}


	public void clickEditButtonForNewContactSpouseInContactDetailsPage(){
		switchToExtFrame(3);
		driver.findElement(By.xpath("//div[@class='pbHeader']//td[@class='pbTitle']/../td[2]/input")).click();
	}

	public String isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent(){
		switchToExtFrame(3);
		return driver.findElement(By.xpath("//div[@class='errorMsg']")).getText();

	}

	public void enterMainPhoneInNewContactForSpouse(String mainPhone){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		switchToExtFrame(3);
		driver.type(By.xpath("//table[@class='detailList']//label[text()='Main Phone']/../following-sibling::td//input"), mainPhone);
	}

	public String verifyDataAfterSavingInNewContactForSpouse(String label){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		return driver.findElement(By.xpath("//div[@class='pbSubsection']//td[text()='"+label+"']/following::td[1]")).getText();
	}

	public int getCountOfAccountMainMenuOptions(String label){
		switchToExtFrame(2);
		return driver.findElements(By.xpath("//h3[contains(text(),'"+label+"')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]")).size();
	}

	public boolean verifyIsSpouseContactTypePresentNew(int count){
		boolean flag = false;
		if(count==0){
			logger.info("No Contact Is Available Under Contact Section");
			return flag;
		}else{
			for (int i = 2; i < count+2; i++) {
				String contactType = driver.findElement(By.xpath("//div[@class='listRelatedObject contactBlock']//table[@class='list']//tr["+i+"]//th/following::td[1]")).getText();
				if(!contactType.equals("Spouse")){
					continue;
				}else{
					flag = true;
					break;
				}
			}
			return flag;
		}
	}

	public void clickOnEditUnderContactSection(String contactType){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		switchToExtFrame(2);
		driver.findElement(By.xpath("//td[text()='"+contactType+"']/..//a[text()='Edit']")).click();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public boolean verifyDataUnderContactSectionInContactDetailsPageIsEditable(String label){
		boolean flag = false;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		try{
			driver.findElement(By.xpath("//div[@class='pbSubsection']//td[text()='"+label+"']/following::td[1]")).clear();	
		}catch(Exception e){
			String exceptionMessage = e.getMessage();
			if(exceptionMessage.contains("Element must be user-editable in order to clear it."))
				flag = false;
		}
		return flag;
	}

	public boolean verifyFieldsUnderAccountEditInAccountInformationIsEditable(String label){
		boolean flag = true;
		switchToExtFrame(2);
		try{
			driver.findElement(By.xpath("//td[text()='"+label+"']/following::td[1]")).clear();
		}catch(Exception e){
			String exceptionMessage = e.getMessage();
			if(exceptionMessage.contains("Element must be user-editable in order to clear it."))
				flag = false;
		}
		return flag;
	}

	public boolean verifyRecognizationNameUnderAccountEditInAccountInformationIsEditable(){
		boolean flag = true;
		switchToExtFrame(2);
		try{
			driver.findElement(By.xpath("//label[text()='Recognition Name']/following::td[1]//input")).clear();
		}catch(Exception e){
			String exceptionMessage = e.getMessage();
			if(exceptionMessage.contains("Element must be user-editable in order to clear it."))
				flag = false;
		}
		return flag;
	}

	public boolean verifyAccountNameNameUnderAccountEditInAccountInformationIsEditable(){
		boolean flag = true;
		switchToExtFrame(2);
		try{
			driver.findElement(By.xpath("//label[text()='Account Name']/following::td[1]//input")).clear();
			driver.findElement(By.xpath("//label[text()='Account Name']/following::td[1]//input")).sendKeys(Keys.TAB);
		}catch(Exception e){
			String exceptionMessage = e.getMessage();
			if(exceptionMessage.contains("Element must be user-editable in order to clear it."))
				flag = false;
		}
		return flag;
	}

	public boolean verifyActiveCheckboxUnderAccountEditInAccountInformationIsEditable(){
		boolean flag = true;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		driver.findElement(By.xpath("//td[text()='Active']/following-sibling::td//img")).click();
		if(driver.findElement(By.xpath("//td[text()='Active']/following-sibling::td//img")).getAttribute("title").equalsIgnoreCase("Checked")){
			flag = false;
		}
		return flag;
	}

	public void clickCancelButtonUnderAccountEditInAccountInformationSection(){
		switchToExtFrame(2);
		driver.findElement(By.xpath("//h2[text()='Account Edit']/../following-sibling::td/input[@title='Cancel']")).click();
		driver.waitForPageLoad();
	}

	public boolean isActiveLabelOnAccountDetailsSectionPresent(){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//td[text()='Recognition Title']/../following::tr[1]/td[text()='Active']"));
	}

	public void clickClearButtonInLogAccountActivity(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		driver.click(By.xpath("//a[text()='Clear fields']"),"");
		driver.waitForCRMLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean verifyDropdownTextfieldsAreClearedInLogAccountActivity(String label){
		boolean flag = false;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		WebElement mySelectElm = driver.findElement(By.xpath("//div[contains(text(),'"+label+"')]/..//select"));
		Select mySelect= new Select(mySelectElm);
		WebElement option = mySelect.getFirstSelectedOption();
		String dropdownSelectedValue = option.getText();
		if(dropdownSelectedValue.equals("--None--")){
			flag = true;
		}
		return flag;
	}

	public String IsLogInAccountActivityUpdated(String label){
		int id = 0 ;
		switch (label){
		case "Notes":   id = 3;
		break;
		case "Channel": id = 4;
		break;
		case "Reason":  id = 5;
		break;
		case "Detail":  id = 6;
		break;
		}
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//h3[contains(text(),'Account Notes')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')][1]/td["+id+"]")).getText();
	}

	public boolean isDataValuesInDropDownUnderLogAccountActivityPresent(String label){
		boolean flag = false;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		int size = driver.findElements(By.xpath("//div[contains(text(),'"+label+"')]/../select/option")).size();
		if(size>1){
			flag = true;
		}
		return flag;  
	}

	public void selectReasonToChangeAccountStatusFromDropDown(String dropdownValue){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		//switchFrame(By.xpath("//div[text()='Select the reason to Change Account Status']/../select"));
		//driver.switchTo().frame(driver.findElement(By.xpath("//div[text()='Select the reason to Change Account Status']/../select")));
		switchToExtFrame(3);
		driver.click(By.xpath("//div[text()='Select the reason to Change Account Status']/../select"),"");
		//driver.waitForElementPresent(By.xpath("//div[text()='Select the reason to Change Account Status']/../select/option[@value='"+dropdownValue+"']"));
		driver.click(By.xpath("//div[text()='Select the reason to Change Account Status']/../select/option[@value='"+dropdownValue+"']"),"");
	}

	public String clickSaveButtonToChangeAccountStatus(){
		TimeZone timeZone = TimeZone.getTimeZone("US/Pacific");//"US/Pacific"
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		driver.waitForElementPresent(By.xpath("//a[text()='Save']"));
		driver.click(By.xpath("//a[text()='Save']"),"");
		driver.waitForCRMLoadingImageToDisappear();
		driver.isElementPresent(By.xpath("//xhtml:h4[text()='Success:']"));
		String strCurrentDay = CommonUtils.getCurrentDate("M/d/yyyy", timeZone);
		driver.pauseExecutionFor(6000);
		return strCurrentDay;
	}

	public boolean validateNewUrlWithNewWindow() {
		String parentWindowID=driver.getWindowHandle();
		clickAccountDetailsButton("My Account");
		driver.pauseExecutionFor(8000);
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		boolean status=false;
		while(it.hasNext()){
			String childWindowID=it.next();
			if(!parentWindowID.equalsIgnoreCase(childWindowID)){
				driver.switchTo().window(childWindowID);
				if(driver.getCurrentUrl().contains("corprfo")){
					status=true;
				}

			}
		}
		driver.close();
		driver.switchTo().window(parentWindowID);
		return status;
	}

	public void checkUnKnownAccountChkBox(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/descendant::iframe[1]")));
		if(!driver.isElementPresent(By.xpath("//input[@type='checkbox' and @checked='checked']"))){
			driver.click(By.xpath("//input[@type='checkbox']"),"");
		}
	}

	public int getCountUnderAccountPoliciesSection(){
		return (driver.findElements(By.xpath("//h3[text()='Account Policies']/ancestor::div[3]//tr")).size())-2;
	}

	public boolean isLabelOfAccountMainMenuOptionsPresent(String accountMainMenuOption, String label){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'"+accountMainMenuOption+"')]/following::table[@class='list'][1]//th[text()='"+label+"']"));
	}

	public boolean isMessageOfAccountStatusChangedPresent(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		return driver.isElementPresent(By.xpath("//xhtml:h4[text()='Success:']"));
	}

	public String getValuesOfLabelInAccountStatusesHistory(int columnNumber){
		String value = null;
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		String reasonFistTime = driver.findElement(By.xpath("//h3[contains(text(),'Account Statuses History')]/following::tr[2]/td[2]")).getText();
		if(reasonFistTime.contains("Fist Time activation")){
			if(columnNumber==0){
				value = driver.findElement(By.xpath("//h3[contains(text(),'Account Statuses History')]/following::tr[3]/th")).getText().trim();
			}else{
				value = driver.findElement(By.xpath("//h3[contains(text(),'Account Statuses History')]/following::tr[3]/td["+columnNumber+"]")).getText().trim();
			}
		}else{
			if(columnNumber==0){
				value = driver.findElement(By.xpath("//h3[contains(text(),'Account Statuses History')]/following::tr[2]/th")).getText().trim();
			}else{
				value = driver.findElement(By.xpath("//h3[contains(text(),'Account Statuses History')]/following::tr[2]/td["+columnNumber+"]")).getText().trim();
			}
		}
		return value;
	}

	public boolean verifyWelcomeDropdownToCheckUserRegistered(){  
		driver.waitForElementPresent(By.id("account-info-button"));
		return driver.isElementPresent(By.id("account-info-button"));
	}

	public String getUserNameFromStoreFrontAccountDetails(){
		String username = driver.findElement(By.id("email-account")).getAttribute("value");
		return username;
	}

	public boolean isLabelOnPerformanceKPIsSectionPresent(String label){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Performance KPIs')]/following::table[@class='list'][1]//th[text()='"+label+"']"));
	}

	public String getPerformanceKPIsCount(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return String.valueOf(driver.findElements(By.xpath("//h3[contains(text(),'Performance KPIs')]/following::table[@class='list'][1]//tr[contains(@class,'dataRow')]")).size());
	}

	public boolean verifyActionItemsOnlyViewable() {
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Performance KPIs')]/following::div[1]//tr[2]/td/a[text()='Edit']"));
	}

	public boolean isPeriodDisplayedInYYYY_MMFormat() {
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		String expiryYear = driver.findElement(By.xpath("//h3[contains(text(),'Performance KPIs')]/following::th[text()='Period']/following::td[2]")).getText();
		System.out.println("exp year is "+expiryYear);
		if(expiryYear.split("/")[1].length()==2){
			return true;
		}
		return false;
	}

	public boolean isPerformanceKPIsDetailsPresent() {
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[text()='Performance KPI Information']"));
	}

	public boolean isLabelPresentUnderPerformanceKPIsInformation(String label) {
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Performance KPI Information')]/following::table[@class='detailList'][1]//td[text()='"+label+"']"));
	}

	public boolean isLabelPresentUnderPerformanceKPIsDetails(String label) {
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Performance KPI Details')]/following::table//tr//td[text()='"+label+"']"));
	}

	public void clickPerformanceKPIsName(){
		switchToExtFrame(2);
		driver.click(By.xpath("//h3[contains(text(),'Performance KPIs')]/following::th[text()='Period']/following::tr[1]/th/a"),"");
	}

	public boolean isConsultantDetailsHighlightPanelDisplayed(String label) {
		driver.switchTo().defaultContent();
		return driver.isElementPresent(By.xpath("//div[text()='"+label+"']/following::div[@class='wc_value'][1]"));
	}

	public void hoverOnMainMenuOptionsLink(String label){
		switchToExtFrame(2);
		Actions action = new Actions(RFWebsiteDriver.driver);
		action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'"+label+"')]"))).perform();
	}

	public boolean isRelatedListItemPresent() {
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@class='bRelatedList']"));
	}

	public boolean isAccountMainMenuOptionsPresent(String label){
		switchToExtFrame(2);
		return driver.isElementPresent(By.xpath("//span[contains(text(),'"+label+"')]"));
	}

	public void selectUserEnteredAddress(){
		try{
			driver.switchTo().defaultContent();
			driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
			driver.waitForElementPresent(By.xpath("//label[contains(text(),'User entered address')]/following::input[1]"));
			driver.click(By.xpath("//label[contains(text(),'User entered address')]/following::input[1]"),"");
		}catch(Exception e){
			logger.info("No User Entered Address Button Found");
		}
	}

	public boolean isProfileNameValueOfDefaultShippingProfilesPresent(String profileName){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping Address')]/following::img[@title='Checked'][1]/../preceding::td[text()='"+profileName+"']"));
	}

	public void clickUserEnteredAddress(String addressLocaleRegion){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		driver.click(By.xpath(String.format(userEnteredAddress,addressLocaleRegion)),"");
		driver.waitForCRMLoadingImageToDisappear();
	}		

	public boolean isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(String addressLocaleRegionPostalCode){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::img[@title='Checked'][1]/../following-sibling::td[text()='"+addressLocaleRegionPostalCode+"']"));
	}

	public boolean isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(String addressType, String addressLocaleRegionPostalCode){
		switchToExtFrame(3);
		return driver.isElementPresent(By.xpath("//div[@class='pbSubsection']//td[text()='"+addressType+"']/following::td[text()='"+addressLocaleRegionPostalCode+"']"));
	}

	public void editAddressFieldsOfMainAddressSection(String addressField,String addressText){
		switchToExtFrame(3);
		driver.type(By.xpath("//label[contains(text(),'"+addressField+"')]/following::input[1]"), addressText);
	}

	public String getDataValueOfLabelsInMainAddressSection(String label){
		switchToExtFrame(2);
		return driver.findElement(By.xpath("//td[contains(text(),'"+label+"')]/following-sibling::td[1]")).getText();
	}

	public String getValueOfLabelInAccountMainMenuOptionsPresent(String accountMainMenuOption, int columnNumber){
		String value = null;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		if(columnNumber==0){
			value = driver.findElement(By.xpath("//h3[contains(text(),'"+accountMainMenuOption+"')]/following::tr[2]/th")).getText().trim();
		}else{
			value = driver.findElement(By.xpath("//h3[contains(text(),'"+accountMainMenuOption+"')]/following::tr[2]/td["+columnNumber+"]")).getText().trim();
		}
		return value;
	}

	public void clickUserEnteredAddressRadioBtn(){
		driver.pauseExecutionFor(2000);
		try{
//			driver.switchTo().defaultContent();
//			driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
//			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
			driver.click(By.xpath("//input[@value='User entered']"),"");
			driver.waitForCRMLoadingImageToDisappear();
		}catch (Exception e) {
			logger.info("NO User entered address btn");
		}
	}

	public boolean isNoRecordToDisplayPresentOnShippingProfile(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::div[1]//th[contains(text(),'No records to display')]"));
	}
	public boolean clickMyAccountAndVerifyWelcomeDropDownPresent() {
		String parentWindowID=driver.getWindowHandle();
		clickAccountDetailsButton("My Account");
		driver.waitForPageLoad();
		//driver.pauseExecutionFor(8000);
		boolean isWelcomeDrpdownPresent = false;
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			String childWindowID = it.next();
			if(!parentWindowID.equalsIgnoreCase(childWindowID)){
				driver.switchTo().window(childWindowID);
				if(driver.getCurrentUrl().contains("corprfo") && verifyWelcomeDropdownToCheckUserRegistered()){
					isWelcomeDrpdownPresent = true;
				}
				driver.close();
				driver.switchTo().window(parentWindowID);
			}
		}

		return isWelcomeDrpdownPresent;
	}

	public void clickSendForgotPasswordEmailLink(){
		switchToExtFrame(2);
		driver.switchTo().frame("ResetHybrisPassword");
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[contains(text(),'Send Forgot Password Email')]"),"");
		driver.pauseExecutionFor(2500);		
	}

	public void clickEditButtonForPrimaryContactInContactDetailsPage(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe")));
		driver.waitForElementPresent(By.xpath("//td[text()='Primary']/preceding::td/a[text()='Edit']"));
		driver.findElement(By.xpath("//td[text()='Primary']/preceding::td/a[text()='Edit']")).click();
	}

	public void updateEmailAddressFieldForPrimaryContact(String emailAddress){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe[2]")));
		driver.findElement(By.xpath("//label[text()='Email Address']/../following-sibling::td[@class='dataCol']/input[1]")).clear();
		driver.type(By.xpath("//label[text()='Email Address']/../following-sibling::td[@class='dataCol']/input[1]"), emailAddress);
	}

	public void clickSaveBtnUnderContactInformation(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe[2]")));
		driver.click(By.xpath("//td[@id='bottomButtonRow']/input[@title='Save']"),"");
		driver.waitForPageLoad();
	}

	public boolean isAccountDetailsPagePresentRC(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe")));
		return driver.findElement(By.xpath("//h2[contains(@class,'mainTitle')]")).getText().contains("Account Detail");  
	}

	public void clickSendForgotPasswordEmailLinkRC(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe")));
//		System.out.println("Switched to outer frame");
		//switchFrame(By.xpath("//td[@class='dataCol']//iframe"));
		//driver.waitForElementPresent(By.xpath("//td[@class='dataCol']//iframe"));
		//driver.pauseExecutionFor(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='ResetHybrisPassword']")));
//		System.out.println("Switched to inner frame");
		//switchFrame(By.xpath("//a[contains(text(),'Send Forgot Password Email')]"));
		driver.click(By.xpath("//a[contains(text(),'Send Forgot Password Email')]"),"");
		driver.pauseExecutionFor(2500);
	}

	public boolean isEmailForNewPasswordSentForRC(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe")));
//		driver.switchTo().frame(driver.findElement(By.xpath("//td[@class='dataCol']//iframe")));
		//driver.waitForCRMLoadingProcessImageToDisappear();
		driver.waitForElementPresent(By.xpath(".//*[contains(text(),'A new password was sent successfully')]"));
		boolean status=driver.isElementPresent(By.xpath(".//*[contains(text(),'A new password was sent successfully')]"));
		return status;
	}

	public String getEmailAddressOfUserAfterUpdation(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/div[2]/div[2]/descendant::iframe[2]")));
		String emailAddress=driver.findElement(By.xpath("//td[text()='Email Address']/following-sibling::td[@class='dataCol']/a")).getText();
		logger.info("Address after updation is "+emailAddress);
		return emailAddress;
	}

	public boolean isShowMoreOptionUnderShippingProfilesPresent(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::div[@class='pShowMore']/a[contains(text(),'Show')]"));
	}
	public void clickShowMoreOptionUnderShippingProfilesPresent(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		driver.click(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::div[@class='pShowMore']/a[contains(text(),'Show')]"),"");
		logger.info("Show more link clicked");
		while(true){
			if(isShowMoreOptionUnderShippingProfilesPresent() ==true){
				driver.click(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::div[@class='pShowMore']/a[contains(text(),'Show')]"),"");
			}else{
				break;
			}
		}

	}

	public boolean isEditActionNotPresentUnderContactSection(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return !driver.isElementPresent(By.xpath("//a[@class='actionLink' and contains(text(),'Edit')]"));
	}


	public boolean isMouseHoverContactsSectionEditLinkNotPresentOfFields(String field){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='listTitle' and contains(text(),'Contacts')]"))).build().perform();
		return !driver.isElementPresent(By.xpath("//tr[@class='dataRow even last first highlight']//a[contains(text(),'"+field+"')]/preceding::a[contains(text(),'Edit')]"));

	}

	public boolean isMouseHoverContactsSectionNewContentButtonNotPresentOfFields(String field){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='listTitle' and contains(text(),'Contacts')]"))).build().perform();
		return !driver.isElementPresent(By.xpath("//tr[@class='dataRow even last first highlight']//a[contains(text(),'"+field+"')]/ancestor::div//input"));

	}

	public void selectUserEnteredAddressAndClickOnSaveButton(){
		try{
			driver.switchTo().defaultContent();
			driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
			driver.waitForElementPresent(By.xpath("//label[contains(text(),'User entered address')]/following::input[1]"));
			driver.click(By.xpath("//label[contains(text(),'User entered address')]/following::input[1]"),"");
			driver.click(By.xpath("//a[text()='Save Address']"),"");
			try{
				driver.click(By.xpath(String.format(userEnteredAddress)),"");
				driver.waitForElementPresent(By.xpath("//a[text()='Save Address']"));
				driver.click(By.xpath("//a[text()='Save Address']"),"");
			}catch(Exception e){
			}  
		}catch(Exception e1){
			logger.info("No User Entered Address Button Found");
		}
	}

	public boolean isAddNewShippingProfileButtonPresentAShippingProfileSectiont(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//input[@value='New Shipping Profile']"));
	}

	public boolean isActionPresentAtShippingProfileSectionForFirstProfile(String actionName){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[2]//a[text()='"+actionName+"']"));
	}

	public void clickFirstShippingProfile(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		driver.click(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[2]//th[contains(@class,'cellCol1')]/a"),"");
		driver.waitForCRMLoadingImageToDisappear();
	}

	public boolean isActionPresentAtShippingProfilePage(String actionName){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		return driver.isElementPresent(By.xpath("//td[@id='topButtonRow']//input[contains(@value,'"+actionName+"')]"));
	}

	public String getErrorMessageFromLogAccountNoteSection(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		String errorMessage =  driver.findElement(By.xpath("//td[@class='messageCell']//div[contains(@id,'accountNoteId')]")).getText().toLowerCase().trim();
		logger.info("Error message is "+errorMessage);
		return errorMessage;
	}

	public String clickEditOfNonDefaultShippingProfile(){
		String profileName = null;
		switchToExtFrame(2);
		String title = driver.findElement(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::div[@class='pbBody'][1]//tr[contains(@class,'dataRow')][1]//img")).getAttribute("title");
		if(title.equals("Not Checked")){
			profileName = driver.findElement(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[2]/td[2]")).getText();
			driver.click(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::a[text()='Edit'][1]"),"");
		}else{
			profileName = driver.findElement(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[3]/td[2]")).getText();
			driver.click(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::a[text()='Edit'][2]"),"");
		}
		driver.waitForCRMLoadingImageToDisappear();
		return profileName;
	}

	public String getDefaultSelectedShippingAddressName(){
		switchToExtFrame(2);
		return driver.findElement(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//img[@title='Checked']/../preceding::td[1]")).getText();
	}

	public void clickSaveAddressButtonInEditMainAddressSection(String addressLine){
		switchToExtFrame(3);
		driver.click(By.xpath("//div[@class='pbBottomButtons']//input[@value='Update Account MailingStreet'][1]"),"");
		driver.pauseExecutionFor(2000);
		driver.switchAndAcceptAlert();
		driver.waitForPageLoad();
	}

	public boolean isAccountStatusActive(){
		boolean isActive = false;
		switchToExtFrame(2);
		String checkboxStatus = driver.getText(By.xpath("//td[contains(text(),'Account Status')]/following::td[1]"));
		if(checkboxStatus.equalsIgnoreCase("active")){
			isActive = true;
		}
		return isActive;
	}

	public void enterEmailIdInNewContactForSpouse(String email){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		driver.type(By.xpath("//label[contains(text(),'Email Address')]/following::input[1]"), email);
	}

	public boolean isEmailForNewPasswordSent(){
		switchToExtFrame(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//td[@class='last dataCol']//iframe")));
		driver.waitForElementPresent(By.xpath(".//*[contains(text(),'successfully')]"));
		boolean status=driver.isElementPresent(By.xpath(".//*[contains(text(),'successfully')]"));
		return status;
	}

	public boolean isDateReleasedOrAcceptedColumnPresent(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//th[contains(text(),'Date Released') or contains(text(),'Date Accepted')]"));
	}

	public void editAddressFieldsOfMainAddressSectionWithTextarea(String addressField,String addressText){
		switchToExtFrame(3);
		driver.type(By.xpath("//label[contains(text(),'"+addressField+"')]/following::textarea[1]"), addressText);
	}

	public boolean handleAlertPopUpForMyAccountProxy() {
		try{
			//Wait 10 seconds till alert is present
//			WebDriverWait wait = new WebDriverWait(driver, 20);
//			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String alertMessage = alert.getText();
			System.out.println("alert message"+alertMessage);
			//Accepting alert.
			alert.accept();
			logger.info("Accepted the alert successfully.");
			if(alertMessage.contains("is not available")){
				return true;
			}
		}catch(Throwable e){
			System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
		}
		return false;
	}


	public void clickMyAccountButton(String buttonName){
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
		switchToExtFrame(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='AccountButtons']")));  
		driver.click(By.xpath("//input[@value='"+buttonName+"']"),"");
		//driver.waitForLoadingImageToDisappear();
	}


	public boolean validateErrorMsgIsDisplayedForPostalCode(){
		switchToExtFrame(3);
		driver.waitForElementPresent(By.xpath("//div[@class='errorMsg']"));
		return driver.findElement(By.xpath("//div[@class='errorMsg']")).getText().contains("USA Postal code format is");
	}

	public void clickSaveBtnAfterEditShippingAddress(){
			switchToExtFrame(3);
			driver.click(By.xpath("//div[@class='pbHeader']//input[@title='Save'] | //div[@class='pbHeader']//input[@value='Save']"),"");
			driver.waitForPageLoad();
			driver.waitForCRMLoadingImageToDisappear();
			driver.pauseExecutionFor(10000);
	}

	public void clickEditFirstShippingProfile(){
		switchToExtFrame(2);
		driver.click(By.xpath("//h3[contains(text(),'Shipping Profiles')]/following::table[@class='list'][1]//tr[2]//a[text()='Edit']"),"");
		driver.waitForCRMLoadingImageToDisappear();
	}


	public void enterShippingAddress(String addressLine1, String city, String state, String postalCode, String phoneNumber){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[2]")));
		driver.waitForElementPresent(By.xpath("//label[contains(text(),'Address')]/following::input[1]"));
		driver.type(By.xpath("//label[contains(text(),'Address')]/following::input[1]"), addressLine1);
		driver.type(By.xpath("//label[contains(text(),'Locale')]/following::input[1]"), city);
		driver.type(By.xpath(".//label[contains(text(),'Region')]/following::input[1]"), state);
		driver.type(By.xpath("//label[contains(text(),'Postal')]/following::input[1]"), postalCode);
		driver.type(By.xpath("//label[contains(text(),'Phone')]/following::input[1]"), phoneNumber);
	}
	
	public void clickAccountHeaderLinks(String label){
		switchToExtFrame(2);
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'"+label+"')]"));
		driver.findElement(By.xpath("//span[text()='"+label+"']")).click();
		try{
			driver.pauseExecutionFor(2000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}catch(Exception e){

		}
		driver.waitForCRMLoadingImageToDisappear();
	}
}

