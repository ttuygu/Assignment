package StepDefinitions;
import Pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Amazon_StepDefs {

    HomePage home = new HomePage();

    @Given("user is on landing page")
    public void user_is_on_landing_page() {
        home.navigateToMainPage();
    }

    @When("user search for {string}")
    public void user_search_for(String string) {
        home.searchItem(string);
    }

    @When("^user clicks on (.+) item in the listed results$")
    public void user_clicks_on_1st_item_in_the_listed_results(String itemNumber)  {
        home.clickOnItem(itemNumber);
    }

    @When("^user assets (new|used) book price$")
    public void user_asserts_book_price(String type)  {
        home.assetBookPrice(type);
    }


    @When("user clicks on {string} button")
    public void user_clicks_on_button(String buttonname) {
        home.clickOn(buttonname);
    }

    @When("user assets cart subtotal")
    public void user_assets_cart_subtotal() {
        home.assetCartSubtotal();
    }

    @Then("^verify (order total|cart subtotal) amount$")
    public void verify_amount(String price) {
        home.verifyPrice(price);
    }

}