package testSuites;

import static utils.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.annotations.Test;



public class ShoppingTest extends BaseTest {

	
	
	
	@Test(enabled=true, 
			priority = 3, 
			dataProvider= "buy2Products",
			dataProviderClass = StaticDataProvider.class,
			groups= {"addProductToCart"},
			description="add Product to cart")
	public void buy2ProductsTest(String uname, String pass, String productName1, String productName2, Method method) throws InterruptedException {		
		// Set ExtentReports Description
		startTest(method.getName(), "Buy 2 Products Test Scenario");

		
		homePage
			.goToMedicare()
			.goToLoginPage()
			.loginMedicare(uname, pass)
			.sleep(1000)
			.goToLandingPage()
			.clickViewProductsMenu()
			.showAllEntries()
			.addProductToCart(productName1)
			.transitToCartPage()
			.sleep(1000)
			.clickContinueShopping()
			.sleep(1000)
			.addProductToCart(productName2)
			.transitToCartPage()
			.clickCheckout()
			.isShippingPage()
			.selectFirstAddress()
			.isPaymentPage()
			.valFinalPaymentSum()
			.insertCardDetails("1111-2222-3333-4444", "11", "11", "123")
			.clickPay()
			.validateOrderConfirmed()
			.continueShopping()
			.logoutAction();
			
			
			
	}		
	
}
