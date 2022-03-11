package com.example.personmicroservice.account;

import com.example.personmicroservice.AllDataInfoXML;
import com.example.personmicroservice.AllDataInfoXMLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

@Service
public class SoapClient {

    private RestTemplate template;

    private static final String url = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";

    public ResponseEntity<AllDataInfoXMLResponse> getData (String request) throws JAXBException {
        template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_XML));

        AllDataInfoXML allDataInfoXML = new AllDataInfoXML();
        StringWriter writer = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(AllDataInfoXML.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(allDataInfoXML, writer);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        HttpEntity<Map<String, Object>> entity = new HttpEntity(writer.toString(), headers);

        ResponseEntity<String> response = template
                .exchange(url, HttpMethod.POST, entity, String.class);

        String responseXML = response.getBody();

        JAXBContext context = JAXBContext.newInstance(AllDataInfoXMLResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        AllDataInfoXMLResponse dataInfoXMLResponse = (AllDataInfoXMLResponse) unmarshaller.unmarshal(new StringReader(responseXML));

        return null;
    }
}
