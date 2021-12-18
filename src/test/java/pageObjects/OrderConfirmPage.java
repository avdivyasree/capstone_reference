package pageObjects;

import static org.testng.Assert.assertEquals;
import static utils.ExtentTestManager.getTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import utils.MyLog;

public class OrderConfirmPage extends BasePage {

	// Constructor
	public OrderConfirmPage(WebDriver driver) {
		super(driver);

	}

	// === Web Elements
	
	By SuccessBanner = By.xpath("//div[@class='alert alert-success']/h3");
	By continueShoppingButton = By.xpath("//a[contains(text(),'Continue')]");


	
	public OrderConfirmPage validateOrderConfirmed() {
		MyLog.info("Validate order is confirmed..");
		getTest().log(Status.INFO, "Order is confirmed");
		
		assertEquals(readText(SuccessBanner), "Your Order is Confirmed!!");
		return this;
	}
	
	public LandingPage continueShopping() {
		MyLog.info("Return to shopping");
		
		click(continueShoppingButton);
		return new LandingPage(driver);
	}	
	
	
	
	
	
	
	@Override
	public OrderConfirmPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}
	
	

}
