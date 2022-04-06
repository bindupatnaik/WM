package com.rf.pages.website.LSD.dsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.pages.website.LSD.LSDHomePage;

public class DSVLSDLoginPage extends DSVLSDRFWebsiteBasePage {

	private static final Logger logger = LogManager
			.getLogger(DSVLSDLoginPage.class.getName());

	public DSVLSDLoginPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	private static final By USERNAME_TXT_FIELD = By.id("username");
	private static final By PASSWORD_TXT_FIELD = By.id("password");
	private static final By LOGIN_BTN = By.id("button-login");
	private static final By GREETING_TEXT_LOC =  By.xpath("//div[@class='greeting']/div[contains(text(),'Hello')]");
	
	
	public void enterUsername(String userName){
		driver.waitForElementPresent(USERNAME_TXT_FIELD);
		driver.type(USERNAME_TXT_FIELD, userName);
		logger.info("username is "+userName);
	}

	public void enterPassword(String password){
		//		driver.waitForElementPresent(PASSWORD_TXT_FIELD);
		driver.type(PASSWORD_TXT_FIELD, password);
		logger.info("password is "+password);
	}

	public LSDHomePage clickLoginBtn(){
		driver.click(LOGIN_BTN,"");
		driver.waitForElementToBeVisible(GREETING_TEXT_LOC,20);
		return new LSDHomePage(driver);
	}
	public LSDHomePage loginToPulse(String userName, String password){
		enterUsername(userName);
		enterPassword(password);
		clickLoginBtn();
		driver.waitForLoginToPulse();
		return new LSDHomePage(driver);
	} 
}
