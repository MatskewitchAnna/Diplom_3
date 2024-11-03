package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver webDriver;

    //Кнопка Конструктор
    private final By constructorButtonLocator = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href = '/']");
    //Кнопка Логотип
    private final By logoButtonLocator = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    //Кнопка Выход
    private final By exitButtonLocator = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Нажатие на кнопку Конструктор")
    public void clickConstructorButton(){
        webDriver.findElement(constructorButtonLocator).click();
    }

    @Step("Нажатие на Логотип")
    public void clickLogotypeButton(){
        webDriver.findElement(logoButtonLocator).click();
    }

    @Step("Нажатие на кнопку Выход")
    public void clickExitButton(){
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable (exitButtonLocator));
        webDriver.findElement(exitButtonLocator).click();
    }

    @Step("Проверка видимости кнопки Выход")
    public boolean exitButtonIsDisplayed(){
        new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable (exitButtonLocator));
        return webDriver.findElement(exitButtonLocator).isDisplayed();
    }

}
