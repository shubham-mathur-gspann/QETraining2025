package com.p1;
import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.edge.EdgeDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
 

public class MainClass {

	public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		Thread.sleep(3000);
		String s = driver.getTitle();
		System.out.println("title = "+s);
		String url =driver.getCurrentUrl();
		System.out.println("URL = " + url);
		driver.quit();


	}

		

}
