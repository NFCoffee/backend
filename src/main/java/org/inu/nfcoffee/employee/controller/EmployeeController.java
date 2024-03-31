package org.inu.nfcoffee.employee.controller;

import lombok.RequiredArgsConstructor;
import org.inu.nfcoffee.employee.dto.CreateWallerRequest;
import org.inu.nfcoffee.employee.dto.FinishSignRequest;
import org.inu.nfcoffee.employee.dto.SignRequest;
import org.inu.nfcoffee.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/sign")
    public ResponseEntity<?> sign(@RequestBody SignRequest request) {
        employeeService.sign(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/finish-sign")
    public ResponseEntity<?> finishSign(@RequestBody FinishSignRequest request) {
        employeeService.finishSign(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/wallet")
    public ResponseEntity<?> finishSign(@RequestBody CreateWallerRequest request) {
        employeeService.updateWallet(request);
        return ResponseEntity.ok().build();
    }
}
