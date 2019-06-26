package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.EnhancementWebDriver;

public class CartPage extends AbstractPage {

    private final By BTN_PLACE_ORDER = By.xpath("//button[contains(@class, 'btn btn-success')]");
    private final By PRODUCT_TABLE = By.xpath("//table[contains(@class, 'table table-bordered table-hover table-striped')]");

    public CartPage(EnhancementWebDriver driver) {
        super(driver);
        waitUntilElementIsPresents(BTN_PLACE_ORDER);
        waitUntilAsyncTasksAreCompleted();
    }

    public CartProductTable getProductTable(){
        return new CartProductTable(driver);
    }


}
