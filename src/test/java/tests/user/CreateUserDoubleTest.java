package tests.user;

import general_action.RegisteredUser;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.UserAPI;

import static asserts.Asserts.*;
import static constants.Responses.USER_REGISTRATION_SC_FORBIDDEN_USER_ALREADY_EXISTS;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;

@Epic(value = "User tests")
@Feature(value = "Create user")
@Story(value = "Negative tests")
public class CreateUserDoubleTest {
    private RegisteredUser registeredUser;

    @Before
    public void setUp() { registeredUser = new RegisteredUser(); }
    @After
    public void deleteTestData() {
        UserAPI.deleteUser(registeredUser.userResponse.getAccessToken());
    }

    @Test
    @DisplayName("Сreatе the same user twice")
    @Description("Check of cannot create user when it already exists in BD")
    public void createUserDouble() {
        Response response = UserAPI.createUser(registeredUser.user);
        response.then().statusCode(SC_FORBIDDEN);
        compareJson(USER_REGISTRATION_SC_FORBIDDEN_USER_ALREADY_EXISTS,response);
    }
}

