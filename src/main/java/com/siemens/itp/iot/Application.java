package com.siemens.itp.iot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@SpringBootApplication
public class Application {

    @Autowired
    private MongoOperations mongoOperations;

    private final String DEVICE_COLLECTION = "devices";

    @PostConstruct
    public void tuneMongo() {
        mongoOperations.dropCollection(DEVICE_COLLECTION);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String slash() {
        return "ITP Hackaton - IoT";
    }

}
