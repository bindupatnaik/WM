package com.rf.test.website.storeFront.hybris.captcha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.test.website.RFWebsiteBaseTest;
import com.rf.test.website.storeFront.hybris.billingAndShipping.AddEditBillingTest;

public class CaptchaTest extends RFWebsiteBaseTest {
	private static final Logger logger = LogManager
			.getLogger(AddEditBillingTest.class.getName());
	
	private final By NAME_FIELD_LOC = By.id("field");
	private final By SUBMIT_BTN_LOC = By.id("submit");
	
	@Test
	public void testEnterNameAndSubmit(){
		driver.get("http://rfinvrecpoc.westus.cloudapp.azure.com/");
		driver.findElement(NAME_FIELD_LOC).sendKeys("auto");
		driver.findElement(SUBMIT_BTN_LOC).click();
		driver.switchAndAcceptAlert();
		driver.pauseExecutionFor(5000);
		driver.switchAndAcceptAlert();
		driver.pauseExecutionFor(5000);
	}
	
}
