package com.rf.pages.website.heirloom;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import com.rf.core.driver.website.RFWebsiteDriver;
import com.rf.core.utils.CommonUtils;
import com.rf.core.website.constants.TestConstantsRFL;

public class StoreFrontHeirloomHomePage extends StoreFrontHeirloomWebsiteBasePage{

	private static final Logger logger = LogManager
			.getLogger(StoreFrontHeirloomHomePage.class.getName());

	private Actions actions;
	private static String regimenLoc= "//cufontext[text()='%s']/following::a[1]/img";
	private static String genderLocForPCAndRC= "//label[text()='%s']/preceding::input[1]";
	private static String expiryMonthLoc= "//select[contains(@id,'uxMonthDropDown')]//option[@value='%s']";
	private static String expiryYearLoc= "//select[contains(@id,'uxYearDropDown')]//option[@value='%s']";
	private static String regimenHeaderLoc = "//div[@id='HeaderCol']//span[text()='%s']";
	private static String regimenNameLoc= "//cufon[@alt='%s']";
	private static String redefineRegimenSubLinks= "//div[@id='LeftNav']//a/span[text()='REDEFINE']/../following-sibling::ul//span[text()='%s']";
	private static String detailLinkOnProgramIncentivePageLoc = "//div[@id='RFContent']//td[1]/p/strong[text()='%s']/following-sibling::a[1]";
	private static String businessSystemSubTitleLoc = "//div[@id='HeaderCol']//span[text()='%s']";
	private static String subTitleLoc = "//div[@id='HeaderCol']//span[text()='%s']";
	private static String essentialsRegimenSubLinks= "//cufontext[text()='ESSENTIALS']/following::li//span[text()='%s']";
	private static String enhancementsRegimenSubLinks= "//cufontext[text()='ENHANCEMENTS']/following::li//span[text()='%s']";
	private static String regimenNameOnPwsLoc = "//div[@id='LeftNav']//a/span[text()='%s']";
	private static String regimenImageOnPwsLoc = "//div[@id='ProductCategories']//p[@class='productInfo']//span[text()='%s']/../preceding-sibling::p/a";
	private static String consultantEnrollmentKit = "//span[@class='kitPrice']//cufontext[contains(text(),'%s')]/preceding::div[@class='imageWrap'][1]";
	private static String consultantRegimenLoc = "//span[@class='catName']//cufontext[contains(text(),'%s')]/following::img[1]";
	private static String retailPriceOfItem = "//div[contains(@id,'uxStatusPanel')]//div[@class='FloatCol'][1]/div[%s]//span[contains(text(),'Retail')]";
	private static String addToCartBtnLoc = "//div[contains(@id,'uxStatusPanel')]//div[@class='FloatCol'][1]/div[%s]//a[text()='Add to Bag']";
	private static String sectionUnderReplenishmentOrderManagementLoc = "//a[text()='%s']";
	private static String linkUnderMyAccount = "//div[@id='RFContent']//span[contains(text(),'%s')]";
	private static String regimenImageHeaderLoc = "//div[@id='HeaderCol']//cufon[@alt='%s']";
	private static String linkUnderShopSkinCareOrBeAConsultant = "//div[@id='LeftNav']//a/span[text()='%s']";
	private static String viewDetailsOnOrderHistoryPage = "//div[@class='divTable CartTable']//div[@class='divTableBody']/div[%s]//a[contains(text(),'View Details')]";
	private static String orderNumberOnOrderHistoryPage = "//div[@class='divTable CartTable']//div[@class='divTableBody']/descendant::div[%s]/descendant::div[1]";
	private static String PAGE_HEADER_LOC= "//h1[contains(text(),'%s')]";
	private  static String exactCategoryNameTextLoc = "//h1[text()='%s']";
	private static String deleteButtonForProfileLoc = "//span[contains(text(),'%s')]/following::a[text()='Delete'][1]";
	private static String specificprofileInSavedProfilesLoc = "//.[contains(text(),'Saved') and contains(text(),'')]/following-sibling::div[1]//span[contains(text(),'%s')]";
	private static String addToBagButtonForProductNameLoc = "//a[contains(text(),'%s')]/following::span[contains(text(),'Add to Bag')][1]";
	private static String productNameInCartLoc = "//div[contains(text(),'%s')]";
	private static String shadeDDLoc = "//a[contains(text(),'%s')]/following::select[contains(@id,'variantRelation')][1]";
	private static final String countryLoc="//div[contains(@id,'CountryFlags')]/descendant::span[contains(text(),'%s')][1]";

	private static final By COUNTRY_TOGGLE_LOC=By.xpath("//div[contains(@id,'CountryFlags')]/descendant::a[1] | //div[contains(@id,'countryflagslink')]");
	private static final By MY_SHOPPING_BAG_LINK = By.xpath("//a[@class='BagLink'] | //div[@id='shoppingbag']//img"); 	
	private static final By UP_BUTTON = By.id("back-to-top");
	private static final By PULSE_DISMISS_BUTTON_LOC=By.xpath("//span[contains(text(),'Dismiss')]"); 
	private static final By TAX_FOR_KIT_ON_ORDER_REVIEW_PAGE_LOC=By.xpath("//div[@id='OrderDetail']//strong[contains(text(),'Tax')]/following::span[1]");
	private static final By TAX_FOR_CRP_ON_ORDER_REVIEW_PAGE_LOC=By.xpath("//div[@id='CRPOrderDetail']//strong[contains(text(),'Tax')]/following::span[1]");
	private static final By UPLAOD_A_NEW_PHOTO_BTN = By.xpath("//b[contains(text(),'Upload your own photo:')]/following-sibling::div/a | //div[@class='qq-uploader']"); 	
	private static final By SIGN_IN_BTN_CHECKOUT_PAGE_LOC = By.xpath("//*[contains(@id,'lnkLogin')]"); 
	private static final By LOGIN_BTN_LOC = By.xpath("//*[@id='loginButton'] | //*[@id='loginbutton']"); 
	private static final By EXISTING_CONSULTANT_LOC = By.xpath("//div[@id='ExistentConsultant']/p[contains(text(),'already have a Consultant account')] | //div[@id='ExistentRFOAccount']/p[contains(text(),'already used by another account')]");
	private static final By COMPENSATION_PLAN_LINK=By.xpath(".//*[@id='RFContent']//a[contains(text(),'Compensation Plan')]");
	private static final By LOGIN_BUTTON_MOBILE_LOC=By.xpath("//*[contains(@id,'mobileLoginLink')]/img");
	private static final By SOLUTION_TOOL_HOME_PAGE_LINK_LOC=By.xpath("//a[contains(text(),'Get Started') or contains(text(),'GET STARTED')]");
	private static final By SPONSOR_INFO_TOP_OF_PAGE_LOC=By.xpath("//span[contains(@class,'consultantName')]");
	private static final By EXISTING_RC_ACCOUNT_LOC=By.xpath("//li[contains(text(),'already have an account with Rodan + Fields')]");
	private static final By YOU_MAY_ALSO_FIND_THESE_PRODUCTS_SECTION_LOC=By.xpath(".//h3[contains(text(),'You May Also Enjoy These Products')]");
	private static final By SHIPPING_NAME_ON_ORDER_CONFIRMATION_PAGE_LOC=By.xpath("//h4[contains(text(),'Shipping to')]/following::span[1]");
	private static final By ADDRESS_LINE1_ON_ORDER_CONFIRMATION_PAGE_LOC = By.xpath("//h4[contains(text(),'Shipping to')]/following::span[2]");
	private static final By DONATION_PRODUCT_LOC=By.xpath("//a[contains(text(),'Prescription For Change Donation')]/following::span[contains(text(),'Add to Bag')][1]");
	private static final By DONATE_BUTTON_LOC=By.xpath("//span[contains(text(),'Donate')]");
	private static final By ADD_TO_BAG_BUTTON_FOR_DONATION_PRODUCT_LOC=By.id("donateAddToCartBtn");
	private final static By FEATURED_PAGE_HEADER_LOC=By.xpath("//h1[contains(text(),'FEATURED')]");
	private static final By ORDER_GRAND_TOTAL_BOTTOM_LOC = By.xpath("//span[contains(@id,'uxGrandTotal')]");
	private static final By ORDER_NUMBER_ON_ORDER_CONFIRMATION_PAGE_LOC=By.xpath("//span[contains(@id,'uxOrderNumber')]");
	private static final By SHIPPING_TOTAL_ON_ORDER_CONFIRMATION_PAGE_LOC=By.xpath("//div[@class='CartTotals']//span[contains(@id,'uxShipping')]");
	private static final By TAX_ON_ORDER_CONFIRMATION_PAGE_LOC=By.xpath("//span[contains(@id,'uxTax')] | //div[@class='CartTotals']/descendant::p[3]/span");
	private static final By GRAND_TOTAL_ON_ORDER_CONFIRMATION_PAGE_LOC=By.xpath("//span[contains(@id,'uxGrandTotal')]");
	private static final By SUBTOTAL_FROM_ORDER_HISTORY_LOC=By.xpath("//div[contains(@class,'CartTotals')]/descendant::p[1]");
	private static final By SHIPPING_FROM_ORDER_HISTORY_LOC=By.xpath("//div[@class='CartTotals']/descendant::p[2]");
	private static final By TAX_FROM_ORDER_HISTORY_LOC=By.xpath("//div[contains(@class,'CartTotals')]/descendant::p[3]");
	private static final By GRAND_TOTAL_FROM_ORDER_HISTORY_LOC=By.xpath("//div[contains(@class,'CartTotals')]/descendant::p[4]");
	private static final By ORDER_CONFIRMATION_THANK_YOU_TXT = By.xpath("//h2[contains(text(),'Thank')]");
	private static final By NUMBER_OF_ITEMS_IN_SHOPPING_BAG_LOC = By.xpath("//span[@id='cartNumberOfItems']");
	private static final By RODAN_AND_FIELDS_IMG_LOC = By.xpath("//div[contains(@id,'Logo')or contains(@id,'logo')]//img");
	private static final By COMPLETE_ORDER_BTN = By.xpath("//input[contains(@id,'uxSubmitOrder')]");
	private static final By CONTINUE_WITHOUT_CONSULTANT_LINK = By.xpath("//a[contains(@id,'uxSkipStep')]");
	private static final By SHOP_SKINCARE_LOC = By.xpath("//span[text()='Shop Skincare']");

	private static final By USERNAME1_TXTFLD_LOC = By.xpath("//*[contains(@id,'uxUserNameText')]");
	private static final By PASSWORD1_TXTFLD_LOC = By.xpath("//*[contains(@id,'uxPasswordText')]");
	private static final By LOGIN1_BTN_LOC = By.xpath("//*[contains(@id,'Login')]");
	private static final By UPDATE_CART_BTN_LOC = By.xpath(".//*[@id='uxProductQty']");
	private static final By CONTINUE_SHOPPING_BTN_LOC= By.xpath(".//*[@id='uxContinueShoppingLink']");
	private static final By SHOP_SKINCARE_ON_PWS_LOC = By.xpath("//span[text()='SHOP SKINCARE' or text()='Shop Skincare']");

	private static final By FAQS_TEXT_LOC = By.xpath("//h1[contains(text(),'FAQs') or contains(text(),'Questions')]");
	private static final By RESULTS_TEXT_LOC = By.xpath("//h1[contains(text(),'REAL RESULTS')]");
	private static final By CAREERS_LINK_LOC=By.xpath("//span[contains(text(),'Careers')]");
	private static final By USE_THIS_ADDRESS_SHIPPING_INFORMATION = By.xpath("//a[contains(@id,'uxUseNewAddress') or contains(@id,'uxContinueAndEdit')]");
	private static final By PRODUCTS_LIST_LOC = By.xpath("//div[@id='FullPageItemList']");
	private static final By TESTIMONIAL_PAGE_CONTENT_LOC = By.xpath("//div[@id='RFContent']/div/div/blockquote[1]/div[1]");
	private static final By NEWS_TEXT_LOC = By.xpath("//div[@id='RFContent']");
	private static final By ADVICE_PAGE_CONTENT_LOC = By.xpath("//div[@id='RFContent']//td[1]");
	private static final By GLOSSARY_PAGE_CONTENT_LOC = By.xpath("//div[@id='GlossaryAF']");
	private static final By INGREDIENTS_AND_USAGE_LINK_LOC = By.xpath("//a[text()='Ingredients and Usage']");
	private static final By INGREDIENTS_CONTENT_LOC = By.xpath("//span[@id='ProductUsage']");
	private static final By FOOTER_CONTACT_US_LINK_LOC = By.xpath("//footer[@id='FooterPane']//span[text()='Contact Us']");
	private static final By CONTACT_US_PAGE_HEADER_LOC = By.xpath("//h1[text()='CONTACT US' or text()='Contact Us']");
	private static final By CONTACT_US_PAGE_LOC = By.xpath("//*[text()='HOW MAY WE HELP YOU?']");
	private static final By ENROLL_NOW_ON_BUSINESS_PAGE_LOC = By.xpath("//*[@id='LeftNav']//span[text()='Enroll Now']");
	private static final By CID_LOC = By.id("NameOrId");
	private static final By CID_SEARCH_LOC = By.id("BtnSearch");
	private static final By SEARCH_RESULTS_LOC = By.xpath("//div[@id='searchResults']//a");
	private static final By BIG_BUSINESS_LAUNCH_KIT_LOC = By.xpath("//cufontext[contains(text(),'Big')]/ancestor::div[contains(@class,'KitContent')][1]");
	private static final By RF_EXPRESS_LAUNCH_KIT_LOC = By.xpath("//cufontext[contains(text(),'Express')]/ancestor::div[contains(@class,'KitContent')][1]");
	private static final By BUSINESS_PORTFOLIO_KIT_LOC =By.xpath("//cufontext[contains(text(),'Portfolio')]/ancestor::div[@class='KitContent'][1]");
	private static final By SELECT_ENROLLMENT_KIT_NEXT_BTN_LOC =By.xpath("//a[@id='BtnNext']//canvas");
	private static final By ACCOUNT_INFORMATION_NEXT_BTN = By.xpath("//a[@id='btnNext']//cufon");
	private static final By REDEFINE_REGIMEN_LOC = By.xpath("//cufontext[text()='REDEFINE ']/ancestor::a[1]");
	private static final By REGIMEN_NEXT_BTN_LOC = By.xpath("//a[@id='BtnDone']");
	private static final By EXPRESS_ENROLLMENT_LOC = By.id("btnGoToExpressEnroll");
	private static final By ENROLLMENT_NEXT_BTN_LOC = By.xpath("//a[@id='btnNext']");
	private static final By STANDARD_ENROLLMENT_LOC = By.id("btnGoToStandardEnrollment");
	private static final By CVV_FIELD = By.xpath("//input[contains(@id,'uxCreditCardCvv')]");

	private static final By ACCOUNT_FIRST_NAME_LOC = By.id("Account_FirstName");
	private static final By ACCOUNT_LAST_NAME_LOC = By.id("Account_LastName");
	private static final By ACCOUNT_EMAIL_ADDRESS_LOC = By.id("Account_EmailAddress");
	private static final By ACCOUNT_PASSWORD_LOC = By.id("Account_Password");
	private static final By ACCOUNT_CONFIRM_PASSWORD_LOC = By.id("txtConfirmPassword");
	private static final By ACCOUNT_MAIN_ADDRESS1_LOC = By.id("MainAddress_Address1");
	private static final By ACCOUNT_POSTAL_CODE_LOC = By.id("MainAddress_PostalCode");
	private static final By ACCOUNT_PHONE_NUMBER1_LOC = By.id("txtPhoneNumber1");
	private static final By ACCOUNT_PHONE_NUMBER2_LOC = By.id("txtPhoneNumber2");
	private static final By ACCOUNT_PHONE_NUMBER3_LOC = By.id("txtPhoneNumber3");

	private static final By SETUP_ACCOUNT_NEXT_BTN_LOC = By.id("btnNext");
	private static final By USE_AS_ENTERED_BTN_LOC = By.xpath(".//*[@id='QAS_AcceptOriginal']");
	private static final By CARD_NUMBER_LOC = By.xpath(".//*[@id='Payment_AccountNumber']");
	private static final By NAME_ON_CARD_LOC = By.xpath(".//*[@id='Payment_NameOnCard']");
	private static final By EXP_MONTH_LOC = By.id("ExpMonth");
	private static final By EXP_YEAR_LOC = By.id("ExpYear");
	private static final By SSN1_LOC = By.xpath(".//*[@id='txtTaxNumber1']");
	private static final By SSN2_LOC = By.xpath(".//*[@id='txtTaxNumber2']");
	private static final By SSN3_LOC = By.xpath(".//*[@id='txtTaxNumber3']");
	private static final By SSN_CARD_NAME_LOC  = By.xpath(".//*[@id='Account_EnrollNameOnCard']");
	private static final By PWS_LOC = By.id("Account_EnrollSubdomain");
	private static final By PWS_AVAILABLE_MSG_LOC = By.xpath("//li[@id='Abailable0' and contains(text(),'is available')]");
	private static final By COMPLETE_ACCOUNT_NEXT_BTN_LOC = By.xpath("//form[@id='FrmCompleteAccount']//*[@id='btnNext']");

	private static final By EMAIL_POLICY_AND_PROCEDURE_TOGGLE_BTN_LOC = By.xpath("//input[@id='Account_EnrollPolicyProcedure']/following::div[1]//div[@class='ibutton-handle-middle']") ;
	private static final By ENROLL_SWITCH_POLICY_TOGGLE_BTN_LOC = By.xpath("//input[@id='Account_EnrollSwitchingPolicy']/following::div[1]//div[@class='ibutton-handle-middle']") ;
	private static final By ENROLL_TERMS_CONDITIONS_TOGGLE_BTN_LOC = By.xpath("//input[@id='Account_EnrollTermsAndConditions']/following::div[1]//div[@class='ibutton-handle-middle']") ;
	private static final By ENROLL_E_SIGN_CONSENT_TOGGLE_BTN_LOC = By.xpath("//input[@id='Account_EnrollESignConsent']/following::div[1]//div[@class='ibutton-handle-middle']") ;

	private static final By CHARGE_AND_ENROLL_ME_BTN_LOC = By.id("ChargeAndEnrollMe");
	private static final By CONFIRM_AUTOSHIP_BTN_LOC = By.id("confirmAuthoship");
	private static final By CONGRATS_MSG_LOC = By.id("Congrats");
	private static final By AUTOSHIP_OPTIONS_NEXT_BTN_LOC = By.xpath("//form[@id='FrmAutopshipOptions']//*[@id='btnNext']");
	private static final By ADD_TO_CART_BTN_LOC = By.xpath("//div[@class='productList']/div[1]/div[3]/a[2]"); 
	private static final By ADD_TO_CART_BTN_SECOND_PRODUCT_LOC = By.xpath("//div[@class='productList']/div[1]/div[4]/a[2]"); 
	private static final By YOUR_CRP_ORDER_POPUP_NEXT_BTN_LOC = By.id("PlaceCRPOrder");
	private static final By USERNAME_TEXT_BOX_LOC = By.id("username");
	private static final By PASSWORD_TXTFLD_ONFOCUS_LOC = By.id("passwordWatermarkReplacement");
	private static final By PASSWORD_TEXT_BOX_LOC = By.id("password");
	private static final By SUBSCRIBE_TO_PULSE_TOGGLE_BTN_LOC = By.xpath("//input[@id='Account_EnrollPulse']/following::div[1]//div[@class='ibutton-handle-middle']");
	private static final By ENROLL_IN_CRP_TOGGLE_BTN_LOC = By.xpath("//input[@id='Account_EnrollCRP']/following::div[1]//div[@class='ibutton-handle-middle']");
	private static final By LOGOUT_LOC = By.xpath("//a[text()='Log Out']");
	private static final By EXISTING_CONSULTANT_CROSS_COUNTRY_LOC = By.xpath("//div[@id='ExistentRFOAccount']/p[contains(text(),'already used by another account')]");
	private static final By BECOME_A_CONSULTANT_MENU = By.xpath("//a[@href='/Pages/BusinessSystem/WhyRF/GettingStarted']");
	private static final By ENROLL_NOW_ON_BIZ_PWS_PAGE_LOC = By.xpath("//*[@id='nav']/div/ul/li[2]/ul/li[2]/a/span");
	private static final By ENROLL_NOW_ON_WHY_RF_PAGE_LOC = By.xpath("//ul[@class='SubNav']//span[contains(text(),'Enroll Now') or contains(text(),'ENROLL NOW')]");

	private static final By ADD_TO_CART_BTN = By.id("addToCartButton");//"//a[text()='Add to Cart']");
	private static final By CLICK_HERE_LINK_FOR_PC = By.xpath("//a[contains(@id,'HyperLink1')] | //a[contains(@id,'PreferredLink')]");
	private static final By ENROLL_NOW_FOR_PC_AND_RC = By.xpath("//a[contains(text(),'Enroll Now') or contains(text(),'ENROLL NOW') or contains(text(),'SUBSCRIBE NOW')]");
	private static final By FIRST_NAME_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxFirstName')]");
	private static final By LAST_NAME_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxLastName')]");
	private static final By EMAIL_ADDRESS_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxEmailAddress')]");
	private static final By CREATE_PASSWORD_FOR_PC_AND_RC = By.xpath("//div[@class='form-group']//input[contains(@id,'uxPassword')]");
	private static final By CONFIRM_PASSWORD_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxConfirmPassword')]");
	private static final By PHONE_NUMBER_FOR_PC_AND_RC = By.xpath("//input[contains(@id,'uxPhoneNumber')]");	
	private static final By ID_NUMBER_AS_SPONSOR = By.xpath("//input[contains(@id,'uxSponsorID')]");
	private static final By BEGIN_SEARCH_BTN = By.xpath("//a[contains(@id,'uxSearchByName')]");
	private static final By SPONSOR_RADIO_BTN = By.xpath("//div[@class='DashRow']/input");
	private static final By TERMS_AND_CONDITION_CHK_BOX_FOR_PC = By.xpath("//input[contains(@id,'uxAgree1')]");
	private static final By CONTINUE_BTN_PREFERRED_AUTOSHIP_SETUP_PAGE_LOC = By.xpath("//div[@id='MyAutoshipItems']/following::input[contains(@id,'uxContinue')]");

