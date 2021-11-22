package com.olx.repository;

import com.olx.entity.AdvertiseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer> {

    List<AdvertiseEntity> findByUsername(String authToken);

    AdvertiseEntity findByIdAndUsername(int adId, String authToken);

    void deleteByIdAndUsername(@Param("id") int adId, @Param("username") String authToken);

    List<AdvertiseEntity> findByTitleOrDescriptionContaining(String searchTextTitle, String searchTextDesc);

    @Query(
            value = "SELECT ae FROM AdvertiseEntity ae WHERE " +
                    "(ae.title LIKE %?1% OR ae.description like %?1%) AND " +
                    "ae.categoryId = ?2 AND " +
                    "ae.username = ?3 AND " +
                    "(ae.createdDate = ?4 OR ae.createdDate >= ?5 AND ae.createdDate <= ?6) AND " +
                    "ae.statusId = ?7"
    )
    Page<AdvertiseEntity> searchAdvertisementBySearchCriteria(
            String searchText,
            int category,
            String postedBy,
            LocalDate onDate,
            LocalDate fromDate,
            LocalDate toDate,
            int status,
            Pageable pageWithFewRecords);


    //    "SELECT ae FROM AdvertiseEntity ae WHERE " +
//            "ae.title like '%:searchText%' AND " +
//            "ae.description like '%:searchText%' AND " +
//            "ae.categoryId = ':category' AND " +
//            "ae.statusId = ':status' AND " +
//            "ae.username = ':postedBy' AND " +
//            "(ae.createDate = ':onDate' or ae.createDate >= ':fromDate' and ae.createDate <= ':toDate')"

}
