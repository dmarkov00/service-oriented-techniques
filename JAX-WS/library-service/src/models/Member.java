package models;

public class Member {
    private int memberID;
    private String name;
    private int age;

    public Member() {

    }


    public Member(int memberID, String name, int age) {
        this.memberID = memberID;
        this.name = name;
        this.age = age;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMemberID() {
        return memberID;
    }


    public int getAge() {
        return age;
    }


    public String getName() {
        return name;
    }

}
