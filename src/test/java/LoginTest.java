import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import user.ResponseUser;
import user.User;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static org.junit.Assert.assertTrue;

public class LoginTest extends DriverManagement {
    private User user;
    String accessToken;

    @Before
    public void setUpUser(){
        user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@" + "gmail.com");
        user.setPassword(RandomStringUtils.randomAlphabetic(6));
        user.setName(RandomStringUtils.randomAlphabetic(6));

        ResponseUser responseUser = userSteps
                .registerUser(user)
                .extract().as (ResponseUser.class);
        accessToken = responseUser.getAccessToken();

    }

    @After
    public void deleteUser(){
        userSteps
                .deleteUser(accessToken)
                .statusCode(HTTP_ACCEPTED);
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void loginToAccountButtonTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickLogInToAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    public void loginPersonalAccountButtonTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через кнопку в форме регистрации")
    public void loginFromRegistrationButtonTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickRegisterButton();

        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.clickLoginButton();

        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через кнопку в форме восстановления пароля")
    public void loginFromRecoverPasswordTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickRecoveryPasswordButton();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(webDriver);
        forgotPasswordPage.clickLoginButton();

        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(homePage.createOrderButtonIsEnabled());
    }
}