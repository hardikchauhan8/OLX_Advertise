package com.olx.controller;

import com.olx.dto.AdvertiseV2;
import com.olx.service.AdvertiseService;
import com.olx.service.LoginDelegate;
import com.olx.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("olx/advertise/v2")
@Api(value = "Advertise Controller Version 2")
public class AdvertiseControllerV2 {

    @Autowired
    AdvertiseService advertiseService;

    @Autowired
    LoginDelegate loginDelegate;

    // 9
    @ApiOperation(value = "Get all advertise posted by a user")
    @GetMapping(value = "/user/advertise",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AdvertiseV2>> getAdvertisementByUser(@RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            return new ResponseEntity<>(advertiseService.getAdvertisementByUserV2(getUserName(authToken)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    // 10
    @ApiOperation(value = "Get a specific advertise by id posted by a user")
    @GetMapping(value = "/user/advertise/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AdvertiseV2> getAdvertisementOfUserById(@PathVariable("id") int adId, @RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            return new ResponseEntity<>(advertiseService.getAdvertisementOfUserByIdV2(adId, getUserName(authToken)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    // 12
    @ApiOperation(value = "Search for an advertise filtered by different search criteria")
    @GetMapping(value = "/search/filtercriteria",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AdvertiseV2>> searchAdvertisementBySearchCriteria(
            @RequestParam(value = "searchText", required = false) String searchText,
            @RequestParam(value = "category", defaultValue = "-1", required = false) int categoryId,
            @RequestParam(value = "postedBy", required = false) String postedBy,
            @RequestParam(value = "dateCondition", required = false) String dateCondition,
            @RequestParam(value = "onDate", required = false) String onDate,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "sortBy", defaultValue = "asc", required = false) String sortBy,
            @RequestParam(value = "sortOn", defaultValue = "modifiedDate", required = false) String sortOn,
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) int startIndex,
            @RequestParam(value = "records", defaultValue = "10", required = false) int records,
            @RequestParam(value = "status", defaultValue = "-1", required = false) int statusId
    ) {
        return new ResponseEntity<>(
                advertiseService.searchAdvertisementBySearchCriteriaV2(
                        searchText,
                        categoryId,
                        postedBy,
                        dateCondition,
                        onDate == null ? null : DateUtils.convertStringToDate(onDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                        fromDate == null ? null : DateUtils.convertStringToDate(fromDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                        toDate == null ? null : DateUtils.convertStringToDate(toDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                        sortBy,
                        sortOn,
                        startIndex,
                        records,
                        statusId
                ), HttpStatus.OK);
    }

    // 13
    @ApiOperation(value = "Search for an advertise that contains query search text")
    @GetMapping(value = "/search",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AdvertiseV2>> searchAdvertisementBySearchText(@RequestParam("searchText") String searchText) {
        return new ResponseEntity<>(advertiseService.searchAdvertisementBySearchTextV2(searchText), HttpStatus.OK);
    }

    // 14
    @ApiOperation(value = "Get an advertise by id")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AdvertiseV2> getAdvertisementById(@PathVariable("id") int adId, @RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            return new ResponseEntity<>(advertiseService.getAdvertisementByIdV2(adId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isValidToken(String authToken) {
        ResponseEntity<Boolean> validTokenResponse = loginDelegate.validateToken(authToken);
        return validTokenResponse.hasBody() && Boolean.TRUE.equals(validTokenResponse.getBody());
    }

    private String getUserName(String authToken) {
        ResponseEntity<String> userResponse = loginDelegate.getUsername(authToken);
        if (userResponse.hasBody()) {
            return userResponse.getBody();
        }
        return "";
    }
}
