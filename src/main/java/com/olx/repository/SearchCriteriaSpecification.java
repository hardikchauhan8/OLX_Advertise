package com.olx.repository;

import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidDateConditionException;
import com.olx.exception.InvalidDateException;
import com.olx.utils.ExceptionConstants;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchCriteriaSpecification implements Specification<AdvertiseEntity> {

    private final String searchText;
    private final int categoryId;
    private final String postedBy;
    private final String dateCondition;
    private final LocalDate onDate;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final int statusId;

    public SearchCriteriaSpecification(String searchText, int categoryId, String postedBy, String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, int statusId) {
        this.searchText = searchText;
        this.categoryId = categoryId;
        this.postedBy = postedBy;
        this.dateCondition = dateCondition;
        this.onDate = onDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.statusId = statusId;
    }

    @Override
    public Predicate toPredicate(Root<AdvertiseEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (searchText != null) {
            predicates.add(
                    builder.or(
                            builder.like(builder.lower(root.get("title")), "%" + searchText.toLowerCase() + "%"),
                            builder.like(builder.lower(root.get("description")), "%" + searchText.toLowerCase() + "%")
                    )
            );
        }

        if (categoryId > 0) {
            predicates.add(builder.equal(root.get("categoryId"), categoryId));
        }

        if (postedBy != null) {
            predicates.add(builder.equal(root.get("username"), postedBy));
        }

        if (dateCondition != null) {
            switch (dateCondition) {
                case "between":
                    if (fromDate == null || toDate == null || fromDate.isAfter(toDate)) {
                        throw new InvalidDateException(ExceptionConstants.INVALID_FROM_TO_DATES);
                    } else {
                        predicates.add(builder.and(builder.greaterThanOrEqualTo(root.get("createdDate"), fromDate), builder.lessThanOrEqualTo(root.get("createdDate"), toDate)));
                    }
                    break;

                case "greaterThan":
                    if (fromDate == null) {
                        throw new InvalidDateException(ExceptionConstants.INVALID_FROM_DATE);
                    } else {
                        predicates.add(builder.greaterThan(root.get("createdDate"), fromDate));
                    }
                    break;

                case "lessThan":
                    if (fromDate == null) {
                        throw new InvalidDateException(ExceptionConstants.INVALID_FROM_DATE);
                    } else {
                        predicates.add(builder.lessThan(root.get("createdDate"), fromDate));
                    }
                    break;

                case "equals":
                    if (onDate == null) {
                        throw new InvalidDateException(ExceptionConstants.INVALID_ON_DATE);
                    } else {
                        predicates.add(builder.equal(root.get("createdDate"), onDate));
                    }
                    break;

                default:
                    throw new InvalidDateConditionException();
            }
        }

        if (statusId > 0) {
            predicates.add(builder.equal(root.get("statusId"), statusId));
        }

        return builder.and(predicates.toArray(new Predicate[]{}));
    }
}
