package com.vaadin.tutorial.cleverhome.configuration;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * Created by volk on 23.04.14.
 */
public class ConfigurationImpl{

    private String variable;
    private String ipAddress;
    private String title;
    private String controller_ip;
    private String controller_port;
    private String tmpFilePath;

    private static ConfigurationImpl configuration;
    private String reqTime;

    private ConfigurationImpl(){

    }

    public static void getInstance(){

        File file = new File("conf/spring.config.xml");

        AbstractXmlApplicationContext context;

        if(file.exists()){

            context = new ClassPathXmlApplicationContext("file:" + file.getAbsolutePath());

        }else{

            context = new ClassPathXmlApplicationContext(
                    "classpath*:/spring.config.xml"
            );

        }

        configuration = context.getBean(ConfigurationImpl.class);

        System.out.println("Configuration successfully instanced");

    }

    public static ConfigurationImpl getConfiguration() {
        return configuration;
    }

    public String getVariable() {

        return null;

    }

    public void setVariable(String variable) {

        this.variable = variable;

    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setController_ip(String controller_ip) {
        this.controller_ip = controller_ip;
    }

    public String getController_ip() {
        return controller_ip;
    }

    public void setController_port(String controller_port) {
        this.controller_port = controller_port;
    }

    public String getController_port() {
        return controller_port;
    }

    public void setTmpFilePath(String tmpFilePath) {
        this.tmpFilePath = tmpFilePath;
    }

    public String getTmpFilePath() {
        return tmpFilePath;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getReqTime() {
        return reqTime;
    }

}
