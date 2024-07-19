package org.interswitch.onboardingservice.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Verify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String lastName;
    @Column
    private Date registrationDate;
    @Column
    private Date dateOfBirth;
    @Column
    private Integer age;
    @Column
    private String customerBVN;
    @Column
    private String customerNIN;
}
