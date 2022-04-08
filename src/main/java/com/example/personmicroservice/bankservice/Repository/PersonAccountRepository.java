package com.example.personmicroservice.bankservice.Repository;

import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAccountRepository extends JpaRepository<PersonAccount, Long> {
    PersonAccount findAccountById(Long id);
//
//    PersonAccount findByNumberEquals(String fromAccountNumber);

//    PersonAccount findByAccountNumberEquals(String account);
}
