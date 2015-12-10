package com.siemens.itp.iot.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Objects;

public class Device {
  
  @Id
  private String id;

  @Indexed(unique = true)
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
