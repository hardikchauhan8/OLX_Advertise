package com.olx.actuator;

import com.olx.dto.Advertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = AdvertiseStateActuator.class)
public class AdvertiseStateActuatorExtension {

    @Autowired
    AdvertiseStateActuator advertiseStateActuator;

    @ReadOperation
    public Map<String, Integer> getAdvertisesByUser() {
        Map<String, Integer> userAdMap = new HashMap<>();
        for (Advertise advertise : advertiseStateActuator.advertises) {
            int count = userAdMap.getOrDefault(advertise.getUsername(), 0);
            userAdMap.put(advertise.getUsername(), ++count);
        }
        return userAdMap;
    }
}
