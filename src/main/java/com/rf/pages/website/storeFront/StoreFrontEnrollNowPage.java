package com.rf.pages.website.storeFront;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import com.rf.core.driver.website.RFWebsiteDriver;

public class StoreFrontEnrollNowPage extends StoreFrontRFWebsiteBasePage{
		
	private Actions actions;
	
	public StoreFrontEnrollNowPage(RFWebsiteDriver driver) {
		super(driver);		
	}

	public void searchCID(String cid){
		driver.type(By.id("sponserparam"), cid,"sponsor ="+cid);
		driver.click(By.id("search-sponsor-button"),"search sponsor button");
	}
	
	public void mouseHoverSponsorDataAndClickContinue(){
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.cssSelector("div[class='sponsorDataDiv']"))).click(driver.findElement(By.cssSelector("input[value='Select & Continue']"))).build().perform();				
	}
	
	public void selectEnrollmentKitPage(String kitPrice,String regimenName){
		kitPrice =  kitPrice.toUpperCase();
		driver.click(By.xpath("//div[@class='kit-price' and contains(text(),'"+kitPrice+"')]"),"kitPrice = "+kitPrice);
		regimenName = regimenName.toUpperCase();
		driver.click(By.xpath("//div[@class='regimen-name' and text()='"+regimenName+"']"),"regimen ="+regimenName);
		driver.click(By.cssSelector("input[value='Next']"),"Next button after regimen");
	}
	
	public StoreFrontCreateAccountPage chooseEnrollmentOption(String option){
		option = option.toUpperCase();
		if(option.equalsIgnoreCase("EXPRESS ENROLLMENT")){
			driver.click(By.id("express-enrollment"),"Express enrollment radio button");
		}
		else{
			// to do
		}
		driver.click(By.cssSelector("input[value='Next']"),"Next button after enrollment type");
		return new StoreFrontCreateAccountPage(driver);
	}
}
