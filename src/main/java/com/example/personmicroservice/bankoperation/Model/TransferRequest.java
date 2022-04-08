package com.example.personmicroservice.bankoperation.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @JsonProperty("fromAccountNumber")
    private String fromAccountNumber;

    @JsonProperty("toAccountNumber")
    private String toAccountNumber;

    private String currency;

    private BigDecimal amount;

}
