package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class EditPWSTest extends RFHeirloomWebsiteBaseTest {

	/**
	 * Jira Story Id: MAIN-5262
	 * qTest Id: TC-2820
	 */
	@Test(alwaysRun=true,description = "If not enrolled in CRP, Edit My CRP hidden")
	public void testIfNotEnrolledInCRPEditMyCRPHidden_2820() {
		String myAccountCategory="Edit CRP Cart";
		List<Map<String, Object>> randomConsultantList =  null;
		String consultantEmailID = null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}

		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit My CRP is present in my account dropdown");

		openBIZSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit My CRP is present in my account dropdown on biz pws");

		openCOMSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit My CRP is present in my account dropdown on com pws");
		s_assert.assertAll();
	}


	/**
	 * Jira Story Id: MAIN-5262
	 * qTest Id: TC-2819
	 */
	@Test(alwaysRun=true,description = "Edit My CRP option in My Account if enrolled")
	public void testEditMyCRPOptionInMyAccountIfEnrolled_2819() {
		String consultantEmailID = null;
		String myAccountCategory="Edit CRP Cart";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;
		RFL_DB=driver.getDBNameRFL();
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}

		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("Replenishment/Overview"),"User is not redirected to Edit My CRP page");

		openBIZSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("Replenishment/Overview"),"User is not redirected to Edit My CRP page on biz site");

		openCOMSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("Replenishment/Overview"),"User is not redirected to Edit My CRP page on com site");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7228
	 * qTest Id: TC-2848
	 */
	@Test(alwaysRun=true,description = "Edit My PWS from My Account drop-down(CORP)")
	public void testEditMyPWSFromMyAccountDropDown_Corp_2848() {
		String consultantEmailID = null;
		String myAccountCategory="Edit PWS";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit PWS Link is not present in My Account dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("Edit/Options"),"User is not redirected to Edit PWS page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isEditPWSPageDisplayed(),"Edit PWS Page is not Displayed");
		s_assert.assertAll();
	}


	/**
	 * Jira Story Id: MAIN-7228
	 * qTest Id: TC-2849
	 */
	@Test(alwaysRun=true,enabled=false,description = "Edit My PWS removed for unpaid subscription(CORP)")//Need query
	public void testEditMyPWSRemovedForUnpaidSubscription_Corp_2849() {
		String consultantEmailID = null;
		String myAccountCategory="Edit PWS";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}

		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit PWS Link present in My Account dropdown");
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertFalse(currentUrl.contains("Edit/Options"),"User is not redirected to Edit PWS page");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isEditPWSPageDisplayed(),"Edit PWS Page is not Displayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7228
	 * qTest Id: TC-2850
	 */
	@Test(alwaysRun=true,description = "Edit My PWS from My Account drop-down(BIZ)")
	public void testEditMyPWSFromMyAccountDropDown_BIZ_2850() {
		String consultantEmailID = null;
		String myAccountCategory="Edit PWS";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openBIZSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit PWS Link is not present in My Account dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("Edit/Options"),"User is not redirected to Edit PWS page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isEditPWSPageDisplayed(),"Edit PWS Page is not Displayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7228
	    qTest Id: TC-2851 
	 */
	// Required active Consultant without a paid Pulse subscription
	@Test(alwaysRun=true,enabled=false, description = "Edit My PWS removed for unpaid subscription(BIZ)")//Need query
	public void testEditMyPWSRemovedForUnpaidSubscription_BIZ_2851() {
		String consultantEmailID = null;
		String myAccountCategory="Edit PWS";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openBIZSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit PWS Link present in My Account dropdown");
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertFalse(currentUrl.contains("Edit/Options"),"User is not redirected to Edit PWS page");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isEditPWSPageDisplayed(),"Edit PWS Page is not Displayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7228
	 * qTest Id: TC-2852
	 */
	@Test(alwaysRun=true,description = "Edit My PWS from My Account drop-down(COM)")
	public void testEditMyPWSFromMyAccountDropDown_COM_2852() {
		String consultantEmailID = null;
		String myAccountCategory="Edit PWS";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{
					randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_ORDERS_AND_AUTOSHIPS_RFL,RFL_DB);
					consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		openCOMSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit PWS Link is not present in My Account dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(myAccountCategory);
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("Edit/Options"),"User is not redirected to Edit PWS page");
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isEditPWSPageDisplayed(),"Edit PWS Page is not Displayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7228
	 * qTest Id: TC-2853
	 */
	@Test(alwaysRun=true,enabled=false ,description = "Edit My PWS removed for unpaid subscription(COM)")//Need query
	public void testEditMyPWSRemovedForUnpaidSubscription_COM_2853() {
		String consultantEmailID = null;
		String myAccountCategory="Edit PWS";
		String currentUrl=null;
		List<Map<String, Object>> randomConsultantList =  null;

		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFL.GET_RANDOM_ACTIVE_CONSULTANT_WITH_INACTIVE_CRP_AND_PULSE_TEMPLATE_RFL,RFL_DB);
			consultantEmailID = (String) getValueFromQueryResult(randomConsultantList, "UserName");
		}
		openCOMSite();
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(myAccountCategory),"Edit PWS Link present in My Account dropdown");
		currentUrl=storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertFalse(currentUrl.contains("Edit/Options"),"User is not redirected to Edit PWS page");
		s_assert.assertFalse(storeFrontHeirloomConsultantPage.isEditPWSPageDisplayed(),"Edit PWS Page is not Displayed");
		s_assert.assertAll();
	}

}
