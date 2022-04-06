package com.rf.test.website.storeFront.CommissionsUI;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rf.core.utils.SoftAssert;
import com.rf.pages.website.commissionsUI.CommissionsUIPage;

public class CommissionsUIOverrideTest extends CommissionsUIWebsiteBaseTest{
	CommissionsUIPage commissionsUIpage ;

	@BeforeClass
	public void resetToHomePage(){
		commissionsUIpage = new 	CommissionsUIPage(driver);
		s_assert = new SoftAssert();
		driver.get(driver.getURL());
		commissionsUIpage.clickSponsorChangeButton();
	}

	@Test(dataProvider="rfTestData")
	public void testCommissionsUIOverride(String downlineCID, String newSponosrCID){
		System.out.println("downlineCID="+downlineCID);
		System.out.println("newSponosrCID="+newSponosrCID);
		commissionsUIpage.enterDownlineCIDAndclickSearch(downlineCID);
		commissionsUIpage.selectOverrideDropDownValues(1, "Placement", "Consultant Request");
		commissionsUIpage.enterSponsorNewCIDAndclickSearch(newSponosrCID);
		commissionsUIpage.clickSubmitButton();
		s_assert.assertTrue(commissionsUIpage.isSuccessMsgDisplayed(), "Success msg is NOT displyed for Placement sponsorType");
		commissionsUIpage.selectOverrideDropDownValues(1, "Enrollment", "Consultant Request");
		commissionsUIpage.clickSubmitButton();
		s_assert.assertTrue(commissionsUIpage.isSuccessMsgDisplayed(), "Success msg is NOT displyed for Enrollment sponsorType");
		s_assert.assertTrue(commissionsUIpage.isOverrideHistoryHasExpectedValues(downlineCID, "Placement", newSponosrCID),"Override history doesn't have the value for placement override for downlineCID "+downlineCID);
		s_assert.assertTrue(commissionsUIpage.isOverrideHistoryHasExpectedValues(downlineCID, "Enrollment", newSponosrCID),"Override history doesn't have the value for enrollment override for downlineCID "+downlineCID);
		s_assert.assertAll();	
	}


	@AfterClass
	public void tearDown(){
		driver.quit();
	}
}	
