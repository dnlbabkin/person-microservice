package com.example.personmicroservice.bankoperation.Repositories;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Transfer findByAccountNumberEquals(String fromAccountNumber);
}
