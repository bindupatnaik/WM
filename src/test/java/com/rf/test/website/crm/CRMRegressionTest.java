package com.rf.test.website.crm;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rf.core.utils.CommonUtils;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.pages.website.crm.CRMAccountDetailsPage;
import com.rf.pages.website.crm.CRMContactDetailsPage;
import com.rf.pages.website.crm.CRMHomePage;
import com.rf.pages.website.crm.CRMLoginPage;
import com.rf.pages.website.storeFront.StoreFrontHomePage;
import com.rf.test.website.RFCRMWebsiteBaseTest;

public class CRMRegressionTest extends RFCRMWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(CRMRegressionTest.class.getName());

	private CRMLoginPage crmLoginpage;
	private CRMHomePage crmHomePage;
	private CRMAccountDetailsPage crmAccountDetailsPage; 
	private CRMContactDetailsPage crmContactDetailsPage;
	private StoreFrontHomePage storeFrontHomePage;

	List<Map<String, Object>> randomConsultantList =  null;
	List<Map<String, Object>> randomConsultantUsernameList =  null;
	List<Map<String, Object>> randomPCUserList =  null;
	List<Map<String, Object>> randomRCUserList =  null;
	List<Map<String, Object>> randompcEmailIDList =  null;
	List<Map<String, Object>> randomrcEmailIDList =  null;

	private String consultantEmailID = null;
	private String pcEmailID = null;
	private String rcEmailID = null;

	@BeforeClass
	public void BeforeCRM(){
		loginUser(CRMUserName, CRMPassword);
		RFO_DB = driver.getDBNameRFO();
		crmLoginpage = new CRMLoginPage(driver);
		crmHomePage = new CRMHomePage(driver);
		crmAccountDetailsPage = new CRMAccountDetailsPage(driver);
		crmContactDetailsPage = new CRMContactDetailsPage(driver);
		storeFrontHomePage = new StoreFrontHomePage(driver);
		consultantEmailID = getActiveEmailIdFromDB("Consultant");
		pcEmailID =  getActiveEmailIdFromDB("Preferred Customer");
		rcEmailID = getActiveEmailIdFromDB("Retail Customer");
		crmHomePage.closeAllOpenedTabs();
	}		

	// QTest ID TC-283:Edit Preferred Customer Account details
	@Test(priority=1)
	public void testEditPreferredCustomerActDetails_283q() throws InterruptedException	{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAccountDetailsButton("Edit Account");
		crmAccountDetailsPage.updateRecognitionNameField(TestConstants.CRM_ACT_INFORMATION_RECOGNITION_NAME+randomNum);
		crmAccountDetailsPage.clickSaveBtnUnderAccountDetail();
		s_assert.assertTrue(crmAccountDetailsPage.getRecognitionName().equalsIgnoreCase(TestConstants.CRM_ACT_INFORMATION_RECOGNITION_NAME+randomNum),"Recognition name is not updated");
		crmAccountDetailsPage.clickAccountDetailsButton("Edit Main Address");
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSection("Full Address", addressLine);
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSectionWithTextarea("Street Address", addressLine);
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSection("City", city);
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSection("State/Province", province);
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSection("Postal Code", postal);
		crmAccountDetailsPage.clickSaveAddressButtonInEditMainAddressSection(addressLine);
		s_assert.assertTrue(crmAccountDetailsPage.getDataValueOfLabelsInMainAddressSection("Address Line 1").trim().equalsIgnoreCase(addressLine),"Actual Value is :"+crmAccountDetailsPage.getDataValueOfLabelsInMainAddressSection("Address Line 1").trim()+"& Expected Value is :"+addressLine+".Main account Address Line 1 is not updated");
		s_assert.assertAll();
	}

	//QTest ID TC-284:Edit Retail Customer Account details
	@Test(priority=2)
	public void testEditRetailCustomerActDetails_284q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAccountDetailsButton("Edit Account");
		crmAccountDetailsPage.updateRecognitionNameField(TestConstants.CRM_ACT_INFORMATION_RECOGNITION_NAME+randomNum);
		crmAccountDetailsPage.clickSaveBtnUnderAccountDetail();
		s_assert.assertTrue(crmAccountDetailsPage.getRecognitionName().equalsIgnoreCase(TestConstants.CRM_ACT_INFORMATION_RECOGNITION_NAME+randomNum),"Recognition name is not updated");
		crmAccountDetailsPage.clickAccountDetailsButton("Edit Main Address");
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSection("Full Address", addressLine);
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSectionWithTextarea("Street Address", addressLine);
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSection("City", city);
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSection("State/Province", province);
		crmAccountDetailsPage.editAddressFieldsOfMainAddressSection("Postal Code", postal);
		crmAccountDetailsPage.clickSaveAddressButtonInEditMainAddressSection(addressLine);
		s_assert.assertTrue(crmAccountDetailsPage.getDataValueOfLabelsInMainAddressSection("Address Line 1").trim().equalsIgnoreCase(addressLine),"Actual Value is :"+crmAccountDetailsPage.getDataValueOfLabelsInMainAddressSection("Address Line 1").trim()+"& Expected Value is :"+addressLine+".Main account Address Line 1 is not updated");
		s_assert.assertAll();
	}

	//QTest ID TC-102:Search for account by name
	@Test(priority=3)
	public void testSearchForAccountByName_102q() throws InterruptedException{
		List<Map<String, Object>> randomFirstName =  null;
		String firstName = null;
		randomFirstName = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_FirstName_RFO,countryId),RFO_DB);	
		firstName = (String) getValueFromQueryResult(randomFirstName, "FirstName");		
		logger.info("The first name is "+firstName);	
		crmHomePage.enterTextInSearchFieldAndHitEnter(firstName);
		s_assert.assertTrue(crmHomePage.isNamePresentInAccountSection(firstName), "results not found from name");
		s_assert.assertTrue(crmHomePage.isAccountLinkPresentInLeftNaviagation(), "Accounts link is not present on left navigation panel");
		s_assert.assertTrue(crmHomePage.isContactsLinkPresentInLeftNaviagation(), "Contacts link is not present on left navigation panel");
		crmHomePage.clickNameOnFirstRowInSearchResults();
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertAll();
	}

	//QTest ID TC-151:View Consultant Account details
	@Test(priority=4)
	public void testViewConsultantAccountDetailsTest_151q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsSectionPresent(),"Account Details Section is not present");
		s_assert.assertTrue(crmAccountDetailsPage.isMainAddressSectionPresent(),"Main Address Section is not present");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsButtonEnabled("Edit Account"),"Edit Account button is not Enabled in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsButtonEnabled("Edit Main Address"),"Edit Main Address button is not Enabled in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsButtonEnabled("PWS Domain"),"Edit PWS Domain button is not Enabled in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsButtonEnabled("Change Account Status"),"Change Account Status button is not Enabled in account detail section");
		if(driver.getCountry().equalsIgnoreCase("CA")) {
			s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsButtonEnabled("Pulse"),"Pulse button is not Enabled in account detail section");
		}
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsButtonEnabled("New Pulse"),"New Pulse button is not Enabled in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsButtonEnabled("My Account"),"My Account button is not Enabled in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Name"),"Account Name label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Recognition Name"),"Recognition Name label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Recognition Title"),"Recognition Title label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Active"),"Active label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Status"),"Account Status label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Soft Termination Date"),"Soft Termination Date label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Hard Termination Date"),"Hard Termination Date label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("SV"),"SV label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("PSQV"),"PSQV label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Type"),"Account Type label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Enrollment Sponsor"),"Enrollment Sponsor label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Placement Sponsor"),"Placement Sponsor label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Enrollment Date"),"Enrollment Date label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Next Renewal Date"),"Next Renewal Date label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Last Renewal Date"),"Last Renewal Date label is not present in account detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Address Line 1"),"Address Line 1 label is not present in main address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Address Line 2"),"Address Line 2 label is not present in main address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Locale"),"Locale label is not present in main address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Sub Region"),"Sub Region label is not present in main address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Region"),"Region label is not present in main address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Postal code"),"Postal code label is not present in main address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Country"),"Country label is not present in main address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderTaxInformationSectionPresent("Business Entity"),"Business Entity label is not present under tax information section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderTaxInformationSectionPresent("Tax Exempt"),"Tax Exempt label is not present under tax information section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderTaxInformationSectionPresent("Tax ID / ABN Number"),"Tax ID Number label is not present under tax information section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderTaxInformationSectionPresent("Legal Tax Name"),"Legal Tax Name label is not present under tax information section");
		s_assert.assertAll();
	}

	//QTest ID TC-152:View Preferred Customer Account details
	@Test(priority=5)
	public void testViewPreferredCustomerAccountDetails_152q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Name"),"Account Name");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Recognition Name"),"Recognition Name");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Recognition Title"),"Recognition Title");
		s_assert.assertTrue(crmAccountDetailsPage.isActiveLabelOnAccountDetailsSectionPresent(),"Active Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Status"),"Account Status Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Soft Termination Date"),"Soft Termination Date Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Number"),"Account Number Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Enrollment Sponsor"),"Enrollment Sponsor Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Placement Sponsor"),"Placement Sponsor Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Enrollment Date"),"Enrollment Date Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Main Phone"),"Main Phone Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Email Address"),"Email Address Label is not Present");
		crmAccountDetailsPage.clickAccountDetailsButton("Edit Account");
		s_assert.assertTrue(crmAccountDetailsPage.verifyRecognizationNameUnderAccountEditInAccountInformationIsEditable(), "Recognition Name is not Editable");
		s_assert.assertTrue(crmAccountDetailsPage.verifyAccountNameNameUnderAccountEditInAccountInformationIsEditable(), "Account Name is not Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Account Type"), "Account Type is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Soft Termination Date"), "Soft Termination Date is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Account Number"), "Account Number is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Enrollment Sponsor"), "Enrollment Sponsor is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Placement Sponsor"), "Placement Sponsor is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Enrollment Date"), "Enrollment Date is Editable");
		crmAccountDetailsPage.clickCancelButtonUnderAccountEditInAccountInformationSection();
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Address Line 1"), "Address Line 1 Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Address Line 2"), "Address Line 2 Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Locale"), "Locale Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Sub Region"), "Sub Region Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Region"), "Region Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Postal code"), "Postal code Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Country"), "Country Label is not Present");
		s_assert.assertAll();
	}

	//QTest ID TC-153:View Retail Customer Account details
	@Test(priority=6)
	public void testViewRetailCustomerAccountDetails_153q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Name"),"Account Name");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Recognition Name"),"Recognition Name");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Recognition Title"),"Recognition Title");
		s_assert.assertTrue(crmAccountDetailsPage.isActiveLabelOnAccountDetailsSectionPresent(),"Active Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Status"),"Account Status Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Soft Termination Date"),"Soft Termination Date Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Account Number"),"Account Number Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Enrollment Sponsor"),"Enrollment Sponsor Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Placement Sponsor"),"Placement Sponsor Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Enrollment Date"),"Enrollment Date Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Main Phone"),"Main Phone Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnAccountDetailsSectionPresent("Email Address"),"Email Address Label is not Present");
		crmAccountDetailsPage.clickAccountDetailsButton("Edit Account");
		s_assert.assertTrue(crmAccountDetailsPage.verifyRecognizationNameUnderAccountEditInAccountInformationIsEditable(), "Recognition Name is not Editable");
		s_assert.assertTrue(crmAccountDetailsPage.verifyAccountNameNameUnderAccountEditInAccountInformationIsEditable(), "Account Name is not Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Account Type"), "Account Type is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Soft Termination Date"), "Soft Termination Date is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Account Number"), "Account Number is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Enrollment Sponsor"), "Enrollment Sponsor is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Placement Sponsor"), "Placement Sponsor is Editable");
		s_assert.assertFalse(crmAccountDetailsPage.verifyFieldsUnderAccountEditInAccountInformationIsEditable("Enrollment Date"), "Enrollment Date is Editable");
		crmAccountDetailsPage.clickCancelButtonUnderAccountEditInAccountInformationSection();
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Address Line 1"), "Address Line 1 Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Address Line 2"), "Address Line 2 Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Locale"), "Locale Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Sub Region"), "Sub Region Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Region"), "Region Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Postal code"), "Postal code Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnMainAddressSectionPresent("Country"), "Country Label is not Present");
		s_assert.assertAll();
	}

	//QTest ID TC-274:Add Shipping Profile for Consultant
	@Test(priority=7)
	public void testAddShippingProfileForConsultant_274q() throws InterruptedException{
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypeRegion = "Region";
		String addressTypePostalCode = "Postal code";
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstName = TestConstants.FIRST_NAME+randomNum;
		String lastName = TestConstants.LAST_NAME;
		String fullName = shippingProfileFirstName+" "+lastName;
		//crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAddNewShippingProfileBtn();
		crmAccountDetailsPage.updateShippingProfileName(fullName);
		crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(crmAccountDetailsPage.isProfileNameValueOfDefaultShippingProfilesPresent(fullName), "Profile Name Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine), "Address Line Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeRegion,province), "Region Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postal), "Postal Not Matched");
		s_assert.assertAll();
	}

	//QTest ID TC-275:Add Shipping Profile for PC
	@Test(priority=8)
	public void testAddShippingProfileForPC_275q() throws InterruptedException{
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypeRegion = "Region";
		String addressTypePostalCode = "Postal code";
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstName = TestConstants.FIRST_NAME+randomNum;
		String lastName = TestConstants.LAST_NAME;
		String fullName = shippingProfileFirstName+" "+lastName;
		//crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAddNewShippingProfileBtn();
		crmAccountDetailsPage.updateShippingProfileName(fullName);
		crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(crmAccountDetailsPage.isProfileNameValueOfDefaultShippingProfilesPresent(fullName), "Profile Name Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine), "Address Line Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeRegion,province), "Region Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postal), "Postal Not Matched");
		s_assert.assertAll();
	}

	//QTest ID TC-276:Add Shipping Profile for RC
	@Test(priority=9)
	public void testAddShippingProfileForRC_276q() throws InterruptedException{
		String addressTypeLine1 = "Address Line 1";
		String addressTypeLocale = "Locale";
		String addressTypeRegion = "Region";
		String addressTypePostalCode = "Postal code";
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstName = TestConstants.FIRST_NAME+randomNum;
		String lastName = TestConstants.LAST_NAME;
		String fullName = shippingProfileFirstName+" "+lastName;
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAddNewShippingProfileBtn();
		crmAccountDetailsPage.updateShippingProfileName(fullName);
		crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		s_assert.assertTrue(crmAccountDetailsPage.isProfileNameValueOfDefaultShippingProfilesPresent(fullName), "Profile Name Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLine1,addressLine), "Address Line Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeLocale,city), "Locale Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypeRegion,province), "Region Not Matched");
		s_assert.assertTrue(crmAccountDetailsPage.isAddressLocaleRegionPostalCodeValueOfDefaultShippingProfilesPresent(addressTypePostalCode,postal), "Postal Not Matched");
		s_assert.assertAll();
	}

	//QTest ID TC-90:View Shipping Profile for Consultant
	@Test(priority=10)
	public void testViewShippingProfileForConsultant_90q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Action"),"Action label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Name"),"Name label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("ProfileName"),"ProfileName label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Default"),"Default label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Address Line 1"),"Address Line 1 label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Address Line 2"),"Address Line 2 label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Locale"),"Locale label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Region"),"Region label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Postal code"),"Postal code label is not present in Shipping address section");
		String shippingProfilesCount = crmAccountDetailsPage.getShippingProfilesCount();
		String countDisplayedWithShippingLink = crmAccountDetailsPage.getCountDisplayedWithLink("Shipping Profiles");
		s_assert.assertTrue(shippingProfilesCount.equals(countDisplayedWithShippingLink), "shipping profiles count = "+shippingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithShippingLink);
		s_assert.assertTrue(crmAccountDetailsPage.isOnlyOneShippingProfileIsDefault(),"default shipping profiles is not one");
		s_assert.assertAll();
	}

	//QTest ID TC-91:View Shipping Profile for PC
	@Test(priority=11)
	public void testViewShippingProfileForPC_91q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Action"),"Action label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Name"),"Name label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("ProfileName"),"ProfileName label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Default"),"Default label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Address Line 1"),"Address Line 1 label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Address Line 2"),"Address Line 2 label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Locale"),"Locale label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Region"),"Region label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Postal code"),"Postal code label is not present in Shipping address section");
		String shippingProfilesCount = crmAccountDetailsPage.getShippingProfilesCount();
		String countDisplayedWithShippingLink = crmAccountDetailsPage.getCountDisplayedWithLink("Shipping Profiles");
		s_assert.assertTrue(shippingProfilesCount.equals(countDisplayedWithShippingLink), "shipping profiles count = "+shippingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithShippingLink);
		s_assert.assertTrue(crmAccountDetailsPage.isOnlyOneShippingProfileIsDefault(),"default shipping profiles is not one");
		s_assert.assertAll();
	}

	//QTest ID TC-92:View Shipping Profile for RC
	@Test(priority=12)
	public void testViewShippingProfileForRC_92q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Action"),"Action label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Name"),"Name label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("ProfileName"),"ProfileName label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Default"),"Default label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Address Line 1"),"Address Line 1 label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Address Line 2"),"Address Line 2 label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Locale"),"Locale label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Region"),"Region label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Postal code"),"Postal code label is not present in Shipping address section");
		String shippingProfilesCount = crmAccountDetailsPage.getShippingProfilesCount();
		String countDisplayedWithShippingLink = crmAccountDetailsPage.getCountDisplayedWithLink("Shipping Profiles");
		s_assert.assertTrue(shippingProfilesCount.equals(countDisplayedWithShippingLink), "shipping profiles count = "+shippingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithShippingLink);
		s_assert.assertTrue(crmAccountDetailsPage.isOnlyOneShippingProfileIsDefault(),"default shipping profiles is not one");
		s_assert.assertAll();
	}

	//QTest ID TC-87:Edit Shipping Profile for Consultant
	@Test(priority=13)
	public void testEditShippingProfileForConsultant_87q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstNameWithSpecialChar = TestConstants.FIRST_NAME+randomNum+"%%";
		String lastName = TestConstants.LAST_NAME;
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickEditFirstShippingProfile();
		crmAccountDetailsPage.updateShippingProfileName(shippingProfileFirstNameWithSpecialChar+" "+lastName);
		//crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		//crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
		crmAccountDetailsPage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		String updatedProfileName = crmAccountDetailsPage.getDefaultSelectedShippingAddressName();
		s_assert.assertTrue(updatedProfileName.contains(shippingProfileFirstNameWithSpecialChar), "Expected shipping profile name is "+shippingProfileFirstNameWithSpecialChar+"Actual on UI "+updatedProfileName);
		s_assert.assertAll();
	}

	//QTest ID TC-88:Edit Shipping Profile for PC
	@Test(priority=14)
	public void testEditShippingProfileForPC_88q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstNameWithSpecialChar = TestConstants.FIRST_NAME+randomNum+"%%";
		String lastName = TestConstants.LAST_NAME;
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickEditFirstShippingProfile();
		crmAccountDetailsPage.updateShippingProfileName(shippingProfileFirstNameWithSpecialChar+" "+lastName);
		//crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		//crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
		crmAccountDetailsPage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		String updatedProfileName = crmAccountDetailsPage.getDefaultSelectedShippingAddressName();
		s_assert.assertTrue(updatedProfileName.contains(shippingProfileFirstNameWithSpecialChar), "Expected shipping profile name is "+shippingProfileFirstNameWithSpecialChar+"Actual on UI "+updatedProfileName);
		s_assert.assertAll();
	}

	//QTest ID TC-89:Edit Shipping Profile for RC
	@Test(priority=15)
	public void testEditShippingProfileForRC_89q() throws InterruptedException	{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstNameWithSpecialChar = TestConstants.FIRST_NAME+randomNum+"%%";
		String lastName = TestConstants.LAST_NAME;
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickEditFirstShippingProfile();
		crmAccountDetailsPage.updateShippingProfileName(shippingProfileFirstNameWithSpecialChar+" "+lastName);
		//crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		//crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
		crmAccountDetailsPage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		String updatedProfileName = crmAccountDetailsPage.getDefaultSelectedShippingAddressName();
		s_assert.assertTrue(updatedProfileName.contains(shippingProfileFirstNameWithSpecialChar), "Expected shipping profile name is "+shippingProfileFirstNameWithSpecialChar+"Actual on UI "+updatedProfileName);
		s_assert.assertAll();
	}

	//QTest ID TC-94:Change default PC Shipping address
	@Test(priority=16)
	public void testChangeDefaultConsultantShippingAddress_94q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstName = TestConstants.FIRST_NAME+randomNum;
		String lastName = TestConstants.LAST_NAME;
		String profileName = shippingProfileFirstName+" "+lastName;
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		crmAccountDetailsPage.clickShippingProfiles();
		int noOfShippingProfileInteger = Integer.parseInt(crmAccountDetailsPage.getShippingProfilesCount());
		if(noOfShippingProfileInteger==1){
			crmAccountDetailsPage.clickAddNewShippingProfileBtn();
			crmAccountDetailsPage.updateShippingProfileName(profileName);
			crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
			crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
			crmAccountDetailsPage.clickUserEnteredAddressRadioBtn();
			crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
			crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
			crmAccountDetailsPage.closeAllOpenedTabs();
			crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
			crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		}
		String profileNameBeforeEdit =  crmAccountDetailsPage.clickEditOfNonDefaultShippingProfile();
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.clickUserEnteredAddressRadioBtn();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
		crmAccountDetailsPage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		String profileNameAfterEdit = crmAccountDetailsPage.getDefaultSelectedShippingAddressName();
		s_assert.assertTrue(profileNameBeforeEdit.equalsIgnoreCase(profileNameAfterEdit), "Expected profile name "+profileNameBeforeEdit+"Actual on UI "+profileNameAfterEdit);
		s_assert.assertAll();
	}

	//QTest ID - 95:Change default consultant Shipping address
	@Test(priority=17)
	public void testChangeDefaultConsultantShippingAddress_95q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstName = TestConstants.FIRST_NAME+randomNum;
		String lastName = TestConstants.LAST_NAME;
		String profileName = shippingProfileFirstName+" "+lastName;
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		crmAccountDetailsPage.clickShippingProfiles();
		int noOfShippingProfileInteger = Integer.parseInt(crmAccountDetailsPage.getShippingProfilesCount());
		if(noOfShippingProfileInteger==1){
			crmAccountDetailsPage.clickAddNewShippingProfileBtn();
			crmAccountDetailsPage.updateShippingProfileName(profileName);
			crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
			crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
			crmAccountDetailsPage.clickUserEnteredAddressRadioBtn();
			crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
			crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
			crmAccountDetailsPage.closeAllOpenedTabs();
			crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
			crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		}
		String profileNameBeforeEdit =  crmAccountDetailsPage.clickEditOfNonDefaultShippingProfile();
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.clickUserEnteredAddressRadioBtn();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
		crmAccountDetailsPage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		String profileNameAfterEdit = crmAccountDetailsPage.getDefaultSelectedShippingAddressName();
		s_assert.assertTrue(profileNameBeforeEdit.equalsIgnoreCase(profileNameAfterEdit), "Expected profile name "+profileNameBeforeEdit+"Actual on UI "+profileNameAfterEdit);
		s_assert.assertAll();
	}

	//QTest ID TC-69:View Billing profile for a consultant
	@Test(priority=18)
	public void testViewBillingProfileForConsultant_69q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Action"),"Action label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Name"),"Name label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Profile Name"),"Profile Name label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Default"),"Default label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Name On Card"),"Name On Card label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Valid Thru"),"Valid Thru label is not present in Billing profiles section");

		String billingProfilesCount = crmAccountDetailsPage.getBillingProfilesCount();
		String countDisplayedWithBillingLink = crmAccountDetailsPage.getCountDisplayedWithLink("Billing");
		s_assert.assertTrue(billingProfilesCount.equals(countDisplayedWithBillingLink), "billing profiles count = "+billingProfilesCount+"while count Displayed With billing Link = "+countDisplayedWithBillingLink);
		s_assert.assertTrue(crmAccountDetailsPage.isOnlyOneBillingProfileIsDefault(),"default billing profiles is not one");

		crmAccountDetailsPage.clickRandomBillingProfile();
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Profile Name"),"Profile Name label is not present in Billing profile detail section on");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Account"),"Account label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Name On Card"),"Name On Card label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Type"),"Payment Type label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Vendor"),"Payment Vendor label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Default"),"Default label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Valid Thru"),"Valid Thru label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("End Date"),"End Date label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Start Date"),"Start Date label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Last Modified By"),"Last Modified By label is not present in Billing profile detail section");

		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 1"),"Address Line 1 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 2"),"Address Line 2 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 3"),"Address Line 3 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Locale"),"Locale label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Sub Region"),"Sub Region label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Region"),"Region label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Postal code"),"Postal code label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Latitude"),"Latitude label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Longitude"),"Longitude label is not present in Billing Address section");

		s_assert.assertTrue(crmAccountDetailsPage.isCreditCardOnBillingProfileDetailSectionOnNewTabIsSixteenEncryptedDigit(),"credit card number on billing profile detail section is either not of 16 digit or not encrypted");
		s_assert.assertTrue(crmAccountDetailsPage.isExpiryYearOnBillingProfileDetailSectionOnNewTabIsInYYYYFormat(),"expiry year format on billing profile detail section is not yyyy");
		s_assert.assertAll();
	}

	//QTest ID TC-70:View Billing profile for a PC
	@Test(priority=19)
	public void testViewBillingProfileForPC_70q()throws InterruptedException{		
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Action"),"Action label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Name"),"Name label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Profile Name"),"Profile Name label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Default"),"Default label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Name On Card"),"Name On Card label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Valid Thru"),"Valid Thru label is not present in Billing profiles section");
		String billingProfilesCount = crmAccountDetailsPage.getBillingProfilesCount();
		String countDisplayedWithBillingLink = crmAccountDetailsPage.getCountDisplayedWithLink("Billing");
		s_assert.assertTrue(billingProfilesCount.equals(countDisplayedWithBillingLink), "billing profiles count = "+billingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithBillingLink);
		s_assert.assertTrue(crmAccountDetailsPage.isOnlyOneBillingProfileIsDefault(),"default billing profiles is not one");
		crmAccountDetailsPage.clickRandomBillingProfile();
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Profile Name"),"Profile Name label is not present in Billing profile detail section on");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Account"),"Account label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Name On Card"),"Name On Card label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Type"),"Payment Type label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Vendor"),"Payment Vendor label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Default"),"Default label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Valid Thru"),"Valid Thru label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("End Date"),"End Date label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Start Date"),"Start Date label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Last Modified By"),"Last Modified By label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isCreditCardOnBillingProfileDetailSectionOnNewTabIsSixteenEncryptedDigit(),"credit card number on billing profile detail section is either not of 16 digit or not encrypted");
		s_assert.assertTrue(crmAccountDetailsPage.isExpiryYearOnBillingProfileDetailSectionOnNewTabIsInYYYYFormat(),"expiry year format on billing profile detail section is not yyyy");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 1"),"Address Line 1 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 2"),"Address Line 2 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 3"),"Address Line 3 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Locale"),"Locale label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Sub Region"),"Sub Region label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Region"),"Region label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Postal code"),"Postal code label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Latitude"),"Latitude label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Longitude"),"Longitude label is not present in Billing Address section");
		s_assert.assertAll();
	}

	//QTest ID TC-71:View Billing profile for a RC
	@Test(priority=20)
	public void testViewBillingProfileForRC_71q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Action"),"Action label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Name"),"Name label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Profile Name"),"Profile Name label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Default"),"Default label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Name On Card"),"Name On Card label is not present in Billing profiles section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionPresent("Valid Thru"),"Valid Thru label is not present in Billing profiles section");
		String billingProfilesCount = crmAccountDetailsPage.getBillingProfilesCount();
		String countDisplayedWithBillingLink = crmAccountDetailsPage.getCountDisplayedWithLink("Billing");
		s_assert.assertTrue(billingProfilesCount.equals(countDisplayedWithBillingLink), "billing profiles count = "+billingProfilesCount+"while count Displayed With Shipping Link = "+countDisplayedWithBillingLink);
		s_assert.assertTrue(crmAccountDetailsPage.isOnlyOneBillingProfileIsDefault(),"default billing profiles is not one");
		crmAccountDetailsPage.clickRandomBillingProfile();
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Profile Name"),"Profile Name label is not present in Billing profile detail section on");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Account"),"Account label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Name On Card"),"Name On Card label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Type"),"Payment Type label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Payment Vendor"),"Payment Vendor label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Default"),"Default label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Valid Thru"),"Valid Thru label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("End Date"),"End Date label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Start Date"),"Start Date label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingProfileDetailSectionOnNewTabPresent("Last Modified By"),"Last Modified By label is not present in Billing profile detail section");
		s_assert.assertTrue(crmAccountDetailsPage.isCreditCardOnBillingProfileDetailSectionOnNewTabIsSixteenEncryptedDigit(),"credit card number on billing profile detail section is either not of 16 digit or not encrypted");
		s_assert.assertTrue(crmAccountDetailsPage.isExpiryYearOnBillingProfileDetailSectionOnNewTabIsInYYYYFormat(),"expiry year format on billing profile detail section is not yyyy");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 1"),"Address Line 1 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 2"),"Address Line 2 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Address Line 3"),"Address Line 3 label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Locale"),"Locale label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Sub Region"),"Sub Region label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Region"),"Region label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Postal code"),"Postal code label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Latitude"),"Latitude label is not present in Billing Address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnBillingAddressSectionOnNewTabPresent("Longitude"),"Longitude label is not present in Billing Address section");
		s_assert.assertAll();
	}

	//QTest ID TC-98:Search for account by email address
	@Test(priority=21)
	public void testSearchForAccountByEmail_98q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.isNamePresentInAccountSection(consultantEmailID);
		s_assert.assertTrue(crmHomePage.isAccountLinkPresentInLeftNaviagation(), "Accounts link is not present on left navigation panel");
		s_assert.assertTrue(crmHomePage.isContactsLinkPresentInLeftNaviagation(), "Contacts link is not present on left navigation panel");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertAll();
	}

	// QTest ID TC-148q:Consultant detail view page
	@Test(priority=22)
	public void testConsultantDetailViewPage_148q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isConsultantDetailsHighlightPanelDisplayed("Account Type"),"Account Type field not present in Highlight panel");
		s_assert.assertTrue(crmAccountDetailsPage.isConsultantDetailsHighlightPanelDisplayed("Account Status"),"Account Status field not present in Highlight panel");
		s_assert.assertTrue(crmAccountDetailsPage.isConsultantDetailsHighlightPanelDisplayed("CID"),"CID field not present in Highlight panel");
		s_assert.assertTrue(crmAccountDetailsPage.isConsultantDetailsHighlightPanelDisplayed("Country"),"Country field not present in Highlight panel");
		s_assert.assertTrue(crmAccountDetailsPage.isConsultantDetailsHighlightPanelDisplayed("Email Address"),"Email Address field not present in Highlight panel");
		s_assert.assertTrue(crmAccountDetailsPage.isConsultantDetailsHighlightPanelDisplayed("SV"),"SV field not present in Highlight panel");
		s_assert.assertTrue(crmAccountDetailsPage.isConsultantDetailsHighlightPanelDisplayed("PSQV"),"PSQV field not present in Highlight panel");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountMainMenuOptionsPresent("Contacts"),"contact option not present");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountMainMenuOptionsPresent("Autoships"),"Autoships option not present");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountMainMenuOptionsPresent("Performance KPIs"),"Performance KPIs option not present");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountMainMenuOptionsPresent("Shipping Profiles"),"Shipping Profiles option not present");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountMainMenuOptionsPresent("Billing  Profiles"),"Billing Profiles option not present");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountMainMenuOptionsPresent("Account Policies"),"Account Policies option not present");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountMainMenuOptionsPresent("Account Statuses History"),"Account Statuses History option not present");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountMainMenuOptionsPresent("Account History"),"Account History option not present");
		crmAccountDetailsPage.hoverOnMainMenuOptionsLink("Shipping Profiles");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Action"),"Action label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Name"),"Name label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("ProfileName"),"ProfileName label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Default"),"Default label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Address Line 1"),"Address Line 1 label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Address Line 2"),"Address Line 2 label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Locale"),"Locale label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Region"),"Region label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnShippingAddressSectionPresent("Postal code"),"Postal code label is not present in Shipping address section");
		s_assert.assertTrue(crmAccountDetailsPage.isRelatedListItemPresent(),"related item is not present");
		crmAccountDetailsPage.clickPerformanceKPIsName();
		s_assert.assertTrue(crmAccountDetailsPage.isPerformanceKPIsDetailsPresent(),"Performance KPIs Detail is not present on UI as expected");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Period"),"Period label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Account"),"Account label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Recognized Title"),"Recognized Title label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Qualification Title"),"Qualification Title label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Sales Volume"),"Sales Volume label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Paid As Title"),"Paid As Title label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Estimated Sales Volume"),"Estimated Sales Volume label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Period Status"),"Period Status label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Created By"),"Created By label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("EC Leg Current"),"Period label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("EC Leg Prior Month"),"EC Leg Prior Month label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("LV EC Legs"),"LV EC Legs label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("L1+L2 Volume"),"L1+L2 Volume label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("L1-L6 Volume"),"L1-L6 Volume label is not present under PerformanceKPIs Details Section");
		s_assert.assertAll();
	}	

	//QTest ID TC-149:Preferred Customer detail view page
	@Test(priority=23)
	public void testPreferredCustomerdDetailViewTest_149q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Contacts"),"Contact link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Autoships"),"Autoships link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Account Notes"),"Account Notes link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Shipping Profiles"),"Shipping Profiles link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Billing  Profiles"),"Billing  Profiles link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Account Statuses History"),"Account Statuses History link is not displayed on account in section account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Contacts"),"Contacts mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Autoships"),"Autoships mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Account Notes"),"Account Notes mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Shipping Profiles"),"Shipping Profiles mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Billing"),"Billing Profiles mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Account Statuses History"),"Contacts mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Contacts"),"Contacts blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Autoships"),"Autoships blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Account Notes"),"Account Activities blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Shipping Profiles"),"Shipping Profiles blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Billing"),"Billing Profiles blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Account Statuses History"),"Account Statuses History blue line section is not displayed on account section in account details page");
		s_assert.assertAll();
	}

	//QTest ID TC-150:Retail Customer detail view page
	@Test(priority=24)
	public void testRetailCustomerdDetailViewTest_150q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Contacts"),"Contact link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Account Notes"),"Account Notes link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Shipping Profiles"),"Shipping Profiles link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Billing  Profiles"),"Billing  Profiles link is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isLinkOnAccountSectionPresent("Account Statuses History"),"Account Statuses History link is not displayed on account in section account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Contacts"),"Contacts mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Account Notes"),"Account Notes mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Shipping Profiles"),"Shipping Profiles mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Billing"),"Billing Profiles mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverSectionPresentOfLink("Account Statuses History"),"Contacts mouse hover section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Contacts"),"Contacts blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Account Notes"),"Account Activities blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Shipping Profiles"),"Shipping Profiles blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Billing"),"Billing Profiles blue line section is not displayed on account section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isListItemsWithBlueLinePresent("Account Statuses History"),"Account Statuses History blue line section is not displayed on account section in account details page");
		s_assert.assertAll();
	}

	//QTest ID TC-107:Edit Consultant Account details
	@Test(priority=25)
	public void testEditConsultantAccountDetails_107q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAccountDetailsButton("Edit Account");
		crmAccountDetailsPage.updateAccountNameField(TestConstants.CRM_INVALID_ACCOUNT_NAME);
		//save Account information
		crmAccountDetailsPage.clickSaveBtnUnderAccountDetail();
		s_assert.assertTrue(crmAccountDetailsPage.validateErrorMsgIsDisplayed(),"Error message is not displayed for Account name field");
		//update account name field with valid name
		crmAccountDetailsPage.updateAccountNameField(TestConstants.CRM_VALID_ACCOUNT_NAME+randomNum);
		crmAccountDetailsPage.clickSaveBtnUnderAccountDetail();
		s_assert.assertTrue(crmAccountDetailsPage.getAccountName().contains(TestConstants.CRM_VALID_ACCOUNT_NAME+randomNum),"?ccount name is not updated!!");
		s_assert.assertAll();
	}

	//QTest ID TC-145:Edit Spouse Contact details for Consultant
	@Test(priority=26)
	public void testEditConsultantContactDetails_145q() throws InterruptedException	{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickContactOnFirstRowInSearchResults();
		//verify contact type should be 'primary'
		s_assert.assertTrue(crmContactDetailsPage.getContactType().trim().equalsIgnoreCase("Primary".trim()),"Contact type is not primary");
		//click on edit button under contact detail section
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		//verify primary contact type cannot be deleted
		s_assert.assertTrue(crmContactDetailsPage.validatePrimaryContactTypeCannotBeDeleted(),"Primary contact type can be deleted");
		//Update first & last name
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify first,last name updated?
		s_assert.assertTrue(crmContactDetailsPage.getName().trim().contains(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME.trim()),"Name is not updated on store front");
		//click on edit button under contact detail section
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME+randomNum);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify user name field is updated with the new name
		s_assert.assertTrue(crmContactDetailsPage.getName().trim().contains(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME+randomNum),"User Name is not updated on store front");
		//edit user name with alpha numeric and special characters
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAMEWITHSPCLCHARS+randomNum);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		crmContactDetailsPage.clickContactDetailEditBtn();
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//edit main phone field with less than 10 digits
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateMainPhoneNoField("");
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify error message is displayed and should not update phone number field
		s_assert.assertTrue(crmContactDetailsPage.validateErrorMsgIsDisplayed(),"Error message is not displayed for Main Phone Number field");
		//edit main phone field with valid phone no
		crmContactDetailsPage.updateMainPhoneNoField(TestConstants.CRM_CONTACTDETAILS_VALIDPHONENUM);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify main phone is updated in store front?
		s_assert.assertTrue(crmContactDetailsPage.getMainPhone().trim().equalsIgnoreCase(TestConstants.CRM_CONTACTDETAILS_VALIDPHONENUM.trim()),"Main Phone field is not updated");
		s_assert.assertAll();
	}

	// QTest ID TC-146:Edit Spouse Contact details for PC
	@Test(priority=27)
	public void testEditPreferredCustomerContactDetails_146q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickContactOnFirstRowInSearchResults();
		//verify contact type should be 'primary'
		s_assert.assertTrue(crmContactDetailsPage.getContactType().trim().equalsIgnoreCase("Primary".trim()),"Contact type is not primary");
		//click on edit button under contact detail section
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		//verify primary contact type cannot be deleted
		s_assert.assertTrue(crmContactDetailsPage.validatePrimaryContactTypeCannotBeDeleted(),"Primary contact type can be deleted");
		//Update first & last name
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify first,last name updated?
		s_assert.assertTrue(crmContactDetailsPage.getName().trim().contains(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME.trim()),"Name is not updated on store front");
		//click on edit button under contact detail section
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME+randomNum);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify user name field is updated with the new name
		s_assert.assertTrue(crmContactDetailsPage.getName().trim().contains(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME+randomNum),"User Name is not updated on store front");
		//edit user name with alpha numeric and special characters
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAMEWITHSPCLCHARS+randomNum);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		crmContactDetailsPage.clickContactDetailEditBtn();
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//edit main phone field with less than 10 digits
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateMainPhoneNoField("");
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify error message is displayed and should not update phone number field
		s_assert.assertTrue(crmContactDetailsPage.validateErrorMsgIsDisplayed(),"Error message is not displayed for Main Phone Number field");
		//edit main phone field with valid phone no
		crmContactDetailsPage.updateMainPhoneNoField(TestConstants.CRM_CONTACTDETAILS_VALIDPHONENUM);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify main phone is updated in store front?
		s_assert.assertTrue(crmContactDetailsPage.getMainPhone().trim().equalsIgnoreCase(TestConstants.CRM_CONTACTDETAILS_VALIDPHONENUM.trim()),"Main Phone field is not updated");
		s_assert.assertAll();
	}

	//QTest ID TC-147:Edit Spouse Contact details for RC
	@Test(priority=28)
	public void testEditRetailCustomerContactDetails_147q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickContactOnFirstRowInSearchResults();
		//verify contact type should be 'primary'
		s_assert.assertTrue(crmContactDetailsPage.getContactType().trim().equalsIgnoreCase("Primary".trim()),"Contact type is not primary");
		//click on edit button under contact detail section
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		//verify primary contact type cannot be deleted
		s_assert.assertTrue(crmContactDetailsPage.validatePrimaryContactTypeCannotBeDeleted(),"Primary contact type can be deleted");
		//Update first & last name
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify first,last name updated?
		s_assert.assertTrue(crmContactDetailsPage.getName().trim().contains(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME.trim()),"Name is not updated on store front");
		//click on edit button under contact detail section
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME+randomNum);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify user name field is updated with the new name
		s_assert.assertTrue(crmContactDetailsPage.getName().trim().contains(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME+randomNum),"User Name is not updated on store front");
		//edit user name with alpha numeric and special characters
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAMEWITHSPCLCHARS+randomNum);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		crmContactDetailsPage.clickContactDetailEditBtn();
		crmContactDetailsPage.updateFirstNameField(TestConstants.CRM_CONTACTDETAILS_FIRSTNAME);
		crmContactDetailsPage.updateLastNameField(TestConstants.CRM_CONTACTDETAILS_LASTNAME);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//edit main phone field with less than 10 digits
		crmContactDetailsPage.clickContactDetailEditBtn(); 
		crmContactDetailsPage.updateMainPhoneNoField("");
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify error message is displayed and should not update phone number field
		s_assert.assertTrue(crmContactDetailsPage.validateErrorMsgIsDisplayed(),"Error message is not displayed for Main Phone Number field");
		//edit main phone field with valid phone no
		crmContactDetailsPage.updateMainPhoneNoField(TestConstants.CRM_CONTACTDETAILS_VALIDPHONENUM);
		crmContactDetailsPage.clickSaveBtnUnderAccountDetail();
		//verify main phone is updated in store front?
		s_assert.assertTrue(crmContactDetailsPage.getMainPhone().trim().equalsIgnoreCase(TestConstants.CRM_CONTACTDETAILS_VALIDPHONENUM.trim()),"Main Phone field is not updated");
		s_assert.assertAll();
	}

	//QTest ID TC-65:Verify Display of Autoship details for a Consultant
	@Test(priority=29)
	public void testVerifyDisplayAutoshipDetailsForConsultant_65q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Name"),"Name mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Autoship Type"),"Autoship Type mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Autoship Status"),"Autoship Status mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Status"),"Status mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Last OrderDate"),"Last OrderDate mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Next Order Date"),"Next Order Date mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Total"),"Total mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("QV"),"QV mouse hover section is not displayed in Autoships section in account details page");
		crmAccountDetailsPage.getCountAutoships();
		crmAccountDetailsPage.clickAutoships();
		s_assert.assertTrue(crmAccountDetailsPage.getCountAutoships().equals(crmAccountDetailsPage.getCountAutoshipNumber()), "Autoships Numbers are not equal");
		crmAccountDetailsPage.clickFirstAutoshipID();
		//s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Autoship Number"),"Autoship Number is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Autoship Type"),"Autoship Type is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Autoship Status"),"Autoship Status is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Do Not Ship"),"Autoship Do not ship is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Start Date"),"Autoship Start Date is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("End Date"),"Autoship Status is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Completion Date"),"Autoship Completion Date is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Account"),"Autoship Account is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Source"),"Autoship Source is not Present"); 
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Last OrderDate"),"Autoship Last Order Date is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Next Order Date"),"Autoship Next Order Date is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Last Modified By"),"Autoship Last Modified By is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Created By"),"Autoship Created By is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Is Tax Exempt"),"In Pending Autoship Breakdown Tax Exempt is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("QV"),"In Pending Autoship Breakdown QV is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("CV"),"In Pending Autoship Breakdown CV is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Product Discount"),"In Pending Autoship Breakdown Product Discount is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Total Discount"),"In Pending Autoship Breakdown Total Discount is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Sub Total"),"In Pending Autoship Breakdown Sub Total is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Taxable Total"),"In Pending Autoship Breakdown Taxable Total is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Total"),"In Pending Autoship Breakdown Total is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Delay Count"),"In Pending Autoship Breakdown Delay Count is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Fuel Surcharge"),"In Pending Autoship Breakdown  Fuel Surcharge is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Residential Surcharge"),"In Pending Autoship Breakdown Residential Surcharge is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Product Tax"),"In Pending Autoship Breakdown Product Tax is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Total Tax"),"In Pending Autoship Breakdown Total Tax is not Present");  
		s_assert.assertAll();
	}

	//QTest ID TC-66:Verify Display of Autoship details for a PC
	@Test(priority=30)
	public void testVerifyDisplayAutoshipDetailsForPC_66q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Name"),"Name mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Autoship Type"),"Autoship Type mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Autoship Status"),"Autoship Status mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Status"),"Status mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Last OrderDate"),"Last OrderDate mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Next Order Date"),"Next Order Date mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("Total"),"Total mouse hover section is not displayed in Autoships section in account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMouseHoverAutoshipSectionPresentOfFields("QV"),"QV mouse hover section is not displayed in Autoships section in account details page");
		crmAccountDetailsPage.getCountAutoships();
		crmAccountDetailsPage.clickAutoships();
		s_assert.assertTrue(crmAccountDetailsPage.getCountAutoships().equals(crmAccountDetailsPage.getCountAutoshipNumber()), "Autoships Numbers are not equal");
		crmAccountDetailsPage.clickFirstAutoshipID();
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Autoship Number"),"Autoship Number is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Autoship Type"),"Autoship Type is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Autoship Status"),"Autoship Status is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Do Not Ship"),"Autoship Do not ship is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Start Date"),"Autoship Start Date is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("End Date"),"Autoship Status is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Completion Date"),"Autoship Completion Date is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Account"),"Autoship Account is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Source"),"Autoship Source is not Present"); 
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Last OrderDate"),"Autoship Last Order Date is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Next Order Date"),"Autoship Next Order Date is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Last Modified By"),"Autoship Last Modified By is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderAutoshipNumberPresent("Created By"),"Autoship Created By is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Is Tax Exempt"),"In Pending Autoship Breakdown Tax Exempt is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("QV"),"In Pending Autoship Breakdown QV is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("CV"),"In Pending Autoship Breakdown CV is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Product Discount"),"In Pending Autoship Breakdown Product Discount is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Total Discount"),"In Pending Autoship Breakdown Total Discount is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Sub Total"),"In Pending Autoship Breakdown Sub Total is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Taxable Total"),"In Pending Autoship Breakdown Taxable Total is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Total"),"In Pending Autoship Breakdown Total is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Delay Count"),"In Pending Autoship Breakdown Delay Count is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Fuel Surcharge"),"In Pending Autoship Breakdown  Fuel Surcharge is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Residential Surcharge"),"In Pending Autoship Breakdown Residential Surcharge is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Product Tax"),"In Pending Autoship Breakdown Product Tax is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelUnderPendingAutoshipBreakdownPresent("Total Tax"),"In Pending Autoship Breakdown Total Tax is not Present");  
		s_assert.assertAll();
	}

	// QTest ID TC-96:Search for account by Account number
	@Test(priority=31)
	public void testSearchForAccountByAccountNumber_96q() throws InterruptedException {
		List<Map<String, Object>> randomAccountNumberList =  null;
		randomAccountNumberList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
		String accountNumber = (String) getValueFromQueryResult(randomAccountNumberList, "AccountNumber");
		crmHomePage.enterTextInSearchFieldAndHitEnter(accountNumber);
		s_assert.assertTrue(crmHomePage.isAccountLinkPresentInLeftNaviagation(),"Accounts link is not present in the left navigation section");
		s_assert.assertTrue(crmHomePage.isContactsLinkPresentInLeftNaviagation(),"Contacts link is not present in the left navigation section");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account details page is not present");
		s_assert.assertTrue(crmAccountDetailsPage.getAccountNumberFromAccountDetailsPage().equals(accountNumber),"Account details page is not present");
		s_assert.assertAll();
	}	

	//QTest ID TC-99:Search for account by partial email address
	@Test(priority=32)
	public void testSearchForAccountByPartialEmail_99q() throws InterruptedException{
		String consultantEmailID= null;
		List<Map<String, Object>> randomConsultantUserList =  null;
		randomConsultantUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
		accountID = String.valueOf(getValueFromQueryResult(randomConsultantUserList, "AccountID"));
		randomConsultantUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID),RFO_DB);
		consultantEmailID = String.valueOf(getValueFromQueryResult(randomConsultantUserList, "EmailAddress"));
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID.split("\\.")[0]);
		s_assert.assertTrue(crmHomePage.isAccountLinkPresentInLeftNaviagation(), "Accounts link is not present on left navigation panel");
		s_assert.assertTrue(crmHomePage.isContactsLinkPresentInLeftNaviagation(), "Contacts link is not present on left navigation panel");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertAll();
	}

	// QTest ID TC-103:Search for account by partial name
	@Test(priority=33)
	public void testSearchForAccountByPartialName_103q() throws InterruptedException{
		List<Map<String, Object>> randomFirstName =  null;
		String firstName = null;
		randomFirstName = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_FirstName_RFO,countryId),RFO_DB); 
		firstName = (String) getValueFromQueryResult(randomFirstName, "FirstName");  
		logger.info("The first name is "+firstName); 
		//split the first name returned,and search with start 3 characters
		crmHomePage.enterTextInSearchFieldAndHitEnter(firstName.split("(?<=\\G...)")[0]);
		s_assert.assertTrue(crmHomePage.isAccountLinkPresentInLeftNaviagation(), "Accounts link is not present on left navigation panel");
		s_assert.assertTrue(crmHomePage.isContactsLinkPresentInLeftNaviagation(), "Contacts link is not present on left navigation panel");
		crmHomePage.clickNameOnFirstRowInSearchResults();
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountNumberFieldDisplayedAndNonEmpty(),"Account Number is not displayed on account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountTypeFieldDisplayedAndNonEmpty(),"Account Type is not displayed on account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusFieldDisplayedAndNonEmpty(),"Account Status is not displayed on account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isCountryFieldDisplayedAndNonEmpty(),"Country field is not displayed on account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isEnrollmentDateFieldDisplayedAndNonEmpty(),"Enrollment Date is not displayed on account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isMainPhoneFieldDisplayedAndNonEmpty(),"Main Phone is not displayed on account details page");
		s_assert.assertTrue(crmAccountDetailsPage.isEmailAddressFieldDisplayedAndNonEmpty(),"Email Address is not displayed on account details page");
		s_assert.assertAll();
	}

	//QTest ID TC-81:Delete shipping Profile for consultant
	@Test(priority=34)
	public void testDeleteShippingProfileForConsultant_81q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String profileName=TestConstants.CRM_PROFILE_NAME+randomNum;
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickDeleteForTheDefaultShippingProfileSelected();
		crmAccountDetailsPage.clickOKOnDeleteDefaultShippingProfilePopUp();
		s_assert.assertTrue(crmAccountDetailsPage.validateDefaultShippingAddressCanNotBeDeleted(),"Default shipping address is deleted");
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		crmAccountDetailsPage.addANewShippingProfileAndMakeItDefault(profileName, countryName, addressLine, city, province, postal, phoneNumber);
		crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
		crmAccountDetailsPage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		//s_assert.assertTrue(crmHomePage.getProfileNameOfTheDefaultShippingProfile().trim().equalsIgnoreCase(TestConstants.CRM_NEW_PROFILENAME_CA),"Existing shipping profile is still marked as default");
		String deletedProfile = crmAccountDetailsPage.clickDeleteForNonDefaultShippingProflle();
		crmAccountDetailsPage.clickOKOnDeleteDefaultShippingProfilePopUp();
		s_assert.assertTrue(crmAccountDetailsPage.isNonDefaultShippingProfileDeleted(deletedProfile),"Non Default shipping profile is not deleted");
		s_assert.assertAll();
	}

	//QTest ID TC-82:Delete shipping Profile for PC
	@Test(priority=35)
	public void testDeleteShippingProfileForPCUser_82q() throws InterruptedException {
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String profileName=TestConstants.CRM_PROFILE_NAME+randomNum;
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickDeleteForTheDefaultShippingProfileSelected();
		crmAccountDetailsPage.clickOKOnDeleteDefaultShippingProfilePopUp();
		s_assert.assertTrue(crmAccountDetailsPage.validateDefaultShippingAddressCanNotBeDeleted(),"Default shipping address is deleted");
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		crmAccountDetailsPage.addANewShippingProfileAndMakeItDefault(profileName, countryName, addressLine, city, province, postal, phoneNumber);
		crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
		crmAccountDetailsPage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		//s_assert.assertTrue(crmHomePage.getProfileNameOfTheDefaultShippingProfile().trim().equalsIgnoreCase(TestConstants.CRM_NEW_PROFILENAME_CA),"Existing shipping profile is still marked as default");
		String deletedProfile = crmAccountDetailsPage.clickDeleteForNonDefaultShippingProflle();
		crmAccountDetailsPage.clickOKOnDeleteDefaultShippingProfilePopUp();
		s_assert.assertTrue(crmAccountDetailsPage.isNonDefaultShippingProfileDeleted(deletedProfile),"Non Default shipping profile is not deleted");
		s_assert.assertAll();;
	}

	//QTest ID TC-1188:Save Main address as shipping for consultant in Salesforce
	@Test(priority=36)
	public void testSaveMainAddressAsShippingForConsultant_1188q() throws InterruptedException {
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		//click on 'Save as Shipping' for consultant and validate Main address is saved as shipping Profile(s)
		s_assert.assertTrue(crmAccountDetailsPage.validateMainAddressIsSavedAsShippingProfile(),"Main address is not saved as Shipping profile");
		s_assert.assertAll();
	}

	//QTest ID TC-1189:Save Main address as shipping for PC in Salesforce
	@Test(priority=37)
	public void testSaveMainAddressAsShippingForPC_1189q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		//click on 'Save as Shipping' for consultant and validate Main address is saved as shipping Profile(s)
		s_assert.assertTrue(crmAccountDetailsPage.validateMainAddressIsSavedAsShippingProfile(),"Main address is not saved as Shipping profile");
		s_assert.assertAll();
	}

	//QTest ID TC-104:Add a new contact - spouse to a consultant
	@Test(priority=38)
	public void testAddNewContactSpouseToConsultant_104q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String firstName = TestConstants.FIRST_NAME+randomNum;
		String lastName = firstName;
		String combineFullName = firstName+" "+lastName;
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAccountMainMenuOptions("Contacts");
		if(crmAccountDetailsPage.verifyIsSpouseContactTypePresentNew(crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Contacts"))==false){
			crmAccountDetailsPage.clickNewContactButtonUnderContactSection();
			crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(firstName, lastName);
			crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(TestConstants.PHONE_NUMBER);
			crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Name").equals(combineFullName), "Name of the spouse not Matched");
		}else{
			logger.info("Spouse is already present");
			crmAccountDetailsPage.clickOnEditUnderContactSection("Spouse");
			crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(firstName, lastName);
			crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(TestConstants.PHONE_NUMBER);
			crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Name").equals(combineFullName), "Name of the spouse not Matched");
		}
		s_assert.assertAll();
	}

	//Hybris Project-4472:Verify Status changes of Soft terminated Consultant
	@Test(priority=39)
	public void testVerifyStatusChangesOfSoftTerminatedConsultant_4472() throws InterruptedException{
		getActiveEmailIdFromDB("Consultant");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		if(crmAccountDetailsPage.isAccountStatusActive()==true){
			crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
			crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown("Other");
			crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
			crmAccountDetailsPage.closeTabViaNumberWise(2);
			crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
			s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is Active");
		}
		s_assert.assertAll();
	}

	// Hybris Project-4474:Verify Status changes of Soft terminated RC
	@Test(priority=40)
	public void testVerifyStatusChangesOfSoftTerminatedRC_4474() throws InterruptedException{
		getActiveEmailIdHavingContactsFromDB("Retail Customer");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		if(crmAccountDetailsPage.isAccountStatusActive()==true){
			crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
			crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown("Other");
			crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
			crmAccountDetailsPage.closeTabViaNumberWise(2);
			crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
			s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is Active");
		}
		s_assert.assertAll();
	}

	//Hybris Project-4473:Verify Status changes of Soft terminated PC
	@Test(priority=41)
	public void testVerifyStatusChangesOfSoftTerminatedPC_4473() throws InterruptedException{
		getActiveEmailIdFromDB("Preferred Customer");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		if(crmAccountDetailsPage.isAccountStatusActive()==true){
			crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
			crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown("Other");
			crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
			crmAccountDetailsPage.closeTabViaNumberWise(2);
			crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
			s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is Active");
		}
		s_assert.assertAll();
	}

	//Hybris Project-4518:Change Account status for Preferred customer from Active to Inactive
	@Test(priority=42, enabled=false)
	public void testChangeAccountStatusForPreferredCustomerFromActiveToInactive_4518() throws InterruptedException{
		getActiveEmailIdFromDB("Preferred Customer");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown("Other");
		crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(),"Account status is active");
		crmAccountDetailsPage.clickMyAccountButton("My Account");
		s_assert.assertTrue(crmAccountDetailsPage.handleAlertPopUpForMyAccountProxy(),"account is active and proxy of my account is allowed");
		//crmAccountDetailsPage.clickAccountMainMenuOptions("Autoships");
		//s_assert.assertFalse(crmAccountDetailsPage.isAutoshipStatusActive(),"Autoship Status is active");
		s_assert.assertAll();
	}

	//QTest ID TC-1196:Change Account status for Preferred customer from Inactive to Active
	@Test(priority=43, enabled=false)
	public void testChangeAccountStatusForPreferredCustomerFromInactiveToActive_1196q() throws InterruptedException{
		getActiveEmailIdFromDB("Preferred Customer");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		//Active To Inactive
		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown("Other");
		crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(),"Account status is active");
		//Inactive To Active
		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown("Changed my mind");
		crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(),"Account status is not active");///// code updated
		s_assert.assertTrue(crmAccountDetailsPage.validateNewUrlWithNewWindow(),"new window is not opened for account proxy");
		//crmAccountDetailsPage.clickAccountMainMenuOptions("Autoships");
		//s_assert.assertTrue(crmAccountDetailsPage.isAutoshipStatusActive(),"Autoship Status is not active");
		s_assert.assertAll();
	}

	//QTest ID TC-51:View the Account Policies for Consultant
	@Test(priority=44)
	public void testViewAccountPoliciesForConsultant_51q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		int accountPoliciesHyperLinkCount=crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Policies");
		//Navigate to Account Policies
		crmAccountDetailsPage.clickAccountMainMenuOptions("Account Policies");
		int accountPoliciesTabCount=crmAccountDetailsPage.getCountUnderAccountPoliciesSection();
		//verify the counts are same for Account Policies Hyper link and  Account Policies Tab
		s_assert.assertTrue(accountPoliciesHyperLinkCount==accountPoliciesTabCount,"Counts are different for Act Policies HyperLink and Act Policies Tab");
		//verify the fields/labels in Account Policy Section
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Policies","Policy Link"),"'Policy Link' label is not present under Account Policy Section");
		s_assert.assertTrue(crmAccountDetailsPage.isDateReleasedOrAcceptedColumnPresent(),"'Date Accepted/Released' label is not present under Account Policy Section");
		s_assert.assertAll();
	}

	//QTest ID TC-1199:View Account Status History for RC
	@Test(priority=45)
	public void testViewAccountStatusHistoryForRC_1199q() throws InterruptedException{
		String otherReason = TestConstants.OTHER_REASON;
		String changedMyMind = TestConstants.CHANGED_MY_MIND;
		getActiveEmailIdHavingContactsFromDB("Retail Customer");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		int accountStatusesHistoryCount = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		if(accountStatusesHistoryCount>0){
			crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label 1 is not Present");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label 1 is not Present");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By 1 Label is not Present");
		}
		/////////////////////////////Change Customer's Status from Active to InActive//////////////////////////
		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(otherReason);
		String dateOfAccountStatusChangedFromActiveToInactive = crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is Active");
		int accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive == accountStatusesHistoryCount+1, "Account Statues History Actual is "+accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive + " & Account Statues History after changing statues Expected is "+ accountStatusesHistoryCount+1);
		crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");

		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label 2 is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label 2 is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By 2 Label is not Present");
		String accountStatusChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(0); // 0 : For Account Status
		String reasonChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(2);   // 2 : For Reason
		String lastModifiedChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(3);  // 3 : For Last Modified Value
		s_assert.assertTrue(accountStatusChangedFromActiveToInactive.equalsIgnoreCase("Soft Terminated Voluntary"), "Account Status 1 Not Matched Actual is "+accountStatusChangedFromActiveToInactive+" & Expected is Soft Terminated Voluntary");
		s_assert.assertTrue(reasonChangedFromActiveToInactive.equalsIgnoreCase(otherReason), "Reason Not 1 Matched Actual is "+reasonChangedFromActiveToInactive +" & Expected is "+otherReason);
		s_assert.assertTrue(lastModifiedChangedFromActiveToInactive.contains(dateOfAccountStatusChangedFromActiveToInactive), "Last Modified By 1 Date Not Matched Actual is "+lastModifiedChangedFromActiveToInactive+" & Expected is "+dateOfAccountStatusChangedFromActiveToInactive);
		/////////////////////////////Change Customer's Status from InActive to Active//////////////////////////

		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(changedMyMind);
		String dateOfAccountStatusChangedChangedFromInactiveToActive = crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");

		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		int accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive == accountStatusesHistoryCount+2, "Account Statues History Actual is "+ accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive + " & Account Statues History after changing statuses Expected is "+ accountStatusesHistoryCount+2);

		crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By Label is not Present");
		String account_StatusChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(0); // 0 : For Account Status
		String reasonChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(2);   // 2 : For Reason
		String last_ModifiedChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(3); // 3 : For Last Modified Value
		s_assert.assertTrue(account_StatusChangedFromInactiveToActive.equalsIgnoreCase("Active"), "Account Status 2 Not Matched Actual is "+account_StatusChangedFromInactiveToActive +" & Expected is Active");
		s_assert.assertTrue(reasonChangedFromInactiveToActive.equalsIgnoreCase(changedMyMind), "Reason Not 2 Matched Actual is "+reasonChangedFromInactiveToActive +"<<=& Expected is "+changedMyMind);
		s_assert.assertTrue(last_ModifiedChangedFromInactiveToActive.contains(dateOfAccountStatusChangedChangedFromInactiveToActive), "Last Modified By 2 Date Not Matched Actual is "+last_ModifiedChangedFromInactiveToActive + " & Expected is "+dateOfAccountStatusChangedChangedFromInactiveToActive);
		s_assert.assertAll();
	}

	//QTest ID TC-1193:Change Account status for Retail customer from Active to Inactive
	@Test(priority=46)
	public void testChangeAccountStatusForRetailCustomerFromActiveToInactive_1193q() throws InterruptedException{
		String otherReason = TestConstants.OTHER_REASON;
		getActiveEmailIdHavingContactsFromDB("Retail Customer");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");

		/////////////////////////////Change Customer's Status from Active to InActive//////////////////////////

		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(otherReason);
		crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");

		s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is Active");
		crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");

		String reasonChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(2);   // 2 : For Reason
		s_assert.assertTrue(reasonChangedFromActiveToInactive.equalsIgnoreCase(otherReason), "Reason Not 1 Matched Actual is "+reasonChangedFromActiveToInactive +" & Expected is "+otherReason);
		s_assert.assertAll();
	}

	//QTest ID TC-72:Verify Display of KPI details for a Consultant
	@Test(priority=47)
	public void testVerifyDisplayKPIDetailsForAConsultant_72q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnPerformanceKPIsSectionPresent("Action"),"Action label is not present in PerformanceKPIs section");
		//s_assert.assertTrue(crmAccountDetailsPage.isLabelOnPerformanceKPIsSectionPresent("Name"),"Name label is not present in PerformanceKPIs section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnPerformanceKPIsSectionPresent("Period"),"Period label is not present in PerformanceKPIs section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnPerformanceKPIsSectionPresent("Recognized Title"),"Recognized Title label is not present in PerformanceKPIs section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnPerformanceKPIsSectionPresent("Qualification Title"),"Qualification Title label is not present in PerformanceKPIs section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnPerformanceKPIsSectionPresent("Sales Volume"),"Sales Volume label is not present in PerformanceKPIs section");

		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnPerformanceKPIsSectionPresent("PSQV"),"PSQV label is not present in PerformanceKPIs section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOnPerformanceKPIsSectionPresent("EC Legs"),"EC Legs label is not present in PerformanceKPIs section");
		//
		//		String PerformanceKPIsCount = crmAccountDetailsPage.getPerformanceKPIsCount();
		//		String countDisplayedWithPerformanceKPIsLink = crmAccountDetailsPage.getCountDisplayedWithLink("Performance KPIs");
		//s_assert.assertTrue(PerformanceKPIsCount.equals(countDisplayedWithPerformanceKPIsLink), "billing profiles count = "+PerformanceKPIsCount+"while count Displayed With Shipping Link = "+countDisplayedWithPerformanceKPIsLink);
		s_assert.assertFalse(crmAccountDetailsPage.verifyActionItemsOnlyViewable(),"Action Item Editable and deletable");
		//		s_assert.assertTrue(crmAccountDetailsPage.isPeriodDisplayedInYYYY_MMFormat(),"Period date is not YYYY_MM Format");
		crmAccountDetailsPage.clickPerformanceKPIsName();
		s_assert.assertTrue(crmAccountDetailsPage.isPerformanceKPIsDetailsPresent(),"Performance KPIs Detail is not present on UI as expected");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Period"),"Period label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Account"),"Account label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Recognized Title"),"Recognized Title label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Qualification Title"),"Qualification Title label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Sales Volume"),"Sales Volume label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Paid As Title"),"Paid As Title label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Estimated Sales Volume"),"Estimated Sales Volume label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Period Status"),"Period Status label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("PSQV"),"PSQV label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Estimated PSQV"),"Estimated PSQV label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Period Closed Date"),"Period Closed Date label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Last Modified By"),"Last Modified By label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsInformation("Created By"),"Created By label is not present under PerformanceKPIs Information Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("EC Leg Current"),"Period label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("EC Leg Prior Month"),"EC Leg Prior Month label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("LV EC Legs"),"LV EC Legs label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("L1+L2 Volume"),"L1+L2 Volume label is not present under PerformanceKPIs Details Section");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelPresentUnderPerformanceKPIsDetails("L1-L6 Volume"),"L1-L6 Volume label is not present under PerformanceKPIs Details Section");
		s_assert.assertAll();
	}

	//QTest ID TC-1190:Save Main address as shipping for RC in Salesforce
	@Test(priority=48)
	public void testSaveMainAddressAsShippingForRC_1190q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.validateMainAddressIsSavedAsShippingProfile(),"Main address is not saved as Shipping profile");
		s_assert.assertAll();
	}

	//QTest ID TC-74:Single sign on to CRM
	@Test(priority=49)
	public void testSingleSignOnToCRM_74q() throws InterruptedException{
		//Enter Incorrect login credentials and verify user should not be able to login and salesforce should throw error
		crmLogoutFromHome();
		crmLoginpage.loginUser(TestConstants.CRM_INVALID_LOGIN_USERNAME, CRMPassword);  
		s_assert.assertTrue(crmLoginpage.getErrorMessageOnLoginPage().contains("Please check your username and password"),"Salesforce didn't throw the required message for Invalid login");
		//Enter correct login credentials and verify user should be able to login
		crmHomePage = crmLoginpage.loginUser(CRMUserName, CRMPassword);
		s_assert.assertTrue(crmHomePage.verifyHomePage(),"Home page does not come after login");
		s_assert.assertAll();
	}

	//QTest ID TC-145:Edit Spouse Contact details for Consultant
	@Test(priority=50)
	public void testEditSpouseContactDetailsForConsultant_145q() throws InterruptedException{
		List<Map<String, Object>> randomConsultantListToVerify =  null;
		String consultantEmailIDToVerifiy = null;
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String randomFirstName = CommonUtils.getRandomWord(4);
		String randomLastName = randomFirstName;
		String mainPhoneNumber = TestConstants.PHONE_NUMBER; 
		String firstName = TestConstants.FIRST_NAME+randomNum;
		String dob = null;
		String lastName = firstName;
		String combineFullName = firstName+" "+lastName;
		randomConsultantListToVerify = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_AUTOSHIPS_RFO,countryId),RFO_DB);
		consultantEmailIDToVerifiy = (String) getValueFromQueryResult(randomConsultantListToVerify, "UserName");

		logger.info("The another email address to verify is "+consultantEmailIDToVerifiy);

		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		crmAccountDetailsPage.clickAccountHeaderLinks("Contacts");
		if(crmAccountDetailsPage.verifyIsSpouseContactTypePresentNew(crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Contacts"))==false){
			crmAccountDetailsPage.clickNewContactButtonUnderContactSection();
			crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(firstName, lastName);
			//dob = crmAccountDetailsPage.enterBirthdateInCreatingNewContactForSpouse();
			//crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailId);
			crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(mainPhoneNumber);
			crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			crmAccountDetailsPage.closeFrameAfterSavingDetailsForNewContactSpouse(firstName);
			crmAccountDetailsPage.clickAccountMainMenuOptions("Contacts");
			crmAccountDetailsPage.clickOnEditUnderContactSection("Spouse");
			s_assert.assertFalse(crmAccountDetailsPage.verifyDataUnderContactSectionInContactDetailsPageIsEditable("Contact Type"), "Contact Type is Editable");
			crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(randomFirstName, randomLastName);
			crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Name").equals(randomFirstName+" "+randomLastName), "Name of the spouse not Reflected in SalesForce");
			crmAccountDetailsPage.clickEditButtonForNewContactSpouseInContactDetailsPage();
			//   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(consultantEmailIDToVerifiy);
			//   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			//   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("This email address already exists in our system, email must be unique."), "No Error Message Displayed");
			//   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailIDContainsSpecialCharacter);
			//   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			//   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("Invalid Email Address."), "Email Address with Special Character Saved.");
			//   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailId);
			//   crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse("", "");
			//   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			//   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("Phone number should be in (999) 999-9999 format"), "No Error Message Displayed for Mobile less than 9 digits");
			//   crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(firstName, lastName);
			crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
		}else{
			logger.info("Spouse is already present");
			crmAccountDetailsPage.clickOnEditUnderContactSection("Spouse");
			s_assert.assertFalse(crmAccountDetailsPage.verifyDataUnderContactSectionInContactDetailsPageIsEditable("Contact Type"), "Contact Type is Editable");
			crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(firstName, lastName);
			dob = crmAccountDetailsPage.enterBirthdateInCreatingNewContactForSpouse();
			//crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailId);
			crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(mainPhoneNumber);
			crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Name").equals(combineFullName), "Name of the spouse not Matched");
			s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Birthdate").equals(dob), "Birthdate of the spouse not Matched");
			s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Main Phone").replaceAll("\\D", "").equals(mainPhoneNumber), "Main Phone of the spouse not Matched");
			//s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Email Address").equals(emailId), "Email Address of the spouse not Matched");
			crmAccountDetailsPage.clickEditButtonForNewContactSpouseInContactDetailsPage();
			//   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(consultantEmailIDToVerifiy);
			//   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			//   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("This email address already exists in our system, email must be unique."), "No Error Message Displayed");
			//   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailIDContainsSpecialCharacter);
			//   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			//   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("Invalid Email Address."), "Email Address with Special Character Saved.");
			//   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailId);
			//   crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse("", "");
			//   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
			//   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("Phone number should be in (999) 999-9999 format"), "No Error Message Displayed for Mobile less than 9 digits");
			//   crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(firstName, lastName);
			crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
		}
		crmAccountDetailsPage.closeAllOpenedTabs();
		s_assert.assertAll();
	}

	/*//Hybris Project-4506:Edit Spouse Contact details for Consultant
	 @Test(priority=55)
	 public void testEditSpouseContactDetailsForConsultant_4506() throws InterruptedException{
	  List<Map<String, Object>> randomConsultantList =  null;
	  List<Map<String, Object>> randomConsultantListToVerify =  null;
	  String consultantEmailID = null;
	  String consultantEmailIDToVerifiy = null;
	  int randomNum = CommonUtils.getRandomNum(10000, 1000000);
	  String randomFirstName = CommonUtils.getRandomWord(4);
	  String randomLastName = randomFirstName;
	  String randomWrongPhoneNumber = String.valueOf(randomNum);
	  String mainPhoneNumber = TestConstants.PHONE_NUMBER; 
	  String firstName = TestConstants.FIRST_NAME+randomNum;
	  String dob = null;
	  String lastName = firstName;
	  String combineFullName = firstName+" "+lastName;
	  String emailId = firstName+"@gmail.com";
	  String emailIDContainsSpecialCharacter = "^&@#"+"@gmail.com";
	  randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_AUTOSHIPS_RFO,countryId),RFO_DB);
	  randomConsultantListToVerify = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_AUTOSHIPS_RFO,countryId),RFO_DB);
	  consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
	  consultantEmailIDToVerifiy = (String) getValueFromQueryResult(randomConsultantListToVerify, "UserName");
	  logger.info("The email address is "+consultantEmailID);
	  logger.info("The another email address to verify is "+consultantEmailIDToVerifiy); 
	  s_assert.assertTrue(crmHomePage.verifyHomePage(),"Home page does not come after login");
	  crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
	  crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
	  crmAccountDetailsPage.clickAccountMainMenuOptions("Contacts");
	  if(crmAccountDetailsPage.verifyIsSpouseContactTypePresentNew(crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Contacts"))==false){
	   crmAccountDetailsPage.clickNewContactButtonUnderContactSection();
	   crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(firstName, lastName);
	   //dob = crmAccountDetailsPage.enterBirthdateInCreatingNewContactForSpouse();
	   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailId);
	   crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(mainPhoneNumber);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   crmAccountDetailsPage.closeFrameAfterSavingDetailsForNewContactSpouse(firstName);
	   crmAccountDetailsPage.clickAccountMainMenuOptions("Contacts");
	   crmAccountDetailsPage.clickOnEditUnderContactSection("Spouse");
	   s_assert.assertFalse(crmAccountDetailsPage.verifyDataUnderContactSectionInContactDetailsPageIsEditable("Contact Type"), "Contact Type is Editable");
	   crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(randomFirstName, randomLastName);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Name").equals(randomFirstName+" "+randomLastName), "Name of the spouse not Reflected in SalesForce");
	   crmAccountDetailsPage.clickEditButtonForNewContactSpouseInContactDetailsPage();
	   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(consultantEmailIDToVerifiy);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("This email address already exists in our system, email must be unique."), "No Error Message Displayed");
	   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailIDContainsSpecialCharacter);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("Invalid Email Address."), "Email Address with Special Character Saved.");
	   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailId);
	   crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(randomWrongPhoneNumber);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("Phone number should be in (999) 999-9999 format"), "No Error Message Displayed for Mobile less than 9 digits");
	   crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(mainPhoneNumber);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	  }else{
	   logger.info("Spouse is already present");
	   crmAccountDetailsPage.clickOnEditUnderContactSection("Spouse");
	   s_assert.assertFalse(crmAccountDetailsPage.verifyDataUnderContactSectionInContactDetailsPageIsEditable("Contact Type"), "Contact Type is Editable");
	   crmAccountDetailsPage.enterFirstAndLastNameInCreatingNewContactForSpouse(firstName, lastName);
	   dob = crmAccountDetailsPage.enterBirthdateInCreatingNewContactForSpouse();
	   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailId);
	   crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(mainPhoneNumber);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Name").equals(combineFullName), "Name of the spouse not Matched");
	   s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Birthdate").equals(dob), "Birthdate of the spouse not Matched");
	   s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Main Phone").replaceAll("\\D", "").equals(mainPhoneNumber), "Main Phone of the spouse not Matched");
	   s_assert.assertTrue(crmAccountDetailsPage.verifyDataAfterSavingInNewContactForSpouse("Email Address").equals(emailId), "Email Address of the spouse not Matched");
	   crmAccountDetailsPage.clickEditButtonForNewContactSpouseInContactDetailsPage();
	   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(consultantEmailIDToVerifiy);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("This email address already exists in our system, email must be unique."), "No Error Message Displayed");
	   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailIDContainsSpecialCharacter);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("Invalid Email Address."), "Email Address with Special Character Saved.");
	   crmAccountDetailsPage.enterEmailIdInNewContactForSpouse(emailId);
	   crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(randomWrongPhoneNumber);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	   s_assert.assertTrue(crmAccountDetailsPage.isErrorMessageOnSavingExistingEmailIdOrWrongPhoneNumberPresent().contains("Phone number should be in (999) 999-9999 format"), "No Error Message Displayed for Mobile less than 9 digits");
	   crmAccountDetailsPage.enterMainPhoneInNewContactForSpouse(mainPhoneNumber);
	   crmAccountDetailsPage.clickSaveButtonForNewContactSpouse();
	  }
	  crmAccountDetailsPage.closeAllOpenedTabs();
		s_assert.assertAll();
	}*/



	//QTest ID TC-1197:View Account Status History for Consultant
	@Test(priority=51)
	public void testViewAccountStatusHistoryForConsultant_1197q() throws InterruptedException{
		String otherReason = TestConstants.OTHER_REASON;
		String changedMyMind = TestConstants.CHANGED_MY_MIND;
		getActiveEmailIdFromDB("Consultant");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		int accountStatusesHistoryCount = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		if(accountStatusesHistoryCount>0){
			crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label 1 is not Present");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label 1 is not Present");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By 1 Label is not Present");
		}
		/////////////////////////////Change Customer's Status from Active to InActive//////////////////////////

		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(otherReason);
		String dateOfAccountStatusChangedFromActiveToInactive = crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");

		s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is Active");
		int accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive == accountStatusesHistoryCount+1, "Account Statues History Actual is "+accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive + " & Account Statues History after changing statues Expected is "+ accountStatusesHistoryCount+1);
		crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");

		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label 2 is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label 2 is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By 2 Label is not Present");
		String accountStatusChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(0); // 0 : For Account Status
		String reasonChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(2);   // 2 : For Reason
		String lastModifiedChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(3);  // 3 : For Last Modified Value
		s_assert.assertTrue(accountStatusChangedFromActiveToInactive.equalsIgnoreCase("Soft Terminated Voluntary"), "Account Status 1 Not Matched Actual is "+accountStatusChangedFromActiveToInactive+" & Expected is Soft Terminated Voluntary");
		s_assert.assertTrue(reasonChangedFromActiveToInactive.equalsIgnoreCase(otherReason), "Reason Not 1 Matched Actual is "+reasonChangedFromActiveToInactive +" & Expected is "+otherReason);
		s_assert.assertTrue(lastModifiedChangedFromActiveToInactive.contains(dateOfAccountStatusChangedFromActiveToInactive), "Last Modified By 1 Date Not Matched Actual is "+lastModifiedChangedFromActiveToInactive+" & Expected is "+dateOfAccountStatusChangedFromActiveToInactive);
		/////////////////////////////Change Customer's Status from InActive to Active//////////////////////////

		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(changedMyMind);
		String dateOfAccountStatusChangedChangedFromInactiveToActive = crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");

		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		int accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive == accountStatusesHistoryCount+2, "Account Statues History Actual is "+ accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive + " & Account Statues History after changing statuses Expected is "+ accountStatusesHistoryCount+2);

		crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By Label is not Present");
		String account_StatusChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(0); // 0 : For Account Status
		String reasonChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(2);   // 2 : For Reason
		String last_ModifiedChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(3); // 3 : For Last Modified Value
		s_assert.assertTrue(account_StatusChangedFromInactiveToActive.equalsIgnoreCase("Active"), "Account Status 2 Not Matched Actual is "+account_StatusChangedFromInactiveToActive +" & Expected is Active");
		s_assert.assertTrue(reasonChangedFromInactiveToActive.equalsIgnoreCase(changedMyMind), "Reason Not 2 Matched Actual is "+reasonChangedFromInactiveToActive +"<<=& Expected is "+changedMyMind);
		s_assert.assertTrue(last_ModifiedChangedFromInactiveToActive.contains(dateOfAccountStatusChangedChangedFromInactiveToActive), "Last Modified By 2 Date Not Matched Actual is "+last_ModifiedChangedFromInactiveToActive + " & Expected is "+dateOfAccountStatusChangedChangedFromInactiveToActive);
		s_assert.assertAll();
	}

	//QTest ID TC-1195:Change Account status for Retail customer from Inactive to Active
	@Test(priority=52)
	public void testChangeAccountStatusForRetailCustomerFromInactiveToActive_1195q() throws InterruptedException{
		String otherReason = TestConstants.OTHER_REASON;
		String changedMyMind = TestConstants.CHANGED_MY_MIND;
		getActiveEmailIdHavingContactsFromDB("Retail Customer");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");

		/////////////////////////////Change Customer's Status from Active to InActive//////////////////////////

		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(otherReason);
		crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");

		s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is Active");

		/////////////////////////////Change Customer's Status from InActive to Active//////////////////////////

		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(changedMyMind);
		crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		crmAccountDetailsPage.clickAccountDetailsButton("My Account");
		storeFrontHomePage.switchToChildWindow();
		String consultantMyAccountPage = driver.getCurrentUrl();
		s_assert.assertTrue(consultantMyAccountPage.contains("corp"), "Not Logged in consultant's account page");
		crmHomePage.switchToPreviousTab();
		s_assert.assertAll();
	}

	//QTest ID TC-93 Change default RC Shipping address
	@Test(priority=53)
	public void testChangeDefaultRCShippingAddress_93q() throws InterruptedException{
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String shippingProfileFirstName = TestConstants.FIRST_NAME+randomNum;
		String lastName = TestConstants.LAST_NAME;
		String profileName = shippingProfileFirstName+" "+lastName;
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		crmAccountDetailsPage.clickShippingProfiles();
		int noOfShippingProfileInteger = Integer.parseInt(crmAccountDetailsPage.getShippingProfilesCount());
		if(noOfShippingProfileInteger==1){
			crmAccountDetailsPage.clickAddNewShippingProfileBtn();
			crmAccountDetailsPage.updateShippingProfileName(profileName);
			crmAccountDetailsPage.enterShippingAddress(addressLine, city, province, postal, phoneNumber,countryName);
			crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
			crmAccountDetailsPage.clickUserEnteredAddressRadioBtn();
			crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
			crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
			crmAccountDetailsPage.closeAllOpenedTabs();
			crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
			crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		}
		String profileNameBeforeEdit =  crmAccountDetailsPage.clickEditOfNonDefaultShippingProfile();
		crmAccountDetailsPage.clickCheckBoxForDefaultShippingProfileIfCheckBoxNotSelected();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.clickUserEnteredAddressRadioBtn();
		crmAccountDetailsPage.clickSaveBtnAfterEditShippingAddress();
		crmAccountDetailsPage.closeSubTabOfEditShippingProfile();
		crmAccountDetailsPage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		String profileNameAfterEdit = crmAccountDetailsPage.getDefaultSelectedShippingAddressName();
		s_assert.assertTrue(profileNameBeforeEdit.equalsIgnoreCase(profileNameAfterEdit), "Expected profile name "+profileNameBeforeEdit+"Actual on UI "+profileNameAfterEdit);
		s_assert.assertAll();
	}

	//QTest ID TC-55:Verify the Proxy to Pulse for a Consultant
	@Test(priority=54)
	public void testVerifyProxyToPulseForConsultant_55q() throws InterruptedException{
		if(driver.getCountry().equalsIgnoreCase("CA")) {
			crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
			crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
			crmAccountDetailsPage.clickAccountDetailsButton("Pulse");
			crmAccountDetailsPage.switchToChildWindow();
			s_assert.assertTrue(crmAccountDetailsPage.isSVSectionPresentOnPulsePage(), "SV Section is not present on Pulse Page");  
			s_assert.assertAll();
		}else {
			logger.info("CA specific TC, Old Pulse not available for AU user's");
		}
	}

	//QTest ID TC-52:Verify the Proxy to my account for a Consultant
	@Test(priority=55)
	public void testVerifyProxyToMyAccountForConsultant_52q() throws InterruptedException{ 
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		String accountName = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Account Name").trim().toLowerCase();
		String emailId = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Email Address").trim().toLowerCase();
		//String mainPhoneNo = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Main Phone");
		String addressLine1 = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Address Line 1").trim().toLowerCase();
		String locale = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Locale").trim().toLowerCase();
		crmAccountDetailsPage.clickAccountDetailsButton("My Account");
		storeFrontHomePage.switchToChildWindow();
		s_assert.assertTrue(storeFrontHomePage.isUserNamePresentOnDropDown(), "Consultant Account Page Not Verified");
		s_assert.assertTrue(accountName.contains(storeFrontHomePage.getConsultantStoreFrontInfo("first-name")), "First Name Not Matched, Expected is "+ accountName +"But Actual Contain is " +storeFrontHomePage.getConsultantStoreFrontInfo("first-name"));
		s_assert.assertTrue(addressLine1.contains(storeFrontHomePage.getConsultantStoreFrontInfo("address-1")), "Address Line Not Matched, Expected is "+ addressLine1 +"But Actual is " +storeFrontHomePage.getConsultantStoreFrontInfo("address-1"));
		s_assert.assertTrue(locale.contains(storeFrontHomePage.getConsultantStoreFrontInfo("city")), "City Not Matched, Expected is "+ locale +"But Actual is " +storeFrontHomePage.getConsultantStoreFrontInfo("city"));
		//s_assert.assertTrue(mainPhoneNo.equals(storeFrontHomePage.getConsultantStoreFrontInfo("phonenumber")), "Phone Number Not Matched, Expected is "+ mainPhoneNo +"But Actual is " +storeFrontHomePage.getConsultantStoreFrontInfo("phonenumber"));
		s_assert.assertTrue(emailId.toLowerCase().contains(storeFrontHomePage.getConsultantStoreFrontInfo("email-account")), "Email ID Not Matched, Expected is "+ emailId +"But Actual is " +storeFrontHomePage.getConsultantStoreFrontInfo("email-account"));
		storeFrontHomePage.switchToPreviousTab();
		s_assert.assertAll();
	}

	//QTest ID TC-54:Verify the Proxy to my account for a Retail Customer
	@Test(priority=56)
	public void testVerifyTheProxyToMyAccountForRetailCustomer_54q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
		String accountName = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Account Name").trim().toLowerCase();
		String emailId = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Email Address").trim().toLowerCase();
		//String mainPhoneNo = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Main Phone").trim();
		String addressLine1 = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Address Line 1").trim().toLowerCase();
		String locale = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Locale").trim().toLowerCase();
		crmAccountDetailsPage.clickAccountDetailsButton("My Account");
		storeFrontHomePage.switchToChildWindow();
		s_assert.assertTrue(storeFrontHomePage.isUserNamePresentOnDropDown(), "Consultant Account Page Not Verified");
		s_assert.assertTrue(accountName.toLowerCase().contains(storeFrontHomePage.getConsultantStoreFrontInfo("first-name")), "First Name Not Matched, Expected is "+ accountName +"But Actual Contain is " +storeFrontHomePage.getConsultantStoreFrontInfo("first-name"));
		s_assert.assertTrue(addressLine1.toLowerCase().contains(storeFrontHomePage.getConsultantStoreFrontInfo("address-1"))||storeFrontHomePage.getConsultantStoreFrontInfo("address-1").contains(addressLine1), "Address Line Not Matched, Expected is "+ addressLine1 +"But Actual is " +storeFrontHomePage.getConsultantStoreFrontInfo("address-1"));
		s_assert.assertTrue(locale.equals(storeFrontHomePage.getConsultantStoreFrontInfo("city")), "City Not Matched, Expected is "+ locale +"But Actual is " +storeFrontHomePage.getConsultantStoreFrontInfo("city"));
		/*String phoneNumberFromUI = storeFrontHomePage.convertPhoneNumber(storeFrontHomePage.getConsultantStoreFrontInfo("phonenumber"));
		  s_assert.assertTrue(mainPhoneNo.equals(phoneNumberFromUI), "Phone Number Not Matched, Expected is "+ mainPhoneNo +"But Actual is " +phoneNumberFromUI);*/
		s_assert.assertTrue(emailId.toLowerCase().equals(storeFrontHomePage.getConsultantStoreFrontInfo("email-account")), "Email ID Not Matched, Expected is "+ emailId.toLowerCase() +"But Actual is " +storeFrontHomePage.getConsultantStoreFrontInfo("email-account").toLowerCase());
		crmAccountDetailsPage.switchToPreviousTab();
		s_assert.assertAll();
	}

	// QTest ID TC-58:View and Edit PWS Domain for a Consultant
	@Test(enabled=false)
	public void testViewAndEditPWSDomainForConsultant_58q() throws InterruptedException{
		String consultantEmailID =null;
		List<Map<String, Object>> randomConsultantSitePrefix =  null;
		String consultantConsumedSitePrefix = null;
		String specialCharacter = "%%$#";
		int randomNum = CommonUtils.getRandomNum(10000, 1000000);
		String randomString = CommonUtils.getRandomWord(4);
		String randomSitePrefixName = randomString+randomNum;
		String randomSitePrefixNameWithSpecialCharacter = randomString+randomNum+specialCharacter;
		for(int i=0; i<=4; i++){
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguementPWS(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_PWS_RFO,driver.getEnvironment()+".com",driver.getCountry(),countryId),RFO_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
			String PWS = (String) getValueFromQueryResult(randomConsultantList, "URL");
			driver.get(PWS);
			if(driver.getCurrentUrl().contains("sitenotfound")){
				continue;
			}else{
				break;
			}
		}
		driver.get(driver.getURL());
		randomConsultantSitePrefix = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_SITE_PREFIX_RFO,countryId),RFO_DB);
		consultantConsumedSitePrefix = (String) getValueFromQueryResult(randomConsultantSitePrefix, "SitePrefix");
		logger.info("The email address is "+consultantEmailID);
		logger.info("Already used consultant site prefix is "+consultantConsumedSitePrefix);

		crmHomePage = crmLoginpage.loginUser(CRMUserName, CRMPassword);
		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
		crmAccountDetailsPage.clickAccountDetailsButton("PWS Domain");
		String siteUrlBeforeEdit = crmAccountDetailsPage.getOldSitePrefixWithCompleteSiteBeforeEdit();
		crmAccountDetailsPage.enterRandomSitePrefixName(randomSitePrefixName);
		crmAccountDetailsPage.clickCheckAvailabilityButton();
		s_assert.assertTrue(crmAccountDetailsPage.getCheckAvailabilityMessage().trim().contains(randomSitePrefixName.trim()+" is available"), "Random site prefix is not available");
		crmAccountDetailsPage.clickPWSSaveButton();
		crmAccountDetailsPage.clickAccountDetailsButton("PWS Domain");
		String siteUrlAfterEdit = crmAccountDetailsPage.getNewSitePrefixWithCompleteSiteAfterEdit();
		String[] afterEditPWSUrl = siteUrlAfterEdit.split("-");
		String afterEditPWSPrefix = afterEditPWSUrl[0];
		String afterEditPWSSuffix = afterEditPWSUrl[1];
		crmAccountDetailsPage.enterRandomSitePrefixName(randomSitePrefixNameWithSpecialCharacter);
		crmAccountDetailsPage.clickCheckAvailabilityButton();
		s_assert.assertEquals(crmAccountDetailsPage.getCheckAvailabilityMessage(),"Invalid characters entered. PWS Prefix can only contain letters and numbers. Please try again.");
		crmAccountDetailsPage.enterRandomSitePrefixName(consultantConsumedSitePrefix);
		crmAccountDetailsPage.clickCheckAvailabilityButton();
		s_assert.assertEquals(crmAccountDetailsPage.getCheckAvailabilityMessage(),"Sorry "+consultantConsumedSitePrefix+" is not available, please try another one.");
		storeFrontHomePage.openConsultantPWS(afterEditPWSPrefix+afterEditPWSSuffix);
		s_assert.assertTrue(driver.getCurrentUrl().contains(afterEditPWSPrefix), "New PWS Site Url is not active");
		storeFrontHomePage.openConsultantPWS(siteUrlBeforeEdit);
		s_assert.assertFalse(driver.getCurrentUrl().contains(siteUrlBeforeEdit), "Old PWS Site Url is active");
		driver.get(driver.getURL());
		crmHomePage = crmLoginpage.loginUser(CRMUserName, CRMPassword);
		s_assert.assertAll();
	}

	//QTest ID TC-53:Verify the Proxy to my account for a Preferred Customer
	@Test(priority=57)
	public void testVerifyProxyToMyAccountForPC_53q() throws InterruptedException{
		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		String accountName = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Account Name").trim().toLowerCase();
		String emailId = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Email Address").trim().toLowerCase();
		String addressLine1 = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Address Line 1").trim().toLowerCase();
		String locale = crmAccountDetailsPage.getInfoUnderAccountDetailSection("Locale").trim().toLowerCase();
		crmAccountDetailsPage.clickAccountDetailsButton("My Account");
		storeFrontHomePage.switchToChildWindow();
		s_assert.assertTrue(storeFrontHomePage.isUserNamePresentOnDropDown(), "PC Account Page Not Verified");
		s_assert.assertTrue(accountName.contains(storeFrontHomePage.getpreferredCustomerStoreFrontInfo("first-name")), "First Name Not Matched, Expected is "+ accountName +"But Actual Contain is " +storeFrontHomePage.getpreferredCustomerStoreFrontInfo("first-name"));
		s_assert.assertTrue(addressLine1.equals(storeFrontHomePage.getpreferredCustomerStoreFrontInfo("address-1")), "Address Line Not Matched, Expected is "+ addressLine1 +"But Actual is " +storeFrontHomePage.getpreferredCustomerStoreFrontInfo("address-1"));
		s_assert.assertTrue(locale.equals(storeFrontHomePage.getpreferredCustomerStoreFrontInfo("city")), "City Not Matched, Expected is "+ locale +"But Actual is " +storeFrontHomePage.getpreferredCustomerStoreFrontInfo("city"));
		s_assert.assertTrue(emailId.equals(storeFrontHomePage.getpreferredCustomerStoreFrontInfo("email-account")), "Email ID Not Matched, Expected is "+ emailId.toLowerCase() +"But Actual is " +storeFrontHomePage.getpreferredCustomerStoreFrontInfo("email-account").toLowerCase());
		storeFrontHomePage.switchToPreviousTab();
		driver.pauseExecutionFor(3000);
		s_assert.assertAll();
	}

	//QTest ID TC-1198:View Account Status History for PC
	@Test(priority=58)
	public void testViewAccountStatusHistoryForPC_1198q() throws InterruptedException{
		String otherReason = TestConstants.OTHER_REASON;
		String changedMyMind = TestConstants.CHANGED_MY_MIND;
		getActiveEmailIdFromDB("Preferred Customer");
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		int accountStatusesHistoryCount = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		if(accountStatusesHistoryCount>0){
			crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label 1 is not Present");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label 1 is not Present");
			s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By 1 Label is not Present");
		}
		/////////////////////////////Change Customer's Status from Active to InActive//////////////////////////
		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(otherReason);
		String dateOfAccountStatusChangedFromActiveToInactive = crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		s_assert.assertFalse(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is Active");
		int accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive == accountStatusesHistoryCount+1, "Account Statues History Actual is "+accountStatusesHistoryCountAfterAccountStatusChangedFromActiveToInactive + " & Account Statues History after changing statues Expected is "+ accountStatusesHistoryCount+1);
		crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label 2 is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label 2 is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By 2 Label is not Present");
		String accountStatusChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(0); // 0 : For Account Status
		String reasonChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(2);   // 2 : For Reason
		String lastModifiedChangedFromActiveToInactive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(3);  // 3 : For Last Modified Value
		s_assert.assertTrue(accountStatusChangedFromActiveToInactive.equalsIgnoreCase("Soft Terminated Voluntary"), "Account Status 1 Not Matched Actual is "+accountStatusChangedFromActiveToInactive+" & Expected is Soft Terminated Voluntary");
		s_assert.assertTrue(reasonChangedFromActiveToInactive.equalsIgnoreCase(otherReason), "Reason Not 1 Matched Actual is "+reasonChangedFromActiveToInactive +" & Expected is "+otherReason);
		s_assert.assertTrue(lastModifiedChangedFromActiveToInactive.contains(dateOfAccountStatusChangedFromActiveToInactive), "Last Modified By 1 Date Not Matched Actual is "+lastModifiedChangedFromActiveToInactive+" & Expected is "+dateOfAccountStatusChangedFromActiveToInactive);
		/////////////////////////////Change Customer's Status from InActive to Active//////////////////////////
		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
		crmAccountDetailsPage.clickAccountDetailsButton("Change Account Status");
		crmAccountDetailsPage.selectReasonToChangeAccountStatusFromDropDown(changedMyMind);
		String dateOfAccountStatusChangedChangedFromInactiveToActive = crmAccountDetailsPage.clickSaveButtonToChangeAccountStatus();
		crmAccountDetailsPage.closeTabViaNumberWise(2);
		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
		s_assert.assertTrue(crmAccountDetailsPage.isAccountStatusActive(), "Account Status is not Active");
		int accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive = crmAccountDetailsPage.getCountOfAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive == accountStatusesHistoryCount+2, "Account Statues History Actual is "+ accountStatusesHistoryCountAfterAccountStatusChangedFromInactiveToActive + " & Account Statues History after changing statuses Expected is "+ accountStatusesHistoryCount+2);
		crmAccountDetailsPage.clickAccountMainMenuOptions("Account Statuses History");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Account Status"), "Account Status Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Reason"), "Reason Label is not Present");
		s_assert.assertTrue(crmAccountDetailsPage.isLabelOfAccountMainMenuOptionsPresent("Account Statuses History", "Last Modified By"), "Last Modified By Label is not Present");
		String account_StatusChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(0); // 0 : For Account Status
		String reasonChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(2);   // 2 : For Reason
		String last_ModifiedChangedFromInactiveToActive = crmAccountDetailsPage.getValuesOfLabelInAccountStatusesHistory(3); // 3 : For Last Modified Value
		s_assert.assertTrue(account_StatusChangedFromInactiveToActive.equalsIgnoreCase("Active"), "Account Status 2 Not Matched Actual is "+account_StatusChangedFromInactiveToActive +" & Expected is Active");
		s_assert.assertTrue(reasonChangedFromInactiveToActive.equalsIgnoreCase(changedMyMind), "Reason Not 2 Matched Actual is "+reasonChangedFromInactiveToActive +"<<=& Expected is "+changedMyMind);
		s_assert.assertTrue(last_ModifiedChangedFromInactiveToActive.contains(dateOfAccountStatusChangedChangedFromInactiveToActive), "Last Modified By 2 Date Not Matched Actual is "+last_ModifiedChangedFromInactiveToActive + " & Expected is "+dateOfAccountStatusChangedChangedFromInactiveToActive);
		s_assert.assertAll();
	}

	//	//Hybris Project-5004:Verify the forgot password for Consultant from Salesforce
	//	@Test(priority=78)
	//	public void testForgotPasswordForConsultant_5004() throws InterruptedException{
	////		crmHomePage.enterTextInSearchFieldAndHitEnter(consultantEmailID);
	//		consultantEmailID = getActiveEmailIdFromDB("Consultant");
	//		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Consultant");
	//		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
	//		//click 'send forgot password email' link
	//		crmAccountDetailsPage.clickSendForgotPasswordEmailLink();
	//		crmAccountDetailsPage.acceptAlert();
	//		s_assert.assertTrue(crmAccountDetailsPage.isEmailForNewPasswordSent(),"Email for new password has not been sent");
	//		s_assert.assertAll();
	//	}
	//
	//	//Hybris Project-5005:Verify the forgot password for Preferred Customer from Salesforce
	//	@Test(priority=79)
	//	public void testForgotPasswordForPreferredCustomer_5005() throws InterruptedException{
	////		crmHomePage.enterTextInSearchFieldAndHitEnter(pcEmailID);
	//		pcEmailID =  getActiveEmailIdFromDB("Preferred Customer");
	//		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Preferred Customer");
	//		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
	//		crmAccountDetailsPage.clickSendForgotPasswordEmailLink();
	//		crmAccountDetailsPage.acceptAlert();
	//		s_assert.assertTrue(crmAccountDetailsPage.isEmailForNewPasswordSent(),"Email for new password has not been sent");
	//		s_assert.assertAll();
	//	}

	//Hybris Project-4528:Search for account by Main phone number
	@Test(priority=59)
	public void testSearchForAccountByMainPhoneNumber_4528() throws InterruptedException{
		String consultantEmailID = null;
		List<Map<String, Object>> randomConsultantUserList =  null;
		String mainPhoneNumberDB = null;
		List<Map<String, Object>> mainPhoneNumberList =  null;
		for(int i=1;i<=5;i++){
			randomConsultantUserList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId),RFO_DB);
			consultantEmailID = String.valueOf(getValueFromQueryResult(randomConsultantUserList, "Username"));
			//get main phone number
			mainPhoneNumberList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_ACCOUNT_PHONE_NUMBER_QUERY_RFO, consultantEmailID, consultantEmailID), RFO_DB);
			mainPhoneNumberDB = String.valueOf(getValueFromQueryResult(mainPhoneNumberList, "PhoneNumberRaw"));
			if(mainPhoneNumberDB!="null")
				break;
		}

		//search for account by Main Phone no.
		crmHomePage.enterTextInSearchFieldAndHitEnter(mainPhoneNumberDB);
		s_assert.assertTrue(crmHomePage.isAccountLinkPresentInLeftNaviagation(), "Accounts link is not present on left navigation panel");
		s_assert.assertTrue(crmHomePage.isContactsLinkPresentInLeftNaviagation(), "Contacts link is not present on left navigation panel");
		String email = crmHomePage.getEmailOnFirstRowInSearchResultsOfMainPhoneNumber();
		String accountName = crmHomePage.getAccountNameOnFirstRowInSearchResultsOfMainPhoneNumber();
		String name = crmHomePage.getNameOnFirstRowInSearchResultsOfMainPhoneNumber();
		crmHomePage.clickContactOnFirstRowInSearchResults();
		s_assert.assertTrue(crmContactDetailsPage.getName().contains(name), "Expected name on contact details page: "+name+" But actual on UI is: "+crmContactDetailsPage.getName());
		s_assert.assertTrue(crmContactDetailsPage.getEmailID().contains(email), "Expected name on contact details page: "+email+" But actual on UI is: "+crmContactDetailsPage.getEmailID());
		s_assert.assertTrue(crmContactDetailsPage.getAccountName().contains(accountName), "Expected name on contact details page: "+accountName+" But actual on UI is: "+crmContactDetailsPage.getAccountName());
		s_assert.assertTrue(crmAccountDetailsPage.isMainPhoneFieldDisplayedAndNonEmpty(),"Main Phone is not displayed on account details page");
		s_assert.assertAll();
	}

	//	//Hybris Project-5006:Verify the forgot password for Retail Customer from Salesforce
	//	@Test
	//	public void testForgotPasswordForRetailCustomer_5006() throws InterruptedException{
	//		//crmHomePage.enterTextInSearchFieldAndHitEnter(rcEmailID);
	//		rcEmailID = getActiveEmailIdFromDB("Retail Customer");
	//		crmHomePage.clickAnyTypeOfActiveCustomerInSearchResult("Retail Customer");
	//		s_assert.assertTrue(crmAccountDetailsPage.isAccountDetailsPagePresent(),"Account Details page has not displayed");
	//		crmAccountDetailsPage.clickSendForgotPasswordEmailLinkRC();
	//		crmAccountDetailsPage.acceptAlert();
	//		s_assert.assertTrue(crmAccountDetailsPage.isEmailForNewPasswordSentForRC(),"Email for new password has not been sent");
	//		s_assert.assertAll();
	//	}

}