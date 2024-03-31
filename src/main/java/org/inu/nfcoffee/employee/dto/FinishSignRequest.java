package org.inu.nfcoffee.employee.dto;

import lombok.Getter;

@Getter
public class FinishSignRequest {
    private String email;
    private String employeeId;
    private String code;
}
