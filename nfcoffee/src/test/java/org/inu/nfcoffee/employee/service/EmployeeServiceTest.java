package org.inu.nfcoffee.employee.service;

import org.inu.nfcoffee.common.ErrorCode;
import org.inu.nfcoffee.employee.domain.Employee;
import org.inu.nfcoffee.employee.domain.EmployeeRepository;
import org.inu.nfcoffee.employee.dto.FinishSignRequest;
import org.inu.nfcoffee.exception.AuthCodeExpiredException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @DisplayName("인증코드 만료 검사")
    @Test
    void finishSign() {
        Employee employee = new Employee("202401022", "nfcoffee2024@gmail.com");
        employee.assignAuthEmailCode("123456", LocalDateTime.now().minusHours(1));
        FinishSignRequest request = new FinishSignRequest("nfcoffee2024@gmail.com", "202401022", "123456");

        Mockito.when(employeeRepository.findByEmployeeId("202401022")).thenReturn(Optional.of(employee));

        Throwable exception = assertThrows(AuthCodeExpiredException.class, () -> employeeService.finishSign(request));
        assertEquals(ErrorCode.EXPIRED_AUTH_CODE.description(), exception.getMessage());
    }
}