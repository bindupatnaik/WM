package com.rf.core.driver.mobile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class IOSAppiumIntegrationWeb
{
	public static void main(String[] args) 
	{
		AppiumDriver wd = null;
		int count = 1;
		
			try
			{
				DesiredCapabilities capabilities = new DesiredCapabilities();
//				capabilities.setCapability("platformName", "iOS");//android
//				capabilities.setCapability("browserName", "Safari");//chrome
//				capabilities.setCapability("ensureCleanSession","true");
//				capabilities.setCapability("automationName", "XCUITest");//above 9.3.5 we have to use this , only for iOS
//				capabilities.setCapability("deviceConnectUsername","smathur@rodanandfields.com");
//				capabilities.setCapability("deviceConnectApiKey","8696c84e-b002-43e9-a7b1-f00207b297e4");
//				capabilities.setCapability("deviceConnectDevice","a520720c-5a6f-4e48-b385-93f81013a114");
				
				capabilities.setCapability("platformName", "Android");//android
				capabilities.setCapability("browserName", "Chrome");//chrome
				capabilities.setCapability("ensureCleanSession","true");
//				capabilities.setCapability("automationName", "XCUITest");//above 9.3.5 we have to use this , only for iOS
				capabilities.setCapability("deviceConnectUsername","smathur@rodanandfields.com");
				capabilities.setCapability("deviceConnectApiKey","8696c84e-b002-43e9-a7b1-f00207b297e4");
				capabilities.setCapability("deviceConnectDevice","e3599acf-cd74-4400-a12d-595b74100190");
	
				System.out.println("**********11");
				URL url = new URL("http://10.6.200.89/appium");
				Thread.sleep(5000);
				//wd = new IOSDriver(url, capabilities);
				wd = new AndroidDriver(url, capabilities);
				System.out.println("**********22");
				// Adding implicit timout
				wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				wd.navigate().to("http://www.corprfo.tst5.rodanandfields.com/ca");
				
				System.out.println("***********Waiting for page to load************");
				for(count=1; count<=5; count++)
				{
					System.out.println("count "+count);
					System.out.println("*****Select apple from single dropdown******************");
					WebElement Single_Dropdown = wd.findElement(By.id("select-id"));
					Select s = new Select(Single_Dropdown);
					s.selectByValue("apple");
		
					System.out.println("*****Select Androids Radio Button******************");
					WebElement RadioButton_Androids = wd.findElement(By.id("robots-id"));
					RadioButton_Androids.click();
		
					System.out.println("*****Select iPhone CheckBox******************");
					WebElement iPhone_chkbox = wd.findElement(By.xpath("//input[@value='iPhone']"));
					iPhone_chkbox.click();
		
					System.out.println("*****Enter UName******************");
					WebElement Input_UName = wd.findElement(By.xpath("(//input[@type='text'])[1]"));
					Input_UName.sendKeys("admin");
		
					System.out.println("*****Enter Text******************");
					WebElement Input_pwd = wd.findElement(By.xpath("(//input[@type='text'])[2]"));
					Input_pwd.sendKeys("ABCD");
				}
				System.out.println("CLose");
			}
			catch(Exception e)
			{
				System.out.println("Error at iteration number :" + count + e.getMessage()  + "\r\n Actual time : " + Calendar.getInstance().getTime());
			}
		
		
		//wd.quit();
	}
}
