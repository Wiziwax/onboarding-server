package org.interswitch.onboardingservice.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CustomerRequestDTO {

    private Long id;
    private String emailAddress;
    private String phoneNumber1;
    private String phoneNumber2;
    private String firstName;
    private String middleName;
    private Date dateOfBirth;
    private String lastName;
    private Date createdDate;
    private String motherMaidenName;
    private String customerNIN;
    private String customerBVN;
    private String stateOfOrigin;
}
