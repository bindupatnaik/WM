package com.rf.test.website.LSD.dsv;

import org.testng.annotations.Test;
import com.rf.core.website.constants.TestConstants;
import com.rf.test.website.RFLSDDSVWebsiteBaseTest;

public class LSD_DSVTest extends RFLSDDSVWebsiteBaseTest{

	// TC-2631 Main Menu links
	@Test
	public void testMainMenuLinks_2631(){
		String currentUrl = null;
		String urlToAssert = null;
		String topMenuNamePulse="Pulse";
		s_assert.assertTrue(dsvLsdHomePage.isTopNavigationHeaderDDMenuPresent(topMenuNamePulse),"Menu "+topMenuNamePulse+" is not present In top navigation");
		//Verify Home Page link under pulse.
		urlToAssert = "#/home";
		dsvLsdHomePage.selectSubMenuFromPulseMenu("Home");
		currentUrl = dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(dsvLsdHomePage.isHomePageOfPulsePresent(),"HomePage of pulse is not present");
		//Verify team link under pulse.
		urlToAssert = "#/team";
		dsvLsdHomePage.selectSubMenuFromPulseMenu("Team");
		currentUrl = dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(dsvLsdHomePage.isTeamPageOfPulsePresent(),"Team Page of pulse is not present");
		//Verify customers link under pulse.
		urlToAssert = "#/pcs";
		dsvLsdHomePage.selectSubMenuFromPulseMenu("Customers");
		currentUrl = dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(dsvLsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present");
		//Verify Order link under pulse.
		urlToAssert = "#/orders";
		dsvLsdHomePage.selectSubMenuFromPulseMenu("Orders");
		currentUrl = dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertTrue(dsvLsdHomePage.isOrderPageOfPulsePresent(),"Order Page of pulse is not present");

		urlToAssert = "#/help";
		dsvLsdHomePage.selectSubMenuFromPulseMenu("FAQ");
		currentUrl = dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertAll();
	}

	// TC-2627 Login Success
	// TC-2632 Successful Log Out
	@Test
	public void testSuccessfulLoginAndLogout_2627_2632(){
		String currentUrl=null;
		String urlToAppend="/#/home";
		String urlToAssert="/#/login";
		s_assert.assertTrue(dsvLsdHomePage.isHomePageOfPulsePresent(), "User is not able to logged in successfully");
		dsvLsdHomePage.clickLogout();
		currentUrl=dsvLsdHomePage.getCurrentURL(); 
		currentUrl =currentUrl.replaceAll("/#/login",urlToAppend);
		dsvLsdHomePage.navigateToURL(currentUrl);
		currentUrl = dsvLsdHomePage.getCurrentURL(); 
		s_assert.assertTrue(currentUrl.contains(urlToAssert),"Current URL : "+currentUrl+" does not contains "+urlToAssert);
		s_assert.assertAll();
	}

	// TC-2545 Enrollment Date
	@Test
	public void testEnrollmentDate_2545(){
		String enrolledDateToAssert =TestConstants.DSV_NEW_PULSE_CONSULTANT_ENROLLMENT_DATE;
		String enrolledDateFromUI = null;
		s_assert.assertTrue(dsvLsdHomePage.isHomePageOfPulsePresent(), "User is not able to logged in successfully");
		enrolledDateFromUI = dsvLsdHomePage.getEnrolledDateFromOverviewSection();
		s_assert.assertTrue(enrolledDateFromUI.contains(enrolledDateToAssert),"Expected Qualification title is"+enrolledDateToAssert+" but actual on UI is "+enrolledDateFromUI);
		s_assert.assertAll();
	}

	// TC-2546 Greeting and First Name
	@Test
	public void testGreetingAndFirstName_2546(){
		//Verify section in overview.
		String firstNameFromUI = null;
		String firstNameToAssert = TestConstants.DSV_NEW_PULSE_CONSULTANT_FIRST_NAME;
		firstNameFromUI = dsvLsdHomePage.getFirstNameFromOverviewSection();
		s_assert.assertTrue(dsvLsdHomePage.isHelloTextPresentInOverviewSection(),"Hello text not present in overview section");
		s_assert.assertTrue(firstNameToAssert.equalsIgnoreCase(firstNameFromUI),"Expected First name is"+firstNameToAssert+" but actual on UI is "+firstNameFromUI);
		s_assert.assertTrue(dsvLsdHomePage.isFirstNamePresentInUpperCase(), "First Name is not in upper case");
		s_assert.assertAll();
	}

	// TC-2547 2-Initial icon
	@Test
	public void test2InitialIcon_2547(){
		//Verify section in overview.
		String initialNameFromUI = null;
		String initialNameToAssert = null;
		String firstName = TestConstants.DSV_NEW_PULSE_CONSULTANT_FIRST_NAME;
		String lastName = TestConstants.DSV_NEW_PULSE_CONSULTANT_LAST_NAME;
		initialNameFromUI = dsvLsdHomePage.getInitialName();
		initialNameToAssert = dsvLsdHomePage.getFirstLetterFromString(firstName)+dsvLsdHomePage.getFirstLetterFromString(lastName);
		s_assert.assertTrue(dsvLsdHomePage.isInitialNameContainerPresent(),"Initial name container is not visible on UI");
		s_assert.assertTrue(initialNameToAssert.equalsIgnoreCase(initialNameFromUI),"Expected First name is"+initialNameToAssert+" but actual on UI is "+initialNameFromUI);
		s_assert.assertAll();
	}

	//TC-3217 Qualification Title - C
	@Test
	public void testQualificationTitleC_3217(){
		String title = TestConstants.TITLE_TYPE_CSTAR;
		String titleName = "Qualification Title";
		String qualificationTitleFromOverview = null;
		String qualificationTitleFromCard = null;
		String qualificationTitleFromHistory = null;

		qualificationTitleFromOverview = dsvLsdHomePage.getQualificationtitleFromOverviewSection();
		s_assert.assertTrue(qualificationTitleFromOverview.equalsIgnoreCase(title), "Expected qualification title at overview section  is "+title+" actual on UI is "+qualificationTitleFromOverview);
		qualificationTitleFromCard = dsvLsdHomePage.getQualificationTitleFromCardDetails();
		s_assert.assertTrue(qualificationTitleFromCard.equalsIgnoreCase(title), "Expected qualification title at card is "+title+" actual on UI is "+qualificationTitleFromCard);
		dsvLsdHomePage.clickViewTitleHistoryLink();
		qualificationTitleFromHistory = dsvLsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		s_assert.assertTrue(qualificationTitleFromHistory.equalsIgnoreCase(title), "Expected qualification title at history is "+title+" actual on UI is "+qualificationTitleFromHistory);
		dsvLsdHomePage.navigateToBackPage();
		s_assert.assertAll();
	}

	//TC-3126 Color of SV Value and SV Ring SV<100
	@Test
	public void testColorOfSVValueAndSVRingLessThan100_3126(){
		String colorOfSV = "rgb(248, 178, 152)";//"rgb(242, 102, 49)";
		String colorOfSVFromUI = null;
		//Verify color of text
		colorOfSVFromUI = dsvLsdHomePage.getColorOfNumbersForSV();
		s_assert.assertTrue(colorOfSVFromUI.contains(colorOfSV), "Expected color of number for SV is "+colorOfSV+" but actual on UI is"+colorOfSVFromUI);
		//verify color of ring
		s_assert.assertTrue(dsvLsdHomePage.isColorOfSVRedForLessThan100(), "Expected color of for SV ring is not red");
		s_assert.assertAll();
	}

	//TC-3127 Color of PSQV Value and PSQV Ring - PSQV<600
	@Test
	public void testColorOfPSQVValueAndPSQVRingLessThan600_3127(){
		String colorOfPSQV = "rgb(242, 102, 49)";
		String colorOfPSQVFromUI = null;
		//Verify color of text
		colorOfPSQVFromUI = dsvLsdHomePage.getColorOfNumbersForPSQV();
		s_assert.assertTrue(colorOfPSQVFromUI.contains(colorOfPSQV), "Expected color of number for PSQV is "+colorOfPSQV+" but actual on UI is"+colorOfPSQVFromUI);
		//verify color of ring
		s_assert.assertTrue(dsvLsdHomePage.isColorOfPSQVRedForLessThan600(), "Expected color of for PSQV ring is not red");
		s_assert.assertAll();
	}

	//TC-2560 Vital Signs page navigation
	@Test
	public void testVitalSignsPageNavigation_2560(){
		String reportName = "Vital Signs";
		dsvLsdHomePage.clickReportLinks(reportName);
		s_assert.assertTrue(dsvLsdHomePage.isTextPresentAtEstimatedPage(reportName) && dsvLsdHomePage.getCurrentURL().contains("vital"), "Vital signs report page is not present");
		dsvLsdHomePage.navigateToBackPage();
		s_assert.assertTrue(dsvLsdHomePage.isHomePageOfPulsePresent(), "Back button is not navigating to home page");
		s_assert.assertAll();
	}

	//TC-2561 Downline report link
	@Test
	public void testDownlineReportLink_2561(){
		String reportName = "Downline";
		String currentURL = null;
		String parentWindowHandle = null;
		parentWindowHandle = dsvLsdHomePage.getParentWindowHandle();
		dsvLsdHomePage.clickReportLinks(reportName, parentWindowHandle);
		currentURL = dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(reportName.toLowerCase()), "Expected url should contains "+reportName+" actual on UI is "+currentURL);
		dsvLsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	//TC-2562 Earnings Statements report link
	@Test
	public void testEarningsStatementsReportLink_2562(){
		String reportName = "Earnings";
		String currentURL = null;
		String parentWindowHandle = null;
		parentWindowHandle = dsvLsdHomePage.getParentWindowHandle();
		dsvLsdHomePage.clickReportLinks(reportName, parentWindowHandle);
		currentURL = dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(reportName.toLowerCase()), "Expected url should contains "+reportName+" actual on UI is "+currentURL);
		dsvLsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	//TC-2563 Solution Tool report link
	@Test
	public void testSolutionToolReportLink_2563(){
		String reportName = "Solution";
		String currentURL = null;
		String parentWindowHandle = null;
		parentWindowHandle = dsvLsdHomePage.getParentWindowHandle();
		dsvLsdHomePage.clickReportLinks(reportName, parentWindowHandle);
		currentURL = dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentURL.contains(reportName), "Expected url should contains "+reportName+" actual on UI is "+currentURL);
		dsvLsdHomePage.switchToParentWindow(parentWindowHandle);
		s_assert.assertAll();
	}

