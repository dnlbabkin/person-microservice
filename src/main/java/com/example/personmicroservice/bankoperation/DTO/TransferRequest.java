package com.example.personmicroservice.bankoperation.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Value
public class TransferRequest {

    @JsonProperty("fromAccountNumber")
    String fromAccountNumber;

    @JsonProperty("toAccountNumber")
    String toAccountNumber;

    String currency;

    BigDecimal amount;

}
