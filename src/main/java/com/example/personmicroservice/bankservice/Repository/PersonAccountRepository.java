package com.example.personmicroservice.bankservice.Repository;

import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAccountRepository extends JpaRepository<PersonAccount, Long> {

    PersonAccount findByAccountNumberEquals(String accountNumber);

    PersonAccount findAccountById(Long id);
}
