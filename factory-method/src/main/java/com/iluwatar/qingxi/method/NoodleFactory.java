package com.iluwatar.qingxi.method;

/**
 * 具体工厂
 * 面条工厂
 */
public class NoodleFactory extends Factory {
    @Override
    public MachineAPI newMachine() {
        return new NoodleMachine();
    }
}
