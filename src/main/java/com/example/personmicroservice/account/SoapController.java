package com.example.personmicroservice.account;


import com.example.personmicroservice.AllDataInfoXMLResponse;
import com.example.personmicroservice.model.USD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
public class SoapController {

    @Autowired
    private SoapClient soapClient;

    @PostMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AllDataInfoXMLResponse> invokeSoapClient(@RequestBody String request) throws JAXBException {
        return soapClient.getData(request);
    }

    @GetMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE})
    public USD result(USD usd){
        return usd;
    }
}
