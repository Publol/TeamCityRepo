package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.EnhancementWebDriver;

public class CartProductElement extends WrappedElement {



    protected CartProductElement(EnhancementWebDriver driver, WebElement element) {
        super(driver, element);
        this.element = element;
    }

    public void deleteProduct() {
        element.findElement(By.xpath("//td[4]/a")).click();
        waitForPageLoaded();
        waitUntilAsyncTasksAreCompleted();

    }


}
