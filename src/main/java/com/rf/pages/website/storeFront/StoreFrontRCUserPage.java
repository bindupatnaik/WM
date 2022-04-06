package com.rf.pages.website.storeFront;

import org.openqa.selenium.By;
import com.rf.core.driver.website.RFWebsiteDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StoreFrontRCUserPage extends StoreFrontRFWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontRCUserPage.class.getName());

	private final By WELCOME_USER_LOC = By.xpath("//a[contains(text(),'Welcome')]");
	private final By WELCOME_DD_SHIPPING_INFO_LINK_LOC = By.linkText("Shipping Info");
	private final By WELCOME_DD_ACCOUNT_INFO_LOC = By.xpath("//a[text()='Account Info']");

	public StoreFrontRCUserPage(RFWebsiteDriver driver) {
		super(driver);		
	}

	public boolean verifyRCUserPage(String username) {
		try{
			driver.quickWaitForElementPresent(WELCOME_USER_LOC);
			return driver.isElementPresent(WELCOME_USER_LOC);
		}catch(Exception e){
			driver.quickWaitForElementPresent(By.xpath("//div[@id='account-info-button']/a/span[2]"));
			return driver.isElementPresent(By.xpath("//div[@id='account-info-button']/a/span[2]"));
		}

	}

	public boolean isLinkPresentOnWelcomeDropDown(String link){
		return driver.isElementPresent(By.linkText(link));
	}

	public StoreFrontShippingInfoPage clickShoppingLinkPresentOnWelcomeDropDown(){
		driver.click(WELCOME_DD_SHIPPING_INFO_LINK_LOC,"Shipping link from welcome drop down ");
		return new StoreFrontShippingInfoPage(driver);
	}


	public StoreFrontAccountInfoPage clickAccountInfoLinkPresentOnWelcomeDropDown() {
		driver.click(WELCOME_DD_ACCOUNT_INFO_LOC,"Account info link from welcome drop down");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
		return new StoreFrontAccountInfoPage(driver);
	}

	public void enterNewUserNameAndClickSaveButton(String newUserName) {
		driver.type(By.id("username-account"),newUserName+"\t");
		logger.info("New username entered is "+newUserName);
		driver.waitForElementPresent(By.id("saveAccountInfo"));
		driver.clickByJS(RFWebsiteDriver.driver,By.id("saveAccountInfo"),"");
		driver.pauseExecutionFor(2000);
		while(true) {
			if(driver.isElementPresent(By.xpath("//p[contains(text(),'Your profile has been updated')]"))) {
				break;
			}else {
				driver.clickByJS(RFWebsiteDriver.driver,By.id("saveAccountInfo"),"");				
			}
		}
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(5000);
	}
}
