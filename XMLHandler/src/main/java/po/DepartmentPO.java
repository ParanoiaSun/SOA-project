package po;

/**
 * @author tsnk
 * @since 13/12/2017.
 */
public class DepartmentPO {
    private String number;
    private String name;
    private String type;

    /**
     * @param number 院系部门编号
     * @param name   院系部门名称
     * @param type   院系种类编号
     */
    public DepartmentPO(String number, String name, String type) {
        this.number = number;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
