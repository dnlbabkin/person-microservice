package com.example.personmicroservice.Repository;

import com.example.personmicroservice.Entity.PersonAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonAccountRepository extends JpaRepository<PersonAccount, Integer> {
    PersonAccount findAccountById(Integer id);
}
