package com.example.personmicroservice.bankservice.Repository;

import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonAccountRepository extends JpaRepository<PersonAccount, Integer> {
    PersonAccount findAccountById(Integer id);
}
