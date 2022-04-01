package com.example.personmicroservice.bankservice.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class Income {

    String currency;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigDecimal amount;

}
