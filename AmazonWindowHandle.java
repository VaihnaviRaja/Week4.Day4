package Week4.Day4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonWindowHandle {

	public static void main(String[] args) throws IOException, InterruptedException {
//		1.Load the URL https://www.amazon.in/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		String handle = driver.getWindowHandle();
		System.out.println(handle);
		
//			2.search as oneplus 9 pro 
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("OnePlus 9 Pro",Keys.ENTER);
		
//			3.Get the price of the first product
		String text = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[5]")).getText();
		String text1= text.replaceAll(",","");
		Double price = Double.parseDouble(text1);
		System.out.println("The price of OnePlus 9 Pro is "+price);
		
		
//			5. Click the first text link of the first image
		driver.findElement(By.xpath("(//span[contains(@class,'a-size-medium a-color-base')])[2]")).click();
		
//		4. Print the number of customer ratings for the first displayed product
		WebElement rating = driver.findElement(By.xpath("//span[text()='4.1 out of 5 stars']"));
		System.out.println("Rating for the first product is: " + rating.getAttribute("innerHTML"));
		
//			6. Take a screen shot of the product displayed
		Set<String> window= driver.getWindowHandles();
		List<String> handles=new ArrayList<String>(window);
		System.out.println(handles);
		driver.switchTo().window(handles.get(2));
		File source= driver.getScreenshotAs(OutputType.FILE);
		File Destination= new File("./Snaps/oneplus9pro.png");
		FileUtils.copyToDirectory(source,Destination);

		
//			7. Click 'Add to Cart' button
		Thread.sleep(3000);
		WebElement findElement = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(findElement).click().perform();
		
//			8. Get the cart subtotal and verify if it is correct.
		
		WebElement findElement2 = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']"));
		Thread.sleep(3000);
		String carttotal = findElement2.getAttribute("innerHTML");
		System.out.println("The Cart subtotal is "+carttotal);
	
//			9.close the browser
		driver.quit();

	}

}
