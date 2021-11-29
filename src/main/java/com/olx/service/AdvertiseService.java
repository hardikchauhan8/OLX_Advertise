package com.olx.service;

import com.olx.dto.AdvertiseV1;
import com.olx.dto.AdvertiseV2;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface AdvertiseService {

    /**
     * Apis for Advertise controller version 1
     */
    AdvertiseV1 addAdvertisementV1(AdvertiseV1 advertise,
                                   String username);

    AdvertiseV1 updateAdvertisementV1(int adId,
                                      AdvertiseV1 advertise);

    List<AdvertiseV1> getAdvertisementByUserV1(String username);

    AdvertiseV1 getAdvertisementOfUserByIdV1(int adId, String username);

    @Transactional
    boolean deleteAdvertisementById(int adId, String username);

    List<AdvertiseV1> searchAdvertisementBySearchCriteriaV1(
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

    List<AdvertiseV1> searchAdvertisementBySearchTextV1(String searchText);

    AdvertiseV1 getAdvertisementByIdV1(int adId);

    /**
     * Apis for Advertise controller version 2
     */

    List<AdvertiseV2> getAdvertisementByUserV2(String username);

    AdvertiseV2 getAdvertisementOfUserByIdV2(int adId, String username);

    List<AdvertiseV2> searchAdvertisementBySearchCriteriaV2(
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

    List<AdvertiseV2> searchAdvertisementBySearchTextV2(String searchText);

    AdvertiseV2 getAdvertisementByIdV2(int adId);
}
