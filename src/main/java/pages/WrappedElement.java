package pages;

import org.openqa.selenium.WebElement;
import selenium.EnhancementWebDriver;

public class WrappedElement extends AbstractPage {

    protected WebElement element;

    protected WrappedElement(EnhancementWebDriver driver, WebElement element) {
        super(driver);
    }
}
