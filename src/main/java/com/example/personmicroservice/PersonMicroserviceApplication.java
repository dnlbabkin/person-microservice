package com.example.personmicroservice;

import com.example.personmicroservice.account.AllDataInfoJAXB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

@SpringBootApplication
public class PersonMicroserviceApplication {

	public static void main(String[] args) throws SOAPException, TransformerException {
		SpringApplication.run(PersonMicroserviceApplication.class, args);
	}

}
