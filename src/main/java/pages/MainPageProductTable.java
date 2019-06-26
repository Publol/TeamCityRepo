package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.EnhancementWebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class MainPageProductTable extends AbstractPage {
    private Logger logger = Logger.getLogger(MainPageProductTable.class);

    private final String AREA_PRODUCT_TABLE = "//div[contains(@id, 'tbodyid')]//div[contains(@class, 'card-block')]";
    private final String LI_PRODUCTS = "//a[contains(@class, 'hrefch')]";

    protected MainPageProductTable(EnhancementWebDriver driver) {
        super(driver);
        waitUntilElementIsPresents(By.xpath(AREA_PRODUCT_TABLE));
        waitUntilAsyncTasksAreCompleted();
    }

    private WebElement getProductTable(){
        return getWebDriver().findElement(By.xpath(AREA_PRODUCT_TABLE));
    }

    private List<WebElement> getProducts(){
        return getProductTable().findElements(By.xpath(LI_PRODUCTS));
    }

    private WebElement getProductBy(int index) {
        return getProductTable().findElement(By.xpath(LI_PRODUCTS + "[ " + index + "]"));
    }

    public String getProductTextByIndex(int index) {
        return getProductBy(index).getText();
    }

    public SingleProductPage clickOnProductByIndex(int index) {
        WebElement product = waitUntilElementIsPresents(By.xpath("(" + AREA_PRODUCT_TABLE + ")[" + index + "]/../a"));
        logger.info("Clicking on product by" + index + " index");
        product.click();
        return new SingleProductPage(driver);
    }

}
