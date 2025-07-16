// package com.gspann;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.*;
// public class firstProgram {
// 	public static void main(String[] args) throws InterruptedException {
// 		WebDriver driver = new ChromeDriver();
// 		driver.get("https://www.google.com/");
// 		driver.manage().window().maximize();
// 		Thread.sleep(5000);
// 	    String title = driver.getTitle();
// 	    System.out.println(title);
// 	     String url = driver.getCurrentUrl();
// 	     System.out.println(url);
// 		driver.quit();
// 	}

// }

package com.gspann;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class locators {

	public static void main(String[] args) throws InterruptedException {
	WebDriver driver = new ChromeDriver();
	driver.get("https://www.facebook.com/");
	driver.manage().window().maximize();
	WebElement userName =driver.findElement(By.id("email")); //by id
	userName.sendKeys("sakshiAGGARWAL2123");
	WebElement password =driver.findElement(By.name("pass"));// by name
	password.sendKeys("sakshi@123");
	WebElement loginBtn =driver.findElement(By.tagName("button")); //by tagName
	loginBtn.click();
	
	WebElement forgottenLink =driver.findElement(By.linkText("Forgotten password?"));// by linkText
	forgottenLink.click();
	WebElement partialForgotten =driver.findElement(By.partialLinkText("Forgotten")); //by partialLinkText
	partialForgotten.click();
	driver.quit();	
	

	}

}

