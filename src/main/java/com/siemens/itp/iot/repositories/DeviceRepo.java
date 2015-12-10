package com.siemens.itp.iot.repositories;

import com.siemens.itp.iot.domain.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepo extends MongoRepository<Device, String>{

  public Device findByUuid(String uuid);
  
}
