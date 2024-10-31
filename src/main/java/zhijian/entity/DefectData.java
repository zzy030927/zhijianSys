package zhijian.entity;


public class DefectData {
    private int id;
    private int defectAnalyze_id;
    private String productName;
    private String operator;
    private String defectId;
    private String defectType;
    private String area;
    private String state;
    private String coordinate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefectAnalyze_id() {
        return defectAnalyze_id;
    }

    public void setDefectAnalyze_id(int defectAnalyze_id) {
        this.defectAnalyze_id = defectAnalyze_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDefectId() {
        return defectId;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    public String getDefectType() {
        return defectType;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}

