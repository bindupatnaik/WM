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

public class LSDHomeTest extends PulseWebsiteBaseTest{

	 @BeforeMethod()
		public void setUpBeforeMethod() {
			s_assert = new SoftAssert();
			checkAndCloseMoreThanOneWindows();
			driver.get(driver.getURL()+"#/Home");
			if(lsdHomePage.istopNavigationPresent()){
				lsdHomePage.clickLogout();
			}
	 }
	 
	//TC-2560 Vital Signs page navigation
	@Test(alwaysRun=true)
	public void testVitalSignsPageNavigation_2560q(){
		String reportName = "Vital Signs";
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_ACCOUNT_ID_HAVING_ACTIVE_PULSE,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickReportLinks(reportName);
		s_assert.assertTrue(lsdHomePage.isTextPresentAtEstimatedPage(reportName) && lsdHomePage.getCurrentURL().contains("vital"), "Vital signs report page is not present");
		lsdHomePage.navigateToBackPage();
		s_assert.assertTrue(lsdHomePage.isHomePageOfPulsePresent(), "Back button is not navigating to home page");
		s_assert.assertAll();
	}

	//TC-2480 Paid-As Title - C*
	@Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleCStar_2480q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_CSTAR;
		String paidAsTitleFromUI = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2481 Paid-As Title - C
	@Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleC_2481q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_C;
		String paidAsTitleFromUI = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2482 Paid-As Title - EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleEC_2482q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_EC;
		String paidAsTitleFromUI = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2483 Paid-As Title - LI EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleLIEC_2483q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_LIEC;
		String paidAsTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2484 Paid-As Title - LII EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleLIIEC_2484q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_LIIEC;
		String paidAsTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2485 Paid-As Title - LIII EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleLIIIEC_2485q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_LIIIEC;
		String paidAsTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2486 Paid-As Title - LIV EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleLIVEC_2486q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_LIVEC;
		String paidAsTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2487 Paid-As Title - LV EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleLVEC_2487q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_LVEC;
		String paidAsTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2488 Paid-As Title - LV PREMIER EC
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleLVPremierEC_2488q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_LVPREMIEREC;
		String paidAsTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2489 Paid-As Title - LV ELITE EC
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleLVEliteEC_2489q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_ELITE_EC;
		String paidAsTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2490 Paid-As Title - RFx
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testPaidAsTitleRFxEC_2490q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String titleName = "Paid-as Title";
		String title = TestConstants.TITLE_TYPE_ELITE_EC;
		String paidAsTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(paidAsTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+paidAsTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2502 Recognition Title - C*
	@Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleCStar_2502q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_CSTAR;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2503 Recognition Title - C
	@Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleC_2503q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_C;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2504 Recognition Title - EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleEC_2504q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_EC;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2505 Recognition Title - LI EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleLIEC_2505q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIEC;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2506 Recognition Title - LII EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleLIIEC_2506q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIIEC;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2507 Recognition Title - LIII EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleLIIIEC_2507q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIIIEC;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2508 Recognition Title - LIV EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleLIVEC_2508q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIVEC;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2509 Recognition Title - LV EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleLVEC_2509q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LVEC;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2510 Recognition Title - LV PREMIER EC
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleLVPremierEC_2510q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LVPREMIEREC;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2511 Recognition Title - LV ELITE EC
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleLVEliteEC_2511q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_ELITE_EC;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-2512 Recognition Title - RFx
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testRecognitionTitleRFx_2512q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_RFx;
		String recognitionTitleFromUI = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_RECOGNITION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.clickViewTitleHistoryLink();
		recognitionTitleFromUI = lsdHomePage.getRecoginitionTitleFromTitleHistroy();
		s_assert.assertTrue(recognitionTitleFromUI.equalsIgnoreCase(title), "Expected paid-as title title is "+title+" actual on UI is "+recognitionTitleFromUI);
		s_assert.assertAll();
	}

