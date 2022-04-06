package com.rf.test.website.storeFront.heirloom.radiantMigration;

import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.pages.website.heirloom.StoreFrontHeirloomHomePage;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class MigratedPCTest extends RFHeirloomWebsiteBaseTest{
	
	private StoreFrontHeirloomHomePage storeFrontHeirloomHomePage;
	
	@Test
	public void testAddShippingThroughMyAccount(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME+randomNumber;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		String addressLine1 =  TestConstantsRFL.ADDRESS_LINE1;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String phnNumber = TestConstantsRFL.NEW_ADDRESS_PHONE_NUMBER_US;
		storeFrontHeirloomHomePage = new StoreFrontHeirloomHomePage(driver);
		/*storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);*/
		storeFrontHeirloomHomePage.loginAsPCUser("brianlloyd@gmail.com",password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "PC user not logged in successfully");
		storeFrontHeirloomHomePage.clickHeaderLinkFromTopNav();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickChangeLinkUnderShippingToOnPWS();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getShippingAddressName().contains(shippingProfileFirstName),"Shipping address name expected is "+shippingProfileFirstName+" While on UI is "+storeFrontHeirloomHomePage.getShippingAddressName());
		storeFrontHeirloomHomePage.clickChangeLinkUnderShippingToOnPWS();
		storeFrontHeirloomHomePage.selectDeleteForProfileAndAcceptConfirmationAlert(shippingProfileFirstName);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isProfilePresentInSavedProfiles(shippingProfileFirstName),"Shipping Profile "+shippingProfileFirstName+" is still present after Deletion");
		s_assert.assertAll();
	}
	
	
	@Test
	public void testAddBillingThroughMyAccount(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumber;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = TestConstantsRFL.FIRST_NAME;
		String expMonth = TestConstantsRFL.EXP_MONTH;
		String expYear = TestConstantsRFL.EXP_YEAR;

		String addressLine1 =  TestConstantsRFL.ADDRESS_LINE1;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String phnNumber = TestConstantsRFL.NEW_ADDRESS_PHONE_NUMBER_US;
		
		storeFrontHeirloomHomePage = new StoreFrontHeirloomHomePage(driver);
		storeFrontHeirloomHomePage.loginAsPCUser("brianlloyd@gmail.com",password);		
		/*storeFrontHeirloomHomePage.loginAsPCUser(pcEmailId,password);*/
		storeFrontHeirloomHomePage.clickHeaderLinkFromTopNav();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickChangeBillingInformationLinkUnderBillingTabOnPWS();
		storeFrontHeirloomHomePage.enterBillingInfoForPWS(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		storeFrontHeirloomHomePage.clickChangeBillingInformationLinkUnderBillingTabOnPWS();
		storeFrontHeirloomHomePage.selectDeleteForProfileAndAcceptConfirmationAlert(billingProfileFirstName);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isProfilePresentInSavedProfiles(billingProfileFirstName),"Billing Profile "+billingProfileFirstName+" is still present after Deletion");
		s_assert.assertAll();
	}
	
	
	@Test
	public void testAddShippingBillingWhileAdhocCheckoutForPC(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String postalCode = TestConstantsRFL.POSTAL_CODE;
		String cardNumber = TestConstantsRFL.CARD_NUMBER;
		String nameOnCard = firstName;
		String expMonth = TestConstantsRFL.EXP_MONTH;
		String expYear = TestConstantsRFL.EXP_YEAR;
		String phnNumber = TestConstantsRFL.NEW_ADDRESS_PHONE_NUMBER_US;
		String addressLine1 =  TestConstantsRFL.ADDRESS_LINE1;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME+randomNumber;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		
		storeFrontHeirloomHomePage = new StoreFrontHeirloomHomePage(driver);
		storeFrontHeirloomHomePage.loginAsPCUser("brianlloyd@gmail.com", password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "RC user not logged in successfully");
		storeFrontHeirloomHomePage.clickShopSkinCareHeader();
		storeFrontHeirloomHomePage.selectRegimen(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickChangeShippingAddressBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getShippingAddressName().contains(shippingProfileFirstName),"Shipping address name expected is "+shippingProfileFirstName+" While on UI is "+storeFrontHeirloomHomePage.getShippingAddressName());
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}
	
	
	

}
