package com.example.personmicroservice.account;

import com.example.personmicroservice.AllDataInfoXML;
import com.example.personmicroservice.AllDataInfoXMLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoapController {

    @Autowired
    private SoapClient soapClient;

    @PostMapping("/account")
    public AllDataInfoXMLResponse invokeSoapClient(@RequestBody AllDataInfoXML request) {
        return soapClient.getData(request);
    }
}
