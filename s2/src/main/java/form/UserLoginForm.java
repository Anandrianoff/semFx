package form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Andrey on 25.04.2017.
 */
public class UserLoginForm {
    @NotEmpty(message = "Это поле не должно быть пустым")
    private String username;

    @NotEmpty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    private String password;
}
