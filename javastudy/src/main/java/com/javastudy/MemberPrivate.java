package com.javastudy;

public class MemberPrivate {
    private int age;
    private Phone phone;

    public MemberPrivate(int age, Phone phone) {
        this.age = age;
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    // 방어적 복사 메서드
    public MemberPrivate defensiveCopy() {
        return new MemberPrivate(age, phone);
    }

    static class Phone {

    }
}
