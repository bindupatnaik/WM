package com.rf.test.website.storeFront.SKUValidation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.cscockpit.CSCockpitAutoshipCartTabPage;
import com.rf.pages.website.cscockpit.CSCockpitAutoshipTemplateTabPage;
import com.rf.pages.website.cscockpit.CSCockpitAutoshipTemplateUpdateTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCartTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCheckoutTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerSearchTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerTabPage;
import com.rf.pages.website.cscockpit.CSCockpitLoginPage;
import com.rf.pages.website.cscockpit.CSCockpitOrderTabPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.pages.website.storeFront.StoreFrontUpdateCartPage;
import com.rf.test.website.RFWebsiteBaseTest;

public class PCOrderValidationTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(PCOrderValidationTest.class.getName());

	private CSCockpitLoginPage cscockpitLoginPage;	
	private CSCockpitCheckoutTabPage cscockpitCheckoutTabPage;
	private CSCockpitCustomerSearchTabPage cscockpitCustomerSearchTabPage;
	private CSCockpitCustomerTabPage cscockpitCustomerTabPage;
	private CSCockpitOrderTabPage cscockpitOrderTabPage;
	private CSCockpitCartTabPage cscockpitCartTabPage;
	private CSCockpitAutoshipTemplateTabPage cscockpitAutoshipTemplateTabPage;
	private CSCockpitAutoshipCartTabPage cscockpitAutoshipCartTabPage;
	private CSCockpitAutoshipTemplateUpdateTabPage cscockpitAutoshipTemplateUpdateTabPage;

	public PCOrderValidationTest() {
		cscockpitLoginPage = new CSCockpitLoginPage(driver);
		cscockpitCheckoutTabPage = new CSCockpitCheckoutTabPage(driver);
		cscockpitCustomerSearchTabPage = new CSCockpitCustomerSearchTabPage(driver);
		cscockpitCustomerTabPage = new CSCockpitCustomerTabPage(driver);
		cscockpitOrderTabPage = new CSCockpitOrderTabPage(driver);
		cscockpitCartTabPage = new CSCockpitCartTabPage(driver);
		cscockpitAutoshipTemplateTabPage = new CSCockpitAutoshipTemplateTabPage(driver);
		cscockpitAutoshipCartTabPage = new CSCockpitAutoshipCartTabPage(driver);
		cscockpitAutoshipTemplateUpdateTabPage = new CSCockpitAutoshipTemplateUpdateTabPage(driver);
	}

	@Test(dataProvider="rfTestData")
	public void placeAdhocOrderForPCFromSF(String sNo, String catalogID, String SKU, String productName, String retailPrice, String pcPrice, String CV, String QV, String newEnrollment) throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String emailAddress=firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String unitPriceFromUI = null;
		String catalogFromUI = null;
		if(newEnrollment.equalsIgnoreCase("yes")){
			// New Enrollment from corp	
			storeFrontHomePage.addAProductToCartAndCheckout();
			storeFrontHomePage.enterNewPCDetails(firstName,lastName,emailAddress, password);
			storeFrontHomePage.enterMainAccountInfo(addressLine1,city, state, postalCode, phoneNumber);
			storeFrontHomePage.clickOnContinueWithoutSponsorLink();
			storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
			storeFrontHomePage.clickOnShippingAddressNextStepBtn();
			storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.clickOnBillingNextStepBtn();
			storeFrontHomePage.clickOnPCPerksTermsAndConditionsCheckBoxes();
			storeFrontHomePage.clickPlaceOrderBtn();
			s_assert.assertTrue(storeFrontHomePage.isPCEnrolledCongratsMessagePresent(), "'Welcome to Rodan + Fields PC Perks' message has not appeared");
			storeFrontHomePage.clickOnRodanAndFieldsLogo();
		}else{
			String pcEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
			storeFrontHomePage.loginAsPCUser(pcEmailID, password);
			Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application");
		}
		storeFrontHomePage.searchProduct(catalogID);
		storeFrontHomePage.addAProductToCartForSearchedProduct(catalogID);
		storeFrontHomePage.clickOnCheckoutButton();
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnDefaultBillingProfileEdit();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyOrderPlacedConfirmationMessage(), "Order has been not placed successfully");
		storeFrontUpdateCartPage.getOrderNumberAfterPlaceOrder();
		catalogFromUI = storeFrontUpdateCartPage.getSKUFromOrderConfirmation();
		unitPriceFromUI = storeFrontUpdateCartPage.getUnitPriceFromOrderConfirmationForPC();
		s_assert.assertTrue(catalogFromUI.contains(catalogID), "Expected catalog id "+catalogID+"but actual on UI is "+catalogFromUI);
		s_assert.assertTrue(unitPriceFromUI.contains(pcPrice), "Expected unit/pc price is "+pcPrice+"but actual on UI is "+unitPriceFromUI);
		s_assert.assertAll();
	}

	@Test(dataProvider="rfTestData")
	public void placePCPerksOrderFromSF(String sNo, String catalogID, String SKU, String productName, String retailPrice, String pcPrice, String CV, String QV, String newEnrollment) throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String emailAddress=firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String unitPriceFromUI = null;
		if(newEnrollment.equalsIgnoreCase("yes")){
			// New Enrollment from corp	
			storeFrontHomePage.addAProductToCartAndCheckout();
			storeFrontHomePage.enterNewPCDetails(firstName,lastName,emailAddress, password);
			storeFrontHomePage.enterMainAccountInfo(addressLine1,city, state, postalCode, phoneNumber);
			storeFrontHomePage.clickOnContinueWithoutSponsorLink();
			storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
			storeFrontHomePage.clickOnShippingAddressNextStepBtn();
			storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.clickOnBillingNextStepBtn();
			storeFrontHomePage.clickOnPCPerksTermsAndConditionsCheckBoxes();
			storeFrontHomePage.clickPlaceOrderBtn();
			s_assert.assertTrue(storeFrontHomePage.isPCEnrolledCongratsMessagePresent(), "'Welcome to Rodan + Fields PC Perks' message has not appeared");
			storeFrontHomePage.clickOnRodanAndFieldsLogo();
		}else{
			String pcEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
			storeFrontHomePage.loginAsPCUser(pcEmailID, password);
			Assert.assertTrue(storeFrontHomePage.hasPCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application");
		}
		storeFrontHomePage.searchProduct(catalogID);
		storeFrontHomePage.addAProductToPCPerksForSearchedProduct(catalogID);
		storeFrontUpdateCartPage.clickOnUpdateMoreInfoButton();
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		storeFrontHomePage.clickOnDefaultBillingProfileEdit();
		String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME+randomNum;
		storeFrontHomePage.enterEditedCardNumber(TestConstants.CARD_NUMBER);
		storeFrontHomePage.enterNewBillingNameOnCard(editedBillingProfileName+" "+lastName);
		storeFrontUpdateCartPage.selectNewBillingCardExpirationDate("OCT","2025");
		storeFrontHomePage.enterNewBillingSecurityCode(TestConstants.SECURITY_CODE);
		storeFrontHomePage.selectNewBillingCardAddress();
		storeFrontHomePage.clickOnSaveBillingProfile();
		storeFrontHomePage.clickOnBillingNextStepBtn();
		storeFrontHomePage.clickUpdateCartBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyCartUpdateMessage(), "cart is not updated!! ");
		int productNumber = storeFrontUpdateCartPage.getProductNumberFromSKUAtCRP(catalogID);
		// TODO need to as for CA there is no unit price
		unitPriceFromUI = storeFrontUpdateCartPage.getUnitPriceAtCRPThroughProductNumber(productNumber);
		// TODO Need to ask for assertion to verify product catalog we already getting the product number
		s_assert.assertTrue(unitPriceFromUI.contains(pcPrice), "Expected unit/pc price is "+pcPrice+"but actual on UI is "+unitPriceFromUI);
		s_assert.assertAll();
	}

	@Test(dataProvider="rfTestData")
	public void PlaceAdhocOrderForPCFromCSC(String sNo, String catalogID, String SKU, String productName, String retailPrice, String pcPrice, String CV, String QV) throws InterruptedException{
		String randomCustomerSequenceNumber = null;
		String basePriceFromUI = null;
		String cvFromOrderConfirmation = null;
		String qvFromOrderConfirmation = null;
		String country = null;
		if(driver.getCountry().equalsIgnoreCase("ca")){
			country= TestConstants.COUNTRY_DD_VALUE_CA;
		}else if(driver.getCountry().equalsIgnoreCase("au")){
			country= TestConstants.COUNTRY_DD_VALUE_AU;
		}
		String pcEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		driver.get(driver.getCSCockpitURL());
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(pcEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("PC");
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(country);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		cscockpitCustomerTabPage.clickPlaceOrderButtonInCustomerTab();
		cscockpitCartTabPage.selectCatalogFromDropDownInCartTab();	
		cscockpitCartTabPage.searchSKUValueInCartTab(catalogID);
		cscockpitCartTabPage.clickAddToCartBtnInCartTab();
		cscockpitCartTabPage.clickCheckoutBtnInCartTab();
		cscockpitCheckoutTabPage.clickAddNewPaymentAddressInCheckoutTab();
		cscockpitCheckoutTabPage.enterBillingInfo();
		cscockpitCheckoutTabPage.clickSaveAddNewPaymentProfilePopUP();
		cscockpitCheckoutTabPage.enterCVVValueInCheckoutTab(TestConstants.SECURITY_CODE);
		cscockpitCheckoutTabPage.clickUseThisCardBtnInCheckoutTab();
		cscockpitCheckoutTabPage.clickPlaceOrderButtonInCheckoutTab();
		assertTrue(cscockpitOrderTabPage.isOrderPlacedSuccessfully(), "Order is not placed successfully");
		cvFromOrderConfirmation = cscockpitOrderTabPage.getCVValue();
		qvFromOrderConfirmation = cscockpitOrderTabPage.getQVValue();
		basePriceFromUI = cscockpitOrderTabPage.getBasePriceFromSKU(catalogID);
		s_assert.assertTrue(cscockpitOrderTabPage.isProductSKUPresent(catalogID), "Product SKU value is not present after placed the order");
		s_assert.assertTrue(basePriceFromUI.contains(pcPrice), "Expected base/PC price is "+pcPrice+"but actual on UI is "+basePriceFromUI);
		s_assert.assertTrue(qvFromOrderConfirmation.contains(QV),"Expected QV value at order confirmation page "+QV+" actual on UI is"+qvFromOrderConfirmation);
		s_assert.assertTrue(cvFromOrderConfirmation.contains(CV),"Expected CV value at order confirmation page "+CV+" actual on UI is"+cvFromOrderConfirmation);
		s_assert.assertAll();
	}


	@Test(dataProvider="rfTestData")
	public void placePCPerksOrderFromCSC(String sNo, String catalogID, String SKU, String productName, String retailPrice, String pcPrice, String CV, String QV) throws InterruptedException{
		String randomCustomerSequenceNumber = null;
		String cvFromUI = null;
		String qvFromUI = null;
		String basePriceFromUI = null;
		boolean isProductPresent = false;
		String productQuantity = null;
		String productCount = null;
		String updatedQuantity = null;
		String country = null;
		if(driver.getCountry().equalsIgnoreCase("ca")){
			country= TestConstants.COUNTRY_DD_VALUE_CA;
		}else if(driver.getCountry().equalsIgnoreCase("au")){
			country= TestConstants.COUNTRY_DD_VALUE_AU;
		}
		String pcEmailID = QueryUtils.getRandomActivePCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		driver.get(driver.getCSCockpitURL());
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(pcEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("PC");
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(country);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		cscockpitCustomerTabPage.getAndClickAutoshipIDHavingTypeAsPCAutoshipAndStatusIsPending();
		cscockpitAutoshipTemplateTabPage.clickEditTemplateLinkInAutoshipTemplateTab();
		isProductPresent = cscockpitAutoshipTemplateTabPage.isProductSKUPresent(catalogID);
		if(isProductPresent){
			productCount = cscockpitAutoshipTemplateTabPage.getProductNumberFromSKUAtAutoship(catalogID);
			productQuantity = cscockpitAutoshipTemplateTabPage.getQuantityOfProductInAutoshipTemplateTabPage(productCount);
			updatedQuantity = cscockpitAutoshipTemplateTabPage.updateProductQuantityByAdd(productQuantity, "1");
		}
		cscockpitAutoshipTemplateTabPage.clickAddMoreLinesLinkInAutoShipTemplateTab();
		cscockpitAutoshipCartTabPage.searchSKUValueInCartTab(catalogID);
		cscockpitAutoshipCartTabPage.clickAddToCartBtnInCartTab();
		cscockpitAutoshipCartTabPage.clickCheckoutBtnInCartTab();
		cscockpitAutoshipTemplateUpdateTabPage.clickAddNewPaymentAddressInCheckoutTab();
		cscockpitAutoshipTemplateUpdateTabPage.enterBillingInfo();
		cscockpitAutoshipTemplateUpdateTabPage.clickSaveAddNewPaymentProfilePopUP();
		cscockpitAutoshipTemplateUpdateTabPage.enterCVVValueInCheckoutTab(TestConstants.SECURITY_CODE);
		cscockpitAutoshipTemplateUpdateTabPage.clickUseThisCardBtnInCheckoutTab();
		cscockpitAutoshipTemplateUpdateTabPage.clickUpdateAutoshipTemplateInAutoshipTemplateUpdateTab();
		cscockpitAutoshipTemplateTabPage.clickRunNowButtonOnAutoshipTemplateTab();
		String confirmationMsgOfRunNow = cscockpitAutoshipTemplateTabPage.getConfirmMessageAfterClickOnRunNowBtn();
		String orderNumberFromMsg = cscockpitAutoshipTemplateTabPage.getOrderNumberFromConfirmationMsg(confirmationMsgOfRunNow);
		cscockpitAutoshipTemplateTabPage.clickOkConfirmMessagePopUp();
		cscockpitAutoshipTemplateTabPage.clickCustomerTab();
		cscockpitCustomerTabPage.clickOrderNumberInCustomerOrders(orderNumberFromMsg);
		qvFromUI = cscockpitOrderTabPage.getProductDetailsFromSKU(catalogID, "QV");
		cvFromUI = cscockpitOrderTabPage.getProductDetailsFromSKU(catalogID, "CV");
		basePriceFromUI = cscockpitOrderTabPage.getBasePriceFromSKU(catalogID);
		if(isProductPresent){
			QV = cscockpitOrderTabPage.updateProductQuantityByMultiply(QV, updatedQuantity);
			CV = cscockpitOrderTabPage.updateProductQuantityByMultiply(CV, updatedQuantity);
		}
		s_assert.assertTrue(cscockpitOrderTabPage.isProductSKUPresent(catalogID), "Product SKU value is not present after placed the order");
		s_assert.assertTrue(basePriceFromUI.contains(pcPrice), "Expected base/consultant price is "+pcPrice+"but actual on UI is "+basePriceFromUI);
		s_assert.assertTrue(qvFromUI.contains(QV),"Expected QV value at order confirmation page "+QV+" actual on UI is"+qvFromUI);
		s_assert.assertTrue(cvFromUI.contains(CV),"Expected CV value at order confirmation page "+CV+" actual on UI is"+cvFromUI);
		s_assert.assertAll();
	}

}
