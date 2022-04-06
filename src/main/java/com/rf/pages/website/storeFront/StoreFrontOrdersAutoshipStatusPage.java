package com.rf.pages.website.storeFront;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import com.rf.core.driver.website.RFWebsiteDriver;

public class StoreFrontOrdersAutoshipStatusPage extends StoreFrontRFWebsiteBasePage {
	private static final Logger logger = LogManager
			.getLogger(StoreFrontOrdersAutoshipStatusPage.class.getName());

	private final By AUTOSHIP_PAGE_HEADER_LOC = By.xpath("//div[@id='main-content']//div[@class='gray-container-info-top pt1 text-uppercase']");
	private final By AUTOSHIP_CRP_STATUS_LOC = By.xpath("//div[@id='crp-status']/div[1]/span");
	private final By AUTOSHIP_PULSE_STATUS_LOC = By.xpath("//div[@id='pulse-status']/div[1]/span");
	private final By ENROLL_IN_CRP_LOC = By.xpath("//input[@id='crp-enroll']");

	public StoreFrontOrdersAutoshipStatusPage(RFWebsiteDriver driver) {
		super(driver);
	}


	public boolean verifyAutoShipStatusHeader(){
		driver.waitForElementPresent(AUTOSHIP_PAGE_HEADER_LOC);
		String autoShipStatusHeaderText = driver.findElement(AUTOSHIP_PAGE_HEADER_LOC).getText();
		if(autoShipStatusHeaderText.contains("AUTOSHIP STATUS")){
			return true;
		}
		return false;
	}

	public boolean verifyAutoShipCRPStatus(){
		driver.waitForElementPresent(AUTOSHIP_CRP_STATUS_LOC);
		String autoShipCRPStatusText = driver.findElement(AUTOSHIP_CRP_STATUS_LOC).getText();
		if(autoShipCRPStatusText.contains("Enrolled")){
			return true;
		}
		return false;
	}

	public boolean verifyAutoShipPulseSubscriptionStatus(){
		driver.waitForElementPresent(AUTOSHIP_PULSE_STATUS_LOC);
		String autoShipPulseStatusText = driver.findElement(AUTOSHIP_PULSE_STATUS_LOC).getText();
		if(autoShipPulseStatusText.contains("Enrolled")){
			return true;
		}
		return false;
	}

	public void clickOnEnrollInCRP() throws InterruptedException{
		driver.click(ENROLL_IN_CRP_LOC,"Enrol in CRP");
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOnCRPCheckout(){
		driver.click(By.id("crpCheckoutButton"),"CRP checkout button");
		logger.info("checkout button clicked");
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOnEditShipping(){
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-addresses-summary']//p[2]/a"));  
		driver.click(By.xpath("//div[@id='multiple-addresses-summary']//p[2]/a"),"Edit Shipping link");
		driver.waitForLoadingImageToDisappear();
	}

}
