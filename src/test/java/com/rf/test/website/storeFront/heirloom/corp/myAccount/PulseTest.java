package com.rf.test.website.storeFront.heirloom.corp.myAccount;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.TestConstants;
import com.rf.core.website.constants.TestConstantsRFL;
import com.rf.core.website.constants.dbQueries.DBQueries_RFL;
import com.rf.test.website.RFHeirloomWebsiteBaseTest;

public class PulseTest extends RFHeirloomWebsiteBaseTest {
	
	/**
	 * Jira Story Id: MAIN-7296
	 * qTest Id: TC-2845
	 */
	@Test(alwaysRun=true,description = "Check My Pulse navigation(CORP)")
	public void testCheckMyPulse_2845() {
		List<Map<String, Object>> randomConsultantList =  null;
		String consultantEmailID = null;
		RFL_DB=driver.getDBNameRFL();
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
		storeFrontHeirloomConsultantPage.clickOnWelcomeDropdown();
		s_assert.assertTrue(storeFrontHeirloomConsultantPage.isCheckMyPulsePresent(),"Check My pulse is not present in welcome Dorpdown");
		s_assert.assertAll();
	}

}
