package com.rf.test.website.storeFront.hybris.billingAndShipping;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;

public class AddEditBillingOnBillingPageTest extends RFBillingShippingPageWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(AddEditBillingOnBillingPageTest.class.getName());

	private int randomNum; 	
	List<Map<String, Object>> randomConsultantList =  null;
	String consultantEmailID = null;
	String accountID = null;
	
	@BeforeMethod
	public void loginAndNavigateToBillingPage(){
		s_assert = new SoftAssert();
		navigateToStoreFrontBaseURL();
		getUserAndLogin(TestConstants.USER_TYPE_CONSULTANT);
		navigateToBillingPage();
	}

	// Hybris Phase 2-2041 :: Version : 1 :: Add new billing profile on 'Billing Profile' page
	// Hybris Phase 2-2047 :: Version : 1 :: Edit billing profile on 'Billing Profile' page
	@Test(groups="billingPageCorpCons",priority=1)
	public void testAddAndEditNewBillingProfileOnBillingProfilePage_404q_419q() throws InterruptedException, SQLException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName=TestConstants.FIRST_NAME+randomNum;
		String lastName = "lN";
		String defaultBillingProfileName = null;
		int totalBillingProfiles = storeFrontBillingInfoPage.getTotalBillingAddressesDisplayed();
		if(totalBillingProfiles>1){
			defaultBillingProfileName = storeFrontBillingInfoPage.getDefaultSelectedBillingAddressName();
		}
		else{
			defaultBillingProfileName = storeFrontBillingInfoPage.getFirstBillingProfileName();
		}
		logger.info("Default Billing profile is "+defaultBillingProfileName);
		storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
		storeFrontBillingInfoPage.selectUseThisBillingProfileFutureAutoshipChkbox();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, newBillingProfileName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(newBillingProfileName),"Newly added Billing profile is NOT listed on the billing info page");
		s_assert.assertFalse(storeFrontBillingInfoPage.isDefaultBillingAddressSelected(newBillingProfileName),"Newly created billing profile is DEFAULT selected on the billing info page when we add a new billing info");
		s_assert.assertTrue(storeFrontBillingInfoPage.isDefaultBillingAddressSelected(defaultBillingProfileName),"Old Default billing profile is not DEFAULT selected on the billing info page when we add a new billing info");

		//Edit the newly added billing profile
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		storeFrontBillingInfoPage.clickOnEditOfBillingProfile(newBillingProfileName+" "+lastName);
		storeFrontBillingInfoPage.enterNewBillingNameOnCard(editedBillingProfileName+" "+lastName);
		storeFrontBillingInfoPage.selectUseThisBillingProfileFutureAutoshipChkbox();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, editedBillingProfileName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly edited Billing profile is NOT listed on the billing info page when we edit the billing info");
		s_assert.assertTrue(storeFrontBillingInfoPage.isDefaultBillingAddressSelected(defaultBillingProfileName),"Already Default billing profile is not DEFAULT selected on billing info page when we edit the billing info");
		
		storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		s_assert.assertTrue(storeFrontHomePage.isCartPageDisplayed(), "Cart page is not displayed");
		storeFrontHomePage.clickOnCheckoutButton();
		storeFrontHomePage.clickOnShippingAddressNextStepBtn();
		s_assert.assertTrue(storeFrontHomePage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly/Edited Billing profile is NOT listed on the Adhoc cart");
		s_assert.assertTrue(storeFrontHomePage.isBillingProfileIsSelectedByDefault(defaultBillingProfileName),"Old Default billing profile is not DEFAULT selected on the Adhoc cart");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontCartAutoShipPage = storeFrontConsultantPage.clickEditCrpLinkPresentOnWelcomeDropDown();
		storeFrontUpdateCartPage = storeFrontCartAutoShipPage.clickUpdateMoreInfoLink();
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		s_assert.assertTrue(storeFrontUpdateCartPage.isNewBillingProfileIsSelectedByDefault(editedBillingProfileName),"Newly/Edited Billing Profile is not selected by default on autoship cart page");
		s_assert.assertAll();  
	}

	//QTest ID TC-412 Add new billing profile | My Account | checkbox UN-CHECKED
	//QTest ID TC-423 Edit billing profile on 'Billing Profile' page | checkbox NOT checked
	// Hybris Phase 2-2341:Add new billing profile | My Account | checkbox UN-CHECKED
	// Hybris Phase 2-2342:Edit billing profile | My Account | Not check
	@Test(groups="billingPageCorpCons",priority=2)
	public void testAddAndEditBillingProfileMyAccountFutureAutoshipCheckboxNotChecked_412q_423q() throws InterruptedException{
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		String lastName = "lN";
		String defaultBillingProfileName = null;		
		int totalBillingProfiles = storeFrontBillingInfoPage.getTotalBillingAddressesDisplayed();
		if(totalBillingProfiles>1){
			defaultBillingProfileName = storeFrontBillingInfoPage.getDefaultSelectedBillingAddressName();
		}
		else{
			defaultBillingProfileName = storeFrontBillingInfoPage.getFirstBillingProfileName();
		}
		logger.info("Default Billing profile is "+defaultBillingProfileName);
		storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, newBillingProfileName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(newBillingProfileName),"Newly added Billing profile is NOT listed on the billing info page");
		s_assert.assertFalse(storeFrontBillingInfoPage.isDefaultBillingAddressSelected(newBillingProfileName),"Newly created billing profile is DEFAULT selected on the billing info page when we add a new billing info");
		s_assert.assertTrue(storeFrontBillingInfoPage.isDefaultBillingAddressSelected(defaultBillingProfileName),"Old Default billing profile is not DEFAULT selected on the billing info page when we add a new billing info");

		//Edit the newly added billing profile
		randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String editedBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
		storeFrontBillingInfoPage.clickOnEditOfBillingProfile(newBillingProfileName+" "+lastName);
		storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, editedBillingProfileName+" "+lastName, cardExpMonth, cardExpYear, CVV);
		s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly edited Billing profile is NOT listed on the billing info pagewhen we edit the billing info ");
		s_assert.assertTrue(storeFrontBillingInfoPage.isDefaultBillingAddressSelected(defaultBillingProfileName),"Already Default billing profile is not DEFAULT selected on billing info page when we edit the billing info");
		storeFrontHomePage.hoverOnShopLinkAndClickAllProductsLinks();
		storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
		s_assert.assertTrue(storeFrontHomePage.isCartPageDisplayed(), "Cart page is not displayed");
		logger.info("Cart page is displayed");
		storeFrontHomePage.clickOnCheckoutButton();
		storeFrontHomePage.clickOnShippingAddressNextStepBtn();
		s_assert.assertTrue(storeFrontHomePage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly/Edited Billing profile is NOT listed on the Adhoc cart");
		s_assert.assertTrue(storeFrontHomePage.isBillingProfileIsSelectedByDefault(defaultBillingProfileName),"Old Default billing profile is not DEFAULT selected on the Adhoc cart");
		storeFrontHomePage.clickOnRodanAndFieldsLogo();
		storeFrontConsultantPage.clickOnWelcomeDropDown();
		storeFrontCartAutoShipPage = storeFrontConsultantPage.clickEditCrpLinkPresentOnWelcomeDropDown();
		storeFrontUpdateCartPage = storeFrontCartAutoShipPage.clickUpdateMoreInfoLink();
		storeFrontUpdateCartPage.clickOnEditPaymentBillingProfile();
		s_assert.assertTrue(storeFrontHomePage.isBillingAddressPresentOnPage(editedBillingProfileName),"Newly/Edit created Billing profile is NOT listed on the autoship cart");
		s_assert.assertFalse(storeFrontHomePage.isBillingProfileIsSelectedByDefault(newBillingProfileName),"Old Default billing profile is not DEFAULT selected on the Autoship cart");
		s_assert.assertAll();  
	}

	//QTest ID TC-413 Add New Billing from accounts and chk on Checkout screen
		//Hybris Project-2278:Add New Billing from accounts and chk on Checkout screen
		@Test(groups="billingPageCorpCons",priority=12)
		public void testAddNewBillingProfileFromAccountsAndCheckOn_413q() throws InterruptedException{
			int randomNum = CommonUtils.getRandomNum(10000, 1000000);
			int i=0;
			String newBillingProfileName = TestConstants.NEW_BILLING_PROFILE_NAME_US+randomNum;
			String defaultSelectedBillingProfileName = storeFrontBillingInfoPage.getDefaultBillingAddress();
			for(i=1;i<=2;i++){
				storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
				storeFrontHomePage.enterBillingDetailsAndSave(cardNumber, newBillingProfileName+" "+i, cardExpMonth, cardExpYear, CVV);
			}
			storeFrontConsultantPage.hoverOnShopLinkAndClickAllProductsLinks();
			storeFrontHomePage.selectProductForAdhocAndProceedToBuy(TestConstants.PRICE_FILTER_HIGH_TO_LOW,TestConstants.PRODUCT_NUMBER);
			storeFrontUpdateCartPage.clickOnCheckoutButton();
			storeFrontUpdateCartPage.clickOnShippingAddressNextStepBtn();
			s_assert.assertTrue(storeFrontUpdateCartPage.verifyNewAddressGetsAssociatedWithTheDefaultBillingProfile(defaultSelectedBillingProfileName), "Default selected billing address is not present on checkout page");
			s_assert.assertTrue(storeFrontUpdateCartPage.isTheBillingAddressPresentOnPage(newBillingProfileName, i),"Newly added Billing profile is NOT listed on the checkout page page");	
			i--;
			storeFrontUpdateCartPage.selectDifferentBillingProfile(newBillingProfileName+" "+i);
			s_assert.assertTrue(storeFrontUpdateCartPage.verifyNewAddressGetsAssociatedWithTheDefaultBillingProfile(newBillingProfileName), "Bill to this card is not selected");
			storeFrontUpdateCartPage.clickOnBillingNextStepBtn(); 
			s_assert.assertTrue(storeFrontUpdateCartPage.isNewlyCreatedBillingProfileIsSelectedByDefault(newBillingProfileName+" "+i),"New Billing Profile is not selected by default on CRP cart page");
			storeFrontUpdateCartPage.clickPlaceOrderBtn();
			s_assert.assertTrue(storeFrontHomePage.isOrderPlacedSuccessfully(),"Order is not placed successfully");
			storeFrontConsultantPage = storeFrontUpdateCartPage.clickOnRodanAndFieldsLogo();
			storeFrontConsultantPage.clickOnWelcomeDropDown();
			storeFrontOrdersPage = storeFrontConsultantPage.clickOrdersLinkPresentOnWelcomeDropDown();
			storeFrontOrdersPage.clickOnFirstAdHocOrder();
			s_assert.assertTrue(storeFrontOrdersPage.isPaymentMethodContainsName(newBillingProfileName+" "+i),"AdHoc Orders Template Payment Method contains new billing profile when future autoship checkbox not selected");
			s_assert.assertAll();
		}

	//qTest ID-429 Delete billing profile from 'billing profile' page
	@Test(groups="billingPageCorpCons",priority=13)
	public void testDeleteBillingProfileFromBillingProfilePage_429q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(1000, 9999);
		String fName = "RF"; 
		String lName1 = "AutoSF "+randomNum;
		String name1 = fName+" "+lName1;		
		storeFrontBillingInfoPage.clickAddNewBillingProfileLink();
		storeFrontBillingInfoPage.enterBillingDetailsAndSave(cardNumber, name1,cardExpMonth, cardExpYear, CVV);
		//storeFrontBillingInfoPage.clickOnSaveBillingProfile();
		s_assert.assertTrue(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(lName1), name1+" billing profile is not added on the page");
		storeFrontBillingInfoPage. clickOnDeleteOfBillingProfile(lName1);
		s_assert.assertFalse(storeFrontBillingInfoPage.isBillingAddressPresentOnPage(lName1), name1+" billing profile is not deleted from the page");
		s_assert.assertAll();
	}
	
}