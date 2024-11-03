package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
    private final WebDriver webDriver;

    private final By loginButtonLocator = By.xpath(".//a[@class='Auth_link__1fOlj']");

    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginButton(){
        WebElement loginButton = webDriver.findElement(loginButtonLocator);
        loginButton.click();
    }
}
