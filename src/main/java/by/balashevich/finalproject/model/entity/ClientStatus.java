package by.balashevich.finalproject.model.entity;

import java.util.Arrays;

public enum ClientStatus {
    PENDING,
    ACTIVE,
    BLOCKED;

    public static ClientStatus getClientStatus(int index){
        return Arrays.stream(ClientStatus.values()).filter(s -> s.ordinal() == index).findFirst().get();
    }
}
