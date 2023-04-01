package generators;

import com.github.javafaker.Faker;
import jsonobjects.ingredients.Ingredients;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static general_action.OrdersAction.*;

public class GeneratorIngredients {

    public static Ingredients getValidBurger() {

        List<String> bun = getHashIngredientsBun();
        List<String> sauce = getHashIngredientsSauce();
        List<String> main = getHashIngredientsMain();

        Random rand = new Random();

        List<String> burger = new ArrayList<>();
        burger.add(bun.get(rand.nextInt(bun.size())));

        for (int i=1;i<rand.nextInt(3)+1;i++) {
            burger.add(sauce.get(rand.nextInt(sauce.size())));
        }
        for (int i=1;i<rand.nextInt(3)+1;i++) {
            burger.add(main.get(rand.nextInt(main.size())));
        }
        return new Ingredients(burger);
        }

    public static Ingredients getInvalidHashIngredients() {
        List<String> burger = List.of(new Faker().random().hex(20));
        return new Ingredients(burger);
    }
    public static Ingredients getRandomHashIngredients() {
        List<String> burger = List.of(new Faker().random().hex(24));
        return new Ingredients(burger);
    }
}
