package com.seb.anime.config.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Emp")
@XmlType(propOrder = {"name", "age", "role", "gender"})
@SuppressWarnings("unused")
public class Employee {

    private int id;
    private String name;
	private Company company;
    private Gianni<?> gianni;
    private Gianni<String> gianni2;

    private String gender;

    private int age;
    private String role;

    private String password;


    @XmlTransient
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    @XmlAttribute
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @XmlElement(name = "gen")
    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "ID = " + id + " NAME=" + name + " AGE=" + age + " GENDER=" + gender + " ROLE=" +
                role + " PASSWORD=" + password;
    }
}