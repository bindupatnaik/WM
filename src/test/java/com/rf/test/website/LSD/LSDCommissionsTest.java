package com.rf.test.website.LSD;

import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.QueryUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.PulseWebsiteBaseTest;
import com.rf.test.website.RFLSDWebsiteBaseTest;

public class LSDCommissionsTest extends PulseWebsiteBaseTest{

	@BeforeMethod()
	public void setUpBeforeMethod() {
		System.out.println("*************SETUP");
		s_assert = new SoftAssert();
	}

	//qTest ID-3017 Pending PC autoships
	@Test(alwaysRun=true)
	public void testPendingPCAutoships_3017q(){
		String countOfPCOrderFromDB = null; 
		String countOfPCOrderFromUI = null;
		String subLinkCustomers = "Customers";
		List<Map<String, Object>> randomConsultantListCommi;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_COUNT_OF_PENDING_PC_AUTOSHIPS_PULSE,accountId),RFO_DBName,RFO_DB_IP);
		System.out.println(randomConsultantListCommi);
		countOfPCOrderFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "PCount"));
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickPCOrderForNextMonth();
		countOfPCOrderFromUI = lsdCustomerPage.getCountOfPCOrdersForNextMonthFromHeader();
		s_assert.assertTrue(countOfPCOrderFromDB.equalsIgnoreCase(countOfPCOrderFromUI), "Expected count of orders pcs are"+countOfPCOrderFromDB+"but actual on UI is "+countOfPCOrderFromUI);
		s_assert.assertAll();
	}	

	//TC-2476 EC Legs value
	@Test(alwaysRun=true)
	public void testECLegsValue_2476q(){
		String expectedECLegs = "null";
		String actualECLegs = null;
		expectedECLegs = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "EClegs"));
		actualECLegs = lsdHomePage.getECLegsValue();

		s_assert.assertTrue(lsdHomePage.isECLegsAndTitlesSectionPresent(),"EC Legs and Tiles section is not present");
		s_assert.assertTrue(expectedECLegs.contains(actualECLegs),"EC Legs Value is not found as Expected. Expected : "+expectedECLegs+" .  Actual : "+actualECLegs);

		s_assert.assertAll();
	}


	//TC-2542 L1-L6 Qualifying Volume value
	@Test(alwaysRun=true)
	public void testL1L6QualifyingVolumeValue_2542q(){
		String L1L6VolumeFromDB = null;
		String L1L6VolumeFromUI = null;
		L1L6VolumeFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "L1L6"));
		L1L6VolumeFromUI = lsdHomePage.getL1L6QualifyingVolume();
		System.out.println("FRom DB" +L1L6VolumeFromDB );
		System.out.println("FRom UI "+L1L6VolumeFromUI);
		s_assert.assertTrue(L1L6VolumeFromDB.contains(L1L6VolumeFromUI), "Expected L1-L6 qualifying volume is"+L1L6VolumeFromDB+" actual on UI is "+L1L6VolumeFromUI);
		s_assert.assertAll();
	}

	//TC-2395 PSQV value
	@Test(alwaysRun=true)
	public void testPSQVValue_2395q(){
		String PSQVFromDB = null;
		String PSQVFromUI = null;
		PSQVFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "PSQV"));
		PSQVFromUI = lsdHomePage.getPSQVValue();
		System.out.println(PSQVFromDB);
		System.out.println(PSQVFromUI);
		s_assert.assertTrue(PSQVFromDB.contains(PSQVFromUI), "Expected PSQV is "+PSQVFromDB+" but actual on UI is "+PSQVFromUI);
		s_assert.assertAll();
	}

	//TC-2394 SV value
	@Test(alwaysRun=true)
	public void testSVValue_2394q(){
		String SVFromDB = null;
		String SVFromUI = null;
		SVFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "SV"));
		SVFromUI = lsdHomePage.getSVValue();
		System.out.println(SVFromDB);
		System.out.println(SVFromUI);
		s_assert.assertTrue(SVFromDB.contains(SVFromUI), "Expected SV value is "+SVFromDB+" but actual on UI is "+SVFromUI);
		s_assert.assertAll();
	}

	//TC-2419 L1-L6 Qualifying Volume value
	@Test(alwaysRun=true)
	public void testL1L6QualifyingVolumeValue_2419q(){
		String L1L6VolumeFromDB = null;
		String L1L6VolumeFromUI = null;
		L1L6VolumeFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "L1L6"));
		L1L6VolumeFromUI = lsdHomePage.getL1L6QualifyingVolume();
		System.out.println(L1L6VolumeFromDB);
		System.out.println(L1L6VolumeFromUI);
		s_assert.assertTrue(L1L6VolumeFromDB.contains(L1L6VolumeFromUI), "Expected L1-L6 qualifying volume is"+L1L6VolumeFromDB+" actual on UI is "+L1L6VolumeFromUI);
		s_assert.assertAll();
	}

	//TC-2418 L1+L2 Qualifying Volume value
	@Test(alwaysRun=true)
	public void testL1L2QualifyingVolumeValue_2418q(){
		String L1L2VolumeFromDB = null;
		String L1L2VolumeFromUI = null;
		L1L2VolumeFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "L1L2"));
		L1L2VolumeFromUI = lsdHomePage.getL1L2QualifyingVolume();
		System.out.println(L1L2VolumeFromDB);
		System.out.println(L1L2VolumeFromUI);
		s_assert.assertTrue(L1L2VolumeFromDB.contains(L1L2VolumeFromUI), "Expected L1+L2 qualifying volume is"+L1L2VolumeFromDB+" actual on UI is "+L1L2VolumeFromUI);
		s_assert.assertAll();
	}

	//TC-2541 L1+L2 Qualifying Volume value
	@Test(alwaysRun=true)
	public void testL1L2QualifyingVolumeValue_2541q(){
		String L1L2VolumeFromDB = null;
		String L1L2VolumeFromUI = null;
		L1L2VolumeFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "L1L2"));
		L1L2VolumeFromUI = lsdHomePage.getL1L2QualifyingVolume();
		System.out.println(L1L2VolumeFromDB);
		System.out.println(L1L2VolumeFromUI);
		s_assert.assertTrue(L1L2VolumeFromDB.contains(L1L2VolumeFromUI), "Expected L1+L2 qualifying volume is"+L1L2VolumeFromDB+" actual on UI is "+L1L2VolumeFromUI);
		s_assert.assertAll();
	}

	//TC-2634 SV value
	@Test(alwaysRun=true)
	public void testSVValueFromCard_2634q(){
		String svValueFromDB = null;
		String svValueFromUI = null;
		svValueFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "SV"));
		svValueFromUI = lsdHomePage.getSVValueFromCardDetails();
		s_assert.assertTrue(svValueFromDB.contains(svValueFromUI.replaceAll(",","")), "Expected SV value is"+svValueFromDB+" actual on UI is "+svValueFromUI);
		s_assert.assertAll();
	}

	//TC-2635 PSQV value
	@Test(alwaysRun=true)
	public void testPSQVValueFromCard_2635q(){
		String psqvValueFromDB = null;
		String psqvValueFromUI = null;
		psqvValueFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "PSQV"));
		psqvValueFromUI = lsdHomePage.getPSQVValueFromCardDetails();
		System.out.println(psqvValueFromDB);
		System.out.println(psqvValueFromUI);
		s_assert.assertTrue(psqvValueFromDB.contains(psqvValueFromUI), "Expected PSQV value is"+psqvValueFromDB+" actual on UI is "+psqvValueFromUI);
		s_assert.assertAll();
	}


}