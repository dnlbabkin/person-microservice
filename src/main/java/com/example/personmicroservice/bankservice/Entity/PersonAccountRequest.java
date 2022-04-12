package com.example.personmicroservice.bankservice.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.math.BigDecimal;

@Setter
@Getter
public class PersonAccountRequest {

    @JsonProperty("initialCurrency")
    String initialCurrency;

    @JsonProperty("initialPayment")
    BigDecimal initialPayment;
}