	private static final By ADDRESS_NAME_FOR_SHIPPING_PROFILE = By.xpath("//input[contains(@id,'uxAddressName')]");
	private static final By ATTENTION_FIRST_NAME = By.xpath("//div[@class='form-group']//input[contains(@id,'uxAttentionFirstName')][1]");
	private static final By ATTENTION_LAST_NAME = By.xpath("//div[@class='form-group']//input[contains(@id,'uxAttentionLastName')][1]");
	private static final By ADDRESS_LINE_1 = By.xpath("//input[contains(@id,'uxAddressLine1')]");
	private static final By ZIP_CODE = By.xpath("//input[contains(@id,'uxZipCode')]");
	private static final By CITY_DD = By.xpath("//input[contains(@id,'uxCityDropDown_Input')]/following::a[text()='select']");
	private static final By FIRST_VALUE_OF_CITY_DD = By.xpath("//div[contains(@id,'uxCityDropDown_DropDown')]//ul[@class='rcbList']/li");
	private static final By COUNTRY_DD = By.xpath("//input[contains(@id,'uxCountyDropDown_Input')]/following::a[text()='select']");
	private static final By FIRST_VALUE_OF_COUNTRY_DD = By.xpath("//div[contains(@id,'uxCountyDropDown_DropDown')]//ul[@class='rcbList']/li");
	private static final By PHONE_NUMBER_SHIPPING_PROFILE_PAGE = By.xpath("//input[contains(@id,'uxShippingEditor_AppPhone')]");
	private static final By BILLING_NAME_FOR_BILLING_PROFILE = By.xpath("//input[contains(@id,'uxBillingProfileName')]");
	private static final By NAME_ON_CARD = By.xpath("//input[contains(@id,'uxNameOnCard')]");
	private static final By CREDIT_CARD_NUMBER_INPUT_FIELD = By.xpath("//input[contains(@id,'uxCreditCardNumber')]");
	private static final By EXPIRATION_DATE_MONTH_DD = By.xpath("//select[contains(@id,'uxMonthDropDown')]");
	private static final By EXPIRATION_DATE_YEAR_DD = By.xpath("//select[contains(@id,'uxYearDropDown')]");
	private static final By PHONE_NUMBER_BILLING_PROFILE_PAGE = By.xpath("//input[contains(@id,'uxPhone')]");
	private static final By COMPLETE_ENROLLMENT_BTN = By.xpath("//input[contains(@id,'uxComplete')]");
	private static final By WELCOME_TXT_AFTER_ENROLLMENT = By.xpath("//cufontext[contains(text(),'Welcome')]");
	private static final By USERNAME_TXTFLD_LOC = By.id("username");
	private static final By PASSWORD_TXTFLD_LOC = By.id("password");
	private static final By CLICK_HERE_LINK_FOR_RC = By.xpath("//a[contains(@id,'RetailLink')]");
	private static final By LOG_OUT_ON_CORP_BTN = By.xpath("//a[text()='Log-Out']");
	private static final By CARRERS_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Careers']");
	private static final By PRESS_ROOM_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Press Room']");
	private static final By CONTACT_US_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Contact Us']");
	private static final By WHO_WE_ARE_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Who We Are']");
	private static final By PC_PERKS_LINK_LOC = By.xpath("//div[@id='HeaderCol']//span[text()='PC PERKS']");
	private static final By DIGITAL_PRODUCT_LINK_LOC = By.xpath("//div[@id='HeaderCol']//span[text()='Digital Product Catalog']");
	private static final By PRODUCT_PHILOSOPHY_LOC = By.xpath("//span[text()='Product Philosophy']");
	private static final By DIGITAL_PRODUCT_CATALOG_LOC = By.xpath("//span[text()='Digital Product Catalog']");
	private static final By REAL_RESULTS_LINK_LOC = By.xpath("//div[@id='HeaderCol']//span[text()='Real Results']");
	private static final By EXECUTIVE_TEAM_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Executive Team']");
	private static final By ABOUT_RF_LOC = By.xpath("//span[text()='About R+F']");
	private static final By SOLUTION_TOOL_LINK_LOC = By.xpath("//div[@id='HeaderCol']//span[text()='Solution Tool']");
	private static final By PRIVACY_POLICY_LINK = By.xpath("//span[text()='Privacy Policy']");
	private static final By PRIVACY_POLICY_TEXT = By.xpath("//*[text()='PRIVACY POLICY']");
	private static final By SATISFACTION_GUARANTEE_LINK = By.xpath("//span[text()='Satisfaction Guarantee']");
	private static final By SATISFACTION_GUARANTEE_TEXT = By.xpath("//h1[contains(text(),'Guarantee') or contains(text(),'GUARANTEE')]");
	private static final By TERMS_AND_CONDITIONS_LINK = By.xpath("//span[text()='Terms & Conditions']");
	private static final By SOLUTION_TOOL_LOC = By.xpath("//span[text()='Products']/following::li/a/span[text()='Solution Tool']");
	private static final By SOLUTION_TOOL_PAGE_LOC = By.xpath("//div[@id='RFContent']");
	private static final By FIND_RODAN_FIELD_CONSULTANT_LINK_LOC = By.xpath("//div[@id='RFContent']//a[text()='Find a Rodan + Fields Consultant']");
	private static final By SPONSOR_RADIO_BTN_ON_FIND_CONSULTANT_PAGE = By.xpath("//div[@class='DashRow']//input");
	private static final By PWS_TXT_ON_FIND_CONSULTANT_PAGE = By.xpath("//span[contains(text(),'PWS URL')]/a");
	private static final By CHECKOUT_BTN = By.xpath("//span[text()='Checkout']");
	private static final By CLICK_HERE_LINK = By.xpath("//a[text()='Click here']");
	private static final By DETAILS_LINK = By.xpath("//a[text()='Details']");
	private static final By CLICK_HERE_LOC = By.xpath("//div[@id='RFContent']//a[contains(text(),'Income Disclosure Statement')]");
	private static final By SELECTED_HIGHLIGHT_LINK = By.xpath("//div[@id='ContentWrapper']//a[@class='selected']/span");
	private static final By REAL_RESULTS_PAGE_LOC = By.xpath("//div[@id='RFContent']//cufontext[text()='REAL ']/preceding::canvas[1]");
	private static final By PC_PERKS_PAGE_LOC = By.xpath("//div[@id='HeaderCol']//cufontext[text()='PC ']/preceding::canvas[1]");
	private static final By SOLUTION_TOOL_PAGE = By.xpath("//div[@id='RFContent']//a[text()='Find a Rodan + Fields Consultant']");
	private static final By DIGITAL_PRODUCT_CATALOGUE = By.xpath("//div[@class='body']//a[text()='Digital Product Catalog']");
	private static final By BUSINESS_PRESENTATION_SECTION_LOC = By.xpath("//div[@id='RFContent']//following::div[@style='margin-bottom:1em;']/h2//cufontext[text()='Presentations']/ancestor::cufon | .//*[@id='RFContent']//h3[contains(text(),'Business Presentations')]");
	private static final By HOME_TAB_LOC = By.xpath("//span[text()='Home']");
	private static final By FIND_A_CONSULTANT_IMAGE_LOC = By.xpath("//img[@alt='Find a Rodan and Fields Consultant']");
	private static final By START_NOW_BTN = By.xpath("//a[text()='START NOW']");
	private static final By MEET_OUR_COMMUNITY_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Meet Our Community']");
	private static final By EVENTS_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Events']");
	private static final By INCOME_ILLUSTRATOR_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Income Illustrator']");
	private static final By PROGRAMS_INCENTIVES_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Programs and Incentives']");
	private static final By WHY_RF_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Why R+F']");
	private static final By BUSINESS_KITS_LINK_LOC = By.xpath("//span[text()='Business Kits']");
	private static final By BUSINESS_KITS_SECTION_LOC = By.xpath("//div[@id='RFContent']/div/div[@class='body']");
	private static final By REDEFINE_YOUR_FUTURE_LINK_LOC = By.xpath("//span[text()='Redefine Your Future']");
	private static final By ENROLL_NOW_LINK_LOC = By.xpath("//div[@id='LeftNav']//span[text()='Enroll Now']");
	private static final By ENROLL_NOW_LINK_UNDER_WHYRF_LOC = By.xpath("//div[@id='HeaderCol']//span[text()='Enroll Now']");
	private static final By UPCOMING_EVENTS_LINK_LOC = By.xpath("//span[text()='Upcoming Events']");
	private static final By DISCLAIMER_LINK_LOC = By.xpath("//span[text()='Disclaimer']");
	private static final By GIVING_BACK_LINK_LOC = By.xpath("//div[@id='HeaderCol']//span[text()='Giving Back']");
	private static final By BILLING_FIRST_NAME = By.xpath("//td[@class='tdinput']//input[@type='text' and contains(@id,'uxAttentionFirstName')]");
	private static final By BILLING_LAST_NAME = By.xpath("//td[@class='tdinput']//input[@type='text' and contains(@id,'uxAttentionLastName')]");
	private static final By SOLUTION_TOOL_IMAGE_LOC = By.xpath("//img[@alt='Solution Tool']");
	private static final By RF_IN_THE_NEWS_IMAGE_LOC = By.xpath("//img[@alt='Rodan and Fields AGING REDEFINED']");
	private static final By USERNAME_TXTFLD_CHECKOUT_PAGE_LOC = By.xpath("//input[contains(@id,'uxUserNameText')]");
	private static final By PASSWORD_TXTFLD_CHECKOUT_PAGE_LOC = By.xpath("//input[contains(@id,'uxPasswordText')]");
	private static final By RENEW_LATER_LINK = By.xpath("//a[@id='renewLater']");
	private static final By FORGOT_PASSWORD_PWS_LINK_LOC = By.xpath("//a[@id='uxForgotPasswordLink']");
	private static final By CHANGE_PASSWORD_TEXT_LOC = By.xpath("//div[@id='ContentWrapper']//h3[contains(text(),'Recover Password')]");
	private static final By SEND_EMAIL_BTN_LOC = By.xpath("//input[@value='Submit']");
	private static final By EMAIL_ADDRESS_FIELD_LOC = By.xpath("//label[contains(text(),'Email Address')]/following-sibling::input[@type='text']");
	private static final By EDIT_ORDER_UNDER_MY_ACCOUNT_LOC = By.xpath("//span[text()=' Edit Order']");
	private static final By CHANGE_LINK_FOR_SHIPPING_INFO_ON_PWS = By.xpath("//a[contains(@id,'uxChangeShippingLink')]");
	private static final By SHIPPING_ADDRESS_NAME_LOC = By.xpath("//*[text()='Shipping to:']/../span[1]");
	private static final By ENROLL_NOW_LINK = By.xpath("//span[text()='Enroll Now']");
	private static final By WEBSITE_PREFIX_BIZ_PWS = By.xpath("//li[@id='Abailable1']");
	private static final By CHANGE_MY_PC_PERKS_STATUS_UNDER_MYACCOUNT_LINK = By.xpath("//a[@class='IconLink']/span[text()=' Change my PC Perks Status']");
	private static final By DELAY_OR_CANCEL_PC_PERKS_LINK = By.xpath("//a[@id='PcCancellLink']/span[text()=' Delay or Cancel PC Perks']");
	private static final By YES_CHANGE_MY_AUTOSHIP_DATE_BTN = By.xpath("//a[@id='BtnChangeAutoship']");
	private static final By CONFIRM_MSG_AT_ORDERS_LOC = By.xpath("//div[@id='ContentWrapper']/div[2]//p[contains(text(),'our next replenishment order is scheduled')]");
	private static final By RADIO_BUTTON_FOR_SIXTY_DAYS_LOC = By.xpath("//input[@value='60']");
	private static final By TOTAL_ITEM_LOC = By.xpath("//div[@id='MyAutoshipItems']//li");
	private static final By REMOVE_LINK_LOC = By.xpath("//div[@id='MyAutoshipItems']//li[1]/a");
	private static final By ADD_TO_CART_LINK_LOC = By.xpath("//span[contains(text(),'Retail')]");
	private static final By STATUS_THRESHOLD_MSG_FOR_MIN_VALUE_LOC = By.xpath("//span[contains(@id,'uxStatusMessage')][contains(text(),'at least $80.00')]");
	private static final By UPDATE_ORDER_BTN_LOC = By.xpath("//div[@id='MyAutoshipItems']/following-sibling::p[2]/input");
	private static final By MSG_UPDATE_ORDER_CONFIRMATION_LOC = By.xpath("//p[@class='success']");
	private static final By ORDER_TOTAL_LOC = By.xpath("//div[@id='TotalBar']/p");
	private static final By ORDER_TOTAL_AT_OVERVIEW_LOC = By.xpath("//span[contains(@id,'uxSubTotal')]");
	private static final By PREFIX_SUGGESTIONS_LIST = By.xpath("//div[@class='websitePrefix']//li[contains(text(),'unavailable')]");
	private static final By EDIT_ORDER_BTN_LOC = By.xpath("//a[text()='Edit Order']");
	private static final By CHANGE_BILLING_INFO_LINK_ON_PWS = By.xpath("//a[contains(@id,'uxChangeBillingLink')]");
	private static final By EDIT_ORDER_BILLING_DETAILS_UPDATE_MESSAGE = By.xpath("//p[contains(@class,'success Pad10')]");
	private static final By WEBSITE_PREFIX_LOC = By.xpath("//input[@id='Account_EnrollSubdomain']");
	private static final By INVALID_LOGIN = By.xpath("//p[@id='loginError']");
	private static final By EXISTING_PC_LOC = By.xpath("//div[@id='ExistentPC']/p[contains(text(),'already have a Preferred Customer')]");
	private static final By EXISTING_RC_LOC = By.xpath("//div[@id='ExistentRetail']/p[contains(text(),'already have a Retail account')]");
	private static final By WEBSITE__PREFIX_LOC = By.xpath("//input[@id='Account_EnrollSubdomain']");
	private static final By EDIT_MY_PHOTO_LINK = By.xpath("//a[contains(text(),'Edit My Photo')]");
	private static final By EMAIL_VERIFICATION_TEXT = By.xpath("//div[@class='SubmittedMessage'][@style='']");
	private static final By CANCEL_ENROLLMENT_BTN = By.xpath("//a[@id='BtnCancelEnrollment']");
	private static final By SEND_EMAIL_TO_RESET_MY_PASSWORD_BTN = By.xpath("//a[@id='BtnResetPassword']");
	private static final By TOTAL_ROWS_ON_ORDER_HISTORY_PAGE = By.xpath("//div[@class='divTable CartTable']//div[@class='divTableBody']/div");
	private static final By ORDER_DETAILS_POPUP = By.xpath("//div[@id='RFContent']//h2[text()='Order Details']");
	private static final By CLOSE_OF_ORDER_DETAILS_POPUP = By.xpath("//div[@id='RFContent']//a[text()='X']");
	private static final By CONNECT_WITH_A_CONSULTANT = By.cssSelector("a[href*='LocatePWS']");
	private static final By CLICK_HERE_TO_LEARN_MORE_ABOUT_DIRECT_SELLING = By.cssSelector("a[href*='directselling']");
	private static final By PRESS_ROOM = By.xpath("//span[contains(text(),'Press Room')]");
	private static final By ERROR_MESSAGE_FOR_TERMS_AND_CANDITIONS_FOR_PC_RC = By.xpath("//p[@class='AgreementCheck']");
	private static final By RECOVER_PWD_EMAIL_ADDRESS_LOC=By.xpath("//label[contains(text(),'Email Address')]/following::input[1]");
	private static String CHOOSE_SHIPPING_METHOD_lOC ="//label[contains(text(),'%s')]";
	private static String RF_CONNECTION_LINK_LOC="//span[contains(text(),'%s')]";
	private static final By RF_CONNECTION_PAGE_HEADER_LOC=By.xpath("//a[contains(@href,'rfconnection')]/img");
	private static final By DERM_RF_PAGE_HEADER_LOC=By.xpath("//div[contains(@class,'logo')]//img");
	private static final By SHIPPING_PAGE_HEADER_LOC=By.xpath("//h1[contains(text(),'U.S. SHIPPING AND DELIVERY')]");
	private static final By CALIFORNIA_SUPPLY_CHAINS_ACT_PAGE_HEADER_LOC=By.xpath("//h1[contains(text(),'CALIFORNIA TRANSPARENCY IN SUPPLY CHAINS ACT')]");
	private static final By RF_CONVENTION_SECTION_LOC=By.xpath(".//*[@id='RFContent']//h3[contains(text(),'R+F CONVENTION')]");
	private static final By BUSINESS_REDEFINED_LEARNING_SECTION_LOC=By.xpath(".//*[@id='RFContent']//h3[contains(text(),'Business Redefined Learning Events')]");
	private static final By CONSULTANT_LED_EVENTS_SECTION_LOC=By.xpath(".//*[@id='RFContent']//h3[contains(text(),'Consultant-Led Events')]");
	private static final By BUSINESS_PRESENTATION_EVENT_CALENDAR_LINK_LOC=By.xpath(".//*[@id='RFContent']//h3[contains(text(),'Business Presentations')]/following::a[contains(text(),'Visit the Rodan + Fields Event Calendar')][1]");
	private static final By EVENT_CALENDAR_LINK_LOC=By.xpath(".//*[@class='container cal-items-container']//div[@class='cal-item']");
	private static final By ENROLL_NOW_START_YOUR_JOURNEY_LOC=By.xpath("//a[contains(text(),'Enroll Now')]");
	private static final By RF_EVENTS_LEARNING_LEADRESHIP_SECTION_LOC=By.xpath("//a[contains(text(),'R+F Events')]");
	private static final By MEET_OUR_COMMUNITY_PAGE_LOC=By.xpath("//h1[contains(text(),'MEET OUR COMMUNITY')]");
	private static final By READ_THEIR_INSPIRING_STORIES_LINK_LOC=By.xpath("//a[contains(text(),'Read their inspiring stories')]");
	private static final By ENROLL_NOW_MEET_OUR_COMMUNITY_PAGE_LOC=By.xpath("//a[contains(text(),'Enroll Now')]");
	private static String LINKS_ON_MEET_OUR_COMMUNITY_PAGE_LOC="//img[contains(@alt,'%s')]";
	private static String shippingMethodRadioBtnLoc = "//label[contains(text(),'%s')]/preceding::input[1]";
	private static final By YOUR_PRICE_LOC = By.xpath("//span[@class='YourPrice']");
	private static final By SV_PRICE_LOC = By.xpath("//span[contains(text(),'SV')]");	
	private static final By SHIIPING_PRICE_FROM_CRP_REVIEW_AND_CONFIRM_PAGE_LOC=By.xpath(".//div[@id='CRPOrderDetail']//strong[contains(text(),'Shipping')]/following::span[1]");
	private static final By USE_AS_ENTERED_ADDRESS_LOC=By.xpath("//*[contains(@alt,'entered')]");
	private static final By ENTER_LOGIN_BTN_LOC = By.xpath(".//*[@id='loginButton'] | //*[@id='loginbutton']");
	private static final By LOGIN_DROPDOWN_LINK_LOC=By.id("logindropdownlink");

	public static String GET_RANDOM_ACTIVE_RC_EMAILID = "select top 1 * from dbo.Accounts where AccountTypeID='3' AND Active='1' order by NEWID()";
	public static String GET_RANDOM_ACTIVE_CONSULTANT_EMAILID = "select top 1 * from dbo.Accounts where AccountTypeID='1' AND Active='1' order by NEWID()";
	private static final By LOGIN_ERROR_LOC=By.id("loginError");
	private static final By EYEBROW_PANEL_LOC=By.xpath("//*[@id='navbaroptions']/div");
	private static final By COUNTRY_DROPDOWN_ICON = By.id("countryflags");
	private static final By CART_ICON = By.xpath("//div[@id='countryflags']/following-sibling::div[@id='shoppingbag'] ");
	private static final By LOGIN_ICON = By.xpath("//div[@id='countryflags']/following-sibling::div[@id='shoppingbag']/following-sibling::div[@id='logindropdown']");
	private static final By WELCOME_USER_LINK=By.cssSelector("#welcomeuser div:nth-child(2)");
	private static final By SPONSOR_NAME_TOP_HEADER_LOC=By.xpath("//div[@class='CInfo']/a[contains(text(),'Independent Consultant')]");
	private static final By CHECK_MY_PULSE_LOC=By.xpath("//*[contains(text(),'Check My Pulse') or contains(text(),'check my pulse')]");
	private static final By ACCEPT_BTN_LOC = By.xpath("//cufontext[contains(text(),'Accept')]/ancestor::button[1] | //*[contains(text(),'Accept')]"); 
	private static final By BUY_NOW_BTN_LOC = By.id("BtnBuyNow");
	private static final By CONFIRM_BTN_LOC = By.id("BtnConfirmUp");
	private static final By FIRST_VIEW_DETAILS_LINK_LOC = By.xpath("//div[@id='orderList']/descendant::a[@class='OrderDetailsLink'][1]");
	private static final By ERROR_MSG_FOR_INVALID_CC = By.xpath("//*[contains(text(),'we are not able to validate the card number you entered')]");	
	private static String addToBagButtonForProductNumberLoc = "//div[@id='FullPageItemList']/descendant::div[%s]//span[contains(text(),'Add to Bag')]";

