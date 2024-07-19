package org.interswitch.onboardingservice.Repositories;

import org.interswitch.onboardingservice.Entities.Customer;
import org.interswitch.onboardingservice.Entities.Verify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerifyRepository extends JpaRepository<Verify, Long> {

    Boolean existsByCustomerBVN(String bvn);
    Boolean existsByCustomerNIN(String nin);
    List<Verify> findAllByCustomerBVN(String bvn);
    List<Verify> findAllByCustomerNIN(String nin);
}
