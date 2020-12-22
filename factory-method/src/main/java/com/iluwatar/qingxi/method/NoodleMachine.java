package com.iluwatar.qingxi.method;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


/**
 * 具体产品
 * noodle : 面条
 * {@link MachineAPI}
 * */
public class NoodleMachine implements MachineAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoodleMachine.class);

    @Override
    public void process(String material) {
        System.out.println("我把 " + material + " 加工成了面条");
        LOGGER.info("我使用Logger把 " + material + " 加工成了面条");
    }
}
