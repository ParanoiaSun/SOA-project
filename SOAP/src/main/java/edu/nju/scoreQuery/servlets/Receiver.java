package edu.nju.scoreQuery.servlets;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.activation.DataHandler;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Servlet implementation class showMyOrderServlet
 */
@WebServlet("/Receiver")
public class Receiver extends HttpServlet {
    private String ns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private String wsdlUrl = "http://localhost:8989/ms?wsdl";
    private static final long serialVersionUID = 1L;
    private SOAPConnection connection;
    private SOAPElementFactory factory;

    public Receiver() {
        super();
    }

    public void init() {
        try {
            factory = SOAPElementFactory.newInstance();
            SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
            connection = connectionFactory.createConnection();
        } catch (UnsupportedOperationException | SOAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String number = request.getParameter("number");//学号

        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage outgoingMessage = messageFactory.createMessage();
            SOAPPart soappart = outgoingMessage.getSOAPPart();
            SOAPEnvelope envelope = soappart.getEnvelope();
            SOAPHeader header = envelope.getHeader();
            SOAPBody body = envelope.getBody();
            QName eName = new QName(ns, "RDF", "rdf");   //<nn:add xmlns="ns" />
            SOAPBodyElement bodyElement = body.addBodyElement(eName);

            String filePath = Receiver.class.getResource("StudentList.xml").getPath();
            NodeList scoreList = readXML(number, filePath);
            for (int i = 0; i < scoreList.getLength(); i++) {//遍历节点
                Element element = (Element) scoreList.item(i);
                String scoreType = element.getAttribute("成绩性质");
                String course = element.getAttribute("课程编号");
                String score = element.getElementsByTagName("得分").item(0).getFirstChild().getNodeValue();
                SOAPElement scoreElement = bodyElement.addChildElement("课程成绩");
                scoreElement.setValue(score);
                scoreElement.addAttribute(envelope.createName("成绩性质"), scoreType);
                scoreElement.addAttribute(envelope.createName("课程编号"), course);
            }
            outgoingMessage.saveChanges();
            System.out.println();
            outgoingMessage.writeTo(System.out);
            System.out.println("\n invoking......");

            StringBuffer serverUrl = new StringBuffer();
            serverUrl.append(request.getScheme()).append("://").append(request.getServerName());
            serverUrl.append(":").append(request.getServerPort()).append(request.getContextPath());
            System.out.print("serverUrl: " + serverUrl);


            String baseUrl = serverUrl.toString();
            URL client = new URL(baseUrl + "/Client");
            SOAPMessage incomingMessage = connection.call(outgoingMessage, client);
            if (incomingMessage != null) {
                incomingMessage.writeTo(System.out);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    private static NodeList readXML(String number, String filePath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(filePath));
        NodeList list = document.getElementsByTagName("学生信息");
        NodeList scoreList = null;
        for (int i = 0; i < list.getLength(); ++i) {
            Element element = (Element) list.item(i);
            String content = element.getElementsByTagName("学号").item(0)
                    .getFirstChild().getNodeValue();
            if (!content.equals(number)) {
                continue;
            }
            scoreList = element.getElementsByTagName("课程成绩");
            System.out.print(scoreList.getLength());
        }
        return scoreList;
    }
}
