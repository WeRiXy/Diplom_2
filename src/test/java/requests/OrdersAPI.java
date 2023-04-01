package requests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jsonobjects.ingredients.Ingredients;

import static constants.ApiConstants.*;

public class OrdersAPI {
    @Step("Send POST request create order")
    public static Response createOrder(Ingredients ingredients, String token) {
        return RestAssured.given()
                .spec(REQUEST_SPEC)
                .auth().oauth2(token.replace("Bearer ",""))
                .body(ingredients)
                .post(ENDPOINT_ORDER);
    }
   @Step("Send GET request to get list orders")
    public static Response getListOrders(String token) {
        return RestAssured.given()
                .spec(REQUEST_SPEC)
                .auth().oauth2(token.replace("Bearer ",""))
                .get(ENDPOINT_ORDER);
    }
}
