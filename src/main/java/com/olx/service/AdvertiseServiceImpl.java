package com.olx.service;

import com.olx.dto.Advertise;
import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.*;
import com.olx.repository.AdvertiseRepository;
import com.olx.utils.AdvertiseConverterUtil;
import com.olx.utils.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    MasterDataDelegate masterDataDelegate;

    @Autowired
    AdvertiseRepository advertiseRepository;

    @Autowired
    ModelMapper modelMapper;

    // 7
    public Advertise addAdvertisement(Advertise advertise,
                                      String authToken) {
        if (advertise == null) {
            throw new InvalidAdvertiseDataException(ExceptionConstants.INVALID_CREATE_ADVERTISE_DATA);
        }
        return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.save(AdvertiseConverterUtil.convertDtoToEntity(modelMapper, advertise)));
    }

    // 8
    public Advertise updateAdvertisement(int adId,
                                         Advertise advertise,
                                         String authToken) {

        if (!advertiseRepository.findById(adId).isPresent()) {
            throw new InvalidAdvertiseIdException(adId);
        }
        if (advertise == null) {
            throw new InvalidAdvertiseDataException(ExceptionConstants.INVALID_UPDATED_ADVERTISE_DATA);
        }

        AdvertiseEntity oldEntity = advertiseRepository.getById(adId);
        oldEntity.setTitle(advertise.getTitle());
        oldEntity.setCategoryId(advertise.getCategoryId());
        oldEntity.setDescription(advertise.getDescription());
        oldEntity.setPrice(advertise.getPrice());
        oldEntity.setModifiedDate(LocalDate.now());
        oldEntity.setStatusId(advertise.getStatusId());
        return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.save(oldEntity));
    }

    // 9
    public List<Advertise> getAdvertisementByUser(String authToken) {
        return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.findByUsername(authToken));
    }

    // 10
    public Advertise getAdvertisementOfUserById(int adId, String authToken) {

        if (!advertiseRepository.findById(adId).isPresent()) {
            throw new InvalidAdvertiseIdException(adId);
        }
        AdvertiseEntity advertise = advertiseRepository.findByIdAndUsername(adId, authToken);
        if(advertise == null){
            return null;
        } else {
            return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertise);
        }
    }

    // 11
    public boolean deleteAdvertisementById(int adId, String authToken) {

        if (!advertiseRepository.findById(adId).isPresent()) {
            throw new InvalidAdvertiseIdException(adId);
        }
        advertiseRepository.deleteByIdAndUsername(adId, authToken);
        return !advertiseRepository.findById(adId).isPresent();
    }

    // 12
    public List<Advertise> searchAdvertisementBySearchCriteria(
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
    ) {

        checkCategory(categoryId);
        checkDateConditions(dateCondition, onDate, fromDate, toDate);
        checkSorting(sortBy);
        checkStatus(statusId);

        Sort sort = Sort.by(Sort.Direction.ASC, sortOn);
        Pageable pageWithFewRecords = PageRequest.of(0, records, sort);
        Page<AdvertiseEntity> advertiseEntities = advertiseRepository.searchAdvertisementBySearchCriteria(searchText, categoryId, postedBy, onDate, fromDate, toDate, statusId, pageWithFewRecords);
        return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseEntities.getContent());
    }

    private void checkSorting(String sortBy) {
        if (!sortBy.equals("asc") && !sortBy.equals("desc")) {
            throw new InvalidSortingException();
        }
    }

    private void checkDateConditions(String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate) {
        switch (dateCondition) {
            case "between":
                if (fromDate == null ||
                        toDate == null ||
                        fromDate.isAfter(toDate)) {
                    throw new InvalidDateException(ExceptionConstants.INVALID_FROM_TO_DATES);
                }
                break;

            case "before":
            case "after":
            case "equals":
                if (onDate == null) {
                    throw new InvalidDateException(ExceptionConstants.INVALID_ON_DATE);
                }
                break;

            default:
                throw new InvalidDateConditionException();
        }
    }

    private void checkCategory(int categoryId) {
        ResponseEntity<Category> categoryResponse = masterDataDelegate.getCategoryById(categoryId);
        if (categoryId <= -1
                || categoryResponse.getStatusCode() != HttpStatus.OK
                || !categoryResponse.hasBody()
                || categoryResponse.getBody().getCategory() == null) {
            throw new InvalidCategoryIdException(categoryId);
        }
    }

    private void checkStatus(int statusId) {
        ResponseEntity<Status> statusResponse = masterDataDelegate.getStatusById(statusId);
        if (statusId <= -1
                || statusResponse.getStatusCode() != HttpStatus.OK
                || !statusResponse.hasBody()
                || statusResponse.getBody().getStatus() == null) {
            throw new InvalidStatusIdException(statusId);
        }
    }

    // 13
    public List<Advertise> searchAdvertisementBySearchText(String searchText) {
        return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.findByTitleOrDescriptionContaining(searchText, searchText));
    }

    // 14
    public Advertise getAdvertisementById(int adId, String authToken) {
        if (!advertiseRepository.findById(adId).isPresent()) {
            throw new InvalidAdvertiseIdException(adId);
        }
        if (!authToken.equals("hardik")) {
            throw new InvalidAdvertiseDataException(ExceptionConstants.INVALID_AUTH_TOKEN);
        }
        if (advertiseRepository.findById(adId).isPresent()) {
            return AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.getById(adId));
        }
        return new Advertise();
    }
}
