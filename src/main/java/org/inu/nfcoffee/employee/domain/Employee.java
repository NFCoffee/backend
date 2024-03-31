package org.inu.nfcoffee.employee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String employeeId;
    private String email;
    private String wallet;
    private boolean isSigning;
    private String authEmailCode;

    public Employee(String employeeId, String email) {
        this.employeeId = employeeId;
        this.email = email;
        this.wallet = "";
        this.isSigning = false;
        this.authEmailCode = "";
    }

    public void signing() {
        this.isSigning = true;
    }
}
