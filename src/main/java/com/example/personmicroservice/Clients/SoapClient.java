package com.example.personmicroservice.Clients;

import com.example.personmicroservice.AllDataInfoXML;
import com.example.personmicroservice.AllDataInfoXMLResponse;
import com.example.personmicroservice.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
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
public class SoapClient extends WebServiceGatewaySupport {

    @Autowired
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate webServiceTemplate;

    private RestTemplate template;

    private static final String url = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";

    public Envelope getData () throws JAXBException {
        template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_XML));

        Envelope envelope = new Envelope();
        StringWriter writer = new StringWriter();

        envelope.setBody(new Envelope.Body());
        envelope.getBody().setAllDataInfoXML(new AllDataInfoXML());

        JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(envelope, writer);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        HttpEntity<Map<String, Object>> entity = new HttpEntity(writer.toString(), headers);

        ResponseEntity<String> response = template
                .exchange(url, HttpMethod.POST, entity, String.class);

        String responseXML = response.getBody();

        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Envelope dataInfoXMLResponse = (Envelope) unmarshaller.unmarshal(new StringReader(responseXML));

        return dataInfoXMLResponse;
    }
}
