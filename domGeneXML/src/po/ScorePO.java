package po;

/**
 * @author tsnk
 * @since 13/12/2017.
 */
public class ScorePO {
    private String courseNumber;
    private String type;
    private String studentID;
    private int score;

    public ScorePO(String course_number, String type, String studentID, int score) {
        this.courseNumber = course_number;
        this.type = type;
        this.studentID = studentID;
        this.score = score;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
