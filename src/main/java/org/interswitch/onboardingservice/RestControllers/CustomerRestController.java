package org.interswitch.onboardingservice.RestControllers;

import org.interswitch.onboardingservice.Entities.Customer;
import org.interswitch.onboardingservice.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("getbyid")
    public Customer getCustomerById(@RequestParam Long customerId){
        return customerService.findById(customerId);
    }

    @GetMapping("getbycustno")
    public Customer getByCustomerNumber(@RequestParam String customerNo){
        return customerService.findByCustomerNumber(customerNo);
    }
}
