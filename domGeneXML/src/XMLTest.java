import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;



public class XMLTest {
    static String XMLPath = "domGeneXML\\StudentList.xml";
    static String XSDPath = "domGeneXML\\xsd\\StudentList.xsd";
    //static String XMLPath = "domGeneXML\\Student.xml";
    //static String xsdPath = "domGeneXML\\xsd\\Student.xsd";
    public static void main(String [] args){
        System.out.println(System.getProperty("user.dir"));//user.dirָ���˵�ǰ��·��
        validateXMLByXSD(XMLPath,XSDPath);
    }
    /**
     * ͨ��XSD��XML Schema��У��XML
     */
    public static void validateXMLByXSD(String xmlFileName,String xsdFileName) {
        try {
            //����Ĭ�ϵ�XML��������
            XMLErrorHandler errorHandler = new XMLErrorHandler();
            //��ȡ���� SAX �Ľ�������ʵ��
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //�������ڽ���ʱ��֤ XML ���ݡ�
            factory.setValidating(true);
            //ָ���ɴ˴������ɵĽ��������ṩ�� XML ���ƿռ��֧�֡�
            factory.setNamespaceAware(true);
            //ʹ�õ�ǰ���õĹ����������� SAXParser ��һ����ʵ����
            SAXParser parser = factory.newSAXParser();
            //����һ����ȡ����
            SAXReader xmlReader = new SAXReader();
            //��ȡҪУ��xml�ĵ�ʵ��
            Document xmlDocument = (Document) xmlReader.read(new File(xmlFileName));
            //���� XMLReader �Ļ���ʵ���е��ض����ԡ����Ĺ��ܺ������б������ [url]http://sax.sourceforge.net/?selected=get-set[/url] ���ҵ���
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource",
                    "file:" + xsdFileName);
            //����һ��SAXValidatorУ�鹤�ߣ�������У�鹤�ߵ�����
            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            //����У�鹤�ߵĴ�������������������ʱ�����ԴӴ����������еõ�������Ϣ��
            validator.setErrorHandler(errorHandler);
            //У��
            validator.validate(xmlDocument);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("GBK");
            XMLWriter writer = new XMLWriter(format);
            //���������Ϣ��Ϊ�գ�˵��У��ʧ�ܣ���ӡ������Ϣ
            if (errorHandler.getErrors().hasContent()) {
                System.out.println("XML�ļ�ͨ��XSD�ļ�У��ʧ�ܣ�");

                writer.write(errorHandler.getErrors());
            } else {
                System.out.println("Good! XML�ļ�ͨ��XSD�ļ�У��ɹ���");
            }
        } catch (Exception ex) {
            System.out.println("XML�ļ�: " + xmlFileName + " ͨ��XSD�ļ�:" + xsdFileName + "����ʧ�ܡ�\nԭ�� " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
