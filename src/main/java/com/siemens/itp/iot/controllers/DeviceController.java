package com.siemens.itp.iot.controllers;

import com.siemens.itp.iot.domain.Device;
import com.siemens.itp.iot.repositories.DeviceRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.siemens.itp.iot.controllers.RestUriConstants.DEVICE_URL;

@RestController
public class DeviceController {
  
  static final Logger LOG = Logger.getLogger(DeviceController.class);

  @Autowired
  private DeviceRepo deviceRepo;

  @RequestMapping(value = DEVICE_URL, method = RequestMethod.GET)
  public List<Device> getAll() {
    List<Device> devices = deviceRepo.findAll();
    LOG.debug("get all devices returns " + devices.size() + " elements");
    return devices;
  }

  @RequestMapping(value = DEVICE_URL + "/{id}", method = RequestMethod.GET)
  public Device getByUuid(@PathVariable String uuid) {
    Device device = deviceRepo.findByUuid(uuid);
    LOG.debug("get device by uuid returns " + device);
    return device;
  }

  @RequestMapping(value = DEVICE_URL + "/{uuid}", method = RequestMethod.PUT)
  public Device update(@PathVariable String uuid, @RequestBody Device device) {
    LOG.debug("saving " + device);
    return deviceRepo.save(device);
  }

  @RequestMapping(value = DEVICE_URL, method = RequestMethod.POST)
  public ResponseEntity<?> create(@RequestBody Device device) {
    LOG.debug("creating " + device);
    device = deviceRepo.insert(device);
    return new ResponseEntity<>(device, HttpStatus.CREATED);
  }
  
  @RequestMapping(value = DEVICE_URL + "/{uuid}", method = RequestMethod.DELETE)
  public void delete(@PathVariable String uuid){
    Device device = getByUuid(uuid);
    LOG.debug("deleting " + device);
    deviceRepo.delete(device);
  }
  
  @RequestMapping(value = DEVICE_URL, method = RequestMethod.DELETE)
  public void deleteAll(){
    LOG.debug("deleting all beacons");
    deviceRepo.deleteAll();
  }

}
