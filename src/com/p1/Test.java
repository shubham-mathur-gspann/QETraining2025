package com.pr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class Test {
public static void main(String[] args) throws InterruptedException {
	WebDriver driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.google.com/");
		Thread.sleep(2000);
		String title=driver.getTitle();
		System.out.println("title"+title);
		String url=driver.getCurrentUrl();
		System.out.println("current url="+url);

	driver.quit();
}
}