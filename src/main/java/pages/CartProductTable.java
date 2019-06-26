package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import selenium.EnhancementWebDriver;

public class CartProductTable extends AbstractPage {

    private final String TABLE_ELEMENT_TEMPLATE = "//tbody[contains(@id, 'tbodyid')]";

    private final String AREA_PRODUCT = "//div[contains(@class, 'table-responsive')]";
    private final String TABLE_ELEMENT = TABLE_ELEMENT_TEMPLATE + "/tr[%s]";
    private final String TABLE_ELEMENT_COUNT = TABLE_ELEMENT_TEMPLATE + "/tr";

    protected CartProductTable(EnhancementWebDriver driver) {
        super(driver);
        waitUntilElementIsPresents(By.xpath(AREA_PRODUCT));
        waitUntilAsyncTasksAreCompleted();
    }

    public CartProductElement getProductBy(int index) {
        try {
            return new CartProductElement(driver, getWebDriver().findElement(By.xpath(AREA_PRODUCT.concat(String.format(TABLE_ELEMENT, index)))));
        }catch (NoSuchElementException nse) {
            return null;
        }
    }

    public int getProductCount(){
        return getWebDriver().findElements(By.xpath(AREA_PRODUCT.concat(TABLE_ELEMENT_COUNT))).size();
    }


}
