package app.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    USER("USER"),
    ADMIN("admin");


    private final String value;

}
