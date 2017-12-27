package edu.nju.scoreQuery.servlets;

import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;

import javax.activation.DataHandler;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.messaging.JAXMServlet;
import javax.xml.messaging.ReqRespListener;
import javax.xml.soap.AttachmentPart;  
import javax.xml.soap.MessageFactory;  
import javax.xml.soap.SOAPBody;  
import javax.xml.soap.SOAPConnection;  
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;  
import javax.xml.soap.SOAPException;  
import javax.xml.soap.SOAPHeader;  
import javax.xml.soap.SOAPMessage;  
import javax.xml.soap.SOAPPart;  
  
@WebServlet("/Client")
public class Client extends HttpServlet  implements ReqRespListener{  
  
    private static final long serialVersionUID = 1L;  
  
    private SOAPConnection connection;  
    private static MessageFactory messageFactory = null;  
    @Override  
    public void init() throws ServletException {  
        super.init();  
        try {  
        	//创建连接工厂
            SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();  
            messageFactory = MessageFactory.newInstance();  
            connection = connectionFactory.createConnection();  
        } catch (UnsupportedOperationException e) {  
            e.printStackTrace();  
        } catch (SOAPException e) {  
            e.printStackTrace();  
        }  
    }  
    //使用传递学号给用户
    @Override  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>"
        		+ "<p name='tip'>请输入学号</p>");

        out.println(
                "<form method='POST' action='"
                    + response.encodeURL(request.getContextPath()+"/Receiver")
                    + "'>");
        out.println(
            " <input type='text' name='number' value=''>");
        out.println("<input type='submit' name='Submit' value='查询'>");
        out.println("</form></body></html>");
    }  
  
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
    }
	@Override
	public SOAPMessage onMessage(SOAPMessage msg) {
		SOAPPart soappart = msg.getSOAPPart();  
        try {  
            SOAPEnvelope incomingEnvelope;        
            incomingEnvelope = soappart.getEnvelope();        
            SOAPBody body = incomingEnvelope.getBody();  
      
            Iterator<?> iterator = body.getChildElements(incomingEnvelope.createName("numberAvailable", "laptops", "http://ecodl.taobao.com/"));  
      
            SOAPElement element;  
            element = (SOAPElement) iterator.next();  
      
            SOAPMessage message = messageFactory.createMessage();  
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();  
      
            SOAPBody responsebody = envelope.getBody();  
            String responseText = "Got the SOAP message indicating there are " + element.getValue() + " laptops available.";  
            responsebody.addChildElement(envelope.createName("Response")).addTextNode(responseText);  
      
            return message;  
        } catch (SOAPException e) {  
            e.printStackTrace();  
            return null;  
        }  
	}  
  
      
}  