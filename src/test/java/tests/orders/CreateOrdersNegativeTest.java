package tests.orders;

import general_action.RegisteredUser;
import general_action.UserAction;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jsonobjects.ingredients.Ingredients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.OrdersAPI;

import static asserts.Asserts.compareJson;
import static constants.Responses.ORDER_SC_BAD_REQUEST_INGREDIENT_IDS_MUST_BE_PROVIDED;
import static constants.Responses.ORDER_SC_BAD_REQUEST_ONE_OR_MORE_IDS_PROVIDED_ARE_INCORRECT;
import static generators.GeneratorIngredients.*;
import static org.apache.http.HttpStatus.*;

@Epic(value = "Orders tests")
@Feature(value = "Create order")
@Story(value = "Negative tests")
public class CreateOrdersNegativeTest {
    private RegisteredUser registeredUser;

    @Before
    public void setUp() { registeredUser = new RegisteredUser(); }

    @Test
    @DisplayName("Create order without ingredients")
    public void createOrderWithoutIngredient() {

        Response response = OrdersAPI.createOrder(new Ingredients(),registeredUser.userResponse.getAccessToken());
        response.then().statusCode(SC_BAD_REQUEST);
        compareJson(ORDER_SC_BAD_REQUEST_INGREDIENT_IDS_MUST_BE_PROVIDED,response);
    }

    @Test
    @DisplayName("Create order with not exists ingredients")
    public void createOrderWitNotExistsIngredient() {

        Response response = OrdersAPI.createOrder(getRandomHashIngredients(),registeredUser.userResponse.getAccessToken());
        response.then().statusCode(SC_BAD_REQUEST);
        compareJson(ORDER_SC_BAD_REQUEST_ONE_OR_MORE_IDS_PROVIDED_ARE_INCORRECT,response);
    }

    @Test
    @DisplayName("Create order with invalid hash ingredient")
    public void createOrderWithInvalidHashIngredient() {
        OrdersAPI.createOrder(getInvalidHashIngredients(),registeredUser.userResponse.getAccessToken())
                .then()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void deleteTestData() {
        UserAction.delete(registeredUser.userResponse);
    }
}
