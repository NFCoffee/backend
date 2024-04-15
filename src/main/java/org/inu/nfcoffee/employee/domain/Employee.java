package org.inu.nfcoffee.employee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.inu.nfcoffee.common.BaseTimeEntity;
import org.inu.nfcoffee.common.ErrorCode;
import org.inu.nfcoffee.exception.DuplicateSignException;

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
    private boolean signed;
    private String authEmailCode;

    private Employee(String employeeId, String email) {
        this.employeeId = employeeId;
        this.email = email;
        this.wallet = "";
        this.signed = false;
        this.authEmailCode = "";
    }

    public boolean isSigned() {
        return signed;
    }

    public void signing(String code) {
        if (!code.equals(authEmailCode)) {
            throw new IllegalArgumentException(ErrorCode.NOT_MATCH_AUTH_CODE.description());
        }
        signed = true;
    }

    public void assignAuthEmailCode(String authEmailCode) {
        if (signed) {
            throw new DuplicateSignException(ErrorCode.DUPLICATE_SIGN);
        }
        this.authEmailCode = authEmailCode;
    }

    public void fixWallet(String wallet) {
        if (!signed) {
            throw new IllegalStateException(ErrorCode.NOT_YET_AUTHENTICATED.description());
        }
        this.wallet = wallet;
    }
}
