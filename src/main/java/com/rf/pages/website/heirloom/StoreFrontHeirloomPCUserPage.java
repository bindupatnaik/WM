package com.rf.pages.website.heirloom;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import com.rf.core.driver.website.RFWebsiteDriver;

public class StoreFrontHeirloomPCUserPage extends StoreFrontHeirloomWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontHeirloomPCUserPage.class.getName());

	public StoreFrontHeirloomPCUserPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static String consultantOnlyProduct= "//p[contains(text(),'%s')]/preceding::a[1]/img";
	private static String linkUnderMyAccount = "//div[@id='RFContent']//span[contains(text(),'%s')]";

	private static final By DELAY_NEXT_SHIP_DATE_LINK = By.xpath("//a[contains(text(),'Delay Next Ship Date')]");
	private static final By CANCEL_PC_PERKS_LINK = By.xpath("//span[contains(text(),'Cancel PC Perks')]/ancestor::a[1]");
	private static final By DELAY_OR_CANCEL_PC_PERKS = By.xpath("//a[@id='PcCancellLink']");
	private static final By PC_CANCELLATION_POPUP = By.xpath("//div[@id='PcCancellationDialog']");
	private static final By CANCEL_MY_PC_PERKS_ACCOUNT_BTN = By.xpath("//a[@id='BtnCancelPCPerks']");
	private static final By CHANGED_MY_MIND_BTN = By.xpath("//a[@id='BtnChangedMyMind']");
	private static final By SELECT_REASON_DD_FOR_PC_TERMINATION = By.xpath("//select[@id='CancellationReasonId']");
	private static final By REASON_FOR_PC_TERMINATION = By.xpath("//select[@id='CancellationReasonId']/option[2]");
	private static final By MESSAGE_BOX_FOR_PC_TERMINATION = By.xpath("//textarea[@id='EmailMessage']");
	private static final By SEND_EMAIL_TO_CANCEL_BTN = By.xpath("//a[@id='BtnSendEmail']");
	private static final By EMAIL_CONFIRMATION_MSG = By.xpath("//div[@id='RFContent']/p");
	private static final By INVALID_LOGIN = By.xpath("//p[@id='loginError']");
	private static final By UPDATE_ORDER_BTN = By.xpath("//div[@id='TotalBar']/following::input[@value='Update Order']");
	private static final By DELAY_OR_CANCEL_PC_PERKS_LINK = By.xpath("//a[@id='PcCancellLink']/span[text()=' Delay or Cancel PC Perks']");
	private static final By EDIT_ORDER_BTN_LOC = By.xpath("//p[@class='FormButtons']//a[text()='Edit Order']");
	private static final By VIEW_DETAILS_LINK_LOC = By.xpath("//a[text()='View Details']");
	private static final By CHANGE_MY_AUTOSHIP_DATE_BTN = By.xpath("//a[@id='BtnChange']");
	
	/***
	 * This method is used to verify if PC Perks FAQ page(in separate window) is dislpayed or not
	 * 
	 * @param redirectedPageLink
	 * @return
	 */
	public boolean isPCPerksFAQsLinkRedirectingToAppropriatePage(String redirectedPageLink){
		String parentWin = switchToChildWindow();
		boolean status= driver.getCurrentUrl().contains(redirectedPageLink);
		switchToParentWindow(parentWin);
		return status;
	}

	public void clickDelayOrCancelPCPerksLink(){
		driver.waitForElementPresent(DELAY_OR_CANCEL_PC_PERKS);
		driver.click(DELAY_OR_CANCEL_PC_PERKS,"Delay or cancel PC perks link");
	}

	public boolean isDelayOrCancelPCPerksPopupPresent(){
		driver.waitForElementPresent(PC_CANCELLATION_POPUP);
		return driver.isElementPresent(PC_CANCELLATION_POPUP);
	}

	public void clickNoThanksPleaseCancelMyPCPerksAccountBtn(){
		driver.click(CANCEL_MY_PC_PERKS_ACCOUNT_BTN,"No Thanks, Please cancel my pc perks account button");
		driver.waitForPageLoad();
	}

	/***
	 * This method is used to click on Change my mind button for PC perks cancellation
	 */
	public StoreFrontHeirloomPCUserPage clickChangedMyMindBtn(){
		driver.click(CHANGED_MY_MIND_BTN,"I changed my mind button");
		driver.waitForPageLoad();
		return this;
	}

	/***
	 * This method is used to select reason for PC perks cancellation
	 */
	public StoreFrontHeirloomPCUserPage selectReasonForPCTermination(){
		driver.click(SELECT_REASON_DD_FOR_PC_TERMINATION,"select reason dropdown for pc termination");
		driver.click(REASON_FOR_PC_TERMINATION,"Reason selected for pc termination");
		driver.waitForPageLoad();
		return this;
	}

	/***
	 * This method is used to enter message for PC perks cancellation
	 */
	public StoreFrontHeirloomPCUserPage enterMessageForPCTermination(){
		driver.waitForElementPresent(MESSAGE_BOX_FOR_PC_TERMINATION);
		driver.type(MESSAGE_BOX_FOR_PC_TERMINATION, "For Automation","PC Termination msg");
		logger.info("Message typed for pc trmination");
		return this;
	}

	/***
	 * This method is used to click on 'send mail to cancel button' for PC perks cancellation
	 */
	public StoreFrontHeirloomPCUserPage clickSendEmailToCancelBtn(){
		driver.click(SEND_EMAIL_TO_CANCEL_BTN,"Send email to cancel button");
		driver.waitForPageLoad();
		return this;
	}

	public String getEmailConfirmationMsgAfterPCTermination(){
		driver.waitForElementPresent(EMAIL_CONFIRMATION_MSG);
		String emailConfirmationMsg = driver.findElement(EMAIL_CONFIRMATION_MSG).getText();
		logger.info("Email confirmation message is: "+emailConfirmationMsg);
		return emailConfirmationMsg;
	}

	public boolean isInvalidLoginPresent(){
		driver.waitForElementPresent(INVALID_LOGIN);
		return driver.isElementPresent(INVALID_LOGIN);
	}

	public void clickUpdateOrderBtn() {
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
		driver.waitForElementPresent(UPDATE_ORDER_BTN);
		JavascriptExecutor js = (JavascriptExecutor)(RFWebsiteDriver.driver);
		js.executeScript("arguments[0].click();", driver.findElement(UPDATE_ORDER_BTN));
		logger.info("Update order button clicked");
	}

	public boolean verifyOrderHistoryPresent(String string) {
		return driver.isElementPresent(VIEW_DETAILS_LINK_LOC);
	}

	/***
	 * This method is used to verify if edit order page is displayed or not
	 * @return true or false
	 */
	public boolean isEditOrderPagePresent() {
		return driver.isElementVisible(EDIT_ORDER_BTN_LOC);
	}


	public boolean verifyFaqPagePresent() {
		String parentWindowID=driver.getWindowHandle();
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		boolean status=false;
		while(it.hasNext()){
			String childWindowID=it.next();
			if(!parentWindowID.equalsIgnoreCase(childWindowID)){
				driver.switchTo().window(childWindowID);
				System.out.println("FAQ page url on UI is "+driver.getCurrentUrl().toLowerCase());
				status = driver.getCurrentUrl().toLowerCase().contains("pc-perks-faqs.pdf");
				driver.close();
				driver.switchTo().window(parentWindowID);
				return status;
			}
		}
		return status;
	}

	public boolean verifyCurrentPage(String content) {
		return driver.getCurrentUrl().contains(content);
	}

	public boolean verifyLinkPresentUnderMyAccount(String linkName) {
		return driver.isElementPresent(By.xpath(String.format(linkUnderMyAccount, linkName)));
	}

	/***
	 * This method is used to verify if delay and cancel PC perks link is displayed
	 * @return
	 */
	public boolean isDelayOrCancelPCPerksLinkPresent(){
		return driver.isElementVisible(DELAY_NEXT_SHIP_DATE_LINK) && driver.isElementVisible(CANCEL_PC_PERKS_LINK);
	}

	/***
	 * This method is used to click on the delay PC Perks link
	 */
	public StoreFrontHeirloomPCUserPage clickDelayPCPerksLink(){
		driver.click(DELAY_NEXT_SHIP_DATE_LINK,"Delay PC perks link");
		return this;
	}
	
	public void clickChangeAutohipDateBtn() {
		driver.click(CHANGE_MY_AUTOSHIP_DATE_BTN,"change my date btn");
	}

/***
 * This method is used to click on the cancel PC Perks link
 */
	public StoreFrontHeirloomPCUserPage clickCancelPCPerksLink(){
		driver.click(CANCEL_PC_PERKS_LINK,"Cancel PC perks link");
		return this;
	}

	public boolean isPcPerksStatusLinkPresent() {
		return driver.isElementPresent(DELAY_NEXT_SHIP_DATE_LINK) && driver.isElementPresent(CANCEL_PC_PERKS_LINK);
	}

	public StoreFrontHeirloomHomePage clickGoToRFHomeButton(){
		driver.click(By.xpath("//a[@id='BtnGoHome']"),"Go to R+F Home Button");
		return new StoreFrontHeirloomHomePage(driver);
	}

}