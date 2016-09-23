package automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	static String driverPath = "C:\\appiumsoftwares\\";
	public WebDriver driver;

	private String baseURL;
	private String emailId;
	private String password;

	String loginButton = "#launch-screen .km-footer .km-button:first-child";
	String emailTextField = "#logindiv #userid";
	String passwordTextField = "#logindiv #password";
	String signinButton = "#logindiv #button-login";
	String homePageXpath = "//div[@class='km-view-title']/span[@data-role='view-title']";
	String drawerMenuXpath = "//a[@data-click='drawerMenu.displayDrawer']/span[@class='km-icon km-hamburgericon km-notext']";
	String logoutXpath = "//li[@class='menu-btn logout']/a";

	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("Send arguments - <url> <email> <password>");
			System.exit(1);
		}
		String baseURL = args[0];
		String emailId = args[1];
		String password = args[2];

		Login login = new Login(baseURL, emailId, password);

		login.setUp();
		
		try {
			login.LaunchScreen();	
		} catch (Exception e) {
			System.out.println("Couldnt run the test successfully");
		}
		login.tearDown();
		

	}

	Login(String baseURL, String emailId, String password) {
		this.baseURL = baseURL;
		this.emailId = emailId;
		this.password = password;
	}

	void setUp() {

		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		driver = new ChromeDriver();

	}

	void LaunchScreen() {
		driver.navigate().to(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		System.out.println("Launch Screen is displayed");

		WebElement loginElement = driver.findElement(By.cssSelector(loginButton));
		if (loginElement.isDisplayed()) {
			System.out.println("Login Button is present");
			loginElement.click();
			System.out.println("Clicked on Login Button");
		} else {
			System.out.println("Login Button is NOT present");
		}

		WebElement emailField = driver.findElement(By.cssSelector(emailTextField));
		if (emailField.isEnabled()) {
			System.out.println("Email text field is enabled");
			emailField.clear();
			emailField.sendKeys(emailId);
			System.out.println(emailId + " is keyed into email id text field");
		} else {
			System.out.println("Email text field is DISABLED");
		}

		WebElement passwordField = driver.findElement(By.cssSelector(passwordTextField));
		if (passwordField.isEnabled()) {
			System.out.println("Password text field is enabled");
			passwordField.clear();
			passwordField.sendKeys(password);
			System.out.println(password + " is keyed into email id text field");
		} else {
			System.out.println("Password text field is DISABLED");
		}

		WebElement loginButton = driver.findElement(By.cssSelector(signinButton));
		if (loginButton.isDisplayed()) {
			System.out.println("Login button is present");
			loginButton.click();
			System.out.println("Clicked on Login button");
		} else {
			System.out.println("Login Button isn NOT present");
		}

		WebElement homePage = driver.findElement(By.xpath(homePageXpath));

		if (homePage.getText().equals("Home Page")) {
			System.out.println("Home page is displayed");
		} else {
			System.out.println("Login failed");
		}

		try {
			Thread.sleep(3000);
			WebElement drawerMenu = driver.findElement(By.xpath(drawerMenuXpath));
			if (drawerMenu.isDisplayed()) {
				System.out.println("Drawer menu is present");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				drawerMenu.click();
				System.out.println("Clicking on Drawer menu");
			} else {
				System.out.println("Drawer menu is not present");
			}
			Thread.sleep(3000);
			WebElement Logout = driver.findElement(By.xpath(logoutXpath));
			if (Logout.isDisplayed()) {
				System.out.println("Logout button is displayed");
				Logout.click();
				System.out.println("Clicked on logout button");
			} else {
				System.out.println("Failed to click on Logout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tearDown() {

		if (driver != null) {
			System.out.println("Closing chrome browser");
			driver.quit();
		}

	}

}
