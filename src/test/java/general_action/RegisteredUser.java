package general_action;

import generators.GeneratorTestUser;
import generators.GeneratorUserRegistryData;
import io.restassured.response.Response;
import jsonobjects.user.User;
import jsonobjects.user.UserResponse;
import jsonobjects.user.UserRegistryData;
import requests.UserAPI;

import static org.apache.http.HttpStatus.SC_OK;

public class RegisteredUser {
    public final User user;
    public final UserResponse userResponse;
    public final UserRegistryData userRegistryData;
   {
       user = GeneratorTestUser.getValidUser();
       Response response = UserAPI.createUser(user);
       response.then().statusCode(SC_OK);
       userResponse = response.body().as(UserResponse.class);
       userRegistryData = GeneratorUserRegistryData.getRegistryDataFromUser(user);
    }
}
