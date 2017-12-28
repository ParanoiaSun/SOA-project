/**
 * Created by paranoia on 2017/12/27.
 */
package edu.nju.scoreQuery.servlets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.messaging.ReqRespListener;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/query")
public class QueryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String ns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage outgoingMessage = messageFactory.createMessage();
            SOAPPart soappart = outgoingMessage.getSOAPPart();
            SOAPEnvelope envelope = soappart.getEnvelope();
            SOAPHeader header = envelope.getHeader();
            SOAPBody body = envelope.getBody();
            QName eName = new QName(ns, "RDF", "rdf");   //<nn:add xmlns="ns" />
            SOAPBodyElement bodyElement = body.addBodyElement(eName);

            String filePath = QueryServlet.class.getResource("StudentList.xml").getPath();
            NodeList scoreList = readXML(id, filePath);
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
            outgoingMessage.writeTo(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
