package com.rf.test.website.storeFront.hybris.billingAndShipping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.QueryUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.RFWebsiteBaseTest;

public class AddEditShippingOnShippingPageTest extends RFBillingShippingPageWebsiteBaseTest{

	private static final Logger logger = LogManager
			.getLogger(AddEditShippingOnShippingPageTest.class.getName());

	private int randomNum; 	
	//	List<Map<String, Object>> randomConsultantList =  null;
	String consultantEmailID = null;
	
	@BeforeMethod
	public void loginAndNavigateToShippingPage(){
		s_assert = new SoftAssert();
		navigateToStoreFrontBaseURL();
		getUserAndLogin(TestConstants.USER_TYPE_CONSULTANT);
		navigateToShippingPage();
	}
	
	//qTest ID - 433 Add shipping address on 'Shipping Profile' page
	//qTest ID - 442 Edit shipping address on 'Shipping Profile' page
	@Test(groups="shippingPageCorpCons",priority=1)
	public void testAddAndEditShippingAddressOnShippingProfilePage_433q_442q() {
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		storeFrontShippingInfoPage.clickAddNewShippingProfileLink();
		String newShippingAddressName = TestConstants.ADDRESS_NAME+randomNum;
		storeFrontShippingInfoPage.enterNewShippingAddressName(newShippingAddressName+" "+lastName);
		storeFrontShippingInfoPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontShippingInfoPage.enterNewShippingAddressCity(city);
		storeFrontShippingInfoPage.selectNewShippingAddressState(state);
		storeFrontShippingInfoPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontShippingInfoPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontShippingInfoPage.selectUseThisShippingProfileFutureAutoshipChkbox();
		storeFrontShippingInfoPage.clickOnSaveShippingProfile();
		s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(newShippingAddressName), "Newly Added Shipping address is not listed on Shipping profile page");
		s_assert.assertTrue(storeFrontShippingInfoPage.isAutoshipOrderAddressTextPresent(newShippingAddressName), "Autoship order text is not present under the newly added Shipping Address when future autoship checkbox is selected");

