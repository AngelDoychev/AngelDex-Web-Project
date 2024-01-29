package com.example.angeldex.model.dtos;

import jakarta.validation.constraints.NotEmpty;

public class ChangePasswordDto {

    public int code;
    @NotEmpty(message = "Cannot change to an empty password.")
    public String newPassword;


    public ChangePasswordDto() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
