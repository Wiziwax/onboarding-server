package org.interswitch.onboardingservice.Repositories;

import org.interswitch.onboardingservice.Entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

    List<Accounts> findAllByCustomerNo(String customerNo);
}
