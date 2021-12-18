package pageObjects;



import static org.testng.Assert.assertEquals;
import static utils.ExtentTestManager.getTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.aventstack.extentreports.Status;

import utils.MyLog;

public class PaymentPage extends BasePage {

	// Constructor
	public PaymentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// === Web Elements
	
	
	By PaymentPageVal = By.xpath("//*[@class='panel-title']");
	
	By FinalPaymentSum = By.xpath("//span[@class='badge pull-right']");
	
	By PayButton = By.xpath("//a[@role='button' and contains(text(), 'Pay')]");
	
	@FindBy(xpath="//h3[contains(text(),'Grand Total')]")
	List<WebElement> GrandTotals;
	
	By cardNumberField = By.xpath("//input[@id='cardNumber']");
	By expMonthField = By.xpath("//input[@id='expityMonth']");	// take note the developer of medicare website got typo error
	By expYearField = By.xpath("//input[@id='expityYear']");
	By cvCodeField = By.xpath("//input[@id='cvCode']");
	
	
	/**
	 * Verify landing in Payment Page
	 */
	public PaymentPage isPaymentPage()  {
		MyLog.info("Validate is Payment Page.. ");
		assertEquals(readText(PaymentPageVal), "Payment Details");
		return this;
	}
		
	
	/**
	 * Verify final payment sum is correct by adding up all grand total
	 * @throws InterruptedException
	 */	
	public PaymentPage valFinalPaymentSum() {
		
		MyLog.info("Going to validate final payment sum..");
		getTest().log(Status.INFO, "validate final payment sum");
		
		// Sum up all price
		float finalSum = 0.0f ;
		
		for (WebElement item : GrandTotals ) {
			
			String priceString = item.getText();
			String p2 = priceString.replaceAll("Grand Total - ₹ ", "");
			String p3 = p2.replaceAll("/-", "");
			
			finalSum += Float.parseFloat(p3);
			
			MyLog.debug("valFinalPaymentSum():: price [" + p3 + "]" );
			
		}
		
		MyLog.debug("valFinalPaymentSum():: final sum computed [" + finalSum + "]");
		MyLog.debug("valFinalPaymentSum():: final sum shown on page [" + readText(FinalPaymentSum) + "]");
		
		// final sum shown on page [₹ 76.0/-]
		String computedFinalSum = "₹ " + finalSum + "/-";
		
		assertEquals(computedFinalSum,readText(FinalPaymentSum));
		
		return this;
	}	
	

	
	public PaymentPage insertCardDetails(String cardNo, String expMonth, String expYear, String cvCode) {
		
		MyLog.info("Going to insert payment card details..");
		
		writeText(cardNumberField, cardNo);
		writeText(expMonthField, expMonth);
		writeText(expYearField, expYear);
		writeText(cvCodeField, cvCode);
		
		
		return this;
		
	}
	
	
	public OrderConfirmPage clickPay() {
		MyLog.info("Going to proceed confirm payment..");
		
		click(PayButton);
		
		return new OrderConfirmPage(driver);
	}
	

@Override
	public PaymentPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}	
	
}
