package org.interswitch.onboardingservice.RestControllers;

import org.interswitch.onboardingservice.DTOs.PaymentDTO;
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
    public Accounts createAccount(@RequestBody Accounts account){
       return accountService.createAccount(account);
    }

    @GetMapping("/all")
    public List<Accounts> getAllCustomerAccounts(@RequestParam String customerNo){
        return accountService.findAllCustomerAccounts(customerNo);
    }

    @PutMapping("/debit")
    public String debitAccount(@RequestBody PaymentDTO paymentDTO){
        return accountService.debitAccount(paymentDTO);
    }
}
