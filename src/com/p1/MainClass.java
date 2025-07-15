package com.p1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.wikipedia.org/");
		Thread.sleep(3000);
		String title=driver.getTitle();
		System.out.println("Title = "+title);
		String url=driver.getCurrentUrl();
		System.out.println("Current url = "+url);
		driver.quit();

	}

}