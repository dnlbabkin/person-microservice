package com.example.personmicroservice.bankoperation.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountStatementRequest {
    private String accountNumber;

}
