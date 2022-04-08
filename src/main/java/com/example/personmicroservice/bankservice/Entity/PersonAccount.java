package com.example.personmicroservice.bankservice.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "person_account")
public class PersonAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String account;

    String currency;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigDecimal amount;
}
