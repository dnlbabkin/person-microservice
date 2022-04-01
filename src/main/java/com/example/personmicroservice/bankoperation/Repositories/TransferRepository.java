package com.example.personmicroservice.bankoperation.Repositories;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Map;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    BigDecimal getBalanceById(Long account_number);

//    @Query()
//    void saveBalance(Map<Integer, BigDecimal> balanceFrom);

}
