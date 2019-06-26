package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.enums.EMainTopBar;
import selenium.EnhancementWebDriver;

import java.util.List;

public class MainPageLogIn extends PageWithSubBar {

    private final By AREA_LOG_IN = By.xpath("//div[contains(@id, 'logInModal')]//div[contains(@class, 'modal-content')]");

    private final By FIELD_USR_NAME_INPUT = By.id("loginusername");
    private final By FIELD_USR_PASSWORD_INPUT = By.id("loginpassword");
    private final By BTN_LOG_IN = By.xpath("//button[contains(@class, 'btn btn-primary')]");

    public MainPageLogIn(EnhancementWebDriver eDriver) {
        super(eDriver);
        waitUntilElementIsPresents(AREA_LOG_IN);
        waitUntilAsyncTasksAreCompleted();
    }

    public WebElement getLoginElement(){
        return getWebDriver().findElement(AREA_LOG_IN);
    }


    public void enterUsername(String userName) {
        WebElement element = getLoginElement().findElement(FIELD_USR_NAME_INPUT);
        element.sendKeys(userName);
        waitUntilAsyncTasksAreCompleted();
    }

    public void enterPassword(String password) {
        WebElement element = getLoginElement().findElement(FIELD_USR_PASSWORD_INPUT);
        element.sendKeys(password);
        waitUntilAsyncTasksAreCompleted();
    }

    public <V extends AbstractPage> V clickLogInButton(){
        List<WebElement> buttons = getLoginElement().findElements(BTN_LOG_IN);
        if (buttons.size() > 1) {
            buttons.stream()
                    .filter(button->button.getText().equals("Log in"))
                    .findFirst().get().click();
        } else {
            buttons.get(0).click();
        }
        waitForPageLoaded();
        return (V)EMainTopBar.HOME.createInstance(driver);
    }




}
