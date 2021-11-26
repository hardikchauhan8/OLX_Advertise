package com.olx.repository;

import com.olx.entity.AdvertiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer>, JpaSpecificationExecutor<AdvertiseEntity> {

    List<AdvertiseEntity> findByUsername(String authToken);

    AdvertiseEntity findByIdAndUsername(int adId, String authToken);

    void deleteByIdAndUsername(@Param("id") int adId, @Param("username") String authToken);

    List<AdvertiseEntity> findByTitleOrDescriptionContainingIgnoreCase(@Param("title") String searchTextTitle, @Param("description") String searchTextDesc);
}
