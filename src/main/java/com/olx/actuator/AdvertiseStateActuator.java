package com.olx.actuator;

import com.olx.dto.Advertise;
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

    final List<Advertise> advertises = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        advertises.add(new Advertise(
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

        advertises.add(new Advertise(
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

        advertises.add(new Advertise(
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

        advertises.add(new Advertise(
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

        advertises.add(new Advertise(
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

        advertises.add(new Advertise(
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

        advertises.add(new Advertise(
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
    public List<Advertise> getAdvertises(@Selector String status) {
        if (status.isEmpty()) {
            return advertises;
        } else {
            return getFilteredList(status);
        }
    }

    private List<Advertise> getFilteredList(String status) {
        List<Advertise> filteredList = new ArrayList<>();
        for (Advertise advertise : advertises) {
            if (String.valueOf(advertise.getStatusId()).equalsIgnoreCase(status)) {
                filteredList.add(advertise);
            }
        }
        return filteredList;
    }
}
