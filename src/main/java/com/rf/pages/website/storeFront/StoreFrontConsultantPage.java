
package com.rf.pages.website.storeFront;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import com.rf.core.driver.website.RFWebsiteDriver;

public class StoreFrontConsultantPage extends StoreFrontRFWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontConsultantPage.class.getName());

	Actions actions;
	private final By WELCOME_USER_LOC = By.id("account-info-button");	
	private final By NEXT_CRP_IMG_LOC = By.xpath("//li[@id='mini-shopping-special-button']//div[contains(text(),'Next')]");

	public StoreFrontConsultantPage(RFWebsiteDriver driver) {
		super(driver);		
	}

	public boolean verifyConsultantPage() throws InterruptedException{		
		driver.waitForElementPresent(WELCOME_USER_LOC);
		return driver.isElementPresent(WELCOME_USER_LOC);		
	}

	public boolean isLinkPresentOnWelcomeDropDown(String link){
		return driver.isElementPresent(By.linkText(link));
	}

	public StoreFrontCartAutoShipPage clickNextCRP(){
		driver.click(NEXT_CRP_IMG_LOC,"Next CRP link ");
		driver.waitForPageLoad();
		return new StoreFrontCartAutoShipPage(driver);
	}

	public boolean validateErrorMessageWithSpclCharsOnPulseSubscription(){
		driver.type(By.xpath("//input[@id='webSitePrefix']"),"!@","website prefix");
		driver.click(By.id("pulse-enroll"),"pulse enroll");
		return (driver.findElement(By.xpath("//img[@id='prefixIsAvailableImage']")).isDisplayed()||driver.findElement(By.xpath("//span[@class='prefix unavailable']")).isDisplayed());
	}

	public boolean validateNextCRPMiniCart() {
		actions=new Actions(RFWebsiteDriver.driver);
		driver.waitForElementToBeVisible(By.xpath("//li[@id='mini-shopping-special-button']"), 20);
		return driver.findElement(By.xpath("//li[@id='mini-shopping-special-button']")).isDisplayed();
	}

	public boolean validatePulsePrefixSuggestionsAvailable(){
		driver.pauseExecutionFor(1000);
		return driver.findElement(By.xpath("//p[@id='prefix-validation']")).isDisplayed();
	}

	public String getUserNameAForVerifyLogin(String profileName){
		/*		driver.waitForElementPresent(By.xpath("//span[contains(text(),'"+profileName+"')]"));
		String userName = driver.findElement(By.xpath("//span[contains(text(),'"+profileName+"')]")).getText();
		return userName;*/
		driver.waitForElementPresent(By.xpath("//span[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'"+profileName+"')]"));
		String userName = driver.findElement(By.xpath("//span[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'"+profileName+"')]")).getText().toLowerCase();
		return userName;
	}

	public String getHomtownNamePresentOnAfterClickonPersinalizeLink(){
		driver.waitForPageLoad();
		String homeTown = driver.findElement(By.xpath("//input[@id='homeTown']")).getAttribute("value");
		return homeTown;
	}

	public String getConsultantSinceTextPresentAfterClickonPersinalizeLink(){
		return driver.findElement(By.xpath("//span[contains(text(),'Consultant since')]")).getText();
	}

	public String getFavoriteProductNameIsPresentAfterClickonPersinalizeLink(){
		if(driver.getCountry().equalsIgnoreCase("au")){
			return driver.findElement(By.xpath("//span[contains(text(),'FAVOURITE PRODUCTS')]")).getText();
		}else{
			return driver.findElement(By.xpath("//span[contains(text(),'Favorite products')]")).getText();
		}
	}

	public boolean verifyBlockOfWhyIJoinedOnMeetYourConsultantPage(){
		driver.waitForPageLoad();
		if(driver.isElementPresent(By.xpath("//div[contains(@class,'consultant-biodata-right')]/p[1]"))){
			return true;
		}else
			return false;
	}

	public boolean verifyBlockOfProductsOnMeetYourConsultantPage(){
		driver.waitForPageLoad();
		if(driver.isElementPresent(By.xpath("//div[contains(@class,'consultant-biodata-right')]/p[1]"))){
			return true;
		}else
			return false;
	}

	public boolean verifyBlockOfBusinessOnMeetYourConsultantPage(){
		driver.waitForPageLoad();
		if(driver.isElementPresent(By.xpath("//div[contains(@class,'consultant-biodata-right')]/p[1]"))){
			return true;
		}else
			return false;
	}

	public boolean validateMeetYourConsultantPage(){
		driver.pauseExecutionFor(3000);
		String meetYourConsultantViewURL = "meetYourConsultant/view/meetYourConsultant"; 
		String meetYourConsultantURL = "meetYourConsultant/meetYourConsultant/MeetYourConsultantPage";
		return driver.getCurrentUrl().toLowerCase().contains(meetYourConsultantViewURL.toLowerCase())|| driver.getCurrentUrl().toLowerCase().contains(meetYourConsultantURL.toLowerCase());
	}

	public boolean validateCRPCartDisplayed(){
		driver.waitForElementPresent(By.xpath("//div[@id='bag-special']/span"));
		return driver.isElementPresent(By.xpath("//div[@id='bag-special']/span"));
	}

	public boolean validateAdhocCartIsDisplayed(){
		driver.waitForElementPresent(By.xpath("//span[@class='cart-section']"));
		return driver.isElementPresent(By.xpath("//span[@class='cart-section']"));
	}

	public void addNewContentOfYourOwnCopy() {
		driver.waitForElementPresent(By.xpath("//form[@id='consultantInfoForm']//p[3]//div[4]"));
		driver.clear(By.xpath("//form[@id='consultantInfoForm']//p[3]//div[4]"));
		driver.type(By.xpath("//form[@id='consultantInfoForm']//p[3]//div[4]"), "newly added content");
		driver.pauseExecutionFor(1000);
	}

	public boolean verifyDefaultContentReseted() {
		driver.waitForElementPresent(By.xpath("//form[@id='consultantInfoForm']//p[3]//div[4]"));
		String content = driver.findElement(By.xpath("//form[@id='consultantInfoForm']//p[3]//div[4]")).getText();
		if(content.contains("Rodan + Fields has brought confidence, freedom, connections and fun into my life.")){
			return true;
		}
		return false;

	}
	public void clickSaveButton() {
		driver.waitForElementPresent(By.xpath("//div[@id='consultant-container']//input[1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='consultant-container']//input[1]"),"Save button");
		driver.waitForLoadingImageToDisappear();
	}

	public boolean verifyNewlyAddedContentSaved() {
		driver.waitForElementPresent(By.xpath("//div[@class='content-left-side1']/p"));
		return driver.isElementPresent(By.xpath("//div[@class='content-left-side1']/p"));
	}

	public void addNewContentOfYourOwnCopyInComPWS() {
		driver.waitForElementPresent(By.xpath("//form[@id='consultantInfoForm']//p[2]//div[4]"));
		driver.clear(By.xpath("//form[@id='consultantInfoForm']//p[2]//div[4]"));
		driver.type(By.xpath("//form[@id='consultantInfoForm']//p[2]//div[4]"), "newly added content in com pws");
		driver.pauseExecutionFor(1000);
	}

	public void clickResetToDefaultCopyLinkInComPWS() {
		driver.click(By.xpath("//a[@id='aboutMeComReset']"),"About me");
	}

	public boolean verifyDefaultContentResetedForComPWS() {
		driver.waitForElementPresent(By.xpath("//form[@id='consultantInfoForm']//p[2]//div[4]"));
		String content = driver.findElement(By.xpath("//form[@id='consultantInfoForm']//p[2]//div[4]")).getText();
		if(content.contains("I am proud to represent Rodan + Fields")){
			return true;
		}
		return false;
	}

	public boolean verifyPersonalizeMyProfileLinkPresent() {
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Personalise') or contains(text(),'Personalize')]"));
		return driver.isElementPresent(By.xpath("//a[contains(text(),'Personalise') or contains(text(),'Personalize')]"));
	}

	public boolean validateEditedPhoneNumberSaved(String phoneNumber) {
		driver.waitForElementPresent(By.xpath("//div[@class='contactBox']//a[1]"));
		String editedPhoneNumber = driver.findElement(By.xpath("//div[@class='contactBox']//a[1]")).getText();
		String number[] = editedPhoneNumber.split("\\.");
		String requiredNumber = number[0]+number[1]+number[2];
		if(requiredNumber.contains(phoneNumber))
			return true;
		else
			return false;
	}

	public boolean verifyShopSkinCareLinkPresent() {
		if(driver.getCountry().equalsIgnoreCase("au"))
			return driver.isElementPresent(By.xpath("//div[@id='header']/following::a[@title='SHOP SKINCARE'][1]"));
		else
			return driver.isElementPresent(By.xpath("//div[@id='header']//a[@title='SHOP SKINCARE']"));
	}

	public boolean verifyAboutRFLinkPresent() {
		if(driver.getCountry().equalsIgnoreCase("au"))
			return driver.isElementPresent(By.xpath("//div[@id='header']/following::li[@id='CompanyBar'][1]/a[contains(text(),'About') or contains(text(),'ABOUT')]"));
		else
			return driver.isElementPresent(By.xpath("//div[@id='header']//li[@id='CompanyBar']/a[contains(text(),'ABOUT')]"));

	}

	public void eraseHomeTownCityName(){
		driver.waitForPageLoad();
		driver.findElement(By.xpath("//input[@id='homeTown']")).clear();
	}

	public boolean validateHomeTownCityFieldValueIsNull(){
		driver.quickWaitForElementPresent(By.xpath("//input[@id='homeTown']"));
		return driver.isElementPresent(By.xpath("//input[@id='homeTown' and @value='']"));
	}

	public boolean isAutoshipLinkPresentOnThePage() {
		return driver.isElementPresent(By.xpath("//div[@id='bag-special']"));
	}
	
