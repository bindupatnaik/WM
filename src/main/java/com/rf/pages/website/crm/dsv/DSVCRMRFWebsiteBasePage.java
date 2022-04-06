package com.rf.pages.website.crm.dsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.pages.RFBasePage;

public class DSVCRMRFWebsiteBasePage extends RFBasePage {
	
	private static final Logger logger = LogManager
			.getLogger(DSVCRMRFWebsiteBasePage.class.getName());
	
	protected RFWebsiteDriver driver;

	public void navigateToStroreFrontURL() {
		String storefrontURL=driver.getStoreFrontURL();
		driver.get(storefrontURL);
	}

	public DSVCRMRFWebsiteBasePage(RFWebsiteDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void switchToChildWindow(String parentWindowHandle) {
		driver.switchToChildWindow(parentWindowHandle);
	}
	
	public void closeChildAndSwitchToParentWindow(String parentWindowHandle){
		driver.close();
		logger.info("child window closed");
		driver.switchTo().window(parentWindowHandle);
		logger.info("Child window closed and swiched to Parent");
	}
	
	
	private final By SEARCH_TEXT_BOX_LOC = By.id("phSearchInput");
	
	public void enterTextInSearchFieldAndHitEnter(String text){
		driver.switchTo().defaultContent();
		driver.type(SEARCH_TEXT_BOX_LOC,text,"search text");
		driver.pauseExecutionFor(1000);
		driver.findElement(SEARCH_TEXT_BOX_LOC).sendKeys(Keys.RETURN);
		driver.pauseExecutionFor(1000);
		driver.waitForPageLoad();
		driver.waitForCRMLoadingImageToDisappear();
		try{
			driver.click(By.xpath("//a[@id='phSearchClearButton']"),"");	
		}catch(Exception e){

		}
		driver.pauseExecutionFor(1000);
	}
	
	public boolean isAccountDetailsPagePresent(){
		switchToExtFrame(2);
		driver.waitForElementPresent(By.xpath("//h2[contains(@class,'mainTitle')]"));
		return driver.findElement(By.xpath("//h2[contains(@class,'mainTitle')]")).getText().contains("Account Detail");		
	}
	
	public void switchToExtFrame(int i){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/descendant::iframe[contains(@class,'x-border-panel')]["+i+"]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[contains(@class,'x-border-panel')]["+i+"]")));
		logger.info("Switched to ext frame index = "+i);
	}
	
	public void closeAllOpenedTabs(){
		driver.switchTo().defaultContent();
		int totalOpenedTabs = 0;
		totalOpenedTabs = driver.findElements(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]")).size();
		for(int count=1;count<=3;count++){
			logger.info("total opened tabs = "+totalOpenedTabs);
			Actions actions = new Actions(RFWebsiteDriver.driver);
			for(int i=totalOpenedTabs;i>=1;i--){
				//driver.waitForElementPresent(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]["+i+"]/descendant::a[@class='x-tab-strip-close']"));
				actions.moveToElement(driver.findElement(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]["+i+"]/descendant::a[@class='x-tab-strip-close']"))).pause(500).click().build().perform();
				//driver.click(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]["+i+"]/descendant::a[@class='x-tab-strip-close']"));
				driver.pauseExecutionFor(1000);
				if(driver.isElementPresent(By.xpath("//button[text()='Save']/following::button[1]"))){
					driver.click(By.xpath("//button[text()='Save']/following::button[1]"),"Don't save button");
				}
				driver.pauseExecutionFor(1000);
			}
			totalOpenedTabs = driver.findElements(By.xpath("//li[contains(@id,'navigatortab__scc-pt')]")).size();
			if(totalOpenedTabs==0){
				break;
			}else{
				continue;
			}
		}
		logger.info("All tabs closed");
	}

}
