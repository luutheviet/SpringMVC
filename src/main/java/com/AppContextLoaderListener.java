package com;

import org.springframework.web.context.ContextLoaderListener;


public class AppContextLoaderListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent event) {
        System.out.println("------> Da khoi tao ung dung");
    }

    @Override
    public void contextDestroyed(javax.servlet.ServletContextEvent event) {
        System.out.println("------> Da huy ung dung");
    }

}
