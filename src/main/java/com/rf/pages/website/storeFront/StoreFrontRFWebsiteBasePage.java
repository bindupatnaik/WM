package com.rf.pages.website.storeFront;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.RFBasePage;

public class StoreFrontRFWebsiteBasePage extends RFBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontRFWebsiteBasePage.class.getName());

	protected final By LOGIN_LINK_LOC = By.cssSelector("li[id='log-in-button']>a");
	protected final By USERNAME_TXTFLD_LOC = By.id("username");
	protected final By PASSWORD_TXTFLD_LOC = By.id("password");
	protected final By LOGIN_BTN_LOC = By.cssSelector("input[value='SIGN IN']");
	private final By RODAN_AND_FIELDS_LOGO_IMG_LOC = By.xpath("//*[@title='Rodan+Fields']");
	private final By WELCOME_USER_DD_LOC = By.xpath("//div[@id='account-info-button']/a");
	private final By WELCOME_DD_ORDERS_LINK_LOC = By.xpath("//a[text()='Orders']");
	private final By YOUR_ACCOUNT_DROPDOWN_LOC = By.xpath("//button[@class='btn btn-default dropdown-toggle']");
	private final By WELCOME_DD_BILLING_INFO_LINK_LOC = By.linkText("Billing Info");
	private final By WELCOME_DD_SHIPPING_INFO_LINK_LOC = By.xpath("//a[text()='Shipping Info']");
	private final By ADD_NEW_SHIPPING_LINK_LOC = By.xpath("//a[@class='add-new-shipping-address']");
	private final By WELCOME_DD_ACCOUNT_INFO_LOC = By.xpath("//a[text()='Account Info']");
	private final By ADD_NEW_BILLING_CARD_NUMBER_LOC = By.id("card-nr");
	private final By UPDATE_CART_BTN_LOC = By.xpath("//input[@value='UPDATE CART']");
	private final By SHOP_SKINCARE_LOC = By.xpath("//span[text()='Shop Skincare']");
	private final By WELCOME_DD_EDIT_CRP_LINK_LOC = By.xpath("//div[@id='account-info-button']//a[contains(text(),'CRP')]");
	private final By WELCOME_DD_CHECK_MY_PULSE_LINK_LOC = By.xpath("//div[@id='account-info-button']//a[contains(text(),'Check My Pulse')]");
	private final By PROFILE_NAME_LOC = By.xpath("//div[@class='profile-image']");
	private final By MY_ACCOUNT_UNDER_PROFILE_NAME_LOC = By.xpath("//div[contains(@class,'profile-header')]//a[text()='My Account']");
	private final By WELCOME_DD_PC_PERKS_STATUS_LINK_LOC = By.xpath("//a[text()='PC Perks Status']");
	private final By BUY_NOW_BTN = By.xpath("//a[text()='Buy Now']");
	
	private String linkUnderShopSkinCareOrBeAConsultant = "//div[@id='LeftNav']//a/span[text()='%s']";
	private String addToCRPButton = "//div[@id='main-content']/descendant::*[contains(text(),'ADD TO CRP') or @value='Add to crp'][%s]";
	private String addToBagButton = "//div[@id='main-content']/descendant::*[contains(text(),'ADD TO BAG') or @value='Add to bag'][%s]";

	protected RFWebsiteDriver driver;
	private String RFO_DB = null;
	String countryId = null;

	Actions actions;
	public StoreFrontRFWebsiteBasePage(RFWebsiteDriver driver){		
		super(driver);
		this.driver = driver;		
	}

	public boolean isRodanAndFieldsLogoDisplayed(){
		return driver.isElementVisible(By.id("header-logo"));
	}

	public static String convertDBDateFormatToUIFormat(String DBDate){
		String UIMonth=null;
		String[] splittedDate = DBDate.split(" ");
		String date = (splittedDate[0].split("-")[2].charAt(0))=='0'?splittedDate[0].split("-")[2].split("0")[1]:splittedDate[0].split("-")[2];
		String month = (splittedDate[0].split("-")[1].charAt(0))=='0'?splittedDate[0].split("-")[1].split("0")[1]:splittedDate[0].split("-")[1];		
		String year = splittedDate[0].split("-")[0];		
		switch (Integer.parseInt(month)) {		
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

		return UIMonth+" "+date+", "+year;
	}

	public void clickOnShopLink(){
		driver.waitForElementPresent(By.id("our-products"));
		driver.click(By.id("our-products"),"");
		logger.info("Shop link clicked ");
	}

	public void clickOnAllProductsLink(){
		try{
			//driver.waitForElementPresent(By.xpath("//a[@title='All Products']"));
			//driver.click(By.xpath("//a[@title='All Products']"));
			driver.moveToELement(By.xpath("//*[@id='header']//A[@id='our-products']"));
			//driver.waitForElementPresent(By.xpath("//A[contains(text(),'All Products')]"));
			driver.moveToELement(By.xpath("//A[contains(text(),'All Products')]"));
			driver.click(By.xpath("//A[contains(text(),'All Products')]"),"");
		}catch(NoSuchElementException e){
			logger.info("All products link was not present");
			driver.click(By.xpath("//div[@id='dropdown-menu']//a[@href='/us/quick-shop/quickShop']"),"");
		}
		logger.info("All products link clicked "+"//a[@title='All Products']");	
		driver.waitForPageLoad();
	}

	public void deleteAllItemsFromCart(){
		int totalProductsInCart = driver.findElements(By.xpath("//div[@class='cartItems']/descendant::a[text()='Delete']")).size();
		logger.info("total products to delete from cart ="+totalProductsInCart);
		while(driver.isElementVisible(By.xpath("//div[@class='cartItems']/descendant::a[text()='Delete']"),5)){
			driver.click(By.xpath("//div[@class='cartItems']/descendant::a[text()='Delete'][1]"),"Delete the product from cart");
			driver.waitForLoadingImageToDisappear();
			driver.waitForPageLoad();
		}
	}

	public StoreFrontUpdateCartPage clickOnQuickShopImage(){
		driver.waitForElementToBeVisible(By.xpath("//a[@href='/us/quick-shop/quickShop' and @title='']"), 30);
		driver.waitForElementPresent(By.xpath("//a[@href='/us/quick-shop/quickShop' and @title='']"));
		driver.click(By.xpath("//a[@href='/us/quick-shop/quickShop' and @title='']"),"");
		logger.info("Quick shop Img link clicked "+"//a[@href='/us/quick-shop/quickShop' and @title='']");
		driver.waitForPageLoad();
		return new StoreFrontUpdateCartPage(driver);
	}

	public boolean areProductsDisplayed(){
		driver.waitForElementPresent(By.xpath("//div[contains(@class,'quickshop-section')]"));
		return driver.isElementPresent(By.xpath("//div[contains(@class,'quickshop-section')]"));
	}

	public String selectProductForCRPAndProceedToBuy(String priceFilter, String productNumber){
		if(priceFilter.equals(TestConstants.PRICE_FILTER_HIGH_TO_LOW))
			applyPriceFilterHighToLow();
		else
			applyPriceFilterLowToHigh();

		String productName = null;
		String addToCRPButtonOfFirstProductLoc=String.format(addToCRPButton,productNumber);
		String addToCRPButtonOfSecondProductLoc=String.format(addToCRPButton,String.valueOf(Integer.parseInt(productNumber)+1));
		WebElement  addToCRPButtonOfFirstProduct = driver.findElement(By.xpath(addToCRPButtonOfFirstProductLoc));
		if(addToCRPButtonOfFirstProduct.isEnabled()==true){
			productName = driver.findElement(By.xpath(addToCRPButtonOfFirstProductLoc+"/preceding::a[1]")).getText();
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath(addToCRPButtonOfFirstProductLoc),"first product");
		}
		else{
			productName = driver.findElement(By.xpath(addToCRPButtonOfSecondProductLoc+"/preceding::a[1]")).getText();
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath(addToCRPButtonOfSecondProductLoc),"second product");
		}
		driver.waitForLoadingImageToDisappear();
		clickOnOKBtn();
		driver.waitForPageLoad();
		return productName;
	}

	public String selectProductForAdhocAndProceedToBuy(String priceFilter, String productNumber){
		if(priceFilter.equals(TestConstants.PRICE_FILTER_HIGH_TO_LOW))
			applyPriceFilterHighToLow();
		else
			applyPriceFilterLowToHigh();
		String productName = null;
		String addToBagButtonOfFirstProductLoc=String.format(addToBagButton,productNumber);
		String addToBagButtonOfSecondProductLoc=String.format(addToBagButton,String.valueOf(Integer.parseInt(productNumber)+1));
		WebElement  addToBagButtonOfFirstProduct = driver.findElement(By.xpath(addToBagButtonOfFirstProductLoc));
		if(addToBagButtonOfFirstProduct.isEnabled()==true){
			productName = driver.findElement(By.xpath(addToBagButtonOfFirstProductLoc+"/preceding::a[1]")).getText();
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath(addToBagButtonOfFirstProductLoc),"first product");
		}
		else{
			productName = driver.getText(By.xpath(addToBagButtonOfSecondProductLoc+"/preceding::a[1]"),"product name");
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath(addToBagButtonOfSecondProductLoc),"second product");
		}
		driver.waitForLoadingImageToDisappear();
		clickOnOKBtn();
		driver.waitForPageLoad();
		return productName;
	}

	public Boolean isCRPButtonPresentOnProductsPage(){
		String addToCRPButtonOfFirstProduct=String.format(addToCRPButton,"1");
		return driver.isElementVisible(By.xpath(addToCRPButtonOfFirstProduct));
	}

	public String getNameOfTheOnlyAddedProductOnCart(){
		String productNameFromCart = null;
		productNameFromCart=driver.findElement(By.xpath("//div[@id='left-shopping']/div[@class='cartItems']//h3")).getText();
		logger.info("product name from cart is "+productNameFromCart);
		return productNameFromCart;
	}

	/**
	 * This method will click on the Add to PC Perks button of the first product
	 * @return added product name
	 * 
	 */
	public String selectProductAndProceedToAddToPCPerks(){
		String productAddToPCPerksBtnLoc ="//div[@id='main-content']/descendant::*[contains(text(),'ADD TO PC PERKS') or @value='ADD to PC Perks'][1]";
		String productName= driver.getText(By.xpath(productAddToPCPerksBtnLoc+"/preceding::a[1]"));
		driver.waitForElementPresent(By.xpath(productAddToPCPerksBtnLoc));
		driver.click(By.xpath(productAddToPCPerksBtnLoc),"Add To PC Perks button");
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[@value='OK']"));
			driver.click(By.xpath("//input[@value='OK']"),"");
			driver.waitForLoadingImageToDisappear();
		}catch(Exception e){

		}
		driver.waitForLoadingImageToDisappear();
		return productName;
	}


	public void clickOnAddToPcPerksButton(){
		driver.waitForPageLoad();
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::input[contains(@value,'ADD to PC Perks')][1]"));
		//driver.click(By.xpath("//div[@id='main-content']/descendant::input[contains(@value,'ADD to PC Perks')][1]"));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='main-content']/descendant::input[contains(@value,'ADD to PC Perks')][1]"),"");
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[@value='OK']"));
			driver.click(By.xpath("//input[@value='OK']"),"");
			driver.waitForLoadingImageToDisappear();
		}catch(Exception e){

		}
	}

	public void addQuantityOfProduct(String qty) {
		//driver.pauseExecutionFor(2000);
		try{
			driver.quickWaitForElementPresent(By.id("quantity0"));
			driver.clear(By.id("quantity0"));
			driver.type(By.id("quantity0"),qty);
			logger.info("quantity added is "+qty);
			driver.click(By.xpath("//div[@id='left-shopping']/div//a[@class='updateLink']"),"");
			logger.info("Update button clicked after adding quantity");
		}catch(NoSuchElementException e){			
			driver.waitForElementPresent(By.id("quantity1"));
			driver.clear(By.id("quantity1"));
			driver.type(By.id("quantity1"),qty);
			logger.info("quantity added is "+qty);
			driver.click(By.xpath("//div[@id='left-shopping']/div//a[@class='updateLink']"),"");
			logger.info("Update button clicked after adding quantity");
		}
		//driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
	}

	public void clickOnNextBtnAfterAddingProductAndQty() {
		driver.clickByJS(RFWebsiteDriver.driver, By.id("submitForm"),"Next button after adding quantity");
		driver.waitForLoadingImageToDisappear();
	}

	public boolean isCartPageDisplayed(){
		driver.waitForElementPresent(By.xpath("//div[@id='left-shopping']/h1"));
		return driver.IsElementVisible(driver.findElement(By.xpath("//div[@id='left-shopping']/h1")));
	}

	public boolean verifyNumberOfProductsInCart(String numberOfProductsInCart){
		driver.waitForElementPresent(By.xpath("//div[@id='left-shopping']/h1/span"));
		return driver.findElement(By.xpath("//div[@id='left-shopping']/h1/span")).getText().contains(numberOfProductsInCart);
	}

	public int getRemovableProductsCountInTheCart(){
		return driver.findElements(By.xpath("//div[contains(@class,'shopping-item')]//*[text()='Delete']")).size();
	}

	public boolean isCartHasZeroItems(){
		driver.waitForElementPresent(By.xpath("//div[@id='left-shopping']//span[contains(text(),'0 item')]"));
		return driver.isElementPresent(By.xpath("//div[@id='left-shopping']//span[contains(text(),'0 item')]"));
	}

	public void clickOnCheckoutButton(){
		driver.waitForElementPresent(By.xpath("//input[@value='PLACE ORDER']"));
		driver.click(By.xpath("//input[@value='PLACE ORDER']"),"");
		logger.info("checkout button clicked");
		driver.waitForPageLoad();
	}

	public boolean isLoginOrCreateAccountPageDisplayed(){
		driver.waitForElementPresent(By.xpath("//h1[text()='Log in or register']"));
		return driver.IsElementVisible(driver.findElement(By.xpath("//h1[text()='Log in or register']")));
	}

	/**
	 * This method is used for entering RC details on LoginOrCreateAccountPage and click create new Account button
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param password
	 * @
	 */
	public void enterNewRCDetails(String firstName,String lastName,String emailAddress,String password) {
		driver.type(By.id("first-Name"),firstName,"RC firstName");
		driver.type(By.id("last-name"),lastName,"RC lastName");
		driver.type(By.id("email-account"),emailAddress+"\t","RC email");
		driver.type(By.id("password"),password,"RC password");
		driver.type(By.id("the-password-again"),password,"RC confirm password");
		driver.waitForElementTobeEnabled( By.xpath("//input[@id='next-button']"), 20);
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//*[@id='next-button']"),"Create New Account button");
		driver.pauseExecutionFor(1000);
		if(driver.isElementVisible(By.id("next-button"))){
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='next-button']"),"Create New Account button");
		}
		driver.waitForLoadingImageToDisappear();
	}

	/**
	 * This method is used for entering PC details on LoginOrCreateAccountPage, check become PC checkbox and click create new Account button
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param password
	 * @
	 */
	public void enterNewPCDetails(String firstName,String lastName,String emailAddress,String password) {
		driver.type(By.id("first-Name"),firstName,"PC firstName");
		driver.type(By.id("last-name"),lastName,"PC lastName");
		driver.type(By.id("email-account"),emailAddress+"\t","PC email");
		driver.type(By.id("password"),password,"PC password");
		driver.type(By.id("the-password-again"),password,"PC confirm password");
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='become-pc']/.."),"Become PC checkbox"); 

		if (isMobile()) {
			driver.click(By.id("next-button"), "Create account button");
		}else {

			driver.waitForElementTobeEnabled( By.xpath("//input[@id='next-button']"), 20);
			driver.click(By.xpath("//input[@id='next-button']"),"Create New Account button");
			driver.pauseExecutionFor(1000);
			if(driver.isElementVisible(By.id("next-button"))){
				driver.click( By.xpath("//input[@id='next-button']"),"Create New Account button");
			}
		}
		driver.waitForLoadingImageToDisappear();
		if(isProductNotAvailableForReplenishment()){
			deleteAllItemsFromCart();
			clickOnContinueShoppingLink();
			selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER_3);
			clickOnCheckoutButton();
		}
	}

	public boolean isProductNotAvailableForReplenishment(){
		return driver.isElementVisible(By.xpath("//*[contains(text(),'not available')]"), 15);
	}

	public boolean isPopUpForPCThresholdPresent() {
		boolean isPopUpForPCThresholdPresent=false;
		driver.waitForElementPresent(By.xpath("//div[@id='popup-content']//p[contains(text(),'Please add products')]"));
		isPopUpForPCThresholdPresent = driver.IsElementVisible(driver.findElement(By.xpath("//div[@id='popup-content']//p[contains(text(),'Please add products')]")));
		if(isPopUpForPCThresholdPresent==true){
			driver.click(By.xpath("//div[@id='popup-content']//input"),"");
			return true;
		}
		return false;
	}

	public boolean isCheckoutPageDisplayed(){
		driver.waitForElementPresent(By.xpath("//h1[contains(text(),'Checkout')]"));
		return driver.IsElementVisible(driver.findElement(By.xpath("//h1[contains(text(),'Checkout')]")));
	}

	public void enterMainAccountInfo(String address1,String city,String province,String postalCode,String phoneNumber){
		driver.waitForElementToBeVisible(By.id("address.line1"), 20);
		driver.type(By.id("address.line1"),address1,"addressLine1");
		driver.type(By.id("address.townCity"),city,"city");
		driver.click(By.xpath("//div[@id='checkout_delivery_address']//select[@id='state']/.."),"state");
		driver.pauseExecutionFor(500);
		driver.click(By.xpath("//div[@id='checkout_delivery_address']//select[@id='state']/option[contains(text(),'"+province+"')]"),province);
		driver.type(By.id("address.postcode"),postalCode,"postalcode");
		driver.type(By.id("address.phonenumber"),phoneNumber,"phoneNumber");
	}

	public void clickOnNextButtonAfterSelectingSponsor() {
		driver.clickByJS(RFWebsiteDriver.driver, By.id("saveAccountAddress"),"Next button after selecting sponsor");
		By QASPopUpLoc =By.xpath("//*[contains(@id,'_AcceptOriginal')]");// By.id("QAS_AcceptOriginal");
		driver.waitForLoadingImageToDisappear(30);
		driver.quickWaitForElementPresent(QASPopUpLoc,20);
		if(driver.isElementPresent(QASPopUpLoc)){
			driver.click(QASPopUpLoc,"Use as entered button");     
			driver.waitForLoadingImageToDisappear(30);
			driver.waitForPageLoad();
		}
		else
			logger.info("QAS Popup NOT present");  

	}

	public void clickOnShippingAddressNextStepBtn() {
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
		try{
			driver.turnOffImplicitWaits();
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[contains(@class,'use_address')]"),"save shipping info..");
		}catch(Exception e){
			driver.clickByJS(RFWebsiteDriver.driver, By.id("saveShippingInfo"),"Save shipping Info button");
		}  
		logger.info("Next button on shipping address clicked");  

		//TODO what is the reason for second click? Need to sort it out
		/*
		try{
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[contains(@class,'use_address')]"),"save shipping info");
		}catch(Exception e){

		}		
		finally{
			driver.turnOnImplicitWaits();
		}
		 */
		driver.turnOnImplicitWaits();

		driver.pauseExecutionFor(5000);// don't remove this wait
		driver.waitForLoadingImageToDisappear(50);
	}

	public void enterNewBillingCardNumber(String cardNumber){
		driver.waitForElementToBeVisible(By.id("card-nr"), 15);
		if(driver.getDevice().equalsIgnoreCase("mobile")){
			driver.waitForElementPresent(By.id("card-nr"));
			driver.pauseExecutionFor(3000);
			driver.click(By.id("card-nr"),"");
			//			String val = cardNumber; 
			//			WebElement element = driver.findElement(By.id("card-nr"));
			//			element.clear();
			////			for (int i = 0; i < val.length(); i++){
			////				char c = val.charAt(i);
			////				String s = new StringBuilder().append(c).toString();
			////				element.sendKeys(s);
			////			} 
			//			element.sendKeys(cardNumber);
			driver.type(ADD_NEW_BILLING_CARD_NUMBER_LOC,cardNumber,"card number");
			driver.click(By.id("card-name"), "");
			driver.pauseExecutionFor(2000);

		}
		else{
			driver.quickWaitForElementPresent(By.id("card-nr"));		
			JavascriptExecutor js = ((JavascriptExecutor)RFWebsiteDriver.driver);
			js.executeScript("$('#card-nr-masked').hide();$('#card-nr').show(); ", driver.findElement(ADD_NEW_BILLING_CARD_NUMBER_LOC));
			driver.pauseExecutionFor(1000);
			driver.type(ADD_NEW_BILLING_CARD_NUMBER_LOC,cardNumber,"card number");
		}
	}


	public void enterBillingDetailsAndSave(String cardNumber,String nameOnCard,String expMonth,String expYear,String CVV){
		enterNewBillingNameOnCard(nameOnCard);
		enterNewBillingCardNumber(cardNumber);
		selectNewBillingCardExpirationDate(expMonth, expYear);
		enterNewBillingSecurityCode(CVV);
		selectNewBillingCardAddress();
		clickOnSaveBillingProfile();
	}

	public void enterBillingDetails(String cardNumber,String nameOnCard,String expMonth,String expYear,String CVV){
		enterNewBillingNameOnCard(nameOnCard);
		enterNewBillingCardNumber(cardNumber);
		selectNewBillingCardExpirationDate(expMonth, expYear);
		enterNewBillingSecurityCode(CVV);
		selectNewBillingCardAddress();
	}

	public void enterNewBillingNameOnCard(String nameOnCard){
		driver.scrollDownIntoView(By.id("card-name"));
		driver.type(By.id("card-name"),nameOnCard,"name on card");
	}

	public void enterNewBillingSecurityCode(String securityCode){
		driver.type(By.id("security-code"), securityCode,"CVV");
	}

	public void clickPlaceOrderBtn(){
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//*[@id='placeOrderButton']"),"Place Order button");
		driver.waitForPageLoad();  
	}

	public void switchToPreviousTab(){
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		//driver.pauseExecutionFor(2000);
	}

	public void dismissPolicyPopup(){
		try {	
			driver.turnOffImplicitWaits(1);
			WebElement we = driver.findElement(By.xpath("//div[@class='shipping-popup-gray']/span[1]"));
			if (we.isDisplayed()){
				we.click();
				driver.click(By.xpath("//input[@value='Continue']"),"Policy Popup Dialog");
				driver.waitForLoadingImageToDisappear();
			}
			//do nothing
		}
		catch (Exception e) {
		}
		finally{
			driver.turnOnImplicitWaits();
		}
	} 

	public void clickRenewLater()  {
		try{
			driver.turnOffImplicitWaits(1);
			driver.click(By.xpath("//div[contains(@class,'fancybox-overlay')]//input[@id='renewLater']"),"Renew later button");
			driver.waitForLoadingImageToDisappear(3);
		}catch(Exception e){

		}
		finally{
			driver.turnOnImplicitWaits();
		}
	}

	public void handlePolicies(){
		dismissPolicyPopup();
		clickRenewLater();
	}

	public boolean isOrderPlacedSuccessfully(){
		driver.waitForElementPresent(By.xpath("//h1[contains(text(),'Thank you')]"));
		return driver.IsElementVisible(driver.findElement(By.xpath("//h1[contains(text(),'Thank you')]")));
	}

	public boolean verifyWelcomeDropdownToCheckUserRegistered(){		
		driver.waitForElementPresent(By.id("account-info-button"));
		return driver.isElementPresent(By.id("account-info-button"));
		//driver.findElement(By.xpath("//div[@id='account-info-button']/a")).getText().contains("Welcome");
	}

	public boolean isPCEnrolledCongratsMessagePresent(){
		driver.waitForElementPresent(By.xpath("//div[@id='Congrats']//span[contains(text(),'Welcome to Rodan + Fields')]/parent::h1[contains(text(),'PC Perks')]"));
		return driver.isElementPresent(By.xpath("//div[@id='Congrats']//span[contains(text(),'Welcome to Rodan + Fields')]/parent::h1[contains(text(),'PC Perks')]"));
	}

	public void applyPriceFilterLowToHigh(){
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//select[@id='sortOptions']"),"Price drop down");
		driver.click(By.xpath("//select[@id='sortOptions']/option[3]"),"low to high");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public boolean verifyPCPerksTermsAndConditionsPopup() {
		boolean isPCPerksTermsAndConditionsPopup = false;
		driver.waitForElementPresent(By.xpath("//div[@id='pcperktermsconditions']//input[@value='Ok']"));
		isPCPerksTermsAndConditionsPopup = driver.IsElementVisible(driver.findElement(By.xpath("//div[@id='pcperktermsconditions']//input[@value='Ok']")));
		if(isPCPerksTermsAndConditionsPopup==true){
			driver.click(By.xpath("//div[@id='pcperktermsconditions']//input[@value='Ok']"),"");
			return true;
		}
		return false;
	}

	public boolean validatePasswordFieldMessage(){
		if(driver.findElement(By.xpath("//div[contains(text(),'Please enter 6')]")).isDisplayed()){
			return true;
		}
		else{
			return false;
		}
	}
	public void clearPasswordField(){
		driver.clear(By.id("new-password-account"));
		logger.info("password field cleared");
	}

	public boolean recurringMonthlyChargesSection() {
		driver.quickWaitForElementPresent(By.xpath("//h3[contains(text(),'Recurring Monthly Charges')]"));
		return driver.findElement(By.xpath("//h3[contains(text(),'Recurring Monthly Charges')]")).isDisplayed();
	}

	public boolean pulseSubscriptionTextbox() {
		driver.waitForElementPresent(By.id("webSitePrefix"));
		return driver.findElement(By.id("webSitePrefix")).isEnabled();
	}

	public void enterSpouseFirstName(String firstName){
		driver.waitForElementToBeVisible(By.id("spouse-first"), 15);
		driver.type(By.id("spouse-first"),firstName);
		logger.info("Spouse first name entered as "+firstName);
	}

	public void enterSpouseLastName(String firstName){
		driver.type(By.id("spouse-last"),firstName);
		logger.info("Spouse last name entered as "+firstName);
	}

	public void clickOnRequestASponsorBtn(){
		driver.click(By.xpath("//*[@value='Request a sponsor' or @id='requestsponsor']"),"");
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOKOnSponsorInformationPopup(){
		//driver.pauseExecutionFor(2000);
		try{
			//   driver.waitForElementToBeVisible(By.xpath("//div[@id='sponsorMessage']//div[@id='popup-sponsorMessage']//input[contains(@value,'OK')]"), 15);
			driver.quickWaitForElementPresent(By.xpath("//div[@id='confirm-left-shopping']//div[@id='popup']//input[@value ='OK ']"));
			driver.click(By.xpath("//div[@id='confirm-left-shopping']//div[@id='popup']//input[@value ='OK ']"),"");
		}catch(Exception e){
			logger.info("No sponsor informantion popup appeared");
		}
	}

	public void clickYesIWantToJoinPCPerksCB(){
		driver.waitForElementPresent(By.id("pc-customer2-div-order-summary"));
		driver.clickByJS(RFWebsiteDriver.driver,By.id("pc-customer2-div-order-summary"),"");
		driver.waitForPageLoad();
	}

	public void checkIAcknowledgePCAccountCheckBox(){
		try{
			driver.waitForElementPresent(By.xpath("//input[@id='Terms2']/.."));
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='Terms2']/.."),"");
			logger.info("I Acknowledge PC Account Checkbox selected");
		}catch(Exception e){
			driver.waitForElementPresent(By.xpath("//form[@id='placeOrderForm1']/ul/div[1]//li[1]/div"));
			driver.click(By.xpath("//form[@id='placeOrderForm1']/ul/div[1]//li[1]/div"),"");  
			logger.info("I Acknowledge PC Account Checkbox selected");
		}
	}

	public void checkPCPerksTermsAndConditionsCheckBox(){
		try{
			driver.waitForElementPresent(By.xpath("//input[@id='Terms3']/.."));
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='Terms3']/.."),"");
			//driver.click(By.xpath("//input[@id='Terms3']/.."));  
			logger.info("PC Perks terms and condition checkbox selected");
		}catch(Exception e){
			driver.waitForElementPresent(By.xpath("//form[@id='placeOrderForm1']/ul/div[1]//li[2]/div"));
			driver.click(By.xpath("//form[@id='placeOrderForm1']/ul/div[1]//li[2]/div"),"");  
			logger.info("PC Perks terms and condition checkbox selected");
		}
	}

	public boolean isWelcomePCPerksMessageDisplayed(){
		driver.waitForElementPresent(By.xpath("//div[@id='Congrats']/h1[contains(text(),'PC')]"));
		return driver.IsElementVisible(driver.findElement(By.xpath("//div[@id='Congrats']/h1[contains(text(),'PC')]")));
	}

	public String createNewRC(String firstName,String lastName,String password){	
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		//		String firstName="RCUser";
		//		String lastName = "Test";
		String emailAddress = firstName+randomNum+TestConstants.EMAIL_ADDRESS_SUFFIX;
		driver.type(By.id("first-Name"),firstName);
		logger.info("first name entered as "+firstName);
		driver.type(By.id("last-name"),lastName);
		logger.info("last name entered as "+lastName);
		driver.type(By.id("email-account"),emailAddress+"\t");
		logger.info("email entered as "+emailAddress);
		//driver.pauseExecutionFor(2000);
		driver.type(By.id("password"),password);
		logger.info("password entered as "+password);
		driver.type(By.id("the-password-again"),password);
		logger.info("confirm password entered as "+password);
		driver.click(By.id("next-button"),"");  
		logger.info("Create New Account button clicked");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
		return emailAddress;
	}

	public void enterNewRCDetails(String firstName,String emailAddress) {
		String lastName = "ln";
		driver.type(By.id("first-Name"),firstName);
		logger.info("first name entered as "+firstName);
		driver.type(By.id("last-name"),lastName);
		logger.info("last name entered as "+lastName);
		driver.clear(By.id("email-account"));
		driver.type(By.id("email-account"),emailAddress+"\t");
		logger.info("email entered as "+emailAddress);
		//driver.pauseExecutionFor(2000);
	}

	public StoreFrontCartAutoShipPage clickEditCrpLinkPresentOnWelcomeDropDown() {
		driver.click(WELCOME_DD_EDIT_CRP_LINK_LOC,"edit Crp link from welcome drop down");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
		return new StoreFrontCartAutoShipPage(driver);
	}

	public boolean isEditCRPLinkPresent(){
		try{
			driver.waitForElementPresent(WELCOME_DD_EDIT_CRP_LINK_LOC);
			driver.findElement(WELCOME_DD_EDIT_CRP_LINK_LOC);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean isCheckMyPulseLinkPresent(){
		try{
			driver.findElement(WELCOME_DD_CHECK_MY_PULSE_LINK_LOC);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean validateExistingUserPopUp(String emailId){
		driver.findElement(By.id("email-account")).sendKeys(emailId);
		logger.info("email entered as "+emailId);
		driver.click(By.id("password"),"");
		//		try{
		//			driver.click(By.id("new-password-account"));
		//		}catch(Exception e){
		//			driver.click(By.id("password"));
		//		}
		driver.pauseExecutionFor(1000);
		driver.waitForElementPresent(By.xpath("//div[@class='fancybox-inner']"));
		return driver.isElementPresent(By.xpath("//div[@class='fancybox-inner']"));
	}

	public boolean validateCancelEnrollmentFunctionalityPC(){
		driver.waitForElementPresent(By.xpath("//div[@id='activePCPopup']//input[contains(@class,'cancelEnrollment')]"));
		driver.click(By.xpath("//div[@id='activePCPopup']//input[contains(@class,'cancelEnrollment')]"),"");
		//driver.pauseExecutionFor(2000);
		driver.waitForLoadingImageToDisappear();
		//driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//div[@class='fancybox-inner']"));
	}

	public boolean validateSendMailToResetMyPasswordFunctionalityRC(){
		driver.waitForElementPresent(By.xpath(" //div[@id='activeRetailPopup']//input[contains(@class,'resetPasswordEmail')]"));
		JavascriptExecutor js = ((JavascriptExecutor)RFWebsiteDriver.driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(" //div[@id='activeRetailPopup']//input[contains(@class,'resetPasswordEmail')]")));
		return driver.isElementPresent(By.xpath("//div[contains(text(),'An e-mail has been sent to reset your password')]"));   
	}


	public boolean validateCancelEnrollmentFunctionality(){
		driver.waitForElementPresent(By.xpath("//div[@id='activeRetailPopup']//input[contains(@class,'cancelEnrollment')]"));
		driver.click(By.xpath("//div[@id='activeRetailPopup']//input[contains(@class,'cancelEnrollment')]"),"");
		//driver.pauseExecutionFor(2000);
		driver.waitForLoadingImageToDisappear();
		return driver.isElementPresent(By.xpath("//div[@id='header']//a[@title='BECOME A CONSULTANT']")); 
	}


	public boolean validateSendMailToResetMyPasswordFunctionalityConsultant(){
		driver.waitForElementPresent(By.xpath("//div[@id='notavailablePopup']//input[contains(@class,'resetPasswordEmail')]"));
		JavascriptExecutor js = ((JavascriptExecutor)RFWebsiteDriver.driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='notavailablePopup']//input[contains(@class,'resetPasswordEmail')]")));
		//driver.findElement(By.xpath("//div[@id='notavailablePopup']//input[@class='resetPasswordEmail']")).click();
		//driver.pauseExecutionFor(2000);
		driver.waitForLoadingImageToDisappear();
		//driver.pauseExecutionFor(2000);
		return driver.isElementPresent(By.xpath("//div[@id='header']//a[@title='BECOME A CONSULTANT']")); 

	}

	public boolean validateCancelEnrollmentFunctionalityConsultant(){
		driver.waitForElementPresent(By.xpath("//div[@id='notavailablePopup']//input[contains(@class,'cancelEnrollment')]"));
		driver.click(By.xpath("//div[@id='notavailablePopup']//input[contains(@class,'cancelEnrollment')]"),"");
		//driver.pauseExecutionFor(2000);
		driver.waitForLoadingImageToDisappear();
		return driver.isElementPresent(By.xpath("//div[@id='header']//a[@title='BECOME A CONSULTANT']"));
	}

	public boolean validateHomePage(){
		driver.waitForPageLoad();
		String url = driver.getURL();
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		return driver.getCurrentUrl().contains(url);
	}

	public void clickOnAllowMySpouseOrDomesticPartnerCheckboxForUncheck() {
		//driver.waitForElementToBeVisible(By.xpath("//input[@id='spouse-check']"), 15);
		boolean status=driver.findElement(By.xpath("//input[@id='spouse-check']/..")).isSelected();
		if(status==true){
			driver.click(By.xpath("//input[@id='spouse-check']/.."),"");
		}
	}

	public void cancelTheProvideAccessToSpousePopup(){
		driver.pauseExecutionFor(6000);
		if(driver.findElement(By.id("cancelSpouse")).isDisplayed()){
			driver.click(By.id("cancelSpouse"),"");
			driver.pauseExecutionFor(3000);
		}
	}

	public boolean verifyAllowMySpouseCheckBoxIsSelectedOrNot(){
		logger.info("Checkbox status "+driver.findElement(By.id("spouse-check")).isSelected());
		driver.waitForElementPresent(By.id("spouse-check"));
		if(driver.findElement(By.id("spouse-check")).getAttribute("class").equalsIgnoreCase("checked")){
			return true;
		}else{
			return false;
		}

	}

	public Object getValueFromQueryResult(List<Map<String, Object>> userDataList,String column){
		Object value = null;
		for (Map<String, Object> map : userDataList) {
			logger.info("query result:" + map.get(column));
			value = map.get(column);			
		}
		return value;
	}

	public String getBizPWS(String country,String env){
		RFO_DB = driver.getDBNameRFO();
		List<Map<String, Object>> randomActiveSitePrefixList =  null;
		String activeSitePrefix = null;
		String PWS = null;
		String countryID =null;

		if(country.equalsIgnoreCase("ca")){
			countryID="40";
		}
		else if(country.equalsIgnoreCase("us")){
			countryID="236";
		}else if(country.equalsIgnoreCase("au")){
			countryID="14";
		}
		randomActiveSitePrefixList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_SITE_PREFIX_RFO,countryID),RFO_DB);
		activeSitePrefix = (String) getValueFromQueryResult(randomActiveSitePrefixList, "SitePrefix");   
		PWS = "https://"+activeSitePrefix+".myrfo"+env+".biz/"+country.toLowerCase();
		logger.info("PWS is "+PWS);
		return PWS;
	}


	public String getComPWS(String country,String env){
		RFO_DB = driver.getDBNameRFO();
		List<Map<String, Object>> randomActiveSitePrefixList =  null;
		String activeSitePrefix = null;
		String PWS = null;
		String countryID =null;

		if(country.equalsIgnoreCase("ca")){
			countryID="40";
		}
		else if(country.equalsIgnoreCase("us")){
			countryID="236";
		}else if(country.equalsIgnoreCase("au")){
			countryID="14";
		}
		randomActiveSitePrefixList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_SITE_PREFIX_RFO,countryID),RFO_DB);
		activeSitePrefix = (String) getValueFromQueryResult(randomActiveSitePrefixList, "SitePrefix");			
		PWS = "http://"+activeSitePrefix+".myrfo"+env+".com/"+country.toLowerCase();
		logger.info("PWS is "+PWS);
		return PWS;
	}


	public String openPWSSite(String country,String env){
		if(driver.isUserFetchedFromDB().equalsIgnoreCase("false")){
			driver.get(" https://"+TestConstants.PREFIX_AU+".myrfo"+driver.getEnvironment()+".com/"+driver.getCountry()+"/");	
		}
		else{
			while(true){
				driver.get(getBizPWS(country, env));
				driver.waitForPageLoad();
				if(driver.getCurrentUrl().contains("sitenotfound")){
					logger.info("SITE NOT FOUND error,try new PWS");
					continue;
				}
				else
					break;
			}	
		}
		logger.info("PWS opened is "+driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}

	public String createPWSFromPrefix(String prefix,String pwsType){
		String pws = " https://"+prefix+".myrfo"+driver.getEnvironment()+"."+pwsType+"/"+driver.getCountry()+"/";
		logger.info("PWS opened is "+pws);
		return pws;
	}

	public String openComPWSSite(String country,String env){
		if(driver.isUserFetchedFromDB().equalsIgnoreCase("false")){
			driver.get(" https://"+TestConstants.PREFIX_AU+".myrfo"+driver.getEnvironment()+".com/"+driver.getCountry()+"/");	
		}
		else{
			while(true){
				driver.get(getComPWS(country, env));
				driver.waitForPageLoad();
				if(driver.getCurrentUrl().contains("sitenotfound"))
					continue;
				else
					break;
			}	
		}
		driver.waitForPageLoad();
		return driver.getCurrentUrl();
	}

	public String openBizPWSSite(String country,String env){
		if(driver.isUserFetchedFromDB().equalsIgnoreCase("false")){
			driver.get(" https://"+TestConstants.PREFIX_AU+".myrfo"+driver.getEnvironment()+".biz/"+driver.getCountry()+"/"); 
		}
		else{
			while(true){
				driver.get(getBizPWS(country, env));
				driver.waitForPageLoad();
				if(driver.getCurrentUrl().contains("sitenotfound"))
					continue;
				else
					break;
			} 
		}
		driver.waitForPageLoad();
		return driver.getCurrentUrl();
	}

	public void switchToChildWindow(){
		//driver.pauseExecutionFor(2000);
		driver.switchToSecondWindow();
		driver.waitForPageLoad();
	}

	public void clickCheckMyPulseLinkPresentOnWelcomeDropDown(){
		driver.waitForElementPresent(By.xpath("//*[@id='account-info-dropdown']//*[contains(text(),'Check My Pulse')]"));
		driver.click(By.xpath("//*[@id='account-info-dropdown']//*[contains(text(),'Check My Pulse')]"),"");
		driver.pauseExecutionFor(3000);
	}

	public void hoverOnShopLinkAndClickAllProductsLinks(){
		if(driver.getDevice().equalsIgnoreCase("mobile")){
			driver.click(By.xpath("//div[@id='header']//span[contains(@class,'icon-menu')]"),"left icon");
			driver.click(By.xpath("//div[contains(@class,'mobile-nav')]/descendant::li[@id='ShopOurProductsBar']"),"Shop Skincare link");
			if (isMobile()){
				driver.click(By.xpath("//div[contains(@class,'mobile-nav')]/descendant::li[@id='ShopOurProductsBar']"),"Shop Skincare link");
			}
			driver.pauseExecutionFor(500);
			driver.click(By.xpath("//div[contains(@class,'mobile-nav')]/descendant::li[@id='ShopOurProductsBar'][1]//a[contains(text(),'All Products')]"),"All Products");
		}
		else{
			Actions actions;
			WebElement shopSkinCare;
			actions= new Actions(RFWebsiteDriver.driver);
			driver.waitForElementPresent(By.xpath("//div[@id='header']/../descendant::a[contains(text(),'SHOP SKINCARE')][1]"));
			shopSkinCare = driver.findElement(By.xpath("//div[@id='header']/../descendant::a[contains(text(),'SHOP SKINCARE')][1]"));
			actions.moveToElement(shopSkinCare).pause(1000).click().build().perform();
			logger.info("MOUSEHOVER on shopskincare");
			WebElement allProducts = driver.findElement(By.xpath("//ul[@id='dropdown-menu' and @style='display: block;']//a[text()='All Products']"));
			actions.moveToElement(allProducts).pause(1000).click().build().perform();
			driver.waitForPageLoad();
			driver.waitForLoadingImageToDisappear();
		}
	}

	public void clickOnWelcomeDropDown() {
		driver.clickByJS(RFWebsiteDriver.driver, WELCOME_USER_DD_LOC,"Welcome Dropdown");
	}

	public StoreFrontOrdersPage clickOrdersLinkPresentOnWelcomeDropDown() {
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.waitForElementPresent(WELCOME_USER_DD_LOC);
		actions.moveToElement(driver.findElement(WELCOME_USER_DD_LOC)).perform();
		driver.waitForElementPresent(WELCOME_DD_ORDERS_LINK_LOC);
		actions.moveToElement(driver.findElement(WELCOME_DD_ORDERS_LINK_LOC)).click().perform();
		logger.info("User has clicked on orders link from welcome drop down");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
		return new StoreFrontOrdersPage(driver);
	}

	public void clickOnPlaceOrderButton(){
		driver.waitForElementPresent(By.xpath("//input[@value='NEXT' or @value='CHECKOUT']"));
		driver.click(By.xpath("//input[@value='NEXT' or @value='CHECKOUT']"),"");
		logger.info("Place order button clicked");
		driver.waitForPageLoad();
	}

	public boolean verifyUpradingToConsulTantPopup(){
		driver.waitForPageLoad();
		if(driver.isElementPresent(By.xpath("//div[@id='activePCPopup']//h2[contains(text(),'UPGRADING TO A CONSULTANT')]"))){
			return true;
		}else
			return false;
	}

	public boolean isActiveRetailPopupDisplayed(){
		return driver.isElementVisible(By.id("activeRetailPopup"));
	}

	public void enterPasswordForUpgradePcToConsultant(){
		try{
			driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Log In to Terminate My PC Account')]/following::input[2]"));
			driver.type(By.xpath("//h3[contains(text(),'Log In to Terminate My PC Account')]/following::input[2]"), driver.getStoreFrontPassword());
		}catch(Exception e){
			driver.quickWaitForElementPresent(By.xpath("//h3[contains(text(),'Log In to Reactivate My Account')]/following::input[2]"));
			driver.type(By.xpath("//h3[contains(text(),'Log In to Reactivate My Account')]/following::input[2]"), driver.getStoreFrontPassword());
		}
	}

	public void clickOnLoginToTerminateToMyPCAccount(){
		////driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Log In to Terminate My PC Account')]/following::a[2]/input"));
		driver.click(By.xpath("//h3[contains(text(),'Log In to Terminate My PC Account')]/following::a[2]/input"),"");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();	
		driver.pauseExecutionFor(3000);
		driver.waitForLoadingImageToDisappear();

	}

	public void navigateToCountry(){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//button"));
		String defaultSelectedCountry= driver.findElement(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//button")).getText();
		driver.click(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//button"),"");
		if(defaultSelectedCountry.contains("USA")){
			driver.waitForElementPresent(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//a[contains(text(),'CAN')]"));
			driver.click(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//a[contains(text(),'CAN')]"),"");
			logger.info("navigated to canada site successfully");
		}else{
			driver.waitForElementPresent(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//a[contains(text(),'USA')]"));
			driver.click(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//a[contains(text(),'USA')]"),"");
			logger.info("navigated to USA site successfully");
		}
		driver.waitForPageLoad();
	}

	public void navigateToCountry(String country){
		driver.waitForElementPresent(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//button"));
		//		String defaultSelectedCountry= driver.findElement(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//button")).getText();
		driver.click(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//button"),"");
		if(country.equalsIgnoreCase("ca")){
			driver.waitForElementPresent(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//a[contains(text(),'CAN')]"));
			driver.click(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//a[contains(text(),'CAN')]"),"");
			logger.info("navigated to canada site successfully");
		}
		else if(country.equalsIgnoreCase("au")){
			driver.waitForElementPresent(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//a[contains(text(),'AUS')]"));
			driver.click(By.xpath("//div[@class='footer-tagline-decide']/following::div[4]//a[contains(text(),'AUS')]"),"");
			logger.info("navigated to canada site successfully");
		}
		driver.waitForPageLoad();
	}

	public void clickOnBillingNextStepButton() {
		driver.waitForElementPresent(By.xpath("//div[@id='start-shipping-method']/div[2]/div/input"));
		driver.click(By.xpath("//*[@id='start-shipping-method']/div[2]/div/input"),"");
		logger.info("Next button on clicked"); 
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath("//div[@id='payment-next-button']/input"));
		driver.click(By.xpath("//div[@id='payment-next-button']/input"),"");
	}

	public boolean verifyConsultantCantShipToQuebecMsg(){
		String message = driver.findElement(By.xpath("//div[@id='errorQCEnrollDiv']")).getText();
		if(message.equals("Consultants cannot ship to Quebec.")){
			return true;
		}
		else{
			return false;
		}
	}

	public void clickOnAccountInfoNextButton(){
		driver.waitForElementPresent(By.id("clickNext"));
		driver.clickByJS(RFWebsiteDriver.driver,By.id("clickNext"),"");
	} 	

	public boolean verifyEditPcPerksIsPresentInWelcomDropdownForUpgrade(){
		if(driver.isElementPresent(By.xpath("//a[text()='Edit PC Perks']"))){
			return true;
		}else
			return false;
	}

	public String getSponserNameFromUIWhileEnrollingPCUser(){
		driver.waitForElementPresent(By.xpath("//div[@id='sponsorInfo']"));
		String sponserEmailID =driver.findElement(By.xpath("//div[@id='sponsorInfo']")).getText();
		logger.info("Default Sponser email address from UI is "+sponserEmailID);
		return sponserEmailID.toLowerCase();
	}

	public String splitPWS(String pws){
		if(pws.contains(".biz")){
			pws = pws.split(".biz")[0];	
		}
		else
			pws = pws.split(".com")[0];
		return pws;
	}

	public StoreFrontBillingInfoPage clickBillingInfoLinkPresentOnWelcomeDropDown(){
		driver.click(WELCOME_DD_BILLING_INFO_LINK_LOC,"billing link from welcome drop down");
		driver.waitForPageLoad();
		return new StoreFrontBillingInfoPage(driver);
	}

	public StoreFrontShippingInfoPage clickShippingLinkPresentOnWelcomeDropDown() {
		driver.click(WELCOME_DD_SHIPPING_INFO_LINK_LOC,"shipping link from welcome drop down");	
		driver.waitForPageLoad();
		return new StoreFrontShippingInfoPage(driver);
	}

	public void enterNewShippingAddressPostalCode(String postalCode){
		driver.type(By.id("postcode"),postalCode);
	}

	public void enterNewShippingAddressPhoneNumber(String phoneNumber){
		driver.type(By.id("phonenumber"),phoneNumber);
	}

	public void clickOnAutoshipCart(){
		driver.click(By.xpath("//div[@id='bag-special']/span"),"Autoship cart");
		driver.waitForPageLoad();
	}

	public void clickOnCRPCheckout(){
		driver.click(By.id("crpCheckoutButton"),"CRP checkout button");
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOnBillingNextStepButtonDuringEnrollInCRP() {
		try{
			driver.waitForElementPresent(By.xpath("//div[@id='payment-next-button']/input"));
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='payment-next-button']/input"),"");
		}catch(Exception e){
			driver.waitForElementPresent(By.xpath("//div[@id='start-shipping-method']/div[2]/div/input"));
			driver.click(By.xpath("//*[@id='start-shipping-method']/div[2]/div/input"),""); 
		}  
		logger.info("Next button on clicked"); 
		driver.waitForLoadingImageToDisappear();
	}

	public StoreFrontAccountInfoPage clickAccountInfoLinkPresentOnWelcomeDropDown() {
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//a[text()='Account Info']"),"Account Info link on Welcome DropDown");
		driver.waitForLoadingImageToDisappear();
		return new StoreFrontAccountInfoPage(driver);
	}

	public boolean isWelcomeDropDownPresent(){
		driver.waitForElementPresent(By.id("account-info-button"));
		return driver.isElementPresent(By.id("account-info-button"));
	}

	public boolean verifyOrderConfirmation(){
		logger.info("Asserting Order Confirmation Message");
		driver.waitForElementPresent(By.xpath("//div[@id='order-confirm']/span"));
		if(driver.findElement(By.xpath("//div[@id='order-confirm']/span")).getText().equalsIgnoreCase("Your CRP order has been created")){
			return true;
		}
		return false;
	}

	public boolean validateMyAccountDDPresentInTopNav(){
		driver.waitForElementPresent(WELCOME_USER_DD_LOC);
		return driver.isElementPresent(WELCOME_USER_DD_LOC);
	}

	public boolean validateAdhocCartIsDisplayed(){
		driver.waitForElementPresent(By.xpath("//span[@class='cart-section']"));
		return driver.isElementPresent(By.xpath("//span[@class='cart-section']"));
	}

	public void deselectPriceFilter() {
		driver.waitForElementPresent(By.xpath("//select[@id='sortOptions']"));
		driver.click(By.xpath("//select[@id='sortOptions']"),"");
		driver.click(By.xpath("//select[@id='sortOptions']/option[1]"),"");
		logger.info("deselect the price filter");
	}

	public String getProductPriceBeforeApplyFilter(){
		return driver.findElement(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][1]")).getText().split("\\$")[1].trim();
	}

	public void clickOnContinueShoppingLink(){
		driver.click(By.xpath("//*[@value='ADD MORE ITEMS' or contains(text(),'Continue shopping')]"),"Add More Items/Continue shopping");
		driver.waitForPageLoad();
	}

	public void selectAProductAndAddItToPCPerks(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::input[contains(@value,'ADD to PC Perks')][1]"));
		driver.click(By.xpath("//div[@id='main-content']/descendant::input[contains(@value,'ADD to PC Perks')][1]"),"");
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[@value='OK']"));
			driver.click(By.xpath("//input[@value='OK']"),"");
		}catch(Exception e){
		}
		logger.info("Add To PC perks button clicked");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean isBillingProfileIsSelectedByDefault(String profileName){
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+profileName+"')]/following::input[@checked='checked']"));
		return driver.isElementPresent(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+profileName+"')]/following::input[@checked='checked']"));
	}


	public boolean isTheBillingAddressPresentOnPage(String firstName){
		boolean isFirstNamePresent = false;
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-billing-profiles']/div"));

		//List<WebElement> allBillingProfiles = driver.findElements(By.xpath("//div[@id='multiple-billing-profiles']/div[contains(@class,'sel-profile')]/p[1]/span[@class='font-bold']"));
		List<WebElement> allBillingProfiles = driver.findElements(By.xpath("//div[@id='multiple-billing-profiles']//p[1]/span[@class='font-bold']"));
		for(WebElement e:allBillingProfiles){
			if(e.getText().contains(firstName)==true){
				isFirstNamePresent=true;
				break;
			}
		}
		return isFirstNamePresent;
	}

	public String getDotComPWS(){
		//		driver.waitForElementPresent(By.xpath("//p[@id='prefix-validation']/span[1]"));
		String pwsUnderPulse = driver.findElement(By.xpath("//p[@id='prefix-validation']/span[1]")).getText();
		return pwsUnderPulse;
	}

	public String getDotBizPWS(){
		//		driver.waitForElementPresent(By.xpath("//p[@id='prefix-validation']/span[2]"));
		String pwsUnderPulse = driver.findElement(By.xpath("//p[@id='prefix-validation']/span[2]")).getText();
		return pwsUnderPulse;
	}

	public String getEmailId(String country){
		driver.waitForElementPresent(By.xpath("//p[@id='prefix-validation']/span[3]"));
		String pwsUnderPulse = driver.findElement(By.xpath("//p[@id='prefix-validation']/span[3]")).getText();
		return pwsUnderPulse;
	}

	public boolean verifySpecialCharNotAcceptInPrefixName(){
		driver.quickWaitForElementPresent(By.xpath("//span[contains(text(),'This prefix is not available')] | //label[contains(text(),'Please fix this field')]"));
		if(driver.isElementPresent(By.xpath("//span[contains(text(),'This prefix is not available')] | //label[contains(text(),'Please fix this field')]"))){
			return true;
		}else
			return false;
	}

	public void selectDifferentProductAndAddItToCRP() {
		applyPriceFilterHighToLow();		
		driver.waitForElementPresent(By.xpath(String.format(addToCRPButton,"1")));
		driver.click(By.xpath(String.format(addToCRPButton,"1")),"");
		logger.info("Add to CRP button clicked");
		driver.waitForLoadingImageToDisappear();
	}

	public boolean verifyCheckoutConfirmationPOPupPresent(){
		driver.quickWaitForElementPresent(By.xpath("//div[@id='popup-review']"));
		return driver.isElementPresent(By.xpath("//div[@id='popup-review']"));		  
	}

	public void clickOnOkButtonOnCheckoutConfirmationPopUp(){
		driver.click(By.xpath("//input[@value='OK']"),"");
		driver.waitForPageLoad();
	}

	public boolean verifyAccountInfoPageHeaderPresent(){
		driver.waitForElementPresent(By.xpath("//div[@id='checkout_summary_deliveryaddress_div']//span"));
		if(driver.isElementPresent(By.xpath("//div[@id='checkout_summary_deliveryaddress_div']//span"))){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean verifyCheckoutConfirmationPopUpMessagePC(){
		if(driver.findElement(By.xpath("//div[@id='popup-review']/div/p")).getText().contains("This is not a PC Perks order.")){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean verifyCheckoutConfirmationPopUpMessageConsultant() {
		if(driver.findElement(By.xpath("//div[@id='popup-review']/div/p")).getText().contains("This is not a CRP order. You will be charged for regular order. Please click OK to continue.")){
			return true;}

		else{
			return false;
		}
	}

	public boolean verifyRetailPriceIsAvailableOnProductsPage(){
		return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::span[@class='old-price'][1]"));
	}

	public boolean verifyYourPriceIsAvailableOnProductsPage(){		
		return driver.isElementPresent(By.xpath("//div[@id='main-content']/descendant::span[@class='your-price'][1]"));		
	}

	public boolean verifyRetailPriceIsAvailableOnAdhocCart(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.id("cart-retail-price"));
	}

	public boolean verifyYourPriceIsAvailableOnAdhocCart(){
		return driver.isElementPresent(By.id("cart-price"));
	}

	public boolean verifyTotalSavingsIsAvailableOnAdhocCart(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//div[@class='checkout-module-content']//div[@id='module-total'][2]"));
	}

	public boolean verifyRetailPriceIsAvailableOnAutoshipCart(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//div[@class='cart-items']/div[1]//p[@id='cart-retail-price']"));
	}

	public boolean verifyYourPriceIsAvailableOnAutoshipCart(){
		return driver.isElementPresent(By.xpath("//div[@class='cart-items']/div[1]//p[@id='cart-price']"));
	}

	public boolean validateEditYourInformationLink(){
		driver.quickWaitForElementPresent(By.xpath("//a[text()='Personalize my Profile']"));
		return driver.isElementPresent(By.xpath("//a[text()='Personalize my Profile']"));
	}

	public void enterPasswordForUpgradeRCToConsultant(){
		driver.type(By.xpath("//p[contains(text(),'LOG IN TO TERMINATE MY RETAIL ACCOUNT')]/following::div[@id='terminate-log-in']/div[3]/input"), driver.getStoreFrontPassword());
	}

	public void clickOnLoginToTerminateToMyRCAccount(){
		driver.waitForElementPresent(By.xpath("//p[contains(text(),'LOG IN TO TERMINATE MY RETAIL ACCOUNT')]/following::a[@class='confirm']/input"));
		driver.click(By.xpath("//p[contains(text(),'LOG IN TO TERMINATE MY RETAIL ACCOUNT')]/following::a[@class='confirm']/input"),"");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(3000);
	}

	public boolean verifyAccountTerminationMessage(){
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'Your old account has been terminated successfully')]"));
		return driver.isElementPresent(By.xpath("//span[contains(text(),'Your old account has been terminated successfully')]"));
	}

	public void enterPhoneNumberOnEditPWS(String number){
		driver.waitForElementPresent(By.id("phone"));
		driver.type(By.id("phone"), number);
	}

	public void clickOnSaveAfterEditPWS(){
		driver.waitForElementPresent(By.xpath("//div[@class='editphotosmode']//input"));
		driver.click(By.xpath("//div[@class='editphotosmode']//input"),"");
	}

	public boolean verifyUpradingToConsulTantPopupForRC(){
		driver.waitForPageLoad();
		if(driver.isElementPresent(By.xpath("//div[@id='activePCPopup' and @style=' display:none;']"))){
			return true;
		}else
			return false;
	}

	public void clickOnConnectWithAConsultant(){
		driver.waitForElementPresent(By.xpath("//div[@class='hidden-xs']//h3[contains(text(),'CONNECT WITH A CONSULTANT')]/following::a[contains(text(),'CONNECT')][1]"));
		driver.click(By.xpath("//div[@class='hidden-xs']//h3[contains(text(),'CONNECT WITH A CONSULTANT')]/following::a[contains(text(),'CONNECT')][1]"),"");
	}

	public boolean verifyFindYourSponsorPage(){
		driver.waitForPageLoad();
		System.out.println("current url "+driver.getCurrentUrl());
		return driver.getCurrentUrl().toLowerCase().contains("sponsorpage");
		//return driver.isElementPresent(By.xpath("//h2[contains(text(),'Find Your R+F Sponsor')]"));
	}

	public boolean verifySponsorListIsPresentAfterClickOnSearch(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.id("search-results"));
	}

	public boolean isSponsorNameContainRFCorporate(){
		String sponsorName = driver.findElement(By.xpath("//div[@id='sponsorInfo']/span")).getText().toLowerCase(); 
		return sponsorName.contains("corporate") || sponsorName.contains(TestConstants.RF_CORP_SPONSOR);
	}

	public boolean isYourConsultantPresentWhilePCRCEnrollment(){
		try{
			driver.turnOffImplicitWaits();
			return driver.isElementVisible(By.xpath("//*[@id='sponsorInfo']"));

		}catch(Exception e){
			return false;
		}finally{
			driver.turnOnImplicitWaits();
		}
	}

	public boolean verifyConfirmationMessagePresentOnUI(){
		return driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).isDisplayed();
	}

	public void enterEditedCardNumber(String cardNumber){
		driver.waitForElementPresent(By.id("credit-cards"));  
		JavascriptExecutor js = ((JavascriptExecutor)RFWebsiteDriver.driver);
		js.executeScript("$('#card-nr-masked').hide();$('#card-nr').show(); ", driver.findElement(ADD_NEW_BILLING_CARD_NUMBER_LOC));
		//driver.pauseExecutionFor(2000);
		driver.waitForElementToBeVisible(ADD_NEW_BILLING_CARD_NUMBER_LOC, 10);
		driver.type(ADD_NEW_BILLING_CARD_NUMBER_LOC,cardNumber);
		logger.info("New Billing card number enterd as "+cardNumber);  
	}

	public String getSponsorResultAccordingToCID(String CID){
		driver.waitForPageLoad();
		return driver.findElement(By.xpath("//li[contains(text(),'"+CID+"')]")).getText();
	}

	public boolean isSearchedSponsorIdPresentInSearchList(String CID){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//li[contains(text(),'"+CID+"')]"));
	}

	public boolean validateCorpCurrentUrlPresent() {
		return driver.getCurrentUrl().contains("corp");
	}

	public void updateQuantityOfProductToTheSecondProduct(String qty) {
		driver.type(By.id("quantity1"),qty,"quantity");
		driver.click(By.xpath("//form[@id='updateCartForm1']/a"),"update quantity button");
		driver.waitForPageLoad();
	}

	public void clickUpdateCartBtn(){
		driver.clickByJS(RFWebsiteDriver.driver,UPDATE_CART_BTN_LOC,"Update cart button");		
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean verifyPCPerksInfoOnModalWindow(){
		driver.quickWaitForElementPresent(By.xpath("//div[contains(@class,'pc-perks fancybox')]"));
		return driver.isElementPresent(By.xpath("//div[contains(@class,'pc-perks fancybox')]"));
	}

	public boolean verifyAddNewBillingProfileLinkIsPresent(){
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Add new billing profile')]"));
		//driver.pauseExecutionFor(2000);
		return driver.isElementVisible(By.xpath("//a[contains(text(),'Add new billing profile')]"));
	}

	public String getPSTDate(){
		Date startTime = new Date();
		TimeZone pstTimeZone = TimeZone.getTimeZone("America/Los_Angeles");
		DateFormat formatter = DateFormat.getDateInstance();
		formatter.setTimeZone(pstTimeZone);
		String formattedDate = formatter.format(startTime);
		logger.info("PST Date is "+formattedDate);
		return formattedDate;
	}

	public boolean isShippingAddressSelected(String shippingProfileName){
		return driver.isElementPresent(By.xpath("//span[contains(text(),'"+shippingProfileName+"')]/ancestor::div[1]//input[@type='radio' and @checked='checked'][1]"));
	}

	public void selectBillingAddress(String billingProfileName){
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+billingProfileName+"')]/ancestor::div[1]//span[@class='radio-button billtothis']/input"));
		driver.click(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+billingProfileName+"')]/ancestor::div[1]//span[@class='radio-button billtothis']/input"),"");
		logger.info("Billing profile selected as: "+billingProfileName);
	}

	public void enterNewShippingAddressCity(String city){
		driver.type(By.id("townCity"),city);
	}

	public void clickOnSaveShippingProfile() {
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//*[@id='saveCrpShippingAddress' or @id='saveShippingAddreessId']"),""); 
		driver.waitForLoadingImageToDisappear();
		logger.info("Save shipping profile button clicked");
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[contains(@id,'AcceptOriginal')]"));
			driver.click(By.xpath("//input[contains(@id,'AcceptOriginal')]"),"");
			logger.info("Accept New shipping address button clicked");
		}catch(Exception e1){

		}  
		driver.waitForLoadingImageToDisappear();
	}

	public boolean isNewlyCreatedShippingProfileIsSelectedByDefault(String profileName){
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-addresses-summary']//span[contains(text(),'"+profileName+"')]/../following-sibling::p//input[@checked='checked']"));
		return driver.isElementPresent(By.xpath("//div[@id='multiple-addresses-summary']//span[contains(text(),'"+profileName+"')]/../following-sibling::p//input[@checked='checked']"));
	}

	public void enterNewShippingAddressLine1DuringEnrollment(String addressLine1){
		driver.clear(By.id("address-1"));
		driver.type(By.id("address-1"),addressLine1);
		logger.info("New Shipping Address is "+addressLine1);
	}

	public String getDefaultSelectedBillingAddressName(){
		driver.waitForElementPresent(By.xpath("//input[@checked='checked' and @name='bill-card']/../../preceding::p[3]/span[1]"));
		return driver.findElement(By.xpath("//input[@checked='checked' and @name='bill-card']/../../preceding::p[3]/span[1]")).getText();
	}

	public String getFirstBillingProfileName(){
		return driver.findElement(By.xpath("//div[@id='multiple-billing-profiles']/div[1]//p[1]/span[1]")).getText();
	}

	public void enterNewShippingAddressLine1(String addressLine1){
		driver.type(By.id("new-address-1"),addressLine1,"New Shipping Address");
	}

	public void clickOnEditForDefaultShippingAddress() {
		driver.navigate().refresh();
		driver.waitForPageLoad();
		driver.pauseExecutionFor(5000);
		clickOnEditShipping();
		driver.waitForElementPresent(By.xpath("//input[contains(@name,'shipping')][@checked='checked']/ancestor::div[contains(@class,'address-section')]//a[text()='Edit']"));
		driver.click(By.xpath("//input[contains(@name,'shipping')][@checked='checked']/ancestor::div[contains(@class,'address-section')]//a[text()='Edit']"),"");
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOnDefaultBillingProfileEdit() {
		//		driver.waitForElementPresent(By.xpath("//input[@checked='checked' and @name='bill-card']/preceding::p[1]/a"));
		//		((JavascriptExecutor) RFWebsiteDriver.driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@checked='checked' and @name='bill-card']/preceding::p[1]/a")));
		//		//driver.click(By.xpath("//input[@checked='checked' and @name='bill-card']/preceding::p[1]/a"));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@checked='checked' and @name='bill-card']/preceding::p[1]/a"),"Edit link of default billing profile");
		driver.waitForLoadingImageToDisappear();
	}

	public String getDefaultSelectedShippingAddress(){
		//driver.waitForElementPresent(By.xpath("//input[@checked='checked']/preceding::span[@class='font-bold'][1]"));
		return driver.findElement(By.xpath("//input[@checked='checked']/preceding::span[@class='font-bold'][1]")).getText();
	}

	public void clickOnEditOfBillingProfile(String profileName){
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'"+profileName+"')]/ancestor::div[1]//a[text()='Edit']"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//span[contains(text(),'"+profileName+"')]/ancestor::div[1]//a[text()='Edit']"),"");
		driver.waitForPageLoad();
		logger.info("billing profile"+profileName+" 's edit link clicked");
		driver.scrollDownIntoView(By.id("card-name"));
	}

	public void clickOnAcceptOfQASPopup(){
		if(driver.isElementPresent(By.xpath("//input[contains(@id,'AcceptOriginal')]"))) {
			driver.click(By.xpath("//input[contains(@id,'AcceptOriginal')]"),"");
			logger.info("Accept New shipping address button clicked");
			driver.waitForLoadingImageToDisappear();
		}
	}

	public boolean isShippingAddressPresentAtOrderAndReviewPage(String profilename){
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Shipping info')]/../..//span[contains(text(),'"+profilename+"')]"));
		return driver.isElementPresent(By.xpath("//h3[contains(text(),'Shipping info')]/../..//span[contains(text(),'"+profilename+"')]"));

	}

	public void clickOnCheckoutButtonAfterAddProduct(){
		driver.waitForElementPresent(By.xpath("//input[@value='CHECKOUT']"));
		driver.click(By.xpath("//input[@value='CHECKOUT']"),"");
		logger.info("Checkout button clicked");
	}

	public boolean isProfileHasUpdatedMessagePresent(){
		driver.pauseExecutionFor(2000);
		return driver.isElementPresent(By.xpath("//div[@id='globalMessages']//p[text()='Your profile has been updated']"));
	}

	public void mouseHoverOnMiniCart(){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.xpath("//a[@id='shopping-cart']"))).build().perform();
		driver.waitForLoadingImageToDisappear();
	}

	public String getNameOfOnlyProductAddedOnMiniCart(){
		String productNameOnMiniCart = null;
		driver.waitForElementPresent(By.xpath("//div[@id='mini-shopping']//a"));
		productNameOnMiniCart = driver.findElement(By.xpath("//div[@id='mini-shopping']//a")).getText().trim();
		logger.info("Product Name On Mini Cart is "+productNameOnMiniCart);
		return productNameOnMiniCart;
	}

	public void clickOnViewShippingCartBtnOnMiniCart(){
		driver.waitForElementToBeVisible(By.xpath("//input[@value='VIEW SHOPPING CART']"), 30);
		driver.click(By.xpath("//input[@value='VIEW SHOPPING CART']"),"");
		logger.info("'VIEW SHOPPING CART' button clicked on mini cart");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public boolean isBillingAddressPresentOnPage(String firstName){
		return driver.isElementVisible(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+firstName+"')]"));
	}

	public void clickEditPCPerksLinkPresentOnWelcomeDropDown() {
		driver.click(By.xpath("//a[text()='Edit PC Perks']"),"Edit PC Perks button on welcome drop down");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public void deleteShippingAddress(String shippingProfileName){
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+shippingProfileName+"')]/ancestor::div[1]//a[@class='deleteAddress']"));
		driver.click(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+shippingProfileName+"')]/ancestor::div[1]//a[@class='deleteAddress']"),"");
		logger.info("Shipping profile deleted as: "+shippingProfileName);
		driver.click(By.xpath("//input[contains(@value,'delete this profile')]"),"");
		driver.waitForPageLoad();
	}


	public void clickUseAsEnteredOnQASPopup() {
		try{			
			driver.quickWaitForElementPresent(By.xpath("//*[@id='QAS_RefineBtn']"));
			driver.click(By.xpath("//*[@id='QAS_RefineBtn']"),"");
			logger.info("QAS Popup clicked");
		}catch(Exception e){

		}
		driver.waitForLoadingImageToDisappear();
	}

	public void clickOnDeleteOfBillingProfile(String profileName){
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'"+profileName+"')]/ancestor::div[1]//a[text()='Delete']"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//span[contains(text(),'"+profileName+"')]/ancestor::div[1]//a[text()='Delete']"),"");
		driver.waitForPageLoad();
		logger.info("billing profile"+profileName+" 's delete link clicked");
		driver.click(By.xpath("//input[@value='Yes, delete this profile']"),"");
		logger.info("Clicked on confirm delete on popup.");
		driver.waitForPageLoad();
	}

	public void mouseHoverShopSkinCareAndClickLink(String link){
		driver.pauseExecutionFor(2000);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(SHOP_SKINCARE_LOC)).build().perform();
		logger.info("hover on Products link now as shop skincare");
		driver.click(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, link)),"");
		logger.info("Clicked "+link+" link is clicked after hovering shop skincare.");
		driver.waitForPageLoad();
	}

	public void clickAddToBagBtn() {	
		driver.quickWaitForElementPresent(By.xpath("//*[@id='FullPageItemList']/div[2]//a[@id='addToCartButton']"));
		if(driver.isElementPresent(By.xpath("//*[@id='FullPageItemList']/div[2]//a[@id='addToCartButton']"))){
			driver.click(By.xpath("//*[@id='FullPageItemList']/div[2]//a[@id='addToCartButton']"),"");	
		}
		logger.info("Add To Bag button clicked");
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public void clickOKBtnOfJavaScriptPopUp(){
		driver.pauseExecutionFor(2000);
		try{
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logger.info("Ok button of java Script popup is clicked.");
			driver.waitForPageLoad();
			driver.pauseExecutionFor(3000);
		}catch(Exception e){
			logger.info("No Alert is present");

		}
	}

	public void selectNewBillingCardAddress(){
		if(isMobile()){
			driver.waitForElementPresent(By.id("addressBookdropdown"));
			WebElement element=driver.findElement(By.id("addressBookdropdown"));
			Select select=new Select(element);
			driver.waitForElementPresent(By.xpath("//*[@id='addressBookdropdown']/option[1]"));
			select.selectByIndex(0);
			logger.info("New Billing card address selected");
		}else{
			driver.clickByJS(RFWebsiteDriver.driver,By.id("addressBookdropdown"),"New Billing card address dropdown");
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//*[@id='addressBookdropdown']/option[1]"),"first billing  address");
		}
	}

	public void selectNewBillingCardExpirationDate(){
		WebElement expMonthDD= driver.findElement(By.id("expiryMonth"));
		Select select=new Select(expMonthDD);
		select.selectByIndex(10);
		logger.info("New billing card exp month selected");
		WebElement expYearDD= driver.findElement(By.id("expiryYear"));
		Select sel=new Select(expYearDD);
		sel.selectByIndex(7);
		logger.info("New billing card exp Year selected");
	}

	public void clickOnPCPerksTermsAndConditionsCheckBoxes(){
		try{
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='content']/li[1]//input/.."),"");
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@class='content']/li[2]//input/.."),"");
		}catch(Exception e){
			driver.waitForElementPresent(By.xpath("//input[@id='Terms2']/.."));
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='Terms2']/.."),"");
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='Terms3']/.."),"");
		}
	}

	public StoreFrontPCUserPage loginAsPCUser(String username,String password){
		if(driver.isUserFetchedFromDB().equals("false")){
			username=TestConstants.STATIC_PC_EMAIL_ID;
		}
		driver.click(LOGIN_LINK_LOC,"login icon");
		driver.type(USERNAME_TXTFLD_LOC, username,"username");
		driver.type(PASSWORD_TXTFLD_LOC, password,"password");   
		driver.click(LOGIN_BTN_LOC,"login button");
		driver.waitForPageLoad();
		dismissPolicyPopup();
		clickRenewLaterForCon();
		driver.waitForPageLoad();
		return new StoreFrontPCUserPage(driver);
	}

	public StoreFrontRCUserPage loginAsRCUser(String username,String password){
		if(driver.isUserFetchedFromDB().equals("false")){
			username=TestConstants.STATIC_RC_EMAIL_ID;
		}
		driver.click(LOGIN_LINK_LOC,"login icon");
		driver.type(USERNAME_TXTFLD_LOC, username,"username");
		driver.type(PASSWORD_TXTFLD_LOC, password,"password");   
		driver.click(LOGIN_BTN_LOC,"login button");
		driver.waitForPageLoad();
		dismissPolicyPopup();
		clickRenewLaterForCon();
		driver.waitForPageLoad();
		return new StoreFrontRCUserPage(driver);
	}

	public void loginWithTerminatedUser(String username,String password){
		driver.click(LOGIN_LINK_LOC,"login icon");
		driver.type(USERNAME_TXTFLD_LOC, username,"username");
		driver.type(PASSWORD_TXTFLD_LOC, password,"password");   
		driver.click(LOGIN_BTN_LOC,"login button");
		driver.waitForPageLoad();
	}

	public boolean hasConsultantLoggedInSuccessfully(String query,String countryId){
		boolean hasConsultantLoggedInSuccessfully=false;
		for(int i=1;i<=TestConstants.LOOP_COUNT;i++){
			if(driver.getCurrentUrl().contains("error")==true){
				logger.info("Login was unsuccessful..trying again");
				navigateToStoreFrontBaseURL();
				loginAsConsultant(QueryUtils.getRandomActiveConsultantFromDB(driver.getDBNameRFO(), query, countryId),driver.getStoreFrontPassword());
			}	
			else{
				hasConsultantLoggedInSuccessfully=true;
				//				driver.click(By.xpath("//*[contains(text(),'Details')]"),"details");
				//				driver.click(By.xpath("//*[contains(text(),'visit')]"),"visit");
				break;
			}
		}
		return hasConsultantLoggedInSuccessfully;
	}

	public boolean hasConsultantWithPWSLoggedInSuccessfully(String query,String env, String site, String country,String countryId){
		boolean hasConsultantLoggedInSuccessfully=false;
		for(int i=1;i<=TestConstants.LOOP_COUNT;i++){
			if(driver.getCurrentUrl().contains("error")==true){
				navigateToStoreFrontBaseURL();
				loginAsConsultant(QueryUtils.getRandomActiveConsultantWithPWSFromDB(driver.getDBNameRFO(), query, env,site,country,countryId),driver.getStoreFrontPassword());
			}	
			else{
				hasConsultantLoggedInSuccessfully=true;
				break;
			}
		}
		return hasConsultantLoggedInSuccessfully;
	}

	public boolean hasPCLoggedInSuccessfully(String query,String countryId){
		boolean hasPCLoggedInSuccessfully=false;
		for(int i=1;i<=TestConstants.LOOP_COUNT;i++){
			if(driver.getCurrentUrl().contains("error")==true){
				navigateToStoreFrontBaseURL();
				loginAsPCUser(QueryUtils.getRandomActivePCFromDB(driver.getDBNameRFO(), query, countryId),driver.getStoreFrontPassword());
			}	
			else{
				hasPCLoggedInSuccessfully=true;
				break;
			}
		}
		return hasPCLoggedInSuccessfully;
	}

	public boolean hasPCLoggedInSuccessfullyOnPWS(String query,String country,String countryId,String env,String siteType){
		boolean hasPCLoggedInSuccessfully=false;
		for(int i=1;i<=TestConstants.LOOP_COUNT;i++){
			if(driver.getCurrentUrl().contains("error")==true){
				if(siteType.equalsIgnoreCase(TestConstants.SITE_TYPE_COM))
					openComPWSSite(country, env);
				loginAsPCUser(QueryUtils.getRandomActivePCFromDB(driver.getDBNameRFO(), query, countryId),driver.getStoreFrontPassword());
			}	
			else{
				hasPCLoggedInSuccessfully=true;
				break;
			}
		}
		return hasPCLoggedInSuccessfully;
	}

	public boolean hasRCLoggedInSuccessfully(String query,String countryId){
		boolean hasRCLoggedInSuccessfully=false;
		for(int i=1;i<=TestConstants.LOOP_COUNT;i++){
			if(driver.getCurrentUrl().contains("error")==true){
				navigateToStoreFrontBaseURL();
				loginAsPCUser(QueryUtils.getRandomActiveRCFromDB(driver.getDBNameRFO(), query, countryId),driver.getStoreFrontPassword());
			}	
			else{
				hasRCLoggedInSuccessfully=true;
				break;
			}
		}
		return hasRCLoggedInSuccessfully;
	}

	private void navigateToStoreFrontBaseURL() {
		driver.get(driver.getURL()+"/"+driver.getCountry());

	}

	public void clickRenewLaterForCon()  {
		RFO_DB = driver.getDBNameRFO();
		boolean renewNow =false;
		if(driver.getCountry().equalsIgnoreCase("ca"))
			countryId = "40";
		else if(driver.getCountry().equalsIgnoreCase("us"))
			countryId = "236";  
		try{
			boolean renewLater = driver.quickWaitForElementPresent(By.xpath("//div[contains(@class,'fancybox-overlay')]//input[@id='renewLater']"));
			if(renewLater==true){
				//driver.pauseExecutionFor(2000);
				driver.click(By.xpath("//div[contains(@class,'fancybox-overlay')]//input[@id='renewLater']"),"");
				logger.info("Renew later button clicked");
				driver.waitForLoadingImageToDisappear(3);
			}
			else{	
				try{
					renewNow = driver.IsElementVisible(driver.findElement(By.xpath("//div[@id='renewalPopUpLater']//following::input[@value='RENEW NOW']")));
				}catch(Exception e){
					logger.info("Renew popup not present");
				}
				if(renewNow==true){
					driver.get(driver.getURL()+"/"+driver.getCountry());
					while(true){
						List<Map<String, Object>> randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
						String consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
						String accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
						logger.info("Account Id of the user is "+accountID);
						loginAsConsultant(consultantEmailID, driver.getStoreFrontPassword());
						boolean isLoginError = driver.getCurrentUrl().contains("error");
						if(isLoginError){
							logger.info("Login error for the user "+consultantEmailID);
							driver.get(driver.getURL()+"/"+driver.getCountry());
						}
						else
							break;
					}
				}
			}
		}catch(Exception e1){

		}
	}

	public void clickRenewLaterForPC()  {
		RFO_DB = driver.getDBNameRFO();
		boolean renewNow =false;
		if(driver.getCountry().equalsIgnoreCase("ca"))
			countryId = "40";
		else if(driver.getCountry().equalsIgnoreCase("us"))
			countryId = "236";  
		try{
			boolean renewLater = driver.quickWaitForElementPresent(By.xpath("//div[contains(@class,'fancybox-overlay')]//input[@id='renewLater']"));
			if(renewLater==true){
				//driver.pauseExecutionFor(2000);
				driver.click(By.xpath("//div[contains(@class,'fancybox-overlay')]//input[@id='renewLater']"),"");
				logger.info("Renew later button clicked");
				driver.waitForLoadingImageToDisappear(3);
			}
			else{	
				try{
					renewNow = driver.IsElementVisible(driver.findElement(By.xpath("//div[@id='renewalPopUpLater']//following::input[@value='RENEW NOW']")));
				}catch(Exception e){
					logger.info("Renew popup not present");
				}
				if(renewNow==true){
					driver.get(driver.getURL()+"/"+driver.getCountry());
					while(true){
						List<Map<String, Object>> randomPCUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_AUTOSHIPS_RFO,countryId),RFO_DB);
						String pcUserEmailID = (String) getValueFromQueryResult(randomPCUserList, "UserName");  
						String accountId = String.valueOf(getValueFromQueryResult(randomPCUserList, "AccountID"));
						logger.info("Account Id of the user is "+accountId);
						loginAsPCUser(pcUserEmailID, driver.getStoreFrontPassword());
						boolean isError = driver.getCurrentUrl().contains("error");
						if(isError){
							logger.info("SITE NOT FOUND for the user "+pcUserEmailID);
							driver.get(driver.getURL()+"/"+driver.getCountry());
						}
						else
							break;
					} 
				}
			}
		}catch(Exception e1){

		}
	}

	public void clickRenewLaterForRC()  {
		RFO_DB = driver.getDBNameRFO();
		boolean renewNow =false;
		if(driver.getCountry().equalsIgnoreCase("ca"))
			countryId = "40";
		else if(driver.getCountry().equalsIgnoreCase("us"))
			countryId = "236";  
		try{
			boolean renewLater = driver.quickWaitForElementPresent(By.xpath("//div[contains(@class,'fancybox-overlay')]//input[@id='renewLater']"));
			if(renewLater==true){
				//driver.pauseExecutionFor(2000);
				driver.click(By.xpath("//div[contains(@class,'fancybox-overlay')]//input[@id='renewLater']"),"");
				logger.info("Renew later button clicked");
				driver.waitForLoadingImageToDisappear(3);
			}
			else{	
				try{
					renewNow = driver.IsElementVisible(driver.findElement(By.xpath("//div[@id='renewalPopUpLater']//following::input[@value='RENEW NOW']")));
				}catch(Exception e){
					logger.info("Renew popup not present");
				}
				if(renewNow==true){
					driver.get(driver.getURL()+"/"+driver.getCountry());
					while(true){
						List<Map<String, Object>> randomRCList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId),RFO_DB);
						String rcEmailID = (String) getValueFromQueryResult(randomRCList, "Username");
						loginAsRCUser(rcEmailID,driver.getStoreFrontPassword());
						boolean isLoginError = driver.getCurrentUrl().contains("error");
						if(isLoginError){
							logger.info("Login error for the user "+rcEmailID);
							driver.get(driver.getURL()+"/"+driver.getCountry());
						}
						else
							break;
					}
				}
			}
		}catch(Exception e1){

		}
	}

	public void changeMainAddressToQuebec(){
		driver.waitForElementPresent(By.id("state"));
		driver.clickByJS(RFWebsiteDriver.driver,By.id("state"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='state']/option[@value='QC']"));
		driver.click(By.xpath("//select[@id='state']/option[@value='QC']"),"");
		logger.info("state selected is quebec");
	}

	public StoreFrontRFWebsiteBasePage hoverOnProfileNameAndClickOnMyAccount(){
		if(driver.getCountry().equalsIgnoreCase("au")){
			driver.moveToELement(PROFILE_NAME_LOC);
			logger.info("hover on profile name");
			driver.click(MY_ACCOUNT_UNDER_PROFILE_NAME_LOC,"");
			logger.info("Clicked on my account under profile name");
		}
		return new StoreFrontRFWebsiteBasePage(driver);
	}

	public void selectAllTermsAndConditionsChkBox(){
		List<WebElement> all = driver.findElements(By.xpath("//form[@id='placeOrderForm']/div/descendant::input/.."));
		for(WebElement chkbox : all){
			driver.clickByJS(RFWebsiteDriver.driver, chkbox,"");
			driver.pauseExecutionFor(500);
		}
	}

	public void selectNewShippingAddressStateForDuringCRPEnrollment(String state){ 
		selectNewShippingAddressState(state);
	} 

	public String getCurrentURL() {
		driver.waitForPageLoad();
		logger.info("Current url is "+driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}

	public void openURL(String url){
		driver.get(url);
	}

	public String getEnrollmentKitDeliveryCharges(){
		return fetchDigitsFromPrice(driver.getText(By.id("deliveryCost")));
	}

	public String getEmrollmentShippingMethod(){
		return driver.getText(By.xpath("//[@id='selectDeliveryMode']/option"));
	}

	public String fetchDigitsFromPrice(String price){
		if(price.contains("$"))
			return price=price.split("\\$")[1].trim();
		else
			return price;
	}

	public void clickOnOKBtn(){
		if(driver.isElementPresent(By.xpath("//*[@value='OK']"))){
			driver.click(By.xpath("//*[@value='OK']"),"OK button");
		}
	}

	public void clickNextButtonWithoutPopUp(){
		driver.clickByJS(RFWebsiteDriver.driver,By.id("enrollment-next-button")," next button");
		driver.waitForLoadingImageToDisappear();
	}

	public void addMySpouse(String firstName, String lastName){
		clickOnAllowMySpouseOrDomesticPartnerCheckbox();
		enterSpouseFirstName(firstName);
		enterSpouseLastName(lastName);
		driver.click(By.xpath("//*[@id='S-S-N' or @id='A-B-N']"),"SSN field");
		driver.pauseExecutionFor(2000);
	}

	public void clickOnAllowMySpouseOrDomesticPartnerCheckbox() {
		boolean status = false;
		try{
			driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='spouse-check' or @id='enrollAllowSpouse1']/.."), "Allow spouse checkbox");
		}
		catch(Exception e){
			status = driver.isElementPresent(By.xpath("//input[@id='enrollAllowSpouse1' and @class='checked']/.."));
			if(status==false){
				logger.info("Spouse checbox is not selected in try");
				driver.click(By.xpath("//input[@id='enrollAllowSpouse1']/.."),"");
			}
		}
	}

	public void clickXSpousePopup(){
		driver.click(By.xpath("//span[@class='icon-close']"),"spouse popup X button");
		driver.pauseExecutionFor(1000);
	}

	public boolean isSpousePopupDisplayed(){
		return driver.isElementVisible(By.id("spouceConfirm"));
	}

	public void clickCancelSpousePopup(){
		driver.click(By.id("cancelSpouse"), "spouse popup cancel button");
		driver.pauseExecutionFor(1000);
	}

	public void clickAcceptSpousePopup(){
		driver.click(By.xpath("//input[@id='acceptSpouse']"),"spouse popup accept button");
	}

	public boolean isShippingAddressNextStepBtnIsPresent(){
		return driver.isElementPresent(By.id("saveShippingInfo"));
	}

	public void refreshPage(){
		driver.navigate().refresh();
		driver.waitForPageLoad();
	}

	public void clickOnEditAtAutoshipTemplate(){
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[@value='Edit']"));
			driver.click(By.xpath("//input[@value='Edit']"),"");
		}
		catch(Exception e){
			try{
				driver.click(By.xpath("//input[@value='EDIT']"),"");
			}
			catch(Exception e1){
				driver.click(By.xpath("//input[@value='edit']"),""); 
			}
		}
	}

	public void navigateToBackPage(){
		driver.waitForPageLoad();
		driver.navigate().back();
		driver.waitForPageLoad();
	}

	public boolean verifyTotalSavingsIsAvailableOnAutoshipCart(){
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//div[@class='checkout-module-content']//div[@id='module-subtotal'][1] | //*[contains(text(),'Total Savings*')]/following::span[contains(@class,'saving-price')]"));
	}

	public void clickOnSetupCRPAccountBtn() {
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//ul[@style='cursor: pointer;']/li[1]/div"),"CRP terms and conditions 1");
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//ul[@style='cursor: pointer;']/li[2]/div"),"CRP terms and conditions 2");
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@value='ENROL IN CRP'] | //input[@value='Setup CRP Account']"),"");
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(2000);
	}	

	public void applyPriceFilterHighToLow(){
		driver.waitForElementPresent(By.xpath("//select[@id='sortOptions']"));
		driver.selectByVisibleText(driver.findElement(By.xpath("//select[@id='sortOptions']")), "Price: High to Low","price filter");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	public String createNewPC(String firstName,String lastName,String password) {
		String emailAddress = firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		driver.type(By.id("first-Name"),firstName);
		logger.info("first name entered as "+firstName);
		driver.type(By.id("last-name"),lastName);
		logger.info("last name entered as "+lastName);
		driver.type(By.id("email-account"),emailAddress+"\t");
		logger.info("email entered as "+emailAddress);
		//driver.pauseExecutionFor(2000);
		driver.type(By.id("password"),password);
		logger.info("password entered as "+password);
		driver.type(By.id("the-password-again"),password);
		logger.info("confirm password entered as "+password);
		driver.click(By.xpath("//input[@id='become-pc']/.."),"");
		logger.info("check box for PC user checked");
		driver.pauseExecutionFor(2000);
		driver.click(By.xpath("//input[@id='next-button']"),"");  
		logger.info("Create New Account button clicked"); 
		System.out.println("1hp");
		return emailAddress;
	}

	public boolean isRequestSponsorLinkPresent(){
		try{
			driver.turnOffImplicitWaits();
			return driver.isElementVisible(By.xpath("//*[@value='Request a sponsor' or @id='requestsponsor']"));
			//return true;
		}catch(Exception e){
			return false;
		}finally{
			driver.turnOnImplicitWaits();
		}
	}

	public void selectShippingAddress(String shippingProfileName){
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath("//span[contains(text(),'"+shippingProfileName+"')]/following::input[@type='radio' and @name='shipping4']"));
		driver.findElements(By.xpath("//*[contains(text(),'"+shippingProfileName+"')]/ancestor::div[1]//input[@type='radio' and @name='shipping4']")).get(0).click();;
		logger.info("Shipping profile selected as: "+shippingProfileName);
	}	

	public void clickOnBillingNextStepButtonWhileEnrollingCRP() {
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath("//div[@id='payment-next-button']/input"));
		driver.click(By.xpath("//div[@id='payment-next-button']/input"),"");
	}	

	public void clickOnEditOfShippingProfile(String profileName){
		driver.pauseExecutionFor(3000);
		//driver.click(By.xpath("//span[contains(text(),'"+profileName+"')]/ancestor::div[1]//a[text()='Edit']"),"edit shipping link for profile "+profileName );
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//span[contains(text(),'"+profileName+"')]/ancestor::div[1]//a[text()='Edit']"),"edit shipping link for profile" +profileName);
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(3000);
	}

	public void enterNewShippingAddressName(String name){
		//		driver.waitForElementPresent(By.id("new-attention"));
		//		driver.clear(By.id("new-attention"));
		driver.pauseExecutionFor(3000);
		driver.type(By.id("new-attention"),name);
		logger.info("New Shipping Address name is "+name);
	}


	public void clickOnEditShipping(){
		driver.waitForElementPresent(By.xpath("//div[@id='checkout_summary_deliverymode_div']//a"));
		driver.scrollToElement(RFWebsiteDriver.driver,driver.findElement(By.xpath("//div[@id='checkout_summary_deliverymode_div']//a")));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='checkout_summary_deliverymode_div']//a"),"Edit shipping");
		//driver.click(By.xpath("//div[@id='checkout_summary_deliverymode_div']//a"),"Edit shipping");
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(3000);
	}

	public void clickAddNewShippingProfileLink() {
		driver.click(By.xpath("//a[contains(text(),'Add new shipping address')]"),"new shipping profile link");
	}

	public void clickAddNewBillingProfileLink() {
		driver.waitForElementPresent(By.xpath("//a[contains(text(),'Add new billing profile')]"));
		driver.pauseExecutionFor(2000);
		driver.click(By.xpath("//a[contains(text(),'Add new billing profile')]"),"");
		//driver.click(By.xpath("//a[contains(text(),'Add new billing profile')]"),"");
		//driver.click(By.xpath("//a[contains(text(),'Add new billing profile')]"));
		logger.info("Add New Billing Profile link clicked");
	}

	/**
	 * This method clicks on the continue without sponsor link on Main account info page while
	 * enrolling PC/RC
	 */
	public void clickOnContinueWithoutSponsorLink() {
		driver.pauseExecutionFor(2000);
		driver.clickByJS(RFWebsiteDriver.driver,By.id("continue-no-sponsor"),"continue without sponsor link");	
		driver.waitForLoadingImageToDisappear();
	}

	public void clickNextButton(){
		driver.waitForElementToBeClickable(By.id("enrollment-next-button"),10);
		driver.clickByJS(RFWebsiteDriver.driver, By.id("enrollment-next-button"),"next button");//(By.id("enrollment-next-button"),"next button");
		driver.waitForLoadingImageToDisappear();
		By QASPopUpLoc =By.xpath("//*[contains(@id,'_AcceptOriginal')]");// By.id("QAS_AcceptOriginal");
		driver.quickWaitForElementPresent(QASPopUpLoc,10);
		if(driver.isElementPresent(QASPopUpLoc)){
			driver.clickByJS(RFWebsiteDriver.driver,QASPopUpLoc,"Use as entered button");     
		}
		else
			logger.info("QAS Popup NOT present");  
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}	

	public void clickOnYourAccountDropdown(){
		if(driver.getCountry().equalsIgnoreCase("ca")){
			driver.clickByJS(RFWebsiteDriver.driver, YOUR_ACCOUNT_DROPDOWN_LOC, "Your account dropdown");
		}
	}

	public void clickOnAutoshipStatusLink() {
		if(driver.getCountry().equalsIgnoreCase("au")){
			clickOnWelcomeDropDown();
		}
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[contains(text(),'Autoship Status')]"),"Autoship status link");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(5000);
	}

	public void subscribeToPulse(){
		if(driver.isElementPresent(By.xpath("//a[text()='Cancel my Pulse subscription �']"))){
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[text()='Cancel my Pulse subscription �']"),"");
			driver.pauseExecutionFor(1000);
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[@id='cancelPulse']"),"");
			driver.waitForLoadingImageToDisappear();
			try{
				driver.quickWaitForElementPresent(By.id("cancel-pulse-button"));
				driver.clickByJS(RFWebsiteDriver.driver,By.id("cancel-pulse-button"),"");
				driver.waitForLoadingImageToDisappear();
			}catch(Exception e){

			}
			driver.waitForPageLoad();
		}
		driver.pauseExecutionFor(5000);
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@id='subscribe_pulse_button_new']"),"");
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(5000);
	} 	

	public void selectNewBillingCardExpirationDate(String month,String year){
		Select monthDD = new Select(driver.findElement(By.id("expiryMonth")));
		Select yearDD = new Select(driver.findElement(By.id("expiryYear")));
		monthDD.selectByVisibleText(month);
		yearDD.selectByVisibleText(year);
		/*driver.clickByJS(RFWebsiteDriver.driver,By.id("expiryMonth"),"expiry month dropdown");
	if(driver.isElementVisible(By.xpath("//select[@id='expiryMonth']/option[text()='"+month.toUpperCase()+"']"))) {
		driver.click(By.xpath("//select[@id='expiryMonth']/option[text()='"+month.toUpperCase()+"']"),"expiry month as"+month);
	}else {
		driver.click(By.xpath("//select[@id='expiryMonth']/option[@value='"+month.toUpperCase()+"']"), "expiry month as"+month);
	}
	driver.clickByJS(RFWebsiteDriver.driver,By.id("expiryYear"),"expiry  year dropdown");
	driver.click(By.xpath("//select[@id='expiryYear']/option[text()='"+year+"']"),"expiry year as"+year);*/
	}	

	public void clickOnShippingNextStepButton() {
		driver.waitForElementPresent(By.xpath("//div[@id='start-shipping-method']/div[2]/div/input"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//*[@id='start-shipping-method']/div[2]/div/input"),"");
		logger.info("Next button on clicked"); 
		driver.waitForLoadingImageToDisappear();
	}


	public StoreFrontConsultantPage loginAsConsultant(String username,String password){
		if(driver.isUserFetchedFromDB().equals("false")){
			username=TestConstants.STATIC_CONSULTANT_EMAIL_ID;
		}
		driver.pauseExecutionFor(5000);
		driver.clickByJS(RFWebsiteDriver.driver,LOGIN_LINK_LOC,"login icon");
		driver.type(USERNAME_TXTFLD_LOC, username,"username");
		driver.type(PASSWORD_TXTFLD_LOC, password,"password");   
		driver.click(LOGIN_BTN_LOC,"login button");
		driver.waitForPageLoad();
		dismissPolicyPopup();
		clickRenewLaterForCon();
		driver.waitForPageLoad();
		return new StoreFrontConsultantPage(driver);
	}

	public void retryClick(By by, String msg){
		for(int i=1;i<=3;i++){
			logger.info("click not worked for  "+msg+" trying again..");
			if(driver.isElementPresent(by)){
				driver.clickByJS(RFWebsiteDriver.driver,by,msg);
				driver.waitForPageLoad();
			}
			else{
				break;
			}
		}
	}

	public void enterWebsitePrefixName(String name){
		driver.type(By.id("webSitePrefix"), name,"prefix name");
		driver.click(By.id("prefix-validation"),"prefix validation area");
		driver.pauseExecutionFor(2000);
	}

	public void cancelPulseSubscription(){
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//a[contains(text(),'Cancel my Pulse subscription' ) or contains(text(),'Cancel my PULSE Pro subscription')]"),"");
		driver.pauseExecutionFor(5000);
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//a[@id='cancelPulse']"),"");
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(3000);
		try{
			driver.click(By.id("cancel-pulse-button"),"");
			driver.waitForLoadingImageToDisappear();
		}catch(Exception e){
		}
		driver.waitForPageLoad();
	}

	public void addAnotherProduct() {
		try{
			driver.scrollDownIntoView(By.xpath("//*[contains(text(),'Continue shopping')]"));
			driver.click(By.xpath("//*[contains(text(),'Continue shopping')]"),"Continue shopping link");
			driver.waitForPageLoad();
		}catch(Exception e){
			Actions action = new Actions(RFWebsiteDriver.driver);
			driver.quickWaitForElementPresent(By.xpath("//div[@id='left-shopping']/div/a[contains(text(),'Continue shopping')]"));
			action.moveToElement(driver.findElement(By.xpath("//div[@id='left-shopping']/div/a[contains(text(),'Continue shopping')]"))).doubleClick(driver.findElement(By.xpath("//div[@id='left-shopping']/div/a[contains(text(),'Continue shopping')]"))).build().perform();
			driver.waitForPageLoad();
		}
		driver.waitForLoadingImageToDisappear();
		selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER_3);
		driver.waitForLoadingImageToDisappear();
		driver.waitForPageLoad();
	}

	public void clickOnBillingNextStepBtn() {
		driver.waitForElementPresent(By.xpath("//div[@id='payment-next-button']/input|//input[@id='submitButton']"));
		WebElement nextBtn = driver.findElement(By.xpath("//div[@id='payment-next-button']/input|//input[@id='submitButton']"));
		Point point = nextBtn.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		JavascriptExecutor jse = (JavascriptExecutor)RFWebsiteDriver.driver;
		jse.executeScript("scroll("+xcord+", -("+ycord+"));");
		//driver.pauseExecutionFor(7000);
		//driver.clickByJS(RFWebsiteDriver.driver, driver.findElement(By.xpath("//div[@id='payment-next-button']/input")));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='payment-next-button']/input|//input[@id='submitButton']"),"");//(By.xpath("//div[@id='payment-next-button']/input"),"");
		logger.info("Next button on billing profile clicked"); 
		try {
			Alert alert = driver.switchTo().alert();

			// check if alert exists
			// TODO find better way
			alert.getText();

			// alert handling
			logger.info("Alert detected: {}" + alert.getText());
			alert.accept();
		} catch (Exception e) {
		}
		driver.waitForLoadingImageToDisappear(20);
	}


	public void clickOnEditMyPWS(){
		try {
			if(driver.getCountry().equalsIgnoreCase("AU")) {
				clickOnWelcomeDropDown();
			}
			driver.waitForElementPresent(By.xpath("//div[@id='left-menu']//a[contains(text(),'Edit My PWS')] | //a[contains(text(),'EDIT MY PWS')] | //div[@id='account-info-dropdown']//a[contains(text(),'Edit My PWS')]"));
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='left-menu']//a[contains(text(),'Edit My PWS')] | //a[contains(text(),'EDIT MY PWS')] | //div[@id='account-info-dropdown']//a[contains(text(),'Edit My PWS')]"),"Edit My PWS");
		}catch(Exception E) {
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//a[contains(text(),'EDIT MY PWS')]"),"");
		}
	}	

	public boolean validateAccessSolutionTool(){
		driver.waitForElementPresent(By.xpath("//div[@id='corp_content']/descendant::a[contains(text(),'GET STARTED')][1]"));
		//driver.scollUpIntoView(By.xpath("//div[@id='corp_content']/descendant::a[contains(text(),'GET STARTED')][1]"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='corp_content']/descendant::a[contains(text(),'GET STARTED')][1]"),"");
		driver.waitForPageLoad();
		driver.waitForElementPresent(By.id("mirror"));
		return driver.getCurrentUrl().contains("solution-tool") && driver.isElementPresent(By.xpath("//h1[contains(text(),'SOLUTION TOOL')]"));
	}

	public void clickOnSaveBillingProfile(){
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@id='submitButton' | @id='enrollment-next-button']"),"Save Billing profile");//(By.id("submitButton"),"Save Billing profile");
		driver.pauseExecutionFor(1000);
		driver.waitForLoadingImageToDisappear();
		if(driver.isElementVisible(By.id("submitButton"))){
			driver.clickByJS(RFWebsiteDriver.driver,By.id("submitButton"),"Save billing profile");
		}
		driver.waitForLoadingImageToDisappear(); 
	}	

	public void clickBillToCardForSpecifiedProfile(String billingProfileName) {
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//*[contains(text(),'"+billingProfileName+"')]/following::label[contains(text(),'Bill to this Card')][1]"), "Bill to card profile name: "+billingProfileName);
	}

	public void selectNewShippingAddressState(String state){ 
		driver.pauseExecutionFor(3000);
		//Select stateDD = new Select(driver.findElement(By.id("state")));
		driver.waitForElementToBeVisible(By.xpath("//*[contains(@id,'new-shipping-added')]//select[@id='state']"), 5);
		try{
			Select stateDD = new Select(driver.findElement(By.xpath("//*[contains(@id,'new-shipping-added')]//select[@id='state']")));
			stateDD.selectByVisibleText(state);
		}catch(Exception e){
			try{
				Select stateDD = new Select(driver.findElement(By.xpath("//*[contains(@id,'enrollmentForm')]//select[@id='state']")));
				stateDD.selectByVisibleText(state);
			}catch(Exception e1){
				Select stateDD = new Select(driver.findElement(By.xpath("//*[contains(@id,'addressForm')]//select[@id='state']")));
				stateDD.selectByVisibleText(state);				
			}
		}
	}	

	public boolean selectUseAsEnteredBtnOnQASPopup(){
		driver.quickWaitForElementPresent(By.xpath("//input[contains(@id,'AcceptOriginal')]"));
		if(driver.isElementVisible(By.xpath("//input[contains(@id,'AcceptOriginal')]"))){

			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[contains(@id,'AcceptOriginal')]"),"Accept New shipping address button");
			return true;
		}
		return true;
	}

	public StoreFrontConsultantPage clickOnRodanAndFieldsLogo(){
		driver.clickByJS(RFWebsiteDriver.driver,RODAN_AND_FIELDS_LOGO_IMG_LOC, "R+F logo");
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(5000);
		return new StoreFrontConsultantPage(driver);
	}

	public boolean isMoreThanOneShippingOrBillingProfilePresent(){
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-billing-profiles']/div"));
		int count =  driver.findElements(By.xpath("//div[@id='multiple-billing-profiles']/div")).size();
		return count>1;
	}

	public boolean validateSendMailToResetMyPasswordFunctionalityPC(){
		driver.waitForElementPresent(By.xpath("//div[@id='activePCPopup']//input[contains(@class,'resetPasswordEmail')]"));
		JavascriptExecutor js = ((JavascriptExecutor)RFWebsiteDriver.driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='activePCPopup']//input[contains(@class,'resetPasswordEmail')]")));
		driver.pauseExecutionFor(3000);
		driver.waitForLoadingImageToDisappear();
		driver.waitForElementPresent(By.xpath("//div[@class='fancybox-inner']"));
		return driver.isElementPresent(By.xpath("//div[@class='fancybox-inner']"));
	}
	
	public boolean validatePulseHomePage(){
		driver.waitForPageLoad();
		return driver.getTitle().toLowerCase().contains("pulse");
	} 
	
	public StoreFrontPCUserPage clickPCPerksStatusLinkPresentOnWelcomeDropDown() {
		driver.click(WELCOME_USER_DD_LOC, "Welcome DDD clicked");
		driver.pauseExecutionFor(2000);
		driver.click(WELCOME_DD_PC_PERKS_STATUS_LINK_LOC, "Welcome DDD PC Perks status clicked");
		logger.info("User has clicked on PC Perks status link from welcome drop down");
		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
		driver.pauseExecutionFor(5000);
		return new StoreFrontPCUserPage(driver);
	}

	public void clickOnPCPerksStatus() {
		if(driver.getCountry().equalsIgnoreCase("au")){
			this.clickOnWelcomeDropDown();
			driver.click(By.xpath("//a[contains(text(),'PC Perks Status')]"),"PC Perks status");
		}else{
		driver.click(By.xpath("//div[@id='content-left-menu']//a[contains(text(),'PC Perks Status')]"),"PC Perks status");
		}
		driver.waitForPageLoad();
		driver.pauseExecutionFor(3000);
	} 
		
	public int getCurrentDayFromCurrentDate(){
		LocalDate localDate = LocalDate.now();
		String currenDate = DateTimeFormatter.ofPattern("dd-MM-YYYY").format(localDate);
		logger.info("Current system date is "+currenDate);
		String day = currenDate.split("\\-")[0];
		logger.info("Day is "+day);
		return Integer.parseInt(day);
	}
	
	public String getCurrentMonthFromCurrentDate(){
		TimeZone timeZone = TimeZone.getTimeZone("US/Pacific");//"US/Pacific"
		String currenDate = CommonUtils.getCurrentDate("dd-MMMM-YYYY", timeZone);
		logger.info("Current date is "+currenDate);
		String month = currenDate.split("\\-")[1];
		logger.info("Month is "+month);
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
	
	public String getCurrentYearFromCurrentDate(){
		TimeZone timeZone = TimeZone.getTimeZone("US/Pacific");//"US/Pacific"
		String currenDate = CommonUtils.getCurrentDate("dd-MMMM-YYYY", timeZone);
		logger.info("Current date is "+currenDate);
		String year = currenDate.split("\\-")[2];
		logger.info("Year is "+year);
		return year;
	}
	
	
	public void clickBuyNowBtn(){
		driver.waitForElementToBeVisible(BUY_NOW_BTN, 20);
		driver.click(BUY_NOW_BTN, "Buy now button");
	}
	
	public int getNextDayFromCureentDay(int day){
		return day+1;
	}
	
}
