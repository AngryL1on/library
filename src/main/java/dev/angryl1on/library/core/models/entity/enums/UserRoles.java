package dev.angryl1on.library.core.models.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRoles {
    READER(0, "Reader"),
    LIBRARIAN(1, "Librarian");

    private final int index;
    private final String roleName;


    UserRoles(int index, String roleName) {
        this.index = index;
        this.roleName = roleName;
    }

    public int getIndex() {
        return index;
    }

    @JsonValue
    public String getRoleName() {
        return roleName;
    }
}
