package com.javastudy.serialize;

import java.io.Serializable;

public class Eagle extends Bird implements Serializable {
    private String claw;

    public void setClaw(String claw) {
        this.claw = claw;
    }

    @Override
    public String toString() {
        return "Eagle{wing='" + super.getWing() + "', claw='" + claw + "'}";
    }

}
