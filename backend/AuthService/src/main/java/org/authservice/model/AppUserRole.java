package org.authservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AppUserRole {
    SCHOOL_OWNER,
    SUPER_ADMIN,
    ADMIN,
    SUPPORT;

    @JsonCreator
    public static AppUserRole fromString (String value) {
        try {
            return  AppUserRole.valueOf(value.toUpperCase());
        }
        catch (Exception e) {
            return null;
        }
    }

    @JsonValue
    public String getValue() {
        return name();
    }
}
