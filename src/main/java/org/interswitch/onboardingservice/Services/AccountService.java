package org.interswitch.onboardingservice.Services;

import org.interswitch.onboardingservice.DTOs.PaymentDTO;
import org.interswitch.onboardingservice.Entities.Accounts;

import java.util.List;

public interface AccountService {

    void createAccountForCustomer(Long customerId);
    Accounts findAccountByCustomerId(Long customerId);
    Accounts createAccount(Accounts accounts);
    List<Accounts> findAllCustomerAccounts(String number);

    String debitAccount(PaymentDTO paymentDTO);
}
