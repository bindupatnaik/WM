package com.rf.test.website.storeFront.SKUValidation;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
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
import com.rf.pages.website.cscockpit.CSCockpitOrderSearchTabPage;
import com.rf.pages.website.cscockpit.CSCockpitOrderTabPage;
import com.rf.pages.website.storeFront.StoreFrontConsultantPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.pages.website.storeFront.StoreFrontOrdersPage;
import com.rf.pages.website.storeFront.StoreFrontPCUserPage;
import com.rf.pages.website.storeFront.StoreFrontRCUserPage;
import com.rf.pages.website.storeFront.StoreFrontUpdateCartPage;
import com.rf.test.website.RFWebsiteBaseTest;

public class ConsultantOrderValidationTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(ConsultantOrderValidationTest.class.getName());

	private CSCockpitLoginPage cscockpitLoginPage;	
	private CSCockpitCheckoutTabPage cscockpitCheckoutTabPage;
	private CSCockpitCustomerSearchTabPage cscockpitCustomerSearchTabPage;
	private CSCockpitCustomerTabPage cscockpitCustomerTabPage;
	private CSCockpitOrderTabPage cscockpitOrderTabPage;
	private CSCockpitCartTabPage cscockpitCartTabPage;
	private CSCockpitAutoshipTemplateTabPage cscockpitAutoshipTemplateTabPage;
	private CSCockpitAutoshipCartTabPage cscockpitAutoshipCartTabPage;
	private CSCockpitAutoshipTemplateUpdateTabPage cscockpitAutoshipTemplateUpdateTabPage;

	public ConsultantOrderValidationTest() {
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

	@Test(dataProvider="rfTestData",enabled=false)
	public void placeAdhocOrderForConFromSF(String sNo, String catalogID, String SKU, String productName, String retailPrice, String consultantPrice, String CV, String QV, String newEnrollment) throws InterruptedException{
		System.out.println("product name is "+productName);
		String qvFromOrderReview = null;
		String qvFromOrderConfirmation = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		String enrollmentType = TestConstants.EXPRESS_ENROLLMENT;
		String regimenName = TestConstants.REGIMEN_NAME_REDEFINE;
		String kitName = TestConstants.KIT_NAME_PERSONAL;
		String unitPriceFromUI = null;
		String catalogFromUI = null;
		if(newEnrollment.equalsIgnoreCase("yes")){
			// New Enrollment from corp	
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();  
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.enterUserInformationForEnrollment(kitName,regimenName,enrollmentType, consultantFirstName, consultantLastName+randomNum,  password, addressLine1, city,state, postalCode, phoneNumber);
			storeFrontHomePage.clickNextButton();
			storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
			storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
			storeFrontHomePage.clickNextButtonWithoutPopUp();
			storeFrontHomePage.selectAllTermsAndConditionsChkBox();
			storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
			storeFrontHomePage.clickOnConfirmAutomaticPayment();
			s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
			storeFrontHomePage.clickOnRodanAndFieldsLogo();

		}else{
			String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
			storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application");
		}
		storeFrontHomePage.searchProduct(catalogID);
		storeFrontHomePage.addAProductToCartForSearchedProduct(catalogID);
		storeFrontHomePage.clickOnCheckoutButton();
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnDefaultBillingProfileEdit();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn();
		qvFromOrderReview = storeFrontUpdateCartPage.getTotalQV();
		s_assert.assertTrue(qvFromOrderReview.contains(QV),"Expected QV value at order review page "+QV+" actual on UI is"+qvFromOrderReview);
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.verifyOrderPlacedConfirmationMessage(), "Order has been not placed successfully");
		storeFrontUpdateCartPage.getOrderNumberAfterPlaceOrder();
		catalogFromUI = storeFrontUpdateCartPage.getSKUFromOrderConfirmation();
		unitPriceFromUI = storeFrontUpdateCartPage.getUnitPriceFromOrderConfirmation();
		qvFromOrderConfirmation = storeFrontUpdateCartPage.getTotalQV();
		s_assert.assertTrue(catalogFromUI.contains(catalogID), "Expected catalog id "+catalogID+"but actual on UI is "+catalogFromUI);
		s_assert.assertTrue(unitPriceFromUI.contains(consultantPrice), "Expected unit/consultant price is "+consultantPrice+"but actual on UI is "+unitPriceFromUI);
		s_assert.assertTrue(qvFromOrderConfirmation.contains(QV),"Expected QV value at order confirmation page "+QV+" actual on UI is"+qvFromOrderConfirmation);
		s_assert.assertAll();
	}


	@Test(dataProvider="rfTestData")
	public void placeCRPOrderForConFromSF(String sNo, String catalogID, String SKU, String productName, String retailPrice, String consultantPrice, String CV, String QV, String newEnrollment) throws InterruptedException{
		String qvFromOrderConfirmation = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		String unitPriceFromUI = null;
		String firstName=TestConstants.FIRST_NAME+randomNum;
		String socialInsuranceNumber = String.valueOf(CommonUtils.getRandomNum(100000000, 999999999));
		if(newEnrollment.equalsIgnoreCase("yes")){
			// New Enrollment from corp	
			storeFrontHomePage.hoverOnBecomeAConsultantAndClickEnrollNowLink();  
			storeFrontHomePage.searchCID(sponsor);
			storeFrontHomePage.mouseHoverSponsorDataAndClickContinue();
			storeFrontHomePage.enterUserInformationForEnrollment(kitName,regimenName,enrollmentType, consultantFirstName, consultantLastName+randomNum,  password, addressLine1, city,state, postalCode, phoneNumber);
			storeFrontHomePage.clickNextButton();
			storeFrontHomePage.enterBillingDetails(cardNumber, firstName+" "+lastName, cardExpMonth, cardExpYear, CVV);
			storeFrontHomePage.enterSocialInsuranceNumber(socialInsuranceNumber);
			storeFrontHomePage.enterNameAsItAppearsOnCard(TestConstants.FIRST_NAME);
			storeFrontHomePage.clickNextButtonWithoutPopUp();
			storeFrontHomePage.selectAllTermsAndConditionsChkBox();
			storeFrontHomePage.clickOnChargeMyCardAndEnrollMeBtn();
			storeFrontHomePage.clickOnConfirmAutomaticPayment();
			s_assert.assertTrue(storeFrontHomePage.verifyCongratsMessage(), "Congrats Message is not visible");
			storeFrontHomePage.clickOnRodanAndFieldsLogo();

		}else{

			String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
			storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
			Assert.assertTrue(storeFrontHomePage.hasConsultantLoggedInSuccessfully(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),"Not able to login to the application");
		}
		storeFrontHomePage.searchProduct(catalogID);
		storeFrontHomePage.addAProductToAutoshipForSearchedProduct(catalogID);
		storeFrontUpdateCartPage.clickOnUpdateMoreInfoButton();
		//storeFrontUpdateCartPage.clickOnNextStepBtnShippingAddress();
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
		unitPriceFromUI = storeFrontUpdateCartPage.getUnitPriceAtCRPThroughProductNumber(productNumber);
		qvFromOrderConfirmation = storeFrontUpdateCartPage.getProductDetailsAtCRPThroughProductNumber(productNumber);
		s_assert.assertTrue(unitPriceFromUI.contains(consultantPrice), "Expected unit/consultant price is "+consultantPrice+"but actual on UI is "+unitPriceFromUI);
		s_assert.assertTrue(qvFromOrderConfirmation.contains(QV),"Expected QV value at order confirmation page "+QV+" actual on UI is"+qvFromOrderConfirmation);
		s_assert.assertAll();
	}

	@Test(dataProvider="rfTestData")
	public void placeAdhocOrderForConFromCSC(String sNo, String catalogID, String SKU, String productName, String retailPrice, String consultantPrice, String CV, String QV) throws InterruptedException{
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
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		driver.get(driver.getCSCockpitURL());
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(consultantEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("CONSULTANT");
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
		s_assert.assertTrue(basePriceFromUI.contains(consultantPrice), "Expected base/consultant price is "+consultantPrice+"but actual on UI is "+basePriceFromUI);
		s_assert.assertTrue(qvFromOrderConfirmation.contains(QV),"Expected QV value at order confirmation page "+QV+" actual on UI is"+qvFromOrderConfirmation);
		s_assert.assertTrue(cvFromOrderConfirmation.contains(CV),"Expected CV value at order confirmation page "+CV+" actual on UI is"+cvFromOrderConfirmation);
		s_assert.assertAll();
	}

	@Test(dataProvider="rfTestData")
	public void placeCRPOrderForConFromCSC(String sNo, String catalogID, String SKU, String productName, String retailPrice, String consultantPrice, String CV, String QV) throws InterruptedException{
		String randomCustomerSequenceNumber = null;
		String basePriceFromUI = null;
		RFO_DB = driver.getDBNameRFO();
		String cvFromUI = null;
		String qvFromUI = null;
		String country = null;
		boolean isProductPresent = false;
		String productQuantity = null;
		String productCount = null;
		String updatedQuantity = null;
		if(driver.getCountry().equalsIgnoreCase("ca")){
			country= TestConstants.COUNTRY_DD_VALUE_CA;
		}else if(driver.getCountry().equalsIgnoreCase("au")){
			country= TestConstants.COUNTRY_DD_VALUE_AU;
		}
		String consultantEmailID = QueryUtils.getRandomActiveConsultantFromDB(RFO_DB, DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		driver.get(driver.getCSCockpitURL());
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(consultantEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("CONSULTANT");
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(country);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		cscockpitCustomerTabPage.getAndClickAutoshipIDHavingTypeAsCRPAutoshipAndStatusIsPending();
		cscockpitAutoshipTemplateTabPage.clickEditAutoshiptemplate();
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
		s_assert.assertTrue(basePriceFromUI.contains(consultantPrice), "Expected base/consultant price is "+consultantPrice+"but actual on UI is "+basePriceFromUI);
		s_assert.assertTrue(qvFromUI.contains(QV),"Expected QV value at order confirmation page "+QV+" actual on UI is"+qvFromUI);
		s_assert.assertTrue(cvFromUI.contains(CV),"Expected CV value at order confirmation page "+CV+" actual on UI is"+cvFromUI);
		s_assert.assertAll();
	}

}
