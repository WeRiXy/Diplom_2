package tests.user;

import general_action.RegisteredUser;
import general_action.UserAction;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jsonobjects.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.UserAPI;

import static asserts.Asserts.*;
import static constants.Responses.UPDATE_SC_FORBIDDEN_USER_WITH_SUCH_EMAIL_ALREADY_EXISTS;
import static constants.Responses.USER_SC_UNAUTHORIZED_YOU_SHOULD_BE_AUTHORISED;
import static org.apache.http.HttpStatus.*;

@Epic(value = "User tests")
@Feature(value = "Update user")
@Story(value = "Negative tests")
public class UpdateUserNegativeTest {
    private RegisteredUser registeredUser;
    private RegisteredUser registeredUser2;

    @Before
    public void setUp() {
        registeredUser = new RegisteredUser();
    }

    @Test
    @DisplayName("Check for the impossibility of create user with incomplete data")
    public void  updateUserWithoutAuthorised() {
        Response response = UserAPI.updateUser(registeredUser.user,"");
        response.then().statusCode(SC_UNAUTHORIZED);
        compareJson(USER_SC_UNAUTHORIZED_YOU_SHOULD_BE_AUTHORISED,response);
    }

    @Test
    @DisplayName("Check for the impossibility of create user when email is already exists")
    public void  updateUserWhenEmailExists() {
        registeredUser2 = new RegisteredUser();

        Response response = UserAPI.updateUser(User.builder().email(registeredUser2.user.getEmail()).build(),
                registeredUser.userResponse.getAccessToken());
        response.then().statusCode(SC_FORBIDDEN);
        compareJson(UPDATE_SC_FORBIDDEN_USER_WITH_SUCH_EMAIL_ALREADY_EXISTS,response);
    }

    @After
    public void deleteTestData() {
        UserAction.delete(registeredUser.userResponse);
        if (registeredUser2 != null) { UserAction.delete(registeredUser2.userResponse); }
    }
}
