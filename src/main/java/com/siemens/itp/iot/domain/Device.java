package com.siemens.itp.iot.domain;

import java.io.File;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Device {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private final String uuid;
  
  private String swagger;
  
  private Device(){
    this.uuid = "";
  }
  
  @JsonCreator
  public Device(@JsonProperty("uuid") String uuid){
    this.uuid = uuid;
  }
  
  @Override
  public String toString() {
    return String.format(
            "{Beacon:{\"id\": \"%s\", \"uuid\": \"%s\"}}",
            id, uuid);
  }

  public String getUuid() {
    return uuid;
  }

  public String getSwagger() {
    return swagger;
  }
  
  public void setSwagger(String swagger){
    this.swagger = swagger;
  }
  
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + Objects.hashCode(this.uuid);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Device other = (Device) obj;
    if (!Objects.equals(this.uuid, other.uuid)) {
      return false;
    }
    return true;
  }

}
