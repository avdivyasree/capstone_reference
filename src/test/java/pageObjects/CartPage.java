package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.MyLog;

public class CartPage extends BasePage {

	// Constructor
	public CartPage(WebDriver driver) {
		super(driver);

	}

	// === Web Elements
	
	By continueShoppingButton = By.xpath("//table[@id='cart']//a[contains(text(),'Continue')]");
	By checkoutButton = By.xpath("//table[@id='cart']//a[contains(text(),'Checkout')]");

	
	// Click "Continue Shopping" Button
	public LandingPage clickContinueShopping() {
		MyLog.info("Go back shopping..");
		click(continueShoppingButton);
		return new LandingPage(driver);
	}
	
	
	// Click Checkout Button, ie. go to ShippingPage
	public ShippingPage clickCheckout() {
		MyLog.info("Checking out..");
		click(checkoutButton);
		return new ShippingPage(driver);
	}
	
	
	// Goto Cart Page, access from Logon drop down menu
	public CartPage goToCartPage() {
		MyLog.info("Access to Cart Page from drop down menu..");
		// click(loginNavMenu);
		return new CartPage(driver);
	}
	
	
	
	
	
	@Override
	public CartPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}
	
	

}
