package com.iluwatar.qingxi.method;

/**
 * 具体工厂
 * 馒头工厂
 */
public class SteamedBunFactory extends Factory{
    @Override
    public MachineAPI newMachine() {
        return new SteamedBunMachine();
    }
}
