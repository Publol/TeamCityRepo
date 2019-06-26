package pages.enums;

import org.openqa.selenium.By;
import pages.AbstractPage;
import pages.CartPage;
import pages.MainPage;
import pages.MainPageLogIn;
import selenium.EnhancementWebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EMainTopBar {

    HOME(       "Home", MainPage.class),
    CONTACT(    "Contact", null),
    ABOUT_US(   "About us", null),
    CART(       "Cart", CartPage.class),
    LOG_IN(     "Log in", MainPageLogIn.class),
    SIGH_IN(    "Sigh in", null);


    String value;
    Class<? extends AbstractPage> clz;

    EMainTopBar(String value, Class<? extends AbstractPage> clz){
        this.value = value;
        this.clz = clz;
    }

    public String getValue() {
        return value;
    }

    public Class<? extends AbstractPage> getClz() {
        return clz;
    }

    public AbstractPage createInstance(EnhancementWebDriver driver){
        Constructor<? extends AbstractPage> constructor = null;
        try {
            constructor = this.getClz().getConstructor(EnhancementWebDriver.class);
            constructor.setAccessible(true);
            try {
                return constructor.newInstance(driver);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
