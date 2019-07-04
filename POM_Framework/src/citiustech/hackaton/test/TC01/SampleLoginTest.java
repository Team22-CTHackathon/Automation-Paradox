package citiustech.hackaton.test.TC01;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

import citiustech.hackaton.utils.PropertyFileReader;

public class SampleLoginTest {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public PropertyFileReader propertyFileReader;
	public String workingDir = System.getProperty("user.dir") + "\\src\\citiustech\\hackaton";
	Random random = new Random();

	@Test
	public void login() throws Exception {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		propertyFileReader = new PropertyFileReader(workingDir + "\\resources\\locator.properties");
		try {
			driver.findElement(propertyFileReader.getLocator("input_name")).sendKeys("citiustech");
		} catch (NoSuchElementException e) {
			Reporter.log("FAIL::: Unable to find element: input_name with locator: "+propertyFileReader.getLocator("input_name"), true);
		} 
		
		try {
			Select genderDD = new Select(driver.findElement(propertyFileReader.getLocator("select_gender")));
			genderDD.selectByVisibleText("Male");
		} catch (NoSuchElementException e) {
			Reporter.log("FAIL::: Unable to find element: select_gender with locator: "+propertyFileReader.getLocator("select_gender"), true);
		} 
		
		try {
			driver.findElement(propertyFileReader.getLocator("input_project")).sendKeys("Sample Project");
		} catch (NoSuchElementException e) {
			Reporter.log("FAIL::: Unable to find element: input_project with locator: "+propertyFileReader.getLocator("input_project"), true);
		} 
		
		try {
			driver.findElement(propertyFileReader.getLocator("input_email")).sendKeys("test_"+random.nextInt(9999)+"@gmail.com");
		} catch (NoSuchElementException e) {
			Reporter.log("FAIL::: Unable to find element: input_email with locator: "+propertyFileReader.getLocator("input_email"), true);
		} 
		
		try {
			driver.findElement(propertyFileReader.getLocator("input_mob")).sendKeys("99999999999");
		} catch (NoSuchElementException e) {
			Reporter.log("FAIL::: Unable to find element: input_mob with locator: "+propertyFileReader.getLocator("input_mob"), true);
		} 
		
		try {
			driver.findElement(propertyFileReader.getLocator("input_password")).sendKeys("testpwd");
		} catch (NoSuchElementException e) {
			Reporter.log("FAIL::: Unable to find element: input_password with locator: "+propertyFileReader.getLocator("input_password"), true);
		} 
		
		try {
			driver.findElement(propertyFileReader.getLocator("input_cpassword")).sendKeys("testpwd");
		} catch (NoSuchElementException e) {
			Reporter.log("FAIL::: Unable to find element: input_cpassword with locator: "+propertyFileReader.getLocator("input_cpassword"), true);
		} 
		
		try {
			driver.findElement(propertyFileReader.getLocator("input_signup")).click();
		} catch (NoSuchElementException e) {
			Reporter.log("FAIL::: Unable to find element: input_signup with locator: "+propertyFileReader.getLocator("input_signup"), true);
		}
		
		Thread.sleep(1000);
		Boolean temp = false;
		try {
			driver.findElement(propertyFileReader.getLocator("input_signup")).isDisplayed();
		} catch (NoSuchElementException e) {
			temp = true;
		}
		Assert.assertTrue(temp);
		Reporter.log("PASS::: User logged-in successfully...!!");
	
	}

	@BeforeMethod
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", workingDir + "\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://localhost/");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		
		driver.quit();
	}

}
