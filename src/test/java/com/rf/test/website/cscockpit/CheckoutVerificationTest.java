package com.rf.test.website.cscockpit;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.cscockpit.CSCockpitCartTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCheckoutTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerSearchTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerTabPage;
import com.rf.pages.website.cscockpit.CSCockpitLoginPage;
import com.rf.pages.website.cscockpit.CSCockpitOrderTabPage;
import com.rf.pages.website.storeFront.StoreFrontConsultantPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.pages.website.storeFront.StoreFrontOrdersPage;
import com.rf.test.website.RFWebsiteBaseTest;

public class CheckoutVerificationTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(CheckoutVerificationTest.class.getName());

	//-------------------------------------------------Pages---------------------------------------------------------
	private CSCockpitLoginPage cscockpitLoginPage;	
	private CSCockpitCheckoutTabPage cscockpitCheckoutTabPage;
	private CSCockpitCustomerSearchTabPage cscockpitCustomerSearchTabPage;
	private CSCockpitCustomerTabPage cscockpitCustomerTabPage;
	private CSCockpitOrderTabPage cscockpitOrderTabPage;
	private CSCockpitCartTabPage cscockpitCartTabPage;
	private StoreFrontHomePage storeFrontHomePage; 
	private StoreFrontConsultantPage storeFrontConsultantPage;
	private StoreFrontOrdersPage storeFrontOrdersPage;

	//-----------------------------------------------------------------------------------------------------------------

	public CheckoutVerificationTest() {
		cscockpitLoginPage = new CSCockpitLoginPage(driver);
		cscockpitCheckoutTabPage = new CSCockpitCheckoutTabPage(driver);
		cscockpitCustomerSearchTabPage = new CSCockpitCustomerSearchTabPage(driver);
		cscockpitCustomerTabPage = new CSCockpitCustomerTabPage(driver);
		cscockpitOrderTabPage = new CSCockpitOrderTabPage(driver);
		cscockpitCartTabPage = new CSCockpitCartTabPage(driver);
		storeFrontHomePage = new StoreFrontHomePage(driver);
		storeFrontConsultantPage = new StoreFrontConsultantPage(driver);
		storeFrontOrdersPage = new StoreFrontOrdersPage(driver);		
	}

	private String RFO_DB = null;

	// Hybris Project-1733:To verify create payment address functionality in the Checkout Page
		@Test
		public void testVerifyCreatePaymentAddressOnCheckoutPage_1733() throws InterruptedException{
			RFO_DB = driver.getDBNameRFO();
			int randomNum = CommonUtils.getRandomNum(10000, 1000000);
			String randomCustomerSequenceNumber = null;
			String randomProductSequenceNumber = null;
			String consultantEmailID = null;
			String SKUValue = null;
			String attendeeLastName="IN";
			String orderNumber=null;
			String orderNumberOfOrderTab=null;
			String orderHistoryNumber=null;
			String CountryDDValue= null;
			String addressLine1Value=null;
			String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
			String cardNumber=TestConstants.CARD_NUMBER;
			String attendeeFirstName=TestConstants.FIRST_NAME+randomNum;
			if(driver.getCountry().equalsIgnoreCase("ca")){
				CountryDDValue=TestConstants.COUNTRY_DD_VALUE_CA;
				addressLine1Value="Line 1";
			}
			else if(driver.getCountry().equalsIgnoreCase("au")){
				CountryDDValue=TestConstants.COUNTRY_DD_VALUE_AU;
				addressLine1Value="Address 1";
			}


			//		driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			List<Map<String, Object>> randomConsultantList =  null;
			//
			//		while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
			//			try{
			//				storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			//			}catch(Exception e){
			//				driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			//				storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			//			}	
			//			boolean isLoginError = driver.getCurrentUrl().contains("error");
			//			if(isLoginError){
			//				logger.info("Login error for the user "+consultantEmailID);
			//				driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			//			}
			//			else
			//				break;
			//		}
			//		logout();
			//logger.info("login is successful");
			//driver.get(driver.getCSCockpitURL());

			cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
			cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(consultantEmailID);
			cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("CONSULTANT");
			//cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(CountryDDValue);
			cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");

			cscockpitCustomerSearchTabPage.clickSearchBtn();
			randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
			cscockpitCustomerSearchTabPage.clickCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
			orderNumber=cscockpitCustomerTabPage.clickAndGetOrderNumberInCustomerTab();
			logger.info("clicked order number 1 is "+orderNumber);
			cscockpitCustomerTabPage.clickPlaceOrderButtonInCustomerTab();
			cscockpitCartTabPage.selectValueFromSortByDDInCartTab("Price: High to Low");
			cscockpitCartTabPage.selectCatalogFromDropDownInCartTab();
			randomProductSequenceNumber = String.valueOf(cscockpitCartTabPage.getRandomProductWithSKUFromSearchResult()); 
			SKUValue = cscockpitCartTabPage.getCustomerSKUValueInCartTab(randomProductSequenceNumber);
			cscockpitCartTabPage.searchSKUValueInCartTab(SKUValue);
			cscockpitCartTabPage.clickAddToCartBtnInCartTab();
			cscockpitCartTabPage.clickCheckoutBtnInCartTab();
			cscockpitCheckoutTabPage.clickAddNewPaymentAddressInCheckoutTab();
			cscockpitCheckoutTabPage.clickAddNewAddressLinkInPopUpInCheckoutTab();
			s_assert.assertTrue(cscockpitCheckoutTabPage.verifyAddressTextBoxInPopUpInCheckoutTab(addressLine1Value),"Address Line 1 text box is not present in popup at checkout tab");
			cscockpitCheckoutTabPage.clickCloseOfPaymentAddressPopUpInCheckoutTab();
			cscockpitCheckoutTabPage.clickAddNewPaymentAddressInCheckoutTab();
			// cscockpitCheckoutTabPage.clickAddNewAddressLinkInPopUpInCheckoutTab();
			cscockpitCheckoutTabPage.clickSaveOfShippingAddressPopUpInCheckoutTab();
			s_assert.assertTrue(cscockpitCheckoutTabPage.getErrorMessageOfPopupWithoutFillingDataInCheckoutTab().contains("Please review credit card information entered"),"CSCockpit checkout tab popup error message expected = Please review credit card information entered and on UI = " +cscockpitCheckoutTabPage.getErrorMessageOfPopupWithoutFillingDataInCheckoutTab());
			cscockpitCheckoutTabPage.enterPaymentDetailsInPopUpInCheckoutTab(cardNumber, newBillingProfileName,CVV,"09",cardExpYear,"VISA");
			cscockpitCheckoutTabPage.enterShippingDetailsInPopUpInCheckoutTab(attendeeFirstName,attendeeLastName,addressLine1,city,postalCode,CountryDDValue,state,phoneNumber);
			cscockpitCheckoutTabPage.clickSaveOfShippingAddressPopUpInCheckoutTab();
			s_assert.assertTrue(cscockpitCheckoutTabPage.getNewBillingAddressNameInCheckoutTab().contains(attendeeFirstName),"CSCockpit checkout tab newly created billing address name expected ="+ attendeeFirstName+ "and on UI = " +cscockpitCheckoutTabPage.getNewBillingAddressNameInCheckoutTab());
			//		cscockpitCheckoutTabPage.enterCVVValueInCheckoutTab(TestConstants.SECURITY_CODE);
			//		cscockpitCheckoutTabPage.clickUseThisCardBtnInCheckoutTab();
			//		cscockpitCheckoutTabPage.clickPlaceOrderButtonInCheckoutTab();
			//		orderNumberOfOrderTab = cscockpitOrderTabPage.getOrderNumberInOrderTab();
			//		logger.info("order number 2 in order tab is "+orderNumberOfOrderTab);
			//		cscockpitOrderTabPage.clickCustomerTab();
			//		s_assert.assertTrue(cscockpitCustomerTabPage.getOrderTypeInCustomerTab(orderNumberOfOrderTab.split("\\-")[0].trim()).contains("Consultant Order"),"CSCockpit Customer tab Order type expected = Consultant Order and on UI = " +cscockpitCustomerTabPage.getOrderTypeInCustomerTab(orderNumberOfOrderTab.split("\\-")[0].trim()));
			//		driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			//		try{
			//			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			//		}catch(Exception e){
			//			driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			//			storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			//		}	
			//		storeFrontConsultantPage.clickOnWelcomeDropDown();
			//		storeFrontOrdersPage =  storeFrontConsultantPage.clickOrdersLinkPresentOnWelcomeDropDown();
			//		s_assert.assertTrue(storeFrontOrdersPage.verifyOrdersPageIsDisplayed(),"Orders page has not been displayed");
			//		orderHistoryNumber = storeFrontOrdersPage.getFirstOrderNumberFromOrderHistory();
			//		logger.info("order number 3 from order history is "+orderHistoryNumber);
			//		s_assert.assertTrue(orderHistoryNumber.contains(orderNumberOfOrderTab.split("\\-")[0].trim()),"Expected CSCockpit Order number= "+orderNumberOfOrderTab.split("\\-")[0].trim()+" and on UI = " +orderHistoryNumber);
			//		logout();
			s_assert.assertAll();
		}

	//Hybris Project-1734:To verify create delivery address functionality in the Checkout Page
		@Test
		public void testVerifyCreateDeliveryAddressOnCheckoutPage_1734() throws InterruptedException{
			RFO_DB = driver.getDBNameRFO();
			String randomCustomerSequenceNumber = null;
			String randomProductSequenceNumber = null;
			String consultantEmailID = null;
			String SKUValue = null;
			int randomNum = CommonUtils.getRandomNum(10000, 1000000);
			int randomNumber=CommonUtils.getRandomNum(10000, 1000000);
			String attendeeLastName="IN";
			String orderNumberOfOrderTab=null;
			String orderHistoryNumber=null;
			String attendeeFirstName=TestConstants.FIRST_NAME+randomNum;
			String attendeeNewFirstName=TestConstants.FIRST_NAME+randomNumber;
			String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US + randomNum;
			String cardNumber = TestConstants.CARD_NUMBER;

			String line1 = null;
			if(driver.getCountry().equalsIgnoreCase("us")){
				city = TestConstants.CITY_US;
				phoneNumber = TestConstants.PHONE_NUMBER_US;
				country = "United States";
			}else if(driver.getCountry().equalsIgnoreCase("ca")){
				city = TestConstants.CITY_CA;
				phoneNumber = TestConstants.PHONE_NUMBER_CA;
				country = "Canada";
				line1 = "Line 1";
			}
			else if(driver.getCountry().equalsIgnoreCase("au")){
				city = TestConstants.CITY_AU;
				phoneNumber = TestConstants.PHONE_NUMBER_AU;
				country = "Australia";
				line1 = TestConstants.LINE1_AU;
			}  

			//			driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			List<Map<String, Object>> randomConsultantList =  null;
			//			while(true){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");  
			//				try{
			//					storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			//				}catch(Exception e){
			//					driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			//					storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			//				}	
			//				boolean isLoginError = driver.getCurrentUrl().contains("error");
			//				if(isLoginError){
			//					logger.info("Login error for the user "+consultantEmailID);
			//					driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			//				}
			//				else
			//					break;
			//			}
			//			logout();
			//			logger.info("login is successful");
			//			driver.get(driver.getCSCockpitURL());

			cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
			cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(consultantEmailID);
			cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("CONSULTANT");
			//cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(countryDDValue);
			cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");

			cscockpitCustomerSearchTabPage.clickSearchBtn();
			randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
			cscockpitCustomerSearchTabPage.clickCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
			cscockpitCustomerTabPage.clickPlaceOrderButtonInCustomerTab();
			cscockpitCartTabPage.selectValueFromSortByDDInCartTab("Price: High to Low");
			cscockpitCartTabPage.selectCatalogFromDropDownInCartTab();
			randomProductSequenceNumber = String.valueOf(cscockpitCartTabPage.getRandomProductWithSKUFromSearchResult()); 
			SKUValue = cscockpitCartTabPage.getCustomerSKUValueInCartTab(randomProductSequenceNumber);
			cscockpitCartTabPage.searchSKUValueInCartTab(SKUValue);
			cscockpitCartTabPage.clickAddToCartBtnInCartTab();
			cscockpitCartTabPage.clickCheckoutBtnInCartTab();
			cscockpitCheckoutTabPage.clickAddNewAddressUnderDeliveryAddressInCheckoutTab();
			//s_assert.assertTrue(cscockpitCheckoutTabPage.verifyAddressTextBoxInPopUpInCheckoutTab(line1),"Address Line 1 text box is not present in popup at checkout tab");
			s_assert.assertTrue(cscockpitCheckoutTabPage.verifyAddressTextBoxInPopUpInCheckoutTab(line1),"Address Line 1 text box is not present in popup at checkout tab");

			cscockpitCheckoutTabPage.clickCloseOfDeliveryAddressPopUpInCheckoutTab();
			cscockpitCheckoutTabPage.clickAddNewAddressUnderDeliveryAddressInCheckoutTab();
			cscockpitCheckoutTabPage.clickSaveOfDeliveryAddressPopUpInCheckoutTab();
			s_assert.assertTrue(cscockpitCheckoutTabPage.getErrorMessageOfDeliveryAddressPopupWithoutFillingDataInCheckoutTab().contains("Attention should contain First Name and Last Name"),"CSCockpit checkout tab popup error message expected = Attention should contain First Name and Last Name and on UI = " +cscockpitCheckoutTabPage.getErrorMessageOfDeliveryAddressPopupWithoutFillingDataInCheckoutTab());
			cscockpitCheckoutTabPage.clickOKOfDeliveryAddressPopupInCheckoutTab();
			cscockpitCheckoutTabPage.enterShippingDetailsInPopUpInCheckoutTab(attendeeFirstName,attendeeLastName,addressLine1,city,postalCode,countryDDValue,state,phoneNumber);
			cscockpitCheckoutTabPage.clickSaveOfDeliveryAddressPopUpInCheckoutTab();
			cscockpitCheckoutTabPage.clickUseAsEnteredPopupOkayInCheckoutTab();
			s_assert.assertTrue(cscockpitCheckoutTabPage.getNewlyCreatedDeliveryAddressNameInCheckoutTab().contains(attendeeFirstName),"CSCockpit checkout tab newly created Delivery address name expected ="+ attendeeFirstName+ "and on UI = " +cscockpitCheckoutTabPage.getNewlyCreatedDeliveryAddressNameInCheckoutTab());
			//			cscockpitCheckoutTabPage.clickAddNewAddressUnderDeliveryAddressInCheckoutTab();
			//			cscockpitCheckoutTabPage.enterShippingDetailsInPopUpInCheckoutTab(attendeeNewFirstName,attendeeLastName,addressLine1,city,postalCode,countryDDValue,state,phoneNumber);
			//			cscockpitCheckoutTabPage.clickSaveOfDeliveryAddressPopUpInCheckoutTab();
			//			cscockpitCheckoutTabPage.clickUseAsEnteredPopupOkayInCheckoutTab();
			//			s_assert.assertTrue(cscockpitCheckoutTabPage.getNewlyCreatedDeliveryAddressNameInCheckoutTab().contains(attendeeNewFirstName),"CSCockpit checkout tab newly created Delivery address name expected ="+ attendeeFirstName+ "and on UI = " +cscockpitCheckoutTabPage.getNewlyCreatedDeliveryAddressNameInCheckoutTab());

			//			// Add an new payment profile
			//			cscockpitCheckoutTabPage.clickAddNewPaymentAddressInCheckoutTab();
			//			cscockpitCheckoutTabPage.clickAddNewAddressLinkInPopUpInCheckoutTab();
			//			cscockpitCheckoutTabPage.enterPaymentDetailsInPopUpInCheckoutTab(cardNumber, newBillingProfileName, CVV, "09",cardExpYear, "VISA");
			//			cscockpitCheckoutTabPage.enterShippingDetailsInPopUpInCheckoutTab(attendeeFirstName, attendeeLastName,addressLine1, city, postalCode, countryDDValue, state, phoneNumber);
			//			cscockpitCheckoutTabPage.clickSaveOfShippingAddressPopUpInCheckoutTab();
			//			cscockpitCheckoutTabPage.clickSaveAddNewPaymentProfilePopUP();
			//			cscockpitCheckoutTabPage.enterCVVValueInCheckoutTab(TestConstants.SECURITY_CODE);
			//			cscockpitCheckoutTabPage.clickUseThisCardBtnInCheckoutTab();
			//			cscockpitCheckoutTabPage.clickPlaceOrderButtonInCheckoutTab();
			//			orderNumberOfOrderTab = cscockpitOrderTabPage.getOrderNumberInOrderTab();
			//			cscockpitOrderTabPage.clickCustomerTab();
			//			s_assert.assertTrue(cscockpitCustomerTabPage.getOrderTypeInCustomerTab(orderNumberOfOrderTab.split("\\-")[0].trim()).contains("Consultant Order"),"CSCockpit Customer tab Order type expected = Consultant Order and on UI = " +cscockpitCustomerTabPage.getOrderTypeInCustomerTab(orderNumberOfOrderTab.split("\\-")[0].trim()));
			//			driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			//			try{
			//				storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			//			}catch(Exception e){
			//				driver.get(driver.getStoreFrontURL()+"/"+driver.getCountry());
			//				storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			//			}	
			//			storeFrontConsultantPage.clickOnWelcomeDropDown();
			//			storeFrontOrdersPage =  storeFrontConsultantPage.clickOrdersLinkPresentOnWelcomeDropDown();
			//			s_assert.assertTrue(storeFrontOrdersPage.verifyOrdersPageIsDisplayed(),"Orders page has not been displayed");
			//			orderHistoryNumber = storeFrontOrdersPage.getFirstOrderNumberFromOrderHistory();
			//			storeFrontOrdersPage.clickOrderNumber(orderHistoryNumber);
			//			s_assert.assertTrue(orderHistoryNumber.contains(orderNumberOfOrderTab.split("\\-")[0].trim()),"CSCockpit Order number expected = "+orderNumberOfOrderTab.split("\\-")[0].trim()+" and on UI = " +orderHistoryNumber);
			//			s_assert.assertTrue(storeFrontOrdersPage.getShippingAddress().contains(attendeeNewFirstName),"CSCockpit Delivery Address expected = attendeeNewFirstName and on UI = " +storeFrontOrdersPage.getShippingAddress());
			//			logout();
			s_assert.assertAll();
		}
		
	//Hybris Project-1944:To verify Checkout Page UI while placing the order
	@Test 
	public void testVerifyCheckoutPageUIWhilePlacingOrder_1944(){
		String randomProductSequenceNumber = null;
		String SKUValue = null;
		String country = null;
		if(driver.getCountry().equalsIgnoreCase("ca")){
			country= TestConstants.COUNTRY_DD_VALUE_CA;
		} else if(driver.getCountry().equalsIgnoreCase("us")){
			country= TestConstants.COUNTRY_DD_VALUE_US;
		}else if(driver.getCountry().equalsIgnoreCase("au")){
			country= TestConstants.COUNTRY_DD_VALUE_AU;
		}

		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("CONSULTANT");
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(country);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		String randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		String firstName = cscockpitCustomerSearchTabPage.getfirstNameOfTheCustomerInCustomerSearchTab(randomCustomerSequenceNumber);
		cscockpitCustomerSearchTabPage.clickCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		String shippingAddress = cscockpitCustomerTabPage.getDefaultSelectedShippingAddressFromDropDown().split("\\,")[0];
		String shippingAddressFirstName = shippingAddress.split("\\ ")[0];
		cscockpitCustomerTabPage.clickPlaceOrderButtonInCustomerTab();
		cscockpitCartTabPage.selectValueFromSortByDDInCartTab("Price: High to Low");
		cscockpitCartTabPage.selectCatalogFromDropDownInCartTab();	
		randomProductSequenceNumber = String.valueOf(cscockpitCartTabPage.getRandomProductWithSKUFromSearchResult()); 
		SKUValue = cscockpitCartTabPage.getCustomerSKUValueInCartTab(randomProductSequenceNumber);
		cscockpitCartTabPage.searchSKUValueInCartTab(SKUValue);
		cscockpitCartTabPage.clickAddToCartBtnInCartTab();
		cscockpitCartTabPage.clickCheckoutBtnInCartTab();
		String fullName = cscockpitCheckoutTabPage.getNameFromCartSectionInCheckoutTab(firstName);
		s_assert.assertTrue(fullName.contains(firstName),"full name on UI in cart section"+fullName+"while expected "+firstName);
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyBasePriceIsPresentInCartSectionInCheckoutTab(), "Base price is not present in cart section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyTotalPriceIsPresentInCartSectionInCheckoutTab(), "Total price is not present in cart section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyEntryCVIsPresentInCartSectionInCheckoutTab(), "Entry CV is not present in cart section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyEntryQVIsPresentInCartSectionInCheckoutTab(), "Entry QV is not present in cart section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.getSelectedDeliveryAddressInCheckoutTab().trim().contains(shippingAddressFirstName),"selected delivery address on UI "+cscockpitCheckoutTabPage.getSelectedDeliveryAddressInCheckoutTab()+" does not contain "+shippingAddressFirstName);
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyNewAddressIsPresentInDeliverySectionInCheckoutTab(), "New address button is not present in delivery section of checkeout tab");
		if(driver.getCountry().equalsIgnoreCase("ca")){
			s_assert.assertTrue(cscockpitCheckoutTabPage.getSizeOfDeliveryModeDDValues().contains("3"),"CSCockpit checkout tab no of delivery mode expected = 3 and on UI = " +cscockpitCheckoutTabPage.getSizeOfDeliveryModeDDValues());
		}
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifySubtotalTxtIsPresentInTotalsSectionInCheckoutTab(), "Subtotal is not present in totals section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyDiscountTxtIsPresentInTotalsSectionInCheckoutTab(), "Discount is not present in totals section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyDeliverCostsTxtIsPresentInTotalsSectionInCheckoutTab(), "Delivery Costs is not present in totals section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyHandlingCostsTxtIsPresentInTotalsSectionInCheckoutTab(), "Handling Costs is not present in totals section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyTotalPriceTxtIsPresentInTotalsSectionInCheckoutTab(), "Total price is not present in totals section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyTotalCVTxtIsPresentInTotalsSectionInCheckoutTab(), "Total CV is not present in totals section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyTotalQVTxtIsPresentInTotalsSectionInCheckoutTab(), "Total QV is not present in totals section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyOrderNotesTextInOrderInfoSectionInCheckoutTab(), "Order notes text is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyOrderNotesTextAreaInOrderInfoSectionInCheckoutTab(), "Order notes text area is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyOrderNoteAddBtnInOrderInfoSectionInCheckoutTab(), "Order notes add button is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyNoPromotionsAppliedInAppliedPromotionsSectionInCheckoutTab(), "No promotion txt is not present in promotion section of checkeout tab");
		//payment Section
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyCreditCardNumberSectionInCheckoutTab(), "Credit card number section is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyCreditCardOwnerSectionInCheckoutTab(), "Credit card owner section is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyCreditCardTypeSectionInCheckoutTab(), "Credit card type section is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyCreditCardMonthSectionInCheckoutTab(), "Credit card month section is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyCreditCardValidToYearSectionInCheckoutTab(), "Credit card valid to year section is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyPaymentBillingAddressSectionInCheckoutTab(), "Billing address section is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyPaymentAmountSectionInCheckoutTab(), "Payment amount section is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyCreditCardCv2SectionInCheckoutTab(), "Credit Card Cv2 section is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyUseThisCardBtnInCheckoutTab(), "Use this card button is not present in order note info section of checkeout tab");
		//Place Order Section
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyPlaceOrderBtnIsPresentInCheckoutTab(), "Place order button is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyPerformSOOBtnIsPresentInCheckoutTab(), "Perform SOO button is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyTestOrderChkBoxIsPresentInCheckoutTab(), "Txt order checkbox is not present in order note info section of checkeout tab");
		s_assert.assertTrue(cscockpitCheckoutTabPage.verifyDoNotShipChkBoxIsPresentInCheckoutTab(), "Do Not Ship checkbox is not present in order note info section of checkeout tab");
		s_assert.assertAll();
	}

}
