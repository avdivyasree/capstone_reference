package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import utils.MyLog;



public class HomePage extends BasePage {
	
	// Constructor
	public HomePage(WebDriver driver ) {
		// invoke parent class constructor
		super(driver);
	}

	
	// === Web Elements 
	// Note: we use Page Object Model, By method instead of @FindBy (Page Factory)
	
	By medicareNavMenu = By.className("navbar-brand");
	
	By aboutNavMenu = By.id("about");
	By contactNavMenu = By.id("contact");
	By viewProductNavMenu = By.id("listProducts");

	By loginNavMenu = By.xpath("//*[@id='login']/a");
	By logoutNavMenu = By.id("logout");
	By signNavMenu = By.id("signup");

	
	// === Page Methods
	
	// Goto HomePage, ie. open URL
	public HomePage goToMedicare() {
		MyLog.info("Opening Medicare website..");
		driver.get(appURL);
		return this;
	}
	
	// Goto LoginPage
	public LoginPage goToLoginPage() {
		MyLog.info("Going to Login Page..");
		click(loginNavMenu);
		return new LoginPage(driver);
	}
	
	@Override
	public HomePage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}

	
	
	
	
}
