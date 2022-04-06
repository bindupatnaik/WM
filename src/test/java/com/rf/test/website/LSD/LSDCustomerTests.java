package com.rf.test.website.LSD;

import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.RFLSDWebsiteBaseTest;

public class LSDCustomerTests extends RFLSDWebsiteBaseTest{

	//TC-3021 Total number of PCs Next Month
	@Test(alwaysRun=true,enabled=true)//Not a part of mini Reg
	public void testTotalNumberOfPCsNextMonth_3021q(){
		List<Map<String, Object>> countOfPCOrderList;
		String countOfPCsOrdersNextMonthFromDB = null;
		String countOfPCOrdersNextMonthFromUI = null;
		String countOfPCOrderFromHeader = null;
		String subLinkCustomers = "Customers";

		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		countOfPCsOrdersNextMonthFromDB= String.valueOf(getValueFromQueryResult(getAllCustomersInfo(), "Customer Scheduled Order Next Month"));
		countOfPCOrdersNextMonthFromUI = lsdCustomerPage.getCountOfPCOrdersForNextMonth();
		s_assert.assertTrue(countOfPCsOrdersNextMonthFromDB.equalsIgnoreCase(countOfPCOrdersNextMonthFromUI), "Expected count of order is "+countOfPCsOrdersNextMonthFromDB+" actual on UI is "+countOfPCOrdersNextMonthFromUI);
		lsdCustomerPage.clickPCOrderForNextMonth();
		countOfPCOrderFromHeader = lsdCustomerPage.getCountOfPCOrdersForNextMonthFromHeader();
		s_assert.assertTrue(countOfPCsOrdersNextMonthFromDB.equalsIgnoreCase(countOfPCOrderFromHeader), "Expected count of order at header is "+countOfPCsOrdersNextMonthFromDB+" actual on UI is "+countOfPCOrderFromHeader);
		s_assert.assertAll();
	}

	//TC-3306 Total number of New PCs
	@Test(alwaysRun=true,enabled=true)//Not a part of mini Reg
	public void testTotalNumberOfNewPCs_3306q(){
		List<Map<String, Object>> countOfPCList;
		String countOfPCFromDB = null;
		String countOfPCFromUI = null;
		String countOfPCFromHeader = null;
		String subLinkCustomers = "Customers";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		countOfPCFromDB= String.valueOf(getValueFromQueryResult(getAllCustomersInfo(), "New Pcs"));
		//countOfPCList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_NEW_PC_COUNT_FROM_ACCOUNT_ID_PULSE,accountId),RFO_DBName,RFO_DB_IP);
		//countOfPCFromDB= String.valueOf(getValueFromQueryResult(countOfPCList, "PCount"));
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		countOfPCFromUI = lsdCustomerPage.getCountOfNewPC();
		s_assert.assertTrue(countOfPCFromDB.equalsIgnoreCase(countOfPCFromUI), "Expected count of order is "+countOfPCFromDB+" actual on UI is "+countOfPCFromUI);
		lsdCustomerPage.clickNewPC();
		countOfPCFromHeader = lsdCustomerPage.getCountOfNewPCFromHeader();
		s_assert.assertTrue(countOfPCFromDB.equalsIgnoreCase(countOfPCFromHeader), "Expected count of order at header is "+countOfPCFromDB+" actual on UI is "+countOfPCFromHeader);
		s_assert.assertAll();
	}

