package com.qingxi.prototype;

public class SheepTest {
    public static void main(String[] args) {
        var original = new Sheep("Jolly");
        System.out.println(original.getName()); // Jolly

        var cloned = original.clone();
        cloned.setName("Dolly");
        System.out.println(cloned.getName()); // Dolly
    }
}
