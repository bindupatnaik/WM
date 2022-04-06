package com.rf.test.website.LSD;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.rf.core.utils.CommonUtils;
import com.rf.test.website.RFLSDWebsiteBaseTest;

public class LSDLinkTests extends RFLSDWebsiteBaseTest{

//	@AfterClass
//	public void afterLSDLinkLogout(){
//		lsdHomePage.clickLogout();
//	}

	// qTest ID - 1455 Link - Lead The Way
	@Test(alwaysRun=true,priority=1,enabled=false)
	public void testVerifyLeadTheWayLink_1455q(){
		String currentUrl = null;
		String urlToAssert = "randfjourney";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("Lead the Way");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isLeadTheWayLinkPresentInTopNav(),"Lead the Way Link is not Present");
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	//  qTest ID - 1456 Link - RF Connection
	@Test(alwaysRun=true,priority=2,enabled=false)
	public void testVerifyRFConnectionLink_1456q(){
		String currentUrl = null;
		String urlToAssert = "rfconnection";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("RF Connection");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isRFConnectionPageHeaderPresent(),"RF Connection Page Header is not Present");
		s_assert.assertTrue(lsdHomePage.isRFConnectionHomeMenuPresent(),"RF Connection Home Menu is not Present");
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	// qTest ID-1457 Link - RF Journey
	@Test(alwaysRun=true,priority=3,enabled=false)
	public void testVerifyRFJourneyLink_1457q(){
		String currentUrl = null;
		String urlToAssert = "randfjourney";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("RF Journey");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isRFJourneyPageHeaderPresent(),"RF Journey Page Header is not Present");
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	//  qTest ID-1458 Link - RF Mall
	@Test(alwaysRun=true,priority=4,enabled=false)
	public void testVerifyRFMallLink_1458q(){
		String currentUrl = null;
		String urlToAssert = "dsa-direct";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("RF Mall");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	// qTest ID-1459 Link - RF Pay Day
	@Test(alwaysRun=true,priority=5,enabled=false)
	public void testVerifyRFPaydayLink_1459q(){
		String currentUrl = null;
		String urlToAssert = "payday";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("RF Payday");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	// qTest ID-1447 Link - Comms Corner
	@Test(alwaysRun=true,priority=7,enabled=false)
	public void testVerifyLinkCommsCorner_1447q(){
		String currentUrl = null;
		String urlToAssert = "communications.rodanandfields.com";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("Comms Corner");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	// qTest ID-1448 Link - Consultant - LED Events
	@Test(alwaysRun=true,priority=8,enabled=false)
	public void testVerifyLinkConsultantLEDEvents_1448(){
		String currentUrl = null;
		String urlToAssert = "rfconsultantevents";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("Consultant - Led Events");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	// qTest ID-1449 Link - Corporate Events 
	@Test(alwaysRun=true,priority=9,enabled=false)
	public void testVerifyLinkCorporateEvents_1449q(){
		String currentUrl = null;
		String urlToAssert = "rodanandfieldsevents";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("Corporate events");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	// qTest ID-1450 Link - Getting Started
	@Test(alwaysRun=true,priority=10)
	public void testVerifyLinkGettingStarted_1450q(){
		String currentUrl = null;
		String urlToAssert = "getstarted";
		String parentWindowHandle = null;
		parentWindowHandle = CommonUtils.getCurrentWindowHandle();
		lsdHomePage.selectSubMenuFromLinksMenu("Getting Started");
		lsdHomePage.switchToChildWindow(parentWindowHandle);
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		lsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	
	// TC-2631 Main Menu links
	@Test
	public void testMainMenuLinks_2631q(){
		String currentUrl = null;
		String urlToAssert = null;
		String topMenuNamePulse="Pulse";
		s_assert.assertTrue(lsdHomePage.isTopNavigationHeaderDDMenuPresent(topMenuNamePulse),"Menu "+topMenuNamePulse+" is not present In top navigation");
		//Verify Home Page link under pulse.
		urlToAssert = "#/home";
		lsdHomePage.selectSubMenuFromPulseMenu("Home");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isHomePageOfPulsePresent(),"HomePage of pulse is not present");
		//Verify team link under pulse.
		urlToAssert = "#/team";
		lsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		//Verify customers link under pulse.
		urlToAssert = "#/pcs";
		lsdHomePage.selectSubMenuFromPulseMenu("Customers");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present");
		//Verify Order link under pulse.
		urlToAssert = "#/orders";
		lsdHomePage.selectSubMenuFromPulseMenu("Orders");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(lsdHomePage.isOrderPageOfPulsePresent(),"Order Page of pulse is not present");

		urlToAssert = "#/help";
		lsdHomePage.selectSubMenuFromPulseMenu("FAQ");
		currentUrl = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertAll();
	}

	// qTest ID-2953 FAQ navigation
	@Test
	public void testFAQNavigation_2953q(){
		String subLinkFAQs = "FAQ";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkFAQs);
		s_assert.assertTrue(lsdHomePage.isTextPresentAtEstimatedPage(subLinkFAQs) && lsdHomePage.getCurrentURL().contains("help"), "Vital signs report page is not present");
		s_assert.assertAll();
	}

	//qTest ID-2954 Menu and Menu actions
	@Test(alwaysRun=true,enabled=false)//Not a part of mini Reg
	public void testMenuAndMenuActions_2954q(){
		String subLinkFAQs = "FAQ";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkFAQs);
		s_assert.assertTrue(lsdHomePage.getCurrentURL().contains("help"), "User is not redirecting to FAQ page");
		s_assert.assertTrue(lsdHomePage.isExpandableSymbolPresent(), "Expandable symbol is not present");
		lsdHomePage.clickFirstExpandableSymbolOfReport();
		s_assert.assertTrue(lsdHomePage.isDetailVisibleAfterClickedOnExpandableSymbol(), "Detail section is not present after click on expandable symbol");
		lsdHomePage.clickFirstExpandableSymbolOfReport();
		s_assert.assertFalse(lsdHomePage.isDetailVisibleAfterClickedOnExpandableSymbol(), "Detail section is present after click on 2nd expandable symbol");
		s_assert.assertAll();
	}

	//qTest ID-2876 Consultant Card components
	@Test
	public void testConsultantCardComponents_2876q(){
		String subLinkTeam = "Team";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkTeam);
		s_assert.assertTrue(lsdHomePage.getCurrentURL().contains("team"), "User is not redirecting to team page");
		lsdHomePage.clickFirstSubCategoryOfTeamPage();
		s_assert.assertTrue(lsdHomePage.isFirstAndLastNamePresentAtConsultantCardUnderTeam(), "First and last name is not present at consultant card");
		s_assert.assertTrue(lsdHomePage.isQualificationTitlePresentAtConsultantCardUnderTeam(), "Qualification title is not present at consultant card");
		s_assert.assertTrue(lsdHomePage.isSVPresentAtConsultantCardUnderTeam(), "SV value is not present at consultant card");
		s_assert.assertTrue(lsdHomePage.isPSQVPresentAtConsultantCardUnderTeam(), "PSQV value is not present at consultant card");
		s_assert.assertTrue(lsdHomePage.isRelationPresentAtConsultantCardUnderTeam(), "relation is not present at consultant card");
		s_assert.assertTrue(lsdHomePage.isTwoInitialIconPresentAtConsultantCardUnderTeam(), "Two initial icon is not present at consultant card");
		s_assert.assertTrue(lsdHomePage.isContactIconPresentAtConsultantCardUnderTeam(), "contact icon is not present at consultant card");
		s_assert.assertAll();
	}

	//qTest ID-2874 Check in With your Team Section -Label
	@Test(alwaysRun=true,enabled=false)//Not a part of mini Reg
	public void testCheckInWithYourTeamSectionLabel_2874q(){
		String subLinkTeam = "Team";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkTeam);
		s_assert.assertTrue(lsdHomePage.isTextPresentUnderCheckInWithYourTeam(), "Text is not present under check in with your team section");
		s_assert.assertAll();
	}

	//qTest ID-2875 Team View subcategories
	@Test(alwaysRun=true,enabled=false)//Not a part of mini Reg
	public void teamViewSubCategories_2875q(){
		String subLinkTeam = "Team";
		String text_HavePromotedToECAgain = "have promoted to EC again";
		String text_AreGettingThere = "are getting there";
		String text_MayNotPromote = "may not promote";
		String text_NewConsultants = "new consultants";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkTeam);
		lsdHomePage.clickFirstSubCategoryOfTeamPage();
		s_assert.assertTrue(lsdHomePage.isTextPresentAtHeader(text_HavePromotedToECAgain), text_HavePromotedToECAgain+" text is not present at header");
		lsdHomePage.navigateToBackPage();
		lsdHomePage.clickSubCategoryOnTeamPage("2");
		s_assert.assertTrue(lsdHomePage.isTextPresentAtHeader(text_AreGettingThere), text_AreGettingThere+" text is not present at header");
		lsdHomePage.navigateToBackPage();
		lsdHomePage.clickSubCategoryOnTeamPage("3");
		s_assert.assertTrue(lsdHomePage.isTextPresentAtHeader(text_MayNotPromote), text_MayNotPromote+" text is not present at header");
		lsdHomePage.navigateToBackPage();
		lsdHomePage.clickNewConsultantLink();
		s_assert.assertTrue(lsdHomePage.isTextPresentAtHeader(text_NewConsultants), text_NewConsultants+" text is not present at header");
		s_assert.assertAll();
	}