	//TC-3216 Qualification Title - C*
	@Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleCStar_3216q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_CSTAR;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		lsdHomePage.navigateToBackPage();
		s_assert.assertAll();
	}

	//TC-3217 Qualification Title - C
	@Test(alwaysRun=true)
	public void testQualificationTitleC_3217q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_C;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		lsdHomePage.navigateToBackPage();
		s_assert.assertAll();
	}

	//TC-3218 Qualification Title - EC
	@Test(alwaysRun=true)
	public void testQualificationTitleEC_3218q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_EC;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		lsdHomePage.navigateToBackPage();
		s_assert.assertAll();
	}

	//TC-3219 Qualification Title - LI EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleLIEC_3219q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIEC;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		lsdHomePage.navigateToBackPage();
		s_assert.assertAll();
	}

	//TC-3220 Qualification Title - LII EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleLIIEC_3220q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIIEC;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		lsdHomePage.navigateToBackPage();
		s_assert.assertAll();
	}

	//TC-3221 Qualification Title - LIII EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleLIIIEC_3221q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIIIEC;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		s_assert.assertAll();
	}

	//TC-3222 Qualification Title - LIV EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleLIVEC_3222q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIVEC;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		s_assert.assertAll();
	}

	//TC-3223 Qualification Title - LV EC
	@Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleLVEC_3223q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LVEC;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		s_assert.assertAll();
	}

	//TC-3224 Qualification Title - LV PREMIER EC
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleLVPremierEC_3224q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LVPREMIEREC;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		s_assert.assertAll();
	}

	//TC-3225 Qualification Title - LV ELITE EC
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleLVEliteEC_3225q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_ELITE_EC;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		s_assert.assertAll();
	}

	//TC-3226 Qualification Title - RFx
	@Test(enabled=false)//Waiting for data in DB @Test(enabled=false)//Not a part of mini Reg
	public void testQualificationTitleRFx_3225q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_RFx;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_QUALIFICATION_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		qualificationTitleFromOverview = lsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = lsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		lsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = lsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		s_assert.assertAll();
	}


	//TC-3126 Color of SV Value and SV Ring SV<100
	@Test(alwaysRun=true)
	public void testColorOfSVValueAndSVRingLessThan100_3126q(){
		String colorOfSV = "rgb(248, 178, 152)";//"rgb(242, 102, 49)";
		String colorOfSVFromUI = null;

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_LESS_THAN_100_SV,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		//Verify color of text
		colorOfSVFromUI = lsdHomePage.getColorOfNumbersForSV();
		s_assert.assertTrue(colorOfSVFromUI.contains(colorOfSV), "Expected color of number for SV is "+colorOfSV+" but actual on UI is"+colorOfSVFromUI);
		//verify color of ring
		s_assert.assertTrue(lsdHomePage.isColorOfSVRedForLessThan100(), "Expected color of for SV ring is not red");
		s_assert.assertAll();
	}

	//TC-3125 Color of SV Value and SV Ring SV>100
	@Test(alwaysRun=true)
	public void testColorOfSVValueAndSVRingGreaterThan100_3125q(){
		String colorOfSV = "rgb(169, 220, 163)";//"rgb(242, 102, 49)";
		String colorOfSVFromUI = null;

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_GREATER_THAN_100_SV,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		//Verify color of text
		colorOfSVFromUI = lsdHomePage.getColorOfNumbersForSV();
		s_assert.assertTrue(colorOfSVFromUI.contains(colorOfSV), "Expected color of number for SV is "+colorOfSV+" but actual on UI is"+colorOfSVFromUI);
		//verify color of ring
		s_assert.assertTrue(lsdHomePage.isColorOfSVGreenForGreaterThan100(), "Expected color of for SV ring is not red");
		s_assert.assertAll();
	}
	//TC-3103 Verify R+F Progress Link - Level EC III or less.
	@Test(alwaysRun=true)
	public void testVerifyRFLinkForPaidAsTitleLevelECIIIOrLess_3103q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIIIEC;

		System.out.println(title);
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		System.out.println(emailID);
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertFalse(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		lsdHomePage.clickLogout();
		//title type LII EC
		title = TestConstants.TITLE_TYPE_LIIEC;
        System.out.println(title);
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertFalse(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		System.out.println(emailID);
		lsdHomePage.clickLogout();
		//title type LI EC
		title = TestConstants.TITLE_TYPE_LIEC;
        System.out.println(title);
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		System.out.println(emailID);
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertFalse(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		lsdHomePage.clickLogout();
		//title type C
		title = TestConstants.TITLE_TYPE_C;
        System.out.println(title);
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		System.out.println(emailID);
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertFalse(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		s_assert.assertAll();
	}


	//TC-2956 Verify R+F Progress Link - Level EC IV or more.
	@Test(alwaysRun=true)
	public void testVerifyRFLinkForLevelECIVOrMore_2956q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String title = TestConstants.TITLE_TYPE_LIVEC;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is not present for title type "+title);
		lsdHomePage.clickLogout();
		
		//title type LV EC
		title = TestConstants.TITLE_TYPE_LVEC;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is not present for title type "+title);
		lsdHomePage.clickLogout();
		//title type LV Premier EC
		title = TestConstants.TITLE_TYPE_LVPREMIEREC;
       	randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is not present for title type "+title);
		lsdHomePage.clickLogout();
		//title type ELITE EC
		title = TestConstants.TITLE_TYPE_ELITE_EC;
        randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is not present for title type "+title);
		lsdHomePage.clickLogout();
		//title type RFx
		title = TestConstants.TITLE_TYPE_RFx;
        System.out.println(title);
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is not present for title type "+title);
		s_assert.assertAll();
	}



	//TC-2535 Enrollment Sponsor
	@Test(alwaysRun=true)
	public void testEnrollmentSponsor_2535q(){
		List<Map<String, Object>> randomConsultantList;
		String sponsorNameFromDB = null;
		String sponsorNameFromUI = null;

		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_EMAIL_ID_HAVING_ENROLLMENT_SPONSOR_PULSE,commDBName,Commisions_DB_IP);
		String emailID = String.valueOf(getValueFromQueryResult(randomConsultantList, "EmailAddress"));
		sponsorNameFromDB = String.valueOf(getValueFromQueryResult(randomConsultantList, "EnrollmentSponsorName"));
		lsdLoginPage.loginToPulse(emailID, password);
		sponsorNameFromUI = lsdHomePage.getEnrollmentSponsorName();
		s_assert.assertTrue(sponsorNameFromUI.contains(sponsorNameFromDB), "Expected sponsor name is "+sponsorNameFromUI+" but actual on UI is "+sponsorNameFromUI);
		s_assert.assertAll();
	}

	//TC-2435 Upcoming Orders -  Autoships
	@Test(enabled=false)//Not a part of mini Reg
	public void testUpcomingOrdersAutoships_2435q(){
		boolean isUserEnrolledInCRP = false;
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String label_OrderStatus = "Order Status";
		String label_OrderType = "Order Type";
		String label_OrderNumber = "Order Number";
		String label_QV = "QV";

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_ACCOUNT_ID_HAVING_PENDING_AUTOSHIP_ORDERS_PULSE,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		isUserEnrolledInCRP = lsdHomePage.isUserEnrolledInCRP();
		if(isUserEnrolledInCRP){
			s_assert.assertTrue(lsdHomePage.isPersonalOrderSectionValuePresent(label_OrderStatus), "Expected "+label_OrderStatus+"'s value is empty");
			s_assert.assertTrue(lsdHomePage.isPersonalOrderSectionValuePresent(label_OrderType), "Expected "+label_OrderType+"'s value is empty");
			s_assert.assertTrue(lsdHomePage.isPersonalOrderSectionValuePresent(label_OrderNumber), "Expected "+label_OrderNumber+"'s value is empty");
			s_assert.assertTrue(lsdHomePage.isPersonalOrderSectionValuePresent(label_QV), "Expected "+label_QV+"'s value is empty");
		}else{
			s_assert.assertTrue(lsdHomePage.isNoUpcomingOrderPresent(), "No upcoming order text is present");
		}
		s_assert.assertAll();
	}



	//TC-2469 Color of SV Value and SV Ring SV=0
	@Test(alwaysRun=true)
	public void testColorOfSVValueAndSVRingForEqualsTo0_2469q(){
		String colorOfSV = "rgb(248, 178, 152)";
		String colorOfSVFromUI = null;

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_EQUALS_TO_0_SV,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		//Verify color of text
		colorOfSVFromUI = lsdHomePage.getColorOfNumbersForSV();
		s_assert.assertTrue(colorOfSVFromUI.contains(colorOfSV), "Expected color of number for SV is "+colorOfSV+" but actual on UI is"+colorOfSVFromUI);
		//verify color of ring
		s_assert.assertTrue(lsdHomePage.isColorOfSVRedForLessThan100(), "Expected color of for SV ring is not red");
		s_assert.assertAll();
	}

	//TC-2470 Color of PSQV Value and PSQV Ring- PSQV=0
	@Test(alwaysRun=true)
	public void testColorOfPSQVValueAndPSQVRingEqualsTo0_2470q(){
		String colorOfPSQV = "rgb(242, 102, 49)";
		String colorOfPSQVFromUI = null;

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_EQUALS_TO_0_PSQV,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		//Verify color of text
		colorOfPSQVFromUI = lsdHomePage.getColorOfNumbersForPSQV();
		s_assert.assertTrue(colorOfPSQVFromUI.contains(colorOfPSQV), "Expected color of number for PSQV is "+colorOfPSQV+" but actual on UI is"+colorOfPSQVFromUI);
		//verify color of ring
		s_assert.assertTrue(lsdHomePage.isColorOfPSQVRedForLessThan600(), "Expected color of for PSQV ring is not red");
		s_assert.assertAll();
	}

	//TC-3127 Color of PSQV Value and PSQV Ring - PSQV<600
	@Test(alwaysRun=true)
	public void testColorOfPSQVValueAndPSQVRingLessThan600_3127q(){
		String colorOfPSQV = "rgb(242, 102, 49)";
		String colorOfPSQVFromUI = null;

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_LESS_THAN_600_PSQV,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		//Verify color of text
		colorOfPSQVFromUI = lsdHomePage.getColorOfNumbersForPSQV();
		s_assert.assertTrue(colorOfPSQVFromUI.contains(colorOfPSQV), "Expected color of number for PSQV is "+colorOfPSQV+" but actual on UI is"+colorOfPSQVFromUI);
		//verify color of ring
		s_assert.assertTrue(lsdHomePage.isColorOfPSQVRedForLessThan600(), "Expected color of for PSQV ring is not red");
		s_assert.assertAll();
	}

	//TC-3128 Color of PSQV Value and PSQV Ring - PSQV>600
	@Test(alwaysRun=true)
	public void testColorOfPSQVValueAndPSQVRingGreaterThan600_3128q(){
		String colorOfPSQV = "rgb(84, 185, 72)";
		String colorOfPSQVFromUI = null;

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_GREATER_THAN_600_PSQV,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		//Verify color of text
		colorOfPSQVFromUI = lsdHomePage.getColorOfNumbersForPSQV();
		s_assert.assertTrue(colorOfPSQVFromUI.contains(colorOfPSQV), "Expected color of number for PSQV is "+colorOfPSQV+" but actual on UI is"+colorOfPSQVFromUI);
		//verify color of ring
		s_assert.assertTrue(lsdHomePage.isColorOfPSQVGreenForGreaterThan600(), "Expected color of for SV ring is not green");
		s_assert.assertAll();
	}

	//qTest ID-3351 Processed PC autoships
	//qTest ID-3015 Processed PC autoships
	@Test(alwaysRun=true)
	public void testProcessedPCAutoships_3015q_3351q(){
		String countOfPCOrderFromDB = null; 
		String countOfPCOrderFromUI = null;
		String subLinkCustomers = "Customers";
		String totalNumberOfOrders = null;
		String totalNumberOfProcessedOrders = null;

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String consultantId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_COUNT_AND_ACCOUNTID_OF_PROCESSED_PC_AUTOSHIPS_PULSE,commDBName,Commisions_DB_IP);
		consultantId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "ConsultantID"));
		countOfPCOrderFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "Ocount"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,consultantId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickPCOrderForThisMonth();
		countOfPCOrderFromUI = lsdCustomerPage.getCountOfPCAutoshipOrdersForThisMonthFromHeader();
		totalNumberOfOrders =lsdCustomerPage.getTotalNumberOrders();
		totalNumberOfProcessedOrders = lsdCustomerPage.getTotalNumberOfProcessedAutoshipOrders();
		s_assert.assertTrue(countOfPCOrderFromDB.equalsIgnoreCase(countOfPCOrderFromUI), "Expected count of orders are"+countOfPCOrderFromDB+"but actual on UI is "+countOfPCOrderFromUI);
		s_assert.assertTrue(totalNumberOfProcessedOrders.equalsIgnoreCase(totalNumberOfOrders), "Expected count of total processed orders"+totalNumberOfProcessedOrders+" but actual on UI "+totalNumberOfOrders);
		s_assert.assertAll();
	}


	//qTest ID-2988 Deactivated PC card Details
	@Test(alwaysRun=true)
	public void testDeactivatedPCCardDetails_2988q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String consultantId = null;
		String firstName = null;
		String lastName = null;
		String enrollmentDate = null;
		String softTerminationDate = null;
		String QV = null;
		String fullNameFromUI = null;
		String enrollmentDateFromUI = null;
		String softTerminationDateFromUI = null;
		String QVFromUI = null;
		String enrollYearFromUI = null;
		String terminateYearFromUI = null;
		String subLinkCustomers = "Customers";

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_DEACTIVATED_PC_ACCOUNT_DETAILS_WITH_CONSULTANT_ID_PULSE,commDBName,Commisions_DB_IP);
		consultantId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "ConsultantID"));
		firstName = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "FirstName"));
		lastName = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "LastName"));
		enrollmentDate = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "EnrollmentDate"));
		softTerminationDate = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "SoftTerminationDate"));
		QV = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "Qv"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,consultantId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickSubCategoryOnCustomerPageUnderViewOurCustomer("3");
		fullNameFromUI = lsdCustomerPage.getNameOfFirstDeactivatePC();
		enrollmentDateFromUI = lsdCustomerPage.getEnrolledDateOfFirstDeactivatePC();
		softTerminationDateFromUI = lsdCustomerPage.getCancelledDateOfFirstDeactivatePC();
		QVFromUI = lsdCustomerPage.getQVOfFirstDeactivatePC();
		enrollYearFromUI = lsdCustomerPage.getYearFromDate(enrollmentDateFromUI);
		terminateYearFromUI = lsdCustomerPage.getYearFromDate(softTerminationDateFromUI);
		s_assert.assertTrue(fullNameFromUI.contains(firstName), "Expected first name is "+firstName+" but actual on UI is "+fullNameFromUI);
		s_assert.assertTrue(fullNameFromUI.contains(lastName), "Expected last name is "+lastName+" but actual on UI is "+fullNameFromUI);
		s_assert.assertTrue(enrollmentDate.contains(enrollYearFromUI), "Expected enrollment date is "+enrollmentDate+" but actual on UI is "+enrollYearFromUI);
		s_assert.assertTrue(softTerminationDate.contains(terminateYearFromUI), "Expected termination date is "+softTerminationDate+" but actual on UI is "+terminateYearFromUI);
		QVFromUI = lsdCustomerPage.getQVValue(QVFromUI);
		s_assert.assertTrue(QV.contains(QVFromUI), "Expected QV is "+QV+" but actual on UI is "+QVFromUI);
		s_assert.assertAll();
	}
	//TC-2420 Consultant Overview Details
	@Test(alwaysRun=true)
	public void testConsultantOverviewDetails_2420q(){
		String initialNameFromUI = null;
		String initialNameFromDB = null;
		String firstName = null;
		String lastName = null;
		String userName=null;
		List<Map<String, Object>> randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_RANDOM_CONSULTANT,commDBName,Commisions_DB_IP);
		userName = getUserNmae(randomConsultantList);
		System.out.println(userName);
		firstName = (String) getValueFromQueryResult(randomConsultantList, "FirstName");
		lastName = (String) getValueFromQueryResult(randomConsultantList, "LastName");
		System.out.println("********* FN="+firstName);
		System.out.println("********* LN="+lastName);
		lsdLoginPage.loginToPulse(userName, password);
		s_assert.assertTrue(lsdHomePage.isFullNamePresentInCaps(), "Fullname name is not present in capital letters");
		s_assert.assertTrue(lsdHomePage.isQualificationtitlePresentInOverviewSection(), "Qualification title is not present");
		s_assert.assertTrue(lsdHomePage.isEnrollmentDatePresentInOverviewSection(), "Enrollded date is not present");
		s_assert.assertTrue(lsdHomePage.isInitialNameContainerPresent(), "Two initial icon is not present");
		initialNameFromUI = lsdHomePage.getInitialName();
		initialNameFromDB = lsdHomePage.getFirstLetterFromString(firstName)+lsdHomePage.getFirstLetterFromString(lastName);
		System.out.println("********* INI name from UI="+initialNameFromUI);
		System.out.println("********* INI name from DB="+initialNameFromDB);
		s_assert.assertTrue(lsdHomePage.isInitialNameContainerPresent(),"Initial name container is not visible on UI");
		s_assert.assertTrue(initialNameFromDB.equalsIgnoreCase(initialNameFromUI),"Expected First name is"+initialNameFromDB+" but actual on UI is "+initialNameFromUI);
		s_assert.assertAll();
	}


	//TC-2536 PCs with Orders  value
	@Test(alwaysRun=true)
	public void testPCWithOrdersValue_2536q(){
		//lsdHomePage.clickLogout();
		lsdLoginPage.loginToPulse(TestConstants.EMAIL_ID_CONSULTANT_PULSE, password);
		String pcOrderValueFromDB = null;
		String pcOrderValueFromUI = null;
		List<Map<String, Object>> randomConsultantPCOrderList;
		randomConsultantPCOrderList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_COUNT_OF_PC_CUSTOMERS_PULSE,accountId),commDBName,Commisions_DB_IP);
		pcOrderValueFromDB= String.valueOf( getValueFromQueryResult(randomConsultantPCOrderList, "Totol PC Accounts Count"));
		pcOrderValueFromUI = lsdHomePage.getCountOfPCCustomerWithOrders();
		s_assert.assertTrue(pcOrderValueFromDB.equalsIgnoreCase(pcOrderValueFromUI), "Expected count of PC with orders "+pcOrderValueFromDB+" but actual on UI is "+pcOrderValueFromUI);
		s_assert.assertAll();
	}

	//qTest ID-2521 CRP - Never enrolled
	//qTest ID-2522 CRP - Autoship processed in past for this month
	@Test(alwaysRun=true)
	public void testCRPAutoshipProcessedInPastForThismonth_2522q(){

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_ACCOUNT_ID_HAVING_PENDING_AUTOSHIP_ORDERS_CRP,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		if(lsdHomePage.isUserEnrolledInPulse())
			s_assert.assertTrue(lsdHomePage.isUserEnrolledInCRP(), "User is not enrolled in CRP for this month");
		else
			s_assert.assertFalse(lsdHomePage.isUserEnrolledInCRP(), "User is enrolled in CRP");
		s_assert.assertAll();
	}

	//qTest ID-2529 Pulse Pro - Never enrolled
	//qTest ID-2530 Pulse Pro - Autoship processed in past for this month
	@Test(alwaysRun=true)
	public void testPulseProAutoshipProcessedInPastForThisMonth_2530q_2529q(){

		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		String pulseStatusFromUI = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_ACCOUNT_ID_HAVING_PENDING_AUTOSHIP_ORDERS_PULSE,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		pulseStatusFromUI = lsdHomePage.getPulseProInfoFromKeepInMindSection();
		if(lsdHomePage.isUserEnrolledInPulse())
			s_assert.assertTrue(pulseStatusFromUI.toLowerCase().contains("yes"), "User is enrolled in pulse but displayed as No");
		else
			s_assert.assertTrue(pulseStatusFromUI.toLowerCase().contains("no"), "User is enrolled in pulse but displayed as yes");
		s_assert.assertAll();
	}

	//TC-3104 Verify R+F Progress Link - Level EC IV or more.
	@Test(enabled=false)//Not having premier,Elite and RFX paid as title users.
	public void testVerifyRFLinkForPaidAsTitleLevelECIVOrMore_3104q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		//title type LIV EC
		String title = TestConstants.TITLE_TYPE_LIVEC;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		lsdHomePage.clickTrackMyProgressLink();
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressPagePresent(), "Track my progress progress or program card page is not present for title type "+title);
		//title type LV EC
		title = TestConstants.TITLE_TYPE_LVEC;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		lsdHomePage.clickTrackMyProgressLink();
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressPagePresent(), "Track my progress progress or program card page is not present for title type "+title);

		//title type Premier EC
		title = TestConstants.TITLE_TYPE_LVPREMIEREC;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		lsdHomePage.clickTrackMyProgressLink();
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressPagePresent(), "Track my progress progress or program card page is not present for title type "+title);
		s_assert.assertAll();

		//title type Elite EC
		title = TestConstants.TITLE_TYPE_ELITE_EC;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		lsdHomePage.clickTrackMyProgressLink();
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressPagePresent(), "Track my progress progress or program card page is not present for title type "+title);	
		//title type RFX EC
		title = TestConstants.TITLE_TYPE_RFx;

		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACCOUNT_ID_FOR_DIFFERENT_PAID_AS_TITLE_COMMISSION,title),commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+title);
		lsdHomePage.clickTrackMyProgressLink();
		s_assert.assertTrue(lsdHomePage.isTrackMyProgramProgressPagePresent(), "Track my progress progress or program card page is not present for title type "+title);
		s_assert.assertAll();
	}
	
	//TC-2563 Solution Tool report link
	@Test(alwaysRun=true)
	public void testSolutionToolReportLink_2563q(){

		String reportName = "Solution";
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommi;
		String accountId = null;
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_ACCOUNT_ID_HAVING_ACTIVE_PULSE,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement			(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountId),RFO_DBName,RFO_DB_IP);
		String emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.loginToPulse(emailID, password);

		String currentURL = null;
		String parentWindowHandle = null;
		parentWindowHandle = lsdHomePage.getParentWindowHandle();
		lsdHomePage.clickReportLinks(reportName, parentWindowHandle);
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(reportName.toLowerCase()), "Expected url should contains "+reportName+" actual on UI is "+currentURL);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();

	}

}