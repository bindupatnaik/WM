package com.rf.pages.website.storeFront;

import org.openqa.selenium.By;
import com.rf.core.driver.website.RFWebsiteDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StoreFrontCartAutoShipPage extends StoreFrontRFWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontCartAutoShipPage.class.getName());


	//	private final By UPDATE_MORE_INFO_LINK_LOC = By.xpath("//input[@value='Update more info']");
	private final By UPDATE_MORE_INFO_LINK_LOC = By.xpath("//a[contains(text(),'Update Shipping and Billing info')]");	
	private final By SHIP_AND_PROCESS_DATE_LOC = By.xpath("//div[contains(text(),'Ship & Process Date')]/following::span[1]");	

	public StoreFrontCartAutoShipPage(RFWebsiteDriver driver) {
		super(driver);		
	}

	public StoreFrontUpdateCartPage clickUpdateMoreInfoLink() {
		driver.click(UPDATE_MORE_INFO_LINK_LOC,"Update More Info link");
		driver.waitForLoadingImageToDisappear();
		return new StoreFrontUpdateCartPage(driver);
	}

	public String getShipAndProcessDate() {
		String date = driver.getText(SHIP_AND_PROCESS_DATE_LOC,"Ship and process date").toLowerCase().trim();
		String day = date.split(" ")[1];
		String month = date.split(" ")[0];
		String year = date.split(" ")[2];
		if(day.substring(0, 1).contains("0")){
			day = day.replaceAll("0", "");
		}
		logger.info("Final ship and preocess date is "+month+" "+day+" "+year);
		return month+" "+day+" "+year;

	}	
}

