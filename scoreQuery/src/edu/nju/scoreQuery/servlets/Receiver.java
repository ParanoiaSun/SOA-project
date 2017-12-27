package edu.nju.scoreQuery.servlets;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;


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
        } catch (SOAPException e) {  
            e.printStackTrace();  
        }  
          
    }  
  
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doGet(req, resp);  
    }  

}
