package com.rf.pages.website.crm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import com.rf.core.driver.website.RFWebsiteDriver;

public class CRMContactDetailsPage extends CRMRFWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(CRMContactDetailsPage.class.getName());

	public CRMContactDetailsPage(RFWebsiteDriver driver) {
		super(driver);
	}

	public String getContactType(){
		switchToExtFrame(2);
		return driver.findElement(By.xpath("//td[contains(text(),'Contact Type')]/following-sibling::td[1]")).getText();
	}

	public boolean validatePrimaryContactTypeCannotBeDeleted(){
		switchToExtFrame(2);
		return driver.findElement(By.xpath("//td[contains(text(),'Primary')]/input")).getAttribute("value").equalsIgnoreCase("Primary");
	}

	public void updateFirstNameField(String firstName){
		switchToExtFrame(2);
		driver.type(By.xpath("//label[contains(text(),'First Name')]/following::input[1]"), firstName);
	}
	public void updateLastNameField(String lastName){
		switchToExtFrame(2);
		driver.type(By.xpath("//label[contains(text(),'Last Name')]/following::input[1]"), lastName);

	}

	public void updateEmailField(String eMailID){
		driver.switchTo().defaultContent();
		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		driver.findElement(By.xpath("//label[contains(text(),'Email Address')]/following::input[1]")).clear();
		driver.type(By.xpath("//label[contains(text(),'Email Address')]/following::input[1]"), eMailID);
	}

	public void updateMainPhoneNoField(String mainPhone){
		switchToExtFrame(2);
		driver.type(By.xpath("//label[contains(text(),'Main Phone')]/following::input[1]"), mainPhone);
	}


	public String getName(){
		switchToExtFrame(2);
		return driver.findElement(By.xpath("//td[text()='Name']/following-sibling::td[1]")).getText();
	}

	public String getMainPhone(){
		switchToExtFrame(2);
		return driver.findElement(By.xpath("//td[text()='Main Phone']/following-sibling::td[1]")).getText();
	}

	public String getEmailID(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//td[text()='Email Address']/following-sibling::td[1]/a")).getText();
	}

	public String getAccountName(){
//		driver.switchTo().defaultContent();
//		driver.waitForElementPresent(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]"));
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/div/div[3]/descendant::iframe[1]")));
		return driver.findElement(By.xpath("//td[text()='Account Name']/following-sibling::td[1]/a")).getText();
	}

	public void clickContactDetailEditBtn(){
		switchToExtFrame(2);
		driver.click(By.xpath("//td[@id='topButtonRow']/input[contains(@value,'Edit')]"),"");
		driver.waitForPageLoad();
	}

	public void clickSaveBtnUnderAccountDetail(){
		switchToExtFrame(2);
		driver.click(By.xpath("//td[@id='topButtonRow']/input[@title='Save']"),"");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(1000);
	}
}