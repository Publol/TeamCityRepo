package selenium;

import enums.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class EnhancementWebDriver {

    private WebDriver webDriver;

    private EnhancementWebDriver(WebDriver driver){
        webDriver = driver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void executeJavaScript(String script, Object ...arguments){
        ((JavascriptExecutor)webDriver).executeScript(script, arguments);
    }

    public static EnhancementWebDriver initWebDiver(Browser browser) {
        return new EnhancementWebDriver(browser.getBrowser());
    }
}
