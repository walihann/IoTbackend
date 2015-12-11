package com.siemens.itp.iot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siemens.itp.iot.domain.Device;

@Repository
public interface DeviceRepo extends JpaRepository<Device, Long>{

  public Device findByUuid(String uuid);
  
}
