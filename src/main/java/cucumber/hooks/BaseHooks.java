package cucumber.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import enums.Browser;
import selenium.EnhancementWebDriver;
import selenium.SelBrowser;

import java.util.Objects;

public class BaseHooks extends SelBrowser {

    @Before
    public EnhancementWebDriver setUp(){
        if (Objects.nonNull(driver)) {
            return driver;
        }
        String browser = environment.getProperty("global.default.browser");
        if (Objects.isNull(browser)) {
            browser = "chrome".toUpperCase();
        }

        Browser selBrowser;
        try {
            selBrowser = Browser.valueOf(browser);
        }catch (NullPointerException | IllegalArgumentException ex) {
            selBrowser = Browser.CHROME;
        }
        driver = EnhancementWebDriver.initWebDiver(selBrowser);
        return driver;
    }

    @After
    public void closeBrowser(){
        driver.getWebDriver().quit();
    }
}
