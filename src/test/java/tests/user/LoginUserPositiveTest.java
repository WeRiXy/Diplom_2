package tests.user;

import general_action.RegisteredUser;
import general_action.UserAction;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jsonobjects.user.UserResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.UserAPI;

import static asserts.Asserts.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@Epic(value = "User tests")
@Feature(value = "Login user")
@Story(value = "Positive tests")
public class LoginUserPositiveTest {
    private RegisteredUser registeredUser;
    private UserResponse userResponse;

    @Before
    public void setUp() {
        registeredUser = new RegisteredUser();
    }

    @Test
    @DisplayName("Login user with valid account data")
    @Description("Expected status code - 200")
    public void  loginCourierWithValidAccountData() {
        Response response = UserAPI.loginUser(registeredUser.userRegistryData);
        userResponse = response.body().as(UserResponse.class);

        response.then()
                .statusCode(SC_OK)
                .body("success",equalTo(true))
                .body("user.email",equalTo(registeredUser.user.getEmail()))
                .body("user.name",equalTo(registeredUser.user.getName()))
                .body("accessToken",notNullValue())
                .body("refreshToken",notNullValue());

        compareJson(userResponse,response);
    }

    @After
    public void deleteTestData() {
        UserAction.delete(registeredUser.userResponse);
    }
}
