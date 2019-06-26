package pages;

import env.SystemConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.EnhancementWebDriver;

import java.util.concurrent.TimeUnit;

public class AbstractPage {

    private Logger logger = Logger.getLogger(AbstractPage.class);

    protected EnhancementWebDriver driver;


    protected AbstractPage(EnhancementWebDriver driver) {
        this.driver = driver;
        waitForPageLoaded();
    }

    protected AbstractPage(EnhancementWebDriver driver, By rootElement) {
        this(driver);
        waitUntilElementIsPresents(rootElement);
    }

    protected void waitForPageLoaded() {
        new WebDriverWait(getWebDriver(), Integer.valueOf(SystemConstants.PAGE_LOAD_TIMEOUT))
                .pollingEvery(2, TimeUnit.SECONDS)
                .until((ExpectedCondition<Boolean>) wd -> isComplete((JavascriptExecutor) wd));

    }

    protected WebElement waitUntilElementIsPresents(By by){
        return waitUntilElementIsPresents(by, Integer.valueOf(SystemConstants.PAGE_LOAD_TIMEOUT));
    }

    protected WebElement waitUntilElementIsPresents(WebElement parentElement, By by){
        return waitUntilElementIsPresents(parentElement, by, Integer.valueOf(SystemConstants.PAGE_LOAD_TIMEOUT));
    }

    protected WebElement waitUntilElementIsPresents(WebElement parentElement, By by, int timeout){
        logger.info("Wait until " + by.toString() + " element is presents for " + timeout + " - seconds...");
        try {
            parentElement.getText();
            new WebDriverWait(getWebDriver(), timeout)
                    .withMessage("Trying to locate the " + by.toString() + " - element")
                    .until((driver)->{
                        WebElement element = parentElement.findElement(by);
                        return element.isDisplayed() && element.isEnabled();
                    });
        } catch (Exception ex) {
            logger.info(parentElement.toString() + " - element is not presented anymore");
        }
        return parentElement.findElement(by);
    }

    protected WebElement waitUntilElementIsPresents(By by, int timeout){
        logger.info("Wait until " + by.toString() + " element is presents for " + timeout + " - seconds...");
        new WebDriverWait(getWebDriver(), timeout)
                .withMessage("Trying to locate the " + by.toString() + " - element")
                .until((driver)->{
                    WebElement element = driver.findElement(by);
                    return element.isDisplayed() && element.isEnabled();
                });
        return getWebDriver().findElement(by);
    }

    protected void waitUntilAsyncTasksAreCompleted(){
        new WebDriverWait(getWebDriver(), Integer.valueOf(Integer.valueOf(SystemConstants.PAGE_LOAD_TIMEOUT)))
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(JavascriptException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return (boolean) ((JavascriptExecutor) driver).executeScript(
                                "if (typeof jQuery !== 'undefined') {return jQuery.active == 0;} return true;");
                    }
                });
    }

    protected boolean isComplete(JavascriptExecutor wd) {
        return wd.executeScript("return document.readyState").equals("complete");
    }

    protected WebDriver getWebDriver(){
        return driver.getWebDriver();
    }

    public String getTitle(){
        return getWebDriver().getTitle();
    }


}
