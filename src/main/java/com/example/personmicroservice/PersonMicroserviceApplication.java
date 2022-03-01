package com.example.personmicroservice;

import com.example.personmicroservice.account.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@SpringBootApplication
public class PersonMicroserviceApplication {

	public static void main(String[] args) throws SOAPException, TransformerException {
		SpringApplication.run(PersonMicroserviceApplication.class, args);

		AccountService accountService = new AccountService();
		System.out.println(accountService.message());
	}

}
