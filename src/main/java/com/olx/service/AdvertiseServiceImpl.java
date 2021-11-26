package com.olx.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.Advertise;
import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidAdvertiseDataException;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.repository.AdvertiseRepository;
import com.olx.repository.SearchCriteriaSpecification;
import com.olx.utils.AdvertiseConverterUtil;
import com.olx.utils.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
                                      String username) {
        if (advertise == null) {
            throw new InvalidAdvertiseDataException(ExceptionConstants.INVALID_CREATE_ADVERTISE_DATA);
        }

        advertise.setUsername(username);
        advertise.setCreatedDate(LocalDate.now());
        advertise.setModifiedDate(LocalDate.now());
        advertise.setStatusId(1);

        return convertAdToResponse(
                AdvertiseConverterUtil.convertEntityToDto(
                        modelMapper,
                        advertiseRepository.save(
                                AdvertiseConverterUtil.convertDtoToEntity(modelMapper, advertise)
                        )
                )
        );
    }

    // 8
    public Advertise updateAdvertisement(int adId,
                                         Advertise advertise) {

        if (!advertiseRepository.findById(adId).isPresent()) {
            throw new InvalidAdvertiseIdException(adId);
        }
        if (advertise == null) {
            throw new InvalidAdvertiseDataException(ExceptionConstants.INVALID_UPDATED_ADVERTISE_DATA);
        }

        AdvertiseEntity oldEntity = advertiseRepository.getById(adId);
        oldEntity.setTitle(advertise.getTitle());
        oldEntity.setCategoryId(advertise.getCategoryId());
        advertise.setCategory(masterDataDelegate.getCategoryById(advertise.getCategoryId()).getBody());
        oldEntity.setDescription(advertise.getDescription());
        oldEntity.setPrice(advertise.getPrice());
        oldEntity.setModifiedDate(LocalDate.now());
        oldEntity.setStatusId(Math.max(oldEntity.getStatusId(), advertise.getStatusId()));

        return convertAdToResponse(
                AdvertiseConverterUtil.convertEntityToDto(
                        modelMapper,
                        advertiseRepository.save(oldEntity)
                )
        );
    }

    // 9
    public List<Advertise> getAdvertisementByUser(String username) {
        return convertAdToResponse(
                AdvertiseConverterUtil.convertEntityToDto(
                        modelMapper,
                        advertiseRepository.findByUsername(username)
                )
        );
    }

    // 10
    public Advertise getAdvertisementOfUserById(int adId, String username) {

        if (!advertiseRepository.findById(adId).isPresent()) {
            throw new InvalidAdvertiseIdException(adId);
        }
        AdvertiseEntity advertise = advertiseRepository.findByIdAndUsername(adId, username);
        if (advertise == null) {
            return null;
        } else {
            return convertAdToResponse(
                    AdvertiseConverterUtil.convertEntityToDto(
                            modelMapper,
                            advertise
                    )
            );
        }
    }

    // 11
    public boolean deleteAdvertisementById(int adId, String username) {

        if (!advertiseRepository.findById(adId).isPresent()) {
            throw new InvalidAdvertiseIdException(adId);
        }
        advertiseRepository.deleteByIdAndUsername(adId, username);
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
        Sort sort;
        if (sortBy.equalsIgnoreCase("asc")) {
            sort = Sort.by(Sort.Direction.ASC, sortOn);
        } else {
            sort = Sort.by(Sort.Direction.DESC, sortOn);
        }

        Pageable pageWithFewRecords = PageRequest.of(startIndex, records, sort);

        SearchCriteriaSpecification spec1 = new SearchCriteriaSpecification(
                searchText,
                categoryId,
                postedBy,
                dateCondition,
                onDate,
                fromDate,
                toDate,
                statusId
        );
        Page<AdvertiseEntity> advertiseEntities = advertiseRepository.findAll(spec1, pageWithFewRecords);

        return convertAdToResponse(
                AdvertiseConverterUtil.convertEntityToDto(
                        modelMapper,
                        advertiseEntities.getContent()
                )
        );
    }

    // 13
    public List<Advertise> searchAdvertisementBySearchText(String searchText) {
        return convertAdToResponse(
                AdvertiseConverterUtil.convertEntityToDto(
                        modelMapper,
                        advertiseRepository.findByTitleOrDescriptionContainingIgnoreCase(searchText, searchText)
                )
        );
    }

    // 14
    public Advertise getAdvertisementById(int adId) {
        if (!advertiseRepository.findById(adId).isPresent()) {
            throw new InvalidAdvertiseIdException(adId);
        }
        if (advertiseRepository.findById(adId).isPresent()) {
            return convertAdToResponse(AdvertiseConverterUtil.convertEntityToDto(modelMapper, advertiseRepository.getById(adId)));
        }
        return new Advertise();
    }

    private Advertise convertAdToResponse(Advertise advertise) {
        advertise.setCategory(masterDataDelegate.getCategoryById(advertise.getCategoryId()).getBody());
        advertise.setStatus(masterDataDelegate.getStatusById(advertise.getStatusId()).getBody());
        return advertise;
    }

    private List<Advertise> convertAdToResponse(List<Advertise> advertiseList) {
        List<Category> categories = masterDataDelegate.getCategories().getBody();
        List<Status> statuses = masterDataDelegate.getStatus().getBody();
        advertiseList = advertiseList
                .stream()
                .peek(advertise -> {
                    advertise.setCategory(findCategory(categories, advertise.getCategoryId()));
                    advertise.setStatus(findStatus(statuses, advertise.getStatusId()));
                }).collect(Collectors.toList());

        return advertiseList;
    }

    private Category findCategory(List<Category> categories, int id) {
        if (categories == null)
            return null;

        List<Category> categoryList = new ObjectMapper().convertValue(categories, new TypeReference<List<Category>>() {
        });
        return categoryList.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    private Status findStatus(List<Status> statuses, int id) {
        if (statuses == null)
            return null;

        List<Status> statusList = new ObjectMapper().convertValue(statuses, new TypeReference<List<Status>>() {
        });
        return statusList.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }
}