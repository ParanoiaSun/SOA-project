import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 * @author tsnk
 * @since 16/12/2017.
 */
public class TransformXML {
    public static void main(String[] args) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            // get XSLT file
            Source stylesheetSource = new StreamSource(new File("transform.xsl").getAbsoluteFile());
            Transformer transformer = factory.newTransformer(stylesheetSource);
            // get Input XML file
            String inputPathname = "StudentList.xml";
            Source inputSource = new StreamSource(new File(inputPathname).getAbsoluteFile());
            // set Output XML result file
            String outputPathname = "ScoreList.xml";
            Result outputResult = new StreamResult(new File(outputPathname).getAbsoluteFile());
            // Transform
            transformer.transform(inputSource, outputResult);
        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }

}
