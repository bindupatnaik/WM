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
import com.rf.test.website.RFLSDWebsiteBaseTest;

public class LSDTests extends RFLSDWebsiteBaseTest{

	// TC-2626 Login components
	// TC-2545 Enrollment Date
	@Test(alwaysRun=true)
	public void testEnrollmentDate_2545q_2626q(){
		String enrolledDateFromDB = null;
		String convertedDateInDBFromat = null;
		s_assert.assertTrue(lsdHomePage.isHomePageOfPulsePresent(), "User is not able to logged in successfully");
		String enrolledDateFromUI = lsdHomePage.getEnrolledDateFromOverviewSection();
		List<Map<String, Object>> randomConsultantDetailsList;
		randomConsultantDetailsList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_AND_ACCOUNT_SPONSOR_DETAILS_COMMISSION,accountId),commDBName,Commisions_DB_IP);
		enrolledDateFromDB = String.valueOf( getValueFromQueryResult(randomConsultantDetailsList, "EnrollmentDate"));
		convertedDateInDBFromat = lsdHomePage.convertDateIntoDBFormat(enrolledDateFromUI);
		s_assert.assertTrue(enrolledDateFromDB.contains(convertedDateInDBFromat),"Expected Qualification title is"+enrolledDateFromDB+" but actual on UI is "+convertedDateInDBFromat);
		s_assert.assertAll();
	}

	// TC-2546 Greeting and First Name
	@Test(alwaysRun=true)
	public void testGreetingAndFirstName_2546q(){
		//Verify section in overview.
		String firstNameFromUI = null;
		String firstNameFromDB = (String) getValueFromQueryResult(randomConsultantList, "FirstName");
		firstNameFromUI = lsdHomePage.getFirstNameFromOverviewSection();
		s_assert.assertTrue(lsdHomePage.isHelloTextPresentInOverviewSection(),"Hello text not present in overview section");
		s_assert.assertTrue(firstNameFromDB.equalsIgnoreCase(firstNameFromUI),"Expected First name is"+firstNameFromDB+" but actual on UI is "+firstNameFromUI);
		s_assert.assertTrue(lsdHomePage.isFirstNamePresentInUpperCase(), "First Name is not in upper case");
		s_assert.assertAll();
	}

	// TC-2547 2-Initial icon
	@Test(alwaysRun=true)
	public void test2InitialIcon_2547q(){
		//Verify section in overview.
		String initialNameFromUI = null;
		String initialNameFromDB = null;
		String firstName = null;
		String lastName = null;
		initialNameFromUI = lsdHomePage.getInitialName();
		firstName = (String) getValueFromQueryResult(randomConsultantList, "FirstName");
		lastName = (String) getValueFromQueryResult(randomConsultantList, "LastName");
		initialNameFromDB = lsdHomePage.getFirstLetterFromString(firstName)+lsdHomePage.getFirstLetterFromString(lastName);
		s_assert.assertTrue(lsdHomePage.isInitialNameContainerPresent(),"Initial name container is not visible on UI");
		s_assert.assertTrue(initialNameFromDB.equalsIgnoreCase(initialNameFromUI),"Expected First name is"+initialNameFromDB+" but actual on UI is "+initialNameFromUI);
		s_assert.assertAll();
	}

	// TC-2474 Current Progress - What These Numbers Mean page navigation
	@Test(enabled=false)//Not a part of mini Reg
	public void testCurrentProgressWhatTheseNumbersMean_2474q(){
		String currentUrl = null;
		String expectedUrl = "what-these-numbers-mean-psqv";
		lsdHomePage.clickWhatTheseNumbersMeanLink();
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(expectedUrl),"Current URL : "+currentUrl+" does not contains "+expectedUrl);
		s_assert.assertTrue(lsdHomePage.isSVAndPSQVDescriptionPresent(),"SV and PSQV description is not present as expected");
		s_assert.assertAll();
	}

	//TC-2471 Est SV page navigation
	@Test(enabled=false)//Not a part of mini Reg
	public void testEstSVPageNavigation_2471q(){
		lsdHomePage.clickEstimatedSV();
		s_assert.assertTrue(lsdHomePage.isTextPresentAtEstimatedPage("Estimated SV") && lsdHomePage.getCurrentURL().contains("/sv"), "Estimated SV page is not present");
		lsdHomePage.navigateToBackPage();
		s_assert.assertTrue(lsdHomePage.isHomePageOfPulsePresent(), "Back button is not navigating to home page");
		s_assert.assertAll();
	}





	//TC-2561 Downline report link
	@Test(alwaysRun=true)
	public void testDownlineReportLink_2561q(){
		String reportName = "Downline";
		String currentURL = null;
		String parentWindowHandle = null;
		parentWindowHandle = lsdHomePage.getParentWindowHandle();
		lsdHomePage.clickReportLinks(reportName, parentWindowHandle);
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(reportName.toLowerCase()), "Expected url should contains "+reportName+" actual on UI is "+currentURL);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	//TC-2562 Earnings Statements report link
	@Test(alwaysRun=true)
	public void testEarningsStatementsReportLink_2562q(){
		String reportName = "Earnings";
		String currentURL = null;
		String parentWindowHandle = null;
		parentWindowHandle = lsdHomePage.getParentWindowHandle();
		lsdHomePage.clickReportLinks(reportName, parentWindowHandle);
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(reportName.toLowerCase()), "Expected url should contains "+reportName+" actual on UI is "+currentURL);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	//TC-2534 Performance Sponsor (name/city/state/country)
	@Test(alwaysRun=true)
	public void testPerformanceSponsorDetails_2534q(){
		String sNamefromDB = null;
		String locale = null;
		String region= null;
		String country = null;
		String sponsorNameFromUI = null;
		String addressFromUI = null;
		List<Map<String, Object>> randomSponsorDetailsList;
		randomSponsorDetailsList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_PLACEMENT_SPONSOR_INFORMATION_COMMISSION,accountId),commDBName,Commisions_DB_IP);
		sNamefromDB = String.valueOf( getValueFromQueryResult(randomSponsorDetailsList, "LegalName"));
		locale = String.valueOf( getValueFromQueryResult(randomSponsorDetailsList, "locale"));
		region = String.valueOf( getValueFromQueryResult(randomSponsorDetailsList, "Region"));
		country = String.valueOf( getValueFromQueryResult(randomSponsorDetailsList, "countrycode3"));
		String sponsorLegalFirstName=lsdHomePage.dbsponsorName(sNamefromDB)[0];
		String sponsorLegalLastName= lsdHomePage.dbsponsorName(sNamefromDB)[1];
		sponsorNameFromUI = lsdHomePage.getSponsorNameFromMySponsorSection();
		addressFromUI = lsdHomePage.getSponsorAddressFromMySponsorSection();
		s_assert.assertTrue(sponsorNameFromUI.contains(sponsorLegalFirstName), "Expected sponsor First name is "+sponsorLegalFirstName+" actual on UI is "+sponsorNameFromUI);
		s_assert.assertTrue(sponsorNameFromUI.contains(sponsorLegalLastName), "Expected sponsor Last name is "+sponsorLegalLastName+" actual on UI is "+sponsorNameFromUI);
		s_assert.assertTrue(addressFromUI.contains(locale), "Expected locale is "+locale+" actual on UI is "+addressFromUI);
		s_assert.assertTrue(addressFromUI.contains(region), "Expected region is "+region+" actual on UI is "+addressFromUI);
		s_assert.assertTrue(addressFromUI.contains(country), "Expected country is "+country+" actual on UI is "+addressFromUI);		
		s_assert.assertAll();
	}

	//TC-2539 Consultants on My Level 1 value
	@Test(alwaysRun=true)
	public void testConsultantsOnMyLevel1Value_2539q(){
		String currentURL = null;
		String countOfConsultantFromDB = null;
		String countOfConsultantFromUI = null;
		List<Map<String, Object>> randomConsultantCountList;
		countOfConsultantFromUI = lsdHomePage.getCountOfConsultantOnMyLevel1();
		randomConsultantCountList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_COUNT_OF_CONSULTANT_OF_LEVEL_1_PULSE,accountId),RFO_DBName,RFO_DB_IP);
		countOfConsultantFromDB = String.valueOf( getValueFromQueryResult(randomConsultantCountList, "count"));
		s_assert.assertTrue(countOfConsultantFromDB.equalsIgnoreCase(countOfConsultantFromUI), "Expected count of consultant is "+countOfConsultantFromDB+" but actual on UI is "+countOfConsultantFromUI);
		lsdHomePage.clickConsultantOnMyLevel1Link();
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("/level"), "Expected url should contain /level but actual on UI is "+currentURL);
		countOfConsultantFromUI = lsdHomePage.getCountOfConsultantOnMyLevel1AtTeamPage();
		s_assert.assertTrue(countOfConsultantFromDB.equalsIgnoreCase(countOfConsultantFromUI), "Expected count of consultant at team page is "+countOfConsultantFromDB+" but actual on UI is "+countOfConsultantFromUI);
		s_assert.assertAll();
	}

	//TC-2537 PC Autoships This Month value
	@Test(alwaysRun=true)
	public void testPCAutoshipsThisMonthValue_2537q(){
		String currentURL = null;
		String countOfPCFromDB = null;
		String countOfPCFromUI = null;
		List<Map<String, Object>> randomPCCountList;
		countOfPCFromUI = lsdHomePage.getCountOfPCCustomerWithOrders();
		randomPCCountList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_COUNT_OF_PC_CUSTOMER_WITH_ORDERS_PULSE,accountId),RFO_DBName,RFO_DB_IP);
		countOfPCFromDB = String.valueOf( getValueFromQueryResult(randomPCCountList, "count"));
		s_assert.assertTrue(countOfPCFromDB.equalsIgnoreCase(countOfPCFromUI), "Expected count of PC is "+countOfPCFromDB+" but actual on UI is "+countOfPCFromUI);
		lsdHomePage.clickPCCustomerWithOrdersLink();
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("/pcs"), "Expected url should contain /level but actual on UI is "+currentURL);
		countOfPCFromUI = lsdHomePage.getCountOfPCCustomerWithOrdersAtPCOrdersPage();
		s_assert.assertTrue(countOfPCFromDB.equalsIgnoreCase(countOfPCFromUI), "Expected count of consultant at team page is "+countOfPCFromDB+" but actual on UI is "+countOfPCFromUI);
		s_assert.assertAll();
	}

	//TC-2531 Upcoming Orders - Pending Autoships
	@Test(alwaysRun=true)
	public void testUpcomingOrdersPendingAutoships_2531q(){
		String label_OrderStatus = "Order Status";
		String label_OrderType = "Order Type";
		String label_OrderNumber = "Order Number";
		String label_QV = "QV";
		boolean isUserEnrolledInCRP = false;
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

	//TC-2895 View Past Orders Details
	@Test(alwaysRun=true)
	public void testViewPastOrderDetails_2895q(){
		String label_OrderStatus = "Order Status";
		String label_OrderType = "Order Type";
		String label_OrderNumber = "Order Number";
		String label_SV = "SV";
		String dateAndTimeStamp = null;
		lsdHomePage.clickViewMyPastOrdersLink();
		dateAndTimeStamp = lsdHomePage.getDateAndTimeStampFromPastOrdersPage();
		s_assert.assertTrue(dateAndTimeStamp.contains("AM")|| dateAndTimeStamp.contains("PM"), "Time stamp is not present at past orders page");
		s_assert.assertTrue(dateAndTimeStamp.contains("/"), "Date stamp is not present at past orders page");
		s_assert.assertTrue(lsdHomePage.isPastOrderSectionValuePresent(label_OrderStatus), "Expected "+label_OrderStatus+"'s value is empty");
		s_assert.assertTrue(lsdHomePage.isPastOrderSectionValuePresent(label_OrderType), "Expected "+label_OrderType+"'s value is empty");
		s_assert.assertTrue(lsdHomePage.isPastOrderSectionValuePresent(label_OrderNumber), "Expected "+label_OrderNumber+"'s value is empty");
		s_assert.assertTrue(lsdHomePage.isPastOrderSectionValuePresent(label_SV), "Expected "+label_SV+"'s value is empty");
		s_assert.assertAll();
	}



	//TC-2479 Copy for Title History page
	@Test(alwaysRun=true)
	public void testTitleHistoryPage_2479q(){
		String title_PaidAsTitle = "Paid-as Title";
		String title_QualificationTitle = "Qualification Title";
		String title_RecognitionTitle = "Recognition Title";
		String title_HighestTitle = "Highest Title";
		lsdHomePage.clickViewTitleHistoryLink();
		s_assert.assertTrue(lsdHomePage.isTextPresentAtEstimatedPage("Title History") && lsdHomePage.getCurrentURL().contains("title"), "Title History page is not present");
		s_assert.assertTrue(lsdHomePage.iscurrentTitleValuePresentWithTitleName(title_PaidAsTitle), title_PaidAsTitle+" is not present with title name");
		s_assert.assertTrue(lsdHomePage.iscurrentTitleValuePresentWithTitleName(title_QualificationTitle), title_QualificationTitle+" is not present with title name");
		s_assert.assertTrue(lsdHomePage.iscurrentTitleValuePresentWithTitleName(title_RecognitionTitle), title_RecognitionTitle+" is not present with title name");
		s_assert.assertTrue(lsdHomePage.isHighestTitleValuePresent(), title_HighestTitle+" is not present with title name");
		s_assert.assertTrue(lsdHomePage.isDatePresentUnderCircle(), "Date is not present under circle");
		s_assert.assertTrue(lsdHomePage.isCurrentAndEnrollmentDatePresent(), "Current and enrollment date is not present");
		lsdHomePage.navigateToBackPage();
		s_assert.assertTrue(lsdHomePage.isHomePageOfPulsePresent(), "Back button is not navigating to home page");
		s_assert.assertAll();
	}



	//TC-2468 Est PSQV value
	@Test(alwaysRun=true)
	public void testEstPSQVValue_2468q(){
		List<Map<String, Object>> randomConsultantValueList;
		String PSQVFromDB = null;
		String PSQVFromUI = null;
		randomConsultantValueList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ESTIMATED_SV_AND_PSQV_VALUE_PULSE,accountId),commDBName,Commisions_DB_IP);
		PSQVFromDB = String.valueOf(getValueFromQueryResult(randomConsultantValueList, "EstimatedPSQV"));
		System.out.println(PSQVFromDB);
		PSQVFromUI = lsdHomePage.getEstimatedPSQV();
		s_assert.assertTrue(PSQVFromDB.contains(PSQVFromUI), "Expected PSQV is "+PSQVFromDB+" but actual on UI is "+PSQVFromUI);
		s_assert.assertAll();
	}

	//TC-2467 Est SV value
	@Test(alwaysRun=true)
	public void testCurrentProgressEstSVValue_2467q(){
		List<Map<String, Object>> randomConsultantValueList;
		String SVFromDB = null;
		String SVFromUI = null;
		randomConsultantValueList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ESTIMATED_SV_AND_PSQV_VALUE_PULSE,accountId),commDBName,Commisions_DB_IP);
		SVFromDB = String.valueOf(getValueFromQueryResult(randomConsultantValueList, "Estimated Total SV "));
		SVFromUI = lsdHomePage.getEstimatedSV();
		s_assert.assertTrue(SVFromDB.contains(SVFromUI), "Expected SV is "+SVFromDB+" but actual on UI is "+SVFromUI);
		s_assert.assertAll();
	}

	//qTest ID-3353 Pending PC autoships
	//qTest ID-3017 Pending PC autoships
	@Test(alwaysRun=true)
	public void testPendingPCAutoships_3017q_3353q(){
		String countOfPCOrderFromDB = null; 
		String countOfPCOrderFromUI = null;
		String totalNumberOfOrders = null;
		String totalNumberOfPendingOrders = null;
		String subLinkCustomers = "Customers";
		List<Map<String, Object>> randomConsultantListCommi;
		//	randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_COUNT_OF_PENDING_PC_AUTOSHIPS_PULSE,accountId),RFO_DBName,RFO_DB_IP);
		//	countOfPCOrderFromDB = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "PCount"));
		countOfPCOrderFromDB= String.valueOf(getValueFromQueryResult(getAllCustomersInfo(), "CustomersHaveOrdersNextMonth"));
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickPCOrderForNextMonth();
		countOfPCOrderFromUI = lsdCustomerPage.getCountOfPCOrdersForNextMonthFromHeader();
		totalNumberOfOrders =lsdCustomerPage.getTotalNumberOrders();
		totalNumberOfPendingOrders = lsdCustomerPage.getTotalNumberOfPendingAutoshipOrders();
		s_assert.assertTrue(countOfPCOrderFromDB.equalsIgnoreCase(countOfPCOrderFromUI), "Expected count of orders pcs are"+countOfPCOrderFromDB+"but actual on UI is "+countOfPCOrderFromUI);
		s_assert.assertTrue(totalNumberOfPendingOrders.equalsIgnoreCase(totalNumberOfOrders), "Expected count of total pending orders"+totalNumberOfPendingOrders+" but actual on UI "+totalNumberOfOrders);
		clickRFLogo();
		s_assert.assertAll();
	}

	//TC-2634 Full Name
	@Test(alwaysRun=true)
	public void testFullNameFromCard_2634q(){
		String fullNameFromDB = null;
		String fullNameFromUI = null;
		String firstNameFromDB = (String) getValueFromQueryResult(randomConsultantList, "FirstName");
		String lastNameFromDB = (String) getValueFromQueryResult(randomConsultantList, "LastName");
		fullNameFromDB = firstNameFromDB+" "+lastNameFromDB;
		fullNameFromUI = lsdHomePage.getConsultantCardFullName();
		s_assert.assertTrue(fullNameFromUI.equalsIgnoreCase(fullNameFromDB), "Expected Full name is"+fullNameFromDB+" actual on UI is "+fullNameFromUI);
		s_assert.assertAll();
	}




}