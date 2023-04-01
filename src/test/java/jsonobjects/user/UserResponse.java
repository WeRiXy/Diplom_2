package jsonobjects.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private boolean success;
    private UserData user;
    private String accessToken;
    private String refreshToken;
}