package com.example.personmicroservice.bankoperation.Model;

import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Value
public class TransferRequest {

    Long id;
    @JsonProperty("from_account")
    PersonAccount fromAccount;
    @JsonProperty("to_account")
    PersonAccount toAccount;
    String currency;
    BigDecimal amount;

    @Override
    public String toString() {
        return "TransferRequest{" +
                "id=" + id +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
