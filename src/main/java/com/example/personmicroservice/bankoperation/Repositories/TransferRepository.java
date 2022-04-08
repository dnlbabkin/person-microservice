package com.example.personmicroservice.bankoperation.Repositories;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Transfer findByAccountNumberEquals(String accountNumber);

    Transfer findAccountById(Long id);
}
