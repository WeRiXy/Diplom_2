package requests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static constants.ApiConstants.*;

public class IngredientsAPI {
    @Step("Send GET request create User")
    public static Response getIngredients() {
        return RestAssured.given()
                .spec(REQUEST_SPEC)
                .get(ENDPOINT_INGREDIENTS);
    }
}
