package org.inu.nfcoffee.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class FinishSignRequest {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String employeeId;
    @NotBlank @Size(min = 6, max = 6)
    private String code;

    public FinishSignRequest(String email, String employeeId, String code) {
        this.email = email;
        this.employeeId = employeeId;
        this.code = code;
    }
}
