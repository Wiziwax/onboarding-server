package org.interswitch.onboardingservice.Services;

import org.interswitch.onboardingservice.DTOs.AccountRequestDTO;
import org.interswitch.onboardingservice.DTOs.AccountResponseDTO;
import org.interswitch.onboardingservice.DTOs.PaymentDTO;
import org.interswitch.onboardingservice.Entities.Accounts;

import java.util.List;

public interface AccountService {

    void createAccountForCustomer(Long customerId);
    Accounts findAccountByCustomerId(Long customerId);
    Accounts createAccount(AccountRequestDTO accounts);
    List<AccountResponseDTO> findAllCustomerAccounts(String number);

    String debitAccount(PaymentDTO paymentDTO);
}
