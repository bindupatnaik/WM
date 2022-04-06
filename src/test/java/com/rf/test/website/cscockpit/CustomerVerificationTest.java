package com.rf.test.website.cscockpit;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.rf.core.utils.DBUtil;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.cscockpit.CSCockpitAutoshipSearchTabPage;
import com.rf.pages.website.cscockpit.CSCockpitAutoshipTemplateTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCartTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCheckoutTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerSearchTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerTabPage;
import com.rf.pages.website.cscockpit.CSCockpitLoginPage;
import com.rf.pages.website.cscockpit.CSCockpitOrderSearchTabPage;
import com.rf.pages.website.cscockpit.CSCockpitOrderTabPage;
import com.rf.pages.website.storeFront.StoreFrontConsultantPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.pages.website.storeFront.StoreFrontOrdersPage;
import com.rf.pages.website.storeFront.StoreFrontPCUserPage;
import com.rf.pages.website.storeFront.StoreFrontRCUserPage;
import com.rf.pages.website.storeFront.StoreFrontUpdateCartPage;
import com.rf.test.website.RFWebsiteBaseTest;

public class CustomerVerificationTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(CustomerVerificationTest.class.getName());

	//-------------------------------------------------Pages---------------------------------------------------------
	private CSCockpitLoginPage cscockpitLoginPage;	
	private CSCockpitAutoshipSearchTabPage cscockpitAutoshipSearchTabPage;
	private CSCockpitCheckoutTabPage cscockpitCheckoutTabPage;
	private CSCockpitCustomerSearchTabPage cscockpitCustomerSearchTabPage;
	private CSCockpitCustomerTabPage cscockpitCustomerTabPage;
	private CSCockpitOrderTabPage cscockpitOrderTabPage;
	private CSCockpitCartTabPage cscockpitCartTabPage;


	public CustomerVerificationTest() {
		cscockpitLoginPage = new CSCockpitLoginPage(driver);
		cscockpitAutoshipSearchTabPage = new CSCockpitAutoshipSearchTabPage(driver);
		cscockpitCheckoutTabPage = new CSCockpitCheckoutTabPage(driver);
		cscockpitCustomerSearchTabPage = new CSCockpitCustomerSearchTabPage(driver);
		cscockpitCustomerTabPage = new CSCockpitCustomerTabPage(driver);
		cscockpitOrderTabPage = new CSCockpitOrderTabPage(driver);
		cscockpitCartTabPage = new CSCockpitCartTabPage(driver);
		storeFrontHomePage = new StoreFrontHomePage(driver);
		storeFrontConsultantPage = new StoreFrontConsultantPage(driver);
		storeFrontOrdersPage = new StoreFrontOrdersPage(driver);
		storeFrontPCUserPage = new StoreFrontPCUserPage(driver);
		storeFrontRCUserPage = new StoreFrontRCUserPage(driver);
		storeFrontUpdateCartPage = new StoreFrontUpdateCartPage(driver);
	}

	//private String RFO_DB = null;

	public void testVerifyConsultantCustomerDetailPageUI_1929(){
		String randomCustomerSequenceNumber = null;
		String accountStatus = "Account Status";
		String consultant = "CONSULTANT";
		String mainAddress = "Main Address"; 
		String email = "Email";
		String sponsor = "Sponsor"; 
		String autoshipTemplateID = "ID";
		String autoshipTemplateType = "Type";
		String autoshipTemplateStatus = "Status";
		String autoshipTemplateActive = "Active";
		String autoshipTemplateCreationDate = "Creation Date";
		String autoshipTemplateTemplateTotal = "Template Total";
		String autoshipTemplateNextDueDate = "Next Due Date";
		String autoshipTemplateOfOrders = "# of Orders";
		String orderType = "Order Type";
		String orderStatus = "Order Status";
		String orderTotal = "Order Total";
		String orderDate = "Order Date";
		String orderNotes = "Order Notes";
		String creditCardNumber = "Credit Card number";
		String creditCardOwner = "Credit Card Owner";
		String type = "Type";
		String month = "Month";
		String validToYear = "Valid to year";
		String billingAddress = "Billing address";
		String lastName = "Last Name";
		String line1 = null;
		String line2 = null;
		String cityOrTown = "Town";
		String postalCode = "Post";
		String country = "Country";
		String stateOrProvince = "State";
		String addressType = "Address Type";
		List<Map<String, Object>> randomAccountDetailsList =  null;
		if(driver.getCountry().equalsIgnoreCase("CA")) {
			line1 = "Line 1";
			line2 = "Line 2";
		}else {
			line1 = TestConstants.LINE1_AU; 
			line2 = TestConstants.LINE2_AU; 
		}
		driver.get(driver.getCSCockpitURL());		
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		//Fetch consultant having autoship from database.
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		randomAccountDetailsList =DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_MOBILE_NUMBER_QUERY_RFO,consultantEmailID),RFO_DB);
		String PhoneNumber =  (String) getValueFromQueryResult(randomAccountDetailsList,"Number");
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(consultantEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("CONSULTANT");
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(countryDDValue);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		String CID = cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		//assert account details
		s_assert.assertTrue(cscockpitCustomerTabPage.getOrderDetailsInCustomerTab(CID).contains(CID), "order number in customer tab"+CID+"and on UI "+cscockpitCustomerTabPage.getOrderDetailsInCustomerTab(CID));
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(accountStatus), "Account status is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyCustomerTypeIsPresentInCustomerTab(consultant), "Account type  is not consultant in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(PhoneNumber), "customer phone is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(mainAddress), "Main address is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(email), "Email is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(sponsor), "sponsor is not present in customer tab");
		//assert autoship template details
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateID), "Autoship Template ID is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateType), "Autoship Template type is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateStatus), "Autoship Template Status is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateActive), "Autoship Template Active is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateCreationDate), "Autoship Template Creation Date is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateTemplateTotal), "Autoship Template Template total is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateNextDueDate), "Autoship Template Next Due Date is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateOfOrders), "Autoship Template # pf orders is not present in customer tab");
		cscockpitCustomerTabPage.getAndClickFirstAutoshipIDInCustomerTab();
		s_assert.assertTrue(cscockpitAutoshipSearchTabPage.isAutoshipTemplateDisplayedInAutoshipTemplateTab(), "Autoship template page is not displayed");
		cscockpitAutoshipSearchTabPage.clickCustomerTab();
		//assert customer orders
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderType), "Order type section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderStatus), "Order status section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderTotal), "Order total section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderDate), "Order Date section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderNotes), "Order Notes section is not present in customer tab");
		cscockpitCustomerTabPage.clickAndGetOrderNumberInCustomerTab();
		s_assert.assertTrue(cscockpitOrderTabPage.isOrderTemplateDisplayedInOrderTab(), "Order template page is not displayed");
		cscockpitOrderTabPage.clickCustomerTab();
		//assert billing information section
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(creditCardNumber), "Credit card number section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(creditCardOwner), "credit card owner section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(type), "type section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(month), "Month section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(validToYear), "Valid to year section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(billingAddress), "billing address section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.isAddCardButtonPresentInCustomerTab(), "Add card button is not present in billing section of customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditButtonForCreditCardPresentInCustomerTab(), "Edit button for credit card is not present in billing section of customer tab");
		cscockpitCustomerTabPage.clickAddCardButtonInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isAddNewPaymentProfilePopupPresentInCustomerTab(), "Add new payment profile popup is not present in billing section of customer tab");
		cscockpitCheckoutTabPage.clickCloseOfPaymentAddressPopUpInCheckoutTab();
		cscockpitCustomerTabPage.clickEditButtonForCreditCardInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditPaymentProfilePopupPresentInCustomerTab(), "Edit payment profile popup is not present in billing section of customer tab");
		cscockpitCheckoutTabPage.clickCloseOfEditPaymentAddressPopUpInCheckoutTab();
		//assert customer address
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(lastName), "Last Name section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(line1), "Line 1 section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(line2), "Line 2 section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(cityOrTown), "City/Town section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(postalCode), "Postal Code section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(country), "Country section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(stateOrProvince), "State/Province section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(addressType), "Address type section is not present in customer tab");
		cscockpitCustomerTabPage.clickEditButtonOfShippingAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditAddressPopupPresentInCustomerTab(), "Edit Address popup is not present in billing section of customer tab");
		cscockpitCustomerTabPage.clickCloseOfEditAddressPopUpInCustomerTab();
		cscockpitCustomerTabPage.clickAddButtonOfCustomerAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isCreateNewAddressPopupPresentInCustomerTab(), "Create new Address popup is not present in billing section of customer tab");
		s_assert.assertAll();
	}	



	@Test
	public void testVerifyPreferredCustomerDetailPageUI_1930(){
		String randomCustomerSequenceNumber = null;
		String accountStatus = "Account Status";
		String pc = "PC";
		String mainAddress = "Main Address"; 
		String email = "Email";
		String sponsor = "Sponsor"; 
		String autoshipTemplateID = "ID";
		String autoshipTemplateType = "Type";
		String autoshipTemplateStatus = "Status";
		String autoshipTemplateActive = "Active";
		String autoshipTemplateCreationDate = "Creation Date";
		String autoshipTemplateTemplateTotal = "Template Total";
		String autoshipTemplateNextDueDate = "Next Due Date";
		String autoshipTemplateOfOrders = "# of Orders";
		String orderType = "Order Type";
		String orderStatus = "Order Status";
		String orderTotal = "Order Total";
		String orderDate = "Order Date";
		String orderNotes = "Order Notes";
		String creditCardNumber = "Credit Card number";
		String creditCardOwner = "Credit Card Owner";
		String type = "Type";
		String month = "Month";
		String validToYear = "Valid to year";
		String billingAddress = "Billing address";
		String lastName = "Last Name";
		String line1 = null;
		String line2 = null;
		String cityOrTown = "Town";
		String postalCode = "Post";
		String country = "Country";
		String stateOrProvince = "State";
		String addressType = "Address Type";
		List<Map<String, Object>> randomAccountDetailsList =  null;
		if(driver.getCountry().equalsIgnoreCase("CA")) {
			line1 = "Line 1";
			line2 = "Line 2";
		}else {
			line1 = TestConstants.LINE1_AU; 
			line2 = TestConstants.LINE2_AU; 
		}

		driver.get(driver.getCSCockpitURL());		
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		//Fetch PC user having autoship from database.
		String pcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		randomAccountDetailsList =DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_MOBILE_NUMBER_QUERY_RFO,pcUserEmailID),RFO_DB);
		String PhoneNumber =  (String) getValueFromQueryResult(randomAccountDetailsList,"Number");
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(pcUserEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("PC");
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(countryDDValue);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		String CID = cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		//assert account details
		s_assert.assertTrue(cscockpitCustomerTabPage.getOrderDetailsInCustomerTab(CID).contains(CID), "order number in customer tab"+CID+"and on UI "+cscockpitCustomerTabPage.getOrderDetailsInCustomerTab(CID));
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(accountStatus), "Account status is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyCustomerTypeIsPresentInCustomerTab(pc), "Account type  is not consultant in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(PhoneNumber), "customer phone is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(mainAddress), "Main address is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(email), "Email is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(sponsor), "sponsor is not present in customer tab");
		//assert autoship template details
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateID), "Autoship Template ID is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateType), "Autoship Template type is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateStatus), "Autoship Template Status is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateActive), "Autoship Template Active is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateCreationDate), "Autoship Template Creation Date is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateTemplateTotal), "Autoship Template Template total is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateNextDueDate), "Autoship Template Next Due Date is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateOfOrders), "Autoship Template # pf orders is not present in customer tab");
		cscockpitCustomerTabPage.getAndClickFirstAutoshipIDInCustomerTab();
		s_assert.assertTrue(cscockpitAutoshipSearchTabPage.isAutoshipTemplateDisplayedInAutoshipTemplateTab(), "Autoship template page is not displayed");
		cscockpitAutoshipSearchTabPage.clickCustomerTab();
		//assert customer orders
		if(cscockpitCustomerTabPage.isOrderPresentInCustomerOrderSection()){
			s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderType), "Order type section is not present in customer tab");
			s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderStatus), "Order status section is not present in customer tab");
			s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderTotal), "Order total section is not present in customer tab");
			s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderDate), "Order Date section is not present in customer tab");
			s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderNotes), "Order Notes section is not present in customer tab");
			cscockpitCustomerTabPage.clickAndGetOrderNumberInCustomerTab();
			s_assert.assertTrue(cscockpitOrderTabPage.isOrderTemplateDisplayedInOrderTab(), "Order template page is not displayed");
			cscockpitOrderTabPage.clickCustomerTab();
		}
		//assert billing information section
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(creditCardNumber), "Credit card number section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(creditCardOwner), "credit card owner section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(type), "type section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(month), "Month section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(validToYear), "Valid to year section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(billingAddress), "billing address section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.isAddCardButtonPresentInCustomerTab(), "Add card button is not present in billing section of customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditButtonForCreditCardPresentInCustomerTab(), "Edit button for credit card is not present in billing section of customer tab");
		cscockpitCustomerTabPage.clickAddCardButtonInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isAddNewPaymentProfilePopupPresentInCustomerTab(), "Add new payment profile popup is not present in billing section of customer tab");
		cscockpitCheckoutTabPage.clickCloseOfPaymentAddressPopUpInCheckoutTab();
		cscockpitCustomerTabPage.clickEditButtonForCreditCardInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditPaymentProfilePopupPresentInCustomerTab(), "Edit payment profile popup is not present in billing section of customer tab");
		cscockpitCheckoutTabPage.clickCloseOfEditPaymentAddressPopUpInCheckoutTab();
		//assert customer address
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(lastName), "Last Name section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(line1), "Line 1 section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(line2), "Line 2 section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(cityOrTown), "City/Town section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(postalCode), "Postal Code section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(country), "Country section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(stateOrProvince), "State/Province section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(addressType), "Address type section is not present in customer tab");
		cscockpitCustomerTabPage.clickEditButtonOfShippingAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditAddressPopupPresentInCustomerTab(), "Edit Address popup is not present in billing section of customer tab");
		cscockpitCustomerTabPage.clickCloseOfEditAddressPopUpInCustomerTab();
		cscockpitCustomerTabPage.clickAddButtonOfCustomerAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isCreateNewAddressPopupPresentInCustomerTab(), "Create new Address popup is not present in billing section of customer tab");
		s_assert.assertAll();
	}


	@Test
	public void testVerifyRetailCustomerDetailPageUI_1931(){
		String randomCustomerSequenceNumber = null;
		String accountStatus = "Account Status";
		String rc = "RETAIL";
		String mainAddress = "Main Address"; 
		String email = "Email";
		String sponsor = "Sponsor"; 
		String autoshipTemplateID = "ID";
		String orderType = "Order Type";
		String orderStatus = "Order Status";
		String orderTotal = "Order Total";
		String orderDate = "Order Date";
		String orderNotes = "Order Notes";
		String creditCardNumber = "Credit Card number";
		String creditCardOwner = "Credit Card Owner";
		String type = "Type";
		String month = "Month";
		String validToYear = "Valid to year";
		String billingAddress = "Billing address";
		String lastName = "Last Name";
		String line1 = null;
		String line2 = null;
		String cityOrTown = "Town";
		String postalCode = "Post";
		String country = "Country";
		String stateOrProvince = "State";
		String addressType = "Address Type";
		String randomProductSequenceNumber = null;
		String SKUValue = null;
		driver.get(driver.getCSCockpitURL());	
		if(driver.getCountry().equalsIgnoreCase("CA")) {
			line1 = "Line 1";
			line2 = "Line 2";
		}else {
			line1 = TestConstants.LINE1_AU; 
			line2 = TestConstants.LINE2_AU; 
		}

		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		String rcUserEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId);
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(rcUserEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab(rc);
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(countryDDValue);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		String CID = cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);

		//place an order
		cscockpitCustomerTabPage.clickPlaceOrderButtonInCustomerTab();
		cscockpitCartTabPage.selectValueFromSortByDDInCartTab("Price: High to Low");
		cscockpitCartTabPage.selectCatalogFromDropDownInCartTab();	
		randomProductSequenceNumber = String.valueOf(cscockpitCartTabPage.getRandomProductWithSKUFromSearchResult()); 
		SKUValue = cscockpitCartTabPage.getCustomerSKUValueInCartTab(randomProductSequenceNumber);
		cscockpitCartTabPage.searchSKUValueInCartTab(SKUValue);
		cscockpitCartTabPage.clickAddToCartBtnInCartTab();
		cscockpitCartTabPage.clickCheckoutBtnInCartTab();

		String attendentFirstName = TestConstants.FIRST_NAME;
		String attendeeLastName = TestConstants.LAST_NAME;

		cscockpitCheckoutTabPage.addDeliveryAddressIfNonSelected(attendentFirstName, attendeeLastName, addressLine1, city, postalCode, countryDDValue, state, phoneNumber);
		cscockpitCheckoutTabPage.clickAddNewPaymentAddressInCheckoutTab();
		cscockpitCheckoutTabPage.enterBillingInfo();
		cscockpitCheckoutTabPage.clickSaveAddNewPaymentProfilePopUP();
		cscockpitCheckoutTabPage.enterCVVValueInCheckoutTab(TestConstants.SECURITY_CODE);
		cscockpitCheckoutTabPage.clickUseThisCardBtnInCheckoutTab();
		cscockpitCheckoutTabPage.clickPlaceOrderButtonInCheckoutTab();
		cscockpitCheckoutTabPage.clickCustomerTab();

		//assert account details
		s_assert.assertTrue(cscockpitCustomerTabPage.getOrderDetailsInCustomerTab(CID).contains(CID), "order number in customer tab"+CID+"and on UI "+cscockpitCustomerTabPage.getOrderDetailsInCustomerTab(CID));
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(accountStatus), "Account status is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifyCustomerTypeIsPresentInCustomerTab(rc), "Account type  is not consultant in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(mainAddress), "Main address is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(email), "Email is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.getAccountDetailsInCustomerTab(sponsor), "sponsor is not present in customer tab");
		//assert autoship template details
		s_assert.assertFalse(cscockpitCustomerTabPage.verifyAutoshipTemplateDetailsInCustomerTab(autoshipTemplateID), "Autoship Template ID is not present in customer tab");
		//assert customer orders
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderType), "Order type section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderStatus), "Order status section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderTotal), "Order total section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderDate), "Order Date section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(orderNotes), "Order Notes section is not present in customer tab");
		cscockpitCustomerTabPage.clickAndGetOrderNumberInCustomerTab();
		s_assert.assertTrue(cscockpitOrderTabPage.isOrderTemplateDisplayedInOrderTab(), "Order template page is not displayed");
		cscockpitCheckoutTabPage.clickCustomerTab();
		//assert billing information section
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(creditCardNumber), "Credit card number section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(creditCardOwner), "credit card owner section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(type), "type section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(month), "Month section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(validToYear), "Valid to year section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(billingAddress), "billing address section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.isAddCardButtonPresentInCustomerTab(), "Add card button is not present in billing section of customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditButtonForCreditCardPresentInCustomerTab(), "Edit button for credit card is not present in billing section of customer tab");
		cscockpitCustomerTabPage.clickAddCardButtonInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isAddNewPaymentProfilePopupPresentInCustomerTab(), "Add new payment profile popup is not present in billing section of customer tab");
		cscockpitCheckoutTabPage.clickCloseOfPaymentAddressPopUpInCheckoutTab();
		cscockpitCustomerTabPage.clickEditButtonForCreditCardInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditPaymentProfilePopupPresentInCustomerTab(), "Edit payment profile popup is not present in billing section of customer tab");
		cscockpitCheckoutTabPage.clickCloseOfEditPaymentAddressPopUpInCheckoutTab();
		//assert customer address
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(lastName), "Last Name section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(line1), "Line 1 section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(line2), "Line 2 section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(cityOrTown), "City/Town section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(postalCode), "Postal Code section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(country), "Country section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(stateOrProvince), "State/Province section is not present in customer tab");
		s_assert.assertTrue(cscockpitCustomerTabPage.verifySectionsIsPresentInCustomerTab(addressType), "Address type section is not present in customer tab");
		cscockpitCustomerTabPage.clickEditButtonOfShippingAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isEditAddressPopupPresentInCustomerTab(), "Edit Address popup is not present in billing section of customer tab");
		cscockpitCustomerTabPage.clickCloseOfEditAddressPopUpInCustomerTab();
		cscockpitCustomerTabPage.clickAddButtonOfCustomerAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.isCreateNewAddressPopupPresentInCustomerTab(), "Create new Address popup is not present in billing section of customer tab");
		s_assert.assertAll();
	}


}