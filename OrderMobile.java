package servicenow;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class OrderMobile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver();
		ChromeOptions option = new ChromeOptions();
	    option.addArguments("--disable-notificatons");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    driver.get("https://dev242606.service-now.com/");
	    driver.findElement(By.id("user_name")).sendKeys("admin");
	    driver.findElement(By.id("user_password")).sendKeys("v^S8PbUx1J^k");
	    driver.findElement(By.id("sysverb_login")).click();
	    
	    //Click-All Enter Service catalog in filter navigator and press enter or Click the ServiceCatalog
	    Shadow shadow1=new Shadow(driver);
	    shadow1.setImplicitWait(10);
	    shadow1.findElementByXPath("//div[text()='All']").click();
	    
	    shadow1.findElementByXPath("//span[text()='Service Catalog']").click();
	    
	    //Click on  mobiles
	    Shadow shadow2=new Shadow(driver);
	    WebElement element = shadow2.findElementByXPath("//iframe[@id='gsft_main']");
	    driver.switchTo().frame(element);
	    driver.findElement(By.xpath("//table[@class='drag_section_header']/tbody/tr/td/a[text()='Mobiles']")).click();
	    //driver.findElement(By.xpath("//a[contains(text(),'mobi')]")).click(); 
	    
	    //Select Apple iphone13pro
	    driver.findElement(By.xpath("//strong[text()='Apple iPhone 13']")).click();
	    
	    //Choose yes option in lost or broken iPhone
	    driver.findElement(By.xpath("//label[text()='Yes']")).click();
	    
	    //7. Enter phonenumber as 99 in original phonenumber field
	    driver.findElement(By.xpath("(//input[contains(@class,'cat_item_option')])[4]")).sendKeys("99");
	    
	    //8. Select Unlimited from the dropdown in Monthly data allowance
	    WebElement month = driver.findElement(By.xpath("//select[contains(@class,'form-control')]"));
	    Select allowance=new Select(month);
	    allowance.selectByValue("Unlimited");
	    
	    //9. Update color field to SierraBlue and storage field to 512GB
	    driver.findElement(By.xpath("//label[text()='Blue']")).click();
	    driver.findElement(By.xpath("//label[text()='512 GB [add $300.00]']")).click();
	    
	    //10. Click on Order now option
	    driver.findElement(By.xpath("//button[text()='Order Now']")).click();
	    
	    //11. Verify order is placed and copy the request number
	    String text = driver.findElement(By.xpath("//dt[contains(text(),'Order Placed')]")).getText();
	    System.out.println(text);
	    
	    if(text.contains("Order Placed")) {
	    	System.out.println("Verified");
	    }else {
	    	System.out.println("Not Verified");
	    	
	    String reqNo = driver.findElement(By.xpath("//a[@id='requesturl']")).getText();
	    System.out.println("Request No is: "+reqNo);
	    }
	    
	    File src = driver.getScreenshotAs(OutputType.FILE);
	    File dest=new File("./snap/img.png");
	    FileUtils.copyFile(src, dest);    
	    
	}

}
