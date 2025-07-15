package com.p1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SecPro {
	public static void main(String[] args) throws InterruptedException {
		WebDriver d=new EdgeDriver();
		d.manage().window().maximize();
		d.get("https://www.google.com/");
		String title = d.getTitle();
		System.out.println("Title"+title);
		String url = d.getCurrentUrl();
		System.out.println("url"+url);
		Thread.sleep(3000);
		d.close();
	}
}

