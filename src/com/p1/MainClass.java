package com.p1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainClass {

	public static void main(String[] args) throws InterruptedException
	{
		//WebDriver driver=new EdgeDriver();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		String  title=driver.getTitle();
		System.out.println("title="+title);
		String url=driver.getCurrentUrl();
		System.out.println("Url="+url);
		driver.quit();
		
	}
	
}


