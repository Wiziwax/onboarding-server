package org.interswitch.onboardingservice.ServiceImpls;

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
    public Accounts createAccount(Accounts accounts) {


        Customer customer = customerRepository.findCustomerByCustomerNo(accounts.getCustomerNo());
        if (customer==null){
            throw new RuntimeException(String.format("Customer with Customer number %s not found!", accounts.getCustomerNo()));
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
    public List<Accounts> findAllCustomerAccounts(String customerNo) {
        return accountRepository.findAllByCustomerNo(customerNo);
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
