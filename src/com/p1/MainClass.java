package com.gspann;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
public class firstProgram {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	    String title = driver.getTitle();
	    System.out.println(title);
	     String url = driver.getCurrentUrl();
	     System.out.println(url);
		driver.quit();
	}

}
