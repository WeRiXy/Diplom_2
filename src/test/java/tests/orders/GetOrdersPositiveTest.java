package tests.orders;

import general_action.RegisteredUser;
import general_action.UserAction;
import generators.GeneratorIngredients;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import requests.OrdersAPI;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@Epic(value = "Orders tests")
@Feature(value = "Get orders")
@Story(value = "Positive tests")
public class GetOrdersPositiveTest {
    private RegisteredUser registeredUser;

    @Before
    public void setUp() {
        registeredUser = new RegisteredUser();
        OrdersAPI.createOrder(GeneratorIngredients.getValidBurger(),registeredUser.userResponse.getAccessToken())
                .then().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Get list of orders from authorised user")
    public void getListOrders() {

        OrdersAPI.getListOrders(registeredUser.userResponse.getAccessToken())
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("orders.size()", greaterThan(0))
                .body("total", greaterThan(0) )
                .body("totalToday", greaterThan(0) );
    }

    @After
    public void deleteTestData() {
        UserAction.delete(registeredUser.userResponse);
    }
}
