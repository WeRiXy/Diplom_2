package constants;

import java.util.Map;

public class Responses {

    public static final Map<String,?> ORDER_SC_BAD_REQUEST_INGREDIENT_IDS_MUST_BE_PROVIDED =
            Map.of("success", false, "message", "Ingredient ids must be provided");
    public static final Map<String,?> ORDER_SC_BAD_REQUEST_ONE_OR_MORE_IDS_PROVIDED_ARE_INCORRECT =
            Map.of("success", false, "message", "One or more ids provided are incorrect");


    public static final Map<String,?> USER_SC_UNAUTHORIZED_YOU_SHOULD_BE_AUTHORISED =
            Map.of("success", false, "message", "You should be authorised");
    public static final Map<String,?> USER_REGISTRATION_SC_FORBIDDEN_USER_ALREADY_EXISTS =
            Map.of("success", false, "message", "User already exists");
    public static final Map<String,?> USER_REGISTRATION_SC_FORBIDDEN_EMAIL_PASSWORD_AND_NAME_ARE_REQUIRED_FIELDS =
            Map.of("success", false, "message", "Email, password and name are required fields");
    public static final Map<String,?> USER_LOGIN_SC_UNAUTHORIZED_EMAIL_OR_PASSWORD_ARE_INCORRECT =
            Map.of("success", false, "message", "email or password are incorrect");


    public static final Map<String,?> UPDATE_SC_FORBIDDEN_USER_WITH_SUCH_EMAIL_ALREADY_EXISTS =
            Map.of("success", false, "message", "User with such email already exists");
}
