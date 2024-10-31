package zhijian.entity;

import org.apache.poi.ss.formula.eval.UnaryMinusEval;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private String sex;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {this.sex = sex;}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
