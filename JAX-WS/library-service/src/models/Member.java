package models;

public class Member {
    private int memberID;
    private String name;
    private int age;

    public Member(int memberID, String name, int age) {
        this.memberID = memberID;
        this.name = name;
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
