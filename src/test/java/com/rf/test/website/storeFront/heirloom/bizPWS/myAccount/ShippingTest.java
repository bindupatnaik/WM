package com.rf.test.website.storeFront.heirloom.bizPWS.myAccount;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ShippingTest extends RFHeirloomWebsiteMyAccountBaseTest{

	String bizPWS = null;
	
	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(bizPWS+"/my-account/address-book");
	}
	
	@BeforeGroups("myAccountShippingConsBIZ")
	public void loginWithConsultantBIZAndNavigateToShippingProfile(){
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToShippingProfile();
	}

	@BeforeGroups("myAccountShippingBIZPC")
	public void loginWithPCBIZAndNavigateToShippingProfile(){
		logout();
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToShippingProfile();
	}
	
	@BeforeGroups("myAccountShippingBIZRC")
	public void loginWithRCBIZAndNavigateToShippingProfile(){
		logout();
		bizPWS = openBIZSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_RC);
		navigateToShippingProfile();
	}
	
	/**
	 * Jira Story Id: MAIN-5258
	 * qTest Id: TC-2786
	 */
	@Test(priority=1,alwaysRun=true,groups="myAccountShippingConsBIZ",description = "Edit existing shipping profile for biz site")
	public void testEditExistingShippingProfileForBizSite_2786() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editFirstAndLastNameForExistingShippingProfile(shippingProfileFirstName, shippingProfileLastName+randomNum);
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Edited Shipping profile not present on page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5258
	 * qTest Id: TC-2788
	 */
	@Test(priority=2,alwaysRun=true,groups="myAccountShippingConsBIZ",description = "Verify changes are not saved after clicking cancel(biz site)")
	public void testChangesNotSavedAfterClickingCancelInShippingDetails_BIZSite_2788() {
		List<String> listOfShippindDetails=null;
		String profileName = "My Profile";
		String firstName = "My First Name";
		String lastName = "My Last Name";
		String address = "My Address";
		listOfShippindDetails=storeFrontHeirloomBillingAndShippingProfilePage.getShippingDetailsForProfile1();
		storeFrontHeirloomBillingAndShippingProfilePage.editShippingDetailsForProfile1AndCancel(profileName,firstName,lastName,address);
		s_assert.assertFalse(listOfShippindDetails.get(0).equals(profileName),"Profile Name is Matching");
		s_assert.assertFalse(listOfShippindDetails.get(1).contains(firstName),"First name is matching");
		s_assert.assertFalse(listOfShippindDetails.get(2).equals(address),"Address is matching");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5258
	 * qTest Id: TC-2790
	 */
	@Test(priority=3,alwaysRun=true,groups="myAccountShippingConsBIZ",description = "Verify changes saved after checkbox is selected for future CRP orders for biz site")
	public void testVerifyChangesSavedAfterCheckboxIsSelectedForFutureCRPOrdersForBizSite_2790() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingFirstAndLastName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditNonDefaultShippingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editFirstAndLastNameInShippingProfileAndCheckForFutureAutoshipCRP(shippingProfileFirstName, shippingProfileLastName+randomNum);
		shippingFirstAndLastName = shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyEditedShippingProfileAndMarkedAsDefaultCRPAddressPresentOnPage(shippingFirstAndLastName)," Newly Edited shipping profile for future CRP Orders Not present on page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2973
	 */
	@Test(priority=4,alwaysRun=true,groups="myAccountShippingConsBIZ",description = "Verify when consultant Selects the Default radio button on one of the Shipping profiles that is not selected as default(BIZ site)")
	public void testWhenUserSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_BIZSite_2973() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int shippingProfilesCount=0;
		String addressName = "Home";
		String shippingProfileName=null;
		String nonDefaultProfileName=null;
		
		shippingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getShippingProfilesCount();
		if(shippingProfilesCount<2) {
			storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndSave(addressName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
			assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		}
		else{
			shippingProfileName = storeFrontHeirloomBillingAndShippingProfilePage.getFullNameInShippingProfile();
		}
		
		nonDefaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getFirstNonDefaultProfileName();
		storeFrontHeirloomBillingAndShippingProfilePage.selectShippingOrBillingProfileAsDefault(nonDefaultProfileName);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"Overlay doesn't contains specified text");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isHeaderTextOnAlertWhenSelectingShippingDetailsAsDefaultDisplayed(),"Required Header text is not present on the alert ");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"The Alert is not displayed with the required text");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isSaveButtonOnOverlayDisplayed(),"The Alert is not displayed with save Button");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCancelButtonOnOverlayDisplayed(),"The Alert is not displayed with Cancel Button");
		storeFrontHeirloomBillingAndShippingProfilePage.clickCancelButtonOnOverlay();
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isHeaderTextOnAlertWhenSelectingShippingDetailsAsDefaultDisplayed(),"Required Header text is present on the alert ");
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"The Alert is displayed with the required text");
		s_assert.assertAll();	
	}
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3179
	 */
	@Test(priority=5,alwaysRun=true,groups="myAccountShippingConsBIZ",description = "Verify Consolidated checbox for add new shipping  address (Biz/consultant)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForCons_3179() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3187
	 */
	@Test(priority=6,alwaysRun=true,groups="myAccountShippingConsBIZ",description = "Verify Consolidated checbox for edit existing shipping  address (Biz/consultant)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForCons_3187() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8418
	 * qTest Id: TC-2877
	 */
	@Test(priority=7,alwaysRun=true,groups="myAccountShippingConsBIZ",description = "PWS .BIZ Consultant,Display the following checkboxes on Shipping details Page for Add new Address option.  ")
	public void testVerifyCheckboxOnAddShippingForCons_2877() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		String defaultProfileName=null;
		String myAccountCategory=TestConstantsRFL.EDIT_CRP_CART_CATEGORY;
		shippingProfileName= "TestProfile"+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndCancel(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile Should not be present on page if cancel button clicked instead of save");
		storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageWithCheckboxCheckedAndSave(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, shippingProfileName,"the shipping name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on adhoc cart");
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on autoship cart");
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8418
	 * qTest Id: TC-2878
	 */
	@Test(priority=8,alwaysRun=true,groups="myAccountShippingConsBIZ",description = "PWS .BIZ Consultant,Display the following checkboxes on Shipping details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditShippingForCons_2878() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		String myAccountCategory=TestConstantsRFL.EDIT_CRP_CART_CATEGORY;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox().clickSaveButtonInShippingPage();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();			
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on adhoc cart");
		storeFrontHeirloomHomePage.clickOnRodanAndFieldsLogo();
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		driver.pauseExecutionFor(3000);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on autoship cart");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2974
	 */
	@Test(priority=9,alwaysRun=true,groups="myAccountShippingBIZPC",description = "Verify when PC selects the Default radio button for one the existing billing profiles that is not selected as Default(BIZ site)")
	public void testWhenPCSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_BIZSite_2974() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int shippingProfilesCount=0;
		String addressName = "Home";
		String shippingProfileName=null;
		String nonDefaultProfileName=null;
		
		shippingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getShippingProfilesCount();
		if(shippingProfilesCount<2) {
			storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndSave(addressName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
			assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		}
		else{
			shippingProfileName = storeFrontHeirloomBillingAndShippingProfilePage.getFullNameInShippingProfile();
		}
		
		nonDefaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getFirstNonDefaultProfileName();
		storeFrontHeirloomBillingAndShippingProfilePage.selectShippingOrBillingProfileAsDefault(nonDefaultProfileName);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"Overlay doesn't contains specified text");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isHeaderTextOnAlertWhenSelectingShippingDetailsAsDefaultDisplayed(),"Required Header text is not present on the alert ");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"The Alert is not displayed with the required text");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isSaveButtonOnOverlayDisplayed(),"The Alert is not displayed with save Button");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCancelButtonOnOverlayDisplayed(),"The Alert is not displayed with Cancel Button");
		storeFrontHeirloomBillingAndShippingProfilePage.clickCancelButtonOnOverlay();
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isHeaderTextOnAlertWhenSelectingShippingDetailsAsDefaultDisplayed(),"Required Header text is present on the alert ");
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"The Alert is displayed with the required text");
		s_assert.assertAll();	
	}

	/**
	 * Jira Story Id: MAIN-8418
	 * qTest Id: TC-2883
	 */
	@Test(priority=10,alwaysRun=true,groups="myAccountShippingBIZPC",description = "PWS .BIZ PC,Display the following checkboxes on Shipping details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddShippingForPC_2883() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		String defaultProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndCancel(shippingProfileName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile Should not be present on page if cancel button clicked instead of save");
		storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageWithCheckboxCheckedAndSave(shippingProfileName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, shippingProfileName,"the shipping name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on adhoc cart");
		s_assert.assertAll();	
	}
	
	/**
	 * Jira Story Id: MAIN-8418
	 * qTest Id: TC-2884
	 */
	@Test(priority=11,alwaysRun=true,groups="myAccountShippingBIZPC",description = "PWS .BIZ PC,Display the following checkboxes on Shipping details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditShippingForPC_2884() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox().clickSaveButtonInShippingPage();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();			
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on adhoc cart");
		s_assert.assertAll();	
	}
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3180
	 */
	@Test(priority=12,alwaysRun=true,groups="myAccountShippingBIZPC",description = "Verify Consolidated checbox for add new shipping  address (Biz/PC)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForPC_3180() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3188
	 */
	@Test(priority=13,alwaysRun=true,groups="myAccountShippingBIZPC",description = "Verify Consolidated checbox for edit existing shipping  address (Biz/PC)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForPC_3188() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	 /* Jira Story Id: MAIN-8418
	 * qTest Id: TC-2889
	 */
	@Test(priority=14,alwaysRun=true,groups="myAccountShippingBIZRC",description = "PWS .BIZ RC,Display the following checkboxes on Shipping details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddShippingForRC_2889() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		String defaultProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndCancel(shippingProfileName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile Should not be present on page if cancel button clicked instead of save");
		storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageWithCheckboxCheckedAndSave(shippingProfileName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		defaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getDefaultShippingOrBillingProfileName();
		s_assert.assertEquals(defaultProfileName, shippingProfileName,"the shipping name should come as default if checkbox is checked");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on adhoc cart");
		s_assert.assertAll();	
	}
	
	/**
	 * Jira Story Id: MAIN-8418
	 * qTest Id: TC-2890
	 */
	@Test(priority=15,alwaysRun=true,groups="myAccountShippingBIZRC",description = "PWS .BIZ RC,Display the following checkboxes on Shipping details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditShippingForRC_2890() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox().clickSaveButtonInShippingPage();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();			
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on adhoc cart");
		s_assert.assertAll();	
	}

	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3181
	 */
	@Test(priority=16,alwaysRun=true,groups="myAccountShippingBIZRC",description = "Verify Consolidated checbox for add new shipping  address (Biz/RC)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForRC_3181() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3189
	 */
	@Test(priority=17,alwaysRun=true,groups="myAccountShippingBIZRC",description = "Verify Consolidated checbox for edit existing shipping  address (Biz/RC)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForRC_3189() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2975
	 */
	@Test(priority=18,alwaysRun=true,groups="myAccountShippingBIZRC",description = "Verify when RC selects the Default radio button for one the existing billing profiles that is not selected as Default(BIZ site)")
	public void testWhenRCSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_BIZSite_2975() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int shippingProfilesCount=0;
		String addressName = "Home";
		String shippingProfileName=null;
		String nonDefaultProfileName=null;
		
		shippingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getShippingProfilesCount();
		if(shippingProfilesCount<2) {
			storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndSave(addressName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
			assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		}
		else{
			shippingProfileName = storeFrontHeirloomBillingAndShippingProfilePage.getFullNameInShippingProfile();
		}
		
		nonDefaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getFirstNonDefaultProfileName();
		storeFrontHeirloomBillingAndShippingProfilePage.selectShippingOrBillingProfileAsDefault(nonDefaultProfileName);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"Overlay doesn't contains specified text");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isHeaderTextOnAlertWhenSelectingShippingDetailsAsDefaultDisplayed(),"Required Header text is not present on the alert ");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"The Alert is not displayed with the required text");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isSaveButtonOnOverlayDisplayed(),"The Alert is not displayed with save Button");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCancelButtonOnOverlayDisplayed(),"The Alert is not displayed with Cancel Button");
		storeFrontHeirloomBillingAndShippingProfilePage.clickCancelButtonOnOverlay();
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isHeaderTextOnAlertWhenSelectingShippingDetailsAsDefaultDisplayed(),"Required Header text is present on the alert ");
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"The Alert is displayed with the required text");
		s_assert.assertAll();	
	}
	
}