package com.sel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
	
	public static void main(String... a1) throws InterruptedException
	{
		//WebDriver wd=new ChromeDriver();
		WebDriver wd=new FirefoxDriver();
		wd.manage().window().maximize();
		wd.get("https://www.google.com/");
		Thread.sleep(3000);
		String title=wd.getTitle();
		System.out.println(title+=" title");
		String url=wd.getCurrentUrl();
		System.out.println(url+=" Url");
		wd.quit();
	}

}

