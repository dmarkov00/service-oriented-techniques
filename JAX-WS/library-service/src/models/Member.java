package models;

public class Member {
    private String name;
    private int age;
    private String password;

    public Member() {

    }


    public Member(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getAge() {
        return age;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
