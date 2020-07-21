package AbleTrace.helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utility {
	
	public static void VerifyPartialText(WebDriver driver,By byLocator,String expectedText) {
		WebDriverWait wait= new WebDriverWait(driver,15);
		
		/*WebElement element=waitForWebElement(driver, byLocator);
		boolean status=wait.until(ExpectedConditions.textToBePresentInElement(element,expectedText));
		Assert.assertTrue(status);*/
		
		Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement(waitForWebElement(driver, byLocator),expectedText)));
		
	}
	
	public static void VerifyText(WebDriver driver,By byLocator,String expectedText) {
		WebDriverWait wait= new WebDriverWait(driver,15);
		Assert.assertTrue(wait.until(ExpectedConditions.textToBe(byLocator,expectedText)));
		
	}
	
	
	
	public static String getScreenShot(WebDriver driver) {
		String path = System.getProperty("user.dir") + "\\Screenshots\\Screenshot_" + getCurrentTime() + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File(path));
		} catch (IOException e) {
			System.out.println("Failed to capture the screenshot");

		}
		return path;

	}

	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("ss_mm_HH_dd_MM_yyyy");
		Date date = new Date();
		return dateFormat.format(date);

	}

	public static void selectDropdown(WebElement element,String values) {
		//WebDriverWait wait= new WebDriverWait(driver,15);
		//WebElement element=wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		Select capturedropdownvalues=new Select(element);
		List<WebElement> allvalues=capturedropdownvalues.getOptions();
		for(WebElement ele:allvalues) {
			String data=ele.getText();
			if(data.equalsIgnoreCase(values)) {
				ele.click();
				break;
			}
		}
		
	}
	
	public static void acceptAlert(WebDriver driver) {
		WebDriverWait wait= new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.alertIsPresent()).accept();
		/*Alert alert = driver.switchTo().alert();
		alert.accept();*/
		
	}
	
	public static void getPartialMatchFromCurrentURL(WebDriver driver,String expectedText)
	{
		WebDriverWait wait= new WebDriverWait(driver,15);
		try
		{
			wait.until(ExpectedConditions.urlContains(expectedText));
		}
		catch(Exception e) 
		{
			boolean value = false;
			Assert.assertFalse(value, "Please check: URL doesnt contain SuperAdmin1");
		}
	}
		
	public static boolean getPartialTextMatchFromCurrentURL(WebDriver driver,String expectedText) {
		
					WebDriverWait wait= new WebDriverWait(driver,15);	
						
				try
				{
					
					return wait.until(ExpectedConditions.urlContains(expectedText));
				}
				catch(Exception e) {
					
					return false;
					
				}
				
			}
	
	public static void dismissAlert(WebDriver driver) {
		
		WebDriverWait wait= new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
		
	}
	

	public static void verifyAlertText(WebDriver driver,String expectedText) {
		WebDriverWait wait= new WebDriverWait(driver,15);
		String alert_text_actual=wait.until(ExpectedConditions.alertIsPresent()).getText();
		Assert.assertEquals(alert_text_actual, expectedText);
	
	}
	
	public static void verifyAlertTextPartially(WebDriver driver,String expectedText) {
		WebDriverWait wait= new WebDriverWait(driver,15);
		String alert_text_actual=wait.until(ExpectedConditions.alertIsPresent()).getText();
		Assert.assertTrue(alert_text_actual.contains(expectedText));
	
		
	}
	
	public static List<WebElement> waitForMultipleWebElement(WebDriver driver, By byLocator) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		List<WebElement> allelements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
		return allelements;
		
		
	}
	
	public static List<WebElement> waitForMultipleWebElement(WebDriver driver, By byLocator,int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		List<WebElement> allelements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
		return allelements;

	}
	
	public static void selectValueFromCalendar(List<WebElement> elements,String values)
	{
		for(WebElement ele:elements) {
			String data=ele.getText();
			if(data.equalsIgnoreCase(values)) {
				ele.click();
				break;
			}
		}
		
	}
	
	
	//THis  method will handle sync issue - It will wait for the webelement and highlight the same
	public static WebElement waitForWebElement(WebDriver driver, By byLocator) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		highLightElement(driver, element);
		return element;

	}
	
		
	public static WebElement waitForWebElement(WebDriver driver, By byLocator,int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		highLightElement(driver, element);
		return element;

	}
	
	public static WebElement waitForWebElement(WebDriver driver, WebElement WE,int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(WE));
		highLightElement(driver, element);
		return element;

	}
	
	
	public static boolean verifyTitle(WebDriver driver,String title) {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			return wait.until(ExpectedConditions.titleIs(title));
		}
		catch(Exception e) {
			
			return false;
			
		}
	}
	
	public static void verifyContainsTitle(WebDriver driver,String title) {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Assert.assertTrue(wait.until(ExpectedConditions.titleContains(title)));
	}
	
	public static void verifyURL(WebDriver driver,String url) {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(url)));
	
	}
	
	public static void verifyURLContians(WebDriver driver,String url) {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Assert.assertTrue(wait.until(ExpectedConditions.urlContains(url)));
	
	}
	
	//instead of thread sleep - use below method
	public static void wait(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			
		}
		
	}
		
	
	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

	}

	

}
