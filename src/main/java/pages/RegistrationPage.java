package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver webDriver;

    //Поле Имя
    private final By nameFieldLocator = By.xpath(".//label[text()='Имя']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    //Поле Email
    private final By emailFieldLocator = By.xpath(".//label[text()='Email']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    //Поле пароль
    private final By passwordFieldLocator = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and@type = 'password']");
    //Кнопка Зарегистрироваться
    private final By registerButtonLocator = By.xpath(".//button[text()='Зарегистрироваться']");
    //Кнопка Войти
    private final By loginButtonLocator = By.xpath(".//a[text()='Войти']");

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Регистрация пользователя")
    public void inputLoginInfoUser(String name, String email,String password) {
        webDriver.findElement(nameFieldLocator).sendKeys(name);
        webDriver.findElement(emailFieldLocator).sendKeys(email);
        webDriver.findElement(passwordFieldLocator).sendKeys(password);
        webDriver.findElement(registerButtonLocator).click();
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginButton(){
        webDriver.findElement(loginButtonLocator).click();
    }
}
