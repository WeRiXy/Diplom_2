package tests.user;

import general_action.UserAction;
import generators.GeneratorTestUser;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import jsonobjects.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.UserAPI;

import static asserts.Asserts.compareJson;
import static constants.Responses.USER_REGISTRATION_SC_FORBIDDEN_EMAIL_PASSWORD_AND_NAME_ARE_REQUIRED_FIELDS;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;

@Epic(value = "User tests")
@Feature(value = "Create user")
@Story(value = "Negative tests")
@RunWith(Parameterized.class)
public class CreateUserNegativeTest<T> {
    private Response response;
    private User user;

    public CreateUserNegativeTest(User user) {
        this.user = user;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getData() {
        return new Object[][] {
                {GeneratorTestUser.getWithoutEmail()},
                {GeneratorTestUser.getWithoutName()},
                {GeneratorTestUser.getWithoutPassword()},

        };
    }

    @Before
    public void setUp() {
        response = UserAPI.createUser(user);
    }
    @After
    public void deleteTestData() {
        UserAction.delete(response);
    }

    @Test
    @Description("Check for the impossibility of create user with incomplete data")
    public void  createUser() {
        response.then().statusCode(SC_FORBIDDEN);
        compareJson(USER_REGISTRATION_SC_FORBIDDEN_EMAIL_PASSWORD_AND_NAME_ARE_REQUIRED_FIELDS,response);
    }
}