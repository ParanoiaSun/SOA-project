import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tsnk
 * @since 17/12/2017.
 *
 * Edit by paranoia on 18/12/2017
 * 这里实现了SAX解析XML
 */
public class SAXTransformer {

    private static final String XML_FILE = "./src/main/resources/ScoreList.xml";

    public static void main(String args[]) {
        try {
            SAXReader reader = new SAXReader();
            reader.setEncoding("utf-8");
            Document document = reader.read(XML_FILE);
            Element root = document.getRootElement();

            // 取得某节点下名为"课程成绩"的所有字节点
            List courseGradeList = root.elements("课程成绩");
            Element courseGrade;
            Element grade;
            List<String> students = new ArrayList<>();

            for (Object o1 : courseGradeList) {
                courseGrade = (Element) o1;
                List gradesList = courseGrade.elements("成绩");
                for(Object o2 : gradesList) {
                    grade = (Element) o2;
                    String score = grade.element("得分").getTextTrim();
                    String stuId = grade.element("学号").getTextTrim();
                    // 找出得分为60分及以上的同学并删除成绩节点
                    if(Integer.valueOf(score) < 60) {
                        students.add(stuId);
//                        grade.detach();
                    }
                }
            }

            for (Object o1 : courseGradeList) {
                courseGrade = (Element) o1;
                List gradesList = courseGrade.elements("成绩");
                for(Object o2 : gradesList) {
                    grade = (Element) o2;
                    String stuId = grade.element("学号").getTextTrim();
                    // 找出得分为60分及以上的同学并删除成绩节点
                    boolean delete = true;
                    for(String s : students) {
                        if(s.equals(stuId))
                            delete = false;
                    }
                    if(delete) {
                        grade.detach();
                    }
                }
            }

            OutputFormat format = new OutputFormat("    ",true);
            format.setEncoding("UTF-8");//设置编码格式
            format.setNewlines(true);
            format.setIndent(true);
            format.setTrimText(true);
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("./src/main/resources/NotPassScoreList.xml"),format);

            xmlWriter.write(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}