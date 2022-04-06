package com.rf.pages.website.heirloom;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.pages.RFBasePage;

public class StoreFrontHeirloomWebsiteBasePage extends RFBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontHeirloomWebsiteBasePage.class.getName());

	private static String regimenProductLoc = "//div[@id='ProductCategories']//span[text()='%s']/preceding::p[1]//img";
	private static String businessSystemSubLink= "//div[@id='RFContent']//a[contains(@href,'%s')]";
	private static String regimenImageOnPwsLoc = "//div[@id='ProductCategories']//p[@class='productInfo']//span[text()='%s']/../preceding-sibling::p/a";
	private static String orderManagementSublink = "//a[@class='IconLink']//span[contains(text(),'%s')]";
	private static String sublinkUnderShopSkinCareOrBeAConsultant = "//div[@id='LeftNav']//a/span[text()='%s']/../..//span[contains(text(),'%s')]";	
	private static String consultantOnlyProductonPWSLoc= "//span[contains(text(),'%s')]/preceding::a[1]/img";
	private static  String textLoc = "//*[text()='%s']"; 
	private static String linkUnderShopSkinCareOrBeAConsultant = "//div[@id='LeftNav']//a/span[text()='%s']";
	private static  String categoryNameTextLoc = "//h3[contains(text(),'%s')]";
	private static  String exactCategoryNameTextLoc = "//h3[text()='%s']";
	private static String productNameFromCart = "//div[@id='shoppingcart']/descendant::div[@class='divTableCell cartItem'][%s]";
	private static String consultantOnlyProduct= "//span[contains(text(),'%s')]/preceding::a[1]/img | //p[contains(text(),'%s')]/preceding::a[1]/img";
	private static String productDetailsFromCart = "//div[@id='shoppingcart']//div[@class='divTableBody'][%s]/descendant::div[1]//div[%s]";
	private String cardNumberOfBillingProfile = "//*[contains(text(),'%s')]/following::*[contains(@id,'creditCardNumberLabel')]";
	private static String myAccountLinkAfterLoginLink = "//div[@id='menuDiv']//span[text()='%s']/..|//div[@id='accountDiv']/descendant::a[text()='My Account'] | //*[contains(text(),'%s')]";

	private static final By BACK_TO_MY_ACCOUNT_BTN_LOC = By.xpath("//a[@id='BtnBack']");
	private static final By CONFIRMATION_MSG_LOC = By.xpath("//div[@id='RFContent']/p[contains(text(),'PC Perks ship date has been changed')]");
	private static final By HELLO_OR_WELCOME_TXT_ON_CORP = By.xpath("//*[contains(text(),'Hello') or contains(text(),'Welcome')] | //*[contains(@id,'myaccountdropdownlink')]");	
	private static final By SUBTOTAL_FROM_CHECKOUT_LOC = By.id("subtotal");
	private static final By SHOP_SKINCARE_LOC = By.xpath("//span[text()='Shop Skincare' or (text()='SHOP SKINCARE')]");
	private static final By CHANGE_SHIPPING_ADDRESS_LOC = By.xpath("//span[contains(text(),'Change Shipping Address')]/ancestor::a[1]");
	protected static final By BE_A_CONSULTANT_LOC = By.xpath("//span[text()='Become a Consultant' or text()='BECOME A CONSULTANT']");
	private static final By ORDER_NUMBER_AFTER_PLACED = By.xpath("//span[contains(@id,'uxOrderNumber')]//cufon");
	private static final By EDIT_ORDER_UNDER_MY_ACCOUNT_LOC = By.xpath("//span[text()=' Edit Order']");
	private static final By CHANGE_SHIPPING_INFO_LINK_ON_PWS = By.xpath("//a[contains(@id,'uxChangeShippingLink')]");
	private static final By EDIT_ORDER_BTN_LOC = By.xpath("//p[@class='FormButtons']//a[text()='Edit Order']");
	private static final By ORDER_NUMBER_VIEW_DETAILS_LINK_AT_ORDER_HISTORY = By.xpath("//*[@id='orderList']/div[@class='divTableBody']/div[1]//a[contains(text(),'View Details')]");
	private static final By ADD_TO_CART_BTN_FOR_EDIT_ORDER = By.xpath("//div[@class='FloatCol']/div[1]//a[text()='Add to Bag']");
	private static final By EDIT_ORDER_UPDATE_MESSAGE = By.xpath("//p[@class='success']");
	private static final By LOGOUT_BTN_LOC = By.xpath("//a[text()='Log Out']");
	private static final By SHOP_SKINCARE_HEADER_LOC = By.xpath("//span[text()='Shop Skincare']");
	private static final By ADD_TO_CART_BTN_LOC = By.xpath("//a[@id='addToCartButton']");
	private static final By MY_SHOPPING_BAG_LINK = By.xpath("//a[@class='BagLink']");
	private static final By CHECKOUT_BTN_OF_MY_SHOPPING_BAG_LINK = By.xpath("//span[text()='Checkout Now']");
	private static final By OK_BTN_OF_CONFIRMATION_POPUP_FOR_ADHOC_ORDER = By.xpath("//button[text()='OK']");
	private static final By CHECKOUT_BTN = By.xpath("//span[text()='Checkout']");
	protected static final By CONTINUE_BTN_LOC = By.xpath("//*[contains(@id,'uxContinue') or @value='Continue']");
	private static final By COMPLETE_ORDER_BTN = By.xpath("//input[contains(@id,'uxSubmitOrder')]");
	private static final By ORDER_CONFIRMATION_THANK_YOU_TXT = By.xpath("//h2[contains(text(),'Thank')]");
	private static final By CONTINUE_BTN_BILLING_PAGE = By.xpath("//span[contains(text(),'Change Billing Information')]/following::a[contains(@id,'uxContinue')]");
	private static final By CONTINUE_WITHOUT_CONSULTANT_LINK = By.xpath("//a[contains(@id,'uxSkipStep')]");
	private static final By ADD_TO_CART_BTN_AS_PER_REGIMEN = By.xpath("//div[@id='FullPageItemList']/descendant::a[not(contains(@class,'disabled'))][contains(text(),'Add to Bag') or @id='addToCartButton' ][2]");
	private static final By ORDER_PLACED_CONFIRMATION_TEXT = By.xpath("//div[@id='RFContent']//b");
	private static final By CONSULTANTS_ONLY_PRODUCTS_REGIMEN = By.xpath("//cufontext[contains(text(),'Consultant-Only ')]/following::a[1]/img");
	private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[@id='uxForgotPasswordLink']");
	private static final By MY_ACCOUNT_LINK = By.xpath("//a[text()='My Account'] | //div[@id='LeftNav']//span[contains(text(),'my account')]");
	private static final By SELECTED_HIGHLIGHT_LINK = By.xpath("//div[@id='ContentWrapper']//a[@class='selected']/span");
	private static final By CHANGE_BILLING_INFO = By.xpath("//a[contains(@id,'uxBillingInfo_uxChange')]");
	private static final By SHOP_SKINCARE_ON_PWS_LOC = By.xpath("//span[text()='SHOP SKINCARE']");
	private static final By PRODUCT_LINK_UNDER_SHOP_SKIN_CARE = By.xpath("//span[text()='CONSULTANT-ONLY PRODUCTS']");
	private static final By COM_PWS_CONSULTANT_ENROLLMENT = By.xpath("//div[@class='websitePrefix']/ul[@class='domainResults']/li[1]");
	private static final By BIZ_PWS_CONSULTANT_ENROLLMENT = By.xpath("//div[@class='websitePrefix']/ul[@class='domainResults']/li[2]");
	private static final By EMAIL_ADDRESS_CONSULTANT_ENROLLMENT = By.xpath("//div[@class='websitePrefix']/ul[@class='domainResults']/li[3]");
	private static final By ABOUT_RF_LOC = By.xpath("//span[text()='About R+F' or text()='ABOUT R+F']");
	private static final By RODAN_AND_FIELDS_IMG_LOC = By.xpath("//div[@id='logo']//img");
	private static final By MY_ACCOUNT_FROM_TOP_NAV_LOC =  By.xpath("//nav[@id='header']//a[text()='My Account']");
	private static final By USE_THIS_BILLING_INFORMATION = By.xpath("//a[contains(@id,'uxUseNewPayment') or contains(@id,'uxContinueAndEdit')]");
	protected static final By SUBTOTAL_FROM_ORDER_CONFIRMATION_LOC = By.xpath("//span[contains(@id,'uxSubTotal')]");
	protected static final By DONATE_BTN = By.id("donateBtn");
	protected static final By MY_ACCOUNT_DROPDOWN_LINK_LOC=By.id("myaccountdropdownlink");
	private static final By CONTINUE_WITHOUT_A_CONSULTANT_LINK = By.xpath("//a[contains(text(),'Continue without a Consultant')]");
	protected static final By FIRST_VIEW_DETAILS_LINK_LOC = By.xpath("//div[@id='orderList']/descendant::a[@class='OrderDetailsLink'][1]");
	private static final By UPDATE_ORDER = By.xpath(".//div[@id='MyAutoshipItems']/following-sibling::p[@class='FormButtons']/input");

	protected RFWebsiteDriver driver;
	private String RFL_DB = null;
	protected String parentWindow = null;

	private Actions actions;

	public StoreFrontHeirloomWebsiteBasePage(RFWebsiteDriver driver){		
		super(driver);
		this.driver = driver;
	}

	public boolean verifyUserSuccessfullyLoggedInOnPWSSite() {
		driver.quickWaitForElementPresent(LOGOUT_BTN_LOC);
		return driver.isElementPresent(LOGOUT_BTN_LOC);
	}

	public void clickShopSkinCareHeader() {
		driver.click(SHOP_SKINCARE_HEADER_LOC,"");
		logger.info("shop skincare link on Header clicked");
	}
	public void selectRegimenAfterLogin(String regimen){
		regimen = regimen.toUpperCase();
		driver.click(By.xpath(String.format(regimenProductLoc, regimen)),"Regimen selected i="+regimen);
	}

	public void clickAddToCartButtonAfterLogin() {
		try{
			driver.click(By.xpath("//*[text()='Add to Bag']"),"");
		}
		catch(NoSuchElementException e){
			try{
				driver.click(By.xpath("//a[text()='Add to Cart']"),"");
			} catch (NoSuchElementException e1) {
				driver.click(ADD_TO_CART_BTN_LOC,"");
				logger.info("Add to cart button is clicked");

			}
		}
	}	

	public void mouseHoverOnMyShoppingBagLinkAndClickOnCheckoutBtn(){
		driver.quickWaitForElementPresent(MY_SHOPPING_BAG_LINK);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(MY_SHOPPING_BAG_LINK)).click(driver.findElement(CHECKOUT_BTN_OF_MY_SHOPPING_BAG_LINK)).build().perform();
		logger.info("Mouse hover on My shopping bag link and clicked on checkout button");
	}


	public void clickOKBtnOnPopup(){
		try{
			driver.click(OK_BTN_OF_CONFIRMATION_POPUP_FOR_ADHOC_ORDER,"");
			logger.info("Ok button clicked on popup");
		}catch(Exception e){

		}
	}

	public boolean isThankYouTextPresentAfterOrderPlaced(){
		return driver.isElementPresent(ORDER_CONFIRMATION_THANK_YOU_TXT);
	}

	/***
	 * This method is used to click on continue button
	 */
	public void clickContinueBtn(){
		driver.click(CONTINUE_BTN_LOC,"continue button");
		driver.waitForPageLoad();
	}

	public void clickCompleteOrderBtn(){
		driver.click(COMPLETE_ORDER_BTN,"Complete order button");
	}

	public void clickContinueBtnOnBillingPage() {
		driver.click(CONTINUE_BTN_BILLING_PAGE,"Continue button");
		driver.waitForPageLoad();  
	}

	public void openPWSSite(String pws){
		boolean isAlreadylogin = false;
		driver.get(pws);
		try{
			logout();
			isAlreadylogin = true;
		}catch(NoSuchElementException e){

		} 
		if(isAlreadylogin == true){
			driver.get(pws);
		}
		logger.info("Open Pws Site Is: "+pws);
		driver.waitForPageLoad();
	}

	public void logout(){
		driver.quickWaitForElementPresent(By.xpath("//a[text()='Log-Out' or text()='Log Out']"));
		if(driver.findElements(By.xpath("//a[text()='Log-Out' or text()='Log Out']")).size()>0){
			driver.click(By.xpath("//a[text()='Log-Out' or text()='Log Out']"),"");
			logger.info("Log Out Link clicked"); 
			driver.waitForPageLoad();
		}
		else{
			logger.info("User already logged out");
		}
	}

	public void clickContinueWithoutConsultantLink(){
		driver.click(CONTINUE_WITHOUT_CONSULTANT_LINK,"Continue without sponser link");
	}

	public String getCurrentURL(){
		driver.waitForPageLoad();
		return driver.getCurrentUrl();
	}

	public boolean isCurrentURLContainsExpectedString(String URL){
		driver.waitForPageLoad();
		driver.waitForExpectedURLPresent(URL);
		return driver.getCurrentUrl().contains(URL);
	}

	public boolean verifyUserSuccessfullyLoggedIn() {
		driver.waitForElementPresent(HELLO_OR_WELCOME_TXT_ON_CORP);
		return driver.isElementPresent(HELLO_OR_WELCOME_TXT_ON_CORP);
	}

	/***
	 * This method is used to navigate to back page
	 */
	public void navigateToBackPage(){
		driver.navigate().back();
		driver.waitForPageLoad();
	}

	public String getOrderConfirmationTextMsgAfterOrderPlaced(){
		driver.waitForElementPresent(ORDER_PLACED_CONFIRMATION_TEXT);
		String confirmationMsg = driver.findElement(ORDER_PLACED_CONFIRMATION_TEXT).getText().trim();
		logger.info("Order confirmation msg after order placed is: "+confirmationMsg);
		return confirmationMsg;
	}

	public void selectConsultantOnlyProductsRegimen(){
		driver.click(CONSULTANTS_ONLY_PRODUCTS_REGIMEN,"Consultants only products Regimen");
	}

	public boolean isForgotPasswordLinkPresent() {
		driver.waitForElementPresent(FORGOT_PASSWORD_LINK);
		return driver.isElementPresent(FORGOT_PASSWORD_LINK);
	}

	public void clickShopSkinCareBtn(){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.quickWaitForElementPresent(SHOP_SKINCARE_LOC);
		WebElement shopSkinCare = driver.findElement(SHOP_SKINCARE_LOC);
		actions.moveToElement(shopSkinCare).pause(1000).click().build().perform();
		logger.info("All products link clicked "); 
	}

	public boolean isSublinkOfBusinessSystemPresent(String linkNameOfBusinessSystem){
		driver.quickWaitForElementPresent(By.xpath(String.format(businessSystemSubLink, linkNameOfBusinessSystem)));
		return driver.isElementPresent(By.xpath(String.format(businessSystemSubLink, linkNameOfBusinessSystem)));
	}

	public void clickSublinkOfBusinessSystem(String linkNameOfBusinessSystem){
		driver.click(By.xpath(String.format(businessSystemSubLink, linkNameOfBusinessSystem)),"");
		logger.info("Sublink of business system i.e. :"+linkNameOfBusinessSystem+" clicked");
		driver.waitForPageLoad();
	}

	public String getSelectedHighlightLinkName(){
		return driver.getText(SELECTED_HIGHLIGHT_LINK);
	}

	/***
	 * This method is used to click on 'Use This billing' button
	 */
	public void clickUseThisBillingInformationBtn(){
		driver.pauseExecutionFor(2000);//dont't remove
		driver.click(USE_THIS_BILLING_INFORMATION,"Use this billing information button");
		driver.pauseExecutionFor(2000);//dont't remove
		driver.waitForPageLoad();
	}

	public void clickShopSkinCareBtnOnPWS(){
		driver.pauseExecutionFor(2000);
		driver.quickWaitForElementPresent(SHOP_SKINCARE_ON_PWS_LOC);
		driver.click(SHOP_SKINCARE_ON_PWS_LOC,"");
		logger.info("Products button now as shop skincare clicked");
		driver.waitForPageLoad();
	}

	public void mouseHoverOnShopSkinCareAndClickOnConsultantOnlyProductsLink(){
		driver.waitForElementPresent(SHOP_SKINCARE_ON_PWS_LOC);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(SHOP_SKINCARE_ON_PWS_LOC)).build().perform();
		driver.waitForElementPresent(PRODUCT_LINK_UNDER_SHOP_SKIN_CARE);
		driver.click(PRODUCT_LINK_UNDER_SHOP_SKIN_CARE,"");
		logger.info("Mouse hover on shop skincare link and clicked on product link on pws");
	}

	public void clickRegimenOnPWS(String regimen){
		driver.quickWaitForElementPresent(By.xpath(String.format(regimenImageOnPwsLoc, regimen)));
		driver.click(By.xpath(String.format(regimenImageOnPwsLoc, regimen)),"");
		logger.info("Regimen selected is: "+regimen);
	}

	public String getOrderNumebrAfterOrderPlaced(){
		driver.waitForPageLoad();
		driver.waitForElementPresent(ORDER_NUMBER_AFTER_PLACED);
		String orderNumber = driver.findElement(ORDER_NUMBER_AFTER_PLACED).getAttribute("alt");
		logger.info("Order number is: "+orderNumber);
		return orderNumber;
	}

	/***
	 * This method is used to click the header links
	 * @param linkName
	 */
	public StoreFrontHeirloomWebsiteBasePage clickHeaderLinkAfterLogin(String linkName) {
		if(driver.isElementVisible(MY_ACCOUNT_DROPDOWN_LINK_LOC,5)) {
			driver.click(MY_ACCOUNT_DROPDOWN_LINK_LOC, "Login DropDown");
		}
		driver.click(By.xpath(String.format(myAccountLinkAfterLoginLink, linkName,linkName)),"my account link");
		driver.waitForPageLoad();
		return this;
	}

	public void clickChangeLinkUnderShippingToOnPWS(){
		driver.quickWaitForElementPresent(CHANGE_SHIPPING_INFO_LINK_ON_PWS);
		driver.click(CHANGE_SHIPPING_INFO_LINK_ON_PWS,"");
		logger.info("Change Link under shipping to clicked");
		driver.waitForPageLoad();
	}

	public void clickEditOrderBtn(){
		driver.quickWaitForElementPresent(EDIT_ORDER_BTN_LOC);
		driver.click(EDIT_ORDER_BTN_LOC,"");
		logger.info("edit order button is clicked"); 
	}

	/***
	 * This method is is used to verify the order management sublinks
	 * @param sublinkName
	 * @return true or false
	 */
	public boolean isOrderManagementSublinkPresent(String sublinkName){
		return driver.isElementVisible(By.xpath(String.format(orderManagementSublink, sublinkName)));
	}

	/***
	 * This method is is used to click on  the order management sublinks
	 * @param sublinkName
	 * @return this
	 */
	public StoreFrontHeirloomWebsiteBasePage clickOrderManagementSublink(String sublinkName){
		driver.click(By.xpath(String.format(orderManagementSublink, sublinkName)),sublinkName+" link");
		driver.waitForPageLoad();
		return this;
	}

	/***
	 * his method is is used to verify the order number on Order History page
	 * @return
	 */
	public boolean isOrderNumberPresentAtOrderHistoryPage(){
		return driver.isElementVisible(ORDER_NUMBER_VIEW_DETAILS_LINK_AT_ORDER_HISTORY);
	}

	public void clickAddToCartBtnForEditOrder() {
		driver.waitForElementPresent(ADD_TO_CART_BTN_FOR_EDIT_ORDER);
		driver.click(ADD_TO_CART_BTN_FOR_EDIT_ORDER,"");
		logger.info("Add to cart btn clicked");
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
	}

	public void clickOKBtnOfJavaScriptPopUp(){
		driver.pauseExecutionFor(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		logger.info("Ok button of java Script popup is clicked.");
		driver.waitForPageLoad();
	}

	public String convertBizSiteToComSite(String pws){
		String com  = "com";
		String biz ="biz";
		if(pws.contains(biz))
			pws = pws.replaceAll(biz,com);
		return pws;  
	}

	public String getDotComPWS(){
		driver.waitForElementPresent(COM_PWS_CONSULTANT_ENROLLMENT);
		String pwsUnderPulse = driver.findElement(COM_PWS_CONSULTANT_ENROLLMENT).getText();
		return pwsUnderPulse;
	}

	public String getDotBizPWS(){
		driver.waitForElementPresent(BIZ_PWS_CONSULTANT_ENROLLMENT);
		String pwsUnderPulse = driver.findElement(BIZ_PWS_CONSULTANT_ENROLLMENT).getText();
		return pwsUnderPulse;
	}

	public String getEmailId(){
		driver.waitForElementPresent(EMAIL_ADDRESS_CONSULTANT_ENROLLMENT);
		String pwsUnderPulse = driver.findElement(EMAIL_ADDRESS_CONSULTANT_ENROLLMENT).getText();
		return pwsUnderPulse;
	}

	public void clickMyAccountLink(){
		driver.waitForElementPresent(MY_ACCOUNT_LINK);
		driver.click(MY_ACCOUNT_LINK,"");
		logger.info("My Account link clicked");
		driver.waitForPageLoad();
	}

	public void mouseHoverShopSkinCareOnPWS(){
		driver.pauseExecutionFor(2000);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(SHOP_SKINCARE_ON_PWS_LOC)).build().perform();
		logger.info("hover on Products link now as shop skincare");
	}

	public void mouseHoverShopSkinCareAndClickLinkOnPWS(String link){
		driver.pauseExecutionFor(2000);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(SHOP_SKINCARE_ON_PWS_LOC)).build().perform();
		logger.info("hover on Products link now as shop skincare");
		driver.waitForElementPresent(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, link)));
		driver.click(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, link)),"");
		logger.info("Clicked "+link+" link is clicked after hovering shop skincare.");
		driver.waitForPageLoad();
	}

	/***
	 * This method is used to mouseHover On Be(come) A Consultant link and click on its sublinks
	 * @param link
	 */
	public void mouseHoverBeAConsultantAndClickLink(String link){
		driver.waitForElementPresent(BE_A_CONSULTANT_LOC);
		Actions actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(BE_A_CONSULTANT_LOC)).build().perform();
		driver.pauseExecutionFor(1000);
		driver.click(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, link)),link);
		driver.waitForPageLoad();
	}

	public void mouseHoverShopSkinCareAndClickSubLink(String link, String sublink){
		driver.pauseExecutionFor(2000);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(SHOP_SKINCARE_LOC)).build().perform();
		logger.info("hover on Products link now as shop skincare");
		driver.click(By.xpath(String.format(sublinkUnderShopSkinCareOrBeAConsultant, link,sublink)),"");
		logger.info("Clicked "+sublink+" link is clicked under "+link+" after hovering shop skincare.");
		driver.waitForPageLoad();
	}

	public void mouseHoverAboutRFAndClickLink(String link){
		driver.pauseExecutionFor(2000);
		Actions actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(ABOUT_RF_LOC)).build().perform();
		logger.info("hover performed on about R+F link.");
		driver.click(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, link)),"");
		logger.info("Clicked "+link+" link is clicked after hovering About RF.");
		driver.waitForPageLoad();
	}

	public void mouseHoverShopSkinCare(){
		driver.pauseExecutionFor(2000);
		Actions actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(SHOP_SKINCARE_LOC)).build().perform();
		logger.info("hover on Products link now as shop skincare");
	}


	public void clickMyShoppingBagLink(){
		driver.waitForElementPresent(MY_SHOPPING_BAG_LINK);
		driver.clickByJS(RFWebsiteDriver.driver,MY_SHOPPING_BAG_LINK,"my shopping bag");
		logger.info("Clicked on My shopping bag link.");
		driver.waitForPageLoad();
	}

	public void clickOnRodanAndFieldsLogo(){
		try{
			driver.quickWaitForElementPresent(By.xpath("//div[@id='Logo' or (@id='logoDiv')]//img"));
			driver.findElement(By.xpath("//div[@id='Logo' or (@id='logoDiv')]//img")).click();
		}catch(Exception e){
			driver.quickWaitForElementPresent(RODAN_AND_FIELDS_IMG_LOC);
			driver.click(RODAN_AND_FIELDS_IMG_LOC,"");
		}
		logger.info("Rodan and Fields logo clicked"); 
		driver.waitForPageLoad();
		driver.pauseExecutionFor(3000);
	}

	//	public void clickContinueBtnWhileEnrollment(){
	//		driver.pauseExecutionFor(2000);
	//		driver.clickByJS(RFWebsiteDriver.driver, CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC,"continue button");
	//	}

	public boolean isLoginFailed(){
		driver.quickWaitForElementPresent(By.id("loginError"));
		return driver.isElementPresent(By.id("loginError"));
	}

	public void refreshThePage(){
		driver.navigate().refresh();
		driver.waitForPageLoad();
		driver.pauseExecutionFor(2000);
	}

	public void clickChangeShippingAddressBtn(){
		if(driver.isElementPresent(CHANGE_SHIPPING_ADDRESS_LOC)){
			driver.click(CHANGE_SHIPPING_ADDRESS_LOC,"");
			logger.info("Change Shipping Address button clicked");
			driver.waitForPageLoad();
		}
		else{
			logger.info("User does not have any Shipping Address Yet");
		}
	}

	public void clickHeaderLinkFromTopNav() {
		driver.click(MY_ACCOUNT_FROM_TOP_NAV_LOC,"");
		logger.info("my account link is clicked");
	}

	public boolean isTextPresent(String text){
		driver.quickWaitForElementPresent(By.xpath(String.format(textLoc, text)));
		return driver.isElementPresent(By.xpath(String.format(textLoc, text)));
	}

	public boolean isCategoryNamePresent(String category){
		driver.quickWaitForElementPresent(By.xpath(String.format(categoryNameTextLoc, category)));
		return driver.isElementPresent(By.xpath(String.format(categoryNameTextLoc, category)));
	}

	public boolean isExactCategoryNamePresent(String category){
		driver.quickWaitForElementPresent(By.xpath("//h3[text()='"+category+"'] | //h2[contains(text(),'"+category+"')]"));
		//driver.quickWaitForElementPresent(By.xpath(String.format(exactCategoryNameTextLoc, category)));
		return driver.isElementPresent(By.xpath("//h3[text()='"+category+"'] | //h2[contains(text(),'"+category+"')]"));
	}

	public String getSubtotalFromOrderConfirmation(){
		return driver.getText(SUBTOTAL_FROM_ORDER_CONFIRMATION_LOC,"subtotal");
	}

	public String getSubtotalFromCheckout(){
		driver.waitForElementPresent(SUBTOTAL_FROM_CHECKOUT_LOC);
		String subtotal = driver.getText(SUBTOTAL_FROM_CHECKOUT_LOC);
		logger.info("Subtotal is "+subtotal);
		return subtotal;
	}

	public String getProductNameThroughProductNumber(String productNumber){
		return  driver.getText(By.xpath(String.format(productNameFromCart, productNumber)),"productName"); 
	}

	public String getProductDetailsFromCartAccordingToProductNumber(String productNumber, String productDetailsNumber){
		driver.waitForElementPresent(By.xpath(String.format(productDetailsFromCart, productNumber,productDetailsNumber)));
		String value = driver.getText(By.xpath(String.format(productDetailsFromCart, productNumber,productDetailsNumber))); 
		logger.info("Product number is "+productNumber+" and product value  is "+value);
		return value;
	}

	public String getParentWindowHandle(){
		return driver.getWindowHandle();
	}

	public void closeChildAndSwitchToParentWindow(String parentWindowHandle){
		driver.switchToChildWindow(parentWindowHandle);
		driver.close();
		driver.switchTo().window(parentWindowHandle);
		logger.info("Child window closed and swiched to Parent");
	}

	public int getTotalWindows(){
		driver.pauseExecutionFor(2000);
		return driver.getWindowHandles().size();
	}

	public String switchToChildWindow(){
		String parentWin=driver.switchToSecondWindow();
		return parentWin;
	}

	public String switchToNonParentWindow(){
		parentWindow=driver.getWindowHandle();
		driver.waitForTotalWindowsToBeOpened(2);
		Set<String> allWindows=driver.getWindowHandles();
		for(String handle:allWindows){
			if(handle!=parentWindow){
				driver.switchTo().window(handle);		
			}
		}
		logger.info("Switched to non parent window");
		return parentWindow;
	}

	public void switchToParentWindow(String parentWindow){
		Set<String> allWindows=driver.getWindowHandles();
		for(String handle:allWindows){
			if(handle.equals(parentWindow)==false){
				driver.switchTo().window(handle);		
				driver.close();
			}

		}
		driver.switchTo().window(parentWindow);
	}

	public void clickConsultantOnlyProduct(String productName){
		//		String productCollateral = TestConstantsRFL.CONSULTANT_ONLY_PRODUCT_COLLATERAL;
		//		driver.isElementVisible(By.xpath(String.format(consultantOnlyProduct, productName, productName));
		driver.click(By.xpath(String.format(consultantOnlyProduct, productName, productName)),productName);
		logger.info("consultant only product selected is: "+productName);
		//		}else {
		//			driver.click(By.xpath(String.format(consultantOnlyProduct, productCollateral, productCollateral)),productCollateral);
		//			logger.info("consultant only product selected is: "+productCollateral);
		//		}
	}

	public void clickConsultantOnlyEventSupportProduct(){
		driver.click(By.xpath(String.format(consultantOnlyProductonPWSLoc, "Event", "Event")),"Event Support");
	}


	public void clickConsultantOnlyProductOnPWS(String productName){
		String productCollateral = TestConstantsRFL.CONSULTANT_ONLY_PRODUCT_COLLATERAL;
		if(driver.isElementVisible(By.xpath(String.format(consultantOnlyProductonPWSLoc, productName, productName)))) {
			driver.click(By.xpath(String.format(consultantOnlyProductonPWSLoc, productName, productName)),productName);
			logger.info("consultant only product selected is: "+productName);
		}else {
			driver.click(By.xpath(String.format(consultantOnlyProductonPWSLoc, productCollateral, productCollateral)), productCollateral);
			logger.info("consultant only product selected is: "+productCollateral);
		}
	}	

	public boolean verifyConfirmationMessage() {
		return driver.isElementVisible(CONFIRMATION_MSG_LOC);
	}

	public void clickBackToMyAccountBtn() {
		driver.quickWaitForElementPresent(BACK_TO_MY_ACCOUNT_BTN_LOC);
		driver.click(BACK_TO_MY_ACCOUNT_BTN_LOC,"");
		logger.info("back to my account button is clicked");
		driver.waitForPageLoad();
	}

	public void mouseHoverShopSkinCareAndClickLink(String link){
		driver.pauseExecutionFor(2000);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(SHOP_SKINCARE_LOC)).build().perform();
		logger.info("hover on Products link now as shop skincare");
		//driver.click(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, link)),"");
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, link)),"");
		logger.info("Clicked "+link+" link is clicked after hovering shop skincare.");
		driver.waitForPageLoad();
	}

	public String getCardNumberOfTheBillingProfile(String billingProfile){
		return driver.getText(By.xpath(String.format(cardNumberOfBillingProfile, billingProfile)), "card number in * form");
	}

	public boolean isNewlyCreatedBillingProfilePresent(String billingProfileName) {
		return driver.isElementVisible(By.xpath("//*[contains(text(),'"+billingProfileName+"')]"));
	} 

	public void clickOnWelcomeDropdown(){
		driver.click(MY_ACCOUNT_DROPDOWN_LINK_LOC, "My Account Dropdown");
	} 

	public void createAndGetNSC3Url(){
		String baseurl = driver.getURL();
		String nsc3Url = baseurl.replaceAll("www.corp","nsc3");
		logger.info("NSC3 url is "+nsc3Url);
		driver.get(nsc3Url);
	}

	public void createAndGetNSC4Url(){
		String baseurl = driver.getURL();
		String nsc4Url = baseurl.replaceAll("www.corp","nsc4");
		logger.info("NSC4 url is "+nsc4Url);
		driver.get(nsc4Url);
	}

	/***
	 * This method is used to click on continue without a consultant link
	 */
	public void clickContinueWithoutAConsultantLink(){
		driver.click(CONTINUE_WITHOUT_A_CONSULTANT_LINK,"continue without a consultant link");
	}

	/***
	 * This method is used to verified My Account Drop down link
	 */
	public boolean isMyAccountDropdownDisplayed(){
		return driver.isElementVisible(MY_ACCOUNT_DROPDOWN_LINK_LOC);
	} 

	/***
	 * This method is used to click on edit order link	
	 */
	public StoreFrontHeirloomWebsiteBasePage clickEditOrderLink(){
		if(driver.isElementVisible(MY_ACCOUNT_DROPDOWN_LINK_LOC)){
			String myAccountCategory="Edit PC Perks Cart";
			clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		}else{
			driver.click(EDIT_ORDER_UNDER_MY_ACCOUNT_LOC,"edit order link");
		}
		return this;
	}

	/***
	 * This method is used to click on My Account Dropdown and click on specified category link
	 * 
	 *  return current class object
	 */
	public StoreFrontHeirloomWebsiteBasePage clickMyAccountDropDownAndSelectSpecifiedCategory(String myAccountCategory) {
		driver.pauseExecutionFor(1000);
		driver.click(MY_ACCOUNT_DROPDOWN_LINK_LOC, "My Account Dropdown");
		driver.pauseExecutionFor(1000);
		driver.click(By.xpath(".//*[@id='myaccountoptions']//a[contains(text(),'"+myAccountCategory+"')]"), ""+myAccountCategory);
		return this;
	}

	public int getCurrentDayFromCurrentDate(){
		TimeZone timeZone = TimeZone.getTimeZone("US/Pacific");//"US/Pacific"
		String currenDate = CommonUtils.getCurrentDate("dd-MM-YYYY", timeZone);
		logger.info("Current date is** "+currenDate);
		String day = currenDate.split("\\-")[0];
		logger.info("Day is "+day);
		return Integer.parseInt(day);
	}

	public String getCurrentMonthFromCurrentDate(){
		TimeZone timeZone = TimeZone.getTimeZone("US/Pacific");//"US/Pacific"
		String currenDate = CommonUtils.getCurrentDate("dd-MMMM-YYYY", timeZone);
		logger.info("Current date is "+currenDate);
		String month = currenDate.split("\\-")[1];
		logger.info("Day is "+month);
		return month;
	}

	public String getNextMonthFromCurrentDate(){
		Calendar originalDate = Calendar.getInstance();
		logger.info("The original Date is : " + originalDate.getTime());
		Calendar nextMonthDate = (Calendar) originalDate.clone();
		nextMonthDate.add(Calendar.MONTH, 1);
		String nextMonth = ""+nextMonthDate.getTime();
		logger.info("The Next month date is: " + nextMonth);
		String month = nextMonth.split(" ")[1];
		logger.info("next month is "+month);
		return month.toLowerCase();
	}

	public String getNextTwoMonthDateFromDate(){
		Calendar originalDate = Calendar.getInstance();
		logger.info("The original Date is : " + originalDate.getTime());
		Calendar nextMonthDate = (Calendar) originalDate.clone();
		nextMonthDate.add(Calendar.MONTH, 2);
		String nextMonth = ""+nextMonthDate.getTime();
		logger.info("The Next 2nd month date is: " + nextMonth);
		String month = nextMonth.split(" ")[1];
		logger.info("next 2nd month is "+month);
		return month.toLowerCase();
	}

	public boolean isViewDetailsLinkDisplayed(){
		return driver.isElementVisible(FIRST_VIEW_DETAILS_LINK_LOC);
	}

	/***
	 * This method is used to click on Update Order on Order Items Page
	 * 
	 *  return current class object
	 */
	public StoreFrontHeirloomWebsiteBasePage clickOnUpdateOrder() {
		driver.click(UPDATE_ORDER, "Update Order Button");
		handleAlertAfterUpdateOrder();
		return this;
	}

	public String getOrderUpdateMessage(){
		if (driver.isElementVisible(UPDATE_ORDER)) {
			driver.scrollDownIntoView(UPDATE_ORDER);
		}
		driver.waitForElementPresent(EDIT_ORDER_UPDATE_MESSAGE);
		String messgae = driver.findElement(EDIT_ORDER_UPDATE_MESSAGE).getText();
		logger.info("Order updation message is: "+messgae);
		return messgae;
	}

	public String getYearAfterTwoMonthFromCurrentDate(){
		Calendar originalDate = Calendar.getInstance();
		logger.info("The original Date is : " + originalDate.getTime());
		Calendar nextMonthDate = (Calendar) originalDate.clone();
		nextMonthDate.add(Calendar.MONTH, 2);
		String nextMonth = ""+nextMonthDate.getTime();
		logger.info("The Next 2nd month date is: " + nextMonth);
		String year = nextMonth.split(" ")[5];
		logger.info("next year is "+year);
		return year.toLowerCase();
	}

	public String getMonthCountFromMonthName(String month){
		String monthCount =null;
		if(month.equalsIgnoreCase("Jan")){
			monthCount="01";
		}else if(month.equalsIgnoreCase("Feb")){
			monthCount="02";
		}else if(month.equalsIgnoreCase("Mar")){
			monthCount="03";
		}
		else if(month.equalsIgnoreCase("Apr")){
			monthCount="04";
		}
		else if(month.equalsIgnoreCase("May")){
			monthCount="05";
		}
		else if(month.equalsIgnoreCase("Jun")){
			monthCount="06";
		}
		else if(month.equalsIgnoreCase("Jul")){
			monthCount="07";
		}
		else if(month.equalsIgnoreCase("Aug")){
			monthCount="08";
		}
		else if(month.equalsIgnoreCase("Sep")){
			monthCount="09";
		}
		else if(month.equalsIgnoreCase("Oct")){
			monthCount="10";
		}
		else if(month.equalsIgnoreCase("Nov")){
			monthCount="11";
		}else if(month.equalsIgnoreCase("Dec")){
			monthCount="12";
		}else{
			monthCount="0";
		}
		return monthCount;
	}

	/***
	 * This method is used to click on checkout button
	 */
	public StoreFrontHeirloomWebsiteBasePage clickCheckoutBtn(){
		driver.click(CHECKOUT_BTN,"Checkout button");
		driver.waitForPageLoad();
		if(driver.isElementVisible(CONTINUE_WITHOUT_A_CONSULTANT_LINK)){
			clickContinueWithoutAConsultantLink();
			driver.waitForPageLoad();
		}
		return this;
	} 

	public void clickAddToCartBtn(){
		if(driver.isElementVisible(By.xpath("//*[@id='BuyForm']/descendant::a[contains(text(),'Add to Bag')]"),2)) {
			driver.click(By.xpath("//*[@id='BuyForm']/descendant::a[contains(text(),'Add to Bag')]"),"Add to cart button"); 
		}else if(driver.isElementPresent(ADD_TO_CART_BTN_AS_PER_REGIMEN, 5)){
			driver.click(ADD_TO_CART_BTN_AS_PER_REGIMEN , "Add To Cart Button");
		}else {
			driver.click(By.xpath("//span[contains(text(),'Donate')]"), "Donate button");
			driver.click(By.xpath("//span[contains(text(),'Add to Bag')]"), "Add to bag button");	
		}
		driver.waitForPageLoad();		
	}

	public String getMonthFullnameFromCount(String month){
		String UIMonth = null;
		int monthCount= Integer.parseInt(month);
		switch (monthCount) {  
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
		return UIMonth.toLowerCase();
	}

	public String convertDayInDoubleDigit(int day){
		String date = null;
		if(day<=9){
			date = "0"+day;
		}else{
			date = ""+day;
		}
		return date;
	}

	public StoreFrontHeirloomWebsiteBasePage clickChangeBillingInformationBtn(){
		//driver.click(CHANGE_BILLING_INFO,"Change billing information button");
		if(driver.isElementVisible(CHANGE_BILLING_INFO)) {
			driver.clickByJS(RFWebsiteDriver.driver,CHANGE_BILLING_INFO,"Change billing information button");
			driver.waitForPageLoad();
		}
		return this;
	}


	public String getCurrentDayAsStringFromCurrentDate(){
		TimeZone timeZone = TimeZone.getTimeZone("US/Pacific");//"US/Pacific"
		String currenDate = CommonUtils.getCurrentDate("dd-MM-YYYY", timeZone);
		logger.info("Current date is** "+currenDate);
		String day = currenDate.split("\\-")[0];
		logger.info("Day is "+day);
		return day;
	}

	public void handleAlertAfterUpdateOrder() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			//exception handling
		}
	}
	
}