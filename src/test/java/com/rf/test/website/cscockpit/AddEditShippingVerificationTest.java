package com.rf.test.website.cscockpit;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.cscockpit.CSCockpitAutoshipTemplateTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerSearchTabPage;
import com.rf.pages.website.cscockpit.CSCockpitCustomerTabPage;
import com.rf.pages.website.cscockpit.CSCockpitLoginPage;
import com.rf.pages.website.storeFront.StoreFrontAccountInfoPage;
import com.rf.pages.website.storeFront.StoreFrontConsultantPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.test.website.RFWebsiteBaseTest;

public class AddEditShippingVerificationTest extends RFWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(AddEditShippingVerificationTest.class.getName());

	//-------------------------------------------------Pages---------------------------------------------------------
	private CSCockpitLoginPage cscockpitLoginPage;	
	private CSCockpitCustomerSearchTabPage cscockpitCustomerSearchTabPage;
	private CSCockpitCustomerTabPage cscockpitCustomerTabPage;
	private CSCockpitAutoshipTemplateTabPage cscockpitAutoshipTemplateTabPage;

	//-----------------------------------------------------------------------------------------------------------------

	public AddEditShippingVerificationTest() {
		cscockpitLoginPage = new CSCockpitLoginPage(driver);
		cscockpitCustomerSearchTabPage = new CSCockpitCustomerSearchTabPage(driver);
		cscockpitCustomerTabPage = new CSCockpitCustomerTabPage(driver);
		cscockpitAutoshipTemplateTabPage = new CSCockpitAutoshipTemplateTabPage(driver);
		storeFrontHomePage = new StoreFrontHomePage(driver);
		storeFrontConsultantPage = new StoreFrontConsultantPage(driver);
		storeFrontAccountInfoPage = new StoreFrontAccountInfoPage(driver);
	}

	private String RFO_DB = null;

	//Hybris Project-1754:To verify Edit address functionality in the Customer detail Page for consultant
	@Test
	public void testVerifyEditAddressFunctionalityInCustomerDetailPageForConsultant_1754() throws InterruptedException{
		RFO_DB = driver.getDBNameRFO();
		String randomCustomerSequenceNumber = null;
		String consultantEmailID;
		String accountID;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String attendentFirstName = TestConstants.FIRST_NAME+randomNum;
		String attendeeLastName = TestConstants.LAST_NAME;
		String city = null;
		String postal = null;
		String province = null;
		String phoneNumber = null;
		String country = null;
		String addressLine = null;
		if(driver.getCountry().equalsIgnoreCase("us")){
			addressLine = TestConstants.ADDRESS_LINE_1_US;
			city = TestConstants.CITY_US;
			postal = TestConstants.POSTAL_CODE_US;
			province = TestConstants.PROVINCE_ALABAMA_US;
			phoneNumber = TestConstants.PHONE_NUMBER_US;
			country = "United States";
		}else if(driver.getCountry().equalsIgnoreCase("ca")){
			addressLine = TestConstants.ADDRESS_LINE_1_CA;
			city = TestConstants.CITY_CA;
			postal = TestConstants.POSTAL_CODE_CA;
			province = TestConstants.PROVINCE_ALBERTA;
			phoneNumber = TestConstants.PHONE_NUMBER_CA;
			country = "Canada";
		}
		else if(driver.getCountry().equalsIgnoreCase("au")){
			addressLine = TestConstants.ADDRESS_LINE_1_AU;
			city = TestConstants.CITY_AU;
			postal = TestConstants.POSTAL_CODE_AU;
			province = TestConstants.STATE_AU;
			phoneNumber = TestConstants.PHONE_NUMBER_AU;
			country = "Australia";
		}
		List<Map<String, Object>> randomConsultantList = null;
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
		consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		accountID = String.valueOf(getValueFromQueryResult(randomConsultantList, "AccountID"));
		List<Map<String, Object>> randomConsultantUsernameList =  null;
		randomConsultantUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID),RFO_DB);
		consultantEmailID = String.valueOf(getValueFromQueryResult(randomConsultantUsernameList, "EmailAddress"));  
		logger.info("emaild of consultant username "+consultantEmailID);
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();

		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab("");
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("CONSULTANT");
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Inactive");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		cscockpitCustomerTabPage.clickAddButtonOfCustomerAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.addressCanNotBeAddedForInactiveUserInCustomerTab(), "Address can be added for Inactive user");
		cscockpitCustomerTabPage.clickOkBtnOfAddressCanNotBeAddedForInactiveUserInCustomerTab();
		cscockpitCustomerTabPage.clickCustomerSearchTab();
		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(consultantEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("CONSULTANT");
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(country);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		//without select set as autoship shipping profile check box
		cscockpitCustomerTabPage.clickEditButtonOfShippingAddressInCustomerTab();
		cscockpitCustomerTabPage.enterShippingInfoInAddNewPaymentProfilePopupWithoutSaveBtn(attendentFirstName, attendeeLastName, addressLine, city, postal, country, province, phoneNumber);
		cscockpitCustomerTabPage.clickUpdateAddressbtnInEditAddressPopup();
		cscockpitCustomerTabPage.clickUseEnteredAddressbtnInEditAddressPopup();
		s_assert.assertTrue(cscockpitCustomerTabPage.getFirstShippingAddressProfileName().toLowerCase().trim().contains(attendentFirstName.toLowerCase()), "shipping profile name expected in customer tab page "+attendentFirstName+" actual on UI "+cscockpitCustomerTabPage.getFirstShippingAddressProfileName());
		if(cscockpitCustomerTabPage.isAutoshipIdWithPendingCRPPresent()){
			cscockpitCustomerTabPage.getAndClickAutoshipIDHavingTypeAsCRPAutoshipAndStatusIsPending();
			s_assert.assertFalse(cscockpitAutoshipTemplateTabPage.getShippingAddressNameInAutoshipTemplateTab().toLowerCase().trim().contains(attendentFirstName.toLowerCase()),"Shipping Address Name Expected is "+attendentFirstName.toLowerCase()+" While on UI"+cscockpitAutoshipTemplateTabPage.getShippingAddressNameInAutoshipTemplateTab());
			cscockpitAutoshipTemplateTabPage.clickCustomerTab();
			//with select set as autoship shipping profile check box
			cscockpitCustomerTabPage.clickEditButtonOfShippingAddressInCustomerTab();
			cscockpitCustomerTabPage.enterShippingInfoInAddNewPaymentProfilePopupWithoutSaveBtn(attendentFirstName, attendeeLastName, addressLine, city, postal, country, province, phoneNumber);
			cscockpitCustomerTabPage.clickSetAsAutoshipChkBoxInCreateNewAddressPopup();
			cscockpitCustomerTabPage.clickUpdateAddressbtnInEditAddressPopup();
			cscockpitCustomerTabPage.clickUseEnteredAddressbtnInEditAddressPopup();
			cscockpitCustomerTabPage.clickOnYesOnUpdateAutoshipAddressPopup();
			s_assert.assertTrue(cscockpitCustomerTabPage.getFirstShippingAddressProfileName().toLowerCase().trim().contains(attendentFirstName.toLowerCase()), "shipping profile name for autoship expected in customer tab page "+attendentFirstName+" actual on UI "+cscockpitCustomerTabPage.getFirstShippingAddressProfileName());
			cscockpitCustomerTabPage.getAndClickAutoshipIDHavingTypeAsCRPAutoshipAndStatusIsPending();
			s_assert.assertTrue(cscockpitAutoshipTemplateTabPage.getShippingAddressNameInAutoshipTemplateTab().toLowerCase().trim().contains(attendentFirstName.toLowerCase()),"Shipping Address Name for autoship Expected is "+attendentFirstName.toLowerCase()+" While on UI"+cscockpitAutoshipTemplateTabPage.getShippingAddressNameInAutoshipTemplateTab());
		}
		s_assert.assertAll();
	}

	//Hybris Project-1753:To verify Edit address functionality in the Customer detail Page for PC
	@Test
	public void testVerifyEditAddressFunctionalityInCustomerDetailPageForPC_1753() throws InterruptedException{
		RFO_DB = driver.getDBNameRFO();
		String randomCustomerSequenceNumber = null;
		String pcEmailID;
		String accountID;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String attendentFirstName = TestConstants.FIRST_NAME+randomNum;
		String attendeeLastName = TestConstants.LAST_NAME;
		String city = null;
		String postal = null;
		String province = null;
		String phoneNumber = null;
		String country = null;
		String addressLine = null;
		if(driver.getCountry().equalsIgnoreCase("us")){
			addressLine = TestConstants.ADDRESS_LINE_1_US;
			city = TestConstants.CITY_US;
			postal = TestConstants.POSTAL_CODE_US;
			province = TestConstants.PROVINCE_ALABAMA_US;
			phoneNumber = TestConstants.PHONE_NUMBER_US;
			country = "United States";
		}else if(driver.getCountry().equalsIgnoreCase("ca")){
			addressLine = TestConstants.ADDRESS_LINE_1_CA;
			city = TestConstants.CITY_CA;
			postal = TestConstants.POSTAL_CODE_CA;
			province = TestConstants.PROVINCE_ALBERTA;
			phoneNumber = TestConstants.PHONE_NUMBER_CA;
			country = "Canada";
		}
		else if(driver.getCountry().equalsIgnoreCase("au")){
			addressLine = TestConstants.ADDRESS_LINE_1_AU;
			city = TestConstants.CITY_AU;
			postal = TestConstants.POSTAL_CODE_AU;
			province = TestConstants.STATE_AU;
			phoneNumber = TestConstants.PHONE_NUMBER_AU;
			country = "Australia";
		}
		List<Map<String, Object>> randomPCList = null;
		randomPCList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
		pcEmailID = (String) getValueFromQueryResult(randomPCList, "UserName");
		accountID = String.valueOf(getValueFromQueryResult(randomPCList, "AccountID"));
		List<Map<String, Object>> randomPCUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID),RFO_DB);
		pcEmailID = String.valueOf(getValueFromQueryResult(randomPCUsernameList, "EmailAddress")); 
		logger.info("emaild of PC"+pcEmailID);
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();

		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab("");
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("PC");
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Inactive");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		cscockpitCustomerTabPage.clickAddButtonOfCustomerAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.addressCanNotBeAddedForInactiveUserInCustomerTab(), "Address can be added for Inactive user");
		cscockpitCustomerTabPage.clickOkBtnOfAddressCanNotBeAddedForInactiveUserInCustomerTab();

		cscockpitCustomerTabPage.clickCustomerSearchTab();

		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(pcEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("PC");
		cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(country);
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		//Added work around for get the count of list
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		//without select set as autoship shipping profile check box
		cscockpitCustomerTabPage.clickEditButtonOfShippingAddressInCustomerTab();
		cscockpitCustomerTabPage.enterShippingInfoInAddNewPaymentProfilePopupWithoutSaveBtn(attendentFirstName, attendeeLastName, addressLine, city, postal, country, province, phoneNumber);
		cscockpitCustomerTabPage.clickUpdateAddressbtnInEditAddressPopup();
		cscockpitCustomerTabPage.clickUseEnteredAddressbtnInEditAddressPopup();
		s_assert.assertTrue(cscockpitCustomerTabPage.getFirstShippingAddressProfileName().toLowerCase().trim().contains(attendentFirstName.toLowerCase()), "shipping profile name expected in customer tab page "+attendentFirstName+" actual on UI "+cscockpitCustomerTabPage.getFirstShippingAddressProfileName());
		if(cscockpitCustomerTabPage.isAutoshipIdWithPendingPCPerksPresent()){
			cscockpitCustomerTabPage.getAndClickAutoshipIDHavingTypeAsPCAutoshipAndStatusIsPending();
			s_assert.assertFalse(cscockpitAutoshipTemplateTabPage.getShippingAddressNameInAutoshipTemplateTab().toLowerCase().trim().contains(attendentFirstName.toLowerCase()),"Shipping Address Name Expected is "+attendentFirstName.toLowerCase()+" While on UI"+cscockpitAutoshipTemplateTabPage.getShippingAddressNameInAutoshipTemplateTab());
			cscockpitAutoshipTemplateTabPage.clickCustomerTab();
			//with select set as autoship shipping profile check box
			cscockpitCustomerTabPage.clickEditButtonOfShippingAddressInCustomerTab();
			cscockpitCustomerTabPage.enterShippingInfoInAddNewPaymentProfilePopupWithoutSaveBtn(attendentFirstName, attendeeLastName, addressLine, city, postal, country, province, phoneNumber);
			cscockpitCustomerTabPage.clickSetAsAutoshipChkBoxInCreateNewAddressPopup();
			cscockpitCustomerTabPage.clickUpdateAddressbtnInEditAddressPopup();
			cscockpitCustomerTabPage.clickUseEnteredAddressbtnInEditAddressPopup();
			cscockpitCustomerTabPage.clickOnYesOnUpdateAutoshipAddressPopup();
			s_assert.assertTrue(cscockpitCustomerTabPage.getFirstShippingAddressProfileName().toLowerCase().trim().contains(attendentFirstName.toLowerCase()), "shipping profile name for autoship expected in customer tab page "+attendentFirstName+" actual on UI "+cscockpitCustomerTabPage.getFirstShippingAddressProfileName());
			cscockpitCustomerTabPage.getAndClickAutoshipIDHavingTypeAsPCAutoshipAndStatusIsPending();
			s_assert.assertTrue(cscockpitAutoshipTemplateTabPage.getShippingAddressNameInAutoshipTemplateTab().toLowerCase().trim().contains(attendentFirstName.toLowerCase()),"Shipping Address Name for autoship Expected is "+attendentFirstName.toLowerCase()+" While on UI"+cscockpitAutoshipTemplateTabPage.getShippingAddressNameInAutoshipTemplateTab());
		}
		s_assert.assertAll();

	}

	//Hybris Project-1752:To verify Edit address functionality in the Customer detail Page for RC
	@Test
	public void testVerifyEditAddressFunctionalityInCustomerDetailPageForRC_1752() throws InterruptedException{
		RFO_DB = driver.getDBNameRFO();
		String randomCustomerSequenceNumber = null;
		String rcEmailID;
		String accountID;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String attendentFirstName = TestConstants.FIRST_NAME+randomNum;
		String attendeeLastName = TestConstants.LAST_NAME;
		String city = null;
		String postal = null;
		String province = null;
		String phoneNumber = null;
		String country = null;
		String addressLine = null;
		if(driver.getCountry().equalsIgnoreCase("us")){
			addressLine = TestConstants.ADDRESS_LINE_1_US;
			city = TestConstants.CITY_US;
			postal = TestConstants.POSTAL_CODE_US;
			province = TestConstants.PROVINCE_ALABAMA_US;
			phoneNumber = TestConstants.PHONE_NUMBER_US;
			country = "United States";
		}else if(driver.getCountry().equalsIgnoreCase("ca")){
			addressLine = TestConstants.ADDRESS_LINE_1_CA;
			city = TestConstants.CITY_CA;
			postal = TestConstants.POSTAL_CODE_CA;
			province = TestConstants.PROVINCE_ALBERTA;
			phoneNumber = TestConstants.PHONE_NUMBER_CA;
			country = "Canada";
		}
		else if(driver.getCountry().equalsIgnoreCase("au")){
			addressLine = TestConstants.ADDRESS_LINE_1_AU;
			city = TestConstants.CITY_AU;
			postal = TestConstants.POSTAL_CODE_AU;
			province = TestConstants.STATE_AU;
			phoneNumber = TestConstants.PHONE_NUMBER_AU;
			country = "Australia";
		}
		List<Map<String, Object>> randomRCList = null;
		randomRCList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_RC_EMAIL_ID_RFO,countryId),RFO_DB);
		rcEmailID = (String) getValueFromQueryResult(randomRCList, "UserName");
		accountID = String.valueOf(getValueFromQueryResult(randomRCList, "AccountID"));
		List<Map<String, Object>> randomPCUsernameList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID),RFO_DB);
		rcEmailID = String.valueOf(getValueFromQueryResult(randomPCUsernameList, "EmailAddress")); 
		logger.info("emaild of RC"+rcEmailID);
		cscockpitCustomerSearchTabPage = cscockpitLoginPage.clickLoginBtn();

		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab("");
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("RETAIL");
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Inactive");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		cscockpitCustomerTabPage.clickAddButtonOfCustomerAddressInCustomerTab();
		s_assert.assertTrue(cscockpitCustomerTabPage.addressCanNotBeAddedForInactiveUserInCustomerTab(), "Address can be added for Inactive user");
		cscockpitCustomerTabPage.clickOkBtnOfAddressCanNotBeAddedForInactiveUserInCustomerTab();

		cscockpitCustomerTabPage.clickCustomerSearchTab();

		cscockpitCustomerSearchTabPage.enterEmailIdInSearchFieldInCustomerSearchTab(rcEmailID);
		cscockpitCustomerSearchTabPage.selectCustomerTypeFromDropDownInCustomerSearchTab("RETAIL");
		//cscockpitCustomerSearchTabPage.selectCountryFromDropDownInCustomerSearchTab(country);
		cscockpitCustomerSearchTabPage.selectAccountStatusFromDropDownInCustomerSearchTab("Active");
		cscockpitCustomerSearchTabPage.clickSearchBtn();
		randomCustomerSequenceNumber = String.valueOf(cscockpitCustomerSearchTabPage.getRandomCustomerFromSearchResult());
		cscockpitCustomerSearchTabPage.clickAndReturnCIDNumberInCustomerSearchTab(randomCustomerSequenceNumber);
		//without select set as autoship shipping profile check box
		cscockpitCustomerTabPage.clickEditButtonOfShippingAddressInCustomerTab();
		cscockpitCustomerTabPage.enterShippingInfoInAddNewPaymentProfilePopupWithoutSaveBtn(attendentFirstName, attendeeLastName, addressLine, city, postal, country, province, phoneNumber);
		cscockpitCustomerTabPage.clickUpdateAddressbtnInEditAddressPopup();
		cscockpitCustomerTabPage.clickUseEnteredAddressbtnInEditAddressPopup();
		s_assert.assertTrue(cscockpitCustomerTabPage.getFirstShippingAddressProfileName().toLowerCase().trim().contains(attendentFirstName.toLowerCase()), "shipping profile name expected in customer tab page "+attendentFirstName+" actual on UI "+cscockpitCustomerTabPage.getFirstShippingAddressProfileName());
		s_assert.assertAll();

	}
}