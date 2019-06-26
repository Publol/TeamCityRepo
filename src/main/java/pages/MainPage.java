package pages;

import enums.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import selenium.EnhancementWebDriver;

public class MainPage extends PageWithSubBar {
    private static final String URL = Environment.INSTANCE.getProperty("global.default.url");

    private final By MAIN_CONTENT = By.className("navbar-brand");

    public static MainPage launch(EnhancementWebDriver eDriver){
        eDriver.getWebDriver().get(URL);
        return new MainPage(eDriver);
    }

    public MainPage(EnhancementWebDriver eDriver){
        super(eDriver);
        waitUntilElementIsPresents(MAIN_CONTENT);
        waitUntilAsyncTasksAreCompleted();
    }

    public MainPageProductTable getProductTable(){
        return new MainPageProductTable(driver);
    }

}
