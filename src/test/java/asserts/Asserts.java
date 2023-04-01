package asserts;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Asserts {

       @Step("Compare value of response json with \"{expectedJson}\"")
    public static void compareKeyValue(Response response, Map<String,?> expectedJson) {
        try {
            for (Map.Entry<String, ?> entry : expectedJson.entrySet()) {
                response.then().assertThat().body(entry.getKey(), equalTo(entry.getValue()));
            }
        } catch (AssertionError e) {
            Allure.addAttachment("Response json", response.body().asPrettyString());
            throw e;
        }
    }

    @Step("Compare the number of json keys with \"{keyCount}\"")
    public static void compareKeyCount(Response response, int keyCount) {
        try {
            response.then().body("size()", CoreMatchers.is(keyCount));
        } catch (AssertionError e) {
            Allure.addAttachment("Response json", response.body().asPrettyString());
            throw e;
        }
    }

    @Step("Compare two json")
    public static <T> void compareJson(T jsonObj, Response response) {
        JsonParser parser = new JsonParser();
        JsonElement json1 = parser.parse(new Gson().toJson(jsonObj));
        JsonElement json2 = parser.parse(response.body().asString());
        assertEquals(json1, json2);
    }
}
