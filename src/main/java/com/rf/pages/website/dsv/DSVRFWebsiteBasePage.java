package com.rf.pages.website.dsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.pages.RFBasePage;


public class DSVRFWebsiteBasePage extends RFBasePage{
	private static final Logger logger = LogManager
			.getLogger(DSVRFWebsiteBasePage.class.getName());
	protected RFWebsiteDriver driver;
	protected static String BillingProfiles = "//div[@id='multiple-billing-profiles']//span[contains(@class,'font-bold')][contains(text(),'%s')]";
	protected static final By CONFIRM_DELETE_POPUP = By.xpath("//input[@value='Yes, delete this profile']");
	protected static final By USED_IN_AUTOSHIP_POPUP = By.xpath("//input[@value='Update my autoship']");
	protected static final By CANCEL_AUTOSHIP_MSG = By.xpath("//*[contains(@class,'cancel-link') and text()='Cancel']");

	public DSVRFWebsiteBasePage(RFWebsiteDriver driver){		
		super(driver);
		this.driver = driver;
	}

	public String getBaseURL(){
		return driver.getURL();
	}

	public String getCurrentURL(){
		return driver.getCurrentUrl();
	}

	public void openURL(String URL){
		driver.get(URL);
		driver.waitForPageLoad();
		driver.pauseExecutionFor(3000);
		logger.info("URL opened is "+URL);
	}

	public String convertHTTPS_To_HTTP(String URL){
		if(URL.contains("https")){
			URL=URL.replaceAll("https", "http");
		}	
		return URL;
	}

	public void clickLogo(){
		driver.click(By.xpath("//img[@title='Rodan+Fields']"),"");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public void hoverOnShopLinkAndClickCategoryLink(String categoryName){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.waitForElementPresent(By.id("our-products")); 
		WebElement shopSkinCare = driver.findElement(By.id("our-products"));
		actions.moveToElement(shopSkinCare).pause(1000).click().build().perform();
		driver.pauseExecutionFor(2000);
		WebElement categoryLink = driver.findElement(By.xpath("//ul[@id='dropdown-menu' and @style='display: block;']//a[text()='"+categoryName+"']"));
		actions.moveToElement(categoryLink).pause(1000).build().perform();
		while(true){
			try{
				driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//ul[@id='dropdown-menu' and @style='display: block;']//a[text()='"+categoryName+"']"),"category name="+categoryName);
				break;
			}catch(Exception e){
				actions.moveToElement(shopSkinCare).pause(1000).click().build().perform();
			}
		}
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public void clickDeleteShippingOrBillingProfileLink(String profileName){
		driver.click(By.xpath(String.format(BillingProfiles, profileName)+"/following::a[text()='Delete'][1]"),"Billing Profile Name");
		driver.quickWaitForElementPresent(CONFIRM_DELETE_POPUP);
		driver.click(CONFIRM_DELETE_POPUP,"");
		driver.waitForLoadingImageToDisappear();
		try{
			if(driver.isElementVisible(USED_IN_AUTOSHIP_POPUP, 10))
				driver.click(USED_IN_AUTOSHIP_POPUP,"Used in Autoship");
			driver.waitForLoadingImageToDisappear();
		}catch(Exception e){
			logger.info("Used in Autoship? popup NOT appeared");
		}
	}

	public boolean deleteShippingOrBillingProfiles(){
		driver.waitForLoadingImageToDisappear();
		driver.click(By.xpath("//div[@id='multiple-billing-profiles']/descendant::a[contains(text(),'Delete')][1]"),"");
		boolean isAutoshipPopUpDisplayed = driver.findElement(USED_IN_AUTOSHIP_POPUP).isDisplayed();
		logger.info("is Autoship PopUp Displayed = "+isAutoshipPopUpDisplayed);
		if(isAutoshipPopUpDisplayed==false){
			driver.quickWaitForElementPresent(CONFIRM_DELETE_POPUP);
			//((JavascriptExecutor)RFWebsiteDriver.driver).executeScript("arguments[0].click()", driver.findElement(CONFIRM_DELETE_POPUP));
			Actions actions = new Actions(RFWebsiteDriver.driver);
			actions.click(driver.findElement(CONFIRM_DELETE_POPUP)).build().perform();
			driver.waitForLoadingImageToDisappear();
			return true;
		}
		else{
			driver.pauseExecutionFor(2000);
			logger.info("Used in Autoship,can't delete");
			driver.clickByJS(RFWebsiteDriver.driver, CANCEL_AUTOSHIP_MSG,"Cancel autoship message  button");
			driver.waitForLoadingImageToDisappear();
			return true;
		}
	}



}
