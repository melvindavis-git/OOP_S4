package OOP_4_5;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private int age;
    private int height;
    private String address;
    private String phonenumber;

    public Person(String name, int age, int height, String address, String phonenumber) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return getName()+" "+getAge()+" "+getHeight()+" "+getAddress()+" "+getPhonenumber();
    }
}
