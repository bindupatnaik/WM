package com.rf.pages.website.LSD.dsv;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.website.constants.TestConstants;

public class DSVLSDHomePage extends DSVLSDRFWebsiteBasePage {

	private static final Logger logger = LogManager
			.getLogger(DSVLSDHomePage.class.getName());

	public DSVLSDHomePage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	private String subLinkUnderLinksLoc = "//a[contains(text(),'%s')]";
	private String topNavigationDDMenuLoc = "//div[@id='rf-nav-wrapper']/descendant::span[text()='%s'][1]";
	private String titleNameOnTitleHistoryLoc = "//span[contains(text(),'%s')]/following::span[2]";
	private String spanTextLoc = "//span[contains(text(),'%s')]";
	private String textLoc = "//div[@id='sub-stage']//*[contains(text(),'%s')]";

	private static final By VIEW_ALL_CUSTOMER_LOC = By.xpath("//div[@class='row background-white']/descendant::a");
	private static final By HAVE_ORDER_THIS_MONTH_LOC = By.xpath("//div[@class='background-white row']/descendant::a[@href='/#/pcs/have-orders']");
	private static final By SCHEDULED_ORDER_NEXT_MONTH_LOC = By.xpath("//div[@class='background-white row']/descendant::a[@href='/#/pcs/will-order']");
	private static final By ARE_FURTHER_OUT_LOC = By.xpath("//div[@class='background-white row']/descendant::a[@href='/#/pcs/further-out']");
	private static final By NEW_PC_LOC = By.xpath("//div[@class='background-white row']/descendant::a[@href='/#/pcs/new']");
	private static final By DEACTIVATED_PC_LOC = By.xpath("//div[@class='background-white row']/descendant::a[@href='/#/pc-cancellations']");

	private static final By PULSE_MENU_IN_TOP_NAV_LOC = By.xpath("//div[@id='rf-nav-wrapper']/descendant::span[text()='Pulse'][1]");
	private static final By HOME_PAGE_OF_PULSE_LOC = By.xpath("//h4[text()='Qualification Title:']/preceding::div[text()='Hello,']");
	private static final By TEAM_PAGE_OF_PULSE_LOC = By.xpath("//h1[contains(text(),'Check In With Your Team')]");
	private static final By CUSTOMER_PAGE_OF_PULSE_LOC = By.xpath("//h1[contains(text(),'Build Customer Loyalty')]");
	private static final By ORDER_PAGE_OF_PULSE_LOC = By.xpath("//p[contains(text(),'View all orders')]");
	private static final By ENROLLMENT_DATE_OVERVIEW_SECTION_LOC = By.xpath("//h4[text()='Qualification Title:']/following-sibling::div[2]");
	private static final By HELLO_TEXT_OVERVIEW_SECTION_LOC = By.xpath("//div[text()='Hello,']");
	private static final By FIRST_NAME_OVERVIEW_SECTION_LOC = By.xpath("//div[text()='Hello,']/following-sibling::div[contains(@class,'text-uppercase')]");
	private static final By INITIAL_NAME_CONTAINER_LOC = By.xpath("//div[@id='stage']/descendant::div[contains(@class,'circle')][1]");
	private static final By QUALIFICATION_TITLE_OVERVIEW_SECTION_LOC = By.xpath("//h4[text()='Qualification Title:']/following-sibling::div[1]");
	private static final By VIEW_TITLE_HISTORY_LOC = By.xpath("//span[contains(text(),'View Title History')]/ancestor::a[1]");
	private static final By QUALIFICATION_TITLE_FROM_CARD_SECTION_LOC = By.xpath("//div[@id='nav-section-pulse']//h4");
	private static final By NUMBER_OF_SV_LOC = By.id("kpi-chart-value-sv");
	private static final By COLOR_OF_SV_RING_LESS_THAN_100_LOC = By.xpath("//*[@id='progressChart']/*[@class='arc-sv-less']");
	private static final By NUMBER_OF_PSQV_LOC = By.id("kpi-chart-value-psqv");
	private static final By COLOR_OF_PSQV_RING_LESS_THAN_600_LOC = By.xpath("//*[@id='progressChart']/*[@class='arc-psqv-less']");
	private static final By TRACK_MY_PROGRAM_PROGRESS_LINK_LOC = By.xpath("//span[contains(text(),'Track my Program Progress')]");
	private static final By FIRST_SUB_CATEGORY_OF_TEAM_PAGE = By.xpath("//h1[contains(text(),'With Your Team')]/following::div[1]/descendant::a[1]/span");
	private static final By FIRST_LAST_NAME_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//a//div[1]//span[1]");
	private static final By QUALIFICATION_TITLE_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//a//div[2]//span[contains(@class,'text')]");
	private static final By SV_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//p[contains(text(),'SV')]");
	private static final By PSQV_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//p[contains(text(),'SV')]");
	private static final By RELATION_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//div[contains(text(),'on your')]");
	private static final By TWO_INITIAL_ICON_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//div[contains(@class,'rf-circle-bg-active')]");
	private static final By CONTACT_ICON_AT_CONSULTANT_CARD_UNDER_TEAM_LOC = By.xpath("//div[@class='consultants row']/descendant::section[contains(@id,'team-card')][1]//span[contains(@class,'icon-contact')]");


