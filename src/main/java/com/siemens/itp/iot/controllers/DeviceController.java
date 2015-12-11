package com.siemens.itp.iot.controllers;

import static com.siemens.itp.iot.controllers.RestUriConstants.DEVICE_URL;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siemens.itp.iot.domain.Device;
import com.siemens.itp.iot.repositories.DeviceRepo;

@RestController
public class DeviceController {
  
  static final Logger LOG = Logger.getLogger(DeviceController.class);

  @Autowired
  private DeviceRepo deviceRepo;

  @RequestMapping(value = DEVICE_URL, method = RequestMethod.GET)
  public ArrayList<Device> getAll() {
    Iterable<Device> devices = deviceRepo.findAll();
    ArrayList<Device> list = new ArrayList<Device>();
    for (Device device : devices)
    {
        list.add(device);
    }
    LOG.debug("get all devices returns " + list.size() + " elements");
    return list;
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
    device = deviceRepo.save(device);
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
