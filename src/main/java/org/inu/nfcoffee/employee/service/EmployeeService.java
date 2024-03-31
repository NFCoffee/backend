package org.inu.nfcoffee.employee.service;

import lombok.RequiredArgsConstructor;
import org.inu.nfcoffee.common.ErrorCode;
import org.inu.nfcoffee.employee.domain.Employee;
import org.inu.nfcoffee.employee.dto.CreateWallerRequest;
import org.inu.nfcoffee.employee.dto.FinishSignRequest;
import org.inu.nfcoffee.employee.dto.SignRequest;
import org.inu.nfcoffee.exception.DuplicateSignException;
import org.inu.nfcoffee.employee.repository.EmployeeRepository;
import org.inu.nfcoffee.smtp.MailService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MailService mailService;

    public void sign(SignRequest request) {
        // todo: DB 확인해서 이미 회원가입한 유저인지 확인
        String id = request.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new IllegalStateException(ErrorCode.EMPTY_EMPLOYEE_INFO.description()));
        if (employee.isSigning()) {
            throw new DuplicateSignException(ErrorCode.DUPLICATE_SIGN);
        }

        // todo: SES 이메일 보내기 + 코드 디비에 저장
        String code = String.valueOf(mailService.sendMail(request.getEmail()));
        employee.setAuthEmailCode(code);
        employeeRepository.save(employee);
    }

    public void finishSign(FinishSignRequest request) {
        // todo: 이메일 인증 코드 확인해서 성공/실패 응답전송
        String id = request.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new IllegalStateException(ErrorCode.EMPTY_EMPLOYEE_INFO.description()));
        if (employee.isSigning()) {
            throw new DuplicateSignException(ErrorCode.DUPLICATE_SIGN);
        }

        if (!request.getCode().equals(employee.getAuthEmailCode())) {
            throw new IllegalArgumentException(ErrorCode.NOT_MATCH_AUTH_CODE.description());
        }
        employee.signing();
        employeeRepository.save(employee);
    }

    public void updateWallet(CreateWallerRequest request) {
        String id = request.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new IllegalStateException(ErrorCode.EMPTY_EMPLOYEE_INFO.description()));
        if (!employee.isSigning()) {
            throw new DuplicateSignException(ErrorCode.NOT_YET_AUTHENTICATED);
        }
        employee.setWallet(request.getWallet());
        employeeRepository.save(employee);
    }
}
