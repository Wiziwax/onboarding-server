package org.interswitch.onboardingservice.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.interswitch.onboardingservice.Enums.EnumCustomerType;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String emailAddress;
    @Column
    private String phoneNumber1;
    @Column
    private String phoneNumber2;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String lastName;
    @Column
    private Date dateOfBirth;
    @Column
    private String createdBy;
    @Column
    @CreatedDate
    private Date createdDate;
    @Column
    private String motherMaidenName;
    @Column
    private String customerNIN;
    @Column
    private String customerNo;
    @Column
    private String customerBVN;
    @Column
    private String stateOfOrigin;
//    @Column
//    private EnumCustomerType customerType;
}
