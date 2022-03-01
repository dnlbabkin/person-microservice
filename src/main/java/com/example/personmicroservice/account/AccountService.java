package com.example.personmicroservice.account;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;

public class AccountService {

    public StreamResult message() throws SOAPException, TransformerException {
        SOAPMessage soapMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();

        SOAPPart part = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = part.getEnvelope();
        SOAPBody body = envelope.getBody();
        Name bodyName = envelope.createName("AllDataInfoXML", null, "http://web.cbr.ru/");
        body.addBodyElement(bodyName);
        soapMessage.saveChanges();

        String destination = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = soapConnectionFactory.createConnection();

        SOAPMessage reply = connection.call(soapMessage, destination);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        Source sourceContent = reply.getSOAPPart().getContent();

        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);

        return result;
    }
}
