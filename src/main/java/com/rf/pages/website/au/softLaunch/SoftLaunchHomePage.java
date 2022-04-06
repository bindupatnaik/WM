package com.rf.pages.website.au.softLaunch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.website.constants.TestConstants;
import com.rf.pages.website.storeFront.StoreFrontConsultantPage;

public class SoftLaunchHomePage extends SoftLaunchRFWebsiteBasePage {

	public SoftLaunchHomePage(RFWebsiteDriver driver) {
		super(driver);
	}

	private final By LOGIN_LINK_LOC = By.xpath("//div[@class='login-block']");
	private final By USERNAME_TXTFLD_LOC = By.id("emailAddress");
	private final By PASSWORD_TXTFLD_LOC = By.id("password");
	private final By LOGIN_BTN_LOC = By.id("login");
	private final By CALLING_PRE_ENROLLEES_TEXT_LOC = By.xpath("//div[@class='learn-txt']");
	private final By SHARE_YOUR_URL_LOC = By.xpath("//p[@class='desktop-link']//em[text()='Share your URL to build your network']");
	private final By WHAT_IF_TXT_LOC = By.id("what-if");
	private final By PROFILE_NAME_LOC = By.xpath("//div[@class='profile-image']");
	private final By SPONSOR_NAME_LOC = By.xpath("//div[@class='sponcer-name']");
	private final By SETTINGS_UNDER_PROFILE_NAME_LOC = By.xpath("//div[contains(@class,'profile-header')]//a[text()='Settings']");
	private final By FIRST_NAME_UNDER_MY_PROFILE_LOC = By.id("first-Name");
	private final By LAST_NAME_UNDER_MY_PROFILE_LOC = By.id("last-Name");
	private final By EMAIL_ID_UNDER_MY_PROFILE_LOC = By.id("email-account");
	private final By PHONE_NUMBER_UNDER_MY_PROFILE_LOC = By.id("phonenumber");
	private final By ADDRESS_1_UNDER_MY_PROFILE_LOC = By.id("address-1");
	private final By CITY_UNDER_MY_PROFILE_LOC = By.id("city");
	private final By STATE_UNDER_MY_PROFILE_LOC = By.id("state");
	private final By POSTAL_CODE_UNDER_MY_PROFILE_LOC = By.id("postal-code");
	private final By SIGN_OUT_UNDER_PROFILE_NAME_LOC = By.xpath("//div[contains(@class,'profile-header')]//a[text()='Sign Out']");
	private final By ENROLL_NOW_BTN_LOC = By.xpath("//a[text()='ENROL NOW']");

	private String headerLinkLoc= "//div[contains(@class,'header-containers')]//a[contains(text(),'%s')]";
	private String valuesUnderMyTeamSectionLoc= "//div[contains(@class,'myteam-block')]/descendant::*[contains(text(),'%s')][1]/preceding::span[1]";

	public SoftLaunchHomePage loginAsPreEnrollee(String username,String password){
		driver.waitForElementPresent(LOGIN_LINK_LOC,5);
		driver.click(LOGIN_LINK_LOC,"");
		driver.waitForElementPresent(USERNAME_TXTFLD_LOC);
		driver.type(USERNAME_TXTFLD_LOC, username);
		driver.type(PASSWORD_TXTFLD_LOC, password);   
		driver.click(LOGIN_BTN_LOC,"");
		driver.waitForPageLoad();
		return new SoftLaunchHomePage(driver);
	}

	public SoftLaunchHomePage clickTheLinkOfHeaderSection(String linkName){
		driver.click(By.xpath(String.format(headerLinkLoc, linkName)),"");
		return new SoftLaunchHomePage(driver);
	}

	public String getCallingAllPreEnroleesText(){
		String text = driver.getText(CALLING_PRE_ENROLLEES_TEXT_LOC);
		return text;
	}

	public boolean isShareYourURLIsNotALink(){
		driver.waitForElementPresent(SHARE_YOUR_URL_LOC);
		return driver.isElementPresent(SHARE_YOUR_URL_LOC);
	}

