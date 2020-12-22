package com.iluwatar.qingxi.method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 具体产品
 * steamed bun : 馒头
 */
public class SteamedBunMachine implements MachineAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(SteamedBunMachine.class);

    @Override
    public void process(String material) {
        System.out.println("我把 " + material + " 加工成了馒头");
        LOGGER.info("我使用Logger把 " + material + " 加工成了馒头");
    }
}
