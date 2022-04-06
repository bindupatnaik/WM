package com.rf.pages.website.crm;

import java.util.List;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.rf.core.driver.website.RFWebsiteDriver;

public class CRMHomePage extends CRMRFWebsiteBasePage {
	private static final Logger logger = LogManager
			.getLogger(CRMHomePage.class.getName());

	private final By USER_NAVIGATION_LABEL_LOC = By.id("userNavLabel");
	private final By SEARCH_TEXT_BOX_LOC = By.id("phSearchInput");

	private final By CASE_NUMBER_FROM_TAB_LOC = By.xpath("//td[text()='Case Number']/following::td[1]");
	private final By IFRAME_LOC_1 = By.xpath("//div[@id='navigatortab']/div[3]/div/descendant::iframe[1]");
	private final By IFRAME_LOC_2 = By.xpath("//div[@id='navigatortab']/div[3]/div/descendant::iframe[2]");
	private final By IFRAME_LOC_3 = By.xpath("//div[@id='navigatortab']/div[3]/div/descendant::iframe[3]");
	private final By IFRAME_LOC_4 = By.xpath("//div[contains(@class,'multiCompStac')]/iframe");
	private final By SERVICE_CLOUD_DD_LOC = By.xpath("//div[contains(@class,'support-servicedesk')]//em");
	private final By CANCEL_BTN_OF_EDIT_RECORD_POPUP_LOC = By.id("RPPCancelButton");
	private final By NEW_CASE_BTN_LOC = By.xpath("//input[@value='New Case']");
	private final By TYPE_DD_LOC = By.xpath("//div[@class='pbBody']/descendant::div[@class='requiredInput'][1]//select");
	private final By SUB_TYPE_DD_LOC = By.xpath("//div[@class='pbBody']/descendant::table[@class='detailList'][1]/descendant::select[2]");
	private final By SAVE_BTN_LOC = By.xpath("//td[@id='bottomButtonRow']/input[@name='save']");
	private final By CASE_OWNER_NAME_LOC = By.xpath("//div[text()='Case Owner']/following::a[1]");
	private final By CHANGE_TYPE_AND_STATUS_LINK_LOC = By.xpath("//span[@class='iconContainer']/following::span[text()='Change Type and Status'][1]");
	private final By STATUS_DD_UNDER_CHANGE_TYPE_AND_STATUS_LOC = By.xpath("//label[contains(text(),'Status')]/following::select");
	private final By UPDATE_BTN_LOC = By.id("publishersharebutton");
	private final By CASE_STATUS_LOC = By.xpath("//div[text()='Status']/following::span[1]");
	private final By SUB_DD_LOC = By.xpath("//div[contains(@class,'primaryPalette')]//select");
	private final By TYPE_DETAIL_DD_LOC = By.xpath("//div[@class='pbBody']/descendant::table[@class='detailList'][1]/descendant::select[3]");
	private final By CHANGE_OWNER_BTN_LOC = By.xpath("//input[@value='Change Owner']");
	private final By OWNER_TYPE_TEXTBOX_LOC = By.id("newOwn");
	private final By SAVE_BUTTON_FOR_OWNER_TYPE_LOC = By.xpath("//input[@name='save']");
	private final By CASE_SAVED_TEXT_LOC = By.xpath("//span[text()='Case successfully saved.']/following::td[1]");
	private final By STATUS_TYPE_DD_LOC = By.xpath("//div[@class='pbBody']/descendant::table[@class='detailList'][1]/descendant::select[4]");
	private final By ESCALATE_BUTTON_LOC = By.id("customButtonMuttonLabel");
	private final By SEARCH_ICON_LOC = By.xpath("//img[@class='lookupIcon']");
	private final By SEARCH_TEXT_FIELD_LOC = By.id("lksrch");
	private final By GO_BTN_LOC = By.xpath("//input[@name='go']");
	private final By CASE_OWNER_NAME_IN_SEARCH_RESULT_LOC = By.xpath("//table[@class='list']//a");
	private final By DETAILS_LINK_LOC = By.xpath("//a[contains(@title,'Details')]//span[@class='iconFont']");
	private final By FEED_LINK_LOC = By.xpath("//a[contains(@title,'Feed')]//span[@class='iconFont']");


