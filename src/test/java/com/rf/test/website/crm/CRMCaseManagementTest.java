package com.rf.test.website.crm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.rf.test.website.RFCRMWebsiteBaseTest;

public class CRMCaseManagementTest extends RFCRMWebsiteBaseTest{
	private static final Logger logger = LogManager
			.getLogger(CRMCaseManagementTest.class.getName());

	@AfterMethod
	public void afterCRMCaseManagementTest(){
		crmHomePage.closeAllOpenedTabs();
		crmLogout();
	}

	// QTest ID TC-1235 Verify WhiteList Queue - open case status
	@Test
	public void testVerifyWhiteListQueueOpenCaseStatus_1235q() throws InterruptedException{
		String caseValue = "Cases";
		String typeValue = "Suspicious Activity";
		String subypeValue = "Whitelist Request";
		String caseOwnerName = "SSCoordinator";
		String caseOwnerNameFromUI = null;
		String caseNumber = null;
		String statusType = "Resolved";
		String statusFromUI = null;
		loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.clickNewCaseButton();
		crmHomePage.fillTheDetailsAndClickSaveForNewCase(typeValue, subypeValue);
		crmHomePage.clickDetailsViewLink();
		caseNumber = crmHomePage.getCaseNumberFromTab();
		crmHomePage.clickFeedViewLink();
		crmHomePage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(caseNumber);
		crmHomePage.clickCaseNumberAfterSearch(caseNumber);
		caseOwnerNameFromUI = crmHomePage.getCaseOwnerName();
		s_assert.assertTrue(caseOwnerNameFromUI.contains(caseOwnerName), "Expected case owner name is "+caseOwnerName+" but actual on UI is "+caseOwnerNameFromUI);
		crmHomePage.changeStatusTypeAndUpdate(statusType);
		statusFromUI = crmHomePage.getStatusOfCase();
		s_assert.assertTrue(statusFromUI.contains(statusType), "Expected case status is "+statusType+" but actual on UI is "+statusFromUI);
		s_assert.assertAll();
	}

	// QTest ID TC-1244 Verify  resolve case owner
	@Test
	public void testVerifyResolveCaseOwner_1244q() throws InterruptedException{
		String caseValue = "Cases";
		String typeValue = "Suspicious Activity";
		String caseOwnerName = "Automation_ SSCoordinator";
		String caseOwnerNameFromUI = null;
		String statusType = "Resolved";
		String statusFromUI = null;
		loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.clickNewCaseButton();
		crmHomePage.fillTheDetailsAndClickSaveForNewCase(typeValue, "2","2",statusType);
		caseOwnerNameFromUI = crmHomePage.getCaseOwnerName();
		s_assert.assertTrue(caseOwnerNameFromUI.contains(caseOwnerName), "Expected case owner name is "+caseOwnerName+" but actual on UI is "+caseOwnerNameFromUI);
		statusFromUI = crmHomePage.getStatusOfCase();
		s_assert.assertTrue(statusFromUI.contains(statusType), "Expected case status is "+statusType+" but actual on UI is "+statusFromUI);
		crmHomePage.closeAllOpenedTabs();
		crmHomePage.clickNewCaseButton();
		typeValue = "Spam";
		caseOwnerName = "Spam Queue";
		crmHomePage.selectTypeForNewCaseByValue(typeValue);
		crmHomePage.selectStatusForNewCaseByValue(statusType);
		crmHomePage.clickSaveBtnForNewCase();
		caseOwnerNameFromUI = crmHomePage.getCaseOwnerName();
		s_assert.assertTrue(caseOwnerNameFromUI.contains(caseOwnerName), "Expected case owner name is "+caseOwnerName+" but actual on UI for spam type is "+caseOwnerNameFromUI);
		statusFromUI = crmHomePage.getStatusOfCase();
		s_assert.assertTrue(statusFromUI.contains(statusType), "Expected case status is "+statusType+" but actual on UI for spam type is "+statusFromUI);
		s_assert.assertAll();
	}

