package tests.orders;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import requests.OrdersAPI;

import static asserts.Asserts.compareJson;
import static constants.Responses.USER_SC_UNAUTHORIZED_YOU_SHOULD_BE_AUTHORISED;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

@Epic(value = "Orders tests")
@Feature(value = "Get orders")
@Story(value = "Negative tests")
public class GetOrdersNegativeTest {


    @Test
    @DisplayName("Get list of orders from unauthorised user")
    public void getListOrdersFromUnauthorisedUser() {

        Response response = OrdersAPI.getListOrders("");
        response.then().statusCode(SC_UNAUTHORIZED);
        compareJson(USER_SC_UNAUTHORIZED_YOU_SHOULD_BE_AUTHORISED,response);
    }
}
