import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * @author tsnk
 * @since 16/12/2017.
 */
public class TransformXML {
    public static void main(String[] args) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            // get XSLT file
            Source stylesheetSource = new StreamSource(new File("src/main/resources/transform.xsl").getAbsoluteFile());
            Transformer transformer = factory.newTransformer(stylesheetSource);
            // get Input XML file
            String inputPathname = "src/main/resources/StudentList.xml";
            Source inputSource = new StreamSource(new File(inputPathname).getAbsoluteFile());
            // set Output XML result file
            String outputPathname = "src/main/resources/ScoreList.xml";
            Result outputResult = new StreamResult(new File(outputPathname).getAbsoluteFile());
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            // Transform
            transformer.transform(inputSource, outputResult);
        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }

}
