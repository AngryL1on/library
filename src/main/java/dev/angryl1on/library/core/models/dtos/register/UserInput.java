package dev.angryl1on.library.core.models.dtos.register;

import dev.angryl1on.libraryapi.models.entity.enums.UserRoles;

public class UserInput {
    private String name;
    private String password;
    private String email;
    private UserRoles role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
