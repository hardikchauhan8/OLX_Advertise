package com.olx.service;

import com.olx.dto.Advertise;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface AdvertiseService {

    Advertise addAdvertisement(Advertise advertise,
                               String username);

    Advertise updateAdvertisement(int adId,
                                  Advertise advertise);

    List<Advertise> getAdvertisementByUser(String username);

    Advertise getAdvertisementOfUserById(int adId, String username);

    @Transactional
    boolean deleteAdvertisementById(int adId, String username);

    List<Advertise> searchAdvertisementBySearchCriteria(
            String searchText,
            int categoryId,
            String postedBy,
            String dateCondition,
            LocalDate onDate,
            LocalDate fromDate,
            LocalDate toDate,
            String sortBy,
            String sortOn,
            int startIndex,
            int records,
            int statusId
    );

    List<Advertise> searchAdvertisementBySearchText(String searchText);

    Advertise getAdvertisementById(int adId);
}
