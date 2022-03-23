package com.example.personmicroservice.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person_account")
public class PersonAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal RUB;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal USD;
}
