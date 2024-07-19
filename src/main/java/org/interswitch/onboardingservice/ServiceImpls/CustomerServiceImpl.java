package org.interswitch.onboardingservice.ServiceImpls;

import com.netflix.discovery.converters.Auto;
import org.interswitch.onboardingservice.Entities.Customer;
import org.interswitch.onboardingservice.Entities.Verify;
import org.interswitch.onboardingservice.Repositories.CustomerRepository;
import org.interswitch.onboardingservice.Repositories.VerifyRepository;
import org.interswitch.onboardingservice.Services.AccountService;
import org.interswitch.onboardingservice.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VerifyRepository verifyRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public String registerCustomer(Customer customer) {
        if(customerRepository.existsByCustomerBVN(customer.getCustomerBVN()) || customerRepository.existsByCustomerNIN(customer.getCustomerNIN())){
          return "Customer already exists";
        }

        Customer customer1 = new Customer();
        customer1.setEmailAddress(customer.getEmailAddress().trim());
        customer1.setPhoneNumber1(customer.getPhoneNumber1().trim());
        customer1.setPhoneNumber2(customer.getPhoneNumber2().trim());
        customer1.setFirstName(customer.getFirstName().trim());
        customer1.setLastName(customer.getLastName().trim());
        customer1.setMiddleName(customer.getMiddleName().trim());
        customer1.setCreatedDate(new Date());
        customer1.setMotherMaidenName(customer.getMotherMaidenName().trim());
        customer1.setCustomerBVN(customer.getCustomerBVN().trim());
        customer1.setCustomerNIN(customer.getCustomerNIN().trim());
//        customer1.setCustomerType(customer.getCustomerType());
        customer1.setCustomerNo(generateCustomerNumber());
        customer1.setDateOfBirth(customer.getDateOfBirth());
        customer1.setStateOfOrigin(customer.getStateOfOrigin().trim());
//        customer1.setCreatedBy();

//        if (findCustomerByBVN(customer.getCustomerBVN())!=null || !findCustomerByBVN(customer.getCustomerBVN()).isEmpty()){
//
//        }
        if(validateCustomer(customer)){
            customerRepository.save(customer1);
            accountService.createAccountForCustomer(customer1.getId());
        return "Customer successfully onboarded and account created successfully";
        };

        return "BVN details and Customer Details aren't consistent";
    }


    public boolean validateCustomer(Customer customer) {
        List<Verify> verifyingPerson = verifyRepository.findAllByCustomerBVN(customer.getCustomerBVN());

        if (verifyingPerson == null || verifyingPerson.isEmpty()) {
            throw new RuntimeException(String.format("No user with BVN %s found", customer.getCustomerBVN()));
        }

        String verifyingFirstName = verifyingPerson.get(0).getFirstName();
        String verifyingLastName = verifyingPerson.get(0).getLastName();
        String verifyingMiddleName = verifyingPerson.get(0).getMiddleName();

        String customerFirstName = customer.getFirstName();
        String customerLastName = customer.getLastName();
        String customerMiddleName = customer.getMiddleName();

        return Objects.equals(trimAndLower(verifyingFirstName), trimAndLower(customerFirstName)) &&
                Objects.equals(trimAndLower(verifyingLastName), trimAndLower(customerLastName)) &&
                Objects.equals(trimAndLower(verifyingMiddleName), trimAndLower(customerMiddleName));
    }

        private String trimAndLower(String value) {
            return value == null ? "" : value.trim().toLowerCase();
        }





    @Override
    public List<Verify> findCustomerByBVN(String bvn) {
        return verifyRepository.findAllByCustomerBVN(bvn);
    }

    @Override
    public List<Verify> findCustomerByNIN(String nin) {
        return verifyRepository.findAllByCustomerNIN(nin);
    }

    public static String generateCustomerNumber() {
        Random random = new Random();
        int min = 1000000;
        int max = 9999999;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return String.valueOf(randomNumber);
    }

    @Override
    public Customer findById(Long customerId){
        return customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("Could not find customer"));
    }

    @Override
    public Customer findByCustomerNumber(String customerNo) {
        return customerRepository.findCustomerByCustomerNo(customerNo);
    }
}
