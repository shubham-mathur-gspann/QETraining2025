package Day1;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Day1 {

	public static void main(String[] args)throws InterruptedException {
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		Thread.sleep(3000);
		String title=driver.getTitle();
		System.out.println("title"+ title);
		String url=driver.getCurrentUrl();
		System.out.println("current url"+ url);
		driver.quit();


	}

}
