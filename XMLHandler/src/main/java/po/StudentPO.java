package po;

import java.util.List;

/**
 * @author tsnk
 * @since 13/12/2017.
 */
public class StudentPO {
    private String name;
    private String sex;
    private String birth;
    private String country;
    private String nation;
    private String nativePlace;
    private String id;
    private String phone;
    private String email;
    private String studentID;
    private int entranceYear;
    private DepartmentPO departmentPO;
    private List<CoursePO> courseList;
    private List<ScorePO> scoreList;

    /**
     * @param name         姓名
     * @param sex          性别
     * @param birth        出生日期
     * @param country      国籍
     * @param nation       民族
     * @param nativePlace  籍贯
     * @param id           身份证号
     * @param phone        手机
     * @param email        电子邮箱
     * @param studentID    学号
     * @param entranceYear 入学年份
     * @param departmentPO 院系部门
     * @param courseList   课程信息列表
     * @param scoreList    课程成绩列表
     */
    public StudentPO(String name, String sex, String birth, String country, String nation,
                     String nativePlace, String id, String phone, String email, String studentID,
                     int entranceYear, DepartmentPO departmentPO, List<CoursePO> courseList, List<ScorePO> scoreList) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.country = country;
        this.nation = nation;
        this.nativePlace = nativePlace;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.studentID = studentID;
        this.entranceYear = entranceYear;
        this.departmentPO = departmentPO;
        this.courseList = courseList;
        this.scoreList = scoreList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(int entranceYear) {
        this.entranceYear = entranceYear;
    }

    public DepartmentPO getDepartmentPO() {
        return departmentPO;
    }

    public void setDepartmentPO(DepartmentPO departmentPO) {
        this.departmentPO = departmentPO;
    }

    public List<CoursePO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CoursePO> courseList) {
        this.courseList = courseList;
    }

    public List<ScorePO> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<ScorePO> scoreList) {
        this.scoreList = scoreList;
    }
}
