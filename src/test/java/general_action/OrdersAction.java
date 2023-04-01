package general_action;

import requests.IngredientsAPI;

import java.util.List;

public class OrdersAction {
    public static List<String> getHashIngredientsBun() {
        return IngredientsAPI.getIngredients().then().extract().path("data.findAll {it.type=='bun'}._id");
    }
    public static List<String> getHashIngredientsSauce() {
        return IngredientsAPI.getIngredients().then().extract().path("data.findAll {it.type=='sauce'}._id");
    }
    public static List<String> getHashIngredientsMain() {
        return IngredientsAPI.getIngredients().then().extract().path("data.findAll {it.type=='main'}._id");
    }
}
