package org.interswitch.onboardingservice.Services;

import org.interswitch.onboardingservice.Entities.Customer;
import org.interswitch.onboardingservice.Entities.Verify;

import java.util.List;

public interface CustomerService {

    String registerCustomer(Customer customer);
    List<Verify> findCustomerByBVN(String bvn);
    List<Verify> findCustomerByNIN(String nin);
    Customer findById(Long customerId);

    Customer findByCustomerNumber(String customerNo);
}
