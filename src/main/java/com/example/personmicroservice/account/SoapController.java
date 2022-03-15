package com.example.personmicroservice.account;

import com.example.personmicroservice.AllData;
import com.example.personmicroservice.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
public class SoapController {

    @Autowired
    private SoapClient soapClient;

    @PostMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE})
    public Envelope invokeSoapClient() throws JAXBException {
        Envelope env = soapClient.getData();
        env.getBody().getAllDataInfoXMLResponse().getAllDataInfoXMLResult()
                .getContent().get(0);

        return env;
    }

    @GetMapping(value = "/account/USD", produces = {MediaType.APPLICATION_XML_VALUE})
    public String returnUSD() throws JAXBException {
        AllData allData = new AllData();
        return allData.getMainIndicatorsVR().getCurrency().getUSD().getCurs();
    }
}
