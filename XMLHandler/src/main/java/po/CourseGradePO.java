/**
 * Created by paranoia on 2017/12/18.
 */

package po;

import java.util.List;

public class CourseGradePO {
    private String number;
    private String attribute;
    private List<GradePO> scores;

    /**
     * @param number  课程编号
     * @param attribute    成绩属性
     * @param scores 课程得分立标
     */
    public CourseGradePO(String number, String attribute, List<GradePO> scores) {
        this.number = number;
        this.attribute = attribute;
        this.scores = scores;
    }

    public CourseGradePO() {
        this.number = null;
        this.attribute = null;
        this.scores = null;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public List<GradePO> getScores() {
        return this.scores;
    }

    public void setScores(List<GradePO> scores) {
        this.scores = scores;
    }
}