	boolean  isProductAddedSuccessfulyToPCPerks =true;

	public StoreFrontHeirloomHomePage(RFWebsiteDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void clickBeAConsultantBtn(){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.quickWaitForElementPresent(BE_A_CONSULTANT_LOC);
		WebElement shopSkinCare = driver.findElement(BE_A_CONSULTANT_LOC);
		actions.moveToElement(shopSkinCare).pause(1000).build().perform();
		logger.info("Be a consultant drop doen link clicked");
	}

	public void clickEnrollNowBtnOnBusinessPage(){
		driver.quickWaitForElementPresent(ENROLL_NOW_ON_BUSINESS_PAGE_LOC);
		driver.click(ENROLL_NOW_ON_BUSINESS_PAGE_LOC,"");
		logger.info("Enroll Now button on business page is clicked");
		driver.waitForPageLoad();
	}
	public void hoverOnBeAConsultantAndClickEnrollNowLink(){
		Actions actions = new Actions(RFWebsiteDriver.driver);
		driver.quickWaitForElementPresent(BE_A_CONSULTANT_LOC);
		WebElement shopSkinCare = driver.findElement(BE_A_CONSULTANT_LOC);
		actions.moveToElement(shopSkinCare).pause(1000).build().perform();
		driver.quickWaitForElementPresent(ENROLL_NOW_ON_BUSINESS_PAGE_LOC);
		driver.click(ENROLL_NOW_ON_BUSINESS_PAGE_LOC,"");
		logger.info("Enroll Now button is clicked");
		driver.waitForPageLoad();
	}

	public void enterCID(String cid){
		driver.type(CID_LOC, cid,"sponsor");
		driver.click(CID_SEARCH_LOC,"search sponsor button");
	}

	public void clickSearchResults(){
		driver.click(SEARCH_RESULTS_LOC,"Search results");
		driver.waitForPageLoad();
	}

	/**
	 * This method is used to click
	 * @param kit
	 */
	public StoreFrontHeirloomHomePage selectEnrollmentKit(String kit){
		if(kit.contains("Big Business")){
			driver.click(BIG_BUSINESS_LAUNCH_KIT_LOC,"Big Business Launch Kit");
		}
		else if(kit.contains("Express")){
			driver.click(RF_EXPRESS_LAUNCH_KIT_LOC,"RFx Express Business Kit");
		}
		return this;
	}

	/***
	 * This methid is used to click on regimen and then click on next button
	 * @param regimen
	 */
	public void selectRegimenAndClickNext(String regimen){
		if(regimen.equalsIgnoreCase("Redefine")){
			driver.click(REDEFINE_REGIMEN_LOC,"Redefine regimen");
		}
		driver.click(REGIMEN_NEXT_BTN_LOC,"Next button");
	}

	/***
	 * This method is used to select the enrollment type
	 * @param enrollmentType
	 */
	public StoreFrontHeirloomHomePage selectEnrollmentType(String enrollmentType){
		if(enrollmentType.equalsIgnoreCase("Express")){
			driver.click(EXPRESS_ENROLLMENT_LOC,"express enrollment");
		}
		else if(enrollmentType.equalsIgnoreCase("Standard")){
			driver.click(STANDARD_ENROLLMENT_LOC,"standard enrollment");
		}
		driver.click(ENROLLMENT_NEXT_BTN_LOC,"Next button");
		driver.waitForPageLoad();
		return this;
	}

	/***
	 * This method is used to click on next button on account setup page
	 */
	public StoreFrontHeirloomHomePage clickSetUpAccountNextBtn(){
		driver.click(SETUP_ACCOUNT_NEXT_BTN_LOC,"set up account next button");
		if(driver.isElementPresent(USE_AS_ENTERED_BTN_LOC))
			driver.click(USE_AS_ENTERED_BTN_LOC,"use as entered button");
		driver.waitForPageLoad();
		return this;
	}

	public void enterBillingInformation(String cardNumber,String nameOnCard,String expMonth,String expYear){
		driver.findElement(CARD_NUMBER_LOC).sendKeys(cardNumber);
		logger.info("card number entered is: "+cardNumber);
		driver.findElement(NAME_ON_CARD_LOC).sendKeys(nameOnCard);
		logger.info("name on card entered is: "+nameOnCard);
		/*Select dropdown1 = new Select(driver.findElement(EXP_MONTH_LOC));
		  dropdown1.selectByVisibleText(expMonth);*/
		driver.click(By.xpath("//select[@id='ExpMonth']"),"");
		driver.click(By.xpath("//select[contains(@id,'ExpMonth')]//option[@value='"+expMonth+"']"),"");
		logger.info("Exp Month selected as: "+expMonth);
		Select dropdown2 = new Select(driver.findElement(EXP_YEAR_LOC));
		dropdown2.selectByVisibleText(expYear);
		logger.info("Exp Year selected as: "+expYear);
	}

	/***
	 * This method is used to enter the billing information 
	 * 
	 * @param cardNumber
	 * @param nameOnCard
	 * @param expMonth
	 * @param expYear
	 * @param CVV
	 */
	public StoreFrontHeirloomHomePage enterBillingInformation(String cardNumber,String nameOnCard, String expMonth,String expYear,String CVV){
		driver.type(CARD_NUMBER_LOC,cardNumber,"card number");
		driver.type(NAME_ON_CARD_LOC,nameOnCard,"name on card");
		driver.type(By.id("TxtBCvv"), CVV,"cvv");
		driver.click(By.xpath("//select[@id='ExpMonth']"),"Exp month drop down");
		driver.click(By.xpath("//select[contains(@id,'ExpMonth')]//option[@value='"+expMonth+"']"),expMonth);
		Select dropdown2 = new Select(driver.findElement(EXP_YEAR_LOC));
		dropdown2.selectByVisibleText(expYear);
		return this;
	}

	/***
	 * This method is used to enter SSN related account info
	 * 
	 * @param randomNum1
	 * @param randomNum2
	 * @param randomNum3
	 * @param SSNCardName
	 */
	public StoreFrontHeirloomHomePage enterAccountInformation(int randomNum1,int randomNum2,int randomNum3,String SSNCardName){
		driver.type(SSN1_LOC, String.valueOf(randomNum1),"SSN1");
		driver.type(SSN2_LOC, String.valueOf(randomNum2),"SSN2");
		driver.type(SSN3_LOC, String.valueOf(randomNum3),"SSN3");
		driver.type(SSN_CARD_NAME_LOC, String.valueOf(SSNCardName),"SSN Card Name");
		return this;
	}

	public void enterPWS(String pws){
		driver.quickWaitForElementPresent(PWS_LOC);
		driver.type(PWS_LOC, pws,"");
		logger.info("PWS enterd as: "+pws);
	}

	/***
	 * This method is used to click on charge my card button
	 */
	public StoreFrontHeirloomHomePage chargeMyCardAndEnrollMe(){
		driver.click(CHARGE_AND_ENROLL_ME_BTN_LOC,"Charge and enroll me button");
		driver.click(CONFIRM_AUTOSHIP_BTN_LOC,"Confirm autoship button");
		driver.waitForPageLoad();
		return this;
	}

	public boolean isCongratulationsMessageAppeared(){
		driver.pauseExecutionFor(5000);
		driver.waitForElementToBeVisible(CONGRATS_MSG_LOC, 10);
		return driver.isElementVisible(CONGRATS_MSG_LOC);
	}

	public void clickYesSubscribeToPulseNow() {
		driver.scrollToElement(RFWebsiteDriver.driver, driver.findElement(SUBSCRIBE_TO_PULSE_TOGGLE_BTN_LOC));
		driver.click(SUBSCRIBE_TO_PULSE_TOGGLE_BTN_LOC,"subscribe to pulse now option");
	}

	public void clickYesEnrollInCRPNow() {
		driver.scrollToElement(RFWebsiteDriver.driver, driver.findElement(ENROLL_IN_CRP_TOGGLE_BTN_LOC));
		driver.click(ENROLL_IN_CRP_TOGGLE_BTN_LOC,"Enroll in CRP now option");
	}

	public void clickAutoShipOptionsNextBtn() {
		driver.click(AUTOSHIP_OPTIONS_NEXT_BTN_LOC,"Autoships options next step button");
		driver.waitForPageLoad();
	}

	public void selectProductToAddToCart() {
		driver.click(ADD_TO_CART_BTN_LOC,"Add to cart button second product");
	}

	public void selectSecondProductToAddToCart() {
		driver.click(ADD_TO_CART_BTN_SECOND_PRODUCT_LOC,"Add to cart button");
	}

	public void setQuantityOfProductInCRPCart(String qty,String productNum){
		driver.type(By.xpath("//div[@id='actionLabel']/following::*[@class='quantity']["+productNum+"]"), qty+"\t","quantity");
		driver.pauseExecutionFor(2000);
	}

	public void clickYourCRPOrderPopUpNextBtn() {
		driver.click(YOUR_CRP_ORDER_POPUP_NEXT_BTN_LOC,"Your crp order popup next btn");
	}

	public String getTotalSV(){
		return driver.getText(By.xpath("//ul[@class='priceList']/li[2]//cufontext"), "SV");
	}

	public void clickAddMoreButton(){
		driver.click(By.id("AddMoreCRP"), "Add more CRP");
	}

	public void clickBillingInfoNextBtn(){
		driver.pauseExecutionFor(2000);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		try{
			for(int i=1;i<=5;i++){
				actions.moveToElement(driver.findElement(COMPLETE_ACCOUNT_NEXT_BTN_LOC)).pause(1000).doubleClick(driver.findElement(COMPLETE_ACCOUNT_NEXT_BTN_LOC)).build().perform();
				//driver.clickByJS(RFWebsiteDriver.driver,COMPLETE_ACCOUNT_NEXT_BTN_LOC,"");
				logger.info("standard enrollment complete account next button clicked");
				driver.waitForPageLoad();
				driver.pauseExecutionFor(2000);
				if(driver.isElementVisible(COMPLETE_ACCOUNT_NEXT_BTN_LOC, 2)){
					continue;
				}
				else
					break;
			}	
		}catch(Exception e){

		}

	}

	public void openBizPWS(String pws){
		driver.get(pws);
		driver.waitForPageLoad();
		logger.info("page redirected to biz PWS");
	}

	public void clickEnrollNowBtnOnbizPWSPage(){
		WebElement enrollnow = driver.findElement(ENROLL_NOW_ON_BIZ_PWS_PAGE_LOC);
		JavascriptExecutor executor = (JavascriptExecutor)(RFWebsiteDriver.driver);
		executor.executeScript("arguments[0].click();", enrollnow);
		logger.info("Enroll Now button on biz PWS page is clicked");
		driver.waitForPageLoad();
	}

	public void clickEnrollNowBtnOnWhyRFPage(){
		driver.quickWaitForElementPresent(ENROLL_NOW_ON_WHY_RF_PAGE_LOC);
		driver.clickByJS(RFWebsiteDriver.driver,ENROLL_NOW_ON_WHY_RF_PAGE_LOC,"Enroll Now button on Why RF page");
		driver.waitForPageLoad();
	}

	public boolean validateExistingConsultantPopUpCrossCountry(String emailAddress){
		driver.type(ACCOUNT_EMAIL_ADDRESS_LOC, emailAddress,"email address");
		driver.type(ACCOUNT_PASSWORD_LOC, "","password");
		return driver.isElementVisible(EXISTING_CONSULTANT_CROSS_COUNTRY_LOC);
	}

	public void clickClickHereLinkForPC(){
		driver.quickWaitForElementPresent(CLICK_HERE_LINK_FOR_PC);
		driver.click(CLICK_HERE_LINK_FOR_PC,"Click here link");
		driver.waitForPageLoad();
	}

	public void clickEnrollNowBtnForPCAndRC(){
		driver.quickWaitForElementPresent(ENROLL_NOW_FOR_PC_AND_RC);
		driver.click(ENROLL_NOW_FOR_PC_AND_RC,"Enroll now button");
		driver.pauseExecutionFor(2000);
		if(driver.getWindowHandles().size()>1){
			String parentWinHandle = driver.getWindowHandle();
			driver.close();
			driver.switchToChildWindow(parentWinHandle);
			logger.info("switched to second window");
		}

		driver.waitForPageLoad();
		driver.waitForLoadingImageToDisappear();
	}

	//	public boolean isProductAddedSuccessfulyToPCPerks(){
	//		return isProductAddedSuccessfulyToPCPerks;
	//	}

	public void enterProfileDetailsForPCAndRC(String firstName,String lastName,String emailAddress,String password,String phnNumber,String gender){
		driver.type(FIRST_NAME_FOR_PC_AND_RC, firstName,"first name");
		driver.type(LAST_NAME_FOR_PC_AND_RC, lastName,"last name");
		driver.type(EMAIL_ADDRESS_FOR_PC_AND_RC, emailAddress,"email address");
		driver.type(CREATE_PASSWORD_FOR_PC_AND_RC, password,"password");
		driver.type(CONFIRM_PASSWORD_FOR_PC_AND_RC, password,"confirm password");
		driver.type(PHONE_NUMBER_FOR_PC_AND_RC,phnNumber,"phone number");
		driver.click(By.xpath(String.format(genderLocForPCAndRC, gender)),"gender");
	}

	public void enterIDNumberAsSponsorForPCAndRC(String sponsorID){
		driver.type(ID_NUMBER_AS_SPONSOR, sponsorID,"sponsorID");
	}

	public void checkTermsAndConditionChkBoxForPCAndRC(){
		driver.quickWaitForElementPresent(TERMS_AND_CONDITION_CHK_BOX_FOR_PC);
		driver.click(TERMS_AND_CONDITION_CHK_BOX_FOR_PC,"");
		logger.info("Terms & condition checkbox checked");
	}

	public void clickContinueBtnOnAutoshipSetupPageForPC(){
		driver.waitForElementToBeVisible(By.xpath("//*[contains(text(),'now continue')]"), 10);
		driver.click(CONTINUE_BTN_PREFERRED_AUTOSHIP_SETUP_PAGE_LOC,"");
		logger.info("Continue button clicked on Autoship setup page");
		driver.waitForPageLoad();
		driver.getCurrentUrlWithExpectedWaitForURLPresent("AutoshipCart");
	}

	public void enterBillingInfoDetails(String billingName, String firstName,String lastName,String cardName,String cardNumer,String month,String year,String addressLine1,String postalCode,String phnNumber,String CVV){
		driver.type(BILLING_NAME_FOR_BILLING_PROFILE, billingName,"billing profile name");
		driver.type(ATTENTION_FIRST_NAME, firstName,"first name");
		logger.info("Attention first name entered as: "+firstName);
		driver.type(ATTENTION_LAST_NAME, lastName,"last name");
		driver.type(NAME_ON_CARD, cardName,"name on card");
		driver.type(CREDIT_CARD_NUMBER_INPUT_FIELD, cardNumer,"");
		driver.waitForElementPresent(CVV_FIELD);
		driver.type(CVV_FIELD, CVV,"CVV");
		driver.click(EXPIRATION_DATE_MONTH_DD,"Expiration month");
		driver.click(By.xpath(String.format(expiryMonthLoc, month)),"");
		driver.click(EXPIRATION_DATE_YEAR_DD,"");
		driver.click(By.xpath(String.format(expiryYearLoc, year)),"");
		driver.type(ADDRESS_LINE_1, addressLine1,"");
		driver.type(ZIP_CODE, postalCode+"\t","postal code");
		driver.pauseExecutionFor(5000);
		driver.findElement(By.xpath("//input[contains(@id,'uxCityDropDown_Input')]")).click();
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
		driver.pauseExecutionFor(2000);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(CITY_DD)).click().build().perform();
		logger.info("City dropdown clicked");
		//		driver.waitForElementPresent(FIRST_VALUE_OF_CITY_DD);
		driver.findElement(By.xpath("//input[contains(@id,'uxCityDropDown_Input')]")).click();
		driver.click(FIRST_VALUE_OF_CITY_DD,"city"); 
		//		try{
		//			driver.click(FIRST_VALUE_OF_CITY_DD);
		//		}catch(Exception e){
		//			driver.findElement(By.xpath("//input[contains(@id,'uxCityDropDown_Input')]")).click();
		//			driver.waitForElementPresent(FIRST_VALUE_OF_CITY_DD);
		//			driver.click(FIRST_VALUE_OF_CITY_DD); 
		//		}
		//		logger.info("City selected");
		driver.type(PHONE_NUMBER_BILLING_PROFILE_PAGE,phnNumber,"phone number");
	}

	public void clickCompleteEnrollmentBtn(){
		driver.quickWaitForElementPresent(COMPLETE_ENROLLMENT_BTN);
		driver.click(COMPLETE_ENROLLMENT_BTN,"");
		logger.info("Complete enrollmet button clicked");
		//		driver.waitForPageLoad();
	}

