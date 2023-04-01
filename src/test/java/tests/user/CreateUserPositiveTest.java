package tests.user;

import generators.GeneratorTestUser;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jsonobjects.user.User;
import jsonobjects.user.UserResponse;
import org.junit.After;
import org.junit.Test;
import requests.UserAPI;

import static asserts.Asserts.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@Epic(value = "User tests")
@Feature(value = "Create user")
@Story(value = "Positive tests")
public class CreateUserPositiveTest {
    private UserResponse userResponse;

    @Test
    @DisplayName("Check of create courier with valid data")
    public void createCourier() {
        User user = GeneratorTestUser.getValidUser();
        Response response = UserAPI.createUser(user);
        userResponse = response.body().as(UserResponse.class);

        response.then()
                .statusCode(SC_OK)
                .body("success",equalTo(true))
                .body("user.email",equalTo(user.getEmail()))
                .body("user.name",equalTo(user.getName()))
                .body("accessToken",notNullValue())
                .body("refreshToken",notNullValue());

        compareJson(userResponse,response);
    }

    @After
    public void deleteTestData() {
            UserAPI.deleteUser(userResponse.getAccessToken());
    }
}