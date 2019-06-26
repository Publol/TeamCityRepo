package cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.hooks.BaseHooks;
import junit.AssertWrapper;
import pages.*;
import pages.enums.EMainTopBar;
import selenium.EnhancementWebDriver;
import selenium.SelBrowser;

import java.util.Objects;

public class StepsUT extends AbstractStep{

    public StepsUT(BaseHooks baseHooks){
        super(baseHooks);
    }

    @Given("^User is on home page$")
    public void userIsOnHomePage() {
        MainPage mainPage = new MainPage(eDriver);

    }

    @Given("^User should select product by \\((\\d+)\\) index and click on it$")
    public void user_should_select_product_by_index_and_click_on_it(int index) throws Throwable {
        MainPage mainPage = new MainPage(eDriver);
        SingleProductPage singleProductPage = mainPage.getProductTable().clickOnProductByIndex(index);
        assert singleProductPage.isMainContentPresented();
    }

    @When("^User click on 'Add to cart' button$")
    public void userClickOnAddToCartButton() {
        SingleProductPage singleProductPage = new SingleProductPage(eDriver);
        singleProductPage.clickOnAddToCardBtn();
        String alertText = singleProductPage.waitForAlertTextAndAccept();

        AssertWrapper.assertEquals("Product was successfully added.", alertText, "Product added.");
        assert alertText.equals("Product added.");

    }

    @Then("^Product should be added to cart$")
    public void productShouldBeAddedToCart() {
        MainPage mainPage = new MainPage(eDriver);
        CartPage cartPage = mainPage.clickSubBarButton(EMainTopBar.CART);
        CartProductElement productElement = cartPage.getProductTable().getProductBy(1);

        assert Objects.nonNull(productElement);
    }

    @Then("^User can delete this product$")
    public void userCanDeleteThisProduct() {
        MainPage mainPage = new MainPage(eDriver);
        CartPage cartPage = mainPage.clickSubBarButton(EMainTopBar.CART);

        CartProductTable productTable = cartPage.getProductTable();
        CartProductElement productElement = productTable.getProductBy(1);

        AssertWrapper.assertTrueThrowIfFailed("User have 1 added product", productTable.getProductCount() == 1);
        productElement.deleteProduct();

        AssertWrapper.assertEquals("Product should be deleted successfully", productTable.getProductCount(), 0);
    }
}
