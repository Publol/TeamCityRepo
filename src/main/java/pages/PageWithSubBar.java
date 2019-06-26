package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.enums.EMainTopBar;
import selenium.EnhancementWebDriver;

import java.util.List;

public class PageWithSubBar extends AbstractPage {
    private final By AREA_SUB_BAR = By.id("navbarExample");

    private final By SUB_BAR_ITEM = By.xpath("./ul/li[contains(@class, 'nav-item')]/a");


    protected PageWithSubBar(EnhancementWebDriver driver) {
        super(driver);
    }

    private WebElement getMainBar(){
        return getWebDriver().findElement(AREA_SUB_BAR);
    }

    public <V extends AbstractPage> V clickSubBarButton(EMainTopBar buttonType) {
        String requiredText = buttonType.getValue();
        List<WebElement> options = getMainBar().findElements(SUB_BAR_ITEM);
        options.stream()
                .filter(webElement -> webElement.getText().contains(requiredText))
                .findFirst().get().click();
        waitForPageLoaded();

        return (V)buttonType.createInstance(driver);
    }

}
