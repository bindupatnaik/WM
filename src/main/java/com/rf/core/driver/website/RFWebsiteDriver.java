package com.rf.core.driver.website;

import java.io.File;

import org.openqa.selenium.Dimension;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xpath.CachedXPathAPI;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.reflect.Method;
import com.rf.core.driver.RFDriver;
import com.rf.core.listeners.TestListner;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.PropertyFile;
import com.rf.core.utils.SoftAssert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * @author ShubhamMathur RFWebsiteDriver extends implements Webdriver and
 *         uses Firefox/Internet/Chrome for implementation, customized reusable
 *         functions are added along with regular functions
 */
public class RFWebsiteDriver implements RFDriver,WebDriver {
	public static WebDriver driver; // added static and changed visibility from public to private
	private PropertyFile propertyFile;
	private static int DEFAULT_TIMEOUT = 20;
	private static int DEFAULT_TIMEOUT_CSCOCKPIT = 30;
	String browser = null;
	String dbIP = null;
	String baseURL  =null;
	String country = null;
	String environment = null;
	public RFWebsiteDriver(PropertyFile propertyFile) {
		//super();
		this.propertyFile = propertyFile;			
	}

	private static final Logger logger = LogManager
			.getLogger(RFWebsiteDriver.class.getName());


	/**
	 * @throws MalformedURLException
	 *             Prepares the environment that tests to be run on
	 */
	public void loadApplication() throws MalformedURLException {
		browser=System.getProperty("browser");

		if(StringUtils.isEmpty(browser)){
			browser = propertyFile.getProperty("browser");
		}

		if(propertyFile.getProperty("device").equalsIgnoreCase("mobile")){
			String deviceConnectUsername = propertyFile.getProperty("deviceConnectUsername");
			String deviceConnectApiKey = propertyFile.getProperty("deviceConnectApiKey");
			String deviceConnectDeviceId = propertyFile.getProperty("deviceConnectDeviceId");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			if(propertyFile.getProperty("platform").equalsIgnoreCase("android")){
				capabilities.setCapability("platformName", Platform.ANDROID);
				capabilities.setCapability("browserName", BrowserType.CHROME);
				capabilities.setCapability("newCommandTimeout", 200000);
				capabilities.setCapability("ensureCleanSession","true");
				capabilities.setCapability("deviceConnectUsername",deviceConnectUsername);
				capabilities.setCapability("deviceConnectApiKey",deviceConnectApiKey);
				capabilities.setCapability("deviceConnectDevice",deviceConnectDeviceId);
				URL url = new URL("http://10.6.200.89/appium");
				driver = new AndroidDriver<>(url, capabilities);
			}else{		
				capabilities.setCapability("platformName", "iOS");//android
				capabilities.setCapability("browserName", "Safari");//chrome
				capabilities.setCapability("ensureCleanSession","true");
				capabilities.setCapability("automationName", "XCUITest");//above 9.3.5 we have to use this , only for iOS
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability("deviceConnectUsername",deviceConnectUsername);
				capabilities.setCapability("deviceConnectApiKey",deviceConnectApiKey);
				capabilities.setCapability("deviceConnectDevice",deviceConnectDeviceId);
				URL url = new URL("http://10.6.200.89/appium");
				driver = new IOSDriver(url, capabilities);
			}

		} else {
		
			if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
					FirefoxOptions options = new FirefoxOptions();
					options.addPreference("brower.startup.homepage", "about:blank");
					options.addPreference("startup.homepage_welcome_url", "about:blank");
					options.addPreference("startup.homepage_welcome_url.additional", "about:blank");
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
					System.out.println("****************88");
					driver = new FirefoxDriver(capabilities);
					Dimension d = new Dimension(1936, 1056);
					driver.manage().window().setSize(d);
				} 
			else if (browser.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("no-sandbox");
					options.addArguments("chrome.switches","--disable-extensions");
					options.addArguments("disable-popup-blocking");
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					// for clearing cache
					capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
					driver = new ChromeDriver(capabilities);
				}
				else if(browser.equalsIgnoreCase("headless")){
					driver = new HtmlUnitDriver(true);
				}
				else if (browser.equalsIgnoreCase("ie")) {
					System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
					InternetExplorerOptions options = new InternetExplorerOptions();
					options.setCapability(CapabilityType.BROWSER_NAME,DesiredCapabilities.internetExplorer());
					options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
					options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					driver = new InternetExplorerDriver(options);
				}
			 driver.manage().window().maximize();
			 
			}
	}


	public void setDBConnectionString(){
		String dbUsername = null;
		String dbPassword = null;
		String dbDomain = null;

		dbIP=System.getProperty("dbIP");
		if(StringUtils.isEmpty(dbIP)){
			dbIP = propertyFile.getProperty("dbIP");
		}
		String authentication = null;
		dbUsername = propertyFile.getProperty("dbUsername");
		dbPassword = propertyFile.getProperty("dbPassword");
		dbDomain = propertyFile.getProperty("dbDomain");		 
		authentication = propertyFile.getProperty("authentication");
		DBUtil.setDBDetails(dbIP, dbUsername, dbPassword, dbDomain, authentication);
		logger.info("DB connections are set");
	}

	public void setDBConnectionString(String dbIP, String dbUsername, String dbPassword, String dbDomain, String authentication){
		DBUtil.setDBDetails(dbIP, dbUsername, dbPassword, dbDomain, authentication);
		logger.info("DB connections are set");
	}

	public String getDBIP2(){
		return propertyFile.getProperty("dbIP2");
	}

	public String getDBIP(){
		return propertyFile.getProperty("dbIP");
	}

	public String getDBUsername(){
		return propertyFile.getProperty("dbUsername");
	}

	public String getDBPassword(){
		return propertyFile.getProperty("dbPassword");
	}

	public String getDBDomain(){
		return propertyFile.getProperty("dbDomain");
	}

	public String getDBAuthentication(){
		return propertyFile.getProperty("authentication");
	}

	public void selectCountry(String country){
		driver.findElement(By.xpath("//div[@class='btn-group']")).click();
		if(country.equalsIgnoreCase("ca")){
			driver.findElement(By.xpath("//div[contains(@class,'btn-group')]//a[@class='dropdownCA']")).click();
		}
		else{
			driver.findElement(By.xpath("//div[contains(@class,'btn-group')]//a[@class='dropdownUS']")).click();
		}
		waitForPageLoad();
	}

	public String getURL() {
		baseURL=System.getProperty("baseURL");
		if(StringUtils.isEmpty(baseURL)){
			baseURL = propertyFile.getProperty("baseUrl");
		}
		return baseURL;
	}

	public String getBrowser(){
		return browser;
	}
	public String getBizPWSURL() {
		if (propertyFile.getProperty("environment").equalsIgnoreCase("tst1"))
		{
			return propertyFile.getProperty("pwsBase")+".biz";
		}
		else
		{
			return propertyFile.getProperty("pwsBase")+getEnvironment()+".biz";
		}
	}

	public boolean shouldFetchDataFromExcelForTokeniation(){
		String shouldFetchDataFromExcelForTokeniation = propertyFile.getProperty("shouldFetchDataFromExcelForTokeniation");
		System.out.println("shouldFetchDataFromExcelForTokeniation "+shouldFetchDataFromExcelForTokeniation);
		if(shouldFetchDataFromExcelForTokeniation.equalsIgnoreCase("true")){
			return true;
		}else{
			return false;
		}
	}

	public String getComPWSURL() {
		//		return propertyFile.getProperty("pwsComBase");
		if (propertyFile.getProperty("environment").equalsIgnoreCase("tst1"))
		{
			return propertyFile.getProperty("pwsBase")+".com";
		}
		else
		{
			return propertyFile.getProperty("pwsBase")+getEnvironment()+".com";
		}
	}

	public String getDBNameRFL(){
		return propertyFile.getProperty("databaseNameRFL");
	}

	public String getDBNameRFO(){
		return propertyFile.getProperty("databaseNameRFO");
	}

	public String getDBNameCom(){
		return propertyFile.getProperty("databaseNameCom");
	}


	public String getCountry(){
		country=System.getProperty("country");
		if(StringUtils.isEmpty(country)){
			country = propertyFile.getProperty("country");
		}
		return country;
	}

	public String getEnvironment(){
		environment=System.getProperty("env");
		if(StringUtils.isEmpty(environment)){
			environment = propertyFile.getProperty("environment");
		}
		return environment;
	}

	public String getStoreFrontPassword(){
		return propertyFile.getProperty("storeFrontPassword");
	}

	public String getCSCockpitURL(){
		String country=getCountry();
		String url=propertyFile.getProperty("csCockpitUrl");
		if(url.contains("cscockpit") && country.equalsIgnoreCase("au")){
			String URL=url.replaceAll("\\.com","\\.com.au");
			driver.get(url);
			return URL;
		}else{
			return propertyFile.getProperty("csCockpitUrl");
		}
	}

	public String getStoreFrontURL(){
		return propertyFile.getProperty("storeFrontUrl");
	}

	public String getNSCore4URL(){
		return propertyFile.getProperty("nscore4Url");
	}

	public String isUserFetchedFromDB(){
		return propertyFile.getProperty("fetchUsersFromDB");
	}

	public String getDevice(){
		return propertyFile.getProperty("device");		
	}

	public void get(String Url) {
		logger.info("URL opened is "+Url);
		try{
		driver.get(Url);
		waitForPageLoad();
		pauseExecutionFor(2000);
		}catch(Exception e){
			logger.info("Exception caught in driver.get = "+e.getMessage());
		}
	} 

	/**
	 * @param locator
	 * @return
	 */
	public boolean isElementPresent(By locator) {
		turnOffImplicitWaits();
		waitForElementPresent(locator,5);
		try{
			if (driver.findElements(locator).size() > 0) {
				return true;
			} else
				return false;
		}
		catch(Exception e){
			return false;
		}

		finally{
			turnOnImplicitWaits();
		}
	}

	/**
	 * @param locator
	 * @return
	 */
	public boolean isElementPresent(By locator,int time) {
		turnOffImplicitWaits(time);
		waitForElementPresent(locator,time);
		try{
			if (driver.findElements(locator).size() > 0) {
				return true;
			} else
				return false;
		}
		catch(Exception e){
			return false;
		}

		finally{
			turnOnImplicitWaits();
		}
	}

	public void waitForElementPresent(By locator) {
		logger.info("wait started for "+locator);
		try {
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
			logger.info("waiting for locator " + locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			logger.info("Element found");
		} catch (Exception e) {
			e.getStackTrace();
		}	
	}

	public void waitForElementPresent(By locator,String msg) {
		logger.info("wait started for "+msg);
		try {
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
			logger.info("waiting for locator " + locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			logger.info("Element found");
		} catch (Exception e) {
			e.getStackTrace();
		}	
	}

	public void waitForElementPresent(By locator, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			logger.info("waiting for locator " + locator);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			logger.info("Element found");
		} catch (Exception e) {
			e.getStackTrace();
		}	
	}

	public boolean quickWaitForElementPresent(By locator){
		logger.info("quick wait started for "+locator);
		int timeout = 2;
		turnOffImplicitWaits();
		boolean isElementFound = false;
		for(int i=1;i<=timeout;i++){
			//			try{
			if(driver.findElements(locator).size()==0){
				pauseExecutionFor(1000);
				logger.info("waiting...");
				continue;
			}else{
				logger.info("wait over,element found");
				isElementFound =true;
				turnOnImplicitWaits();
				break;
			}			
		}

		if(isElementFound==false)
			logger.info("ELEMENT NOT FOUND");
		turnOnImplicitWaits();
		return isElementFound;
	}

	public boolean quickWaitForElementPresent(By locator, int timeout){
		logger.info("quick wait started for "+locator);
		turnOffImplicitWaits();
		boolean isElementFound = false;
		for(int i=1;i<=timeout;i++){
			if(driver.findElements(locator).size()==0){
				pauseExecutionFor(1000);
				logger.info("waiting...");
				continue;
			}else{
				logger.info("wait over,element found");
				isElementFound =true;
				turnOnImplicitWaits();
				break;
			}			
		}

		if(isElementFound==false)
			logger.info("ELEMENT NOT FOUND");
		turnOnImplicitWaits();
		return isElementFound;
	}

	public void waitForElementNotPresent(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
			logger.info("waiting for locator " + locator);
			wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(locator)));
			logger.info("Element found");
		} catch (Exception e) {
			e.getStackTrace();
		}		
	}

	public void waitForLoadingImageToDisappear(){
		int DEFAULT_TIMEOUT = 30;
		pauseExecutionFor(1000);
		//waitForLoadingImageToAppear();
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@id='blockUIBody'] | //div[@class='blockUI blockMsg blockPage']//img");
		if(driver.findElements(locator).size()==1){
			logger.info("Waiting for loading image to get disappear");
			for(int i=1;i<=DEFAULT_TIMEOUT;i++){			
				try{
					if(driver.findElements(locator).size()==1){
						pauseExecutionFor(1000);
						logger.info("waiting..");
						continue;
					}else{
						turnOnImplicitWaits();
						logger.info("loading image disappears");
						break;
					}			
				}catch(Exception e){
					continue;
				}
			}

		}
	}

	public void waitForLoadingImageToDisappear(int timeout){
		int DEFAULT_TIMEOUT = timeout;
		pauseExecutionFor(1000);
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@id='blockUIBody']");
		logger.info("Waiting for loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){			
			try{
				if(isElementVisible(locator)==true){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("loading image disappears");
					break;
				}			
			}catch(Exception e){
				continue;
			}
		}

	}

	public void waitForCRMLoadingImageToDisappear(){
		turnOffImplicitWaits();
		By locator = By.xpath("//span[contains(text(),'Loading')]");
		logger.info("Waiting for crm loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){			
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info(" crm loading image disappears");
					break;
				}			
			}catch(Exception e){
				continue;
			}
		}

	}	

	public void waitForCRMLoadingImageToDisappear(int time){
		turnOffImplicitWaits();
		By locator = By.xpath("//span[contains(text(),'Loading')]");
		logger.info("Waiting for crm loading image to get disappear");
		for(int i=1;i<=time;i++){			
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info(" crm loading image disappears");
					break;
				}			
			}catch(Exception e){
				continue;
			}
		}

	}	

	public void waitForLoadingImageToAppear(){
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@id='blockUIBody']");
		int timeout = 3;

		for(int i=1;i<=timeout;i++){			
			try{
				if(driver.findElements(locator).size()==0){
					pauseExecutionFor(1000);

					continue;
				}else{
					turnOnImplicitWaits();

					break;
				}			
			}catch(Exception e){
				continue;
			}
		}

	}

	public void waitForSpinImageToDisappear(){
		turnOffImplicitWaits();
		By locator = By.xpath("//span[@id='email-ajax-spinner'][contains(@style,'display: inline;')]");
		logger.info("Waiting for spin image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else {
					logger.info("spin image disappears");
					turnOnImplicitWaits();
					break;
				}			
			}catch(Exception e){
				continue;
			}
		}
	}

	public void waitForStorfrontLegacyLoadingImageToDisappear(){
		pauseExecutionFor(1000);
		turnOffImplicitWaits();
		By locator = By.xpath("//div[contains(@id,'UpdateProgress')][contains(@style,'display: block;')]");
		logger.info("Waiting for storefront legacy loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){   
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("Storefront legacy loading image disappears");
					break;
				}   
			}catch(Exception e){
				continue;
			}
		}

	}

	public void waitForNSCore4LoadingImageToDisappear(){
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@style='display: block;']/img");
		logger.info("Waiting for NSCore4 loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){   
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("NSCore4 loading image disappears");
					break;
				}   
			}catch(Exception e){
				continue;
			}
		}

	}

	public void waitForNSCore4ProcessImageToDisappear(){
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@class='HModalOverlay']");
		logger.info("Waiting for NSCore4 process loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){   
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("NSCore4 process loading image disappears");
					break;
				}   
			}catch(Exception e){
				continue;
			}
		}
	}

	public void waitForLSDJustAMomentImageToDisappear(){
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@id='preload'][not(@style='display:none')]");
		logger.info("Waiting for LSD loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){   
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("LSD loading image disappears");
					break;
				}   
			}catch(Exception e){
				continue;
			}
		}
	}

	public void waitForLSDLoaderAnimationImageToDisappear(){
		turnOffImplicitWaits();
		By locator = By.xpath("//div[contains(@class,'loader-animation')][(@style='')]");
		logger.info("Waiting for LSD loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){   
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("LSD Animation image disappears");
					break;
				}   
			}catch(Exception e){
				continue;
			}
		}
	}

	public void waitForElementTobeEnabled(By locator){
		for(int time=1;time<=30;time++){
			if(driver.findElement(locator).isEnabled()==true){
				break;
			}		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public boolean waitForElementTobeEnabled(By locator,int timeout){
		boolean isEnabled=false;
		for(int time=1;time<=timeout;time++){
			if(driver.findElement(locator).isEnabled()==true){
				isEnabled=true;
				break;
			}		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return isEnabled;

	}

	public void switchAndAcceptAlert(){
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
		}
	}

	public void click(By locator, String elementToBeClicked) {	
		waitForElementToBeVisible(locator,10);
		logger.info(elementToBeClicked+" ready to be clicked");
		try{
			findElement(locator).click();	
			logger.info("CLICKED on "+elementToBeClicked);
		}
		catch(Exception e){
			logger.info("Got exception while clicking on "+elementToBeClicked);
			logger.info("The exception is "+e);
			if(isElementVisible(locator)==true)
				retryingFindClick(locator,elementToBeClicked);
			else{
				logger.info("The element = "+elementToBeClicked+" is invisible at the time of click");
				throw e;
			}
		}
	}	

	/**
	 * Use this method when the element to bring in focus is down
	 * @param locator
	 */
	public void scrollUpIntoView(By locator){
		waitForElementPresent(locator, 10);
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor)(RFWebsiteDriver.driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		pauseExecutionFor(1000);
	}

	/**
	 * Use this method when the element to bring in focus is up
	 * @param locator
	 */
	public void scrollDownIntoView(By locator){
		waitForElementPresent(locator, 10);
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor)(RFWebsiteDriver.driver);
		js.executeScript("arguments[0].scrollIntoView(false);", element);
		pauseExecutionFor(2000);
	}

	public void doubleClick(By locator, String elementToBeClicked){
		waitForElementToBeVisible(locator,10);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		WebElement e = driver.findElement(locator);
		actions.moveToElement(e).doubleClick(e).build().perform();
	}

	public void type(By locator, String input) {
//		waitForElementToBeVisible(locator, 5);
		type(locator, input, "");
	}

	public void clearTxtField(By by){
		Actions action = new Actions(driver);
		int lenText = driver.findElement(by).getText().length();

		for(int i = 0; i < lenText; i++){
			action.sendKeys(Keys.ARROW_LEFT);
		}
		action.build().perform();

		for(int i = 0; i < lenText; i++){
			action.sendKeys(Keys.DELETE);
		}
		pauseExecutionFor(1000);
		action.build().perform();
	}

	public void type(By locator, String input, String element) {
		waitForElementPresent(locator);
		try{
			findElement(locator).clear();
			findElement(locator).sendKeys(input);
		}catch(Exception e){
			waitForElementToBeVisible(locator, 5);
			findElement(locator).sendKeys(input);
		}
		logger.info("TYPED text as = "+input+" in "+element+" text field");
	}


	public void quit() {
		driver.quit();
	}

	public String getCurrentUrl() {
		String currentURL = driver.getCurrentUrl();
		logger.info("current URL is "+currentURL);
		return currentURL;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public List<WebElement> findElements(By by) {
		// movetToElementJavascript(by);
		return driver.findElements(by);
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	public WebElement findElementWithoutMove(By by) {
		// movetToElementJavascript(by);
		return driver.findElement(by);
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void close() {
		driver.close();
		logger.info("current window closed");
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	public Navigation navigate() {
		return driver.navigate();

	}

	public Options manage() {
		return driver.manage();
	}

	public Select getSelect(By by) {
		return new Select(findElement(by));
	}

	public void scrollToBottomJS() throws InterruptedException {
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
	}

	public void clear(By by) {
		//		quickWaitForElementPresent(by);
		findElement(by).clear();
	}

	public void movetToElementJavascript(By locator) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);",
				driver.findElement(locator));
	}

	public String getAttribute(By by, String name) {
		waitForElementPresent(by);
		return findElement(by).getAttribute(name);
	}

	/**
	 * 
	 * Purpose:This Method return text value of Element.
	 * 
	 * @author: 
	 * @date:
	 * @returnType: String
	 * @param by
	 * @return
	 */
	public String getText(By by) {
		waitForElementPresent(by);
		return findElement(by).getText();
	}

	/**
	 * 
	 * Purpose:This Method return text value of Element.
	 * 
	 * @author: 
	 * @date:
	 * @returnType: String
	 * @param by
	 * @return
	 */
	public String getText(By by,String msg) {
		waitForElementToBeVisible(by, 10);
		String text =  findElement(by).getText();
		logger.info("TEXT fetched for "+msg+" is="+text);
		return text;
	}

	public void fnDragAndDrop(By by) throws InterruptedException {
		WebElement Slider = driver.findElement(by);
		Actions moveSlider = new Actions(driver);
		moveSlider.clickAndHold(Slider);
		moveSlider.dragAndDropBy(Slider, 150, 0).build().perform();
	}

	public Actions action() {
		return new Actions(driver);
	}

	public void pauseExecutionFor(long lTimeInMilliSeconds) {
		try {
			Thread.sleep(lTimeInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if element is visible Purpose:
	 * 
	 * @author: 
	 * @date:
	 * @returnType: WebElement
	 */
	public boolean IsElementVisible(WebElement element) {
		return element.isDisplayed() ? true : false;
	}

	/**
	 * Checks if element is visible Purpose:
	 * 
	 * @author: 
	 * @date:
	 * @returnType: By locator
	 */
	public boolean isElementVisible(By locator) {
		try{
			waitForElementToBeVisible(locator,5);
			return driver.findElement(locator).isDisplayed() ? true : false;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * Checks if element is visible Purpose:
	 * 
	 * @author: 
	 * @date:
	 * @returnType: By locator
	 */
	public boolean isElementVisible(By locator,int timeout) {
		try{
			waitForElementToBeVisible(locator,timeout);
			return driver.findElement(locator).isDisplayed() ? true : false;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * Checks if element is visible Purpose:
	 * 
	 * @author: 
	 * @date:
	 * @returnType: By locator
	 */
	public boolean isElementVisible(WebElement element,int timeout) {
		try{
			return element.isDisplayed() ? true : false;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * 
	 * Purpose:Waits for element to be visible
	 * 
	 * @author: 
	 * @date:
	 * @returnType: WebElement
	 */
	public void waitForElementToBeVisible(By locator, int timeOut) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			WebElement element = wait.until(ExpectedConditions
					.visibilityOfElementLocated(locator));
		}catch(Exception e){

		}
	}

	/**
	 * 
	 * Purpose: Waits and matches the exact title
	 * 
	 * @author: 
	 * @date:
	 * @returnType: boolean
	 */
	public boolean IsTitleEqual(By locator, int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleIs(title));
	}

	/**
	 * Waits and matches if title contains the text Purpose:
	 * 
	 * @author: 
	 * @date:
	 * @returnType: boolean
	 */
	public boolean IsTitleContains(By locator, int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * Wait for element to be clickable Purpose:
	 * 
	 * @author: 
	 * @date:
	 * @returnType: WebElement
	 */
	public boolean waitForElementToBeClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try{
			WebElement element = wait.until(ExpectedConditions
					.elementToBeClickable(locator));
			return true;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * 
	 * Purpose:Wait for element to disappear
	 * 
	 * @author: 
	 * @date:
	 * @returnType: boolean
	 */
	public void  waitForElementToBeInVisible(By locator, int timeOut) {
		try{
			logger.info("wait for invisible of "+locator);
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(locator));
		}catch(Exception e){

		}
	}

	public boolean waitForPageLoad() {
		boolean isLoaded = false;
		try {
			logger.info("Waiting For Page load via JS");
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript(
							"return document.readyState").equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
			wait.until(pageLoadCondition);
			isLoaded = true;
		} catch (Exception e) {
			logger.error("Error Occured waiting for Page Load "
					+ driver.getCurrentUrl());
		}
		logger.info("page load complete..");
		return isLoaded;
	}

	public void selectFromList(List<WebElement> lstElementList,
			String sValueToBeSelected) throws Exception {
		logger.info("START OF FUNCTION->selectFromList");
		boolean flag = false;
		logger.info("Total element found --> " + lstElementList.size());
		logger.info("Value to be selected " + sValueToBeSelected + " from list"
				+ lstElementList);
		for (WebElement e : lstElementList) {
			logger.info(">>>>>>>>>>>>>" + e.getText() + "<<<<<<<<<<<<<<<");
			if (e.getText().equalsIgnoreCase(sValueToBeSelected)) {
				logger.info("Value matched in list as->" + e.getText());
				e.click();
				flag = true;
				break;
			}
		}
		if (flag == false) {
			throw new Exception("No match found in the list for value->"
					+ sValueToBeSelected);
		}
		logger.info("END OF FUNCTION->selectFromList");
	}

	public void selectByVisibleText(WebElement webElement, String visibleText,String element){
		Select select = new Select(webElement);
		select.selectByVisibleText(visibleText);
		logger.info("SELECTED "+visibleText+" from "+element+" dropdown");
	}

	public WebElement waitForElementToBeClickable(WebElement element,
			int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}


	public String getCurrentUrlWithExpectedWaitForURLPresent(String url) {
		waitForExpectedURLPresent(url);
		String currentURL = driver.getCurrentUrl();
		logger.info("current URL is "+currentURL);
		return currentURL;
	}

	/**
	 * 
	 * Purpose: Helps in case of Stalement Exception
	 * 
	 * @author: 
	 * @date:
	 * @returnType: boolean
	 */
	public void retryingFindClick(By by,String elementToBeClicked) {
		logger.info("retrying click for JS ");
		waitForElementToBeClickable(by, 1);
		clickByJS(driver, by,elementToBeClicked);
	}

	public boolean deleteAllCookies(WebDriver driver) {
		boolean IsDeleted = false;
		try {
			driver.manage().deleteAllCookies();
			IsDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return IsDeleted;
	}

	public static void scrollToElement(WebDriver driver, WebElement element) {
		logger.info("Scrolling to Element");
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}

	public void turnOffImplicitWaits() {
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public void turnOffImplicitWaits(int seconds) {
		//driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void turnOnImplicitWaits() {
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}


	public void clickByJS(WebDriver driver, By locator, String message) {
		waitForElementToBeVisible(locator, 10);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(locator));
		logger.info("CLICKED by JS on "+message);

	}

	public void clickByJS(WebDriver driver,WebElement e, String message) {		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", e);
		logger.info("CLICKED on "+message);

	}

	public static String takeSnapShotAndRetPath(WebDriver driver,String screenshotName,boolean isScreenshotFolderCreated ) throws Exception {
		String FullSnapShotFilePath = "";
		String methodName = TestListner.getMethodName();
		try {
			logger.info("Taking Screenshot");
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			String sFilename = null;
			if(isScreenshotFolderCreated==false){
				CommonUtils.createFolder("output\\Screenshots\\"+methodName);
			}
			sFilename = "\\output\\Screenshots\\"+methodName +"\\"+ screenshotName+".png";
			FullSnapShotFilePath = System.getProperty("user.dir")+sFilename;
			FileUtils.copyFile(scrFile, new File(FullSnapShotFilePath));
		} catch (Exception e) {

		}

		return FullSnapShotFilePath;
	}

	/**
	 * Returns current Date Time
	 * 
	 * @return
	 */
	public static String getDateTime() {
		String sDateTime = "";
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
			Date now = new Date();
			String strDate = sdfDate.format(now);
			String strTime = sdfTime.format(now);
			strTime = strTime.replace(":", "-");
			sDateTime = "D" + strDate + "_T" + strTime;
		} catch (Exception e) {
			System.err.println(e);
		}
		return sDateTime;
	}

	public String switchToSecondWindow(){
		Set<String> allWindows = driver.getWindowHandles();
		String parentWindow = driver.getWindowHandle();
		for(String allWin : allWindows){
			if(!allWin.equalsIgnoreCase(parentWindow)){
				driver.switchTo().window(allWin);
				break;
			}

		}
		logger.info("Switched to second window whose title is "+driver.getTitle());	
		return parentWindow;
	}

	public void switchToChildWindow(String parentWinHandle){
		Set<String> allWindows = driver.getWindowHandles();
		for(String allWin : allWindows){
			if(!allWin.equalsIgnoreCase(parentWinHandle)){
				driver.switchTo().window(allWin);
				break;
			}

		}
	}

	public String getCrmURL(){
		return propertyFile.getProperty("crmUrl");
	}

	public void moveToELement(By locator) {
		Actions build = new Actions(driver);
		waitForElementPresent(locator);
		build.moveToElement(driver.findElement(locator)).build().perform();
	}

	public void waitForExpectedURLPresent(String URL) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
			logger.info("waiting for URL " + URL);
			wait.until(ExpectedConditions.urlContains(URL));
			logger.info("Element found");
		} catch (Exception e) {
			e.getStackTrace();
		}  
	}

	public void enterValueUsingJS(By locator,String value){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement e = driver.findElement(locator);
		executor.executeScript("arguments[0].innerHTML='" + value + "'", e);
	}

	public boolean waitForAjaxCallCompletion() {
		boolean isAjaxCompleted = false;
		try {
			logger.info("Waiting For Ajax call completion");
			ExpectedCondition<Boolean> ajaxCallCompletion = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
			wait.until(ajaxCallCompletion);
			isAjaxCompleted = true;
		} catch (Exception e) {
			logger.error("Error Occured waiting for Page Load "
					+ driver.getCurrentUrl());
		}
		logger.info("Ajax call is complete.");
		return isAjaxCompleted;
	}		

	public void waitForAlert(){
		WebDriverWait wait = new WebDriverWait(RFWebsiteDriver.driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForSpinImageToDisappearPulse(){
		By locator = By.xpath("//*[contains(@class,'spin')]");
		logger.info("Waiting for spin image to get disappear"+driver.findElements(locator).size());
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){
			if(driver.findElements(locator).size()>0){
				pauseExecutionFor(1000);
				logger.info("waiting..");
				continue;
			}else {
				logger.info("spin image disappears");
				break;
			}			
		}		
	}

	public void waitForSpinImageToDisappearPulse(int DEFAULT_TIMEOUT){
		turnOffImplicitWaits();
		By locator = By.xpath("//*[@id='sub-stage']/descendant::span[contains(@class,'icon-settings spin')][1]");
		logger.info("Waiting for spin image to get disappear"+(driver.findElements(locator).size()));
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){
			try{
				if(driver.findElements(locator).size()>0){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else {
					logger.info("spin image disappears");
					turnOnImplicitWaits();
					break;
				}			
			}catch(Exception e){
				continue;
			}
		}
		pauseExecutionFor(4000);
	}


	public void waitForLoginToPulse(){
		turnOffImplicitWaits();
		By locator = By.xpath("//button[contains(text(),'logging in')] | //button[contains(text(),'log in')]");
		logger.info("Waiting for login button to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else {
					logger.info("login button disappears");
					turnOnImplicitWaits();
					break;
				}			
			}catch(Exception e){
				continue;
			}
		}
	} 

	public String getTextByJS(WebDriver driver,By locator, String message) {		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String text = (String) executor.executeScript("return arguments[0].textContent", driver.findElement(locator));
		logger.info("text on "+text);
		return text;
	} 

	public boolean areElementsVisible(By locator) {
		boolean flag=false;
		List<WebElement> lstWb = driver.findElements(locator);
		try{
			for(WebElement wb:lstWb){
				flag =wb.isDisplayed() ? true : false;
				if(flag==true){
					continue;
				}else{
					break;
				}
			}
			return flag;
		}catch(Exception e){
			return false;
		}
	}

	public void waitForNSCore4PopupImageToDisappear(){
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@class='mContent']/img[contains(@alt,'loading..')]");
		logger.info("Waiting for NSCore4 loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT;i++){   
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("NSCore4 loading image disappears");
					break;
				}   
			}catch(Exception e){
				continue;
			}
		}
	}

	public void waitForTotalWindowsToBeOpened(int expectedNumberOfWindows){
		WebDriverWait wait = new WebDriverWait(RFWebsiteDriver.driver, 10);
		wait.until(ExpectedConditions.numberOfwindowsToBe(expectedNumberOfWindows));
	}

	public void waitForCSCockpitLoadingImageToDisappear(){
		pauseExecutionFor(4000);
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@class='z-loading-indicator']");
		logger.info("Waiting for cscockpit loading image to get disappear");
		for(int i=1;i<=DEFAULT_TIMEOUT_CSCOCKPIT;i++){			
			try{
				if(driver.findElements(locator).size()==1){
					pauseExecutionFor(1000);
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("cscockpit loading image disappears");
					break;
				}			
			}catch(Exception e){
				continue;
			}
		}

	} 
	
	public void waitForCSCockpitLoadingImageToDisappear(int time){
		pauseExecutionFor(3000);
		turnOffImplicitWaits();
		By locator = By.xpath("//div[@class='z-loading-indicator']");
		logger.info("Waiting for cscockpit loading image to get disappear");
		for(int i=1;i<=time;i++){			
			try{
				if(driver.findElements(locator).size()==1){
					logger.info("waiting..");
					continue;
				}else{
					turnOnImplicitWaits();
					logger.info("cscockpit loading image disappears");
					break;
				}			
			}catch(Exception e){
				continue;
			}
		}

	} 

	/**
	 * Checks if element is visible Purpose:
	 * 
	 * @author: 
	 * @date:
	 * @returnType: By locator
	 */
	public boolean isElementVisible(String locator, String value) {
		try{
			waitForElementToBeVisible(By.xpath(String.format(locator, value)),5);
			return driver.findElement(By.xpath(String.format(locator, value))).isDisplayed() ? true : false;
		}catch(Exception e){
			return false;
		}
	}
	
	public void click(String location, String value, String elementToBeClicked) {	
		By locator = By.xpath(String.format(location, value));
		waitForElementToBeVisible(locator,10);
		logger.info(elementToBeClicked+" ready to be clicked");
		try{
			findElement(locator).click();	
			logger.info("CLICKED on "+elementToBeClicked);
		}
		catch(Exception e){
			logger.info("Got exception while clicking on "+elementToBeClicked);
			logger.info("The exception is "+e);
			if(isElementVisible(locator)==true)
				retryingFindClick(locator,elementToBeClicked);
			else{
				logger.info("The element = "+elementToBeClicked+" is invisible at the time of click");
				throw e;
			}
		}
	}	
	
	public boolean isCSCockpitLoadingImageDisplay() {
		By locator = By.xpath("//div[@class='z-loading-indicator']");
		if(isElementVisible(locator)) {
			return true;
		}else {
			return false;
		}
	}
}