	//TC-3103 Verify R+F Progress Link - Level EC III or less.
	//TC-2956 Verify R+F Progress Link - Level EC IV or more.
	@Test
	public void testVerifyRFLinkForPaidAsTitleLevelUptoECIIIOrMoreThanECIV_3103_2956(){
		String titleName = "Paid-as Title";
		String paidAsTitleFromUI = null;
		dsvLsdHomePage.clickViewTitleHistoryLink();
		paidAsTitleFromUI =dsvLsdHomePage.getQualificationTitleFromTitleHistroy(titleName);
		dsvLsdHomePage.navigateToBackPage();
		boolean levelOfConsultant=dsvLsdHomePage.VerifyPaidAsTitleFromUIIsMoreThanOrEqualToLIVEC(paidAsTitleFromUI);
		if(levelOfConsultant){
			s_assert.assertTrue(dsvLsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is not present for title type "+paidAsTitleFromUI);	
		}else{
			s_assert.assertFalse(dsvLsdHomePage.isTrackMyProgramProgressLinkPresent(), "Track my progress link is present for title type "+paidAsTitleFromUI);
		}
		s_assert.assertAll();
	}

	//qTest ID-2876 Consultant Card components
	@Test
	public void testConsultantCardComponents_2876(){
		String subLinkTeam = "Team";
		dsvLsdHomePage.selectSubMenuFromPulseMenu(subLinkTeam);
		s_assert.assertTrue(dsvLsdHomePage.getCurrentURL().contains("team"), "User is not redirecting to team page");
		dsvLsdHomePage.clickFirstSubCategoryOfTeamPage();
		s_assert.assertTrue(dsvLsdHomePage.isFirstAndLastNamePresentAtConsultantCardUnderTeam(), "First and last name is not present at consultant card");
		s_assert.assertTrue(dsvLsdHomePage.isQualificationTitlePresentAtConsultantCardUnderTeam(), "Qualification title is not present at consultant card");
		s_assert.assertTrue(dsvLsdHomePage.isSVPresentAtConsultantCardUnderTeam(), "SV value is not present at consultant card");
		s_assert.assertTrue(dsvLsdHomePage.isPSQVPresentAtConsultantCardUnderTeam(), "PSQV value is not present at consultant card");
		s_assert.assertTrue(dsvLsdHomePage.isRelationPresentAtConsultantCardUnderTeam(), "relation is not present at consultant card");
		s_assert.assertTrue(dsvLsdHomePage.isTwoInitialIconPresentAtConsultantCardUnderTeam(), "Two initial icon is not present at consultant card");
		s_assert.assertTrue(dsvLsdHomePage.isContactIconPresentAtConsultantCardUnderTeam(), "contact icon is not present at consultant card");
		s_assert.assertAll();
	}

	//TC-2448 Customers View subcategories
	@Test
	public void testCustomerViewSubcategories_2448(){
		String currentUrl = null;
		String urlToAssertForCustomerPage = "/#/pcs/all";
		String urlToAssertHaveOrderThisMonth = "/#/pcs/have-orders";
		String urlToAssertScheduledOrderNextMonth = "/#/pcs/will-order";
		String urlToAssertAreFurtherOutPage = "/#/pcs/further-out";
		String urlToAssertNewPCPage = "/#/pcs/new";
		String urlToAssertDeactivatePCPage = "/#/pc-cancellations";
		String subLinkCustomer = "Customers";
		s_assert.assertTrue(dsvLsdHomePage.isHomePageOfPulsePresent(),"HomePage of pulse is not present");
		dsvLsdHomePage.selectSubMenuFromPulseMenu(subLinkCustomer);
		s_assert.assertTrue(dsvLsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present");
		//Click view all customer link.
		dsvLsdHomePage.clickViewAllCustomerLink();
		currentUrl=dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.toLowerCase().contains(urlToAssertForCustomerPage.toLowerCase()),"Current URL : "+currentUrl+" does not contains "+urlToAssertForCustomerPage+"user is not on view customer page");
		dsvLsdHomePage.navigateToBackPage();
		s_assert.assertTrue(dsvLsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present back again");
		//Click Have order this month link
		dsvLsdHomePage.clickHaveOrderThisMonthLink();
		currentUrl=dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.toLowerCase().contains(urlToAssertHaveOrderThisMonth.toLowerCase()),"Current URL : "+currentUrl+" does not contains "+urlToAssertHaveOrderThisMonth+"user is not on have order this month page");
		dsvLsdHomePage.navigateToBackPage();
		s_assert.assertTrue(dsvLsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present from have order this month page");
		//Click scheduled order next month link
		dsvLsdHomePage.clickScheduledOrderNextMonthLink();
		currentUrl=dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.toLowerCase().contains(urlToAssertScheduledOrderNextMonth.toLowerCase()),"Current URL : "+currentUrl+" does not contains "+urlToAssertScheduledOrderNextMonth+"user is not on scheduled order next month page");
		dsvLsdHomePage.navigateToBackPage();
		s_assert.assertTrue(dsvLsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present from scheduled order next month page");
		//Click are further out link.
		dsvLsdHomePage.clickAreFurtherOutLink();
		currentUrl=dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.toLowerCase().contains(urlToAssertAreFurtherOutPage.toLowerCase()),"Current URL : "+currentUrl+" does not contains "+urlToAssertAreFurtherOutPage+"user is not on are further out page");
		dsvLsdHomePage.navigateToBackPage();
		s_assert.assertTrue(dsvLsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present from are further out page");
		//Click new PC link.
		dsvLsdHomePage.clickNewPCLink();
		currentUrl=dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.toLowerCase().contains(urlToAssertNewPCPage.toLowerCase()),"Current URL : "+currentUrl+" does not contains "+urlToAssertNewPCPage+" user is not on new PC page");
		dsvLsdHomePage.navigateToBackPage();
		s_assert.assertTrue(dsvLsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present from new PC page");
		//Click Deactivated PC link.
		dsvLsdHomePage.clickDeactivatedPCLink();
		currentUrl=dsvLsdHomePage.getCurrentURL();
		s_assert.assertTrue(currentUrl.toLowerCase().contains(urlToAssertDeactivatePCPage.toLowerCase()),"Current URL : "+currentUrl+" does not contains "+urlToAssertDeactivatePCPage+"user is not on deactivated PC page");
		dsvLsdHomePage.navigateToBackPage();
		s_assert.assertTrue(dsvLsdHomePage.isCustomerPageOfPulsePresent(),"Customer page of pulse is not present from deactivated PC page");
		s_assert.assertAll();
	}

	//TC-2835 Order Status - Order Results based on Order Filter
	@Test
	public void testOrderStatusOrderResultsBasedOnOrderFilter_2835(){
		String subLinkOrders = "Orders";
		String orderStatus = "Pending";
		boolean isOrderPresent = false;
		//Login with consultant having pulse.
		dsvLsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderStatus);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();

		//Order status Processed
		orderStatus = "Processed";
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderStatus);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();

		//Order status Failed
		orderStatus = "Failed";
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderStatus);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();

