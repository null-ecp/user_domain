package bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户对象
 * 用于封装用户信息
 */
public class user {
    @JsonIgnore
    private int id;
    private String uname;
    @JsonIgnore
    private String sex;
    @JsonIgnore
    private int age;
    @JsonIgnore
    private String address;
    private String email;

    static String test = "test";

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
