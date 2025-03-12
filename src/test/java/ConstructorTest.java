import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;

public class ConstructorTest extends DriverManagement {

    @Test
    @DisplayName("Переход в раздел Соусы")
    public void openSaucesSectionTest() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickSaucesConstructorButton();
        Assert.assertEquals("Соусы", homePage.currentSectionText());
    }

    @Test
    @DisplayName("Переход в раздел Булки")
    public void openBunsSectionTest() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickFillingsConstructorButton(); //сначала идём в другой раздел т.к. "Булки" начальный
        homePage.clickBunsConstructorButton();
        Assert.assertEquals("Булки", homePage.currentSectionText());
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    public void openFillingsSectionTest() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickFillingsConstructorButton();
        Assert.assertEquals("Начинки", homePage.currentSectionText());
    }
}