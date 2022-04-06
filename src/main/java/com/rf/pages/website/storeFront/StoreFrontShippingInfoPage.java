package com.rf.pages.website.storeFront;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.website.constants.TestConstants;

public class StoreFrontShippingInfoPage extends StoreFrontRFWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontShippingInfoPage.class.getName());

	private final By SHIPPING_PAGE_TEMPLATE_HEADER_LOC = By.xpath("//div[@class='gray-container-info-top']/div[text()='Shipping info']");  
	private final By TOTAL_SHIPPING_ADDRESSES_LOC = By.xpath("//div[@id='multiple-billing-profiles']/div[contains(@class,'col')]");
	private final By USE_THIS_SHIPPING_PROFILE_FUTURE_AUTOSHIP_CHKBOX_LOC = By.xpath("//div[@id='use-for-autoship']/div");
	private final By NEW_SHIPPING_PROFILE_SAVE_BTN_LOC = By.id("saveShippingAddress");
	private final By ADD_NEW_SHIPPING_LINK_LOC = By.xpath("//a[@class='add-new-shipping-address']");

	public StoreFrontShippingInfoPage(RFWebsiteDriver driver) {
		super(driver);		
	}

	public boolean verifyShippingInfoPageIsDisplayed(){
		return driver.getCurrentUrl().contains(TestConstants.SHIPPING_PAGE_SUFFIX_URL);
	}

	public boolean isDefaultAddressRadioBtnSelected(String defaultAddressFirstName) throws InterruptedException{
		try{
			driver.waitForElementPresent(By.xpath("//span[contains(text(),'"+defaultAddressFirstName+"')]/ancestor::div[1]/form/span/input[@checked='checked']"));
			boolean flag=driver.findElement(By.xpath("//span[contains(text(),'"+defaultAddressFirstName+"')]/ancestor::div[1]/form/span/input[@checked='checked']")).isSelected();
			return flag;
		}catch(NoSuchElementException e){
			//String word = Character.toUpperCase(defaultAddressFirstName.charAt(0)) + defaultAddressFirstName.substring(1);
			if(driver.findElement(By.xpath("//span[contains(text(),'"+defaultAddressFirstName+"')]/ancestor::div[1]/form/span[@checked='address.defaultAddress']")).isSelected()){
				return true;
			}else{
				return false;
			}
		}
	}

	public boolean isAutoshipOrderAddressTextPresent(String firstName){
		String getAutoshipOrderDetailsText = driver.findElement(By.xpath("//div[@class='autoshipOrderDetails']")).getText();
		logger.info("Autoship address details "+getAutoshipOrderDetailsText);
		return getAutoshipOrderDetailsText.contains(firstName);
	}

	public boolean isDefaultShippingAddressSelected(String name) throws InterruptedException{
		driver.waitForElementPresent(By.xpath("//input[@name='addressCode' and @checked='checked']/ancestor::li[1]/p[1]"));
		return driver.findElement(By.xpath("//input[@name='addressCode' and @checked='checked']/ancestor::li[1]/p[1]")).getText().contains(name);
	}	


	public boolean verifyAutoShipAddressTextNextToDefaultRadioBtn(String defaultAddressFirstNameDB){
		return driver.findElement(By.xpath("//span[contains(text(),'"+defaultAddressFirstNameDB+"')]/following::b[@class='AutoshipOrderAddress']")).getText().equals(TestConstants.AUTOSHIP_ADRESS_TEXT);
	}

	public int getTotalShippingAddressesDisplayed(){
		driver.waitForElementPresent(TOTAL_SHIPPING_ADDRESSES_LOC);
		List<WebElement> totalShippingAddressesDisplayed = driver.findElements(TOTAL_SHIPPING_ADDRESSES_LOC);
		return totalShippingAddressesDisplayed.size();
	}

	public void clickOnEditForFirstAddress(){
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-billing-profiles']/div[1]//a[text()='Edit']"));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='multiple-billing-profiles']/div[1]//a[text()='Edit']"),"First Address Edit link");
		logger.info("First Address Edit link clicked");
		driver.waitForLoadingImageToDisappear();

	}

	public void clickAddNewShippingProfileLink() {
		driver.click(ADD_NEW_SHIPPING_LINK_LOC,"Add new shipping profile link");
	}

	public void enterNewShippingAddressName(String name){
		driver.type(By.id("new-attention"),name,"New Shipping Address name");
	}

	public void enterNewShippingAddressCity(String city){
		driver.type(By.id("townCity"),city,"New Shipping City");
	}

	public void enterNewShippingAddressPostalCode(String postalCode){
		driver.type(By.id("postcode"),postalCode,"postal code");
	}

	public void enterNewShippingAddressPhoneNumber(String phoneNumber){
		driver.type(By.id("phonenumber"),phoneNumber,"phone number");
	}

	public void selectFirstCardNumber() throws InterruptedException{
		try{
			driver.waitForElementPresent(By.id("cardDropDowndropdown"));
			driver.click(By.id("cardDropDowndropdown"),"");
		}catch(WebDriverException e){
			Actions action = new Actions(RFWebsiteDriver.driver);
			action.moveToElement(driver.findElement(By.id("cardDropDowndropdown"))).click().build().perform();

		}
		driver.waitForElementPresent(By.xpath("//select[@id='cardDropDowndropdown']/option[2]"));
		driver.click(By.xpath("//select[@id='cardDropDowndropdown']/option[2]"),"");
		logger.info("First Card number selected from drop down");
	}

	public void enterNewShippingAddressSecurityCode(String securityCode){
		driver.clear(By.id("security-code"));
		driver.type(By.id("security-code"),securityCode);
	}

	public void selectUseThisShippingProfileFutureAutoshipChkbox(){
		driver.click(USE_THIS_SHIPPING_PROFILE_FUTURE_AUTOSHIP_CHKBOX_LOC,"future autoship checkbox");
	}

	public void makeShippingProfileAsDefault(String firstName) throws InterruptedException{
		driver.click(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+firstName+"')]/following::form[@id='setDefaultAddressForm'][1]/span[1]"),"default radiobutton for shipping profile "+firstName);
		driver.waitForLoadingImageToDisappear();
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[@value='YES, UPDATE MY AUTO-SHIP']"));
			driver.click(By.xpath("//input[@value='YES, UPDATE MY AUTO-SHIP']"),"");
		}catch(Exception e){

		}
		driver.waitForLoadingImageToDisappear();
	}

	public boolean isShippingAddressPresentOnShippingPage(String firstName){
		boolean isFirstNamePresent = false;
		driver.waitForElementPresent(By.xpath("//div[@id='multiple-billing-profiles']/div[contains(@class,'sel-profile')]"));
		List<WebElement> allBillingProfiles = driver.findElements(By.xpath("//div[@id='multiple-billing-profiles']/div[contains(@class,'sel-profile')]"));  
		for(WebElement e:allBillingProfiles){
			if(e.getText().contains(firstName)==true){
				isFirstNamePresent=true;
				break;
			}
		}
		return isFirstNamePresent;
	}

	public String getAddressUpdateConfirmationMessageFromUI(){
		return driver.findElement(By.xpath(".//div[@id='globalMessages']//p")).getText();

	}

	public String getErrorMessageForUSAddressFromUI(){
		//  return driver.findElement(By.xpath("//div[@class='tipsy-inner']")).getText();
		System.out.println(driver.findElement(By.xpath("//input[@id='postcode']/following::label")).getText());
		return driver.findElement(By.xpath("//input[@id='postcode']/following::label")).getText();  
	}

	public void changeAddressToUSAddress() throws InterruptedException{
		driver.waitForElementPresent(By.id("new-address-1"));
		driver.clear(By.id("new-address-1"));
		driver.type(By.id("new-address-1"),TestConstants.ADDRESS_LINE_1_US);
		logger.info("Address line 1 entered is "+TestConstants.ADDRESS_LINE_1_US);
		driver.clear(By.id("townCity"));
		driver.type(By.id("townCity"),TestConstants.CITY_US);
		Select select = new Select(driver.findElement(By.id("state")));
		//driver.click(By.id("state"));
		driver.waitForElementPresent(By.xpath("//select[@id='state']/option[2]"));  
		select.selectByIndex(1);
		//driver.click(By.xpath("//select[@id='state']/option[2]"));
		logger.info("state selected");
		driver.clear(By.id("postcode"));
		driver.type(By.id("postcode"),TestConstants.POSTAL_CODE_US);
		logger.info("postal code entered is "+TestConstants.POSTAL_CODE_US);
		driver.clear(By.id("phonenumber"));
		driver.type(By.id("phonenumber"),TestConstants.PHONE_NUMBER_US);
		logger.info("phone number entered is "+TestConstants.PHONE_NUMBER_US);
	}

	public void clickOnNewAddressRadioButton(){
		driver.click(By.xpath("//div[@id='multiple-billing-profiles']/div[2]//input"),"new address radio button");
	}

	public void clickOnPopUpAfterClickingRadioButton(){
		driver.waitForElementPresent(By.xpath("//div[@id='popup-quickinfo']/div/input[@value='YES, UPDATE MY AUTO-SHIP']"));
		driver.click(By.xpath("//div[@id='popup-quickinfo']/div/input[@value='YES, UPDATE MY AUTO-SHIP']"),"YES, UPDATE MY AUTO-SHIP Button");
	}

	public boolean verifyChangeInDefaultAddressForShippingAddress(){
		driver.waitForElementPresent(By.xpath("//div[@id='main-content']//span[text()='Failed to change default address.']"));
		logger.info(driver.findElement(By.xpath("//div[@id='main-content']//span[text()='Failed to change default address.']")).getText());
		if(driver.isElementPresent(By.xpath("//div[@id='main-content']//span[text()='Failed to change default address.']"))){
			return true;}
		else{
			return false;
		}
	}

	public boolean verifyOldDefaultSelectAddress(String addressname, String addressnameAfterAdd){
		return addressname.equalsIgnoreCase(addressnameAfterAdd);
	}

	public boolean verifyRadioButtonNotSelectedByDefault(String name){
		return driver.isElementPresent(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+name+"')]/following::input[@checked='checked']"));
	}

	public boolean verifyRadioButtonIsSelectedByDefault(String name) {
		String shippingname = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		return driver.isElementPresent(By.xpath("//div[@id='multiple-billing-profiles']//span[contains(text(),'"+name+"') or contains(text(),'"+shippingname+"')]/following::input[@checked='checked']"));
	}

	public boolean validateUpdateAutoShipPopUpPresent(){
		try{
			driver.quickWaitForElementPresent(By.xpath("//div[@id='popup-quickinfo']"));
			boolean status= driver.isElementPresent(By.xpath("//div[@id='popup-quickinfo']"));
			driver.click(By.xpath("//input[@class='shippingAddresspopup btn btn-primary']"),"");
			driver.waitForLoadingImageToDisappear();
			return status;
		}catch(Exception e){
			return driver.isElementPresent(By.xpath("//div[@class='successMessage']/span"));
		}
	}

	public boolean isQuebecProvinceDisabled(){
		driver.waitForElementPresent(By.xpath("//option[contains(text(),'Quebec')][@disabled='disabled']"));
		return driver.isElementPresent(By.xpath("//option[contains(text(),'Quebec')][@disabled='disabled']"));
	}

	public void clickSelectStateDD(){
		driver.waitForElementPresent(By.xpath("//div[@id='start-new-shipping-address']//select[@id='state']"));
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//div[@id='start-new-shipping-address']//select[@id='state']"),"");
	}
	
	public void clickOnSaveShippingProfile() {
		driver.click(NEW_SHIPPING_PROFILE_SAVE_BTN_LOC,"save shipping");
		driver.waitForLoadingImageToDisappear();
		clickOnAcceptOfQASPopup();
//		if(driver.getCountry().equalsIgnoreCase("au")){
//			try{
//				driver.quickWaitForElementPresent(By.xpath("//input[@value='Use as entered'] | //input[@value='USE AS ENTERED*']"),5);
//				driver.click(By.xpath("//input[@value='Use as entered'] | //input[@value='USE AS ENTERED*']"),"Use as entered button");
//			}
//			catch(Exception e){
//				logger.info("Accept the original pop up was NOT present");
//			}
//		}else{
//			try{
//				driver.click(By.id("QAS_RefineBtn"),"Accept button");
//			}catch(Exception e){
//			}
//			driver.waitForLoadingImageToDisappear();
//			driver.waitForPageLoad();
//		}
	}
}