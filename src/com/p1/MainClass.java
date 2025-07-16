package comseleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();		
		driver.get("https://www.facebook.com/");
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys("Rohansingh@gmail.com");
		WebElement password = driver.findElement(By.name("pass"));
		password.sendKeys("12345678");
		Thread.sleep(2000);
//		WebElement button = driver.findElement(By.tagName("button"));
//		button.click();
		WebElement forgotpass = driver.findElement(By.linkText("Forgotten password?"));
		forgotpass.click();
		WebElement email = driver.findElement(By.id("identify_email"));
		email.sendKeys("RohanSingh@gmail.com");
		WebElement clickForgot = driver.findElement(By.name("did_submit"));
		clickForgot.click();
		Thread.sleep(3000);
		
		
		driver.quit();

	}

}
