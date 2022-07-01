package testCases;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Webtable_OpenCart_Dynamic_Pagination2 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost/opencart/upload/admin/");
		
		driver.manage().window().maximize();
		
		//Login
		
		WebElement username=driver.findElement(By.id("input-username"));
		username.clear();
		username.sendKeys("admin");  //demo
		
		WebElement password=driver.findElement(By.id("input-password"));
		password.clear();
		password.sendKeys("admin"); //demo
		
		driver.findElement(By.xpath("//button[text()=' Login']")).click();
		
		//Customers-->customers
		driver.findElement(By.xpath("//a[@class='parent collapsed'][normalize-space()='Customers']")).click();
		driver.findElement(By.xpath("//ul[@class='collapse in']//a[contains(text(),'Customers')]")).click();
		
		
		//Total Pages
		String text=driver.findElement(By.xpath("//div[@class='col-sm-6 text-right']")).getText();
		System.out.println(text); //Showing 1 to 20 of 23 (2 Pages)
		
		int total_pages = Integer.valueOf(text.substring(text.indexOf("(")+1, text.indexOf("Pages")-1));
        System.out.println("Total Pages:"+total_pages); //2
		
		for(int p=1;p<=total_pages;p++)
		 {					
			WebElement active_Page=driver.findElement(By.xpath("//ul[@class='pagination']//li//*[text()="+p+"]"));
					
			System.out.println("active page:"+active_Page.getText());
			active_Page.click();
								
			int rows=driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr")).size();
			System.out.println("Total rows in active Page:"+rows);
			
				//to read all the table data
				for(int r=1;r<=rows;r++)
				{
					String customerName=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+r+"]/td[2]")).getText();
					String customerEmail=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+r+"]/td[3]")).getText();
					String status=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr["+r+"]/td[5]")).getText();
				
					if(status.equals("Enabled")) // printing only Enabled customers
					{
					System.out.println(customerName+"  "+customerEmail+"  "+status);
					}
				}
		}
		
		driver.quit();
	}

}