package com.javastudy.oop;

public class King implements Judge {

    private final Witness witness;

    public King(Witness witness) {
        this.witness = witness;
    }

    @Override
    public void conductTrial() {
        // 증인에게 증언하라고 시킴
        String statement = witness.state();
    }
}
