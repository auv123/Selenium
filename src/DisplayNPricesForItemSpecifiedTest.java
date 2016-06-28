
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class DisplayNPricesForItemSpecifiedTest {
	
	@Parameters({ "item-name", "item-count" })
	@Test
	public void returnPricesTest(String itemName, int itemCount) throws InterruptedException{
		List<String> itemPriceListActual = DisplayNPricesForItemSpecified.returnPrices(itemName, itemCount);
		Assert.assertNotNull(itemPriceListActual);
		Assert.assertEquals(itemPriceListActual.size(), itemCount);					
	}
	
	@Test
	public void cleanupStringTest() throws InterruptedException{
		String result = DisplayNPricesForItemSpecified.cleanupString("$450.99");
		Assert.assertNotNull(result);
		Assert.assertEquals(result, "450.99");					
	}
	
	@Test
	public void cleanupStringRangeTest() throws InterruptedException{
		String result = DisplayNPricesForItemSpecified.cleanupString("$564.99 to $605.99");
		Assert.assertNotNull(result);
		Assert.assertEquals(result, "564.99");					
	}

}
