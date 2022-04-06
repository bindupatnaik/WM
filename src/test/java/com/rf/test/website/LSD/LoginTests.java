package com.rf.test.website.LSD;

import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.rf.core.utils.DBUtil;
import com.rf.core.utils.QueryUtils;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.RFLSDWebsiteBaseTest;

public class LoginTests extends RFLSDWebsiteBaseTest{

	//TC-2628 Login Failed
	@Test(alwaysRun=true)
	public void testLoginFailed_2628q(){
		List<Map<String, Object>> randomConsultantList;
		List<Map<String, Object>> randomConsultantListCommision;
		List<Map<String, Object>> randomPCList;
		String pcEmail=null;
		String accountID=null;
		String emailID = "";
		String Password = "";
		String incorrectPassword = "222Maiden";
		String loginErrorTextForUsername="Please enter your username.";
		String loginErrorTextForPassword="Please enter your password.";
		String loginErrorTextForBoth="Please confirm you have entered the correct password and username.";
		lsdHomePage.clickLogout();

		//Verify login error for empty username and password fields.
		lsdLoginPage.pulseLogin(emailID,Password);
		s_assert.assertTrue(lsdLoginPage.isLoginFailErrorPresent(),"Login Error not present for empty username and password");
		s_assert.assertTrue(lsdLoginPage.getLoginErrorText().contains(loginErrorTextForUsername),"Login error text not present for empty username and password.");
		//Verify login error for empty password fields.
		randomConsultantListCommision = DBUtil.performDatabaseQuery(DBQueries_RFO.GET_ACCOUNT_ID_HAVING_ACTIVE_PULSE,commDBName,Commisions_DB_IP);
		accountID = String.valueOf(getValueFromQueryResult(randomConsultantListCommision, "AccountID"));
		randomConsultantList = DBUtil.performDatabaseQuery(DBQueries_RFO.callQueryWithArguement(DBQueries_RFO.GET_EMAIL_ID_FROM_ACCOUNT_ID,accountID),RFO_DBName,RFO_DB_IP);
		emailID= (String) getValueFromQueryResult(randomConsultantList, "EmailAddress");
		lsdLoginPage.pulseLogin(emailID,Password);
		s_assert.assertTrue(lsdLoginPage.isLoginFailErrorPresent(),"Login Error not present for empty password field");
		s_assert.assertTrue(lsdLoginPage.getLoginErrorText().contains(loginErrorTextForPassword),"Login error text not present for empty Password.");
		//Verify login error for incorrect password fields.
		lsdLoginPage.pulseLogin(emailID,incorrectPassword);
		s_assert.assertTrue(lsdLoginPage.isLoginFailErrorPresent(),"Login Error not present for incorrect password");
		s_assert.assertTrue(lsdLoginPage.getLoginErrorText().contains(loginErrorTextForBoth),"Login error text not present for incorrect password.");
		//Verify login error for user who is not consultant.
		pcEmail = QueryUtils.getRandomActivePCFromDB(RFO_DBName, DBQueries_RFO.GET_RANDOM_ACTIVE_PC_WITH_ORDERS_AND_AUTOSHIPS_RFO,countryId);
		lsdLoginPage.pulseLogin(pcEmail,password);
		s_assert.assertTrue(lsdLoginPage.isLoginFailErrorPresent(),"Login Error not present for PC user.");
		s_assert.assertTrue(lsdLoginPage.getLoginErrorText().contains(loginErrorTextForBoth),"Login error text not present for PC user.");
		s_assert.assertAll();
	}

	//TC-3412 Text above the username and password
	@Test(alwaysRun=true)
	public void testTextAboveUsernameAndPassword_3412q(){
		String getStartedText="Lets get started!";
		String loginText = "Please enter your login and password.";
		lsdHomePage.clickLogout();
		//Verify text above login and password fields.	
		s_assert.assertTrue(lsdLoginPage.getTextAboveLoginFields().contains(getStartedText),"Get started text not present above login fields");
		s_assert.assertTrue(lsdLoginPage.getTextAboveLoginFields().contains(loginText),"Please enter your login and password text not present.");
		lsdLoginPage.loginToPulse(userName,password);
		s_assert.assertAll();
	}
}
