

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DisplayNPricesForItemSpecified {
	
	public static List<String> returnPrices(String itemName, int itemCount){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.ebay.com/");
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Search...']")));
		driver.findElement(By.cssSelector("input[placeholder='Search...']")).sendKeys(itemName);
		driver.findElement(By.cssSelector("input[value='Search']")).click();
		
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='ResultSetItems']/ul/li/ul[1]/li[1]/span")));
		int totalPricesDisplayed = driver.findElements(By.xpath(".//*[@id='ResultSetItems']/ul/li/ul[1]/li[1]/span")).size();
		List<WebElement> elementList = driver.findElements(By.xpath(".//*[@id='ResultSetItems']/ul/li/ul[1]/li[1]/span"));
		List<String> itemPriceList = new ArrayList<String>();
		//If item count is higher than number of results returned, we set item count equal to number of total results and
		//return prices of all of the results returned		
		if(totalPricesDisplayed < itemCount) itemCount = totalPricesDisplayed;
		
		for (int i = 0; i < itemCount; i++) {
			itemPriceList.add(cleanupString(elementList.get(i).getText()));
			System.out.println(itemPriceList.get(i));
		}
		
		driver.close();
		return itemPriceList;
	}
	
	//Removes $ signs and additional information and only returns the first price from the input string.
	public static String cleanupString(String str){
		StringBuilder builder = new StringBuilder();
		int dotOccurred = 0;
		boolean dollarOccurred = false;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '.'){
				dotOccurred = 1;
				builder.append(str.charAt(i));
			}
			else if(str.charAt(i) == '$') dollarOccurred = true;
			else if(dollarOccurred && Character.isDigit(str.charAt(i))){
				builder.append(str.charAt(i));
			}
			else if(dollarOccurred && Character.isLetter(str.charAt(i))){
				break;
			}
		}
		return builder.toString();
	}

}
