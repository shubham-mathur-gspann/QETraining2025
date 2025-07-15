package seleniumscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class firstprogram {
public static void main(String[] args) throws InterruptedException {
	
	//1.To launch the browser
	WebDriver driver=new ChromeDriver();
	//2.To maximize the window
	  driver.manage().window().maximize();

	driver.get("https://www.google.com/");
	
	Thread.sleep(2000);
	
	String s="Google";
	String title=driver.getTitle();
	if (s.equals(title)) {
		System.out.println("title is:"+title);
	} else {
		System.out.println("title not visible");
	}
	
 String url	=driver.getCurrentUrl();
System.out.println(url);
	driver.close();
}
}
