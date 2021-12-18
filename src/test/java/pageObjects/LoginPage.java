package pageObjects;

import static org.testng.Assert.assertEquals;
import static utils.ExtentTestManager.getTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.Status;

import utils.MyLog;

public class LoginPage extends BasePage {
	
	// Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// === Web Elements
	
	By alertBanner = By.xpath("//*[@class='alert alert-danger fade in']");
	By usernameField = By.id("username");
	By passwordField = By.id("password");
	By loginButton =  By.xpath("//*[@value='Login']");
	By logonName = By.id("dropdownMenu1");

	

	// === Page Methods
	
	// Login with username & password
	public LoginPage loginMedicare(String username, String password) {
		MyLog.info("Trying to login using [" + username + "/" + password + "]");
		getTest().log(Status.INFO, "Login via [" + username + "/" + password + "]");
		
		writeText(usernameField, username);
		writeText(passwordField, password);
		click(loginButton);
		return this;
	}

	// Verify invalid login
	public LoginPage verifyInvalidLogin() {
		MyLog.info("Verifying invalid login");
		waitVisibility(alertBanner);
		assertEquals(readText(alertBanner), "Username and Password is invalid!");
		return this;
	}
	
	// transition to LoggedInLanding page, ie. the next page after a valid login.
	public LandingPage goToLandingPage() {
		MyLog.info("Going to Logged-in Landing Page..");
		return new LandingPage(driver);
	}
	
	
	@Override
	public LoginPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}
	
}
