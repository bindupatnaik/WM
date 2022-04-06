package com.rf.test.website.nscore4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.nscore.NSCore4AdminPage;
import com.rf.pages.website.nscore.NSCore4HomePage;
import com.rf.pages.website.nscore.NSCore4LoginPage;
import com.rf.pages.website.nscore.NSCore4MobilePage;
import com.rf.pages.website.nscore.NSCore4OrdersTabPage;
import com.rf.pages.website.nscore.NSCore4ProductsTabPage;
import com.rf.pages.website.nscore.NSCore4SitesTabPage;
import com.rf.test.website.RFNSCoreWebsiteBaseTest;

public class NSCore4OrdersTest extends RFNSCoreWebsiteBaseTest{

	private static final Logger logger = LogManager
			.getLogger(NSCore4OrdersTest.class.getName());

	private NSCore4HomePage nscore4HomePage;
	private NSCore4OrdersTabPage nscore4OrdersTabPage;
	String RFL_DB = null;
	//List<Map<String, Object>> randomConsultantAccountList =  null;
	List<Map<String, Object>> randomConsultantAccountList =  null;
	public NSCore4OrdersTest() {
		nscore4HomePage = new NSCore4HomePage(driver);
		nscore4OrdersTabPage = new NSCore4OrdersTabPage(driver);
	}

	@BeforeClass
	public void executeCommonQuery(){
		RFL_DB = driver.getDBNameRFL();
		//randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS,RFL_DB);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
	}

	//QTest ID TC-1964 Orders>FirstName and LastName
	//QTest ID TC-1965 Orders>CID
	@Test()
	public void testOrdersTab_OrderIdSearch_1964_1965q(){
		String accountNumber = null;
		String firstNameDB = null;
		String lastNameDB =null;
		String completeNameDB =null;
		List<Map<String, Object>> randomConsultantList =  null;
		logger.info("DB is "+RFL_DB); 
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		firstNameDB = String.valueOf(getValueFromQueryResult(randomConsultantList, "FirstName"));
		lastNameDB = String.valueOf(getValueFromQueryResult(randomConsultantList, "LastName"));
		accountNumber=String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountNumber"));
		completeNameDB = firstNameDB+" "+lastNameDB;
		logger.info("Complete name from DB is: "+completeNameDB);
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();

		//click Browse Orders link
		nscore4OrdersTabPage.clickBrowseOrdersLlink();
		nscore4OrdersTabPage.enterFirstAndLastNameInBrowseOrders(firstNameDB, lastNameDB);
		nscore4OrdersTabPage.clickSearchIcon();
		s_assert.assertTrue(nscore4OrdersTabPage.isSearchResultPresent(completeNameDB),"Expected Search result not present on UI");

		//click Browse Orders link
		nscore4OrdersTabPage.clickBrowseOrdersLlink();
		nscore4OrdersTabPage.enterCIDInBrowseOrders(accountNumber);
		nscore4OrdersTabPage.clickSearchIcon();
		s_assert.assertTrue(nscore4OrdersTabPage.isSearchResultPresent(accountNumber),"Expected Search result does not contain CID on UI");
		s_assert.assertAll();
	}

	//QTest ID TC-1968 Orders>From Date
	@Test()
	public void testOrdersFromDate_1968(){
		String todayDate=null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		todayDate=dateFormat.format(date);
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
		//click Browse Orders link
		nscore4OrdersTabPage.clickBrowseOrdersLlink();
		s_assert.assertTrue(nscore4OrdersTabPage.isFromDateInBrowseOrdersEditable(todayDate),"From Date on UI is not Editable");
		s_assert.assertAll();
	}

	//QTest ID TC-2476 Cancel Pulse Template
	@Test()
	public void testCancelPulseTemplate_2476(){
		String accountNumber = null;
		String pulseStatus = "Cancelled";
		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		for(int i=1;i<=5;i++){
			accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
			logger.info("Account number from DB is "+accountNumber);
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(accountNumber);
			if(nscore4HomePage.isEditPresentForPulseMonthlySubscriptionPresent()==true)	
				break;
			else
				nscore4HomePage.clickAccountsTab();
		}
		nscore4HomePage.clickPulseMonthlySubscriptionEdit();
		nscore4HomePage.selectTemplateStatus(pulseStatus);
		nscore4HomePage.clickSaveTemplate();
		s_assert.assertTrue(nscore4HomePage.isCRPOrPulseTemplateStatusPresent(pulseStatus),"Pulse of user is NOT cancelled successfully.");
		s_assert.assertAll();
	}

