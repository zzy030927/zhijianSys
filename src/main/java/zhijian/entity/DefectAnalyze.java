package zhijian.entity;


public class DefectAnalyze {
    private int id;
    private int defectAnalyze_id;
    private String productName;
    private String operator;
    private String checkParameters;
    private String defectTotal;
    private String qualifiedNumber;
    private String notQualifiedNumber;
    private String defectBackground;
    private String defectTexture;
    private String checkTime;

    public int getDefectAnalyze_id() {
        return defectAnalyze_id;
    }

    public void setDefectAnalyze_id(int defectAnalyze_id) {
        this.defectAnalyze_id = defectAnalyze_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCheckParameters() {
        return checkParameters;
    }

    public void setCheckParameters(String checkParameters) {
        this.checkParameters = checkParameters;
    }

    public String getDefectTotal() {
        return defectTotal;
    }

    public void setDefectTotal(String defectTotal) {
        this.defectTotal = defectTotal;
    }

    public String getQualifiedNumber() {
        return qualifiedNumber;
    }

    public void setQualifiedNumber(String qualifiedNumber) {
        this.qualifiedNumber = qualifiedNumber;
    }

    public String getNotQualifiedNumber() {
        return notQualifiedNumber;
    }

    public void setNotQualifiedNumber(String notQualifiedNumber) {
        this.notQualifiedNumber = notQualifiedNumber;
    }

    public String getDefectBackground() {
        return defectBackground;
    }

    public void setDefectBackground(String defectBackground) {
        this.defectBackground = defectBackground;
    }

    public String getDefectTexture() {
        return defectTexture;
    }

    public void setDefectTexture(String defectTexture) {
        this.defectTexture = defectTexture;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
}

