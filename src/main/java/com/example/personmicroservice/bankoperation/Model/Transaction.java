package com.example.personmicroservice.bankoperation.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "accountnumber")
    private String accountNumber;

    @Column(name = "currency")
    private String currency;

    @Column(name = "transactionamount")
    private BigDecimal transactionAmount;

}