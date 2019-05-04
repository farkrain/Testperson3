package com.example.testperson;

public class Person {
    private String name;
    private String surname;
    private int age;
    private String position;
    private char gender;

    public Person(String name, String surname, int age, String position, char gender)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
        this.gender = gender;
    }

    public String getName()
    {
        return this.name;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public String getPosition() {
        return this.position;
    }

    public char getGender() {
        return this.gender;
    }
}
