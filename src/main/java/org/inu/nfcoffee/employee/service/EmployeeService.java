package org.inu.nfcoffee.employee.service;

import lombok.RequiredArgsConstructor;
import org.inu.nfcoffee.common.ErrorCode;
import org.inu.nfcoffee.employee.domain.Employee;
import org.inu.nfcoffee.employee.dto.CreateWallerRequest;
import org.inu.nfcoffee.employee.dto.FinishSignRequest;
import org.inu.nfcoffee.employee.dto.SignRequest;
import org.inu.nfcoffee.exception.DuplicateSignException;
import org.inu.nfcoffee.employee.domain.EmployeeRepository;
import org.inu.nfcoffee.exception.EntityNotFoundException;
import org.inu.nfcoffee.smtp.MailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MailService mailService;

    public void signUp(SignRequest request) {
        String id = request.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EMPTY_EMPLOYEE_INFO));

        String code = String.valueOf(mailService.sendMail(request.getEmail()));
        employee.assignAuthEmailCode(code, LocalDateTime.now().plusMinutes(15));
        employeeRepository.save(employee);
    }

    public void finishSign(FinishSignRequest request) {
        String id = request.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EMPTY_EMPLOYEE_INFO));

        validateIsSigned(employee);

        employee.signing(request.getCode());
        employeeRepository.save(employee);
    }

    private void validateIsSigned(Employee employee) {
        if (employee.isSigned()) {
            throw new DuplicateSignException(ErrorCode.DUPLICATE_SIGN);
        }
    }

    public void updateWallet(CreateWallerRequest request) {
        String id = request.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.EMPTY_EMPLOYEE_INFO));

        employee.fixWallet(request.getWallet());
        employeeRepository.save(employee);
    }


}
