package by.balashevich.finalproject.model.entity;

public class User extends Entity{
    private long userId;
    private String login;
    private String password;
    private UserRole role;

    public User(long userId, String login, String password) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = UserRole.CLIENT;
    }

    public User(long userId, String login, String password, UserRole role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        User user = (User) o;

        return userId == user.userId
                && login.equals(user.login)
                && password.equals(user.password)
                && role == user.role;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 37 * userId;
        result += 37 * login.hashCode();
        result += 37 * password.hashCode();
        result += 37 * role.ordinal();

        return result;
    }

    @Override
    public String toString() {
        return String.format("User %d: login %s, role %s",
                userId, login, role.name());
    }
}
