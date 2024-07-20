package org.interswitch.onboardingservice.RestControllers;

import org.interswitch.onboardingservice.DTOs.CustomerRequestDTO;
import org.interswitch.onboardingservice.DTOs.RestResponsePojo;
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
    public RestResponsePojo<String> registerCustomer(@RequestBody CustomerRequestDTO customer) {
        RestResponsePojo<String> restResponsePojo = new RestResponsePojo<>();
        String result = customerService.registerCustomer(customer);
        restResponsePojo.setData(result);
        restResponsePojo.setSuccess(true);
        restResponsePojo.setMessage("Success");
        return restResponsePojo;
    }


}


