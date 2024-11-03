import io.qameta.allure.junit4.DisplayName;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import user.ResponseUser;
import user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends DriverManagement {
    private final String name = RandomStringUtils.randomAlphabetic(6);
    private final String email = RandomStringUtils.randomAlphabetic(10) + "@" + "gmail.com";
    private final String password6 = RandomStringUtils.randomAlphabetic(6);
    private final String password5 = RandomStringUtils.randomAlphabetic(5);

    @After
    public void deleteUser(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password6);
        user.setName(name);
        ResponseUser responseUser =
                userSteps
                        .loginUser(user)
                        .extract().as (ResponseUser.class);
        String accessToken = responseUser.getAccessToken();
        userSteps
                .deleteUser(accessToken)
                .statusCode(HTTP_ACCEPTED);
    }

    @Test
    @DisplayName("Успешная регистарция")
    public void registerTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickRegisterButton();

        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.inputLoginInfoUser(name, email, password6);

        loginPage.waitForLoad();
        loginPage.login(email, password6);

        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @Test
    @DisplayName("Ошибка неорректного пароля. Минимальный пароль — шесть символов.")
    public void registerFaultTest() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickRegisterButton();

        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.inputLoginInfoUser(name, email, password5);

        assertTrue(loginPage.messageErrorIsDisplayed());

    }
}
