package com.rf.test.website.lsdDataCreation;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.test.website.storeFront.hybris.billingAndShipping.RFBillingShippingPageWebsiteBaseTest;

public class DataCreationTest extends RFBillingShippingPageWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(DataCreationTest.class.getName());

	private StoreFrontHomePage storeFrontHomePage;
	private String RFO_DB = driver.getDBNameRFO();
	private String kitName = null;
	private String regimenName = null;
	private String enrollmentType = null;
//address variables	
	private String addressLine1 = null;
	private String city = null;
	private String postalCode = null;
	private String phoneNumber = null;
	private String country = null;
	
	private String sponsorIDOfTopLevelConsultant = null;
	private String firstConsultantEmailIDOfL1 = null;
	private int randomNum = CommonUtils.getRandomNum(10000, 1000000);
	private String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
	String cardNumber = TestConstants.CARD_NUMBER;
	private String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;

	@BeforeMethod
	public void setUp() {
	//navigate to store front url
		navigateToStoreFrontBaseURL();
	}


	/**
	 * LEVEL 0 FN: Mary LN: McQueen+random_number enrollment(TOP LEVEL)
	 */
	@Test(enabled=true,priority=1)
	public void testLSDTopLevel_0_Consultant() throws InterruptedException{
	//data initialization for creating Mary McQueen
		String nameOnCard = TestConstants.LAST_NAME_FOR_TOP_LEVEL_CONSULTANT+randomNum;
		String productQuantity = "5";
		List<Map<String, Object>> sponsorList =  null;
		storeFrontHomePage = new StoreFrontHomePage(driver);
		country = driver.getCountry();
		enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
		kitName = TestConstants.KIT_NAME_EXPRESS;			 
		addressLine1 = TestConstants.ADDRESS_LINE_1_CA;
		city = TestConstants.CITY_CA;
		postalCode = TestConstants.POSTAL_CODE_CA;
		phoneNumber = TestConstants.PHONE_NUMBER_CA;
		

		String topLevelUserEmailID = TestConstants.LAST_NAME_FOR_TOP_LEVEL_CONSULTANT+randomNum+TestConstants.EMAIL_ADDRESS_SUFFIX;
		storeFrontHomePage.clickOnOurBusinessLink();
		storeFrontHomePage.clickOnOurEnrollNowLink();
		String firstName = TestConstants.FIRST_NAME_FOR_TOP_LEVEL_CONSULTANT;
		String lastName = TestConstants.LAST_NAME_FOR_TOP_LEVEL_CONSULTANT+randomNum;

	//create consultant
		String consultantEmailID = 	storeFrontHomePage.createConsultant("test", kitName, regimenName, enrollmentType, firstName, lastName, topLevelUserEmailID, password, addressLine1, city, postalCode, phoneNumber, cardNumber,nameOnCard, socialInsuranceNumber, productQuantity );
		Assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible for top level consultant");
		logger.info("=================Top level consultant email : "+consultantEmailID+"=========================");
		System.out.println("=================Top level consultant email : "+consultantEmailID+"=========================");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		logout();

		//get Account number of top level consultant
		sponsorList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_NUMBER_FROM_EMAIL_ADDRESS,consultantEmailID),RFO_DB);
		logger.info("Sponsor List: " +sponsorList);
		System.out.println("Sponsor List: " +sponsorList);
		
		sponsorIDOfTopLevelConsultant = String.valueOf(getValueFromQueryResult(sponsorList, "AccountNumber"));
		logger.info("SponsorID: " +sponsorIDOfTopLevelConsultant);
		System.out.println("Sponsor ID: " +sponsorList);
		logger.info("****************************************************************************************************************");


	}	
}
	