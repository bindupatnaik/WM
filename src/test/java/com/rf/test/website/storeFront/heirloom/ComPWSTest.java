package com.rf.test.website.storeFront.heirloom;

import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class ComPWSTest extends RFHeirloomWebsiteBaseTest{

	//QTest ID TC-418 Forgot Password from COM
	@Test(priority=1)
	public void testForgotPasswordPWS_418(){
		String consultantEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
		}
		else{
			consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
		}
		openCOMSite();
		//click 'forgot password' on biz home page
		storeFrontHeirloomHomePage.clickForgotPasswordLinkOnBizHomePage();
		//verify a message prompt to change the password displayed?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateChangePasswordMessagePrompt(),"Message not prompted for 'change password'");
		//verify user is able to change the password and email is being sent?
		storeFrontHeirloomHomePage.enterEmailAddressToRecoverPassword(consultantEmailID);
		storeFrontHeirloomHomePage.clickSendEmailButton();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validatePasswordChangeAndEmailSent(),"resetting your password mail is not displayed!!");
		s_assert.assertAll();
	}

	//QTest ID TC-2391 Log in as an existing consultant from COM
	//QTest ID TC-419 Shop_Skincare from COM
	@Test(priority=2)
	public void testShopSkinCarePWS_419_2391(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String regimen = "REVERSE";
		String consultantEmailID = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
			}
			else{
				consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		//click on 'our products' in tha nav menu
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		//select a product and add to cart
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickMyShoppingBagLink();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertAll();
	}

	// QTest ID TC-420 Shop Skincare-menu navigation
	@Test(priority=3)
	public void testShopSkinCareMenuNavigation_420(){
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareOnPWS();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresent(TestConstantsRFL.REGIMEN_NAME_REDEFINE),"Redefine regimen name is not present on pws site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresent(TestConstantsRFL.REGIMEN_NAME_REVERSE),"Reverse regimen name is not present on pws site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresent(TestConstantsRFL.REGIMEN_NAME_UNBLEMISH),"Unblemish regimen name is not present on pws site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresent(TestConstantsRFL.REGIMEN_NAME_SOOTHE),"Soothe regimen name is not present on pws site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresent(TestConstantsRFL.REGIMEN_NAME_ENHANCEMENTS),"Enhancements regimen name is not present on pws site");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRegimenNamePresent(TestConstantsRFL.REGIMEN_NAME_ESSENTIALS),"Essentials regimen name is not present on pws site");
		s_assert.assertAll();
	}

	//QTest ID TC-422 Shop Skincare-Consultants Only -buy only products (enhancements micro dermabrasion paste packets)
	@Test(priority=4)
	public void testShopSkinCareConsultantsOnlyBuyProductPromotion_422(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
			}
			else{
				consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverOnShopSkinCareAndClickOnConsultantOnlyProductsLink();
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProductOnPWS(TestConstantsRFL.CONSULTANT_ONLY_PRODUCT);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("consultant-only"), "Expected url contains is:Consultant-Only Products but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		//  storeFrontHeirloomConsultantPage.mouseHoverOnShopSkinCareAndClickOnConsultantOnlyProductsLink();
		storeFrontHeirloomHomePage.clickMyShoppingBagLink();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate product details before and after placing an order from com
	 */	
	//*QTest ID TC-836 Checkout as Consultant
	@Test(priority=4)
	public void testCheckoutAsConsultant_836(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String subTotalFromOrderReview = null;
		String shippingFromOrderReview = null;
		String taxFromOrderReview = null;
		String grandTotalFromOrderReview = null;
		String quantityFromOrderReview = null;
		String SKUFromOrderReview = null;
		String itemNameFromOrderReview = null;
		String priceFromOrderReview = null;
		String totalFromOrderReview = null;
		String quantityFromOrderConfirmation = null;
		String SKUFromOrderConfirmation = null;
		String itemNameFromOrderConfirmation = null;
		String priceFromOrderConfirmation = null;
		String totalFromOrderConfirmation = null;
		String subTotalFromOrderConfirmation = null;
		String shippingFromOrderConfirmation = null;
		String taxFromOrderConfirmation = null;
		String grandTotalFromOrderConfirmation = null;
		String category = "REDEFINE";
		openCOMSite();
		String consultantEmailID = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
			}
			else{
				consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(), "User is not logged in successfully");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		subTotalFromOrderReview = storeFrontHeirloomHomePage.getSubtotalFromOrderConfirmation();
		shippingFromOrderReview = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		taxFromOrderReview = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		grandTotalFromOrderReview = storeFrontHeirloomHomePage.getGrandTotalFromOrderConfirmationPage();
		quantityFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","1");
		SKUFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","2");
		itemNameFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","3");
		priceFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","5");
		totalFromOrderReview = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","6");

		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		// get details from order confirmation
		subTotalFromOrderConfirmation = storeFrontHeirloomHomePage.getSubtotalFromOrderConfirmation();
		shippingFromOrderConfirmation = storeFrontHeirloomHomePage.getShippingTotalFromOrderConfirmationPage();
		taxFromOrderConfirmation = storeFrontHeirloomHomePage.getTaxFromOrderConfirmationPage();
		grandTotalFromOrderConfirmation = storeFrontHeirloomHomePage.getGrandTotalFromOrderConfirmationPage();
		quantityFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","1");
		SKUFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","2");
		itemNameFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","3");
		priceFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","5");
		totalFromOrderConfirmation = storeFrontHeirloomHomePage.getProductDetailsFromCartAccordingToProductNumber("1","6");

		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from biz site for Consultant user.");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		//assert all product details

		s_assert.assertTrue(quantityFromOrderConfirmation.equalsIgnoreCase(quantityFromOrderReview), "Expected qunatity of product is "+quantityFromOrderReview+ "but actual on UI "+quantityFromOrderConfirmation);
		s_assert.assertTrue(SKUFromOrderConfirmation.contains(SKUFromOrderReview), "Expected SKU value of product is "+SKUFromOrderReview+ "but actual on UI "+SKUFromOrderConfirmation);
		s_assert.assertTrue(itemNameFromOrderConfirmation.contains(itemNameFromOrderReview), "Expected name of product is "+itemNameFromOrderReview+ "but actual on UI "+itemNameFromOrderConfirmation);
		s_assert.assertTrue(priceFromOrderConfirmation.contains(priceFromOrderReview), "Expected price of product is "+priceFromOrderReview+ "but actual on UI "+priceFromOrderConfirmation);
		s_assert.assertTrue(totalFromOrderConfirmation.contains(totalFromOrderReview), "Expected total value of product is "+totalFromOrderReview+ "but actual on UI "+totalFromOrderConfirmation);
		s_assert.assertTrue(subTotalFromOrderConfirmation.contains(subTotalFromOrderReview), "Expected subtotal of product is "+subTotalFromOrderReview+ "but actual on UI "+subTotalFromOrderConfirmation);
		s_assert.assertTrue(shippingFromOrderConfirmation.contains(shippingFromOrderReview), "Expected shipping value of product is "+shippingFromOrderReview+ "but actual on UI "+shippingFromOrderConfirmation);
		s_assert.assertTrue(taxFromOrderConfirmation.contains(taxFromOrderReview), "Expected tax of product is "+taxFromOrderReview+ "but actual on UI "+taxFromOrderConfirmation);
		s_assert.assertTrue(grandTotalFromOrderConfirmation.contains(grandTotalFromOrderReview), "Expected grand total of product is "+grandTotalFromOrderReview+ "but actual on UI "+grandTotalFromOrderConfirmation);
		s_assert.assertAll();
	}


	//QTest ID TC-861 My account options as RC customer
	@Test 
	public void testMyAccountOptionsAsRCCustomer_861(){
		String orderHistory = "Order History";
		String rcEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				rcEmailId=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
			}
			else{
				rcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
				storeFrontHeirloomHomePage.loginAsRCUser(rcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomRCUserPage.clickHeaderLinkAfterLogin("my account");
		s_assert.assertTrue(storeFrontHeirloomRCUserPage.isOrderManagementSublinkPresent(orderHistory), "Order History link is not present in order management");
		storeFrontHeirloomRCUserPage.clickOrderManagementSublink(orderHistory);
		s_assert.assertTrue(storeFrontHeirloomRCUserPage.isOrderNumberPresentAtOrderHistoryPage(), "Order number is not present at order history page");
		s_assert.assertAll();
	}

	//QTest ID TC-2403 Footer- Privacy Policy link should be redirecting to the appropriate page
	@Test
	public void testFooterPrivacyPolicyLinkShouldRedirectionToAppropriatePage_2403(){
		openCOMSite();
		storeFrontHeirloomHomePage.clickPrivacyPolicyLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPrivacyPolicyPagePresent(), "Privacy policy page is not present after clicked on privacy policy link");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("privacy"), "Expected url having privacy but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	//QTest ID TC-2401 Satisfaction Guarantee-link should be redirecting properly 
	@Test
	public void testSatisfactionGuaranteeLinkShouldBeRedirectionProperly_2401(){
		openCOMSite();		
		storeFrontHeirloomHomePage.clickSatisfactionGuaranteeLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSatisfactionGuaranteePagePresent(), "Satisfaction guarantee page is not present after clicked on Satisfaction guarantee link");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("guarantee"), "Expected url having guarantee but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	//QTest ID TC-2402 Footer-Terms & Conditions link should redirecting to the appropriate page
	@Test
	public void testFooterTermsAndConditionLinkShouldRedirectionToAppropriatePage_2402(){
		String termsAndConditionsText = "TERMS & CONDITIONS";
		openCOMSite();
		storeFrontHeirloomHomePage.clickTermsAndConditionsLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isTextPresent(termsAndConditionsText), "TERMS & CONDITIONS page is not present after clicked on terms And Conditions link");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("terms"), "Expected url having terms but actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertAll();
	}

	//QTest ID TC-2404 Disclaimer-link should be redirecting properly from COM
	@Test
	public void testDisclaimerLinkShouldBeRedirectedProperlyFromCOM_2404(){
		String disclaimerText = "DISCLAIMER";
		openCOMSite();
		//verify Disclaimer in footer should redirect properly?
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateDisclaimerLinkInFooter(),"'Disclaimer Link' doesn't redirect to disclaimer page");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isTextPresent(disclaimerText), "disclaimer text page is not present after clicked on disclaimer link");
		s_assert.assertAll();
	}

	//QTest ID TC-2395 Contact us-link should be redirecting properly from COM
	@Test(enabled=true)
	public void testContactUsLinkShouldBeRedirectingProperlyFromCOM_2395(){
		String contactUsText = "CONTACT US";
		openCOMSite();
		storeFrontHeirloomHomePage.clickContactUsAtFooter();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("Contact".toLowerCase()), "URL does not contain Contact Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.isTextPresent(contactUsText),"link is not redirected to contact us page");
		s_assert.assertAll();
	}

	// QTest ID TC-2415 PC User termination from COM
	@Test(enabled=true)
	public void testPCUserTerminationFromCOM_2415(){
		String userEmailId = null;
		String changeMyPCPerksStatus = "Change my PC Perks Status";
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isDelayOrCancelPCPerksLinkPresent(), "Delay or cancel PC perks link is not present");
		storeFrontHeirloomPCUserPage.clickCancelPCPerksLink();
		storeFrontHeirloomPCUserPage.clickChangedMyMindBtn();
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.getCurrentURL().contains("Dashboard"), "User is not redirecting to dashboard after clicked on changed my mind button");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		storeFrontHeirloomPCUserPage.clickCancelPCPerksLink();
		storeFrontHeirloomPCUserPage.selectReasonForPCTermination();
		storeFrontHeirloomPCUserPage.enterMessageForPCTermination();
		storeFrontHeirloomPCUserPage.clickSendEmailToCancelBtn();
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.getEmailConfirmationMsgAfterPCTermination().contains("you will be receiving a confirmation e-mail shortly"), "Expected email confirmation message is: you will be receiving a confirmation e-mail shortly, but actual on UI is: "+storeFrontHeirloomPCUserPage.getEmailConfirmationMsgAfterPCTermination());
		storeFrontHeirloomPCUserPage.clickGoToRFHomeButton();
		storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isInvalidLoginPresent(),"Terminated User is able to Login");
		s_assert.assertAll();
	}

	//QTest ID TC-2413 My account options as PC customer from COM
	@Test(enabled=true) 
	public void testMyAccountOptionsAsPCCustomerFromCOM_2413(){
		//		String orderHistory = "Order History";
		//		String editOrder = "Edit Order";
		//		String changeMyPCPerksStatus = "Change my PC Perks Status";
		//		String pCPerksFAQs = "PC Perks FAQs";
		String orderHistory = "Order History";
		String editOrder = "Edit Order";
		String changeMyPCPerksStatus = "Change my PC Perks Status";
		String PCPerksFAQs = "PC Perks FAQs";
		String userEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		//		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
		//		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(orderHistory), "Order History link is not present in order management");
		//		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(editOrder), "Edit order link is not present in order management");
		//		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(changeMyPCPerksStatus), "Change my pc perks status link is not present in order management");
		//		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(pCPerksFAQs), "PC Perks FAQs link is not present in order management");
		//		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(orderHistory);
		//		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderNumberPresentAtOrderHistoryPage(), "Order number is not present at order history page");
		//		storeFrontHeirloomPCUserPage.navigateToBackPage();
		//		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(editOrder);
		//		storeFrontHeirloomPCUserPage.clickEditOrderBtn();
		//		storeFrontHeirloomPCUserPage.clickAddToCartBtnForEditOrder();
		//		storeFrontHeirloomPCUserPage.clickUpdateOrderBtn();
		//		storeFrontHeirloomPCUserPage.clickOKBtnOfJavaScriptPopUp();
		//		s_assert.assertTrue(storeFrontHeirloomPCUserPage.getOrderUpdateMessage().contains("successfully updated"), "Expected order update message is successfully updated but actual on UI is: "+storeFrontHeirloomPCUserPage.getOrderUpdateMessage());
		//		storeFrontHeirloomPCUserPage.clickOnRodanAndFieldsLogo();
		//		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
		//		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		//		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isDelayOrCancelPCPerksLinkPresent(), "Delay or cancel PC perks link is not present");
		//		storeFrontHeirloomPCUserPage.navigateToBackPage();
		//		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(pCPerksFAQs);
		//		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isPCPerksFAQsLinkRedirectingToAppropriatePage("PC-Perks-FAQs.pdf"), "PC Perks FAQs link is not redirecting to appropriate page");
		//		s_assert.assertAll();

		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(orderHistory), "Order History link is not present in order management");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(editOrder), "Edit order link is not present in order management");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(changeMyPCPerksStatus), "Change my pc perks status link is not present in order management");
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderManagementSublinkPresent(PCPerksFAQs), "PC Perks FAQs link is not present in order management");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(orderHistory);
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderNumberPresentAtOrderHistoryPage(), "Order number is not present at order history page");
		storeFrontHeirloomPCUserPage.navigateToBackPage();
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(editOrder);
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isEditOrderPagePresent(), "Edit Order button Not present on Edit Order page");
		storeFrontHeirloomPCUserPage.navigateToBackPage();
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isDelayOrCancelPCPerksLinkPresent(), "Delay or cancel PC perks link is not present");
		storeFrontHeirloomPCUserPage.navigateToBackPage();
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(PCPerksFAQs);
		s_assert.assertTrue(storeFrontHeirloomPCUserPage.isPCPerksFAQsLinkRedirectingToAppropriatePage("PC-Perks-FAQs.pdf"), "PC Perks FAQs link is not redirecting to appropriate page");
		s_assert.assertAll();
	}

	//QTest ID TC-2417 PC Perks Delay - 60 days from COM
	@Test (enabled=true)
	public void testPCPerksDelay60DaysFromCOM_2417(){
		String changeMyPCPerksStatus = "Change my PC Perks Status";
		String userEmailId = null;  
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		storeFrontHeirloomPCUserPage.clickDelayPCPerksLink();
		storeFrontHeirloomHomePage.selectSecondRadioButton();
		storeFrontHeirloomPCUserPage.clickChangeAutohipDateBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessage(),"confirmation msg not present as expected");
		storeFrontHeirloomHomePage.clickBackToMyAccountBtn();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessageInOrders(),"Confirmation msg not present at orders page as expected");
		s_assert.assertAll();
	}

	//QTest ID TC-912 PWS.com R+FInTheNews
	@Test(enabled=false)//no such links on com site
	public void testPWSCOMRPlusFInTheNews_912(){
		String expectedURL = "Company/PR";
		openCOMSite();
		storeFrontHeirloomHomePage.clickCompanyPressRoomLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertAll();
	}

	//QTest ID TC-2392 Log in as valid PC customer from COM
	@Test(enabled=true)
	public void testLoginAsExistingPCFromCOM_2392(){
		String userEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"PC user is not logged in successfully");
		s_assert.assertAll();
	}

	//QTest ID TC-2393: Log in with a valid RC customer from COM
	@Test(enabled=true)
	public void testLoginAsExistingRCFromCOM_2393(){
		String rcEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				rcEmailId=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
			}
			else{
				rcEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
				storeFrontHeirloomHomePage.loginAsRCUser(rcEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"RC user is not logged in successfully");
		s_assert.assertAll();
	}

	//QTest ID TC-2416 PC Perks Delay - 30 days from COM
	@Test (enabled=true)
	public void testPCPerksDelay30DaysFromCOM_2416(){
		String userEmailId = null;
		String changeMyPCPerksStatus = "Change my PC Perks Status";
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomPCUserPage.clickOrderManagementSublink(changeMyPCPerksStatus);
		storeFrontHeirloomPCUserPage.clickDelayPCPerksLink();
		storeFrontHeirloomPCUserPage.clickChangeAutohipDateBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessage(),"confirmation msg not present as expected");
		storeFrontHeirloomHomePage.clickBackToMyAccountBtn();
		storeFrontHeirloomHomePage.clickEditOrderLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyConfirmationMessageInOrders(),"Confirmation msg not present at orders page as expected");
		s_assert.assertAll();
	}

	//*QTest ID TC-2399 Company-Careers link from COM
	@Test(enabled=true)
	public void testCompanyCarrersLink_2399(){
		String pageName="CAREERS";
		String expectedURL="careers";
		openCOMSite();
		storeFrontHeirloomHomePage.clickCareersLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().contains(expectedURL), "Current url expected is: "+expectedURL+" while actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL());
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPageHeaderVisible(pageName),"User is not redirected to CAREERS Page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the redefine category links and their sublinks(Results and FAQs) under shopskincare from com site
	 *  
	 */
	//*QTest ID TC-887:Shop Shop Skincare-menu navigation REDEFINE (PWS.com)
	@Test(enabled=true)
	public void testShopSkincareMenuNavigationRedefineFromCom_887(){
		String category = "REDEFINE";
		String subLinkResults = "Results";
		String subLinkFAQ = "FAQ";
		String currentURL = null;
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCategoryNamePresent(category) && currentURL.contains(category),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category, subLinkResults);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(category),"user is not on results page");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category,subLinkFAQ);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is CommonQuestions".toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(category),"user is not on FAQs page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the REVERSE category links and their sublinks(Results and FAQs) under shopskincare from com site
	 *  
	 */
	//*QTest ID TC-888:Shop Skincare-menu navigation REVERSE (PWS.com)
	@Test(enabled=true)
	public void testShopSkincareMenuNavigationReverseFromCom_888(){
		String category = "REVERSE";
		String subLinkResults = "Results";
		String subLinkFAQ = "FAQ";
		String currentURL = null;
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCategoryNamePresent(category) && currentURL.contains(category),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category, subLinkResults);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(category),"user is not on results page");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category,subLinkFAQ);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is CommonQuestions".toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(category),"user is not on FAQs page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the SOOTHE category links and their sublinks(Results and FAQs) under shopskincare from com site
	 *  
	 */
	//*QTest ID TC-890:Shop Skincare-menu navigation SOOTHE (PWS.com)
	@Test(enabled=true)
	public void testShopSkincareMenuNavigationSootheFromCom_890(){
		String category = "SOOTHE";
		String subLinkResults = "Results";
		String subLinkFAQ = "FAQ";
		String currentURL = null;
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL().toLowerCase();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isExactCategoryNamePresent(category) && currentURL.contains(category.toLowerCase()),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category, subLinkResults);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(category),"user is not on results page");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category,subLinkFAQ);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is CommonQuestions".toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(category),"user is not on FAQs page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the ENHANCEMENTS category links under shopskincare from com site
	 *  
	 */
	//*QTest ID TC-891:Shop Skincare-menu navigation ENHANCEMENTS (PWS.com)
	@Test(enabled=true)
	public void testShopSkincareMenuNavigationEnhancementsFromCom_891(){
		String category = "ENHANCEMENTS";
		String currentURL = null;
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL().toLowerCase();
		s_assert.assertTrue(currentURL.contains(category.toLowerCase()),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the ESSENTIALS category links under shopskincare from com site
	 *  
	 */
	//*QTest ID TC-892:Shop Skincare-menu navigation ESSENTIALS (PWS.com)
	@Test(enabled=true)
	public void testShopSkincareMenuNavigationEssentialsFromCom_892(){
		String category = "ESSENTIALS";
		String currentURL = null;
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
		currentURL = storeFrontHeirloomHomePage.getCurrentURL().toLowerCase();
		s_assert.assertTrue(currentURL.toLowerCase().contains(category.toLowerCase()),"Not redirected to essentials page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- placed an adhoc order with consultant only buy event support pack 
	 * and validate orders details like subtotal & product name from com site
	 */
	//*QTest ID TC-904 Consultants Only -buy event support pack 
	@Test(enabled=true)
	public void consultantsOnlyBuyEventSupportPack_904(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
			}
			else{
				consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		//  storeFrontHeirloomConsultantPage.clickShopSkinCareBtn();
		//  storeFrontHeirloomConsultantPage.selectConsultantOnlyProductsRegimen();
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_EVENT_SUPPORT);
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate the sublink of About R+F from com site
	 */
	//*QTest ID TC-908 Company-Links should be present (Execute team, our history, carrers, PFC foundation, press room, contact us) 
	@Test(enabled=true)
	public void testVerfiyCompanyLinks_908(){
		String whoWeAre = "WHO WE ARE";
		String meetTheDoctors = "MEET THE DOCTORS";
		String givingBack = "GIVING BACK";
		String currentURL = null;
		openCOMSite();
		//verify company careers Link?
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Company");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("Company"),"Company link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(whoWeAre),whoWeAre+" link text is not present");

		// assert meet the doctors link
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Meet the Doctors");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("MeetTheDoctors"),"MeetTheDoctors link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(meetTheDoctors),meetTheDoctors+" link text is not present");

		// assert Giving Back link
		storeFrontHeirloomHomePage.mouseHoverAboutRFAndClickLink("Giving Back");
		currentURL = driver.getCurrentUrl();
		s_assert.assertTrue(currentURL.contains("GivingBack"),"Giving Back link didn't work");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLinkTextPresent(givingBack),givingBack+" link text is not present");
		s_assert.assertAll();
	}

	//*QTest ID TC-698 Mouse hover on shopping bag should bring drop down screen
	@Test(enabled=false)
	public void testMouseHoverOnShoppingBagShouldBringDropDownScreen_698() {
		String regimen=TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickMyShoppingBagLink();
		//verify the Qty price and item name 
		storeFrontHeirloomHomePage.checkProductNameThroughCart();
		storeFrontHeirloomHomePage.checkQtyThroughCart();
		storeFrontHeirloomHomePage.checkTotalPriceThroughCart();
		storeFrontHeirloomHomePage.checkProductPriceThroughCart();
		//Biz site
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickMyShoppingBagLink();
		storeFrontHeirloomHomePage.checkProductNameThroughCart();
		storeFrontHeirloomHomePage.checkQtyThroughCart();
		storeFrontHeirloomHomePage.checkTotalPriceThroughCart();
		storeFrontHeirloomHomePage.checkProductPriceThroughCart();
		//Com site
		openBIZSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickMyShoppingBagLink();
		storeFrontHeirloomHomePage.checkProductNameThroughCart();
		storeFrontHeirloomHomePage.checkQtyThroughCart();
		storeFrontHeirloomHomePage.checkTotalPriceThroughCart();
		storeFrontHeirloomHomePage.checkProductPriceThroughCart();
		s_assert.assertAll();
	}

	//*QTest ID TC-2406  RC Enrollment from COM
	@Test(enabled=true)
	public void testRCEnrollmentFromCOM_2406(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		int randomNumbers = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String emailAddress = firstName+randomNum+"@rodanandfields.com";
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumbers;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String gender = TestConstantsRFL.GENDER_MALE;
		String addressName = "Home";
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickClickHereLinkForRC();
		s_assert.assertTrue(driver.getCurrentUrl().contains("Retail"), "After clicking click here link for RC not navigated to RC Enrollment page.");
		storeFrontHeirloomHomePage.enterProfileDetailsForPCAndRC(firstName,lastName,emailAddress,password,phnNumber,gender);
		storeFrontHeirloomHomePage.clickCreateMyAccountBtnOnCreateRetailAccountPage();
		storeFrontHeirloomHomePage.clickSelectAndContinueBtnForPCAndRC();
		storeFrontHeirloomHomePage.enterShippingProfileDetails(addressName, shippingProfileFirstName,shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Enrollment is not completed successfully");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isQuantityOnConfirmOrderpagePresent(), "Qty is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSKUOnConfirmOrderPagePresent(), "SKU is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isItemOnConfirmOrderpagePresent(), "Item name is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPriceOnConfirmOrderpagePresent(), "Price is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isTotalOnConfirmOrderpagePresent(), "Total is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSubTotalOnConfirmOrderpagePresent(), "SubTotal is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isTaxAmountOnConfirmOrderpagePresent(), "Tax Amount is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isShippingAmountOnConfirmOrderpagePresent(), "Shipping Total is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isGrandTotalOnConfirmOrderpagePresent(), "Grand Total is not visible");
		s_assert.assertAll();
	}

	//*QTest ID TC-2421 Adhoc order from COM
	@Test(enabled=true)
	public void testAdhocOrderRCFromCOM_2421(){
		RFL_DB = driver.getDBNameRFL();
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String nameOnCard = firstName;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String rcEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(rcEmailID, password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "RC user not logged in successfully");
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.chooseShippingMethod("FedEx Grnd");
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from com site for RC user.");
		s_assert.assertAll();
	}	

	//*QTest ID TC-2422 Adhoc Order - Consultant Only Products Com site
	//>100 QV product is not avaialbe
	@Test(enabled=true)
	public void testAdhocOrderConsultantsOnlyProductsFromCOM_2422(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
			}
			else{
				consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_PRODUCT);
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.chooseShippingMethod("FedEx Grnd");
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isQuantityOnConfirmOrderpagePresent(), "Qty is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSKUOnConfirmOrderPagePresent(), "SKU is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isItemOnConfirmOrderpagePresent(), "Item name is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPriceOnConfirmOrderpagePresent(), "Price is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isTotalOnConfirmOrderpagePresent(), "Total is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSubTotalOnConfirmOrderpagePresent(), "SubTotal is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isTaxAmountOnConfirmOrderpagePresent(), "Tax Amount is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isShippingAmountOnConfirmOrderpagePresent(), "Shipping Total is not visible");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isGrandTotalOnConfirmOrderpagePresent(), "Grand Total is not visible");
		s_assert.assertAll();

	}

	//* QTest ID TC-2423 COM>Add Product to the Cart - Checkout as PC
	@Test(enabled=true)
	public void testAddProductToTheCartCheckoutAsPCOnCorp_2423() {
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String pcEmailID = null;
		String firstName = TestConstantsRFL.FIRST_NAME;
		String nameOnCard = firstName;
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumber;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String regimen = TestConstantsRFL.REGIMEN_NAME_REDEFINE;
		openCOMSite();
		if(shouldFetchDataFromExcelForTokeniation){
			pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
		}
		else{
			pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(pcEmailID, password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSignInButtonPresent(), "PC user not logged in successfully");
		storeFrontHeirloomHomePage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName)||storeFrontHeirloomHomePage.getBillingAddress().contains(addressLine1), "Existing billing profile is not selected for new order.");
		storeFrontHeirloomHomePage.chooseShippingMethod("FedEx Grnd");
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from corp site.");
		s_assert.assertAll();
	}


	//QTest TC-2418:PC Perks Template Update - Add/modify products from COM
	@Test(enabled=true)
	public void testPCPerksTemplateUpdateFromCOM_2418(){
		String updatedOrderTotal = null;
		String userEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickEditOrderbtn();
		storeFrontHeirloomHomePage.clickRemoveLinkAboveTotal();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isStatusMessageDisplayed(),"status message is not displayed as expected");
		storeFrontHeirloomHomePage.clickAddToCartBtnForLowPriceItems();
		storeFrontHeirloomHomePage.clickAddToCartBtnForHighPriceItems();
		storeFrontHeirloomHomePage.clickOnUpdateOrderBtn();
		storeFrontHeirloomHomePage.handleAlertAfterUpdateOrder();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getConfirmationMessage().contains("Replenishment Order items successfully updated!"),"No Message appearing after order update");
		updatedOrderTotal = storeFrontHeirloomHomePage.getOrderTotal();
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomHomePage.clickEditOrderLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderTotalAtOverview().contains(updatedOrderTotal),"order total is not updated at overview page");
		s_assert.assertAll();
	}

	//QTest TC-2419:PC Perks Template - Shipping Address from COM
	@Test(enabled=true)
	public void testPCPerksTemplateShippingAddressUpdateFromCOM_2419(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME+randomNumber;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		String userEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickChangeLinkUnderShippingToOnPWS();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getShippingAddressName().contains(shippingProfileFirstName),"Shipping address name expected is "+shippingProfileFirstName+" While on UI is "+storeFrontHeirloomHomePage.getShippingAddressName());
		s_assert.assertAll();
	}

	//QTest TC-2420:PC Perks Template - Billing Profile from COM
	@Test(enabled=true)
	public void testPCPerksTemplateBillingAddressUpdateFromCOM_2420(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME+randomNumber;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME;
		String userEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickHeaderLinkAfterLogin("my account");
		storeFrontHeirloomHomePage.clickEditOrderLink();
		storeFrontHeirloomHomePage.clickChangeBillingInformationLinkUnderBillingTabOnPWS();
		storeFrontHeirloomHomePage.enterBillingInfoForPWS(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open COM, Login with consultant, logout and Verify 
	 * Assertions:
	 * User is able to login successfully
	 * User is logout successfully
	 */
	//QTest ID TC-2394 COM> log out with a valid user  from COM PWS
	@Test(enabled=true)
	public void testLogoutWithAValidUserOnCOMPWS_2394(){
		String consultantEmailID = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
			}
			else{
				consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		logout();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isForgotPasswordLinkPresent(),"User is not logout successfully");
		s_assert.assertAll();
	}	

	@Test(enabled=true)
	public void testRFConnectionFromCOMPWS_2396(){
		String rfConnection="RF Connection";
		String parentWindow=null;
		openCOMSite();
		parentWindow=storeFrontHeirloomHomePage.getParentWindowHandle();
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(rfConnection);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserRedirectedToExpectedPage(parentWindow, "rfconnection"),"Expected URL does not contain 'rfconnection'");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isRFConnectionPageHeaderVisible()," User is not redirected to RF Connection Page");
		s_assert.assertAll();
	}

	@Test(enabled=true)
	public void testDermConnectionFromCOMPWS_2397(){
		String dermRF="Derm RF";
		String parentWindow=null;
		openCOMSite();
		parentWindow=storeFrontHeirloomHomePage.getParentWindowHandle();
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(dermRF);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserRedirectedToExpectedPage(parentWindow, "dermrf"),"Expected URL does not contain 'dermrf'");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isDERMRFPageHeaderVisible()," User is not redirected to DERM RF Page");
		s_assert.assertAll();
	}	

	/***
	 * Test Summary:- validate Shipping Link From COM PWS
	 * Assertions:
	 * Page URL should contain 'Shipping'
	 * Shipping should be visible on page Header
	 */	
	//*QTest ID TC-2400 Shipping from COM PWS
	@Test(enabled=true)
	public void testShippingFromCOMPWS_2400(){
		String shipping="Shipping";
		String currentURL=null;
		openCOMSite();
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(shipping);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("Shipping"),"Expected URL should contain 'shipping' But actual on UI is: "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isShippingPageHeaderVisible()," User is not redirected to Shipping Page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate California Supply Chains Act Link From COM PWS
	 * Assertions:
	 * Page URL should contain 'california-supply-chains-act'
	 * 'CALIFORNIA TRANSPARENCY IN SUPPLY CHAINS ACT' should be visible on page Header
	 */	
	//*QTest ID TC-2405 California Supply Chains Act from COM PWS
	@Test(enabled=true)
	public void testCaliforniaSupplyChainsActFromCOMPWS_2405(){
		String califSupplyChainsAct="California Supply Chains Act";
		String currentURL=null;
		openCOMSite();
		storeFrontHeirloomHomePage.clickBottomPageCategoryLink(califSupplyChainsAct);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("california-supply-chains-act"),"Expected URL should contain 'california-supply-chains-act' But actual on UI is: "+currentURL);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCaliforniaSupplyChainsActPageHeaderVisible()," User is not redirected to 'California Supply Chains' Act Page");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- validate Shop Skincare-menu navigation PROMOTIONS (PWS.com)
	 * Assertions:
	 * Page URL should contain 'Promotions'
	 * 'FEATURED' should be visible on page Header
	 */	
	//*QTest ID TC-886 Shop Skincare-menu navigation PROMOTIONS (PWS.com)
	@Test(enabled=true)
	public void testShopSkincareFeaturedFromCOMPWS_886(){
		String promotions="Promotions";
		String currentURL=null;
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(TestConstantsRFL.REGIMEN_NAME_FEATURED);
		currentURL=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(promotions),"Expected URL should contain"+promotions+" But actual on UI is: "+currentURL);
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open COM, Click on Country Toggle Select AUS and verify
	 * Assertions:
	 * AUS Should be present in Country Toggle
	 * User should redirected to AUS Country after clicking on 'AUS' from country toggle
	 */
	//QTest ID TC-927 PWS COM> Verify the User is redirected to the Corporate Australian Home Page
	@Test(enabled=false)//not there in qTest Automation, need to remove
	public void testVerifyTheUserIsRedirectedToTheCorporateAustralianHomePageCOMPWS_927(){
		String countryName="AUS";
		String url="rodanandfields.com.au";
		String currentUrl=null;
		openCOMSite();
		storeFrontHeirloomHomePage.clickCountryToggle();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isCountryPresentInCountryToggle(countryName)," Expected country: "+countryName+" not present in country toggle");
		storeFrontHeirloomHomePage.selectCountry(countryName);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(url),"User is not redirected to expected country:"+countryName+" Url should contains: "+url+" But actual on UI is: "+currentUrl);
		s_assert.assertAll();
	}	

	//QTest ID TC-2408 Registering the PC using Retail customer's email id from COM
	@Test
	public void testRegisteringThePCUsingRetailCustomerEmailId_2408(){
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String lastName = TestConstantsRFL.LAST_NAME+randomNum;
		String gender = TestConstantsRFL.GENDER_MALE;
		String rcEmailID = null;
		if(shouldFetchDataFromExcelForTokeniation){
			rcEmailID=getActiveUserFromExcel(TestConstantsRFL.RC_USER_TYPE);	
		}
		else{
			rcEmailID =  getRandomUserFromDB(TestConstantsRFL.USER_TYPE_RC);
		}
		openCOMSite();
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickClickHereLinkForPC();
		storeFrontHeirloomHomePage.clickEnrollNowBtnForPCAndRC();
		storeFrontHeirloomHomePage.enterProfileDetailsForPCAndRC(firstName,lastName,rcEmailID,password,phnNumber,gender);
		storeFrontHeirloomHomePage.clickContinueBtn();
		s_assert.assertTrue(storeFrontHeirloomHomePage.validateExistingRCPopUpCom(),"Existing RC Pop Up is not displayed!!");
		s_assert.assertAll();
	}

	/**
	 * Test Summary: Open COM PWS, login with PC User, place Ad-hoc order with new
	 * Shipping address Assertions: Newly created Shipping profile at order
	 * confirmation page
	 */
	// QTest ID TC-2482 Adhoc Order with Newly created shipping address
	@Test
	public void testAdhocOrderWithNewlyCreatedShippingAddressFromCOM_2482() {
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String addressName = "Home";
		String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME + randomNumber;
		String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME;
		String userEmailId = null;
		String shippingProfileName = shippingProfileFirstName + " " + shippingProfileLastName;
		String shippingName = null;
		String shippingAddress = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomHomePage.clickChangeShippingAddressBtn();
		storeFrontHeirloomHomePage.enterShippingProfileDetailsForPWS(addressName, shippingProfileFirstName,shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseThisAddressShippingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickContinueBtnOnBillingPage();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(),"Adhoc order not placed successfully from COM PWS site.");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"),"Order confirmation message does not contains email confirmation");
		shippingName = storeFrontHeirloomHomePage.getShippingNameFromOrderConfirmationPage();
		s_assert.assertTrue(shippingProfileName.contains(shippingName), "Shipping Profile is not the same as entered");
		shippingAddress = storeFrontHeirloomHomePage.getAddressFromOrderConfirmationPage();
		s_assert.assertTrue(shippingAddress.contains(addressLine1.toUpperCase()),"Shipping Address is not the same as entered");	
		s_assert.assertAll();
	}


	/**
	 * Test Summary: Open COM PWS, login with PC User, place adhoc order with new billing address
	 * Assertions:
	 * Newly created billing profile at order confirmation page 
	 */
	//QTest ID TC-2483 Adhoc Order with Newly created billing address
	@Test
	public void testAdhocOrderWithNewlyCreatedBillingAddressFromOM_2483(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstantsRFL.FIRST_NAME;
		String nameOnCard = firstName;
		String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
		String userEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomPCUserPage.mouseHoverShopSkinCareAndClickLink(regimen);
		storeFrontHeirloomPCUserPage.clickAddToCartButtonAfterLogin();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomHomePage.clickCompleteOrderBtn();
		storeFrontHeirloomHomePage.clickOKBtnOnPopup();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from COM PWS site.");
		s_assert.assertTrue(storeFrontHeirloomHomePage.getBillingAddressName().contains(billingProfileFirstName), "Expected billing profile name is: "+billingProfileFirstName+" Actual on UI is: "+storeFrontHeirloomHomePage.getBillingAddressName());
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}

	/***
	 * Test Summary:- Open COM PWS, Login with PC User, and verify
	 * Assertions:
	 * Verify sponsor name on the top of the page
	 */ 
	//QTest ID TC-2153 Sponsor infor_PWS.com
	@Test
	public void testSponsorInfo_MyAccountFromCOMPWS_2153(){
		String userEmailId = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				userEmailId=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				userEmailId = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
				storeFrontHeirloomHomePage.loginAsPCUser(userEmailId,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		storeFrontHeirloomHomePage.clickMyAccountLink();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSponsorInfoPresentOnTopOfPage(),"Sponsor Info not Available on Top of Page");
		s_assert.assertAll();
	}	

	/***
	 * Test Summary:- placed an adhoc order with consultant only buy product promotion
	 *  and validate orders details like subtotal & product name from com site
	 */
	//*QTest ID TC-906 Consultants Only -buy product promotion 
	@Test(enabled=true)
	public void consultantsOnlyBuyProductPromotion_906(){
		int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
		String billingName =TestConstantsRFL.BILLING_PROFILE_NAME;
		String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
		String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
		String consultantEmailID = null;
		openCOMSite();
		for(int i=1;i<=5;i++){
			if(shouldFetchDataFromExcelForTokeniation){
				consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
			}
			else{
				consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
				storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
				if(storeFrontHeirloomHomePage.isLoginFailed()==false){
					break;
				}
				else
					continue;
			}
		}
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
		storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
		storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_PRODUCT_PROMOTION);
		s_assert.assertTrue(storeFrontHeirloomHomePage.getCurrentURL().toLowerCase().contains("consultant-only"), "Expected url contains is: consultant-only but Actual on UI is "+storeFrontHeirloomHomePage.getCurrentURL().toLowerCase());
		storeFrontHeirloomConsultantPage.clickAddToCartBtn();
		storeFrontHeirloomConsultantPage.clickCheckoutBtn();
		storeFrontHeirloomConsultantPage.clickContinueBtn();
		storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
		storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
		storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		storeFrontHeirloomConsultantPage.clickCompleteOrderBtn();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isThankYouTextPresentAfterOrderPlaced(), "Order is not placed successfully");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.getOrderConfirmationTextMsgAfterOrderPlaced().contains("You will receive an email confirmation shortly"), "Order confirmation message does not contains email confirmation");
		s_assert.assertAll();
	}
	
	//QTest ID TC-2424 COM> Donation order 
		@Test(enabled=true)//should be a manual test
		public void testDonationOrderFromCOM_2424(){
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			String billingProfileFirstName = TestConstantsRFL.BILLING_PROFILE_FIRST_NAME;
			String billingProfileLastName = TestConstantsRFL.BILLING_PROFILE_LAST_NAME+randomNumber;
			List<Map<String, Object>> randomOrderList =  null;
			String consultantEmailID = null;
			openCOMSite();
			for(int i=1;i<=5;i++){
				if(shouldFetchDataFromExcelForTokeniation){
					consultantEmailID=getActiveUserFromExcel(TestConstantsRFL.CONS_USER_TYPE);	
				}
				else{
					consultantEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_CONSULTANT);
					storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
					if(storeFrontHeirloomHomePage.isLoginFailed()==false){
						break;
					}
					else
						continue;
				}
			}
			s_assert.assertTrue(storeFrontHeirloomConsultantPage.verifyUserSuccessfullyLoggedIn(),"consultant is not logged in successfully");
			//storeFrontHeirloomConsultantPage.mouseHoverShopSkinCareAndClickLink("CONSULTANT-ONLY PRODUCTS");
			//storeFrontHeirloomConsultantPage.clickConsultantOnlyProduct(TestConstantsRFL.CONSULTANT_ONLY_EVENT_SUPPORT);
			storeFrontHeirloomHomePage.clickDonationProduct();
			//storeFrontHeirloomHomePage.addDonationProductToBag();
			storeFrontHeirloomHomePage.clickCheckoutBtn();
			storeFrontHeirloomHomePage.clickContinueBtn();
			storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
			storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber, CVV);
			storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			storeFrontHeirloomHomePage.clickCompleteOrderBtn();
			s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(), "Adhoc order not placed successfully from biz site for Consultant user.");
			s_assert.assertAll();
		}
		

	/***
		 * Test Summary:- Open com url, Login with PC, start to place an adhoc order, add billing profile with INVALID credit card and place the order
		 * Assertions:
		 * Error message should be displayed for INVALID credit card after placed an order
		 */	
		//QTest ID TC-2409 COM> Payment error message
		@Test
		public void testPaymentErrorMessageFromCOM_2409(){
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
			String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME+randomNumber;
			String errorMessageFromUI = null;
			String errorMessage = TestConstantsRFL.ERROR_MSG_FOR_INVALID_CC;
			String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
			String pcEmailID = null;
			String cardNumber = TestConstantsRFL.INVALID_CARD_NUMBER;
			if(shouldFetchDataFromExcelForTokeniation){
				pcEmailID=getActiveUserFromExcel(TestConstantsRFL.PC_USER_TYPE);	
			}
			else{
				pcEmailID = getRandomUserFromDB(TestConstantsRFL.USER_TYPE_PC);
			}
			openCOMSite();
			storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
			//storeFrontHeirloomHomePage.selectRegimen(regimen);
			storeFrontHeirloomHomePage.clickAddToCartBtn();
			storeFrontHeirloomHomePage.clickCheckoutBtn();
			storeFrontHeirloomHomePage.loginAsUserOnCheckoutPage(pcEmailID, password);
			storeFrontHeirloomHomePage.clickContinueBtn();
			storeFrontHeirloomHomePage.clickChangeBillingInformationBtn();
			storeFrontHeirloomHomePage.enterBillingInfo(billingName, billingProfileFirstName, billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
			storeFrontHeirloomHomePage.clickUseThisBillingInformationBtn();
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		/*	storeFrontHeirloomHomePage.clickCompleteOrderBtn();
			storeFrontHeirloomHomePage.clickOKBtnOnPopup();*/
			errorMessageFromUI = storeFrontHeirloomHomePage.getErrorMessageForInvalidCreditCard();
			s_assert.assertTrue(errorMessageFromUI.contains(errorMessage), "Expected error message is "+errorMessage+"actual on UI is "+errorMessageFromUI);
			s_assert.assertAll();
		}
		
		
	// QTest ID TC-2407 PC Enrollment from COM
		@Test(enabled=true)
		public void testPCEnrollmentFromCOM_2407() {
			int randomNum = CommonUtils.getRandomNum(10000, 1000000);
			int randomNumber = CommonUtils.getRandomNum(10000, 1000000);
			String firstName = TestConstantsRFL.FIRST_NAME;
			String lastName = TestConstantsRFL.LAST_NAME + randomNum;
			String shippingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
			String shippingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME + randomNumber;
			String emailAddress = firstName + randomNum + "@rodanandfields.com";
			String regimen = TestConstantsRFL.REGIMEN_NAME_REVERSE;
			String gender = TestConstantsRFL.GENDER_MALE;
			String addressName = "Home";
			String orderNumber=null;
			String subTotal = null;
			String tax = null; 
			String grandTotal = null;
			String grandTotalFromDB=null;
			String taxFromDB=null;
			String shippingFromDB=null;
			String subTotalFromDB=null;
			String orderHistory = "Order History";
			String billingName = TestConstantsRFL.BILLING_PROFILE_NAME;
			String billingProfileFirstName = TestConstantsRFL.SHIPPING_PROFILE_FIRST_NAME;
			String billingProfileLastName = TestConstantsRFL.SHIPPING_PROFILE_LAST_NAME + randomNumber;
			// Navigating to COM PWS
			openCOMSite();
			storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
			storeFrontHeirloomHomePage.clickAddToCartBtn();
			storeFrontHeirloomHomePage.clickCheckoutBtn();
			storeFrontHeirloomHomePage.clickClickHereLinkForPC();
			s_assert.assertTrue(driver.getCurrentUrl().contains("PCProgram"),"After clicking click here link for PC not navigated to PC Enrollment page.");
			storeFrontHeirloomHomePage.clickEnrollNowBtnForPCAndRC();
			storeFrontHeirloomHomePage.enterProfileDetailsForPCAndRC(firstName, lastName, emailAddress, password,phnNumber, gender);
			storeFrontHeirloomHomePage.clickContinueBtn();
			storeFrontHeirloomHomePage.clickContinueBtn();
			s_assert.assertTrue(storeFrontHeirloomHomePage.verifyErrorMessageForTermsAndConditionsForPCAndRC(),"Terms and candition error message not present for PC User.");
			storeFrontHeirloomHomePage.checkTermsAndConditionChkBoxForPCAndRC();
			storeFrontHeirloomHomePage.clickContinueBtn();
			storeFrontHeirloomHomePage.clickContinueBtnOnAutoshipSetupPageForPC();
			storeFrontHeirloomHomePage.clickContinueBtn();
			storeFrontHeirloomHomePage.enterShippingProfileDetails(addressName, shippingProfileFirstName,shippingProfileLastName, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickContinueBtn();
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			storeFrontHeirloomHomePage.enterBillingInfoDetails(billingName, billingProfileFirstName,billingProfileLastName, nameOnCard, cardNumber, expMonth, expYear, addressLine1, postalCode, phnNumber,CVV);
			storeFrontHeirloomHomePage.clickContinueBtn();
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			storeFrontHeirloomHomePage.clickCompleteEnrollmentBtn();
			storeFrontHeirloomHomePage.clickOKBtnOfJavaScriptPopUp();
			s_assert.assertTrue(storeFrontHeirloomHomePage.isPCEnrollmentCompletedSuccessfully(),"PC enrollment is not completed successfully");
			//s_assert.assertTrue(storeFrontHeirloomHomePage.isThankYouTextPresentAfterOrderPlaced(),"Enrollment is not completed successfully");
			orderNumber = storeFrontHeirloomHomePage.getOrderNumberFromOrderConfirmationPage();
			storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
			storeFrontHeirloomPCUserPage.clickHeaderLinkAfterLogin(orderHistory);
			//storeFrontHeirloomPCUserPage.clickOrderManagementSublink(orderHistory);
			s_assert.assertTrue(storeFrontHeirloomPCUserPage.isOrderNumberPresentAtOrderHistoryPage(), "Order number is not present at order history page");
			storeFrontHeirloomHomePage.clickViewDetailsLinkInOrderHistoryForPC("1");
			subTotal = storeFrontHeirloomHomePage.getSubtotalFromOrderHistoryPageForPC();
			tax = storeFrontHeirloomHomePage.getTaxFromOrderHistoryPageForPC();
			grandTotal = storeFrontHeirloomHomePage.getGrandTotalFromOrderHistoryPageForPC();
			List<Map<String, Object>> orderList = DBUtil.performDatabaseQuery(DBQueries_RFL.callQueryWithArguement(DBQueries_RFL.GET_ORDER_DETAILS, orderNumber), RFL_DB);
			grandTotalFromDB = String.valueOf(getValueFromQueryResult(orderList, "GrandTotal"));
			s_assert.assertTrue(grandTotalFromDB.contains(grandTotal),"Grand Total from DB is:" + grandTotalFromDB + " different from UI is:" + grandTotal);
			taxFromDB = String.valueOf(getValueFromQueryResult(orderList, "TaxAmountTotal"));
			s_assert.assertTrue(taxFromDB.contains(tax), "Tax from DB is:" + taxFromDB + " different from UI is:" + tax);
			shippingFromDB = String.valueOf(getValueFromQueryResult(orderList, "ShippingTotal"));
			s_assert.assertTrue(shippingFromDB.contains("0.0"),"shipping Total from DB is:" + shippingFromDB + " different from UI is:'0.0'");
			subTotalFromDB = String.valueOf(getValueFromQueryResult(orderList, "Subtotal"));
			s_assert.assertTrue(subTotalFromDB.contains(subTotal),"SubTotal from DB is:" + subTotalFromDB + " different from UI is:" + subTotal);
			s_assert.assertAll();
		}	
		
		
	/***
		 * Test Summary:- validate the UNBLEMISH category links and their sublinks(Results and FAQs) under shopskincare from com site
		 *  
		 */
		//*QTest ID TC-889:Shop Skincare-menu navigation UNBLEMISH (PWS.com)
		@Test(enabled=true)
		public void testShopSkincareMenuNavigationUnblemishFromCom_889(){
			String category = "UNBLEMISH";
			String subLinkResults = "Results";
			String subLinkFAQ = "FAQ";
			String currentURL = null;
			openCOMSite();
			storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(category);
			currentURL = storeFrontHeirloomHomePage.getCurrentURL().toLowerCase();
			s_assert.assertTrue(currentURL.contains(category.toLowerCase()),category+"category link is not redirected to"+category+" category page and current url is "+currentURL);
			storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
			storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category, subLinkResults);
			currentURL = storeFrontHeirloomHomePage.getCurrentURL();
			s_assert.assertTrue(currentURL.toLowerCase().contains(subLinkResults.toLowerCase()), "Expected sublink in url is "+subLinkResults.toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
			s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToResultsPage(category),"user is not on results page");
			storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
			storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickSubLink(category,subLinkFAQ);
			currentURL = storeFrontHeirloomHomePage.getCurrentURL();
			s_assert.assertTrue(currentURL.toLowerCase().contains("CommonQuestions".toLowerCase()), "Expected sublink in url is CommonQuestions".toLowerCase()+" Actual on UI is "+currentURL.toLowerCase());
			s_assert.assertTrue(storeFrontHeirloomHomePage.verifyUserIsRedirectedToFAQsPage(category),"user is not on FAQs page");
			s_assert.assertAll();
		}	
}