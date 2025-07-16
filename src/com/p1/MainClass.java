import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstProgram {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver=new ChromeDriver();  //launch browser
		driver.get("https://www.google.com/");  //open URL
		driver.manage().window().maximize();
		
		Thread.sleep(3000);
		
		String t=driver.getTitle();
		System.out.println("Title==="+t);
		
		String url=driver.getCurrentUrl();
		System.out.println("current URL"+ url);
		
		driver.close();

	}

}
