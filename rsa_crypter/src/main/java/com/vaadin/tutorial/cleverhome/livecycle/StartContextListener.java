package com.vaadin.tutorial.cleverhome.livecycle;

import com.vaadin.tutorial.cleverhome.configuration.ConfigurationImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by volk on 22.05.14.
 */
public class StartContextListener implements ServletContextListener {

    private ServletContext context = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        this.context = sce.getServletContext();

        ConfigurationImpl.getInstance();
//        Monitor.getInstance(Integer.valueOf(ConfigurationImpl.getConfiguration().getReqTime()));

        //Output a simple message to the server's console
        System.out.println("The Web App. Is Ready");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        //Output a simple message to the server's console
        System.out.println("The Simple Web App. Has Been Removed");
        this.context = null;

    }
}
