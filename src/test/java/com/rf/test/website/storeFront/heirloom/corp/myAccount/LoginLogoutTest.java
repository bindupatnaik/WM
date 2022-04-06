package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class LoginLogoutTest  extends RFHeirloomWebsiteBaseTest{
	/**
	 * Jira Story Id: MAIN-7401
	 * qTest Id: TC-2766
	 */
	@Test(alwaysRun=true,description = "Login drop down form")
	public void testLoginDropDownForm_2766() {
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");
		storeFrontHeirloomHomePage.clickLoginDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEmailTextFieldDisplayedInLoginDropdown(),"Email Text Field is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPasswordTextFieldDisplayedInLoginDropdown(),"Password Text Field is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isForgotPasswordLinkDisplayedInLoginDropdown(),"Forgot Password Link is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginButtonDisplayedInLoginDropdown(),"Login Button is not present on Login Dropdown");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7401
	 * qTest Id: TC-2767
	 */
	@Test(alwaysRun=true,description = "Failed Login - Invalid Credentials")
	public void testFailedLoginInvalidCredentials_2767() {
		String invalidUser=TestConstants.INVALID_USER;
		String invalidPwd=TestConstants.INVALID_PASSWORD;
		String consultantEmailID=null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{

					consultantEmailID = getRandomUserFromDB(TestConstants.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");
		storeFrontHeirloomHomePage.loginAsConsultantForInvalid(invalidUser,password);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginErrorDisplayed(),"Login Error for Invalid user is not displayed");
		storeFrontHeirloomHomePage.clickLoginDropdown();
		storeFrontHeirloomHomePage.loginAsConsultantForInvalid(consultantEmailID,invalidPwd);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginErrorDisplayed(),"Login Error for Invalid pwd is not displayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7401
	 * qTest Id: TC-2768
	 */
	@Test(alwaysRun=true,description = "Failed Login - Missing required fields")
	public void testFailedLoginMissingRequiredFields_2768() {
		String consultantEmailID=null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{

					consultantEmailID = getRandomUserFromDB(TestConstants.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		storeFrontHeirloomHomePage.clickLoginDropdown();
		storeFrontHeirloomHomePage.clickLoginButtonInLoginDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginErrorDisplayed(),"Login Error is not displayed after clicking on Login button without enter any user information.");
		storeFrontHeirloomHomePage.clickLoginDropdown();
		storeFrontHeirloomHomePage.loginAsConsultantForInvalid(consultantEmailID,"");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginErrorDisplayed(),"Login Error is not displayed after clicking on Login button without enter password");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7401
	 * qTest Id: TC-2769
	 */
	@Test(alwaysRun=true,description = "Successful Login")
	public void testSuccessfulLogin_2769() {
		String consultantEmailID=null;
		if(shouldFetchDataFromExcelForTokeniation){
			consultantEmailID=getActiveUserFromExcel(TestConstants.CONS_USER_TYPE);
		}
		else{
			for(int i=1;i<=5;i++){
				try{

					consultantEmailID = getRandomUserFromDB(TestConstants.USER_TYPE_CONSULTANT);
					break;
				}catch(Exception e){
					continue;
				}
			}

		}
		assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");
		storeFrontHeirloomHomePage.loginAsConsultant(consultantEmailID,password);
		s_assert.assertFalse(storeFrontHeirloomHomePage.isLoginErrorDisplayed(),"Login Error is displayed for valid user");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isMyAccountDropdownDisplayedAfterLogin(),"User is not logged in successfully");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-7376
	 * qTest Id: TC-2814
	 */
	@Test(alwaysRun=true,description = "Logout from CORP")
	public void testLogoutFromCorp_2814() {
		String consultantEmailID = null;
		String ordersLink="Order History";
		String accountDetailsLink="Account Details";
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String editPWSLink="Edit PWS";
		String shippingDetailsLink="Shipping Details";
		String billingDetailsLink="Billing Details";
		String checkMyPulseLink="Check My Pulse";
		String logoutLink="Log Out";
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
		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(ordersLink),"Orders Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(accountDetailsLink),"Account Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(manageCRPAndPulseLink),"Manage CRP & PULSE Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(editPWSLink),"Edit PWS Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(shippingDetailsLink),"Shipping Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(billingDetailsLink),"Billing Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(checkMyPulseLink),"Check My Pulse Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(logoutLink),"Log Out Link is not present in My Account Dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(logoutLink);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserLogoutSuccessfully(),"User is not logout successfully");
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("corprfo"),"User is not redirected to corprfo site after logout");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8395
	 * qTest Id: TC-2988
	 */
	@Test(alwaysRun=true,description = "Fields for Login(COM)")
	public void testFieldsForLoginCOM_2988() {
		openCOMSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");

		storeFrontHeirloomHomePage.clickLoginDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEmailTextFieldDisplayedInLoginDropdown(),"Email Text Field is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPasswordTextFieldDisplayedInLoginDropdown(),"Password Text Field is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isForgotPasswordLinkDisplayedInLoginDropdown(),"Forgot Password Link is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginButtonDisplayedInLoginDropdown(),"Login Button is not present on Login Dropdown");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8395
	 * qTest Id: TC-2989
	 */
	@Test(alwaysRun=true,description = "Fields for Login(CORP)")
	public void testFieldsForLoginCORP_2989() {
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");
		storeFrontHeirloomHomePage.clickLoginDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEmailTextFieldDisplayedInLoginDropdown(),"Email Text Field is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPasswordTextFieldDisplayedInLoginDropdown(),"Password Text Field is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isForgotPasswordLinkDisplayedInLoginDropdown(),"Forgot Password Link is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginButtonDisplayedInLoginDropdown(),"Login Button is not present on Login Dropdown");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8395
	 * qTest Id: TC-2990
	 */
	@Test(alwaysRun=true,description = "Fields for Login(BIZ)")
	public void testFieldsForLoginBiz_2990() {
		openBIZSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");
		storeFrontHeirloomHomePage.clickLoginDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isEmailTextFieldDisplayedInLoginDropdown(),"Email Text Field is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isPasswordTextFieldDisplayedInLoginDropdown(),"Password Text Field is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isForgotPasswordLinkDisplayedInLoginDropdown(),"Forgot Password Link is not present on Login Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginButtonDisplayedInLoginDropdown(),"Login Button is not present on Login Dropdown");
		s_assert.assertAll();
	}


	/**
	 * Jira Story Id: MAIN-8395
	 * qTest Id: TC-2991
	 */
	@Test(alwaysRun=true,description = "Invalid Login error message(CORP)")
	public void testInvalidLoginErrorMessageCORP_2991() {
		String invalidUser=TestConstants.INVALID_USER;
		String invalidPwd=TestConstants.INVALID_PASSWORD;
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");
		storeFrontHeirloomHomePage.loginAsConsultantForInvalid(invalidUser,invalidPwd);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginErrorDisplayed(),"Login Error for Invalid user is not displayed");
		s_assert.assertAll();
	}

	/**
	 * Jira Story Id: MAIN-8395
	 * qTest Id: TC-2992
	 */
	@Test(alwaysRun=true,description = "Invalid Login error message(COM)")
	public void testInvalidLoginErrorMessageCOM_2992() {
		String invalidUser=TestConstants.INVALID_USER;
		String invalidPwd=TestConstants.INVALID_PASSWORD;
		openCOMSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");
		storeFrontHeirloomHomePage.loginAsConsultantForInvalid(invalidUser,invalidPwd);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginErrorDisplayed(),"Login Error for Invalid user is not displayed");
		s_assert.assertAll();
	}


	/**
	 * Jira Story Id: MAIN-8395
	 * qTest Id: TC-2993
	 */
	@Test(alwaysRun=true,description = "Invalid Login error message(BIZ)")
	public void testInvalidLoginErrorMessageBIZ_2993() {
		String invalidUser=TestConstants.INVALID_USER;
		String invalidPwd=TestConstants.INVALID_PASSWORD;
		openBIZSite();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginDropDownDisplayed(),"Login dropdown is not present on UI");
		storeFrontHeirloomHomePage.loginAsConsultantForInvalid(invalidUser,invalidPwd);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isLoginErrorDisplayed(),"Login Error for Invalid user is not displayed");
		s_assert.assertAll();
	}
	
	@Test(alwaysRun=true,description = "Logout from PWS")
	public void testLogoutFromPWS_2813() {
		String consultantEmailID = null;
		String ordersLink="Order History";
		String accountDetailsLink="Account Details";
		String manageCRPAndPulseLink="Manage CRP & PULSE";
		String editPWSLink="Edit PWS";
		String shippingDetailsLink="Shipping Details";
		String billingDetailsLink="Billing Details";
		String checkMyPulseLink="Check My Pulse";
		String logoutLink="Log Out";
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

		storeFrontHeirloomHomePage.clickMyAccountDropdown();
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(ordersLink),"Orders Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(accountDetailsLink),"Account Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(manageCRPAndPulseLink),"Manage CRP & PULSE Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(editPWSLink),"Edit PWS Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(shippingDetailsLink),"Shipping Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(billingDetailsLink),"Billing Details Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(checkMyPulseLink),"Check My Pulse Link is not present in My Account Dropdown");
		s_assert.assertTrue(storeFrontHeirloomHomePage.isSpecifiedCategoryPresentInMyAccountDropdown(logoutLink),"Log Out Link is not present in My Account Dropdown");
		storeFrontHeirloomHomePage.clickMyAccountDropdown();

		storeFrontHeirloomHomePage.clickMyAccountDropDownAndSelectSpecifiedCategory(logoutLink);
		s_assert.assertTrue(storeFrontHeirloomHomePage.isUserLogoutSuccessfully(),"User is not logout successfully");
		currentUrl = storeFrontHeirloomHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains("biz"),"User is not redirected to biz site after logout");
		s_assert.assertAll();
	} 	
	
	
}