	//QTest ID TC-2477 Cancel CRP Template
	@Test()
	public void testCancelCRPTemplate_2477(){
		String accountNumber = null;
		String crpStatus = "Cancelled";
		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		for(int i=1;i<=5;i++){
			accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
			logger.info("Account number from DB is "+accountNumber);
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(accountNumber);
			if(nscore4HomePage.isEditPresentForCRPTemplatePresent()==true)	
				break;
			else
				nscore4HomePage.clickAccountsTab();
		}
		nscore4HomePage.clickEditCRPTemplate();
		nscore4HomePage.selectTemplateStatus(crpStatus);
		nscore4HomePage.clickSaveTemplate();
		s_assert.assertTrue(nscore4HomePage.isCRPOrPulseTemplateStatusPresent(crpStatus),"CRP of user is NOT cancelled successfully.");
		s_assert.assertAll();
	}

	//QTest ID TC-2468 Create CRP template
	@Test()
	public void testCreateCRPTemplate_2468(){
		String accountNumber = null;
		String crpStatus = "Submitted Template";
		String SKU = "AARG001";

		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		assertTrue(nscore4HomePage.isCreateForCRPTemplatePresent(),"Create link for CRP is not present.");
		nscore4HomePage.clickCreateCRPTemplate();
		nscore4HomePage.selectTemplateStatus(crpStatus);
		nscore4HomePage.enterSKUValues(SKU);
		nscore4HomePage.clickFirstSKUSearchResultOfAutoSuggestion();
		nscore4HomePage.clickAddToOrder();
		//nscore4HomePage.addPaymentMethod(firstName, lastNames, nameOnCard, cardNumber, CVV, attentionName);
		nscore4HomePage.clickSaveTemplate();
		nscore4HomePage.clickConsultantNameOnOrderDetailsPage();
		s_assert.assertTrue(nscore4HomePage.isEditPresentForCRPTemplatePresent(),"Create link for CRP is present after enroll in CRP template.");
		s_assert.assertAll();
	}	

	//QTest ID TC-2469 Create Pulse template
	@Test()
	public void testCreatePulseTemplate_2469(){
		String accountNumber = null;
		String crpStatus = "Submitted Template";
		String SKU = "PULSE01";

		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		assertTrue(nscore4HomePage.isCreateForPulseTemplatePresent(),"Create link for CRP is not present.");
		nscore4HomePage.clickCreatePulseTemplate();
		nscore4HomePage.selectTemplateStatus(crpStatus);
		nscore4HomePage.enterSKUValues(SKU);
		nscore4HomePage.clickFirstSKUSearchResultOfAutoSuggestion();
		nscore4HomePage.clickAddToOrder();
		//nscore4HomePage.addPaymentMethod(firstName, lastNames, nameOnCard, cardNumber, CVV, attentionName);
		nscore4HomePage.clickSaveTemplate();
		nscore4HomePage.clickConsultantNameOnOrderDetailsPage();
		s_assert.assertTrue(nscore4HomePage.isEditPresentForPulseMonthlySubscriptionPresent(),"Create link for CRP is present after enroll in CRP template.");
		s_assert.assertAll();
	}


