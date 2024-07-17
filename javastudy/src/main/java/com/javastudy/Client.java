package com.javastudy;

public class Client {
    PayService payService;
    Payment payment;

    public Client(PayService payService, Payment payment) {
        this.payService = payService;
        this.payment = payment;
    }

    public void pay() {
        payService.pay(payment);
    }
}
