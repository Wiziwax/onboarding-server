package org.interswitch.onboardingservice.RestControllers;

import org.interswitch.onboardingservice.Entities.Customer;
import org.interswitch.onboardingservice.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/onboarding/")
public class OnboardingController {

    @Autowired
    private final CustomerService customerService;

    public OnboardingController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @PostMapping("/register")
    public String registerCustomer(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }

}


