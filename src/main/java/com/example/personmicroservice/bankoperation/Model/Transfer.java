package com.example.personmicroservice.bankoperation.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transfer {

    private Integer from;
    private Integer to;
    private BigDecimal amount;
}
