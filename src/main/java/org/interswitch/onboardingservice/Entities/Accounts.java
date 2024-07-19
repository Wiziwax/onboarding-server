package org.interswitch.onboardingservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.interswitch.onboardingservice.Enums.EnumAccountType;
import org.interswitch.onboardingservice.Enums.EnumCustomerType;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String accountNo;
//    @Column
//    private EnumAccountType accountType;
    @Column
    private Integer branchNo;
    @Column
    private String createdBy;
    @Column
    @CreatedDate
    private Date createdDate;
    @Column
    private String customerNo;
    @Column
    private String customerName;
    @Column
    private String customerNIN;
    @Column
    private String customerBVN;
    @Column
    private Boolean isActive;
    @Column
    @JsonIgnore
    private Boolean isBlocked;
    @Column
    @JsonIgnore
    private Boolean isClosed;
    @Column
    @JsonIgnore
    private Boolean isDormant;
    @Column
    private Boolean isDebitRestricted;
    @Column
    private BigDecimal accountBalance;


}
