package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class sai {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver  driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		Thread.sleep(3000);
	    String title=driver.getTitle();
	    System.out.println("title="+title);
	    String url=driver.getCurrentUrl();
	    System.out.println("current url="+url);
		driver.quit();

	}

}
