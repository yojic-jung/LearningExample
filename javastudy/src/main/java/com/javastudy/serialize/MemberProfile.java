package com.javastudy.serialize;

import java.io.Serializable;

public class MemberProfile implements Serializable {
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
