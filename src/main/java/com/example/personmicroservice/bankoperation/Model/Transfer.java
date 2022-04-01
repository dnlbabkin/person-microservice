package com.example.personmicroservice.bankoperation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer from_account;

    private Integer to_account;

    private String currency;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;
}
