package com.rf.test.website.storeFront.heirloom.radiantMigration;

import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.pages.website.heirloom.StoreFrontHeirloomHomePage;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class MigratedConsultantTest extends RFHeirloomWebsiteBaseTest {

	StoreFrontHeirloomHomePage storeFrontHeirloomHomePage;

	@Test
	public void testAddShippingBillingWhileAdhocCheckoutForConsultant(){
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
		storeFrontHeirloomHomePage.loginAsPCUser("t-johnson5@hotmail.com", password);
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
