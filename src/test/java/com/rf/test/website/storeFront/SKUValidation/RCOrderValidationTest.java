package com.rf.test.website.storeFront.SKUValidation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.cscockpit.CSCockpitCartTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCheckoutTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerSearchTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerTabPage;
import com.rf.pages.website.cscockpit.CSCockpitLoginPage;
import com.rf.pages.website.cscockpit.CSCockpitOrderTabPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.pages.website.storeFront.StoreFrontUpdateCartPage;
import com.rf.test.website.RFWebsiteBaseTest;

public class RCOrderValidationTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(RCOrderValidationTest.class.getName());

	private CSCockpitLoginPage cscockpitLoginPage;	
	private CSCockpitCheckoutTabPage cscockpitCheckoutTabPage;
	private CSCockpitCustomerSearchTabPage cscockpitCustomerSearchTabPage;
	private CSCockpitCustomerTabPage cscockpitCustomerTabPage;
	private CSCockpitOrderTabPage cscockpitOrderTabPage;
	private CSCockpitCartTabPage cscockpitCartTabPage;
	
	public RCOrderValidationTest() {
		cscockpitLoginPage = new CSCockpitLoginPage(driver);
		cscockpitCheckoutTabPage = new CSCockpitCheckoutTabPage(driver);
		cscockpitCustomerSearchTabPage = new CSCockpitCustomerSearchTabPage(driver);
		cscockpitCustomerTabPage = new CSCockpitCustomerTabPage(driver);
		cscockpitOrderTabPage = new CSCockpitOrderTabPage(driver);
		cscockpitCartTabPage = new CSCockpitCartTabPage(driver);
	}
	
	@Test(dataProvider="rfTestData")
	public void placeAdhocOrderForRCFromSF(String sNo, String catalogID, String SKU, String productName, String retailPrice, String consultantPrice, String CV, String QV, String newEnrollment) throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String emailAddress=firstName+TestConstants.EMAIL_ADDRESS_SUFFIX;
		String unitPriceFromUI = null;
		String catalogFromUI = null;
		if(newEnrollment.equalsIgnoreCase("yes")){
			storeFrontHomePage.addAProductToCartAndCheckout();
			storeFrontHomePage.enterNewRCDetails(firstName,lastName,emailAddress, password);
			storeFrontHomePage.enterMainAccountInfo(addressLine1,city, state, postalCode, phoneNumber);
			storeFrontHomePage.clickOnContinueWithoutSponsorLink();
			storeFrontHomePage.clickOnNextButtonAfterSelectingSponsor();
			storeFrontHomePage.clickOnShippingAddressNextStepBtn();
			storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.clickOnBillingNextStepBtn();
			storeFrontHomePage.clickPlaceOrderBtn();
			s_assert.assertTrue(storeFrontHomePage.isOrderPlacedSuccessfully(), "Order Not placed successfully");
			storeFrontHomePage.clickOnRodanAndFieldsLogo();
		}else{
		String rcEmailID = QueryUtils.getRandomActiveRCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId);
		storeFrontHomePage.loginAsConsultant(rcEmailID, password);
		Assert.assertTrue(storeFrontHomePage.hasRCLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId),"Not able to login to the application");
		}
		storeFrontHomePage.searchProduct(catalogID);
		storeFrontHomePage.addAProductToCartForSearchedProduct(catalogID);
		storeFrontHomePage.clickOnCheckoutButton();
		storeFrontUpdateCartPage.clickOnContinueWithoutSponsorLink();
		storeFrontUpdateCartPage.clickOnNextButtonAfterSelectingSponsor();
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnDefaultBillingProfileEdit();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyOrderPlacedConfirmationMessage(), "Order has been not placed successfully");
		catalogFromUI = storeFrontUpdateCartPage.getSKUFromOrderConfirmation();
		unitPriceFromUI = storeFrontUpdateCartPage.getUnitPriceFromOrderConfirmationForPC();
		s_assert.assertTrue(catalogFromUI.contains(catalogID), "Expected catalog id "+catalogID+"but actual on UI is "+catalogFromUI);
		s_assert.assertTrue(unitPriceFromUI.contains(retailPrice), "Expected unit/retail price is "+retailPrice+"but actual on UI is "+unitPriceFromUI);
		s_assert.assertAll();
	}
	
	@Test(dataProvider="rfTestData")
	public void PlaceAdhocOrderForRCFromCSC(String sNo, String catalogID, String SKU, String productName, String retailPrice, String consultantPrice, String CV, String QV) throws InterruptedException{
		String randomCustomerSequenceNumber = null;
		String cvFromOrderConfirmation = null;
		String qvFromOrderConfirmation = null;
		String basePriceFromUI = null;
		String country = null;
		if(driver.getCountry().equalsIgnoreCase("ca")){
			country= TestConstants.COUNTRY_DD_VALUE_CA;
		}else if(driver.getCountry().equalsIgnoreCase("au")){
			country= TestConstants.COUNTRY_DD_VALUE_AU;
		}
		String rcEmailID = QueryUtils.getRandomActiveRCFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_RC_HAVING_ORDERS_RFO,countryId);
		driver.get(driver.getCSCockpitURL());
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(rcEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("RETAIL");
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
		s_assert.assertTrue(basePriceFromUI.contains(retailPrice), "Expected base/retail price is "+retailPrice+"but actual on UI is "+basePriceFromUI);
		s_assert.assertTrue(cscockpitOrderTabPage.isProductSKUPresent(catalogID), "Product SKU value is not present after placed the order");
		s_assert.assertTrue(qvFromOrderConfirmation.contains(QV),"Expected QV value at order confirmation page "+QV+" actual on UI is"+qvFromOrderConfirmation);
		s_assert.assertTrue(cvFromOrderConfirmation.contains(CV),"Expected CV value at order confirmation page "+CV+" actual on UI is"+cvFromOrderConfirmation);
		s_assert.assertAll();
	}

}
