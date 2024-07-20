package org.interswitch.onboardingservice.ServiceImpls;

import org.interswitch.onboardingservice.DTOs.AccountRequestDTO;
import org.interswitch.onboardingservice.DTOs.AccountResponseDTO;
import org.interswitch.onboardingservice.DTOs.PaymentDTO;
import org.interswitch.onboardingservice.Entities.Accounts;
import org.interswitch.onboardingservice.Entities.Customer;
import org.interswitch.onboardingservice.Enums.EnumAccountType;
import org.interswitch.onboardingservice.Repositories.AccountRepository;
import org.interswitch.onboardingservice.Repositories.CustomerRepository;
import org.interswitch.onboardingservice.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountsServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createAccountForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Accounts account = new Accounts();
        account.setAccountBalance(BigDecimal.ZERO);
        account.setCreatedDate(new Date());
//        account.setAccountType(EnumAccountType.SAVINGS);
//        account.setCreatedBy();
//        account.setBranchNo();
        if (customer.getMiddleName()==null){
        account.setCustomerName(customer.getFirstName() + customer.getLastName());}
        else {
        account.setCustomerName(customer.getFirstName()+ " " +
                customer.getMiddleName() + " " + customer.getLastName());
        }
        account.setCustomerNo(customer.getCustomerNo());
        account.setCustomerBVN(customer.getCustomerBVN());
        account.setCustomerNIN(customer.getCustomerNIN());
        account.setIsClosed(false);
        account.setIsDormant(false);
        account.setIsBlocked(false);
        account.setIsDebitRestricted(false);
        account.setIsActive(true);


            account.setAccountNo(generateAccountNumber(customer.getPhoneNumber1())); // Implement account number generation

        accountRepository.save(account);
    }

    @Override
    public Accounts findAccountByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public Accounts createAccount(AccountRequestDTO accountRequestDTO) {


        Customer customer = customerRepository.findCustomerByCustomerNo(accountRequestDTO.getCustomerNo());
        if (customer==null){
            throw new RuntimeException(String.format("Customer with Customer number %s not found!", accountRequestDTO.getCustomerNo()));
        }
        Accounts account = new Accounts();
        account.setAccountBalance(BigDecimal.ZERO);
        account.setCreatedDate(new Date());
//        account.setAccountType(EnumAccountType.SAVINGS);
//        account.setCreatedBy();
//        account.setBranchNo();
        if (customer.getMiddleName()==null){
            account.setCustomerName(customer.getFirstName() + customer.getLastName());}
        else {
            account.setCustomerName(customer.getFirstName()+ " " +
                    customer.getMiddleName() + " " + customer.getLastName());
        }
        account.setCustomerNo(customer.getCustomerNo());
        account.setCustomerBVN(customer.getCustomerBVN());
        account.setCustomerNIN(customer.getCustomerNIN());
        account.setIsClosed(false);
        account.setIsDormant(false);
        account.setIsBlocked(false);
        account.setIsDebitRestricted(false);
        account.setIsActive(true);


        account.setAccountNo(generateAccountNumber(customer.getPhoneNumber1())); // Implement account number generation
        return accountRepository.save(account);
    }

    @Override
    public List<AccountResponseDTO> findAllCustomerAccounts(String customerNo) {
        List<Accounts> accounts = accountRepository.findAllByCustomerNo(customerNo);
        return accounts.stream().map(account -> AccountResponseDTO.builder()
                .id(account.getId())
                .accountNo(account.getAccountNo())
                .branchNo(account.getBranchNo())
                .createdBy(account.getCreatedBy())
                .createdDate(account.getCreatedDate())
                .customerNo(account.getCustomerNo())
                .customerName(account.getCustomerName())
                .customerNIN(account.getCustomerNIN())
                .customerBVN(account.getCustomerBVN())
                .isActive(account.getIsActive())
                .isBlocked(account.getIsBlocked())
                .isClosed(account.getIsClosed())
                .isDormant(account.getIsDormant())
                .isDebitRestricted(account.getIsDebitRestricted())
                .accountBalance(account.getAccountBalance())
                .build()).collect(Collectors.toList());
    }


    @Override
    public String debitAccount(PaymentDTO paymentDTO) {
        List<Accounts> customerAccounts = accountRepository.findAllByCustomerNo(paymentDTO.getCustomerNo());
        for (Accounts account : customerAccounts) {
            if (account.getId().equals(paymentDTO.getAccountId())) {
                account.setAccountBalance(account.getAccountBalance().subtract(paymentDTO.getAmount()));
                 accountRepository.save(account);
                 return "Account Debited";
            }
        }
        return "Account not Found";
    }

    private String generateAccountNumber(String phoneNumber) {
            return phoneNumber.substring(1);
    }


}
