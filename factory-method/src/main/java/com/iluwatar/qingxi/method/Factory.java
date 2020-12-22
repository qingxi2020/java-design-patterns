package com.iluwatar.qingxi.method;

/**
 * 抽象工厂
 * 抽象工厂和产品是紧密联系的
 */
public abstract class Factory {
    /**
     * 工厂方法
     * 工厂方法的核心，生产产品
     * 让子类（具体工厂）来实例化具体对象（机器）
     * @return 返回值是工厂生产的产品
     */
    public abstract MachineAPI newMachine();

    public void process(String material) {
        MachineAPI machine = newMachine();
        machine.process(material);
    }
}