public void cancelPulseSubscription(){
	driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[contains(text(),'Cancel my PULSE') or (text()='Cancel my Pulse subscription »')]"),"Cancel pulse button");
	driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//a[@id='cancelPulse']"),"cancel pulse");//(By.xpath("//a[@id='cancelPulse']"),"cancel pulse");
	if(driver.isElementVisible(By.id("cancel-pulse-button"),5)) {
		driver.click(By.id("cancel-pulse-button"), "cancel pulse button");
	}
	driver.waitForLoadingImageToDisappear();
//	try{
//		driver.quickWaitForElementPresent(By.id("cancel-pulse-button"));
//		driver.click(By.id("cancel-pulse-button"));
//		driver.waitForLoadingImageToDisappear();
//	}catch(Exception e){
//
//	}
	driver.waitForPageLoad();
}	

public void clickResetToDefaultCopyLink() {
	driver.waitForElementPresent(By.xpath("//a[@id='aboutMeBizReset']"));
	driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//a[@id='aboutMeBizReset']"),"Reset about me button");
	driver.pauseExecutionFor(5000);
}


public void clickOnMeetYourConsultantLink(){
	driver.pauseExecutionFor(1500);
	try{
		driver.waitForElementPresent(By.xpath("//div[@id='header-middle-top']//a"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='header-middle-top']//a"),"Meet your condultant link");
	}catch(Exception e){
		logger.info("Meet Your Consultant link is not present");
		e.printStackTrace();
	}
	driver.waitForPageLoad();
}


}