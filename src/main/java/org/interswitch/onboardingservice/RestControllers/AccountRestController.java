package org.interswitch.onboardingservice.RestControllers;

import org.interswitch.onboardingservice.DTOs.AccountRequestDTO;
import org.interswitch.onboardingservice.DTOs.AccountResponseDTO;
import org.interswitch.onboardingservice.DTOs.PaymentDTO;
import org.interswitch.onboardingservice.DTOs.RestResponsePojo;
import org.interswitch.onboardingservice.Entities.Accounts;
import org.interswitch.onboardingservice.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts/")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public RestResponsePojo<String> createAccount(@RequestBody AccountRequestDTO account){
        RestResponsePojo<String> restResponsePojo = new RestResponsePojo<>();
        restResponsePojo.setData("Account created successfully");
        restResponsePojo.setSuccess(true);
        accountService.createAccount(account);
       return restResponsePojo;
    }

    @GetMapping("/all")
    public RestResponsePojo<List<AccountResponseDTO>> getAllCustomerAccounts(@RequestParam String customerNo) {
        RestResponsePojo<List<AccountResponseDTO>> restResponsePojo = new RestResponsePojo<>();
        List<AccountResponseDTO> accounts = accountService.findAllCustomerAccounts(customerNo);
        restResponsePojo.setData(accounts);
        restResponsePojo.setSuccess(true);
        restResponsePojo.setMessage("Success");
        return restResponsePojo;
    }


    @PutMapping("/debit")
    public RestResponsePojo<String> debitAccount(@RequestBody PaymentDTO paymentDTO) {
        RestResponsePojo<String> restResponsePojo = new RestResponsePojo<>();
        String result = accountService.debitAccount(paymentDTO);
        restResponsePojo.setData(result);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

}
