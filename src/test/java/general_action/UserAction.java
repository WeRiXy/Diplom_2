package general_action;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import jsonobjects.user.User;
import jsonobjects.user.UserResponse;
import requests.UserAPI;

public class UserAction {

    @Step("Delete User")
    public static void delete(UserResponse userResponse) {
        String token = userResponse.getAccessToken();
        if (token != null) {
            UserAPI.deleteUser(token).then().statusCode(202);
        }
    }

    @Step("Delete User")
    public static void delete(Response response) {
        String token = response.then().extract().path("accessToken");
        if (token != null) {
            UserAPI.deleteUser(token);
        }
    }

    public static User updateUser(User userMain, User userUpdated) {
        User user = new User();
        if (userUpdated.getName() != null ) { user.setName(userUpdated.getName()); } else { user.setName(userMain.getName()); }
        if (userUpdated.getEmail() != null ) { user.setEmail(userUpdated.getEmail()); } else { user.setEmail(userMain.getEmail()); }
        if (userUpdated.getPassword() != null ) { user.setPassword(userUpdated.getPassword()); } else { user.setPassword(userMain.getPassword()); }
        return user;
    }
}
