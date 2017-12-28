package edu.nju.scoreQuery;

import javax.xml.soap.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author tsnk
 * @since 29/12/2017.
 */
public class ModifyScore {
    public static void main(String[] args) throws IOException, SOAPException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = new String[4];
        // TODO uncomment before commit
//        System.out.print("学生学号: ");
//        params[0] = br.readLine();
//        System.out.print("课程编号: ");
//        params[1] = br.readLine();
//        System.out.print("成绩性质: ");
//        params[2] = br.readLine();
//        System.out.print("分数: ");
//        params[3] = br.readLine();
        // TODO delete before commit
        params[0] = "151250127";
        params[1] = "25000080";
        params[2] = "平时成绩";
        params[3] = "100";

        SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage outgoingMessage = MessageFactory.newInstance().createMessage();

        SOAPEnvelope envelope = outgoingMessage.getSOAPPart().getEnvelope();
        envelope.addNamespaceDeclaration("tns", "http://jw.nju.edu.cn/schema");

        SOAPHeader header = envelope.getHeader();

        SOAPBody body = envelope.getBody();
        SOAPElement score = body.addBodyElement(envelope.createQName("课程成绩", "tns"));
        score.addAttribute(envelope.createQName("成绩性质", "tns"), params[2]);
        score.addAttribute(envelope.createQName("课程编号", "tns"), params[1]);
        score.addChildElement("学号", "tns").addTextNode(params[0]);
        score.addChildElement("得分", "tns").addTextNode(params[3]);

        URL url = new URL("http://localhost:8080/modify");

        SOAPMessage incomingMessage = connection.call(outgoingMessage, url);
        if (incomingMessage != null) {
            // Print the message to console
            System.out.println("Receive SOAP message from localhost:");
            incomingMessage.writeTo(System.out);
        } else {
            System.err.println("No response received from partner!");
        }

        connection.close();
    }
}