	//QTest ID TC-966 MAIN-5200, Recalculate after updating one order to zero
	@Test
	public void testMAIN5200RecalculateAfterUpdatingOneOrderToZero_966(){
		String accountNumber = null;
		String taxBeforeRecalculation=null;
		String taxAfterRecalculation=null;
		String price=null;
		String SKU=null;
		String QV=null;
		String CV=null;
		String orderTotalFromOrderCreationBeforeChange=null;
		String orderTotalFromOrderCreationAfterChange=null;
		String shippingFromOrderCreationBeforeChange=null;
		String shippingFromOrderCreationAfterChange=null;
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is "+accountNumber);

		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU=nscore4HomePage.addAndGetProductSKU("1");
		nscore4HomePage.addAndGetMultipleProductSKU("1");
		taxBeforeRecalculation=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationBeforeChange=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		shippingFromOrderCreationBeforeChange = nscore4OrdersTabPage.getShippingFromOrderCreationPage();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.changePriceCVAndQVValuesToZero(price, CV, QV,SKU);
		nscore4OrdersTabPage.clickRecalculateTax();
		s_assert.assertTrue(nscore4OrdersTabPage.verifyPriceOfProductIsSetToZero(SKU).contains("0"),"Price of product is not set to 0");
		s_assert.assertTrue(nscore4OrdersTabPage.verifyPriceOfQVIsSetToZero(SKU).contains("0"),"QV of product is not set to 0");
		s_assert.assertTrue(nscore4OrdersTabPage.verifyPriceOfCVIsSetToZero(SKU).contains("0"),"CV of product is not set to 0");
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		taxAfterRecalculation=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationAfterChange=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		shippingFromOrderCreationAfterChange = nscore4OrdersTabPage.getShippingFromOrderCreationPage();
		s_assert.assertFalse(orderTotalFromOrderCreationAfterChange.contains(orderTotalFromOrderCreationBeforeChange),"Order Total is not changed after Re-calculating");
		s_assert.assertTrue(shippingFromOrderCreationBeforeChange.contains(shippingFromOrderCreationAfterChange),"Sub Total is not changed after Re-calculating");
		s_assert.assertFalse(taxAfterRecalculation.contains(taxBeforeRecalculation),"Tax is not changed after Re-calculating");
		s_assert.assertTrue(nscore4OrdersTabPage.isPriceOfProductIsSetToZeroOrderCreationPage(SKU).contains("0"),"Price of product is not set to 0 on Order creation page");
		s_assert.assertTrue(nscore4OrdersTabPage.isCVOfProductIsSetToZeroOrderCreationPage(SKU).contains("0"),"CV of product is not set to 0 on Order creation page");
		s_assert.assertTrue(nscore4OrdersTabPage.isQVOfProductIsSetToZeroOrderCreationPage(SKU).contains("0"),"QV of product is not set to 0 on Order creation page");
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertAll();

	}

	//QTest ID TC-1966 Orders>search by date
	@Test()
	public void testOrdersSearchByDate_1966(){
		String firstName = null;
		String lastName = null;
		String todayDate=null;
		String orderNumber=null;
		String fromdate=null;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		todayDate=dateFormat.format(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -6);
		Date fromDates=c.getTime();
		fromdate=dateFormat.format(fromDates);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_ORDER_DETAILS_BY_DATE,RFL_DB);
		firstName = (String) getValueFromQueryResult(randomConsultantAccountList, "FirstName");	
		lastName = (String) getValueFromQueryResult(randomConsultantAccountList, "LastName");
		orderNumber=(String) getValueFromQueryResult(randomConsultantAccountList, "OrderNumber");

		nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.clickBrowseOrdersLlink();
		nscore4HomePage.enterFirstNameOrdersPage(firstName);
		nscore4HomePage.enterLastNameOrdersPage(lastName);
		nscore4OrdersTabPage.enterFromDateAndToDate(fromdate,todayDate);
		nscore4HomePage.clickSearchIcon();
		s_assert.assertTrue(nscore4HomePage.verifyTheSearchResultsAreDisplayed(firstName, lastName),"First and last name not present in search results");
		s_assert.assertTrue(nscore4HomePage.verifyOrderDisplayedInSearchResult(orderNumber), "order is not present in search results");
		s_assert.assertAll();
	}

	//QTest ID TC-1967 Orders>items per page
	@Test()
	public void testOrdersItemsPerPage_1967(){
		String firstName = null;
		String lastName = null;
		String value="5";
		int intValue=0;
		int rowcount=0;
		nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.clickBrowseOrdersLlink();
		for(int i=0;i<=5;i++) {
			randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_ORDER_DETAILS_BY_DATE,RFL_DB);
			firstName = (String) getValueFromQueryResult(randomConsultantAccountList, "FirstName");	
			lastName = (String) getValueFromQueryResult(randomConsultantAccountList, "LastName");
			nscore4HomePage.enterFirstNameOrdersPage(firstName);
			nscore4HomePage.enterLastNameOrdersPage(lastName);
			nscore4HomePage.clickSearchIcon();
			rowcount=nscore4OrdersTabPage.getRowCountFromSearchResults();
			System.out.println(rowcount);
			if(rowcount>5) {
				nscore4HomePage.changeAmountOfItemPerPage(value);
				intValue=Integer.parseInt(value);
				break;
			}
			else {
				continue;
			}
		}
		s_assert.assertNotEquals(rowcount, intValue, "row count is not changed");
		s_assert.assertTrue(nscore4OrdersTabPage.getRowCountFromSearchResults()==intValue,"row count is not equal to the value entered");
		s_assert.assertTrue(nscore4HomePage.verifyTheSearchResultsAreDisplayed(firstName, lastName),"First and last name not present in search results");
		s_assert.assertAll();
	}

	@Test
	public void testPlaceOrderFromNSCore(){
		String nscore4URL="https://nsc4rfo."+driver.getEnvironment().toLowerCase()+".rodanandfields.com";
		driver.get(nscore4URL);
		login("admin", "skin123!");
		String accountNumber = null;
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		nscore4HomePage.addAndGetMultipleProductSKU("1");
		nscore4OrdersTabPage.clickApplyPaymentButton();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusAfterSubmitOrder(),"Order is not submitted after submit order");
		s_assert.assertAll();
	}

	// QTest ID TC-515 Verify Enrollment Orders for the price and QV value of each product in the Order and Total price and QV
	@Test(enabled=false)//needs fix
	public void testVerifyEnrollmentOrdersForThePriceAndQVValueOfEachProductInTheOrderAndTotalPriceAndQV_515() {
		String orderNumber = null;
		String orderDetails=null;
		String subtotalFromUI=null;
		String taxFromUI=null;
		String ShippingFromUI=null;
		String orderTotalFromUI=null;
		String subtotalFromDB=null;
		String taxFromDB=null;
		String ShippingFromDB=null;
		String orderTotalFromDB=null;
		String taxfromPulse=null;
		String shippingfromPulse=null;
		String orderTotalfromPulse=null;
		String date=null;
		String monthName=null;
		String dbIP1 = driver.getDBIP();
		String RFL_DB = driver.getDBNameRFL();
		List<Map<String, Object>> randomConsultantAccountList =  null;
		randomConsultantAccountList=DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_ORDER_DETAILS_RFO,orderNumber), RFL_DB,dbIP1);
		orderNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "OrderNumber");
		date = (String.valueOf(getValueFromQueryResult(randomConsultantAccountList, "CompletionDate")));
		subtotalFromDB = (String.valueOf(getValueFromQueryResult(randomConsultantAccountList, "SubTotal")));
		subtotalFromDB=nscore4HomePage.convertDBValueUptoTwoDecimal(subtotalFromDB);
		taxFromDB = (String.valueOf(getValueFromQueryResult(randomConsultantAccountList,"TotalTax")));
		taxFromDB=nscore4HomePage.convertDBValueUptoTwoDecimal(taxFromDB);
		ShippingFromDB = (String.valueOf(getValueFromQueryResult(randomConsultantAccountList, "ShippingCost")));
		ShippingFromDB=nscore4HomePage.convertDBValueUptoTwoDecimal(ShippingFromDB);
		orderTotalFromDB = (String.valueOf(getValueFromQueryResult(randomConsultantAccountList,"Total")));
		orderTotalFromDB=nscore4HomePage.convertDBValueUptoTwoDecimal(orderTotalFromDB);
		logger.info("Order number from DB is " + orderNumber);
		nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.enterOrderIDInInputField(orderNumber);

		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(),"This is not order details page");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");

		orderDetails=nscore4OrdersTabPage.getTaxOrderTotalAndSubtotalAppliedFromOrderDetailsPage();
		for(int i=0;i<=3;i++) {
			if(i==0) {
				subtotalFromUI=orderDetails.split("\n")[i];
				if(subtotalFromUI.contains(",")){
					subtotalFromUI=subtotalFromUI.replaceAll(",","");
				}
			}else if(i==1) {
				taxFromUI=orderDetails.split("\n")[i];
				if(taxFromUI.contains(",")){
					taxFromUI=taxFromUI.replaceAll(",","");
				}
			}else if(i==2) {
				ShippingFromUI=orderDetails.split("\n")[i];
				if(ShippingFromUI.contains(",")){
					ShippingFromUI=ShippingFromUI.replaceAll(",","");
				}
			}else if(i==3) {
				orderTotalFromUI=orderDetails.split("\n")[i];
				if(orderTotalFromUI.contains(",")){
					orderTotalFromUI=orderTotalFromUI.replaceAll(",","");
				}
			}
		}
		//Verify order details from NSC4
		s_assert.assertTrue(subtotalFromUI.contains(subtotalFromDB),"Expected Subtotal from DB is: "+subtotalFromDB+" But on UI is"+subtotalFromUI);
		s_assert.assertTrue(taxFromUI.contains(taxFromDB),"Expected Tax from DB is: "+taxFromDB+" But on UI is"+taxFromUI);
		s_assert.assertTrue(ShippingFromUI.contains(ShippingFromDB),"Expected Shipping from DB is: "+ShippingFromDB+" But on UI is"+ShippingFromUI);
		s_assert.assertTrue(orderTotalFromUI.contains(orderTotalFromDB),"Expected Order Total from DB is: "+orderTotalFromDB+" But on UI is"+orderTotalFromUI);

		nscore4HomePage.clickCustomerlabelOnOrderDetailPage();
		nscore4HomePage.clickPulseOnAccountOverviewPage();
		nscore4HomePage.dismissPulsePopup();
		nscore4HomePage.clickOrdersTabOnPulse();
		monthName=nscore4HomePage.getMonthNameFromDate(date);
		nscore4HomePage.selectMonthForOrderPeriod(monthName);
		nscore4HomePage.clickRunReport();
		nscore4HomePage.clickOrderDetailsOnPulseOrdersTab(orderNumber);
		taxfromPulse=nscore4HomePage.getTaxFromPulseOrderDetails();
		shippingfromPulse=nscore4HomePage.getShippingFromPulseOrderDetails();
		orderTotalfromPulse=nscore4HomePage.getOrderTotalFromPulseOrderDetails();

		//Verify order details from Pulse
		s_assert.assertTrue(taxfromPulse.contains(taxFromDB),"Expected Tax from DB is: "+taxFromDB+" But on Pulse UI is: "+taxfromPulse);
		s_assert.assertTrue(shippingfromPulse.contains(ShippingFromDB),"Expected Shipping from DB is: "+ShippingFromDB+" But on Pulse UI is: "+shippingfromPulse);
		s_assert.assertTrue(orderTotalfromPulse.contains(orderTotalFromDB),"Expected Order Total from DB is: "+orderTotalFromDB+" But on Pulse UI is: "+orderTotalfromPulse);
		s_assert.assertAll();
	}

	// QTest ID TC-923 NSC4 - Returns Multiple Items (VT Returns - Returns)
	@Test(enabled=false)//needs fix
	public void testNSC4ReturnsMultipleItemsVTReturnsReturns_923() {
		String orderNumber = null;
		String grandTotal = null;
		String accountNumber = null;
		String skuFromCart=null;
		String skuFromDB = null;
		Double vtrefundtotal1 = 0.0;
		Double sumOfRefundTotal = 0.0;
		Double orderTotal = 0.0;
		Double vtrefundtotal2 = 0.0;
		Double sumOfAllRefundTotal = 0.0;
		Double refundtotal = 0.0;
		Double sumOfVTRefundTotal = 0.0;
		List<Map<String, Object>> randomSKUList =  null;
		List<Map<String, Object>> orderDetailsList = null;
		String categoryRedefine=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		String categoryReverse=TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String categorySoothe=TestConstantsRFL.REGIMEN_NAME_SOOTHE;

		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),	RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is " + accountNumber);

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();

		/*
				randomSKUList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_SKU,RFL_DB);
				skuFromDB=(String) getValueFromQueryResult(randomSKUList, "SKU"); */
		nscore4HomePage.clickSearchSKU(categoryRedefine);
		nscore4HomePage.clickAddToOrder();
		nscore4HomePage.clickSearchSKU(categoryReverse);
		nscore4HomePage.clickAddToOrder();
		nscore4HomePage.clickSearchSKU(categorySoothe);
		nscore4HomePage.clickAddToOrder();
		for(int i=2;i<5;i++) {
			skuFromCart=nscore4OrdersTabPage.getProductSKUFromCart(i);
			if(i==2) {
				nscore4OrdersTabPage.updateQuantityOfProductOnBasisOfProductSKU(skuFromCart, "5");
			}else if(i==3) {
				nscore4OrdersTabPage.updateQuantityOfProductOnBasisOfProductSKU(skuFromCart, "10");
			}else {
				nscore4OrdersTabPage.updateQuantityOfProductOnBasisOfProductSKU(skuFromCart, "5");
			}
			driver.pauseExecutionFor(2000);
		}

		nscore4OrdersTabPage.clickApplyPaymentButton();
		nscore4OrdersTabPage.clickSubmitOrderBtn();

		int rowcount = nscore4HomePage.getCountOfSearchResultRows();
		for (int i = 1; i <= rowcount; i++) {
			nscore4OrdersTabPage.clickVTReturnOrder();
			nscore4HomePage.selectItemAndClickUpdateUnderRefundSection(i);
			nscore4OrdersTabPage.clickUpdateLink();
			nscore4OrdersTabPage.clickSubmitReturnBtn();
			vtrefundtotal1 = Double.parseDouble(nscore4OrdersTabPage.getRefundTotal());
			sumOfVTRefundTotal = sumOfVTRefundTotal + vtrefundtotal1;
			nscore4OrdersTabPage.goToOriginalOrder();
			driver.pauseExecutionFor(1000);
		}

		nscore4OrdersTabPage.clickVTReturnOrder();
		String shippingPrice = nscore4OrdersTabPage.getPriceDetailsByLabel("Shipping Available:");
		nscore4OrdersTabPage.enterRefundedShipping(shippingPrice);
		nscore4OrdersTabPage.selectCheckboxForReturnTax();
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		vtrefundtotal2 = Double.parseDouble(nscore4OrdersTabPage.getRefundTotal());
		orderNumber = nscore4OrdersTabPage.getorderNumber();
		nscore4OrdersTabPage.goToOriginalOrder();
		for (int i = 1; i <= rowcount; i++) {
			nscore4OrdersTabPage.clickReturnOrderOrderDetailsPage();
			nscore4OrdersTabPage.selectItemAndClickUpdateUnderRefundSection(i);
			nscore4OrdersTabPage.clickUpdateLink();
			nscore4OrdersTabPage.clickSubmitReturnBtn();
			refundtotal = Double.parseDouble(nscore4OrdersTabPage.getRefundTotal());
			sumOfRefundTotal = sumOfRefundTotal + refundtotal;
			nscore4OrdersTabPage.goToOriginalOrder();
		}

		sumOfAllRefundTotal = sumOfRefundTotal + vtrefundtotal2 + sumOfVTRefundTotal;
		s_assert.assertTrue(nscore4OrdersTabPage.isVTReturnLinksDisabled().contains("void"),"Return and VT return order are clickable");
		s_assert.assertTrue(nscore4OrdersTabPage.isReturnLinksDisabled().contains("void"),"Return and VT return order are clickable");
		orderTotal = Double.parseDouble(nscore4OrdersTabPage.getOriginalTotalOrderAmount());
		s_assert.assertEquals(orderTotal, sumOfAllRefundTotal,"Expected orderTotal is:"+orderTotal+" But Actual on UI is: "+sumOfAllRefundTotal);
		orderDetailsList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_ORDER_DETAILS, orderNumber), RFL_DB);
		grandTotal = String.valueOf(getValueFromQueryResult(orderDetailsList, "GrandTotal"));
		double grandTotalD = Double.parseDouble(grandTotal);
		s_assert.assertEquals(grandTotalD, orderTotal,"Order total from DB is:" + grandTotal + " different from UI is:" + orderTotal);
		s_assert.assertAll();
	}		

	//QTest ID TC-921 NSC4 - Returns (Returns - VT Returns)
	@Test
	public void testNSC4ReturnsReturnsVTReturns_921(){
		Double refundtotal = 0.0;
		Double sumOfRefundTotal= 0.0;
		Double orderTotal=0.0;
		Double vtrefundtotal=0.0;
		Double sumofAllRefund=0.0;
		String accountNumber = null;
		List<Map<String, Object>> orderDetailsList=null;
		String orderNumber=null;
		String grandTotal=null;
		String categoryRedefine=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		String categoryReverse=TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String categorySoothe=TestConstantsRFL.REGIMEN_NAME_SOOTHE;

		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();

		//nscore4HomePage.addAndGetMultipleProductSKU("1");
		nscore4HomePage.clickSearchSKU(categoryRedefine);
		nscore4HomePage.clickAddToOrder();
		nscore4HomePage.clickSearchSKU(categoryReverse);
		nscore4HomePage.clickAddToOrder();
		nscore4HomePage.clickSearchSKU(categorySoothe);
		nscore4HomePage.clickAddToOrder();

		nscore4OrdersTabPage.clickApplyPaymentButton();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		//return order
		int rowcount=nscore4HomePage.getCountOfSearchResultRows();
		for(int i=rowcount;i>=1;i--) {
			nscore4OrdersTabPage.clickReturnOrderOrderDetailsPage();
			nscore4OrdersTabPage.selectItemAndClickUpdateUnderRefundSection(i);
			nscore4OrdersTabPage.clearShippingAndHandlingRefundedAmountsAndUpdate();
			nscore4OrdersTabPage.selectReturnReason();
			nscore4OrdersTabPage.selectReturnType();
			nscore4OrdersTabPage.clickSubmitReturnBtn();
			refundtotal=Double.parseDouble(nscore4OrdersTabPage.getRefundTotal());
			sumOfRefundTotal=sumOfRefundTotal+refundtotal;
			nscore4OrdersTabPage.goToOriginalOrder();

		}
		nscore4OrdersTabPage.clickVTReturnOrder();
		String shippingPrice = nscore4OrdersTabPage.getPriceDetailsByLabel("Shipping Available:");
		nscore4OrdersTabPage.enterRefundedShipping(shippingPrice);
		nscore4OrdersTabPage.selectCheckboxForReturnTax();
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.selectReturnReason();
		nscore4OrdersTabPage.selectReturnType();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		vtrefundtotal=Double.parseDouble(nscore4OrdersTabPage.getRefundTotal());
		sumofAllRefund=vtrefundtotal+sumOfRefundTotal;
		orderNumber=nscore4OrdersTabPage.getorderNumber();
		nscore4OrdersTabPage.goToOriginalOrder();
		s_assert.assertTrue(nscore4OrdersTabPage.isVTReturnLinksDisabled().contains("void"),"Return and VT return order are clickable");
		s_assert.assertTrue(nscore4OrdersTabPage.isReturnLinksDisabled().contains("void"),"Return and VT return order are clickable");
		orderTotal=Double.parseDouble(nscore4OrdersTabPage.getOriginalTotalOrderAmount());
		orderDetailsList= DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_ORDER_DETAILS, orderNumber),RFL_DB);
		grandTotal = String.valueOf(getValueFromQueryResult(orderDetailsList, "GrandTotal"));
		double grandTotalD=Double.parseDouble(grandTotal);
		s_assert.assertEquals(grandTotalD, orderTotal,"Order total from DB is:"+grandTotal+" different from UI is:"+orderTotal);
		s_assert.assertAll();
	}	

}