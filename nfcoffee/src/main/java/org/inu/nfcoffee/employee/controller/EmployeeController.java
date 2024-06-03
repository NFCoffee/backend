package org.inu.nfcoffee.employee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.inu.nfcoffee.employee.client.Web3Service;
import org.inu.nfcoffee.employee.dto.CreateWalletRequest;
import org.inu.nfcoffee.employee.dto.FinishSignRequest;
import org.inu.nfcoffee.employee.dto.SignRequest;
import org.inu.nfcoffee.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final Web3Service web3Service;

    @PostMapping("/sign")
    public ResponseEntity<?> sign(@Valid @RequestBody SignRequest request) {
        employeeService.signUp(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/finish-sign")
    public ResponseEntity<?> finishSign(@Valid @RequestBody FinishSignRequest request) {
        employeeService.finishSign(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/wallet")
    public ResponseEntity<?> finishSign(@Valid @RequestBody CreateWalletRequest request) {
        employeeService.updateWallet(request);
        web3Service.mintAndTransfer(request.getWallet(), "");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestParam String wallet) {
        web3Service.mintAndTransfer(wallet, "");
        return ResponseEntity.ok().build();
    }
}
