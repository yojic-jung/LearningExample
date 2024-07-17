package com.javastudy;

public class TeamMember extends Member {

    private int teamCode;

    public TeamMember(String email, String passwd, MemberPrivate memberPrivate, int teamCode) {
        super(email, passwd, memberPrivate);
        this.teamCode = teamCode;
    }

    public void setTeamCode(int teamCode) {
        this.teamCode = teamCode;
    }
}
