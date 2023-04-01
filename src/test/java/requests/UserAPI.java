package requests;


import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jsonobjects.user.User;
import jsonobjects.user.UserRegistryData;

import static constants.ApiConstants.*;

public class UserAPI {

    @Step("Send POST request create User")
    public static Response createUser(User user) {
        return RestAssured.given()
                .spec(REQUEST_SPEC)
                .body(user)
                .post(ENDPOINT_USER_REGISTER);
    }

    @Step("Send POST request login User")
    public static Response loginUser(UserRegistryData userRegistryData) {
        return RestAssured.given()
                .spec(REQUEST_SPEC)
                .body(userRegistryData)
                .post(ENDPOINT_USER_LOGIN);
    }

    @Step("Send PATCH request update User")
    public static Response updateUser(User user, String token) {
        return RestAssured.given()
                .spec(REQUEST_SPEC)
                .auth().oauth2(token.replace("Bearer ",""))
                .body(user)
                .patch(ENDPOINT_USER);
    }

    @Step("Send DELETE request delete user")
    public static Response deleteUser(String token) {
        return RestAssured.given()
                .spec(REQUEST_SPEC)
                .auth().oauth2(token.replace("Bearer ",""))
                .delete(ENDPOINT_USER);
    }

}