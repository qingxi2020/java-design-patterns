package com.iluwatar.qingxi.method;

public class FactoryMethodTest {

    public static void main(String[] args) {
        Factory factory1 = new SteamedBunFactory();
        factory1.process("面粉");

        Factory factory2 = new NoodleFactory();
        factory2.process("面粉");
    }
}
