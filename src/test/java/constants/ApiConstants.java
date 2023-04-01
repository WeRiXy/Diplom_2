package constants;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiConstants {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";


    public static final String ENDPOINT_USER_REGISTER = "/api/auth/register";
    public static final String ENDPOINT_USER = "/api/auth/user";
    public static final String ENDPOINT_USER_LOGIN ="/api/auth/login";


    public static final String ENDPOINT_ORDER = "/api/orders";


    public static final String ENDPOINT_INGREDIENTS ="/api/ingredients";


    public static final RequestSpecification  REQUEST_SPEC = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(BASE_URI)
            .build();

}
