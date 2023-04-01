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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

@Epic(value = "Orders tests")
@Feature(value = "Create order")
@Story(value = "Positive tests")
public class CreateOrdersPositiveTest {
    private RegisteredUser registeredUser;

    @Before
    public void setUp() { registeredUser = new RegisteredUser(); }

    @Test
    @DisplayName("Create valid order from authorised user")
    public void createOrderFromAuthorisedUser() {

        OrdersAPI.createOrder(GeneratorIngredients.getValidBurger(),registeredUser.userResponse.getAccessToken())
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order.number", greaterThan(0));
    }

    @Test
    @DisplayName("Create valid order from unauthorised user")
    public void createOrderFromUnauthorisedUser() {

        OrdersAPI.createOrder(GeneratorIngredients.getValidBurger(),"")
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order.number", greaterThan(0));
    }

    @After
    public void deleteTestData() {
        UserAction.delete(registeredUser.userResponse);
    }
}
