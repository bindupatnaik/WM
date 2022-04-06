
package com.rf.pages.website.LSD;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import com.rf.core.driver.website.RFWebsiteDriver;

public class LSDHomePage extends LSDRFWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(LSDHomePage.class.getName());

	public LSDHomePage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private String subLinkUnderLinksLoc = "//a[contains(text(),'%s')]";
	private String textLoc = "//div[@id='sub-stage']//*[contains(text(),'%s')]";
	private String subLinkUnderReportsSectionLoc = "//h3[contains(text(),'Reports')]/following::section[@id='%s']";
	private String spanTextLoc = "//span[contains(text(),'%s')]";
	private String subReportUnderReportLoc = "//span[contains(text(),'%s')]";
	private  String filterNameLoc = "//span[contains(text(),'%s')]";
	private String subCategoryOfTeamLoc = "//h1[contains(text(),'With Your Team')]/following::div[1]/descendant::a[%s]/span";
	private String isTextPresentAtHeader = "//h1[contains(text(),'%s')]";
	private String levelTabLoc = "//a[@id='tabs-level-%s']//span[contains(text(),'(')]";
	private String personalOrderValueAccordingToLabel = "//th[text()='%s']/following::span[1]";
	private String pastOrderValueAccordingToLabel = "//div[@class='history-orders']/descendant::th[text()='%s'][1]/following::span[1]";
	private String currentTitleValueWithTitleNameLoc = "//span[text()='%s']/following::span[text()='Current'][1]/following::*[1]";
	private String organizationUpdatesNumberDescLoc = "//h1[contains(text(),'What these numbers mean')]/following-sibling::div[1]/p[contains(text(),'%s')]"; 
	private String organizationUpdatesInfoLoc = "//span[contains(text(),'%s')]/../preceding-sibling::span[1]";
	private String titleHistoryHeadingLoc = "//span[contains(text(),'%s')]";
	private String subLinkUnderMyPageSectionLoc = "//h3[contains(text(),'My Pages')]/following::section[@id='%s']";
	private String topNavigationDDMenuLoc = "//div[@id='rf-nav-wrapper']/descendant::span[text()='%s'][1]";
	private String titleNameOnTitleHistoryLoc = "//span[contains(text(),'%s')]/following::span[2]";

	private static final By  CLOSE_POP_UP = By.xpath("//span[@class='dark-grey xx-large-text icon-close']");
	private static final By WHO_MAY_NOT_PROMOTE_LOC = By.xpath("//div[@class='background-white row']/descendant::a[@href='/#/team/need-to-catch-up']/descendant::span[2]");
	private static final By FPL_PAGE_HEADER_LOC = By.xpath("//header[text()='Flat Performance Lineage']");
	private static final By USER_PROFILE_ICON_LOC = By.xpath("//div[@class='fpl__avatar']");
	private static final By LEVEL_ONE_MEMBERS_LOC = By.xpath("//div[@class='fpl__scheme']/descendant::div[@class='fpl-item__info']");
	private static final By FIRST_PC_LEG_UNDER_CONSULTANT_LOC = By.xpath("//div[@class='fpl__scheme']/descendant::div[contains(@class,'square--blue')][1]/following::div[1]/div[1]");
	private static final By UPCOMMING_ORDER_HEADER_PC_PROFILE_PAGE_LOC = By.xpath("//h2[contains(text(),'Upcoming Order')]");
	private static final By ORDER_HISTORY_HEADER_PC_PROFILE_PAGE_LOC = By.xpath("//h2[contains(text(),'Order History')]");
	private static final By CONTACT_INFORMATION_HEADER_PC_PROFILE_PAGE_LOC = By.xpath("//h2[contains(text(),'Contact Information')]");
	private static final By UPCOMMING_ORDER_SECTION_PC_PROFILE_PAGE_LOC = By.xpath("//h2[contains(text(),'Upcoming Order')]/following::div[1]/descendant::table/descendant::th");
	private static final By UPCOMMING_ORDER_STATUS_PC_PROFILE_PAGE_LOC = By.xpath("//h2[contains(text(),'Upcoming Order')]/following::div[1]/descendant::table/descendant::td[1]/span");
	private static final By UPCOMMING_ORDER_NUMBER_PC_PROFILE_PAGE_LOC = By.xpath("//h2[contains(text(),'Upcoming Order')]/following::div[1]/descendant::table/descendant::td[3]/span");
	private static final By UPCOMMING_ORDER_QV_PC_PROFILE_PAGE_LOC = By.xpath("//h2[contains(text(),'Upcoming Order')]/following::div[1]/descendant::table/descendant::td[4]/span");
	private static final By ENROLLED_IN_PULSE_VALUE_LOC = By.xpath("//section[@id='keep-in-mind']/descendant::span[@class='pull-right'][1]/span");
	private static final By NEW_CONSULTANT_LINK_LOC = By.xpath("//h1[contains(text(),'View Your Team Progress')]/following::a[1]/span");
	private static final By FULL_NAME_IN_UPPERCASE_LOC = By.xpath("//div[contains(text(),'Hello')]/following::div[contains(@class,'text-uppercase')][1]");
	private static final By FILTER_LOC = By.xpath("//span[contains(text(),'Sorted by')]");
	private static final By CONSULTANT_ON_MY_LEVEL_1_LOC = By.xpath("//section[@id='organization-updates']/descendant::a[contains(@href,'level')][1]/span");

	private static final By FIRST_SUB_CATEGORY_OF_TEAM_PAGE = By.xpath("//h1[contains(text(),'With Your Team')]/following::div[1]/descendant::a[1]/span");
	private static final By ENROLLED_IN_CRP_VALUE_LOC = By.xpath("//section[@id='keep-in-mind']/descendant::span[@class='pull-right'][2]/span");
	private static final By NO_UPCOMING_ORDER_LOC = By.xpath("//p[contains(text(),'No Upcoming Orders')]");
	private static final By TEXT_UNDER_CHECK_IN_WITH_YOUR_TEAM_LOC = By.xpath("//p[contains(text(),'See the current progress of the members')]");
	private static final By NEW_CONSULTANTS_LINK_LOC = By.xpath("//h1[contains(text(),'View Your Team Progress')]/following::a[1]/span");
	private static final By VIEW_YOUR_TEAM_LINK_LOC = By.xpath("//span[contains(text(),'days')]/following::a[1]/span");

	private static final By TRACK_MY_PROGRAM_PROGRESS_LINK_LOC = By.xpath("//span[contains(text(),'Track my Program Progress')]");
	private static final By ENROLLMENT_SPONSOR_NAME_LOC = By.xpath("//div[contains(text(),'Enrollment Sponsor')]");
	private static final By DATE_UNDER_CIRCLE_LOC = By.xpath("//div[@class='marker-circle']");
	private static final By CURRENT_DATE_LOC = By.xpath("//section[@id='title-history']/descendant::div[contains(@class,'shadow-card padding')][1]/span[1]");
	private static final By ENROLLMENT_DATE_LOC = By.xpath("//section[@id='title-history']/descendant::div[contains(@class,'shadow-card padding')][1]/span[2]");
	private static final By HIGHEST_TITLE_VALUE_LOC = By.xpath("//span[text()='Highest Title']/following::span[2]");

	private static final By COUNT_OF_CONSULTANT_ON_MY_LEVEL_1_LOC = By.xpath("//section[@id='organization-updates']/descendant::a[contains(@href,'level')][1]/descendant::span[1]");
	private static final By COUNT_OF_CONSULTANT_ON_MY_LEVEL_1_AT_TEAM_PAGE_LOC = By.xpath("//div[contains(@class,'team-header')]//span");
	private static final By COUNT_OF_PC_WITH_ORDERS_LOC = By.xpath("//section[@id='organization-updates']/descendant::a[contains(@href,'pcs')][1]/descendant::span[1]");
	private static final By PC_CUSTOMER_WITH_ORDERS_LINK_LOC = By.xpath("//section[@id='organization-updates']/descendant::a[contains(@href,'pcs')][1]");
	private static final By COUNT_OF_PC_CUSTOMER_WITH_ORDERS_AT_PC_ORDERS_PAGE_LOC = By.xpath("//div[contains(@class,'header')]//span");
	private static final By DATE_AND_TIME_STAMP_AT_PAST_ORDERS_PAGE = By.xpath("//div[@class='history-orders']/descendant::p[@class='commission-date'][1]");
	private static final By VIEW_MY_PAST_ORDERS_LINK_LOC = By.xpath("//a[@id='view-past-orders']/span");
	private static final By FIRST_EXPANDABLE_SYMBOL_LOC = By.xpath("//div[@id='sub-stage']/descendant::img[contains(@class,'expandable-header')][1]");
	private static final By EXPANDABLE_DETAIL_LOC = By.xpath("//div[@class='faq-question-answer-container']//p[2]");
	private static final By FIRST_LAST_NAME_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//a//div[1]//span[1]");
	private static final By QUALIFICATION_TITLE_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//a//div[2]//span[contains(@class,'text')]");
	private static final By SV_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//p[contains(text(),'SV')]");
	private static final By PSQV_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//p[contains(text(),'SV')]");
	private static final By RELATION_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//div[contains(text(),'on your')]");
	private static final By TWO_INITIAL_ICON_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//div[contains(@class,'rf-circle-bg-active')]");
	private static final By CONTACT_ICON_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//span[contains(@class,'icon-contact')]");


	private static final By SV_VALUE_FROM_CARD_DETAILS_LOC = By.xpath("//div[text()='SV']/preceding::div[1]");
	private static final By PSQV_VALUE_FROM_CARD_DETAILS_LOC = By.xpath("//div[text()='PSQV']/preceding::div[1]");
	private static final By L1_L2_QUALIFYING_VOLUME_LOC = By.xpath("//span[contains(text(),'L1+L2')]/preceding::span[1]");
	private static final By L1_L6_QUALIFYING_VOLUME_LOC = By.xpath("//span[contains(text(),'L1-L6')]/preceding::span[1]");
	private static final By COLOR_OF_SV_RING_LESS_THAN_100_LOC = By.xpath("//*[@id='progressChart']/*[@class='arc-sv-less']");
	private static final By COLOR_OF_SV_RING_GREATER_THAN_100_LOC = By.xpath("//*[@id='progressChart']/*[@class='arc-sv']");
	private static final By COLOR_OF_PSQV_RING_LESS_THAN_600_LOC = By.xpath("//*[@id='progressChart']/*[@class='arc-psqv-less']");
	private static final By COLOR_OF_PSQV_RING_GREATER_THAN_600_LOC = By.xpath("//*[@id='progressChart']/*[@class='arc-psqv']");
	private static final By SPONSOR_NAME_FROM_MY_SPONSOR_SECTION_LOC = By.xpath("//div[contains(@class,'position-relative')]/descendant::span[contains(@class,'medium-small')][1]");
	private static final By SPONSOR_ADDRESS_FROM_MY_SPONSOR_SECTION_LOC = By.xpath("//div[contains(@class,'position-relative')]/descendant::span[contains(@class,'medium-small')][2]");
	private static final By INITIAL_NAME_CONTAINER_LOC = By.xpath("//div[@id='stage']/descendant::div[contains(@class,'circle')][1]");
	private static final By FIRST_NAME_OVERVIEW_SECTION_LOC = By.xpath("//div[text()='Hello,']/following-sibling::div[contains(@class,'text-uppercase')]");
	private static final By MY_AUSTRALIA_LAUNCH_PAGE_LOC = By.xpath("//div[@class='login-block']/a[text()='LOG IN']");
	private static final By HOME_PAGE_OF_SOLUTION_TOOL_LOC = By.id("search_key"); 
	private static final By ESTIMATED_SV_LOC = By.xpath("//span[contains(@id,'estimated-sv')]");
	private static final By ESTIMATED_PSQV_LOC = By.xpath("//span[contains(@id,'estimated-psqv')]");
	private static final By MY_SPONSOR_SECTION_LOC = By.id("my-sponsor");
	private static final By ORGANIZATION_UPDATES_SECTION_LOC = By.id("organization-updates");
	private static final By WHAT_THESE_NUMBERS_MEAN_LINK_IN_ORG_UPDATES_SEC_LOC = By.xpath("//section[contains(@id,'organization-updates')]//span[contains(text(),'What these numbers mean')][1]/ancestor::a[1]");
	private static final By VIEW_TITLE_HISTORY_LOC = By.xpath("//span[contains(text(),'View Title History')]/ancestor::a[1]");
	private static final By VIEW_TITLE_HISTORY_DESC_LOC = By.xpath("//h1[contains(text(),'Title History')]/following-sibling::div[1]/p");
	private static final By TIMELINE_HEADING_LOC = By.xpath("//h5[contains(text(),'Timeline')]");
	private static final By LINKS_IN_TOP_NAV_LOC = By.xpath("//div[@id='rf-nav-wrapper']/descendant::span[text()='Links'][1]");
	//private static final By RF_CONNECTION_PAGE_HEADER_LOC = By.xpath("//h1[contains(text(),'WELCOME TO THE RF CONNECTION')]");
	private static final By RF_CONNECTION_PAGE_HEADER_LOC = By.xpath("//a[@href='/rfconnection']/img");
	private static final By RF_CONNECTION_HOME_MENU_LOC = By.xpath("//ul[@id='menu-rfc-menu']//a[text()='Home']");
	private static final By RF_CONNECTION_US_MENU_LOC = By.xpath("//ul[@id='menu-rfc-menu']//a[text()='US']");
	private static final By RF_JOURNEY_PAGE_HEADER_LOC = By.xpath("//span[contains(text(),'Welcome to R+F Journey')]");
	private static final By BUSINESS_CARD_LINK_LOC = By.xpath("//a[contains(text(),'Business Cards')]");
	private static final By RF_PAYDAY_WELCOME_MSG_LOC = By.xpath("//span[@class='landing-page-welcome-message']/span[contains(text(),'Payday')]");
	private static final By PULSE_SHARE_HEADING_LOC = By.xpath("//div[contains(text(),'PULSE SHARE')]");
	private static final By LOGIN_ID_TF_LOC = By.id("inputLoginID");
	private static final By LEAD_THE_WAY_HEADER_IN_TOP_NAV_LOC = By.xpath("//header[@id='rf-primary-header']");
	private static final By LOGOUT_BTN = By.id("nav-logout") ;
	private static final By VIEW_MY_ORDERS_LINK = By.xpath("//span[text()='View my orders']") ;
	private static final By CUSTOMERS_LINK = By.id("nav-customers");
	private static final By ORDERS_LINK = By.id("nav-orders");
	private static final By FEEDBACK_LINK = By.id("nav-feedback");
	private static final By RF_CONSULTANT_EVENT_PAGE_LOC = By.xpath("//h1[text()='R+F CONSULTANT-LED EVENT CALENDER']");
	private static final By RF_GETTING_STARTED_PAGE_LOC = By.id("username");
	private static final By RF_CORPORATE_EVENT_PAGE_LOC = By.xpath("//h2[text()='Event Calendar']");
	private static final By RF_COMMS_CORNER_PAGE_LOC = By.id("headerBanner");
	private static final By PULSE_IN_TOP_NAV_LOC = By.xpath("//div[@id='rf-nav-wrapper']/descendant::span[text()='Pulse'][1]");
	private static final By PULSE_MENU_IN_TOP_NAV_LOC = By.xpath("//div[@id='rf-nav-wrapper']/descendant::span[text()='Pulse'][1]");
	private static final By HOME_PAGE_OF_PULSE_LOC = By.xpath("//h4[text()='Qualification Title:']/preceding::div[text()='Hello,']");
	private static final By TEAM_PAGE_OF_PULSE_LOC = By.xpath("//h1[contains(text(),'Check In With Your Team')]");
	private static final By CUSTOMER_PAGE_OF_PULSE_LOC = By.xpath("//h1[contains(text(),'Build Customer Loyalty')]");
	private static final By ORDER_PAGE_OF_PULSE_LOC = By.xpath("//p[contains(text(),'View all orders')]");
	private static final By FAQ_PAGE_OF_PULSE_LOC = By.xpath("//div[@id='sub-stage']//h1[contains(text(),'FAQ')]");
	private static final By HELLO_TEXT_OVERVIEW_SECTION_LOC = By.xpath("//div[text()='Hello,']");
	private static final By QUALIFICATION_TITLE_OVERVIEW_SECTION_LOC = By.xpath("//h4[text()='Qualification Title:']/following-sibling::div[1]");
	private static final By ENROLLMENT_DATE_OVERVIEW_SECTION_LOC = By.xpath("//h4[text()='Qualification Title:']/following-sibling::div[2]");
	private static final By PULSE_INFO_UNDER_KEEP_IN_MIND_LOC = By.xpath("//section[@id='keep-in-mind']/div[1]/span/span");
	private static final By CRP_INFO_UNDER_KEEP_IN_MIND_LOC = By.xpath("//section[@id='keep-in-mind']/div[2]/span/span");
	private static final By GRACE_BALANCE_INFO_UNDER_KEEP_IN_MIND_LOC = By.xpath("//section[@id='keep-in-mind']/div[3]/div[1]/span/span");
	private static final By ANNIVERSARY_MONTH_UNDER_KEEP_IN_MIND_LOC = By.xpath("//section[@id='keep-in-mind']/div[3]/div[2]/span");
	private static final By WHAT_THESE_NUMBERS_MEAN_LINK_LOC = By.xpath("//span[contains(@id,'estimated-sv')]/following::span[contains(text(),'What these numbers mean')][1]/ancestor::a[1]");
	private static final By SV_PSQV_DESC_LOC = By.xpath("//h1[contains(text(),'What these numbers mean')]/following-sibling::div[1]/p");
	private static final By NAME_INITIALS_LOC = By.xpath("//section[@id='my-sponsor']//div[contains(@class,'initials pull-right')]//span");
	private static final By SPONSOR_NAME_LOC = By.xpath("//section[@id='my-sponsor']//div[contains(@class,'initials pull-right')]/following::div[1]/span[1]");
	private static final By STATE_INITIALS_LOC = By.xpath("//section[@id='my-sponsor']//div[contains(@class,'initials pull-right')]/following::div[1]/span[2]");
	private static final By NAME_INITIALS_CONTAINER_LOC = By.xpath("//section[@id='my-sponsor']//div[contains(@class,'initials pull-right')]//span/..");
	private static final By EC_LEGS_AND_TITLES_SEC_LOC = By.id("ec-legs-titles");
	private static final By EC_LEGS_VALUE_LOC = By.xpath("//span[contains(text(),'EC Legs')]/../preceding-sibling::span[1]");
	private static final By ESTIMATED_DAYS_LEFT_FOR_COMM_LOC = By.xpath("//span[contains(text(),'commissions period')]/..");
	private static final By PROMOTED_CONSULTANT_TO_EC_LOC = By.xpath("//span[text()='have promoted to EC again.']/preceding::span[2]");
	private static final By CONSULTANTS_GETTING_THERE_LOC = By.xpath("//span[text()='are getting there.']/preceding::span[2]");
	private static final By NEW_ENROLLED_CONSULTANT_LOC = By.xpath("//h3[contains(text(),'View Your Team’s Progress')]/following::p[contains(text(),'New Consultants')]");
	private static final By CONSULTANTCARD_FULLNAME = By.xpath("//div[contains(@class,'nav-consultant')]/nav-kpi-card//h3");
	private static final By CONSULTANTCARD_TITLE = By.xpath("//div[contains(@class,'nav-consultant')]/nav-kpi-card//h4");
	private static final By CONSULTANTCARD_SV = By.xpath("//div[contains(@class,'nav-consultant')]/nav-kpi-card/div/div/div/div");
	private static final By CONSULTANTCARD_PSQV = By.xpath("//div[contains(@class,'nav-consultant')]/nav-kpi-card/div/div/div[2]/div");
	private static final By QUALIFICATION_TITLE_FROM_CARD_SECTION_LOC = By.xpath("//div[@id='nav-section-pulse']//h4");
	private static final By RECOGNITION_TITLE_NAME_LOC = By.xpath("//span[contains(text(),'Recognition Title')]/following::div[1]");
	private static final By NUMBER_OF_SV_LOC = By.id("kpi-chart-value-sv");
	private static final By NUMBER_OF_PSQV_LOC = By.id("kpi-chart-value-psqv");
	private static final By PROGRAM_CARD_LOC = By.id("progressChart"); 

	public void clickViewMyOrdersLink(){
		driver.quickWaitForElementPresent(VIEW_MY_ORDERS_LINK);
		driver.click(VIEW_MY_ORDERS_LINK,"");
		driver.waitForLSDLoaderAnimationImageToDisappear();
	}

	public LSDCustomerPage clickCustomersLink(){
		driver.waitForElementPresent(CUSTOMERS_LINK);
		driver.click(CUSTOMERS_LINK,"");
		logger.info("Customers link clicked from main menu");
		driver.waitForLSDLoaderAnimationImageToDisappear();
		return new LSDCustomerPage(driver);
	}

	public LSDOrderPage clickOrdersLink(){
		driver.waitForElementPresent(ORDERS_LINK);
		driver.click(ORDERS_LINK,"");
		logger.info("Orders link clicked from main menu");
		driver.waitForLSDLoaderAnimationImageToDisappear();
		return new LSDOrderPage(driver);
	}
	public LSDFeedbackPage clickFeedbackLink(){
		driver.click(FEEDBACK_LINK,"");
		logger.info("Feedback link clicked from main menu");
		driver.pauseExecutionFor(2000);
		return new LSDFeedbackPage(driver);
	}


	public boolean isRFConsultantEventPagePresent(){
		driver.waitForElementToBeVisible(RF_CONSULTANT_EVENT_PAGE_LOC,20);
		return driver.IsElementVisible(driver.findElement(RF_CONSULTANT_EVENT_PAGE_LOC ));
	}

	public String getColorOfNumbersForSV(){
		String colorCode = driver.getAttribute(NUMBER_OF_SV_LOC, "style");
		logger.info("color code of SV is "+colorCode);
		return colorCode;
	}

	public String getColorOfNumbersForPSQV(){
		String colorCode = driver.getAttribute(NUMBER_OF_PSQV_LOC, "style");
		logger.info("color code of PSQV is "+colorCode);
		return colorCode;
	}

	public boolean verifySponsorSectionIsPresent(){
		return driver.isElementVisible(MY_SPONSOR_SECTION_LOC);
	}


	public boolean verifyOrganizationUpdatesSectionIsPresent(){
		return driver.isElementVisible(ORGANIZATION_UPDATES_SECTION_LOC);
	}


	public void clickWhatTheseNumbersMeanLink(){
		driver.click(WHAT_THESE_NUMBERS_MEAN_LINK_LOC,"");
		logger.info("Clicked on What these Numbers mean Link");
	}

	public String getSVAndPSQVDescription(){
		return driver.getText(SV_PSQV_DESC_LOC);
	}

	public void clickWhatTheseNumbersMeanLinkInOrgUpdatesSection(){
		driver.click(WHAT_THESE_NUMBERS_MEAN_LINK_IN_ORG_UPDATES_SEC_LOC,"");
		logger.info("Clicked on What these Numbers mean Link in Org Updates Section");
	}

	public boolean verifyOrgainizationUpdatesNumbersDescIsPresent(String desc){
		return driver.isElementVisible(By.xpath(String.format(organizationUpdatesNumberDescLoc,desc)));
	}

	public boolean verifySponsorNameIsPresentInBoldLetters(){
		return driver.getAttribute(SPONSOR_NAME_LOC,"class").contains("weight-medium");
	}

	public boolean verifyStateInitialsIsPresentInLightFont(){
		return driver.getAttribute(STATE_INITIALS_LOC,"class").contains("weight-light");
	}

	public boolean verifyNameInitialsIsPresent(){
		driver.waitForElementToBeVisible(NAME_INITIALS_LOC,10);
		if(driver.isElementVisible(NAME_INITIALS_LOC)){
			driver.pauseExecutionFor(3000);
			return !driver.getText(NAME_INITIALS_LOC).isEmpty(); 
		}
		return false;
	}

	public boolean verifyNameInitialsArePresentInCircleContainer(){
		return driver.getAttribute(NAME_INITIALS_CONTAINER_LOC,"class").contains("circle");
	}

	public boolean verifySponsorNameIsPresent(){
		driver.waitForElementToBeVisible(SPONSOR_NAME_LOC,10);
		if(driver.isElementVisible(SPONSOR_NAME_LOC)){
			driver.pauseExecutionFor(3000);
			return !driver.getText(SPONSOR_NAME_LOC).isEmpty(); 
		}
		return false;
	}

	public boolean verifyStateAndCountryDetailsArePresent(){
		driver.waitForElementToBeVisible(STATE_INITIALS_LOC,10);
		if(driver.isElementVisible(STATE_INITIALS_LOC)){
			driver.pauseExecutionFor(3000);
			return !driver.getText(STATE_INITIALS_LOC).isEmpty(); 
		}
		return false;
	}

	public void clickViewTitleHistoryLink(){
		driver.click(VIEW_TITLE_HISTORY_LOC,"");
		logger.info("Clicked View Title History Link");
	}

	public boolean verifyViewTitleHistoryDescIsPresent(){
		if(driver.isElementVisible(VIEW_TITLE_HISTORY_DESC_LOC)){
			driver.pauseExecutionFor(3000);
			return !driver.getText(VIEW_TITLE_HISTORY_DESC_LOC).isEmpty();
		}
		return false;
	}

	public boolean verifyInfoOnViewTitleHistoryPage(String heading){
		return driver.isElementVisible(By.xpath(String.format(titleHistoryHeadingLoc,heading)));
	}

	public boolean verifyTimelineHeaderIsPresent(){
		return driver.isElementVisible(TIMELINE_HEADING_LOC);
	}

	public boolean isLinkPresentUnderMyPageSection(String linkName){
		driver.waitForElementToBeVisible(By.xpath(String.format(subLinkUnderMyPageSectionLoc,linkName)),5);
		return driver.IsElementVisible(driver.findElement(By.xpath(String.format(subLinkUnderMyPageSectionLoc, linkName)) ));
	}

	public void clickLinkUnderMyPageSection(String linkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(subLinkUnderMyPageSectionLoc,linkName)));
		driver.click(By.xpath(String.format(subLinkUnderMyPageSectionLoc, linkName)),"");
		logger.info("Link "+linkName+" clicked under my page section");
	}

	public boolean isTopNavigationHeaderDDMenuPresent(String menuName){
		driver.waitForElementToBeVisible(By.xpath(String.format(topNavigationDDMenuLoc,menuName)),5);
		return driver.IsElementVisible(driver.findElement(By.xpath(String.format(topNavigationDDMenuLoc, menuName))));
	}

	public void selectSubMenuFromPulseMenu(String subLink){
		driver.click(PULSE_MENU_IN_TOP_NAV_LOC,"");
		logger.info("Clicked on Pulse Submenu from Top Navigation");
		driver.waitForElementToBeVisible(By.xpath(String.format(subLinkUnderLinksLoc,subLink)),10);
		driver.click(By.xpath(String.format(subLinkUnderLinksLoc,subLink)),"");
		logger.info("SubMenu Link : " + subLink+ " clicked");
		driver.waitForSpinImageToDisappearPulse(10);
	}

	public String getConsultantCardFullName(){
		driver.click(PULSE_MENU_IN_TOP_NAV_LOC,"");
		return driver.getText(CONSULTANTCARD_FULLNAME);
	}

	public String getConsultantCardTitle(){
		driver.click(PULSE_MENU_IN_TOP_NAV_LOC,"");
		return driver.getText(CONSULTANTCARD_TITLE);
	}

	public String getConsultantCardSV(){
		driver.click(PULSE_MENU_IN_TOP_NAV_LOC,"");
		return driver.getText(CONSULTANTCARD_SV);
	}

	public String getConsultantCardPSQV(){
		driver.click(PULSE_MENU_IN_TOP_NAV_LOC,"");
		return driver.getText(CONSULTANTCARD_PSQV);
	}

	public boolean isHomePageOfPulsePresent(){
		driver.waitForElementToBeVisible(HOME_PAGE_OF_PULSE_LOC,5);
		return driver.IsElementVisible(driver.findElement(HOME_PAGE_OF_PULSE_LOC));
	}
	public boolean isTeamPageOfPulsePresent(){
		driver.waitForElementToBeVisible(TEAM_PAGE_OF_PULSE_LOC,5);
		return driver.IsElementVisible(driver.findElement(TEAM_PAGE_OF_PULSE_LOC));
	}

	public boolean isCustomerPageOfPulsePresent(){
		driver.waitForElementToBeVisible(CUSTOMER_PAGE_OF_PULSE_LOC,5);
		return driver.IsElementVisible(driver.findElement(CUSTOMER_PAGE_OF_PULSE_LOC));
	}

	public boolean isOrderPageOfPulsePresent(){
		driver.waitForElementToBeVisible(ORDER_PAGE_OF_PULSE_LOC,5);
		return driver.IsElementVisible(driver.findElement(ORDER_PAGE_OF_PULSE_LOC));
	}

	public boolean isFAQPageOfPulsePresent(){
		driver.waitForElementToBeVisible(FAQ_PAGE_OF_PULSE_LOC,5);
		return driver.IsElementVisible(driver.findElement(FAQ_PAGE_OF_PULSE_LOC));
	}

	public boolean isHelloTextPresentInOverviewSection(){
		driver.waitForElementToBeVisible(HELLO_TEXT_OVERVIEW_SECTION_LOC,5);
		return driver.IsElementVisible(driver.findElement(HELLO_TEXT_OVERVIEW_SECTION_LOC));
	}

	public boolean isQualificationtitlePresentInOverviewSection(){
		driver.waitForElementToBeVisible(QUALIFICATION_TITLE_OVERVIEW_SECTION_LOC,5);
		return !(driver.findElement(QUALIFICATION_TITLE_OVERVIEW_SECTION_LOC).getText().isEmpty());
	}

	public boolean isEnrollmentDatePresentInOverviewSection(){
		driver.waitForElementToBeVisible(ENROLLMENT_DATE_OVERVIEW_SECTION_LOC,5);
		return driver.findElement(ENROLLMENT_DATE_OVERVIEW_SECTION_LOC).getText().contains("Enrolled:");
	}

	public String getPulseProInfoFromKeepInMindSection(){
		driver.waitForElementToBeVisible(PULSE_INFO_UNDER_KEEP_IN_MIND_LOC,5);
		String pulseStatus = driver.findElement(PULSE_INFO_UNDER_KEEP_IN_MIND_LOC).getText();
		return pulseStatus;
	}

	public String getCRPInfoFromKeepInMindSection(){
		driver.waitForElementToBeVisible(CRP_INFO_UNDER_KEEP_IN_MIND_LOC,5);
		String crpStatus = driver.findElement(PULSE_INFO_UNDER_KEEP_IN_MIND_LOC).getText();
		return crpStatus;
	}

	public String getGraceBalanceInfoFromKeepInMindSection(){
		driver.waitForElementToBeVisible(GRACE_BALANCE_INFO_UNDER_KEEP_IN_MIND_LOC,5);
		return driver.findElement(GRACE_BALANCE_INFO_UNDER_KEEP_IN_MIND_LOC).getText();
	}

	public boolean isAnniversaryMonthPresentUnderKeepInMindSection(){
		driver.waitForElementToBeVisible(ANNIVERSARY_MONTH_UNDER_KEEP_IN_MIND_LOC,5);
		return driver.IsElementVisible(driver.findElement(ANNIVERSARY_MONTH_UNDER_KEEP_IN_MIND_LOC));
	}

	public boolean isSVAndPSQVDescriptionPresent(){
		driver.waitForElementToBeVisible(SV_PSQV_DESC_LOC,10);
		return driver.isElementVisible(SV_PSQV_DESC_LOC);
	}

	public boolean isRFConnectionPageHeaderPresent(){
		driver.waitForElementToBeVisible(RF_CONNECTION_PAGE_HEADER_LOC,20);
		return driver.IsElementVisible(driver.findElement(RF_CONNECTION_PAGE_HEADER_LOC ));
	}

	public boolean isRFConnectionHomeMenuPresent(){
		driver.waitForElementToBeVisible(RF_CONNECTION_HOME_MENU_LOC,20);
		return driver.IsElementVisible(driver.findElement(RF_CONNECTION_HOME_MENU_LOC ));
	}


	public boolean isRFJourneyPageHeaderPresent(){
		driver.waitForElementToBeVisible(RF_JOURNEY_PAGE_HEADER_LOC,20);
		return driver.IsElementVisible(driver.findElement(RF_JOURNEY_PAGE_HEADER_LOC));
	}

	public boolean isBusinessCardLinkPresentOnRFMallPage(){
		driver.waitForElementToBeVisible(BUSINESS_CARD_LINK_LOC,20);
		return driver.IsElementVisible(driver.findElement(BUSINESS_CARD_LINK_LOC));
	}

	public boolean isWelcomeMsgPresentOnRFPaydayPage(){
		driver.waitForElementToBeVisible(RF_PAYDAY_WELCOME_MSG_LOC,20);
		return driver.IsElementVisible(driver.findElement(RF_PAYDAY_WELCOME_MSG_LOC));
	}

	public boolean isPulseShareHeadingPresentOnSharePage(){
		driver.waitForElementToBeVisible(PULSE_SHARE_HEADING_LOC,20);
		return driver.IsElementVisible(driver.findElement(PULSE_SHARE_HEADING_LOC));
	}

	public boolean isLoginIdTFPresentOnSharePage(){
		driver.waitForElementToBeVisible(LOGIN_ID_TF_LOC,20);
		return driver.IsElementVisible(driver.findElement(LOGIN_ID_TF_LOC));
	}

	public boolean isLeadTheWayLinkPresentInTopNav(){
		driver.waitForElementToBeVisible(LEAD_THE_WAY_HEADER_IN_TOP_NAV_LOC,20);
		return driver.IsElementVisible(driver.findElement(LEAD_THE_WAY_HEADER_IN_TOP_NAV_LOC));
	}

	public boolean isRFGettingStartedPagePresent(){
		driver.waitForElementToBeVisible(RF_GETTING_STARTED_PAGE_LOC,20);
		return driver.IsElementVisible(driver.findElement(RF_GETTING_STARTED_PAGE_LOC ));
	}

	public boolean isRFCorporateEventPagePresent(){
		driver.waitForElementToBeVisible(RF_CORPORATE_EVENT_PAGE_LOC,20);
		return driver.IsElementVisible(driver.findElement(RF_CORPORATE_EVENT_PAGE_LOC ));
	}

	public boolean isRFCommsCornerPagePresent(){
		driver.waitForElementToBeVisible(RF_COMMS_CORNER_PAGE_LOC,20);
		return driver.IsElementVisible(driver.findElement(RF_COMMS_CORNER_PAGE_LOC ));
	}

	public boolean isECLegsAndTitlesSectionPresent(){
		return driver.isElementVisible(EC_LEGS_AND_TITLES_SEC_LOC,10);
	}

	public String getECLegsValue(){
		driver.pauseExecutionFor(5000);
		return driver.getText(EC_LEGS_VALUE_LOC).trim();
	}

	public String getOrgainzationUpdatesInfo(String info){
		driver.pauseExecutionFor(7000);
		return driver.getText(By.xpath(String.format(organizationUpdatesInfoLoc,info))).trim();
	}

	public void clickEstimatedSV(){
		driver.waitForElementPresent(ESTIMATED_SV_LOC);
		driver.click(ESTIMATED_SV_LOC,"");
		logger.info("Clicked Estimated SV");
	}

	public String getEstimatedSV(){
		driver.waitForElementPresent(ESTIMATED_SV_LOC);
		String SV = driver.getText(ESTIMATED_SV_LOC).replaceAll(",","");
		logger.info("Estimated SV is "+SV);
		return SV;
	}

	public boolean isTextPresentAtEstimatedPage(String text){
		return driver.isElementPresent(By.xpath(String.format(textLoc, text)));
	}

	public void clickEstimatedPSQV(){
		driver.waitForElementPresent(ESTIMATED_PSQV_LOC);
		driver.click(ESTIMATED_PSQV_LOC,"");
		logger.info("Clicked Estimated PSQV");
	}

	public String getEstimatedPSQV(){
		driver.waitForElementPresent(ESTIMATED_PSQV_LOC);
		String PSQV = driver.getText(ESTIMATED_PSQV_LOC).replaceAll(",","");
		logger.info("Estimated PSQV is "+PSQV);
		return PSQV;
	}

	public boolean isMyAustraliaLaunchPagePresent(){
		driver.waitForElementToBeVisible(MY_AUSTRALIA_LAUNCH_PAGE_LOC,5);
		return driver.IsElementVisible(driver.findElement(MY_AUSTRALIA_LAUNCH_PAGE_LOC));
	}
	public boolean isLinkPresentUnderReportsSection(String linkName){
		driver.waitForElementToBeVisible(By.xpath(String.format(subLinkUnderReportsSectionLoc,linkName)),5);
		return driver.IsElementVisible(driver.findElement(By.xpath(String.format(subLinkUnderReportsSectionLoc, linkName)) ));
	}

	public void clickLinkUnderReportsSection(String linkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(subLinkUnderReportsSectionLoc,linkName)));
		driver.click(By.xpath(String.format(subLinkUnderReportsSectionLoc, linkName)),"");
		logger.info("Link "+linkName+" clicked under Reports section");
	}
	public boolean isLoginPageOfSolutionToolPresent(){
		driver.waitForElementToBeVisible(HOME_PAGE_OF_SOLUTION_TOOL_LOC,5);
		return driver.IsElementVisible(driver.findElement(HOME_PAGE_OF_SOLUTION_TOOL_LOC));
	}

	public String getEstimatedDaysLeftForCommission(){
		driver.waitForElementPresent(ESTIMATED_DAYS_LEFT_FOR_COMM_LOC);
		String EstimatedDays = driver.getText(ESTIMATED_DAYS_LEFT_FOR_COMM_LOC);
		logger.info("Estimated Days left for commission period "+EstimatedDays);
		return EstimatedDays.toLowerCase();
	}
	public String getCountOfConsultantPromotedToEC(){
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(PROMOTED_CONSULTANT_TO_EC_LOC);
		String promotedConsultant = driver.getText(PROMOTED_CONSULTANT_TO_EC_LOC);
		logger.info("Count Of promoted consultant to EC : "+promotedConsultant);
		return promotedConsultant;
	}
	public String getCountOfConsultantGettingThere(){
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(CONSULTANTS_GETTING_THERE_LOC);
		String consultantGettingThere = driver.getText(CONSULTANTS_GETTING_THERE_LOC);
		logger.info("Count Of consultant who doesn't met EC criteria : "+consultantGettingThere);
		return consultantGettingThere;
	}
	public String getCountOfNewEnrolledConsultants(){
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(NEW_ENROLLED_CONSULTANT_LOC);
		String newlyEnrolledConsultant = driver.getText(NEW_ENROLLED_CONSULTANT_LOC);
		logger.info("Count Of newly enrolled consultant : "+newlyEnrolledConsultant);
		return newlyEnrolledConsultant;
	}

	public String getQualificationtitleFromOverviewSection(){
		driver.waitForElementToBeVisible(QUALIFICATION_TITLE_OVERVIEW_SECTION_LOC,5);
		return driver.getText(QUALIFICATION_TITLE_OVERVIEW_SECTION_LOC, "Qualification title in overview section");
	}

	public String getQualificationTitleFromTitleHistroy(String titleName){
		return driver.getText(By.xpath(String.format(titleNameOnTitleHistoryLoc,titleName)),"Title name at title history page");
	}

	public boolean isOrginalValueGreaterOrEqualThanEstimate(String originalValue, String estimatedValue){
		double originalVal = Double.valueOf(originalValue);
		double estimatedVal = Double.valueOf(estimatedValue);

		return originalVal>=estimatedVal;
	}

	public String getEnrolledDateFromOverviewSection(){
		driver.waitForElementToBeVisible(ENROLLMENT_DATE_OVERVIEW_SECTION_LOC,5);
		return driver.getText(ENROLLMENT_DATE_OVERVIEW_SECTION_LOC, "Enrolled in overview section").split("\\:")[1];
	}

	public String convertDateIntoDBFormat(String date){
		String completeDate[] = date.split("\\/");
		String month = completeDate[0].trim();
		String day =completeDate[1];
		String year = completeDate[2];
		System.out.println("Day is "+day);
		System.out.println("Month is "+month);
		System.out.println("Year is "+year);
		if(month.length()==1){
			month="0"+month;
		}
		System.out.println("Date is "+year+"-"+month+"-"+day);
		return year+"-"+month+"-"+day;
	}

	public boolean isFirstNamePresentInUpperCase(){
		driver.waitForElementToBeVisible(FIRST_NAME_OVERVIEW_SECTION_LOC,5);
		return driver.isElementVisible(FIRST_NAME_OVERVIEW_SECTION_LOC);
	}

	public boolean isInitialNameContainerPresent(){
		driver.waitForElementToBeVisible(INITIAL_NAME_CONTAINER_LOC,5);
		return driver.isElementVisible(INITIAL_NAME_CONTAINER_LOC);
	}

	public String getInitialName(){
		return driver.getText(INITIAL_NAME_CONTAINER_LOC, "Initail letter of first and last name");
	}

	public String getFirstLetterFromString(String name){
		return name.substring(0,1);
	}

	public void clickReportLinks(String reportName){
		driver.click(By.xpath(String.format(spanTextLoc, reportName)), reportName+"clikced");
		driver.waitForPageLoad();
	}

	public String getSponsorNameFromMySponsorSection(){
		return driver.getText(SPONSOR_NAME_FROM_MY_SPONSOR_SECTION_LOC,"Sponsor name is ");
	}

	public String getSponsorAddressFromMySponsorSection(){
		return driver.getText(SPONSOR_ADDRESS_FROM_MY_SPONSOR_SECTION_LOC,"Sponsor address is ");
	}

	public void clickReportLinks(String reportName, String parentWindowID){
		driver.click(By.xpath(String.format(spanTextLoc, reportName)), reportName+"clikced");
		driver.waitForPageLoad();
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			String childWindowID=it.next();
			if(!parentWindowID.equalsIgnoreCase(childWindowID)){
				driver.switchTo().window(childWindowID);
				logger.info("navigate to report tab");
				driver.pauseExecutionFor(5000);
			}
		}
	}

	public String getRecoginitionTitleFromTitleHistroy(){
		return driver.getText(RECOGNITION_TITLE_NAME_LOC,"Title name at title history page");
	}

	public boolean isColorOfSVRedForLessThan100(){
		return driver.isElementPresent(COLOR_OF_SV_RING_LESS_THAN_100_LOC);
	}

	public boolean isColorOfSVGreenForGreaterThan100(){
		return driver.isElementPresent(COLOR_OF_SV_RING_GREATER_THAN_100_LOC);
	}
	public boolean isColorOfPSQVRedForLessThan600(){
		return driver.isElementPresent(COLOR_OF_PSQV_RING_LESS_THAN_600_LOC);
	}
	public boolean isColorOfPSQVGreenForGreaterThan600(){
		return driver.isElementPresent(COLOR_OF_PSQV_RING_GREATER_THAN_600_LOC);
	}

	public void clickConsultantOnMyLevel1Link(){
		driver.click(CONSULTANT_ON_MY_LEVEL_1_LOC, "consultant on my level 1");
		driver.waitForLSDLoaderAnimationImageToDisappear();
	}

	public void clickPCCustomerWithOrdersLink(){
		driver.click(PC_CUSTOMER_WITH_ORDERS_LINK_LOC, "pc customer with orders ");
		driver.waitForLSDLoaderAnimationImageToDisappear();
	}

	public String getCountOfPCCustomerWithOrdersAtPCOrdersPage(){
		driver.waitForPageLoad();
		return driver.getText(COUNT_OF_PC_CUSTOMER_WITH_ORDERS_AT_PC_ORDERS_PAGE_LOC, "count of pc customer with orders at PC order page");
	}

	public boolean isPersonalOrderSectionValuePresent(String label){
		driver.waitForElementPresent(By.xpath(String.format(personalOrderValueAccordingToLabel, label)), 10);
		return 	!(driver.getText(By.xpath(String.format(personalOrderValueAccordingToLabel, label))).isEmpty());
	}

	public String getDateAndTimeStampFromPastOrdersPage(){
		return driver.getText(DATE_AND_TIME_STAMP_AT_PAST_ORDERS_PAGE, "Date and time stamp at past orders page");
	}

	public boolean isPastOrderSectionValuePresent(String label){
		driver.waitForElementPresent(By.xpath(String.format(pastOrderValueAccordingToLabel, label)), 10);
		return 	!(driver.getText(By.xpath(String.format(pastOrderValueAccordingToLabel, label))).isEmpty());
	}

	public void clickViewMyPastOrdersLink(){
		driver.click(VIEW_MY_PAST_ORDERS_LINK_LOC, "View my past order link ");
		driver.waitForLSDLoaderAnimationImageToDisappear();
	}

	public String getL1L6QualifyingVolume(){
		return driver.getText((L1_L6_QUALIFYING_VOLUME_LOC),"L1-L6 qualifying volume").replaceAll(",","");
	}

	public boolean isExpandableSymbolPresent(){
		return driver.isElementPresent(FIRST_EXPANDABLE_SYMBOL_LOC);
	}

	public void clickFirstExpandableSymbolOfReport(){
		driver.click(FIRST_EXPANDABLE_SYMBOL_LOC, "Clicked on first expandable symbol of report");
		driver.pauseExecutionFor(1000);
	}

	public boolean isDetailVisibleAfterClickedOnExpandableSymbol(){
		driver.pauseExecutionFor(2000);
		return driver.isElementVisible(EXPANDABLE_DETAIL_LOC);
	}

	public boolean isFirstAndLastNamePresentAtConsultantCardUnderTeam(){
		driver.waitForElementPresent(FIRST_LAST_NAME_AT_CONSULTANT_CARD_UNDER_TEAM_LOC, 10);
		return 	!(driver.getText(FIRST_LAST_NAME_AT_CONSULTANT_CARD_UNDER_TEAM_LOC).isEmpty());
	}

	public boolean isQualificationTitlePresentAtConsultantCardUnderTeam(){
		return 	!(driver.getText(QUALIFICATION_TITLE_AT_CONSULTANT_CARD_UNDER_TEAM_LOC).isEmpty());
	}

	public boolean isSVPresentAtConsultantCardUnderTeam(){
		return 	!(driver.getText(SV_AT_CONSULTANT_CARD_UNDER_TEAM_LOC).isEmpty());
	}

	public boolean isPSQVPresentAtConsultantCardUnderTeam(){
		return 	!(driver.getText(PSQV_AT_CONSULTANT_CARD_UNDER_TEAM_LOC).isEmpty());
	}

	public boolean isRelationPresentAtConsultantCardUnderTeam(){
		return 	!(driver.getText(RELATION_AT_CONSULTANT_CARD_UNDER_TEAM_LOC).isEmpty());
	}

	public boolean isTwoInitialIconPresentAtConsultantCardUnderTeam(){
		return 	!(driver.getText(TWO_INITIAL_ICON_AT_CONSULTANT_CARD_UNDER_TEAM_LOC).isEmpty());
	}

	public boolean isContactIconPresentAtConsultantCardUnderTeam(){
		return 	driver.isElementPresent(CONTACT_ICON_AT_CONSULTANT_CARD_UNDER_TEAM_LOC);
	}

	public boolean isTrackMyProgramProgressLinkPresent(){
		return 	driver.isElementPresent(TRACK_MY_PROGRAM_PROGRESS_LINK_LOC);
	}

	public String getEnrollmentSponsorName(){
		driver.waitForElementPresent(ENROLLMENT_SPONSOR_NAME_LOC, 10);
		return 	driver.getText(ENROLLMENT_SPONSOR_NAME_LOC,"Enrollment sponsor is");
	}

	public boolean iscurrentTitleValuePresentWithTitleName(String titleName){
		return (!driver.getText(By.xpath(String.format(currentTitleValueWithTitleNameLoc, titleName)),"Current title value with title name"+titleName).isEmpty());
	}

	public boolean isDatePresentUnderCircle(){
		return 	driver.isElementPresent(DATE_UNDER_CIRCLE_LOC);
	}

	public boolean isCurrentAndEnrollmentDatePresent(){
		return 	(!(driver.getText(CURRENT_DATE_LOC).isEmpty() && driver.getText(ENROLLMENT_DATE_LOC).isEmpty()));
	}

	public boolean isUserEnrolledInCRP(){
		driver.waitForElementPresent(ENROLLED_IN_CRP_VALUE_LOC, 10);
		return 	driver.getText(ENROLLED_IN_CRP_VALUE_LOC,"Enrollment in CRP value").toLowerCase().trim().contains("yes");
	}

	public boolean isNoUpcomingOrderPresent(){
		return 	driver.isElementPresent(NO_UPCOMING_ORDER_LOC);
	}

	public boolean isTextPresentUnderCheckInWithYourTeam(){
		return 	driver.isElementPresent(TEXT_UNDER_CHECK_IN_WITH_YOUR_TEAM_LOC);
	}

	public void clickSubCategoryOnTeamPage(String categoryCount){
		driver.waitForElementToBeVisible(By.xpath(String.format(subCategoryOfTeamLoc, categoryCount)), 10);
		driver.click(By.xpath(String.format(subCategoryOfTeamLoc, categoryCount)), categoryCount+" category clicked");
	}

	public boolean isTextPresentAtHeader(String text){
		return 	driver.isElementPresent(By.xpath(String.format(isTextPresentAtHeader, text)));
	}

	public void clickNewConsultantLink(){
		driver.click(NEW_CONSULTANTS_LINK_LOC, "New consultants link ");
	}

	public void clickViewYourTeamLink(){
		driver.waitForSpinImageToDisappearPulse();
		driver.click(VIEW_YOUR_TEAM_LINK_LOC, "view your team link ");
	}

	public boolean isLevelTabPresent(String tabNumber){
		return driver.isElementPresent(By.xpath(String.format(levelTabLoc, tabNumber)));
	}

	public void clickLevelTab(String tabNumber){
		driver.click(By.xpath(String.format(levelTabLoc, tabNumber)), "level tab "+tabNumber);
	}

	public void clickFilter(){
		driver.click(FILTER_LOC, "filter");
	}

	public boolean isFilterNameVisible(String filterName){
		return driver.isElementVisible(By.xpath(String.format(filterNameLoc, filterName)));
	}

	public String getPSQVValue(){
		return driver.getText(NUMBER_OF_PSQV_LOC, "SV value is").replaceAll(",", "");
	}

	public String getL1L2QualifyingVolume(){
		return driver.getText((L1_L2_QUALIFYING_VOLUME_LOC),"L1+L2 qualifying volume").replaceAll(",","");
	}

	public String getSVValueFromCardDetails(){
		Actions action = new Actions(RFWebsiteDriver.driver);
		action.moveToElement(driver.findElement(PULSE_IN_TOP_NAV_LOC)).build().perform();;
		logger.info("Mouse Hover on Pulse Section from Top Navigation");
		return driver.getText((SV_VALUE_FROM_CARD_DETAILS_LOC),"SV value at card");
	}

	public String getPSQVValueFromCardDetails(){
		Actions action = new Actions(RFWebsiteDriver.driver);
		action.moveToElement(driver.findElement(PULSE_IN_TOP_NAV_LOC)).build().perform();;
		logger.info("Mouse Hover on Pulse Section from Top Navigation");
		return driver.getText((PSQV_VALUE_FROM_CARD_DETAILS_LOC),"SV value at card").replaceAll(",","");
	}

	public String getCountOfConsultantOnMyLevel1(){
		driver.waitForSpinImageToDisappearPulse();
		return driver.getText(COUNT_OF_CONSULTANT_ON_MY_LEVEL_1_LOC, "count of consultant on my level 1");
	}

	public String getCountOfPCCustomerWithOrders(){
		driver.waitForSpinImageToDisappearPulse();
		return driver.getText(COUNT_OF_PC_WITH_ORDERS_LOC, "count of pc customer with orders");
	}

	public void clickFirstSubCategoryOfTeamPage(){
		driver.waitForSpinImageToDisappearPulse(15);
		driver.click(FIRST_SUB_CATEGORY_OF_TEAM_PAGE, "First sub category of team page");
	} 

	public boolean isHighestTitleValuePresent(){
		driver.pauseExecutionFor(7000);
		return (!driver.getText(HIGHEST_TITLE_VALUE_LOC).isEmpty());
	} 

	public String getQualificationTitleFromCardDetails(){
		driver.pauseExecutionFor(4000);
		logger.info("Mouse Hover on Pulse Section from Top Navigation");
		String text = driver.getTextByJS(RFWebsiteDriver.driver, QUALIFICATION_TITLE_FROM_CARD_SECTION_LOC, "Title name at card").trim();
		logger.info("Title at card is "+text);
		return text;
	} 

	public void clickNewConsultantsLink(){
		driver.waitForSpinImageToDisappearPulse();
		driver.click(NEW_CONSULTANT_LINK_LOC, "New consultant link");
	} 

	public boolean isFullNamePresentInCaps(){
		driver.waitForElementToBeVisible(FULL_NAME_IN_UPPERCASE_LOC,5);
		return !(driver.findElement(FULL_NAME_IN_UPPERCASE_LOC).getText().isEmpty());
	} 

	public boolean isUserEnrolledInPulse(){
		driver.waitForElementPresent(ENROLLED_IN_PULSE_VALUE_LOC, 10);
		return 	driver.getText(ENROLLED_IN_PULSE_VALUE_LOC,"Enrollment in CRP value").toLowerCase().trim().contains("yes");
	}

	public void clickLinkFromReportSection(String reportName){
		driver.waitForElementToBeVisible(By.xpath(String.format(subReportUnderReportLoc,reportName)),10);
		driver.click(By.xpath(String.format(subReportUnderReportLoc,reportName)),"");
		logger.info(reportName + " is clicked");
	}

	public boolean isUserOnFPLReportPage(){
		return (driver.isElementVisible(FPL_PAGE_HEADER_LOC) && driver.isElementVisible(USER_PROFILE_ICON_LOC));
	} 
	public boolean isAllLevelOneMembersPresent(){
		return driver.areElementsVisible(LEVEL_ONE_MEMBERS_LOC);

	} 
	public String clickAndReturnFirstPCLegUnderConsultant(){
		String firstPCAtLevelOne=driver.getText(FIRST_PC_LEG_UNDER_CONSULTANT_LOC,"First PC");
		driver.waitForElementToBeVisible(FIRST_PC_LEG_UNDER_CONSULTANT_LOC,10);
		driver.click(FIRST_PC_LEG_UNDER_CONSULTANT_LOC,"");
		System.out.println("First PC at level one is "+firstPCAtLevelOne);
		return firstPCAtLevelOne;
	}
	public boolean isPCUserProfilePageUpcommingOrderHeadersPresent(){
		return driver.isElementVisible(UPCOMMING_ORDER_HEADER_PC_PROFILE_PAGE_LOC);			
	} 
	public boolean isPCUserProfilePageOrderHistoryHeadersPresent(){
		return driver.isElementVisible(ORDER_HISTORY_HEADER_PC_PROFILE_PAGE_LOC);		
	} 
	public boolean isPCUserProfilePageContactInformationHeadersPresent(){
		return driver.isElementVisible(CONTACT_INFORMATION_HEADER_PC_PROFILE_PAGE_LOC);			
	} 
	public boolean isPCUserProfilePageUpcommingOrderDetailsPresent(){
		boolean flag=false;
		flag=(driver.findElements(UPCOMMING_ORDER_SECTION_PC_PROFILE_PAGE_LOC).size()==4);
		return 	flag;		
	} 

	public String getUpcommingOrderStatusFromPCProfilePage(){
		return driver.getText(UPCOMMING_ORDER_STATUS_PC_PROFILE_PAGE_LOC);		
	} 

	public String getUpcommingOrderNumberFromPCProfilePage(){
		return driver.getText(UPCOMMING_ORDER_NUMBER_PC_PROFILE_PAGE_LOC);			
	} 

	public String getUpcommingOrderQVFromPCProfilePage(){
		return driver.getText(UPCOMMING_ORDER_QV_PC_PROFILE_PAGE_LOC);			
	} 

	public void selectFilter(String filterName){
		driver.click(By.xpath(String.format(filterNameLoc, filterName)),"filter selected");
	} 

	public void clickTrackMyProgressLink(){
		driver.click(TRACK_MY_PROGRAM_PROGRESS_LINK_LOC, "Track my program progress link");
	} 

	public boolean isTrackMyProgramProgressPagePresent(){
		return driver.isElementVisible(PROGRAM_CARD_LOC);
	}

	public String getCountOfConsultantsWhoMayNotPromoteToEC(){
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(WHO_MAY_NOT_PROMOTE_LOC);
		String whoMayNotPromoteToEC = driver.getText(WHO_MAY_NOT_PROMOTE_LOC);
		logger.info("Count who may not promote consultant : "+whoMayNotPromoteToEC);
		return whoMayNotPromoteToEC;
	}

	public void clickClose()	{
		driver.click(CLOSE_POP_UP, "close pop up");
	}

	public void selectSubMenuFromLinksMenu(String subLink){
		if(driver.isElementVisible(By.id("nav-mobile-menu"), 5)) {
			driver.click(By.id("nav-mobile-menu"), "mobile menu bar");
			driver.pauseExecutionFor(1000);
			driver.clickByJS(RFWebsiteDriver.driver,LINKS_IN_TOP_NAV_LOC, "Links Menu");
		}
		else{
			driver.moveToELement(LINKS_IN_TOP_NAV_LOC);
		}
		driver.click(By.xpath(String.format(subLinkUnderLinksLoc,subLink)),subLink);
		waitForTotalExpectedWindowsOpened(2);
	}

	public String getCountOfConsultantOnMyLevel1AtTeamPage(){
		driver.waitForSpinImageToDisappearPulse();
		driver.pauseExecutionFor(5000);
		return driver.getText(COUNT_OF_CONSULTANT_ON_MY_LEVEL_1_AT_TEAM_PAGE_LOC, "count of consultant on my level 1 at team page");
	}

	public String dbsponsorFirstName(String sponsorName) {
		String arr[] = sponsorName.split (" ");
		String firstName = arr[0];
		return firstName ;
	}

	public String[] dbsponsorName(String sponsorName) {
		String nameArray[] = sponsorName.split (" ");
		if(nameArray.length >2){
			nameArray[1]= nameArray[2];}
		else{
			nameArray[1] = nameArray[1];}
		return nameArray ;
	}

	public boolean istopNavigationPresent()	{
		driver.waitForElementToBeVisible(PULSE_IN_TOP_NAV_LOC,5);
		return driver.isElementVisible(PULSE_IN_TOP_NAV_LOC);
	}

	public String getSVValue(){
		driver.waitForElementToBeVisible(NUMBER_OF_SV_LOC, 100);
		return driver.getText(NUMBER_OF_SV_LOC, "SV value is").replaceAll(",", "");
	}
}