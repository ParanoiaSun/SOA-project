package edu.nju.scoreQuery.servlets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.annotation.WebServlet;
import javax.xml.messaging.JAXMServlet;
import javax.xml.messaging.ReqRespListener;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;

/**
 * @author tsnk
 * @since 29/12/2017.
 */
@WebServlet("/modify")
public class ModifyServlet extends JAXMServlet implements ReqRespListener {
    private String ns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Override
    public SOAPMessage onMessage(SOAPMessage soapMessage) {
        SOAPMessage resp = null;
        try {
            resp = MessageFactory.newInstance().createMessage();

            SOAPBody reqBody = soapMessage.getSOAPPart().getEnvelope().getBody();
            String type = reqBody.getElementsByTagName("tns:课程成绩").item(0).getAttributes().item(0).getTextContent();
            String course = reqBody.getElementsByTagName("tns:课程成绩").item(0).getAttributes().item(1).getTextContent();
            String id = reqBody.getElementsByTagName("tns:学号").item(0).getTextContent();
            String score = reqBody.getElementsByTagName("tns:得分").item(0).getTextContent();

            SOAPEnvelope envelope = resp.getSOAPPart().getEnvelope();
            SOAPHeader header = envelope.getHeader();
            SOAPBody body = envelope.getBody();
            QName eName = new QName(ns, "RDF", "rdf");   //<nn:add xmlns="ns" />
            SOAPBodyElement bodyElement = body.addBodyElement(eName);

            if (type == null || course == null || id == null || score == null) {
                body.addFault(envelope.createName("Client"), "传入参数错误");
            } else {
                NodeList scoreList = modifyXML(type, course, id, score);
                if (scoreList == null) {
                    body.addFault(envelope.createName("Server"), "服务器错误 或 该成绩信息不存在");
                } else {
                    for (int i = 0; i < scoreList.getLength(); i++) {//遍历节点
                        Element element = (Element) scoreList.item(i);
                        SOAPElement scoreElement = bodyElement.addChildElement("课程成绩");
                        scoreElement.setValue(element.getElementsByTagName("得分").item(0).getFirstChild().getNodeValue());
                        scoreElement.addAttribute(envelope.createName("成绩性质"), element.getAttribute("成绩性质"));
                        scoreElement.addAttribute(envelope.createName("课程编号"), element.getAttribute("课程编号"));
                    }
                }
            }
            resp.saveChanges();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    private static NodeList modifyXML(String type, String course, String id, String score) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(ModifyServlet.class.getResource("StudentList.xml").getFile());
        NodeList list = document.getElementsByTagName("学生信息");
        NodeList scoreList = null;
        for (int i = 0; i < list.getLength(); ++i) {
            Element element = (Element) list.item(i);
            String content = element.getElementsByTagName("学号").item(0)
                    .getFirstChild().getNodeValue();
            if (!content.equals(id)) {
                continue;
            }
            scoreList = element.getElementsByTagName("课程成绩");
        }
        if (scoreList != null) {
            for (int i = 0; i < scoreList.getLength(); i++) {
                Element element = (Element) scoreList.item(i);
                if (element.getAttribute("成绩性质").equals(type)
                        && element.getAttribute("课程编号").equals(course)) {
                    element.getElementsByTagName("得分").item(0).setTextContent(score);
                }
            }
        }
        return scoreList;
    }
}
