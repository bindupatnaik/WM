package com.rf.pages.website.LSD.dsv;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.pages.RFBasePage;

public class DSVLSDRFWebsiteBasePage extends RFBasePage{
	

	private static final Logger logger = LogManager
			.getLogger(DSVLSDRFWebsiteBasePage.class.getName());

	private String isTextPresentAtHeader = "//h1[contains(text(),'%s')]";
	protected static final By FIRST_NAME_OVERVIEW_SECTION_LOC = By.xpath("//div[text()='Hello,']/following-sibling::div[contains(@class,'text-uppercase')]");
	protected static final By PULSE_IN_TOP_NAV_LOC = By.xpath("//div[@id='rf-nav-wrapper']/descendant::span[text()='Pulse'][1]");
	private static final By LOGOUT_BTN = By.id("nav-logout") ;
	protected RFWebsiteDriver driver;
	public DSVLSDRFWebsiteBasePage(RFWebsiteDriver driver) {
		super(driver);
		this.driver = driver;
	}	

	public String getCurrentURL(){
		return driver.getCurrentUrl();
	}

	public void navigateToHomePage(){
		if(driver.getCurrentUrl().contains("home")==false){
			driver.get(driver.getURL()+"/#/home");
			driver.waitForLSDLoaderAnimationImageToDisappear();
			driver.pauseExecutionFor(2000);
		}	

	}
	public void navigateToURL(String url){
	        driver.get(url);
			driver.waitForLSDJustAMomentImageToDisappear();
			driver.pauseExecutionFor(2000);
		}	

	public String getCurrentPSTDate(){
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		df.setTimeZone(TimeZone.getTimeZone("PST"));
		final String dateString = df.format(new Date());
		String[] datePST = dateString.split(" ");
		System.out.println(dateString);
		String splittedDateForMonth = datePST[1]+" "+datePST[2]+" "+datePST[3];
		return splittedDateForMonth;
	}

	/***
	 * This method switch the window from parent to child
	 * 
	 * @param parent
	 *            window handle
	 * @return LSDRFWebsiteBasePage object
	 * 
	 */
	public DSVLSDRFWebsiteBasePage switchToChildWindow(String parentWindowID) {
		driver.pauseExecutionFor(1000);
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String childWindowID = it.next();
			if (!parentWindowID.equalsIgnoreCase(childWindowID)) {
				driver.switchTo().window(childWindowID);
				logger.info("Switched to child window");
			}
		}
		driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
		return this;
	}

	public void waitForTotalExpectedWindowsOpened(int expectedNumberOfWindows){
		WebDriverWait wait = new WebDriverWait(RFWebsiteDriver.driver, 10);
		wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
	}

	public String getCurrentMonthFromCurrentPSTDate(){
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		df.setTimeZone(TimeZone.getTimeZone("PST"));
		final String dateString = df.format(new Date());
		String[] datePST = dateString.split(" ");
		System.out.println(dateString);
		String splittedMonthFromDate = datePST[1];
		return splittedMonthFromDate;
	}


	public void navigateToBackPage(){
		driver.navigate().back();
		driver.waitForPageLoad();
	}	

	public String getParentWindowHandle(){
		return driver.getWindowHandle();
	}

	public String getBaseURL(){
		return driver.getURL();
	}

	public void openURL(String url){
		driver.waitForLSDJustAMomentImageToDisappear();
		driver.get(url);
		driver.waitForPageLoad();
	}

	/***
	 * This method switch the window
	 * 
	 * @param parent
	 *            window handle
	 * @return LSDRFWebsiteBasePage object
	 * 
	 */
	public DSVLSDRFWebsiteBasePage switchToParentWindow(String parentWindowID) {
		String currentWindowHandle = getParentWindowHandle();
		if(!parentWindowID.equalsIgnoreCase(currentWindowHandle)){
			driver.close();
		}
		driver.switchTo().window(parentWindowID);
		logger.info("Switched to parent window");
		driver.pauseExecutionFor(2000);
		return this;
	}
	
	public boolean isTextPresentAtHeader(String text){
		return 	driver.isElementPresent(By.xpath(String.format(isTextPresentAtHeader, text)));
	}
	
	public String getFirstNameFromOverviewSection(){
		driver.waitForElementToBeVisible(FIRST_NAME_OVERVIEW_SECTION_LOC,5);
		if(driver.isElementPresent(FIRST_NAME_OVERVIEW_SECTION_LOC)){
			return driver.getText(FIRST_NAME_OVERVIEW_SECTION_LOC, "First name in overview section");
		}
		return null;
	}
	
	public boolean isFirstNamePresent(){
		driver.waitForElementToBeVisible(FIRST_NAME_OVERVIEW_SECTION_LOC,5);
		return driver.isElementPresent(FIRST_NAME_OVERVIEW_SECTION_LOC);
	}
	
	public void clickLogout(){
		driver.waitForElementPresent(PULSE_IN_TOP_NAV_LOC, 5);
		driver.movetToElementJavascript(PULSE_IN_TOP_NAV_LOC);
		logger.info("Mouse Hover on Pulse Section from Top Navigation");
		//driver.waitForElementToBeVisible(LOGOUT_BTN, 10);
		driver.clickByJS(RFWebsiteDriver.driver, LOGOUT_BTN,"");
		driver.waitForLSDJustAMomentImageToDisappear();
	}
	
	public boolean isFirstNamePresentInOverviewSection(){
		driver.waitForElementToBeVisible(FIRST_NAME_OVERVIEW_SECTION_LOC,5);
		return !(driver.findElement(FIRST_NAME_OVERVIEW_SECTION_LOC).getText().isEmpty());
	} 
}
