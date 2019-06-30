package cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.hooks.BaseHooks;
import enums.Environment;
import org.openqa.selenium.NotFoundException;
import pages.MainPage;
import pages.MainPageLogIn;
import pages.enums.EMainTopBar;

import java.util.Objects;

public class Background extends AbstractStep {

    public Background(BaseHooks selBrowser){
        super(selBrowser);
    }

    @Given("^The \\\"([^\\\"]*)\\\" should be opened$")
    public void the_url_should_be_opened(String url) throws Throwable {
        if (Objects.isNull(eDriver.getWebDriver())){
            throw new NotFoundException("Driver is null");
        }
        String requiredTitle = "STORE";
        MainPage mainPage = MainPage.launch(eDriver);
        assert eDriver.getWebDriver().getCurrentUrl().equals(url);
        assert mainPage.getTitle().equals(requiredTitle);
    }

    @And("^I wish to log into the system$")
    public void iWishToLoggInTheSystem() {
        String userName = Environment.INSTANCE.getProperty("global.username");
        String password = Environment.INSTANCE.getProperty("global.password");

        MainPage mainPage = new MainPage(eDriver);
        MainPageLogIn logInPage = mainPage.clickSubBarButton(EMainTopBar.LOG_IN);
        logInPage.enterUsername(userName);
        logInPage.enterPassword(password);
        logInPage.clickLogInButton();
    }
}
