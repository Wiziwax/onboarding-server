package org.interswitch.onboardingservice.Services;

import org.interswitch.onboardingservice.DTOs.CustomerRequestDTO;
import org.interswitch.onboardingservice.DTOs.CustomerResponseDTO;
import org.interswitch.onboardingservice.Entities.Customer;
import org.interswitch.onboardingservice.Entities.Verify;

import java.util.List;

public interface CustomerService {

    String registerCustomer(CustomerRequestDTO customer);
    List<Verify> findCustomerByBVN(String bvn);
    List<Verify> findCustomerByNIN(String nin);
    CustomerResponseDTO findById(Long customerId);

    CustomerResponseDTO findByCustomerNumber(String customerNo);
}
