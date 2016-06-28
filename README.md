# Selenium

This Selenium script returns the top N prices for a specific item on Ebay. The item and the numbers of prices to be returned are passed in as config parameters in the testng.xml.

For example, if the item-name is “ipad” and the item-count is “5”, the top five prices listed on Ebay for ipad are returned by the method called returnPrices as a List of Strings. If a range is given as a price, the lower number from the range is displayed. This parsing is handled by a method called cleanupString.

Tests are also provided for both the returnPrices method as well as the cleanupString method.

Framework/technologies used: Selenium Webdriver, TestNG, Maven, Java
