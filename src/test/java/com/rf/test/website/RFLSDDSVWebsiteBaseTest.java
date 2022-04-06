package com.rf.test.website;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.pages.website.LSD.dsv.DSVLSDHomePage;
import com.rf.pages.website.LSD.dsv.DSVLSDLoginPage;
import com.rf.pages.website.LSD.dsv.DSVLSDOrderPage;
import com.rf.test.base.RFBaseTest;

public class RFLSDDSVWebsiteBaseTest extends RFBaseTest {
	StringBuilder verificationErrors = new StringBuilder();
	

	protected DSVLSDLoginPage dsvLsdLoginPage;
	protected DSVLSDHomePage dsvLsdHomePage;
	protected DSVLSDOrderPage dsvLsdOrderPage;
	protected Actions actions;
	protected String country=null;

	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(RFLSDDSVWebsiteBaseTest.class.getName());

	private static final By LOGIN_BTN = By.id("button-login");

	public RFLSDDSVWebsiteBaseTest() {
		dsvLsdLoginPage = new DSVLSDLoginPage(driver);
		dsvLsdHomePage = new DSVLSDHomePage(driver);
		dsvLsdOrderPage = new DSVLSDOrderPage(driver);
	}

	/**
	 * @throws Exception
	 *             setup function loads the driver and environment and baseUrl
	 *             for the tests
	 */
	@BeforeSuite(alwaysRun=true)
	public void setUp() throws Exception {
		driver.loadApplication(); 
		driver.get(driver.getURL());
		logger.info("Application loaded");    
	}
	public void checkAndCloseMoreThanOneWindows(){
		Set<String> allWindows = driver.getWindowHandles();
		String currentWin = driver.getWindowHandle();
		for(String win:allWindows){
			if(win.equals(currentWin)==false && allWindows.size()>1){
				driver.switchTo().window(win);
				driver.close();
				driver.switchTo().window(currentWin);
				allWindows = driver.getWindowHandles();       
			} 
		}

	}

	public void clickRFLogo(){
		driver.click(By.xpath("//div[@id='rf-nav-wrapper']/descendant::img[2]"), "R+F logo");
	}
	@BeforeMethod
	public void initiateSoftAssertObject(){
		s_assert = new SoftAssert();
	}
	@BeforeClass(alwaysRun=true)
	public void beforeClass(){
		dsvLsdLoginPage.enterUsername(TestConstants.DSV_NEW_PULSE_CONSULTANT_EMAILID);
		dsvLsdLoginPage.enterPassword(TestConstants.DSV_NEW_PULSE_CONSULTANT_PASSWORD);
		dsvLsdLoginPage.clickLoginBtn();
		driver.pauseExecutionFor(3000);
		checkAndCloseMoreThanOneWindows();
		clickRFLogo();
	}

	public boolean isLoginBtnPresent(){
		return driver.isElementPresent(LOGIN_BTN);

	}
	
	@AfterClass
	public void afterClass(){
		driver.waitForLSDLoaderAnimationImageToDisappear();
		dsvLsdHomePage.clickLogout();
	}

	@AfterMethod
	public void tearDownAfterMethod(){
		checkAndCloseMoreThanOneWindows();
		clickRFLogo();
		//		driver.manage().deleteAllCookies();
	}

	/**
	 * @throws Exception
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		new HtmlLogger().createHtmlLogFile();                 
		driver.quit();
	}

	// This assertion for the UI Texts
	public void assertTrue(String message, boolean condition) {
		if (!condition) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");                                              
			Assert.fail(message);                                                      
		}

	}


	//This assertion for the Database validations
	public boolean assertTrueDB(String message, boolean condition,String dbName) {
		if (!condition) {
			logger.info("[DATABASE ASSERTION FAILURE -  "+dbName+" ----------- " +message + "]");
			if(!dbName.equals(driver.getDBNameRFL())){
				Assert.fail(message);
			}
			else{
				return false;
			}
		}
		return true;
	}

	public void assertTrue(boolean condition, String message) {

		if (!condition) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public void assertEquals(Object obj1, Object obj2, String message) {
		if (!obj1.equals(obj2)) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public void assertEquals(String message, int num1,int num2) {
		if (!(num1==num2)) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public void assertFalse(boolean condition, String message) {

		if (condition) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	public void assertFalse(String message, boolean condition) {

		if (condition) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

	//            public void assertTrue(boolean condition) {
	//
	//                            try {
	//                                            assertTrue(condition);
	//
	//                            } catch (Exception e) {
	//                                            logger.trace(e.getMessage());
	//                            }
	//            }

	public void assertEquals(String message, float num1,float num2) {

		if (!(num1==num2)) {
			logger.info("[FUNCTIONAL FAILURE - ASSERTION ERROR ----------- "
					+ message + "]");
			Assert.fail(message);
		}
	}

}

