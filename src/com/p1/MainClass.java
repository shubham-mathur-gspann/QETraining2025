package com.pr;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	  public static void main(String args[]) throws InterruptedException
	  {
		  WebDriver driver=new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get("https://www.youtube.com/?gl=IN");
		  Thread.sleep(5000);
		  
		  System.out.println(driver.getTitle());
		  System.out.println(driver.getCurrentUrl());
		  driver.quit();
		  
		  
	  }

}
