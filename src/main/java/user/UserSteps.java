package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserSteps {
    public static final String USER_REGISTER_PATH = "/api/auth/register";
    public static final String USER_LOGIN_PATH = "/api/auth/login";
    public static final String USER_PATH = "/api/auth/user";

    @Step("Создание(регистрация) пользователя")
    public ValidatableResponse registerUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(USER_REGISTER_PATH)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(USER_LOGIN_PATH)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .header("authorization", accessToken)
                .when()
                .delete(USER_PATH)
                .then();
    }

}
