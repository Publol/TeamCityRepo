package selenium;

import enums.Environment;

public abstract class SelCore {

    protected final Environment environment = Environment.INSTANCE;
    protected EnhancementWebDriver driver;

}
