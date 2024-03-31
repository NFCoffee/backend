package org.inu.nfcoffee.employee.dto;

import lombok.Getter;

@Getter
public class CreateWallerRequest {
    private String email;
    private String employeeId;
    private String wallet;
}