		//Order status Partially Shipped
		orderStatus = "Partially";
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderStatus);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();

		//Order status 	Returned
		orderStatus = "Returned";
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderStatus);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();
		s_assert.assertAll();
	}

	//TC-2834 Order Type - Order Results based on Order Filter
	@Test
	public void testOrderTypeOrderResultsBasedOnOrderFilter_2834(){
		String subLinkOrders = "Orders";
		String orderType = "Consultant";
		boolean isOrderPresent = false;
		//Login with consultant having pulse.
		dsvLsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderType);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderTypeFilterAppliedSuccessfullyForConsultant(), "Orders type "+orderType+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();

		//order type Preferred
		orderType = "Preferred";
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderType);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderTypeFilterAppliedSuccessfullyForPC(), "Orders type "+orderType+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();

		//order type Personal
		orderType = "Personal";
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderType);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderTypeFilterAppliedSuccessfullyForPersonal(), "Orders type "+orderType+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();

		//order type 	Retail
		orderType = "Retail";
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderType);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderTypeFilterAppliedSuccessfullyForRetailOrder(), "Orders type "+orderType+" filter is not applied successfully");
		dsvLsdOrderPage.removeAllAppliedFilters();
		s_assert.assertAll();
	}

	//TC-2838 CRP orders - Order Details on Order Cards
	@Test
	public void testVerifyCRPOrderDetailsOnOrderCards_2838(){
		String subLinkOrders = "Orders";
		String orderTypePersonal = "Personal";
		String orderStatusProcessed = "Processed";
		String firstOrderNameFromUI = null;
		String firstOrderTypeFromUI = null;
		String firstOrderStatusFromUI = null;
		String firstOrderSVFromUI = null;
		boolean isOrderPresent = false;
		//Login with consultant having pulse.
		dsvLsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderTypePersonal);
		dsvLsdOrderPage.clickOrderCategoryFilter(orderStatusProcessed);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		firstOrderNameFromUI = dsvLsdOrderPage.getFirstOrderUserName();
		firstOrderStatusFromUI = dsvLsdOrderPage.getOrderStatus(1);
		firstOrderTypeFromUI = dsvLsdOrderPage.getOrderType(1);
		firstOrderSVFromUI = dsvLsdOrderPage.getFirstOrderPSQVValue();
		s_assert.assertTrue(dsvLsdOrderPage.isValueEmpty(firstOrderNameFromUI), "Order name is not present on UI");
		s_assert.assertTrue(dsvLsdOrderPage.isValueEmpty(firstOrderSVFromUI), "Order SV value is not present on UI");
		s_assert.assertTrue(firstOrderStatusFromUI.toLowerCase().contains("processed"), "Expected order status is processed but actual on UI "+firstOrderStatusFromUI);
		s_assert.assertTrue(firstOrderTypeFromUI.toLowerCase().contains("pulse") || firstOrderTypeFromUI.toLowerCase().contains("crp"), "Expected order type is personal but actual on UI "+firstOrderTypeFromUI);
		s_assert.assertTrue(dsvLsdOrderPage.isFirstOrderDatePresent(), "Order date format is not present on UI");
		s_assert.assertAll();
	}

	//TC-2724 Consultant Header section - Processed
	@Test(enabled=false)
	public void testConsultantHeaderSectionProcessed_2724(){
		String subLinkOrders = "Orders";
		boolean isOrderPresent = false;
		String firstNameForCRPAutoShipOrder=null;
		String firstNameForConsultantAdhocOrder=null;
		String firstNameForConsultantSOOOrder=null;
		String firstNameForPulseSubscriptionOrder=null;
		String orderTypeForCRPAutoShipOrder="";
		String orderTypeForConsultantAdhocOrder="";
		String orderTypeForConsultantSOOOrder="";
		String orderTypeForConsultantPulseSubscriptionOrder="";
		String countryCode="";
		String svValueForCRPAutoshipOrder="";
		String svValueForConsultantAdhocOrder="";
		String svValueForConsultantSOOOrder="";
		String svValueForPulseSubscriptionOrder="";
		String psqvValueForConsultantAdhocOrder="";
		String psqvValueForConsultantSOOOrder="";
		String psqvValueForPulseSubscriptionOrder="";
		//Login with consultant having pulse.
		dsvLsdHomePage.selectSubMenuFromPulseMenu(subLinkOrders);
		String orderStatus = "Processed";
		dsvLsdOrderPage.clickOrderFilter();
		dsvLsdOrderPage.clickOrderCategoryFilter(orderStatus);
		dsvLsdOrderPage.clickApplyFilterBtn();
		if(dsvLsdOrderPage.isOrderPresent()==isOrderPresent)
			dsvLsdOrderPage.selectPreviousMonthDateUntilOrderPresent();
		s_assert.assertTrue(dsvLsdOrderPage.isOrderStatusFilterAppliedSuccessfully(orderStatus), "Orders status "+orderStatus+" filter is not applied successfully");
		//Verify details for processed CRP Autoship Order.
		dsvLsdOrderPage.clickFirstProcessedCRPAutoshipOrder();
		s_assert.assertTrue(dsvLsdOrderPage.getFirstNameFromOrderDetailPageHeader().contains(firstNameForCRPAutoShipOrder),"Full name is not present for CRP Autoship Order");
		s_assert.assertTrue(dsvLsdOrderPage.getOrderTypeAndCountryCodeFromOrderDetailPageHeader().contains(orderTypeForCRPAutoShipOrder),"Order type is not present for CRP Autoship Order");
		s_assert.assertTrue(dsvLsdOrderPage.getOrderTypeAndCountryCodeFromOrderDetailPageHeader().contains(countryCode),"country code not present for CRP Autoship Order");
		s_assert.assertTrue(dsvLsdOrderPage.getSVAndPSQVFromOrderDetailPageHeader().contains(svValueForCRPAutoshipOrder),"SV is not present for CRP Autoship Order");
		dsvLsdOrderPage.navigateToBackPage();
		dsvLsdOrderPage.removeAllAppliedFilters();
		//Verify details for processed Consultant Adhoc Order.
		dsvLsdOrderPage.clickFirstProcessedConsultantAdhocOrder();
		s_assert.assertTrue(dsvLsdOrderPage.getFirstNameFromOrderDetailPageHeader().contains(firstNameForConsultantAdhocOrder),"Full name is not present for Consultant adhoc Order");
		s_assert.assertTrue(dsvLsdOrderPage.getOrderTypeAndCountryCodeFromOrderDetailPageHeader().contains(orderTypeForConsultantAdhocOrder),"Order type is not present for Consultant adhoc Order");
		s_assert.assertTrue(dsvLsdOrderPage.getOrderTypeAndCountryCodeFromOrderDetailPageHeader().contains(countryCode),"country code not present for Consultant adhoc Order");
		s_assert.assertTrue(dsvLsdOrderPage.getSVAndPSQVFromOrderDetailPageHeader().contains(svValueForConsultantAdhocOrder),"SV is not present for Consultant adhoc Order");
		s_assert.assertTrue(dsvLsdOrderPage.getSVAndPSQVFromOrderDetailPageHeader().contains(psqvValueForConsultantAdhocOrder),"PSQV is not present for Consultant adhoc Order");
		dsvLsdOrderPage.navigateToBackPage();
		dsvLsdOrderPage.removeAllAppliedFilters();
		//Verify details for processed Consultant SOO Order.
		dsvLsdOrderPage.clickFirstProcessedConsultantSOOOrder();
		s_assert.assertTrue(dsvLsdOrderPage.getFirstNameFromOrderDetailPageHeader().contains(firstNameForConsultantSOOOrder),"Full name is not present for Consultant SOO Order");
		s_assert.assertTrue(dsvLsdOrderPage.getOrderTypeAndCountryCodeFromOrderDetailPageHeader().contains(orderTypeForConsultantSOOOrder),"Order type is not present for Consultant SOO Order");
		s_assert.assertTrue(dsvLsdOrderPage.getOrderTypeAndCountryCodeFromOrderDetailPageHeader().contains(countryCode),"country code not present for Consultant SOO Order");
		s_assert.assertTrue(dsvLsdOrderPage.getSVAndPSQVFromOrderDetailPageHeader().contains(svValueForConsultantSOOOrder),"SV is not present for Consultant SOO Order");
		s_assert.assertTrue(dsvLsdOrderPage.getSVAndPSQVFromOrderDetailPageHeader().contains(psqvValueForConsultantSOOOrder),"PSQV is not present for Consultant SOO Order");
		dsvLsdOrderPage.navigateToBackPage();
		dsvLsdOrderPage.removeAllAppliedFilters();	
		//Verify details for processed pulse subscription Order.
		dsvLsdOrderPage.clickFirstProcessedPulseSubscriptionOrder();
		s_assert.assertTrue(dsvLsdOrderPage.getFirstNameFromOrderDetailPageHeader().contains(firstNameForPulseSubscriptionOrder),"Full name is not present for Pulse subscription Order");
		s_assert.assertTrue(dsvLsdOrderPage.getOrderTypeAndCountryCodeFromOrderDetailPageHeader().contains(orderTypeForConsultantPulseSubscriptionOrder),"Order type is not present for Pulse subscription Order");
		s_assert.assertTrue(dsvLsdOrderPage.getOrderTypeAndCountryCodeFromOrderDetailPageHeader().contains(countryCode),"country code not present for Pulse subscription Order");
		s_assert.assertTrue(dsvLsdOrderPage.getSVAndPSQVFromOrderDetailPageHeader().contains(svValueForPulseSubscriptionOrder),"SV is not present for Pulse subscription Order");
		s_assert.assertTrue(dsvLsdOrderPage.getSVAndPSQVFromOrderDetailPageHeader().contains(psqvValueForPulseSubscriptionOrder),"PSQV is not present for Pulse subscription Order");
		dsvLsdOrderPage.navigateToBackPage();
		dsvLsdOrderPage.removeAllAppliedFilters();
		s_assert.assertAll();
	}


}
