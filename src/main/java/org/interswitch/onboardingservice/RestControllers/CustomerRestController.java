package org.interswitch.onboardingservice.RestControllers;

import org.interswitch.onboardingservice.DTOs.CustomerResponseDTO;
import org.interswitch.onboardingservice.DTOs.RestResponsePojo;
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
    public RestResponsePojo<CustomerResponseDTO> getCustomerById(@RequestParam Long customerId) {
        RestResponsePojo<CustomerResponseDTO> restResponsePojo = new RestResponsePojo<>();
        CustomerResponseDTO customer = customerService.findById(customerId);
        restResponsePojo.setData(customer);
        restResponsePojo.setSuccess(true);
        restResponsePojo.setMessage("Success");
        return restResponsePojo;
    }


    @GetMapping("getbycustno")
    public RestResponsePojo<CustomerResponseDTO> getByCustomerNumber(@RequestParam String customerNo) {
        RestResponsePojo<CustomerResponseDTO> restResponsePojo = new RestResponsePojo<>();
        CustomerResponseDTO customer = customerService.findByCustomerNumber(customerNo);
        restResponsePojo.setData(customer);
        restResponsePojo.setSuccess(true);
        restResponsePojo.setMessage("Success");
        return restResponsePojo;
    }

}
