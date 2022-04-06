package com.rf.pages.website.dsv;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.website.constants.TestConstants;

public class DSVStoreFrontOrdersPage extends DSVRFWebsiteBasePage {

	private final By ORDER_NUM_OF_ORDER_HISTORY = By.xpath("//div[contains(@id,'history-orders-table')]/descendant::a[contains(@href,'order')][1]");
	private final By ORDER_NUM_OF_RETURN_ORDER_HISTORY = By.xpath("//h3[contains(text(),'Return Order')]/following::a[contains(@href,'returnorder')][1]");
	private final By ORDER_NUM_OF_ORDER_DETAILS = By.xpath("//div[@id='main-content']/descendant::div[contains(text(),'Order details')]");


	private static final Logger logger = LogManager
			.getLogger(DSVStoreFrontShippingInfoPage.class.getName());

	public DSVStoreFrontOrdersPage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyOrdersPageIsDisplayed(){
		return driver.getCurrentUrl().contains(TestConstants.ORDERS_PAGE_SUFFIX_URL);
	}

	public String isGetOrderNumberFromOrderPresentOnOrdersPage(){
		String firstOrderNumber = null;
		driver.waitForElementPresent(ORDER_NUM_OF_ORDER_HISTORY);
		if(driver.isElementPresent(ORDER_NUM_OF_ORDER_HISTORY)){
			firstOrderNumber = driver.getText(ORDER_NUM_OF_ORDER_HISTORY,"Order Number from Order History");
			return  firstOrderNumber;
		}else{
			firstOrderNumber = driver.getText(ORDER_NUM_OF_RETURN_ORDER_HISTORY,"Order Number from Return Order History");
			return  firstOrderNumber;
		}
	}

	public String getOrderNumberFromOrderDetails(){
		driver.waitForElementPresent(ORDER_NUM_OF_ORDER_DETAILS);
		String orderNumber = driver.findElement(ORDER_NUM_OF_ORDER_DETAILS).getText().split("#")[1];
		return orderNumber;
	}

	public boolean isProductListPresentOnOrderDetails(){
		List<WebElement> productList = driver.findElements(By.xpath("//div[contains(text(),'Items In Order')]/ancestor::div[2]//img"));
		if(productList.size()>0)
			return true;
		return false;
	}
	
	public String getFirstOrderAndClickOnOrderNumber(){
		String firstOrderNumber = null;
		try{
			driver.waitForElementPresent(ORDER_NUM_OF_ORDER_HISTORY);
			firstOrderNumber = driver.getText(ORDER_NUM_OF_ORDER_HISTORY,"Order Number from Order History");
			driver.click(ORDER_NUM_OF_ORDER_HISTORY,"");
			return firstOrderNumber;
		}catch(NoSuchElementException e){
			firstOrderNumber = driver.getText(ORDER_NUM_OF_RETURN_ORDER_HISTORY,"Order Number from Return Order History");
			driver.scrollDownIntoView(ORDER_NUM_OF_RETURN_ORDER_HISTORY);
			driver.click(ORDER_NUM_OF_RETURN_ORDER_HISTORY,"");
			return firstOrderNumber;
		}
	} 
}
