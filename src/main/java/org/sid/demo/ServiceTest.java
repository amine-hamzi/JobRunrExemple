package org.sid.demo;

import org.springframework.stereotype.Service;

@Service
public class ServiceTest {
    static final String SERVICE_NAME = "service test";
    public String getServiceName(){
        return SERVICE_NAME;
    }
}
