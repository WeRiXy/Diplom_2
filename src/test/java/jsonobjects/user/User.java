package jsonobjects.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private String name;
    private String password;
    private String email;
}
