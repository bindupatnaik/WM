package com.rf.pages.website.nscore;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;

public class NSCore4SitesTabPage extends NSCore4RFWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(NSCore4SitesTabPage.class.getName());

	public NSCore4SitesTabPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static String nscorporateSubLink = "//h3[contains(text(),'nsCorporate')]/following::li[1]//*[contains(text(),'%s')]";
	private static String waitingForApprovedLink = "//div[@class='ContentBox']/div/div/p[text()='%s']/../../following-sibling::p/a[text()='Approve']";
	private static String approvedDisappearLink = "//div[@class='ContentBox' and @style='display: none;']/div/div/p[text()='%s']/../../following-sibling::p/a[text()='Approve']";
	private static String denyLinkForStory = "//div[@class='ContentBox']/div/div/p[text()='%s']/../../following-sibling::p/a[text()='Deny']";
	private static String eventNameAtCalendar = "//div[@id='calendar']//span[text()='%s']";
	private static String corporateSubLink = "//h3[contains(text(),'nsCorporate')]/following::li[1]//span[text()='%s']";
	private static String pageSizeDDoption = "//select[@id='pageSize']/option[%s]";
	private static String titleNameLinkLoc = "//a[text()='%s']";
	private static String titleStatus = "//a[text()='%s']/following::td[3]";
	private static String templateDropdownValue = "//select[@id='parentId']/option[contains(text(),'%s')]";
	private static String linkTextNameInSiteMap = "//div[@id='tree']//a[text()='%s']";
	private static String pageDDOptionForSiteMap = "//select[@id='sExistingPages']/option[contains(text(),'%s')]";
	private static String nameChkBoxInSiteList = "//a[text()='%s']/preceding::input[1]";
	private static String resoursceStatus = "//a[text()='%s']/following::td[4]";
	private static String selectCategoryDDOptionForFilter  = "//select[@id='categoryFilter']//option[contains(text(),'%s')]";
	private static String selectStatusDDOptionForFilter  = "//select[@id='statusFilter']//option[contains(text(),'%s')]";
	private static String selectCategoryDDOptionForMove  = "//select[@id='categorySelection']//option[contains(text(),'%s')]";
	private static String resoursceCategory = "//a[text()='%s']/following::td[3]/a";
	private static String categoryNameToAssert = "//a[contains(text(),'%s')]";
	private static String  selectCategoryDDOptionForUploadResource  = "//select[@id='category']//option[contains(text(),'%s')]";
	private static String calenderEventYearValue = "//select[@id='fc-year']/option[text()='%s']";
	private static String pageNameToAssert = "//a[contains(text(),'%s')]";
	private static String titleNameChkBox = "//a[contains(text(),'%s')]/preceding::input[1]";
	private String linkInLeftNavloc = "//ul[@class='SectionLinks']//span[contains(text(),'%s')]/ancestor::a[1]";
	private String linkUnderBasePWSLoc = "//b[contains(text(),'Base PWS')]/following::span[contains(text(),'%s')]/ancestor::a[1]";
	private String prevMonthLoc = "//select[@class='ui-datepicker-month']/option[contains(text(),'%s')]/preceding-sibling::option[1]";
	private String nextMonthLoc = "//select[@class='ui-datepicker-month']/option[contains(text(),'%s')]/following-sibling::option[1]";
	private String calenderEventMonthValue = "//select[@id='fc-month']/option[contains(text(),'%s')]";
	private String SiteTabLinksLoc="//ul[@id='SubNav']//a[contains(text(),'%s')]";
	private static String distributorSubLink = "//h3[contains(text(),'nsDistributor')]/following::li[1]//*[contains(text(),'%s')]";
	private static String linkTextLoc   = "//*[contains(text(),'%s')]";
	private static String headerLinkLoc = "//a[text()='%s']";
	private static String subLinkUnderHeaderLinkLoc = "//a[text()='nsCorporate']/following::a[contains(text(),'%s')][1]";
	private static String plusSymbolOfLinkLoc   = "//a[text()='%s']/following::a[1]";

	private static final By TXT_FIELD_FOR_PWS_LOC  = By.xpath("//body[contains(@class,'cke_editable')]/p | //body[contains(@class,'cke_editable')]/div[1]");
	private static final By NEXT_MONTH_YEAR_LOC  = By.xpath("//*[@id='ui-datepicker-div']//following::span[text()='Next']");
	private static final By ABOUT_RF_COM_LOC  = By.xpath("//span[contains(text(),'ABOUT R+F')]");
	private static final By PREVIEW_BUTTON_LOC=By.id("preview");
	private static final By SWITCH_BACK_TO_EDIT_MODE_LINK_LOC=By.xpath("//a[contains(text(),'Switch back to Edit Mode')]");
	private static final By EXPAND_SYMBOL_OF_ABOUT_RF_IN_TREE_FOR_BASE_PWS  = By.xpath("//a[text()='ABOUT R+F']/ancestor::li[1] | //a[text()='About R+F']/ancestor::li[1]");
	private static final By SITE_DETAILS_SAVED_SUCCESSFULLY_TXT_LOC = By.xpath("//div[@class='SectionHeader']/following::div[1]/div | //td[@class='CoreContent']/div[1]/div");
	private static final By SITE_PAGE_SAVED_SUCCESSFULLY_TXT_LOC = By.xpath("//div[@class='SectionHeader']/following::div[2]/div/div | div[@class='SectionHeader']/following::div[1]/div/div");
	private static final By ADD_RESOURCE_LINK  = By.xpath("//a[text()='Add Resource']");
	private static final By ADD_CATEGORY_LINK  = By.xpath("//a[contains(text(),'Add Category')]");
	private static final By CATEGORY_NAME  = By.id("title");
	private static final By SELECT_CATEGORY_DD_FOR_Filter  = By.id("categoryFilter");
	private static final By SELECT_STATUS_DD_FOR_Filter  = By.id("statusFilter");
	private static final By SEARCH_TERMS_TXT_BOX_LOC  = By.id("txtSearchTerms");
	private static final By GO_BTN_LOC  = By.id("btnSearchResources");
	private static final By SELECT_CATEGORY_DD_FOR_MOVE  = By.id("categorySelection");
	private static final By MOVE_SELECTED_TO_CATEGORY_LOC  = By.id("btnMoveToCategory");
	private static final By MANAGE_RESOURCE_CATEGORIES_LINK  = By.xpath("//div[@class='SectionHeader']//a[contains(text(),'Manage')]");
	private static final By GO_BTN_FOR_MANAGE_RESOURCE_LOC  = By.id("btnSearchCategories");
	private static final By DELETE_SELECTED_LINK_FOR_MANAGE_RESOURCE  = By.id("btnDeleteSelected");
	private static final By GET_MESSAGE_OF_DELETE_SELECTED  = By.xpath("//div[@id='errorCenter']/div");
	private static final By ADD_NEW_SITE_LINK  = By.id("btnAddLink");
	private static final By LINK_TEXT_BOX  = By.id("txtLinkText");
	private static final By PAGES_DD_FOR_SITE_MAP = By.id("sExistingPages");
	private static final By SITE_MAP_SAVE_BUTTON  = By.id("btnSave");
	private static final By EXPAND_SYMBOL_OF_ABOUT_RF_IN_TREE  = By.xpath("//a[text()='About R+F']/ancestor::li[1]");
	private static final By EXPAND_SYMBOL_OF_WHO_WE_ARE_IN_TREE  = By.xpath("//a[text()='Who We Are']/ancestor::li[1]");
	private static final By EXPAND_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE  = By.xpath("//a[text()='Meet Dr. Fields']/ancestor::li[1]");
	private static final By PLUS_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE  = By.xpath("//a[text()='Meet Dr. Fields']/following::a[1]");
	private static final By ACTIVATE_LINK_FOR_SITE_MAP  = By.id("btnActivate");
	private static final By CLEAR_AND_RELOAD_PRODUCT_CONFIRMATION_MESSAGE  = By.xpath("//div[@id='messageCenterModal'][contains(@style,'display: block')]//div[@id='messageCenter']/div");
	private static final By DEACTIVATE_LINK_FOR_SITE_MAP  = By.id("btnDeactivate");
	private static final By ACTIVATE_LINK_PRESENT  = By.xpath(".//*[@id='btnActivate'][contains(@style,'inline')]");
	private static final By ADD_NEW_PAGE_LINK_LOC  = By.xpath("//div[@class='SectionHeader']/a[text()='Add a new page']");
	private static final By PAGE_NAME_LOC  = By.id("pageName");
	private static final By PAGE_TITLE_LOC  = By.xpath("//input[@name='pageTitle']");
	private static final By PAGE_DESCRIPTION_LOC  = By.xpath("//input[@name='pageDesc']");
	private static final By PAGE_URL_LOC  = By.xpath("//input[@name='pageUrl']");
	private static final By ACTIVE_CHECKBOX_LOC  = By.xpath("//input[@type='checkbox']");
	private static final By PAGE_KEYWORD_LOC  = By.xpath("//input[@name='keywords']");
	private static final By TEMPLATE_DROPDOWN_LOC  = By.id("parentId");
	private static final By SAVE_BUTTON_FOR_PAGE_DETAILS  = By.id("btnSavePage");
	private static final By EXPAND_SYMBOL_OF_MEET_THE_DOCTOR_IN_TREE_FOR_BASE_PWS   = By.xpath("//a[text()='Meet the Doctors']/ancestor::li[1]");
	private static final By EXPAND_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE_FOR_BASE_PWS   = By.xpath("//a[text()='Meet Dr. Fields']/ancestor::li[1]");
	private static final By PLUS_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE_FOR_BASE_PWS   = By.xpath("//a[text()='Meet Dr. Fields']/following::a[1]");
	private static final By CORPORATE_LINK_LOC  = By.xpath("//b[contains(text(),'Corporate')]");
	private static final By ADD_EVENT_LINK_LOC  = By.xpath("//a[text()='Add Event']");
	private static final By SUBJECT_TXT_BOX_LOC  = By.id("subject");
	private static final By EVENT_SAVE_BUTTON  = By.xpath("//a[@class='Button BigBlue']");
	private static final By EVENT_SAVED_SUCCESSFULLY_TXT_LOC = By.xpath("//div[@class='SectionHeader']/following::div[1]/div");
	private static final By EVENT_DELETE_BUTTON  = By.id("cmdDelete");
	private static final By ADD_NEWS_LINK_LOC  = By.xpath("//a[text()='Add News']");
	private static final By PAGE_SIZE_DD  = By.id("pageSize");
	private static final By PAGE_SIZE_DD_OPTIONS  = By.xpath("//select[@id='pageSize']/option");
	private static final By DEACTIVED_IS_ACTIVE_CHK_BOX_FOR_NEWS_TITLE  = By.xpath("//input[@id='isActive'][@value='False']");
	private static final By IS_ACTIVE_CHK_BOX_FOR_NEWS_TITLE  = By.xpath("//input[@id='isActive']/preceding::input[1]");
	private static final By DEACTIVATE_SELECTED_LINK  = By.id("btnDeactivate");
	private static final By ACTIVATE_SELECTED_LINK  = By.id("btnActivate");
	private static final By DELETE_SELECTED_LINK  = By.id("btnDelete");
	private static final By TITLE_TXT_BOX_LOC  = By.id("title");
	private static final By SITE_NAME_TXT_BOX  = By.id("siteName");
	private static final By DEACTIVED_CHK_BOX_FOR_SITE_DETAILS  = By.xpath("//input[@id='active'][@value='False']");
	private static final By ACTIVE_CHK_BOX_FOR_SITE_DETAILS  = By.xpath("//input[@id='active']/preceding::input[1]");
	private static final By SITE_SAVE_BUTTON  = By.id("btnSaveSite");
	private static final By REPLICATED_SITES_HEADER = By.xpath("//div[@class='SectionHeader']//h2[contains(text(),'Replicated Sites')]");
	private static final By PWS_CONTENT_REVIEW_LINK = By.xpath("//div[contains(@class,'nsCorporate')]/div/ul/li[1]//li/a/span[text()='PWS Content Review']");
	private static final By DENY_REASON_TXT_BOX = By.id("denyReason");
	private static final By SUBMIT_BTN_OF_DENY_REASON = By.id("submitDeny");
	private static final By UPLOAD_RESOURCE_LINK  = By.xpath("//a[text()='Upload Resource']");
	private static final By NAME_FOR_UPLOAD_RESOURCE  = By.id("name");
	private static final By FILEPATH_FOR_UPLOAD_RESOURCE  = By.id("filePath");
	private static final By SELECT_CATEGORY_DD_FOR_UPLOAD_RESOURCE  = By.id("category");
	private static final By SELECT_CATEGORY_DD_OPTION_FOR_UPLOAD_RESOURCE  = By.xpath("//select[@id='category']/option[3]");
	private static final By DEACTIVATE_SELECTED_LINK_FOR_UPLOAD_RESOURCE  = By.id("btnDeactivateSelected");
	private static final By CORPORATE_LINK_ON_NSCORPORATE_DD_LOC=By.xpath(".//div[@class='DropDown']//a[contains(text(),'Corporate')]");
	private static final By ACTIVATE_SELECTED_LINK_FOR_UPLOAD_RESOURCE  = By.id("btnActivateSelected");
	private static final By ADD_NEW_EVENT_LINK_ON_CALENDER_LOC  = By.xpath("//div[@class='SectionHeader']/a[contains(text(),'Add a New Event')]");
	private static final By CALENDER_EVENT_MONTH_DROPDOWN  = By.id("fc-month");
	private static final By CALENDER_EVENT_YEAR_DROPDOWN  = By.id("fc-year");
	private static final By NEW_CALENDER_EVENT_START_DATE  = By.id("startDate");
	private static final By NEW_CALENDER_EVENT_END_DATE  = By.id("endDate");
	private static final By TODAY_BUTTON_OF_CALENDER  = By.xpath("//table[@class='fc-header']//td[@class='fc-header-right']//span[text()='today']");
	private static final By PREVIOUS_TAB_BUTTON_ON_CALENDER  = By.xpath("//td[@class='fc-header-right']//div[contains(@class,'fc-button-prev')]//span");
	private static final By NEXT_TAB_BUTTON_ON_CALENDER  = By.xpath("//td[@class='fc-header-right']//div[contains(@class,'fc-button-next')]//span");
	private static final By EVENT_NAME_FROM_EDIT_EVENT_DETAILS_PAGE  = By.xpath("//div[@class='SectionHeader']/h2[contains(text(),'Edit Event')]/following::tr/td/input[@id='subject']");
	private static final By PAGE_DESCRIPTION_FOR_RESOURCE_LOC  = By.xpath("//td[contains(text(),'Description')]/following::textarea[1]");
	private static final By CAPTION_LOC  = By.xpath("//td[contains(text(),'Caption')]/following::textarea[1]");
	private static final By BASE_PWS_LINK_LOC  = By.xpath("//b[contains(text(),'Base PWS')]");
	private static final By YOU_ARE_IN_EDIT_MODE_LINK_LOC  = By.id("editmodelinks");
	private static final By MANAGE_BTN_LOC  = By.xpath("//a[contains(text(),'Manage')]");
	private static final By EDIT_BTN_UNDER_MANAGE_LINK_LOC  = By.xpath("//a[text()='Edit']");
	private static final By EDIT_MODE_PARAGRAPH_LOC  = By.xpath("//a[text()='Edit']");
	private static final By ACTIVE_STATUS_LOC = By.xpath("//input[@id='active']");
	private static final By ACTIVE_STATUS_CHECKBOX_LOC = By.xpath("//input[@id='active']/preceding::input[@type='checkbox'][1]");
	private static final By SITE_SELECTED_LANGUAGE_LOC = By.xpath("//select[@name='languageId']/option[@selected]");
	private static final By SITE_LANGUAGE_DD_LOC = By.xpath("//select[@name='languageId']");
	private static final By SITE_NAME_IN_LEFT_NAV_lOC = By.xpath("//div[@class='Content']/h2");
	private static final By SITE_DESC_IN_LEFT_NAV_LOC = By.xpath("//div[@class='Content']/p");
	private static final By STATUS_IN_LEFT_NAV_LOC = By.xpath("//td[contains(text(),'Status')]/following-sibling::td[1]");
	private static final By STATUS_FILTER_DD_LOC = By.xpath("//select[@id='statusFilter']");
	private static final By ALL_SITES_STATUS_LOC = By.xpath("//table[@id='sites']//a[@class='editSite']/ancestor::td[1]/following-sibling::td[3]");
	private static final By FIRST_SITE_LINK_LOC = By.xpath("//table[@id='sites']/descendant::a[@class='editSite'][1]");
	private static final By BAS_PWS_LINK_LOC = By.xpath("//b[contains(text(),'Base PWS')]/ancestor::a[1]");
	private static final By EVENT_DETAILS_CANCEL_BTN_LOC = By.xpath("//span[contains(text(),'Cancel')]/ancestor::a[1]");
	private static final By EVENT_MONTH_DD_LOC = By.xpath("//select[@class='ui-datepicker-month']");
	private static final By EVENT_YY_DD_LOC = By.xpath("//select[@class='ui-datepicker-year']");
	private static final By FIRST_DATE_FROM_CALENDAR_LOC = By.xpath("//table[@class='ui-datepicker-calendar']/tbody/descendant::a[1]");
	private static final By SELECTED_MONTH_IN_DD_LOC = By.xpath("//select[@class='ui-datepicker-month']/option[@selected]");
	private static final By NAVIGATION_CACHE_ALL_CHK_BOX_LOC  = By.id("chkNavigationAll");
	private static final By RELOAD_BTN_LOC  = By.xpath("//span[text()='Reload']");
	private static final By SITE_PRODUCT_CACHE_ALL_CHK_BOX_LOC  = By.id("chkSitesAll");
	private static final By ABOUT_RF_LOC  = By.xpath("//span[contains(text(),'About R+F')]");
	private static final By EXPAND_SYMBOL_OF_WHO_WE_ARE_IN_TREE_FOR_BASE_PWS   = By.xpath("//a[text()='Who We Are']/ancestor::li[1]");
	private static final By MANAGE_LINK_CORPORATE_SITE_LOC = By.xpath("//div[@class='EditMenu']//a[text()='Manage']");
	private static final By EDIT_TEXT_UNDER_MANAGE_LOC = By.xpath("//div[@class='EditMenu']//a[text()='Edit']");
	private static final By EDIT_TXT_BOX_LOC  = By.xpath("//div[@class='center-block']/a");
	private static final By SAVE_PREVIEW_BUTTON_LOC  = By.id("submit");
	private static final By SWITCH_TO_LIVE_MODE_LOC  = By.xpath("//a[text()='Switch to Live Mode']");
	private static final By LOAD_SITE_IN_EDIT_MODE_LINK_LOC = By.xpath("//a[contains(text(),'Load Site in Edit Mode')]");
	private static final By TXT_FIELD_LOC  = By.xpath("//body/div[1]/descendant::a[1]");
	private static final By TXT_FIELD_FOR_CORP_LOC  = By.xpath("//div[@class='mkc-container']/descendant::div[@class='mkc-description'][1]//p | //div[@class='mkc-container']/descendant::div[@class='mkc-description'][1]//div");
	private static final By TXT_FIELD_FOR_PWS_LIVE_SITE_LOC = By.xpath("//*[@id='CallBoxes']/p | //*[@id='CallBoxes']/div");
	private static final By PREV_BUTTON_FROM_CALENDAR_LOC=By.xpath("//div[@id='ui-datepicker-div']//span[contains(text(),'Prev')]");
	private static final By RENEW_LATER_LINK = By.xpath("//a[@id='renewLater']");
	
	Map<String,String> initialSiteProperties = null;

	public void clickPWSContentReviewLinkUnderNSCorporate(){
		driver.quickWaitForElementPresent(PWS_CONTENT_REVIEW_LINK);
		driver.click(PWS_CONTENT_REVIEW_LINK,"");
		logger.info("pws content review link clicked under nscorporate");
		driver.waitForPageLoad();
	}

	public boolean verifyNewStoryWaitingForApprovedLink(String story){
		driver.waitForElementPresent(By.xpath(String.format(waitingForApprovedLink, story)));
		return driver.isElementPresent(By.xpath(String.format(waitingForApprovedLink, story)));
	}

	public void clickApproveLinkForNewStory(String story){
		driver.quickWaitForElementPresent(By.xpath(String.format(waitingForApprovedLink, story)));
		driver.click(By.xpath(String.format(waitingForApprovedLink, story)),"");
		logger.info("Tab clicked is: "+story);
		driver.waitForPageLoad();
	}

	public boolean verifyApproveRequestDisappearFromUIOnceStoryApproved(String story){
		return driver.isElementPresent(By.xpath(String.format(approvedDisappearLink, story)));  
	}

	public void clickDenyLinkForNewStory(String story){
		driver.quickWaitForElementPresent(By.xpath(String.format(denyLinkForStory, story)));
		driver.click(By.xpath(String.format(denyLinkForStory, story)),"");
		logger.info("Tab clicked is: "+story);
		driver.waitForPageLoad();
	}

	public void enterDenyReasonAndClickSubmit(String denyReason) {
		driver.quickWaitForElementPresent(DENY_REASON_TXT_BOX);
		driver.type(DENY_REASON_TXT_BOX,denyReason);
		driver.click(SUBMIT_BTN_OF_DENY_REASON,"");
		logger.info("After entering deny story reason submit button clicked");
		driver.waitForPageLoad();
	} 


	public void clickCorporateLink(){
		driver.waitForElementPresent(CORPORATE_LINK_LOC);
		driver.click(CORPORATE_LINK_LOC,"");
		logger.info("Corporate link clicked on site page");
		driver.waitForPageLoad();
	}

	public void clickAddEventLink(){
		driver.waitForElementPresent(ADD_EVENT_LINK_LOC);
		driver.click(ADD_EVENT_LINK_LOC,"");
		logger.info("Add event link clicked on site page");
		driver.waitForPageLoad();
	}

	public void enterSubjectForEvent(String subject){
		driver.waitForElementPresent(SUBJECT_TXT_BOX_LOC);
		driver.type(SUBJECT_TXT_BOX_LOC, subject);
		logger.info("subject entered as: "+subject);
	}

	public void clickSaveBtn(){
		driver.waitForElementPresent(EVENT_SAVE_BUTTON);
		driver.click(EVENT_SAVE_BUTTON,"");
		logger.info("Save button clicked for an event");
		driver.waitForPageLoad();
	}

	public String getSavedSuccessfullyTxt(){
		driver.waitForElementPresent(EVENT_SAVED_SUCCESSFULLY_TXT_LOC);
		String savedMsg = driver.findElement(EVENT_SAVED_SUCCESSFULLY_TXT_LOC).getText();
		logger.info("Successfully saved message is: "+savedMsg);
		return savedMsg;
	}

	public boolean isEventPresentAtCalendar(String eventName){
		driver.quickWaitForElementPresent(By.xpath(String.format(eventNameAtCalendar, eventName)));
		return driver.isElementPresent(By.xpath(String.format(eventNameAtCalendar, eventName)));
	}

	public void clickEventNamePresentAtCalendar(String eventName){
		driver.quickWaitForElementPresent(By.xpath(String.format(eventNameAtCalendar, eventName)));
		driver.click(By.xpath(String.format(eventNameAtCalendar, eventName)),"");
		logger.info("Event name clicked i.e.: "+eventName);
		driver.waitForPageLoad();
	}

	public void clickDeleteBtnForEvent(){
		driver.waitForElementPresent(EVENT_DELETE_BUTTON);
		driver.click(EVENT_DELETE_BUTTON,"");
		logger.info("Delete button clicked for an event");
		driver.pauseExecutionFor(3000);
	}

	public void clickAddNewsLink(){
		driver.waitForElementPresent(ADD_NEWS_LINK_LOC);
		driver.click(ADD_NEWS_LINK_LOC,"");
		logger.info("Add news link clicked on site page");
		driver.waitForPageLoad();
	}

	public int getSizeOfOptinsFromPageSizeDD(){
		driver.waitForElementPresent(PAGE_SIZE_DD_OPTIONS);
		int noOfOptins = driver.findElements(PAGE_SIZE_DD_OPTIONS).size();
		logger.info("No of options are available in page size DD: "+noOfOptins);
		return noOfOptins;
	}

	public void clickAndSelectOptionInPageSizeDD(int optionNumber){
		driver.click(PAGE_SIZE_DD,"");
		driver.click(By.xpath(String.format(pageSizeDDoption, optionNumber)),"");
		logger.info("Page size DD option selected");
	}

	public boolean isTitleNamePresentInAnnouncementsList(String titleName){
		driver.waitForElementPresent(By.xpath(String.format(titleNameLinkLoc, titleName)));
		return driver.isElementPresent(By.xpath(String.format(titleNameLinkLoc, titleName)));
	}

	public void checkIsActiveChkBoxForNewsTitle(){
		driver.waitForElementPresent(DEACTIVED_IS_ACTIVE_CHK_BOX_FOR_NEWS_TITLE);
		if(driver.isElementPresent(DEACTIVED_IS_ACTIVE_CHK_BOX_FOR_NEWS_TITLE)==true){
			driver.click(IS_ACTIVE_CHK_BOX_FOR_NEWS_TITLE,"");
			logger.info("Is Active checkbox checked for site details");
		}else{
			logger.info("Is Active checkbox already checked for site details");
		}
	}

	public void clickTitleNamePresentInAnnouncementsList(String titleName){
		driver.waitForElementPresent(By.xpath(String.format(titleNameLinkLoc, titleName)));
		driver.click(By.xpath(String.format(titleNameLinkLoc, titleName)),"");
		logger.info("Title name i.e.: "+titleName+" clicked in announcements list");
	}

	public void clickDeactivateSelectedLink(){
		driver.click(DEACTIVATE_SELECTED_LINK,"");
		logger.info("Deactivate selected link clicked");
	}

	public String getTitleStatus(String titleName){
		driver.pauseExecutionFor(2000);
		driver.isElementPresent(By.xpath(String.format(titleStatus, titleName)));
		String status = driver.findElement(By.xpath(String.format(titleStatus, titleName))).getText();
		logger.info("title status is: "+status);
		return status;
	}

	public void clickActivateSelectedLink(){
		driver.click(ACTIVATE_SELECTED_LINK,"");
		logger.info("Activate selected link clicked");
	}

	public void enterTitleForAddNews(String title){
		driver.waitForElementPresent(TITLE_TXT_BOX_LOC);
		driver.type(TITLE_TXT_BOX_LOC, title);
		logger.info("title entered as: "+title);
	}

	public void clickSubLinkOfCorporate(String subLinkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(corporateSubLink, subLinkName)));
		driver.click(By.xpath(String.format(corporateSubLink, subLinkName)),"");
		logger.info("Sublink clicked is: "+subLinkName);
		driver.waitForPageLoad();
	}

	public void enterSiteNameForSiteDetails(String siteName){
		driver.waitForElementPresent(SITE_NAME_TXT_BOX);
		driver.type(SITE_NAME_TXT_BOX, siteName);
		logger.info("Site name entered as: "+siteName);
	}

	public void checkActiveChkBoxForSiteDetails(){
		driver.waitForElementPresent(DEACTIVED_CHK_BOX_FOR_SITE_DETAILS);
		if(driver.isElementPresent(ACTIVE_CHK_BOX_FOR_SITE_DETAILS)==true){
			driver.click(ACTIVE_CHK_BOX_FOR_SITE_DETAILS,"");
			logger.info("Active checkbox checked for site details");
		}else{
			logger.info("Active checkbox already checked for site details");
		}
	}

	public void clickSaveBtnOnSiteDetails(){
		driver.waitForElementPresent(SITE_SAVE_BUTTON);
		driver.click(SITE_SAVE_BUTTON,"");
		logger.info("Save button clicked for site details");
		driver.waitForPageLoad();
	}

	public String getSavedSuccessfullyTxtForSite(){
		driver.waitForElementPresent(SITE_DETAILS_SAVED_SUCCESSFULLY_TXT_LOC);
		String savedMsg = driver.findElement(SITE_DETAILS_SAVED_SUCCESSFULLY_TXT_LOC).getText();
		logger.info("Successfully saved message is: "+savedMsg);
		return savedMsg;
	}

	public void uncheckActiveChkBoxForSiteDetails(){
		driver.waitForElementPresent(ACTIVE_CHK_BOX_FOR_SITE_DETAILS);
		driver.click(ACTIVE_CHK_BOX_FOR_SITE_DETAILS,"");
		logger.info("Active checkbox unchecked for site details");
	}

	public boolean isReplicatedSitesHeaderPresent(){
		driver.quickWaitForElementPresent(REPLICATED_SITES_HEADER);
		return driver.isElementPresent(REPLICATED_SITES_HEADER);
	}

	public void clickSubLinkOfDistributorOnSitePage(String subLinkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(distributorSubLink, subLinkName)));
		driver.click(By.xpath(String.format(distributorSubLink, subLinkName)),"");
		logger.info("Sublink clicked is: "+subLinkName);
		driver.waitForPageLoad();
	}

	public void clickAddNewPageLink(){
		driver.waitForElementPresent(ADD_NEW_PAGE_LINK_LOC);
		driver.click(ADD_NEW_PAGE_LINK_LOC,"");
		logger.info("Add new Page link is clicked.");
		driver.waitForPageLoad();
	}

	public void enterNewPageDetails(String pageName,String pageTitle,String pageDescription,String pageURL,String keyword,String templateView){
		driver.type(PAGE_NAME_LOC, pageName);
		logger.info("Page Name entered as: "+pageName);
		driver.type(PAGE_TITLE_LOC, pageTitle);
		logger.info("Page title entered as: "+pageTitle);
		driver.type(PAGE_DESCRIPTION_LOC, pageDescription);
		logger.info("Page Description entered as: "+pageDescription);
		driver.type(PAGE_URL_LOC, pageURL);
		logger.info("Page url entered as: "+pageURL);
		driver.click(ACTIVE_CHECKBOX_LOC,"");
		logger.info("Active Checkbox clicked: ");
		driver.type(PAGE_KEYWORD_LOC, keyword);
		logger.info("Keyword entered as: "+keyword);
		driver.click(TEMPLATE_DROPDOWN_LOC,"");
		logger.info("Template dropdown clicked: ");
		driver.click(By.xpath(String.format(templateDropdownValue, templateView)),"");
		logger.info("Template dropdown selected value: "+templateView);
	}

	public void clickSaveButtonForNewCreatedPage(){
		driver.waitForElementPresent(SAVE_BUTTON_FOR_PAGE_DETAILS);
		driver.click(SAVE_BUTTON_FOR_PAGE_DETAILS,"");
		logger.info("Save button is clicked after entering page details.");
		driver.waitForPageLoad();
	}

	public String getPageSavedSuccessfullyTxtForSite(){
		driver.waitForElementPresent(SITE_PAGE_SAVED_SUCCESSFULLY_TXT_LOC);
		String savedMsg = driver.findElement(SITE_PAGE_SAVED_SUCCESSFULLY_TXT_LOC).getText();
		logger.info("Successfully saved message is: "+savedMsg);
		return savedMsg;
	}

	public void clickPageNameOnSitePageList(String pageName){
		driver.waitForElementPresent(By.xpath(String.format(pageNameToAssert, pageName)));
		driver.click(By.xpath(String.format(pageNameToAssert, pageName)),"");
		logger.info("Page Name: "+pageName+" is clicked");
		driver.waitForPageLoad();
	}

	public void checkActiveCheckboxOnSitePage(){
		driver.waitForElementPresent(ACTIVE_CHECKBOX_LOC);
		driver.click(ACTIVE_CHECKBOX_LOC,"");
		logger.info("Active Checkbox clicked: ");
	}

	public void moveToSiteMapLinkUnderDrFieldsForAddLinkOfBasePWS(String linkName){
		Actions builder = new Actions(RFWebsiteDriver.driver);
		builder.clickAndHold(driver.findElement(By.xpath(String.format(linkTextNameInSiteMap, linkName)))).build().perform();
		builder.moveToElement(driver.findElement(PLUS_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE_FOR_BASE_PWS)).release(driver.findElement(PLUS_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE_FOR_BASE_PWS)).build().perform();
		logger.info("New site map link moved under meet dr fields");
	}

	public void expandTheTreeOfSiteMapOfBasePWS(){
		driver.waitForElementPresent(EXPAND_SYMBOL_OF_ABOUT_RF_IN_TREE_FOR_BASE_PWS);
		driver.click(EXPAND_SYMBOL_OF_ABOUT_RF_IN_TREE_FOR_BASE_PWS,"");
		logger.info("Expand symbol clicked for About R+F");
		driver.waitForElementPresent(EXPAND_SYMBOL_OF_MEET_THE_DOCTOR_IN_TREE_FOR_BASE_PWS);
		driver.click(EXPAND_SYMBOL_OF_MEET_THE_DOCTOR_IN_TREE_FOR_BASE_PWS,"");
		logger.info("Expand symbol clicked for meet the doctor");
		driver.waitForElementPresent(EXPAND_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE_FOR_BASE_PWS);
		driver.click(EXPAND_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE_FOR_BASE_PWS,"");
		logger.info("Expand symbol clicked for Meet Dr Fields");
	}

	public void clickAddLinkForSiteMap(){
		driver.waitForElementPresent(ADD_NEW_SITE_LINK);
		driver.click(ADD_NEW_SITE_LINK,"");
		logger.info("Add link clicked for add new site map");
		driver.waitForPageLoad();
	}

	public void enterLinkTextForSiteMap(String linkText){
		driver.waitForElementPresent(LINK_TEXT_BOX);
		driver.type(LINK_TEXT_BOX, linkText);
		logger.info("Site name entered as: "+linkText);
	}

	public void selectPagesForSiteMap(String page){
		Actions actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(PAGES_DD_FOR_SITE_MAP)).click().build().perform();
		logger.info("Pages DD clicked for site map");
		driver.click(By.xpath(String.format(pageDDOptionForSiteMap, page)),"");
		logger.info("option selected for site map is: "+page);
	}

	public void clickSaveBtnOnSiteMap(){
		driver.waitForElementPresent(SITE_MAP_SAVE_BUTTON);
		driver.click(SITE_MAP_SAVE_BUTTON,"");
		logger.info("Save button clicked for site Map");
		driver.waitForPageLoad();
	}

	public boolean isLinkTextNamePresentInTreeMap(String linkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(linkTextNameInSiteMap, linkName)));
		return driver.isElementPresent(By.xpath(String.format(linkTextNameInSiteMap, linkName)));
	}


	public void moveToSiteMapLinkUnderDrFields(String linkName){
		Actions builder = new Actions(RFWebsiteDriver.driver);
		builder.clickAndHold(driver.findElement(By.xpath(String.format(linkTextNameInSiteMap, linkName)))).build().perform();
		builder.moveToElement(driver.findElement(PLUS_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE)).release(driver.findElement(PLUS_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE)).build().perform();
		logger.info("New site map link moved under meet dr fields");
	}

	public void expandTheTreeOfSiteMap(){
		driver.waitForElementPresent(EXPAND_SYMBOL_OF_ABOUT_RF_IN_TREE);
		driver.click(EXPAND_SYMBOL_OF_ABOUT_RF_IN_TREE,"");
		logger.info("Expand symbol clicked for About R+F");
		driver.waitForElementPresent(EXPAND_SYMBOL_OF_WHO_WE_ARE_IN_TREE);
		driver.click(EXPAND_SYMBOL_OF_WHO_WE_ARE_IN_TREE,"");
		logger.info("Expand symbol clicked for Who We Are");
		driver.waitForElementPresent(EXPAND_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE);
		driver.click(EXPAND_SYMBOL_OF_MEET_DR_FIELDS_IN_TREE,"");
		logger.info("Expand symbol clicked for Meet Dr Fields");
	}

	public void clickActivateLinkOnSiteMap(){
		driver.waitForElementPresent(ACTIVATE_LINK_FOR_SITE_MAP);
		driver.click(ACTIVATE_LINK_FOR_SITE_MAP,"");
		logger.info("Activate button clicked for site Map");
		driver.waitForPageLoad();
	}

	public void clickSubLinkOfNSCorporate(String subLinkName){
		driver.pauseExecutionFor(2000);
		driver.quickWaitForElementPresent(By.xpath(String.format(nscorporateSubLink, subLinkName)));
		driver.click(By.xpath(String.format(nscorporateSubLink, subLinkName)),"");
		logger.info("Sublink clicked is: "+subLinkName);
	}

	public String getProductClearAndReloadConfirmationMessage(){
		driver.pauseExecutionFor(1000);
		driver.quickWaitForElementPresent(CLEAR_AND_RELOAD_PRODUCT_CONFIRMATION_MESSAGE);
		String msg = driver.findElement(CLEAR_AND_RELOAD_PRODUCT_CONFIRMATION_MESSAGE).getText();
		logger.info("Confirmation message is: "+msg);
		return msg;
	}

	public void clickLinkTextNamePresentInTreeMap(String linkName){
		driver.waitForElementPresent(By.xpath(String.format(linkTextNameInSiteMap, linkName)));
		driver.click(By.xpath(String.format(linkTextNameInSiteMap, linkName)),"");
		logger.info("Link text name clicked in tree map");
	}

	public void clickDeactivateLinkOnSiteMap(){
		driver.waitForElementPresent(DEACTIVATE_LINK_FOR_SITE_MAP);
		driver.click(DEACTIVATE_LINK_FOR_SITE_MAP,"");
		logger.info("Deactivate button clicked for site Map");
		driver.waitForPageLoad();
	}

	public boolean isActivateLinkPresentOnSiteMap(){
		driver.waitForElementPresent(ACTIVATE_LINK_PRESENT);
		return driver.isElementPresent(ACTIVATE_LINK_PRESENT);
	}

	public void enterUploadResourceDetails(String name, String filePath, String categoryDDValue){
		driver.waitForElementPresent(NAME_FOR_UPLOAD_RESOURCE);
		driver.type(NAME_FOR_UPLOAD_RESOURCE, name);
		logger.info("name entered for upload resource is: "+name);
		driver.findElement(FILEPATH_FOR_UPLOAD_RESOURCE).sendKeys(filePath);
		logger.info("filepath entered for upload resource is: "+filePath);
		driver.click(SELECT_CATEGORY_DD_FOR_UPLOAD_RESOURCE,"");
		logger.info("Select category dropdown clicked");
		driver.waitForElementPresent(By.xpath(String.format(selectCategoryDDOptionForUploadResource, categoryDDValue)));
		driver.click(By.xpath(String.format(selectCategoryDDOptionForUploadResource, categoryDDValue)),"");
		logger.info("Select category dropdown option selected");
	}

	public void clickSaveBtnOnUploadResource(){
		driver.waitForElementPresent(SITE_MAP_SAVE_BUTTON);
		driver.click(SITE_MAP_SAVE_BUTTON,"");
		logger.info("Save button clicked for site Map");
		driver.waitForPageLoad();
	}

	public void clickUploadResourceLink(){
		driver.waitForElementPresent(UPLOAD_RESOURCE_LINK);
		driver.click(UPLOAD_RESOURCE_LINK,"");
		logger.info("Upload resource link clicked on site page");
		driver.waitForPageLoad();
	}

	public void clickCheckBoxForName(String name){
		driver.click(By.xpath(String.format(nameChkBoxInSiteList, name)),"");
		logger.info("Checkbox clicked of name i.e.: "+name);
	}

	public void clickActivateSelectedLinkForUploadResoursce(){
		driver.click(ACTIVATE_SELECTED_LINK_FOR_UPLOAD_RESOURCE,"");
		logger.info("Activate selected link clicked fro upload resoursce");
		driver.waitForNSCore4LoadingImageToDisappear();
	}

	public void clickAddResourceLink(){
		driver.waitForElementPresent(ADD_RESOURCE_LINK);
		driver.click(ADD_RESOURCE_LINK,"");
		logger.info("Add resource link clicked");
		driver.waitForPageLoad();
	}

	public void clickAddCategoryLink(){
		driver.waitForElementPresent(ADD_CATEGORY_LINK);
		driver.click(ADD_CATEGORY_LINK,"");
		logger.info("Add Category link clicked");
		driver.waitForPageLoad();
	}

	public void enterCategoryName(String categoryName){
		driver.waitForElementPresent(CATEGORY_NAME);
		driver.type(CATEGORY_NAME, categoryName);
		logger.info("Category Name entered as: "+categoryName);
	}

	public void selectCategoryInFilter(String categoryDDValue){
		driver.click(SELECT_CATEGORY_DD_FOR_Filter,"");
		logger.info("Select category dropdown clicked for filter");
		driver.waitForElementPresent(By.xpath(String.format(selectCategoryDDOptionForFilter, categoryDDValue)));
		driver.click(By.xpath(String.format(selectCategoryDDOptionForFilter, categoryDDValue)),"");
		logger.info("Select category dropdown option selected is: "+categoryDDValue);
	}

	public void selectStatusInFilter(String status){
		driver.click(SELECT_STATUS_DD_FOR_Filter,"");
		logger.info("Select status dropdown clicked for filter");
		driver.waitForElementPresent(By.xpath(String.format(selectStatusDDOptionForFilter, status)));
		driver.click(By.xpath(String.format(selectStatusDDOptionForFilter, status)),"");
		logger.info("Select status dropdown option selected is: "+status);
	}

	public void enterSearchTerms(String name){
		driver.waitForElementPresent(SEARCH_TERMS_TXT_BOX_LOC);
		driver.type(SEARCH_TERMS_TXT_BOX_LOC, name);
		logger.info("Search terms text entered as: "+name);
	}

	public void clickGoBtn(){
		driver.waitForElementPresent(GO_BTN_LOC);
		driver.click(GO_BTN_LOC,"");
		logger.info("Go button clicked");
		driver.waitForPageLoad();
	}

	public void selectCategoryForMoveToResource(String categoryDDValue){
		driver.click(SELECT_CATEGORY_DD_FOR_MOVE,"");
		logger.info("Select category dropdown clicked for move");
		driver.waitForElementPresent(By.xpath(String.format(selectCategoryDDOptionForMove, categoryDDValue)));
		driver.click(By.xpath(String.format(selectCategoryDDOptionForMove, categoryDDValue)),"");
		logger.info("Select category dropdown option selected is: "+categoryDDValue);
	}

	public void clickMoveSelectedToCategory(){
		driver.waitForElementPresent(MOVE_SELECTED_TO_CATEGORY_LOC);
		driver.click(MOVE_SELECTED_TO_CATEGORY_LOC,"");
		logger.info("Move selected to category link clicked");
		driver.waitForNSCore4LoadingImageToDisappear();
	}

	public void clickManageResourceCategoriesLink(){
		driver.waitForElementPresent(MANAGE_RESOURCE_CATEGORIES_LINK);
		driver.click(MANAGE_RESOURCE_CATEGORIES_LINK,"");
		logger.info("Manage resource categories link clicked");
		driver.waitForPageLoad();
	}

	public void clickGoBtnForManageResource(){
		driver.waitForElementPresent(GO_BTN_FOR_MANAGE_RESOURCE_LOC);
		driver.click(GO_BTN_FOR_MANAGE_RESOURCE_LOC,"");
		logger.info("Go button clicked for manage resource");
		driver.waitForNSCore4LoadingImageToDisappear();
	}

	public void clickDeleteSelectedLinkForManageResource(){
		driver.click(DELETE_SELECTED_LINK_FOR_MANAGE_RESOURCE,"");
		logger.info("Delete selected link clicked for manage resource");
		driver.waitForNSCore4LoadingImageToDisappear();
	}

	public String getMessageOfDelete(){
		driver.waitForElementPresent(GET_MESSAGE_OF_DELETE_SELECTED);
		String msg = driver.findElement(GET_MESSAGE_OF_DELETE_SELECTED).getText();
		logger.info("Confirmation msg of delete is: "+msg);
		driver.waitForNSCore4LoadingImageToDisappear();
		return msg;
	}

	public void clickAddNewEventLink(){
		driver.waitForElementPresent(ADD_NEW_EVENT_LINK_ON_CALENDER_LOC);
		driver.click(ADD_NEW_EVENT_LINK_ON_CALENDER_LOC,"");
		logger.info("Add new event link clicked on site page");
		driver.waitForPageLoad();
	}

	public void selectMonthForCalendarEvent(String month){
		driver.waitForElementPresent(CALENDER_EVENT_MONTH_DROPDOWN);
		driver.click(CALENDER_EVENT_MONTH_DROPDOWN,"");
		driver.quickWaitForElementPresent(By.xpath(String.format(calenderEventMonthValue, month)));
		driver.click(By.xpath(String.format(calenderEventMonthValue, month)),"");
	}

	public void selectYearForCalendarEvent(String year){
		driver.waitForElementPresent(CALENDER_EVENT_YEAR_DROPDOWN);
		driver.click(CALENDER_EVENT_YEAR_DROPDOWN,"");
		driver.quickWaitForElementPresent(By.xpath(String.format(calenderEventYearValue, year)));
		driver.click(By.xpath(String.format(calenderEventYearValue, year)),"");
	}

	public void clickCalenderEventStartDate(){
		driver.waitForElementPresent(NEW_CALENDER_EVENT_START_DATE);
		driver.click(NEW_CALENDER_EVENT_START_DATE,"");
		logger.info("Calender event start date clicked.");
	}

	public void clickCalenderEventEndDate(){
		driver.waitForElementPresent(NEW_CALENDER_EVENT_END_DATE);
		driver.click(NEW_CALENDER_EVENT_END_DATE,"");
		logger.info("Calender event End date clicked.");
	}

	public void selectStartDateForNewEvent(String month,String year,String date){
		clickCalenderEventStartDate();
		selectMonthOnCalenderForNewEvent(month);
		selectYearOnCalenderForNewEvent(year);
		clickSpecficDateOfCalendar(date);
	}

	public void selectEndDateForNewEvent(String month,String year,String date){
		clickCalenderEventEndDate();
		selectMonthOnCalenderForNewEvent(month);
		selectYearOnCalenderForNewEvent(year);
		clickSpecficDateOfCalendar(date); 
	}

	public void clickTodayBtn(){
		driver.waitForElementPresent(TODAY_BUTTON_OF_CALENDER);
		driver.click(TODAY_BUTTON_OF_CALENDER,"");
		logger.info("Today button on calender event page is clicked.");
	}


	public  String[] getPreviousMonthFullAndShortName(String currentMonthFullName){
		int a = 0;
		// int b = 0;
		String[] previousMonthShortAndFullName =new String[5];
		String month = currentMonthFullName;
		if(month.equalsIgnoreCase("January")){
			a=1;
		}
		else if(month.equalsIgnoreCase("February")){
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
		//   a=a+1;
		//   if(a==13){
		//    a=1;
		//    b=1;
		//   }
		switch (a) {  
		case 1:
			previousMonthShortAndFullName[0]="December";
			previousMonthShortAndFullName[1]="Dec";
			break;
		case 2:
			previousMonthShortAndFullName[0]="January";
			previousMonthShortAndFullName[1]="Jan";
			break;
		case 3:
			previousMonthShortAndFullName[0]="February";
			previousMonthShortAndFullName[1]="Feb";
			break;
		case 4:
			previousMonthShortAndFullName[0]="March";
			previousMonthShortAndFullName[1]="Mar";
			break;
		case 5:
			previousMonthShortAndFullName[0]="April";
			previousMonthShortAndFullName[1]="Apr";
			break;
		case 6:
			previousMonthShortAndFullName[0]="May";
			previousMonthShortAndFullName[1]="May";
			break;
		case 7:
			previousMonthShortAndFullName[0]="June";
			previousMonthShortAndFullName[1]="Jun";
			break;
		case 8:
			previousMonthShortAndFullName[0]="July";
			previousMonthShortAndFullName[1]="Jul";
			break;
		case 9:
			previousMonthShortAndFullName[0]="August";
			previousMonthShortAndFullName[1]="Aug";
			break;
		case 10:
			previousMonthShortAndFullName[0]="September";
			previousMonthShortAndFullName[1]="Sep";
			break;
		case 11:
			previousMonthShortAndFullName[0]="October";
			previousMonthShortAndFullName[1]="Oct";
			break;
		case 12:
			previousMonthShortAndFullName[0]="November";
			previousMonthShortAndFullName[1]="Nov";
			break;  
		}
		return previousMonthShortAndFullName;
	}

	public String getPreviousYear(String currentYear){
		String prevYear = Integer.toString((Integer.parseInt(currentYear)-1));
		return prevYear;
	}

	public void clickPreviousBtn(){
		driver.waitForElementPresent(PREVIOUS_TAB_BUTTON_ON_CALENDER);
		driver.click(PREVIOUS_TAB_BUTTON_ON_CALENDER,"");
		logger.info("Previous Tab button on calender event page is clicked.");
	}

	public void clickNextBtn(){
		driver.waitForElementPresent(NEXT_TAB_BUTTON_ON_CALENDER);
		driver.click(NEXT_TAB_BUTTON_ON_CALENDER,"");
		logger.info("Next Tab Button on calender event page is clicked.");
	}

	public String getEventNameFromEditEventDetailsPage(){
		driver.waitForElementPresent(EVENT_NAME_FROM_EDIT_EVENT_DETAILS_PAGE);
		return driver.findElement(EVENT_NAME_FROM_EDIT_EVENT_DETAILS_PAGE).getAttribute("value");
	}

	public void enterDescription(String description){
		driver.type(PAGE_DESCRIPTION_FOR_RESOURCE_LOC, description);
		logger.info("Page Description entered as: "+description);
	}

	public void clickBasePWSLink(){
		driver.waitForElementPresent(BASE_PWS_LINK_LOC);
		driver.click(BASE_PWS_LINK_LOC,"");
		logger.info("base PWS link clicked on site page");
		driver.waitForPageLoad();
	}

	public void enterNewsDetails(String title, String caption){
		driver.waitForElementPresent(CATEGORY_NAME);
		driver.type(CATEGORY_NAME, title);
		logger.info("Title entered as: "+title);
		driver.type(CAPTION_LOC, caption);
		logger.info("caption entered as: "+caption);
	}

	public boolean isYouAreInEditModeLinkPresent(){
		driver.waitForElementPresent(YOU_ARE_IN_EDIT_MODE_LINK_LOC);
		return driver.IsElementVisible(driver.findElement(YOU_ARE_IN_EDIT_MODE_LINK_LOC));
	}

	public void hoverOnManageBtnAndClickOnEditLink(){
		driver.moveToELement(MANAGE_BTN_LOC);
		logger.info("mouse hover on manage btn");
		driver.click(EDIT_BTN_UNDER_MANAGE_LINK_LOC,"");
		logger.info("Edit link clicked under manage btn");
	}

	public Map<String, String> getInitialSiteDetails(){
		initialSiteProperties = new HashMap<String,String>();
		initialSiteProperties.put("SiteName",getSiteName());
		initialSiteProperties.put("Description",driver.getText(PAGE_DESCRIPTION_FOR_RESOURCE_LOC));
		initialSiteProperties.put("Status",getStatusFromSiteDetails());
		initialSiteProperties.put("Language",getSelectedLanguageFromSiteDetails());
		return initialSiteProperties;
	}

	public String getSiteName(){
		return driver.getAttribute(SITE_NAME_TXT_BOX,"value").trim();
	}

	public void clickLinkUnderBasePWS(String link){
		driver.click(By.xpath(String.format(linkUnderBasePWSLoc,link)),"");
		logger.info("Clicked "+link+" Under Base PWS");
	}

	public String getStatusFromSiteDetails(){
		String value = driver.getAttribute(ACTIVE_STATUS_LOC,"value");
		if(value.equals("true")){
			return "Active";
		}
		return "Cancelled";
	}

	public String changeStatusOfSiteDetails(){
		driver.click(ACTIVE_STATUS_CHECKBOX_LOC,"");
		String value = driver.getAttribute(ACTIVE_STATUS_LOC,"value");
		if(value.equals("true")){
			return "Active";
		}
		return "Cancelled";
	}


	public String getSelectedLanguageFromSiteDetails(){
		return driver.getText(SITE_SELECTED_LANGUAGE_LOC).trim();
	}

	public String selectNewLanguageFromDD(String initialLan){
		String selectedLanguage = null;
		Select select = new Select(driver.findElement(SITE_LANGUAGE_DD_LOC));
		for(WebElement option :  select.getOptions()){
			if(!option.getText().trim().contains(initialLan)){
				select.selectByVisibleText(option.getText().trim());
				selectedLanguage = option.getText().trim();
				break;
			}
		}
		return selectedLanguage;
	}
	public void selectLanguageFromDD(String language){
		Select select = new Select(driver.findElement(SITE_LANGUAGE_DD_LOC));
		select.selectByVisibleText(language);
	}

	public boolean verifySiteNameFromLeftNav(String updatedSiteName){
		return driver.getText(SITE_NAME_IN_LEFT_NAV_lOC).trim().contains(updatedSiteName);
	}

	public boolean verifyDescFromLeftNav(String updatedSiteDesc){
		return driver.getText(SITE_DESC_IN_LEFT_NAV_LOC).trim().contains(updatedSiteDesc);
	}

	public boolean verifyStatusFromLeftNav(String status){
		return driver.getText(STATUS_IN_LEFT_NAV_LOC).trim().contains(status);
	}

	public void clickLinkFromLeftNav(String link){
		driver.click(By.xpath(String.format(linkInLeftNavloc, link)),"");
		logger.info("Clicked "+link+" Link from Left Nav");
	}

	public boolean verifyLanguageFromSiteDetails(String language){
		return getSelectedLanguageFromSiteDetails().trim().contains(language);
	}


	public boolean verifyReplicateSitesHeaderIsPresent(){
		return driver.isElementVisible(REPLICATED_SITES_HEADER);
	}

	public void selectFromStatusFilter(String status){
		Select select = new Select(driver.findElement(STATUS_FILTER_DD_LOC));
		select.selectByVisibleText(status);
	}

	public boolean verifySitesStatusAfterFilter(String status){
		boolean isVerificationSuccess = true;
		List<WebElement> sitesStatus = driver.findElements(ALL_SITES_STATUS_LOC);
		if(sitesStatus.size() == 0){
			logger.info("No Site is present with Status : "+status);
		}
		String actualStatus =  null;
		for(WebElement siteStatus : sitesStatus){
			actualStatus = siteStatus.getText().trim();
			if(!actualStatus.equals(status)){
				isVerificationSuccess = false;
			}
		}
		return isVerificationSuccess;  
	}

	public void clickFirstSiteFromSitesGrid(String status){
		selectFromStatusFilter(status);
		driver.click(FIRST_SITE_LINK_LOC,"");
		logger.info("Clicked first Site after applying filter : "+status);
	}

	public String getEventSubjectFromEventDetails(){
		return driver.getAttribute(SUBJECT_TXT_BOX_LOC,"value").trim();
	}

	public void clickCancelBtnOnEventDetails(){
		driver.click(EVENT_DETAILS_CANCEL_BTN_LOC,"");
		logger.info("Clicked Cancel Btn on Event Details");
	}

	public void selectEventMonthFromDD(String month){
		Select select = new Select(driver.findElement(EVENT_MONTH_DD_LOC));
		select.selectByVisibleText(month);
	}

	public void selectEventYearFromDD(String year){
		Select select = new Select(driver.findElement(EVENT_YY_DD_LOC));
		select.selectByVisibleText(year);
	}

	public void selectFirstDateFromCalendar(){
		driver.click(FIRST_DATE_FROM_CALENDAR_LOC,"");
		logger.info("First Date selected from Calendar");
	}

	public void clickDeleteSelectedLink(){
		driver.click(DELETE_SELECTED_LINK,"");
		logger.info("Delete selected link clicked");
		driver.waitForNSCore4ProcessImageToDisappear();
	}

	public void clickDeactivateSelectedLinkForUploadResoursce(){
		driver.click(DEACTIVATE_SELECTED_LINK_FOR_UPLOAD_RESOURCE,"");
		logger.info("Deactivate selected link clicked fro upload resoursce");
		driver.waitForNSCore4ProcessImageToDisappear();
	}

	public void clickSubLinksSitesTab(String linkName) {
		Actions builder = new Actions(RFWebsiteDriver.driver);
		builder.moveToElement(driver.findElement(By.xpath(String.format(SiteTabLinksLoc, linkName)))).build().perform();
		logger.info("Mouse over to corporate link performed");
		driver.click(CORPORATE_LINK_ON_NSCORPORATE_DD_LOC, "Corporate");

	}

	public void moveToSiteMapLinkUnderDrFieldsForAddLinkOfBasePWS(String linkName, String linkNameForMove){
		Actions builder = new Actions(RFWebsiteDriver.driver);
		builder.clickAndHold(driver.findElement(By.xpath(String.format(linkTextNameInSiteMap, linkName)))).build().perform();
		builder.moveToElement(driver.findElement(By.xpath(String.format(plusSymbolOfLinkLoc, linkNameForMove)))).release(driver.findElement(By.xpath(String.format(plusSymbolOfLinkLoc, linkNameForMove)))).build().perform();
		logger.info("New site map link moved under meet dr fields");
	}

	public void clearNavigationCacheAll(){
		driver.waitForElementPresent(NAVIGATION_CACHE_ALL_CHK_BOX_LOC);
		driver.click(NAVIGATION_CACHE_ALL_CHK_BOX_LOC, "select all chk box for navigation cache");
		driver.click(RELOAD_BTN_LOC,"Reload button");
		driver.waitForElementPresent(By.xpath("//div[@id='divLoadingNavCache' and contains(@style,'none')]"), 20);
	}

	public void clearSiteProductCacheAll(){
		driver.waitForElementPresent(SITE_PRODUCT_CACHE_ALL_CHK_BOX_LOC);
		driver.click(SITE_PRODUCT_CACHE_ALL_CHK_BOX_LOC, "select all chk box for PRODUCT cache");
		driver.click(RELOAD_BTN_LOC,"Reload button");
	}

	public boolean isLinkPresentUnderAboutRAndF(String linkName){
		driver.click(ABOUT_RF_LOC,"");
		return driver.isElementPresent(By.xpath(String.format(linkTextLoc, linkName)));
	}

	public void expandTheTreeOfSiteForWhoWeAreLink(){
		driver.waitForElementPresent(EXPAND_SYMBOL_OF_ABOUT_RF_IN_TREE_FOR_BASE_PWS);
		driver.click(EXPAND_SYMBOL_OF_ABOUT_RF_IN_TREE_FOR_BASE_PWS,"");
		logger.info("Expand symbol clicked for About R+F");
		driver.waitForElementPresent(EXPAND_SYMBOL_OF_WHO_WE_ARE_IN_TREE_FOR_BASE_PWS);
		driver.click(EXPAND_SYMBOL_OF_WHO_WE_ARE_IN_TREE_FOR_BASE_PWS,"Expand symbol clicked for Who we are");
	}

	public void clickEditLinkUnderManage(){
		driver.click(EDIT_TEXT_UNDER_MANAGE_LOC,"");
		logger.info("Clicked edit link under manage on corporate site");
		driver.pauseExecutionFor(5000);
	}
	public void clickSaveButtonAfterEnteringEditText(){
		driver.quickWaitForElementPresent(SAVE_PREVIEW_BUTTON_LOC);
		driver.click(SAVE_PREVIEW_BUTTON_LOC,"");
		logger.info("Save button is clicked after entering edit text");
		driver.waitForPageLoad();
	}
	public String getEditTextFromUI(){
		driver.quickWaitForElementPresent(EDIT_TXT_BOX_LOC);
		String editText =  driver.getText(EDIT_TXT_BOX_LOC);
		logger.info("Edit text value is"+editText);
		return editText;
	}
	public void clickSwitchToLiveMode(){
		driver.click(SWITCH_TO_LIVE_MODE_LOC,"");
		logger.info("Switch to live mode clicked on corporate site");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
	}
	public boolean isCorporateSiteIsInLiveMode(){
		boolean liveFlag = driver.isElementPresent(MANAGE_LINK_CORPORATE_SITE_LOC);
		logger.info("boolean live flag value is"+liveFlag);
		return !liveFlag;
	}


	public void hoverOnHeaderLinkAndClickSubLink(String headerLink, String subLink){
		driver.click(By.xpath(String.format(headerLinkLoc, headerLink)),"");
		driver.click(By.xpath(String.format(subLinkUnderHeaderLinkLoc, subLink)),"");
		logger.info("Link clicked "+subLink);
	}

	public void clickLoadSiteInEditModeLink(){
		driver.click(LOAD_SITE_IN_EDIT_MODE_LINK_LOC,"Load site in edit mode link clicked");
	}

	public void enterText(String text){
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		logger.info("Switched to frame successfully");
		driver.waitForElementPresent(TXT_FIELD_LOC);
		driver.pauseExecutionFor(5000);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(TXT_FIELD_LOC)).doubleClick(driver.findElement(TXT_FIELD_LOC)).sendKeys(text).build().perform();
		logger.info("Text entered as: "+text);
		driver.pauseExecutionFor(5000);
		driver.switchTo().defaultContent();
	}

	public boolean isLinkPresent(String linkName){
		return driver.isElementPresent(By.xpath(String.format(linkTextLoc, linkName)));
	}

	public void deleteText(String text){
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		Actions actions = new Actions(RFWebsiteDriver.driver);
		//driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(linkTextLoc, text)));
		actions.moveToElement(driver.findElement(By.xpath(String.format(linkTextLoc, text)))).doubleClick(driver.findElement(By.xpath(String.format(linkTextLoc, text)))).sendKeys("").build().perform();;
		driver.switchTo().defaultContent();
	}

	public void enterTextForPWS(String text){
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		logger.info("Switched to frame successfully");
		driver.waitForElementPresent(TXT_FIELD_FOR_PWS_LOC);
		driver.pauseExecutionFor(5000);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(TXT_FIELD_FOR_PWS_LOC)).doubleClick(driver.findElement(TXT_FIELD_FOR_PWS_LOC)).sendKeys(text).build().perform();
		logger.info("Text entered as: "+text);
		driver.pauseExecutionFor(5000);
		driver.switchTo().defaultContent();
	}

	public boolean isLinkPresentUnderAboutRAndFOnComPresent(String linkName){
		driver.click(ABOUT_RF_COM_LOC,"");
		return driver.isElementPresent(By.xpath(String.format(linkTextLoc, linkName)));
	}

	public void clickPreviewButtonAfterEditingText() {
		driver.pauseExecutionFor(2000);
		driver.waitForElementToBeClickable(PREVIEW_BUTTON_LOC, 10);
		driver.click(PREVIEW_BUTTON_LOC,"Preview Button");
		driver.waitForPageLoad();
	}

	public void clickSwitchBackToEditModeLink() {
		driver.pauseExecutionFor(2000);
		driver.waitForElementToBeClickable(SWITCH_BACK_TO_EDIT_MODE_LINK_LOC, 10);
		driver.click(SWITCH_BACK_TO_EDIT_MODE_LINK_LOC,"Switch back to Edit Mode");
		driver.waitForPageLoad();
	}	

	public void selectNextMonthFromCurrentDate(){
		String currentMonth = driver.getText(SELECTED_MONTH_IN_DD_LOC).trim();
		String nextMonth = null;
		if(currentMonth.equalsIgnoreCase("Dec")){
			driver.click(NEXT_MONTH_YEAR_LOC,"Next Month Icon");
			nextMonth = driver.getText(SELECTED_MONTH_IN_DD_LOC).trim();;	
		}else{
			nextMonth = driver.getText(By.xpath(String.format(nextMonthLoc,currentMonth)));
		}
		Select select = new Select(driver.findElement(EVENT_MONTH_DD_LOC));
		select.selectByVisibleText(nextMonth);
	}

	public void hoverManageOnCorporateSite() {
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
		clickRenewLater();
		Actions build = new Actions(RFWebsiteDriver.driver);
		build.moveToElement(driver.findElement(MANAGE_LINK_CORPORATE_SITE_LOC)).click().build().perform();
	}
	
	public void clickRenewLater(){
	if(driver.isElementVisible(RENEW_LATER_LINK,2)){
		driver.click(RENEW_LATER_LINK,"");
			driver.waitForPageLoad();
		} 
	}

	public boolean isCorporateSiteIsInEditMode(){
		driver.quickWaitForElementPresent(EDIT_TEXT_UNDER_MANAGE_LOC);
		return driver.isElementVisible(EDIT_TEXT_UNDER_MANAGE_LOC);
	}

	public String getTextFromBIZSite(){
		String text=null;
		if(driver.isElementPresent(TXT_FIELD_FOR_PWS_LOC)){
			text = driver.getText(TXT_FIELD_FOR_PWS_LOC, "text for biz PWS");
		}else if(driver.isElementPresent(TXT_FIELD_FOR_PWS_LIVE_SITE_LOC)){
			text = driver.getText(TXT_FIELD_FOR_PWS_LIVE_SITE_LOC, "text for biz PWS");	
		}
		return text;
	}			

	public String  getOriginalAndEnterNewTextForCategoryOnPWS(String text){
		String originalText = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		logger.info("Switched to frame successfully");
		driver.waitForElementPresent(TXT_FIELD_FOR_PWS_LOC);
		driver.pauseExecutionFor(5000);
		originalText = driver.getText(TXT_FIELD_FOR_PWS_LOC);
		if(originalText.equalsIgnoreCase(" ")){
			originalText="AutomationCreatedText"+randomNum;	
			logger.info("Created original text is "+originalText);
		}
		driver.findElement(TXT_FIELD_FOR_PWS_LOC).clear();
		((JavascriptExecutor)RFWebsiteDriver.driver).executeScript("window.focus();");
		driver.enterValueUsingJS(TXT_FIELD_FOR_PWS_LOC,text);
		logger.info("Text entered as: "+text);
		driver.switchTo().defaultContent();
		return originalText;
	}

	public String getOriginalAndEnterNewTextForCategoryOnCorp(String text){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		logger.info("Switched to frame successfully");
		driver.waitForElementPresent(TXT_FIELD_FOR_CORP_LOC);
		driver.pauseExecutionFor(5000);
		String originalText = driver.getText(TXT_FIELD_FOR_CORP_LOC);
		if(originalText.equalsIgnoreCase(" ")){
			originalText="AutomationCreatedText"+randomNum;	
			logger.info("Created original text is "+originalText);
		}
		driver.findElement(TXT_FIELD_FOR_CORP_LOC).clear();
		((JavascriptExecutor)RFWebsiteDriver.driver).executeScript("window.focus();");
		driver.enterValueUsingJS(TXT_FIELD_FOR_CORP_LOC,text);
		logger.info("Text entered as: "+text);
		driver.switchTo().defaultContent();
		return originalText;
	}

	public String  getOriginalAndEnterNewTextOnPWS(String text){
		String originalText = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		logger.info("Switched to frame successfully");
		driver.waitForElementPresent(TXT_FIELD_FOR_PWS_LOC);
		driver.pauseExecutionFor(5000);
		originalText = driver.getText(TXT_FIELD_FOR_PWS_LOC);
		if(originalText.equalsIgnoreCase(" ")){
			originalText="AutomationCreatedText"+randomNum;	
			logger.info("Created original text is "+originalText);
		}
		driver.findElement(TXT_FIELD_FOR_PWS_LOC).clear();
		((JavascriptExecutor)RFWebsiteDriver.driver).executeScript("window.focus();");
		driver.enterValueUsingJS(TXT_FIELD_FOR_PWS_LOC,text);
		logger.info("Text entered as: "+text);
		driver.switchTo().defaultContent();
		return originalText;
	}

	public void selectPreviousMonthFromCurrentDate(){
		driver.click(PREV_BUTTON_FROM_CALENDAR_LOC, "Prev Button From Calendar");
	}

	public String getOriginalAndEnterNewTextOnCorp(String text){
		String originalText = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		logger.info("Switched to frame successfully");
		driver.waitForElementPresent(TXT_FIELD_LOC);
		driver.pauseExecutionFor(5000);
		originalText = driver.getText(TXT_FIELD_LOC);
		if(originalText.equalsIgnoreCase(" ")){
			originalText="AutomationCreatedText"+randomNum;	
			logger.info("Created original text is "+originalText);
		}
		driver.findElement(TXT_FIELD_LOC).clear();
		((JavascriptExecutor)RFWebsiteDriver.driver).executeScript("window.focus();");
		driver.enterValueUsingJS(TXT_FIELD_LOC,text);
		logger.info("Text entered as: "+text);
		driver.switchTo().defaultContent();
		return originalText;
	}

	public boolean isCategoryNamePresent(String categoryName){
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(By.xpath(String.format(categoryNameToAssert, categoryName)));
	}
	
	public String getResourceCategory(String name){
		driver.isElementPresent(By.xpath(String.format(resoursceCategory, name)));
		driver.pauseExecutionFor(2000);
		String category = driver.findElement(By.xpath(String.format(resoursceCategory, name))).getText();
		logger.info("Resoursce category is: "+category);
		return category;
	}	
	

public String getResoursceStatus(String titleName){
		driver.waitForElementPresent(By.xpath(String.format(resoursceStatus, titleName)));
		driver.pauseExecutionFor(2000);
		//driver.isElementPresent(By.xpath(String.format(resoursceStatus, titleName)));
		String status = driver.findElement(By.xpath(String.format(resoursceStatus, titleName))).getText();
		logger.info("Resoursce status is: "+status);
		return status;
	}

}