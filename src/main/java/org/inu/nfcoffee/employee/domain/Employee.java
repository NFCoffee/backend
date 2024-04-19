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
import org.inu.nfcoffee.exception.AuthCodeExpiredException;
import org.inu.nfcoffee.exception.DuplicateSignException;

import java.time.LocalDateTime;

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
    @Getter
    private boolean signed;
    private String authCode;
    private LocalDateTime authCodeExpired;

    public Employee(String employeeId, String email) {
        this.employeeId = employeeId;
        this.email = email;
        this.wallet = "";
        this.signed = false;
        this.authCode = "";
        this.authCodeExpired = LocalDateTime.MIN;
    }


    public void signing(String code) {
        if (!code.equals(authCode)) {
            throw new IllegalArgumentException(ErrorCode.NOT_MATCH_AUTH_CODE.description());
        }
        if (authCodeExpired.isBefore(LocalDateTime.now())) {
            throw new AuthCodeExpiredException(ErrorCode.EXPIRED_AUTH_CODE);
        }
        signed = true;
    }

    public void assignAuthEmailCode(String authEmailCode, LocalDateTime authCodeExpired) {
        if (signed) {
            throw new DuplicateSignException(ErrorCode.DUPLICATE_SIGN);
        }
        this.authCode = authEmailCode;
        this.authCodeExpired = authCodeExpired;
    }

    public void fixWallet(String wallet) {
        if (!signed) {
            throw new IllegalStateException(ErrorCode.NOT_YET_AUTHENTICATED.description());
        }
        this.wallet = wallet;
    }
}

