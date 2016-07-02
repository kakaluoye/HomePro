package com.lin.threelayersqlitemodal.models;

/**
 * Created by my on 2016/6/25.
 */
public class Student {
    private int stuNo;
    private String name;
    private String address;
    private double money;
    private int age;

    public Student() {
    }

    public Student(int stuNo, String name, String address, double money, int age) {
        this.stuNo = stuNo;
        this.name = name;
        this.address = address;
        this.money = money;
        this.age = age;
    }

    @Override
    public String toString() {
        return "student{" +
                "address='" + address + '\'' +
                ", stuNo=" + stuNo +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", age=" + age +
                '}';
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

