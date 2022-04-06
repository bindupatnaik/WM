package com.rf.pages.website.storeFront;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;

import com.rf.core.driver.website.RFWebsiteDriver;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StoreFrontPCUserPage extends StoreFrontRFWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontPCUserPage.class.getName());


	public StoreFrontPCUserPage(RFWebsiteDriver driver) {
		super(driver);
	}
	Actions actions;
	private final By WELCOME_USER_LOC = By.xpath("//a[contains(text(),'Welcome')]");
	//private final By WELCOME_USER_DD_LOC = By.cssSelector("li[id='account-info-button']"); 
	private final By WELCOME_DD_ORDERS_LINK_LOC = By.xpath("//div[@id='account-info']//a[text()='Orders']");
	private final By WELCOME_DD_ACCOUNT_INFO_LOC = By.xpath("//a[text()='Account Info']");
	private final By YOUR_ACCOUNT_DROPDOWN_LOC = By.xpath("//div[@id='left-menu']//div/button[contains(text(),'Your Account')]");
	private final By EDIT_ORDER_DATE_BTN = By.xpath("//a[contains(text(),'Edit order')]");
	private final By NEXT_ORDER_DATE_TXT_LOC = By.xpath("//span[contains(text(),'Next Order Date')]");
	private final By AUTOSHIP_DATE_CALENDAR_ICON = By.id("autoshipdatepicker");
	private final By MONTH_NAME_ON_CALENDAR = By.xpath("//span[@class='ui-datepicker-month']");
	private final By YEAR_ON_CALENDAR = By.xpath("//span[@class='ui-datepicker-year']");
	private final By CONFIRM_DATE_CHANGE_BTN = By.id("set-new-ship-date");
	private final By NEXT_ORDER_DATE_LOC = By.xpath("//span[contains(@class,'scheduled-date')]");
	private final By CANCEL_PC_PERKS_LINK = By.id("cancel-pc-perks-button");
	private final By PROCESSED_DATE_AT_ORDER_SUMMARY = By.xpath("//div[@id='confirm-left-shopping']/descendant::b[1]");
	private final By ORDER_SCHEDULED_DATE_AT_ORDER_SUMMARY = By.xpath("//div[@id='confirm-left-shopping']/descendant::b[2]");
	private final By CONFIRM_BTN_OF_BUY_NOW = By.xpath("//div[@id='confirm-left-shopping']/descendant::input[@value = 'Confirm'][1]");
	private final By TOTAL_DISBALED_DAYS = By.xpath("//td[contains(@class,'disabled')]/span");
	private final By TOTAL_DAYS_IN_CALENDAR = By.xpath("//*[@class='ui-state-default']");
	private final By NEXT_ICON_OF_CALENDAR = By.xpath("//span[text()='Next']");
	private final By CANCEL_BTN_OF_BUY_NOW = By.xpath("//div[@id='confirm-left-shopping']/descendant::a[text()='Cancel'][1]");
	private final By CHANGE_DATE_BTN = By.xpath("//a[text()='Change Date']");
	
	private final String enabledDayInCalendar = "//td[@data-handler='selectDay']/a[text()='%s']";
	private final String disabledDayInCalendar = "//td[contains(@class,'disabled')]/span[text()='%s']";
	private final String textOnAutoshipStatusPageLoc = "//div[@id='cancel-pc-perks']/*[contains(text(),'%s')]";
	private final String dayInCalendarLoc = "//a[text()='%s']";

	public boolean verifyPCUserPage(){
		driver.waitForElementPresent(WELCOME_USER_LOC);
		return driver.isElementPresent(WELCOME_USER_LOC);		
	}

	public StoreFrontAccountInfoPage clickAccountInfoLinkPresentOnWelcomeDropDown() {
		driver.click(WELCOME_DD_ACCOUNT_INFO_LOC,"Account info link from welcome drop down");
		driver.waitForPageLoad();
		return new StoreFrontAccountInfoPage(driver);
	}

	public StoreFrontCartAutoShipPage addProductToPCPerk(){
		try{
			driver.quickWaitForElementPresent(By.xpath("//div[contains(@class,'blue')]/div[2]/div[1]//input[contains(@value,'PC Perks')]"));
			driver.click(By.xpath("//div[contains(@class,'blue')]/div[2]/div[1]//input[contains(@value,'PC Perks')]"),"");
		}catch(Exception e){
			try{
				driver.click(By.xpath("//div[contains(@class,'blue')]/div[2]/div[2]//input[contains(@value,'PC Perks')]"),"");
			}catch(Exception e1){
				driver.click(By.xpath("//div[contains(@class,'blue')]/div[2]/div[3]//input[contains(@value,'PC Perks')]"),"");
			}
		}

		logger.info("Add Product to PC Perk button clicked");

		try{
			driver.click(By.xpath("//input[@value='OK']"),"Ok button");
			driver.waitForLoadingImageToDisappear();
		}catch(Exception e){

		}

		return new StoreFrontCartAutoShipPage(driver);
	}

	public void clickDelayOrCancelPCPerks(){
		driver.waitForElementPresent(By.id("cancel-pc-perks-button"));
		driver.click(By.id("cancel-pc-perks-button"),"Delay or Cancel PC perks button");
		//retryClick(By.xpath("//a[text()='Delay or Cancel PC Perks']"), "Delay or Cancel PC perks button");
		driver.waitForLoadingImageToDisappear();
	}
	
	public StoreFrontHomePage cancelMyPCPerksAct(){
		driver.waitForElementPresent(By.id("problemType"));
		//driver.click(By.id("problemType"));
		driver.click(By.id("problemType"),"Problem type DD");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[6]"));
		driver.click(By.xpath("//select[@id='problemType']/option[6]"),"");
		driver.waitForElementPresent(By.xpath("//textarea[@id='terminationComments']"));
		driver.findElement(By.xpath("//textarea[@id='terminationComments']")).sendKeys("test");
		driver.click(By.xpath("//input[@id='pcperkTerminationConfirm']"),"");
		driver.waitForElementPresent(By.xpath("//input[@id='confirmpcTemrminate']"));
		driver.click(By.xpath("//input[@id='confirmpcTemrminate']"),"");
		driver.waitForLoadingImageToDisappear();
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[@value='Close window']"));
			driver.click(By.xpath("//input[@value='Close window']"),"");
		}catch(Exception e){   
			driver.click(By.xpath("//div[@id='popup-content']/div/div/following::input[@value='Close window']"),"");
		}
		driver.waitForPageLoad();
		return new StoreFrontHomePage(driver);
	}

	public boolean isNextPCPerksMiniCartDisplayed() {  
		actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.id("mini-shopping-special-button"))).build().perform();
		driver.waitForLoadingImageToDisappear();
		return driver.isElementVisible(By.xpath("//*[@id='mini-shopping-special-button']//*[@id='mini-shopping']"));
	}

	public void selectSecondAutoshipDateAndClickSave(){
		driver.click(By.xpath("//ul[@id='autoship-date']//li[2]/span[1]"),"pc perks delayed date");
		driver.click(By.xpath("//ul[@id='autoship-date']//input[@value='save']"),"save button after different date selected");
		driver.waitForLoadingImageToDisappear();
	}

	public String getAccountterminationReasonTooMuchProduct(){
		driver.waitForElementPresent(By.id("problemType"));
		driver.click(By.id("problemType"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[2]"));
		return driver.findElement(By.xpath("//select[@id='problemType']/option[2]")).getText();
	}

	public String getAccountterminationReasonTooExpensive(){
		driver.waitForElementPresent(By.id("problemType"));
		driver.click(By.id("problemType"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[3]"));
		return driver.findElement(By.xpath("//select[@id='problemType']/option[3]")).getText();
	}
	public String getAccountterminationReasonEnrolledInAutoShipProgram(){
		driver.waitForElementPresent(By.id("problemType"));
		driver.click(By.id("problemType"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[4]"));
		return driver.findElement(By.xpath("//select[@id='problemType']/option[4]")).getText();
	}
	public String getAccountterminationReasonProductNotRight(){
		driver.click(By.id("problemType"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[5]"));
		return driver.findElement(By.xpath("//select[@id='problemType']/option[5]")).getText();
	}
	public String getAccountterminationReasonUpgradingToConsultant(){
		driver.click(By.id("problemType"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[6]"));
		return driver.findElement(By.xpath("//select[@id='problemType']/option[6]")).getText();
	}
	public String getAccountterminationReasonReceiveProductTooOften(){
		driver.click(By.id("problemType"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[7]"));
		return driver.findElement(By.xpath("//select[@id='problemType']/option[7]")).getText();
	}
	public String getAccountterminationReasonDoNotWantToObligated(){
		driver.click(By.id("problemType"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[8]"));
		return driver.findElement(By.xpath("//select[@id='problemType']/option[8]")).getText();
	}
	public String getAccountterminationReasonOther(){
		driver.click(By.id("problemType"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='problemType']/option[9]"));
		return driver.findElement(By.xpath("//select[@id='problemType']/option[9]")).getText();
	}

	public boolean verifyToSectionInSendcancellationMessageSection(){
		driver.quickWaitForElementPresent(By.xpath("//form[@id='CancelAutoshipAccountForm']/div[2]/div[1]/div[2]"));
		return driver.isElementPresent(By.xpath("//form[@id='CancelAutoshipAccountForm']/div[2]/div[1]/div[2]"));
	}
	public boolean verifySubjectSectionInSendcancellationMessageSection(){
		driver.quickWaitForElementPresent(By.xpath("//form[@id='CancelAutoshipAccountForm']/div[2]/div[2]/div[2]"));
		return driver.isElementPresent(By.xpath("//form[@id='CancelAutoshipAccountForm']/div[2]/div[2]/div[2]"));
	}

	public boolean verifyMessageSectionInSendcancellationMessageSection(){
		driver.quickWaitForElementPresent(By.xpath("//textarea[@id='terminationComments']"));
		return driver.isElementPresent(By.xpath("//textarea[@id='terminationComments']"));
	}

	public boolean verifyIHaveChangedMyMindButton(){
		driver.quickWaitForElementPresent(By.xpath("//form[@id='CancelAutoshipAccountForm']/div[2]/div[4]/div[2]/input[1]"));
		return driver.isElementPresent(By.xpath("//form[@id='CancelAutoshipAccountForm']/div[2]/div[4]/div[2]/input[1]"));
	}

	public boolean verifySendAnEmailToCancelAccountButton(){
		driver.quickWaitForElementPresent(By.xpath("//form[@id='CancelAutoshipAccountForm']/div[2]/div[4]/div[2]/input[2]"));
		return driver.isElementPresent(By.xpath("//form[@id='CancelAutoshipAccountForm']/div[2]/div[4]/div[2]/input[2]"));
	}

	public boolean validatePCPerkCartDisplayed(){
		driver.waitForElementPresent(By.xpath("//div[@id='bag-special']/span"));
		return driver.isElementPresent(By.xpath("//div[@id='bag-special']/span"));
	}

	public String getNextBillAndShipDate(){
		String nextBillAndShipDate = null;
		driver.quickWaitForElementPresent(By.xpath("//div[@id='pc-perks-status']/div[3]/span[2]"));
		if(driver.getCountry().equalsIgnoreCase("au")){
			String completeDate[] = driver.findElement(By.xpath("//div[@id='pc-perks-status']/div[3]/span[2]")).getText().split(" ");
			String day =completeDate[0];
			String year =completeDate[2];
			String month = completeDate[1];
			nextBillAndShipDate = month+" "+day+", "+year;
			return nextBillAndShipDate;
		}else{
			return driver.findElement(By.xpath("//div[@id='pc-perks-status']/div[3]/span[2]")).getText();
		}
	}		

	public String getOneMonthOutDate(String date){
		String completeDate[] = date.split(" ");
		String []splittedDay =completeDate[1].split("\\,");
		String day =splittedDay[0];
		String year =completeDate[2];
		String month=completeDate[0];
		int a = 0;
		int b = 0;
		String UIMonth = null;
		if(month.equalsIgnoreCase("January")){
			a=1;
		}else if(month.equalsIgnoreCase("February")){
			a=2;
		}else if(month.equalsIgnoreCase("March")){
			a=3;
		}
		else if(month.equalsIgnoreCase("April")){
			a=4;
		}
		else if(month.equalsIgnoreCase("May")){
			a=5;
		}
		else if(month.equalsIgnoreCase("June")){
			a=6;
		}
		else if(month.equalsIgnoreCase("July")){
			a=7;
		}
		else if(month.equalsIgnoreCase("August")){
			a=8;
		}
		else if(month.equalsIgnoreCase("September")){
			a=9;
		}
		else if(month.equalsIgnoreCase("October")){
			a=10;
		}
		else if(month.equalsIgnoreCase("November")){
			a=11;
		}else if(month.equalsIgnoreCase("December")){
			a=12;
		}else{
			a=0;
		}
		a=a+1;
		if(a==13){
			a=1;
			b=1;
		}
		switch (a) {  
		case 1:
			UIMonth="January";
			break;
		case 2:
			UIMonth="February";
			break;
		case 3:
			UIMonth="March";
			break;
		case 4:
			UIMonth="April";
			break;
		case 5:
			UIMonth="May";
			break;
		case 6:
			UIMonth="June";
			break;
		case 7:
			UIMonth="July";
			break;
		case 8:
			UIMonth="August";
			break;
		case 9:
			UIMonth="September";
			break;
		case 10:
			UIMonth="October";
			break;
		case 11:
			UIMonth="November";
			break;
		case 12:
			UIMonth="December";
			break;  
		}
		if(b==1){
			int yearly=Integer.parseInt(year)+1;
			year=Integer.toString(yearly);
		}
		String dateAfterOneMonth=UIMonth+" "+day+","+" "+year;
		logger.info("created date is "+dateAfterOneMonth);
		return dateAfterOneMonth;
	}

	public boolean verifyPCPerksStatus(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']//div[contains(text(),'PC perks status')]"));
		if(driver.isElementPresent(By.xpath("//div[@id='main-content']//div[contains(text(),'PC perks status')]"))){
			return true;
		}else{
			return false;
		}
	}

	public String getShipAndBillDateAfterOneMonthFromUI(){
		driver.waitForElementPresent(By.xpath("//ul[@id='autoship-date']/li[2]/ul/li[1]/span[2]"));
		String dateList=driver.findElement(By.xpath("//ul[@id='autoship-date']/li[2]/ul/li[1]/span[2]")).getText();
		if(driver.getCountry().equalsIgnoreCase("au")){
			String completeDate[] = dateList.split(" ");
			String day =completeDate[0];
			String year =completeDate[2];
			String month = completeDate[1];
			String date = month+" "+day+", "+year;
			logger.info("bill and ship date after one month is "+date);
			return date;
		}else{
			logger.info("bill and ship date after one month is "+dateList);
			return dateList;
		}
	}

	public String getShipAndBillDateAfterTwoMonthFromUI(){
		driver.waitForElementPresent(By.xpath("//ul[@id='autoship-date']/li[2]/ul/li[2]/span[2]"));
		String dateList=driver.findElement(By.xpath("//ul[@id='autoship-date']/li[2]/ul/li[2]/span[2]")).getText();
		if(driver.getCountry().equalsIgnoreCase("au")){
			String completeDate[] = dateList.split(" ");
			String day =completeDate[0];
			String year =completeDate[2];
			String month = completeDate[1];
			String date = month+" "+day+", "+year;
			logger.info("bill and ship date after two month is "+date);
			return date;
		}else{
			logger.info("bill and ship date after two month is "+dateList);
			return dateList;
		}
	}

	public void selectFirstAutoshipDateAndClickSave(){
		driver.waitForElementPresent(By.xpath("//ul[@id='autoship-date']//input[@value='save']"));
		driver.click(By.xpath("//ul[@id='autoship-date']//input[@value='save']"),"");
		logger.info("save button clicked after different date selected");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean verifyNextAutoshipDateRadioButtons(){
		driver.quickWaitForElementPresent(By.xpath("//span[@class='radio-button selectautoshipDate']"));
		if(driver.isElementPresent(By.xpath("//span[@class='radio-button selectautoshipDate']"))){
			return true;
		}else{
			return false;
		}
	}

	public void clickOnAddtoPCPerksButton(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::input[contains(@value,'ADD to PC Perks')][1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='main-content']/descendant::input[contains(@value,'ADD to PC Perks')][1]"),"");
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[@value='OK']"));
			driver.click(By.xpath("//input[@value='OK']"),"");
		}catch(Exception e){
		}
		logger.info("Add To PC perks button clicked");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean verifyUpdateCartMessage(String message){
		driver.quickWaitForElementPresent(By.xpath(".//div[@id='globalMessages']//p"));
		if(driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).getText().equalsIgnoreCase(message)){
			return true;
		}else{
			return false;
		}

	}
	
	public void clickChangeMyAutoshipDateButton(){
		driver.clickByJS(RFWebsiteDriver.driver, By.id("change-autoship-button"),"");//(By.id("change-autoship-button"),"");
	}
	
	public void clickPleaseCancelMyPcPerksActBtn(){
		//driver.click(By.id("cancel-pc-perks-button"),"Cancel PC perks");
		driver.clickByJS(RFWebsiteDriver.driver, By.id("cancel-pc-perks-button"),"Cancel PC perks");
	}
	
	public void clickEditOrderDateBtn(){
		driver.waitForElementPresent(EDIT_ORDER_DATE_BTN);
		driver.click(EDIT_ORDER_DATE_BTN, "Edit order button clicked");
	}
	
	public boolean isNextOrderDateTextDisplayed(){
		return driver.isElementVisible(NEXT_ORDER_DATE_TXT_LOC);
	}
	
	public boolean isTextVisibleOnPCAutoshipStatusPage(String text){
		return driver.isElementVisible(By.xpath(String.format(textOnAutoshipStatusPageLoc, text)));
	}
	
	public boolean isCalendarOrTxtBoxForDateDisplayed(){
		return driver.isElementVisible(AUTOSHIP_DATE_CALENDAR_ICON);
	}
	
	public void clickCalendarIcon(){
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(AUTOSHIP_DATE_CALENDAR_ICON);
		//driver.findElement(AUTOSHIP_DATE_CALENDAR_ICON).click();
		driver.moveToELement(AUTOSHIP_DATE_CALENDAR_ICON);
		driver.click(AUTOSHIP_DATE_CALENDAR_ICON, "Autoship calendar icon");
	}
	
	public void selectDayInCalendar(String day){
		driver.click(dayInCalendarLoc, day, "Day in calendar");
	}
	
	public String getMonthNameFromCalendar(){
		return driver.getText(MONTH_NAME_ON_CALENDAR,"Month name in calendar");
	}
	
	public String getYearFromCalendar(){
		return driver.getText(YEAR_ON_CALENDAR,"Year in calendar");
	}
	
	public void clickConfirmDateChangeBtn(){
		driver.waitForElementPresent(CONFIRM_DATE_CHANGE_BTN);
		driver.click(CONFIRM_DATE_CHANGE_BTN, "Confirm date change btn");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}
	
	public String getNextOrderDate(){
		return driver.getText(NEXT_ORDER_DATE_LOC,"Next order date");
	}
	
	public boolean isCancelPCPerksLinkDisplayed(){
		return driver.isElementVisible(CANCEL_PC_PERKS_LINK);
	}
	
	
	public void fillTheEntriesAndClickOnSubmitDuringTerminationForPC(){
		driver.clickByJS(RFWebsiteDriver.driver, By.id("reason"),"reason");
		driver.click(By.xpath("//select[@id='problemType']/option[contains(text(),'Other')]"),"");
		driver.type(By.id("terminationComments"), "I want to terminate my account");
		driver.clickByJS(RFWebsiteDriver.driver, By.id("pcperkTerminationConfirm"),"Send email to cancel account");
		driver.waitForLoadingImageToDisappear();  
	}
	
	public String getOrderProcessedDateAtOrderReview(){
		return driver.getText(PROCESSED_DATE_AT_ORDER_SUMMARY,"Order processed date at order review page").toLowerCase().trim();
	}
	
	public String createAndGetOrderProcessingDateForOrderReview(){
		int currentDay = getCurrentDayFromCurrentDate();
		String currentMonth = getCurrentMonthFromCurrentDate();
		String currentYear = getCurrentYearFromCurrentDate();
		String expectedDate = null;
		expectedDate = currentMonth+" "+currentDay+", "+currentYear;
		logger.info("Created order processing date "+expectedDate.toLowerCase().trim());
		return expectedDate.toLowerCase().trim();
	}

	public String createAndGetOrderScheduledDate(){
		int currentDay = 0;
		String monthNameAfterTwoMonthFromCurrent = null;
		String yearAfterTwoMonthFromCurrent = null;
		currentDay = getCurrentDayFromCurrentDate();
		monthNameAfterTwoMonthFromCurrent = getNextTwoMonthDateFromDate();
		yearAfterTwoMonthFromCurrent = getYearAfterTwoMonthFromCurrentDate();
		String expectedDate = null;
		if(currentDay<=17){
		expectedDate = monthNameAfterTwoMonthFromCurrent+" "+currentDay+", "+yearAfterTwoMonthFromCurrent;
		}else{
			expectedDate = monthNameAfterTwoMonthFromCurrent+" 17, "+yearAfterTwoMonthFromCurrent;
		}
		logger.info("Created order scheduled date "+expectedDate.toLowerCase().trim());
		return expectedDate.toLowerCase().trim();
	}
	
	public void clickConfirmBtnOfBuyNow(){
		driver.waitForElementPresent(CONFIRM_BTN_OF_BUY_NOW);
		driver.click(CONFIRM_BTN_OF_BUY_NOW, "Confirm btn of Buy now");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
	}
	
	public boolean isValidDateSelectedByDefault(int day){
		return driver.getAttribute(By.xpath(String.format(dayInCalendarLoc, day)), "class").contains("state-active");
	}
	
	public String getDateAfterAddingTheDays(int noOfDays){
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
//		Calendar c = Calendar.getInstance();
//		c.setTime(new Date()); // Now use today date.
//		c.add(Calendar.DATE, noOfDays); 
//		String date = sdf.format(c.getTime());
		String date = LocalDate.now().plusDays(noOfDays).format(DateTimeFormatter.ofPattern("dd/MMM/YYYY"));
		logger.info("Date after add days"+date);
		return date;
	}
	
	public int getDayFromDate(String date){
		String day = date.split("\\/")[0];
		if(day.substring(0, 1).contains("0")){
			day = day.replaceAll("0", "");
		}
		return Integer.parseInt(day);
	}
	
	public String getMonthFromDate(String date){
		return date.split("\\/")[1].toLowerCase().trim();
	}
	
	public boolean isValidDaysEnabledOfMonth(int startingValidDay){
		int currentDay = startingValidDay;
		boolean isValidDayEnabled = false;
		int totalDisabledDays = driver.findElements(TOTAL_DISBALED_DAYS).size();
		if(currentDay<=17){
			for(int day=currentDay; day<=17; day++){
				isValidDayEnabled = false;
				if(driver.isElementPresent(By.xpath(String.format(enabledDayInCalendar, day)))){
					isValidDayEnabled = true;
				}else{
					isValidDayEnabled = false;
					break;
				}
			}
		}else{
			for(int day=18; day<=totalDisabledDays; day++){
				isValidDayEnabled = false;
				if(driver.isElementPresent(By.xpath(String.format(disabledDayInCalendar, day)))){
					isValidDayEnabled = true;
				}else{
					isValidDayEnabled = false;
					break;
				}
			}
		}
		return isValidDayEnabled;
	}
	
	public void clickNextIconOfCalendar(){
		driver.waitForElementPresent(NEXT_ICON_OF_CALENDAR);
		driver.click(NEXT_ICON_OF_CALENDAR, "Next icon of calendar");
	}
	
	public boolean isValidDaysEnabledTillDay(int enabledDay){
		int currentDay = 1;
		boolean isValidDayEnabled = false;
		if(currentDay<=enabledDay){
			for(int day=currentDay; day<=enabledDay; day++){
				isValidDayEnabled = false;
				if(driver.isElementPresent(By.xpath(String.format(enabledDayInCalendar, day)))){
					isValidDayEnabled = true;
				}else{
					isValidDayEnabled = false;
					break;
				}
			}
		}
		return isValidDayEnabled;
	}
	
	public boolean isValidDaysDisabledTillEnd(int enabledDay){
		int currentDay = enabledDay+1;
		boolean isValidDayDisabled = false;
			int totalDisabledDays = driver.findElements(TOTAL_DAYS_IN_CALENDAR).size();
			for(int day=currentDay; day<=totalDisabledDays; day++){
				isValidDayDisabled = false;
				if(driver.isElementPresent(By.xpath(String.format(disabledDayInCalendar, day)))){
					isValidDayDisabled = true;
				}else{
					isValidDayDisabled = false;
					break;
				}
			}
		
		return isValidDayDisabled;
	}
	
	public boolean isAllDaysDisabledOfMonth(){
		boolean isValidDayDisabled = false;
			int totalDisabledDays = driver.findElements(TOTAL_DISBALED_DAYS).size();
			for(int day=1; day<=totalDisabledDays; day++){
				isValidDayDisabled = false;
				if(driver.isElementPresent(By.xpath(String.format(disabledDayInCalendar, day)))){
					isValidDayDisabled = true;
				}else{
					isValidDayDisabled = false;
					break;
				}
			}
		
		return isValidDayDisabled;
	}
	
	public String getOrderScheduledDateAtOrderReview(){
		String date =  driver.getText(ORDER_SCHEDULED_DATE_AT_ORDER_SUMMARY,"Order Scheduled date at order review page").toLowerCase().trim();
		String month = date.split(" ")[0].substring(0, 3);
		String day = date.split(" ")[1];
		String year = date.split(" ")[2];
		return month+" "+day+" "+year;
	}
	
	
	public String createAndGetOrderScheduledDateForAU(){
		int currentDay = 0;
		String monthNameAfterTwoMonthFromCurrent = null;
		String yearAfterTwoMonthFromCurrent = null;
		currentDay = getCurrentDayFromCurrentDate();
		monthNameAfterTwoMonthFromCurrent = getNextTwoMonthDateFromDate();
		yearAfterTwoMonthFromCurrent = getYearAfterTwoMonthFromCurrentDate();
		String expectedDate = null;
		if(currentDay<=17){
				expectedDate = currentDay+" "+monthNameAfterTwoMonthFromCurrent+" "+yearAfterTwoMonthFromCurrent;
		}else{
				expectedDate = "17 "+monthNameAfterTwoMonthFromCurrent+" "+yearAfterTwoMonthFromCurrent;
		}
		logger.info("Created order scheduled date for AU"+expectedDate.toLowerCase().trim());
		return expectedDate.toLowerCase().trim();
	}
	
	public String getAndCreateOrderScheduledDateAtOrderReview(){
		String date =  driver.getText(ORDER_SCHEDULED_DATE_AT_ORDER_SUMMARY,"Order Scheduled date at order review page").toLowerCase().trim();
		String month = date.split(" ")[0];
		String day = date.split(" ")[1].split("\\,")[0];
		String year = date.split(" ")[2];
		String finalDate = null;
		if(driver.getCountry().equalsIgnoreCase("ca")){
			finalDate = month+" "+day+", "+year;
		}else{
			finalDate = day+" "+month+" "+year;
		}
		
		logger.info("get and created order schedule date "+finalDate.toLowerCase());
		return finalDate.toLowerCase();
	}
	
	public void clickCancelButton(){
		driver.waitForElementToBeVisible(CANCEL_BTN_OF_BUY_NOW, 20);
		driver.click(CANCEL_BTN_OF_BUY_NOW ,"Cancel button");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
	}
	
	public String getAndCreateDayForCalendar(){
		int currentDay = getCurrentDayFromCurrentDate();
		String day = null;
		if(currentDay<17){
			day = ""+(currentDay+1);
		}else{
			day = "1";
		}
		return day;
	}
	
	public boolean isDatePresentInFormat(){
		String dateFromUI =  driver.getAttribute(AUTOSHIP_DATE_CALENDAR_ICON,"placeholder");
		logger.info("Date eg in calendar "+dateFromUI);
		String month = dateFromUI.split(" ")[1];
		logger.info("month from UI date "+month);
		String day = dateFromUI.split(" ")[2].split("\\,")[0];
		logger.info("day from UI date "+day);
		String year = dateFromUI.split(" ")[3].split("\\)")[0];
		logger.info("year from UI date "+year);
		return String.valueOf(day).contains("5") && month.toLowerCase().contains("may") && year.contains("2018");
	}
	
	public boolean isChangeDateBtnDisplayed(){
		return driver.isElementVisible(CHANGE_DATE_BTN);
	}
	
	public void clickChangeDateButton(){
		driver.waitForElementToBeVisible(CHANGE_DATE_BTN, 20);
		driver.click(CHANGE_DATE_BTN ,"Change date button");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}
	
	public boolean isEditOrderDateBtnDisplayed(){
		driver.waitForElementPresent(EDIT_ORDER_DATE_BTN);
		return driver.isElementVisible(EDIT_ORDER_DATE_BTN);
	}
	
}

