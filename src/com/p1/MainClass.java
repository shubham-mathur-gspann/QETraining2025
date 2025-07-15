package com.p1;

public class MainClass {

	public static void main(String[] args) {

		System.out.println("Priyanshu Pandey");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com/");
		Thread.sleep(3000);
		String title = driver.getTitle();
		System.out.println("Title - "+title);
		String url = driver.getCurrentUrl();
		System.out.println("URL - "+url);
		driver.quit();

	} 
		

}
