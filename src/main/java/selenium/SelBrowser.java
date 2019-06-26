package selenium;

import cucumber.api.java.After;

public class SelBrowser extends SelCore {

    public void maximizeWindow(){
        driver.getWebDriver().manage().window().maximize();
    }


}
