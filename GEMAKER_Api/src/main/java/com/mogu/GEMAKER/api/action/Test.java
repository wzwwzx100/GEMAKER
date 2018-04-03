package com.mogu.GEMAKER.api.action;

import com.mogu.GEMAKER.service.net.UDPServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UDPServer timeServer=  ac.getBean(UDPServer.class);
        try {
            timeServer.run(8888);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
