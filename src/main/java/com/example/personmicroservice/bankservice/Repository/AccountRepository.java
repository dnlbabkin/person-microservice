package com.example.personmicroservice.bankservice.Repository;

import com.example.personmicroservice.bankservice.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
