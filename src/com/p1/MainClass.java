package com.p1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Firstsilenium {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		


				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				
				driver.get("https://www.google.com");
				Thread.sleep(3000);
				String title = driver.getTitle();
				String url = driver.getCurrentUrl();
				System.out.println("url :"+url);
				driver.quit();
			}
		


	}