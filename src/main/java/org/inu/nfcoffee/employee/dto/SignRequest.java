package org.inu.nfcoffee.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignRequest {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String employeeId;
}
