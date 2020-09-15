package by.balashevich.finalproject.model.entity;

import java.util.Arrays;

public enum UserRole {
    ADMIN,
    CLIENT,
    MANAGER;

    public static UserRole getUserRole(int index){
        return Arrays.stream(UserRole.values()).filter(r -> r.ordinal() == index).findFirst().get();
    }
}
