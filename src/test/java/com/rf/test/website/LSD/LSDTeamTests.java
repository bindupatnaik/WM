package com.rf.test.website.LSD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.rf.test.website.RFLSDWebsiteBaseTest;

public class LSDTeamTests extends RFLSDWebsiteBaseTest{

	@AfterClass
	public void afterLSDTeamLogout(){
		lsdHomePage.clickLogout();
	}

	// TC-3234 Team - Number of consultants promoted to EC again.
	@Test(alwaysRun=true,enabled=false)
	public void testVerifyNumberOfConsultantsPromotedToEC_3234(){
		String currentUrl = null;
		String urlToAssert = "team";
		String consultantPromotedToEC=null;

		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		consultantPromotedToEC=lsdHomePage.getCountOfConsultantPromotedToEC();
		s_assert.assertTrue(consultantPromotedToEC.contains("8"),"Count of consultant promoted to EC expected 8 and actual"+consultantPromotedToEC);
		s_assert.assertAll();
	}

	// TC-3278 Team - Number of consultants getting there
	@Test(alwaysRun=true,enabled=false)
	public void testVerifyNumberOfConsultantsGettingThere_3278(){
		String currentUrl = null;
		String urlToAssert = "team";
		String consultantNotMatchingECCriteria=null;

		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		consultantNotMatchingECCriteria=lsdHomePage.getCountOfConsultantGettingThere();
		s_assert.assertTrue(consultantNotMatchingECCriteria.contains("0"),"Count of consultant not matching EC criteria expected 0 and actual"+consultantNotMatchingECCriteria);
		s_assert.assertAll();
	}

	// TC-3237 Team - Number of New Consultants
	@Test(alwaysRun=true,enabled=false)
	public void testVerifyNumberOfNewConsultants_3237(){
		String currentUrl = null;
		String urlToAssert = "team";
		String newEnrolledConsultants=null;

		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		newEnrolledConsultants=lsdHomePage.getCountOfNewEnrolledConsultants();
		s_assert.assertTrue(newEnrolledConsultants.contains("8"),"Count of newly enrolled consultant expected 8 and actual"+newEnrolledConsultants);
		s_assert.assertAll();
	}

	// TC-1496 Team - View all Consultants
	@Test(alwaysRun=true,enabled=false)//Not a part of mini Reg
	public void testViewAllConsultants_1496(){
		String currentUrl = null;
		String urlToAssert = "team";
		String newEnrolledConsultants=null;

		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		newEnrolledConsultants=lsdHomePage.getCountOfNewEnrolledConsultants();
		s_assert.assertTrue(newEnrolledConsultants.contains("8"),"Count of newly enrolled consultant expected 8 and actual"+newEnrolledConsultants);
		s_assert.assertAll();
	}

	// TC-1491 Team - Verify the Number of days left in the current commission period is correct
	@Test(alwaysRun=true,enabled=false)//Assertion for days is pending.
	public void testVerifyNumberOfdaysInCurrentCommissionPeriod_1491(){
		String currentUrl = null;
		String urlToAssert = "team";
		String currentPSTMonth = null;
		String daysLeft= null;
		String daysAndMonthLeftForComm=null;

		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		daysAndMonthLeftForComm=lsdHomePage.getEstimatedDaysLeftForCommission();
		currentPSTMonth=lsdHomePage.getCurrentMonthFromCurrentPSTDate();
		s_assert.assertTrue(daysAndMonthLeftForComm.contains(currentPSTMonth),"Current month for commission period is not correct.");
		s_assert.assertAll();
	}

	//qTest ID-3329 Sorting menu for New Consultants
	@Test
	public void testSortingMenuForNewConsultant_3329q(){
		String subLinkTeam = "Team";
		String filterFirstNameAtoZ = "First name: A to Z";
		String filterFirstNameZtoA = "First name: Z to A";
		String filterLastNameAtoZ = "Last name: A to Z";
		String filterLastNameZtoA = "Last name: Z to A";
		String filterCountryNameAtoZ = "Country: A to Z";
		String filterCountryNameZtoA = "Country: Z to A";
		String filterQualificationTitleLowToHigh = "Qualification title: Low to High";
		String filterQualificationTitleHighToLow = "Qualification title: High to Low";
		String filterPhysicalLevelLowToHigh = "Physical level: Low to High";
		String filterPhysicalLevelHighToLow = "Physical level: High to Low";
		String filterPSQVLowToHigh = "PSQV: Low to High";
		String filterPSQVHighToLow = "PSQV: High to Low";
		String filterEstPSQVLowToHigh = "Estimated PSQV: Low to High";
		String filterEstPSQVHighToLow = "Estimated PSQV: High to Low";
		String filterSVLowToHigh = "SV: Low to High";
		String filterSVHighToLow = "SV: High to Low";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkTeam);
		lsdHomePage.clickNewConsultantsLink();
		lsdHomePage.clickFilter();
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterFirstNameAtoZ), filterFirstNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterFirstNameZtoA), filterFirstNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterLastNameAtoZ), filterLastNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterLastNameZtoA), filterLastNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterCountryNameAtoZ), filterCountryNameAtoZ+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterCountryNameZtoA), filterCountryNameZtoA+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterQualificationTitleLowToHigh), filterQualificationTitleLowToHigh+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterQualificationTitleHighToLow), filterQualificationTitleHighToLow+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterPhysicalLevelLowToHigh), filterPhysicalLevelLowToHigh+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterPhysicalLevelHighToLow), filterPhysicalLevelHighToLow+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterPSQVLowToHigh), filterPSQVLowToHigh+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterPSQVHighToLow), filterPSQVHighToLow+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterEstPSQVLowToHigh), filterEstPSQVLowToHigh+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterEstPSQVHighToLow), filterEstPSQVHighToLow+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterSVLowToHigh), filterSVLowToHigh+" is not visible at filters page");
		s_assert.assertTrue(lsdHomePage.isFilterNameVisible(filterSVHighToLow), filterSVHighToLow+" is not visible at filters page");
		s_assert.assertAll();
	}

	//TC-3236 Team - Number of consultants who may not Promote
	@Test(alwaysRun=true,enabled=false)
	public void testVerifyNumberOfConsultantsWhoMayNotPromoteToEC_3236(){
		String currentUrl = null;
		String urlToAssert = "team";
		String whoMayNotPromote=null;

		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		whoMayNotPromote=lsdHomePage.getCountOfConsultantsWhoMayNotPromoteToEC();
		s_assert.assertTrue(whoMayNotPromote.contains("8"),"Count of newly enrolled consultant expected 8 and actual"+whoMayNotPromote);
		s_assert.assertAll();
	}

}
