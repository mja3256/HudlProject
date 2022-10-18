package matthew.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class loginTests {

	public static void main(String[] args) {
		//setup a web driver to use (chrome)
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Conci\\OneDrive\\Documents\\selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//navigate to company home page
		driver.get("http://www.hudl.com/");
		driver.manage().window().maximize();
		//navigate to login page
		driver.findElement(By.linkText("Log in")).click();
		
		String correctUser = "input correct email";
		String correctPass = "input correct password";
		
		//test for correct login
		System.out.println("Test Correct Login: " + correctTest(driver, correctUser, correctPass));
		//test for incorrect email
		System.out.println("Test Incorrect Email: " + incorrectTest1(driver, "incorrectEmail@gmail.com", correctPass));
		//test for incorrect password
		System.out.println("Test Incorrect Password: " + incorrectTest1(driver, correctUser, "wrongPass"));
		//test for missing email
		System.out.println("Test Missing Email: " + incorrectTest2(driver, "email", correctUser, correctPass));
		//test for missing password
		System.out.println("Test Missing Password: " + incorrectTest2(driver, "password", correctUser, correctPass));
		//test for missing both entries
		System.out.println("Test Missing Email/Password: " + incorrectTest2(driver, "both", correctUser, correctPass));	
		//test page change after login
		System.out.println("Test back/forward page naviagtion after login: " + forwardBackTest(driver, correctUser, correctPass));
		
		driver.close();

	}
	
	//test for correct login
	public static String correctTest(WebDriver driver, String user, String pass) {
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(user);
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pass);
		//check if login to home page
		driver.findElement(By.id("logIn")).click();
		
		//wait for the driver to find element on home page
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.linkText("Explore")));
		String expected = "Home - Hudl";
		String actual = driver.getTitle();
		//return to login page for next test
		driver.navigate().to("http://www.hudl.com/login");
		if(actual.equals(expected)){
			return "Pass";
		}
		else {
			return "Fail";
		}
	}
	//test for incorrect email/username or password
	public static String incorrectTest1(WebDriver driver, String user, String pass) {
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(user);
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pass);
		//check if login to home page
		driver.findElement(By.id("logIn")).click();
		
		String expected = "Log In";
		String actual = driver.getTitle();
		//waits for login error to pop up
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("/html/body/div/section/div[2]/div/form/div/div[3]/div/p/a")));
		//return to login page for next test
		driver.navigate().to("http://www.hudl.com/login");
		if(actual.equals(expected)){
			return "Pass";
		}
		else {
			return "Fail";
		}
		
	}
	
	//test for missing email or password
		public static String incorrectTest2(WebDriver driver, String missing, String user, String pass) {
			if(missing.equals("email")) {
				WebElement password = driver.findElement(By.id("password"));
				password.sendKeys(pass);
			}
			else if(missing.equals("password")) {
				WebElement email = driver.findElement(By.id("email"));
				email.sendKeys(user);
			}
			else {
				WebElement email = driver.findElement(By.id("email"));
				email.clear();
				WebElement password = driver.findElement(By.id("password"));
				password.clear();
			}
			//check if login to home page
			driver.findElement(By.id("logIn")).click();
			
			String expected = "Log In";
			String actual = driver.getTitle();
			//waits for login error to pop up
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("/html/body/div/section/div[2]/div/form/div/div[3]/div/p/a")));
			//return to login page for next test
			driver.navigate().to("http://www.hudl.com/login");
			if(actual.equals(expected)){
				return "Pass";
			}
			else {
				return "Fail";
			}			
		}
		public static String forwardBackTest(WebDriver driver, String user, String pass) {
			WebElement email = driver.findElement(By.id("email"));
			email.sendKeys(user);
			WebElement password = driver.findElement(By.id("password"));
			password.sendKeys(pass);
			//check if login to home page
			driver.findElement(By.id("logIn")).click();
			
			//wait for the driver to find element on home page
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.linkText("Explore")));
			
			driver.navigate().back();
			//wait for page to get back to main
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("logIn")));
			
			driver.navigate().forward();
			//wait for the driver to find element on home page
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.linkText("Explore")));
			String expected = "Home - Hudl";
			String actual = driver.getTitle();
			//return to login page for next test
			driver.navigate().to("http://www.hudl.com/login");
			if(actual.equals(expected)){
				return "Pass";
			}
			else {
				return "Fail";
			}
		}

}
