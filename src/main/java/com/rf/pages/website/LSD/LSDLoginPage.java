package com.rf.pages.website.LSD;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.rf.core.driver.website.RFWebsiteDriver;

public class LSDLoginPage extends LSDRFWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(LSDLoginPage.class.getName());

	public LSDLoginPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final By LOGIN_BTN = By.id("button-login");
	private static final By USERNAME_TXT_FIELD = By.id("username");
	private static final By PASSWORD_TXT_FIELD = By.id("password");
	private static final By LOGIN_ERROR_MSG = By.id("login-error");
	private static final By GREETING_TEXT_LOC =  By.xpath("//div[@class='greeting']/div[contains(text(),'Hello')]");
	private static final By LOGIN_ERROR_TEXT_LOC = By.xpath("//*[@id='login-error']/descendant::p[2]");
	private static final By TEXT_ABOVE_LOGIN_FIELDS_LOC = By.xpath("//div[@class='text-center get-started']/span");


	public void enterUsername(String userName){
		driver.waitForElementPresent(USERNAME_TXT_FIELD,20);
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
		driver.pauseExecutionFor(4000);
		driver.waitForElementToBeVisible(PULSE_IN_TOP_NAV_LOC,20);
		return new LSDHomePage(driver);
	}
	
	public void waitForPulseTopLinkToGetDisplayed(){
		driver.waitForElementToBeVisible(PULSE_IN_TOP_NAV_LOC, 15);
	}

	public boolean isLoginFailErrorPresent(){
		driver.waitForElementPresent(LOGIN_ERROR_MSG);
		return driver.isElementPresent(LOGIN_ERROR_MSG);
	}

	public String getLoginErrorText(){
		driver.waitForElementPresent(LOGIN_ERROR_TEXT_LOC);
		return driver.getText(LOGIN_ERROR_TEXT_LOC);
	}

	public String getTextAboveLoginFields(){
		driver.waitForElementPresent(TEXT_ABOVE_LOGIN_FIELDS_LOC);
		return driver.getText(TEXT_ABOVE_LOGIN_FIELDS_LOC);
	}

	public boolean isLoginBtnPresent(){
		driver.waitForElementPresent(LOGIN_BTN,15);
		return driver.isElementVisible(LOGIN_BTN);
	}

	public LSDHomePage loginToPulse(String userName, String password){
		enterUsername(userName);
		enterPassword(password);
		clickLoginBtn();
		driver.waitForLoginToPulse();
		if(isFirstNamePresent()==false){
			driver.get(driver.getURL());
			driver.waitForPageLoad();
			if(isFirstNamePresent()==false){
				clickLogout();
			}
			driver.waitForPageLoad();
			for(int i=1; i<3; i++){
				if(isLoginBtnPresent()){
					enterUsername(userName);
					enterPassword(password);
					clickLoginBtn();
					driver.waitForLoginToPulse();
				}else{
					break;
				}
			}
		}
		return new LSDHomePage(driver);
	} 

	public void pulseLogin(String userName, String password){
		if(StringUtils.isEmpty(userName) && StringUtils.isEmpty(password)){
			clickLoginBtn();
			driver.waitForLoginToPulse();
		}else{
			enterUsername(userName);
			enterPassword(password);
			clickLoginBtn();
			driver.waitForLoginToPulse();
		} 
	} 
}