		// Edit the shipping address
		int randomNum1 = CommonUtils.getRandomNum(10000, 1000000);
		String editShippingAddressName = TestConstants.ADDRESS_NAME+randomNum1;
		storeFrontShippingInfoPage.clickOnEditOfShippingProfile(newShippingAddressName);
		storeFrontShippingInfoPage.enterNewShippingAddressName(editShippingAddressName+" "+lastName);
		storeFrontShippingInfoPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontShippingInfoPage.enterNewShippingAddressCity(city);
		storeFrontShippingInfoPage.selectNewShippingAddressState(state);
		storeFrontShippingInfoPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontShippingInfoPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontShippingInfoPage.selectUseThisShippingProfileFutureAutoshipChkbox();
		storeFrontShippingInfoPage.clickOnSaveShippingProfile();
		s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(editShippingAddressName), "Newly Edited Shipping address is not selected listed on Shipping profile page");
		s_assert.assertTrue(storeFrontShippingInfoPage.isAutoshipOrderAddressTextPresent(editShippingAddressName), "Autoship order text not present under the newly edited Shipping Address");
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontCartAutoShipPage = storeFrontConsultantPage.clickEditCrpLinkPresentOnWelcomeDropDown();
		storeFrontUpdateCartPage = storeFrontCartAutoShipPage.clickUpdateMoreInfoLink();
		storeFrontUpdateCartPage.clickOnEditShipping();
		s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(editShippingAddressName), "Newly added/edited Shipping address NOT selected in update cart under shipping section");
		s_assert.assertAll();		
	}

	//qTest ID - 2896 Popup to update autoship shipping profile on changing default selection
	@Test(groups="shippingPageCorpCons",priority=5)
	public void testPopUpToUpdateAutoshipShippingProfileOnChangingDefaultSelection_2896q() throws InterruptedException			 {
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		//	storeFrontConsultantPage = storeFrontHomePage.loginAsConsultant(consultantEmailID, password);
		storeFrontShippingInfoPage.clickAddNewShippingProfileLink();
		String newShippingAddressName = TestConstants.ADDRESS_NAME+randomNum;
		storeFrontShippingInfoPage.enterNewShippingAddressName(newShippingAddressName+" "+lastName);
		storeFrontShippingInfoPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontShippingInfoPage.enterNewShippingAddressCity(city);
		storeFrontShippingInfoPage.selectNewShippingAddressState(state);
		storeFrontShippingInfoPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontShippingInfoPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontShippingInfoPage.clickOnSaveShippingProfile();
		//change default shipping profile selection and validate Update AutoShip PopUp
		storeFrontShippingInfoPage.makeShippingProfileAsDefault(newShippingAddressName);
		s_assert.assertTrue(storeFrontShippingInfoPage.validateUpdateAutoShipPopUpPresent(), "Update AutoShip PopUp is not present!!");
		s_assert.assertAll();
	}

	// qTest ID - 431 Add Multiple shippiing address from myaccounts
	@Test(groups="shippingPageCorpCons",priority=10)
	public void testAddMultipeShippingAddressFromMyAccounts_431q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int i=0;
		String profileName=null;
		String lastName = "lN";
		profileName=TestConstants.NEW_SHIPPING_PROFILE_FIRST_NAME_CA+randomNum;
		for(i=0;i<2;i++){
			storeFrontShippingInfoPage.clickAddNewShippingProfileLink();
			storeFrontShippingInfoPage.enterNewShippingAddressName(profileName+i+" "+lastName);
			storeFrontShippingInfoPage.enterNewShippingAddressLine1(addressLine1);
			storeFrontShippingInfoPage.enterNewShippingAddressCity(city);
			storeFrontShippingInfoPage.selectNewShippingAddressState(state);
			storeFrontShippingInfoPage.enterNewShippingAddressPostalCode(postalCode);
			storeFrontShippingInfoPage.enterNewShippingAddressPhoneNumber(phoneNumber);
			storeFrontShippingInfoPage.clickOnSaveShippingProfile();
			s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(profileName+i),"New Created Shipping Profile is not present on shipping info page");
		}
		i=1;
		storeFrontConsultantPage.hoverOnShopLinkAndClickAllProductsLinks();
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		storeFrontUpdateCartPage.clickOnCheckoutButton();
		s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(profileName+"0"),"Newly created shipping address is not present on shipment section during checkout");
		s_assert.assertTrue(storeFrontUpdateCartPage.isShippingAddressPresent(profileName+i),"Newly created shipping address is not present on shipment section during checkout");
		storeFrontUpdateCartPage.clickOnNewShipToThisAddressRadioButton(profileName+"0");
		storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
		storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
		storeFrontUpdateCartPage.clickPlaceOrderBtn();
		s_assert.assertTrue(storeFrontUpdateCartPage.isNewEditedShippingProfileIsPresentOnOrderConfirmationPage(profileName+"0"),"New Edited Shipping Profile is not Present by default on Order Summary page");
		s_assert.assertTrue(storeFrontHomePage.isOrderPlacedSuccessfully(),"Order is not placed successfully");
		storeFrontConsultantPage = storeFrontUpdateCartPage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontOrdersPage = storeFrontConsultantPage.clickOrdersLinkPresentOnWelcomeDropDown();
		storeFrontOrdersPage.clickOnFirstAdHocOrder();
		s_assert.assertTrue(storeFrontOrdersPage.isShippingAddressContainsName(profileName+"0"),"AdHoc Orders Page do not contain the newly edited shipping address");
		s_assert.assertAll();
	}

	//qTest ID-450 Delete shipping address on 'Shipping Profile' page
	@Test(groups="shippingPageCorpCons")
	public void testDeleteShippingProfileFromShipingProfilePage_450q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String lastName = "lN";
		storeFrontShippingInfoPage.clickAddNewShippingProfileLink();
		String newShippingAddressName = TestConstants.ADDRESS_NAME+randomNum;
		storeFrontShippingInfoPage.enterNewShippingAddressName(newShippingAddressName+" "+lastName);
		storeFrontShippingInfoPage.enterNewShippingAddressLine1(addressLine1);
		storeFrontShippingInfoPage.enterNewShippingAddressCity(city);
		storeFrontShippingInfoPage.selectNewShippingAddressState(state);
		storeFrontShippingInfoPage.enterNewShippingAddressPostalCode(postalCode);
		storeFrontShippingInfoPage.enterNewShippingAddressPhoneNumber(phoneNumber);
		storeFrontShippingInfoPage.clickOnSaveShippingProfile();
		s_assert.assertTrue(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(newShippingAddressName), "Newly Added Shipping address is not listed on Shipping profile page");
		storeFrontShippingInfoPage.clickOnDeleteOfBillingProfile(newShippingAddressName);
		s_assert.assertFalse(storeFrontShippingInfoPage.isShippingAddressPresentOnShippingPage(newShippingAddressName), newShippingAddressName+" shipping profile is not deleted from the page");
		s_assert.assertAll();
	}

}