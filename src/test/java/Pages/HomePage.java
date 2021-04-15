package Pages;

import Framework.Core.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver = Driver.getDriver();
    static String itemPrice = "";
    static String cartSubtotal = "";

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // **************************************************************************

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchField;

    @FindBy(id = "add-to-cart-button")
    public WebElement AddToCartButton;

    @FindBy(xpath = "//a[@id='hlb-ptc-btn-native']")
    public WebElement ProceedToCheckout;

    @FindBy(xpath = "(//b[text()='Cart subtotal'])[1]/../following-sibling::span")
    public WebElement CartSubtotalElement;

    @FindBy(xpath = "//td[contains(@class,'total-price')]")
    public WebElement OrderTotal;


    // **************************************************************************

    public void navigateToMainPage() {
        driver.get("https://www.amazon.com/");
    }

    public void searchItem(String string) {
        searchField.sendKeys(string, Keys.ENTER);
    }

    public void clickOn(String buttonname) {
        buttonname = buttonname.toLowerCase().trim();

        switch (buttonname){
            case "add to cart":
                AddToCartButton.click();
                break;
            case "proceed to checkout":
                ProceedToCheckout.click();
                break;
        }
    }


    public void clickOnItem(String itemNumber) {
        itemNumber = itemNumber.replaceAll("[^\\d.]", "");      // removing all non numeric characters
        String item = "(//div[@class='a-section a-spacing-none a-spacing-top-small']//h2)["+itemNumber+"]/a";
        driver.findElement(By.xpath(item)).click();
    }


    public void assetBookPrice(String type) {
        switch (type){
            case "new":
                itemPrice = driver.findElement(By.xpath("//span[@id='newBuyBoxPrice']")).getText();
                break;
            case "used":
                itemPrice = driver.findElement(By.xpath("//span[@id='usedPrice']")).getText();
                break;
        }
    }

    public void assetCartSubtotal() {
        cartSubtotal = CartSubtotalElement.getText();
    }

    public void verifyPrice(String price) {
        price = price.toLowerCase().trim();
        cartSubtotal = CartSubtotalElement.getText();
        switch (price){
            case "cart subtotal":
                Assertions.assertEquals(itemPrice, cartSubtotal);
                break;
            case "order total":
                Assertions.assertEquals(cartSubtotal, OrderTotal.getText());
                break;
        }
    }


}