package com.javastudy.serialize;

/**
 * json 직렬화 예제 샘플
 */
public class User {
    private String email;
    private String passwd;

    private MemberProfile userProfile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setUserProfile(MemberProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return "User{email='" + email + "', passwd='" + passwd + ", profile.gender='" + userProfile.getGender() + "'}";
    }
}
