package com.example.personmicroservice.account;

import com.example.personmicroservice.AllDataInfoXML;
import com.example.personmicroservice.AllDataInfoXMLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class SoapClient {

    @Autowired
    public Jaxb2Marshaller marshaller;

    private RestTemplate template;

    private static final String url = "http://danil-System-Product-Name:8088/mockDailyInfoSoap";

    public ResponseEntity<AllDataInfoXMLResponse> getData (AllDataInfoXML request) {
        template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

        HttpEntity<Map<AllDataInfoXML, Object>> entity = new HttpEntity(request, headers);

        ResponseEntity<AllDataInfoXMLResponse> allDataInfoXML = template
                .exchange(url, HttpMethod.POST, entity, AllDataInfoXMLResponse.class);

        return allDataInfoXML;
    }
}
