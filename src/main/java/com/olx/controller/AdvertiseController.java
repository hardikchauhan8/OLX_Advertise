package com.olx.controller;

import com.olx.dto.Advertise;
import com.olx.service.AdvertiseService;
import com.olx.utils.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("olx")
public class AdvertiseController {

    @Autowired
    AdvertiseService advertiseService;

    // 7
    @ApiOperation(value = "Add a new advertise")
    @PostMapping(value = "/advertise",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Advertise addAdvertisement(@RequestBody Advertise advertise,
                                      @RequestHeader("auth-token") String authToken) {

        return advertiseService.addAdvertisement(advertise, authToken);
    }

    // 8
    @ApiOperation(value = "Update a specific advertise by id")
    @PutMapping(value = "/advertise/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Advertise updateAdvertisement(@PathVariable("id") int adId,
                                         @RequestBody Advertise advertise,
                                         @RequestHeader("auth-token") String authToken) {

        return advertiseService.updateAdvertisement(adId, advertise, authToken);
    }

    // 9
    @ApiOperation(value = "Get all advertise posted by a user")
    @GetMapping(value = "/user/advertise",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Advertise> getAdvertisementByUser(@RequestHeader("auth-token") String authToken) {

        return advertiseService.getAdvertisementByUser(authToken);
    }

    // 10
    @ApiOperation(value = "Get a specific advertise by id posted by a user")
    @GetMapping(value = "/user/advertise/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Advertise getAdvertisementOfUserById(@PathVariable("id") int adId, @RequestHeader("auth-token") String authToken) {

        return advertiseService.getAdvertisementOfUserById(adId, authToken);
    }

    // 11
    @ApiOperation(value = "delete a specific advertise by id posted by a user")
    @DeleteMapping(value = "/user/advertise/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public boolean deleteAdvertisementById(@PathVariable("id") int adId, @RequestHeader("auth-token") String authToken) {

        return advertiseService.deleteAdvertisementById(adId, authToken);
    }

    // 12
    @ApiOperation(value = "Search for an advertise filtered by different search criteria")
    @GetMapping(value = "/advertise/search/filtercriteria",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Advertise> searchAdvertisementBySearchCriteria(
            @RequestParam(value = "searchText", required = false) String searchText,
            @RequestParam(value = "category", defaultValue = "-1", required = false) int category,
            @RequestParam(value = "postedBy", required = false) String postedBy,
            @RequestParam(value = "dateCondition", required = false) String dateCondition,
            @RequestParam(value = "onDate", required = false) String onDate,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "sortBy", defaultValue = "asc", required = false) String sortBy,
            @RequestParam(value = "sortOn", defaultValue = "title", required = false) String sortOn,
            @RequestParam(value = "startIndex", defaultValue = "1", required = false) int startIndex,
            @RequestParam(value = "records", defaultValue = "-1", required = false) int records,
            @RequestParam(value = "status", defaultValue = "-1", required = false) int status
    ) {


        return advertiseService.searchAdvertisementBySearchCriteria(
                searchText,
                category,
                postedBy,
                dateCondition,
                DateUtils.convertStringToDate(onDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                DateUtils.convertStringToDate(fromDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                DateUtils.convertStringToDate(toDate, DateUtils.DATE_FORMAT_YYYY_MM_DD),
                sortBy,
                sortOn,
                startIndex,
                records,
                status);
    }

    // 13
    @ApiOperation(value = "Search for an advertise that contains query search text")
    @GetMapping(value = "/advertise/search",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Advertise> searchAdvertisementBySearchText(@RequestParam("searchText") String searchText) {
        return advertiseService.searchAdvertisementBySearchText(searchText);
    }

    // 14
    @ApiOperation(value = "Get an advertise by id")
    @GetMapping(value = "/advertise/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Advertise getAdvertisementById(@PathVariable("id") int adId, @RequestHeader("auth-token") String authToken) {
        return advertiseService.getAdvertisementById(adId, authToken);
    }
}
