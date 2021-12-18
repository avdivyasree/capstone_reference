package pageObjects;

import static org.testng.Assert.assertEquals;
import static utils.ExtentTestManager.getTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import utils.MyLog;

public class LandingPage extends BasePage {

	public LandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	// === Web Elements
	
	By logonName = By.id("dropdownMenu1");
	By logoutButton = By.id("logout");
	
	By manageProductNavMenu = By.xpath("//*[@id='manageProduct']/a");
	By viewProductNavMenu = By.xpath("//*[@id='listProducts']/a");

	By productShowLengthSelection = By.xpath("//select[@name='productListTable_length']");


	@FindBy(xpath= "//table/tbody/tr[@role='row']")
	List<WebElement> ProductListings;

	@FindBy(xpath= "//table/tbody/tr[@role='row']/td[2]")
	List<WebElement> ProdList_Name;

	@FindBy(xpath="//table/tbody/tr[@role='row']/td[6]/a[2]")
	List<WebElement> ProdList_AddToCart;
	
	
	
	// === Page Methods
	
	public LandingPage clickViewProductsMenu( ) {
		MyLog.info("Going to click View Products nagivation menu");
		click(viewProductNavMenu);
		return this;
	}
	
	// Verify valid login with full name of user.
	public LandingPage verifyValidUserLogin(String fname) {
		MyLog.info("Verifying valid user login");
		waitVisibility(logonName);
		assertEquals(readText(logonName), fname);
		return this;
	}

	// Verify valid login with full name of user.
	public LandingPage verifyValidAdminLogin(String fname) {
		MyLog.info("Verifying valid admin login");
		
		// -- Check 2 conditions"
		// 1. correct fullname
		// 2. Manage Product menu is available (thus, is Admin)
		waitVisibility(logonName);
		assertEquals(readText(logonName), fname);
		
		waitVisibility(manageProductNavMenu);
		assertEquals(readText(manageProductNavMenu), "Manage Product");
		return this;
	}
	
	
	// Select show all products
	public LandingPage showAllEntries() {
		MyLog.info("Select show ALL entries of product");
		
		pickSelection(productShowLengthSelection, "ALL");
		return this;
		
	}
	
	
	// transition to Cart page, ie. the next page after add to cart
	public CartPage transitToCartPage() {
		MyLog.info("Transit to Cart Page..");
		return new CartPage(driver);
	}
	
	// Add product to cart
	public LandingPage addProductToCart(String product) {
		
		MyLog.info("Going to add [" + product + "] to cart..");
		getTest().log(Status.INFO, "Add [" + product + "] to cart");

		boolean matchedFlag = false;

		int totalProductListed = ProductListings.size();

		if ( totalProductListed > 0 ) {

			for ( int i = 0; i < totalProductListed; i++) {

				String pName = ProdList_Name.get(i).getText();

				System.out.println("DEBUG:AddProduct()::[" + i + "] Product Name is " + pName);

				// MATCHED
				if ( pName.equals(product) ) {
					// To click add to cart
					ProdList_AddToCart.get(i).click();
					MyLog.debug("Product [" + product + "] found & added to cart!");

					// Confirm added
					// http://localhost:8081/medicare/cart/show?result=added

					String ExpectedAddedURL = super.getAppURL() + "cart/show?result=added";

					MyLog.debug("ExpectedAddedURL [" + ExpectedAddedURL + "]");
					MyLog.debug("DriverCurrentURL [" + driver.getCurrentUrl() + "]");

					try {

						assertEquals(driver.getCurrentUrl(), ExpectedAddedURL);
						MyLog.info("Added Product [" + product + "] to cart!");
						Thread.sleep(2000);

						matchedFlag = true;
						
						return this;

					} catch(Throwable e) {
						MyLog.error("FAILED to add Product [" + product + "] to cart!");
						Assert.fail();
					}

					break;
				}

			}
		} 
		else {
			MyLog.error("No product listed!");
			Assert.fail();
		}			


		if ( !matchedFlag ) {
			MyLog.error("Product [" + product + "] not found! Validation FAILED!");
			Assert.fail();
		}		
		
		return this;
	}
	
	
	
	public void logoutAction() {
		MyLog.info("Going to logout");
		
		click(logonName);
		click(logoutButton);
	}
	
	
	
	

	@Override
	public LandingPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}

	
}
