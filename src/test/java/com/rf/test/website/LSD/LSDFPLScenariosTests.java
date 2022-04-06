package com.rf.test.website.LSD;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.rf.core.utils.DBUtil;
import com.rf.core.website.constants.dbQueries.DBQueries_RFO;
import com.rf.test.website.RFLSDWebsiteBaseTest;

public class LSDFPLScenariosTests extends RFLSDWebsiteBaseTest{

	//TC-3574 FPL Link on home page
	@Test
	public void testFPLLinkOnHomePage_3574q(){
		String reportName = "Flat Performance Lineage";
		//Navigate to FPL report page.
		s_assert.assertTrue(lsdHomePage.isHomePageOfPulsePresent(), "User is not able to logged in successfully");
		lsdHomePage.clickLinkFromReportSection(reportName);
		s_assert.assertTrue(lsdHomePage.isUserOnFPLReportPage(),"User is not on FPL report page");
		s_assert.assertAll();		
	}
	//TC-3575 FPL View of consutlants and PC's on level 1
	@Test
	public void testViewConsultantsAndPCOnLevelOne_3575q(){
		String reportName = "Flat Performance Lineage";
		//Navigate to FPL report page.
		s_assert.assertTrue(lsdHomePage.isHomePageOfPulsePresent(), "User is not able to logged in successfully");
		lsdHomePage.clickLinkFromReportSection(reportName);
		s_assert.assertTrue(lsdHomePage.isUserOnFPLReportPage(),"User is not on FPL report page");
		s_assert.assertTrue(lsdHomePage.isAllLevelOneMembersPresent(),"Level one members not present under consultant");
		s_assert.assertAll();		
	}
	//TC-3565 View PC Profile
	@Test
	public void testViewPCProfile_3565q(){
		String reportName = "Flat Performance Lineage";
		//Navigate to FPL report page.
		s_assert.assertTrue(lsdHomePage.isHomePageOfPulsePresent(), "User is not able to logged in successfully");
		lsdHomePage.clickLinkFromReportSection(reportName);
		s_assert.assertTrue(lsdHomePage.isUserOnFPLReportPage(),"User is not on FPL report page");
		s_assert.assertTrue(lsdHomePage.isAllLevelOneMembersPresent(),"Level one members not present under consultant");
		//Click on level 1 PC.
		lsdHomePage.clickAndReturnFirstPCLegUnderConsultant();
		//Verify PC profile page sections.
		s_assert.assertTrue(lsdHomePage.isPCUserProfilePageOrderHistoryHeadersPresent(),"Order history not present on PC profile page");
		s_assert.assertTrue(lsdHomePage.isPCUserProfilePageContactInformationHeadersPresent(),"Contact information not present on PC profile page");
		//Verify Upcomming order section on PC profile page.
		if(lsdHomePage.isPCUserProfilePageUpcommingOrderHeadersPresent()){
			s_assert.assertTrue(lsdHomePage.isPCUserProfilePageUpcommingOrderDetailsPresent(),"Upcomming order details not present");
			s_assert.assertTrue(lsdHomePage.getUpcommingOrderStatusFromPCProfilePage().contains("Pending"),"Upcomming order status is not as expected");
			s_assert.assertTrue(lsdHomePage.getUpcommingOrderNumberFromPCProfilePage().contains("N/A"),"Upcomming order Number is not as expected");
			s_assert.assertTrue(lsdHomePage.getUpcommingOrderQVFromPCProfilePage().contains("0"),"Upcomming order QV is not as expected");
		}
		//Verify order history section on PC profile page.
		if(lsdHomePage.isPCUserProfilePageOrderHistoryHeadersPresent()){

		}
		//Verify contact information section on PC profile page.
		if(lsdHomePage.isPCUserProfilePageContactInformationHeadersPresent()){

		}
		lsdHomePage.navigateToBackPage();
		s_assert.assertTrue(lsdHomePage.isUserOnFPLReportPage(),"User is not on FPL report page after navigating back");
		s_assert.assertAll();		
	}
}
