import io.qameta.allure.junit4.DisplayName;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import user.ResponseUser;
import user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static org.junit.Assert.assertTrue;

public class AccountTest extends DriverManagement {
    String accessToken;

    @Before
    public void setUpUser(){
        User  user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@" + "gmail.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(6));
        user.setName(RandomStringUtils.randomAlphabetic(6));

        ResponseUser responseUser = userSteps
                .registerUser(user)
                .extract().as (ResponseUser.class);
        accessToken = responseUser.getAccessToken();

        HomePage homePage = new HomePage(webDriver);
        homePage.clickLogInToAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(user.getEmail(), user.getPassword());
    }

    @After
    public void deleteUser(){
        userSteps
                .deleteUser(accessToken)
                .statusCode(HTTP_ACCEPTED);
    }

    @Test
    @DisplayName("Переход в Личный кабинет")
    public void goToPersonalAccountTest() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(webDriver);
        assertTrue(profilePage.exitButtonIsDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в Конструктор(кнопка Конструктор)")
    public void goConstructorPageByConstructorButtonTest() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(webDriver);
        profilePage.clickConstructorButton();

        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Переход из личного кабинета в Конструктор(нажатие на лого)")
    public void goConstructorPageByLogoTest() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(webDriver);
        profilePage.clickLogotypeButton();

        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutTest() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(webDriver);
        profilePage.clickExitButton();

        LoginPage loginPage = new LoginPage(webDriver);
        assertTrue(loginPage.emailIsDisplayed());
    }
}
