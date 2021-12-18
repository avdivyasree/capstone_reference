package testSuites;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import utils.MyLog;

import static utils.ExtentTestManager.startTest;

public class LoginTest extends BaseTest {
	
	
	@Test(enabled=true, 
			priority = 0, 
			dataProvider= "invalidLogin",
			dataProviderClass = StaticDataProvider.class,
			groups= {"invalidLoginGrp"}, 
			description="Invalid login")
	public void invalidLoginTest(String uname, String pass, Method method) throws InterruptedException {		
		// Set ExtentReports Description
		startTest(method.getName(), "Invalid login screnario with invalid username and password.");
		homePage
			.goToMedicare()
			.goToLoginPage()
			.loginMedicare(uname, pass)
			.verifyInvalidLogin();
	}	

	@Test(enabled=true, 
			priority = 1, 
			dataProvider= "validUserLogin",
			dataProviderClass = StaticDataProvider.class,
			groups= {"validUserLoginGrp"}, 
			description="Valid user login")
	public void validUserLoginTest(String uname, String pass, String fname, Method method) throws InterruptedException {		
		// Set ExtentReports Description
		startTest(method.getName(), "Valid user login screnario with username and password. To validate full name.");
		homePage
			.goToMedicare()
			.goToLoginPage()
			.loginMedicare(uname, pass)
			.sleep(1000)
			.goToLandingPage()
			.verifyValidUserLogin(fname);
		
		if (testLoginOnlyFlag) {
			MyLog.debug("Going to logout");
			landingPage.logoutAction();
		}

	}		

	@Test(enabled=true, 
			priority = 2, 
			dataProvider= "validAdminLogin",
			dataProviderClass = StaticDataProvider.class,
			groups= {"validAdminLoginGrp"}, 
			description="Valid admin login")
	public void validAdminLoginTest(String uname, String pass, String fname, Method method) throws InterruptedException {		
		// Set ExtentReports Description
		startTest(method.getName(), "Valid admin login screnario with username and password. To validate full name.");
		homePage
			.goToMedicare()
			.goToLoginPage()
			.loginMedicare(uname, pass)
			.sleep(1000)
			.goToLandingPage()
			.verifyValidAdminLogin(fname);

		if (testLoginOnlyFlag) landingPage.logoutAction();
			
	}		

	
	
}
