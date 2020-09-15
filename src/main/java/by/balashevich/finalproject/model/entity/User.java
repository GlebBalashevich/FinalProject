package by.balashevich.finalproject.model.entity;

public class User extends Entity{
    private long userId;
    private String login;
    private UserRole role;

    public User(String login) {
        this.login = login;
        this.role = UserRole.CLIENT;
    }

    public User(long userId, String login) {
        this.userId = userId;
        this.login = login;
        this.role = UserRole.CLIENT;
    }

    public User(String login, UserRole role) {
        this.login = login;
        this.role = role;
    }

    public User(long userId, String login, UserRole role) {
        this.userId = userId;
        this.login = login;
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
                && role == user.role;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 37 * Long.hashCode(userId);
        result += 37 * login.hashCode();
        result += 37 * role.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return String.format("Id %d: login %s, role %s",
                userId, login, role.name());
    }
}
