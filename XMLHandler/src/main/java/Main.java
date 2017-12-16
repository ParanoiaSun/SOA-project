import po.CoursePO;
import po.DepartmentPO;
import po.ScorePO;
import po.StudentPO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 部门
        DepartmentPO software = new DepartmentPO("00001250", "软件学院", "院系");

        // 课程
        List<CoursePO> courseList = new ArrayList<>();
        courseList.add(new CoursePO("25000080", "软件构造", "指选"));
        courseList.add(new CoursePO("25000090", "需求工程", "指选"));
        courseList.add(new CoursePO("25000110", "软件系统设计与体系结构", "指选"));
        courseList.add(new CoursePO("25010350", "J2EE中间件", "自选"));
        courseList.add(new CoursePO("25000370", "Linux系统基础", "自选"));

        List<List<ScorePO>> allScores = new ArrayList<>();

        int[][] scores_normal = new int[][]{
                {85, 50, 90, 75, 83},
                {75, 70, 80, 72, 93},
                {95, 53, 90, 75, 83},
                {85, 76, 90, 75, 93},
                {85, 90, 90, 75, 73},
                {95, 78, 90, 75, 63},
                {85, 72, 90, 75, 83},
                {85, 80, 90, 65, 83},
                {85, 83, 90, 75, 83},
                {85, 65, 90, 85, 62},
                {85, 90, 90, 55, 73},
                {85, 60, 90, 75, 78},
                {85, 72, 90, 75, 82},
        };

        int[][] scores_final = new int[][]{
                {72, 55, 84, 64, 91},
                {88, 66, 77, 87, 73},
                {91, 51, 75, 67, 83},
                {68, 76, 84, 56, 77},
                {69, 90, 64, 87, 86},
                {79, 78, 85, 87, 67},
                {89, 72, 75, 64, 91},
                {57, 80, 85, 86, 76},
                {69, 83, 75, 64, 89},
                {89, 65, 64, 75, 59},
                {97, 90, 86, 53, 71},
                {87, 60, 97, 74, 79},
                {65, 72, 64, 42, 88},
        };

        String[] id = new String[]{
                "151250127", "151250214", "151250170", "151250007", "151250052", "151250134", "151250036", "151250162",
                "151250178", "151250070", "151250120", "151250211"
        };

        for (int i = 0; i < 12; i++)
            allScores.add(getScorePO(id[i], scores_normal[i], scores_final[i]));

        List<StudentPO> students = new ArrayList<>();

        // 第八组
        StudentPO group8stu1 = new StudentPO("孙皓", "女", "1997-06-09", "中国", "汉族",
                "江苏盐城", "321102199706091026", "15105175412", "1369664817@qq.com", "151250127",
                2015, software, courseList, allScores.get(0));
        StudentPO group8stu2 = new StudentPO("朱应山", "男", "1996-10-04", "中国", "苗族",
                "贵州铜仁", "522225199610047539", "15895873801", "313437903@qq.com", "151250214",
                2015, software, courseList, allScores.get(1));
        StudentPO group8stu3 = new StudentPO("辛志庭", "男", "1997-08-28", "中国", "汉族",
                "山西运城", "142726199708280972", "15951926228", "1835427462@qq.com", "151250170",
                2015, software, courseList, allScores.get(2));
        StudentPO group8stu4 = new StudentPO("曹利航", "男", "1996-12-15", "中国", "汉族",
                "江苏南通", "320202199612153518", "15122740731", "151250007@smail.nju.edu.cn", "151250007",
                2015, software, courseList, allScores.get(3));
        students.add(group8stu1);
        students.add(group8stu2);
        students.add(group8stu3);
        students.add(group8stu4);

        // 第七组
        StudentPO group7stu1 = new StudentPO("何林洋", "男", "1997-11-29", "中国", "汉族",
                "宁夏银川", "640103199611291236", "13115017398", "1441732331@qq.com", "151250052",
                2015, software, courseList, allScores.get(4));
        StudentPO group7stu2 = new StudentPO("田原", "男", "1996-09-09", "中国", "汉族",
                "江苏高邮", "321084199709090415", "13655255834", "1395881075@qq.com", "151250134",
                2015, software, courseList, allScores.get(5));
        StudentPO group7stu3 = new StudentPO("冯超", "男", "1997-01-25", "中国", "汉族",
                "海南海口", "460004199808250056", "13151085228", "151250036@smail.nju.edu.cn", "151250036",
                2015, software, courseList, allScores.get(6));
        StudentPO group7stu4 = new StudentPO("吴宇涵", "女", "1997-04-24", "中国", "汉族",
                "山东烟台", "370602199704242922", "17714363061", "1426545185@qq.com", "151250162",
                2015, software, courseList, allScores.get(7));
        students.add(group7stu1);
        students.add(group7stu2);
        students.add(group7stu3);
        students.add(group7stu4);

        // 第九组
        StudentPO group9stu1 = new StudentPO("鄢煜民", "男", "1997-11-16", "中国", "汉族",
                "江西樟树", "439291199711163451", "13672934583", "151250178@nju.edu.cn", "151250178",
                2015, software, courseList, allScores.get(8));
        StudentPO group9stu2 = new StudentPO("蒋健聪", "男", "1996-03-12", "中国", "汉族",
                "广东中山", "271102199703127532", "15102812834", "151250070@nju.edu.cn", "151250070",
                2015, software, courseList, allScores.get(9));
        StudentPO group9stu3 = new StudentPO("邱昌政", "男", "1998-07-05", "中国", "布依族",
                "贵州", "232102199807053391", "18923021778", "151250120@nju.edu.cn", "151250120",
                2015, software, courseList, allScores.get(10));
        StudentPO group9stu4 = new StudentPO("朱劲", "男", "1997-04-18", "中国", "汉族",
                "江苏泰州", "492102199704182341", "13822710981", "151250211@nju.edu.cn", "151250211",
                2015, software, courseList, allScores.get(11));
        students.add(group9stu1);
        students.add(group9stu2);
        students.add(group9stu3);
        students.add(group9stu4);

        DOMGenerator.generateXML(students);
    }

    public static List<ScorePO> getScorePO(String id, int[] score_normal, int[] score_final) {
        List<ScorePO> scoreList = new ArrayList<>();
        scoreList.add(new ScorePO("25000080", "平时成绩", id, score_normal[0]));
        scoreList.add(new ScorePO("25000090", "平时成绩", id, score_normal[1]));
        scoreList.add(new ScorePO("25000110", "平时成绩", id, score_normal[2]));
        scoreList.add(new ScorePO("25000350", "平时成绩", id, score_normal[3]));
        scoreList.add(new ScorePO("25000370", "平时成绩", id, score_normal[4]));
        scoreList.add(new ScorePO("25000080", "期末成绩", id, score_final[0]));
        scoreList.add(new ScorePO("25000090", "期末成绩", id, score_final[1]));
        scoreList.add(new ScorePO("25000110", "期末成绩", id, score_final[2]));
        scoreList.add(new ScorePO("25000350", "期末成绩", id, score_final[3]));
        scoreList.add(new ScorePO("25000370", "期末成绩", id, score_final[4]));
        scoreList.add(new ScorePO("25000080", "总评成绩", id, (int) (score_normal[0] * 0.4 + score_final[0] * 0.6)));
        scoreList.add(new ScorePO("25000090", "总评成绩", id, (int) (score_normal[1] * 0.4 + score_final[1] * 0.6)));
        scoreList.add(new ScorePO("25000110", "总评成绩", id, (int) (score_normal[2] * 0.4 + score_final[2] * 0.6)));
        scoreList.add(new ScorePO("25000350", "总评成绩", id, (int) (score_normal[3] * 0.4 + score_final[3] * 0.6)));
        scoreList.add(new ScorePO("25000370", "总评成绩", id, (int) (score_normal[4] * 0.4 + score_final[4] * 0.6)));
        return scoreList;
    }
}
