package by.scherbakov.audioportal.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class {@code User} is used to store User entity.
 *
 * @author ScherbakovIlia
 */
public class User implements Serializable {
    private String login;
    private String password;
    private String email;
    private String role;
    private String bonus;

    public User(String login, String password, String email, String role, String bonus) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.bonus = bonus;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role) &&
                Objects.equals(bonus, user.bonus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, password, email, role, bonus);
    }
}
