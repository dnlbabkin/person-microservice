package com.example.personmicroservice.bankservice.Entity;

import com.example.personmicroservice.AllData;
import com.example.personmicroservice.AllDataInfoXMLResponse;
import com.example.personmicroservice.Envelope;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal usd;
}
