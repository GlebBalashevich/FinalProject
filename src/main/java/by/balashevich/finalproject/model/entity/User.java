package by.balashevich.finalproject.model.entity;

import java.util.Arrays;

public class User extends Entity {

    public enum Role {
        ADMIN,
        CLIENT;

        public static Role getUserRole(int index) {
            return Arrays.stream(Role.values()).filter(r -> r.ordinal() == index).findFirst().get();
        }
    }

    private long userId;
    private String email;
    private Role role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return userId == user.userId
                && (email != null && email.equals(user.email))
                && role == user.role;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 37 * result + Long.hashCode(userId);
        result += 37 * result + (email == null ? 0 : email.hashCode());
        result += 37 * result + (role == null ? 0 : role.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return String.format("Id %d: email %s, role %s",
                userId, email, role.name());
    }
}