	//qTest ID-2448 Customer View subcategories
	@Test(alwaysRun=true,enabled=false)//Not a part of mini Reg
	public void customerViewSubCategories_2448q(){
		String currentURL = null;
		String text_HaveOrdersThisMonth = "have orders this month";
		String text_scheduledOrdersNextMonth = "scheduled orders next month";
		String text_AreFurtherOut = "are further out";
		String text_YourNewCustomers = "Your new Customers";
		String text_DeactivatedPCs = "Deactivated PCs";
		String subLinkCustomers = "Customers";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickSubCategoryOnCustomerPage("1");
		s_assert.assertTrue(lsdCustomerPage.isTextPresentAtHeader(text_HaveOrdersThisMonth), text_HaveOrdersThisMonth+" text is not present at header");
		lsdCustomerPage.navigateToBackPage();
		lsdCustomerPage.clickSubCategoryOnCustomerPage("2");
		currentURL = lsdCustomerPage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("will-order"), text_scheduledOrdersNextMonth+" is not present at haeader");
		lsdCustomerPage.navigateToBackPage();
		lsdCustomerPage.clickSubCategoryOnCustomerPage("3");
		s_assert.assertTrue(lsdCustomerPage.isTextPresentAtHeader(text_AreFurtherOut), text_AreFurtherOut+" text is not present at header");
		lsdCustomerPage.navigateToBackPage();
		lsdCustomerPage.clickSubCategoryOnCustomerPageUnderViewOurCustomer("1");
		s_assert.assertTrue(lsdCustomerPage.isTextPresentAtHeader(text_YourNewCustomers), text_YourNewCustomers+" text is not present at header");
		lsdCustomerPage.navigateToBackPage();
		lsdCustomerPage.clickSubCategoryOnCustomerPageUnderViewOurCustomer("3");
		s_assert.assertTrue(lsdCustomerPage.isTextPresentAtHeader(text_DeactivatedPCs), text_DeactivatedPCs+" text is not present at header");
		s_assert.assertAll();
	}

	//qTest ID-3000 Sorting Menu for This Month
	@Test
	public void testSortingMenuForThisMonth_3000q(){
		String currentURL = null;
		String filterFirstNameAtoZ = "First name: A to Z";
		String filterFirstNameZtoA = "First name: Z to A";
		String filterLastNameAtoZ = "Last name: A to Z";
		String filterLastNameZtoA = "Last name: Z to A";
		String filterCountryNameAtoZ = "Country: A to Z";
		String filterCountryNameZtoA = "Country: Z to A";
		String filterDateNewestToOldest = "Date: Newest to Oldest";
		String filterDateOldestToNewest = "Date: Oldest to Newest";
		String filterQVLowToHigh = "QV: Low to High";
		String filterQVHighToLow = "QV: High to Low";
		String subLinkCustomers = "Customers";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickPCOrderForThisMonth();
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("orders"),"Current URL : "+currentURL+" does not contain orders");
		lsdHomePage.clickFilter();
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterFirstNameAtoZ), filterFirstNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterFirstNameZtoA), filterFirstNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterLastNameAtoZ), filterLastNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterLastNameZtoA), filterLastNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterCountryNameAtoZ), filterCountryNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterCountryNameZtoA), filterCountryNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterDateNewestToOldest), filterDateNewestToOldest+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterDateOldestToNewest), filterDateOldestToNewest+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterQVLowToHigh), filterQVLowToHigh+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterQVHighToLow), filterQVHighToLow+" is not visible at filters page");
		s_assert.assertAll();
	}

	//qTest ID-2999 Sorting menu for Next Month
	@Test
	public void testSortingMenuForNextMonth_2999q(){
		String currentURL = null;
		String filterFirstNameAtoZ = "First name: A to Z";
		String filterFirstNameZtoA = "First name: Z to A";
		String filterLastNameAtoZ = "Last name: A to Z";
		String filterLastNameZtoA = "Last name: Z to A";
		String filterCountryNameAtoZ = "Country: A to Z";
		String filterCountryNameZtoA = "Country: Z to A";
		String filterQVLowToHigh = "QV: Low to High";
		String filterQVHighToLow = "QV: High to Low";
		String subLinkCustomers = "Customers";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickPCOrderForNextMonth();
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("will"),"Current URL : "+currentURL+" does not contain will");
		lsdHomePage.clickFilter();
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterFirstNameAtoZ), filterFirstNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterFirstNameZtoA), filterFirstNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterLastNameAtoZ), filterLastNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterLastNameZtoA), filterLastNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterCountryNameAtoZ), filterCountryNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterCountryNameZtoA), filterCountryNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterQVLowToHigh), filterQVLowToHigh+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterQVHighToLow), filterQVHighToLow+" is not visible at filters page");
		s_assert.assertAll();
	}

	//TC-3011 Sorting by QV: Low to High
	@Test
	public void testSortingByQVLowTohigh_3011q(){
		String subLinkCustomers = "Customers";
		String filterQVLowToHigh = "QV: Low to High";
		String countOfOrdersForThisMonth = null;
		String countOfOrdersForNextMonth = null;
		String countOfOrdersForFurther = null;
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		countOfOrdersForThisMonth = lsdCustomerPage.getCountOfPCAutoshipOrdersForThisMonth();
		countOfOrdersForNextMonth = lsdCustomerPage.getCountOfPCOrdersForNextMonth();
		countOfOrdersForFurther = lsdCustomerPage.getCountOfPCOrdersForFurtherOut();
		if(lsdCustomerPage.isCountGreaterThanTwo(countOfOrdersForThisMonth)){
			lsdCustomerPage.clickPCOrderForThisMonth();
			lsdHomePage.clickFilter();
			lsdHomePage.selectFilter(filterQVLowToHigh);
			s_assert.assertTrue(lsdCustomerPage.isSortFilterQVHighToLowAppliedSuccessfully(), "Sorting filter QV value from Low To High is not applied successfully for this month");
			lsdCustomerPage.navigateToBackPage();
		}
		//For next month
		if(lsdCustomerPage.isCountGreaterThanTwo(countOfOrdersForNextMonth)){
			lsdCustomerPage.clickPCOrderForNextMonth();
			lsdHomePage.clickFilter();
			lsdHomePage.selectFilter(filterQVLowToHigh);
			s_assert.assertTrue(lsdCustomerPage.isSortFilterQVHighToLowAppliedSuccessfully(), "Sorting filter QV value from Low To High is not applied successfully for next month");
			lsdCustomerPage.navigateToBackPage();
		}
		//For further out
		if(lsdCustomerPage.isCountGreaterThanTwo(countOfOrdersForFurther)){
			lsdCustomerPage.clickPCOrderForThisMonth();
			lsdHomePage.clickFilter();
			lsdHomePage.selectFilter(filterQVLowToHigh);
			s_assert.assertTrue(lsdCustomerPage.isSortFilterQVHighToLowAppliedSuccessfully(), "Sorting filter QV value from Low To High is not applied successfully for this month");
		}
		s_assert.assertAll();
	}

	//TC-3240 PC Card components
	@Test
	public void testPCCardComponents_3240q(){
		String subLinkCustomers = "Customers";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickPCOrderForThisMonth();
		s_assert.assertTrue(lsdCustomerPage.isFirstOrderDetailsPresent("1"), "Frist name is not present of first order");
		s_assert.assertTrue(lsdCustomerPage.isFirstOrderDetailsPresent("2"), "Order status is not present of first order");
		s_assert.assertTrue(lsdCustomerPage.isFirstOrderDetailsPresent("3"), "Order/autoship date is not present of first order");
		s_assert.assertTrue(lsdCustomerPage.isFirstOrderDetailsPresent("4"), "Autoship name is not present of first order");
		s_assert.assertTrue(lsdCustomerPage.isFirstOrderDetailsPresent("5"), "Country name is not present of first order");
		s_assert.assertTrue(lsdCustomerPage.isFirstOrderDetailsPresent("6"), "QV value is not present of first order");
		s_assert.assertTrue(lsdCustomerPage.isContactIconPresentAtPCOrderCardUnderCustomer(), "Contact icon is not present of first order");
		s_assert.assertTrue(lsdCustomerPage.isTwoInitialIconPresentAtPCOrderCardUnderCustomer(), "Two initial icon is not present of first order");
		s_assert.assertAll();
	}

	//TC-2985 Total number of Deactivated PCs
	@Test
	public void testTotalNumberOfDeactivatedPCs_2985q(){
		String subLinkCustomers = "Customers";
		List<Map<String, Object>> randomConsultantListCommi;
		String countOfDeactivatedPC = null;
		String countOfDeactivatedPCFromUI = null;
		String countOfDeactivatedPCFromHeader = null;
		lsdHomePage.clickLogout();
		randomConsultantListCommi = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_USERNAME_HAVING_DEACTIVATED_PC_WITH_COUNT,commDBName,Commisions_DB_IP);
		accountId = String.valueOf(getValueFromQueryResult(randomConsultantListCommi, "AccountID"));
		String username= (String) getValueFromQueryResult(randomConsultantListCommi, "Username");
		countOfDeactivatedPC = String.valueOf( getValueFromQueryResult(randomConsultantListCommi, "PCTerm60DaysCount"));
		lsdLoginPage.loginToPulse(username, password);
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		countOfDeactivatedPCFromUI = lsdCustomerPage.getCountOfPCUnderViewCustomerSection("4");
		s_assert.assertTrue(countOfDeactivatedPC.equals(countOfDeactivatedPCFromUI), "Expected count of deactivate PCs are"+countOfDeactivatedPC+"but actual on UI is "+countOfDeactivatedPCFromUI);
		lsdCustomerPage.clickSubCategoryOnCustomerPageUnderViewOurCustomer("3");
		countOfDeactivatedPCFromHeader = lsdCustomerPage.getCountOfDeactivatedPCFromHeader();
		s_assert.assertTrue(countOfDeactivatedPC.equals(countOfDeactivatedPCFromHeader), "Expected count of deactivate PCs are"+countOfDeactivatedPC+"but actual on header is "+countOfDeactivatedPCFromHeader);
		s_assert.assertAll();
	}

	//qtest ID-TC-3349 Total number of All PCs
	@Test
	public void TotalNumberOfAllPCs_3349q(){
		List<Map<String, Object>> countOfAllPC;
		String countOfAllPCFromDB = null;
		String subLinkCustomers = "Customers";
		String countOfAllPCFromUI = null;
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		lsdCustomerPage.clickViewAllCustomers();

		countOfAllPCFromDB= String.valueOf(getValueFromQueryResult(getAllCustomersInfo(), "Total Customers"));
		countOfAllPCFromUI = lsdCustomerPage.getCountOfAllPCForThisMonth();
		s_assert.assertTrue(countOfAllPCFromDB.equalsIgnoreCase(countOfAllPCFromUI), "Expected count of PC users are "+countOfAllPCFromDB+" actual on UI are "+countOfAllPCFromUI);
		s_assert.assertAll();

	}

	//TC-3013 Total number of PCs This Month
	@Test
	public void testTotalNumberOfPCsThisMonth_3013q(){
		List<Map<String, Object>> countOfPCAutoshipList;
		String countOfPCAutoshipFromHeader = null;
		String subLinkCustomers = "Customers";
		String countOfPCAutoshipThisMonthFromDB = null;
		String countOfPCAutoshipThisMonthFromUI = null;
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomers);
		countOfPCAutoshipThisMonthFromDB= String.valueOf(getValueFromQueryResult(getAllCustomersInfo(), "Have orders  Customer Counts"));
		countOfPCAutoshipThisMonthFromUI = lsdCustomerPage.getCountOfPCAutoshipOrdersForThisMonth();
		s_assert.assertTrue(countOfPCAutoshipThisMonthFromDB.equalsIgnoreCase(countOfPCAutoshipThisMonthFromUI), "Expected count of order is "+countOfPCAutoshipThisMonthFromDB+" actual on UI is "+countOfPCAutoshipThisMonthFromUI);
		lsdCustomerPage.clickPCOrderForThisMonth();
		countOfPCAutoshipFromHeader = lsdCustomerPage.getCountOfPCAutoshipOrdersForThisMonthFromHeader();
		s_assert.assertTrue(countOfPCAutoshipThisMonthFromDB.equalsIgnoreCase(countOfPCAutoshipFromHeader), "Expected count of order at header is "+countOfPCAutoshipThisMonthFromDB+" actual on UI is "+countOfPCAutoshipFromHeader);
		s_assert.assertAll();

	}


}