	//qTest ID-3238 Verify Sublevel tabs with 6 level
	@Test
	public void testVerifyTabsWith6Level_3238q(){
		String currentURL = null;
		String subLinkTeam = "Team";
		lsdHomePage.selectSubMenuFromPulseMenu(subLinkTeam);
		lsdHomePage.clickViewYourTeamLink();
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("level/1"),"Current URL : "+currentURL+" does not contains level/1");
		if(lsdHomePage.isLevelTabPresent("1")){
			lsdHomePage.clickLevelTab("1");
			s_assert.assertTrue(lsdHomePage.isFirstAndLastNamePresentAtConsultantCardUnderTeam(), "Consultant card is not present for level tab 1");
		}
		if(lsdHomePage.isLevelTabPresent("2")){
			lsdHomePage.clickLevelTab("2");
			s_assert.assertTrue(lsdHomePage.isFirstAndLastNamePresentAtConsultantCardUnderTeam(), "Consultant card is not present for level tab 2");
		}
		if(lsdHomePage.isLevelTabPresent("3")){
			lsdHomePage.clickLevelTab("3");
			s_assert.assertTrue(lsdHomePage.isFirstAndLastNamePresentAtConsultantCardUnderTeam(), "Consultant card is not present for level tab 3");
		}
		if(lsdHomePage.isLevelTabPresent("4")){
			lsdHomePage.clickLevelTab("4");
			s_assert.assertTrue(lsdHomePage.isFirstAndLastNamePresentAtConsultantCardUnderTeam(), "Consultant card is not present for level tab 4");
		}
		if(lsdHomePage.isLevelTabPresent("5")){
			lsdHomePage.clickLevelTab("5");
			s_assert.assertTrue(lsdHomePage.isFirstAndLastNamePresentAtConsultantCardUnderTeam(), "Consultant card is not present for level tab 5");
		}
		if(lsdHomePage.isLevelTabPresent("6")){
			lsdHomePage.clickLevelTab("6");
			s_assert.assertTrue(lsdHomePage.isFirstAndLastNamePresentAtConsultantCardUnderTeam(), "Consultant card is not present for level tab 6");
		}
		s_assert.assertAll();
	}

	//qTest ID-3330 Sorting menu for Promoted to EC
	@Test(alwaysRun=true,enabled=false)//Not a part of mini Reg
	public void testSortingMenuForPromotedToEC_3330q(){
		String currentURL = null;
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
		lsdHomePage.clickFirstSubCategoryOfTeamPage();
		currentURL = lsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains("ec-or"),"Current URL : "+currentURL+" does not contain ec-or");
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

	//qTest ID-3331 Sorting menu for Are Getting There
		@Test(alwaysRun=true,enabled=true)//Not a part of mini Reg
		public void testSortingMenuForAreGettingThere_3331q(){
			String currentURL = null;
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
			lsdHomePage.clickSubCategoryOnTeamPage("2");
			currentURL = lsdHomePage.getCurrentURL();
			s_assert.assertTrue(currentURL.contains("getting"),"Current URL : "+currentURL+" does not contain getting");
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
			lsdHomePage.clickClose();
			s_assert.assertAll();
		}

	//qTest ID-3332 Sorting menu for May Not Promote
		@Test(alwaysRun=true,enabled=true)//Not a part of mini Reg
		public void testSortingMenuForMayNotPromote_3332q(){
			String currentURL = null;
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
			lsdHomePage.clickSubCategoryOnTeamPage("3");
			currentURL = lsdHomePage.getCurrentURL();
			s_assert.assertTrue(currentURL.contains("need-to"),"Current URL : "+currentURL+" does not contain need-to");
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
			lsdHomePage.clickClose();
			s_assert.assertAll();
		}
	//qTest ID-3328 Sorting menu for View all Consultants
		@Test
		public void testSortingMenuForViewAllConsultants_3328q(){
			String currentURL = null;
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
			lsdHomePage.clickViewYourTeamLink();
			currentURL = lsdHomePage.getCurrentURL();
			s_assert.assertTrue(currentURL.contains("level/1"),"Current URL : "+currentURL+" does not contain level/1");
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
			lsdHomePage.clickClose();
			s_assert.assertAll();
		}

}
