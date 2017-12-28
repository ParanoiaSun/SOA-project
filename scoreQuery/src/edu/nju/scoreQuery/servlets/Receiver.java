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
	private static final long serialVersionUID = 1L;
	private SOAPConnection connection;  
    public Receiver() {
        super();
    }
    
    public void init() {
        try {  
            SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();  
            connection = connectionFactory.createConnection();  
        } catch (UnsupportedOperationException e) {  
            e.printStackTrace();  
        } catch (SOAPException e) {  
            e.printStackTrace();  
        }  
	}
    
    @Override  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	String number=request.getParameter("number");//学号

        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage outgoingMessage = messageFactory.createMessage();
            SOAPPart soappart = outgoingMessage.getSOAPPart();
            SOAPEnvelope envelope = soappart.getEnvelope();
            SOAPHeader header = envelope.getHeader();
            SOAPBody body = envelope.getBody();
            NodeList scoreList=readXML(number);
            for(int i=0;i<scoreList.getLength();i++){//遍历节点
                Element element=(Element)scoreList.item(i);
                String scoreType=element.getAttribute("成绩性质");
                String course=element.getAttribute("课程编号");
                String score=element.getElementsByTagName("得分").item(0).getFirstChild().getNodeValue();

                //生成soapmessage
            }



            body.addBodyElement(envelope.createName("numberAvailable", "laptops", "http://ecodl.taobao.com/")).addTextNode("216");  

            StringBuffer serverUrl = new StringBuffer();  
            serverUrl.append(request.getScheme()).append("://").append(request.getServerName());  
            serverUrl.append(":").append(request.getServerPort()).append(request.getContextPath());  
            String baseUrl = serverUrl.toString();  
            URL url = new URL(baseUrl + "/test.html");  
            AttachmentPart attachmentpart = outgoingMessage.createAttachmentPart(new DataHandler(url));  
            attachmentpart.setContentType("text/html");  
            outgoingMessage.addAttachmentPart(attachmentpart);  
            URL client = new URL(baseUrl + "/Client");  
            SOAPMessage incomingMessage = connection.call(outgoingMessage, client);  
            incomingMessage.writeTo(System.out);
        } catch (Exception e) {
            e.printStackTrace();  
        }  
          
    }  
  
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doGet(req, resp);  
    }

    private static NodeList readXML(String number)throws  Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File("StudentList.xml"));
        NodeList list = document.getElementsByTagName("学生信息");
        NodeList scoreList=null;
        for (int i = 0; i < list.getLength(); ++i)
        {
            Element element = (Element) list.item(i);
            String content= element.getElementsByTagName("学号").item(0)
                    .getFirstChild().getNodeValue();
            if (!content.equals(number)){
                continue;
            }
            scoreList= element.getElementsByTagName("课程成绩");
            System.out.print(scoreList.getLength());
        }
        return scoreList;
    }
}
