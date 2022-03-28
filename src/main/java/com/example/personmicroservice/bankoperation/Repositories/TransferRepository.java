package com.example.personmicroservice.bankoperation.Repositories;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TransferRepository {

    private final Map<Integer, BigDecimal> storage = new HashMap(Map.of(1L, BigDecimal.class));

    public BigDecimal getBalanceForId(Integer accountId) {
        return storage.get(accountId);
    }

    public void save(Integer id, BigDecimal amount) {
        storage.put(id, amount);
    }
}