	public void selectSubMenuFromPulseMenu(String subLink){
		driver.click(PULSE_MENU_IN_TOP_NAV_LOC,"");
		logger.info("Clicked on Pulse Submenu from Top Navigation");
		driver.waitForElementToBeVisible(By.xpath(String.format(subLinkUnderLinksLoc,subLink)),10);
		driver.click(By.xpath(String.format(subLinkUnderLinksLoc,subLink)),"");
		logger.info("SubMenu Link : " + subLink+ " clicked");
		driver.waitForSpinImageToDisappearPulse(10);
	}
	
	public boolean isTopNavigationHeaderDDMenuPresent(String menuName){
		driver.waitForElementToBeVisible(By.xpath(String.format(topNavigationDDMenuLoc,menuName)),5);
		return driver.IsElementVisible(driver.findElement(By.xpath(String.format(topNavigationDDMenuLoc, menuName))));
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
	public String getEnrolledDateFromOverviewSection(){
		driver.waitForElementToBeVisible(ENROLLMENT_DATE_OVERVIEW_SECTION_LOC,5);
		return driver.getText(ENROLLMENT_DATE_OVERVIEW_SECTION_LOC, "Enrolled in overview section").split("\\:")[1];
	}
	public boolean isHelloTextPresentInOverviewSection(){
		driver.waitForElementToBeVisible(HELLO_TEXT_OVERVIEW_SECTION_LOC,5);
		return driver.IsElementVisible(driver.findElement(HELLO_TEXT_OVERVIEW_SECTION_LOC));
	}
	public boolean isFirstNamePresentInUpperCase(){
		driver.waitForElementToBeVisible(FIRST_NAME_OVERVIEW_SECTION_LOC,5);
		return driver.isElementVisible(FIRST_NAME_OVERVIEW_SECTION_LOC);
	}
	public String getInitialName(){
		return driver.getText(INITIAL_NAME_CONTAINER_LOC, "Initail letter of first and last name");
	}
	public String getFirstLetterFromString(String name){
		return name.substring(0,1);
	}
	public boolean isInitialNameContainerPresent(){
		driver.waitForElementToBeVisible(INITIAL_NAME_CONTAINER_LOC,5);
		return driver.isElementVisible(INITIAL_NAME_CONTAINER_LOC);
	}
	public String getQualificationtitleFromOverviewSection(){
		driver.waitForElementToBeVisible(QUALIFICATION_TITLE_OVERVIEW_SECTION_LOC,5);
		return driver.getText(QUALIFICATION_TITLE_OVERVIEW_SECTION_LOC, "Qualification title in overview section");
	}
	public void clickViewTitleHistoryLink(){
		driver.click(VIEW_TITLE_HISTORY_LOC,"");
		logger.info("Clicked View Title History Link");
	}

	public String getQualificationTitleFromTitleHistroy(String titleName){
		return driver.getText(By.xpath(String.format(titleNameOnTitleHistoryLoc,titleName)),"Title name at title history page");
	}
	public String getQualificationTitleFromCardDetails(){
		driver.pauseExecutionFor(4000);
		logger.info("Mouse Hover on Pulse Section from Top Navigation");
		String text = driver.getTextByJS(RFWebsiteDriver.driver, QUALIFICATION_TITLE_FROM_CARD_SECTION_LOC, "Title name at card").trim();
		logger.info("Title at card is "+text);
		return text;
	} 
	public String getColorOfNumbersForSV(){
		String colorCode = driver.getAttribute(NUMBER_OF_SV_LOC, "style");
		logger.info("color code of SV is "+colorCode);
		return colorCode;
	}
	public boolean isColorOfSVRedForLessThan100(){
		return driver.isElementPresent(COLOR_OF_SV_RING_LESS_THAN_100_LOC);
	}
	public String getColorOfNumbersForPSQV(){
		String colorCode = driver.getAttribute(NUMBER_OF_PSQV_LOC, "style");
		logger.info("color code of PSQV is "+colorCode);
		return colorCode;
	}
	public boolean isColorOfPSQVRedForLessThan600(){
		return driver.isElementPresent(COLOR_OF_PSQV_RING_LESS_THAN_600_LOC);
	}
	public void clickReportLinks(String reportName){
		driver.click(By.xpath(String.format(spanTextLoc, reportName)), reportName+"clikced");
		driver.waitForPageLoad();
	}
	public boolean isTextPresentAtEstimatedPage(String text){
		return driver.isElementPresent(By.xpath(String.format(textLoc, text)));
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
	public boolean isTrackMyProgramProgressLinkPresent(){
		return 	driver.isElementPresent(TRACK_MY_PROGRAM_PROGRESS_LINK_LOC);
	}
	public boolean VerifyPaidAsTitleFromUIIsMoreThanOrEqualToLIVEC(String paidAsTitleFromUI){
		boolean flag=false;
		if(paidAsTitleFromUI.equalsIgnoreCase(TestConstants.TITLE_TYPE_LIVEC)){
			flag= true;
		}else if(paidAsTitleFromUI.equalsIgnoreCase(TestConstants.TITLE_TYPE_LVEC)){
			flag= true;
		}else if(paidAsTitleFromUI.equalsIgnoreCase(TestConstants.TITLE_TYPE_LVEC)){
			flag= true;
		}else if(paidAsTitleFromUI.equalsIgnoreCase(TestConstants.TITLE_TYPE_LVPREMIEREC)){
			flag = true;
		}else if(paidAsTitleFromUI.equalsIgnoreCase(TestConstants.TITLE_TYPE_ELITE_EC)){
			flag = true;
		}else if(paidAsTitleFromUI.equalsIgnoreCase(TestConstants.TITLE_TYPE_RFx)){
			flag = true;
		}
		return flag;
	}
	public void clickFirstSubCategoryOfTeamPage(){
		driver.waitForSpinImageToDisappearPulse(15);
		driver.click(FIRST_SUB_CATEGORY_OF_TEAM_PAGE, "First sub category of team page");
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


	public void clickViewAllCustomerLink(){
		//driver.waitForElementToBeVisible(VIEW_ALL_CUSTOMER_LOC, 10);
		driver.click(VIEW_ALL_CUSTOMER_LOC,"view all customer");
		driver.waitForSpinImageToDisappearPulse(10);
	}
	public void clickHaveOrderThisMonthLink(){
		driver.click(HAVE_ORDER_THIS_MONTH_LOC,"have order this month");
		driver.waitForSpinImageToDisappearPulse(10);
	}
	public void clickScheduledOrderNextMonthLink(){
		driver.click(SCHEDULED_ORDER_NEXT_MONTH_LOC,"Scheduled Order Next month");
		driver.waitForSpinImageToDisappearPulse(10);
	}

	public void clickAreFurtherOutLink(){
		driver.click(ARE_FURTHER_OUT_LOC,"Are further out");
		driver.waitForSpinImageToDisappearPulse(10);
	}
	public void clickNewPCLink(){
		driver.click(NEW_PC_LOC,"New PC");
		driver.waitForSpinImageToDisappearPulse(10);
	}
	public void clickDeactivatedPCLink(){
		driver.click(DEACTIVATED_PC_LOC,"Deactivated PC's");
		driver.waitForSpinImageToDisappearPulse(10);
	}

}