	public boolean isWhatIfTextPresent(){
		driver.waitForElementPresent(WHAT_IF_TXT_LOC);
		return driver.isElementPresent(WHAT_IF_TXT_LOC);
	}

	public boolean isLoginIconPresent(){
		driver.waitForElementPresent(LOGIN_LINK_LOC);
		return driver.isElementPresent(LOGIN_LINK_LOC);
	}

	public String getCurrentURL(){
		driver.waitForPageLoad();
		String url = driver.getCurrentUrl().toLowerCase();
		return url;
	}

	public boolean isUserLoggedInSuccessfully(){
		driver.waitForElementPresent(PROFILE_NAME_LOC);
		return driver.isElementPresent(PROFILE_NAME_LOC);
	}

	public boolean isValuePresentUnderMyTeamSection(String sectionName){
		String value = driver.findElement(By.xpath(String.format(valuesUnderMyTeamSectionLoc, sectionName))).getText();
		return (!value.isEmpty());
	}

	public boolean isSponsorNamePresent(){
		String value = driver.findElement(SPONSOR_NAME_LOC).getText();
		return (!value.isEmpty());
	}

	public SoftLaunchHomePage hoverOnProfileNameAndClickOnSettings(){
		driver.moveToELement(PROFILE_NAME_LOC);
		driver.click(SETTINGS_UNDER_PROFILE_NAME_LOC,"");
		return new SoftLaunchHomePage(driver);
	}

	public boolean isFirstNamePresent(){
		String name =  driver.findElement(FIRST_NAME_UNDER_MY_PROFILE_LOC).getAttribute("value");
		return (!name.isEmpty());
	}

	public boolean isLastNamePresent(){
		String name =  driver.findElement(LAST_NAME_UNDER_MY_PROFILE_LOC).getAttribute("value");
		return (!name.isEmpty());
	}

	public boolean isEmailIdPresent(){
		String email =  driver.findElement(EMAIL_ID_UNDER_MY_PROFILE_LOC).getAttribute("value");
		return (!email.isEmpty());
	}

	public boolean isPhoneNumberPresent(){
		String phoneNumber =  driver.findElement(PHONE_NUMBER_UNDER_MY_PROFILE_LOC).getAttribute("value");
		return (!phoneNumber.isEmpty());
	}

	public boolean isAddress1Present(){
		String address1 =  driver.findElement(ADDRESS_1_UNDER_MY_PROFILE_LOC).getAttribute("value");
		return (!address1.isEmpty());
	}

	public boolean isCityPresent(){
		String city =  driver.findElement(CITY_UNDER_MY_PROFILE_LOC).getAttribute("value");
		return (!city.isEmpty());
	}

	public boolean isStatePresent(){
		String state =  driver.findElement(STATE_UNDER_MY_PROFILE_LOC).getAttribute("value");
		return (!state.isEmpty());
	}

	public boolean isPostalCodePresent(){
		String postalCode =  driver.findElement(POSTAL_CODE_UNDER_MY_PROFILE_LOC).getAttribute("value");
		return (!postalCode.isEmpty());
	}

	public SoftLaunchHomePage logout(){
		driver.moveToELement(PROFILE_NAME_LOC);
		driver.click(SIGN_OUT_UNDER_PROFILE_NAME_LOC,"");
		return new SoftLaunchHomePage(driver);
	}


	/***
	 * This method click the enroll now button 
	 * 
	 * @param
	 * @return SofLaunch Home page object
	 * 
	 */
	public SoftLaunchHomePage clickEnrollNowBtn(){
		driver.waitForElementPresent(ENROLL_NOW_BTN_LOC);
		driver.click(ENROLL_NOW_BTN_LOC,"");
		return new SoftLaunchHomePage(driver);
	}

