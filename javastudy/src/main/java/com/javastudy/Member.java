package com.javastudy;

public class Member {
    private final String email;
    private final String passwd;
    private final MemberPrivate memberPrivate;

    public Member(String email, String passwd, MemberPrivate memberPrivate) {
        this.email = email;
        this.passwd = passwd;
        this.memberPrivate = memberPrivate;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public MemberPrivate getMemberPrivate() {
        return memberPrivate.defensiveCopy();
    }
}
