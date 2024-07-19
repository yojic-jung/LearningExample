package com.javastudy.serialize;

import java.io.Serializable;

/**
 * def. 자바 직렬화와 역직렬화 예제 샘플
 */
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    private String emails;

    // transient 키워드 사용시 직렬화에 포함 안됨
    transient private String passwd;

    private MemberProfile memberProfile;


    public void setEmail(String email) {
        this.emails = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setMemberProfile(MemberProfile memberProfile) {
        this.memberProfile = memberProfile;
    }

    @Override
    public String toString() {
        return "Member{email='" + emails + "', passwd='" + passwd + ", profile.gender='" + memberProfile.getGender() + "'}";
    }
}
