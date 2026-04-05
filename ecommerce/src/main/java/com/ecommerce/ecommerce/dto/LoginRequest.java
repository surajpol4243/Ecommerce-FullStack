package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "email is require")
    private String email;

    @NotBlank(message = "passwrod is require")
    private String password;
}
