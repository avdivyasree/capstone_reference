package pageObjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import utils.MyLog;

public class ShippingPage extends BasePage {

	// Constructor
	public ShippingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// === Web Elements
	
	@FindBy(linkText="Select")
	List<WebElement> ShippingAddresses;	
	
	By ShippingPageVal = By.xpath("//div[@class='col-md-4']/h4");


	
	/**
	 * Verify landing in Shipping Page
	 */
	public ShippingPage isShippingPage()  {
		MyLog.info("Validate is Shipping Page.. ");
		assertEquals(readText(ShippingPageVal), "Select Shipping Address");
		return this;
	}
		
	
	/**
	 * Select first shipping address, it will transit to Payment page
	 */
	public PaymentPage selectFirstAddress() {
		
		MyLog.info("Selecting first address.. ");
		ShippingAddresses.get(0).click();
		return new PaymentPage(driver);
	}

	
	
	@Override
	public ShippingPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}	
	
}
