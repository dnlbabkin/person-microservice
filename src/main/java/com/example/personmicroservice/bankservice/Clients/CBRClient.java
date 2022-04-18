package com.example.personmicroservice.bankservice.Clients;

import com.example.personmicroservice.AllData;
import com.example.personmicroservice.AllDataInfoXML;
import com.example.personmicroservice.Envelope;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CBRClient extends WebServiceGatewaySupport {

    private WebServiceTemplate webServiceTemplate;
    private final RestTemplate restTemplate;

    @Value("${cbr.url}")
    private String url;

    private HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_XML));

        return headers;
    }

    private void makeMarshaller(Envelope envelope, StringWriter writer) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(envelope, writer);
    }

    private Unmarshaller makeUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller;
    }

    public AllData.MainIndicatorsVR getData() throws JAXBException {
        HttpHeaders headers = makeHeaders();

        Envelope envelope = new Envelope();
        StringWriter writer = new StringWriter();

        envelope.setBody(new Envelope.Body());
        envelope.getBody().setAllDataInfoXML(new AllDataInfoXML());

        makeMarshaller(envelope, writer);

        HttpEntity<Map<String, Object>> entity = new HttpEntity(writer.toString(), headers);

        ResponseEntity<String> response = restTemplate
                .exchange(url, HttpMethod.POST, entity, String.class);

        String responseXML = response.getBody();

        Envelope dataInfoXMLResponse = (Envelope) makeUnmarshaller().unmarshal(new StringReader(responseXML));

        return dataInfoXMLResponse.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData().getMainIndicatorsVR();
    }
}
