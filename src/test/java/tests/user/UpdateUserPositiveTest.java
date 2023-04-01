package tests.user;

import general_action.RegisteredUser;
import general_action.UserAction;
import generators.GeneratorUserRegistryData;
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

import static generators.GeneratorTestUser.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic(value = "User tests")
@Feature(value = "Update user")
@Story(value = "Positive tests")
@RunWith(Parameterized.class)
public class UpdateUserPositiveTest {
    private RegisteredUser registeredUser;

    private User user;


    public UpdateUserPositiveTest(User user) {
        this.user = user;
    }


    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getData() {
        return new Object[][] {
                { User.builder().name(name()).build() },
                { User.builder().password(password()).build() },
                { User.builder().email(email()).build() },
                { User.builder().name(name()).email(email()).build() },
                { User.builder().email(email()).password(password()).build() },
                { User.builder().name(name()).email(email()).password(password()).build() },
        };
    }

    @Before
    public void setUp() {
        registeredUser = new RegisteredUser();
    }

    @Test
    @Description("Check update user data")
    public void  updateUser() {
        Response response = UserAPI.updateUser(user,registeredUser.userResponse.getAccessToken());

        User newUser = UserAction.updateUser(registeredUser.user,user);

        response.then()
                .statusCode(SC_OK)
                .body("success",equalTo(true))
                .body("user.email",equalTo(newUser.getEmail()))
                .body("user.name",equalTo(newUser.getName()));

        UserAPI.loginUser(GeneratorUserRegistryData.getRegistryDataFromUser(newUser)).then()
                .statusCode(SC_OK)
                .body("success",equalTo(true))
                .body("user.email",equalTo(newUser.getEmail()))
                .body("user.name",equalTo(newUser.getName()));
    }

    @After
    public void deleteTestData() {
        UserAction.delete(registeredUser.userResponse);
    }
}
