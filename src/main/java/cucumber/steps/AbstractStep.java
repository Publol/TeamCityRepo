package cucumber.steps;

import cucumber.hooks.BaseHooks;
import selenium.EnhancementWebDriver;

public abstract class AbstractStep {

    private BaseHooks hooks;
    protected EnhancementWebDriver eDriver;

    protected AbstractStep(BaseHooks browser){
        this.hooks = browser;
        this.eDriver = this.hooks.setUp();
        this.hooks.maximizeWindow();
    }

    protected void closeBrowser(){
        hooks.closeBrowser();
        eDriver = null;
        hooks = null;
    }

}
