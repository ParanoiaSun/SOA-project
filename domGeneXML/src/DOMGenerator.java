import org.w3c.dom.Document;
import org.w3c.dom.Element;
import po.CoursePO;
import po.ScorePO;
import po.StudentPO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @author tsnk
 * @since 13/12/2017.
 */
public class DOMGenerator {
    public static void generateXML(List<StudentPO> students) {
        File file = new File("StudentList.xml");
        generateXML(file, students);
    }

    public static void generateXML(File file, List<StudentPO> students) {
        // 创建DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {

            // 创建DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 创建Document
            Document document = builder.newDocument();

            // 设置XML声明中standalone为yes，即没有dtd和schema作为该XML的说明文档，且不显示该属性
            // document.setXmlStandalone(true);

            // 创建根节点
            Element studentList = document.createElement("学生列表");
            studentList.setAttribute("xmlns", "http://jw.nju.edu.cn/schema");
            studentList.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            studentList.setAttribute("xsi:schemaLocation", "http://jw.nju.edu.cn/schema");

            Iterator<StudentPO> it = students.iterator();

            while (it.hasNext()) {
                StudentPO stu = it.next();

                Element student = document.createElement("学生信息");

                // 创建子节点，并设置属性
                Element person = document.createElement("基本信息");
                // 为person添加子节点
                Element name = document.createElement("姓名");
                name.setTextContent(stu.getName());
                person.appendChild(name);
                Element sex = document.createElement("性别");
                sex.setTextContent(stu.getSex());
                person.appendChild(sex);
                Element birth = document.createElement("出生日期");
                birth.setTextContent(stu.getBirth());
                person.appendChild(birth);
                Element country = document.createElement("国籍");
                country.setTextContent(stu.getCountry());
                person.appendChild(country);
                Element nation = document.createElement("民族");
                nation.setTextContent(stu.getNation());
                person.appendChild(nation);
                Element place = document.createElement("籍贯");
                place.setTextContent(stu.getNativePlace());
                person.appendChild(place);
                Element identity = document.createElement("身份证号");
                identity.setTextContent(stu.getId());
                person.appendChild(identity);
                Element phone = document.createElement("手机");
                phone.setTextContent(stu.getPhone());
                person.appendChild(phone);
                Element mail = document.createElement("电子邮箱");
                mail.setTextContent(stu.getEmail());
                person.appendChild(mail);
                //person中的子节点院系部门
                Element department = document.createElement("院系部门");
                Element departmentID = document.createElement("院系部门编号");
                departmentID.setTextContent(stu.getDepartmentPO().getNumber());
                department.appendChild(departmentID);
                Element departmentName = document.createElement("院系部门名称");
                departmentName.setTextContent(stu.getDepartmentPO().getName());
                department.appendChild(departmentName);
                Element depClassification = document.createElement("院系种类编号");
                depClassification.setTextContent(stu.getDepartmentPO().getType());
                department.appendChild(depClassification);
                person.appendChild(department);
                // 为根节点添加子节点
                student.appendChild(person);


                Element studentID = document.createElement("学号");
                studentID.setTextContent(stu.getStudentID());
                student.appendChild(studentID);
                Element studyYear = document.createElement("入学年份");
                studyYear.setTextContent(Integer.toString(stu.getEntranceYear()));
                student.appendChild(studyYear);

                Element courseList = document.createElement("课程信息列表");
                for (CoursePO coursePO :
                        stu.getCourseList()) {
                    Element course = document.createElement("课程信息");
                    Element courseID = document.createElement("课程编号");
                    courseID.setTextContent(coursePO.getNumber());
                    course.appendChild(courseID);
                    Element courseName = document.createElement("课程名称");
                    courseName.setTextContent(coursePO.getName());
                    course.appendChild(courseName);
                    Element courseClass = document.createElement("课程分类");
                    courseClass.setTextContent(coursePO.getCatalog());
                    course.appendChild(courseClass);
                    courseList.appendChild(course);
                }
                student.appendChild(courseList);

                Element courseScoreList = document.createElement("课程成绩列表");
                for (ScorePO scorePO :
                        stu.getScoreList()) {
                    Element courseScore = document.createElement("课程成绩");
                    courseScore.setAttribute("课程编号", scorePO.getCourseNumber());
                    courseScore.setAttribute("成绩性质", scorePO.getType());

                    Element score = document.createElement("成绩");

                    Element studentId = document.createElement("学号");
                    studentId.setTextContent(stu.getStudentID());

                    score.appendChild(studentId);

                    Element grade = document.createElement("得分");
                    grade.setTextContent(Integer.toString(scorePO.getScore()));

                    score.appendChild(grade);
                    courseScore.appendChild(score);

                    courseScoreList.appendChild(courseScore);
                }
                student.appendChild(courseScoreList);

                // 将根节点添加到Document下
                studentList.appendChild(student);
            }

            document.appendChild(studentList);

            /*
             * 下面开始实现： 生成XML文件
             */

            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();

            // 创建Transformer对象
            Transformer tf = tff.newTransformer();

            // 设置输出数据时换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // 使用Transformer的transform()方法将DOM树转换成XML
            tf.transform(new DOMSource(document), new StreamResult(file));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
