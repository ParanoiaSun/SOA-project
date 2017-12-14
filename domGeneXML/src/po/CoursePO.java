package po;

/**
 * @author tsnk
 * @since 13/12/2017.
 */
public class CoursePO {
    private String number;
    private String name;
    private String catalog;

    /**
     * @param number    课程编号
     * @param name      课程名称
     * @param catalog   课程分类
     */
    public CoursePO(String number, String name, String catalog) {
        this.number = number;
        this.name = name;
        this.catalog = catalog;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
