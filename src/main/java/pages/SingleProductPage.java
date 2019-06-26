package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import selenium.EnhancementWebDriver;

public class SingleProductPage extends AbstractPage {

    private final By BTN_ADD_TO_CARD = By.xpath("//a[contains(@class, 'btn btn-success btn-lg')]");
    private final By MAIN_CONTENT = By.xpath("//div[contains(@class, 'product-content product-wrap clearfix product-deatil')]");

    public SingleProductPage(EnhancementWebDriver driver) {
        super(driver);
        waitUntilElementIsPresents(BTN_ADD_TO_CARD);
        waitUntilAsyncTasksAreCompleted();
    }

    public void clickOnAddToCardBtn(){
        waitUntilElementIsPresents(BTN_ADD_TO_CARD).click();
    }

    public boolean isMainContentPresented(){
        return getWebDriver().findElement(MAIN_CONTENT).isDisplayed();
    }

    public String waitForAlertTextAndAccept(){
        int count = 0;
        do {
            try {
                Alert alert = getWebDriver().switchTo().alert();
                String text = alert.getText();
                alert.accept();
                return text;
            } catch (Exception ex) {
                count++;
                try {
                    Thread.sleep(500);
                } catch (Exception ex2) {

                }

            }
        }while (count < 10);
        return null;
    }


}
