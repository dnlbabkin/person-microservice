package com.example.personmicroservice.bankservice.Repository;

import com.example.personmicroservice.bankservice.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
