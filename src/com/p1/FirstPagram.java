package com.gspann;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class FirstPagram {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver(); // Launch Chrome browser
        driver.manage().window().maximize(); // Maximize browser window
driver.get("https://www.google.com/"); // Open Google
 
        Thread.sleep(3000); // Pause for 3 seconds (for demonstration)
 
        String title = driver.getTitle(); // Get page title
        System.out.println("title = " + title); // Print title
 
        String url = driver.getCurrentUrl(); // Get current URL
        System.out.println("current url = " + url); // Print URL
 
        driver.quit(); // Close the browser
    }
}