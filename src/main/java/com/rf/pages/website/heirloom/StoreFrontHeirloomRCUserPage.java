package com.rf.pages.website.heirloom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.rf.core.driver.website.RFWebsiteDriver;

public class StoreFrontHeirloomRCUserPage extends StoreFrontHeirloomWebsiteBasePage{
	private static final Logger logger = LogManager
			.getLogger(StoreFrontHeirloomRCUserPage.class.getName());

	public StoreFrontHeirloomRCUserPage(RFWebsiteDriver driver) {
		super(driver);
	}
	
}