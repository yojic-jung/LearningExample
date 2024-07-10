package com.javastudy.optional;

import java.util.Optional;

public class OptionalMain {
    public static void main(String[] args) {
        // optional 사용 안하는 경우 null을 if문으로 걸러냄
        Bag bag = getBag();
        if (bag == null) {
            bag = new Bag();
        } else {
            if (bag.purse == null) {
                bag.setPurse(new Purse());
            }
        }

        // optional에 담는 경우
        Bag bag1 = Optional.ofNullable(getBag()).orElse(new Bag());

        // bag을 다시 Otional로 래핑함
        Purse purse1 = Optional.ofNullable(bag1.getPurse()).orElse(new Purse());


    }

    private static Bag getBag() {
        return new Bag();
    }

    private static class Purse {
        private int money;

        public int getMoney() {
            return money;
        }
    }

    private static class Bag {
        private Purse purse;

        public Purse getPurse() {
            return purse;
        }

        public void setPurse(Purse purse) {
            this.purse = purse;
        }
    }
}
