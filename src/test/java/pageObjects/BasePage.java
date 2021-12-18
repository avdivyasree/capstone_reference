package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public static String appURL;
	public void setAppURL (String url) { appURL = url; }
	public String getAppURL() {	return appURL;	}

	// Constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	// Wait
	public WebElement waitVisibility(By by) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}


	// lick Method
	public void click(By by) {
		// wrap around the click & ensure element is ready
		waitVisibility(by).click();
	}
	
	// writeText Method
	public void writeText(By by, String text) {
		waitVisibility(by).sendKeys(text);
	}
	
	// readText Method
	public String readText(By by) {
		return waitVisibility(by).getText();
	}
	
	// readValue Method
	public String readValue(By by) {
		return waitVisibility(by).getAttribute("value");
	}
	
	// pickSelection Method
	public void pickSelection(By by, String selText) {
				
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement sel = driver.findElement(by);
		Select selShowList = new Select(sel);
		selShowList.selectByVisibleText(selText);	
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public abstract BasePage sleep(int miliseconds);
	
	public void threadSleep(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
