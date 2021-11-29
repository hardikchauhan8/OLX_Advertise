package com.olx.actuator;

import com.olx.dto.AdvertiseV1;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Endpoint(id = "advertisestats")
public class AdvertiseStateActuator {

    final List<AdvertiseV1> advertises = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        advertises.add(new AdvertiseV1(
                1,
                "Mobile phone",
                50000,
                5,
                "Mobile phone advertise",
                "hardik",
                LocalDate.now(),
                LocalDate.now(),
                1
        ));

        advertises.add(new AdvertiseV1(
                2,
                "Water botttle",
                50,
                6,
                "Water Bottle advertise",
                "hardik",
                LocalDate.now(),
                LocalDate.now(),
                1
        ));

        advertises.add(new AdvertiseV1(
                3,
                "Laptop table",
                600,
                8,
                "Laptop Table Ad",
                "abc",
                LocalDate.now(),
                LocalDate.now(),
                1
        ));

        advertises.add(new AdvertiseV1(
                4,
                "Laptop Bag",
                1200,
                7,
                "Laptop Bag Ad",
                "abc",
                LocalDate.now(),
                LocalDate.now(),
                1
        ));

        advertises.add(new AdvertiseV1(
                5,
                "Laptop",
                50000,
                9,
                "Laptop advertise",
                "hardik",
                LocalDate.now(),
                LocalDate.now(),
                1
        ));

        advertises.add(new AdvertiseV1(
                6,
                "Mobile phone",
                70000,
                5,
                "Mobile phone advertise",
                "abc",
                LocalDate.now(),
                LocalDate.now(),
                1
        ));

        advertises.add(new AdvertiseV1(
                7,
                "Laptop",
                50000,
                9,
                "Laptop advertise",
                "hardik",
                LocalDate.now(),
                LocalDate.now(),
                1
        ));
    }

    @ReadOperation
    public List<AdvertiseV1> getAdvertises(@Selector String status) {
        if (status.isEmpty()) {
            return advertises;
        } else {
            return getFilteredList(status);
        }
    }

    private List<AdvertiseV1> getFilteredList(String status) {
        List<AdvertiseV1> filteredList = new ArrayList<>();
        for (AdvertiseV1 advertise : advertises) {
            if (String.valueOf(advertise.getStatusId()).equalsIgnoreCase(status)) {
                filteredList.add(advertise);
            }
        }
        return filteredList;
    }
}