	public void enterShippingProfileDetails(String addressName, String firstName,String lastName,String addressLine1,String postalCode,String phnNumber){
		driver.type(ADDRESS_NAME_FOR_SHIPPING_PROFILE, addressName,"address name");
		logger.info("Address name entered as: "+addressName);
		driver.type(ATTENTION_FIRST_NAME, firstName,"first name");
		logger.info("Attention first name entered as: "+firstName);
		driver.type(ATTENTION_LAST_NAME, lastName,"last name");
		logger.info("Attention last name entered as: "+lastName);
		driver.type(ADDRESS_LINE_1, addressLine1,"address line1");
		logger.info("Address line 1 entered as: "+addressLine1);
		driver.type(ZIP_CODE, postalCode+"\t","");
		logger.info("Postal code entered as: "+postalCode,"postal code");
		driver.pauseExecutionFor(5000);//Required
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(CITY_DD)).click().build().perform();
		logger.info("City dropdown clicked");
		driver.pauseExecutionFor(2000);//Required
		driver.waitForElementPresent(FIRST_VALUE_OF_CITY_DD);
		driver.click(FIRST_VALUE_OF_CITY_DD,"first value in city dropdown");
		actions.moveToElement(driver.findElement(COUNTRY_DD)).click().build().perform();
		logger.info("Country dropdown clicked");
		driver.pauseExecutionFor(1000);
		driver.click(FIRST_VALUE_OF_COUNTRY_DD,"first value in country dropdown");
		driver.type(PHONE_NUMBER_SHIPPING_PROFILE_PAGE,phnNumber,"phone number");

	}

	public boolean isEnrollmentCompletedSuccessfully(){
		driver.quickWaitForElementPresent(WELCOME_TXT_AFTER_ENROLLMENT);
		return driver.isElementPresent(WELCOME_TXT_AFTER_ENROLLMENT);
	}

	public void clickClickHereLinkForRC(){
		driver.click(CLICK_HERE_LINK_FOR_RC,"link for RC User");
	}

	public void clickCreateMyAccountBtnOnCreateRetailAccountPage(){
		driver.click(CONTINUE_BTN_LOC,"Create my account button");
		try{
			clickOKBtnOfJavaScriptPopUp();
		}catch(Exception e){
			logger.info("No alert present");
		}
	}

	public String getJavaScriptPopUpText(){
		Alert alert = driver.switchTo().alert();
		String alertTxt=alert.getText();
		logger.info("Txt From java Script popup is "+alertTxt);
		return alertTxt;
	}

	public void selectBusinessPortfolioEnrollmentKit(){
		driver.quickWaitForElementPresent(BUSINESS_PORTFOLIO_KIT_LOC);
		driver.click(BUSINESS_PORTFOLIO_KIT_LOC,"");
		logger.info("Business Portfolio Kit is selected");
	}

	public void clickNextBtnOnSelectEnrollmentKitPage(){
		driver.quickWaitForElementPresent(SELECT_ENROLLMENT_KIT_NEXT_BTN_LOC);
		driver.click(SELECT_ENROLLMENT_KIT_NEXT_BTN_LOC,"");
		logger.info("Business Portfolio Kit is selected");
		driver.waitForPageLoad();
	}

	public void clickAccountInformationNextBtn(){
		driver.quickWaitForElementPresent(ACCOUNT_INFORMATION_NEXT_BTN);
		driver.click(ACCOUNT_INFORMATION_NEXT_BTN,"");
		logger.info("Account Information Next button clicked");
		driver.waitForPageLoad();
	}

	public void clickChargeMyCardAndEnrollMeWithOutConfirmAutoship(){
		driver.click(CHARGE_AND_ENROLL_ME_BTN_LOC,"Charge and enroll me button");
		driver.pauseExecutionFor(1000);
		driver.waitForPageLoad();
	}

	public void clickSubSectionUnderRegimen(String regimenHeader){
		driver.quickWaitForElementPresent(By.xpath(String.format(regimenHeaderLoc, regimenHeader)));
		driver.click(By.xpath(String.format(regimenHeaderLoc, regimenHeader)),"");
		logger.info("Regimen selected is: "+regimenHeader);
	}

	public boolean verifyUserIsRedirectedToProductsPage(String regimenType) {
		driver.waitForElementPresent(PRODUCTS_LIST_LOC);
		return driver.isElementPresent(PRODUCTS_LIST_LOC)&& driver.isElementPresent(By.xpath(String.format(regimenImageHeaderLoc, regimenType.toUpperCase()))); 
	}

	public boolean verifyUserIsRedirectedToResultsPage(String regimenType) {
		try{
			driver.waitForElementPresent(RESULTS_TEXT_LOC);
			driver.click(RESULTS_TEXT_LOC,"");
			return driver.isElementPresent(RESULTS_TEXT_LOC);
		}catch(Exception e){
			return driver.isElementPresent(By.xpath("//div[@id='RFContent']//span[contains(text(),'REAL RESULTS')]"));
		}
	}

	public boolean verifyUserIsRedirectedToTestimonialsPage() {
		// TODO Auto-generated method stub
		driver.waitForElementPresent(TESTIMONIAL_PAGE_CONTENT_LOC);
		return driver.isElementPresent(TESTIMONIAL_PAGE_CONTENT_LOC);
	}

	public boolean verifyUserIsRedirectedToNewsPage() {
		driver.waitForElementPresent(NEWS_TEXT_LOC);
		return driver.isElementPresent(NEWS_TEXT_LOC);
	}

	public boolean verifyUserIsRedirectedToFAQsPage(String regimenType) {
		driver.waitForElementPresent(FAQS_TEXT_LOC);
		return driver.isElementPresent(FAQS_TEXT_LOC);
	}

	public boolean verifyUserIsRedirectedToAdvicePage() {
		driver.waitForElementPresent(ADVICE_PAGE_CONTENT_LOC);
		return driver.isElementPresent(ADVICE_PAGE_CONTENT_LOC);
	}

	public boolean verifyUserIsRedirectedToGlossaryPage() {
		driver.waitForElementPresent(GLOSSARY_PAGE_CONTENT_LOC);
		return driver.isElementPresent(GLOSSARY_PAGE_CONTENT_LOC);
	}

	public void clickIngredientsAndUsageLink() {
		driver.waitForElementPresent(INGREDIENTS_AND_USAGE_LINK_LOC);
		driver.click(INGREDIENTS_AND_USAGE_LINK_LOC,"");
		logger.info("INGREDIENTS AND USAGE LINK CLICKED");
	}

	public boolean verifyIngredientsAndUsageInfoVisible() {
		driver.waitForElementPresent(INGREDIENTS_CONTENT_LOC);
		return driver.IsElementVisible(driver.findElement(INGREDIENTS_CONTENT_LOC));

	}

	public void clickContactUsAtFooter() {
		driver.waitForElementPresent(FOOTER_CONTACT_US_LINK_LOC);
		driver.click(FOOTER_CONTACT_US_LINK_LOC,"");
		logger.info("contact us link is clicked");
		driver.waitForPageLoad();
	}

	public boolean verifylinkIsRedirectedToContactUsPage() {
		try{
			driver.waitForElementPresent(CONTACT_US_PAGE_HEADER_LOC);
			return driver.findElement(CONTACT_US_PAGE_HEADER_LOC).isDisplayed();
		}catch(Exception e){
			return driver.findElement(CONTACT_US_PAGE_LOC).isDisplayed();
		}
	}

	public void clickProductPhilosophyLink(){
		driver.quickWaitForElementPresent(PRODUCT_PHILOSOPHY_LOC);
		driver.click(PRODUCT_PHILOSOPHY_LOC,"");
		logger.info("Product Philosophy Link clicked");
		driver.waitForPageLoad();
	}

	public void clickDigitalProductCatalogLink(){
		driver.quickWaitForElementPresent(DIGITAL_PRODUCT_CATALOG_LOC);
		driver.click(DIGITAL_PRODUCT_CATALOG_LOC,"");
		logger.info("Digital Product Catalog Link clicked");
		driver.waitForPageLoad();
	}

	public boolean validateProductPhilosohyPageDisplayed(){
		return driver.getCurrentUrl().contains("Products/Philosophy");
	}

	public boolean validateRealResultsLink(){
		driver.quickWaitForElementPresent(REAL_RESULTS_LINK_LOC);
		driver.click(REAL_RESULTS_LINK_LOC,"");
		logger.info("Real Results Link clicked");
		driver.waitForPageLoad();
		boolean status= driver.getCurrentUrl().contains("Results");
		driver.navigate().back();
		return status;
	}

	public boolean validateExecutiveTeamLinkPresent(){
		driver.quickWaitForElementPresent(EXECUTIVE_TEAM_LINK_LOC);
		return driver.isElementPresent(EXECUTIVE_TEAM_LINK_LOC);
	}

	public String getCareersLinkURL(){
		return driver.getCurrentUrlWithExpectedWaitForURLPresent("careers");
	}

	public boolean validateContactUsLinkPresent(){
		driver.quickWaitForElementPresent(CONTACT_US_LINK_LOC);
		return driver.isElementPresent(CONTACT_US_LINK_LOC);
	}

	public boolean validateWhoWeAreLinkPresent(){
		driver.quickWaitForElementPresent(WHO_WE_ARE_LINK_LOC);
		return driver.isElementPresent(WHO_WE_ARE_LINK_LOC);
	}

	public boolean validatePressRoomLinkPresent(){
		driver.quickWaitForElementPresent(PRESS_ROOM_LINK_LOC);
		return driver.isElementPresent(PRESS_ROOM_LINK_LOC);
	}

	public boolean validateSolutionToolLink(){
		driver.quickWaitForElementPresent(SOLUTION_TOOL_LINK_LOC);
		driver.click(SOLUTION_TOOL_LINK_LOC,"");
		logger.info("Solution Tool Link clicked");
		driver.waitForPageLoad();
		boolean status= driver.getCurrentUrl().contains("SolutionTool");
		driver.navigate().back();
		return status;
	}

	public boolean validatePCPerksLink(){
		driver.quickWaitForElementPresent(PC_PERKS_LINK_LOC);
		driver.click(PC_PERKS_LINK_LOC,"");
		logger.info("PC Perks Link clicked");
		driver.waitForPageLoad();
		boolean status= driver.getCurrentUrl().contains("PCPerks");
		driver.navigate().back();
		return status;
	}

	public boolean validateDigitalProductCatalogLink(){
		driver.quickWaitForElementPresent(DIGITAL_PRODUCT_LINK_LOC);
		driver.click(DIGITAL_PRODUCT_LINK_LOC,"");
		logger.info("Digital Product Link clicked");
		driver.waitForPageLoad();
		boolean status= driver.getCurrentUrl().contains("Digimag");
		driver.navigate().back();
		return status;
	}

	public void clickAboutRFBtn(){
		driver.quickWaitForElementPresent(ABOUT_RF_LOC);
		driver.click(ABOUT_RF_LOC,"");
		logger.info("About RF button clicked");
		driver.waitForPageLoad();
	}

	public void clickPrivacyPolicyLink(){
		driver.quickWaitForElementPresent(PRIVACY_POLICY_LINK);
		driver.click(PRIVACY_POLICY_LINK,"");
		logger.info("Privacy policy link clicked");
		driver.waitForPageLoad();
	}

	public boolean isPrivacyPolicyPagePresent(){
		driver.quickWaitForElementPresent(PRIVACY_POLICY_TEXT);
		return driver.isElementPresent(PRIVACY_POLICY_TEXT);
	}

	public void clickSatisfactionGuaranteeLink(){
		driver.click(SATISFACTION_GUARANTEE_LINK,"Satisfaction guarantee link");
		driver.waitForPageLoad();
	}

	public boolean isSatisfactionGuaranteePagePresent(){
		driver.quickWaitForElementPresent(SATISFACTION_GUARANTEE_TEXT);
		return driver.isElementPresent(SATISFACTION_GUARANTEE_TEXT);
	}

	public void selectPromotionRegimen(){
		String regimen = "Promotions";
		driver.quickWaitForElementPresent(By.xpath(String.format(regimenLoc, regimen)));
		driver.click(By.xpath(String.format(regimenLoc, regimen)),"");
		logger.info("Regimen selected is: "+regimen);
	}

	public boolean isRegimenNamePresentAfterClickedOnRegimen(String regimen){
		regimen = regimen.toUpperCase();
		driver.quickWaitForElementPresent(By.xpath(String.format(regimenNameLoc, regimen)));
		return driver.isElementPresent(By.xpath(String.format(regimenNameLoc, regimen)));
	}

	public void clickTermsAndConditionsLink(){
		driver.quickWaitForElementPresent(TERMS_AND_CONDITIONS_LINK);
		driver.click(TERMS_AND_CONDITIONS_LINK,"");
		logger.info("Terms & Conditions link clicked");
		driver.waitForPageLoad();
	}

	public void clickSolutionToolUnderProduct(){
		driver.quickWaitForElementPresent(SOLUTION_TOOL_LOC);
		driver.click(SOLUTION_TOOL_LOC,"");
		logger.info("Solution tool clicked under product tab button");
		driver.waitForPageLoad();
	}

	public boolean verifySolutionToolPage(){
		driver.quickWaitForElementPresent(SOLUTION_TOOL_PAGE_LOC);
		return driver.isElementPresent(SOLUTION_TOOL_PAGE_LOC);
	}

	public void clickFindRodanFieldConsultantLink(){
		driver.quickWaitForElementPresent(FIND_RODAN_FIELD_CONSULTANT_LINK_LOC);
		driver.click(FIND_RODAN_FIELD_CONSULTANT_LINK_LOC,"");
		logger.info("Find rodan and fields consultant link is clicked.");
		driver.waitForPageLoad();
	}

	public void selectSponsorRadioBtnOnFindConsultantPage(){
		driver.quickWaitForElementPresent(SPONSOR_RADIO_BTN_ON_FIND_CONSULTANT_PAGE);
		driver.click(SPONSOR_RADIO_BTN_ON_FIND_CONSULTANT_PAGE,"");
		logger.info("Radio button of sponsor is selected");
	}

	public String getPWSFromFindConsultantPage(){
		driver.quickWaitForElementPresent(PWS_TXT_ON_FIND_CONSULTANT_PAGE);
		String fetchPWS=driver.findElement(PWS_TXT_ON_FIND_CONSULTANT_PAGE).getText();
		logger.info("Fetched PWS from find a consultant page is"+fetchPWS);
		return fetchPWS;
	}

	public boolean verifyRedefineRegimenSections(String sublinkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(redefineRegimenSubLinks, sublinkName)));
		return driver.IsElementVisible(driver.findElement(By.xpath(String.format(redefineRegimenSubLinks, sublinkName))));
	}

	public boolean verifyAddToCartButton() {
		driver.quickWaitForElementPresent(ADD_TO_CART_BTN);
		return driver.isElementPresent(ADD_TO_CART_BTN);
	}

	public boolean verifyCheckoutBtnOnMyShoppingCart() {
		driver.quickWaitForElementPresent(CHECKOUT_BTN);
		return driver.isElementPresent(CHECKOUT_BTN);
	}

	public void clickClickhereLink(){
		driver.quickWaitForElementPresent(CLICK_HERE_LINK);
		driver.click(CLICK_HERE_LINK,"");
		logger.info("Clicke here link clicked");
		driver.waitForPageLoad();
	}

	public boolean isClickHereLinkRedirectinToAppropriatePage(String redirectedPageLink){
		driver.waitForPageLoad();
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		String parentWindow=it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
		boolean status= driver.getCurrentUrl().contains(redirectedPageLink);
		driver.close();
		driver.switchTo().window(parentWindow);
		return status;
	}

	public void clickDetailsLink(){
		driver.quickWaitForElementPresent(DETAILS_LINK);
		driver.click(DETAILS_LINK,"");
		logger.info("Details link clicked");
		driver.waitForPageLoad();
	}

	public boolean verifySubSectionPresentAtBusinessSystemPage(
			String SubSectionUnderBusinessSystem) {
		return driver.isElementPresent(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, SubSectionUnderBusinessSystem)));
	}

	public void clickSubSectionUnderBusinessSystem(String secondSubSectionUnderBusinessSystem) {
		driver.quickWaitForElementPresent(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, secondSubSectionUnderBusinessSystem)));
		driver.click(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, secondSubSectionUnderBusinessSystem)),"");
		logger.info("business System SubTitle selected is: "+secondSubSectionUnderBusinessSystem);
		driver.waitForPageLoad();
	}

	public boolean verifySubSectionPresentAtProgramsAndIncentives(String firstSubSectionUnderProgramsAndIncentives) {
		String firstSubsectionInUpperCase = firstSubSectionUnderProgramsAndIncentives.toUpperCase();
		driver.waitForElementPresent(By.xpath(String.format("//*[contains(text(),'%s') or contains(text(),'"+firstSubsectionInUpperCase+"')]", firstSubSectionUnderProgramsAndIncentives)));
		return driver.isElementPresent(By.xpath(String.format("//*[contains(text(),'%s') or contains(text(),'"+firstSubsectionInUpperCase+"')]", firstSubSectionUnderProgramsAndIncentives))); 
	}

	public void clickToReadIncomeDisclosure() {
		driver.quickWaitForElementPresent(CLICK_HERE_LOC);
		driver.clickByJS(RFWebsiteDriver.driver,CLICK_HERE_LOC,"");
		driver.waitForPageLoad();
	}

	public String getCurrentUrlOpenedWindow() {
		driver.pauseExecutionFor(5000);
		String parentWindowID=driver.getWindowHandle();
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			String childWindowID=it.next();
			if(!parentWindowID.equalsIgnoreCase(childWindowID)){
				driver.switchTo().window(childWindowID);
				driver.waitForPageLoad();
				driver.pauseExecutionFor(3000);
				String currentUrl = driver.getCurrentUrl();
				driver.close();
				driver.switchTo().window(parentWindowID);
				return currentUrl;
			}
		}
		return null;
	}


	public String getSelectedHighlightLinkName() {
		driver.waitForElementPresent(SELECTED_HIGHLIGHT_LINK);
		String linkName = driver.findElement(SELECTED_HIGHLIGHT_LINK).getText();
		logger.info("Selected And highlight link: "+linkName);
		return linkName;
	}

	public void clickDetailsLinkUnderProgramsIncentivePage(String sectionName) {
		driver.quickWaitForElementPresent(By.xpath(String.format(detailLinkOnProgramIncentivePageLoc,sectionName)));
		driver.click(By.xpath(String.format(detailLinkOnProgramIncentivePageLoc,sectionName)),"");
		logger.info("detail link clicked for section :"+sectionName);

	}

	public boolean verifyEssentialsRegimenSections(String sublinkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(essentialsRegimenSubLinks, sublinkName)));
		return driver.IsElementVisible(driver.findElement(By.xpath(String.format(essentialsRegimenSubLinks, sublinkName))));
	}

	public boolean verifyUserIsRedirectedToRealResultsPage() {
		driver.waitForElementPresent(REAL_RESULTS_PAGE_LOC);
		return driver.isElementPresent(REAL_RESULTS_PAGE_LOC); 
	}

	public boolean verifyUserIsRedirectedToPCPerksPage() {
		driver.waitForElementPresent(PC_PERKS_PAGE_LOC);
		return driver.isElementPresent(PC_PERKS_PAGE_LOC); 
	}

	public boolean verifyUserIsRedirectedToSolutionToolPage() {
		driver.waitForElementPresent(SOLUTION_TOOL_PAGE);
		return driver.isElementPresent(SOLUTION_TOOL_PAGE); 
	}

	public boolean verifyUserIsRedirectedToDigitalProductCataloguePage() {
		driver.waitForElementPresent(DIGITAL_PRODUCT_CATALOGUE);
		return driver.isElementPresent(DIGITAL_PRODUCT_CATALOGUE); 
	}

	public boolean verifyEnhancementsRegimenSections(String sublinkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(enhancementsRegimenSubLinks, sublinkName)));
		return driver.IsElementVisible(driver.findElement(By.xpath(String.format(enhancementsRegimenSubLinks, sublinkName))));
	}

	public void clickSubSectionUnderBusinessSystemTab(String tabName) {
		driver.quickWaitForElementPresent(By.xpath(String.format(businessSystemSubTitleLoc, tabName)));
		driver.click(By.xpath(String.format(businessSystemSubTitleLoc, tabName)),"");
		logger.info("business System SubTitle selected is: "+tabName);
	}

	public boolean verifyBusinessPresentationSectionUnderEvents() {
		driver.quickWaitForElementPresent(BUSINESS_PRESENTATION_SECTION_LOC);
		return driver.IsElementVisible(driver.findElement(BUSINESS_PRESENTATION_SECTION_LOC));
	}

	public void clickHomeTabBtn(){
		driver.quickWaitForElementPresent(HOME_TAB_LOC);
		driver.click(HOME_TAB_LOC,"");
		logger.info("Home button clicked");
		driver.waitForPageLoad();
	}

	public void clickFindAConsultantImageLink(){
		driver.quickWaitForElementPresent(FIND_A_CONSULTANT_IMAGE_LOC);
		driver.click(FIND_A_CONSULTANT_IMAGE_LOC,"");
		logger.info("Find a consultant Image Link Is clicked");
		driver.waitForPageLoad();
	}

	public boolean isStartNowBtnRedirectinToAppropriatePage(String redirectedPageLink){
		driver.waitForPageLoad();
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		String parentWindow=it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
		boolean status= driver.getCurrentUrl().contains(redirectedPageLink);
		driver.close();
		driver.switchTo().window(parentWindow);
		return status;
	}

	public void clickStartNowBtn(){
		driver.quickWaitForElementPresent(START_NOW_BTN);
		driver.click(START_NOW_BTN,"");
		logger.info("Start now btn clicked");
		driver.waitForPageLoad();
	}

	public boolean validateWhoWeAreLink(){
		driver.quickWaitForElementPresent(WHO_WE_ARE_LINK_LOC);
		driver.click(WHO_WE_ARE_LINK_LOC,"");
		logger.info("Who We Are Link clicked");
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("About");
	}

	public boolean validateCompanyCareersLink(){
		driver.quickWaitForElementPresent(CARRERS_LINK_LOC);
		driver.click(CARRERS_LINK_LOC,"");
		logger.info("Company Careers Link clicked");
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("Company/careers");
	}

	public boolean validateCompanyPressRoomLink(){
		driver.quickWaitForElementPresent(PRESS_ROOM_LINK_LOC);
		driver.click(PRESS_ROOM_LINK_LOC,"");
		logger.info("Company Press Room Link clicked");
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("Company/PR");
	}

	public boolean validateCompanyContactUsLink(){
		driver.quickWaitForElementPresent(CONTACT_US_LINK_LOC);
		driver.click(CONTACT_US_LINK_LOC,"");
		logger.info("Company Contact Us Link clicked");
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("Company/Contact");
	}

	public boolean validateEnrollNowLinkPresent(){
		driver.quickWaitForElementPresent(ENROLL_NOW_LINK_LOC);
		return driver.isElementPresent(ENROLL_NOW_LINK_LOC);
	}

	public boolean validateMeetOurCommunityLinkPresent(){
		driver.quickWaitForElementPresent(MEET_OUR_COMMUNITY_LINK_LOC);
		return driver.isElementPresent(MEET_OUR_COMMUNITY_LINK_LOC);
	}

	public boolean validateEventsLinkPresent(){
		driver.quickWaitForElementPresent(EVENTS_LINK_LOC);
		return driver.isElementPresent(EVENTS_LINK_LOC);
	}

	public boolean validateIncomeIllustratorLinkPresent(){
		driver.quickWaitForElementPresent(INCOME_ILLUSTRATOR_LINK_LOC);
		return driver.isElementPresent(INCOME_ILLUSTRATOR_LINK_LOC);
	}

	public boolean validateProgramsAndIncentivesLinkPresent(){
		driver.quickWaitForElementPresent(PROGRAMS_INCENTIVES_LINK_LOC);
		return driver.isElementPresent(PROGRAMS_INCENTIVES_LINK_LOC);
	}

	public boolean validateWhyRFLinkPresent(){
		driver.quickWaitForElementPresent(WHY_RF_LINK_LOC);
		return driver.isElementPresent(WHY_RF_LINK_LOC);
	}

	public void clickWhyRFLinkUnderBusinessSystem(){
		driver.quickWaitForElementPresent(WHY_RF_LINK_LOC);
		driver.click(WHY_RF_LINK_LOC,"");
		logger.info("Why RF Link clicked");
		driver.waitForPageLoad();
	}

	public void clickBusinessKitsUnderWhyRF(){
		driver.quickWaitForElementPresent(BUSINESS_KITS_LINK_LOC);
		driver.click(BUSINESS_KITS_LINK_LOC,"");
		logger.info("Business Kits Link clicked");
		driver.waitForPageLoad();
	}

	public boolean validateBusinessKitSectionIsDisplayed(){
		driver.quickWaitForElementPresent(BUSINESS_KITS_SECTION_LOC);
		return driver.isElementPresent(BUSINESS_KITS_SECTION_LOC);
	}

	public void clickRedefineYourFutureLinkUnderWhyRF(){
		driver.quickWaitForElementPresent(REDEFINE_YOUR_FUTURE_LINK_LOC);
		driver.click(REDEFINE_YOUR_FUTURE_LINK_LOC,"");
		logger.info("Redefine Your Future Link clicked");
		driver.waitForPageLoad();
	}

	public boolean validateRedefineYourFuturePageDisplayed(){
		return driver.getCurrentUrl().contains("Business/Redefine");
	}

	public void clickEnrollNowLinkUnderWhyRF(){
		driver.quickWaitForElementPresent(ENROLL_NOW_LINK_UNDER_WHYRF_LOC);
		driver.click(ENROLL_NOW_LINK_UNDER_WHYRF_LOC,"");
		logger.info("Enroll Now Link clicked");
		driver.waitForPageLoad();
	}


	public boolean validateSearchSponsorPageDisplayed(){
		return driver.getCurrentUrl().contains("NewEnrollment/SearchSponsor");
	}

	public void clickEventsLinkUnderBusinessSystem(){
		driver.quickWaitForElementPresent(EVENTS_LINK_LOC);
		driver.click(EVENTS_LINK_LOC,"");
		logger.info("Events Link clicked");
		driver.waitForPageLoad();
	}

	public boolean validateUpcomingEventsLinkIsPresent(){
		driver.quickWaitForElementPresent(UPCOMING_EVENTS_LINK_LOC);
		return driver.isElementPresent(UPCOMING_EVENTS_LINK_LOC);
	}

	public void clickGivingBackLinkUnderAboutRF(){
		driver.quickWaitForElementPresent(GIVING_BACK_LINK_LOC);
		driver.click(GIVING_BACK_LINK_LOC,"");
		logger.info("Giving Back Link clicked");
		driver.waitForPageLoad();
	}

	public boolean validateCompanyPFCFoundationPageDisplayed(){
		return driver.getCurrentUrl().contains("Company/PFCFoundation/Mission");
	}

	public void clickSolutionToolImageLink(){
		driver.quickWaitForElementPresent(SOLUTION_TOOL_IMAGE_LOC);
		driver.click(SOLUTION_TOOL_IMAGE_LOC,"");
		logger.info("Solution tool clicked under Home tab button");
		driver.waitForPageLoad();
	}

	public void clickRFInTheNewsImageLink(){
		driver.quickWaitForElementPresent(RF_IN_THE_NEWS_IMAGE_LOC);
		driver.click(RF_IN_THE_NEWS_IMAGE_LOC,"");
		logger.info("R+F In the news Image Link Is clicked");
		driver.waitForPageLoad();
	}

	public void loginAsUserOnCheckoutPage(String username,String password){
		driver.type(USERNAME_TXTFLD_CHECKOUT_PAGE_LOC, username,"username");
		driver.click(PASSWORD_TXTFLD_CHECKOUT_PAGE_LOC,"password");
		driver.type(PASSWORD_TXTFLD_CHECKOUT_PAGE_LOC,password,"password");
		driver.waitForElementPresent(SIGN_IN_BTN_CHECKOUT_PAGE_LOC, 5);
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(SIGN_IN_BTN_CHECKOUT_PAGE_LOC)).click().build().perform();  
		driver.waitForPageLoad();
		acceptPoliciesAndProcedures();
	}

	public void clickRenewLater(){
		if(driver.isElementVisible(RENEW_LATER_LINK,4)){
			driver.click(RENEW_LATER_LINK,"renew later link");
			driver.waitForPageLoad();
		} 
	}


	public boolean isRegimenNamePresent(String regimen){
		regimen = regimen.toUpperCase();
		driver.quickWaitForElementPresent(By.xpath(String.format(regimenNameOnPwsLoc, regimen)));
		return driver.isElementPresent(By.xpath(String.format(regimenNameOnPwsLoc, regimen)));
	}

	public void clickRegimenOnPWS(String regimen){
		driver.quickWaitForElementPresent(By.xpath(String.format(regimenImageOnPwsLoc, regimen)));
		driver.click(By.xpath(String.format(regimenImageOnPwsLoc, regimen)),"");
		logger.info("Regimen selected is: "+regimen);
	}

	public boolean validateChangePasswordMessagePrompt(){
		driver.quickWaitForElementPresent(CHANGE_PASSWORD_TEXT_LOC);
		return driver.isElementPresent(CHANGE_PASSWORD_TEXT_LOC);
	}

	public void enterEmailAddressToRecoverPassword(String emailID){
		driver.waitForElementPresent(EMAIL_ADDRESS_FIELD_LOC);
		driver.type(EMAIL_ADDRESS_FIELD_LOC, emailID,"");
		logger.info("email address entered to recover password is "+emailID);
	}

	public void clickSendEmailButton(){
		driver.quickWaitForElementPresent(SEND_EMAIL_BTN_LOC);
		driver.click(SEND_EMAIL_BTN_LOC,"");
		logger.info("Send Email Button clicked");
		driver.pauseExecutionFor(3000);
	}

	public boolean validatePasswordChangeAndEmailSent(){
		Alert alt=driver.switchTo().alert();
		boolean status= alt.getText().contains("An email has been sent to your email address with a link for resetting your password");
		alt.accept();
		return status;
	}

	/***
	 * This method is used to click on the change link under shipping
	 */
	public void clickChangeLinkUnderShippingToOnPWS(){
		driver.click(CHANGE_LINK_FOR_SHIPPING_INFO_ON_PWS,"Change Link under shipping");
		driver.waitForPageLoad();
	}

	public String getShippingAddressName(){
		return  driver.getText(SHIPPING_ADDRESS_NAME_LOC,"shipping address name");
	}

	public void clickUseThisAddressShippingInformationBtn(){
		driver.click(USE_THIS_ADDRESS_SHIPPING_INFORMATION,"Use this Address shipping information");
		driver.waitForPageLoad();
	}

	public void clickEnrollNowBtnAtWhyRFPage(){
		driver.waitForElementPresent(ENROLL_NOW_LINK);
		driver.click(ENROLL_NOW_LINK,"");
		logger.info("Enroll now button clicked");
		driver.waitForPageLoad();
	}

	public void selectConsultantEnrollmentKitByPrice(String price) {
		JavascriptExecutor js = (JavascriptExecutor) RFWebsiteDriver.driver;
		js.executeScript("selectKit(269)");
		driver.waitForPageLoad();
	}

	/***
	 * This method is used to click on the regimen while consultant enrollment
	 * @param regimen
	 */
	public StoreFrontHeirloomHomePage selectRegimenForConsultant(String regimen){
		regimen = regimen.toUpperCase();
		driver.click(By.xpath(String.format(consultantRegimenLoc, regimen)),regimen);
		return this;
	}

	/***
	 * This method is used to click on the NEXT button after selecting regimen while consultant enrollment
	 * @param regimen
	 */
	public StoreFrontHeirloomHomePage clickNextBtnAfterSelectRegimen(){
		driver.click(REGIMEN_NEXT_BTN_LOC,"Regimen Next button");
		return this;
	}

	public String getBizPWSBeforeEnrollment(){
		driver.waitForElementPresent(WEBSITE_PREFIX_BIZ_PWS);
		String bizPWS = driver.findElement(WEBSITE_PREFIX_BIZ_PWS).getText().split("\\ ")[0];
		logger.info("Biz PWS before enrollment is: "+bizPWS);
		return bizPWS;
	}

	public void clickChangeMyPcPerksStatus() {
		driver.waitForElementPresent(CHANGE_MY_PC_PERKS_STATUS_UNDER_MYACCOUNT_LINK);
		driver.click(CHANGE_MY_PC_PERKS_STATUS_UNDER_MYACCOUNT_LINK,"");
		logger.info("change my pc perks status link is clicked");

	}

	public void clickDelayCancelPcPerksLink() {
		driver.quickWaitForElementPresent(DELAY_OR_CANCEL_PC_PERKS_LINK); 
		driver.click(DELAY_OR_CANCEL_PC_PERKS_LINK,"");
		logger.info("delay or cancel pc perks link is clicked");
	}

	public void clickPopUpYesChangeMyAutoshipDate() {
		driver.waitForElementPresent(YES_CHANGE_MY_AUTOSHIP_DATE_BTN);
		driver.click(YES_CHANGE_MY_AUTOSHIP_DATE_BTN,"");
		logger.info("yes change my autoship date is clicked");

	}

	public boolean verifyConfirmationMessageInOrders() {
		return driver.isElementVisible(CONFIRM_MSG_AT_ORDERS_LOC);
	}

	public void selectSecondRadioButton() {
		driver.click(RADIO_BUTTON_FOR_SIXTY_DAYS_LOC,"");
		logger.info("radio button for 60 days is selected");
	}

	/***
	 * This method is used to click on edit order button
	 */
	public void clickEditOrderbtn() {
		driver.click(EDIT_ORDER_BTN_LOC,"edit order button");
	}

	/***
	 * This method is used to click on all the remove links(above total label) on autoship cart 
	 */
	public StoreFrontHeirloomHomePage clickRemoveLinkAboveTotal() {
		int numberofRemoveLink = driver.findElements(TOTAL_ITEM_LOC).size();
		for(int i =1;i<=numberofRemoveLink;i++ ){
			driver.click(REMOVE_LINK_LOC,"remove link");
			driver.waitForStorfrontLegacyLoadingImageToDisappear();
		}
		return this;
	}

	public void clickAddToCartBtnForLowPriceItems() {
		int numberofAddToCartLink = driver.findElements(ADD_TO_CART_LINK_LOC).size();
		for(int i = 1;i<=numberofAddToCartLink;i++){
			String LowerPrice = driver.getText(By.xpath(String.format(retailPriceOfItem,i)));
			String []split = LowerPrice.split("\\$")[1].split("\\.");
			int value = Integer.parseInt(split[0]);
			if(value<50){
				driver.click(By.xpath(String.format(addToCartBtnLoc,i)),"");
				driver.waitForStorfrontLegacyLoadingImageToDisappear();
				break;
			}else
				continue;
		}
	}

	/***
	 * this method is used to add all the products to the cart having price>80
	 */
	public void clickAddToCartBtnForHighPriceItems() {
		int numberofAddToCartLink = driver.findElements(ADD_TO_CART_LINK_LOC).size();
		for(int i = 1;i<=numberofAddToCartLink;i++){
			String highPrice = driver.getText(By.xpath(String.format(retailPriceOfItem,i)));
			String []split = highPrice.split("\\$")[1].split("\\.");
			int value = Integer.parseInt(split[0]);
			if(value>80){
				//				driver.waitForElementPresent(By.xpath(String.format(addToCartBtnLoc,i)));
				//				JavascriptExecutor executor = (JavascriptExecutor)(RFWebsiteDriver.driver);
				//				executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(String.format(addToCartBtnLoc,i))));
				driver.click(By.xpath(String.format(addToCartBtnLoc,i)),"add to cart button for product sequence num="+i);
				driver.waitForStorfrontLegacyLoadingImageToDisappear();
				break;
			}else
				continue;
		}
		driver.pauseExecutionFor(2000);
		driver.waitForElementToBeVisible(By.xpath("//*[contains(text(),'now continue')]"), 15);
	}

	/***
	 * This method is used to verify if the threshold message for minimum 80$ value is displayed or not
	 * @return
	 */
	public boolean isStatusMessageDisplayed() {
		return driver.isElementVisible(STATUS_THRESHOLD_MSG_FOR_MIN_VALUE_LOC);
	}

	public void clickOnUpdateOrderBtn() {
		//		JavascriptExecutor executor = (JavascriptExecutor)(RFWebsiteDriver.driver);
		//		executor.executeScript("arguments[0].click();",driver.findElement(UPDATE_ORDER_BTN_LOC));
		//driver.pauseExecutionFor(5000);
		driver.clickByJS(RFWebsiteDriver.driver,UPDATE_ORDER_BTN_LOC,"update order button");
		driver.pauseExecutionFor(3000);
	}

	public String getConfirmationMessage() {
		String confirmationMessage = driver.getText(MSG_UPDATE_ORDER_CONFIRMATION_LOC);
		System.out.println(confirmationMessage+"confirmatin mssge");
		return confirmationMessage;

	}

	public void clickSectionUnderReplenishmentOrderManagement(String subSectionName) {
		driver.quickWaitForElementPresent(By.xpath(String.format(sectionUnderReplenishmentOrderManagementLoc, subSectionName)));
		driver.click(By.xpath(String.format(sectionUnderReplenishmentOrderManagementLoc, subSectionName)),"");
		logger.info("subSection"+subSectionName+" link is clicked");

	}

	/***
	 * This method is used to get the Order total from cart
	 * @return
	 */
	public String getOrderTotal() {
		return driver.getText(ORDER_TOTAL_LOC);
	}

	/***
	 * This method is used to get the Order total from Orders page
	 * @return
	 */
	public String getOrderTotalAtOverview() {
		return  driver.getText(ORDER_TOTAL_AT_OVERVIEW_LOC,"Order total from orders page");
	}

	public String getSplittedPrefixFromConsultantUrl(String url) {
		String requiredPrefix[] = url.split("\\//")[1].split("\\.");
		return requiredPrefix[0];
	}

	public boolean isExistingPrefixAvailable() {
		driver.waitForElementPresent(PREFIX_SUGGESTIONS_LIST);
		return driver.isElementPresent(PREFIX_SUGGESTIONS_LIST);
	}

	public void clickChangeBillingInformationLinkUnderBillingTabOnPWS(){
		driver.quickWaitForElementPresent(CHANGE_BILLING_INFO_LINK_ON_PWS);
		driver.click(CHANGE_BILLING_INFO_LINK_ON_PWS,"");
		logger.info("Change Billing info link clicked");
		driver.waitForPageLoad();
	}

	public String getOrderBillingDetailsUpdateMessage(){
		driver.waitForElementPresent(EDIT_ORDER_BILLING_DETAILS_UPDATE_MESSAGE);
		String messgae = driver.findElement(EDIT_ORDER_BILLING_DETAILS_UPDATE_MESSAGE).getText();
		logger.info("Order updation message is: "+messgae);
		return messgae;
	}

	public String getModifiedPWSValue(String url,String availablityText) {
		String requiredPrefix[] = url.split("\\//")[1].split("\\/");
		String siteprefixToAssert = requiredPrefix[0]+availablityText;
		return siteprefixToAssert;
	}

	public String getModifiedEmailValue(String url) {
		String requiredPrefix[] = url.split("\\//")[1].split("\\.");
		String siteprefixToAssert = requiredPrefix[0]+"@"+requiredPrefix[1]+".com";
		return siteprefixToAssert;
	}

	public boolean isInvalidLoginPresent(){
		driver.waitForElementToBeVisible(INVALID_LOGIN,15);
		return driver.isElementVisible(INVALID_LOGIN);
	}

	public void enterEmailAddress(String emailAddress){
		driver.waitForElementPresent(ACCOUNT_EMAIL_ADDRESS_LOC);
		driver.type(ACCOUNT_EMAIL_ADDRESS_LOC, emailAddress+"\t","");
		logger.info("email address entered as: "+emailAddress);
		driver.pauseExecutionFor(2000);
	}

	public void clickSetUpAccountNextButton(){
		driver.quickWaitForElementPresent(SETUP_ACCOUNT_NEXT_BTN_LOC);
		driver.click(SETUP_ACCOUNT_NEXT_BTN_LOC,"");
		logger.info("set up account next button is clicked");
	}

	/***
	 * This method is used to verify if validation pop is coming while using existing PC during consultant enrollment
	 * 
	 * @param emailAddress
	 * @return
	 */
	public boolean validateExistingPCPopUp(String emailAddress){
		driver.type(ACCOUNT_EMAIL_ADDRESS_LOC, emailAddress,"email address");
		driver.type(ACCOUNT_PASSWORD_LOC, "","password");
		return driver.isElementVisible(EXISTING_PC_LOC);
	}

	/***
	 * This method is used to verify if validation pop is coming while using existing RC during consultant enrollment
	 * 
	 * @param emailAddress
	 * @return
	 */
	public boolean validateExistingRCPopUp(String emailAddress){
		driver.type(ACCOUNT_EMAIL_ADDRESS_LOC, emailAddress,"email address");
		driver.type(ACCOUNT_PASSWORD_LOC, "","password");
		return driver.isElementVisible(EXISTING_RC_LOC);
	}

	public void enterSpecialCharacterInWebSitePrefixField(String prefixField){
		driver.quickWaitForElementPresent(WEBSITE__PREFIX_LOC);
		driver.type(WEBSITE__PREFIX_LOC, prefixField);
		logger.info("PWS enterd as: "+prefixField);
	}

	public String getWebSitePrefixFieldText(){
		driver.quickWaitForElementPresent(WEBSITE__PREFIX_LOC);
		return driver.findElement(WEBSITE__PREFIX_LOC).getText();
	}

	public void clickCancelEnrollmentBtn(){
		driver.waitForElementPresent(CANCEL_ENROLLMENT_BTN);
		driver.click(CANCEL_ENROLLMENT_BTN,"");
		logger.info("Cancel enrollment button clicked");
		driver.waitForPageLoad();
	}

	public void clickSendEmailToResetMyPassword(){
		driver.waitForElementPresent(SEND_EMAIL_TO_RESET_MY_PASSWORD_BTN);
		driver.click(SEND_EMAIL_TO_RESET_MY_PASSWORD_BTN,"");
		logger.info("Send email to reset my password button clicked");
		driver.waitForPageLoad();
	}

	public boolean isExistingConsultantPopupPresent(){
		driver.waitForElementPresent(EXISTING_CONSULTANT_LOC);
		return driver.isElementPresent(EXISTING_CONSULTANT_LOC);
	}

	public boolean isEmailVerificationTextPresent(){
		driver.waitForElementPresent(EMAIL_VERIFICATION_TEXT);
		return driver.isElementPresent(EMAIL_VERIFICATION_TEXT);
	}

	public boolean verifyLinkPresentUnderMyAccount(String linkName) {
		return driver.isElementPresent(By.xpath(String.format(linkUnderMyAccount, linkName)));
	}

	public int clickViewDetailsForOrderAndReturnOrderNumber() {
		driver.waitForElementPresent(TOTAL_ROWS_ON_ORDER_HISTORY_PAGE);
		int totalRowsSize=driver.findElements(TOTAL_ROWS_ON_ORDER_HISTORY_PAGE).size();
		logger.info("Total rows on order history page: "+totalRowsSize);
		if(totalRowsSize<1){
			return 0;
		}else{
			int randomOrderFromSearchResult = CommonUtils.getRandomNum(1, totalRowsSize);
			logger.info("Random Number created is: "+randomOrderFromSearchResult);
			String orderNumber = driver.findElement(By.xpath(String.format(orderNumberOnOrderHistoryPage, randomOrderFromSearchResult))).getText();
			driver.click(By.xpath(String.format(viewDetailsOnOrderHistoryPage, randomOrderFromSearchResult)),"");
			logger.info("View Order details link is clicked for order :"+orderNumber);
			return Integer.parseInt(orderNumber);
		}
	}

	public boolean isOrderDetailsPopupPresent(){
		driver.waitForElementPresent(ORDER_DETAILS_POPUP);
		return driver.isElementPresent(ORDER_DETAILS_POPUP);
	}

	public void  clickCloseOfOrderDetailsPopup(){
		driver.waitForElementPresent(CLOSE_OF_ORDER_DETAILS_POPUP);
		driver.click(CLOSE_OF_ORDER_DETAILS_POPUP,"");
		logger.info("Order details popup closed.");
	}

	public String clickAndReturnPWSFromFindConsultantPage(){
		driver.quickWaitForElementPresent(PWS_TXT_ON_FIND_CONSULTANT_PAGE);
		String fetchPWS=driver.findElement(PWS_TXT_ON_FIND_CONSULTANT_PAGE).getText();
		logger.info("Fetched PWS from find a consultant page is"+fetchPWS);
		driver.click(PWS_TXT_ON_FIND_CONSULTANT_PAGE,"");
		logger.info("PWS "+fetchPWS+" is clicked.");
		driver.waitForPageLoad();
		return fetchPWS;
	}

	public void clickConnectWithAConsultant(){
		driver.waitForElementPresent(CONNECT_WITH_A_CONSULTANT);
		driver.click(CONNECT_WITH_A_CONSULTANT,"");
		logger.info("Connect with a consultant");
		driver.waitForPageLoad();
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		String parentWindow=it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
		driver.pauseExecutionFor(3000);
	}

	public void clickClickhereLinkToLearnDirectSelling(){
		driver.quickWaitForElementPresent(CLICK_HERE_TO_LEARN_MORE_ABOUT_DIRECT_SELLING);
		driver.click(CLICK_HERE_TO_LEARN_MORE_ABOUT_DIRECT_SELLING,"");
		logger.info("Click here link clicked");
		logger.info("Redirect to direct selling page");
		driver.waitForPageLoad();
		driver.pauseExecutionFor(3000);
	}

	public void clickCompanyPressRoomLink(){
		driver.quickWaitForElementPresent(PRESS_ROOM);
		driver.click(PRESS_ROOM,"");
		logger.info("Company Press Room Link clicked");
		driver.waitForPageLoad();
	}

	public void hoverOnBeAConsultantAndClickLinkOnEnrollMe(){
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(By.xpath("//span[text()= 'BECOME A CONSULTANT']"));
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[text()= 'BECOME A CONSULTANT']"))).build().perform();
		logger.info("hover on Products link now as shop skincare");
		driver.click(By.xpath("//span[text()= 'Enroll Now']"),"");
		// logger.info("Clicked "+link+" link is clicked after hovering shop skincare.");
		driver.waitForPageLoad();
	}	

	public void mouseHoverBeAConsultant(){
		driver.pauseExecutionFor(2000);
		Actions actions =  new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(BE_A_CONSULTANT_LOC)).build().perform();
		logger.info("hover performed on be a consultant link.");
	}	

	public String getPrefixMessageForBiz() {
		return  driver.getText(By.id("Abailable0"),"msg from .biz prefix");
	}

	public String getPrefixMessageForCom() {
		return  driver.getText(By.id("Abailable1"),"msg from .com prefix");
	}

	public String getPrefixMessageForEmail() {
		return  driver.getText(By.id("Abailable2"),"msg from .email prefix");
	}

	public boolean isValidationMessagePresentForPrefixField() {
		driver.waitForElementToBeVisible(By.xpath("//div[@id='completeAccountForm']//label[contains(text(),'Please enter a name for you PWS site')]"),10);
		return driver.isElementVisible(By.xpath("//div[@id='completeAccountForm']//label[contains(text(),'Please enter a name for you PWS site')]"));
	}

	public boolean isSignInButtonPresent(){
		return driver.isElementPresent(By.xpath("//a[contains(@id,'lnkLogin')]"));
	}

	/***
	 * This nethod is used to get the billing Address Name
	 * @return
	 */
	public String getBillingAddressName(){
		return  driver.getText(By.xpath("//span[contains(@id,'lblBillingAddrName')]"),"Billing Profilename");
	}

	public boolean isPCEnrollmentCompletedSuccessfully(){
		driver.waitForElementToBeVisible(By.xpath("//h2[contains(text(),'Welcome to PC Perks')]"),20);
		return  driver.isElementVisible(By.xpath("//h2[contains(text(),'Welcome to PC Perks')]"));
	}

	public boolean verifyErrorMessageForTermsAndConditionsForConsultant(){
		driver.waitForElementPresent(By.xpath("//label[@class='fieldError']"));
		return driver.isElementPresent(By.xpath("//label[@class='fieldError']"));
	}

	public boolean verifyErrorMessageForTermsAndConditionsForPCAndRC(){
		driver.waitForElementPresent(ERROR_MESSAGE_FOR_TERMS_AND_CANDITIONS_FOR_PC_RC);
		return driver.isElementPresent(ERROR_MESSAGE_FOR_TERMS_AND_CANDITIONS_FOR_PC_RC);
	}

	public String getBillingAddress(){
		driver.findElement(By.xpath("//span[contains(@id,'_lblBillingAddrSt')]"));
		String name =  driver.findElement(By.xpath("//span[contains(@id,'_lblBillingAddrSt')]")).getText();
		logger.info("Billing Address line name is "+name);
		return name;
	}

	public String getExistingBillingProfileName(){
		driver.findElement(By.xpath("//div[contains(@id,'_uxBillingEditor_uxUpdatePanel')]//div[@class='form-group']/span[1]"));
		String name =  driver.findElement(By.xpath("//div[contains(@id,'_uxBillingEditor_uxUpdatePanel')]//div[@class='form-group']/span[1]")).getText();
		logger.info("Existing Billing profile name is "+name);
		return name;
	}

	public StoreFrontHeirloomHomePage selectDeleteForProfileAndAcceptConfirmationAlert(String profile){
		driver.click(By.xpath(String.format(deleteButtonForProfileLoc,profile)),"");
		logger.info("Delete Link Clicked for "+profile);
		driver.switchAndAcceptAlert();
		driver.navigate().refresh();
		return this;
	}

	public boolean isProfilePresentInSavedProfiles(String profile){
		return driver.isElementPresent(By.xpath(String.format(specificprofileInSavedProfilesLoc, profile)));
	}

	public boolean isLoginInvalid(){
		return driver.isElementPresent(By.xpath("//p[@id='loginError']"));
	}

	/***
	 * This method is used to click on Edit My Photo Link
	 */
	public StoreFrontHeirloomHomePage clickEditMyPhotoLink(){
		driver.click(EDIT_MY_PHOTO_LINK,"Edit my photo link");
		driver.waitForPageLoad();
		return this;
	}

	/***
	 * This method is used to verify if upload photo button is visile or not	
	 * @return
	 */
	public boolean isUploadANewPhotoButtonPresent(){
		return driver.isElementVisible(UPLAOD_A_NEW_PHOTO_BTN);
	}

	public void acceptPoliciesAndProcedures(){
		boolean isModalPresent = driver.isElementPresent(By.xpath("//div[@class='ModalNotice']//a[@id='uxContinue']"),2);
		if(isModalPresent){
			driver.click(By.xpath("//div[@class='ModalNotice']//a[@id='uxContinue']"),"Continue Button on Policies and Procesures Modal");
		}

	}

	public boolean isRecoverPWDEmailAddressPresent() {
		return driver.isElementPresent(RECOVER_PWD_EMAIL_ADDRESS_LOC);
	}

	public boolean isPageHeaderVisible(String pageName) {
		boolean flag;
		try {
			flag = driver.isElementVisible(By.xpath(String.format(PAGE_HEADER_LOC, pageName)));
		}catch (Exception e) {
			flag=false;
			logger.info("catch block flag value is : "+flag);
		}
		return flag;
	}

	public void clickCareersLink(){
		driver.pauseExecutionFor(3000);
		driver.click(CAREERS_LINK_LOC,"Careers Link");
		driver.waitForPageLoad();
	}

	/***
	 * This method is used to verify if check my pulse link is displayed or not
	 * @return
	 */
	public boolean isCheckMyPulseLinkDisplayed(){
		driver.waitForElementToBeVisible(CHECK_MY_PULSE_LOC, 20);
		return driver.isElementVisible(CHECK_MY_PULSE_LOC);
	}

	public boolean isLinkTextPresent(String linkName){
		driver.quickWaitForElementPresent(By.xpath(String.format(exactCategoryNameTextLoc, linkName)));
		return driver.isElementPresent(By.xpath(String.format(exactCategoryNameTextLoc, linkName)));
	}


	public boolean isThankYouTextPresentAfterOrderPlaced(){
		driver.isElementVisible(ORDER_CONFIRMATION_THANK_YOU_TXT, 20);
		return driver.isElementVisible(ORDER_CONFIRMATION_THANK_YOU_TXT);
	}

	public void clickCompleteOrderBtn(){
		driver.click(COMPLETE_ORDER_BTN,"complete order button");
		driver.pauseExecutionFor(1000);
	}

	public void clickContinueWithoutConsultantLink(){
		driver.quickWaitForElementPresent(CONTINUE_WITHOUT_CONSULTANT_LINK);
		driver.click(CONTINUE_WITHOUT_CONSULTANT_LINK,"Continue without sponser link");
	}

	public void clickOnRodanAndFieldsLogo(){
		driver.click(RODAN_AND_FIELDS_IMG_LOC,"Rodan and Fields logo ");
		driver.waitForPageLoad();
	}

	public boolean verifyShoppingBagDoesnotShowNumber() {
		return driver.isElementPresent(NUMBER_OF_ITEMS_IN_SHOPPING_BAG_LOC);

	}

	public boolean verifyIfProductIsPresent() {
		return driver.isElementPresent(By.xpath(".//*[@id='uxProductQty']"));
	}

	public void clickMyShoppingBagLink(){
		driver.waitForElementPresent(MY_SHOPPING_BAG_LINK);
		driver.clickByJS(RFWebsiteDriver.driver,MY_SHOPPING_BAG_LINK,"My shopping bag");
		logger.info("Clicked on My shopping bag link.");
		driver.waitForPageLoad();
	}

	public boolean verifyShoppingBagAppears() {
		driver.waitForPageLoad();
		return driver.isElementPresent(By.xpath("//a[@class='BagLink']/img"));

	}

	public void loginAsRCUserCorpSite(String username,String password){
		driver.waitForElementPresent(USERNAME1_TXTFLD_LOC);
		driver.type(USERNAME1_TXTFLD_LOC, username);
		//driver.click(PASSWORD_TXTFLD_ONFOCUS_LOC);
		driver.type(PASSWORD1_TXTFLD_LOC,password);  
		logger.info("login username is "+username);
		logger.info("login password is "+password);
		driver.click(LOGIN1_BTN_LOC,"");
		logger.info("login button clicked");
		driver.waitForPageLoad();
		acceptPoliciesAndProcedures();
	}

	public String getNumberOfItemsFromShoppingBag() {
		driver.isElementVisible(NUMBER_OF_ITEMS_IN_SHOPPING_BAG_LOC);
		driver.pauseExecutionFor(2000);
		return driver.getText(NUMBER_OF_ITEMS_IN_SHOPPING_BAG_LOC);

	}

	public void addQuantityOfProduct(String qty) throws InterruptedException{
		//driver.pauseExecutionFor(2000);
		try{
			driver.quickWaitForElementPresent(By.id("quantity0"));
			driver.clear(By.id("quantity0"));
			driver.type(By.id("quantity0"),qty);
			logger.info("quantity added is "+qty);
			driver.click(By.xpath("//div[@id='left-shopping']/div//a[@class='updateLink']"),"");
			logger.info("Update button clicked after adding quantity");
		}catch(NoSuchElementException e){			
			driver.waitForElementPresent(UPDATE_CART_BTN_LOC);
			driver.clear(UPDATE_CART_BTN_LOC);
			driver.type(UPDATE_CART_BTN_LOC,qty);
			logger.info("quantity added is "+qty);
			driver.click(By.xpath(".//*[@id='lnkUpdate']"),"");
			logger.info("Update button clicked after adding quantity");
		}
		//driver.pauseExecutionFor(2000);
		driver.waitForPageLoad();
	}

	public void clickContinueShoppingButton() {
		driver.click(CONTINUE_SHOPPING_BTN_LOC,"");
	}

	public void mouseHoverShopSkinCareAndClickLinkOnPWS(String link){
		actions =  new Actions(RFWebsiteDriver.driver);
		driver.waitForElementPresent(SHOP_SKINCARE_ON_PWS_LOC,10);
		actions.moveToElement(driver.findElement(SHOP_SKINCARE_ON_PWS_LOC)).build().perform();
		logger.info("hover on Products link now as shop skincare");
		driver.click(By.xpath(String.format(linkUnderShopSkinCareOrBeAConsultant, link)),link);
		driver.waitForPageLoad();
	}

	public void clickAddToCartButtonAfterLogin() {
		try{
			driver.waitForElementPresent((By.xpath("//*[text()='Add to Bag']")));
			driver.click(By.xpath("//*[text()='Add to Bag']"),"");
			System.out.println("Add to Bag button on ProdDetailPage is clicked");
		}
		catch(NoSuchElementException e){
			try{
				driver.findElement(By.xpath("//a[text()='Add to Cart']"));
				driver.click(By.xpath("//a[text()='Add to Cart']"),"");
				System.out.println("Add to cart button on ProdDetailPage is clicked");

			} catch (NoSuchElementException e1) {
				driver.findElement(ADD_TO_CART_BTN_LOC);
				driver.quickWaitForElementPresent(ADD_TO_CART_BTN_LOC);
				driver.click(ADD_TO_CART_BTN_LOC,"");
				logger.info("Add to cart button is clicked");

			}
		}
	}	

	public void refreshThePage(){
		driver.navigate().refresh();
		driver.waitForPageLoad();
		driver.pauseExecutionFor(2000);
	}

	public String getOrderNumberFromOrderConfirmationPage() {
		String orderNumber=driver.getText(ORDER_NUMBER_ON_ORDER_CONFIRMATION_PAGE_LOC);
		logger.info("Order Number is: "+orderNumber);
		return orderNumber;
	}

	public String getSubtotalFromOrderConfirmationPage() { 
		String subTotal=driver.getText(SUBTOTAL_FROM_ORDER_CONFIRMATION_LOC).split("\\$")[1];
		logger.info("Subtotal from UI is: "+subTotal);
		return subTotal;
	}

	public String getShippingTotalFromOrderConfirmationPage() {
		String shipping=driver.getText(SHIPPING_TOTAL_ON_ORDER_CONFIRMATION_PAGE_LOC).split("\\$")[1];
		logger.info("shipping from order confirmation page is: "+shipping);
		return shipping;
	}

	public String getTaxFromOrderConfirmationPage() {
		String tax=driver.getText(TAX_ON_ORDER_CONFIRMATION_PAGE_LOC).split("\\$")[1];
		logger.info("tax from order confirmation page is: "+tax);
		return tax;
	}

	public String getGrandTotalFromOrderConfirmationPage() {
		String grandTotal=driver.getText(GRAND_TOTAL_ON_ORDER_CONFIRMATION_PAGE_LOC).split("\\$")[1];
		logger.info("grandTotal from order confirmation page is: "+grandTotal);
		return grandTotal;
	}	

	public void clickViewDetailsLinkInOrderHistoryForPC(String orderNumber) {
		driver.click(By.xpath(String.format(viewDetailsOnOrderHistoryPage, orderNumber)),"");
		logger.info("clicked on View Order Details link on "+orderNumber+" Order");
		driver.pauseExecutionFor(2000);
	}

	public String getSubtotalFromOrderHistoryPageForPC() { 
		String subTotal=driver.getText(SUBTOTAL_FROM_ORDER_HISTORY_LOC).split("\\$")[1];
		logger.info("Subtotal from UI is: "+subTotal);
		return subTotal;
	}

	public String getShippingTotalFromOrderHistoryPageForPC() {
		String shipping=driver.getText(SHIPPING_FROM_ORDER_HISTORY_LOC);
		logger.info("shipping from UI is: "+shipping);
		return shipping;
	}

	public String getTaxFromOrderHistoryPageForPC() {
		String tax=driver.getText(TAX_FROM_ORDER_HISTORY_LOC).split("\\$")[1];
		logger.info("tax from UI is: "+tax);
		return tax;
	}

	public String getGrandTotalFromOrderHistoryPageForPC() {
		String grandTotal=driver.getText(GRAND_TOTAL_FROM_ORDER_HISTORY_LOC).split("\\$")[1];
		logger.info("grandTotal from UI is: "+grandTotal);
		return grandTotal;
	}	

	public boolean isQuantityOnConfirmOrderpagePresent() {

		return driver.isElementPresent(By.xpath(".//*[@class='divTableBody']/div/div[1]"));
	}
	public boolean isSKUOnConfirmOrderPagePresent() {

		return driver.isElementPresent(By.xpath(".//*[@class='divTableBody']/div/div[2]"));
	}
	public boolean isItemOnConfirmOrderpagePresent() {

		return driver.isElementPresent(By.xpath(".//*[@class='divTableBody']//div[contains(@class,'cartItem')]"));
	}
	public boolean isPriceOnConfirmOrderpagePresent() {

		return driver.isElementPresent(By.xpath(".//*[@class='divTableBody']//div[5]"));
	}
	public boolean isTotalOnConfirmOrderpagePresent() {

		return driver.isElementPresent(By.xpath(".//*[@class='divTableBody']//div[6]"));
	}
	public boolean isTaxAmountOnConfirmOrderpagePresent(){

		return driver.isElementPresent(By.xpath("//*[contains(@id,'uxTax')]"));
	}

	public boolean isShippingAmountOnConfirmOrderpagePresent(){

		return driver.isElementPresent(By.xpath("//div[@class='CartTotals']//span[contains(@id,'uxShipping')]"));
	}

	public boolean isSubTotalOnConfirmOrderpagePresent(){

		return driver.isElementPresent(By.xpath("//span[contains(@id,'uxSubTotal')]"));
	}

	public boolean isGrandTotalOnConfirmOrderpagePresent(){

		return driver.isElementPresent(ORDER_GRAND_TOTAL_BOTTOM_LOC);
	}

	public void chooseShippingMethod(String methodName) {
		driver.click(By.xpath(String.format(CHOOSE_SHIPPING_METHOD_lOC, methodName)),"shipping method = "+methodName);
	}

	public String checkProductNameThroughCart() {
		//String productname=driver.findElement(By.xpath(".//*[@id='uxProductQty']")).getText();
		System.out.println("product name=:"+driver.findElement(By.xpath(".//*[@class='divTableBody']//div[contains(@class,'cartItem')]")).getText());
		return driver.findElement(By.xpath(".//*[@class='divTableBody']//div[contains(@class,'cartItem')]")).getText();

	}

	public String checkQtyThroughCart() {
		System.out.println("product name=:"+driver.findElement(By.xpath(".//*[@id='uxProductQty']")).getText());
		return driver.findElement(By.xpath(".//*[@id='uxProductQty']")).getText();

	}
	public String checkTotalPriceThroughCart() {
		System.out.println("product name=:"+driver.findElement(By.xpath(".//*[@class='divTableRow']//div[contains(@data-name,'adjustedPrice')]")).getText());
		return driver.findElement(By.xpath(".//*[@class='divTableRow']//div[contains(@data-name,'adjustedPrice')]")).getText();
	}
	public String checkProductPriceThroughCart() {
		System.out.println("product name=:"+driver.findElement(By.xpath(".//*[@class='divTableRow']//div[contains(@data-name,'itemPrice')]")).getText());
		return driver.findElement(By.xpath(".//*[@class='divTableRow']//div[contains(@data-name,'itemPrice')]")).getText();
	}

	public void selectVariantOfProduct(String productName, String shade){
		Select selectVarient=new Select(driver.findElement(By.xpath(String.format(shadeDDLoc, productName))));
		logger.info("Variant Product is available. Selecting value from dropdown.");
		selectVarient.selectByVisibleText(shade);
	}

	public void addProductToAdHocCart(String productName){
		logger.info("Waiting for Variant Product");
		driver.waitForElementPresent(By.xpath(String.format(addToBagButtonForProductNameLoc, productName)));
		driver.click(By.xpath(String.format(addToBagButtonForProductNameLoc, productName)),"");
		logger.info("Variant Product"+productName+ "added to Ad-hoc cart");
		driver.waitForPageLoad();
	}

	public boolean isProductAvailableInCart(String productName){
		if(driver.findElement(By.xpath(String.format(productNameInCartLoc, productName))).isDisplayed()){
			return true;
		}else{
			return false;
		}
	}

	public boolean isShippingPageHeaderVisible() {
		return driver.isElementVisible(SHIPPING_PAGE_HEADER_LOC);
	}

	public boolean isCaliforniaSupplyChainsActPageHeaderVisible() {
		return driver.isElementVisible(CALIFORNIA_SUPPLY_CHAINS_ACT_PAGE_HEADER_LOC);
	}

	public void clickCompensationPlanHereLink() {
		driver.click(COMPENSATION_PLAN_LINK,"");
		logger.info("Compansation plan link clicked");
		driver.waitForPageLoad();
	}

	public void clickSolutionToolHomePage() {
		driver.clickByJS(RFWebsiteDriver.driver,SOLUTION_TOOL_HOME_PAGE_LINK_LOC,"Solution tool get started link");
		driver.waitForPageLoad();
	}

	public boolean verifyRFConventionSectionUnderEvents() {
		driver.quickWaitForElementPresent(RF_CONVENTION_SECTION_LOC);
		return driver.IsElementVisible(driver.findElement(RF_CONVENTION_SECTION_LOC));
	}

	public boolean verifyBussinessRedefinedLearningSectionUnderEvents() {
		driver.quickWaitForElementPresent(BUSINESS_REDEFINED_LEARNING_SECTION_LOC);
		return driver.IsElementVisible(driver.findElement(BUSINESS_REDEFINED_LEARNING_SECTION_LOC));
	}

	public boolean verifyConsultantLedEventsSectionUnderEvents() {
		driver.quickWaitForElementPresent(CONSULTANT_LED_EVENTS_SECTION_LOC);
		return driver.IsElementVisible(driver.findElement(CONSULTANT_LED_EVENTS_SECTION_LOC));
	}

	public void clickBusinessPresentationEventsCalendarLink() {
		driver.clickByJS(RFWebsiteDriver.driver,BUSINESS_PRESENTATION_EVENT_CALENDAR_LINK_LOC,"Compansation plan link");
		driver.waitForPageLoad();
	}

	public boolean eventsCalendarDetails() {
		driver.quickWaitForElementPresent(EVENT_CALENDAR_LINK_LOC);
		return driver.isElementPresent(EVENT_CALENDAR_LINK_LOC);
	}

	public String getErrorMessageForInvalidCreditCard(){
		driver.waitForElementPresent(ERROR_MSG_FOR_INVALID_CC);
		return driver.getText(ERROR_MSG_FOR_INVALID_CC, "Error message for Invalid Credit Card");
	}

	public boolean isUserRedirectedToFeaturedPage(){
		return driver.isElementVisible(FEATURED_PAGE_HEADER_LOC);
	}

	public void clickEnrollNowUnderStartYourJourney() {
		driver.quickWaitForElementPresent(ENROLL_NOW_START_YOUR_JOURNEY_LOC);
		driver.click(ENROLL_NOW_START_YOUR_JOURNEY_LOC,"");
		logger.info("enroll now link under start your journey clicked");
		driver.waitForPageLoad();
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		String parentWindow=it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
		driver.pauseExecutionFor(3000);
	}

	public boolean verifyMeetOurCommunityPage() {
		driver.quickWaitForElementPresent(MEET_OUR_COMMUNITY_PAGE_LOC);
		return driver.isElementPresent(MEET_OUR_COMMUNITY_PAGE_LOC);
	}

	public String clickOnReadTheirInspiringStoriesLink() {
		driver.quickWaitForElementPresent(READ_THEIR_INSPIRING_STORIES_LINK_LOC);
		driver.click(READ_THEIR_INSPIRING_STORIES_LINK_LOC,"Read their inspiring stories");
		return switchToNonParentWindow();
	}

	public boolean verifyReadTheirInspiringStoriespage() {
		driver.quickWaitForElementPresent(By.xpath("//title[contains(text(),'Inspirational Consultant Stories')]"));
		return driver.isElementPresent(By.xpath("//title[contains(text(),'Inspirational Consultant Stories')]"));
	}

	public String clickEnrollNowBtnOnMeetOurCommunityPage() {
		driver.click(ENROLL_NOW_MEET_OUR_COMMUNITY_PAGE_LOC,"Enroll now button on meet our community page");
		return switchToNonParentWindow();

	}

	public String clickLinksOnMeetourCommunityPage(String linkName) {
		driver.quickWaitForElementPresent(By.xpath(String.format(LINKS_ON_MEET_OUR_COMMUNITY_PAGE_LOC, linkName)));
		driver.click(By.xpath(String.format(LINKS_ON_MEET_OUR_COMMUNITY_PAGE_LOC, linkName)),"");
		return switchToNonParentWindow();
	}

	public void closeChildSwitchToParentWindow() {
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		String parentWindow=it.next();
		String childWindow=it.next();
		driver.switchTo().window(childWindow);
		closeChildAndSwitchToParentWindow(parentWindow);
	}

	public void selectShippingMethod(String shippingMethodName) {
		driver.quickWaitForElementPresent(By.xpath(String.format(shippingMethodRadioBtnLoc, shippingMethodName)));
		driver.click(By.xpath(String.format(shippingMethodRadioBtnLoc, shippingMethodName)), "shipping method selected as"+shippingMethodName);
		driver.waitForPageLoad();
	}


	public int getPositionOfProductForLessPrice(double price) {
		List<WebElement> lis = driver.findElements(YOUR_PRICE_LOC);
		double yourPrice = 0.00;
		int i = 1;
		for (WebElement wb : lis) {
			yourPrice = Double.parseDouble(wb.getText().split("\\$")[1]);
			if (yourPrice<price) {
				break;
			}
			i++;
		}
		logger.info("Value of i is"+i);
		return i;
	}

	public void addProductToAdHocCartThroughProductNumber(String productNumber){
		driver.waitForElementPresent(By.xpath(String.format(addToBagButtonForProductNumberLoc, productNumber)));
		driver.click(By.xpath(String.format(addToBagButtonForProductNumberLoc, productNumber)),"Product"+productNumber+ "added to Ad-hoc cart");
		driver.waitForPageLoad();
	}

	public int getPositionOfProductForMorePrice(double price) {
		List<WebElement> lis = driver.findElements(YOUR_PRICE_LOC);
		double yourPrice = 0.00;
		int i = 1;
		for (WebElement wb : lis) {
			System.out.println("***"+wb.getText());
			yourPrice = Double.parseDouble(wb.getText().split("\\$")[1]);
			System.out.println("your price is "+yourPrice);
			if (yourPrice>price) {
				break;
			}
			i++;
		}
		logger.info("Value of i is"+i);
		return i;
	}

	public int getPositionOfProductForLessSVPrice(double price) {
		List<WebElement> lis = driver.findElements(SV_PRICE_LOC);
		double yourPrice = 0.00;
		int i = 1;
		for (WebElement wb : lis) {
			System.out.println("***"+wb.getText());
			yourPrice = Double.parseDouble(wb.getText().split("\\SV")[1]);
			System.out.println("your price is "+yourPrice);
			if (yourPrice<price) {
				break;
			}
			i++;
		}
		logger.info("Value of i is"+i);
		return i;
	}


	public int getPositionOfProductForMoreSVPrice(double price) {
		List<WebElement> lis = driver.findElements(SV_PRICE_LOC);
		double yourPrice = 0.00;
		int i = 1;
		for (WebElement wb : lis) {
			System.out.println("***"+wb.getText());
			yourPrice = Double.parseDouble(wb.getText().split("\\SV")[1]);
			System.out.println("your price is "+yourPrice);
			if (yourPrice>price) {
				break;
			}
			i++;
		}
		logger.info("Value of i is"+i);
		return i;
	}

	public void addDonationProductToBag() {
		driver.click(DONATE_BUTTON_LOC,"");
		logger.info("clicked on donate button");
		driver.click(ADD_TO_BAG_BUTTON_FOR_DONATION_PRODUCT_LOC,"");
		logger.info("clicked on Add To Bag button for donation product");
	}

	public void clickCountryToggle() {
		driver.click(COUNTRY_TOGGLE_LOC,"");
		logger.info("clicked on country toggle on Homepage");
	}

	public String getShippingNameFromOrderConfirmationPage() {
		return driver.getText(SHIPPING_NAME_ON_ORDER_CONFIRMATION_PAGE_LOC);
	}

	public String getAddressFromOrderConfirmationPage() {
		return driver.getText(ADDRESS_LINE1_ON_ORDER_CONFIRMATION_PAGE_LOC);
	}

	public boolean validateExistingRCPopUpCom(){
		driver.quickWaitForElementPresent(EXISTING_RC_ACCOUNT_LOC);
		return driver.findElement(EXISTING_RC_ACCOUNT_LOC).isDisplayed();
	}

	public boolean isYouMayAlsoFindTheseProductsSectionVisible() {
		boolean isYouMayAlsoFindTheseProductsSectionVisible=false;

		for(int i =5;i<=17;i++ ){
			logger.info("loop " +i+ " ");
			driver.click(By.xpath("//div[@id='FullPageItemList']/div["+i+"]//img[@class='ProductImage']"),"");
			driver.waitForPageLoad();
			logger.info("clicked product " +i+ " ");
			try{
				driver.isElementVisible(YOU_MAY_ALSO_FIND_THESE_PRODUCTS_SECTION_LOC);
				driver.isElementVisible(By.xpath(".//*[@id='RelatedItems']//button[contains(text(),'Previous')]"));
				isYouMayAlsoFindTheseProductsSectionVisible= true;
				break;
			}catch(Exception e){
				driver.navigate().back();
			}
			logger.info("you may also find these products section not found");
		}
		return isYouMayAlsoFindTheseProductsSectionVisible= true;

	}


	public boolean isYouMayAlsoFindTheseProductsSectionVisibleBiz() {
		return driver.isElementVisible(YOU_MAY_ALSO_FIND_THESE_PRODUCTS_SECTION_LOC);
	}

	public String getShippingPriceFromCRPReviewAndConfirmPage() {
		return driver.getText(SHIIPING_PRICE_FROM_CRP_REVIEW_AND_CONFIRM_PAGE_LOC).split("\\$")[1];
	}

	public void clickUseAsEnteredForGuamAndPuertoRicoAddress() {
		if(driver.isElementVisible(USE_AS_ENTERED_ADDRESS_LOC)) {
			driver.click(USE_AS_ENTERED_ADDRESS_LOC,"");
			logger.info("clicked on Use as Entered button for verify your address");
		}
	}	

	public String selectProductFromCarousel() {
		String prodname1=driver.getText(By.xpath(".//*[@class='slick-track']//div[1]//span/a")).toLowerCase();
		logger.info(prodname1);	
		driver.clickByJS(RFWebsiteDriver.driver,By.xpath(".//*[@class='slick-track']/div[1]//img"),"Carousel product");
		driver.pauseExecutionFor(2000);
		return prodname1;
	}


	public boolean isUserRedirectedToCarouselProductPDPcorp(String prodname) {
		driver.waitForPageLoad();
		String pdpprod=driver.getText(By.xpath(".//h2[@class='ProductName']")).toLowerCase();
		logger.info(pdpprod);
		return driver.getText(By.xpath(".//h2[@class='ProductName']")).toLowerCase().contains(prodname);

	}

	public boolean isUserRedirectedToCarouselProductPDP(String prodname1) {
		driver.waitForPageLoad();
		String pdpprod1=driver.getText(By.xpath(".//h2[@class='ProductName']")).toLowerCase();
		logger.info(pdpprod1);
		return driver.getText(By.xpath(".//h2[@class='ProductName']")).toLowerCase().contains(prodname1);
	}

	public boolean isCarouselProductOpenedInTheSameTab() {
		int wind=driver.getWindowHandles().size();
		logger.info(wind);
		if(wind==1) {
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isSponsorInfoPresentOnTopOfPage() {
		return driver.isElementVisible(SPONSOR_INFO_TOP_OF_PAGE_LOC);
	}

	public boolean isCarouselIsContinous() {
		boolean isCarouselIsContinous=false;
		for(int i=1;i<=5;i++)
		{
			logger.info("loop " +i+ " ");
			driver.isElementVisible(YOU_MAY_ALSO_FIND_THESE_PRODUCTS_SECTION_LOC);
			driver.waitForPageLoad();
			logger.info("clicked product " +i+ " ");
			try {
				isCarouselIsContinous=driver.isElementVisible(By.xpath(".//*[@id='RelatedItems']//button[contains(text(),'Previous')]")) && driver.isElementVisible(By.xpath(".//*[@id='RelatedItems']//button[contains(text(),'Next')]"));

				break;
			}
			catch(Exception e){
				driver.navigate().back();
				driver.click(By.xpath("//div[@id='FullPageItemList']/div["+ i+1 +"]//img[@class='ProductImage']"),"");
			}

			logger.info("Carousel Is not Continous");
		}
		return isCarouselIsContinous;
	}

	public boolean isCarouselIsContinousBiz() {
		return driver.isElementVisible(By.xpath(".//*[@id='RelatedItems']//button[contains(text(),'Previous')]")) && driver.isElementVisible(By.xpath(".//*[@id='RelatedItems']//button[contains(text(),'Next')]"));
	}

	public String verifyTextCalendarPageisDisplyed() {
		driver.pauseExecutionFor(5000);
		String parentWin = null;
		parentWin=switchToChildWindow();
		String text = driver.findElement(By.xpath("//h2[contains(text(),'Event Calendar')]")).getText();
		closeChildAndSwitchToParentWindow(parentWin);
		return text;
	}

	/***
	 * This method is used to enter shipping details
	 * @param addressName
	 * @param firstName
	 * @param lastName
	 * @param addressLine1
	 * @param postalCode
	 * @param phnNumber
	 */
	public void enterShippingProfileDetailsForPWS(String addressName, String firstName,String lastName,String addressLine1,String postalCode,String phnNumber){
		driver.type(ADDRESS_NAME_FOR_SHIPPING_PROFILE, addressName,"Address name");
		driver.type(ATTENTION_FIRST_NAME, firstName,"Attention first name");
		driver.type(ATTENTION_LAST_NAME, lastName,"Attention last name");
		driver.type(ADDRESS_LINE_1, addressLine1,"Address line 1");
		driver.type(ZIP_CODE, postalCode+"\t","Postal code");
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
		driver.pauseExecutionFor(3000);//required
		driver.click(By.xpath("//input[contains(@id,'uxCityDropDown_Input')]"),"city dropdown");
		driver.pauseExecutionFor(1000);//required
		driver.click(FIRST_VALUE_OF_CITY_DD,"First city");
		driver.click(COUNTRY_DD,"country dropdown");
		driver.pauseExecutionFor(1000);//required
		driver.click(FIRST_VALUE_OF_COUNTRY_DD,"First value of Country");
		driver.type(PHONE_NUMBER_SHIPPING_PROFILE_PAGE,phnNumber,"Phone number");
	}

	public void clickRFEventslinkUnderLearningAndLeadershipSection() {
		driver.quickWaitForElementPresent(RF_EVENTS_LEARNING_LEADRESHIP_SECTION_LOC);
		driver.click(RF_EVENTS_LEARNING_LEADRESHIP_SECTION_LOC,"");
		driver.waitForPageLoad();
		Set<String> set=driver.getWindowHandles();
		int windcount=driver.getWindowHandles().size();
		logger.info("no. of windows:"+windcount);
		if(windcount>1) {
			Iterator<String> it=set.iterator();
			String parentWindow=it.next();
			String childWindow=it.next();
			driver.switchTo().window(childWindow);
			driver.pauseExecutionFor(3000);
		}
	}

	/***
	 * This method is used to enter the Billing information
	 * @param billingName
	 * @param firstName
	 * @param lastName
	 * @param cardName
	 * @param cardNumer
	 * @param month
	 * @param year
	 * @param addressLine1
	 * @param postalCode
	 * @param phnNumber
	 * @param CVV
	 */
	public void enterBillingInfoForPWS(String billingName, String firstName,String lastName,String cardName,String cardNumer,String month,String year,String addressLine1,String postalCode,String phnNumber, String CVV){
		driver.type(BILLING_NAME_FOR_BILLING_PROFILE, billingName,"Billing profile name");
		driver.type(ATTENTION_FIRST_NAME, firstName,"Attention first name");
		driver.type(ATTENTION_LAST_NAME, lastName,"Attention last name");
		driver.type(NAME_ON_CARD, cardName,"Card Name");
		driver.type(CREDIT_CARD_NUMBER_INPUT_FIELD, cardNumer,"Card number ");
		driver.type(By.xpath(".//*[contains(@id,'uxCreditCardCvv')]"), CVV,"cvv");
		driver.click(EXPIRATION_DATE_MONTH_DD,"exp month DD");
		driver.click(By.xpath(String.format(expiryMonthLoc, month)),"exp month");
		driver.click(EXPIRATION_DATE_YEAR_DD,"exp year DD");
		driver.click(By.xpath(String.format(expiryYearLoc, year)),"exp year");
		driver.type(ADDRESS_LINE_1, addressLine1,"Billing street address");
		driver.type(ZIP_CODE, postalCode+"\t","postal code");
		driver.pauseExecutionFor(5000);
		driver.findElement(By.xpath("//input[contains(@id,'uxCityDropDown_Input')]")).click();
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(CITY_DD)).click().build().perform();
		logger.info("City dropdown clicked");
		driver.pauseExecutionFor(2000);
		driver.waitForElementPresent(FIRST_VALUE_OF_CITY_DD);
		driver.clickByJS(RFWebsiteDriver.driver,FIRST_VALUE_OF_CITY_DD,"First vale in city DD");
		logger.info("City selected");
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
		driver.type(PHONE_NUMBER_BILLING_PROFILE_PAGE,phnNumber,"phone number");
	}

	public void clickBeginSearchBtn(){
		driver.clickByJS(RFWebsiteDriver.driver, BEGIN_SEARCH_BTN,"Begin search button");
		//(BEGIN_SEARCH_BTN,"Begin search button");
	}


	public void selectSponsorRadioBtn(){
		driver.waitForElementPresent(SPONSOR_RADIO_BTN);
		driver.clickByJS(RFWebsiteDriver.driver, SPONSOR_RADIO_BTN, "Radio button of sponsor");
	}


	public void clickSelectAndContinueBtnForPCAndRC(){
		driver.clickByJS(RFWebsiteDriver.driver, CONTINUE_BTN_LOC,"Select and Continue button");
		//driver.click(CONTINUE_BTN_PREFERRED_PROFILE_PAGE_LOC,"Select and Continue button");
		driver.waitForPageLoad();		
	}

	public void clickLoginButtonInMobile() {
		driver.quickWaitForElementPresent(LOGIN_BUTTON_MOBILE_LOC);
		driver.click(LOGIN_BUTTON_MOBILE_LOC, "Login Button in Mobile");
	}	

	public String selectProductFromCarouselcorp() {
		String prodname=null;;
		for(int i =1;i<=4;i++ ){
			try {
				driver.isElementVisible(By.xpath(".//*[@class='slick-track']//div["+i+"]//span/a"));
				prodname=driver.getText(By.xpath(".//*[@class='slick-track']//div["+i+"]//span/a")).toLowerCase();
				logger.info(prodname);  
				driver.clickByJS(RFWebsiteDriver.driver,By.xpath(".//*[@class='slick-track']/div["+i+"]//img"),"Carousel product ");
				driver.pauseExecutionFor(2000);
				break;
			}catch(Exception e){
				logger.info("element not visible");
			}
		}
		return prodname;
	}	

	/***
	 * This method is used to enter billing information
	 * 
	 * @param billingName
	 * @param firstName
	 * @param lastName
	 * @param cardName
	 * @param cardNumer
	 * @param month
	 * @param year
	 * @param addressLine1
	 * @param postalCode
	 * @param phnNumber
	 * @param CVV
	 */
	public void enterBillingInfo(String billingName, String firstName,String lastName,String cardName,String cardNumer,String month,String year,String addressLine1,String postalCode,String phnNumber,String CVV){
		driver.type(BILLING_NAME_FOR_BILLING_PROFILE, billingName,"billing name");
		driver.type(ATTENTION_FIRST_NAME, firstName,"first name");
		driver.type(ATTENTION_LAST_NAME, lastName,"last name");
		driver.type(NAME_ON_CARD, cardName,"card name");
		driver.type(CREDIT_CARD_NUMBER_INPUT_FIELD, cardNumer,"card number");
		driver.type(By.xpath("//input[contains(@id,'uxCreditCardCvv')]"), CVV,"CVV");
		driver.click(EXPIRATION_DATE_MONTH_DD,"Exp Month DD");
		driver.click(By.xpath(String.format(expiryMonthLoc, month)),"Exp Month");
		driver.click(EXPIRATION_DATE_YEAR_DD,"Exp Year DD");
		driver.click(By.xpath(String.format(expiryYearLoc, year)),"Exp Year");
		driver.type(ADDRESS_LINE_1, addressLine1,"address Line 1");
		driver.type(ZIP_CODE, postalCode+"\t","zip code");
		driver.pauseExecutionFor(5000);// required
		if(driver.getDevice().equalsIgnoreCase("desktop")) {
			Actions actions = new Actions(RFWebsiteDriver.driver);
			driver.waitForElementPresent(CITY_DD, 5);
			actions.moveToElement(driver.findElement(CITY_DD)).click().build().perform();
			driver.pauseExecutionFor(1000);
			driver.click(FIRST_VALUE_OF_CITY_DD,"First Value of City DD");
		}else {
			driver.clickByJS(RFWebsiteDriver.driver, CITY_DD, "City DD");
			driver.waitForElementPresent(FIRST_VALUE_OF_CITY_DD);
			driver.clickByJS(RFWebsiteDriver.driver, FIRST_VALUE_OF_CITY_DD,"First Value of City DD");//(FIRST_VALUE_OF_CITY_DD,"");
		}
		driver.type(PHONE_NUMBER_BILLING_PROFILE_PAGE,phnNumber);
	}

	public StoreFrontHeirloomConsultantPage loginAsConsultant(String userName, String password) {
		logout();
		if(driver.getDevice().equalsIgnoreCase("mobile")){
			clickLoginButtonInMobile();
		}
		if(driver.isElementVisible(LOGIN_DROPDOWN_LINK_LOC,5)) {
			driver.click(LOGIN_DROPDOWN_LINK_LOC, "Login DropDown");
		}
		driver.type(USERNAME_TEXT_BOX_LOC, userName,"username");
		driver.type(PASSWORD_TEXT_BOX_LOC, password,"password");
		Actions actions = new Actions(RFWebsiteDriver.driver);
		actions.moveToElement(driver.findElement(ENTER_LOGIN_BTN_LOC)).click().build().perform();
		logger.info("login  enter button clicked");
		acceptPoliciesAndProcedures();
		clickRenewLater();
		return new StoreFrontHeirloomConsultantPage(driver);
	}	

	public boolean isLoginFailed(){
		return driver.isElementVisible(By.xpath("//*[contains(text(),'Invalid login')]"),5);
	}

	public StoreFrontHeirloomPCUserPage loginAsPCUser(String username,String password){
		if(driver.getDevice().equalsIgnoreCase("mobile")){
			clickLoginButtonInMobile();
		}
		if(driver.isElementVisible(LOGIN_DROPDOWN_LINK_LOC,2)) {
			driver.click(LOGIN_DROPDOWN_LINK_LOC, "Login DropDown");
		}
		driver.type(USERNAME_TXTFLD_LOC, username,"username");
		driver.type(PASSWORD_TXTFLD_LOC,password,"password");  
		driver.click(LOGIN_BTN_LOC,"login button");
		driver.waitForPageLoad();
		acceptPoliciesAndProcedures();
		return new StoreFrontHeirloomPCUserPage(driver);		
	}	

	public StoreFrontHeirloomPCUserPage loginAsInvalidPCUser(String username,String password){
		if(driver.getDevice().equalsIgnoreCase("mobile")){
			clickLoginButtonInMobile();
		}
		if(driver.isElementVisible(LOGIN_DROPDOWN_LINK_LOC,2)) {
			driver.click(LOGIN_DROPDOWN_LINK_LOC, "Login DropDown");
		}
		driver.type(USERNAME_TXTFLD_LOC, username,"username");
		driver.type(PASSWORD_TXTFLD_LOC,password,"password");  
		driver.click(LOGIN_BTN_LOC,"login button");
		return new StoreFrontHeirloomPCUserPage(driver);		
	}	

	public StoreFrontHeirloomRCUserPage loginAsRCUser(String username,String password){
		if(driver.getDevice().equalsIgnoreCase("mobile")){
			clickLoginButtonInMobile();
		}
		if(driver.isElementVisible(LOGIN_DROPDOWN_LINK_LOC)) {
			driver.click(LOGIN_DROPDOWN_LINK_LOC, "Login DropDown");
		}
		driver.waitForElementPresent(USERNAME_TXTFLD_LOC);
		driver.type(USERNAME_TXTFLD_LOC, username);
		//driver.click(PASSWORD_TXTFLD_ONFOCUS_LOC);
		driver.type(PASSWORD_TXTFLD_LOC,password);  
		logger.info("login username is "+username);
		logger.info("login password is "+password);
		driver.click(LOGIN_BTN_LOC,"login button");
		logger.info("login button clicked");
		driver.waitForPageLoad();
		acceptPoliciesAndProcedures();
		return new StoreFrontHeirloomRCUserPage(driver);
	}	

	/***
	 * This method is used to click on the terms and conditions while doing consultant enrollment
	 */
	public StoreFrontHeirloomHomePage clickTermsAndConditions(){
		driver.waitForElementToBeVisible(EMAIL_POLICY_AND_PROCEDURE_TOGGLE_BTN_LOC, 10);
		driver.scrollToElement(RFWebsiteDriver.driver, driver.findElement(EMAIL_POLICY_AND_PROCEDURE_TOGGLE_BTN_LOC));
		driver.click(EMAIL_POLICY_AND_PROCEDURE_TOGGLE_BTN_LOC,"Email policy and procedure toggle button");
		driver.click(ENROLL_SWITCH_POLICY_TOGGLE_BTN_LOC,"Enroll switch policy toggle button");
		driver.click(ENROLL_TERMS_CONDITIONS_TOGGLE_BTN_LOC,"Enroll terms and conditions toggle button");
		driver.click(ENROLL_E_SIGN_CONSENT_TOGGLE_BTN_LOC,"Enroll e sign consent toggle button");
		return this;
	} 

	/***
	 * This method is used to click on the Next button for completing the account
	 */
	public StoreFrontHeirloomHomePage clickCompleteAccountNextBtn(){
		driver.click(COMPLETE_ACCOUNT_NEXT_BTN_LOC,"complete account next button");
		driver.waitForPageLoad();
		return this;
	}


	public void enterUserPrefixInPrefixField(String prefixField){
		driver.quickWaitForElementPresent(WEBSITE_PREFIX_LOC);
		driver.clear(WEBSITE_PREFIX_LOC);
		driver.type(WEBSITE_PREFIX_LOC, prefixField,"");
		logger.info("Prefix field enterd as: "+prefixField);
		driver.pauseExecutionFor(3000);
	}

	/***
	 * This method is used to click on Use As Entered Button
	 */
	public void clickUseAsEnteredBtn(){
		if(driver.isElementVisible(USE_AS_ENTERED_BTN_LOC)==true){			
			driver.click(USE_AS_ENTERED_BTN_LOC,"use as entered button");
		}
		driver.waitForPageLoad();
	}	

	/***
	 * This method is used to verify if User name textfield visible on login dropdown or not
	 * 
	 *  return boolean value
	 */
	public boolean isEmailTextFieldDisplayedInLoginDropdown() {
		return driver.isElementVisible(USERNAME_TXTFLD_LOC);
	}

	/***
	 * This method is used to verify if Password textfield visible on login dropdown or not
	 * 
	 *  return boolean value
	 */
	public boolean isPasswordTextFieldDisplayedInLoginDropdown() {
		return driver.isElementVisible(PASSWORD_TXTFLD_LOC);
	}

	/***
	 * This method is used to verify if Forgot Password Link visible on login dropdown or not
	 * 
	 *  return boolean value
	 */
	public boolean isForgotPasswordLinkDisplayedInLoginDropdown() {
		return driver.isElementVisible(FORGOT_PASSWORD_PWS_LINK_LOC);
	}

	/***
	 * This method is used to verify if Login Button visible on login dropdown or not
	 * 
	 *  return boolean value
	 */
	public boolean isLoginButtonDisplayedInLoginDropdown() {
		return driver.isElementVisible(ENTER_LOGIN_BTN_LOC);
	}

	/***
	 * This method is used to click on login dropdown
	 * 
	 *  return StoreFrontHeirloomHomePage current class object
	 */
	public StoreFrontHeirloomHomePage clickLoginDropdown() {
		driver.click(LOGIN_DROPDOWN_LINK_LOC,"Login dropdown");
		return this;
	}

	/***
	 * This method is used to verify login error
	 * 
	 *  return boolean value
	 */
	public boolean isLoginErrorDisplayed() {
		return driver.isElementVisible(LOGIN_ERROR_LOC);
	}

	/***
	 * This method is used to click on login button from login dropdown
	 * 
	 *  return StoreFrontHeirloomHomePage current class object
	 */
	public StoreFrontHeirloomHomePage clickLoginButtonInLoginDropdown() {
		driver.click(ENTER_LOGIN_BTN_LOC, "Login Button In Login Dropdown");
		return this;
	}

	/***
	 * This method is used to verify My Account Dropdown Visible or not
	 * 
	 *  return boolean value
	 */
	public boolean isMyAccountDropdownDisplayedAfterLogin() {
		driver.waitForElementPresent(MY_ACCOUNT_DROPDOWN_LINK_LOC);
		return driver.isElementPresent(MY_ACCOUNT_DROPDOWN_LINK_LOC);
	}

	/***
	 * This method is used to verify if Country Dropdown Icon is present else return false
	 * 
	 *  return StoreFrontHeirloomRCUserPage object
	 */
	public boolean isCountryDropdownIconPresent(){
		return driver.isElementVisible(COUNTRY_DROPDOWN_ICON);
	}

	/***
	 * This method is used to verify if Cart Icon is present else return false
	 * 
	 *  return StoreFrontHeirloomRCUserPage object
	 */
	public boolean  isCartIconPresent(){
		return driver.isElementVisible(CART_ICON);
	}

	/***
	 * This method is used to verify if Login Icon is present else return false
	 * 
	 *  return StoreFrontHeirloomRCUserPage object
	 */
	public boolean  isLoginIconPresent(){
		return driver.isElementVisible(LOGIN_ICON);
	}	

	/*
	 * This method is used to fetch the "Welcome User" Link in the Home Page
	 * */
	public String fetchWelcomeUserLink()	{
		driver.quickWaitForElementPresent(WELCOME_USER_LINK);
		String welcomeUserName=driver.getText(WELCOME_USER_LINK);
		logger.info("Welcome User Link is displayed");
		return welcomeUserName;
	}

	/***
	 * This method is used to verify Eyebrow panel is displayed on UI or not
	 * 
	 *  return boolean value
	 */
	public boolean isEyeBrowSectionVisible() {
		driver.waitForElementPresent(EYEBROW_PANEL_LOC);
		return driver.isElementVisible(EYEBROW_PANEL_LOC);
	} 

	/***
	 * This method is used to verify Eyebrow panel is displayed after scrolling to Bottom or not
	 * 
	 *  return boolean value
	 * @throws InterruptedException 
	 */
	public boolean isEyebrowPanelDisplayedAfterScrollingToBottom() throws InterruptedException {
		driver.waitForElementToBeVisible(EYEBROW_PANEL_LOC, 5);
		driver.scrollToBottomJS();
		driver.waitForElementToBeInVisible(EYEBROW_PANEL_LOC, 2);
		return driver.isElementVisible(EYEBROW_PANEL_LOC);
	}	

	/***
	 * This method is used to verify whether user is logout or not
	 * 
	 *  return boolean value
	 */
	public boolean isUserLogoutSuccessfully() {
		return driver.isElementVisible(LOGIN_DROPDOWN_LINK_LOC);
	} 

	public boolean isSponsorNameContainsRFIndependentConsultant() {
		return driver.isElementPresent(SPONSOR_NAME_TOP_HEADER_LOC);
	}

	public boolean isPageHeaderContainsSpecifiedCategory(String pageHeaderName) {
		return driver.isElementPresent(By.xpath("//*[contains(text(),'"+pageHeaderName+"')]"));
	}	

	/***
	 * This method is used to verify if login dropdown is visible on UI or not
	 * 
	 *  return boolean value
	 */
	public boolean isLoginDropDownDisplayed() {
		return driver.isElementVisible(LOGIN_DROPDOWN_LINK_LOC);
	}

	/***
	 * This method is used to click on My Account Dropdown
	 * 
	 *  return current class object
	 */
	public StoreFrontHeirloomHomePage clickMyAccountDropdown() {
		driver.waitForElementPresent(MY_ACCOUNT_DROPDOWN_LINK_LOC);
		driver.click(MY_ACCOUNT_DROPDOWN_LINK_LOC, "My Account Dropdown");
		return this;
	}

	/***
	 * This method is used to verify specified category present in My Account Dropdown or not
	 * 
	 *  return boolean value
	 */
	public boolean isSpecifiedCategoryPresentInMyAccountDropdown(String category) {
		return driver.isElementPresent(By.xpath("//a[contains(text(),'"+category+"')]"));
	}

	/***
	 * This method is used to Login as consultant user
	 * 
	 *  return StoreFrontHeirloomConsultantPage object
	 */
	public StoreFrontHeirloomConsultantPage loginAsConsultantForInvalid(String userName, String password) {
		if(driver.getDevice().equalsIgnoreCase("mobile")){
			clickLoginButtonInMobile();
		}
		if(driver.isElementVisible(LOGIN_DROPDOWN_LINK_LOC)) {
			driver.click(LOGIN_DROPDOWN_LINK_LOC, "Login DropDown");
		}
		driver.type(USERNAME_TEXT_BOX_LOC, userName);
		logger.info("Entered Username is: "+userName);
		driver.type(PASSWORD_TEXT_BOX_LOC, password);
		driver.click(ENTER_LOGIN_BTN_LOC, "Login button");
		return new StoreFrontHeirloomConsultantPage(driver);
	}	

	public boolean validateDisclaimerLinkInFooter(){
		driver.quickWaitForElementPresent(DISCLAIMER_LINK_LOC);
		driver.clickByJS(RFWebsiteDriver.driver, DISCLAIMER_LINK_LOC,"Disclaimer Link");
		logger.info("Disclaimer Link clicked");
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("Disclaimer");
	}

	public void clickBottomPageCategoryLink(String categoryName) {
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(RF_CONNECTION_LINK_LOC, categoryName)),"");//(By.xpath(String.format(RF_CONNECTION_LINK_LOC, categoryName)),"");
		logger.info("Clicked on "+categoryName);
	}

	public void selectRegimen(String regimen){
		regimen = regimen.toUpperCase();
		//		driver.get(driver.getURL().replaceAll("http", "https")+"/Shop/REVERSE-BRIGHTENING/products");

		driver.quickWaitForElementPresent(By.xpath(String.format(regimenLoc, regimen)));
		//driver.click(By.xpath(String.format(regimenLoc, regimen)),"regimen="+regimen);
		driver.clickByJS(RFWebsiteDriver.driver, By.xpath(String.format(regimenLoc, regimen)),"regimen="+regimen);
		//		logger.info("Regimen selected is: "+regimen);
	}

	public String getTaxForKitFromOrderReviewAndConfirmPage() {
		return driver.getText(TAX_FOR_KIT_ON_ORDER_REVIEW_PAGE_LOC,"tax FOR KIT form order review and confirm page").split("\\$")[1];
	}

	public String getTaxForcrpFromOrderReviewAndConfirmPage() {
		return driver.getText(TAX_FOR_CRP_ON_ORDER_REVIEW_PAGE_LOC,"tax FOR CRP form order review and confirm page").split("\\$")[1];
	}

	/***
	 * This method is used to click on Accept Button
	 */
	public void clickAcceptBtnAtQASPopup(){
		if(driver.isElementVisible(ACCEPT_BTN_LOC)==true){			
			driver.click(ACCEPT_BTN_LOC,"use as entered button");
		}
	}

	/***
	 * This method is used to verify Eyebrow panel is displayed after scrolling to Top
	 * 
	 *  return boolean value
	 * @throws InterruptedException 
	 */
	public boolean isEyebrowPanelDisplayedAfterScrollingToTopHeader() throws InterruptedException {
		driver.click(UP_BUTTON, "UP button on right bottom");
		driver.waitForElementToBeVisible(EYEBROW_PANEL_LOC, 5);
		return driver.isElementVisible(EYEBROW_PANEL_LOC);
	}


	public void dismissPulsePopup() {
		try {
			if(driver.isElementVisible(PULSE_DISMISS_BUTTON_LOC));
			driver.click(PULSE_DISMISS_BUTTON_LOC, "dismiss button on pulse homepage");
		} catch (Exception E) {
			logger.info("Pulse Dismiss popup not present");
		}

	}

	public void goToTop() {
		driver.click(UP_BUTTON, "UP button to scroll top.");
	}


	/***
	 * This method is used to enter shipping details
	 * @param addressName
	 * @param firstName
	 * @param lastName
	 * @param addressLine1
	 * @param postalCode
	 * @param phnNumber
	 */
	public void enterShippingProfileDetailsForEditCRP(String addressName, String firstName,String lastName,String addressLine1,String postalCode,String phnNumber){
		driver.type(ADDRESS_NAME_FOR_SHIPPING_PROFILE, addressName,"Address name");
		driver.type(ATTENTION_FIRST_NAME, firstName,"Attention first name");
		driver.type(ATTENTION_LAST_NAME, lastName,"Attention last name");
		driver.type(ADDRESS_LINE_1, addressLine1,"Address line 1");
		driver.type(ZIP_CODE, postalCode+"\t","Postal code");
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
		driver.pauseExecutionFor(3000);//required
		driver.type(PHONE_NUMBER_BILLING_PROFILE_PAGE,phnNumber,"Phone number");
	}

	/***
	 * This method is used to enter the Billing information
	 * @param billingName
	 * @param firstName
	 * @param lastName
	 * @param cardName
	 * @param cardNumer
	 * @param month
	 * @param year
	 * @param addressLine1
	 * @param postalCode
	 * @param phnNumber
	 * @param CVV
	 */
	public void enterBillingInfoForEditCRP(String billingName, String firstName,String lastName,String cardName,String cardNumer,String month,String year,String addressLine1,String postalCode,String phnNumber, String CVV){
		driver.type(BILLING_NAME_FOR_BILLING_PROFILE, billingName,"Billing profile name");
		driver.type(ATTENTION_FIRST_NAME, firstName,"Attention first name");
		driver.type(ATTENTION_LAST_NAME, lastName,"Attention last name");
		driver.type(NAME_ON_CARD, cardName,"Card Name");
		driver.type(CREDIT_CARD_NUMBER_INPUT_FIELD, cardNumer,"Card number ");
		driver.type(By.xpath(".//*[contains(@id,'uxCvv')]"), CVV,"cvv");
		driver.click(EXPIRATION_DATE_MONTH_DD,"exp month DD");
		driver.click(By.xpath(String.format(expiryMonthLoc, month)),"exp month");
		driver.click(EXPIRATION_DATE_YEAR_DD,"exp year DD");
		driver.click(By.xpath(String.format(expiryYearLoc, year)),"exp year");
		driver.type(ADDRESS_LINE_1, addressLine1,"Billing street address");
		driver.type(ZIP_CODE, postalCode+"\t","postal code");
		driver.pauseExecutionFor(5000);
		driver.type(PHONE_NUMBER_BILLING_PROFILE_PAGE,phnNumber,"phone number");
	}

	public boolean validateExistingConsultantPopUp(String emailAddress){
		driver.type(ACCOUNT_EMAIL_ADDRESS_LOC, emailAddress,"email address");
		driver.type(ACCOUNT_PASSWORD_LOC, "","password");
		if(driver.isElementVisible(By.xpath("//div[contains(@id,'Existent')]/p[contains(text(),'already have a Consultant account')]"))){
			return true;
		}else if(driver.isElementVisible(By.xpath("//div[@id='ExistentRFOAccount']/p[contains(text(),'already used by another account')]"))) {
			return true;
		}
		return false;
	}

	public boolean isDERMRFPageHeaderVisible() {
		boolean flag= driver.isElementVisible(DERM_RF_PAGE_HEADER_LOC);
		closeChildSwitchToParentWindow();
		return flag;
	}

	public boolean isRFConnectionPageHeaderVisible() {
		boolean flag= driver.isElementVisible(RF_CONNECTION_PAGE_HEADER_LOC);
		closeChildSwitchToParentWindow();
		return flag;
	}	


	public boolean isUserRedirectedToExpectedPage(String parentWindow, String url) {
		String currentUrl=null;
		boolean flag=false;
		driver.switchToChildWindow(parentWindow);
		currentUrl=driver.getCurrentUrl();
		return currentUrl.contains(url);
	}

	/***
	 * This method is used to enter account information
	 */
	public StoreFrontHeirloomHomePage enterSetUpAccountInformation(String firstName,String lastName,String emailAddress,String password,String addressLine1,String postalCode,String phnNumber1,String phnNumber2,String phnNumber3){
		driver.type(ACCOUNT_FIRST_NAME_LOC, firstName,"first name");
		driver.type(ACCOUNT_LAST_NAME_LOC, lastName,"last name");
		driver.type(ACCOUNT_EMAIL_ADDRESS_LOC, emailAddress,"email address");
		driver.type(ACCOUNT_PASSWORD_LOC, password,"password");
		driver.type(ACCOUNT_CONFIRM_PASSWORD_LOC, password,"confirm password");
		driver.type(ACCOUNT_MAIN_ADDRESS1_LOC, addressLine1,"address line 1");
		if(postalCode.contains("-")){
			String postalCode1 = postalCode.split("\\-")[0];
			String postalCode2 = "-"+postalCode.split("\\-")[1];
			driver.click(ACCOUNT_POSTAL_CODE_LOC, "Postal code field");
			driver.findElement(ACCOUNT_POSTAL_CODE_LOC).sendKeys(postalCode1);
			driver.findElement(ACCOUNT_POSTAL_CODE_LOC).click();
			driver.pauseExecutionFor(3000);
			driver.findElement(ACCOUNT_POSTAL_CODE_LOC).sendKeys(postalCode2);
		}else{
			driver.type(ACCOUNT_POSTAL_CODE_LOC,postalCode,"Postal code");
		}
		//driver.click(ACCOUNT_PHONE_NUMBER1_LOC, "phone number 1");//\t is working too fast
		driver.type(ACCOUNT_PHONE_NUMBER1_LOC,phnNumber1,"phn num 1");
		driver.type(ACCOUNT_PHONE_NUMBER2_LOC,phnNumber2,"phn num 2");
		driver.type(ACCOUNT_PHONE_NUMBER3_LOC,phnNumber3,"phn num 3");
		driver.waitForStorfrontLegacyLoadingImageToDisappear();
		return this;
	}  

	public boolean validateExecuteTeamLink(){
		//driver.quickWaitForElementPresent(EXECUTIVE_TEAM_LINK_LOC);
		//driver.click(EXECUTIVE_TEAM_LINK_LOC,"");
		//logger.info("Execute Team Link clicked");
		driver.waitForPageLoad();
		return driver.getCurrentUrl().contains("Executives");
	}	


	public void clickDonationProduct() {
		String currentUrl =driver.getCurrentUrl();
		String donationUrl= currentUrl+"/Shop/Product/PFC0001";
		driver.get(donationUrl);
		logger.info("Donation Url opened is: "+donationUrl);
		//driver.click(DONATION_PRODUCT_LOC,"");
		driver.click(By.xpath("//span[contains(text(),'Donate')]"), "Donate button");
		driver.click(By.xpath("//span[contains(text(),'Add to Bag')]"), "Add to bag button");
		//driver.clickByJS(RFWebsiteDriver.driver, DONATION_PRODUCT_LOC,"");
		logger.info("clicked on donation product");
	}

	public void clickBuyNowBtn(){
		driver.click(BUY_NOW_BTN_LOC,"Buy Now btn");
	}

	public void clickConfirmBtnForPCPerks(){
		driver.click(CONFIRM_BTN_LOC,"Confirm btn");
		driver.waitForPageLoad();
	}

	public void clickViewDetailsLinkOfFirstOrderForPC(){
		driver.click(FIRST_VIEW_DETAILS_LINK_LOC,"View details link of first order");
	}

	public boolean verifyUserSuccessfullyLoggedInOnCorpSite() {
		if(driver.isElementVisible(MY_ACCOUNT_DROPDOWN_LINK_LOC)){
			driver.click(MY_ACCOUNT_DROPDOWN_LINK_LOC, "Login DropDown");
			if(driver.isElementPresent(LOGOUT_LOC)){
				logger.info("User Successfully Logged In");
				return true;
			}else return false;

		}
		else{
			driver.waitForElementPresent(LOG_OUT_ON_CORP_BTN);
			if(driver.isElementPresent(LOG_OUT_ON_CORP_BTN)){
				logger.info("User Successfully Logged In");
				return true;
			}else 
				return false;
		}
	} 

	public void clickForgotPasswordLinkOnBizHomePage(){
		if(driver.isElementVisible(LOGIN_DROPDOWN_LINK_LOC)){
			driver.click(LOGIN_DROPDOWN_LINK_LOC, "Login DropDown");
			driver.click(FORGOT_PASSWORD_PWS_LINK_LOC,"");
			logger.info("Forgot Password Link clicked");
			driver.waitForPageLoad();
		}
		else{
			driver.quickWaitForElementPresent(FORGOT_PASSWORD_PWS_LINK_LOC);
			driver.click(FORGOT_PASSWORD_PWS_LINK_LOC,"");
			logger.info("Forgot Password Link clicked");
			driver.waitForPageLoad();
		}
	} 

	public boolean isLoginButtonPresent(){
		driver.waitForElementPresent(LOGIN_DROPDOWN_LINK_LOC,50);
		return driver.isElementPresent(LOGIN_DROPDOWN_LINK_LOC);
	}

	public StoreFrontHeirloomPulsePage clickCheckMyPulse() {
		if(driver.isElementVisible(MY_ACCOUNT_DROPDOWN_LINK_LOC,5)) {
			driver.click(MY_ACCOUNT_DROPDOWN_LINK_LOC, "Login DropDown");
		}
		driver.doubleClick(CHECK_MY_PULSE_LOC,"check my pulse");
		return new StoreFrontHeirloomPulsePage(driver);
	}	
	
	public boolean isCountryPresentInCountryToggle(String countryName) {
		driver.waitForElementToBeVisible(By.xpath(String.format(countryLoc, countryName)), 5);
		return driver.isElementVisible(By.xpath(String.format(countryLoc, countryName))) || driver.isElementVisible(By.xpath("//*[contains(@src,'flag_aus')]"), 10000);
	}
	
	public void selectCountry(String countryName) {
		if(driver.isElementVisible(By.xpath(countryLoc))) {
		driver.click(By.xpath(String.format(countryLoc, countryName,countryName)),"country flag");
		}else {
			countryName=countryName.toLowerCase();
			driver.click(By.xpath(String.format("//*[contains(@id,'countryflagsmenu')]/descendant::img[contains(@src,'%s')]",countryName)),"country flag");
		}
		logger.info("Country selected is: "+countryName);
	}

}