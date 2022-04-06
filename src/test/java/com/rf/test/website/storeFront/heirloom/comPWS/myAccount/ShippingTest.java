package com.rf.test.website.storeFront.heirloom.comPWS.myAccount;

import java.util.List;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ShippingTest extends RFHeirloomWebsiteMyAccountBaseTest{

	String comPWS = null;
	
	@BeforeMethod
	public void refreshBillingPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
			driver.get(comPWS+"/my-account/address-book");
	}
	
	@BeforeGroups("myAccountShippingCOMCons")
	public void loginWithConsultantCOMAndNavigateToShippingProfile(){
		logout();
		comPWS = openCOMSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToShippingProfile();
	}
	
	@BeforeGroups("myAccountShippingCOMPC")
	public void loginWithPCCOMAndNavigateToShippingProfile(){
		logout();
		comPWS = openCOMSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToShippingProfile();
	}

	@BeforeGroups("myAccountShippingCOMRC")
	public void loginWithRCCOMAndNavigateToShippingProfile(){
		logout();
		comPWS = openCOMSite();
		getUserAndLogin(TestConstantsRFL.USER_TYPE_RC);
		navigateToShippingProfile();
	}
	
	/**
	 * Jira Story Id: MAIN-5258
	 * qTest Id: TC-2787
	 */
	@Test(priority=1,alwaysRun=true,groups="myAccountShippingCOMCons",description = "Edit existing shipping profile for com site")
	public void testEditExistingShippingProfileForComSite_2787() {
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
	 * qTest Id: TC-2789
	 */
	@Test(priority=2,alwaysRun=true,groups="myAccountShippingCOMCons",description = "Verify changes are not saved after clicking cancel(COM site)")
	public void testChangesNotSavedAfterClickingCancelInShippingDetails_COMSite_2789() {
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
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3191
	 */
	@Test(priority=3,alwaysRun=true,groups="myAccountShippingCOMCons",description = "Verify Consolidated checbox for edit existing shipping  address (com/consultant)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForCons_3191() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2970
	 */
	@Test(priority=4,alwaysRun=true,groups="myAccountShippingCOMCons",description = "Verify when consultant Selects the Default radio button on one of the Shipping profiles that is not selected as default(Com site)")
	public void testWhenUserSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_ComSite_2970() {
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
	 * qTest Id: TC-2879
	 */
	@Test(priority=5,alwaysRun=true,groups="myAccountShippingCOMCons",description = "PWS .COM Consultant,Display the following checkboxes on Shipping details Page for Add new Address option.  ")
	public void testVerifyCheckboxOnAddShippingForCons_2879() {
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
	 * qTest Id: TC-2880
	 */
	@Test(priority=6,alwaysRun=true,groups="myAccountShippingCOMCons",description = "PWS .COM Consultant,Display the following checkboxes on Shipping details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditShippingForCons_2880() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox().clickSaveButtonInShippingPage();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();			
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		storeFrontHeirloomHomePage.mouseHoverShopSkinCareAndClickLinkOnPWS(regimen);
		storeFrontHeirloomHomePage.clickAddToCartBtn();
		storeFrontHeirloomHomePage.clickCheckoutBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly created and default shipping profile name not present on adhoc cart");
		s_assert.assertAll();	
	}
	
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3182
	 */
	@Test(priority=7,alwaysRun=true,groups="myAccountShippingCOMCons",description = "Verify Consolidated checbox for add new shipping  address (com/consultant)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForCons_3182() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3190
	 */
	@Test(priority=8,alwaysRun=true,groups="myAccountShippingCOMCons",description = "Verify Consolidated checbox for edit existing shipping  address (com/consultant)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForCons_3190() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2971
	 */
	@Test(priority=9,alwaysRun=true,groups="myAccountShippingCOMPC",description = "Verify when PC selects the Default radio button for one the existing billing profiles that is not selected as Default(Com site)")
	public void testWhenPCSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_ComSite_2971() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int shippingProfilesCount=0;
		String shippingProfileName=null;
		String nonDefaultProfileName=null;
		
		shippingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getShippingProfilesCount();
		if(shippingProfilesCount<2) {
			shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
			storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndSave(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
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
	 * qTest Id: TC-2885
	 */
	@Test(priority=10,alwaysRun=true,groups="myAccountShippingCOMPC",description = "PWS .COM PC,Display the following checkboxes on Shipping details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddShippingForPC_2885() {
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
	 * qTest Id: TC-2886
	 */
	@Test(priority=11,alwaysRun=true,groups="myAccountShippingCOMPC",description = "PWS .COM PC,Display the following checkboxes on Shipping details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditShippingForPC_2886() {
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
	 * qTest Id: TC-3183
	 */
	@Test(priority=12,alwaysRun=true,groups="myAccountShippingCOMPC",description = "Verify Consolidated checbox for add new shipping  address (com/PC)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForPC_3183() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3192
	 */
	@Test(priority=13,alwaysRun=true,groups="myAccountShippingCOMPC",description = "Verify Consolidated checbox for edit existing shipping  address (com/PC)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForPC_3192() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	 /* Jira Story Id: MAIN-8418
	 * qTest Id: TC-2891
	 */
	@Test(priority=14,alwaysRun=true,groups="myAccountShippingCOMRC",description = "PWS .BIZ RC,Display the following checkboxes on Shipping details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddShippingForRC_2891() {
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
	 * qTest Id: TC-2892
	 */
	@Test(priority=15,alwaysRun=true,groups="myAccountShippingCOMRC",description = "PWS .COM RC,Display the following checkboxes on Shipping details Page for edit existing address option. ")
	public void testVerifyCheckboxOnEditShippingForRC_2892() {
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
	 * qTest Id: TC-3184
	 */
	@Test(priority=16,alwaysRun=true,groups="myAccountShippingCOMRC",description = "Verify Consolidated checbox for add new shipping  address (com/RC)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForRC_3184() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3193
	 */
	@Test(priority=17,alwaysRun=true,groups="myAccountShippingCOMRC",description = "Verify Consolidated checbox for edit existing shipping  address (com/RC)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForRC_3193() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2972
	 */
	@Test(priority=18,alwaysRun=true,groups="myAccountShippingCOMRC",description = "Verify when RC selects the Default radio button for one the existing billing profiles that is not selected as Default(Com site)")
	public void testWhenRCSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_ComSite_2972() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int shippingProfilesCount=0;
		String shippingProfileName=null;
		String nonDefaultProfileName=null;
		
		shippingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getShippingProfilesCount();
		if(shippingProfilesCount<2) {
			shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
			storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndSave(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
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