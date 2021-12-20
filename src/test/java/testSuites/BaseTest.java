package testSuites;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import utils.MyLog;




public class BaseTest {

	public RemoteWebDriver Rdriver;

	public WebDriver driver;
	public WebDriver getDriver() { return driver; }
	

	public static boolean gridFlag=false;
	public static boolean testLoginOnlyFlag=false;

	
	SoftAssert soft = new SoftAssert();
	
	public static XSSFWorkbook wbook;
	public static XSSFSheet sheet;
	

	
	public HomePage homePage;
	public LandingPage landingPage;

	
	/**
	 * Setup chrome driver
	 * @param aws_host
	 * @param headlessFlag
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@BeforeClass(enabled=true, alwaysRun = true, description="Setup the webdriver, either local or grid")
	@Parameters({"headlessFlag", "loginOnlyFlag"})
	public void classLevelSetup(String headlessFlag, String loginOnlyFlag) throws MalformedURLException, IOException {
		
		MyLog.info("Starting test!");

		
		if ( loginOnlyFlag.equalsIgnoreCase("TRUE") ) {
			testLoginOnlyFlag = true;
		}
		
		MyLog.debug("testLoginOnlyFlag is [" + testLoginOnlyFlag + "]" );
		
		/* Can test different browser
		 * - on windows
		 */
		// cap.setPlatform(Platform.LINUX);
		// cap.setBrowserName("chrome");
		// cap.setBrowserName("firefox");
		// cap.setPlatform(Platform.WINDOWS);
		// cap.setBrowserName("opera");
		// cap.setBrowserName("MicrosoftEdge");
		
		//gridFlag=true;
		gridFlag=false;
		
		if (gridFlag) {

			// -- for future improvement
			
			String sURL = "http://localhost:4444/wd/hub";
			// String sURL = "http://192.168.100.190:4444/wd/hub";
			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setPlatform(Platform.LINUX);
			cap.setBrowserName("chrome");
			
			// -- We can test other browsers if connect to Selenium Grid
			// cap.setPlatform(Platform.WINDOWS);
			// cap.setBrowserName("opera");
			// cap.setBrowserName("MicrosoftEdge");
			
			Rdriver = new RemoteWebDriver(new URL(sURL), cap);
			driver = Rdriver;
		}
		else {

			// localhost
			// System.setProperty("webdriver.chrome.driver", "chromedriver.96");
			
			// 20Dec21 - added webdriver manager
			// - this will ease the version problem
			// https://bonigarcia.dev/webdrivermanager/
		
			// This code will automatically download & select the right chromedriver version.
			WebDriverManager.chromedriver().setup();
			
			// - use headless option on Chrome if we run on AWS linux machine (non-GUI & minimal resource)
	        ChromeOptions options = new ChromeOptions();
	        
	        if ( headlessFlag.equalsIgnoreCase("TRUE")) {
	        	options.addArguments("--headless");     
	        	MyLog.info("Setup():: Running in headless chrome mode.");
	        }
	        options.addArguments("--disable-gpu");
	        options.addArguments("--disable-crash-reporter");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--no-sandbox");		// Jenkins on AWS most likely run under root
	        options.addArguments("--window-size=1400,800");  
	        driver = new ChromeDriver(options);   
			
	        // -- more options
	        // options.addArguments("--headless", "--disable-gpu", "--window-size=1024,768","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");	        
	        
	        // -- default 
			// driver = new ChromeDriver();			
			
		}

		MyLog.info("WebDriver created");
	
	}
	
	
	/**
	 * Open the url of medicare website
	 */
	@BeforeMethod
	@Parameters({"aws_host"})
	public void methodLevelSetup(String aws_host) {
		
		String medicareURL = aws_host + "/medicare/";
		
		homePage = new HomePage(driver);
		landingPage = new LandingPage(driver);
		
		homePage.setAppURL(medicareURL);
		
		MyLog.debug("appURL is : [" + medicareURL + "]");
		
	}
	
	@AfterClass (enabled=true, alwaysRun = true, description="Close the report & driver")
	public void teardown() {
		MyLog.info("Ending test.");

		try {
			  Thread.sleep(3000); // wait 5 seconds
			  driver.quit();
		} catch (InterruptedException e) {e.printStackTrace();}			

	
	}

	
	
	
	
	
}
