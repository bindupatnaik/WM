package com.rf.pages.website.nscore;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.rf.core.driver.website.RFWebsiteDriver;

public class NSCore4HomePage extends NSCore4RFWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(NSCore4HomePage.class.getName());
	int allBillingProfilesSize = 0;

	public NSCore4HomePage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static String customerLabelNameLoc="//div[@class='CustomerLabel']//a[contains(text(),'%s')]";
	private static String orderIdLinkLoc = "//a[contains(text(),'%s')]";
	private static String productOnOrderTableOnOrderPage = "//table[@id='products']//td[contains(text(),'%s')]";
	private static String productOnOrderTableOnOrderDetailPage = "//table[@class='DataGrid']//td[contains(text(),'%s')]";
	private static String proxyLinksLoc = "//div[@class='DistributorProxies']//li/a[text()='%s']";
	private static String waitingForApprovalLink = "//ul[@id='stories']/li/a[contains(text(),'%s')]";
	private static String storyNameOnEditOptionPage = "//p[text()='%s']";
	private static String selectCategoryOnAddNotePopup = "//select[@id='newNoteCategory']/option[text()='%s']";
	private static String selectTypeOnAddNotePopup = "//select[@id='newNoteType']/option[text()='%s']";
	private static String newlyCreatedNoteLoc = "//div[@id='notesPanel']/div[text()='%s']";
	private static String postFollowUpLinkOfParent = "//div[@id='notesPanel']/div[text()='%s']/span/a[text()='Post Follow-up']";
	private static String newlyCreatedChildNoteLoc = "//div[@id='notesPanel']/div[text()='%s']/div[@class='ChildNotes']/div[text()='%s']";
	private static String postFollowUpChildLink = "//div[@id='notesPanel']/div[text()='%s']/div[@class='ChildNotes']/div[text()='%s']/span/a[text()='Post Follow-up']";
	private static String collapseLinkNextToParentNote = "//div[@id='notesPanel']/div[text()='%s']/span/a[text()='Collapse']";
	private static String expandLinkNextToParentNote = "//div[@id='notesPanel']/div[text()='%s']/span/a[text()='Expand']";
	private static String childNoteDetailsOnUI = "//div[@id='notesPanel']/div[text()='%s']/div[@class='ChildNotes' and @style='display: block;']";
	private static String completedDateOfOrder = "//table[@id='orders']/tbody/tr[%s]/td[4]";
	private static String shippedDateOfOrder = "//table[@id='orders']/tbody/tr[%s]/td[5]";
	private static String orderNumber = "//table[@id='orders']/tbody/tr[%s]/td[1]/a";
	private static String genderDDValue ="//select[@id='gender']/option[contains(text(),'%s')]";
	private static String newlyCeatedShippingProfileSetDefault = "//div[@id='ContentWrap']//table//a[contains(text(),'%s')]/ancestor::div[1]//a[contains(text(),'Set As Default Address')]";
	private static String newlyCeatedShippingProfileIsDefault = "//div[@id='ContentWrap']//table//a[contains(text(),'%s')]/ancestor::div[1]//span[contains(text(),'default')]";
	private static String deleteAddressnewlyCeatedShippingProfile = "//div[@id='ContentWrap']//table//a[contains(text(),'%s')]/preceding::a[contains(text(),'Delete Address')][1]";
	private static String overViewSublinkLoc = "//div[@class='Overview']//a[contains(text(),'%s')]";
	private static String setAsDefaultForNewlyCreatedBillingProfile = "//div[@id='paymentMethods']/descendant::div[contains(@class,'Profile')][%s]//a[contains(text(),'Set As Default Payment Method')]";
	private static String isDefaultPresentForNewlyCreatedBillingProfile = "//div[@id='paymentMethods']/descendant::div[contains(@class,'Profile')][%s]//span[contains(text(),'default')]";
	private static String deleteNewlyCreatedBillingProfile = "//div[@id='paymentMethods']/descendant::div[contains(@class,'Profile')][%s]//a[contains(text(),'Delete Payment Method')]";
	private static String productQVBasedOnSKULoc="//table[@class='FormTable Section']/descendant::td[text()='%s']/following::td[6]";
	private static String skuBasedProductQVSOOPopupLoc="//table[@id='overrideProducts']//following::td[text()='ENPS050']/following::input[3]";
	private static String editBillingProfileLINK="//a[contains(text(),'%s')]";
	private static String newlyCeatedShippingProfile = "//*[@class='shippingAddressDisplay' and @style='display: block;']/b[contains(text(),'%s')]";

	private static final By ERROR_MESSAGE_LOC=By.xpath("//*[contains(text(),\"We're sorry, but we are not able to validate the card number you entered.\")]");
	private static final By PENDING_ORDER_NUMBER_LOC=By.xpath("//div[@class='ui-grid-canvas']//div[text()='Pending']/preceding::a[1]");
	private static final By EDIT_ORDER_LOC = By.id("editOrder");
	private static final By ITEMS_PER_PAGE_LOC=By.xpath("//span[contains(text(),'items per page')]/preceding::select[1]");
	private static final By LAST_NAME_ORDERS_PAGE=By.id("input_1");
	private static final By FIRST_NAME_ORDERS_PAGE=By.id("input_0");
	private static final By ORDERS_SEARCH_RESULTS = By.xpath("//span[text()='Order Number']/following::div[@class='ng-isolate-scope']");
	private static final By ORDER_ID_FROM_OVERVIEW_PAGE_LOC = By.xpath("//div[contains(@class,'ui-grid-canvas')]/descendant::a[1] | table[@id='orders']/tbody/tr[1]/td[1]/a");
	private static final By SHIPPING_PROFILE_NAME_LOC=By.xpath("//div[@id='shippingAddressContainer']//b");
	private static final By PAYMENT_PROFILE_NAME_LOC=By.xpath("//*[@id='paymentMethodContainer']/div[contains(@style,'block')]/b");
	private static final By PAYMENT_PROFILE_CARD_NAME_LOC=By.xpath("//*[@id='paymentMethodContainer']/div[contains(@style,'block')]");
	private static final By EDIT_PAYMENT_PROFILE=By.xpath("//div[@id='paymentMethodContainer']//a[contains(text(),'Edit')]");
	private static final By PRODUCTS_TAB_LOC=By.xpath("//ul[@id='GlobalNav']//span[text()='Products']");
	private static final By PRODUCTS_MANAGEMENT_TAB_LOC=By.xpath(".//a[contains(text(),'Product')]");
	private static final By PRICING_TAB_LOC=By.xpath(".//*[@id='ContentWrap']//span[contains(text(),'Pricing')]");
	private static final By CMS_TAB_LOC=By.xpath(".//*[@id='ContentWrap']//span[contains(text(),'CMS')]");
	private static final By CATEGORIES_TAB_LOC=By.xpath(".//*[@id='ContentWrap']//span[contains(text(),'Categories')]");
	private static final By RELATIONSHIPS_TAB_LOC=By.xpath(".//*[@id='ContentWrap']//span[contains(text(),'Relationships')]");
	private static final By AVAILABILITY_TAB_LOC=By.xpath(".//*[@id='ContentWrap']//span[contains(text(),'Availability')]");
	private static final By PRODUCT_PROPERTIES_TAB_LOC=By.xpath(".//*[@id='ContentWrap']//span[contains(text(),'Product Properties')]");
	private static final By DETAILS_TAB_LOC=By.xpath(".//*[@id='ContentWrap']//span[contains(text(),'Details')]");
	private static final By SAVE_BUTTON_DETAILS_TAB_LOC=By.id("btnSave");
	private static final By SAVE_PRODUCT_DESCRIPTION_LOC=By.id("btnSaveDescriptions");
	private static final By ACCOUNTS_TAB_LOC=By.xpath("//ul[@id='GlobalNav']//span[text()='Accounts']");
	private static final By SEARCH_ICON_OREDRS_TAB_LOC=By.xpath(".//*[@id='searchIcon']");
	private static final By PULSE_DISMISS_BUTTON_LOC=By.xpath("//span[contains(text(),'Dismiss')]");
	private static final By RENEW_LATER_LINK = By.xpath("//a[@id='renewLater']");
	private static final By EDIT_PULSE_TEMPLATE_LOC=By.xpath("//li[contains(text(),'Pulse Monthly Subscription')]//a[contains(text(),'Edit')]");
	private static final By ADD_SHIPPING_PROFILE_ADD_LINK_LOC=By.xpath("//a[contains(text(),'Add New Shipping Address')]");
	private static final By SUBSCRIPTION_STATUS_LOC  = By.xpath("//td[contains(text(),'Subscription Status')]/following::td[1]");
	private static final By SAVE_CHANGES_BTN_LOC = By.xpath("//a[@id='btnSaveChanges']");
	private static final By PULSE_EMAIL_LOC = By.id("PulseEmailAddress");
	private static final By PAYABLE_TO_LOC = By.id("txtPayableTo");
	private static final By USE_ADDRESS_OF_RECORD_CHK_BOX_LOC = By.id("chkUseAddressOfRecord");
	private static final By ADDRESS_1_FOR_DISBURSEMENT_PROFILE_LOC = By.id("txtAddressLine1");
	private static final By CITY_FOR_DISBURSEMENT_PROFILE_LOC = By.id("txtCity");
	private static final By STATE_FOR_DISBURSEMENT_PROFILE_LOC = By.id("txtState");
	private static final By POSTAL_CODE_FOR_DISBURSEMENT_PROFILE_LOC = By.id("txtZip");
	private static final By SAVE_CHANGES_BTN_FOR_DISBURSEMENT_PROFILE_LOC = By.id("btnSave");
	private static final By GO_SEARCH_BTN = By.xpath("//button[contains(@title,'Search Accounts')]");
	private static final By ACCOUNT_SEARCH_RESULTS = By.xpath("//span[text()='Account Number']/following::div[@class='ng-isolate-scope']");
	private static final By EDIT_MY_STORY_LINK = By.xpath("//a[@class='EditButton' and contains(text(),'Edit My Story')]");
	private static final By I_WANT_TO_WRITE_OWN_STORY = By.xpath("//a[@id='newStory']/span");
	private static final By STORY_TITLE_LOC = By.id("myStoryName");
	private static final By OUTER_FRAME_ID = By.id("myStoryEditor___Frame");
	private static final By INNER_FRAME_ID = By.xpath("//td[@id='xEditingArea']/iframe");
	private static final By STORY_TEXT_LOC = By.xpath("//html/body");
	private static final By SAVE_STORY_BUTTON = By.id("save");
	private static final By LOGOUT_LINK = By.xpath("//a[contains(text(),'Logout')]") ;
	private static final By CONSULTANT_REPLENISHMENT_EDIT = By.xpath("//li[contains(text(),'Consultant Replenishment')]/descendant::a[text()='Edit']");
	private static final By PULSE_MONTHLY_SUBSCRIPTION_EDIT = By.xpath("//li[contains(text(),'Pulse Monthly Subscription')]/descendant::a[text()='Edit']");
	private static final By PC_BI_MONTHLY_REPLNISHMENT_DELAY = By.xpath("//td[contains(@id,'accountInfo')]/descendant::a[text()='Delay']");
	private static final By PC_BI_MONTHLY_REPLNISHMENT_EDIT = By.xpath("//td[contains(@id,'accountInfo')]/descendant::a[text()='Edit']");
	private static final By PULSE_PRODUCT_QUANTITY_TXT_FIELD = By.xpath("//input[@class='quantity']");
	private static final By SKU_SEARCH_FIELD = By.id("txtQuickAddSearch");
	private static final By SKU_SEARCH_FIRST_RESULT = By.xpath("//div[contains(@class,'jsonSuggestResults')][1]//p[1]");
	private static final By CUSTOMER_LABEL_ORDER_DETAIL_PAGE = By.xpath("//div[@class='CustomerLabel']/a");
	private static final By AUTOSHIP_PRODUCT_QUANTITY_TXT_FIELD  = By.id("txtQuickAddQuantity");
	private static final By AUTOSHIP_PRODUCT_QUANTITY_ADD_BTN  = By.id("btnQuickAdd");
	private static final By UPDATE_PULSE_CART_BTN  = By.id("btnUpdateCart");
	private static final By SAVE_TEMPLATE_BTN  = By.id("btnSaveAutoship");
	private static final By QUANTITY_OF_PULSE_PRODUCT_ON_ORDER_DETAIL_PAGE  = By.xpath("//tr[@class='GridRow']/td[4]");
	private static final By MOBILE_TAB_LOC  = By.xpath("//ul[@id='GlobalNav']//span[text()='Mobile']");
	private static final By ORDERS_TAB_LOC = By.xpath("//ul[@id='GlobalNav']//span[text()='Orders']");
	private static final By ADMIN_TAB_LOC  = By.xpath("//ul[@id='GlobalNav']//span[text()='Admin']");
	private static final By RECENT_ORDER_DROP_DOWN_LOC = By.id("orderStatusFilter");
	private static final By NO_ORDER_FOUND_MSG_LOC = By.xpath("//table[@id='orders']//td[contains(text(),'No orders found')]");
	private static final By POLICIES_CHANGE_HISTORY_LINK_LOC = By.xpath("//a[contains(text(),'Policies Change History')]");
	private static final By VERSION_COLUMN_LOC = By.xpath("//th[text()='Version']");
	private static final By DATERELEASED_COLUMN_LOC = By.xpath("//th[text()='Date Released']");
	private static final By DATEACCEPTED_COLUMN_LOC = By.xpath("//th[text()='Date Accepted']");
	private static final By STATUS_HISTORY_LINK_LOC = By.xpath("//a[text()='Status History']");
	private static final By CHANGED_ON_COLUMN_LOC = By.xpath("//th[text()='Changed On']");
	private static final By STATUS_COLUMN_LOC = By.xpath("//th[text()='Status']");
	private static final By REASON_COLUMN_LOC = By.xpath("//th[text()='Reason']");
	private static final By POST_NEW_NODE_LINK  = By.xpath("//a[text()='Post New Note']");
	private static final By CATEGORY_DROPDOWN_ON_ADD_NOTE_POPUP  = By.id("newNoteCategory");
	private static final By TYPE_DROPDOWN_ON_ADD_NOTE_POPUP  = By.id("newNoteType");
	private static final By ENTER_NOTE_ON_ADD_NOTE_POPUP  = By.id("newNoteText");
	private static final By SAVE_BTN_ON_ADD_A_NOTE_POPUP  = By.id("btnSaveNote");
	private static final By PLACE_NEW_ORDER_LINK_LOC = By.xpath("//a[text()='Place New Order']");
	private static final By STATUS_LINK_LOC = By.xpath("//td[contains(text(),'Status:')]/following-sibling::td/a");
	private static final By CHANGE_STATUS_DD_LOC = By.xpath("//select[@id='sStatus']");
	private static final By SAVE_STATUS_BTN_LOC = By.xpath("//a[@id='btnSaveStatus']");
	private static final By VIEW_ORDER_CONSULTANT_REPLENISHMENT = By.xpath("//li[contains(text(),'Consultant Replenishment')]//a[text()='View Orders']");
	private static final By VIEW_ORDER_PULSE_MONTHLY_SUBSCRIPTION = By.xpath("//li[contains(text(),'Pulse Monthly Subscription')]//a[text()='View Orders']");
	private static final By START_DATE_OF_DATE_RANGE = By.id("txtStartDate");
	private static final By ORDER_SEARCH_RESULTS = By.xpath("//table[@id='orders']/tbody/tr");
	private static final By ORDER_NUMBER_FROM_ORDER_DETAILS = By.xpath("//div[@class='CustomerLabel']/a");
	private static final By FULL_ACCOUNT_RECORD_LINK = By.xpath("//span[contains(text(),'Full Account Record')]");
	private static final By APPLICATION_ON_FILE_CHKBOX = By.id("chkApplicationOnFile");
	private static final By CRP_START_DATE = By.id("crpStartDate");
	private static final By USERNAME_LOC = By.id("txtUsername");
	private static final By FIRST_NAME = By.id("txtFirstName");
	private static final By LAST_FOUR_DIGIT_OF_HOME_PHONE_NUMBER = By.id("txtHomePhoneLastFour");
	private static final By EMAIL_ADDRESS_LOC = By.id("txtEmail");
	private static final By TAX_EXEMPT_VALUE = By.xpath("//select[@id='isTaxExempt']/option[@selected='selected']");
	private static final By TAX_EXEMPT_DD = By.id("isTaxExempt");
	private static final By TAX_EXEMPT_DD_YES_VALUE = By.xpath("//select[@id='isTaxExempt']/option[contains(text(),'Yes')]");
	private static final By TAX_EXEMPT_DD_NO_VALUE = By.xpath("//select[@id='isTaxExempt']/option[contains(text(),'No')]");
	private static final By DOB_LOC = By.id("txtDOB");
	private static final By NAME_ON_SSN_CARD = By.id("txtLegalName");
	private static final By GENDER_DD = By.id("gender");
	private static final By GENDER_DD_SELECTED_VALUE = By.xpath("//select[@id='gender']/option[@selected='selected']");
	private static final By ATTENTION_NAME = By.id("txtAttention");
	private static final By ZIP_CODE = By.id("txtPostalCode");
	private static final By LAST_FOUR_DIGIT_OF_PHONE_NUMBER = By.xpath("//*[contains(@id,'LastFour')]");
	private static final By SAVE_ACCOUNT_BTN = By.id("btnSaveAccount");
	private static final By USE_AS_ENTERED_BTN = By.xpath("//span[contains(text(),'Use as entered')]/..[@aria-disabled='false']");
	private static final By ACCEPT_BTN = By.xpath("//span[contains(text(),'Accept')]");
	private static final By GET_UPDATION_MSG = By.xpath("//div[@id='messageCenter']/div[contains(@id,'message')]");
	private static final By USE_ADDRESS_AS_ENTERED = By.id("QAS_AcceptOriginal");
	private static final By BILLING_AND_SHIPPING_PROFILE_LINK_LOC = By.xpath("//span[text()='Billing & Shipping Profiles']");
	private static final By SHIPPING_PROFILE_ADD_LINK_LOC = By.xpath("//a[@id='btnAddShippingAddress']");
	private static final By ADD_SHIPPING_ADDRESS_PROFILE_NAME_LOC = By.xpath("//input[@id='profileName']");
	private static final By ADD_SHIPPING_ADDRESS_ATTENTION_LOC = By.xpath("//input[@id='attention']");
	private static final By ADD_SHIPPING_ADDRESS_LINE1_LOC = By.xpath("//input[@id='addressLine1']");
	private static final By ADD_SHIPPING_ADDRESS_ZIPCODE_LOC = By.xpath("//input[@id='zip']");
	private static final By STATE_DD_LOC  = By.id("state");
	private static final By STATE_DD_OPTION_LOC  = By.xpath("//select[@id='state']/option[2]");
	private static final By BILLING_PROFILE_ADD_LINK_LOC = By.xpath("//a[@id='btnAddBillingAddress']");
	private static final By ADD_NEW_BILLING_ADDRESS_DROP_DOWN_LOC = By.id("existingAddress");
	private static final By ADD_PAYMENT_METHOD_FIRST_NAME_LOC = By.xpath("//input[@id='uxAttentionFirstName']");
	private static final By ADD_PAYMENT_METHOD_LAST_NAME_LOC = By.xpath("//input[@id='uxAttentionLastName']");
	private static final By ADD_PAYMENT_METHOD_NAME_ON_CARD_LOC = By.xpath("//input[@id='nameOnCard']");
	private static final By ADD_PAYMENT_METHOD_CREDIT_CARD_NO_LOC = By.xpath("//input[@id='accountNumber']");
	private static final By ADD_NEW_PAYMENT_EXPIRATION_YEAR_DROP_DOWN_LOC = By.id("expYear");
	private static final By SAVE_PAYMENT_METHOD_BTN_LOC = By.xpath("//a[@id='btnSavePaymentMethod']");
	private static final By USE_AS_ENTERED_BTN_LOC = By.xpath("//*[@id='QAS_AcceptOriginal']");
	private static final By ACCEPT_BTN_LOC = By.xpath("//button/span[text()='Accept']");
	private static final By NEWLY_CREATED_BILLING_PROFILE_LOC = By.xpath("//div[@id='ContentWrap']//table//a[contains(text(),'Main Billing')]");
	private static final By PLACED_ORDER_NUMBER  = By.xpath("//div[@class='Content']//a[contains(text(),'Order #')]");
	private static final By OPEN_BULK_ADD_LOC  = By.id("btnOpenBulkAdd");
	private static final By SKU_FIELD_SEARCH  = By.xpath("//*[@id='txtQuickAddSearch']");
	private static final By PRODUCT_QUANTITY_LOC  = By.xpath("//table[@id='bulkProductCatalog']//tr[1]//input[@class='quantity']");
	private static final By PRODUCT_SKU_VALUE  = By.xpath("//table[@id='bulkProductCatalog']/tbody//tr[1]/td[1]");
	private static final By ADD_TO_ORDER_BTN  = By.id("btnBulkAdd");
	private static final By CLOSE_LINK_LOC  = By.xpath("//div[@id='bulkAddModal']//a[text()='Close']");
	private static final By TOTAL_BILLING_PROFILES = By.xpath("//div[@id='paymentMethods']/div[contains(@class,'Profile')]");
	private static final By SHIPPING_PROFILES_LOC  = By.xpath("//div[@id='addresses']/div");
	private static final By PRODUCT_SKU_VALUE_CATALOG  = By.xpath("//table[@id='productBulkAddGrid']//tr[3]/td[2]");
	private static final By PRODUCT_SKU_CHK_BOX_CATALOG  = By.xpath("//table[@id='productBulkAddGrid']//tr[3]/td[2]");
	private static final By ADD_TO_CATALOG  = By.id("btnBulkAddProducts");
	private static final By CLOSE_LINK_CATALOG_LOC  = By.xpath("//div[@id='productBulkAdd']//a[text()='Close']");
	private static final By ADD_NEW_PAYMENT_METHOD_LINK  = By.xpath("//a[contains(text(),'Add New Payment Method')]");
	private static final By EMAIL_ADDRESS_IN_ACCOUNT_TAB_LOC=By.xpath("//label[contains(text(),'Email')]/following::input[1]");
	private static final By EDIT_BILLING_PROFILE_LOC=By.xpath(".//*[@class='PaymentSelector']/descendant::a[contains(text(),'Edit')]");
	private static final By CREDIT_CARD_LOC=By.id("accountNumber");
	private static final By CVV_NUMBER_LOC=By.id("cvvNumber");
	private static final By SAVE_PAYMENT_METHOD_BUTTON_LOC=By.id("btnSavePaymentMethod");
	private static final By ACCEPT_QAS_POPUP_LOC=By.xpath("//span[text()='Accept']");
	private static final By EDIT_EXISTING_PAYMENT_DETAILS_LOC=By.xpath("//div[contains(@id,'paymentMethodContainer')]//a[contains(text(),'Edit')]");
	private static final By FIRST_NAME_BROWSE_PAGE_LOC  = By.id("input_5");
	private static final By LAST_NAME_BROWSE_PAGE_LOC  = By.id("input_6");
	private static final By PULSE_STATUS_DD_LOC  = By.id("sOrderStatus");
	private static String pulseOrCRPStatusOnTemplateLoc  = ".//*[@id='sOrderStatus']/option[@selected='selected' and contains(text(),'%s')]";
	private static final By EDIT_CRP_TEMPLATE_LOC=By.xpath("//li[contains(text(),'Consultant Replenishment')]/descendant::a[text()='Edit']");
	private static final By ACCOUNT_SEARCH_TXTFIELD = By.xpath("//input[@id='input_4'] | label[text()='Account Number']/following::input[1]");
	private static final By ORDER_STATUS_TEXTBOX_LOC  = By.xpath("//span[text()='Order Status']/following::div[@class='ng-scope'][1]/input");
	private static final By FIRST_PENDING_ORDER_LOC = By.xpath("//div[@class='ui-grid-canvas']/descendant::div[text()='Pending'][1]/preceding::a[1] | table[@id='orders']/tbody/tr[1]/td/a");
	private static final By LOCATION_DD_LOC=By.xpath("//label[contains(text(),'Type')]/following::md-select[2]");
	private static final By PAYMENT_PROFILE_SECOND_LOC=By.xpath("//*[@id='paymentMethodContainer']/div[2]/b");
	private static final By PAYMENT_PROFILE_CARD_NAME_SECOND_LOC=By.xpath("//*[@id='paymentMethodContainer']/div[2]");
	private static final By CREATE_CRP_TEMPLATE_LOC=By.xpath("//li[contains(text(),'Consultant Replenishment')]/descendant::a[text()='Create']");
	private static final By CREATE_PULSE_TEMPLATE_LOC=By.xpath("//li[contains(text(),'Pulse Monthly Subscription')]/descendant::a[text()='Create']");
	private static final By EDIT_PC_AUTOSHIP_TEMPLATE_LOC=By.xpath("//li[contains(text(),'PC Bi-Monthly Replenishment')]/descendant::a[text()='Edit']");
	private static final By BROWSE_ACCOUNT_LOC=By.xpath("//span[text()='Browse Accounts']");
	private static final By ROWS_COUNT_OF_SEARCH_RESULT  = By.xpath("//div[@class='ui-grid-canvas']/div | span[text()='Account Number']/following::div[@class='ng-isolate-scope'] | //td[contains(text(),'Products')]/following-sibling::td//table[@class='DataGrid']/tbody[2]/tr");
	private static final By REMOVE_DONATION_PRODUCT_LINK_LOC=By.xpath("//table[@id='products']/descendant::td[contains(text(),'Prescription For Change Donation')]/preceding::a[@title='Remove'][1]");	
	private static final By COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC=By.xpath("//input[@id='commissionDate']");
	private static final By PULSE_LINK_OVERVIEW_PAGE_LOC=By.xpath("//a[text()='Pulse']");
	private static final By ORDERS_TAB_ON_PULSE_HOMEPAGE_LOC=By.xpath("//span[contains(text(),'Orders')]");
	private static final By ORDER_PERIOD_ON_PULSE_ORDER_TAB_LOC=By.id("PeriodId");
	private static final By RUN_REPORT_ON_PULSE_LOC=By.xpath("//span[contains(text(),'Run Report')]");
	private static final By TAX_FROM_PULSE_ORDER_LOC=By.xpath("//label[contains(text(),'Taxes')]/following::div[1]");
	private static final By SHIPPING_ON_PULSE_ORDER_LOC=By.xpath("//label[contains(text(),'Shipping')]/following::div[1]");
	private static final By ORDER_TOTAL_ON_PULSE_LOC=By.xpath("//label[contains(text(),'Order Total')]/following::div[1]");
	private static final By TOTAL_ORDERS_PRESENT_LOC = By.xpath("//div[@class='ui-grid-canvas']/div");
	private static final By SITE_SUBSCRIPTION_LINK_LOC = By.xpath("//*[contains(text(),'Site Subscriptions')]");
	private static final By OVERVIEW_LINK_LOC=By.xpath("//span[contains(text(),'Overview')]");
	private static final By AUTOSHIP_CANCELLED_STATUS_LOC=By.xpath("//td[contains(text(),'Autoship')]/following::td[contains(text(),'Cancelled')]");
	private static final By REACTIVATE_LINK_LOC=By.xpath("//a[contains(text(),'Re-Activate')]");
	private static final By AUTOSHIP_ACTIVE_STATUS_LOC=By.xpath("//td[contains(text(),'Autoship')]/following::td[contains(text(),'Active')]");
	private static final By MY_ACCOUNT_DROPDOWN_LOC=By.xpath("//a[@id='myaccountdropdownlink']");
	private static final By LOGOUT_LINK_IN_MY_ACCOUNT_DROPDOWN_LOC=By.xpath("//a[text()='Log Out']");
	protected static final By MY_ACCOUNT_DROPDOWN_LINK_LOC=By.id("myaccountdropdownlink");
	private static String myAccountLinkAfterLoginLink = "//div[@id='menuDiv']//span[text()='%s']/..|//div[@id='accountDiv']/descendant::a[text()='My Account'] | //*[contains(text(),'%s')]";	
	private static String bizComUrl = "//td[contains(text(),'Website URL')]/following::input[contains(@class,'domain')][%s]";
	private static String radioButtonForPCAutoshipDelay = "//input[@id='DelayTypeId'][@value='%s']";
	private static String nextDateStringForPCAutoshipDelay = "//input[@id='DelayTypeId'][@value='%s']/following::label[1]";
	private static String PendingOrderLoc ="//div[@class='ui-grid-canvas']/descendant::div[text()='Pending'][%s]/preceding::a[1] | table[@id='orders']/tbody/tr[1]/td/a";
	private static String monthNameOnOrderPeriodLoc="//option[contains(text(),'%s')]";
	private static String orderDetailsOnPulseLoc="//a[contains(@id,'%s')]";	
	private static String productPriceBasedOnSKULoc="//table[@class='FormTable Section']/descendant::td[text()='%s']/following::td[2]";
	private static String newlyAddedSKUOnCRPTemplate="//td[contains(text(),'%s')]";
	private static String locationOptionLoc="//*[contains(@value,'%s')]/div[1]";
	private static String firstNameSearchResultLoc="//a[contains(text(),'%s')]/following::div[1]";
	private static String lastNameSearchResultLoc="//a[contains(text(),'%s')]/following::div[3]";
	private static String pulseStatusLoc = "//select[@id='sOrderStatus']/option[text()='%s']";	
	private static String rowNumber = "//div[@class='ui-grid-canvas']/descendant::a[%s] | span[text()='Account Number']/following::div[@class='ng-isolate-scope'][%s]//a";
	private static String accountRecordOnBasisOfAccountNumberLoc="//a[contains(text(),'%s')]";
	private static String ConsultantIdOnBrowseAccountsPage="//a[contains(text(),'%s')]";
	private static String ProductNameOnOrderDetailsPage=".//*[@class='DataGrid']//td[contains(text(),'%s')]";
	private static String OrderIdSearchResultOverviewPage="//a[contains(text(),'%s')]";
	private static String linkUnderProductsManagementTab = ".//*[@id='SubNav']//a/span[text()='%s']";

	public boolean isLogoutLinkPresent(){
		driver.waitForElementPresent(LOGOUT_LINK);
		return driver.isElementPresent(LOGOUT_LINK);
	}

	public NSCore4LoginPage clickLogoutLink(){
		driver.quickWaitForElementPresent(LOGOUT_LINK);
		driver.click(LOGOUT_LINK,"");
		logger.info("Logout link clicked");
		driver.waitForPageLoad();	
		return new NSCore4LoginPage(driver);
	}

	public void enterAccountNumberInAccountSearchField(String accountinfo){
		driver.type(ACCOUNT_SEARCH_TXTFIELD, accountinfo,"account search field");
	}

	public void clickConsultantReplenishmentEdit(){
		driver.waitForElementPresent(CONSULTANT_REPLENISHMENT_EDIT);
		driver.click(CONSULTANT_REPLENISHMENT_EDIT,"");
		logger.info("consultant Replenishment edit clicked");
		driver.waitForPageLoad();
	}

	public String updatePulseProductQuantityAndReturnValue(){
		int quantity = Integer.valueOf(driver.findElement(PULSE_PRODUCT_QUANTITY_TXT_FIELD).getAttribute("value"));;
		quantity = quantity +1;
		driver.findElement(PULSE_PRODUCT_QUANTITY_TXT_FIELD).clear();
		driver.findElement(PULSE_PRODUCT_QUANTITY_TXT_FIELD).sendKeys(String.valueOf(quantity));
		driver.click(UPDATE_PULSE_CART_BTN,"");
		logger.info("update cart button on pulse clicked");
		return String.valueOf(quantity);
	}

	public void enterSKUValue(String sku){
		driver.get(driver.getCurrentUrl());
		driver.waitForPageLoad();
		driver.quickWaitForElementPresent(SKU_SEARCH_FIELD);
		driver.type(SKU_SEARCH_FIELD, sku);
		logger.info("sku value entered is "+sku);
	}

	public void clickFirstSKUSearchResultOfAutoSuggestion(){
		driver.waitForElementPresent(SKU_SEARCH_FIRST_RESULT);
		driver.click(SKU_SEARCH_FIRST_RESULT,"");	
		logger.info("sku first result clicked");
	}

	public void enterProductQuantityAndAddToOrder(String quantity){
		driver.type(AUTOSHIP_PRODUCT_QUANTITY_TXT_FIELD, quantity);
		driver.click(AUTOSHIP_PRODUCT_QUANTITY_ADD_BTN,"");
		logger.info("autoship product quantity add button clicked");
	}

	public boolean isProductAddedToOrder(String SKU){
		return driver.isElementPresent(By.xpath(String.format(productOnOrderTableOnOrderPage, SKU)));
	}

	public void clickSaveAutoshipTemplate(){
		driver.click(SAVE_TEMPLATE_BTN,"");
		logger.info("save template button clicked");
		driver.pauseExecutionFor(2000);
		driver.waitForNSCore4ProcessImageToDisappear();
		driver.waitForPageLoad();
	}

	public boolean isAddedProductPresentInOrderDetailPage(String SKU){
		driver.quickWaitForElementPresent(By.xpath(String.format(productOnOrderTableOnOrderDetailPage, SKU)));
		return driver.isElementPresent(By.xpath(String.format(productOnOrderTableOnOrderDetailPage, SKU)));
	}

	public String getQuantityOfPulseProductFromOrderDetailPage(){
		driver.waitForElementPresent(QUANTITY_OF_PULSE_PRODUCT_ON_ORDER_DETAIL_PAGE);
		logger.info("pulse quantity on UI is "+driver.findElement(QUANTITY_OF_PULSE_PRODUCT_ON_ORDER_DETAIL_PAGE).getText().trim());
		return driver.findElement(QUANTITY_OF_PULSE_PRODUCT_ON_ORDER_DETAIL_PAGE).getText().trim();
	}

	public void clickCustomerlabelOnOrderDetailPage(){
		driver.click(CUSTOMER_LABEL_ORDER_DETAIL_PAGE,"");
		logger.info("customer label on order detail page clicked");
		driver.waitForPageLoad();
	}

	public NSCore4MobilePage clickMobileTab(){
		driver.quickWaitForElementPresent(MOBILE_TAB_LOC);
		driver.click(MOBILE_TAB_LOC,"");
		logger.info("Mobile Tab is clicked");
		driver.waitForPageLoad();
		return new NSCore4MobilePage(driver);
	}

	public NSCore4OrdersTabPage clickPendingOrderID() {
		driver.click(FIRST_PENDING_ORDER_LOC,"");
		return new NSCore4OrdersTabPage(driver);
	}

	public NSCore4OrdersTabPage clickOrdersTab() {
		driver.click(ORDERS_TAB_LOC,"");
		logger.info("orders tab is clicked");
		return new NSCore4OrdersTabPage(driver);
	}

	public String getOrderIDFromOverviewPage() {
		return driver.getText(ORDER_ID_FROM_OVERVIEW_PAGE_LOC,"Order Id");
	}

	public int getCountOfSearchResultRows(){
		driver.waitForElementPresent(ROWS_COUNT_OF_SEARCH_RESULT);
		int count= driver.findElements(ROWS_COUNT_OF_SEARCH_RESULT).size();
		logger.info("Count of search result rows are: "+count);
		return count;
	}

	public void clickProxyLink(String linkName){
		driver.waitForElementPresent(By.xpath(String.format(proxyLinksLoc, linkName)));
		driver.click(By.xpath(String.format(proxyLinksLoc, linkName)),"");
		logger.info(linkName+" proxy link clicked");
		driver.waitForPageLoad();
	}

	public void switchToPreviousTab(){
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		driver.pauseExecutionFor(1000);
	}

	public void clickIWantToWriteMyOwnStory(){
		driver.waitForElementPresent(I_WANT_TO_WRITE_OWN_STORY);
		driver.click(I_WANT_TO_WRITE_OWN_STORY,"");
		logger.info("I want to write my own story is clicked");
		driver.waitForPageLoad();
	}

	public boolean verifyNewlyCreatedStoryIsUpdated(String storyTitle){
		driver.waitForElementPresent(By.xpath(String.format(storyNameOnEditOptionPage, storyTitle)));
		return driver.isElementPresent(By.xpath(String.format(storyNameOnEditOptionPage, storyTitle)));
	}

	public boolean verifyWaitingForApprovalLinkForNewStory(String storyTitle){
		driver.waitForElementPresent(By.xpath(String.format(waitingForApprovalLink, storyTitle)));
		return driver.isElementPresent(By.xpath(String.format(waitingForApprovalLink, storyTitle)));
	}

	public String getStoryDeniedText(String storyTitle){
		driver.waitForElementPresent(By.xpath(String.format(waitingForApprovalLink, storyTitle)));
		String denyTxt=driver.findElement(By.xpath(String.format(waitingForApprovalLink, storyTitle))).getText();
		logger.info("Story deny text from UI is: "+denyTxt);
		return denyTxt;
	}

	public NSCore4AdminPage clickAdminTab(){
		driver.quickWaitForElementPresent(ADMIN_TAB_LOC);
		driver.click(ADMIN_TAB_LOC,"");
		logger.info("Admin Tab is clicked");
		driver.waitForPageLoad();
		return new NSCore4AdminPage(driver);
	}

	public void selectOrderStatusByDropDown(String value) {
		Select select = new Select(driver.findElement(RECENT_ORDER_DROP_DOWN_LOC));
		select.selectByVisibleText(value);
		driver.pauseExecutionFor(2000);

	}

	public boolean isNoOrderFoundMessagePresent() {
		return driver.isElementPresent(NO_ORDER_FOUND_MSG_LOC);  
	}

	public void clickPoliciesChangeHistoryLink(){
		driver.quickWaitForElementPresent(POLICIES_CHANGE_HISTORY_LINK_LOC);
		driver.click(POLICIES_CHANGE_HISTORY_LINK_LOC,"");
		logger.info("Policies Change History link clicked");
		driver.waitForPageLoad(); 
	}

	public boolean validatePoliciesChangeHistoryPageDisplayedWithRespectiveColumns(){
		return driver.getCurrentUrl().contains("PoliciesChangeHistory") &&
				driver.isElementPresent(VERSION_COLUMN_LOC) &&
				driver.isElementPresent(DATERELEASED_COLUMN_LOC) &&
				driver.isElementPresent(DATEACCEPTED_COLUMN_LOC);
	}

	public void clickStatusHistoryLink(){
		driver.quickWaitForElementPresent(STATUS_HISTORY_LINK_LOC);
		driver.click(STATUS_HISTORY_LINK_LOC,"");
		logger.info("Status History link clicked");
		driver.waitForPageLoad(); 
	}

	public boolean validateStatusHistoryPageDisplayedWithRespectiveColumns(){
		return driver.getCurrentUrl().contains("StatusHistory") &&
				driver.isElementPresent(CHANGED_ON_COLUMN_LOC) &&
				driver.isElementPresent(STATUS_COLUMN_LOC) &&
				driver.isElementPresent(REASON_COLUMN_LOC);
	}

	public void clickPostNewNodeLinkInOverviewTab(){
		driver.waitForElementPresent(POST_NEW_NODE_LINK);
		driver.click(POST_NEW_NODE_LINK,"");
		logger.info("Post new node link clicked");
	}

	public void selectCategoryOnAddANotePopup(String category){
		driver.waitForElementPresent(CATEGORY_DROPDOWN_ON_ADD_NOTE_POPUP);
		driver.click(CATEGORY_DROPDOWN_ON_ADD_NOTE_POPUP,"");
		logger.info("Category Dropdown clicked on add note popup");
		driver.waitForElementPresent(By.xpath(String.format(selectCategoryOnAddNotePopup, category)));
		driver.click(By.xpath(String.format(selectCategoryOnAddNotePopup, category)),"");
		logger.info("Category"+category+" is selected on add new note popup");
	}

	public void selectTypeOnAddANotePopup(String type){
		driver.waitForElementPresent(TYPE_DROPDOWN_ON_ADD_NOTE_POPUP);
		driver.click(TYPE_DROPDOWN_ON_ADD_NOTE_POPUP,"");
		logger.info("Type Dropdown clicked on add note popup");
		driver.waitForElementPresent(By.xpath(String.format(selectTypeOnAddNotePopup, type)));
		driver.click(By.xpath(String.format(selectTypeOnAddNotePopup, type)),"");
		logger.info("Type"+type+" is selected on add new note popup");
	}

	public void enterNoteOnAddANotePopup(String note){
		driver.waitForElementPresent(ENTER_NOTE_ON_ADD_NOTE_POPUP);
		driver.type(ENTER_NOTE_ON_ADD_NOTE_POPUP, note);
		logger.info("Note"+note+" is entered on enter note txt field");
	}

	public void selectAndEnterAddANoteDetailsInPopup(String category,String type,String note){
		selectCategoryOnAddANotePopup(category);
		selectTypeOnAddANotePopup(type);
		enterNoteOnAddANotePopup(note);
	}

	public void clickSaveBtnOnAddANotePopup(){
		driver.waitForElementPresent(SAVE_BTN_ON_ADD_A_NOTE_POPUP);
		driver.click(SAVE_BTN_ON_ADD_A_NOTE_POPUP,"");
		logger.info("Save btn clicked on add a note popup");
	}

	public boolean isNewlyCreatedNotePresent(String note){
		driver.waitForElementPresent(By.xpath(String.format(newlyCreatedNoteLoc, note)));
		return driver.isElementPresent(By.xpath(String.format(newlyCreatedNoteLoc, note)));
	}

	public void clickPostFollowUpLinkForParentNote(String note){
		driver.quickWaitForElementPresent(By.xpath(String.format(postFollowUpLinkOfParent, note)));
		driver.click(By.xpath(String.format(postFollowUpLinkOfParent, note)),"");
		logger.info("Post follow up link is clicked for note"+note);
	}

	public boolean isNewlyCreatedChildNotePresent(String parentNote,String childNote){
		driver.waitForElementPresent(By.xpath(String.format(newlyCreatedChildNoteLoc, parentNote,childNote)));
		return driver.isElementPresent(By.xpath(String.format(newlyCreatedChildNoteLoc,parentNote, childNote)));
	}

	public boolean isPostFollowUpLinkPresentForChildNote(String parentNote,String childNote){
		driver.waitForElementPresent(By.xpath(String.format(postFollowUpChildLink, parentNote,childNote)));
		return driver.isElementPresent(By.xpath(String.format(postFollowUpChildLink,parentNote, childNote)));
	}

	public void clickCollapseLinkNearToParentNote(String parentNote){
		driver.quickWaitForElementPresent(By.xpath(String.format(collapseLinkNextToParentNote, parentNote)));
		driver.click(By.xpath(String.format(collapseLinkNextToParentNote, parentNote)),"");
		logger.info("Collapse link next to Parent note"+parentNote+" is clicked");

	}

	public void clickExpandLinkNearToParentNote(String parentNote){
		driver.quickWaitForElementPresent(By.xpath(String.format(expandLinkNextToParentNote, parentNote)));
		driver.click(By.xpath(String.format(expandLinkNextToParentNote, parentNote)),"");
		logger.info("Expand link next to Parent note"+parentNote+" is clicked");
	}

	public boolean isChildNoteDetailsAppearsOnUI(String parentNote){
		return driver.isElementPresent(By.xpath(String.format(childNoteDetailsOnUI, parentNote)));
	}

	public void clickStatusLink(){
		driver.click(STATUS_LINK_LOC,"Status link");
		driver.waitForPageLoad();
	}

	public void changeStatusDD(String status ){
		driver.waitForElementPresent(CHANGE_STATUS_DD_LOC);
		driver.click(CHANGE_STATUS_DD_LOC, "Status dropdown");
		driver.click(By.xpath("//select[@id='sStatus']/option[contains(text(),'"+status+"')]"), "status="+status);		
	}

	public void clickSaveStatusBtn(){
		driver.click(SAVE_STATUS_BTN_LOC,"Save Status button");
		driver.pauseExecutionFor(2000);
		driver.waitForElementToBeInVisible(SAVE_STATUS_BTN_LOC, 10);
		driver.waitForPageLoad();
	}

	public void refreshPage(){
		driver.navigate().refresh();
		driver.pauseExecutionFor(2000);
	}

	public void clickViewOrderLinkUnderConsultantReplenishment(){
		driver.waitForElementPresent(VIEW_ORDER_CONSULTANT_REPLENISHMENT);
		driver.click(VIEW_ORDER_CONSULTANT_REPLENISHMENT,"");
		logger.info("View order link clicked under consultant replenishment");
		driver.waitForPageLoad();
	}

	public void clickViewOrderLinkUnderPulseMonthlySubscription(){
		driver.waitForElementPresent(VIEW_ORDER_PULSE_MONTHLY_SUBSCRIPTION);
		driver.click(VIEW_ORDER_PULSE_MONTHLY_SUBSCRIPTION,"");
		logger.info("View order link clicked under Pulse Monthly Subscription"); 
		driver.waitForPageLoad();
	}

	public void clickCalenderStartDateForFilter(){
		driver.waitForElementPresent(START_DATE_OF_DATE_RANGE);
		driver.click(START_DATE_OF_DATE_RANGE,"");
		logger.info("Calender event start date clicked.");
	}

	public int getCountOfSearchResults(){
		driver.waitForElementPresent(ORDER_SEARCH_RESULTS);
		int count = driver.findElements(ORDER_SEARCH_RESULTS).size();
		logger.info("Total search results: "+count);
		return count;
	}

	public String getCompletedDateOfOrder(int randomOrderRowNumber){
		driver.waitForElementPresent(By.xpath(String.format(completedDateOfOrder, randomOrderRowNumber)));
		String completedDate = driver.findElement(By.xpath(String.format(completedDateOfOrder, randomOrderRowNumber))).getText();
		logger.info("Completed date is: "+completedDate);
		return completedDate;
	}

	public String getShippedDateOfOrder(int randomOrderRowNumber){
		driver.waitForElementPresent(By.xpath(String.format(shippedDateOfOrder, randomOrderRowNumber)));
		String shippedDate = driver.findElement(By.xpath(String.format(shippedDateOfOrder, randomOrderRowNumber))).getText();
		logger.info("Shipped date is: "+shippedDate);
		return shippedDate;
	}

	public String clickAndReturnRandomOrderNumber(int randomOrderRowNumber){
		driver.waitForElementPresent(By.xpath(String.format(orderNumber, randomOrderRowNumber)));
		String orderNo = driver.findElement(By.xpath(String.format(orderNumber, randomOrderRowNumber))).getText();
		driver.click(By.xpath(String.format(orderNumber, randomOrderRowNumber)),"");
		logger.info("Order Number: "+orderNo+" Clicked");
		driver.waitForPageLoad();
		return orderNo;
	}

	public String getOrderNumberFromOrderDetails(){
		driver.waitForElementPresent(ORDER_NUMBER_FROM_ORDER_DETAILS);
		String orderNumber = driver.findElement(ORDER_NUMBER_FROM_ORDER_DETAILS).getText();
		logger.info("Order Number from order details page is: "+orderNumber);
		return orderNumber;
	}

	public String[] getAllCompleteDate(int totalSearchResults){
		String[] completeDate = new String[totalSearchResults+1];
		for(int i=1; i<=totalSearchResults; i++){
			completeDate[i]=	driver.getText(By.xpath(String.format(completedDateOfOrder, i)));
		}
		return completeDate;
	}

	public boolean isAllCompleteDateContainCurrentYear(String[] totalSearchResults){
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).trim();
		boolean isCurrentYearPresent = false;
		for(int i=1; i<totalSearchResults.length; i++){
			if(totalSearchResults[i].contains(year) || totalSearchResults[i].contains("N/A")){
				isCurrentYearPresent = true;
			}else{
				isCurrentYearPresent = false;
				break;
			}
		}
		return isCurrentYearPresent;
	}

	public void clickFullAccountRecordLink(){
		driver.click(FULL_ACCOUNT_RECORD_LINK,"Full account record link");
		driver.waitForPageLoad();
	}

	public void checkApplicationOnFileChkBox(){
		driver.waitForElementPresent(APPLICATION_ON_FILE_CHKBOX);
		driver.click(APPLICATION_ON_FILE_CHKBOX,"");
		logger.info("Application on file check box clicked");
		driver.waitForPageLoad();
	}

	public String getCRPStartDate(){
		driver.waitForElementPresent(CRP_START_DATE);
		String startDate = driver.findElement(CRP_START_DATE).getAttribute("value");
		logger.info("CRP start date is: "+startDate);
		return startDate;
	}

	public void clickCRPStartDate(){
		driver.waitForElementPresent(CRP_START_DATE);
		driver.click(CRP_START_DATE,"");
		logger.info("CRP Start date clicked");
		driver.waitForPageLoad();
	}

	public String getUserName(){
		return  driver.getAttribute(USERNAME_LOC,"value");
	}

	public void enterUserName(String name){
		driver.type(USERNAME_LOC, name,"username");
	}

	public String getFirstName(){
		driver.waitForElementPresent(FIRST_NAME);
		String userName = driver.findElement(FIRST_NAME).getAttribute("value");
		logger.info("Firstname is: "+userName);
		return userName;
	}

	public String getLastFourDgitOfHomePhoneNumber(){
		driver.waitForElementPresent(LAST_FOUR_DIGIT_OF_HOME_PHONE_NUMBER);
		String homePhoneNumber = driver.findElement(LAST_FOUR_DIGIT_OF_HOME_PHONE_NUMBER).getAttribute("value");
		logger.info("last four digit of home phone number is: "+homePhoneNumber);
		return homePhoneNumber;
	}

	public void enterLastFourDigitOfHomePhoneNumber(String number){
		driver.type(LAST_FOUR_DIGIT_OF_HOME_PHONE_NUMBER, number,"last four digit of home phone number");
	}

	public String getEmailAddress(){
		return  driver.getAttribute(EMAIL_ADDRESS_LOC,"value");
	}

	public void enterEmailAddress(String email){
		driver.type(EMAIL_ADDRESS_LOC, email,"email");
	}

	public String getTaxExemptValue(){
		reLoadPage();
		driver.waitForElementPresent(TAX_EXEMPT_VALUE);
		String taxExemptValue = driver.findElement(TAX_EXEMPT_VALUE).getText().trim();
		logger.info("Tax exempt value is: "+taxExemptValue);
		return taxExemptValue;
	}

	public String getTaxExemptValueForUpdate(String taxExemptValue){
		if(taxExemptValue.contains("No")){
			taxExemptValue = "Yes";
		}else{
			taxExemptValue = "No";
		}
		logger.info("Tax exempt value for update is: "+taxExemptValue);
		return taxExemptValue;
	}

	public void selectTaxExemptValue(String taxExemptValue){
		driver.click(TAX_EXEMPT_DD,"Tax exempt DD");
		if(taxExemptValue.equalsIgnoreCase("Yes")){
			driver.click(TAX_EXEMPT_DD_YES_VALUE,"");
			logger.info("Yes value selected from DD for tax exempt");
		}else{
			driver.click(TAX_EXEMPT_DD_NO_VALUE,"");
			logger.info("No value selected from DD for tax exempt");
		}
	}

	public String getNameOnSSNCard(){
		driver.waitForElementPresent(NAME_ON_SSN_CARD);
		String email = driver.findElement(NAME_ON_SSN_CARD).getAttribute("value");
		logger.info("Name on SSN card is: "+email);
		return email;
	}

	public void enterNameOnSSNCard(String name){
		if(name.equalsIgnoreCase("null")){
			logger.info("Before updation name on SSN card is null ");
		}else{
			driver.waitForElementPresent(NAME_ON_SSN_CARD);
			driver.type(NAME_ON_SSN_CARD, name);
			logger.info("SSN Card name entered as: "+name);
		}
	}

	public String getDOBValue(){
		driver.waitForElementPresent(DOB_LOC);
		String dob = driver.findElement(DOB_LOC).getAttribute("value");
		logger.info("Date of birth is: "+dob);
		return dob;
	}

	public String getSelectedGender(){
		//reLoadPage();
		return driver.getText(GENDER_DD_SELECTED_VALUE).trim();
	}

	public String getAttentionName(){
		driver.waitForElementPresent(ATTENTION_NAME);
		String attentionName = driver.findElement(ATTENTION_NAME).getAttribute("value");
		logger.info("Attention name is: "+attentionName);
		return attentionName;
	}

	public void enterAttentionName(String attentionName){
		driver.type(ATTENTION_NAME, attentionName);
		logger.info("Attention name entered as: "+attentionName);
	}

	public String getZIPCode(){
		driver.waitForElementPresent(ZIP_CODE);
		String zipCode = driver.findElement(ZIP_CODE).getAttribute("value");
		logger.info("ZIP code is: "+zipCode);
		return zipCode;
	}

	public void enterZIPCode(String zipCode){
		driver.type(ZIP_CODE, zipCode);
		logger.info("ZIP Code entered as: "+zipCode);
		driver.pauseExecutionFor(5000);
	}


	public String getLastFourDgitOfPhoneNumber(){
		driver.waitForElementPresent(LAST_FOUR_DIGIT_OF_PHONE_NUMBER);
		String phoneNumber = driver.findElement(LAST_FOUR_DIGIT_OF_PHONE_NUMBER).getAttribute("value");
		logger.info("Phone Number is: "+phoneNumber);
		return phoneNumber;
	}

	public void enterLastFourDigitOfPhoneNumber(String number){
		driver.waitForElementPresent(LAST_FOUR_DIGIT_OF_PHONE_NUMBER);
		driver.type(LAST_FOUR_DIGIT_OF_PHONE_NUMBER, number);
		logger.info("Phone number entered as: "+number);
	}

	public void clickSaveBtnForAccountRecord(){
		driver.click(SAVE_ACCOUNT_BTN,"Save Account button");
		try{
			driver.waitForElementToBeVisible(USE_ADDRESS_AS_ENTERED_BTN_LOC,10);
			driver.pauseExecutionFor(2000);
			driver.click(USE_ADDRESS_AS_ENTERED_BTN_LOC,"Use Address as Entered Btn");
			driver.pauseExecutionFor(2000);
		}catch(Exception e){
			e.getMessage();
			logger.info("'Use Address Ad Entered' PopUp not displayed");
		}
		driver.waitForPageLoad();
	}

	public String getDayFromDate(String date){
		String day = date.split("\\/")[1];
		logger.info("The day is: "+day);
		return day;
	}

	public String getUpdatedDayFromDate(String day){
		if(day.contains("30")|| day.contains("31")){
			day = "1";
		}else{
			//int updatedDay = Integer.parseInt(day) 
			day = String.valueOf(Integer.parseInt(day)+1);
		}
		logger.info("Updated day is: "+day);
		return day;
	}

	public String getMonthFromDate(String date){
		String month = date.split("\\/")[0];
		logger.info("The month is: "+month);
		return month;
	}

	public String getYearFromDate(String date){
		String year = date.split("\\/")[2];
		logger.info("The Year is: "+year);
		return year;
	}

	public void clickDOBDate(){
		driver.waitForElementPresent(DOB_LOC);
		driver.click(DOB_LOC,"");
		logger.info("DOB date clicked");
		driver.waitForPageLoad();
	}

	public String getGenderValueForUpdate(String gender){
		if(gender.contains("None")){
			gender = "Male";
		}else{
			if(gender.contains("Male")){
				gender = "Female";
			}else{
				gender = "Male";
			}

		}
		logger.info("Gender for update is: "+gender);
		return gender;
	}

	public void clickBillingAndShippingProfileLink(){
		driver.quickWaitForElementPresent(BILLING_AND_SHIPPING_PROFILE_LINK_LOC);
		driver.click(BILLING_AND_SHIPPING_PROFILE_LINK_LOC,"");
		logger.info("Billing & Shipping Profile link clicked");
		driver.waitForPageLoad(); 
	}

	public int getTotalBillingProfiles(){
		driver.navigate().refresh();
		List<WebElement> allBillingProfiles = driver.findElements(TOTAL_BILLING_PROFILES);
		logger.info("total Billing profiles are "+allBillingProfiles);
		return allBillingProfiles.size();
	}		

	public void clickShippingProfileAddLink(){
		driver.quickWaitForElementPresent(SHIPPING_PROFILE_ADD_LINK_LOC);
		driver.click(SHIPPING_PROFILE_ADD_LINK_LOC,"");
		logger.info("Shipping Profile -Add link clicked");
		driver.waitForPageLoad(); 
	}

	public void clickSetAsDefaultAddressForNewlyCreatedProfile(String shippingProfileName){
		driver.quickWaitForElementPresent(By.xpath(String.format(newlyCeatedShippingProfileSetDefault, shippingProfileName)));
		driver.click(By.xpath(String.format(newlyCeatedShippingProfileSetDefault, shippingProfileName)),"");
		driver.pauseExecutionFor(3000);
	}

	public boolean validateNewlyCreatedShippingProfileIsDefault(String shippingProfileName){
		driver.quickWaitForElementPresent(By.xpath(String.format(newlyCeatedShippingProfileIsDefault, shippingProfileName)));
		return driver.isElementPresent(By.xpath(String.format(newlyCeatedShippingProfileIsDefault, shippingProfileName)));
	}

	public void deleteAddressNewlyCreatedProfile(String shippingProfileName){
		driver.quickWaitForElementPresent(By.xpath(String.format(deleteAddressnewlyCeatedShippingProfile, shippingProfileName)));
		driver.click(By.xpath(String.format(deleteAddressnewlyCeatedShippingProfile, shippingProfileName)),"");
		driver.pauseExecutionFor(2000);
		//switch to Alert to delete payment method-
		clickOKBtnOfJavaScriptPopUp();
		driver.pauseExecutionFor(2000);
	}

	public void clickBillingProfileAddLink(){
		driver.quickWaitForElementPresent(BILLING_PROFILE_ADD_LINK_LOC);
		driver.click(BILLING_PROFILE_ADD_LINK_LOC,"");
		logger.info("Billing Profile -Add link clicked");
		driver.waitForPageLoad(); 
	}

	public void addANewBillingProfile(String firstName,String lastName,String nameOnCard,String cardNumber,String addressLine1,String zipCode){
		driver.pauseExecutionFor(3000);
		//Add 'Payment-Method'
		driver.type(ADD_PAYMENT_METHOD_FIRST_NAME_LOC, firstName);
		driver.type(ADD_PAYMENT_METHOD_LAST_NAME_LOC, lastName);
		driver.type(ADD_PAYMENT_METHOD_NAME_ON_CARD_LOC, nameOnCard);
		driver.type(ADD_PAYMENT_METHOD_CREDIT_CARD_NO_LOC, cardNumber);
		//select 'Expiration' Date(change year)
		Select sel = new Select(driver.findElement(ADD_NEW_PAYMENT_EXPIRATION_YEAR_DROP_DOWN_LOC));
		sel.selectByIndex(7);
		driver.type(By.id("profileName"), firstName+" "+lastName);
		driver.type(By.id("addressLine1"), addressLine1);
		driver.type(By.id("zip"), zipCode+"\t");
		driver.pauseExecutionFor(2000);
	}

	public boolean isNewlyCreatedBilingProfilePresent(){
		driver.quickWaitForElementPresent(NEWLY_CREATED_BILLING_PROFILE_LOC);
		return driver.isElementPresent(NEWLY_CREATED_BILLING_PROFILE_LOC);
	}


	public void clickSetAsDefaultPaymentMethodForNewlyCreatedProfile(){
		driver.pauseExecutionFor(2000);
		driver.quickWaitForElementPresent(By.xpath(String.format(setAsDefaultForNewlyCreatedBillingProfile, getTotalBillingProfiles())));
		driver.click(By.xpath(String.format(setAsDefaultForNewlyCreatedBillingProfile, getTotalBillingProfiles())),"");
		driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
	}

	public boolean validateNewlyCreatedBillingProfileIsDefault(){
		driver.quickWaitForElementPresent(By.xpath(String.format(isDefaultPresentForNewlyCreatedBillingProfile, getTotalBillingProfiles())));
		return driver.isElementPresent(By.xpath(String.format(isDefaultPresentForNewlyCreatedBillingProfile, getTotalBillingProfiles())));
	}

	public void deletePaymentMethodNewlyCreatedProfile(){
		driver.quickWaitForElementPresent(By.xpath(String.format(deleteNewlyCreatedBillingProfile, getTotalBillingProfiles())));
		driver.click(By.xpath(String.format(deleteNewlyCreatedBillingProfile, getTotalBillingProfiles())),"");
		logger.info("Delete button clicked");
		driver.pauseExecutionFor(2000);
		//switch to Alert to delete payment method-
		try{
			Alert alt =driver.switchTo().alert();
			alt.accept();
			logger.info("Alert handled");
		}catch(Exception e){

		}
		driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
	}

	public boolean isBillingProfileDeleted(){
		List<WebElement> allBillingProfiles = driver.findElements(TOTAL_BILLING_PROFILES);
		if(allBillingProfilesSize!=allBillingProfiles.size()){
			return true;
		}
		return false;
	}

	public void clickSublinkOfOverview(String linkname){
		driver.waitForElementPresent(By.xpath(String.format(overViewSublinkLoc, linkname)));
		driver.click(By.xpath(String.format(overViewSublinkLoc, linkname)),"");
		logger.info(linkname+"clicked on overview page");
		driver.waitForPageLoad();
	}

	public String getOrderID(){
		driver.waitForElementPresent(PLACED_ORDER_NUMBER);
		String orderID = driver.findElement(PLACED_ORDER_NUMBER).getText();
		logger.info("Order number is: "+orderID);
		return orderID;
	}

	public void clickOrderId(String orderID){
		driver.waitForElementPresent(By.xpath(String.format(orderIdLinkLoc, orderID)));
		driver.click(By.xpath(String.format(orderIdLinkLoc, orderID)),"");
		logger.info(orderID+"clicked on overview page");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(2000);
	}

	public String addAndGetProductSKU(String quantity){
		driver.click(OPEN_BULK_ADD_LOC,"Open bulk add link");
		driver.waitForNSCore4LoadingImageToDisappear();
		String SKU = driver.getText(PRODUCT_SKU_VALUE,"product sku value");
		driver.type(PRODUCT_QUANTITY_LOC, quantity,"quantity");
		driver.click(ADD_TO_ORDER_BTN,"add to order btn");
		driver.click(CLOSE_LINK_LOC,"close link on bulk add popup");
		return SKU;
	}

	public int getTotalNoOfShippingProfiles(){
		driver.waitForElementPresent(SHIPPING_PROFILES_LOC);
		int noOfShippingProfile = driver.findElements(SHIPPING_PROFILES_LOC).size();
		logger.info("Total no of shipping profiles is: "+noOfShippingProfile);
		return noOfShippingProfile;
	}

	public String addAndGetProductSKUForCatalog(){
		driver.waitForElementPresent(OPEN_BULK_ADD_LOC);
		driver.click(OPEN_BULK_ADD_LOC,"");
		driver.waitForNSCore4LoadingImageToDisappear();
		logger.info("Open bulk add link clicked");
		driver.waitForElementPresent(PRODUCT_SKU_VALUE_CATALOG);
		String SKU = driver.findElement(PRODUCT_SKU_VALUE_CATALOG).getText();
		driver.click(PRODUCT_SKU_CHK_BOX_CATALOG,"");
		logger.info("Checkbox checked for add product");
		driver.click(ADD_TO_CATALOG,"");
		logger.info("Add to catalog button clicked");
		driver.click(CLOSE_LINK_CATALOG_LOC,"");
		logger.info("Close link clicked on bulk catalog popup");
		return SKU;
	}

	public void addPaymentMethod(String firstName,String lastName,String nameOnCard,String cardNumber){
		driver.waitForElementPresent(ADD_NEW_PAYMENT_METHOD_LINK);
		driver.click(ADD_NEW_PAYMENT_METHOD_LINK,"");
		logger.info("Add new payment method link clicked");
		driver.pauseExecutionFor(3000);
		//enter billing info
		//select 'Main-Billing' as billing address-
		Select select = new Select(driver.findElement(ADD_NEW_BILLING_ADDRESS_DROP_DOWN_LOC));
		select.selectByIndex(1);
		driver.pauseExecutionFor(4500);
		//Add 'Payment-Method'
		driver.type(ADD_PAYMENT_METHOD_FIRST_NAME_LOC, firstName);
		logger.info("First name entered for billing profile is: "+firstName);
		driver.type(ADD_PAYMENT_METHOD_LAST_NAME_LOC, lastName);
		logger.info("last name entered for billing profile is: "+lastName);
		driver.type(ADD_PAYMENT_METHOD_NAME_ON_CARD_LOC, nameOnCard);
		logger.info("name on card entered for billing profile is: "+nameOnCard);
		driver.type(ADD_PAYMENT_METHOD_CREDIT_CARD_NO_LOC, cardNumber);
		logger.info("card number entered for billing profile is: "+cardNumber);
		//select 'Expiration' Date(change year)
		Select sel = new Select(driver.findElement(ADD_NEW_PAYMENT_EXPIRATION_YEAR_DROP_DOWN_LOC));
		sel.selectByIndex(7);
		logger.info("Credit card expire year selected");
	}

	public boolean isEditPresentForConsultantReplenishmentPresent(){
		driver.waitForElementPresent(CONSULTANT_REPLENISHMENT_EDIT);
		return driver.isElementPresent(CONSULTANT_REPLENISHMENT_EDIT);
	}

	public boolean isEditPresentForPulseMonthlySubscriptionPresent(){
		driver.waitForElementPresent(PULSE_MONTHLY_SUBSCRIPTION_EDIT);
		return driver.isElementPresent(PULSE_MONTHLY_SUBSCRIPTION_EDIT);
	}

	public void enterFirstAndLastNameInCorrespondingFields(String fisrtName, String lastName){
		driver.quickWaitForElementPresent(By.xpath("//label[text()='First Name']/following::input[1]"));
		driver.type(By.xpath("//label[text()='First Name']/following::input[1]"),fisrtName);
		logger.info("First name entered as "+fisrtName);
		driver.type(By.xpath("//label[text()='Last Name']/following::input[1]"),lastName);
		logger.info("Last name entered as "+lastName);
		driver.pauseExecutionFor(2000);
	}

	public void addANewBillingProfile(String firstName,String lastName,String nameOnCard,String cardNumber,String addressLine1,String zipCode, String CVV){
		driver.pauseExecutionFor(3000);
		//Add 'Payment-Method'
		driver.type(ADD_PAYMENT_METHOD_FIRST_NAME_LOC, firstName);
		driver.type(ADD_PAYMENT_METHOD_LAST_NAME_LOC, lastName);
		driver.type(ADD_PAYMENT_METHOD_NAME_ON_CARD_LOC, nameOnCard);
		driver.type(ADD_PAYMENT_METHOD_CREDIT_CARD_NO_LOC, cardNumber);
		driver.type(By.id("cvvNumber"), CVV);
		//select 'Expiration' Date(change year)
		Select sel = new Select(driver.findElement(ADD_NEW_PAYMENT_EXPIRATION_YEAR_DROP_DOWN_LOC));
		sel.selectByIndex(7);
		driver.type(By.id("profileName"), firstName+" "+lastName);
		driver.type(By.id("addressLine1"), addressLine1);
		driver.type(By.id("zip"), zipCode+"\t");
		driver.pauseExecutionFor(2000);
	}


	public void selectAccountStatusFromDD(String status){
		driver.click(By.xpath("//div[contains(@class,'top-panel')]//span[contains(text(),'Status')]/following::select[1]"),"");
		driver.click(By.xpath(String.format("//div[contains(@class,'top-panel')]//span[contains(text(),'Status')]/following::select[1]//option[contains(text(),'%s')]", status)),"");
		logger.info("Status selected as "+status);
	}

	public String getSubscriptionStatus(){
		driver.waitForElementPresent(SUBSCRIPTION_STATUS_LOC);
		logger.info("pulse quantity on UI is "+driver.findElement(SUBSCRIPTION_STATUS_LOC).getText().trim());
		return driver.findElement(SUBSCRIPTION_STATUS_LOC).getText().toLowerCase().trim();
	}

	public String getPrefixFromUrl(String urlType){
		String index = null;
		if(urlType.contains("com")){
			index="1";
		}else{
			index="2";
		}
		String prefix = driver.getAttribute((By.xpath(String.format(bizComUrl, index))), "value");
		logger.info("Prefix of "+urlType+ "is "+prefix);
		return prefix;
	}

	public void enterPrefixAccordingToUrlType(String urlType, String prefix){
		String index = null;
		if(urlType.contains("com")){
			index="1";
		}else{
			index="2";
		}
		driver.type(By.xpath(String.format(bizComUrl, index)), prefix);
		logger.info("Prefix entered of "+urlType+ "is "+prefix);
	} 

	public String getPulseEmailFromUI(){
		String email = driver.getAttribute((PULSE_EMAIL_LOC),"value");
		logger.info("email of pulse is"+email);
		return email;
	}

	public void enterPulseEmail(String email){
		driver.type(PULSE_EMAIL_LOC, email);
		logger.info("pulse entered as "+email);
	} 

	public void clickSaveChangesBtn(){
		driver.click(SAVE_CHANGES_BTN_LOC,"");
		logger.info("Clicked save changes btn");
	}

	public void checkUseAddressOfRecordChkBox(){
		driver.waitForElementPresent(USE_ADDRESS_OF_RECORD_CHK_BOX_LOC);
		driver.click(USE_ADDRESS_OF_RECORD_CHK_BOX_LOC,"");
		logger.info("Use address of record check box clicked");
	}

	public String getNameOfPayAbleTo(){
		String name = driver.getAttribute((PAYABLE_TO_LOC),"value");
		logger.info("name of payable to is"+name);
		return name;
	}

	public String getAddress1OfDisbursementProfile(){
		String address1 = driver.getAttribute((ADDRESS_1_FOR_DISBURSEMENT_PROFILE_LOC),"value");
		logger.info("address line 1 is"+address1);
		return address1;
	}

	public String getCityOfDisbursementProfile(){
		String city = driver.getAttribute((CITY_FOR_DISBURSEMENT_PROFILE_LOC),"value");
		logger.info("City is"+city);
		return city;
	}

	public String getStateOfDisbursementProfile(){
		String state = driver.getAttribute((STATE_FOR_DISBURSEMENT_PROFILE_LOC),"value");
		logger.info("state is"+state);
		return state;
	}

	public String getPostalCodeOfDisbursementProfile(){
		String postalCode = driver.getAttribute((POSTAL_CODE_FOR_DISBURSEMENT_PROFILE_LOC),"value");
		logger.info("postal code is"+postalCode);
		return postalCode;
	}

	public void enterShippingDetailsForDisbursementProfile(String name, String address1, String city, String state, String postalCode){
		driver.type(PAYABLE_TO_LOC, name);
		logger.info("Payable to name entered as "+name);
		driver.type(ADDRESS_1_FOR_DISBURSEMENT_PROFILE_LOC, address1);
		logger.info("address1 entered as "+address1);
		driver.type(CITY_FOR_DISBURSEMENT_PROFILE_LOC, city);
		logger.info("city entered as "+city);
		driver.type(STATE_FOR_DISBURSEMENT_PROFILE_LOC, state);
		logger.info("state entered as "+state);
		driver.type(POSTAL_CODE_FOR_DISBURSEMENT_PROFILE_LOC, postalCode);
		logger.info("postalCode entered as "+postalCode);
	}

	public void clickSaveChangesBtnForDisbursementProfile(){
		driver.click(SAVE_CHANGES_BTN_FOR_DISBURSEMENT_PROFILE_LOC,"");
		logger.info("Clicked save changes btn");
	}

	public void clickEditCRPTemplate() {
		driver.waitForElementPresent(EDIT_CRP_TEMPLATE_LOC);
		driver.click(EDIT_CRP_TEMPLATE_LOC,"");
		logger.info("clicked on Edit CRP Template");
	}

	public void editBillingDetails(String invalidCreditCard, String CVV) {
		driver.waitForElementPresent(CREDIT_CARD_LOC);
		driver.type(CREDIT_CARD_LOC, invalidCreditCard);
		logger.info("credit card entered is:" +invalidCreditCard);
		driver.type(CVV_NUMBER_LOC, CVV);
		logger.info("CVV entered is: "+CVV);
		driver.click(SAVE_PAYMENT_METHOD_BUTTON_LOC,"");
		logger.info("clicked on Save Payment Method Button");
		if (driver.isElementPresent(ACCEPT_QAS_POPUP_LOC)) {
			driver.click(ACCEPT_QAS_POPUP_LOC,"");
			logger.info("clicked on Accept button on QAS Popup");
		}

	}

	public boolean isErrorMessagePresentOnUI() {
		return driver.isElementPresent(ERROR_MESSAGE_LOC);
	}

	public void editExistingPaymentDetails() {
		driver.click(EDIT_EXISTING_PAYMENT_DETAILS_LOC,"");
		logger.info("clicked on Edit button of Existing Payment Details");
	}

	public void selectAccountStatusForSearch(String status){
		driver.click(By.xpath("//label[contains(text(),'Status')]/following::md-select[1]"),"");
		driver.click(By.xpath(String.format("//label[contains(text(),'Status')]//following::div[contains(text(),'%s')]", status)),"");
		logger.info("Status selected as "+status);
	}

	public boolean isCIDPresentinSearchResults(String cid){
		driver.waitForElementPresent(ACCOUNT_SEARCH_RESULTS);
		return driver.isElementPresent(By.xpath("//a[text()='"+cid+"']"));
	}

	public void selectAccountTypeForSearch(String type){
		driver.click(By.xpath("//label[contains(text(),'Type')]/following::md-select[1]"),"");
		driver.click(By.xpath(String.format("//label[contains(text(),'Type')]//following::div[contains(text(),'%s')]", type)),"");
		logger.info("Type selected as "+type);
	}

	public void enterFirstName(String name){
		if(driver.isElementPresent(FIRST_NAME_BROWSE_PAGE_LOC)){
			driver.type(FIRST_NAME_BROWSE_PAGE_LOC, name);
		}else{
			driver.type(FIRST_NAME, name);
			logger.info("First Name text entered as: "+name);
		}
	}

	public void enterLastName(String name){
		driver.waitForElementPresent(LAST_NAME_BROWSE_PAGE_LOC);
		driver.type(LAST_NAME_BROWSE_PAGE_LOC, name);
		logger.info("Last Name text entered as: "+name);
	}

	public boolean isFirstAndLastNamePresentinSearchResults(String accountId,String firstName,String lastName){
		driver.waitForElementPresent(ACCOUNT_SEARCH_RESULTS);
		return driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[2]", accountId))).getText().contains(firstName)
				&& driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/following::div[4]",accountId))).getText().contains(lastName);
	}

	public void clickProductsTab() {
		driver.waitForPageLoad();
		driver.click(PRODUCTS_TAB_LOC,"");
		logger.info("products tab clicked");

	}

	public void mouseHoverToProductsManagementTab(String link) {
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(PRODUCTS_MANAGEMENT_TAB_LOC)).build().perform();
		logger.info("hover on Products Management tab");
		driver.waitForElementPresent(By.xpath(String.format(linkUnderProductsManagementTab, link)));
		driver.click(By.xpath(String.format(linkUnderProductsManagementTab, link)),"");
		logger.info("Clicked "+link+" link is clicked after hovering shop skincare.");
	}

	public void AddNewProductDetailsAndClickSave(String sku,String name) {
		driver.type(By.id("txtSKU"), sku);
		logger.info("Sku Id entered as: "+sku);
		driver.type(By.id("txtName"), name);
		logger.info("Product name entered as: "+name);
		driver.click(By.id("btnSave"),"");
		logger.info("Save Details Button clicked");
		driver.waitForPageLoad();
	}

	public String verifyProductOverviewPageisDisplayed() {
		driver.waitForPageLoad();
		driver.pauseExecutionFor(2000);
		//driver.isElementVisible(By.xpath(".//div[@class='SectionHeader']//h2[contains(text(),'Overview')]"));
		String newproductname=driver.findElement(By.xpath("//*[@id='ContentWrap']//h2")).getText();
		logger.info(newproductname);
		System.out.println("Name is "+newproductname);
		return driver.getText(By.xpath("//*[@id='ContentWrap']//h2")).toLowerCase().trim();
	}

	public void clickPricingTab() {
		driver.waitForPageLoad();
		driver.click(PRICING_TAB_LOC,"");
		logger.info("pricing tab clicked");

	}

	public void UpdatePricingDetails(String retail, String pc, String consultant) {
		driver.type(By.xpath(".//*[@id='priceType1']"), retail);
		logger.info("retail value updated as"+retail);
		driver.type(By.xpath(".//*[@id='priceType2']"), pc);
		logger.info("pc value updated as"+pc);
		driver.type(By.xpath(".//*[@id='priceType3']"), consultant);
		logger.info("consultant value updated as"+consultant);
	}

	public void clickCMSTab() {
		driver.waitForPageLoad();
		driver.click(CMS_TAB_LOC,"");
		logger.info("CMS tab clicked");
	}

	public void clickCategoriesTab() {
		driver.waitForPageLoad();
		driver.click(CATEGORIES_TAB_LOC,"");
		logger.info("Categories tab clicked");
	}

	public void clickRelationshipsTab() {
		driver.waitForPageLoad();
		driver.click(RELATIONSHIPS_TAB_LOC,"");
		logger.info("Relationships tab clicked");

	}

	public void clickAvailabilityTab() {
		driver.waitForPageLoad();
		driver.click(AVAILABILITY_TAB_LOC,"");
		logger.info("Availability tab clicked");

	}

	public void clickProductPropertiesTab() {
		driver.click(PRODUCT_PROPERTIES_TAB_LOC,"");
		logger.info("Product tab clicked");
	}

	public void EnterShortDescriptionCMSTabAndClickSave() {
		driver.type(By.id("txtShortDescription"), "Test CMS Description added");
		logger.info("Test CMS Description added");
		driver.click(SAVE_PRODUCT_DESCRIPTION_LOC,"");
		logger.info("Save short description Button clicked");
	}

	public boolean verifySuccessMessageForProductDescription() {
		return driver.isElementPresent(By.xpath(".//*[@id='messageCenter']//div[contains(text(),'Content saved!')]"));
	}

	public void clickDetailsTab() {
		driver.click(DETAILS_TAB_LOC,"");
		logger.info("details tab clicked");
	}

	public void UpdateSortOredrValueAndClickSave(String value) {
		driver.waitForPageLoad();
		driver.type(By.xpath(".//*[@id='txtSortIndex']"), value);
		logger.info("Sort order value updated");
		driver.click(SAVE_BUTTON_DETAILS_TAB_LOC,"");

	}

	public boolean verifySuccessMessageForDetails() {
		// TODO Auto-generated method stub
		return driver.isElementPresent(By.xpath(".//*[@id='messageCenter']//div[contains(text(),'Details saved!')]"));
	}

	public void SelectCategoryOnCategoriesTabAndClickSave() {
		driver.click(By.id("category2"),"");
		logger.info("category Selected");
		driver.click(By.id("btnSave"),"");
		logger.info("Save Categories Button clicked");

	}

	public void clickSearchAProduct(String SKU) {
		driver.type(By.id("txtProductRelationshipSearch"), SKU);
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(By.xpath("//h3[contains(text(),'Select a Product:')]/following::div[1]"));
		driver.click(By.xpath("//h3[contains(text(),'Select a Product:')]/following::div[1]"),"");
		logger.info("searched product clicked");

	}

	public void selectRelationshipTypeAndClickArrowButton() {
		driver.click(By.xpath(".//*[@id='relationshipType']//option[contains(text(),'Refill')]"),"");
		logger.info("relationship type selected");
		driver.click(By.xpath(".//*[@id='btnAddRelation']/img"),"");
		logger.info("arrow button clicked");

	}

	public void SelectCatalogAndClickSave() {
		// TODO Auto-generated method stub
		driver.click(By.xpath(".//*[@id='catalog1']/following::input[1]"),"");
		logger.info("catalog selected");
		driver.click(By.id("btnSave"),"");
		logger.info("save catalog button clicked");
	}

	public boolean verifySuccessMessageForCatalogSaved() {
		// TODO Auto-generated method stub
		return driver.isElementPresent(By.xpath(".//*[@id='messageCenter']/div[contains(text(),'Availability saved!')]"));
	}

	public void selectallCartsAndClickSave() {
		driver.click(By.xpath(".//label[contains(text(),'All Carts')]/preceding::input[1]"),"");
		logger.info("all carts selected");
		driver.click(By.id("btnSave"),"");
		logger.info("Product properties saved button clicked");

	}

	public boolean verifySuccessMessageForProductPropertiesSaved() {
		return driver.isElementPresent(By.xpath(".//*[@id='messageCenter']/div[contains(text(),'Properties saved!')]"));
	}

	public boolean verifySuccessMessageForCategorySelected() {
		return driver.isElementPresent(By.xpath(".//*[@id='messageCenter']/div[contains(text(),'Categories saved!')]"));
	}

	public void clickAccountsTab() {
		driver.waitForPageLoad();
		driver.click(ACCOUNTS_TAB_LOC,"");
		logger.info("accounts tab clicked");
	}

	public void clickSearchConsultant(String accountNumber) {
		driver.type(By.xpath(".//label[contains(text(),'CID')]/following::input[1]"), accountNumber,"account number");
	}

	public void clickSearchIcon() {
		driver.click(SEARCH_ICON_OREDRS_TAB_LOC,"search icon");
	}

	public void clickConsultantId(String accountNumber) {
		driver.click(By.xpath(String.format(ConsultantIdOnBrowseAccountsPage, accountNumber)),"");
		logger.info("Consultant Id clicked");
	}

	public void clickSearchSKU(String SKU) {
		driver.type(By.id("txtQuickAddSearch"), SKU);
		driver.pauseExecutionFor(5000);
		driver.waitForElementPresent(By.xpath("//p[contains(text(),'SKU or Name:')]/div/div[1]"));
		driver.click(By.xpath("//p[contains(text(),'SKU or Name:')]/div/div[1]"),"");
		driver.pauseExecutionFor(2000);
	}

	public void clickAddToOrder() {
		driver.click(By.id("btnQuickAdd"),"");
		driver.pauseExecutionFor(1000);
	}

	public boolean verifyProductNamePresentOnOrderDetailsPage(String SKU) {
		// TODO Auto-generated method stub
		return driver.isElementVisible(By.xpath(String.format(ProductNameOnOrderDetailsPage, SKU)));
	}

	public String getOrderName() {
		String orderNum = driver.findElement(By.xpath("//a[contains(text(),'Order #')]")).getText().split("\\#")[1];
		logger.info(orderNum);
		return orderNum;
	}

	public void clickConsultantNameOnOrderDetailsPage() {
		driver.click(By.xpath(".//*[@class='CustomerLabel']/a"),"");
		logger.info("consultant name clicked");

	}

	public void clickSearchOrderId(String orderNum) {
		driver.type(By.xpath("//span[contains(text(),'ID')]/following::input[1]"), orderNum);
		logger.info("order num searched is"+orderNum);
		driver.pauseExecutionFor(1000);
	}

	public boolean verifyOrderDisplayedInSearchResult(String orderNum) {
		// TODO Auto-generated method stub
		return driver.isElementVisible(By.xpath(String.format(OrderIdSearchResultOverviewPage, orderNum)));
	}

	public void dismissPulsePopup() {
		try {
			driver.click(PULSE_DISMISS_BUTTON_LOC,"");
			logger.info("clicked on dismiss button on pulse homepage");
		}catch(Exception E) {
			logger.info("Pulse Dismiss popup not present");
		}

	}

	public void clickRenewLater(){
		if(driver.isElementPresent(RENEW_LATER_LINK,5))
			driver.click(RENEW_LATER_LINK,"");
		driver.waitForPageLoad();
	}

	public void clickAddNewShippingProfileLink() {
		driver.quickWaitForElementPresent(ADD_SHIPPING_PROFILE_ADD_LINK_LOC);
		driver.click(ADD_SHIPPING_PROFILE_ADD_LINK_LOC,"");
		logger.info("Shipping Profile -Add link clicked");
		driver.waitForPageLoad();
	}

	public void editPaymentProfile(String firstName,String lastName,String nameOnCard,String cardNumber,String CVV) {
		driver.type(ADD_PAYMENT_METHOD_FIRST_NAME_LOC, firstName);
		logger.info("first name is"+firstName);
		driver.type(ADD_PAYMENT_METHOD_LAST_NAME_LOC, lastName);
		logger.info("last name is"+lastName);
		driver.type(ADD_PAYMENT_METHOD_NAME_ON_CARD_LOC, nameOnCard);
		logger.info("name on card is"+nameOnCard);
		driver.type(ADD_PAYMENT_METHOD_CREDIT_CARD_NO_LOC, cardNumber);
		logger.info("card number is"+cardNumber);
		driver.type(By.id("cvvNumber"), CVV);
		//select 'Expiration' Date(change year)
		Select sel = new Select(driver.findElement(ADD_NEW_PAYMENT_EXPIRATION_YEAR_DROP_DOWN_LOC));
		sel.selectByIndex(7);
		driver.type(By.id("profileName"), firstName+" "+lastName);
		driver.pauseExecutionFor(2000);
	}

	public void selectTemplateStatus(String status){
		driver.waitForElementPresent(PULSE_STATUS_DD_LOC);
		driver.click(PULSE_STATUS_DD_LOC,"");
		logger.info("Pulse Status Dropdown is clicked");
		driver.waitForElementPresent(By.xpath(String.format(pulseStatusLoc, status)));
		driver.click(By.xpath(String.format(pulseStatusLoc, status)),"");
		logger.info("Pulse status"+status+" is selected from dropdown");
	}

	public boolean isCRPOrPulseTemplateStatusPresent(String status) {
		return driver.isElementPresent(By.xpath(String.format(pulseOrCRPStatusOnTemplateLoc, status)), 15);
	}

	public boolean isEditPresentForCRPTemplatePresent(){
		driver.waitForElementPresent(EDIT_CRP_TEMPLATE_LOC);
		return driver.isElementPresent(EDIT_CRP_TEMPLATE_LOC);
	}

	public void enterOrderStatus(String Status) {
		driver.type(ORDER_STATUS_TEXTBOX_LOC, Status);
		logger.info("Entered order status is "+Status);
	}

	public void clickEditBillingProfile() {
		driver.waitForElementPresent(EDIT_BILLING_PROFILE_LOC);
		driver.clickByJS(RFWebsiteDriver.driver, EDIT_BILLING_PROFILE_LOC,"");
		logger.info("clicked on Edit billing profile");
	}	


	public void selectByLocation(String locationValue) {
		driver.click(LOCATION_DD_LOC,"");
		logger.info("clicked on location DD");
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath(String.format(locationOptionLoc, locationValue)),"");
		logger.info("selected option is:"+locationValue);
	}

	public boolean isSearchResultContainsFirstAndLastName(String accountNumber,String firstName,String lastName) {
		driver.waitForElementToBeVisible(By.xpath(String.format(firstNameSearchResultLoc, accountNumber)), 10000);
		return driver.getText(By.xpath(String.format(firstNameSearchResultLoc, accountNumber))).contains(firstName) && driver.getText(By.xpath(String.format(lastNameSearchResultLoc, accountNumber))).contains(lastName);
	}

	public void addPaymentMethod(String firstName,String lastName,String nameOnCard,String cardNumber, String CVV, String attentionName){
		driver.waitForElementPresent(ADD_NEW_PAYMENT_METHOD_LINK);
		driver.click(ADD_NEW_PAYMENT_METHOD_LINK,"");
		logger.info("Add new payment method link clicked");
		//enter billing info
		//select 'Main-Billing' as billing address-
		driver.click(ADD_NEW_BILLING_ADDRESS_DROP_DOWN_LOC,"");
		driver.click(By.xpath("//select[@id='existingAddress']/option[2]"),"");
		driver.type(ADD_PAYMENT_METHOD_FIRST_NAME_LOC, firstName,"First name entered for billing profile");
		driver.type(ADD_PAYMENT_METHOD_LAST_NAME_LOC, lastName,"last name entered for billing profile");
		driver.type(ADD_PAYMENT_METHOD_NAME_ON_CARD_LOC, nameOnCard,"name on card entered for billing profile");
		driver.type(ADD_PAYMENT_METHOD_CREDIT_CARD_NO_LOC, cardNumber,"card number entered for billing profile");
		driver.type(By.id("cvvNumber"), CVV,"CVV");
		driver.type(By.id("attention"), attentionName,"attention name");
		Select sel = new Select(driver.findElement(ADD_NEW_PAYMENT_EXPIRATION_YEAR_DROP_DOWN_LOC));
		sel.selectByIndex(7);
		driver.type(By.id("profileName"), firstName+" "+lastName,"profile name");
	}

	public boolean isCreateForCRPTemplatePresent(){
		driver.waitForElementPresent(CREATE_CRP_TEMPLATE_LOC);
		return driver.isElementPresent(CREATE_CRP_TEMPLATE_LOC);
	}

	public void clickCreateCRPTemplate() {
		driver.waitForElementPresent(CREATE_CRP_TEMPLATE_LOC);
		driver.click(CREATE_CRP_TEMPLATE_LOC,"");
		logger.info("Clicked on create CRP Template");
	}

	public boolean isCreateForPulseTemplatePresent(){
		driver.waitForElementPresent(CREATE_PULSE_TEMPLATE_LOC);
		return driver.isElementPresent(CREATE_PULSE_TEMPLATE_LOC);
	}

	public void clickCreatePulseTemplate() {
		driver.waitForElementPresent(CREATE_PULSE_TEMPLATE_LOC);
		driver.click(CREATE_PULSE_TEMPLATE_LOC,"");
		logger.info("Clicked on create Pulse Template");
	}

	public void enterSKUValues(String sku){
		driver.quickWaitForElementPresent(SKU_SEARCH_FIELD);
		driver.type(SKU_SEARCH_FIELD, sku);
		logger.info("sku value entered is "+sku);
	}

	public void changeDefaultBillingProfile(int index) {
		Select select = new Select(driver.findElement(By.id("sPaymentMethod")));
		select.selectByIndex(index);
		logger.info("billing profile selcted by index"+index);
	}

	public boolean isNewlyAddedSKUDisplayedOnCRPTemplate(String SKU) {
		driver.waitForElementPresent(By.xpath(String.format(newlyAddedSKUOnCRPTemplate, SKU)));
		return driver.isElementPresent(By.xpath(String.format(newlyAddedSKUOnCRPTemplate, SKU)));

	}

	public boolean verifyUserIsLoggedIn() {
		driver.waitForPageLoad();
		clickRenewLater();
		if(driver.getCurrentUrl().contains("pulserfo")) {
			driver.waitForElementPresent(By.xpath("//div[@class='UserInfo']"));
			return driver.isElementVisible(By.xpath("//div[@class='UserInfo']"));
		}
		else {
			driver.waitForElementPresent(By.xpath("//a[contains(text(),'Log-Out')]"));
			return driver.isElementVisible(By.xpath("//a[contains(text(),'Log-Out')]"));
		}
	}

	public void editShippingProfile(String profileName, String attentionCO) {
		driver.type(ADD_SHIPPING_ADDRESS_PROFILE_NAME_LOC, profileName);
		logger.info("profile name name is"+profileName);
		driver.type(ADD_SHIPPING_ADDRESS_ATTENTION_LOC, attentionCO);
		logger.info("attentionco is"+attentionCO);	
	}

	public String getShippingProfileName() {
		driver.pauseExecutionFor(2000);
		return driver.getText(SHIPPING_PROFILE_NAME_LOC);
	}

	public void changeDefaultBillingProfile(String visibleText) {
		driver.pauseExecutionFor(2000);
		Select select = new Select(driver.findElement(By.id("sPaymentMethod")));
		select.selectByVisibleText(visibleText);
		logger.info("billing profile selcted by index"+visibleText);
	}


	public void clickSaveTemplate() {
		driver.click(SAVE_TEMPLATE_BTN,"");
		logger.info("clicked on save template button");
		//driver.waitForElementToBeInVisible(SAVE_TEMPLATE_BTN, 15);
		driver.waitForNSCore4ProcessImageToDisappear();
		driver.pauseExecutionFor(5000);
	}

	public void clickBrowseAccounts() {
		driver.click(BROWSE_ACCOUNT_LOC,"");
		logger.info("Browse Account link clicked in account tab");
		driver.waitForPageLoad();

	}
	public String getAddedProductPriceBasedOnSKU(String SKU){
		driver.waitForElementPresent(By.xpath(String.format(productPriceBasedOnSKULoc, SKU)));
		String originalPrice = driver.getText(By.xpath(String.format(productPriceBasedOnSKULoc,SKU)));
		originalPrice = originalPrice.split("\\$")[1];
		logger.info("Product price for product sku "+SKU+" is "+originalPrice);
		return originalPrice;
	}

	public String updatePriceForProduct(String orignalPrice,String updateByValue){
		double originalValue = Double.parseDouble(orignalPrice);
		double updateByPrice = Double.parseDouble(updateByValue);
		double updatedPrice = originalValue+updateByPrice;
		String finalPrice = Double.toString(updatedPrice);
		logger.info("Updated price for product is "+finalPrice);
		return finalPrice;
	}

	public String clickAndReturnAccountnumber(int rowNum){
		driver.waitForElementPresent(By.xpath(String.format(rowNumber, rowNum,rowNum)));
		String accountNumber=driver.findElement(By.xpath(String.format(rowNumber, rowNum,rowNum))).getText();
		driver.click(By.xpath(String.format(rowNumber, rowNum,rowNum)),"");
		logger.info("Account Number "+accountNumber+" clicked.");
		driver.waitForPageLoad();
		return accountNumber;
	}

	public String updatePriceForProduct(String orignalPrice,String updateByValue,String operation){
		double updatedPrice = 0.0;
		double originalValue = Double.parseDouble(orignalPrice);
		double updateByPrice = Double.parseDouble(updateByValue);
		if(operation.equalsIgnoreCase("Add")) {
			updatedPrice = originalValue+updateByPrice;
		}else if(operation.equalsIgnoreCase("Subtraction")) {
			updatedPrice = originalValue-updateByPrice;
		}
		String finalPrice = Double.toString(updatedPrice);
		logger.info("Updated price for product is "+finalPrice);
		return finalPrice;
	}

	public String updateQVForProduct(String orignalQV,String updateByValue,String operation){
		double updatedQV = 0.0;
		double originalValue = Double.parseDouble(orignalQV);
		double updateByPrice = Double.parseDouble(updateByValue);
		if(operation.equalsIgnoreCase("Add")) {
			updatedQV = originalValue+updateByPrice;
		}else if(operation.equalsIgnoreCase("Subtraction")) {
			updatedQV = originalValue-updateByPrice;
		}
		String finalQV = Double.toString(updatedQV);
		logger.info("Updated price for product is "+finalQV);
		return finalQV;
	}

	public String getAddedProductQVBasedOnSKU(String SKU){
		driver.waitForElementPresent(By.xpath(String.format(productQVBasedOnSKULoc, SKU)));
		String originalQV = driver.getText(By.xpath(String.format(productQVBasedOnSKULoc,SKU)));
		logger.info("Product price for product sku "+SKU+" is "+originalQV);
		return originalQV;
	}

	public void enterQVValueInOverrideOrderWindow(String QV,String SKU) {
		driver.pauseExecutionFor(2000);
		driver.findElement(By.xpath(String.format(skuBasedProductQVSOOPopupLoc, SKU))).sendKeys(Keys.CONTROL + "a");
		driver.type(By.xpath(String.format(skuBasedProductQVSOOPopupLoc, SKU)), QV);
		logger.info("price entered is:"+QV);
	}

	public String getBillingProfileEnteredCardName() {
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(PAYMENT_PROFILE_CARD_NAME_LOC);
		if(driver.isElementVisible(PAYMENT_PROFILE_CARD_NAME_LOC)) {
			return driver.getText(PAYMENT_PROFILE_CARD_NAME_LOC);
		}else {
			return driver.getText(PAYMENT_PROFILE_CARD_NAME_SECOND_LOC);
		}
	}

	public void clickCustomerNameOnOrderDetailsPage(String accountNumber) {
		driver.click(By.xpath(String.format(customerLabelNameLoc, accountNumber)),"");
		logger.info("Clicked on Customer Label name on Order Details Page");
	}

	public String addAndGetMultipleProductSKU(String quantity){
		driver.click(OPEN_BULK_ADD_LOC,"open bulk add");
		driver.waitForNSCore4LoadingImageToDisappear();
		driver.waitForElementPresent(PRODUCT_SKU_VALUE);
		String SKU = driver.findElement(PRODUCT_SKU_VALUE).getText();
		for(int i=1;i<=3;i++) {
			driver.type(By.xpath("//table[@id='bulkProductCatalog']//tr["+i+"]//input[@class='quantity']"), quantity,"quantity");
		}
		driver.click(ADD_TO_ORDER_BTN,"Add to Order button");
		driver.click(CLOSE_LINK_LOC,"Close Link");
		return SKU;
	}

	public void changeAmountOfItemPerPage(String value) {
		Select select = new Select(driver.findElement(ITEMS_PER_PAGE_LOC));
		select.selectByVisibleText(value);
		driver.pauseExecutionFor(2000);
	}

	public boolean verifyTheSearchResultsAreDisplayed(String firstName,String lastName){
		driver.waitForElementPresent(ORDERS_SEARCH_RESULTS);
		return driver.isElementPresent(By.xpath(String.format("//a[contains(text(),'%s')]/preceding::div[2]", firstName)))
				&& driver.isElementPresent(By.xpath(String.format("//a[contains(text(),'%s')]/preceding::div[2]",firstName)));
	}
	public void enterFirstNameOrdersPage(String firstName) {
		driver.type(FIRST_NAME_ORDERS_PAGE, firstName);
		logger.info("first name is"+firstName);		
	}

	public void enterLastNameOrdersPage(String lastName) {
		driver.type(LAST_NAME_ORDERS_PAGE, lastName);
		logger.info("last name is"+lastName);		
	}

	public void clickRemoveDonationProductFromCart() {
		if(driver.isElementPresent(REMOVE_DONATION_PRODUCT_LINK_LOC))
			driver.click(REMOVE_DONATION_PRODUCT_LINK_LOC,"Remove link donation product");
	}

	public String clickAndGetCommissionDateFromOrderDetailsPage() {
		driver.waitForElementPresent(COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC);
		String commissionDate =  driver.getAttribute(COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC,"value");
		logger.info("CommissionDate from order details page "+commissionDate);
		driver.click(COMMISSION_DATE_FROM_ORDER_DETAILS_PAGE_LOC,"");
		return commissionDate;
	}

	public String clickAndReturnPendingOrderNumberFromOverviewPage(){
		String pendingOrderNo = driver.getText(PENDING_ORDER_NUMBER_LOC,"pending order number");
		driver.click(PENDING_ORDER_NUMBER_LOC,"");
		logger.info("Pending Order Number: "+pendingOrderNo+" Clicked");
		driver.waitForPageLoad();
		return pendingOrderNo;
	}

	public void clickEditOrder() {
		driver.click(EDIT_ORDER_LOC,"");
		logger.info("Edit order link is clicked");
		driver.waitForPageLoad();

	}

	public String getUpdationMessage(){
		return driver.getText(GET_UPDATION_MSG,"updation msg");
	}


	public void clickUseAsEnteredbtn(){
		try{
			if(driver.isElementPresent(USE_ADDRESS_AS_ENTERED)==true){
				driver.click(USE_ADDRESS_AS_ENTERED,"Usee address as entered button");
			}else{
				driver.click(ACCEPT_BTN,"Accept button");
			}
		}catch(Exception e){
			driver.waitForElementPresent(USE_AS_ENTERED_BTN);
			driver.click(USE_AS_ENTERED_BTN,"");
			logger.info("Use as entered button clicked for Account record");
		}
		driver.waitForPageLoad();
	}	

	public String convertDBValueUptoTwoDecimal(String value) {
		Double valueToBeEvaluated=Double.parseDouble(value);
		Double valueAfterEvaluated=(Math.round(valueToBeEvaluated * 10000.00)/10000.00);
		logger.info("value after convert:"+valueAfterEvaluated);
		return Double.toString(valueAfterEvaluated);
	}

	public void clickPulseOnAccountOverviewPage() {
		String parentWinHandle =driver.getWindowHandle();
		driver.click(PULSE_LINK_OVERVIEW_PAGE_LOC,"Pulse");
		driver.switchToChildWindow(parentWinHandle);
		logger.info("Switched to second tab");
		driver.waitForPageLoad();
	}

	public void clickRunReport() {
		driver.click(RUN_REPORT_ON_PULSE_LOC,"Run Report");
	}

	public void clickOrdersTabOnPulse() {
		driver.click(ORDERS_TAB_ON_PULSE_HOMEPAGE_LOC,"Orders Tab");
	}

	public void selectMonthForOrderPeriod(String monthName) {
		driver.click(ORDER_PERIOD_ON_PULSE_ORDER_TAB_LOC,"Order Period");
		driver.click(By.xpath(String.format(monthNameOnOrderPeriodLoc, monthName)),"Month Name");

	}

	public String getShippingFromPulseOrderDetails() {
		return driver.getText(SHIPPING_ON_PULSE_ORDER_LOC,"Shipping");
	}

	public String getOrderTotalFromPulseOrderDetails() {
		return driver.getText(ORDER_TOTAL_ON_PULSE_LOC,"Order Total");
	}


	public String getMonthNameFromDate(String date) {
		String monthName=null;
		logger.info("Date is: "+date);
		String date1=date.split("-")[1];
		int month=Integer.parseInt(date1);
		logger.info("month after split is: "+month);

		switch(month) {
		case 1:
			monthName= "Jan";
			break;
		case 2:
			monthName= "Feb";
			break;
		case 3:
			monthName="Mar";
			break;
		case 4:
			monthName= "Apr";
			break;
		case 5:
			monthName= "May";
			break;
		case 6:
			monthName= "June";
			break;
		case 7:
			monthName="Jul";
			break;
		case 8:
			monthName= "Aug";
			break;
		case 9:
			monthName="Sep";
			break;
		case 10:
			monthName="Oct";
			break;
		case 11:
			monthName= "Nov";
			break;
		case 12:
			monthName= "Dec";
			break;

		default:
			System.out.println("Not a valid match");
			break;
		}
		logger.info("Month name is: "+monthName);
		return monthName;
	}	

	public void clickSavePaymentMethodBtn(){
		driver.quickWaitForElementPresent(SAVE_PAYMENT_METHOD_BTN_LOC);
		driver.click(SAVE_PAYMENT_METHOD_BTN_LOC,"");
		try{
			driver.waitForElementToBeVisible(USE_ADDRESS_AS_ENTERED_BTN_LOC,10);
			driver.pauseExecutionFor(2000);
			driver.click(USE_ADDRESS_AS_ENTERED_BTN_LOC,"Use Address as Entered Btn");
			driver.pauseExecutionFor(2000);
			driver.waitForElementToBeInVisible(SAVE_PAYMENT_METHOD_BTN_LOC, 5);
		}catch(Exception e){
			e.getMessage();
			logger.info("'Use Address Ad Entered' PopUp not displayed");
		}
		driver.waitForPageLoad();
	}

	public void addAndGetSpecficProductSKU(String quantity){
		driver.waitForElementPresent(SKU_FIELD_SEARCH);
		//Enter value in SKU search field
		driver.click(SKU_FIELD_SEARCH,"");
		driver.type(SKU_FIELD_SEARCH, "AARG001");
		driver.waitForElementPresent(By.xpath("//*[@id='ContentWrap']/table/tbody/tr/td[2]/table[2]/tbody/tr/td[2]/p[1]/div/div/p"));
		driver.click(By.xpath("//*[@id='ContentWrap']/table/tbody/tr/td[2]/table[2]/tbody/tr/td[2]/p[1]/div/div/p"),"");
		//String SKU = driver.findElement(PRODUCT_SKU_VALUE).getText();
		//Enter Quantity for product
		driver.type(By.xpath("//*[@id='txtQuickAddQuantity']"), quantity);
		logger.info("quantity added is: "+quantity);
		//Click Add button.
		driver.click(By.xpath("//*[@id='btnQuickAdd']"),"");
		logger.info("Add to order button clicked");
		driver.waitForNSCore4ProcessImageToDisappear();
		//driver.click(CLOSE_LINK_LOC);
	}

	public void clickEditPaymentProfile() {
		driver.clickByJS(RFWebsiteDriver.driver, EDIT_PAYMENT_PROFILE, "Edit Payment Profile");//(EDIT_PAYMENT_PROFILE,"");
		driver.pauseExecutionFor(2000);
	}

	public void searchUserOnTheBasisOfEmailAddress(String emailAddress) {
		driver.waitForElementPresent(EMAIL_ADDRESS_IN_ACCOUNT_TAB_LOC);
		driver.type(EMAIL_ADDRESS_IN_ACCOUNT_TAB_LOC, emailAddress);
		logger.info("Email entered is: "+emailAddress);
		driver.click(GO_SEARCH_BTN,"");
		logger.info("clicked on search button");
		driver.waitForNSCore4LoadingImageToDisappear();
	}

	public void typeSomeStoryAndclickSaveStory(String title,String Story) {
		driver.quickWaitForElementPresent(STORY_TITLE_LOC);
		driver.type(STORY_TITLE_LOC, title+"\t");
		driver.switchTo().frame(driver.findElement(OUTER_FRAME_ID));
		System.out.println("inside frame one comes");
		driver.switchTo().frame(driver.findElement(INNER_FRAME_ID));
		driver.pauseExecutionFor(2000); 
		driver.waitForElementPresent(STORY_TEXT_LOC);
		driver.type(STORY_TEXT_LOC, Story);
		driver.switchTo().defaultContent();
		driver.click(SAVE_STORY_BUTTON,"");
		if(driver.isElementVisible(SAVE_STORY_BUTTON)){
			driver.clickByJS(RFWebsiteDriver.driver,SAVE_STORY_BUTTON,"");	
		}
		logger.info("After entering story save button clicked");
		driver.waitForPageLoad();
	}		 

	public boolean isOrderWithPendingStatusPresent() {
		boolean pendingOrder=false;
		int totalSearchedRecords = driver.findElements(TOTAL_ORDERS_PRESENT_LOC).size();
		for(int i=1;i<=totalSearchedRecords;i++){
			if(driver.isElementPresent(By.xpath(String.format(PendingOrderLoc,i)))){
				pendingOrder=true;
				break;
			}else{
				continue;
			}
		}
		return pendingOrder;
	}

	public NSCore4OrdersTabPage clickRandomPendingOrderID() {
		int totalSearchedRecords = driver.findElements(TOTAL_ORDERS_PRESENT_LOC).size();
		for(int i=1;i<=totalSearchedRecords;i++){
			if(driver.isElementPresent(By.xpath(String.format(PendingOrderLoc,i)))){
				driver.click(By.xpath(String.format(PendingOrderLoc,i)),"");
				break;
			}
		}
		return new NSCore4OrdersTabPage(driver);
	}

	public void clickOrderDetailsOnPulseOrdersTab(String orderNumber) {
		driver.click(By.xpath(String.format(orderDetailsOnPulseLoc, orderNumber)),"Order Details Link");
		driver.waitForNSCore4ProcessImageToDisappear();
	}

	public void clickAccountRecordOnBasisOfAccountNumber(String accountNumber) {
		driver.waitForNSCore4LoadingImageToDisappear();
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(accountRecordOnBasisOfAccountNumberLoc, accountNumber)),"account record");
	}

	public void clickEditBillingProfileOnBasisOfProfileName(String profileName) {
		driver.click(By.xpath(String.format(editBillingProfileLINK, profileName)), profileName);
	} 

	public String getTaxFromPulseOrderDetails() {
		driver.waitForElementPresent(TAX_FROM_PULSE_ORDER_LOC);
		return driver.getText(TAX_FROM_PULSE_ORDER_LOC,"Tax");
	}

	public String getBillingProfileName() {
		try {
			if(driver.findElement(PAYMENT_PROFILE_NAME_LOC).isDisplayed()) {
				driver.waitForElementPresent(PAYMENT_PROFILE_NAME_LOC);
				driver.pauseExecutionFor(3000);
				return driver.getText(PAYMENT_PROFILE_NAME_LOC);
			}else {
				throw new Exception();
			}

		}catch(Exception e) {
			return driver.getText(PAYMENT_PROFILE_SECOND_LOC);
		}

	}	 

	public String verifyProductreflectedInCurrentRelationshipSection() {
		// TODO Auto-generated method stub
		driver.waitForElementPresent(By.xpath(".//*[@id='relations']/option"));
		return driver.findElement(By.xpath(".//*[@id='relations']/option")).getText();
	}

	public void clickEditMyStoryLink(){
		driver.quickWaitForElementPresent(EDIT_MY_STORY_LINK);
		driver.clickByJS(RFWebsiteDriver.driver, EDIT_MY_STORY_LINK, "");//(,"");
		logger.info("Edit my story link is clicked");
		driver.waitForPageLoad();
	}

	public void clickGoBtnOfSearch(){
		driver.waitForElementPresent(GO_SEARCH_BTN);
		driver.click(GO_SEARCH_BTN,"");
		driver.pauseExecutionFor(2000);
		logger.info("Search Go button clicked");
		driver.waitForNSCore4LoadingImageToDisappear();
	}	

	public boolean isFirstAndLastNamePresentinSearchResults(String firstName,String lastName){
		driver.waitForNSCore4LoadingImageToDisappear();
		return driver.getText(By.xpath("//span[text()='CID']/following::div[@class='ng-isolate-scope']//div[2]/div")).equalsIgnoreCase(firstName) && driver.getText(By.xpath("//span[text()='CID']/following::div[@class='ng-isolate-scope']//div[3]/div")).equalsIgnoreCase(lastName);
	}

	public String getAccountStatusOfAccount(String accountNumber){
		return driver.getText(By.xpath("//*[text()='"+accountNumber+"']/following::div[contains(@class,'ng-binding')][4]"));
	}

	public void clickPlaceNewOrderLink(){
		driver.pauseExecutionFor(4000);
		driver.waitForElementToBeVisible(PLACE_NEW_ORDER_LINK_LOC,10);
		driver.click(PLACE_NEW_ORDER_LINK_LOC,"Place-New-Order' link");
		driver.waitForPageLoad(); 
	}


	public void clickEditPulseTemplate() {
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(EDIT_PULSE_TEMPLATE_LOC);
		driver.click(EDIT_PULSE_TEMPLATE_LOC,"");
		logger.info("clicked on Edit CRP Template");
	}


	public void acceptQASPopup() {
		driver.pauseExecutionFor(2000);
		if (driver.isElementVisible(ACCEPT_QAS_POPUP_LOC)) {
			System.out.println("Entered in Accept QAS Popup");
			driver.click(ACCEPT_QAS_POPUP_LOC,"");
			logger.info("clicked on Accept button on QAS Popup");
			driver.pauseExecutionFor(3000);
		}
	}


	public void clickEditPCAutoshipTemplate() {
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(EDIT_PC_AUTOSHIP_TEMPLATE_LOC);
		driver.click(EDIT_PC_AUTOSHIP_TEMPLATE_LOC,"");
		logger.info("clicked on Edit PC Autoship Template");
	}

	public void selectGender(String gender){
		driver.waitForElementPresent(GENDER_DD);
		driver.clickByJS(RFWebsiteDriver.driver, GENDER_DD,"");//(GENDER_DD,"");
		logger.info("Gender DD clicked");
		driver.click(By.xpath(String.format(genderDDValue, gender)),"");
		logger.info("Gender selected as: "+gender);
	}

	public void clickPulseMonthlySubscriptionEdit(){
		driver.pauseExecutionFor(3000);
		driver.waitForElementPresent(PULSE_MONTHLY_SUBSCRIPTION_EDIT);
		driver.click(PULSE_MONTHLY_SUBSCRIPTION_EDIT,"");
		logger.info("pulse monthly subscription edit clicked");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(2000);
	}

	public void clickPCBiMonthlyReplenishmentDelay(){
		driver.click(PC_BI_MONTHLY_REPLNISHMENT_DELAY, "PC Bi Monthly Replenishment Delay link");
		driver.waitForPageLoad();
	}

	public void clickPCBiMonthlyReplenishmentEdit(){
		driver.click(PC_BI_MONTHLY_REPLNISHMENT_EDIT, "PC Bi Monthly Replenishment Edit link");
		driver.waitForPageLoad();
	}

	public String selectDelayPeriodAndGetNextDate(String delayPeriod){
		String nextDueDate="";
		driver.click(By.xpath(String.format(radioButtonForPCAutoshipDelay, delayPeriod)), "radioButton For PC Autoship Delay for period="+delayPeriod);
		String nextDueDateString = driver.getText(By.xpath(String.format(nextDateStringForPCAutoshipDelay, delayPeriod)));
		String[] nextDueDateArray=nextDueDateString.split("\\(")[0].trim().split("/");
		for(int i=0;i<=2;i++){
			if(nextDueDateArray[i].charAt(0)=='0'){
				nextDueDate = nextDueDate+nextDueDateArray[i].substring(1, nextDueDateArray[i].length());
			}
			else{
				nextDueDate=nextDueDate+nextDueDateArray[i];
			}

			if(i<2)
				nextDueDate=nextDueDate+"/";
		}
		logger.info("Next due date selected is "+nextDueDate);
		return nextDueDate;
	}

	public boolean isAutoshipTemplateContainsNextDueDate(String nextDueDate){
		return driver.getText(By.xpath("//b[contains(text(),'Next')]/.."), "Next Due date").contains(nextDueDate);
	}

	public boolean isOnAutoshipsSectionOnOverviewExpectedTextVisible(String text){
		return driver.isElementVisible(By.xpath("//td[@id='accountInfo']/div[2]//li[contains(text(),'"+text+"')]"),10);
	}

	public void clickOnSiteSubscriptionLink(){
		driver.click(SITE_SUBSCRIPTION_LINK_LOC, "Site subscription link");
		driver.waitForPageLoad();
	}

	public String getPulseEmailDomainSuffix(){
		return driver.getText(By.xpath("//input[@id='PulseEmailAddress']/.."), "pulse email domain suffix");
	}

	public void clickOnCancelLink(){
		driver.click(By.linkText("Cancel"), "Cancel link");
	}

	public void acceptAlert(){
		driver.waitForAlert();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.waitForPageLoad();
	}

	public void clickChangeAutoshipDate(){
		driver.click(By.id("BtnChange"), "change autoship date buton");
		driver.waitForPageLoad();
	}

	public void clickBackButtonOnAutoshipDateChangeConfirmationPage(){
		driver.click(By.id("BtnBack"), "Back Button On Autoship Date Change Confirmation Page");
		driver.waitForPageLoad();
	}

	public void clickGoBtnOfSearch(String accountNumber){
		driver.click(GO_SEARCH_BTN,"Search Go button");
		driver.click(By.linkText(accountNumber),"Account Number");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(3000);
	}	

	public void addANewShippingProfile(String profileName,String attention,String addressLine1,String zipCode){
		driver.waitForElementPresent(By.id("cityC"));
		Select cityDD = new Select(driver.findElement(By.id("cityC")));
		driver.type(ADD_SHIPPING_ADDRESS_PROFILE_NAME_LOC, profileName);
		driver.type(ADD_SHIPPING_ADDRESS_ATTENTION_LOC, attention);
		driver.type(ADD_SHIPPING_ADDRESS_LINE1_LOC, addressLine1);
		driver.type(ADD_SHIPPING_ADDRESS_ZIPCODE_LOC, zipCode+"\t");
		driver.waitForNSCore4LoadingImageToDisappear();
		driver.pauseExecutionFor(2000);
		cityDD.selectByIndex(1);
		driver.click(STATE_DD_LOC,"");
		driver.click(STATE_DD_OPTION_LOC,"");
		driver.pauseExecutionFor(3500);
	}

	public void closeChildAndSwitchToParentWindow(String parentWindowHandle){
		int totalNoOfWindows = driver.getWindowHandles().size();
		if(totalNoOfWindows>1){
			driver.close();
			logger.info("child window closed");
			driver.switchTo().window(parentWindowHandle);
			logger.info("Child window closed and swiched to Parent");
		}
	}	

	public String getStatus(){
		driver.pauseExecutionFor(3000);
		return driver.getText(STATUS_LINK_LOC);
	}

	public void clickOverviewLink() {
		driver.click(OVERVIEW_LINK_LOC, "Overview Link");
	}

	public boolean isAutoshipCancelledOnSiteSubscriptionPage() {
		return driver.isElementVisible(AUTOSHIP_CANCELLED_STATUS_LOC);
	}

	public void clickOnReactivateLinkOnSiteSubscriptionPage() {
		driver.click(REACTIVATE_LINK_LOC, "Re-activate link");
	}

	public boolean isReactivateLinkPresentOnSiteSubscriptionPage() {
		return driver.isElementVisible(REACTIVATE_LINK_LOC);
	}

	public boolean isAutoshipActiveOnSiteSubscriptionPage() {
		return driver.isElementVisible(AUTOSHIP_ACTIVE_STATUS_LOC);
	}
	
	public boolean isMyAccountDropdownLinkPresent(){
		driver.waitForElementPresent(MY_ACCOUNT_DROPDOWN_LOC);
		return driver.isElementPresent(MY_ACCOUNT_DROPDOWN_LOC);
	}
	
	public void clickMyAccountDropdownLink(){
		driver.waitForElementPresent(MY_ACCOUNT_DROPDOWN_LOC);
		driver.click(MY_ACCOUNT_DROPDOWN_LOC,"My Account Dropdown");
	}
	
	public boolean isLogOutLinkInMyAccountDropdownPresent(){
		driver.waitForElementPresent(LOGOUT_LINK_IN_MY_ACCOUNT_DROPDOWN_LOC);
		return driver.isElementPresent(LOGOUT_LINK_IN_MY_ACCOUNT_DROPDOWN_LOC);
	}
	
	public void clickHeaderLinkAfterLogin(String linkName) {
		if(driver.isElementVisible(MY_ACCOUNT_DROPDOWN_LINK_LOC,5)) {
			driver.click(MY_ACCOUNT_DROPDOWN_LINK_LOC, "Login DropDown");
		}
		driver.click(By.xpath(String.format(myAccountLinkAfterLoginLink, linkName,linkName)),"my account link");
		driver.waitForPageLoad();
	}
	
	public boolean isNewlyCreatedShippingProfilePresent(String shippingProfileName){
		driver.pauseExecutionFor(5000);
		try{
			if(driver.findElement(By.linkText(shippingProfileName)).isDisplayed()) {
				driver.waitForElementPresent(By.linkText(shippingProfileName));
				return driver.isElementPresent(By.linkText(shippingProfileName));
			}else {
				throw new Exception();
			}
		}catch (Exception e) {
			// TODO: handle exception
			driver.quickWaitForElementPresent(By.xpath(String.format(newlyCeatedShippingProfile,shippingProfileName)));
			return driver.isElementPresent(By.xpath(newlyCeatedShippingProfile));
		}

	}
	
	public boolean isSearchResultDisplayedWithExpectedValues(String emailId, String status, String userType){
		return driver.isElementVisible(By.xpath("//div[contains(@id,'grid1')]//div[contains(text(),'"+emailId+"')]/preceding::div[contains(text(),'"+status+"')][1]/preceding::div[contains(text(),'"+userType+"')][1]"), 10);
	}

}