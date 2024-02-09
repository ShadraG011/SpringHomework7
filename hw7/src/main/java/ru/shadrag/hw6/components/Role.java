package ru.shadrag.hw6.components;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER"),
    ;

    private final String role;

    Role(String role) {
        this.role = role;
    }


}
