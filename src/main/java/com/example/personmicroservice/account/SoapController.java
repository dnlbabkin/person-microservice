package com.example.personmicroservice.account;


import com.example.personmicroservice.AllDataInfoXML;
import com.example.personmicroservice.AllDataInfoXMLResponse;
import com.example.personmicroservice.model.USD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class SoapController {

    @Autowired
    private SoapClient soapClient;

    @PostMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE})
    public AllDataInfoXMLResponse invokeSoapClient(@RequestBody AllDataInfoXML request) {
        return soapClient.getData(request);
    }

    @GetMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE})
    public USD result(USD usd){
        return usd;
    }
}
