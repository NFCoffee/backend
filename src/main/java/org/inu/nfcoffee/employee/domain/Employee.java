package org.inu.nfcoffee.employee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.inu.nfcoffee.common.BaseTimeEntity;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Employee extends BaseTimeEntity {

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
