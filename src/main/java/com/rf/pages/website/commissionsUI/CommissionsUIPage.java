package com.rf.pages.website.commissionsUI;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;

public class CommissionsUIPage {

	protected RFWebsiteDriver driver;

	private final By SPONSOR_CHANGE_BUTTON= By.xpath("//li[@id='liSponsors']/a");
	private final By DOWNLINE_CID_SEARCH_FIELD= By.xpath("//*[@name='CID']");
	private final By DOWNLINE_CID_SEARCH_BUTTON= By.xpath("//*[@name='CID']/following::button[1]");
	private final By PERIODS_DD= By.id("Periods");
	private final By SPONOSR_TYPES_DD= By.id("SponsorTypes");
	private final By OVERRIDE_REASONS_DD= By.id("OverrideReasons");
	private final By SPONSOR_NEW_CID_SEARCH_FIELD= By.id("txtSponsorCID");
	private final By SPONSOR_NEW_CID_SEARCH_BUTTON= By.id("btnSearchSponsorCID");
	private final By LOADING_IMAGE= By.id("loading");
	private final By SUCCEES_MSG= By.xpath("//li[@class='has-success']");
	private final By SUBMIT_BUTTON= By.id("btnSubmit");
	private String  overrideHistory= "//td[text()='%s']/following::td[text()='%s']";

	public CommissionsUIPage(RFWebsiteDriver driver) {
		this.driver = driver;
	}

	public CommissionsUIPage clickSponsorChangeButton(){
		driver.click(SPONSOR_CHANGE_BUTTON, "Sponsor change link from left panel");
		driver.waitForPageLoad();
		return this;
	}

	public CommissionsUIPage enterDownlineCIDAndclickSearch(String downlineCID){
		driver.type(DOWNLINE_CID_SEARCH_FIELD,downlineCID,"downlineCID");
		driver.pauseExecutionFor(1000);
		driver.click(DOWNLINE_CID_SEARCH_BUTTON, "downlineCID Search btn");
		driver.waitForPageLoad();
		return this;
	}

	public CommissionsUIPage selectOverrideDropDownValues(int periodIndex, String sponsorTypes, String overrideReasons ){
		Select periodDD = new Select(driver.findElement(PERIODS_DD));
		periodDD.selectByIndex(periodIndex);
		Select SponsorTypesDD = new Select(driver.findElement(SPONOSR_TYPES_DD));
		SponsorTypesDD.selectByVisibleText(sponsorTypes);
		Select OverrideReasonsDD = new Select(driver.findElement(OVERRIDE_REASONS_DD));
		OverrideReasonsDD.selectByVisibleText(overrideReasons);
		return this;
	}
	
	public CommissionsUIPage enterSponsorNewCIDAndclickSearch(String sponosorNewCID){
		driver.type(SPONSOR_NEW_CID_SEARCH_FIELD, sponosorNewCID,"Sponosor New CID");
		driver.pauseExecutionFor(1000);
		driver.click(SPONSOR_NEW_CID_SEARCH_BUTTON, "Sponosor New CID Search btn");
		driver.waitForElementToBeInVisible(LOADING_IMAGE, 15);
		driver.pauseExecutionFor(2000);
		return this;
	}
	
	public CommissionsUIPage clickSubmitButton(){
		driver.click(SUBMIT_BUTTON, "Submit Button");
		driver.waitForElementToBeInVisible(LOADING_IMAGE, 15);
		return this;
	}
	
	public boolean isSuccessMsgDisplayed(){
		boolean isSuccessMsgDisplayed=  driver.isElementVisible(SUCCEES_MSG);
		driver.pauseExecutionFor(3000);
		return isSuccessMsgDisplayed;
	}
	
	public boolean isOverrideHistoryHasExpectedValues(String downlineCID, String sponsorTypes, String sponosorNewCID){
		return driver.isElementVisible(By.xpath(String.format(overrideHistory, sponsorTypes,sponosorNewCID)));
	}

}
