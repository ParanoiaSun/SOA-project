/**
 * Created by paranoia on 2017/12/18.
 */

package po;

public class GradePO {
    private String studentID;
    private String score;

    /**
     * @param studentID  学生学号
     * @param score    得分
     */
    public GradePO(String studentID, String score) {
        this.studentID = studentID;
        this.score = score;
    }

    public GradePO() {
        this.studentID = null;
        this.score = null;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
