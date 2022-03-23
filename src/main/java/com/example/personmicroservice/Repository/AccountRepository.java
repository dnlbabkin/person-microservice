package com.example.personmicroservice.Repository;

import com.example.personmicroservice.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
