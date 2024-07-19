package org.interswitch.onboardingservice.Repositories;

import jakarta.transaction.Transactional;
import org.interswitch.onboardingservice.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByCustomerBVN(String bvn);
    Boolean existsByCustomerNIN(String nin);
//    Customer findByCustomerNo(String customerNo);
    Customer findCustomerByCustomerNo(String customerNo);
    List<Customer> findAllByCustomerBVN(String bvn);
    List<Customer> findAllByCustomerNIN(String nin);


}