	private static String valueUnderServiceCloudDDLoc = "//div[@id='navigator-sbmenu']//span[text()='%s']";
	private static String valueUnderTypeDD = "//div[@class='pbBody']/descendant::div[@class='requiredInput'][1]//select//option[text()='%s']";
	private static String valueUnderSubTypeDD = "//div[@class='pbBody']/descendant::table[@class='detailList'][1]/descendant::select[2]//option[text()='%s']";
	private static String caseNumberInSearchResultLoc = "//a[text()='%s']";
	private static String statusTypeValueDDLoc = "//label[contains(text(),'Status')]/following::select/option[contains(text(),'%s')]";
	private static String valueUnderSubDDLoc = "//div[contains(@class,'primaryPalette')]//select//option[contains(text(),'%s')]";
	private static String caseNumberAccordingToStatus = "//form[contains(@id,'actionForm')]/descendant::div[contains(@id,'CASES_STATUS') and text()='%s'][%s]/preceding::div[contains(@id,'CASE_NUMBER')][1]//a";
	private static String statusTypeDDValueLoc = "//div[@class='pbBody']/descendant::table[@class='detailList'][1]/descendant::select[4]//option[contains(text(),'%s')]";
	private static String subTypeDDValueLoc = "//div[@class='pbBody']/descendant::table[@class='detailList'][1]/descendant::select[2]//option[%s]";
	private static String typeDetailDDFirstValueLoc = "//div[@class='pbBody']/descendant::table[@class='detailList'][1]/descendant::select[3]//option[%s]";
	private static String checkboxBasedOnStatusLoc = "//form[contains(@id,'actionForm')]/descendant::div[contains(@id,'CASES_STATUS') and text()='%s'][%s]/preceding::div[contains(@id,'CASE_NUMBER')][1]/preceding::input[@class='checkbox'][1]";
	private static String firstCaseNumberAccordingToStatus = "//form[contains(@id,'actionForm')]/descendant::div[contains(@id,'CASES_STATUS') and text()='%s'][1]/preceding::div[contains(@id,'CASE_NUMBER')][1]//a";
	private static String firstCheckboxBasedOnStatusLoc = "//form[contains(@id,'actionForm')]/descendant::div[contains(@id,'CASES_STATUS') and text()='%s'][1]/preceding::div[contains(@id,'CASE_NUMBER')][1]/preceding::input[@class='checkbox'][1]";

	static String  firstName = null;
	public CRMHomePage(RFWebsiteDriver driver) {
		super(driver);
	}

	public boolean verifyHomePage() throws InterruptedException{
		driver.switchTo().defaultContent();
		driver.waitForCRMLoadingImageToDisappear();
		driver.quickWaitForElementPresent(USER_NAVIGATION_LABEL_LOC);		
		closeAllOpenedTabs();
		return driver.isElementPresent(USER_NAVIGATION_LABEL_LOC);	
	}

