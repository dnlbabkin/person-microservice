package com.example.personmicroservice.bankservice.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "person_account")
public class PersonAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String account;

    @Embedded
    Income income;
}
