
package com.rf.test.website.storeFront.CommissionsUI;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.ExcelReader;
import com.rf.core.utils.HtmlLogger;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.pages.website.heirloom.StoreFrontHeirloomConsultantPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomHomePage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomPCUserPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomPulsePage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomRCUserPage;
import com.rf.pages.website.nscore.NSCore3HomePage;
import com.rf.pages.website.nscore.NSCore4HomePage;
import com.rf.pages.website.nscore.NSCore4OrdersTabPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomAccountDetailsPage;
import com.rf.pages.website.heirloom.StoreFrontHeirloomBillingAndShippingProfilePage;
import com.rf.test.base.RFBaseTest;

/**
 * @author ShubhamMathur
 *
 */
public class CommissionsUIWebsiteBaseTest extends RFBaseTest {
	StringBuilder verificationErrors = new StringBuilder();
	protected RFWebsiteDriver driver = new RFWebsiteDriver(propertyFile);
	private static final Logger logger = LogManager
			.getLogger(CommissionsUIWebsiteBaseTest.class.getName());

	public CommissionsUIWebsiteBaseTest() {
		}

	/**
	 * @throws Exception
	 *             setup function loads the driver and environment and baseUrl
	 *             for the tests
	 */
	@BeforeSuite(alwaysRun=true)
	public void setUp() throws Exception {
		logger.info("In the Before suite..");
		driver.loadApplication();		
		logger.info("Application loaded");				
		driver.setDBConnectionString();
		logger.info("out of Before suite..");
	}

	public void setClipboardData(String string) {
		StringSelection stringSelection = new 	StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

}
