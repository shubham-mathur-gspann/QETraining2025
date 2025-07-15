package com.p1;

public class MainClass {

	public static void main(String[] args) {

<<<<<<< HEAD
		System.out.println("Priyanshu Pandey");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.com/");
		Thread.sleep(3000);
		String title = driver.getTitle();
		System.out.println("Title - "+title);
		String url = driver.getCurrentUrl();
		System.out.println("URL - "+url);
		driver.quit();
=======
		System.out.println("Shubham Mathur has changed this");
>>>>>>> a67c4e2b4c9dec27c0649b2c5cfc50b192e73555

	} 
		

}
