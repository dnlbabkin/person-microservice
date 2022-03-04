package com.example.personmicroservice.account;

import com.example.personmicroservice.AllDataInfoXML;
import com.example.personmicroservice.AllDataInfoXMLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {

    @Autowired
    public Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;

    public AllDataInfoXMLResponse getData (AllDataInfoXML request) {
        template = new WebServiceTemplate(marshaller);

        AllDataInfoXMLResponse allDataInfoXML = (AllDataInfoXMLResponse) template.marshalSendAndReceive("http://danil-System-Product-Name:8088/mockDailyInfoSoap/", request);

        return allDataInfoXML;
    }
}