	public void selectEnrollmentKitPage(String kitName,String regimenName){
		driver.click(By.xpath("//*[contains(text(),'"+kitName+"')]"),"");
		driver.waitForLoadingImageToDisappear();
		regimenName = regimenName.toUpperCase();
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='regimen-name' and contains(.,'"+regimenName+"')]"))).click();
		driver.click (By.id("next-button"),""); // - old UI (By.cssSelector("input[value='Next']"));
	}

	public void chooseEnrollmentOption(String option){
		option = option.toUpperCase();
		if(option.equalsIgnoreCase("EXPRESS ENROLLMENT")){
			driver.click(By.id("express-enrollment"),"");
		}
		else{
			driver.waitForElementPresent(By.id("standard-enrollment"));
			driver.click(By.id("standard-enrollment"),"");
		}
		driver.click(By.cssSelector("input[value='Next']"),"");
		//		driver.waitForLoadingImageToDisappear();
	}

	public void enterPassword(String password){
		driver.type(By.id("new-password-account"),password);
	}

	public void enterConfirmPassword(String password){
		driver.type(By.id("new-password-account2"),password);
	}
	
	public void enterAddressLine1(String addressLine1){
		driver.type(By.id("address-1"),addressLine1);
	}

	public void enterCity(String city){
		driver.type(By.id("city"),city);
	}

	public void selectProvince(String province){ 
		if(driver.getCountry().equalsIgnoreCase("au")){
			driver.clickByJS(RFWebsiteDriver.driver, By.id("state"),"state");
			driver.click(By.xpath(String.format("//select[@id='state']//option[@value='%s']", province)),"");
		}else{
			WebElement stateDD = driver.findElement(By.id("state"));
			Select sel = new Select(stateDD);
			sel.selectByVisibleText(province);
		}
	}
	
	public void enterPostalCode(String postalCode){
		driver.type(By.id("postcode"),postalCode);
	}

	public void enterPhoneNumber(String phnNum){
		driver.type(By.id("phonenumber"),phnNum);
	}

	public void clickNextButton(){
		  driver.waitForElementPresent(By.id("enrollment-next-button"));
		  driver.clickByJS(RFWebsiteDriver.driver, By.id("enrollment-next-button"),"");
		  // driver.click(By.id("enrollment-next-button"));
		  driver.waitForLoadingImageToDisappear();
		  if(driver.isElementPresent(By.xpath("//label[contains(text(),'Postcode must be 4 digits long')]"))){
		   driver.type(By.id("postcode"), TestConstants.POSTAL_CODE_AU);
		   driver.clickByJS(RFWebsiteDriver.driver, By.id("enrollment-next-button"),"");
		   driver.waitForLoadingImageToDisappear();
		  }
		  
		  try{
		   driver.quickWaitForElementPresent(By.xpath("//input[@value='Accept']"),5);
		   driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//input[@value='Accept']"),"");
		  }
		  catch(Exception e){
		  }
		  
		  driver.waitForLoadingImageToDisappear();
		 }

	public void enterCardNumber(String cardNumber){
		driver.waitForElementPresent(By.id("card-nr"));
		driver.type(By.id("card-nr"),cardNumber);
		driver.pauseExecutionFor(2000);
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//*[@id='expiryYear']"),"");
		driver.type(By.id("security-code"),"123");
		driver.clear(By.id("security-code"));
	}

	public void enterNameOnCard(String nameOnCard){
		driver.type(By.id("card-name"),nameOnCard);
	}

	public void selectNewBillingCardExpirationDate(String month,String year){
		driver.clickByJS(RFWebsiteDriver.driver,By.id("expiryMonth"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='expiryMonth']/option[text()='"+month.toUpperCase()+"']"));
		driver.click(By.xpath("//select[@id='expiryMonth']/option[text()='"+month.toUpperCase()+"']"),"");
		driver.clickByJS(RFWebsiteDriver.driver,By.id("expiryYear"),"");
		driver.waitForElementPresent(By.xpath("//select[@id='expiryYear']/option[text()='"+year+"']"));
		driver.click(By.xpath("//select[@id='expiryYear']/option[text()='"+year+"']"),"");
	}

	public void enterSecurityCode(String securityCode){
		driver.type(By.id("security-code"),securityCode);
	}

	public void selectAllTermsAndConditionsChkBox(){
		List<WebElement> all = driver.findElements(By.xpath("//form[@id='placeOrderForm']/div/descendant::input/.."));
		for(WebElement chkbox : all){
			//driver.clickByJS(RFWebsiteDriver.driver, chkbox,"");
		}
	}

	public void clickOnChargeMyCardAndEnrollMeBtn() throws InterruptedException{
		driver.clickByJS(RFWebsiteDriver.driver,By.id("enroll-button"),"Charge and Enroll me button");
		driver.waitForLoadingImageToDisappear();  
	}

	public void clickOnConfirmAutomaticPayment() throws InterruptedException{
		try{
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@id='enroll']"),"confirm payment button");
			driver.waitForLoadingImageToDisappear();
		}catch(NoSuchElementException e){
		}
	}

	public String getCongratulationsMessage(){
		driver.waitForElementPresent(By.id("Congrats"));
		String message = driver.getText(By.id("Congrats"));
		return message;
	}

	public void checkPulseAndCRPEnrollment() throws InterruptedException{
		if(verifySubsribeToPulseCheckBoxIsNotSelected()){
			driver.waitForElementPresent(By.xpath("//input[@id='pulse-check']/.."));
			driver.click(By.xpath("//input[@id='pulse-check']/.."),"");
		}
		if(verifyEnrollToCRPCheckBoxIsNotSelected()){
			driver.waitForElementPresent(By.xpath("//input[@id='CRP-check']/.."));
			driver.click(By.xpath("//input[@id='CRP-check']/.."),"");
		}
	}

	public boolean verifySubsribeToPulseCheckBoxIsNotSelected(){
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(By.xpath("//input[@id='pulse-check']"));
		return !driver.findElement(By.xpath("//input[@id='pulse-check']")).getAttribute("class").contains("checked");
	}

	public boolean verifyEnrollToCRPCheckBoxIsNotSelected(){
		driver.waitForElementPresent(By.xpath("//input[@id='CRP-check']"));
		return !driver.findElement(By.xpath("//input[@id='CRP-check']")).getAttribute("class").contains("checked");
	}

	public void selectProductAndProceedToAddToCRP() throws InterruptedException{
		driver.quickWaitForElementPresent(By.xpath("//div[@id='main-content']/descendant::*[@value='Add to crp' or @value='ADD TO CRP'][1]"));
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath("//div[@id='main-content']/descendant::*[@value='Add to crp' or @value='ADD TO CRP'][1]"),"Add to CRP button");
		driver.waitForLoadingImageToDisappear();
		try{
			driver.quickWaitForElementPresent(By.xpath("//input[@value='OK']"));
			driver.click(By.xpath("//input[@value='OK']"),"");
		}catch(Exception e){

			driver.waitForLoadingImageToDisappear();
			driver.waitForPageLoad();
		}
	}

	public void clickOnNextBtnAfterAddingProductAndQty() throws InterruptedException{
		driver.clickByJS(RFWebsiteDriver.driver, By.id("submitForm"),"Next Btn After Adding Product And Qty");
		driver.waitForLoadingImageToDisappear();
	}
	
	public void uncheckPulseAndCRPEnrollment() throws InterruptedException{
		if(verifySubsribeToPulseCheckBoxIsSelected())
		{
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@id='pulse-check']/.."),"pulse checkbox");
		}
		if(verifyEnrollToCRPCheckBoxIsSelected())
		{
			driver.clickByJS(RFWebsiteDriver.driver,By.xpath("//input[@id='CRP-check']/.."),"crp checkbox");
		}  
	}
	
	public boolean verifySubsribeToPulseCheckBoxIsSelected() throws InterruptedException{  
		return driver.isElementPresent(By.xpath("//input[@id='pulse-check']/ancestor::div[@class='repaired-checkbox checked']"));
	}
	
	public boolean verifyEnrollToCRPCheckBoxIsSelected(){
		return driver.isElementPresent(By.xpath("//input[@id='CRP-check']/ancestor::div[@class='repaired-checkbox checked']"));
	}
	
	public void checkPulseCheckBox(){
		driver.click(By.xpath("//input[@id='pulse-check']/.."),"");
}

public void checkCRPCheckBox(){
	driver.waitForElementPresent(By.xpath("//input[@id='CRP-check']/.."));
	driver.click(By.xpath("//input[@id='CRP-check']/.."),"");
}


}