	// QTest ID TC-1226 Verify Compliance Queue
	@Test
	public void testVerifyComplianceQueue_1226q() throws InterruptedException{
		String caseValue = "Cases";
		String typeValue = "Compliance";
		String subType = "Account Maintenance";
		String typeDetail = "Death and Incapacity";
		String expectedCaseText="successfully";
		String caseTextFromUI = null;
		String subDDValue = "Compliance Queue - Unresolved VIEW";
		String statusType = "Open";
		String caseOwnerName = "Automation_ Compliance";
		String caseNumber = null;
		String newStatusType = "Resolved";
		String statusFromUI = null;
		String caseOwnerNameFromUI = null;
		//Login as sales support coordinator.
		loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.clickNewCaseButton();
		crmHomePage.fillTheDetailsAndClickSaveForNewCase(typeValue,subType,typeDetail);
		caseTextFromUI=crmHomePage.getCaseSavedText();
		s_assert.assertTrue(caseTextFromUI.contains(expectedCaseText),"Expected case text is "+expectedCaseText+" but actual on UI is "+caseTextFromUI);
		crmHomePage.closeAllOpenedTabs();
		crmLogout();
		//Login as compliance user.
		loginUser(CRMComplianceUserName, CRMCompliancePassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.selectValueFromSubDD(subDDValue);
		//Change case owner status
		caseNumber=crmHomePage.changeCaseOwner(statusType, "1", caseOwnerName);
		crmHomePage.enterTextInSearchFieldAndHitEnter(caseNumber);
		crmHomePage.clickCaseNumberAfterSearch(caseNumber);
		//Change status type.
		crmHomePage.clickFeedViewLinkAfterSearch();
		crmHomePage.changeStatusTypeAndUpdate(newStatusType);
		//Assert case owner name.
		caseOwnerNameFromUI = crmHomePage.getCaseOwnerName();
		s_assert.assertTrue(caseOwnerNameFromUI.contains(caseOwnerName), "Expected case owner name is "+caseOwnerName+" but actual on UI is "+caseOwnerNameFromUI);
		//Assert case owner status
		statusFromUI = crmHomePage.getStatusOfCase();
		s_assert.assertTrue(statusFromUI.contains(newStatusType), "Expected case status is "+newStatusType+" but actual on UI is "+statusFromUI);
		s_assert.assertAll();
	}

	// QTest ID TC-1229 Verify RFConnectionTier2  Queue
	@Test(enabled=true)//reason mention in comment in code)
	public void testRFConnectionTier2Queue_1229(){
		String caseValue = "Cases";
		String typeValue = "RF Connection";
		String subDDValue = "RFConnection Tier2";
		String statusType = "Open";
		String caseOwnerName = "RFConnection";
		String caseNumber = null;
		String newStatusType = "Resolved";
		String statusFromUI = null;
		String caseOwnerNameFromUI = null;
		//Login as sales support coordinator.
		loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.clickNewCaseButton();
		crmHomePage.fillTheDetailsAndClickSaveForNewCase(typeValue,"2","2");
		crmHomePage.clickDetailsViewLink();
		caseNumber = crmHomePage.getCaseNumberFromTab();
		crmHomePage.clickFeedViewLink();
		crmHomePage.closeAllOpenedTabs();
		crmLogout();
		//Login as sales support manager user.
		loginUser(CRMSalesSupportManagerUserName, CRMSalesSupportManagerPassword);
		crmHomePage.enterTextInSearchFieldAndHitEnter(caseNumber);
		s_assert.assertTrue(crmHomePage.isCreatedCasePresentInSearchResult(caseNumber),"Created case number "+caseNumber+" is not present in search result");
		crmLogout();
		//Login as RF connection user.
		loginUser(CRMRFConnectionUserName, CRMRFConnectionPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.selectValueFromSubDD(subDDValue);//Value not present in sub-dropdown.
		//Change case owner status
		caseNumber=crmHomePage.changeCaseOwner(statusType, "1",caseOwnerName);
		crmHomePage.enterTextInSearchFieldAndHitEnter(caseNumber);
		crmHomePage.clickCaseNumberAfterSearch(caseNumber);
		//Change status type.
		crmHomePage.clickFeedViewLinkAfterSearch();
		crmHomePage.changeStatusTypeAndUpdate(newStatusType);
		//Assert case owner name.
		caseOwnerNameFromUI = crmHomePage.getCaseOwnerName();
		s_assert.assertTrue(caseOwnerNameFromUI.contains(caseOwnerName), "Expected case owner name is "+caseOwnerName+" but actual on UI is "+caseOwnerNameFromUI);
		//Assert case owner status.
		statusFromUI = crmHomePage.getStatusOfCase();
		s_assert.assertTrue(statusFromUI.contains(newStatusType), "Expected case status is "+newStatusType+" but actual on UI is "+statusFromUI);
		s_assert.assertAll();
	}

	// QTest ID TC-1232 Verify OrderManagement Queue - pending case status
	@Test
	public void testVerifyOrderManagementQueue_1232q(){
		String caseValue = "Cases";
		String orderManagmentQueueValue = "Order Management Queue";
		String caseOwnerName = "Automation_ SSCoordinator";
		String caseOwnerNameFromUI = null;
		String caseNumber = null;
		String statusType = "Open";
		String statusTypeResolve = "Resolved";
		String statusFromUI = null;
		String caseType = null;
		String caseTypeQA = "QA/QC";
		String label_Type = "Type";
		loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.selectValueFromSubDD(orderManagmentQueueValue);
		crmHomePage.setNumberOfRecordsPerPageFromDD("200");
		caseNumber = crmHomePage.clickAndReturnCaseNumberInList(statusType);
		crmHomePage.clickDetailsViewLink();
		caseType = crmHomePage.getCaseType();
		if(caseType.contains(caseTypeQA)){
			crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
		}
		crmHomePage.changeCaseOwnerFromDetailsView(caseOwnerName);
		crmHomePage.clickFeedViewLink();
		crmHomePage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(caseNumber);
		crmHomePage.clickCaseNumberAfterSearch(caseNumber);
		caseOwnerNameFromUI = crmHomePage.getCaseOwnerName();
		s_assert.assertTrue(caseOwnerNameFromUI.contains(caseOwnerName), "Expected case owner name is "+caseOwnerName+" but actual on UI is "+caseOwnerNameFromUI);
		crmHomePage.changeStatusTypeAndUpdate(statusTypeResolve);
		statusFromUI = crmHomePage.getStatusOfCase();
		s_assert.assertTrue(statusFromUI.contains(statusTypeResolve), "Expected case status is "+statusType+" but actual on UI is "+statusFromUI);
		s_assert.assertAll();
	}

	// QTest ID TC-1242 Verify Escalate case functionality
	@Test
	public void testVerifyEscalateCaseFunctionality_1242q(){
		String caseValue = "Cases";
		String statusType = "Resolved";
		String statusTypeOpen = "Open";
		String statusFromUI = null;
		String caseNumber = null;
		String alertMessage = null;
		String caseType = null;
		String caseTypeQA = "QA/QC";
		String label_Type = "Type";
		String caseOwnerName = "Automation_ SSCoordinator";
		String salesSupportMediumQueueValue = "Sales Support Medium";
		String typeValue = "Exceptions";
		String subypeValue ="Bonus Payment Request";
		loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.selectValueFromSubDD(salesSupportMediumQueueValue);
		//Change case owner from details view
		caseNumber = crmHomePage.clickAndReturnCaseNumber(statusTypeOpen);
		crmHomePage.clickDetailsViewLink();
		caseType = crmHomePage.getCaseType();
		if(caseType.contains(caseTypeQA)){
			crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
		}
		crmHomePage.changeCaseOwnerFromDetailsView(caseOwnerName);
		alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		if(alertMessage.contains("typeless")){
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
			alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		}
		s_assert.assertTrue(alertMessage.contains("successfully"), "Case is not escalated successfully from coordinatior, Expected message should contains successfully but actual on UI is "+alertMessage);
		crmLogout();
		alertMessage = null;
		caseOwnerName = "automation sslead";
		loginUser(CRMSalesSupportLeadUserName, CRMSalesSupportLeadPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.enterTextInSearchFieldAndHitEnter(caseNumber);
		crmHomePage.clickCaseNumberAfterSearch(caseNumber);
		crmHomePage.clickDetailsViewLinkAfterSearch();
		crmHomePage.changeCaseOwnerFromDetailsViewAfterSearch(caseOwnerName);
		alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsViewAfterSearch();
		s_assert.assertTrue(alertMessage.contains("successfully"), "Case is not escalated successfully from lead, Expected message should contains successfully but actual on UI is "+alertMessage);
		crmLogout();
		alertMessage = null;
		loginUser(CRMSalesSupportManagerUserName, CRMSalesSupportManagerPassword);
		crmHomePage.closeAllOpenedTabs();
		crmHomePage.enterTextInSearchFieldAndHitEnter(caseNumber);
		crmHomePage.clickCaseNumberAfterSearch(caseNumber);
		crmHomePage.clickDetailsViewLinkAfterSearch();
		crmHomePage.clickCaseResolvedButtonOnDetailsViewAfterSearch();
		statusFromUI = crmHomePage.getStatusOfCase();
		s_assert.assertTrue(statusFromUI.contains(statusType), "Expected case status is "+statusType+" but actual on UI is "+statusFromUI);
		crmLogout();
		loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
		crmHomePage.closeAllOpenedTabs();
		//create new case for exceptions and escalate case
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.clickNewCaseButton();
		crmHomePage.fillTheDetailsAndClickSaveForNewCase(typeValue,subypeValue);
		crmHomePage.clickDetailsViewLink();
		alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		s_assert.assertTrue(alertMessage.contains("successfully"), "Case is not escalated successfully for exceptions, Expected message should contains successfully but actual on UI is "+alertMessage);
		crmHomePage.closeAllOpenedTabs();
		//create new case for Suspicious and escalate case
		typeValue = "Suspicious Activity";
		subypeValue = "Whitelist Request";
		crmHomePage.clickNewCaseButton();
		crmHomePage.fillTheDetailsAndClickSaveForNewCase(typeValue,subypeValue);
		crmHomePage.clickDetailsViewLink();
		alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		s_assert.assertTrue(alertMessage.contains("not escalatable"), "Case is escalated successfully for Suspicious, Expected message should contains successfully but actual on UI is "+alertMessage);
		s_assert.assertAll();
	}

	// QTest ID TC-1243 Verify cases for 'Customer Care Coordinator', 'Teletech Coordinator', 'The Connection Coordinator'
	@Test(enabled=true)
	public void testVerifyCasesForCustomerCare_Teletech_TheConnection_Coordinatior_1243q(){
		String caseValue = "Cases";
		String statusTypeOpen = "Open";
		String alertMessage = null;
		String caseOwnerName = "Automation_ SSCoordinator";
		String salesSupportMediumQueueValue = "Sales Support Medium";
		String caseType = null;
		String caseTypeQA = "QA/QC";
		String label_Type = "Type";
		loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.selectValueFromSubDD(salesSupportMediumQueueValue);
		crmHomePage.clickAndReturnCaseNumber(statusTypeOpen);
		crmHomePage.clickDetailsViewLink();
		caseType = crmHomePage.getCaseType();
		if(caseType.contains(caseTypeQA)){
			crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
		}
		crmHomePage.changeCaseOwnerFromDetailsView(caseOwnerName);
		alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		if(alertMessage.contains("typeless")){
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
			alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		}
		s_assert.assertTrue(alertMessage.contains("successfully"), "Case is not escalated successfully from coordinatior, Expected message should contains successfully but actual on UI is "+alertMessage);
		crmLogout();
		loginUser(CRMTeletechCoordinatorUserName, CRMTeletechCoordinatorPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.selectValueFromSubDD(salesSupportMediumQueueValue);
		caseOwnerName = "automation telecoordinator";
		alertMessage = null;

		crmHomePage.clickAndReturnCaseNumber(statusTypeOpen);
		crmHomePage.clickDetailsViewLink();
		caseType = crmHomePage.getCaseType();
		if(caseType.contains(caseTypeQA)){
			crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
		}
		crmHomePage.changeCaseOwnerFromDetailsView(caseOwnerName);
		alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		if(alertMessage.contains("typeless")){
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
			alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		}
		s_assert.assertTrue(alertMessage.contains("successfully"), "Case is not escalated successfully from teletech coordinatior, Expected message should contains successfully but actual on UI is "+alertMessage);
		crmLogout();
		loginUser(CRMTheConnectionUserName, CRMTheConnectionPassword);
		crmHomePage.selectValueFromServiceCloudDD(caseValue);
		crmHomePage.selectValueFromSubDD(salesSupportMediumQueueValue);
		caseOwnerName = "automation theconncoordinator";
		alertMessage = null;
		//Change case owner status
		crmHomePage.clickAndReturnCaseNumber(statusTypeOpen);
		crmHomePage.clickDetailsViewLink();
		caseType = crmHomePage.getCaseType();
		if(caseType.contains(caseTypeQA)){
			crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
		}
		crmHomePage.changeCaseOwnerFromDetailsView(caseOwnerName);
		alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		if(alertMessage.contains("typeless")){
			crmHomePage.clickEditButtonOnDetailsView();
			crmHomePage.selectFirstValueFromDD(label_Type);
			crmHomePage.clickSaveBtnForNewCase();
			alertMessage = crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
		}
		s_assert.assertTrue(alertMessage.contains("successfully"), "Case is not escalated successfully from connection coordinatior, Expected message should contains successfully but actual on UI is "+alertMessage);
		crmLogout();
		s_assert.assertAll();
	}

	// QTest ID TC-1252 Verify OrderProtectionQueue - pending case status
			@Test
			public void testVerifyOrderProtectionQueue_1252q(){
				String caseValue = "Cases";
				String orderManagmentQueueValue = "Order Protection Queue";
				String caseOwnerName = "Automation_ SSCoordinator";
				String statusType = "Open";
				String statusTypeResolve = "Resolved";
				String statusFromUI = null;
				String caseType = null;
				String caseTypeQA = "QA/QC";
				String label_Type = "Type";
				String label_Status = "Status";
				loginUser(CRMSalesSupportCoordinatorUserName, CRMSalesSupportCoordinatorPassword);
				crmHomePage.selectValueFromServiceCloudDD(caseValue);
				crmHomePage.selectValueFromSubDD(orderManagmentQueueValue);
				//crmHomePage.setNumberOfRecordsPerPageFromDD("200");
				crmHomePage.clickAndReturnCaseNumberInList(statusType);
				crmHomePage.clickDetailsViewLink();
				caseType = crmHomePage.getCaseType();
				if(caseType.contains(caseTypeQA)){
					crmHomePage.clickEscalateCaseAndAcceptAlertFromDetailsView();
					crmHomePage.clickEditButtonOnDetailsView();
					crmHomePage.selectFirstValueFromDD(label_Type);
					crmHomePage.clickSaveBtnForNewCase();
				}
				crmHomePage.changeCaseOwnerFromDetailsView(caseOwnerName);
				crmHomePage.clickCloseCaseAndMakeStatusAsResolved(label_Status, statusTypeResolve);
				crmHomePage.clickSaveBtnForNewCase();
				statusFromUI = crmHomePage.getStatusOfCase();
				s_assert.assertTrue(statusFromUI.contains(statusTypeResolve), "Expected case status is "+statusTypeResolve+" but actual on UI is "+statusFromUI);
				s_assert.assertAll();
			}
			
		// QTest ID TC-1245 Verify case functionality for AU Salessupport
		@Test
		public void testVerifyCaseFunctionalityForAUSalesSupport_1245q(){
			String caseType = null;
			String caseTypeQA = "QA/QC";
			String label_Type = "Type";
			String caseValue = "Cases";
			String typeValue = "Suspicious Activity";
			String statusType = "Resolved";
			String statusTypeOpen = "Open";
			String statusFromUI = null;
			String salesSupportAustraliaQueueValue = "SalesSupport Australia";
			String caseOwnerNameFromUI = null;
			String caseOwnerName = "Automation_ SSCoordinator";
			loginUser(CRMAUSalesSupportCoordinatorUserName, CRMAUSalesSupportCoordinatorPassword);
			crmHomePage.selectValueFromServiceCloudDD(caseValue);
			crmHomePage.clickNewCaseButton();
			crmHomePage.fillTheDetailsAndClickSaveForNewCase(typeValue,"2","2",statusType);
			statusFromUI = crmHomePage.getStatusOfCase();
			s_assert.assertTrue(statusFromUI.contains(statusType), "Case is not created and Expected case status is "+statusType+" but actual on UI for spam type is "+statusFromUI);
			crmHomePage.closeAllOpenedTabs();
			crmHomePage.selectValueFromSubDD(salesSupportAustraliaQueueValue);
			crmHomePage.clickAndReturnCaseNumber(statusTypeOpen);
			crmHomePage.clickDetailsViewLink();
			caseType = crmHomePage.getCaseType();
			if(caseType.contains(caseTypeQA)){
				crmHomePage.clickEditButtonOnDetailsView();
				crmHomePage.selectFirstValueFromDD(label_Type);
				crmHomePage.clickSaveBtnForNewCase();
			}
			crmHomePage.changeCaseOwnerFromDetailsView(caseOwnerName);
			caseOwnerNameFromUI = crmHomePage.getCaseOwnerName();
			s_assert.assertTrue(caseOwnerNameFromUI.contains(caseOwnerName), "Expected case owner name is "+caseOwnerName+" but actual on UI is "+caseOwnerNameFromUI);
			s_assert.assertAll();
		}
}