package com.example.personmicroservice.bankoperation.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Value
public class TransferRequest {

    @JsonProperty("from_account")
    String fromAccount;
    @JsonProperty("to_account")
    String toAccount;
    String currency;
    BigDecimal amount;
}
