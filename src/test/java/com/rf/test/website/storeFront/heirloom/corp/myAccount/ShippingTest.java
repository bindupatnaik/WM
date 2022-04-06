package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.SoftAssert;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.test.website.RFHeirloomWebsiteMyAccountBaseTest;

public class ShippingTest extends RFHeirloomWebsiteMyAccountBaseTest{

	@BeforeMethod
	public void refreshPage(){
		checkAndCloseMoreThanOneWindows();
		s_assert = new SoftAssert();
		driver.get(driver.getURL()+"/my-account/address-book");
	}

	@BeforeGroups("myAccountShippingCorpCons")
	public void loginWithConsultantCorpAndNavigateToShippingProfile(){
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_CONSULTANT);
		navigateToShippingProfile();
	}

	@BeforeGroups("myAccountShippingCorpPC")
	public void loginWithPCCorpAndNavigateToShippingProfile(){
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_PC);
		navigateToShippingProfile();
	}

	@BeforeGroups("myAccountShippingCorpRC")
	public void loginWithrCCorpAndNavigateToShippingProfile(){
		logout();
		driver.get(driver.getURL());
		getUserAndLogin(TestConstantsRFL.USER_TYPE_RC);
		navigateToShippingProfile();
	}

	/**
	 * Jira Story Id: MAIN-7400
	 * qTest Id: TC-2775
	 */
	@Test(priority=1,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Create new shipping profile")
	public void testCreateNewShippingProfile_2775() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndSave(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		s_assert.assertAll();	
	}

	/**
	 * Jira Story Id: MAIN-7400
	 * qTest Id: TC-2776
	 */
	@Test(priority=2,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Verify User is able to select address as default") 
	public void testVerifyUserIsAbleToSelectAddressAsDefault_2776() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int shippingProfilesCount=0;
		String shippingProfileName=null;
		String nonDefaultProfileName=null;
		shippingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getShippingProfilesCount();
		if(shippingProfilesCount<2) {
			shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
			storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndSave(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			driver.pauseExecutionFor(3000);
			assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		}
		else{
			shippingProfileName = storeFrontHeirloomBillingAndShippingProfilePage.getFullNameInShippingProfile();
		}

		nonDefaultProfileName=storeFrontHeirloomBillingAndShippingProfilePage.getFirstNonDefaultProfileName();
		storeFrontHeirloomBillingAndShippingProfilePage.selectShippingOrBillingProfileAsDefault(nonDefaultProfileName);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isOverlayPresentWithSpecifiedText(),"Overlay doesn't contains specified text");
		storeFrontHeirloomBillingAndShippingProfilePage.clickSaveButtonOnOverlay();
		driver.pauseExecutionFor(3000);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyEditedShippingProfileAndMarkedAsDefaultCRPAddressPresentOnPage(shippingProfileName)," Newly Edited shipping profile for future CRP Orders Not present on page");
		s_assert.assertAll();	
	}

	/**
	 * Jira Story Id: MAIN-5258
	 * qTest Id: TC-2783
	 * @throws InterruptedException 
	 */
	@Test(priority=3,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Edit existing shipping profile for corp site")
	public void testEditExistingShippingProfileforCorpSite_2783() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String phnNumber = "9898989898";
		String shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox().clickSaveButtonInShippingPage();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();			
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5258
	 * qTest Id: TC-2784
	 */
	@Test(priority=4,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Verify changes are not saved after clicking cancel")
	public void testVerifyChangesNotSavedAfterClickingCancelInShippingDetails_2784() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		List<String> listOfShippindDetails=null;
		String profileName = "MyProfile"+randomNum;
		String firstName = "MyFirstName"+randomNum;
		String lastName = "MyLastName"+randomNum;
		String address = "MyAddress"+randomNum;
		listOfShippindDetails=storeFrontHeirloomBillingAndShippingProfilePage.getShippingDetailsForProfile1();
		storeFrontHeirloomBillingAndShippingProfilePage.editShippingDetailsForProfile1AndCancel(profileName,firstName,lastName,address);
		s_assert.assertFalse(listOfShippindDetails.get(0).equals(profileName),"Profile Name is Matching in CORP Site");
		s_assert.assertFalse(listOfShippindDetails.get(1).contains(firstName),"First name is matching in CORP Site");
		s_assert.assertFalse(listOfShippindDetails.get(2).equals(address),"Address is matching in CORP Site");
		//		openBIZSite();
		//		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		//		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		//		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isShippingProfilesPresent(),"Shipping profiles not present on the page in BIZ Site");
		//		listOfShippindDetails=storeFrontHeirloomBillingAndShippingProfilePage.getShippingDetailsForProfile1();
		//		storeFrontHeirloomBillingAndShippingProfilePage.editShippingDetailsForProfile1AndCancel(profileName,firstName,lastName,address);
		//		s_assert.assertFalse(listOfShippindDetails.get(0).equals(profileName),"Profile Name is Matching in BIZ Site");
		//		s_assert.assertFalse(listOfShippindDetails.get(1).contains(firstName),"First name is matching in BIZ Site");
		//		s_assert.assertFalse(listOfShippindDetails.get(2).equals(address),"Address is matching in BIZ Site");
		//		openCOMSite();
		//		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		//		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		//		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isShippingProfilesPresent(),"Shipping profiles not present on the page in COM Site");
		//		listOfShippindDetails=storeFrontHeirloomBillingAndShippingProfilePage.getShippingDetailsForProfile1();
		//		storeFrontHeirloomBillingAndShippingProfilePage.editShippingDetailsForProfile1AndCancel(profileName,firstName,lastName,address);
		//		s_assert.assertFalse(listOfShippindDetails.get(0).equals(profileName),"Profile Name is Matching in COM site");
		//		s_assert.assertFalse(listOfShippindDetails.get(1).contains(firstName),"First name is matching in COM site");
		//		s_assert.assertFalse(listOfShippindDetails.get(2).equals(address),"Address is matching in COM site");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5258
	 * qTest Id: TC-2785
	 */
	@Test(priority=5,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Verify changes saved after checkbox is selected for future CRP orders for corp site")
	public void testVerifyChangesSavedAfterCheckboxIsSelectedForFutureCRPOrdersForCorpSite_2785() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingFirstAndLastName=null;
		storeFrontHeirloomBillingAndShippingProfilePage.clickEditNonDefaultShippingProfile();
		storeFrontHeirloomBillingAndShippingProfilePage.editFirstAndLastNameInShippingProfileAndCheckForFutureAutoshipCRP(shippingProfileFirstName, shippingProfileLastName+randomNum);
		shippingFirstAndLastName = shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyEditedShippingProfileAndMarkedAsDefaultCRPAddressPresentOnPage(shippingFirstAndLastName)," Newly Edited shipping profile for future CRP Orders Not present on page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5257
	 * qTest Id: TC-2777
	 */
	@Test(priority=6,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Create new shipping profile")
	public void testCreateNewShippingProfile_2777() {		
		List<WebElement> listOfMandatoryFieldsInShippingDetailsPage;
		List<WebElement> listOfNonMandatoryFieldsInShippingDetailsPage;
		List<WebElement> cityStateName;		

		listOfMandatoryFieldsInShippingDetailsPage=storeFrontHeirloomBillingAndShippingProfilePage.clickAddANewShippingAddressLinkAndGetAllMandatoryFields();
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(0).getAttribute("id").contains("profileName"),"Profile Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(1).getAttribute("id").contains("firstName"),"First Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(2).getAttribute("id").contains("lastName"),"Last Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(3).getAttribute("id").contains("address1"),"Address1 field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(4).getAttribute("id").contains("zipCode"),"Zip Code field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(5).getAttribute("id").contains("phoneNumber"),"Phone Number field is not mandatory");
		listOfNonMandatoryFieldsInShippingDetailsPage=storeFrontHeirloomBillingAndShippingProfilePage.getAllNonMandatoryFieldsInShippingDetailsPage();
		s_assert.assertTrue(listOfNonMandatoryFieldsInShippingDetailsPage.get(1).getAttribute("id").contains("address2"),"Address2 field is mandatory");
		s_assert.assertTrue(listOfNonMandatoryFieldsInShippingDetailsPage.get(2).getAttribute("id").contains("address3"),"Address3 field is mandatory");
		storeFrontHeirloomBillingAndShippingProfilePage.fillZipCodeAndClickOnSaveBtn(postalCode);
		cityStateName=storeFrontHeirloomBillingAndShippingProfilePage.getCityAndState();
		s_assert.assertTrue(cityStateName.get(0).getText().contains("San Francisco"),"City field is not being AutoPopulated");		
		s_assert.assertTrue(cityStateName.get(1).getText().contains("California"),"State field is not being AutoPopulated");		
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isSaveAndCancelBTNDisplayed(),"Save and Cancel Button are not being Dispalyed");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isCRPAndNonCRPCheckBoxWithRequiredTextDisplayed(),"CRP and Non CRP checkbox with Required text are not being present");		
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5257
	 * qTest Id: TC-2782
	 */
	@Test(priority=7,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Verify the error message when user leaves mandatory fields while saving the shipping profile")
	public void testVerifyErrorMessagesWhenUserLeavesMandatoryFieldsWhileSavingShippingProfile_2782() {
		List<WebElement> listOfMandatoryFieldsInShippingDetailsPage;
		List<WebElement> listOfNonMandatoryFieldsInShippingDetailsPage;
		List<WebElement> cityStateName;
		List<WebElement> listOfMandatoryFieldsInShippingDetailsPageErrors;		
		listOfMandatoryFieldsInShippingDetailsPage=storeFrontHeirloomBillingAndShippingProfilePage.clickAddANewShippingAddressLinkAndGetAllMandatoryFields();
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(0).getAttribute("id").contains("profileName"),"Profile Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(1).getAttribute("id").contains("firstName"),"First Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(2).getAttribute("id").contains("lastName"),"Last Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(3).getAttribute("id").contains("address1"),"Address1 field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(4).getAttribute("id").contains("zipCode"),"Zip Code field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(5).getAttribute("id").contains("phoneNumber"),"Phone Number field is not mandatory");
		listOfNonMandatoryFieldsInShippingDetailsPage=storeFrontHeirloomBillingAndShippingProfilePage.getAllNonMandatoryFieldsInShippingDetailsPage();
		s_assert.assertTrue(listOfNonMandatoryFieldsInShippingDetailsPage.get(1).getAttribute("id").contains("address2"),"Address2 field is mandatory");
		s_assert.assertTrue(listOfNonMandatoryFieldsInShippingDetailsPage.get(2).getAttribute("id").contains("address3"),"Address3 field is mandatory");
		storeFrontHeirloomBillingAndShippingProfilePage.fillZipCodeAndClickOnSaveBtn(postalCode);
		cityStateName=storeFrontHeirloomBillingAndShippingProfilePage.getCityAndState();
		s_assert.assertTrue(cityStateName.get(0).getText().contains("San Francisco"),"City field is not being AutoPopulated");		
		s_assert.assertTrue(cityStateName.get(1).getText().contains("California"),"State field is not being AutoPopulated");
		storeFrontHeirloomBillingAndShippingProfilePage.clickSaveButtonInShippingPage();		
		listOfMandatoryFieldsInShippingDetailsPageErrors=storeFrontHeirloomBillingAndShippingProfilePage.getErrorMessagesForAllMandatoryFields();
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPageErrors.get(0).getAttribute("id").contains("profileNameError"),"Profile Name field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPageErrors.get(1).getAttribute("id").contains("firstNameError"),"First Name field  Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPageErrors.get(2).getAttribute("id").contains("lastNameError"),"Last Name field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPageErrors.get(3).getAttribute("id").contains("address1Error"),"Address1 field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPageErrors.get(4).getAttribute("id").contains("zipCodeError"),"Zip Code field Error is not displayed");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPageErrors.get(5).getAttribute("id").contains("phoneNumberError"),"Phone Number field Error is not displayed");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5257
	 * qTest Id: TC-2780
	 */
	@Test(priority=8,alwaysRun=true,groups="myAccountShippingCorpCons",description = "User selects the checkbox \"Use this shipping address for future CRP orders\"")
	public void testCreateNewShippingProfileSelectCheckboxUseShippingAddressForFutureCRPOrders_2780(){				
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String myAccountCategory_1="Edit CRP Cart";
		String shippingProfileName=null;		
		shippingProfileName = shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox().clickSaveButtonInShippingPage();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();				
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile is not present on Shipping Details page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isShippingProfileUpdatedSuccessfullyMessagePresent());		
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory_1);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresentOnEditCRPCartLinkPage(shippingProfileName),"Newly Created Shipping profile is not present on Edit CRP Cart Link Page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5257
	 * qTest Id: TC-2778
	 */
	@Test(priority=9,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Create new shipping profile by selecting the checkbox \"Use this shipping address for future CRP orders\"")
	public void testCreateNewShippingProfileBySelectingTheCheckBoxUseThisShippingAddressForFutureCRPOrders_2778(){
		List<WebElement> listOfMandatoryFieldsInShippingDetailsPage;
		List<WebElement> listOfNonMandatoryFieldsInShippingDetailsPage;
		List<WebElement> cityStateName;		
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);	
		String myAccountCategory_1="Edit CRP Cart";
		String shippingProfileName=null;			
		shippingProfileName = shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		listOfMandatoryFieldsInShippingDetailsPage=storeFrontHeirloomBillingAndShippingProfilePage.clickAddANewShippingAddressLinkAndGetAllMandatoryFields();
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(0).getAttribute("id").contains("profileName"),"Profile Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(1).getAttribute("id").contains("firstName"),"First Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(2).getAttribute("id").contains("lastName"),"Last Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(3).getAttribute("id").contains("address1"),"Address1 field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(4).getAttribute("id").contains("zipCode"),"Zip Code field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(5).getAttribute("id").contains("phoneNumber"),"Phone Number field is not mandatory");
		listOfNonMandatoryFieldsInShippingDetailsPage=storeFrontHeirloomBillingAndShippingProfilePage.getAllNonMandatoryFieldsInShippingDetailsPage();
		s_assert.assertTrue(listOfNonMandatoryFieldsInShippingDetailsPage.get(1).getAttribute("id").contains("address2"),"Address2 field is mandatory");
		s_assert.assertTrue(listOfNonMandatoryFieldsInShippingDetailsPage.get(2).getAttribute("id").contains("address3"),"Address3 field is mandatory");
		storeFrontHeirloomBillingAndShippingProfilePage.fillZipCodeAndClickOnSaveBtn(postalCode);
		cityStateName=storeFrontHeirloomBillingAndShippingProfilePage.getCityAndState();
		s_assert.assertTrue(cityStateName.get(0).getText().contains("San Francisco"),"City field is not being AutoPopulated");		
		s_assert.assertTrue(cityStateName.get(1).getText().contains("California"),"State field is not being AutoPopulated");		
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.checkUseThisAddressForAllFutureOrdersIncludingCRPChkBox().clickSaveButtonInShippingPage();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();	
		
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile is not present on Shipping Details page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isShippingProfileUpdatedSuccessfullyMessagePresent());		
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory_1);
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresentOnEditCRPCartLinkPage(shippingProfileName),"Newly Created Shipping profile is not present on Edit CRP Cart Link Page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-5257
	 * qTest Id: TC-2779
	 */
	@Test(priority=10,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Create new shipping profle without selecting the checkbox \"Use this shipping address for future CRP orders\"")
	public void testCreateNewShippingProfileWithoutSelectingCheckbox_2779() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String myAccountCategory_1="Edit CRP Cart";
		String shippingProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		List<WebElement> listOfMandatoryFieldsInShippingDetailsPage=storeFrontHeirloomBillingAndShippingProfilePage.clickAddANewShippingAddressLinkAndGetAllMandatoryFields();
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(0).getAttribute("id").contains("profileName"),"Profile Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(1).getAttribute("id").contains("firstName"),"First Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(2).getAttribute("id").contains("lastName"),"Last Name field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(3).getAttribute("id").contains("address1"),"Address1 field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(4).getAttribute("id").contains("zipCode"),"Zip Code field is not mandatory");
		s_assert.assertTrue(listOfMandatoryFieldsInShippingDetailsPage.get(5).getAttribute("id").contains("phoneNumber"),"Phone Number field is not mandatory");
		List<WebElement> listOfNonMandatoryFieldsInShippingDetailsPage=storeFrontHeirloomBillingAndShippingProfilePage.getAllNonMandatoryFieldsInShippingDetailsPage();
		s_assert.assertTrue(listOfNonMandatoryFieldsInShippingDetailsPage.get(1).getAttribute("id").contains("address2"),"Address2 field is mandatory");
		s_assert.assertTrue(listOfNonMandatoryFieldsInShippingDetailsPage.get(2).getAttribute("id").contains("address3"),"Address3 field is mandatory");
		storeFrontHeirloomBillingAndShippingProfilePage.fillZipCodeAndClickOnSaveBtn(postalCode);
		List<WebElement> cityStateName=storeFrontHeirloomBillingAndShippingProfilePage.getCityAndState();
		s_assert.assertTrue(cityStateName.get(0).getText().contains("San Francisco"),"City field is not being AutoPopulated");		
		s_assert.assertTrue(cityStateName.get(1).getText().contains("California"),"State field is not being AutoPopulated");	
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.clickSaveButtonInShippingPage();
		storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
		assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isShippingProfileUpdatedSuccessfullyMessagePresent());		
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory_1);
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresentOnEditCRPCartLinkPage(shippingProfileName),"Newly Created Shipping profile is not present on Edit CRP Cart Link Page");
		s_assert.assertAll();
	}	

	/**
	 * Jira Story Id: MAIN-5257
	 * qTest Id: TC-2781
	 */
	@Test(priority=11,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Verify CANCEL CTA button in Shipping Details page")
	public void testCancelButtonFunctionalityDuringCreateNewShippingProfile_2781() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		storeFrontHeirloomBillingAndShippingProfilePage.fillNewShippingAddressDetails(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
		storeFrontHeirloomBillingAndShippingProfilePage.clickCancelButtonInShippingPage();		
		
		s_assert.assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile is present on page");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3176
	 */
	@Test(priority=12,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Verify Consolidated checbox for add new shipping  address (Corp/consultant)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForCons_3176() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3185
	 */
	@Test(priority=13,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Verify Consolidated checbox for edit existing shipping  address (Corp/consultant)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForCons_3185() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2967
	 */
	@Test(priority=14,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Verify when consultant Selects the Default radio button on one of the Shipping profiles that is not selected as default(Corp site)")
	public void testWhenUserSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_CorpSite_2967() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		int shippingProfilesCount=0;
		String shippingProfileName=null;
		String nonDefaultProfileName=null;

		shippingProfilesCount=storeFrontHeirloomBillingAndShippingProfilePage.getShippingProfilesCount();
		if(shippingProfilesCount<2) {
			shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
			storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndSave(shippingProfileName, shippingProfileFirstName, shippingProfileLastName+randomNum, addressLine1, postalCode, phnNumber);
			storeFrontHeirloomHomePage.clickUseAsEnteredBtn();
			
			s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile not present on page");
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
	 * qTest Id: TC-2875
	 */
	@Test(priority=15,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Storefront Consultant,Display the following checkboxes on Shipping details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddShippingForCons_2875() {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileName=null;
		String defaultProfileName=null;
		shippingProfileName=shippingProfileFirstName+" "+shippingProfileLastName+randomNum;
		storeFrontHeirloomBillingAndShippingProfilePage.addNewShippingAddressDetailsFromShippingPageAndCancel(shippingProfileName, shippingProfileFirstName, shippingProfileLastName, addressLine1, postalCode, phnNumber);
		assertFalse(storeFrontHeirloomBillingAndShippingProfilePage.isNewlyCreatedShippingProfilePresent(shippingProfileName),"Newly Created Shipping profile Should not be present on page if cancel button clicked instead of save");
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
	 * qTest Id: TC-2876
	 */
	@Test(priority=16,alwaysRun=true,groups="myAccountShippingCorpCons",description = "Storefront Consultant,Display the following checkboxes on Shipping details Page for edit existing address option.  ")
	public void testVerifyCheckboxOnEditShippingForCons_2876() {
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
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2968
	 */
	@Test(priority=17,alwaysRun=true,groups="myAccountShippingCorpPC",description = "Verify when PC selects the Default radio button for one the existing billing profiles that is not selected as Default(Corp site)")
	public void testWhenPCSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_CorpSite_2968() {
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
	 * qTest Id: TC-2881
	 */
	@Test(priority=18,alwaysRun=true,groups="myAccountShippingCorpPC",description = "Storefront PC,Display the following checkboxes on Shipping details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddShippingForPC_2881() {
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
	 * qTest Id: TC-2882
	 */
	@Test(priority=19,alwaysRun=true,groups="myAccountShippingCorpPC",description = "Storefront PC,Display the following checkboxes on Shipping details Page for edit existing address option.  ")
	public void testVerifyCheckboxOnEditShippingForPC_2882() {
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
	 * qTest Id: TC-3177
	 */
	@Test(priority=20,alwaysRun=true,groups="myAccountShippingCorpPC",description = "Verify Consolidated checbox for add new shipping  address (Corp/PC)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForPC_3177() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3186
	 */
	@Test(priority=21,alwaysRun=true,groups="myAccountShippingCorpPC",description = "Verify Consolidated checbox for edit existing shipping  address (Corp/PC)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForPC_3186() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/* Jira Story Id: MAIN-8418
	 * qTest Id: TC-2887
	 */
	@Test(priority=22,alwaysRun=true,groups="myAccountShippingCorpRC",description = "Storefront RC,Display the following checkboxes on Shipping details Page for Add new Address option. ")
	public void testVerifyCheckboxOnAddShippingForRC_2887() {
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
	 * qTest Id: TC-2888
	 */
	@Test(priority=23,alwaysRun=true,groups="myAccountShippingCorpRC",description = "Storefront RC,Display the following checkboxes on Shipping details Page for edit existing address option.  ")
	public void testVerifyCheckboxOnEditShippingForRC_2888() {
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
	 * qTest Id: TC-3178
	 */
	@Test(priority=24,alwaysRun=true,groups="myAccountShippingCorpRC",description = "Verify Consolidated checbox for add new shipping  address (Corp/RC)")
	public void  testTotalCheckboxesWhileAddNewShippingProfileForRC_3178() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickAddNewShippingAddressLink();
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8621
	 * qTest Id: TC-3187
	 */
	@Test(priority=25,alwaysRun=true,groups="myAccountShippingCorpRC",description = "Verify Consolidated checbox for edit existing shipping  address (Corp/RC)")
	public void  testTotalCheckboxesWhileEditExistingShippingProfileForRC_3187() {
		storeFrontHeirloomBillingAndShippingProfilePage.clickOnEditBtnForFirstShippingProfile();	
		s_assert.assertTrue(storeFrontHeirloomBillingAndShippingProfilePage.getTotalNumberofCheckBoxesOnAddBillingForm()==1, "Total Number of checkboxe expected is NOT 1");	
		s_assert.assertAll();
	}
	
	/**
	 * Jira Story Id: MAIN-8399
	 * qTest Id: TC-2969
	 */
	@Test(priority=26,alwaysRun=true,groups="myAccountShippingCorpRC",description = "Verify when RC selects the Default radio button for one the existing billing profiles that is not selected as Default(Corp site)")
	public void testWhenRCSelectsDefaultRadioButtonOnOneOfTheShippingProfilesThatIsNotSelectedAsDefault_CorpSite_2969() {
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