	public void clickOnAccountNameForAccountDetailPageInAccountSection(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']//iframe[contains(@class,'x-border-panel')]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']//iframe[contains(@class,'x-border-panel')]")));
		driver.quickWaitForElementPresent(By.xpath("//div[@id='Account_body']//tr[@class='headerRow']//a[contains(text(),'Account Name')]/following::tr[1]/th/a"));
		driver.click(By.xpath("//div[@id='Account_body']//tr[@class='headerRow']//a[contains(text(),'Account Name')]/following::tr[1]/th/a"),"");
		driver.switchTo().defaultContent();	
	}	

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

	public String getNameOnFirstRowInSearchResults(){
		switchToExtFrame(1);
		return  driver.findElement(By.xpath("//div[@id='Account_body']//tr[@class='headerRow']//a[contains(text(),'Account Name')]/following::tr[1]/th/a")).getText();	
	}

	public boolean isNamePresentInAccountSection(String name){
		switchToExtFrame(1);
		List<WebElement> allNames = driver.findElements(By.xpath("//*[@id='Account_body']//tr[contains(@class,'dataRow')]//th[1]/a"));
		for(WebElement e: allNames){
			if(e.getText().toLowerCase().trim().contains(name.toLowerCase().trim())){
				return true;
			}			
		}
		return false;
	}

	public boolean isEmailPresentInAccountSection(String email){
		switchToExtFrame(1);
		List<WebElement> allEmails = driver.findElements(By.xpath("//*[@id='Account_body']//tr[contains(@class,'dataRow')]//td[9]/a"));
		for(WebElement e: allEmails){
			if(e.getText().toLowerCase().trim().contains(email.toLowerCase().trim())){
				return true;
			}			
		}
		return false;
	}

	public String getEmailOnFirstRowInSearchResults(){
		String emailOnfirstRow = null;
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		emailOnfirstRow = driver.findElement(By.xpath("//div[@id='Account_body']//tr[@class='headerRow']//a[contains(text(),'Email Address')]/following::tr[1]/td[9]/a")).getText();  
		return emailOnfirstRow;
	}

	public void clickNameOnFirstRowInSearchResults(){
		switchToExtFrame(1);
		driver.click(By.xpath("//div[@id='Account_body']//tr[@class='headerRow']//a[contains(text(),'Account Name')]/following::tr[1]/th/a"),"");
	}

	public void clickNameWithActiveStatusInSearchResults(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		driver.click(By.xpath("//img[@class='checkImg'][@title='Checked']/ancestor::tr[1]/th/a"),"");
		driver.switchTo().defaultContent();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public boolean isSearchResultHasActiveUser(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//img[@class='checkImg'][@title='Checked']/ancestor::tr[1]/th/a"));
	}

	public boolean isSearchResultHasActiveUser(String customer){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		driver.quickWaitForElementPresent(By.xpath("//div[@id='Account_body']//td[text()='"+customer+"']/following::td[text()='Active']/..//th//a"));
		return driver.isElementPresent(By.xpath("//div[@id='Account_body']//td[text()='"+customer+"']/following::td[text()='Active']/..//th//a"));		
	}

	public boolean isAccountSectionPresent(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//div[@id='Account_body']"));
	}

	public boolean isAccountLinkPresentInLeftNaviagation(){
		switchToExtFrame(1);
		return driver.isElementPresent(By.xpath("//a[starts-with(@title,'Accounts')]"));
	}

	public boolean isContactsLinkPresentInLeftNaviagation(){
		switchToExtFrame(1);
		return driver.isElementPresent(By.xpath("//a[starts-with(@title,'Contacts')]"));
	}

	public boolean isAccountActivitiesLinkPresentInLeftNaviagation(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		return driver.isElementPresent(By.xpath("//a[starts-with(@title,'Account Activities')]"));
	}

	public void clickContactOnFirstRowInSearchResults(){
		switchToExtFrame(1);
		driver.click(By.xpath("//div[@id='Contact_body']//tr[@class='headerRow']//a[contains(text(),'Account Name')]/following::tr[1]/th/a"),"");
		driver.switchTo().defaultContent();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public boolean isOrderOfDetailsPresentInListView(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']//iframe[contains(@class,'x-border-panel')]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']//iframe[contains(@class,'x-border-panel')]")));
		return driver.isElementPresent(By.xpath(".//*[@id='Account']/div[2]/div"));
	}

	public void clickConsultantCustomerNameInSearchResult(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		driver.click(By.xpath("//div[@id='Account_body']//tr[@class='headerRow']/following::tr/td[contains(text(),'Active')]/preceding-sibling::th/a"),"");
		driver.switchTo().defaultContent();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public void clickPreferredCustomerNameInSearchResult(){
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		driver.click(By.xpath("//div[@id='Account_body']//tr[@class='headerRow']/following::tr//td[Text()='Preferred Customer']/preceding-sibling::th"),"");
		driver.switchTo().defaultContent();
		driver.waitForCRMLoadingImageToDisappear();
	}

	public void clickAnyTypeOfActiveCustomerInSearchResult(String customer){
		switchToExtFrame(1);
		driver.waitForElementPresent(By.xpath("//div[@id='Account_body']/descendant::*[text()='"+customer+"'][1]/following::*[text()='Active'][1]/preceding::a[1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='Account_body']/descendant::*[text()='"+customer+"'][1]/following::*[text()='Active'][1]/preceding::a[1]"),"");
	}

	public int getCountOfShippingProfile(){
		//refreshPage();
		driver.switchTo().defaultContent();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		driver.quickWaitForElementPresent(By.xpath("//span[contains(text(),'Shipping Profiles')]/span[contains(text(),'[1]')]"));
		String count = driver.findElement(By.xpath("//span[contains(text(),'Shipping Profiles')]/span")).getText().split("\\[")[1].split("\\]")[0];
		logger.info("Count of Shipping Profile "+count);
		driver.switchTo().defaultContent();
		return Integer.parseInt(count);
	}

	public String getAccountNameOnFirstRowInSearchResultsOfMainPhoneNumber(){
		String nameOnfirstRow = null;
		//		driver.switchTo().defaultContent();
		//		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		nameOnfirstRow = driver.findElement(By.xpath("//div[@id='Contact_body']//tr[@class='headerRow']/following::tr[1]/td[2]/a")).getText();  
		return nameOnfirstRow;
	}

	public String getNameOnFirstRowInSearchResultsOfMainPhoneNumber(){
		String nameOnfirstRow = null;
		//		driver.switchTo().defaultContent();
		//		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		nameOnfirstRow = driver.findElement(By.xpath("//div[@id='Contact_body']//tr[@class='headerRow']/following::tr[1]/th/a")).getText();  
		return nameOnfirstRow;
	}

	public String getEmailOnFirstRowInSearchResultsOfMainPhoneNumber(){
		String emailOnfirstRow = null;
		//		driver.switchTo().defaultContent();
		//		driver.quickWaitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]"));
		//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[2]/descendant::iframe[1]")));
		emailOnfirstRow = driver.findElement(By.xpath("//div[@id='Contact_body']//tr[@class='headerRow']/following::tr[1]/td[5]/a")).getText();  
		return emailOnfirstRow;
	}

	public void selectValueFromServiceCloudDD(String value){
		int height=0;
		int width=0;
		driver.waitForPageLoad();
		driver.switchTo().defaultContent();
		WebElement serviceCloudDDLoc = driver.findElement(SERVICE_CLOUD_DD_LOC);
		height=serviceCloudDDLoc.getSize().getHeight();
		width=serviceCloudDDLoc.getSize().getWidth();
		Actions action=new Actions(RFWebsiteDriver.driver);
		action.moveToElement(serviceCloudDDLoc, width-15, height-15).click().build().perform();
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(valueUnderServiceCloudDDLoc, value)),"");
		driver.waitForElementPresent(IFRAME_LOC_1);
		try{
			driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
			driver.click(CANCEL_BTN_OF_EDIT_RECORD_POPUP_LOC,"Cancel button of edit records popup");
		}catch(Exception e){
			logger.info("No edit record popup present");
		}

	}

	public void clickNewCaseButton(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_1);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
		driver.click(NEW_CASE_BTN_LOC, "New case button");
	}

	public void fillTheDetailsAndClickSaveForNewCase(String type, String subTypeValue){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(TYPE_DD_LOC, "Type dropdown");
		driver.click(By.xpath(String.format(valueUnderTypeDD, type)),type);
		//select subtype value
		driver.click(SUB_TYPE_DD_LOC, "Subtype dropdown");
		driver.click(By.xpath(String.format(valueUnderSubTypeDD, subTypeValue)),subTypeValue+" value");
		//click save button
		driver.click(SAVE_BTN_LOC, "Save button");
	}

	public void selectTypeForNewCaseByValue(String type){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(TYPE_DD_LOC, "Type dropdown");
		driver.click(By.xpath(String.format(valueUnderTypeDD, type)),type);
	}

	public void selectSubTypeForNewCaseByIndex(String subType){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		//select subtype value
		driver.click(SUB_TYPE_DD_LOC, "Subtype dropdown");
		driver.click(By.xpath(String.format(subTypeDDValueLoc, subType)),"sub type selected");
	}

	public void clickSaveBtnForNewCase(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(SAVE_BTN_LOC, "Save button");
	}

	public String getCaseNumber(){
		driver.switchTo().defaultContent();
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		String caseNumber = driver.getText(By.xpath("//span[contains(text(),'Case Number')]/following-sibling::span"), "case number");
		return caseNumber;
	}

	public String getCaseNumberFromTab(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		String caseNumber = driver.getText(CASE_NUMBER_FROM_TAB_LOC, "case number").split("\\[")[0].trim();
		return caseNumber;
	}

	public void clickCaseNumberAfterSearch(String caseNumber){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(By.xpath(String.format(caseNumberInSearchResultLoc, caseNumber)),caseNumber);
		driver.waitForCRMLoadingImageToDisappear();
	}

	public String getCaseOwnerName(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_4);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_4));
		String caseOwnerName = driver.getText(CASE_OWNER_NAME_LOC,"owner name is");
		return caseOwnerName;
	}

	public void changeStatusTypeAndUpdate(String statusType){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.click(CHANGE_TYPE_AND_STATUS_LINK_LOC,"change type and status link loc");
		driver.click(STATUS_DD_UNDER_CHANGE_TYPE_AND_STATUS_LOC,"Status Link loc");
		driver.click(By.xpath(String.format(statusTypeValueDDLoc, statusType)),statusType);
		driver.click(UPDATE_BTN_LOC,"Update btn clicked");
		driver.waitForCRMLoadingImageToDisappear();
		driver.pauseExecutionFor(4000);
	}

	public String getStatusOfCase(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_4);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_4));
		String caseStatus = driver.getText(CASE_STATUS_LOC,"case status is");
		return caseStatus;
	}

	public void selectValueFromSubDD(String value){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_1);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
		driver.waitForElementPresent(SUB_DD_LOC);
		driver.clickByJS(RFWebsiteDriver.driver, SUB_DD_LOC, "service cloud dropdown");
		driver.click(By.xpath(String.format(valueUnderSubDDLoc, value)),value);
	}

	public void fillTheDetailsAndClickSaveForNewCase(String type, String subType, String typeDetail){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(TYPE_DD_LOC, "Type dropdown");
		driver.click(By.xpath(String.format(valueUnderTypeDD, type)),type);
		//select subtype value
		driver.click(SUB_TYPE_DD_LOC, "Subtype dropdown");
		driver.click(By.xpath(String.format(subTypeDDValueLoc, subType)),"sub type selected");
		//select type details.
		driver.click(TYPE_DETAIL_DD_LOC, "TypeDetail dropdown");
		driver.click(By.xpath(String.format(typeDetailDDFirstValueLoc, typeDetail)),"Type detail selected");
		//click save button
		driver.click(SAVE_BTN_LOC, "Save button");
	}


	public void fillTheDetailsAndClickSaveForNewCase(String type, String subType, String typeDetail , String statusType){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(TYPE_DD_LOC, "Type dropdown");
		driver.click(By.xpath(String.format(valueUnderTypeDD, type)),type);
		//select subtype value
		driver.click(SUB_TYPE_DD_LOC, "Subtype dropdown");
		driver.click(By.xpath(String.format(subTypeDDValueLoc, subType)),"sub type selected");
		//select type details.
		driver.click(TYPE_DETAIL_DD_LOC, "TypeDetail dropdown");
		driver.click(By.xpath(String.format(typeDetailDDFirstValueLoc, typeDetail)),"Type detail selected");
		//select status
		driver.click(STATUS_TYPE_DD_LOC, "status type dropdown");
		driver.click(By.xpath(String.format(statusTypeDDValueLoc, statusType)),"status type selected");
		//click save button
		driver.click(SAVE_BTN_LOC, "Save button");
	}

	public void selectStatusForNewCaseByValue(String statusType){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		//select subtype value
		driver.click(STATUS_TYPE_DD_LOC, "status type dropdown");
		driver.click(By.xpath(String.format(statusTypeDDValueLoc, statusType)),"status type selected");
	}

	public String changeCaseOwner(String statusType,String caseNumberInSearchResult, String ownerName){
		String caseNumber = null;
		driver.switchTo().defaultContent();
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(IFRAME_LOC_1);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
		caseNumber = driver.getText(By.xpath(String.format(caseNumberAccordingToStatus, statusType, caseNumberInSearchResult)),"case number status as open");
		//Select checkbox based on status type for row record.
		driver.click(By.xpath(String.format(checkboxBasedOnStatusLoc, statusType, caseNumberInSearchResult)),"checkbox for status"+statusType);
		//Click change owner from sub menu.
		driver.click(CHANGE_OWNER_BTN_LOC, "change owner");
		driver.waitForPageLoad();
		//Type owner type.
		driver.type(OWNER_TYPE_TEXTBOX_LOC, ownerName, "ownerTypeTextBox");
		driver.pauseExecutionFor(5000);
		//Click save button after entering owner type.
		driver.click(SAVE_BUTTON_FOR_OWNER_TYPE_LOC, "Save button");
		driver.pauseExecutionFor(5000);
		driver.waitForPageLoad();
		return caseNumber;
	}

	public String getCaseSavedText(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		String savedCaseText = driver.getText(CASE_SAVED_TEXT_LOC,"Saved Case text is");
		return savedCaseText;
	}

	public void setNumberOfRecordsPerPageFromDD(String noOfRecords){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_1);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
		driver.click(By.xpath("//div[@class='bottomNav']/descendant::img[@class='selectArrow'][1]"),"");
		driver.click(By.xpath(String.format("//td[contains(text(),'%s')]", noOfRecords)),"");
		driver.waitForPageLoad();
	}

	public String changeCaseOwneAsPerStatus(String statusType, String ownerName){
		String caseNumber = null;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_1);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
		while(true){
			if(driver.isElementPresent(By.xpath(String.format(firstCaseNumberAccordingToStatus, statusType)))){
				caseNumber = driver.getText(By.xpath(String.format(firstCaseNumberAccordingToStatus, statusType)),"case number status as open");
				driver.click(By.xpath(String.format(firstCheckboxBasedOnStatusLoc, statusType)),"checkbox for status"+statusType);
				//Click change owner from sub menu.
				driver.click(CHANGE_OWNER_BTN_LOC, "change owner");
				driver.waitForPageLoad();
				//Type owner type.
				driver.type(OWNER_TYPE_TEXTBOX_LOC, ownerName, "ownerTypeTextBox");
				driver.pauseExecutionFor(5000);
				//Click save button after entering owner type.
				driver.click(SAVE_BUTTON_FOR_OWNER_TYPE_LOC, "Save button");
				driver.pauseExecutionFor(5000);
				driver.waitForPageLoad();
				break;
			}else{
				driver.click(By.xpath("//a[contains(text(),'Next')]"),"Next button clicked");
				driver.waitForCRMLoadingImageToDisappear();
			}
		}

		return caseNumber;
	}

	public boolean isCreatedCasePresentInSearchResult(String caseNumber){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		return driver.isElementPresent(By.xpath(String.format(caseNumberInSearchResultLoc, caseNumber)));
	}

	public void clickCancelButtonOnEditRecordPopup(){
		try{
			driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
			driver.click(CANCEL_BTN_OF_EDIT_RECORD_POPUP_LOC,"Cancel button of edit records popup");
		}catch(Exception e){
			logger.info("No edit record popup present");
		}
	}

	public String clickEscalateCaseAndAcceptAlert(){
		String message = null;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(ESCALATE_BUTTON_LOC,"Escalate button");
		try{
			driver.pauseExecutionFor(3000);
			Alert alert = driver.switchTo().alert();
			message = alert.getText().toLowerCase();
			logger.info("Alert text is"+message);
			alert.accept();
			logger.info("Ok button of java Script popup is clicked.");
			driver.waitForPageLoad();
			driver.pauseExecutionFor(5000);
		}catch(Exception e){
			logger.info("No Alert is present");
		}
		return message;
	}

	public String clickEscalateCaseAndAcceptAlertForNewCase(){
		String message = null;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(ESCALATE_BUTTON_LOC,"Escalate button");
		try{
			driver.pauseExecutionFor(5000);
			Alert alert = driver.switchTo().alert();
			message = alert.getText().toLowerCase();
			logger.info("Alert text is"+message);
			alert.accept();
			logger.info("Ok button of java Script popup is clicked.");
			driver.waitForPageLoad();
			driver.pauseExecutionFor(3000);
		}catch(Exception e){
			logger.info("No Alert is present");
		}
		return message;
	}

	public void clickUpdateBtnUnderChangeTypeAndStatus(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.click(UPDATE_BTN_LOC,"Update btn clicked");
	}

	public String getConsultantStoreFrontInfo(String label){
		driver.waitForElementPresent(By.id(label));
		return driver.findElement(By.id(label)).getAttribute("value").toLowerCase().trim();
	}

	public void clickDetailsViewLink(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.waitForElementPresent(DETAILS_LINK_LOC);
		driver.click(DETAILS_LINK_LOC,"Details link clicked");
	}

	public void clickDetailsViewLinkAfterSearch(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.waitForElementPresent(DETAILS_LINK_LOC);
		driver.click(DETAILS_LINK_LOC,"Details link clicked");
	}

	public void clickFeedViewLinkAfterSearch(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.waitForElementPresent(FEED_LINK_LOC);
		driver.click(FEED_LINK_LOC,"Feed link clicked after searched");
	}

	public void clickFeedViewLink(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.waitForElementPresent(FEED_LINK_LOC);
		driver.click(FEED_LINK_LOC,"Feed link clicked after searched");
	}

	public String clickAndReturnCaseNumber(String statusType){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_1);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
		String caseNumber = driver.getText(By.xpath(String.format("//form[contains(@id,'actionForm')]/descendant::div[contains(@id,'CASES_STATUS') and text()='%s'][1]/preceding::div[contains(@class,'CASE_NUMBER')][1]//a", statusType)));
		driver.click(By.xpath(String.format("//form[contains(@id,'actionForm')]/descendant::div[contains(@id,'CASES_STATUS') and text()='%s'][1]/preceding::div[contains(@class,'CASE_NUMBER')][1]//a", statusType)),"");
		driver.waitForCRMLoadingImageToDisappear();
		return caseNumber;
	}

	public void changeCaseOwnerFromDetailsView(String ownerName){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(By.xpath("//div[@class='efdFields']/descendant::a[contains(text(),'[Change]')][1]"),"Change link clicked");
		//Type owner type.
		driver.type(OWNER_TYPE_TEXTBOX_LOC, ownerName, "ownerTypeTextBox");
		//Click save button after entering owner type.
		driver.click(SAVE_BUTTON_FOR_OWNER_TYPE_LOC, "Save button");
		driver.pauseExecutionFor(5000);
		driver.waitForPageLoad();
	}

	public String clickAndReturnCaseNumberInList(String statusType){
		String caseNumber = null;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_1);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
		while(true){
			if(driver.isElementPresent(By.xpath(String.format(firstCaseNumberAccordingToStatus, statusType)))){
				caseNumber = driver.getText(By.xpath(String.format("//form[contains(@id,'actionForm')]/descendant::div[contains(@id,'CASES_STATUS') and text()='%s'][1]/preceding::div[contains(@class,'CASE_NUMBER')][1]//a", statusType)));
				driver.click(By.xpath(String.format("//form[contains(@id,'actionForm')]/descendant::div[contains(@id,'CASES_STATUS') and text()='%s'][1]/preceding::div[contains(@class,'CASE_NUMBER')][1]//a", statusType)),"");
				driver.waitForPageLoad();
				break;
			}else{
				driver.click(By.xpath("//a[contains(text(),'Next')]"),"Next button clicked");
				driver.waitForCRMLoadingImageToDisappear();
			}
		}
		return caseNumber;

	}



	public void changeCaseOwnerThroughSearchIconFromDetailsView(String ownerName){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.click(By.xpath("//div[@class='efdFields']/descendant::a[contains(text(),'[Change]')][1]"),"Change link clicked");
		//Change window
		driver.click(SEARCH_ICON_LOC,"Search icon");
		driver.waitForPageLoad();
		driver.switchTo().defaultContent();
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles){
			if(!windowHandle.equals(parentWindow)){
				driver.switchTo().window(windowHandle);
			}
		}
		driver.waitForElementPresent(By.id("searchFrame"));
		driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
		driver.type(SEARCH_TEXT_FIELD_LOC, ownerName,ownerName+"entered");
		driver.click(GO_BTN_LOC,"");
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.id("resultsFrame"));
		driver.switchTo().frame(driver.findElement(By.id("resultsFrame")));
		driver.click(CASE_OWNER_NAME_IN_SEARCH_RESULT_LOC,"Case owner name in search result");
		//cntrl to parent window
		driver.switchTo().window(parentWindow);
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_1);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_1));
		driver.click(SAVE_BUTTON_FOR_OWNER_TYPE_LOC, "Save button");
		driver.pauseExecutionFor(5000);
		driver.waitForPageLoad();
	}


	public void changeCaseOwnerThroughSearchIconFromDetailsViewAfterSearch(String ownerName){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.click(By.xpath("//div[@class='efdFields']/descendant::a[contains(text(),'[Change]')][1]"),"Change link clicked");
		//Change window
		driver.click(SEARCH_ICON_LOC,"Search icon");
		driver.waitForPageLoad();
		driver.switchTo().defaultContent();
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles){
			if(!windowHandle.equals(parentWindow)){
				driver.switchTo().window(windowHandle);
			}
		}
		driver.waitForElementPresent(By.id("searchFrame"));
		driver.switchTo().frame(driver.findElement(By.id("searchFrame")));
		driver.type(SEARCH_TEXT_FIELD_LOC, ownerName,ownerName+"entered");
		driver.click(GO_BTN_LOC,"");
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.id("resultsFrame"));
		driver.switchTo().frame(driver.findElement(By.id("resultsFrame")));
		driver.click(CASE_OWNER_NAME_IN_SEARCH_RESULT_LOC,"Case owner name in search result");
		//cntrl to parent window
		driver.switchTo().window(parentWindow);
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.click(SAVE_BUTTON_FOR_OWNER_TYPE_LOC, "Save button");
		driver.pauseExecutionFor(5000);
		driver.waitForPageLoad();
	}

	public void clickEditButtonOnDetailsView(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(By.xpath("//td[@id='topButtonRow']//input[contains(@value,'Edit')]"),"Edit button");
	}

	public void changeCaseOwnerFromDetailsViewAfterSearch(String ownerName){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.click(By.xpath("//div[@class='efdFields']/descendant::a[contains(text(),'[Change]')][1]"),"Change link clicked");
		//Type owner type.
		driver.type(OWNER_TYPE_TEXTBOX_LOC, ownerName, "ownerTypeTextBox");
		//Click save button after entering owner type.
		driver.click(SAVE_BUTTON_FOR_OWNER_TYPE_LOC, "Save button");
		driver.pauseExecutionFor(5000);
		driver.waitForPageLoad();
	}

	public String clickEscalateCaseAndAcceptAlertFromDetailsView(){
		String message = null;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(By.xpath("//td[@id='topButtonRow']//input[contains(@value,'Escalate')]"),"Escalate button");
		try{
			driver.pauseExecutionFor(5000);
			Alert alert = driver.switchTo().alert();
			message = alert.getText().toLowerCase();
			logger.info("Alert text is"+message);
			alert.accept();
			logger.info("Ok button of java Script popup is clicked.");
			driver.waitForPageLoad();
		}catch(Exception e){
			logger.info("No Alert is present");
		}
		return message;
	}

	public String clickEscalateCaseAndAcceptAlertFromDetailsViewAfterSearch(){
		String message = null;
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.click(By.xpath("//td[@id='topButtonRow']//input[contains(@value,'Escalate')]"),"Escalate button");
		try{
			driver.pauseExecutionFor(3000);
			Alert alert = driver.switchTo().alert();
			message = alert.getText().toLowerCase();
			logger.info("Alert text is"+message);
			alert.accept();
			logger.info("Ok button of java Script popup is clicked.");
			driver.waitForPageLoad();
		}catch(Exception e){
			logger.info("No Alert is present");
		}
		return message;
	}

	public void clickCaseResolvedButtonOnDetailsViewAfterSearch(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_3);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_3));
		driver.click(By.xpath("//td[@id='topButtonRow']//input[@value='Case Resolved']"),"Edit button");
		driver.waitForPageLoad();
	}

	public String getCaseType(){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		return driver.getText(By.xpath("//td[text()='Type']/following::td[1]"), "Case type");
	}

	public void selectFirstValueFromDD(String labelName){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(By.xpath(String.format("//label[text()='%s']/following::select[1]", labelName)),labelName+" DD ");
		driver.click(By.xpath(String.format("//label[text()='%s']/following::select[1]//option[2]", labelName)),labelName+" DD "+" selected");
	}

	public void clickCloseCaseAndMakeStatusAsResolved(String labelName, String value){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(IFRAME_LOC_2);
		driver.switchTo().frame(driver.findElement(IFRAME_LOC_2));
		driver.click(By.xpath("//td[@id='topButtonRow']//input[contains(@value,'Close Case')]"),"Escalate button");
		driver.click(By.xpath(String.format("//label[text()='%s']/following::select[1]", labelName)),labelName+" DD ");
		driver.click(By.xpath(String.format("//label[text()='%s']/following::select[1]//option[contains(text(),'%s')]", labelName, value)),labelName+" DD "+" selected value as"+value);
		driver.waitForPageLoad();
	}

}
