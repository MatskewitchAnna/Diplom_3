package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver webDriver;

    //Поле Email
    private final By emailFieldLocator = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and@type = 'text']");
    //Поле пароль
    private final By passwordFieldLocator = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and@type = 'password']");
    //Кнопка Войти
    private final By loginButtonLocator = By.xpath(".//button[text()='Войти']");
    //Кнопка Зарегистрироваться
    private final By registerButtonLocator = By.xpath(".//a[text()='Зарегистрироваться']");
    //Кнопка Восстановить пароль
    private final By recoveryPasswordButtonLocator = By.xpath(".//a[text()='Восстановить пароль']");
    //Сообщение об ошибке
    private final By errorMessageLocator = By.xpath(".//p[text()='Некорректный пароль']");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Заполнение полей авторизации")
    public void login(String email, String password) {
        new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable (emailFieldLocator));
        webDriver.findElement(emailFieldLocator).sendKeys(email);
        webDriver.findElement(passwordFieldLocator).sendKeys(password);
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable (loginButtonLocator));
        webDriver.findElement(loginButtonLocator).click();
    }

    @Step("Нажатие на кнопку Зарегистрироваться")
    public void clickRegisterButton(){
        webDriver.findElement(registerButtonLocator).click();
    }

    @Step("Нажатие на кнопку Восстановить пароль")
    public void clickRecoveryPasswordButton(){
        webDriver.findElement(recoveryPasswordButtonLocator).click();
    }

    @Step("Проверить видимость поля email")
    public boolean emailIsDisplayed(){
        new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable (emailFieldLocator));
        return webDriver.findElement(emailFieldLocator).isDisplayed();
    }

    @Step("Ожидание загрузки страницы")
    public void waitForLoad() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Вход']")));
    }

    @Step("Проверка ошибки при невалидном пароле")
    public boolean messageErrorIsDisplayed (){
        return webDriver.findElement(errorMessageLocator).isDisplayed();
    }
}
