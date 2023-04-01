package tests.user;

import general_action.RegisteredUser;
import general_action.UserAction;
import io.qameta.allure.*;
import io.restassured.response.Response;
import jsonobjects.user.UserRegistryData;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.UserAPI;

import static asserts.Asserts.*;
import static constants.Responses.*;
import static generators.GeneratorUserRegistryData.*;
import static org.apache.http.HttpStatus.*;


@Epic(value = "User tests")
@Feature(value = "Login user")
@Story(value = "Negative tests")
@RunWith(Parameterized.class)
public class LoginUserNegativeTest {
    private static RegisteredUser registeredUser= new RegisteredUser();
    private UserRegistryData userRegistryData;

    public LoginUserNegativeTest(UserRegistryData userRegistryData) {
        this.userRegistryData = userRegistryData;
    }

    @Parameterized.Parameters(name = "{1} - {0}")
    public static Object[][] getData() {
        return new Object[][] {
                { getRandomRegistryData() },
                { new UserRegistryData(registeredUser.user.getEmail(),password()) },
                { new UserRegistryData(email(),registeredUser.user.getPassword()) },
                { new UserRegistryData(registeredUser.user.getEmail(),null) },
                { new UserRegistryData(null,registeredUser.user.getPassword()) },
                { new UserRegistryData() },
        };
    }
    @Test
    @Description("Check for the impossibility of create user with incomplete data")
    public void  createUser() {
        Response response = UserAPI.loginUser(userRegistryData);
        response.then().statusCode(SC_UNAUTHORIZED);
        compareJson(USER_LOGIN_SC_UNAUTHORIZED_EMAIL_OR_PASSWORD_ARE_INCORRECT,response);
    }

    @AfterClass
    public static void deleteTestData() {
        UserAction.delete(registeredUser.userResponse);
    }
}
