package com.rf.test.website.nscore4;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.ExcelReader;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.pages.website.heirloom.StoreFrontHeirloomHomePage;
import com.rf.pages.website.nscore.NSCore4AdminPage;
import com.rf.pages.website.nscore.NSCore4HomePage;
import com.rf.pages.website.nscore.NSCore4LoginPage;
import com.rf.pages.website.nscore.NSCore4MobilePage;
import com.rf.pages.website.nscore.NSCore4OrdersTabPage;
import com.rf.pages.website.nscore.NSCore4ProductsTabPage;
import com.rf.pages.website.nscore.NSCore4SitesTabPage;
import com.rf.test.website.RFNSCoreWebsiteBaseTest;

public class NSCore4AccountTest extends RFNSCoreWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(NSCore4AccountTest.class.getName());

	private NSCore4HomePage nscore4HomePage;
	private NSCore4LoginPage nscore4LoginPage;
	private NSCore4MobilePage nscore4MobilePage;
	private NSCore4OrdersTabPage nscore4OrdersTabPage;
	private NSCore4SitesTabPage nscore4SitesTabPage;
	private NSCore4AdminPage nscore4AdminPage;
	private NSCore4ProductsTabPage nscore4ProductsTabPage;
	String RFL_DB = null;
	List<Map<String, Object>> randomRCAccountList =  null;
	List<Map<String, Object>> randomConsultantAccountList =  null;
	List<Map<String, Object>> randomPCAccountList =  null;

	public NSCore4AccountTest() {
		nscore4HomePage = new NSCore4HomePage(driver);
		nscore4SitesTabPage = new NSCore4SitesTabPage(driver);
		nscore4ProductsTabPage = new NSCore4ProductsTabPage(driver);
		nscore4OrdersTabPage = new NSCore4OrdersTabPage(driver);
		nscore4AdminPage = new NSCore4AdminPage(driver);
	}

	@BeforeClass
	public void executeCommonQuery(){
		RFL_DB = driver.getDBNameRFL();
		randomRCAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS,RFL_DB);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
		randomPCAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_DETAILS_RFL,RFL_DB);
	}

	//QTest ID TC-239 NSC4_AdministratorLogin_LogingLogout
	@Test(priority=1)
	public void testAdministrationLoginLogout_239(){
		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		nscore4LoginPage = nscore4HomePage.clickLogoutLink();		
		s_assert.assertTrue(nscore4LoginPage.isLoginButtonPresent(),"Login page has not appeared after logout");
		loginToNSC4();
		s_assert.assertAll();
	}

	//QTest ID TC-240 NSC4_AdministratorLogin_InvalidLoging
	@Test(priority=2)
	public void testAdministratorLoginInvalidLogin_240(){
		nscore4LoginPage = nscore4HomePage.clickLogoutLink();	
		login("abcd", "test1234!");
		s_assert.assertTrue(nscore4LoginPage.isLoginCredentailsErrorMsgPresent(), "Login credentials error msg is not displayed");
		loginToNSC4();
		s_assert.assertAll();
	}

	//QTest ID TC-266 NSC4_AccountsTab_AccountLookup
	@Test(priority=3)
	public void testAccountsTabAccountLookup_266(){
		String accountNumber = null;
		String firstName = null;
		String lastName = null;
		logger.info("DB is "+RFL_DB);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACCOUNT_DETAILS,RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");	
		firstName = (String) getValueFromQueryResult(randomConsultantAccountList, "FirstName");	
		lastName = (String) getValueFromQueryResult(randomConsultantAccountList, "LastName");	
		logger.info("Account number from DB is "+accountNumber);
		logger.info("First name from DB is "+firstName);
		logger.info("Last name from DB is "+lastName);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(firstName, lastName), "First and last name is not present in search result,when searched with account number");
		nscore4HomePage.enterFirstAndLastNameInCorrespondingFields(firstName, lastName);
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(firstName, lastName), "First and last name is not present in search result,when searched with first & last name");
		s_assert.assertAll();
	}

	//QTest ID TC-273 NSC4_AccountsTab_OverviewAutoshipsEdit
	@Test(priority=4)
	public void testAccountsTabOverviewAutoshipsEdit_273(){
		String accountNumber = null;  
		String SKU = null;
		do{
			accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
			logger.info("Account number from DB is "+accountNumber);
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(accountNumber);  
			if(nscore4HomePage.isEditPresentForConsultantReplenishmentPresent() && nscore4HomePage.isEditPresentForPulseMonthlySubscriptionPresent()==true){
				break;
			}else{
				randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
				nscore4HomePage.clickTab("Accounts");
			}
		}while(true);
		nscore4HomePage.clickConsultantReplenishmentEdit();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		//nscore4HomePage.addPaymentMethod(newBillingProfileName, lastName, nameOnCard, cardNumber);
		//nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.clickSaveAutoshipTemplate();
		s_assert.assertTrue(nscore4HomePage.isAddedProductPresentInOrderDetailPage(SKU), "SKU = "+SKU+" is not present in the Order detail page");
		nscore4HomePage.clickCustomerlabelOnOrderDetailPage();
		nscore4HomePage.clickPulseMonthlySubscriptionEdit();
		String updatedQuantity = nscore4HomePage.updatePulseProductQuantityAndReturnValue();
		logger.info("updated pulse product quantity = "+updatedQuantity);
		//nscore4HomePage.addPaymentMethod(newBillingProfileName, lastName, nameOnCard, cardNumber);
		//nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.clickSaveAutoshipTemplate();
		s_assert.assertTrue(nscore4HomePage.getQuantityOfPulseProductFromOrderDetailPage().contains(updatedQuantity), "updated pulse product qunatity is not present in the Order detail page");
		s_assert.assertAll();
	}

	//QTest ID TC-302 NSC4_MobileTab_ HeadlineNews
	@Test(priority=5)
	public void testMobileTabHeadLineNews_302(){
		nscore4MobilePage=nscore4HomePage.clickMobileTab();
		//click headlines news link
		nscore4MobilePage.clickHeadLineNewsLink();
		//verify 'Browse HeadLine News' Page comes up?
		s_assert.assertTrue(nscore4MobilePage.validateBrowseHeadLineNewsPage(), "Browse 'HeadLineNews' Page is not displayed");
		s_assert.assertAll();
	}

	//QTest ID TC-303 NSC4_MobileTab_ R+FInTheNews
	@Test(priority=6)
	public void testMobileTabRFInNews_303(){
		nscore4MobilePage=nscore4HomePage.clickMobileTab();
		//click R+F In the news link
		nscore4MobilePage.clickRFInNewsLink();
		//verify 'Browse RF In News' Page comes up?
		s_assert.assertTrue(nscore4MobilePage.validateBrowseRFInNewsPage(), "Browse 'RF In News' Page is not displayed");
		s_assert.assertAll();
	}

	//QTest ID TC-287 NSC4_OrdersTab_OrderIdSearch
	@Test(priority=8)
	public void testOrdersTab_OrderIdSearch_287(){
		String accountNumber = null;
		logger.info("DB is "+RFL_DB);
		String orderID=null;
		for(int i=1;i<=5;i++){
			try{
				randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
				accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
				logger.info("Account number from DB is "+accountNumber);
				nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
				nscore4HomePage.clickGoBtnOfSearch(accountNumber);
				orderID = nscore4HomePage.getOrderIDFromOverviewPage();
				break;
			}
			catch(Exception e){
				nscore4HomePage.clickAccountsTab();
			}
		}
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.enterOrderIDInInputField(orderID);
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(),"This is not order details page");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");
		s_assert.assertAll();
	}

	//QTest ID TC-288 NSC4_OrdersTab_OrderAdvancedSearch
	@Test(priority=9)
	public void testOrdersTab_OrderAdvancedSearch_288(){
		String orderNumber = null;
		String firstName = null;
		String lastName = null;
		List<Map<String, Object>> orderDetailsList =  null;
		logger.info("DB is "+RFL_DB);
		orderDetailsList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_ORDER_DETAILS_BY_DATE,RFL_DB);
		firstName = (String) getValueFromQueryResult(orderDetailsList, "FirstName");	
		lastName = (String) getValueFromQueryResult(orderDetailsList, "LastName");
		orderNumber = (String) getValueFromQueryResult(orderDetailsList, "OrderNumber");
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.enterOrderIDInInputField(orderNumber);
		String completeNameFromUI = nscore4OrdersTabPage.getCustomerNameFromOrderDetailsPage();
		s_assert.assertTrue(completeNameFromUI.contains(firstName), "Expected first name"+firstName+" but actual on UI is "+completeNameFromUI);
		s_assert.assertTrue(completeNameFromUI.contains(lastName), "Expected last name"+lastName+" but actual on UI is "+completeNameFromUI);
		s_assert.assertAll();
	}


	//QTest ID TC-513 NSC4_OrdersTab_NewOrder
	@Test(priority=12)
	public void testOrdersTab_NewOrder_513(){
		String accountNumber = null;
		List<Map<String, Object>> completeNameList =  null;
		String SKU = null;
		logger.info("DB is "+RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.clickStartANewOrderLink();
		completeNameList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_SPONSER_DETAILS_FROM_SPONSER_ACCOUNT_NUMBER, accountNumber),RFL_DB);
		String firstNameDB = String.valueOf(getValueFromQueryResult(completeNameList, "FirstName"));
		String lastNameDB = String.valueOf(getValueFromQueryResult(completeNameList, "LastName"));
		String completeNameDB = firstNameDB+" "+lastNameDB;
		logger.info("Complete name from DB is: "+completeNameDB);
		nscore4OrdersTabPage.enterAccountNameAndClickStartOrder(completeNameDB, accountNumber);
		SKU = nscore4HomePage.addAndGetProductSKU("1");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusAfterSubmitOrder(),"Order is not submitted after submit order");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(),"This is not order details page");
		s_assert.assertAll();
	}

	//QTest ID TC-290 NSC4_OrdersTab_BrowseOrders
	@Test(enabled=false)
	public void testOrdersTabBrowseOrders_290(){
		String accountNumber = null;
		List<Map<String, Object>> randomAccountNumberList =  null;
		logger.info("DB is "+RFL_DB);
		//	accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		randomAccountNumberList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_USERS_WITH_PENDING_ORDERS_RFL,RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomAccountNumberList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
		//click Browse Orders link
		nscore4OrdersTabPage.clickBrowseOrdersLlink();
		//Select Status as pending in filter
		nscore4OrdersTabPage.selectStatusDD("Pending");
		//		//Select Type as PC in filter
		//		nscore4OrdersTabPage.selectTypeDD("PC");
		//click 'GO' Button
		nscore4OrdersTabPage.clickGoSearchBtn();
		//nscore4OrdersTabPage.clickGoSearchBtn();
		//Verify all the results from the table satisfy the previous filter(s)-1.Order Status
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusRow(),"Atleast 1 of the status is not 'Pending'");
		s_assert.assertAll();
	}

	//QTest ID TC-512 NSC4_AccountsTab_OverviewPlaceNew Order
	@Test(priority=15)
	public void testAccountsTab_OverviewPlaceNewOrder_512(){
		String accountNumber = null;
		List<Map<String, Object>> randomSKUList =  null;
		String SKU = null;
		nscore4OrdersTabPage =new NSCore4OrdersTabPage(driver);
		logger.info("DB is "+RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusAfterSubmitOrder(),"Order is not submitted after submit order");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(),"This is not order details page");
		s_assert.assertAll();
	}

	//QTest ID TC-270 NSC4_AccountsTab_OverviewStatusChange
	@Test(priority=16)
	public void testAccountsTab_OverviewStatusChange_270(){
		String accountNumber = null;
		String afterStatusShouldBe = null;
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//get the status before change
		String beforeStatus=nscore4HomePage.getStatus();
		logger.info("status before change is "+beforeStatus);
		//click the status link and change the status-
		nscore4HomePage.clickStatusLink();
		//change the status and save 
		if(beforeStatus.equalsIgnoreCase("inactive")){
			nscore4HomePage.changeStatusDD("Active");
			afterStatusShouldBe="active";
			logger.info("Active status selected");
		}
		else{
			nscore4HomePage.changeStatusDD("Inactive");
			logger.info("Inactive status selected");
			afterStatusShouldBe="inactive";
		}
		nscore4HomePage.clickSaveStatusBtn();
		String afterStatus=nscore4HomePage.getStatus();
		assertTrue(afterStatus.equalsIgnoreCase(afterStatusShouldBe),"account sttatus not changed succesfully");
		s_assert.assertAll();
	}

	//QTest ID TC-242 NSC4_SitesTab_nsCorporate_CorporateAddEditDeleteNews
	@Test(enabled=false)//should be tested manually
	public void testNSC4SitesTabNSCorporateAddEditDeleteNews_242(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum2 = CommonUtils.getRandomNum(10000, 1000000);
		String sites = "Sites";
		String title = "For Automation"+randomNum;
		String newTitle = "For Automation"+randomNum2;
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickCorporateLink();
		nscore4SitesTabPage.clickAddNewsLink();
		nscore4SitesTabPage.enterTitleForAddNews(title);
		nscore4SitesTabPage.checkIsActiveChkBoxForNewsTitle();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("News saved successfully"), "Expected saved message is: News saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		int noOfOptionsInSizePageDD = nscore4SitesTabPage.getSizeOfOptinsFromPageSizeDD();
		nscore4SitesTabPage.clickAndSelectOptionInPageSizeDD(noOfOptionsInSizePageDD);
		s_assert.assertTrue(nscore4SitesTabPage.isTitleNamePresentInAnnouncementsList(title), "Title name is not present in announcement list");
		nscore4SitesTabPage.clickTitleNamePresentInAnnouncementsList(title);
		nscore4SitesTabPage.enterTitleForAddNews(newTitle);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("News saved successfully"), "Expected saved message is: News saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		noOfOptionsInSizePageDD = nscore4SitesTabPage.getSizeOfOptinsFromPageSizeDD();
		nscore4SitesTabPage.clickAndSelectOptionInPageSizeDD(noOfOptionsInSizePageDD);
		s_assert.assertTrue(nscore4SitesTabPage.isTitleNamePresentInAnnouncementsList(newTitle), "Title name is not present in announcement list");
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(newTitle);
		nscore4SitesTabPage.clickDeactivateSelectedLink();
		s_assert.assertTrue(nscore4SitesTabPage.getTitleStatus(newTitle).contains("Inactive"), "Expected title status is: Inactive but actual on UI: "+nscore4SitesTabPage.getTitleStatus(newTitle));
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(newTitle);
		nscore4SitesTabPage.clickActivateSelectedLink();
		s_assert.assertTrue(nscore4SitesTabPage.getTitleStatus(newTitle).contains("Active"), "Expected title status is: Active but actual on UI is: "+nscore4SitesTabPage.getTitleStatus(newTitle));
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(newTitle);
		nscore4SitesTabPage.clickDeleteSelectedLink();
		s_assert.assertFalse(nscore4SitesTabPage.isTitleNamePresentInAnnouncementsList(title), "Title name is present in announcement list after delete");
		s_assert.assertAll();
	}

	//QTest ID TC-244 NSC4_SitesTab_nsCorporate_CorporateSiteDetailsEditSite
	@Test(enabled=false,priority=19)
	public void testNSC4SitesTabNSCorporateSiteDetailEditSite_244(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String sites = "Sites";
		String siteDetails = "Site Details";
		String newSiteName = "Auto"+randomNum;
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfCorporate(siteDetails);
		nscore4SitesTabPage.enterSiteNameForSiteDetails(newSiteName);
		nscore4SitesTabPage.checkActiveChkBoxForSiteDetails();
		nscore4SitesTabPage.clickSaveBtnOnSiteDetails();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxtForSite().contains("Site saved successfully"), "Expected saved message is: Site saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxtForSite());
		nscore4SitesTabPage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfCorporate(siteDetails);
		nscore4SitesTabPage.enterSiteNameForSiteDetails("Corporate");
		nscore4SitesTabPage.uncheckActiveChkBoxForSiteDetails();
		nscore4SitesTabPage.clickSaveBtnOnSiteDetails();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxtForSite().contains("Site saved successfully"), "Expected saved message is: Site saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxtForSite());
		s_assert.assertAll();
	}


	//QTest ID TC-278 NSC4_AccountsTab_ ShippingProfilesAddEditDefaultDelete
	@Test(priority=21)
	public void testAccountsTab_ShippingProfilesAddEditDefaultDelete_278(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);

		String newShippingProfileName = "newSP"+randomNum;
		String attentionCO ="SP";
		String addressLine1 =TestConstantsRFL.ADDRESS_LINE_1_US;
		String zipCode= TestConstantsRFL.POSTAL_CODE_US;
		String emailID = null;
		String accountNumber = null;

		nscore4OrdersTabPage =new NSCore4OrdersTabPage(driver);
		logger.info("DB is "+RFL_DB);

		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");


		//			accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//NAavigate to Billing & Shipping Profile section
		nscore4HomePage.clickBillingAndShippingProfileLink();
		int totalNoOfAddressBeforeAdd = nscore4HomePage.getTotalNoOfShippingProfiles();
		//click 'Add' for the Shipping profile section
		nscore4HomePage.clickShippingProfileAddLink();
		//Enter all Information regarding new Shipping Profile-
		nscore4HomePage.addANewShippingProfile(newShippingProfileName, attentionCO, addressLine1, zipCode);
		//click 'SAVE ADDRESS BTN'
		nscore4HomePage.clickSaveAddressBtn();
		nscore4HomePage.acceptQASPopup();
		nscore4HomePage.refreshPage();
		//verify newly created shipping profile created?
		s_assert.assertTrue(nscore4HomePage.isNewlyCreatedShippingProfilePresent(newShippingProfileName),"Newly created Shipping Profile is not Present");
		//click on 'Set As Default Address' on the newly created profile
		nscore4HomePage.clickSetAsDefaultAddressForNewlyCreatedProfile(newShippingProfileName);
		//Verify profile is now default?
		s_assert.assertTrue(nscore4HomePage.validateNewlyCreatedShippingProfileIsDefault(newShippingProfileName),"Newly created Shipping Profile is Not Marked-DEFAULT");
		//Delete the newly created profile-
		nscore4HomePage.deleteAddressNewlyCreatedProfile(newShippingProfileName);
		int totalNoOfAddressAfterDelete = nscore4HomePage.getTotalNoOfShippingProfiles();
		s_assert.assertTrue(totalNoOfAddressAfterDelete ==  totalNoOfAddressBeforeAdd, "Expected count of shipping profile after delete is: "+totalNoOfAddressBeforeAdd+" Actual on UI is: "+totalNoOfAddressAfterDelete);
		s_assert.assertAll();    
	}

	//QTest ID TC-277 NSC4_AccountsTab_ BillingProfilesAddEditDefaultDelete
	@Test(priority=22)
	public void testAccountsTab_BillingProfilesAddEditDefaultDelete_277(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum2 = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = "RFAutoNSCore4"+randomNum;
		String lastName = "lN";
		String nameOnCard = "rfTestUser";
		String cardNumber =  "4747474747474747";
		String addressLine1 = TestConstantsRFL.ADDRESS_LINE1;
		String zipCode = TestConstantsRFL.POSTAL_CODE;
		String accountNumber = null;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum2;
		String lastNameProfileName = "lN";		

		logger.info("DB is "+RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//NAavigate to Billing & Shipping Profile section
		nscore4HomePage.clickBillingAndShippingProfileLink();
		int totalBillingProfilesBeforeAddingNew = nscore4HomePage.getTotalBillingProfiles();
		//click 'Add' for the billing profile section
		nscore4HomePage.clickBillingProfileAddLink();
		//Enter all the Information regarding New Billing Profile
		nscore4HomePage.addANewBillingProfile(newBillingProfileName, lastName, nameOnCard, cardNumber,addressLine1,zipCode,CVV);
		//click 'SAVE PAYMENT METHOD'
		nscore4HomePage.clickSavePaymentMethodBtn();
		//Verify that the new profile got created?
		s_assert.assertTrue(nscore4HomePage.getTotalBillingProfiles()==totalBillingProfilesBeforeAddingNew+1,"Newly created Billing Profile is not Present");
		//click on 'Set As Default Payment Method' on the newly created profile
		nscore4HomePage.clickSetAsDefaultPaymentMethodForNewlyCreatedProfile();
		//Verify profile is now default?
		s_assert.assertTrue(nscore4HomePage.validateNewlyCreatedBillingProfileIsDefault(),"Newly created Billing Profile is Not Marked-DEFAULT");
		//Edit Newly created billing profile
		nscore4HomePage.clickEditBillingProfileOnBasisOfProfileName(newBillingProfileName);
		nscore4HomePage.editPaymentProfile(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV);
		nscore4HomePage.clickSavePaymentMethodBtn();
		//Delete the newly created profile-
		nscore4HomePage.deletePaymentMethodNewlyCreatedProfile();
		s_assert.assertTrue(nscore4HomePage.isBillingProfileDeleted(),"Billing profile is not deleted successfully");
		s_assert.assertAll();
	} 

	//QTest ID TC-298 NSC4_AdminTab_ Users_AddEditRoles
	@Test(priority=23)
	public void testAdminTabUsersAddEditRoles_298(){
		int randomNum = CommonUtils.getRandomNum(10, 100);
		String roleName ="SampleRole";
		nscore4AdminPage=nscore4HomePage.clickAdminTab();
		//click Role->Add new role
		nscore4AdminPage.clickRolesLink();
		nscore4AdminPage.clickAddNewRoleLink();
		//Enter New Role Name and Save
		nscore4AdminPage.enterRoleName(roleName+randomNum);
		nscore4AdminPage.clickSaveBtn();
		//Verify that the new role got created and displayed as a link in Roles list?
		s_assert.assertTrue(nscore4AdminPage.validateNewRoleListedInRolesList(roleName+randomNum),"NEW Role Name is not listed in the roles list");
		s_assert.assertAll();
	}

	//QTest ID TC-258 NSC4_SitesTab_nsDistributor_BasePWSSitePagesAddEditNewSite
	@Test(enabled=false,priority=24)
	public void testBasePWSPagesAddEditNewSite_258(){
		int randomNumb = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		String sublinkName = "Site Pages";
		String pageName = "AutomationPage"+randomNumb;
		String pageTitle = "AutoTitle"+randomNumber;
		String pageDescription = "AutoDesc"+randomNumbers;
		String pageURL = "/Pages/About/"+pageName;
		String pageKeyword = "Unique"+randomNumb;
		String templateView = "Two Piece View";

		logger.info("DB is "+RFL_DB);
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(sublinkName);
		nscore4SitesTabPage.clickAddNewPageLink();
		nscore4SitesTabPage.enterNewPageDetails(pageName,pageTitle,pageDescription,pageURL,pageKeyword,templateView);
		nscore4SitesTabPage.clickSaveButtonForNewCreatedPage();
		s_assert.assertTrue(nscore4SitesTabPage.getPageSavedSuccessfullyTxtForSite().contains("Page saved successfully!"), "Expected saved message is: Site saved successfully but actual on UI is: "+nscore4SitesTabPage.getPageSavedSuccessfullyTxtForSite());
		int noOfOptionsInSizePageDD = nscore4SitesTabPage.getSizeOfOptinsFromPageSizeDD();
		nscore4SitesTabPage.clickAndSelectOptionInPageSizeDD(noOfOptionsInSizePageDD);
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(pageName),"Newly created page name is not present on site page list");
		nscore4SitesTabPage.clickPageNameOnSitePageList(pageName);
		nscore4SitesTabPage.checkActiveCheckboxOnSitePage();
		nscore4SitesTabPage.clickSaveButtonForNewCreatedPage();
		s_assert.assertTrue(nscore4SitesTabPage.getPageSavedSuccessfullyTxtForSite().contains("Page saved successfully!"), "Expected Active checkbox unchecked message is: Site saved successfully but actual on UI is: "+nscore4SitesTabPage.getPageSavedSuccessfullyTxtForSite());
		s_assert.assertAll();
	}

	//QTest ID TC-291 NSC4_ProductsTab_ NewUpdateCatalog
	@Test(priority=25)
	public void testProductsTab_NewUpdateCatalog_291(){
		int randomNumber =  CommonUtils.getRandomNum(10000, 1000000);
		int randomNum =  CommonUtils.getRandomNum(1000, 100000);
		String catalogName = "Test"+randomNumber;
		String updatedCatalogInfo = "Test"+randomNum;
		nscore4HomePage.clickTab("Products");
		nscore4ProductsTabPage.clickCreateANewCatalogLink();
		nscore4ProductsTabPage.enterCatalogInfo(catalogName);
		nscore4ProductsTabPage.clickSaveCatalogBtn();
		nscore4HomePage.addAndGetProductSKUForCatalog();//(String) getValueFromQueryResult(randomSKUList, "SKU");;
		nscore4ProductsTabPage.clickSaveCatalogBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.isSuccessMessagePresent(),"Success message is not displayed");
		nscore4ProductsTabPage.clickCatalogManagementLink();
		s_assert.assertTrue(nscore4ProductsTabPage.isNewCatalogPresentInList(catalogName),"new catalog is not present in the catalog list");
		nscore4ProductsTabPage.clicknewlyCreatedCatalogName(catalogName);
		nscore4ProductsTabPage.enterCatalogInfo(updatedCatalogInfo);
		nscore4ProductsTabPage.clickSaveCatalogBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.isSuccessMessagePresent(),"Success message is not displayed");
		nscore4ProductsTabPage.clickCatalogManagementLink();
		s_assert.assertTrue(nscore4ProductsTabPage.isNewCatalogPresentInList(updatedCatalogInfo),"catalog info is not updated in the catalog list");
		s_assert.assertAll();
	}

	//QTest ID TC-245 NSC4_SitesTab_nsCorporate_CorporateReplicateSites
	@Test(enabled=false)//should be tested manually
	public void testNSC4SitesTabNSCorporateReplicateSites_245(){
		String sites = "Sites";
		String replicatedSites = "Replicated Sites";
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfCorporate(replicatedSites);
		s_assert.assertTrue(nscore4SitesTabPage.isReplicatedSitesHeaderPresent(), "Replicated Sites is not present");
		s_assert.assertAll();
	}

	// QTest ID TC-297 NSC4_AdminTab_ Users_AddEditUsers
	@Test(priority=30)
	public void testAdminTab_Users_AddEditUsers_297(){
		int randomNum = CommonUtils.getRandomNum(100, 10000);
		String firstName = "firstName"+randomNum;
		String lastName = "Ln"+randomNum;
		String userName = "userName"+randomNum;
		System.out.println(userName+"   username");
		String password = "abc"+randomNum;
		String confirmPassword = password;

		nscore4HomePage.clickTab("Admin");
		nscore4AdminPage.clickAddNewUser();
		nscore4AdminPage.enterInfoAtAddUserPage(firstName,lastName,userName,password,confirmPassword);
		nscore4AdminPage.selectAllSitesAndSave();
		s_assert.assertTrue(nscore4AdminPage.isUserSavedSuccessfully(),"user is saved successfully msg not present");
		s_assert.assertTrue(nscore4AdminPage.isNewUserPresentInList(userName),"new user is not present in the list");
		nscore4AdminPage.clickOnNewUser();
		nscore4AdminPage.selectStatus("InActive");
		s_assert.assertTrue(nscore4AdminPage.isUserSavedSuccessfully(),"user is saved successfully msg not present");
		s_assert.assertAll();

	}

	// QTest ID TC-299 NSC4_AdminTab_ LystTypes
	@Test(priority=31)
	public void testAdminTab_LystTypes_299(){
		int randomNum = CommonUtils.getRandomNum(100, 1000);
		String newAddedListValue = "test"+randomNum;
		nscore4HomePage.clickTab("Admin");
		nscore4AdminPage.clickListTypesLink("Account Note Category");
		nscore4AdminPage.clickAddNewListValue();
		int numberofListPresent = nscore4AdminPage.getTotalNumberOfList();
		nscore4AdminPage.enterValueAndSave(newAddedListValue,numberofListPresent);
		nscore4AdminPage.clickListTypesLink("Account Note Category");
		s_assert.assertTrue(nscore4AdminPage.isNewListAdded(newAddedListValue,numberofListPresent),"new list is not being added");
		nscore4AdminPage.deleteSavedListTypeAndSave(numberofListPresent);
		nscore4AdminPage.handleAlertPop();
		s_assert.assertTrue(nscore4AdminPage.verifyListDeleted(numberofListPresent),"list is not being deleted");
		s_assert.assertAll();
	}

	//QTest ID TC-269 NSC4_AccountsTab_OverviewChangeAndStatusHistory
	@Test(priority=32)
	public void testAccountsTab_OverviewChangeAndStatusHistory_269(){
		String accountNumber = null;
		RFL_DB = driver.getDBNameRFL();
		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//Overview tab-> click 'Policies Change History' link
		nscore4HomePage.clickPoliciesChangeHistoryLink();
		//verify 'Policies Change History' Page is displayed with respective columns?
		s_assert.assertTrue(nscore4HomePage.validatePoliciesChangeHistoryPageDisplayedWithRespectiveColumns(),"'Policis Change History' Page Is Not displayed with the respective columns");
		//click status history
		nscore4HomePage.clickStatusHistoryLink();
		//verify 'Status History' Page is displayed with respective columns?
		s_assert.assertTrue(nscore4HomePage.validateStatusHistoryPageDisplayedWithRespectiveColumns(),"'Status History' Page Is Not displayed with the respective columns");
		s_assert.assertAll();
	}

	//QTest ID TC-241 NSC4_SitesTab_nsCorporate_CorporateNewEditDeleteEvent
	@Test(enabled=false)//should be tested manually
	public void testNSC4SitesTabNSCorporateNewEditDeleteEvent_241(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum2 = CommonUtils.getRandomNum(10000, 1000000);
		String sites = "Sites";
		String subject = "For Automation"+randomNum;
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinksSitesTab("nsCorporate");
		//nscore4SitesTabPage.clickSubLinkOfNSCorporate("Auto923175");
		nscore4SitesTabPage.clickAddEventLink();
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		nscore4SitesTabPage.clickEventNamePresentAtCalendar(subject);
		subject = "For Automation"+randomNum2;
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		nscore4SitesTabPage.clickEventNamePresentAtCalendar(subject);
		nscore4SitesTabPage.clickDeleteBtnForEvent();
		nscore4SitesTabPage.clickOKBtnOfJavaScriptPopUp();
		s_assert.assertFalse(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is present at calendar ");
		s_assert.assertAll();
	}

	@Test(enabled=false)
	public void testOrdersTab_NewOrder_RC() {		
		/*for(int i=0; i<200; i++){*/
		/*System.out.println("Count is: " + i);*/
		String accountNumber = null;
		List<Map<String, Object>> completeNameList =  null;
		String SKU = null;
		logger.info("DB is "+RFL_DB);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS,RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4OrdersTabPage = nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.clickStartANewOrderLink();
		completeNameList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_SPONSER_DETAILS_FROM_SPONSER_ACCOUNT_NUMBER, accountNumber),RFL_DB);
		String firstNameDB = String.valueOf(getValueFromQueryResult(completeNameList, "FirstName"));
		String lastNameDB = String.valueOf(getValueFromQueryResult(completeNameList, "LastName"));
		String completeNameDB = firstNameDB+" "+lastNameDB;
		logger.info("Complete name from DB is: "+completeNameDB);
		nscore4OrdersTabPage.enterAccountNameAndClickStartOrder(completeNameDB, accountNumber);
		//SKU = nscore4HomePage.addAndGetProductSKU("1");
		nscore4HomePage.addAndGetSpecficProductSKU("1");
		//s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		driver.pauseExecutionFor(3000);
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		/*		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
    		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");
    		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusAfterSubmitOrder(),"Order is not submitted after submit order");
    		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(),"This is not order details page");*/
		s_assert.assertAll();
		try {
			ExcelReader.ExcelWriter(firstNameDB, lastNameDB, accountNumber, "C:\\Users\\plu\\heirloom\\rf-automation\\JavaBooks.xlsx");
		} catch (IOException e) {

			/*}*/

		}

	}

	//QTest ID TC-256 NSC4_SitesTab_nsDistributor_BasePWSSiteDetailsEditSite
	@Test(enabled=false)//should be tested manually
	public void testBasePWSSiteDetailsEditSite_256(){
		String updatedSiteName = null;
		String updatedDescName = null;
		String updatedStatus = null;
		String updatedlanguage = null;
		Map<String,String> initialDetails = null;
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickLinkUnderBasePWS("Site Details");
		initialDetails = nscore4SitesTabPage.getInitialSiteDetails();
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		updatedSiteName = initialDetails.get("SiteName")+randomNumber;
		updatedDescName = initialDetails.get("Description")+randomNumber;
		nscore4SitesTabPage.enterSiteNameForSiteDetails(updatedSiteName);
		nscore4SitesTabPage.enterDescription(updatedDescName);
		updatedStatus = nscore4SitesTabPage.changeStatusOfSiteDetails();
		updatedlanguage = nscore4SitesTabPage.selectNewLanguageFromDD(initialDetails.get("Language"));
		nscore4SitesTabPage.clickSaveBtnOnSiteDetails();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxtForSite().contains("Site saved successfully!"),"Saved Success Msg is not Present");
		s_assert.assertTrue(nscore4SitesTabPage.verifySiteNameFromLeftNav(updatedSiteName),"Site name is not present as Expected on Left Nav");
		s_assert.assertTrue(nscore4SitesTabPage.verifyDescFromLeftNav(updatedDescName),"Site Desc is not present as Expected on Left Nav");
		s_assert.assertTrue(nscore4SitesTabPage.verifyStatusFromLeftNav(updatedStatus),"Status is not present as Expected on Left Nav");
		nscore4SitesTabPage.clickLinkFromLeftNav("Site Details");
		s_assert.assertTrue(nscore4SitesTabPage.verifyLanguageFromSiteDetails(updatedlanguage),"Site name is not present as Selected in Language dropdown");

		// Revert The Changes Made
		nscore4SitesTabPage.enterSiteNameForSiteDetails(initialDetails.get("SiteName"));
		nscore4SitesTabPage.enterDescription(initialDetails.get("Description"));
		nscore4SitesTabPage.changeStatusOfSiteDetails();
		nscore4SitesTabPage.selectLanguageFromDD(initialDetails.get("Language"));
		nscore4SitesTabPage.clickSaveBtnOnSiteDetails();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxtForSite().contains("Site saved successfully!"),"Saved Success Msg is not Present");
		s_assert.assertAll();
	}

	//QTest ID TC-257 NSC4_SitesTab_nsDistributor_BasePWSReplicateSites
	@Test(enabled=false)//should be tested manually
	public void testBasePWSReplicateSites_257(){
		String currentURL = null;
		String urlToAssert = "ReplicatedSites";
		String currentSiteName = null;
		String updatedSiteName = null;
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickLinkUnderBasePWS("Replicated Sites");
		currentURL = nscore4SitesTabPage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(urlToAssert),"Current URL "+currentURL+" does not contain expected URL : "+urlToAssert);
		s_assert.assertTrue(nscore4SitesTabPage.verifyReplicateSitesHeaderIsPresent(),"Replicate Site Header is not present as expected");
		nscore4SitesTabPage.selectFromStatusFilter("Active");
		s_assert.assertTrue(nscore4SitesTabPage.verifySitesStatusAfterFilter("Active"),"Sites status is not found as Active after applying Active filter");
		nscore4SitesTabPage.selectFromStatusFilter("Cancelled");
		s_assert.assertTrue(nscore4SitesTabPage.verifySitesStatusAfterFilter("Cancelled"),"Sites status is not found as Cancelled after applying Cancelled filter");
		nscore4SitesTabPage.clickFirstSiteFromSitesGrid("Active");
		currentSiteName = nscore4SitesTabPage.getSiteName();
		updatedSiteName = currentSiteName+randomNumber;
		nscore4SitesTabPage.enterSiteNameForSiteDetails(updatedSiteName);
		nscore4SitesTabPage.clickSaveBtnOnSiteDetails();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxtForSite().contains("Site saved successfully!"),"Saved Success Msg is not Present");
		s_assert.assertTrue(nscore4SitesTabPage.verifySiteNameFromLeftNav(updatedSiteName),"Site name is not present as Expected on Left Nav");

		// Revert The Changes Made
		nscore4SitesTabPage.clickLinkFromLeftNav("Site Details");
		nscore4SitesTabPage.enterSiteNameForSiteDetails(currentSiteName);
		nscore4SitesTabPage.clickSaveBtnOnSiteDetails();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxtForSite().contains("Site saved successfully!"),"Saved Success Msg is not Present");
		s_assert.assertAll();
	}

	// QTest ID TC-253 NSC4_SitesTab_nsDistributor_BasePWSNewEditDeleteEvent
	@Test(enabled=false)//should be tested manually
	public void testBasePWSNewEditDeleteEvent_253(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum2 = CommonUtils.getRandomNum(10000, 1000000);
		String sites = "Sites";
		String subject = "For Automation"+randomNum;
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickBasePWSLink();
		nscore4SitesTabPage.clickAddEventLink();
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		nscore4SitesTabPage.clickEventNamePresentAtCalendar(subject);
		subject = "For Automation"+randomNum2;
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		nscore4SitesTabPage.clickEventNamePresentAtCalendar(subject);
		nscore4SitesTabPage.clickDeleteBtnForEvent();
		nscore4SitesTabPage.clickOKBtnOfJavaScriptPopUp();
		s_assert.assertFalse(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is present at calendar ");
		s_assert.assertAll();

	}
	// QTest ID TC-295 NSC4_ProductsTab_ ProductsManagement_FulfillmentDetails
	@Test
	public void testProductsManagementFulfillmentDetails_295(){
		String products = "Products";
		String sku = null;
		String randomNum = Integer.toString(CommonUtils.getRandomNum(100000, 10000000));
		String itemNum = null;
		nscore4HomePage.clickTab(products);
		nscore4ProductsTabPage.hoverProductManagementAndClickOption("Fulfillment Details");
		sku = nscore4ProductsTabPage.getProductSkuWithoutFulfillmentDetails();
		System.out.println("SKU : "+sku);
		nscore4ProductsTabPage.clickAddFulfillmentDetailsLink(sku);
		nscore4ProductsTabPage.enterFulfillmentDetails(randomNum, randomNum, randomNum);
		nscore4ProductsTabPage.clickSaveFulfillmentBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedFulfillmentDetailsSuccessfulMsg(),"Saved Fulfillment Successful Msg is not present");
		itemNum = nscore4ProductsTabPage.getItemNumberForSku(sku);
		s_assert.assertTrue(itemNum.equals(randomNum),"Item Number is not present as expected for SKU : "+sku+". Expected : "+randomNum+". Actual : "+itemNum);
		nscore4ProductsTabPage.clickUpdateLinkForSKU(sku);
		randomNum = Integer.toString(CommonUtils.getRandomNum(100000, 10000000));
		nscore4ProductsTabPage.enterFulfillmentDetails(randomNum, randomNum, randomNum);
		nscore4ProductsTabPage.clickSaveFulfillmentBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedFulfillmentDetailsSuccessfulMsg(),"Saved Fulfillment Details Successful Msg is not present");
		itemNum = nscore4ProductsTabPage.getItemNumberForSku(sku);
		s_assert.assertTrue(itemNum.equals(randomNum),"Item Number after updation is not present as expected for SKU : "+sku+". Expected : "+randomNum+". Actual : "+itemNum);
		nscore4ProductsTabPage.clickDeleteLinkForSKU(sku);
		s_assert.assertTrue(nscore4ProductsTabPage.verifyDeleteFulfillmentDetailsSuccessfulMsg(),"Delete Fulfillment Details Successful Msg is not present");
		s_assert.assertTrue(nscore4ProductsTabPage.isFulfillmentDetailsNotPresentForSKU(sku),"Fulfillment Details are still present for SKU : "+sku);
		s_assert.assertAll();
	}


	// QTest ID TC-296 NSC4_ProductsTab_ ProductsManagement_ProductTypes
	@Test
	public void testProductsManagementProductTypes_296(){
		String products = "Products";
		String productType = "ProdType"+CommonUtils.getRandomWord(5);
		String updatedProductType = "ProdType"+CommonUtils.getRandomWord(5);
		nscore4HomePage.clickTab(products);
		nscore4ProductsTabPage.hoverProductManagementAndClickOption("Product Types");
		nscore4ProductsTabPage.clickAddANewProductTypeLink();
		nscore4ProductsTabPage.enterProductTypeAndSave(productType);
		s_assert.assertTrue(nscore4ProductsTabPage.isNewlyAddedProductTypePresentInList(productType),"Newly Added ProductType :"+productType+" is not present in the List");
		nscore4ProductsTabPage.clickOnProductTypeFromList(productType);
		nscore4ProductsTabPage.enterProductTypeAndSave(updatedProductType);
		s_assert.assertTrue(nscore4ProductsTabPage.isNewlyAddedProductTypePresentInList(updatedProductType),"Updated ProductType :"+updatedProductType+" is not present in the List");
		nscore4ProductsTabPage.clickOnProductTypeFromList(updatedProductType);
		nscore4ProductsTabPage.clickDeleteProductTypeBtn();
		s_assert.assertFalse(nscore4ProductsTabPage.isNewlyAddedProductTypePresentInList(updatedProductType),"ProductType :"+updatedProductType+" is still present in the List");
		s_assert.assertAll();
	}


	@Test
	public void testProductsManagementAddNewProduct(){
		String products = "Products";
		String productSku = "SKU"+CommonUtils.getRandomNum(100000,1000000);
		String productName = "TestProduct_"+CommonUtils.getRandomNum(100000,1000000);
		String productWeight = "100";
		String expectedUrlForOverviewPage = "Products/Overview";
		String currentUrl = null;
		String linkFromLeftNav = null;
		String prodPrice = "100";
		String shortDesc = "Test Short Description";
		String skuForRelationship = null;
		nscore4HomePage.clickTab(products);
		nscore4ProductsTabPage.hoverProductManagementAndClickOption("Browse Products");
		nscore4ProductsTabPage.chooseProductTypeAndStatus("Kits","All");
		skuForRelationship = nscore4ProductsTabPage.getFirstProductSKU();
		nscore4ProductsTabPage.hoverProductManagementAndClickOption("Add a New Product");
		nscore4ProductsTabPage.enterNewProductDetails(productSku, productName, productWeight);
		nscore4ProductsTabPage.clickSaveCatalogBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.verifyProductOverviewLinkIsPresent(),"Product Overview Link is not present");
		currentUrl = nscore4ProductsTabPage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(expectedUrlForOverviewPage),"Current URL "+currentUrl+" does not contain expected URL : "+expectedUrlForOverviewPage);
		//Details
		linkFromLeftNav = "Details";
		nscore4ProductsTabPage.clickLinkFromLeftNav(linkFromLeftNav);
		productSku = "SKU"+CommonUtils.getRandomNum(100000,1000000);
		nscore4ProductsTabPage.updateSKUOfProduct(productSku);
		nscore4ProductsTabPage.clickSaveCatalogBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.getActionSuccessMsg().contains("Details saved!"),"Details Saved Success msg is not present as expected");
		// Pricing
		linkFromLeftNav = "Pricing";
		nscore4ProductsTabPage.clickLinkFromLeftNav(linkFromLeftNav);
		nscore4ProductsTabPage.enterPriceDetailsForProduct(prodPrice, prodPrice, prodPrice);
		nscore4ProductsTabPage.clickSavePricesBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.getActionSuccessMsg().contains("Prices saved!"),"Pricing Saved Success msg is not present as expected");
		// CMS
		linkFromLeftNav = "CMS";
		nscore4ProductsTabPage.clickLinkFromLeftNav(linkFromLeftNav);
		nscore4ProductsTabPage.enterShortDescOnCMSTab(shortDesc);
		nscore4ProductsTabPage.clickSaveDescriptionsBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.getActionSuccessMsg().contains("Content saved!"),"Description Saved Success msg is not present as expected");
		// Categories
		linkFromLeftNav = "Categories";
		nscore4ProductsTabPage.clickLinkFromLeftNav(linkFromLeftNav);
		nscore4ProductsTabPage.selectRandomCatgory();
		nscore4ProductsTabPage.clickSaveCatalogBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.getActionSuccessMsg().contains("Categories saved!"),"Categories Saved Success msg is not present as expected");
		// Relationships
		linkFromLeftNav = "Relationships";
		nscore4ProductsTabPage.clickLinkFromLeftNav(linkFromLeftNav);
		nscore4ProductsTabPage.enterProductInRelationshipTB(skuForRelationship);
		nscore4ProductsTabPage.selectRelationTypeFromDD("Refill");
		nscore4ProductsTabPage.clickAddRelationBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.isSKUPresentInRelationShipBox(skuForRelationship),skuForRelationship+" is not present in Relationship container");
		//Availability
		linkFromLeftNav = "Availability";
		nscore4ProductsTabPage.clickLinkFromLeftNav(linkFromLeftNav);
		nscore4ProductsTabPage.editProductAvailablityAndSave();
		s_assert.assertTrue(nscore4ProductsTabPage.getActionSuccessMsg().contains("Availability saved!"),"Availability saved Success msg is not present as expected");
		// Product Properties
		linkFromLeftNav = "Product Properties";
		nscore4ProductsTabPage.clickLinkFromLeftNav(linkFromLeftNav);
		nscore4ProductsTabPage.selectAllCartsRadioBtn();
		nscore4ProductsTabPage.clickSaveCatalogBtn();
		s_assert.assertTrue(nscore4ProductsTabPage.getActionSuccessMsg().contains("Properties saved!"),"Properties saved Success msg is not present as expected");
		s_assert.assertAll();
	}

	//QTest ID TC-246 NSC4_SitesTab_nsCorporate_CorporateSitePagesAddEditNewSite
	@Test(enabled=false)//should be tested manually
	public void testCorporateSitePageAddEditNewSite_246(){
		int randomNumb = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		String sublinkName = "Site Pages";
		String pageName = "AutomationPage"+randomNumb;
		String pageTitle = "AutoTitle"+randomNumber;
		String pageDescription = "AutoDesc"+randomNumbers;
		String pageURL = "/Pages/About/"+pageName;
		String pageKeyword = "Unique"+randomNumb;
		String templateView = "Two Piece View";
		logger.info("DB is "+RFL_DB);
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfCorporate(sublinkName);
		nscore4SitesTabPage.clickAddNewPageLink();
		nscore4SitesTabPage.enterNewPageDetails(pageName,pageTitle,pageDescription,pageURL,pageKeyword,templateView);
		nscore4SitesTabPage.clickSaveButtonForNewCreatedPage();
		s_assert.assertTrue(nscore4SitesTabPage.getPageSavedSuccessfullyTxtForSite().contains("Page saved successfully!"), "Expected saved message is: Site saved successfully but actual on UI is: "+nscore4SitesTabPage.getPageSavedSuccessfullyTxtForSite());
		int noOfOptionsInSizePageDD = nscore4SitesTabPage.getSizeOfOptinsFromPageSizeDD();
		nscore4SitesTabPage.clickAndSelectOptionInPageSizeDD(noOfOptionsInSizePageDD);
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(pageName),"Newly created page name is not present on site page list");
		nscore4SitesTabPage.clickPageNameOnSitePageList(pageName);
		//Edit the page details
		randomNumb = CommonUtils.getRandomNum(10000, 1000000);
		randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		pageName = "AutomationPage"+randomNumb;
		pageTitle = "AutoTitle"+randomNumber;
		pageDescription = "AutoDesc"+randomNumbers;
		pageURL = "/Pages/About/"+pageName;
		pageKeyword = "Unique"+randomNumb;
		nscore4SitesTabPage.enterNewPageDetails(pageName,pageTitle,pageDescription,pageURL,pageKeyword,templateView);
		nscore4SitesTabPage.clickSaveButtonForNewCreatedPage();
		s_assert.assertTrue(nscore4SitesTabPage.getPageSavedSuccessfullyTxtForSite().contains("Page saved successfully!"), "Expected saved message after edit the page details is: Site saved successfully but actual on UI is: "+nscore4SitesTabPage.getPageSavedSuccessfullyTxtForSite());
		noOfOptionsInSizePageDD = nscore4SitesTabPage.getSizeOfOptinsFromPageSizeDD();
		nscore4SitesTabPage.clickAndSelectOptionInPageSizeDD(noOfOptionsInSizePageDD);
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(pageName),"Edited page name is not present on site page list");
		s_assert.assertAll();
	}

	//QTest ID TC-243 NSC4_SitesTab_nsCorporate_CorporateUploadEditResource
	@Test(enabled=false)//should be tested manually
	public void testCorporateUploadEditResource_243(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		String name = "AutomationResource"+randomNumber;
		String sites = "Sites";
		String siteDetails = "Site Details";
		String filePath = "/AutoPath"+randomNumbers;
		String categoryDDValue = "Field Events - US";
		String resourceStatus = null;
		nscore4SitesTabPage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfCorporate(siteDetails);
		nscore4SitesTabPage.enterSiteNameForSiteDetails("Corporate");
		nscore4SitesTabPage.uncheckActiveChkBoxForSiteDetails();
		nscore4SitesTabPage.clickSaveBtnOnSiteDetails();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxtForSite().contains("Site saved successfully"), "Expected saved message is: Site saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxtForSite());
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickCorporateLink();
		nscore4SitesTabPage.clickUploadResourceLink();
		nscore4SitesTabPage.enterUploadResourceDetails(name, filePath, categoryDDValue);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Resource saved successfully!"), "Expected saved message after added the new resource details is: Resource saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.enterSearchTerms(name);
		nscore4SitesTabPage.clickGoBtn();
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(name),"Newly created resource is not present on resource list");
		nscore4SitesTabPage.clickPageNameOnSitePageList(name);
		// Edit the resource details
		randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		name = "AutomationResource"+randomNumber;
		filePath = "/AutoPath"+randomNumbers;
		nscore4SitesTabPage.enterUploadResourceDetails(name, filePath, categoryDDValue);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Resource saved successfully!"), "Expected saved message after editedthe new resource details is: Resource saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.enterSearchTerms(name);
		nscore4SitesTabPage.clickGoBtn();
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(name),"Edited resource is not present on resource list");
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickDeactivateSelectedLinkForUploadResoursce();
		resourceStatus = nscore4SitesTabPage.getResoursceStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Inactive"), "Expected resource's status is Inactive but actual on UI is "+resourceStatus);
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickActivateSelectedLinkForUploadResoursce();
		resourceStatus = nscore4SitesTabPage.getResoursceStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Active"), "Expected resource's status is Active but actual on UI is "+resourceStatus);
		s_assert.assertAll();
	}

	//QTest ID TC-249 NSC4_SitesTab_nsCorporate_CorporateResourceLibrary
	@Test(enabled=false)//should be tested manually
	public void testNsCorporateResourceLibrary_249(){
		String resourceLibraryLink = "Resource Library";
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber1 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber2 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber3 = CommonUtils.getRandomNum(10000, 1000000);
		String name = "AutomationResource"+randomNumber;
		String filePath = "/AutoPath"+randomNumber1;
		String categoryDDValue = "Field Events - US";
		String categoryDDValueForMove = "Business System";
		String description = "AutoDesc"+randomNumber2;
		String categoryName = "AutoCategory"+randomNumber3;
		String categoryNameFromUI = null;
		String resourceStatus = null;
		String message = null;
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfCorporate(resourceLibraryLink);
		nscore4SitesTabPage.clickAddResourceLink();
		nscore4SitesTabPage.enterUploadResourceDetails(name, filePath, categoryDDValue);
		nscore4SitesTabPage.enterDescription(description);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Resource saved successfully!"), "Expected saved message after added the new resource details is: Resource saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.clickAddCategoryLink();
		nscore4SitesTabPage.enterCategoryName(categoryName);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Category saved successfully!"), "Expected saved message after added the new category details is: Category saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		//Back to resource library page
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfCorporate(resourceLibraryLink);
		nscore4SitesTabPage.enterSearchTerms(name);
		nscore4SitesTabPage.clickGoBtn();
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(name),"Edited resource is not present on resource list");
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.selectCategoryForMoveToResource(categoryName);
		nscore4SitesTabPage.clickMoveSelectedToCategory();
		categoryNameFromUI = nscore4SitesTabPage.getResourceCategory(name);
		s_assert.assertTrue(categoryNameFromUI.contains(categoryName.trim()), "Expected category name is "+categoryName+" but actual on UI is "+categoryNameFromUI);
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickDeactivateSelectedLinkForUploadResoursce();
		resourceStatus = nscore4SitesTabPage.getResoursceStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Inactive"), "Expected resource's status is Inactive but actual on UI is "+resourceStatus);
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickActivateSelectedLinkForUploadResoursce();
		resourceStatus = nscore4SitesTabPage.getResoursceStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Active"), "Expected resource's status is Active but actual on UI is "+resourceStatus);
		nscore4SitesTabPage.clickManageResourceCategoriesLink();
		nscore4SitesTabPage.enterSearchTerms(categoryName);
		nscore4SitesTabPage.clickGoBtnForManageResource();
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(categoryName);
		nscore4SitesTabPage.clickDeleteSelectedLinkForManageResource();
		message = nscore4SitesTabPage.getMessageOfDelete();
		s_assert.assertTrue(message.contains("is in use and cannot be deleted")&& message.contains(categoryName), "Expected message should contains categor name is "+categoryName+ "and is in use and cannot be deleted but actual on UI is "+message);
		//change the category
		nscore4SitesTabPage.clickLinkInSidePanel(resourceLibraryLink);
		nscore4SitesTabPage.enterSearchTerms(name);
		nscore4SitesTabPage.clickGoBtn();
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.selectCategoryForMoveToResource(categoryDDValueForMove);
		nscore4SitesTabPage.clickMoveSelectedToCategory();
		categoryNameFromUI = nscore4SitesTabPage.getResourceCategory(name);
		s_assert.assertTrue(categoryNameFromUI.contains(categoryDDValueForMove.trim()), "Expected category name after delete is "+categoryName+" but actual on UI is "+categoryDDValueForMove);
		//delete the category
		nscore4SitesTabPage.clickManageResourceCategoriesLink();
		nscore4SitesTabPage.enterSearchTerms(categoryName);
		nscore4SitesTabPage.clickGoBtnForManageResource();
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(categoryName);
		nscore4SitesTabPage.clickDeleteSelectedLinkForManageResource();
		nscore4SitesTabPage.enterSearchTerms(categoryName);
		nscore4SitesTabPage.clickGoBtnForManageResource();
		s_assert.assertFalse(nscore4SitesTabPage.isCategoryNamePresent(categoryName), "Category name is present after delete");
		s_assert.assertAll();
	}

	//QTest ID TC-254 NSC4_SitesTab_nsDistributor_BasePWSAddEditDeleteNews
	@Test(enabled=false)//should be tested manually
	public void testBasePWSAddEditDeleteNews_254(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber1 = CommonUtils.getRandomNum(10000, 1000000);
		String name = "AutomationNews"+randomNumber;
		String caption = "Automation"+randomNumber1;
		String resourceStatus = null;
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickBasePWSLink();
		nscore4SitesTabPage.clickAddNewsLink();
		nscore4SitesTabPage.enterNewsDetails(name, caption);
		nscore4SitesTabPage.checkIsActiveChkBoxForNewsTitle();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("News saved successfully!"), "Expected saved message after added news details is: News saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		int noOfOptionsInSizePageDD = nscore4SitesTabPage.getSizeOfOptinsFromPageSizeDD();
		nscore4SitesTabPage.clickAndSelectOptionInPageSizeDD(noOfOptionsInSizePageDD);
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(name),"Newly edited news name is not present on site page list");
		nscore4SitesTabPage.clickPageNameOnSitePageList(name);
		//Edit the news
		randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		randomNumber1 = CommonUtils.getRandomNum(10000, 1000000);
		name = "AutomationNews"+randomNumber;
		caption = "Automation"+randomNumber1;
		nscore4SitesTabPage.enterNewsDetails(name, caption);
		nscore4SitesTabPage.checkIsActiveChkBoxForNewsTitle();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("News saved successfully!"), "Expected saved message after added news details is: News saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		noOfOptionsInSizePageDD = nscore4SitesTabPage.getSizeOfOptinsFromPageSizeDD();
		nscore4SitesTabPage.clickAndSelectOptionInPageSizeDD(noOfOptionsInSizePageDD);
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(name),"Newly edited news name is not present on site page list");
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickDeactivateSelectedLink();
		resourceStatus = nscore4SitesTabPage.getTitleStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Inactive"), "Expected news status is Inactive but actual on UI is "+resourceStatus);
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickActivateSelectedLink();
		resourceStatus = nscore4SitesTabPage.getTitleStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Active"), "Expected resource's status is Active but actual on UI is "+resourceStatus);
		//delete the news
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickDeleteSelectedLink();
		s_assert.assertFalse(nscore4SitesTabPage.isCategoryNamePresent(name), "news name is present after delete");
		s_assert.assertAll();
	}

	//QTest ID TC-255 NSC4_SitesTab_nsDistributor_BasePWSUploadEditResource
	@Test(enabled=false)//should be tested manually
	public void testBasePWSUploadEditResource_255(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		String name = "AutomationResource"+randomNumber;
		String filePath = "/AutoPath"+randomNumbers;
		String categoryDDValue = "Field Events - US";
		String resourceStatus = null;
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickBasePWSLink();
		nscore4SitesTabPage.clickUploadResourceLink();
		nscore4SitesTabPage.enterUploadResourceDetails(name, filePath, categoryDDValue);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Resource saved successfully!"), "Expected saved message after added the new resource details is: Resource saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.enterSearchTerms(name);
		nscore4SitesTabPage.clickGoBtn();
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(name),"Newly created resource is not present on resource list");
		nscore4SitesTabPage.clickPageNameOnSitePageList(name);
		// Edit the resource details
		randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		name = "AutomationResource"+randomNumber;
		filePath = "/AutoPath"+randomNumbers;
		nscore4SitesTabPage.enterUploadResourceDetails(name, filePath, categoryDDValue);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Resource saved successfully!"), "Expected saved message after editedthe new resource details is: Resource saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.enterSearchTerms(name);
		nscore4SitesTabPage.clickGoBtn();
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(name),"Edited resource is not present on resource list");
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickDeactivateSelectedLinkForUploadResoursce();
		resourceStatus = nscore4SitesTabPage.getResoursceStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Inactive"), "Expected resource's status is Inactive but actual on UI is "+resourceStatus);
		s_assert.assertAll();
	}

	//QTest ID TC-261 NSC4_SitesTab_nsDistributor_BasePWSResourceLibrary
	@Test(enabled=false)//should be tested manually
	public void testBasePWSResourceLibrary(){
		String resourceLibraryLink = "Resource Library";
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber1 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber2 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber3 = CommonUtils.getRandomNum(10000, 1000000);
		String name = "AutomationResource"+randomNumber;
		String filePath = "/AutoPath"+randomNumber1;
		String categoryDDValue = "Field Events - US";
		String categoryDDValueForMove = "Business System";
		String description = "AutoDesc"+randomNumber2;
		String categoryName = "AutoCategory"+randomNumber3;
		String categoryNameFromUI = null;
		String resourceStatus = null;
		String message = null;
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(resourceLibraryLink);
		nscore4SitesTabPage.clickAddResourceLink();
		nscore4SitesTabPage.enterUploadResourceDetails(name, filePath, categoryDDValue);
		nscore4SitesTabPage.enterDescription(description);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Resource saved successfully!"), "Expected saved message after added the new resource details is: Resource saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.clickAddCategoryLink();
		nscore4SitesTabPage.enterCategoryName(categoryName);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Category saved successfully!"), "Expected saved message after added the new category details is: Category saved successfully! but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		//Back to resource library page
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(resourceLibraryLink);
		nscore4SitesTabPage.enterSearchTerms(name);
		nscore4SitesTabPage.clickGoBtn();
		s_assert.assertTrue(nscore4SitesTabPage.verifyPageNameOnSitePageList(name),"Edited resource is not present on resource list");
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.selectCategoryForMoveToResource(categoryName);
		nscore4SitesTabPage.clickMoveSelectedToCategory();
		categoryNameFromUI = nscore4SitesTabPage.getResourceCategory(name);
		s_assert.assertTrue(categoryNameFromUI.contains(categoryName.trim()), "Expected category name is "+categoryName+" but actual on UI is "+categoryNameFromUI);
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickDeactivateSelectedLinkForUploadResoursce();
		resourceStatus = nscore4SitesTabPage.getResoursceStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Inactive"), "Expected resource's status is Inactive but actual on UI is "+resourceStatus);
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.clickActivateSelectedLinkForUploadResoursce();
		resourceStatus = nscore4SitesTabPage.getResoursceStatus(name);
		s_assert.assertTrue(resourceStatus.contains("Active"), "Expected resource's status is Active but actual on UI is "+resourceStatus);
		nscore4SitesTabPage.clickManageResourceCategoriesLink();
		nscore4SitesTabPage.enterSearchTerms(categoryName);
		nscore4SitesTabPage.clickGoBtnForManageResource();
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(categoryName);
		nscore4SitesTabPage.clickDeleteSelectedLinkForManageResource();
		message = nscore4SitesTabPage.getMessageOfDelete();
		s_assert.assertTrue(message.contains("is in use and cannot be deleted")&& message.contains(categoryName), "Expected message should contains categor name is "+categoryName+ "and is in use and cannot be deleted but actual on UI is "+message);
		//change the category
		nscore4SitesTabPage.clickLinkInSidePanel(resourceLibraryLink);
		nscore4SitesTabPage.enterSearchTerms(name);
		nscore4SitesTabPage.clickGoBtn();
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(name);
		nscore4SitesTabPage.selectCategoryForMoveToResource(categoryDDValueForMove);
		nscore4SitesTabPage.clickMoveSelectedToCategory();
		categoryNameFromUI = nscore4SitesTabPage.getResourceCategory(name);
		s_assert.assertTrue(categoryNameFromUI.contains(categoryDDValueForMove.trim()), "Expected category name after delete is "+categoryName+" but actual on UI is "+categoryDDValueForMove);
		//delete the category
		nscore4SitesTabPage.clickManageResourceCategoriesLink();
		nscore4SitesTabPage.enterSearchTerms(categoryName);
		nscore4SitesTabPage.clickGoBtnForManageResource();
		nscore4SitesTabPage.checkTitleNameChkBoxInAnnouncementsList(categoryName);
		nscore4SitesTabPage.clickDeleteSelectedLinkForManageResource();
		nscore4SitesTabPage.enterSearchTerms(categoryName);
		nscore4SitesTabPage.clickGoBtnForManageResource();
		s_assert.assertFalse(nscore4SitesTabPage.isCategoryNamePresent(categoryName), "Category name is present after delete");
		s_assert.assertAll();
	}

	//QTest ID TC-276 NSC4_AccountsTab_SiteSubscriptionsUpdate
	@Test
	public void testSiteSubscriptionsUpdate_276(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber1 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber2 = CommonUtils.getRandomNum(10000, 1000000);
		String accountNumber = null;
		String subscriptionStatus = null;
		String bizPrefixFromUI = null;
		String comPrefixFromUI = null;
		String pulseEmailFromUI = null;
		String bizPrefixAfterUpdate = null;
		String comPrefixAfterUpdate = null;
		String pulseEmailAfterUpdate = null;
		String bizPrefix = null;
		String comPrefix = null;
		String pulseEmail = null;
		String successMessage = null;
		accountNumber = "805910";//(String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");	
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4HomePage.clickLinkInSidePanel("Site Subscriptions");
		subscriptionStatus = nscore4HomePage.getSubscriptionStatus();
		s_assert.assertTrue(subscriptionStatus.contains("paid"), "Expected subscription status is paid but actual on UI is"+subscriptionStatus);
		comPrefixFromUI = nscore4HomePage.getPrefixFromUrl("com");
		bizPrefixFromUI = nscore4HomePage.getPrefixFromUrl("biz");
		pulseEmailFromUI = nscore4HomePage.getPulseEmailFromUI();
		bizPrefix = bizPrefixFromUI+randomNumber;
		comPrefix = comPrefixFromUI+randomNumber1;
		pulseEmail = pulseEmailFromUI+randomNumber2;
		nscore4HomePage.enterPrefixAccordingToUrlType("com",comPrefix);
		nscore4HomePage.enterPrefixAccordingToUrlType("biz",bizPrefix);
		nscore4HomePage.enterPulseEmail(pulseEmail);
		nscore4HomePage.clickSaveChangesBtn();
		successMessage = nscore4HomePage.getUpdationMessage();
		s_assert.assertTrue(successMessage.contains("See below to make sure your sites and email saved correctly"), "Expected success message should contains See below to make sure your sites and email saved correctly but actual on UI is "+successMessage);
		nscore4HomePage.refreshPage();
		comPrefixAfterUpdate = nscore4HomePage.getPrefixFromUrl("com");
		bizPrefixAfterUpdate = nscore4HomePage.getPrefixFromUrl("biz");
		pulseEmailAfterUpdate = nscore4HomePage.getPulseEmailFromUI();
		s_assert.assertTrue(comPrefixAfterUpdate.contains(comPrefix), "Expected com prefix is "+comPrefix+" but actual on UI is "+comPrefixAfterUpdate);
		s_assert.assertTrue(bizPrefixAfterUpdate.contains(bizPrefix), "Expected biz prefix is "+bizPrefix+" but actual on UI is "+bizPrefixAfterUpdate);
		s_assert.assertTrue(pulseEmailAfterUpdate.contains(pulseEmail), "Expected pulse email is "+pulseEmail+" but actual on UI is "+pulseEmailAfterUpdate);
		// revert the updations
		nscore4HomePage.enterPrefixAccordingToUrlType("com",comPrefix);
		nscore4HomePage.enterPrefixAccordingToUrlType("biz",bizPrefix);
		nscore4HomePage.enterPulseEmail(pulseEmail);
		nscore4HomePage.clickSaveChangesBtn();
		successMessage = nscore4HomePage.getUpdationMessage();
		s_assert.assertTrue(successMessage.contains("See below to make sure your sites and email saved correctly"), "Expected success message after revert the changes should contains See below to make sure your sites and email saved correctly but actual on UI is "+successMessage);
		s_assert.assertAll();
	}

	//QTest ID TC-280 NSC4_AccountsTab_DisbursementProfilesNew
	@Test(enabled=false)//MAIN-7740 : After Clicking on 'Disbursement Profiles' User is redirecting to error page.
	public void testDisbursementProfilesNew_280(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String accountNumber = null;
		String namePayable = "rfTestUser"+randomNumber;
		String addressLine1 = TestConstantsRFL.ADDRESS_LINE_1_US;
		String city = TestConstantsRFL.CITY_US;
		String state = TestConstantsRFL.POSTAL_CODE_US;
		String postalCode = TestConstantsRFL.POSTAL_CODE_US;
		String namePayableToAfterUpdate = null;
		String address1AfterUpdate = null;
		String cityAfterUpdate = null;
		String stateAfterUpdate = null;
		String postalCodeAfterUpdate = null;
		String successMessage = null;
		accountNumber = "805910";//(String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");	
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4HomePage.clickLinkInSidePanel("Disbursement Profiles");
		nscore4HomePage.checkUseAddressOfRecordChkBox();
		nscore4HomePage.enterShippingDetailsForDisbursementProfile(namePayable, addressLine1, city, state, postalCode);
		nscore4HomePage.clickSaveChangesBtnForDisbursementProfile();
		successMessage = nscore4HomePage.getUpdationMessage();
		s_assert.assertTrue(successMessage.contains("Your disbursement profile(s) were saved successfully!"), "Expected success message should contains Your disbursement profile(s) were saved successfully! but actual on UI is "+successMessage);
		nscore4HomePage.refreshPage();
		namePayableToAfterUpdate = nscore4HomePage.getNameOfPayAbleTo();
		address1AfterUpdate = nscore4HomePage.getAddress1OfDisbursementProfile();
		cityAfterUpdate = nscore4HomePage.getCityOfDisbursementProfile();
		stateAfterUpdate = nscore4HomePage.getStateOfDisbursementProfile();
		postalCodeAfterUpdate = nscore4HomePage.getPostalCodeOfDisbursementProfile();
		//assert the changes
		s_assert.assertTrue(namePayableToAfterUpdate.contains(namePayable), "Expected name to pay is "+namePayable+" Actual on UI is "+namePayableToAfterUpdate);
		s_assert.assertTrue(address1AfterUpdate.contains(addressLine1), "Expected addressLine1 is "+addressLine1+" Actual on UI is "+address1AfterUpdate);
		s_assert.assertTrue(cityAfterUpdate.contains(city), "Expected city is "+city+" Actual on UI is "+cityAfterUpdate);
		s_assert.assertTrue(stateAfterUpdate.contains(state), "Expected state is "+state+" Actual on UI is "+stateAfterUpdate);
		s_assert.assertTrue(postalCodeAfterUpdate.contains(postalCode), "Expected postalCode is "+postalCode+" Actual on UI is "+postalCodeAfterUpdate);
		nscore4HomePage.checkUseAddressOfRecordChkBox();
		nscore4HomePage.clickSaveChangesBtnForDisbursementProfile();
		successMessage = nscore4HomePage.getUpdationMessage();
		s_assert.assertTrue(successMessage.contains("Your disbursement profile(s) were saved successfully!"), "Expected success message after revert the details should contains Your disbursement profile(s) were saved successfully! but actual on UI is "+successMessage);
		s_assert.assertAll();
	}

	//QTest ID TC-292 NSC4_ProductsTab_ CategoryTrees
	@Test
	public void testProductsTabCategoryTrees_292(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber1 = CommonUtils.getRandomNum(10000, 1000000);
		String treeName = TestConstantsRFL.FIRST_NAME+randomNumber;
		String subTreeName = TestConstants.FIRST_NAME+randomNumber1;
		nscore4HomePage.clickTab("Products");
		nscore4ProductsTabPage.clickLinkInSidePanel("Category Trees");
		nscore4ProductsTabPage.clickCreateANewCategoryTree();
		nscore4ProductsTabPage.enterTreeNameAndClickCreateTreeBtn(treeName);
		s_assert.assertTrue(nscore4ProductsTabPage.verifyConfirmationMessage(), "Expected confirmation message is not displayed");
		nscore4ProductsTabPage.enterLanguageNameAndClickSave(subTreeName);
		s_assert.assertTrue(nscore4ProductsTabPage.isSubTreeCreatedUnderTree(), "Expected subtree is not created");
		nscore4ProductsTabPage.clickLinkInSidePanel("Category Trees");
		s_assert.assertTrue(nscore4ProductsTabPage.isNewlyCreatedTreePresent(treeName), "Newly created tree is not present in category trees");
		nscore4ProductsTabPage.checkTitleNameChkBoxInAnnouncementsList(treeName);
		nscore4ProductsTabPage.clickActivateOrDeActivateSelectedLink("Delete Selected");
		nscore4ProductsTabPage.clickOKBtnOfJavaScriptPopUp();
		s_assert.assertFalse(nscore4ProductsTabPage.isNewlyCreatedTreePresent(treeName), "Newly created tree is present after delete in category trees");
		s_assert.assertAll();
	}

	//QTest ID TC-293 NSC4_ProductsTab_ProductsManagement_BrowseProducts
	@Test
	public void testProductsManagementBrowseProducts_293(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String productName = null;
		String txtName = TestConstantsRFL.FIRST_NAME+randomNumber;
		String firstProductNameInList = null;
		nscore4HomePage.clickTab("Products");
		nscore4ProductsTabPage.hoverProductManagementAndClickOption("Browse Products");
		s_assert.assertTrue(nscore4ProductsTabPage.verifyCurrentPage("BrowseProducts"),"User is not redirect to browse products page");
		nscore4ProductsTabPage.chooseProductTypeAndStatus("Kits", "Inactive");
		productName = nscore4ProductsTabPage.getFirstProductNameFromBrowseProducts();
		nscore4ProductsTabPage.selectProductAndClickActivateSelectedLink(productName);
		nscore4ProductsTabPage.clickActivateOrDeActivateSelectedLink("Activate Selected");
		firstProductNameInList = nscore4ProductsTabPage.getFirstProductNameFromBrowseProducts();
		s_assert.assertTrue(nscore4ProductsTabPage.verifyProductBecomeActivated(productName, firstProductNameInList), "Still product is present in inactive list after clicked on deactivate");
		nscore4ProductsTabPage.chooseProductTypeAndStatus("Kits", "Active");
		s_assert.assertTrue(nscore4ProductsTabPage.verifyPageNameOnSitePageList(productName),"Product name "+productName+" is not present in active list");
		nscore4ProductsTabPage.checkTitleNameChkBoxInAnnouncementsList(productName);
		nscore4ProductsTabPage.clickActivateOrDeActivateSelectedLink("Deactivate Selected");
		nscore4ProductsTabPage.chooseProductTypeAndStatus("Kits", "Inactive");
		s_assert.assertTrue(nscore4ProductsTabPage.verifyPageNameOnSitePageList(productName),"Product name "+productName+" is not present in Inactive list");
		nscore4ProductsTabPage.selectARandomProduct();
		nscore4ProductsTabPage.clickSublinkAccordingToLabel("Available In");
		nscore4ProductsTabPage.editProductAvailablityAndSave();
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedMessagePresent(), "Saved message is not present after edit the product availablity");
		nscore4ProductsTabPage.clickLinkInSidePanel("Product Overview");
		nscore4ProductsTabPage.clickSublinkAccordingToLabel("Pricing");
		nscore4ProductsTabPage.editPricingAndSave();
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedMessagePresent(), "Saved message is not present after edit the product pricing");
		nscore4ProductsTabPage.clickLinkInSidePanel("Product Overview");
		nscore4ProductsTabPage.clickSublinkAccordingToLabel("Product Details");
		nscore4ProductsTabPage.editProductDetailsAndSave();
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedMessagePresent(), "Saved message is not present after edit the product details");
		nscore4ProductsTabPage.clickLinkInSidePanel("Product Overview");
		nscore4ProductsTabPage.clickSublinkAccordingToLabel("Product CMS");
		nscore4ProductsTabPage.editCMSDescriptionAndSave();
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedMessagePresent(), "Saved message is not present after edit the product CMS");
		//edit the Categories
		nscore4ProductsTabPage.clickLinkInSidePanel("Categories");
		nscore4ProductsTabPage.changeCategoriesAndSave("ConsultantsOnly");
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedMessagePresent(), "Saved message is not present after chnage the categories");
		//edit the relationship
		nscore4ProductsTabPage.clickLinkInSidePanel("Relationships");
		nscore4ProductsTabPage.selectAProductAndRemoveFromRelationShip(productName);
		s_assert.assertTrue(nscore4ProductsTabPage.isRelationPresentInBox(productName), "SKU is present in relationship box after remove");
		//edit the Availability
		nscore4ProductsTabPage.clickLinkInSidePanel("Availability");
		nscore4ProductsTabPage.editProductAvailablityAndSave();
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedMessagePresent(), "Saved message is not present after edit the product availablity through left panel");
		//edit the Product Properties
		nscore4ProductsTabPage.clickLinkInSidePanel("Product Properties");
		nscore4ProductsTabPage.enterProductPrioritiesANdClickOnSave(txtName);
		s_assert.assertTrue(nscore4ProductsTabPage.verifySavedMessagePresent(), "Saved message is not present after save the product properties");
		s_assert.assertAll();
	}

	// QTest ID TC-248 NSC4_SitesTab_nsCorporate_CorporateCalendarEvents
	@Test(enabled=false)//should be tested manually
	public void testCorporateCalendarEvents_248(){
		String sites = "Sites";
		String subject = null;
		String monthToSelect = "Oct";
		String yearToSelect = "2020";
		String actualEventSubject = null;
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfCorporate("Calendar Events");

		// Prior Month
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickCalenderEventStartDate();
		nscore4SitesTabPage.selectEventMonthFromDD(monthToSelect);
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		//		nscore4SitesTabPage.selectMonthForCalendarEvent(monthToSelect);
		//		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not created at calendar for prior month");
		// Prior Year
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickCalenderEventStartDate();
		nscore4SitesTabPage.selectEventYearFromDD(yearToSelect);
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		//		nscore4SitesTabPage.selectYearForCalendarEvent(yearToSelect);
		//		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		// Today Event
		nscore4SitesTabPage.clickTodayBtn();
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		//		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		nscore4SitesTabPage.clickEventNamePresentAtCalendar(subject);
		actualEventSubject = nscore4SitesTabPage.getEventSubjectFromEventDetails();
		s_assert.assertTrue(actualEventSubject.contains(subject),"Event Detail is not found as expected");
		nscore4SitesTabPage.clickCancelBtnOnEventDetails();
		// Previous Button
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickCalenderEventStartDate();
		nscore4SitesTabPage.selectPreviousMonthFromCurrentDate();
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		//		nscore4SitesTabPage.clickPreviousBtn();  
		//		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		// Next Button
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickCalenderEventStartDate();
		nscore4SitesTabPage.selectNextMonthFromCurrentDate();
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		//		nscore4SitesTabPage.clickNextBtn();  
		//		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		s_assert.assertAll();
	}
	//QTest ID TC-260 NSC4_SitesTab_nsDistributor_BasePWSCalendarEvents
	@Test(enabled=false)//should be tested manually
	public void testBasePWSCalendarEvents_260(){
		String sites = "Sites";
		String subject = null;
		String monthToSelect = "Jan";
		String yearToSelect = "2017";
		String actualEventSubject = null;
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickLinkUnderBasePWS("Calendar Events");

		// Prior Month
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickCalenderEventStartDate();
		nscore4SitesTabPage.selectEventMonthFromDD(monthToSelect);
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickCalenderEventEndDate();
		nscore4SitesTabPage.selectEventMonthFromDD(monthToSelect);
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.selectMonthForCalendarEvent(monthToSelect);
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		// Prior Year
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickCalenderEventStartDate();
		nscore4SitesTabPage.selectEventYearFromDD(yearToSelect);
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickCalenderEventEndDate();
		nscore4SitesTabPage.selectEventYearFromDD(yearToSelect);
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.selectYearForCalendarEvent(yearToSelect);
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		// Today Event
		nscore4SitesTabPage.clickTodayBtn();
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		nscore4SitesTabPage.clickEventNamePresentAtCalendar(subject);
		actualEventSubject = nscore4SitesTabPage.getEventSubjectFromEventDetails();
		s_assert.assertTrue(actualEventSubject.contains(subject),"Event Detail is not found as expected");
		nscore4SitesTabPage.clickCancelBtnOnEventDetails();
		// Previous Button
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickCalenderEventStartDate();
		nscore4SitesTabPage.selectPreviousMonthFromCurrentDate();
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.clickPreviousBtn();  
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		// Next Button
		nscore4SitesTabPage.clickAddNewEventLink();
		subject = "For Automation"+CommonUtils.getRandomNum(10000, 1000000);
		nscore4SitesTabPage.enterSubjectForEvent(subject);
		nscore4SitesTabPage.clickCalenderEventStartDate();
		nscore4SitesTabPage.selectNextMonthFromCurrentDate();
		nscore4SitesTabPage.selectFirstDateFromCalendar();
		nscore4SitesTabPage.clickSaveBtn();
		s_assert.assertTrue(nscore4SitesTabPage.getSavedSuccessfullyTxt().contains("Event saved successfully"), "Expected saved message is: Event saved successfully but actual on UI is: "+nscore4SitesTabPage.getSavedSuccessfullyTxt());
		nscore4SitesTabPage.clickNextBtn();  
		s_assert.assertTrue(nscore4SitesTabPage.isEventPresentAtCalendar(subject), "Event is not present at calendar ");
		s_assert.assertAll();
	}

	// QTest ID TC-1949 Update billing profile CRP template NSC4
	@Test()
	public void testUpdateBillingProfileCRPTemplateNSC4_1949() {
		String emailID = null;
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = "RFAutoNSCore4"+randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String addressLine1 = TestConstantsRFL.ADDRESS_LINE1;
		String zipCode = TestConstantsRFL.POSTAL_CODE;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String billingProfileName=newBillingProfileName+" "+lastNameProfileName;
		String enteredCardNameOnUI=null;
		String billingProfileNameFromUI=null;

		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");

		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditCRPTemplate();
		nscore4HomePage.clickEditBillingProfile();
		nscore4HomePage.addANewBillingProfile(newBillingProfileName, lastNameProfileName, nameOnCard, cardNumber,addressLine1,zipCode,CVV);
		nscore4HomePage.clickSavePaymentMethodBtn();
		billingProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		s_assert.assertTrue(billingProfileNameFromUI.contains(billingProfileName),"Expected billing profile name is:"+billingProfileName+" But on UI is: "+billingProfileNameFromUI);
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		s_assert.assertTrue(enteredCardNameOnUI.contains(nameOnCard),"Expected billing profile name on card is:"+enteredCardNameOnUI+" But on UI is: "+nameOnCard);
		s_assert.assertAll();
	}

	//QTest ID TC-1956 NSC4_Status_active
	@Test
	public void testSearchActiveUser_1956q(){
		List randomUserList = null;
		String firstName = null;
		String lastName = null;
		String accountNumber = null;

		randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACCOUNT_DETAILS,RFL_DB);
		firstName = (String) getValueFromQueryResult(randomUserList, "FirstName");
		lastName = (String) getValueFromQueryResult(randomUserList, "LastName");
		accountNumber = (String) getValueFromQueryResult(randomUserList, "AccountNumber");

		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.selectAccountStatusForSearch("Active");
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(firstName, lastName), "First and last name is not present in search result,when searched with account number");
		s_assert.assertTrue(nscore4HomePage.isCIDPresentinSearchResults(accountNumber), "CID/Account Number is not present in search result,when searched with account number");
		s_assert.assertTrue(nscore4HomePage.getAccountStatusOfAccount(accountNumber).contains("Active"),"Account status is NOT active");
		s_assert.assertAll();
	}

	//QTest ID TC-1957 Nsc4_status_inactive
	@Test
	public void testSearchInactiveUser_1957q(){
		List randomUserList = null;
		String firstName = null;
		String lastName = null;
		String accountNumber = null;

		randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_INACTIVE_ACCOUNT_DETAILS,RFL_DB);
		firstName = (String) getValueFromQueryResult(randomUserList, "FirstName");
		lastName = (String) getValueFromQueryResult(randomUserList, "LastName");
		accountNumber = (String) getValueFromQueryResult(randomUserList, "AccountNumber");

		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.selectAccountStatusForSearch("Inactive");
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(firstName, lastName), "First and last name is not present in search result,when searched with account number");
		s_assert.assertTrue(nscore4HomePage.isCIDPresentinSearchResults(accountNumber), "CID/Account Number is not present in search result,when searched with account number");
		s_assert.assertTrue(nscore4HomePage.getAccountStatusOfAccount(accountNumber).contains("Inactive"),"Account status is NOT Inactive");
		s_assert.assertAll();
	}

	//QTest ID TC-1959 NSC4_Browse _Type account_CE
	@Test
	public void testSearchConsultantUser_1959q(){
		List randomUserList = null;
		String firstName = null;
		String lastName = null;
		String accountNumber = null;

		randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_EMAILID,RFL_DB);
		firstName = (String) getValueFromQueryResult(randomUserList, "FirstName");
		lastName = (String) getValueFromQueryResult(randomUserList, "LastName");
		accountNumber = (String) getValueFromQueryResult(randomUserList, "AccountNumber");

		nscore4HomePage.enterFirstAndLastNameInCorrespondingFields(firstName, lastName);
		nscore4HomePage.selectAccountTypeForSearch("Consultant");
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(firstName, lastName), "First and last name is not present in search result,when searched with account number");
		s_assert.assertTrue(nscore4HomePage.isCIDPresentinSearchResults(accountNumber), "CID/Account Number is not present in search result,when searched with account number");
		s_assert.assertAll();
	}

	//QTest ID TC-1960 NSC4_Browse _Type account_PC
	@Test
	public void testSearchPCUser_1960q(){
		List randomUserList = null;
		String firstName = null;
		String lastName = null;
		String accountNumber = null;

		randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_PC_EMAILID,RFL_DB);
		firstName = (String) getValueFromQueryResult(randomUserList, "FirstName");
		lastName = (String) getValueFromQueryResult(randomUserList, "LastName");
		accountNumber = (String) getValueFromQueryResult(randomUserList, "AccountNumber");

		nscore4HomePage.enterFirstAndLastNameInCorrespondingFields(firstName, lastName);
		nscore4HomePage.selectAccountTypeForSearch("Preferred Customer");
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(firstName, lastName), "First and last name is not present in search result,when searched with account number");
		s_assert.assertTrue(nscore4HomePage.isCIDPresentinSearchResults(accountNumber), "CID/Account Number is not present in search result,when searched with account number");
		s_assert.assertAll();
	}

	//QTest ID TC-1961 NSC4_Browse _Type account_RC
	@Test
	public void testSearchRCUser_1961q(){
		List randomUserList = null;
		String firstName = null;
		String lastName = null;
		String accountNumber = null;

		randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_RC_EMAILID,RFL_DB);
		firstName = (String) getValueFromQueryResult(randomUserList, "FirstName");
		lastName = (String) getValueFromQueryResult(randomUserList, "LastName");
		accountNumber = (String) getValueFromQueryResult(randomUserList, "AccountNumber");

		nscore4HomePage.enterFirstAndLastNameInCorrespondingFields(firstName, lastName);
		nscore4HomePage.selectAccountTypeForSearch("Retail Customer");
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(firstName, lastName), "First and last name is not present in search result,when searched with account number");
		s_assert.assertTrue(nscore4HomePage.isCIDPresentinSearchResults(accountNumber), "CID/Account Number is not present in search result,when searched with account number");
		s_assert.assertAll();
	}

	//QTest ID TC-294 NSC4_ProductsTab_ ProductsManagement_AddNewProduct
	@Test(priority=3)
	public void testNSC4ProductsTabProductsManagementAddNewProduct_294(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber1 = CommonUtils.getRandomNum(10, 100);
		String sku=TestConstantsRFL.SKU_ID+randomNumber1;
		String name= TestConstantsRFL.PRODCUT_NAME+randomNumber1;
		String value= ""+1;
		List<Map<String, Object>> randomSKUList =  null;
		String SKU=null;
		String retail=""+randomNumber1;
		String pc=""+randomNumber1;
		String consultant=""+randomNumber1;
		randomSKUList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_SKU,RFL_DB);
		SKU=(String) getValueFromQueryResult(randomSKUList, "SKU"); 
		nscore4HomePage.clickProductsTab();
		nscore4HomePage.mouseHoverToProductsManagementTab("Add a New Product");

		nscore4HomePage.AddNewProductDetailsAndClickSave(sku,name);
		//s_assert.assertTrue(nscore4HomePage.verifySuccessMessageForAddNewProduct(), "Details Saved success message is not displayed ");
		s_assert.assertTrue(nscore4HomePage.verifyProductOverviewPageisDisplayed().contains(name.toLowerCase().trim()), "Product Overview Page is not displayed ");
		nscore4HomePage.clickLinkInSidePanel("Details");
		nscore4HomePage.UpdateSortOredrValueAndClickSave(value);
		s_assert.assertTrue(nscore4HomePage.verifySuccessMessageForDetails(), "Details Saved success message is not displayed ");
		nscore4HomePage.clickPricingTab();
		nscore4HomePage.UpdatePricingDetails(retail,pc,consultant);
		nscore4HomePage.clickCMSTab();
		nscore4HomePage.EnterShortDescriptionCMSTabAndClickSave();
		s_assert.assertTrue(nscore4HomePage.verifySuccessMessageForProductDescription(), "Product Decription success message is not displayed ");
		nscore4HomePage.clickCategoriesTab();
		nscore4HomePage.SelectCategoryOnCategoriesTabAndClickSave();
		s_assert.assertTrue(nscore4HomePage.verifySuccessMessageForCategorySelected(), "Category selection success message is not displayed ");
		nscore4HomePage.clickRelationshipsTab();
		nscore4HomePage.clickSearchAProduct(SKU);
		nscore4HomePage.selectRelationshipTypeAndClickArrowButton();
		s_assert.assertTrue(nscore4HomePage.verifyProductreflectedInCurrentRelationshipSection().contains(SKU), "Product is not reflected in Current relationship section ");

		nscore4HomePage.clickAvailabilityTab();
		nscore4HomePage.SelectCatalogAndClickSave();
		s_assert.assertTrue(nscore4HomePage.verifySuccessMessageForCatalogSaved(), "Category saved method is not displayed ");
		nscore4HomePage.clickProductPropertiesTab();
		nscore4HomePage.selectallCartsAndClickSave();
		s_assert.assertTrue(nscore4HomePage.verifySuccessMessageForProductPropertiesSaved(), "Product properties saved method is not displayed");

		s_assert.assertAll();

	}

	//QTest ID TC-514 NSC4_AccountsTab_Full OrderHistoryPlaceNewOrder
	@Test
	public void testNSC4AccountsTabFullOrderHistoryPlaceNewOrder_514(){
		String accountNumber = null;
		String retail;
		String pc;
		String consultant;
		String orderNum=null;
		List<Map<String, Object>> randomSKUList =  null;
		String SKU=null;
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		randomSKUList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_SKU,RFL_DB);
		SKU=(String) getValueFromQueryResult(randomSKUList, "SKU"); 
		logger.info("Account number from DB is "+accountNumber);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		nscore4HomePage.clickAccountsTab();
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickConsultantId(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		nscore4HomePage.clickSearchSKU(SKU);
		nscore4HomePage.clickAddToOrder();
		nscore4HomePage.clickApplyPaymentButton();
		nscore4HomePage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4HomePage.verifyProductNamePresentOnOrderDetailsPage(SKU), "Details Saved success message is not displayed ");
		orderNum  = nscore4HomePage.getOrderName();
		nscore4HomePage.clickConsultantNameOnOrderDetailsPage();
		nscore4HomePage.clickSearchOrderId(orderNum);
		s_assert.assertTrue(nscore4HomePage.verifyOrderDisplayedInSearchResult(orderNum), "Order is Not displayed in the searched results");
		s_assert.assertAll();
	}

	//QTest ID TC-2470 Update billing profile CRP template NSC4
	@Test
	public void testCRPTemplateEditPaymentProfile_2470() {
		String emailID = null;
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String addressLine1 = TestConstantsRFL.ADDRESS_LINE1;
		String zipCode = TestConstantsRFL.POSTAL_CODE;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String PaymentProfileName=newPaymentProfileName+" "+lastNameProfileName;
		String enteredCardNameOnUI=null;
		String paymentProfileNameFromUI=null;

		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		//nscore4HomePage.clickConsultantId(accountNumber);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditCRPTemplate();
		nscore4HomePage.clickEditPaymentProfile();
		nscore4HomePage.editPaymentProfile(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.acceptQASPopup();
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(PaymentProfileName),"Expected payment profile name is:"+PaymentProfileName+" But on UI is: "+paymentProfileNameFromUI);
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		s_assert.assertTrue(enteredCardNameOnUI.contains(nameOnCard),"Expected payment profile name on card is:"+enteredCardNameOnUI+" But on UI is: "+nameOnCard);
		s_assert.assertAll();

	}

	// QTest ID TC-2471 Pulse template > Edit payment Profile
	@Test
	public void testPulseTemplateEditPaymentProfile_2471() {
		String emailID = null;
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String PaymentProfileName=newPaymentProfileName+" "+lastNameProfileName;
		String enteredCardNameOnUI=null;
		String paymentProfileNameFromUI=null;

		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditPulseTemplate();
		nscore4HomePage.clickEditPaymentProfile();
		nscore4HomePage.editPaymentProfile(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.acceptQASPopup();
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(PaymentProfileName),"Expected payment profile name is:"+PaymentProfileName+" But on UI is: "+paymentProfileNameFromUI);
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		s_assert.assertTrue(enteredCardNameOnUI.contains(nameOnCard),"Expected payment profile name on card is:"+enteredCardNameOnUI+" But on UI is: "+nameOnCard);
		s_assert.assertAll();

	}

	// QTest ID TC-2473 CRP template > Add Shipping Profile
	@Test
	public void testCRPTemplateAddShippingProfile_2473() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newShippingProfileName = "newSP"+randomNum;
		String attentionCO ="SP";
		String addressLine1 =TestConstantsRFL.ADDRESS_LINE_1_US;
		String zipCode= TestConstantsRFL.POSTAL_CODE_US;
		String emailID = null;
		String accountNumber = null;
		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditCRPTemplate();
		nscore4HomePage.clickAddNewShippingProfileLink();
		nscore4HomePage.addANewShippingProfile(newShippingProfileName, attentionCO, addressLine1, zipCode);
		//click 'SAVE ADDRESS BTN'
		nscore4HomePage.clickSaveAddressBtn();
		nscore4HomePage.acceptQASPopup();
		//			nscore4HomePage.refreshPage();
		//verify newly created shipping profile created?
		s_assert.assertTrue(nscore4HomePage.isNewlyCreatedShippingProfilePresent(newShippingProfileName),"Newly created Shipping Profile is not Present");
		s_assert.assertAll();
	}

	// QTest ID TC-2472 CRP template > Add payment Profile
	@Test
	public void testCRPTemplateAddPaymentProfile_2472() {
		String emailID = null;
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String lastName = "lN";
		String attentionName = newPaymentProfileName+" "+lastNameProfileName;
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String addressLine1 = TestConstantsRFL.ADDRESS_LINE1;
		String zipCode = TestConstantsRFL.POSTAL_CODE;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String PaymentProfileName=newPaymentProfileName+" "+lastNameProfileName;
		String paymentProfileNameFromUI=null;
		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditCRPTemplate();
		nscore4HomePage.addPaymentMethod(newPaymentProfileName, lastName, nameOnCard, cardNumber,CVV,attentionName);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.changeDefaultBillingProfile(PaymentProfileName);
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(PaymentProfileName),"Expected payment profile name is:"+PaymentProfileName+" But on UI is: "+paymentProfileNameFromUI);
		nscore4HomePage.clickSaveTemplate();
		s_assert.assertAll();

	}	

	//QTest ID TC-1963 NSC4_Browse by market
	@Test()
	public void testBrowseBymarket_1963(){
		String firstName = null;
		String lastName = null;
		String accountNumber = null;
		List<Map<String, Object>> randomConsultantAddressList =  null;
		String stateFromDB=null;
		String emailID=null;

		logger.info("DB is "+RFL_DB);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_EMAILID,RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantAccountList, "EmailAddress");
		firstName = (String) getValueFromQueryResult(randomConsultantAccountList, "FirstName");	
		lastName = (String) getValueFromQueryResult(randomConsultantAccountList, "LastName");	
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");	

		randomConsultantAddressList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_ACCOUNT_ADDRESS_DETAILS_FOR_ACCOUNT_INFO_QUERY_RFL,emailID), RFL_DB);
		stateFromDB = (String) getValueFromQueryResult(randomConsultantAddressList, "State");

		nscore4HomePage.selectByLocation(stateFromDB);
		nscore4HomePage.enterFirstName(firstName);
		nscore4HomePage.enterLastName(lastName);
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isSearchResultContainsFirstAndLastName(accountNumber,firstName, lastName),"First and last name not present in search results");
		s_assert.assertAll();
	}

	// QTest ID TC-1951 Update billing profile Pulse template NSC4
	@Test
	public void testUpdateBillingProfilePulseTemplateNSC4_1951q() {
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String attentionName = "RFAutoNSCore4"+randomNum;
		String PaymentProfileName=newPaymentProfileName+" "+lastNameProfileName;
		String enteredCardNameOnUI=null;
		String paymentProfileNameFromUI=null;

		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditPulseTemplate();

		nscore4HomePage.addPaymentMethod(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV,attentionName);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.changeDefaultBillingProfile(PaymentProfileName);

		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		nameOnCard=nameOnCard+randomNum;

		nscore4HomePage.clickEditPaymentProfile();
		nscore4HomePage.editPaymentProfile(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.acceptQASPopup();
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(newPaymentProfileName+" "+lastNameProfileName),"Expected payment profile name is:"+newPaymentProfileName+" "+lastNameProfileName+" But on UI is: "+paymentProfileNameFromUI);
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		s_assert.assertTrue(enteredCardNameOnUI.contains(nameOnCard),"Expected payment profile name on card is:"+enteredCardNameOnUI+" But on UI is: "+nameOnCard);
		s_assert.assertAll();

	}

	// QTest ID TC-1952 Save new billing profile Pulse template NSC4
	@Test
	public void testSaveNewBillingProfilePulseTemplateNSC4_1952q() {
		String emailID = null;
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String PaymentProfileName=newPaymentProfileName+" "+lastNameProfileName;
		String attentionName = "RFAutoNSCore4"+randomNum;
		String enteredCardNameOnUI=null;
		String paymentProfileNameFromUI=null;

		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditPulseTemplate();

		nscore4HomePage.addPaymentMethod(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV,attentionName);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.changeDefaultBillingProfile(PaymentProfileName);
		nscore4HomePage.clickSaveTemplate();
		nscore4HomePage.clickCustomerNameOnOrderDetailsPage(accountNumber);
		nscore4HomePage.clickEditPulseTemplate();

		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(PaymentProfileName),"Expected profile name is "+PaymentProfileName+" But Actual on UI is:"+paymentProfileNameFromUI);
		s_assert.assertTrue(enteredCardNameOnUI.contains(nameOnCard),"Expected profile card name is "+nameOnCard+" But Actual on UI is:"+enteredCardNameOnUI);
		s_assert.assertAll();
	}


	//QTest ID TC-1962 Nsc4_browse by Location
	@Test()
	public void testNsc4BrowseByLocation_1962(){
		String firstName = null;
		String lastName = null;
		String accountNumber = null;
		List<Map<String, Object>> randomConsultantAddressList =  null;
		String stateFromDB=null;
		String emailID=null;

		logger.info("DB is "+RFL_DB);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_EMAILID,RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantAccountList, "EmailAddress");
		firstName = (String) getValueFromQueryResult(randomConsultantAccountList, "FirstName"); 
		lastName = (String) getValueFromQueryResult(randomConsultantAccountList, "LastName"); 
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 

		randomConsultantAddressList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_ACCOUNT_ADDRESS_DETAILS_FOR_ACCOUNT_INFO_QUERY_RFL,emailID), RFL_DB);
		stateFromDB = (String) getValueFromQueryResult(randomConsultantAddressList, "State");

		nscore4HomePage.selectByLocation(stateFromDB);
		nscore4HomePage.enterFirstName(firstName);
		nscore4HomePage.enterLastName(lastName);
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isSearchResultContainsFirstAndLastName(accountNumber,firstName, lastName),"First and last name not present in search results");
		s_assert.assertAll();
	}

	// QTest ID TC-1953 Update billing profile PC Autoship template NSC4
	@Test()
	public void testUpdateBillingProfilePCAutoshipTemplateNSC4_1953() {
		String emailID = null;
		String accountNumber = null;
		String paymentProfileNameFromUI = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		String newBillingProfileName = "RFAutoNSCore4"+randomNumber;
		String lastNameProfileName = "lN";
		String PaymentProfileName=newPaymentProfileName+" "+lastNameProfileName;
		String nameOnCard = "rfAutoUser";
		String attentionName = newBillingProfileName+lastNameProfileName;
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String enteredCardNameOnUI=null;
		List<Map<String, Object>> randomPCAccountList =  null;
		randomPCAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_DETAILS_RFL,RFL_DB);
		emailID = (String) getValueFromQueryResult(randomPCAccountList, "EmailAddress");
		accountNumber = (String) getValueFromQueryResult(randomPCAccountList, "AccountNumber");
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditPCAutoshipTemplate();
		nscore4HomePage.addPaymentMethod(newBillingProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV,attentionName);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.changeDefaultBillingProfile(1);
		nscore4HomePage.clickEditPaymentProfile();
		nscore4HomePage.editPaymentProfile(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.acceptQASPopup();
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(PaymentProfileName),"Expected payment profile name is:"+PaymentProfileName+" But on UI is: "+paymentProfileNameFromUI);
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		s_assert.assertTrue(enteredCardNameOnUI.contains(nameOnCard),"Expected payment profile name on card is:"+enteredCardNameOnUI+" But on UI is: "+nameOnCard);
		s_assert.assertAll();
	}

	// QTest ID TC-2475 CRP template > Add Few Items
	@Test
	public void testCRPTemplateAddFewItems_2475() {
		String accountNumber=null;
		List<Map<String, Object>> randomSKUList=null;
		String SKU=null;
		List<Map<String, Object>> randomConsultantList =  null;
		logger.info("Account number from DB is "+accountNumber);
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber"); 
		randomSKUList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_SKU,RFL_DB);
		SKU=(String) getValueFromQueryResult(randomSKUList, "SKU"); 
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditCRPTemplate();
		nscore4HomePage.clickSearchSKU(SKU);
		nscore4HomePage.clickAddToOrder();
		nscore4HomePage.clickSaveAutoshipTemplate();
		s_assert.assertTrue(nscore4HomePage.isNewlyAddedSKUDisplayedOnCRPTemplate(SKU),"newly added sku is not present");
		s_assert.assertAll();

	}

	//QTest ID TC-972 MAIN-5200, SOO and Multiple Line item , Zero Shipping only
	@Test()
	public void testMAIN5200SOOAndMultipleLineItemZeroShippingOnly_972(){
		String accountNumber = null;
		String SKU = null;
		String shippingCharges="0.0";
		String taxTotalFromOrderCreationBeforeUpdate = null;
		String taxTotalFromOrderCreationAfterUpdate = null;
		String paymentsAppliedFromOrderDetailsPage=null;
		String orderTotalFromOrderCreationBeforeRecalculateTAx = null;
		String orderTotalFromOrderCreationAfterRecalculateTAX=null;
		String subtotalFromOrderCreationBeforeRecalculateTAx = null;
		String subtotalFromOrderCreationAfterRecalculateTAX=null;
		String shippingTotalFromOrderCreationAfterRecalculateTax=null;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		nscore4HomePage.addAndGetSpecficProductSKU("5");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder("AARG001"), "SKU = "+"AARG001"+" is not added to the Autoship Order");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		taxTotalFromOrderCreationBeforeUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();

		s_assert.assertTrue(nscore4OrdersTabPage.isTaxVisibleOnOrderCreationPage(), "Tax value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderTotalVisibleOnOrderCreationPage(), "Order Total value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isSubtotalVisibleOnOrderCreationPage(), "SubTotal Total value NOT present before override");

		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterShippingInOverrideOrderWindow(shippingCharges);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		nscore4OrdersTabPage.clickPaymentApplyLink();

		//		shippingTotalFromOrderCreationAfterRecalculateTax=nscore4OrdersTabPage.getShippingTotalFromOrderCreationPage();
		//		taxTotalFromOrderCreationAfterUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		//		orderTotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		//		subtotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();

		//		s_assert.assertFalse(orderTotalFromOrderCreationBeforeRecalculateTAx.contains(orderTotalFromOrderCreationAfterRecalculateTAX),"Order Total is not changed after Re-calculating");
		//		s_assert.assertTrue(subtotalFromOrderCreationAfterRecalculateTAX.contains(subtotalFromOrderCreationBeforeRecalculateTAx),"Sub Total is changed after Re-calculating");
		//		s_assert.assertFalse(taxTotalFromOrderCreationAfterUpdate.contains(taxTotalFromOrderCreationBeforeUpdate),"Tax is not changed after Re-calculating");
		//		s_assert.assertTrue(shippingTotalFromOrderCreationAfterRecalculateTax.contains(shippingCharges),"Shipping is not changed after Re-calculating");

		s_assert.assertTrue(nscore4OrdersTabPage.isTaxVisibleOnOrderCreationPage(), "Tax value NOT present after override");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderTotalVisibleOnOrderCreationPage(), "Order Total value NOT present after override");
		s_assert.assertTrue(nscore4OrdersTabPage.isSubtotalVisibleOnOrderCreationPage(), "SubTotal Total value NOT present after override");

		s_assert.assertAll();
	}

	// QTest ID TC-2474 CRP template > Edit Shipping profile
	@Test
	public void testCRPTemplateEditShippingProfile_2474() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newShippingProfileName = "newSP"+randomNum;
		String attentionCO ="SP";
		String accountNumber = null;
		String shippingProfileNameFromUI=null;
		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditCRPTemplate();
		nscore4HomePage.clickEditNewShippingProfileLink();
		nscore4HomePage.editShippingProfile(newShippingProfileName, attentionCO);
		nscore4HomePage.clickSaveAddressBtn();
		nscore4HomePage.acceptQASPopup();
		shippingProfileNameFromUI=nscore4HomePage.getShippingProfileName();
		s_assert.assertTrue(shippingProfileNameFromUI.contains(newShippingProfileName),"Expected payment profile name is:"+newShippingProfileName+" But on UI is: "+shippingProfileNameFromUI);
		nscore4HomePage.clickSaveTemplate();
		s_assert.assertAll();
	}

	//QTest ID TC-968 MAIN-5200, SOO and zero shipping only
	@Test()
	public void testMAIN5200SOOAndZeroShippingOnly_968(){
		String accountNumber = null;
		String SKU = null;
		String shippingCharges="0.0";
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		s_assert.assertTrue(nscore4OrdersTabPage.isTaxVisibleOnOrderCreationPage(), "Tax value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderTotalVisibleOnOrderCreationPage(), "Order Total value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isSubtotalVisibleOnOrderCreationPage(), "SubTotal Total value NOT present before override");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterShippingInOverrideOrderWindow(shippingCharges);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		s_assert.assertTrue(nscore4OrdersTabPage.isTaxVisibleOnOrderCreationPage(), "Tax value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderTotalVisibleOnOrderCreationPage(), "Order Total value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isSubtotalVisibleOnOrderCreationPage(), "SubTotal Total value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.getShippingFromOrderCreationPage().contains("$0.00"),"shipping charges are NOT zero after override");
		s_assert.assertAll();
	}

	//QTest ID TC-969 MAIN-5200, SOO and Zero Tax  only
	@Test()
	public void testMAIN5200SOOAndZeroTaxOnly_969(){
		String accountNumber = null;
		String SKU = null;
		String tax="0.0";
		String taxBeforeReCalculate=null;
		String taxAfterReCalculate=null;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		s_assert.assertTrue(nscore4OrdersTabPage.isTaxVisibleOnOrderCreationPage(), "Tax value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderTotalVisibleOnOrderCreationPage(), "Order Total value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isSubtotalVisibleOnOrderCreationPage(), "SubTotal Total value NOT present before override");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		taxBeforeReCalculate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.enterTaxInOverrideOrderWindow(tax);
		nscore4OrdersTabPage.clickRecalculateTax();

		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		taxAfterReCalculate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		s_assert.assertTrue(nscore4OrdersTabPage.isTaxVisibleOnOrderCreationPage(), "Tax value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderTotalVisibleOnOrderCreationPage(), "Order Total value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isSubtotalVisibleOnOrderCreationPage(), "SubTotal Total value NOT present before override");
		s_assert.assertFalse(nscore4OrdersTabPage.getTaxFromOrderCreationPage().contains(tax),"tax charges are zero after override");
		s_assert.assertEquals(taxBeforeReCalculate, taxAfterReCalculate);
		s_assert.assertAll();
	}	



	//QTest ID TC-971 MAIN-5200, SOO and Multiple Line item , Zero Tax only
	@Test()
	public void testMAIN5200SOOAndMultipleLineItemZeroTaxOnly_971(){
		String accountNumber = null;
		String SKU = null;
		String tax="0.0";
		String taxBeforeReCalculate=null;
		String taxAfterReCalculate=null;
		String taxTotalFromOrderCreationBeforeUpdate = null;
		String taxTotalFromOrderCreationAfterUpdate = null;
		String paymentsAppliedFromOrderDetailsPage=null;
		String orderTotalFromOrderCreationBeforeRecalculateTAx = null;
		String orderTotalFromOrderCreationAfterRecalculateTAX=null;
		String subtotalFromOrderCreationBeforeRecalculateTAx = null;
		String subtotalFromOrderCreationAfterRecalculateTAX=null;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		nscore4HomePage.addAndGetSpecficProductSKU("5");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder("AARG001"), "SKU = "+"AARG001"+" is not added to the Autoship Order");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		s_assert.assertTrue(nscore4OrdersTabPage.isTaxVisibleOnOrderCreationPage(), "Tax value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderTotalVisibleOnOrderCreationPage(), "Order Total value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isSubtotalVisibleOnOrderCreationPage(), "SubTotal Total value NOT present before override");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		taxBeforeReCalculate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.enterTaxInOverrideOrderWindow(tax);
		nscore4OrdersTabPage.clickRecalculateTax();

		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		taxAfterReCalculate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		s_assert.assertEquals(taxBeforeReCalculate, taxAfterReCalculate);
		s_assert.assertTrue(nscore4OrdersTabPage.isTaxVisibleOnOrderCreationPage(), "Tax value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderTotalVisibleOnOrderCreationPage(), "Order Total value NOT present before override");
		s_assert.assertTrue(nscore4OrdersTabPage.isSubtotalVisibleOnOrderCreationPage(), "SubTotal Total value NOT present before override");
		s_assert.assertFalse(nscore4OrdersTabPage.getTaxFromOrderCreationPage().contains(tax),"tax charges are zero after override");
		s_assert.assertAll();
	}

	// QTest ID TC-1954 Save new billing profile PC Autoship template NSC4
	@Test
	public void testSaveNewBillingProfilePCAutoshipTemplateNSC4_1954q() {
		String emailID = null;
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String PaymentProfileName=newPaymentProfileName+" "+lastNameProfileName;
		String attentionName = "RFAutoNSCore4"+randomNum;
		String enteredCardNameOnUI=null;
		String paymentProfileNameFromUI=null;

		emailID = (String) getValueFromQueryResult(randomPCAccountList, "EmailAddress");
		accountNumber = (String) getValueFromQueryResult(randomPCAccountList, "AccountNumber");
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditPCAutoshipTemplate();

		nscore4HomePage.addPaymentMethod(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV,attentionName);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.changeDefaultBillingProfile(newPaymentProfileName+" "+lastNameProfileName);
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		nscore4HomePage.clickSaveTemplate();

		nscore4HomePage.navigateToBackPage();
		nscore4HomePage.reLoadPage();
		nscore4HomePage.changeDefaultBillingProfile(newPaymentProfileName+" "+lastNameProfileName);
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(PaymentProfileName),"Expected profile name is "+PaymentProfileName+" But Actual on UI is:"+paymentProfileNameFromUI);
		s_assert.assertTrue(enteredCardNameOnUI.contains(nameOnCard),"Expected profile card name is "+nameOnCard+" But Actual on UI is:"+enteredCardNameOnUI);
		s_assert.assertAll();
	}

	//QTest ID TC-970 MAIN-5200, SOO and Multiple Line item , Zero Price only
	@Test()
	public void testMAIN5200SOOAndMultipleLineItemZeroPriceOnly_970(){
		String accountNumber = null;
		String SKU = null;
		String price="0.0";
		String taxTotalFromOrderCreationBeforeUpdate = null;
		String taxTotalFromOrderCreationAfterUpdate = null;
		String paymentsAppliedFromOrderDetailsPage=null;
		String orderTotalFromOrderCreationBeforeRecalculateTAx = null;
		String orderTotalFromOrderCreationAfterRecalculateTAX=null;
		String subtotalFromOrderCreationBeforeRecalculateTAx = null;
		String subtotalFromOrderCreationAfterRecalculateTAX=null;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);

		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		nscore4HomePage.addAndGetSpecficProductSKU("5");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder("AARG001"), "SKU = "+"AARG001"+" is not added to the Autoship Order");

		nscore4OrdersTabPage.clickPaymentApplyLink();
		taxTotalFromOrderCreationBeforeUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();

		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterPriceInOverrideOrderWindow(price,"AARG001");
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		nscore4OrdersTabPage.clickPaymentApplyLink();

		taxTotalFromOrderCreationAfterUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();

		s_assert.assertFalse(orderTotalFromOrderCreationBeforeRecalculateTAx.contains(orderTotalFromOrderCreationAfterRecalculateTAX),"Order Total is not changed after Re-calculating");
		s_assert.assertFalse(subtotalFromOrderCreationAfterRecalculateTAX.contains(subtotalFromOrderCreationBeforeRecalculateTAx),"Sub Total is not changed after Re-calculating");
		s_assert.assertFalse(taxTotalFromOrderCreationAfterUpdate.contains(taxTotalFromOrderCreationBeforeUpdate),"Tax is not changed after Re-calculating");

		nscore4OrdersTabPage.clickSubmitOrderBtn();
		paymentsAppliedFromOrderDetailsPage=nscore4OrdersTabPage.getPaymentsAppliedFromOrderDetailsPage();
		s_assert.assertFalse(paymentsAppliedFromOrderDetailsPage.contains(orderTotalFromOrderCreationBeforeRecalculateTAx),"Expected orderTotal on before submit order on UI is: "+orderTotalFromOrderCreationBeforeRecalculateTAx+" But After order placed Actual on UI "+paymentsAppliedFromOrderDetailsPage);
		s_assert.assertAll();
	}		       				

	//QTest ID TC-973 MAIN-5200, Override Comission
	@Test()
	public void testMAIN5200OverrideComission_973(){
		String accountNumber = null;
		String SKU = null;
		String comissionableValue="0.0";
		String taxTotalFromOrderCreationBeforeUpdate = null;
		String taxTotalFromOrderCreationAfterUpdate = null;
		String paymentsAppliedFromOrderDetailsPage=null;
		String orderTotalFromOrderCreationBeforeRecalculateTAx = null;
		String orderTotalFromOrderCreationAfterRecalculateTAX=null;
		String subtotalFromOrderCreationBeforeRecalculateTAx = null;
		String subtotalFromOrderCreationAfterRecalculateTAX=null;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);

		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		nscore4HomePage.addAndGetSpecficProductSKU("5");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder("AARG001"), "SKU = "+"AARG001"+" is not added to the Autoship Order");

		nscore4OrdersTabPage.clickPaymentApplyLink();
		taxTotalFromOrderCreationBeforeUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();

		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterCVInOverrideOrderWindow(comissionableValue);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		nscore4OrdersTabPage.clickPaymentApplyLink();

		taxTotalFromOrderCreationAfterUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();

		s_assert.assertTrue(orderTotalFromOrderCreationBeforeRecalculateTAx.contains(orderTotalFromOrderCreationAfterRecalculateTAX),"Order Total is not changed after Re-calculating");
		s_assert.assertTrue(subtotalFromOrderCreationAfterRecalculateTAX.contains(subtotalFromOrderCreationBeforeRecalculateTAx),"Sub Total is not changed after Re-calculating");
		s_assert.assertTrue(taxTotalFromOrderCreationAfterUpdate.contains(taxTotalFromOrderCreationBeforeUpdate),"Tax is not changed after Re-calculating");

		nscore4OrdersTabPage.clickSubmitOrderBtn();
		paymentsAppliedFromOrderDetailsPage=nscore4OrdersTabPage.getPaymentsAppliedFromOrderDetailsPage();
		s_assert.assertTrue(paymentsAppliedFromOrderDetailsPage.contains(orderTotalFromOrderCreationBeforeRecalculateTAx),"Expected orderTotal on before submit order on UI is: "+orderTotalFromOrderCreationBeforeRecalculateTAx+" But After order placed Actual on UI "+paymentsAppliedFromOrderDetailsPage);
		s_assert.assertAll();
	}		

	//QTest ID TC-976 MAIN-5200, Override the price higher than the original price
	//QTest ID TC-981 MAIN-5200, Override the price higher than the original price
	//QTest ID TC-963 MAIN-5200, Perform Override with a value more than the original
	@Test()
	public void testOverridePriceHigherThanOriginal_976_981_963(){
		String accountNumber = null;
		String SKU = null;
		String orignalPrice= null;
		String updatedPrice = null;
		String updateByValue = "5";
		String operation = "Add";
		String errorMessage = null;
		String expectedError = "Please check all of the values to make sure they are not negative or greater than the original amount.";

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		orignalPrice = nscore4HomePage.getAddedProductPriceBasedOnSKU(SKU);
		updatedPrice = nscore4HomePage.updatePriceForProduct(orignalPrice,updateByValue,operation);

		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterPriceInOverrideOrderWindow(updatedPrice,SKU);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		errorMessage = nscore4OrdersTabPage.getErrorMessageForgreaterProductPrice();
		s_assert.assertTrue(errorMessage.contains(expectedError),"Expected error message after price increase"+expectedError+" but Actual"+errorMessage);
		s_assert.assertAll();
	}

	// QTest ID TC-974 MAIN-5200, Override shipping
	// QTest ID TC-983 MAIN-5200, Override shipping
	@Test()
	public void testMAIN5200OverrideShipping_974_983() {
		String accountNumber = null;
		String SKU = null;
		String originalShippingPrice = null;
		String updatedShippingPrice = null;
		String updatedShippingPriceAfterRecalculateTAX = null;
		String operation = "Subtract";
		String shipping = "5.0";
		String taxTotalFromOrderCreationBeforeUpdate = null;
		String taxTotalFromOrderCreationAfterUpdate = null;
		String paymentsAppliedFromOrderDetailsPage = null;
		String orderTotalFromOrderCreationBeforeRecalculateTAx = null;
		String orderTotalFromOrderCreationAfterRecalculateTAX = null;
		String subtotalFromOrderCreationBeforeRecalculateTAx = null;
		String subtotalFromOrderCreationAfterRecalculateTAX = null;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is " + accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);

		// click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU = nscore4HomePage.addAndGetProductSKU("10");
		nscore4HomePage.addAndGetSpecficProductSKU("5");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU),"SKU = " + SKU + " is not added to the Autoship Order");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder("AARG001"),"SKU = " + "AARG001" + " is not added to the Autoship Order");

		nscore4OrdersTabPage.clickPaymentApplyLink();
		taxTotalFromOrderCreationBeforeUpdate = nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationBeforeRecalculateTAx = nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationBeforeRecalculateTAx = nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();
		originalShippingPrice = nscore4OrdersTabPage.getShippingTotalFromOrderCreationPage();
		updatedShippingPrice = nscore4HomePage.updatePriceForProduct(originalShippingPrice, shipping, operation);

		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterShippingInOverrideOrderWindow(updatedShippingPrice);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		//		nscore4OrdersTabPage.clickPaymentApplyLink();

		taxTotalFromOrderCreationAfterUpdate = nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationAfterRecalculateTAX = nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationAfterRecalculateTAX = nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();
		updatedShippingPriceAfterRecalculateTAX = nscore4OrdersTabPage.getShippingTotalFromOrderCreationPage();

		//		s_assert.assertFalse(orderTotalFromOrderCreationBeforeRecalculateTAx.contains(orderTotalFromOrderCreationAfterRecalculateTAX),"Order Total is not changed after Re-calculating Shipping");
		//		s_assert.assertTrue(subtotalFromOrderCreationAfterRecalculateTAX.contains(subtotalFromOrderCreationBeforeRecalculateTAx),"Sub Total is changed after Re-calculating Shipping");
		//		s_assert.assertFalse(taxTotalFromOrderCreationAfterUpdate.contains(taxTotalFromOrderCreationBeforeUpdate),"Tax is not changed after Re-calculating Shipping");
		s_assert.assertTrue(updatedShippingPriceAfterRecalculateTAX.contains(updatedShippingPrice),"Shipping price is not updated");

		//		nscore4OrdersTabPage.clickSubmitOrderBtn();
		//		paymentsAppliedFromOrderDetailsPage = nscore4OrdersTabPage.getPaymentsAppliedFromOrderDetailsPage();
		//		s_assert.assertFalse(paymentsAppliedFromOrderDetailsPage.contains(orderTotalFromOrderCreationBeforeRecalculateTAx),"Expected orderTotal on before submit order on UI is: "+ orderTotalFromOrderCreationBeforeRecalculateTAx + " But After order placed Actual on UI "+ paymentsAppliedFromOrderDetailsPage);
		s_assert.assertAll();
	}  		 

	// QTest ID TC-975 MAIN-5200, Override_not change on any value
	// QTest ID TC-982 MAIN-5200, Override_not change on any value
	@Test()
	public void testMAIN5200OverrideOverrideNotChangeOnAnyValue_975_982() {
		String accountNumber = null;
		String SKU = null;
		String originalShippingPrice = null;
		String updatedShippingPriceAfterRecalculateTAX = null;
		String subtotalFromOrderCreationBeforeRecalculateTAx = null;
		String subtotalFromOrderCreationAfterRecalculateTAX = null;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is " + accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);

		// click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU = nscore4HomePage.addAndGetProductSKU("10");
		nscore4HomePage.addAndGetSpecficProductSKU("5");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU),"SKU = " + SKU + " is not added to the Autoship Order");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder("AARG001"),"SKU = " + "AARG001" + " is not added to the Autoship Order");

		nscore4OrdersTabPage.clickPaymentApplyLink();
		subtotalFromOrderCreationBeforeRecalculateTAx = nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();
		originalShippingPrice = nscore4OrdersTabPage.getShippingTotalFromOrderCreationPage();

		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();

		subtotalFromOrderCreationAfterRecalculateTAX = nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();
		updatedShippingPriceAfterRecalculateTAX = nscore4OrdersTabPage.getShippingTotalFromOrderCreationPage();

		s_assert.assertTrue(subtotalFromOrderCreationAfterRecalculateTAX.contains(subtotalFromOrderCreationBeforeRecalculateTAx),"Sub Total is changed after perform override");
		s_assert.assertTrue(updatedShippingPriceAfterRecalculateTAX.contains(originalShippingPrice),"Shipping price is changed after perform override");

		s_assert.assertAll();
	}	

	//QTest ID TC-941 MAIN-5200, Perform Override with a value less than the original
	@Test
	public void testMAIN5200PerformOverrideWithAValueLessThanTheOriginal_941(){
		String accountNumber = null;
		List<Map<String, Object>> randomSKUList=null;
		String SKU=null;
		String price=null;
		String QV=null;
		String CV=null;
		String taxBeforeUpdate=null;
		String shippingBeforeUpdate=null;
		String taxBeforeRecalculation=null;
		String taxAfterRecalculation=null;
		String taxAfterUpdate=null;
		String priceValue=null;
		String shippingAfterRecalculation=null;
		String shippingBeforeRecalculation=null;
		String shippingAfterUpdate=null;
		String orignalPrice= null;
		String updatedPrice = null;
		String updateByValue = "10";
		String operation = "Subtraction";
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is "+accountNumber);
		randomSKUList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_SKU,RFL_DB);
		SKU=(String) getValueFromQueryResult(randomSKUList, "SKU");

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		orignalPrice = nscore4HomePage.getAddedProductPriceBasedOnSKU(SKU);
		updatedPrice = nscore4HomePage.updatePriceForProduct(orignalPrice,updateByValue,operation);
		taxBeforeUpdate =nscore4OrdersTabPage.getTaxFromOrderCreationPage();

		nscore4OrdersTabPage.clickPerformOverridesButton();
		priceValue=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage().split("\\$")[1];
		taxBeforeRecalculation=nscore4OrdersTabPage.getTaxFromOverrideOrderWindow();
		shippingBeforeRecalculation=nscore4OrdersTabPage.getShippingValueFromOverrideOrderWindow();
		nscore4OrdersTabPage.clickRecalculateTax();
		taxAfterRecalculation=nscore4OrdersTabPage.getTaxFromOverrideOrderWindow();
		shippingAfterRecalculation=nscore4OrdersTabPage.getShippingValueFromOverrideOrderWindow();
		s_assert.assertTrue(taxAfterRecalculation.contains(taxBeforeRecalculation),"Tax is changed after recalcuating without changing value");
		s_assert.assertTrue(shippingAfterRecalculation.contains(shippingBeforeRecalculation),"Shipping is changed after recalcuating without changing value");
		nscore4OrdersTabPage.enterPriceInOverrideOrderWindow(updatedPrice,SKU);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		taxAfterUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		shippingAfterUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		s_assert.assertFalse(taxAfterUpdate.contains(taxBeforeUpdate),"Tax is not updated after updating shipping charges to zero");
		s_assert.assertAll();

	}

	//QTest ID TC-942 MAIN-5200, Edit QV on override 
	@Test
	public void testMAIN5200EditQVOnOverride_942(){
		String accountNumber = null;
		List<Map<String, Object>> randomSKUList=null;
		String SKU=null;
		String orignalQV=null;
		String updatedQV = null;
		String updateByValue = "5";
		String operation = "Subtraction";

		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is "+accountNumber);
		randomSKUList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_SKU,RFL_DB);
		SKU=(String) getValueFromQueryResult(randomSKUList, "SKU");

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		orignalQV = nscore4HomePage.getAddedProductQVBasedOnSKU(SKU);
		updatedQV = nscore4HomePage.updateQVForProduct(orignalQV,updateByValue,operation);
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.enterQVValueInOverrideOrderWindow(updatedQV,SKU);
		nscore4OrdersTabPage.clickRecalculateTax();
		s_assert.assertFalse(orignalQV.contains(updatedQV),"QV is not updated after changing the value");
		s_assert.assertAll();

	}

	// QTest ID TC-1950 Save new billing profile CRP template NSC4
	@Test
	public void testSaveNewBillingProfileCRPTemplateNSC4_1950q() {
		String emailID = null;
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newPaymentProfileName = "RFAutoNSCore4"+randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String cardNumber =  TestConstantsRFL.CARD_NUMBER;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String PaymentProfileName=newPaymentProfileName+" "+lastNameProfileName;
		String attentionName = "RFAutoNSCore4"+randomNum;
		String enteredCardNameOnUI=null;
		String paymentProfileNameFromUI=null;
		List<Map<String, Object>> randomConsultantList =  null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		emailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditCRPTemplate();
		nscore4HomePage.addPaymentMethod(newPaymentProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV,attentionName);
		nscore4HomePage.clickSavePaymentMethodBtn();
		//		nscore4HomePage.changeDefaultBillingProfile(PaymentProfileName);
		nscore4HomePage.clickSaveTemplate();
		nscore4HomePage.clickCustomerNameOnOrderDetailsPage(accountNumber);
		nscore4HomePage.clickEditCRPTemplate();
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		enteredCardNameOnUI=nscore4HomePage.getBillingProfileEnteredCardName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(PaymentProfileName),"Expected profile name is "+PaymentProfileName+" But Actual on UI is:"+paymentProfileNameFromUI);
		s_assert.assertTrue(enteredCardNameOnUI.contains(nameOnCard),"Expected profile card name is "+nameOnCard+" But Actual on UI is:"+enteredCardNameOnUI);
		s_assert.assertAll();
	}	


	//QTest ID TC-1955 Nsc4_Browse account_migrated account(after rollback)
	@Test
	public void testNsc4BrowseAccountMigratedAccount_After_Rollback_1955q(){
		String firstName = null;
		String lastName = null;
		String accountNumber = null;

		//randomUserList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACCOUNT_DETAILS,RFL_DB);
		firstName = (String) getValueFromQueryResult(randomConsultantAccountList, "FirstName");
		lastName = (String) getValueFromQueryResult(randomConsultantAccountList, "LastName");
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");

		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(firstName, lastName), "First and last name is not present in search result,when searched with account number");
		s_assert.assertTrue(nscore4HomePage.isCIDPresentinSearchResults(accountNumber), "CID/Account Number is not present in search result,when searched with account number");
		s_assert.assertAll();
	}	

	//QTest ID TC-977 MAIN-5200, Change shipping method after override
	//QTest ID TC-980 MAIN-5200, Change shipping method after override
	@Test(enabled=false)//MAIN-7631
	public void testChangeShippingMethodAfterOverride_977_980(){
		String accountNumber = null;
		String SKU = null;
		String orignalPrice= null;
		String updatedPrice = null;
		String updateByValue = "5";
		String operation = "Subtraction";
		String taxTotalFromOrderCreationBeforeUpdate = null;
		String taxTotalFromOrderCreationAfterUpdate = null;
		String orderTotalFromOrderCreationBeforeRecalculateTAx = null;
		String orderTotalFromOrderCreationAfterRecalculateTAX=null;
		String subtotalFromOrderCreationBeforeRecalculateTAx = null;
		String subtotalFromOrderCreationAfterRecalculateTAX=null;
		String shippingFromOrderCreationBeforeUpdatingShippingMethod=null;
		String shippingFromOrderCreationAfterUpgradingShippingMethod=null;
		String shippingFromOrderCreationAfterDowngradingShippingMethod=null;
		String paymentsAppliedFromOrderDetailsPage=null;
		String shippingMethodUpgradeValue="Higher";
		String shippingMethodDowngradeValue="Lower";


		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		orignalPrice = nscore4HomePage.getAddedProductPriceBasedOnSKU(SKU);
		updatedPrice = nscore4HomePage.updatePriceForProduct(orignalPrice,updateByValue,operation);

		nscore4OrdersTabPage.clickPaymentApplyLink();
		//Get original tax,orderTotal and subTotal values
		taxTotalFromOrderCreationBeforeUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();

		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterPriceInOverrideOrderWindow(updatedPrice,SKU);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		nscore4OrdersTabPage.clickPaymentApplyLink();

		//Get updated tax,orderTotal and subtTotal values
		taxTotalFromOrderCreationAfterUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();
		shippingFromOrderCreationBeforeUpdatingShippingMethod = nscore4OrdersTabPage.getShippingFromOrderCreationPage();

		//Assert the updated values tax,orderTotal and subTotal values
		s_assert.assertFalse(orderTotalFromOrderCreationBeforeRecalculateTAx.contains(orderTotalFromOrderCreationAfterRecalculateTAX),"Order Total is not changed after Re-calculating");
		s_assert.assertFalse(subtotalFromOrderCreationAfterRecalculateTAX.contains(subtotalFromOrderCreationBeforeRecalculateTAx),"Sub Total is not changed after Re-calculating");
		s_assert.assertFalse(taxTotalFromOrderCreationAfterUpdate.contains(taxTotalFromOrderCreationBeforeUpdate),"Tax is not changed after Re-calculating");

		//Update shipping method one method higher.
		nscore4OrdersTabPage.updateShippingMethod(shippingMethodUpgradeValue);
		nscore4OrdersTabPage.clickPaymentApplyLink();
		shippingFromOrderCreationAfterUpgradingShippingMethod= nscore4OrdersTabPage.getShippingFromOrderCreationPage();

		//Assert for shipping price after one method higher.
		s_assert.assertFalse(shippingFromOrderCreationAfterUpgradingShippingMethod.contains(shippingFromOrderCreationBeforeUpdatingShippingMethod),"Shipping total after upgrading shipping method Expected "+shippingFromOrderCreationAfterUpgradingShippingMethod+" And Actual on UI "+shippingFromOrderCreationBeforeUpdatingShippingMethod);

		//Update shipping method one method lower.
		nscore4OrdersTabPage.updateShippingMethod(shippingMethodDowngradeValue);
		nscore4OrdersTabPage.clickPaymentApplyLink();
		shippingFromOrderCreationAfterDowngradingShippingMethod= nscore4OrdersTabPage.getShippingFromOrderCreationPage();

		//Assert for shipping price after one method lower.
		s_assert.assertFalse(shippingFromOrderCreationAfterDowngradingShippingMethod.contains(shippingFromOrderCreationAfterUpgradingShippingMethod),"Shipping total after degrading shipping method Expected "+shippingFromOrderCreationAfterDowngradingShippingMethod+" And Actual on UI "+shippingFromOrderCreationAfterUpgradingShippingMethod);

		//Submit the override.
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		paymentsAppliedFromOrderDetailsPage=nscore4OrdersTabPage.getPaymentsAppliedFromOrderDetailsPage();
		s_assert.assertFalse(paymentsAppliedFromOrderDetailsPage.contains(orderTotalFromOrderCreationBeforeRecalculateTAx),"Expected orderTotal on before submit order on UI is: "+orderTotalFromOrderCreationBeforeRecalculateTAx+" But After order placed Actual on UI "+paymentsAppliedFromOrderDetailsPage);
		s_assert.assertAll();

	}

	//QTest ID TC-247 NSC4_SitesTab_nsCorporate_CorporateSiteMap
	@Test(enabled=false)//should be tested manually
	public void testNSC4SitesTabBaseNSCorporateSiteMap_247(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String sites = "Sites";
		String siteMap = "Site Map";
		String linkText = "AutoRF"+randomNum;
		String pageOption = "Meet Dr. Fields";
		String clearNavigationCache = "Clear Navigation Cache";
		String reloadProductCache  = "Reload Product Cache";
		String WhoWeAreLink ="Who We Are";
		String aboutRFLink ="About R+F";
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfCorporate(siteMap);
		nscore4SitesTabPage.clickAddLinkForSiteMap();
		nscore4SitesTabPage.enterLinkTextForSiteMap(linkText);
		nscore4SitesTabPage.selectPagesForSiteMap(pageOption);
		nscore4SitesTabPage.clickSaveBtnOnSiteMap();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkTextNamePresentInTreeMap(linkText), "Link text name is not prsent in site map tree");
		//nscore4SitesTabPage.expandTheTreeOfSiteMapOfBasePWS();
		nscore4SitesTabPage.moveToSiteMapLinkUnderDrFieldsForAddLinkOfBasePWS(linkText, aboutRFLink);
		nscore4SitesTabPage.moveToSiteMapLinkUnderDrFieldsForAddLinkOfBasePWS(linkText, WhoWeAreLink);
		nscore4SitesTabPage.clickActivateLinkOnSiteMap();
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(reloadProductCache);
		nscore4SitesTabPage.clearSiteProductCacheAll();
		nscore4HomePage.clickTab(sites);
		//Verify on SF
		nscore4HomePage.navigateToStoreFrontURL();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresentUnderAboutRAndF(linkText), "Link name is not present under R+F");
		nscore4HomePage.navigateToBaseURL();
		login(TestConstantsRFL.USERNAME_NSC4, TestConstantsRFL.PASSWORD_NSC4);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfCorporate(siteMap);
		nscore4SitesTabPage.expandTheTreeOfSiteForWhoWeAreLink();
		nscore4SitesTabPage.clickLinkTextNamePresentInTreeMap(linkText); 
		nscore4SitesTabPage.clickDeactivateLinkOnSiteMap();
		s_assert.assertTrue(nscore4SitesTabPage.isActivateLinkPresentOnSiteMap(), "Activate link is not present after clicked on Deactivate link");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(reloadProductCache);
		nscore4SitesTabPage.clearSiteProductCacheAll();
		nscore4HomePage.clickTab(sites);
		//Verify on SF
		nscore4HomePage.navigateToStoreFrontURL();
		s_assert.assertFalse(nscore4SitesTabPage.isLinkPresentUnderAboutRAndF(linkText), "Link name is  present under R+F");
		s_assert.assertAll();
	}


	//QTest ID TC-944 MAIN-5200, Reduce the number of products on override
	@Test
	public void testMAIN5200ReduceTheNumberOfProductsOnOverride_944(){
		String accountNumber = null;
		String SKU = null;
		String orignalPrice= null;
		String updatedPrice = null;
		String updateByValue = "5";
		String quantity = "10";
		String operation = "Subtraction";

		nscore4OrdersTabPage =new NSCore4OrdersTabPage(driver);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("5");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		orignalPrice = nscore4HomePage.getAddedProductPriceBasedOnSKU(SKU);
		updatedPrice = nscore4HomePage.updatePriceForProduct(orignalPrice,updateByValue,operation);

		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.clickRecalculateTax();

		nscore4OrdersTabPage.enterPriceInOverrideOrderWindow(updatedPrice, SKU);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		nscore4OrdersTabPage.clickPaymentApplyLink();
		//Update Quantity of product to 10
		nscore4OrdersTabPage.updateQuantityOfProductOnBasisOfProductSKU(SKU, quantity);
		nscore4OrdersTabPage.clickPaymentApplyLink();
		// Update Quantity of product to 5
		quantity = "5";
		nscore4OrdersTabPage.updateQuantityOfProductOnBasisOfProductSKU(SKU, quantity);
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusAfterSubmitOrder(),"Order is not submitted after submit order");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(),"This is not order details page");
		s_assert.assertAll();
	}	


	// QTest ID TC-984 Move an order to an Open Commission period. (To the Future)
	@Test(enabled=false)
	public void testMoveAnOrderToAnOpenCommissionPeriodToTheFuture_984() throws Exception {
		String orderNumber=null;
		String commissionDateBeforeChanged=null;
		String commissionDateAfterChanged=null;
		String dateToBeChanged="15";
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ORDER_DETAILS, orderNumber),RFL_DB);
		orderNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "OrderNumber");
		logger.info("Order number from DB is " + orderNumber);
		nscore4HomePage.clickOrdersTab();
		nscore4OrdersTabPage.enterOrderIDInInputField(orderNumber);

		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(),"This is not order details page");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");

		commissionDateBeforeChanged = nscore4OrdersTabPage.clickAndGetCommissionDateFromOrderDetailsPage();
		nscore4OrdersTabPage.changeDateToNextTwoMonth(dateToBeChanged);
		commissionDateAfterChanged = nscore4OrdersTabPage.clickAndGetCommissionDateFromOrderDetailsPage();
		s_assert.assertFalse(commissionDateAfterChanged.contains(commissionDateBeforeChanged),"Commision date is not changed to future two months head");
		s_assert.assertAll();
	}	

	//QTest ID TC-964 MAIN-5200, Recalculate after updating all fields to zero
	@Test
	public void testRecalculateAfterUpdatingAllFieldsToZero_964(){
		String accountNumber = null;
		String SKU = null;
		String orignalPrice= null;
		String updatedPrice = null;
		String operation = "Subtraction";
		String taxTotalFromOrderCreationAfterUpdate = null;
		String orderTotalFromOrderCreationAfterRecalculateTAX=null;
		String subtotalFromOrderCreationAfterRecalculateTAX=null;
		String orderTotalFromOrderCreationBeforeRecalculateTAx = null;
		String paymentsAppliedFromOrderDetailsPage=null;
		String taxOrderTotalAndSubTotalFromOrderDetails = null;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		orignalPrice = nscore4HomePage.getAddedProductPriceBasedOnSKU(SKU);
		updatedPrice = nscore4HomePage.updatePriceForProduct(orignalPrice,orignalPrice,operation);

		nscore4OrdersTabPage.clickPaymentApplyLink();	
		//Get original tax,orderTotal and subTotal values
		orderTotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterPriceInOverrideOrderWindow(updatedPrice,SKU);
		nscore4OrdersTabPage.enterQVValueInOverrideOrderWindow(updatedPrice, SKU);
		nscore4OrdersTabPage.enterCVValueInOverrideOrderWindow(updatedPrice, SKU);
		nscore4OrdersTabPage.enterTaxInOverrideOrderWindow(updatedPrice);
		nscore4OrdersTabPage.enterShippingInOverrideOrderWindow(updatedPrice);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		//Get updated tax,orderTotal and subTotal values
		taxTotalFromOrderCreationAfterUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();

		//Assert the updated values tax,orderTotal and subTotal values
		s_assert.assertTrue(orderTotalFromOrderCreationAfterRecalculateTAX.contains("$0.00"),"Order Total is not changed after Re-calculating");
		s_assert.assertTrue(subtotalFromOrderCreationAfterRecalculateTAX.contains("$0.00"),"Sub Total is not changed after Re-calculating");
		s_assert.assertTrue(taxTotalFromOrderCreationAfterUpdate.contains("$0.00"),"Tax is not changed after Re-calculating");

		//Submit the override.
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		paymentsAppliedFromOrderDetailsPage=nscore4OrdersTabPage.getPaymentsAppliedFromOrderDetailsPage();
		taxOrderTotalAndSubTotalFromOrderDetails=nscore4OrdersTabPage.getTaxOrderTotalAndSubtotalAppliedFromOrderDetailsPage();

		//Assert for payment applied,tax,orderTotal and orderSubtotal after placing order.
		s_assert.assertTrue(paymentsAppliedFromOrderDetailsPage.contains("$0.00"),"Expected orderTotal After submit order on UI is: $0.00, But After order placed Actual on UI "+orderTotalFromOrderCreationBeforeRecalculateTAx);
		s_assert.assertTrue(taxOrderTotalAndSubTotalFromOrderDetails.contains("$0.00"),"Expected Tax,orderTotal and subtotal after submit order on UI is: $0.00, But After order placed Actual on UI "+taxTotalFromOrderCreationAfterUpdate);
		s_assert.assertAll();
	}

	//QTest ID TC-259 NSC4_SitesTab_nsDistributor_BasePWSSiteMap
	@Test(enabled=false)//should be tested manually
	public void testNSC4SitesTabBasePWSSiteMap_259q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String sites = "Sites";
		String siteMap = "Site Map";
		String linkText = "AutoRF"+randomNum;
		String meetDrFieldsLink ="Meet Dr. Fields";
		String meetTheDoctor = "Meet the Doctors";
		String aboutRFLink = "ABOUT R+F";
		String pageOption = "MeetDrFields";
		String clearNavigationCache = "Clear Navigation Cache";
		String reloadProductCache  = "Reload Product Cache";

		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(siteMap);
		nscore4SitesTabPage.clickAddLinkForSiteMap();
		nscore4SitesTabPage.enterLinkTextForSiteMap(linkText);
		nscore4SitesTabPage.selectPagesForSiteMap(pageOption);
		nscore4SitesTabPage.clickSaveBtnOnSiteMap();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkTextNamePresentInTreeMap(linkText), "Link text name is not prsent in site map tree");
		nscore4SitesTabPage.moveToSiteMapLinkUnderDrFieldsForAddLinkOfBasePWS(linkText,aboutRFLink);
		nscore4SitesTabPage.moveToSiteMapLinkUnderDrFieldsForAddLinkOfBasePWS(linkText,meetTheDoctor);
		nscore4SitesTabPage.moveToSiteMapLinkUnderDrFieldsForAddLinkOfBasePWS(linkText,meetDrFieldsLink);
		nscore4SitesTabPage.clickActivateLinkOnSiteMap();
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		//s_assert.assertTrue(nscore4SitesTabPage.getProductClearAndReloadConfirmationMessage().contains("The navigation cache has been cleared"), "Expected confirmation message is: The navigation cache has been cleared but actual on UI is: "+nscore4SitesTabPage.getProductClearAndReloadConfirmationMessage());
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(reloadProductCache);
		nscore4SitesTabPage.clearSiteProductCacheAll();
		//s_assert.assertTrue(nscore4SitesTabPage.getProductClearAndReloadConfirmationMessage().contains("The product cache has been reloaded"), "Expected confirmation message is: The product cache has been reloaded but actual on UI is: "+nscore4SitesTabPage.getProductClearAndReloadConfirmationMessage());
		//Verify on SF
		openCOMSite();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresentUnderAboutRAndFOnComPresent(linkText), "Link name is not present under R+F");
		nscore4HomePage.navigateToBaseURL();
		login(TestConstantsRFL.USERNAME_NSC4, TestConstantsRFL.PASSWORD_NSC4);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(siteMap);
		nscore4SitesTabPage.expandTheTreeOfSiteMapOfBasePWS();
		nscore4SitesTabPage.clickLinkTextNamePresentInTreeMap(linkText);
		nscore4SitesTabPage.clickDeactivateLinkOnSiteMap();
		s_assert.assertTrue(nscore4SitesTabPage.isActivateLinkPresentOnSiteMap(), "Activate link is not present after clicked on Deactivate link");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(reloadProductCache);
		nscore4SitesTabPage.clearSiteProductCacheAll();
		nscore4HomePage.clickTab(sites);
		//Verify on SF
		openCOMSite();
		s_assert.assertFalse(nscore4SitesTabPage.isLinkPresentUnderAboutRAndFOnComPresent(linkText), "Link name is  present under R+F");
		s_assert.assertAll();
	}

	// QTest ID TC-2467 Pulse template > Add payment Profile
	@Test
	public void testPulseTemplateAddPaymentProfile_2467() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = "RFAutoNSCore4" + randomNum;
		String lastNameProfileName = "lN";
		String nameOnCard = "rfAutoUser";
		String attentionName = "RFAutoNSCore4"+randomNum;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String PaymentProfileName = newBillingProfileName + " " + lastNameProfileName;
		String accountNumber = null;
		String paymentProfileNameFromUI = null;

		List<Map<String, Object>> randomConsultantList = null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickEditPulseTemplate();
		//nscore4HomePage.clickAddNewPaymentProfileLink();
		nscore4HomePage.addPaymentMethod(newBillingProfileName, lastNameProfileName, nameOnCard, cardNumber,CVV,attentionName);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.changeDefaultBillingProfile(PaymentProfileName);
		paymentProfileNameFromUI=nscore4HomePage.getBillingProfileName();
		s_assert.assertTrue(paymentProfileNameFromUI.contains(PaymentProfileName),"Expected payment profile name is:"+PaymentProfileName+" But on UI is: "+paymentProfileNameFromUI);
		nscore4HomePage.clickSaveTemplate();
		s_assert.assertAll();
	}	

	//QTest ID TC-271 NSC4_AccountsTab_OverviewProxyLinks
	@Test
	public void testNSC4AccountsTabOverviewProxyLinks_271() throws InterruptedException{
		String accountNumber=null;
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);

		nscore4HomePage.clickAccountsTab();
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickConsultantId(accountNumber);
		nscore4HomePage.clickProxyLink("Product Focus");
		nscore4HomePage.switchToSecondWindow();
		s_assert.assertTrue(driver.getCurrentUrlWithExpectedWaitForURLPresent("myrfo").contains("myrfo"+driver.getEnvironment()+".com"), "User is not on dot com pws after clicking product focus proxy link.");

		if(nscore4HomePage.isMyAccountDropdownLinkPresent()) {
			nscore4HomePage.clickMyAccountDropdownLink();
			s_assert.assertTrue(nscore4HomePage.isLogOutLinkInMyAccountDropdownPresent(),"Log out link is not present_1");
		}else {
			s_assert.assertTrue(nscore4HomePage.verifyUserIsLoggedIn(),"Log out link is not present_1");
		}
		nscore4HomePage.switchToPreviousTab();
		nscore4HomePage.clickProxyLink("Business Focus");
		nscore4HomePage.switchToSecondWindow();
		s_assert.assertTrue(driver.getCurrentUrlWithExpectedWaitForURLPresent("myrfo").contains("myrfo"+driver.getEnvironment()+".biz"), "User is not on dot biz pws after clicking business focus proxy link.");
		if(nscore4HomePage.isMyAccountDropdownLinkPresent()) {
			nscore4HomePage.clickMyAccountDropdownLink();
			s_assert.assertTrue(nscore4HomePage.isLogOutLinkInMyAccountDropdownPresent(),"Log out link is not present_2");
		}else {
			s_assert.assertTrue(nscore4HomePage.verifyUserIsLoggedIn(),"Log out link is not present_2");
		}
		nscore4HomePage.switchToPreviousTab();
		nscore4HomePage.clickProxyLink("Pulse");
		nscore4HomePage.switchToSecondWindow();
		nscore4HomePage.dismissPulsePopup();
		s_assert.assertTrue(driver.getCurrentUrl().contains("pulserfo."+driver.getEnvironment()+".rodanandfields"+".com"), "User is not on pulse after clicking pulse proxy link.");
		if(nscore4HomePage.isMyAccountDropdownLinkPresent()) {
			nscore4HomePage.clickMyAccountDropdownLink();
			s_assert.assertTrue(nscore4HomePage.isLogOutLinkInMyAccountDropdownPresent(),"Log out link is not present_3");
		}else {
			s_assert.assertTrue(nscore4HomePage.verifyUserIsLoggedIn(),"Log out link is not present_3");
		}
		nscore4HomePage.switchToPreviousTab();
		nscore4HomePage.clickProxyLink("Corporate");
		nscore4HomePage.switchToSecondWindow();
		nscore4HomePage.clickRenewLater();
		s_assert.assertTrue(driver.getCurrentUrl().contains("corprfo."+driver.getEnvironment()+".rodanandfields"+".com"), "User is not on corp site after clicking corporate proxy link.");
		if(nscore4HomePage.isMyAccountDropdownLinkPresent()) {
			nscore4HomePage.clickMyAccountDropdownLink();
			s_assert.assertTrue(nscore4HomePage.isLogOutLinkInMyAccountDropdownPresent(),"Log out link is not present_4");
		}else {
			s_assert.assertTrue(nscore4HomePage.verifyUserIsLoggedIn(),"Log out link is not present_4");
		}
		nscore4HomePage.switchToPreviousTab();
		s_assert.assertAll();
	}

	//QTest ID TC-275 NSC4_AccountsTab_FullAccountRecordUpdate
	@Test(priority=20)
	public void testNSC4AccountTabFullAccountRecordUpdate_275(){
		String accountNumber = null;
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4HomePage.clickFullAccountRecordLink();
		//get all values before update
		String taxExemptValue = nscore4HomePage.getTaxExemptValue();
		String dob = nscore4HomePage.getDOBValue();
		String gender = nscore4HomePage.getSelectedGender();
		String dobDay = nscore4HomePage.getDayFromDate(dob);
		//update the values
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum1 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum2 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum3 = CommonUtils.getRandomNum(10000, 1000000);
		int randomNum4 = CommonUtils.getRandomNum(10000, 1000000);
		String userNameForUpdate = "Automation"+randomNum+"@rodanandfields.com";
		String nameForUpdate = "Autoname"+randomNum1;
		String emailIDForUpdate = "auto"+randomNum2+"@rodanandfields.com";
		String taxExemptValueForUpdate =nscore4HomePage.getTaxExemptValueForUpdate(taxExemptValue);
		String nameOnSSNCardForUpdate = "Auto"+randomNum3;
		String dobDayForUpdate = nscore4HomePage.getUpdatedDayFromDate(dobDay);
		String genderForUpdate = nscore4HomePage.getGenderValueForUpdate(gender);
		String attentionNameForUpdate = "Auto"+randomNum4;
		String ZIPCodeForUpdate = TestConstants.POSTAL_CODE_US;
		String phoneNumberForUpdate =  String.valueOf(CommonUtils.getRandomNum(1000, 9999));
		//For Account Access Section
		nscore4HomePage.enterUserName(userNameForUpdate);
		// For Personal Info Section
		nscore4HomePage.enterAttentionName(attentionNameForUpdate);
		nscore4HomePage.enterFirstName(nameForUpdate);
		nscore4HomePage.enterEmailAddress(emailIDForUpdate);
		nscore4HomePage.selectTaxExemptValue(taxExemptValueForUpdate);
		nscore4HomePage.enterNameOnSSNCard(nameOnSSNCardForUpdate);
		nscore4HomePage.clickDOBDate();
		nscore4HomePage.clickSpecficDateOfCalendar(dobDayForUpdate);
		nscore4HomePage.selectGender(genderForUpdate);
		nscore4HomePage.enterLastFourDigitOfPhoneNumber(phoneNumberForUpdate);
		nscore4HomePage.enterZIPCode(ZIPCodeForUpdate);
		nscore4HomePage.clickSaveBtnForAccountRecord();
		s_assert.assertTrue(nscore4HomePage.getUserName().contains(userNameForUpdate), "Expected username is: "+userNameForUpdate+" But actual on UI is "+nscore4HomePage.getUserName());
		s_assert.assertTrue(nscore4HomePage.getFirstName().contains(nameForUpdate), "Expected first name is: "+nameForUpdate+" But actual on UI is "+nscore4HomePage.getFirstName());
		s_assert.assertTrue(nscore4HomePage.getEmailAddress().contains(emailIDForUpdate), "Expected email ID is: "+emailIDForUpdate+" But actual on UI is "+nscore4HomePage.getEmailAddress());
		s_assert.assertTrue(nscore4HomePage.getTaxExemptValue().contains(taxExemptValueForUpdate), "Expected tax exempt value is: "+taxExemptValueForUpdate+" But actual on UI is "+nscore4HomePage.getTaxExemptValue());
		s_assert.assertTrue(nscore4HomePage.getNameOnSSNCard().contains(nameOnSSNCardForUpdate), "Expected name on SSN Card is: "+nameOnSSNCardForUpdate+" But actual on UI is "+nscore4HomePage.getNameOnSSNCard());
		s_assert.assertTrue(nscore4HomePage.getDOBValue().contains(dobDayForUpdate), "Expected day for DOB is: "+dobDayForUpdate+" But actual on UI is "+nscore4HomePage.getDayFromDate(nscore4HomePage.getDOBValue()));
		s_assert.assertTrue(nscore4HomePage.getSelectedGender().contains(genderForUpdate), "Expected gender is: "+genderForUpdate+" But actual on UI is "+nscore4HomePage.getSelectedGender());
		s_assert.assertTrue(nscore4HomePage.getAttentionName().contains(attentionNameForUpdate), "Expected attention name is: "+attentionNameForUpdate+" But actual on UI is "+nscore4HomePage.getAttentionName());
		s_assert.assertTrue(nscore4HomePage.getZIPCode().contains(ZIPCodeForUpdate), "Expected ZIP code is: "+ZIPCodeForUpdate+" But actual on UI is "+nscore4HomePage.getZIPCode());
		s_assert.assertTrue(nscore4HomePage.getLastFourDgitOfPhoneNumber().contains(phoneNumberForUpdate), "Expected phone number is: "+phoneNumberForUpdate+" But actual on UI is "+nscore4HomePage.getLastFourDgitOfPhoneNumber());
		s_assert.assertAll();
	}	


	//QTest ID TC-699 CORP - Edit Site Content for Product Category Page (Redefine)
	@Test(enabled=false)//should be a manual test
	public void testCORPEditSiteContentForProductCategoryPage_Redefine_699q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String parentWindowHandle = null;
		String sites = "Sites";
		String clearNavigationCache = "Clear Navigation Cache";
		String originalText = null;
		String text = "Auto"+randomNum;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		StoreFrontHeirloomHomePage storeFrontHeirloomHomePage = new StoreFrontHeirloomHomePage(driver);
		//Edit Corp site content for product category
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate("Load Site in Edit Mode");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextForCategoryOnCorp(text);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToStoreFrontURL();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(text), "text is not present on Live site");

		//Revert corp site content for product category.
		nscore4HomePage.navigateToBaseURL();
		login("admin", "skin123!");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate("Load Site in Edit Mode");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextForCategoryOnCorp(originalText);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToStoreFrontURL();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(originalText), "text is not present on Live site");
		s_assert.assertAll();
	}	


	// QTest ID TC-700 PWS.biz - Edit Site Content for Product Category Page (Redefine)
	@Test(enabled=false)//should be a manual test
	public void testPWSBizEditSiteContentForProductCategoryPage_Redefine_700q() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String parentWindowHandle = null;
		String sites = "Sites";
		String clearNavigationCache = "Clear Navigation Cache";
		String text = "Auto" + randomNum;
		String originalText = null;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		StoreFrontHeirloomHomePage storeFrontHeirloomHomePage = new StoreFrontHeirloomHomePage(driver);
		//Edit Biz site content for product category Redefine
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".biz");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(),"Biz site does not came up in edit mode.");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextForCategoryOnPWS(text);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		s_assert.assertTrue(nscore4SitesTabPage.getTextFromBIZSite().contains(text), "Expected text is "+text+"Actual on UI is "+nscore4SitesTabPage.getTextFromBIZSite());
		//Revert Biz site content for product category Redefine.
		nscore4HomePage.navigateToBaseURL();
		login("admin", "skin123!");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".biz");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(),"Biz site does not came up in edit mode.");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		nscore4SitesTabPage.getOriginalAndEnterNewTextForCategoryOnPWS(originalText);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		s_assert.assertTrue(nscore4SitesTabPage.getTextFromBIZSite().contains(originalText), "Expected text is "+text+"Actual on UI is "+nscore4SitesTabPage.getTextFromBIZSite());
		s_assert.assertAll();
	}

	//QTest ID TC-687 NSC4 - Edit PWS.com Site Content
	@Test(enabled=false)//should be a manual test
	public void testEditPWSComSiteContent_687q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String parentWindowHandle = null;
		String sites = "Sites";
		String clearNavigationCache = "Clear Navigation Cache";
		String text = "Auto"+randomNum;
		String url = null;
		String originalText=null;
		//Edit com site content.
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".com");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		url = nscore4HomePage.getCurrentURL();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextOnPWS(text);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToURL(url);
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(text), "text is not present on com site");
		//Revert com site content.
		nscore4HomePage.navigateToBaseURL();
		login("admin", "skin123!");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".com");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		url = nscore4HomePage.getCurrentURL();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		nscore4SitesTabPage.getOriginalAndEnterNewTextOnPWS(originalText);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToURL(url);
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(originalText), "text is not present on com site");
		s_assert.assertAll();
	}

	//QTest ID TC-688 NSC4 - Edit PWS.biz Site Content
	@Test(enabled=false)//should be a manual test
	public void testEditPWSBizSiteContent_688q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String parentWindowHandle = null;
		String sites = "Sites";
		String clearNavigationCache = "Clear Navigation Cache";
		String text = "Auto"+randomNum;
		String url = null;
		String originalText = null;
		//Edit Biz site content.
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".biz");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		url = nscore4HomePage.getCurrentURL();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		//nscore4SitesTabPage.enterTextForPWS(text);
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextOnPWS(text);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		openBIZSite();
		s_assert.assertTrue(nscore4SitesTabPage.getTextFromBIZSite().contains(text), "Expected text is "+text+"Actual on UI is "+nscore4SitesTabPage.getTextFromBIZSite());
		//Revert Biz site content.
		nscore4HomePage.navigateToBaseURL();
		login("admin", "skin123!");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".biz");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		url = nscore4HomePage.getCurrentURL();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		nscore4SitesTabPage.getOriginalAndEnterNewTextOnPWS(originalText);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		openBIZSite();
		s_assert.assertTrue(nscore4SitesTabPage.getTextFromBIZSite().contains(originalText), "Expected text is "+originalText+"Actual on UI is "+nscore4SitesTabPage.getTextFromBIZSite());
		//Revert Biz site content.
		s_assert.assertAll();
	}

	// QTest ID TC-701 PWS.COM - Edit Site Content for Product Category Page (Redefine)
	@Test(enabled=false)//should be a manual test
	public void testPWSCOMEditSiteContentForProductCategoryPage_Redefine_701q() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String parentWindowHandle = null;
		String sites = "Sites";
		String clearNavigationCache = "Clear Navigation Cache";
		String text = "Auto" + randomNum;
		String originalText = null;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		StoreFrontHeirloomHomePage storeFrontHeirloomHomePage = new StoreFrontHeirloomHomePage(driver);
		//Edit com site content for product category Redefine
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".com");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(),"COM site does not came up in edit mode.");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextForCategoryOnPWS(text);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		s_assert.assertTrue(nscore4SitesTabPage.getTextFromBIZSite().contains(text), "Expected text is "+text+"Actual on UI is "+nscore4SitesTabPage.getTextFromBIZSite());
		//Revert com site content for product category Redefine.
		nscore4HomePage.navigateToBaseURL();
		login("admin", "skin123!");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".com");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(),"COM site does not came up in edit mode.");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		nscore4SitesTabPage.getOriginalAndEnterNewTextForCategoryOnPWS(originalText);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		s_assert.assertTrue(nscore4SitesTabPage.getTextFromBIZSite().contains(originalText), "Expected text is "+text+"Actual on UI is "+nscore4SitesTabPage.getTextFromBIZSite());
		s_assert.assertAll();
	}

	//QTest ID TC-2463 NScore4> Create Adhoc order for the Consultant
	@Test()
	public void testNScore4CreateAdhocOrderForTheConsultant_2463q(){
		String accountNumber = null;
		String SKU = null;
		nscore4OrdersTabPage =new NSCore4OrdersTabPage(driver);
		logger.info("DB is "+RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusAfterSubmitOrder(),"Order is not submitted after submit order");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(),"This is not order details page");
		s_assert.assertAll();
	}


	// QTest ID TC-2464 NScore4> Create Adhoc order for the RC
	@Test()
	public void testNScore4CreateAdhocOrderForTheRC_2464q() {
		String accountNumber = null;
		String emailID = null;
		String SKU = null;
		logger.info("DB is " + RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomRCAccountList, "AccountNumber");
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		//nscore4HomePage.clickPlaceNewOrderLink();
		// click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU = nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU),"SKU = " + SKU + " is not added to the Autoship Order");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusAfterSubmitOrder(),"Order is not submitted after submit order");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(), "This is not order details page");
		s_assert.assertAll();
	}

	// QTest ID TC-2465 NScore4> Create Adhoc order for the PC
	@Test()
	public void testNScore4CreateAdhocOrderForThePC_2465q() {
		String accountNumber = null;
		String emailID = null;
		String SKU = null;
		logger.info("DB is " + RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomPCAccountList, "AccountNumber");
		emailID = (String) getValueFromQueryResult(randomPCAccountList, "EmailAddress");
		logger.info("Account number from DB is " + accountNumber);

		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		// click 'Place-New-Order' link
		//nscore4HomePage.clickPlaceNewOrderLink();
		SKU = nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU),"SKU = " + SKU + " is not added to the Autoship Order");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Products"),"Product information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Shipments"),"Shipments information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderInformationPresent("Payment"),"Payment information not present as expected");
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatusAfterSubmitOrder(),"Order is not submitted after submit order");
		s_assert.assertTrue(nscore4OrdersTabPage.isOrderDetailPagePresent(), "This is not order details page");
		s_assert.assertAll();
	}	

	//QTest ID TC-940 NSC4 - MAIN-5200, Recalculate subtotal after change item price
	@Test
	public void testMAIN5200RecalculateSubtotalAfterChangeItemPrice_940(){
		String accountNumber = null;
		String price=null;
		String QV=null;
		String CV=null;
		String taxBeforeUpdate=null;
		String taxAfterUpdateToZero=null;
		String taxAfterUpdate=null;
		String priceValue=null;
		String productCategory=TestConstantsRFL.REGIMEN_NAME_REDEFINE;

		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is "+accountNumber);

		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();	
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		nscore4HomePage.clickSearchSKU(productCategory);
		nscore4HomePage.clickAddToOrder();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		priceValue=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage().split("\\$")[1];
		System.out.println("price value is:"+ priceValue);
		taxBeforeUpdate=nscore4OrdersTabPage.getTaxFromOverrideOrderWindow();
		nscore4OrdersTabPage.changePriceCVAndQVValuesToZero(price,CV,QV);
		nscore4OrdersTabPage.clickRecalculateTax();
		taxAfterUpdateToZero=nscore4OrdersTabPage.getTaxFromOverrideOrderWindow();
		s_assert.assertTrue(taxAfterUpdateToZero.contains(taxBeforeUpdate),"Tax is not updated after updating shipping charges to zero");
		nscore4OrdersTabPage.changePriceCVAndQVValues(priceValue);
		nscore4OrdersTabPage.clickRecalculateTax();
		taxAfterUpdate=nscore4OrdersTabPage.getTaxFromOverrideOrderWindow();
		s_assert.assertTrue(taxAfterUpdate.contains(taxAfterUpdateToZero),"Tax is not updated after updating shipping charges to zero");
		s_assert.assertAll();
	}

	//NSC4_AccountsTab_OrdersEditCancelOrder
	@Test(priority=7)
	public void testAccountsTab_OrdersEditCancelOrder(){
		String accountNumber = null;
		List<Map<String, Object>> randomAccountNumberList =  null;
		logger.info("DB is "+RFL_DB);
		while(true){
			//			randomAccountNumberList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
			randomAccountNumberList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_USERS_WITH_PENDING_ORDERS_RFL,RFL_DB);
			accountNumber = (String) getValueFromQueryResult(randomAccountNumberList, "CID"); 
			logger.info("CID from DB is "+accountNumber);
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(accountNumber);
			nscore4HomePage.enterOrderStatus("Pending");
			if(nscore4HomePage.isNoOrderFoundMessagePresent()){
				nscore4HomePage.clickTab("Accounts");
				continue;
			}else if(nscore4HomePage.isOrderWithPendingStatusPresent()==false){
				nscore4HomePage.clickTab("Accounts");
				continue;
			}
			else
				break;
		}
		nscore4OrdersTabPage = nscore4HomePage.clickRandomPendingOrderID();
		nscore4OrdersTabPage.clickCancelOrderBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.validateOrderStatus(),"order is not cancelled");
		s_assert.assertAll();
	}

	//QTest ID TC-285 NSC4_AccountsTab_OrdersEdit
	@Test()
	public void testNsAccountTabOrderEdit_285(){
		String SKU = null;
		String accountNumber = null;
		String pendingOrder = null;
		String orderStatus = null;
		List<Map<String, Object>> randomAccountNumberList =  null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		//Search for valid consultant
		for(int i=1;i<=5;i++){
			randomAccountNumberList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_USERS_WITH_PENDING_ORDERS_RFL,RFL_DB);
			accountNumber = (String) getValueFromQueryResult(randomAccountNumberList, "CID"); 
			logger.info("CID from DB is "+accountNumber);
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(accountNumber);
			nscore4HomePage.enterOrderStatus("Pending");
			if(nscore4HomePage.isNoOrderFoundMessagePresent()){
				nscore4HomePage.clickTab("Accounts");
				continue;
			}else if(nscore4HomePage.isOrderWithPendingStatusPresent()==false){
				nscore4HomePage.clickTab("Accounts");
				continue;
			}
			else
				break;
		}
		pendingOrder = nscore4HomePage.clickAndReturnPendingOrderNumberFromOverviewPage();
		nscore4HomePage.clickEditOrder();
		//Add a product and sku.
		SKU= nscore4HomePage.addAndGetProductSKU("2");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		orderStatus = nscore4OrdersTabPage.getOrderStatusFromOrderDetailsPage();
		s_assert.assertTrue(orderStatus.contains("Submitted"), "Order status from order details expected = Submitted And actual on UI "+orderStatus);
		nscore4OrdersTabPage.clickPaymentLink();
		s_assert.assertTrue(nscore4OrdersTabPage.isPaymentDetailPopupPresent(), "Payment detail popup is not present after clicking payment details");
		nscore4OrdersTabPage.clickCloseOfPaymentDetailPopup();
		nscore4OrdersTabPage.clickReturnOrderLink();
		nscore4OrdersTabPage.enableReturnedChkBox(SKU);
		String returnQty = nscore4OrdersTabPage.getReturnQuantityBasedOnSKU(SKU);
		nscore4OrdersTabPage.enterReturnQuantity(SKU,returnQty);
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.selectReturnReason();
		nscore4OrdersTabPage.selectReturnType();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.isProductSKUPresentOnOrderDetailPage(SKU),"Return Product SKU not present on return order page.");
		s_assert.assertAll();
	}

	//QTest ID TC-689 NSC4 - Controlled Blocks for Editorial Pages
	@Test(enabled=false)//should be a manual test
	public void testNSC4ControlledBlocksForEditorialPages_689q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String parentWindowHandle = null;
		String sites = "Sites";
		String clearNavigationCache = "Clear Navigation Cache";
		String text = "Auto"+randomNum;
		String originalText = null;
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.hoverOnHeaderLinkAndClickSubLink("nsCorporate", "Corporate");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.clickLoadSiteInEditModeLink();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextOnCorp(text);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToStoreFrontURL();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(text), "text is not present on site");
		//Revert the changes on corp.
		nscore4HomePage.navigateToBaseURL();
		login("admin", "skin123!");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.hoverOnHeaderLinkAndClickSubLink("nsCorporate", "Corporate");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.clickLoadSiteInEditModeLink();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.clickEditLinkUnderManage();
		nscore4SitesTabPage.getOriginalAndEnterNewTextOnCorp(originalText);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToStoreFrontURL();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(originalText), "text is present on site");
		s_assert.assertAll();
	}

	//QTest ID TC-686 NSC4 - Edit Corporate Site Content
	@Test(enabled=false)
	public void testEditCorporateSiteContent_686q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String parentWindowHandle = null;
		String sites = "Sites";
		String clearNavigationCache = "Clear Navigation Cache";
		String text = "Auto"+randomNum;
		String originalText = null;
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.hoverOnHeaderLinkAndClickSubLink("nsCorporate", "Corporate");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.clickLoadSiteInEditModeLink();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextOnCorp(text);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToStoreFrontURL();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(text), "text is not present on site");
		//Revert Corp site content.
		nscore4HomePage.navigateToBaseURL();
		login("admin", "skin123!");
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.hoverOnHeaderLinkAndClickSubLink("nsCorporate", "Corporate");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.clickLoadSiteInEditModeLink();
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.clickEditLinkUnderManage();
		nscore4SitesTabPage.getOriginalAndEnterNewTextOnCorp(originalText);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab(sites);
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToStoreFrontURL();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(originalText), "text is present on site");
		s_assert.assertAll();
	}

	//QTest ID TC-274 NSC4_AccountsTab_OverviewAutoshipsViewOrders
	@Test
	public void testNSC4AccountTabOverviewAutoshipsViewOrders_274(){
		String accountNumber = null;
		String accounts = "Accounts";
		logger.info("DB is "+RFL_DB);
		String year = nscore4HomePage.getCurrentDateAndMonthAndYearAndMonthShortNameFromPstDate(nscore4HomePage.getPSTDate())[2];
		String currentYear = "%"+year+"%";
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_CONSULTANT_ACCOUNT_NUMBER_HAVING_AUTOSHIP_ORDER_WITH_CURRENT_YEAR_RFL, currentYear),RFL_DB);
		accountNumber =(String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4HomePage.clickViewOrderLinkUnderConsultantReplenishment();
		nscore4HomePage.clickCalenderStartDateForFilter();
		nscore4HomePage.selectMonthOnCalenderForNewEvent("Jan");
		nscore4HomePage.selectYearOnCalenderForNewEvent("2018");
		nscore4HomePage.clickSpecficDateOfCalendar("1");
		int totalSearchResults = nscore4HomePage.getCountOfSearchResults();
		String[] allCompleteDate = nscore4HomePage.getAllCompleteDate(totalSearchResults);
		s_assert.assertTrue(nscore4HomePage.isAllCompleteDateContainCurrentYear(allCompleteDate), "1. All complete date on UI are not in the range of filter for consultant autoship");;
		nscore4HomePage.navigateToBackPage();
		nscore4HomePage.clickTab(accounts);
		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_CONSULTANT_ACCOUNT_NUMBER_HAVING_PULSE_AUTOSHIP_ORDER_WITH_CURRENT_YEAR_RFL, currentYear, currentYear),RFL_DB);
		accountNumber =(String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);
		nscore4HomePage.clickViewOrderLinkUnderPulseMonthlySubscription();
		nscore4HomePage.clickCalenderStartDateForFilter();
		nscore4HomePage.selectMonthOnCalenderForNewEvent("Jan");
		nscore4HomePage.selectYearOnCalenderForNewEvent("2018");
		nscore4HomePage.clickSpecficDateOfCalendar("1");
		totalSearchResults = nscore4HomePage.getCountOfSearchResults();
		int randomSearchResult = CommonUtils.getRandomNum(1, totalSearchResults);
		allCompleteDate = nscore4HomePage.getAllCompleteDate(totalSearchResults);
		s_assert.assertTrue(nscore4HomePage.isAllCompleteDateContainCurrentYear(allCompleteDate), "2. All complete date on UI are not in the range of filter for consultant pulse autoship");;
		nscore4HomePage.clickAndReturnRandomOrderNumber(randomSearchResult);
		s_assert.assertTrue(nscore4HomePage.getOrderNumberFromOrderDetails().contains(accountNumber), "Expected OrderNumber on order details page is: "+accountNumber+" Actual on UI is: "+nscore4HomePage.getOrderNumberFromOrderDetails());
		s_assert.assertAll();
	}

	//QTest ID TC-265 NSC4_SitesTab_nsDistributor_BasePWSLoadSiteEditMode.Biz
	@Test(enabled=false)//should be a manual test
	public void testNSC4_SitesTab_nsDistributor_BasePWSLoadSiteEditModeBiz_265q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String storyTitle = "This is story title: "+randomNum;
		String story = "This is new Automation story: "+randomNum;
		String parentWindow=null;

		parentWindow = driver.getWindowHandle();
		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		//Perform changes to site in edit mode.
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfDistributorOnSitePage(".biz");
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), ".Biz site does not came up in edit mode.");

		nscore4HomePage.clickHeaderLinkAfterLogin("edit my pws");
		nscore4HomePage.clickEditMyStoryLink();
		nscore4HomePage.clickIWantToWriteMyOwnStory();
		nscore4HomePage.typeSomeStoryAndclickSaveStory(storyTitle,story);
		s_assert.assertTrue(nscore4HomePage.verifyWaitingForApprovalLinkForNewStory(storyTitle),"Waiting for approval link is not present for newly created story");
		nscore4SitesTabPage.clickSwitchToLiveMode();
		s_assert.assertTrue(nscore4HomePage.verifyWaitingForApprovalLinkForNewStory(storyTitle),"Waiting for approval link is not present for newly created story");
		nscore4HomePage.closeChildAndSwitchToParentWindow(parentWindow);
		s_assert.assertAll();
	}

	//QTest ID TC-252 NSC4_SitesTab_nsCorporate_CorporateLoadSiteEditMode
	@Test(enabled=false)//Should be a manual test
	public void testNsCorporateCorporateSiteEditMode_252q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String parentWindowHandle = null;
		String text = "Auto"+randomNum;
		String originalText=null;
		String clearNavigationCache = "Clear Navigation Cache";

		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		//Perform changes to site in edit mode.
		nscore4HomePage.clickTab("Sites");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.clickSubLinkOfNSCorporate("Load Site in Edit Mode");
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextOnCorp(text);
		System.out.println("***** "+originalText);
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		//nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab("sites");
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4SitesTabPage.navigateToStoreFrontURL();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(text), "text is not present on Live site");
		//		//Revert the changes on corp site.
		//		nscore4HomePage.navigateToBaseURL();
		//		login("admin", "skin123!");
		//		nscore4HomePage.clickTab("Sites");
		//		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		//		nscore4SitesTabPage.clickSubLinkOfNSCorporate("Load Site in Edit Mode");
		//		nscore4SitesTabPage.switchToSecondWindow();
		//		nscore4SitesTabPage.hoverManageOnCorporateSite();
		//		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		//		nscore4SitesTabPage.hoverManageOnCorporateSite();
		//		nscore4SitesTabPage.clickEditLinkUnderManage();
		//		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextOnCorp(originalText);
		//		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		//		nscore4SitesTabPage.clickSwitchToLiveMode();
		//		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		//		nscore4HomePage.clickTab("sites");
		//		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		//		nscore4SitesTabPage.clearNavigationCacheAll();
		//		nscore4SitesTabPage.navigateToStoreFrontURL();
		//		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(originalText), "text is not present on Live site after revert");
		//		//Revert the changes on corp site.
		s_assert.assertAll();
	}

	//QTest ID TC-264 NSC4_SitesTab_nsDistributor_BasePWSLoadSiteEditMode.Com
	@Test(enabled=false)//should be a manual test
	public void testNSC4_SitesTab_nsDistributor_BasePWSLoadSiteEditModeCom_264q(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String text = "Auto"+randomNum;
		String originalText=null;
		String parentWindowHandle=null;
		String clearNavigationCache = "Clear Navigation Cache";

		s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
		//Perform changes to site in edit mode.
		nscore4HomePage.clickTab("Sites");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.clickSubLinkOfNSCorporate("Load Site in Edit Mode");
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		originalText=nscore4SitesTabPage.getOriginalAndEnterNewTextOnCorp(text);
		nscore4SitesTabPage.clickPreviewButtonAfterEditingText();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(text), "text is not present on preview site");
		nscore4SitesTabPage.clickSwitchBackToEditModeLink();
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToStoreFrontURL();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(text), "text is not present on Live site");
		//Revert corp site content.
		nscore4HomePage.navigateToBaseURL();
		login("admin", "skin123!");
		nscore4HomePage.clickTab("Sites");
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		nscore4SitesTabPage.clickSubLinkOfNSCorporate("Load Site in Edit Mode");
		nscore4SitesTabPage.switchToSecondWindow();
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		s_assert.assertTrue(nscore4SitesTabPage.isCorporateSiteIsInEditMode(), "Corporate site does not came up in edit mode.");
		nscore4SitesTabPage.hoverManageOnCorporateSite();
		nscore4SitesTabPage.clickEditLinkUnderManage();
		nscore4SitesTabPage.getOriginalAndEnterNewTextOnCorp(originalText);
		nscore4SitesTabPage.clickPreviewButtonAfterEditingText();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(originalText), "text is not present on preview site");
		nscore4SitesTabPage.clickSwitchBackToEditModeLink();
		nscore4SitesTabPage.clickSaveButtonAfterEnteringEditText();
		nscore4SitesTabPage.clickSwitchToLiveMode();
		nscore4SitesTabPage.switchToParentWindow(parentWindowHandle);
		nscore4HomePage.clickTab("Sites");
		nscore4SitesTabPage.clickSubLinkOfNSCorporate(clearNavigationCache);
		nscore4SitesTabPage.clearNavigationCacheAll();
		nscore4HomePage.navigateToStoreFrontURL();
		s_assert.assertTrue(nscore4SitesTabPage.isLinkPresent(originalText), "text is not present on Live site after reverting");
		s_assert.assertAll();
	}	

	//NSC4 Full Return 
	@Test(priority=27)
	public void testNSC4FullReturn(){
		String accountNumber = null;
		String accounts = "Accounts";
		List<Map<String, Object>> randomSKUList =  null;
		String SKU = null;
		String placeNewOrder = "Place New Order";
		logger.info("DB is "+RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");	
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);		
		nscore4HomePage.clickSublinkOfOverview(placeNewOrder);
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		nscore4HomePage.clickApplyPaymentButton();
		nscore4HomePage.clickSubmitOrderBtn();
		String orderID = nscore4HomePage.getOrderID().split("\\#")[1];
		nscore4HomePage.clickTab(accounts);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);		
		nscore4HomePage.clickOrderId(orderID);
		nscore4OrdersTabPage.clickReturnOrderLink();
		nscore4OrdersTabPage.enableReturnedChkBox();
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.selectReturnType();
		nscore4OrdersTabPage.selectReturnReason();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.getOrderType().contains("Return"), "Order type does not contain return");
		s_assert.assertAll();
	}	

	//NSC4 Partial  Return 
	@Test(priority=28)
	public void testNSC4PartialReturn(){
		String accountNumber = null;
		String emailID = null;
		String accounts = "Accounts";
		String quantity = "5";
		String partialQuantity = "2";
		List<Map<String, Object>> randomSKUList =  null;
		String SKU = null;
		String placeNewOrder = "Place New Order";
		logger.info("DB is "+RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");	
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);		
		nscore4HomePage.clickPlaceNewOrderLink();

		randomSKUList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_SKU,RFL_DB);
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		nscore4HomePage.clickApplyPaymentButton();
		nscore4HomePage.clickSubmitOrderBtn();
		String orderID = nscore4HomePage.getOrderID().split("\\#")[1];
		nscore4HomePage.clickTab(accounts);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);		
		nscore4HomePage.clickOrderId(orderID);
		nscore4OrdersTabPage.clickReturnOrderLink();
		nscore4OrdersTabPage.enableReturnedChkBox();
		nscore4OrdersTabPage.enterReturnQuantity(partialQuantity);
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.selectReturnType();
		nscore4OrdersTabPage.selectReturnReason();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.getOrderType().contains("Return"), "Order type does not contain return");
		s_assert.assertTrue(nscore4OrdersTabPage.getReturnQuantityOfProduct().contains(partialQuantity), "Expected partial quantity is: "+partialQuantity+"Actual on UI is: "+nscore4OrdersTabPage.getReturnQuantityOfProduct());
		s_assert.assertAll();
	}

	// QTest ID TC-922 NSC4 - Returns (VT Returns - Returns)
	@Test(enabled=true)
	public void testNSC4ReturnsVTReturnsReturns_922() {
		String orderNumber = null;
		String grandTotal = null;
		String accountNumber = null;
		String skuFromDB = null;
		Double vtrefundtotal1 = 0.0;
		Double sumOfRefundTotal = 0.0;
		Double orderTotal = 0.0;
		Double vtrefundtotal2 = 0.0;
		Double sumOfAllRefundTotal = 0.0;
		Double refundtotal = 0.0;
		Double sumOfVTRefundTotal = 0.0;
		List<Map<String, Object>> orderDetailsList = null;
		List<Map<String, Object>> randomSKUList =  null;
		String categoryRedefine=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		//		String categoryReverse=TestConstantsRFL.REGIMEN_NAME_REVERSE;
		//		String categorySoothe=TestConstantsRFL.REGIMEN_NAME_SOOTHE;

		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),	RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is " + accountNumber);

		// nscore4HomePage.clickAccountsTab();
		nscore4HomePage.clickSearchConsultant(accountNumber);
		nscore4HomePage.clickSearchIcon();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		nscore4HomePage.clickSearchSKU(categoryRedefine);
		nscore4HomePage.clickAddToOrder();
		//		nscore4HomePage.clickSearchSKU(categoryReverse);
		//		nscore4HomePage.clickAddToOrder();
		//		nscore4HomePage.clickSearchSKU(categorySoothe);
		//		nscore4HomePage.clickAddToOrder();	
		nscore4OrdersTabPage.clickApplyPaymentButton();
		nscore4OrdersTabPage.clickSubmitOrderBtn();

		int rowcount = nscore4HomePage.getCountOfSearchResultRows();
		for (int i = 1; i <= rowcount; i++) {
			nscore4OrdersTabPage.clickVTReturnOrder();
			nscore4HomePage.selectItemAndClickUpdateUnderRefundSection(i);
			// nscore4HomePage.clearShippingAndHandlingRefundedAmountsAndUpdate();
			nscore4OrdersTabPage.clickUpdateLink();
			nscore4OrdersTabPage.selectReturnType();
			nscore4OrdersTabPage.selectReturnReason();
			nscore4OrdersTabPage.clickSubmitReturnBtn();
			vtrefundtotal1 = Double.parseDouble(nscore4OrdersTabPage.getRefundTotal());
			sumOfVTRefundTotal = sumOfVTRefundTotal + vtrefundtotal1;
			nscore4OrdersTabPage.goToOriginalOrder();
		}
		nscore4OrdersTabPage.clickVTReturnOrder();
		String shippingPrice = nscore4OrdersTabPage.getPriceDetailsByLabel("Shipping Available:");
		nscore4OrdersTabPage.enterRefundedShipping(shippingPrice);
		nscore4OrdersTabPage.selectCheckboxForReturnTax();
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.selectReturnType();
		nscore4OrdersTabPage.selectReturnReason();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		vtrefundtotal2 = Double.parseDouble(nscore4OrdersTabPage.getRefundTotal());
		orderNumber = nscore4OrdersTabPage.getorderNumber();
		nscore4OrdersTabPage.goToOriginalOrder();
		for (int i = 1; i <= rowcount; i++) {
			nscore4OrdersTabPage.clickReturnOrderOrderDetailsPage();
			nscore4HomePage.selectItemAndClickUpdateUnderRefundSection(i);
			// nscore4HomePage.clearShippingAndHandlingRefundedAmountsAndUpdate();
			nscore4OrdersTabPage.clickUpdateLink();
			nscore4OrdersTabPage.selectReturnType();
			nscore4OrdersTabPage.selectReturnReason();
			nscore4OrdersTabPage.clickSubmitReturnBtn();
			refundtotal = Double.parseDouble(nscore4OrdersTabPage.getRefundTotal());
			sumOfRefundTotal = sumOfRefundTotal + refundtotal;
			nscore4OrdersTabPage.goToOriginalOrder();
		}
		sumOfAllRefundTotal = sumOfRefundTotal + vtrefundtotal2 + sumOfVTRefundTotal;
		s_assert.assertTrue(nscore4OrdersTabPage.isVTReturnLinksDisabled().contains("void"),"Return and VT return order are clickable");
		s_assert.assertTrue(nscore4OrdersTabPage.isReturnLinksDisabled().contains("void"),"Return and VT return order are clickable");
		orderTotal = Double.parseDouble(nscore4OrdersTabPage.getOriginalTotalOrderAmount());
		//		s_assert.assertEquals(orderTotal, sumOfAllRefundTotal,"Expected orderTotal is:"+orderTotal+" But Actual on UI is: "+sumOfAllRefundTotal);
		orderDetailsList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_ORDER_DETAILS, orderNumber), RFL_DB);
		grandTotal = String.valueOf(getValueFromQueryResult(orderDetailsList, "GrandTotal"));
		double grandTotalD = Double.parseDouble(grandTotal);
		s_assert.assertEquals(grandTotalD, orderTotal,"Order total from DB is:" + grandTotal + " different from UI is:" + orderTotal);
		s_assert.assertAll();
	}

	//Return with Restocking fee
	@Test(enabled=false)//no such test on qTest
	public void testNSC4ReturnWithRestockingFee(){
		String accountNumber = null;
		String accounts = "Accounts";
		String SKU = null;
		String restockingFee = "10.00";
		String placeNewOrder = "Place New Order";
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = "RFAutoNSCore4"+randomNum;
		String lastName = "lN";
		String nameOnCard = "rfTestUser";
		String cardNumber =  "4747474747474747";
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String attentionName = "RFAutoNSCore4"+randomNum;
		logger.info("DB is "+RFL_DB);
		accountNumber =(String) getValueFromQueryResult(randomConsultantAccountList	, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);  
		nscore4HomePage.clickSublinkOfOverview(placeNewOrder);
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		nscore4HomePage.addPaymentMethod(newBillingProfileName, lastName, nameOnCard, cardNumber,CVV,attentionName);
		nscore4HomePage.clickSavePaymentMethodBtn();
		nscore4HomePage.clickApplyPaymentButton();
		nscore4HomePage.clickSubmitOrderBtn();
		String orderID = nscore4HomePage.getOrderID().split("\\#")[1];
		nscore4HomePage.clickTab(accounts);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber);  
		nscore4HomePage.clickOrderId(orderID);
		nscore4OrdersTabPage.clickReturnOrderLink();
		nscore4OrdersTabPage.enableReturnedChkBox();
		nscore4OrdersTabPage.enterRestockingFeeInPercent(restockingFee);
		nscore4OrdersTabPage.clickRestockingAmount();
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.selectReturnType();
		nscore4OrdersTabPage.selectReturnReason();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		s_assert.assertTrue(nscore4OrdersTabPage.getOrderType().contains("Return"), "Order type does not contain return");
		s_assert.assertTrue(nscore4OrdersTabPage.isRestockingFeeTxtPresent(), "Restocking fee is not present after return order with restocking fee");
		s_assert.assertAll();
	}	

	//QTest ID TC-1942 Payment error message - NSC4
	@Test()
	public void testPaymentErrorMessageNSC4_1942(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = "RFAutoNSCore4"+randomNum;
		String lastName = "lN";
		String nameOnCard = "rfAutoUser";
		String attentionCO =newBillingProfileName+" "+lastName;
		String CVV = TestConstantsRFL.SECURITY_CODE;
		String accountNumber = null;
		String SKU = null;
		String emailID=null;
		nscore4OrdersTabPage =new NSCore4OrdersTabPage(driver);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		emailID = (String) getValueFromQueryResult(randomConsultantAccountList, "UserName");	
		nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		SKU= nscore4HomePage.addAndGetProductSKU("10");
		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		//nscore4HomePage.addANewBillingProfile(newBillingProfileName, lastName, nameOnCard, TestConstantsRFL.INVALID_CARD_NUMBER, addressLine1, zipCode);
		nscore4HomePage.addPaymentMethod(newBillingProfileName, lastName, nameOnCard, TestConstantsRFL.INVALID_CARD_NUMBER, CVV, attentionCO);
		nscore4HomePage.clickSavePaymentMethodBtn();
		s_assert.assertTrue(nscore4HomePage.isErrorMessagePresentOnUI(),"This transaction has been declined due to an invalid account number. is not present on UI");
		s_assert.assertAll();
	}	

	//--

	//QTest ID TC-272 NSC4_AccountsTab_OverviewPostNewNote
	@Test(priority=14)
	public void testNSC4AccountsTabOverviewPostNewNote_272(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String accountNumber = null;
		String categoryOfNotePopup = "1b";
		String categoryOfChildNote = "1k";
		String typeOfNotePopup="A";
		String typeOfChildNote="E";
		String noteTxt = "AutomationNote"+randomNum;
		String noteTxtOfChildNote = "AutomationChildNote"+randomNumber;

		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		logger.info("Account number from DB is "+accountNumber);
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch(accountNumber); 
		nscore4HomePage.clickPostNewNodeLinkInOverviewTab();
		nscore4HomePage.selectAndEnterAddANoteDetailsInPopup(categoryOfNotePopup,typeOfNotePopup,noteTxt);
		nscore4HomePage.clickSaveBtnOnAddANotePopup();
		s_assert.assertTrue(nscore4HomePage.isNewlyCreatedNotePresent(noteTxt), "Newly created note"+noteTxt+" is not present ON UI");
		nscore4HomePage.clickPostFollowUpLinkForParentNote(noteTxt);
		nscore4HomePage.selectAndEnterAddANoteDetailsInPopup(categoryOfChildNote,typeOfChildNote,noteTxtOfChildNote);
		nscore4HomePage.clickSaveBtnOnAddANotePopup();
		s_assert.assertTrue(nscore4HomePage.isNewlyCreatedChildNotePresent(noteTxt,noteTxtOfChildNote), "Newly created Child note"+noteTxtOfChildNote+" is not present ON UI under parent note"+noteTxt);
		s_assert.assertTrue(nscore4HomePage.isPostFollowUpLinkPresentForChildNote(noteTxt,noteTxtOfChildNote), "Newly created Child note"+noteTxtOfChildNote+" post follow up link is not present ON UI under parent note"+noteTxt);
		nscore4HomePage.clickCollapseLinkNearToParentNote(noteTxt);
		s_assert.assertFalse(nscore4HomePage.isChildNoteDetailsAppearsOnUI(noteTxt), "Newly created Child note"+noteTxtOfChildNote+" details are present ON UI under parent note"+noteTxt);
		nscore4HomePage.clickExpandLinkNearToParentNote(noteTxt);
		s_assert.assertTrue(nscore4HomePage.isChildNoteDetailsAppearsOnUI(noteTxt), "Newly created Child note"+noteTxtOfChildNote+" details are not present ON UI under parent note"+noteTxt);
		s_assert.assertAll();
	}

	//QTest ID TC-943 MAIN-5200, Return and override order
	@Test
	public void testMAIN5200ReturnAndOverrideOrder_943(){
		String accountNumber = null;
		String priceValue="10";
		String taxTotalFromOrderCreationBeforeUpdate = null;
		String taxTotalFromOrderCreationAfterUpdate = null;
		String orderTotalFromOrderCreationBeforeRecalculateTAx = null;
		String orderTotalFromOrderCreationAfterRecalculateTAX=null;
		String subtotalFromOrderCreationBeforeRecalculateTAx = null;
		String subtotalFromOrderCreationAfterRecalculateTAX=null;
		String taxTotalFromOrderCreationAfterReturn=null;
		String taxAfterReturn="$0";

		randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, accountNumber),RFL_DB);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber");
		logger.info("Account number from DB is "+accountNumber);

		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		nscore4HomePage.clickPlaceNewOrderLink();
		nscore4HomePage.addAndGetSpecficProductSKU("10");
		taxTotalFromOrderCreationBeforeUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationBeforeRecalculateTAx=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterPriceInOverrideOrderWindow(priceValue,"AARG001");
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		taxTotalFromOrderCreationAfterUpdate=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		orderTotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getOrderTotalFromOrderCreationPage();
		subtotalFromOrderCreationAfterRecalculateTAX=nscore4OrdersTabPage.getSubtotalFromOrderCreationPage();
		//Assert the updated values tax,orderTotal and subTotal values
		s_assert.assertFalse(orderTotalFromOrderCreationBeforeRecalculateTAx.contains(orderTotalFromOrderCreationAfterRecalculateTAX),"Order Total is not changed after Re-calculating");
		s_assert.assertFalse(subtotalFromOrderCreationAfterRecalculateTAX.contains(subtotalFromOrderCreationBeforeRecalculateTAx),"Sub Total is not changed after Re-calculating");
		s_assert.assertFalse(taxTotalFromOrderCreationAfterUpdate.contains(taxTotalFromOrderCreationBeforeUpdate),"Tax is not changed after Re-calculating");

		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickSubmitOrderBtn();
		//VT return
		nscore4OrdersTabPage.clickVTReturnOrder();
		nscore4OrdersTabPage.selectItemAndClickUpdateUnderRefundSection(1);
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.selectReturnReason();
		nscore4OrdersTabPage.selectReturnType();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		nscore4OrdersTabPage.goToOriginalOrder();
		//return
		nscore4OrdersTabPage.clickReturnOrderOrderDetailsPage();
		taxTotalFromOrderCreationAfterReturn=nscore4OrdersTabPage.getTaxFromOrderCreationPage();
		nscore4OrdersTabPage.selectItemAndClickUpdateUnderRefundSection(1);
		s_assert.assertTrue(taxTotalFromOrderCreationAfterReturn.contains(taxAfterReturn),"Tax amount after submitting return is not equal to zero");
		nscore4OrdersTabPage.clickUpdateLink();
		nscore4OrdersTabPage.selectReturnReason();
		nscore4OrdersTabPage.selectReturnType();
		nscore4OrdersTabPage.clickSubmitReturnBtn();
		s_assert.assertAll();
	}


	//QTest ID TC-978 MAIN-5200, Change shipping address after override
	//QTest ID TC-979 MAIN-5200, Change shipping address after override
	@Test
	public void testChangeShippingAddressAfterOverride_978_979(){
		String accountNumber = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String SKU = "AARG001";
		String orignalPrice= null;
		String updatedPrice = null;
		String updateByValue = "5";
		String operation = "Subtraction";
		String shippingProfileAddress = null;
		String shippingAttentionName = TestConstants.NEW_SHIPPING_PROFILE_FIRST_NAME+String.valueOf(randomNum);
		accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
		nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
		nscore4HomePage.clickGoBtnOfSearch();
		nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
		//click 'Place-New-Order' link
		nscore4HomePage.clickPlaceNewOrderLink();
		nscore4HomePage.addAndGetSpecficProductSKU("10");

		s_assert.assertTrue(nscore4HomePage.isProductAddedToOrder(SKU), "SKU = "+SKU+" is not added to the Autoship Order");
		orignalPrice = nscore4HomePage.getAddedProductPriceBasedOnSKU(SKU);
		updatedPrice = nscore4HomePage.updatePriceForProduct(orignalPrice,updateByValue,operation);

		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickPerformOverridesButton();
		nscore4OrdersTabPage.enterPriceInOverrideOrderWindow(updatedPrice,SKU);
		nscore4OrdersTabPage.clickRecalculateTax();
		nscore4OrdersTabPage.selectOverrideReasons();
		nscore4OrdersTabPage.clickSaveButtonOnPerformOverrideWindow();
		nscore4OrdersTabPage.clickPaymentApplyLink();
		nscore4OrdersTabPage.clickEditNewShippingProfileLink();
		nscore4OrdersTabPage.enterShippingAttentionName(shippingAttentionName);
		nscore4OrdersTabPage.clickSaveAddressBtn();
		shippingProfileAddress = nscore4OrdersTabPage.getShippingProfileAddress();
		//Assert shipping profile address with attention name.
		s_assert.assertTrue(shippingProfileAddress.contains(shippingAttentionName),"Shipping profile address is not updated by updating attentionName");
		s_assert.assertAll();

	}


	 //QTest ID TC-262 NSC4_SitesTab_nsDistributor_BasePWSContentReviewApprove
		@Test
		public void testNSC4_SitesTab_nsDistributor_BasePWSContentReviewApprove_262() {
			int randomNumb = CommonUtils.getRandomNum(10000, 1000000);
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			String accountNumber = null;
			String linkName = "Product Focus";
			String storyTitle = "This is story title: "+randomNumb;
			String story = "This is new Automation story: "+randomNumber;
			String editMyPWS = "Edit PWS";
			accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
			logger.info("Account number from DB is "+accountNumber);
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(); 
			nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
			nscore4HomePage.clickProxyLink(linkName);
			nscore4HomePage.switchToSecondWindow();
			nscore4HomePage.clickRenewLater();
			s_assert.assertTrue(driver.getCurrentUrlWithExpectedWaitForURLPresent("myrfo").contains("myrfo"+driver.getEnvironment()+".com"), "User is not on dot com pws after clicking product focus proxy link.");
			nscore4HomePage.clickHeaderLinkAfterLogin(editMyPWS);
			nscore4HomePage.clickEditMyStoryLink();
			nscore4HomePage.clickIWantToWriteMyOwnStory();
			nscore4HomePage.typeSomeStoryAndclickSaveStory(storyTitle,story);
			s_assert.assertTrue(nscore4HomePage.verifyWaitingForApprovalLinkForNewStory(storyTitle),"Waiting for approval link is not present for newly created story");
			nscore4HomePage.switchToPreviousTab();
			nscore4HomePage.clickTab("Sites");
			nscore4SitesTabPage.clickPWSContentReviewLinkUnderNSCorporate();
			s_assert.assertTrue(nscore4SitesTabPage.verifyNewStoryWaitingForApprovedLink(story),"Waiting for approved link is not present for newly created story");
			nscore4SitesTabPage.clickApproveLinkForNewStory(story);
			s_assert.assertTrue(nscore4SitesTabPage.verifyApproveRequestDisappearFromUIOnceStoryApproved(story),"Approve request still appears in UI once Approved");
			nscore4SitesTabPage.clickTab("Accounts");
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(); 
			nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
			nscore4HomePage.clickProxyLink(linkName);
			nscore4HomePage.switchToSecondWindow();
			nscore4HomePage.clickHeaderLinkAfterLogin(editMyPWS);
			s_assert.assertTrue(nscore4HomePage.verifyNewlyCreatedStoryIsUpdated(story),"New Story displayed on legacy is not the newly created");
			nscore4HomePage.switchToPreviousTab();
			s_assert.assertAll();
		}
		
	//QTest ID TC-263 NSC4_SitesTab_nsDistributor_CorporatePWSContentReviewDenied
		@Test
		public void testNSC4_SitesTab_nsDistributor_CorporatePWSContentReviewDenied_263(){
			int randomNumb = CommonUtils.getRandomNum(10000, 1000000);
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
			String accountNumber= null;
			String linkName = "Product Focus";
			String storyTitle = "This is story title: "+randomNumb;
			String story = "This is new Automation story: "+randomNumber;
			String denyReason = "This is automation deny: "+randomNumbers;
			String editMyPWS = "Edit PWS";
			accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
			logger.info("Account number from DB is "+accountNumber);
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(); 
			nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
			nscore4HomePage.clickProxyLink(linkName);
			nscore4HomePage.switchToSecondWindow();
			nscore4HomePage.clickRenewLater();
			s_assert.assertTrue(driver.getCurrentUrlWithExpectedWaitForURLPresent("myrfo").contains("myrfo"+driver.getEnvironment()+".com"), "User is not on dot com pws after clicking product focus proxy link.");
			nscore4HomePage.clickHeaderLinkAfterLogin(editMyPWS);
			nscore4HomePage.clickEditMyStoryLink();
			nscore4HomePage.clickIWantToWriteMyOwnStory();
			nscore4HomePage.typeSomeStoryAndclickSaveStory(storyTitle,story);
			s_assert.assertTrue(nscore4HomePage.verifyWaitingForApprovalLinkForNewStory(storyTitle),"Waiting for approval link is not present for newly created story");
			nscore4HomePage.switchToPreviousTab();
			nscore4HomePage.clickTab("Sites");
			nscore4SitesTabPage.clickPWSContentReviewLinkUnderNSCorporate();
			s_assert.assertTrue(nscore4SitesTabPage.verifyNewStoryWaitingForApprovedLink(story),"Waiting for approved link is not present for newly created story");
			nscore4SitesTabPage.clickDenyLinkForNewStory(story);
			nscore4SitesTabPage.enterDenyReasonAndClickSubmit(denyReason);
			s_assert.assertTrue(nscore4SitesTabPage.verifyApproveRequestDisappearFromUIOnceStoryApproved(story),"Approve request still appears in UI once Approved");
			nscore4SitesTabPage.clickTab("Accounts");
			nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
			nscore4HomePage.clickGoBtnOfSearch(); 
			nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
			nscore4HomePage.clickProxyLink(linkName);
			nscore4HomePage.switchToSecondWindow();
			nscore4HomePage.clickHeaderLinkAfterLogin(editMyPWS);
			nscore4HomePage.clickEditMyStoryLink();
			s_assert.assertTrue(nscore4HomePage.getStoryDeniedText(storyTitle).contains("not approved"),"Story denied txt expected =(not approved)"+"while on UI"+nscore4HomePage.getStoryDeniedText(storyTitle));
			nscore4HomePage.switchToPreviousTab();
			s_assert.assertAll();
		}	
		
		
	//QTest ID TC-250 NSC4_SitesTab_nsCorporate_CorporatePWSContentReviewApprove
		@Test(priority=10)
		public void testCorporatePWSContentReviewApprove_250() {
			int randomNumb = CommonUtils.getRandomNum(10000, 1000000);
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			String accountNumber= null;
			String linkName = "Product Focus";
			String storyTitle = "This is story title: "+randomNumb;
			String story = "This is new Automation story: "+randomNumber;
			String editMyPWS = "Edit PWS";
			List<Map<String, Object>> randomConsultantList =  null;

			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL, RFL_DB);
			accountNumber = (String) getValueFromQueryResult(randomConsultantList, "AccountNumber");

			nscore4HomePage.clickSearchConsultant(accountNumber);
			nscore4HomePage.clickSearchIcon();	
			nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);

			nscore4HomePage.clickProxyLink(linkName);
			nscore4HomePage.switchToSecondWindow();
			nscore4HomePage.clickRenewLater();
			s_assert.assertTrue(driver.getCurrentUrlWithExpectedWaitForURLPresent("myrfo").contains("myrfo"+driver.getEnvironment()+".com"), "User is not on dot com pws after clicking product focus proxy link.");
			nscore4HomePage.clickHeaderLinkAfterLogin(editMyPWS);
			nscore4HomePage.clickEditMyStoryLink();
			nscore4HomePage.clickIWantToWriteMyOwnStory();
			nscore4HomePage.typeSomeStoryAndclickSaveStory(storyTitle,story);
			s_assert.assertTrue(nscore4HomePage.verifyWaitingForApprovalLinkForNewStory(storyTitle),"Waiting for approval link is not present for newly created story");
			nscore4HomePage.switchToPreviousTab();
			nscore4HomePage.clickTab("Sites");
			nscore4SitesTabPage.clickPWSContentReviewLinkUnderNSCorporate();
			s_assert.assertTrue(nscore4SitesTabPage.verifyNewStoryWaitingForApprovedLink(story),"Waiting for approved link is not present for newly created story");
			nscore4SitesTabPage.clickApproveLinkForNewStory(story);
			s_assert.assertTrue(nscore4SitesTabPage.verifyApproveRequestDisappearFromUIOnceStoryApproved(story),"Approve request still appears in UI once Approved");
			nscore4SitesTabPage.clickTab("Accounts");
			nscore4HomePage.clickSearchConsultant(accountNumber);
			nscore4HomePage.clickSearchIcon();	
			nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
			nscore4HomePage.clickProxyLink(linkName);
			nscore4HomePage.switchToSecondWindow();
			nscore4HomePage.clickHeaderLinkAfterLogin(editMyPWS);
			s_assert.assertTrue(nscore4HomePage.verifyNewlyCreatedStoryIsUpdated(story),"New Story displayed on legacy is not the newly created");
			nscore4HomePage.switchToPreviousTab();
			s_assert.assertAll();
		}	
		
		//QTest ID TC-267 NSC4_AccountsTab_AccountLookupAdvanceSeach
		@Test()
		public void testAccountLookupAdvanceSearch_267(){
			String firstName = null;
			String lastName = null;
			String accountNumber = null;
			String emailID = null;
			String sponserId = null;
			String sponserAccountNumber = null;
			String sponserFirstName = null;
			String sponserLastName = null;
			List<Map<String, Object>> randomConsultantAddressList =  null;
			List<Map<String, Object>> randomSponserAccountNumberList =  null;
			String stateFromDB=null;

			s_assert.assertTrue(nscore4HomePage.isLogoutLinkPresent(),"Home Page has not appeared after login");
			//Get random consultant from db with sponser details.
			randomConsultantAccountList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_EMAILID,RFL_DB);
			emailID = (String) getValueFromQueryResult(randomConsultantAccountList, "EmailAddress");
			accountNumber = String.valueOf(getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"));
			firstName = (String) getValueFromQueryResult(randomConsultantAccountList, "FirstName");
			lastName = (String) getValueFromQueryResult(randomConsultantAccountList, "LastName");
			sponserId = String.valueOf(getValueFromQueryResult(randomConsultantAccountList, "SponsorID"));
			//Get state details of consultant user.
			randomConsultantAddressList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_ACCOUNT_ADDRESS_DETAILS_FOR_ACCOUNT_INFO_QUERY_RFL,emailID), RFL_DB);
			stateFromDB = (String) getValueFromQueryResult(randomConsultantAddressList, "State");
			//Fetch sponser details from sponser ID.
			randomSponserAccountNumberList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_SPONSER_ACCOUNT_NUMBER_FROM_SPONSER_ID, sponserId),RFL_DB);
			sponserAccountNumber = (String) getValueFromQueryResult(randomSponserAccountNumberList, "AccountNumber");
			sponserFirstName = (String) getValueFromQueryResult(randomSponserAccountNumberList, "FirstName");
			sponserLastName = (String) getValueFromQueryResult(randomSponserAccountNumberList, "LastName");

			//Search User based on email.
			nscore4HomePage.searchUserOnTheBasisOfEmailAddress(emailID);
			s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(accountNumber,firstName, lastName),"First and last name not present in search results");
			//Search User based on First and last name.
			nscore4HomePage.clickBrowseAccounts();
			nscore4HomePage.enterFirstName(firstName);
			nscore4HomePage.enterLastName(lastName);
			nscore4HomePage.clickGoBtnOfSearch();
			s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(accountNumber,firstName, lastName),"First and last name not present in search results");
			//Search User based on Account type.
			nscore4HomePage.clickBrowseAccounts();
			nscore4HomePage.enterFirstName(firstName);
			nscore4HomePage.enterLastName(lastName);
			nscore4HomePage.selectAccountTypeForSearch("Consultant");
			nscore4HomePage.clickGoBtnOfSearch();
			s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(accountNumber,firstName, lastName),"First and last name not present in search results");
			//Search User based on sponsor account number.
			nscore4HomePage.clickBrowseAccounts();
			driver.pauseExecutionFor(5000);
			nscore4HomePage.enterAccountNumberInAccountSearchField(sponserAccountNumber);
			nscore4HomePage.clickGoBtnOfSearch();
			s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(sponserAccountNumber,sponserFirstName, sponserLastName),"Sponser First and last name not present in search results");
			//Search user based on sponsor First and last name.
			nscore4HomePage.clickBrowseAccounts();
			nscore4HomePage.enterFirstName(sponserFirstName);
			nscore4HomePage.enterLastName(sponserLastName);
			nscore4HomePage.clickGoBtnOfSearch();
			s_assert.assertTrue(nscore4HomePage.isFirstAndLastNamePresentinSearchResults(sponserAccountNumber,sponserFirstName, sponserLastName),"Sponser First and last name not present in search results");
			//Search User based on location.
			nscore4HomePage.clickBrowseAccounts();
			nscore4HomePage.selectByLocation(stateFromDB);
			nscore4HomePage.enterFirstName(firstName);
			nscore4HomePage.enterLastName(lastName);
			nscore4HomePage.clickGoBtnOfSearch();
			s_assert.assertTrue(nscore4HomePage.isSearchResultContainsFirstAndLastName(accountNumber,firstName, lastName),"First and last name not present in search results");
			s_assert.assertAll();
		}	
		

		//QTest ID TC-251 NSC4_SitesTab_nsCorporate_CorporatePWSContentReviewDenied
			@Test(priority=11)
			public void testCorporatePWSContentReviewDenied_251(){
				int randomNumb = CommonUtils.getRandomNum(10000, 1000000);
				int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
				int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
				String accountNumber= null;
				String linkName = "Product Focus";
				String storyTitle = "This is story title: "+randomNumb;
				String story = "This is new Automation story: "+randomNumber;
				String denyReason = "This is automation deny: "+randomNumbers;
				logger.info("DB is "+RFL_DB);
				String editMyPWS = "Edit PWS";
				
				accountNumber = (String) getValueFromQueryResult(randomConsultantAccountList, "AccountNumber"); 
				logger.info("Account number from DB is "+accountNumber);
				nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
				nscore4HomePage.clickGoBtnOfSearch(); 
				nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
				nscore4HomePage.clickProxyLink(linkName);
				nscore4HomePage.switchToSecondWindow();
				nscore4HomePage.clickRenewLater();
				s_assert.assertTrue(driver.getCurrentUrlWithExpectedWaitForURLPresent("myrfo").contains("myrfo"+driver.getEnvironment()+".com"), "User is not on dot com pws after clicking product focus proxy link.");
				nscore4HomePage.clickHeaderLinkAfterLogin(editMyPWS);
				nscore4HomePage.clickEditMyStoryLink();
				nscore4HomePage.clickIWantToWriteMyOwnStory();
				nscore4HomePage.typeSomeStoryAndclickSaveStory(storyTitle,story);
				s_assert.assertTrue(nscore4HomePage.verifyWaitingForApprovalLinkForNewStory(storyTitle),"Waiting for approval link is not present for newly created story");
				nscore4HomePage.switchToPreviousTab();
				nscore4HomePage.clickTab("Sites");
				nscore4SitesTabPage.clickPWSContentReviewLinkUnderNSCorporate();
				s_assert.assertTrue(nscore4SitesTabPage.verifyNewStoryWaitingForApprovedLink(story),"Waiting for approved link is not present for newly created story");
				nscore4SitesTabPage.clickDenyLinkForNewStory(story);
				nscore4SitesTabPage.enterDenyReasonAndClickSubmit(denyReason);
				s_assert.assertTrue(nscore4SitesTabPage.verifyApproveRequestDisappearFromUIOnceStoryApproved(story),"Approve request still appears in UI once Approved");
				nscore4SitesTabPage.clickTab("Accounts");
				nscore4HomePage.enterAccountNumberInAccountSearchField(accountNumber);
				nscore4HomePage.clickGoBtnOfSearch(); 
				nscore4HomePage.clickAccountRecordOnBasisOfAccountNumber(accountNumber);
				nscore4HomePage.clickProxyLink(linkName);
				nscore4HomePage.switchToSecondWindow();
				nscore4HomePage.clickHeaderLinkAfterLogin(editMyPWS);
				nscore4HomePage.clickEditMyStoryLink();
				s_assert.assertTrue(nscore4HomePage.getStoryDeniedText(storyTitle).contains("not approved"),"Story denied txt expected =(not approved)"+"while on UI"+nscore4HomePage.getStoryDeniedText(storyTitle));
				nscore4HomePage.switchToPreviousTab();
				s_assert.assertAll();
			}	
			
	
}