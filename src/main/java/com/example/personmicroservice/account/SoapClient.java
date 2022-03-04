package com.example.personmicroservice.account;

import com.example.personmicroservice.AllDataInfoXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {

    @Autowired
    public Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;

    public AllDataInfoXML getData (AllDataInfoXML request) {
        template = new WebServiceTemplate(marshaller);

        AllDataInfoXML allDataInfoXML = (AllDataInfoXML) template.marshalSendAndReceive("http://www.cbr.ru/", request);

        return allDataInfoXML;
    }
}
