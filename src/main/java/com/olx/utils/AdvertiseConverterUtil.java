package com.olx.utils;

import com.olx.dto.AdvertiseV1;
import com.olx.dto.AdvertiseV2;
import com.olx.entity.AdvertiseEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;

public class AdvertiseConverterUtil {

    public static AdvertiseV1 convertEntityToDtoV1(ModelMapper modelMapper, AdvertiseEntity advertiseEntity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TypeMap<AdvertiseEntity, AdvertiseV1> typeMap = modelMapper.typeMap(AdvertiseEntity.class, AdvertiseV1.class);
        typeMap.addMapping(AdvertiseEntity::getCategoryId, AdvertiseV1::setCategoryId);
        typeMap.addMapping(AdvertiseEntity::getStatusId, AdvertiseV1::setStatusId);
        return modelMapper.map(advertiseEntity, AdvertiseV1.class);
    }

    public static AdvertiseV2 convertEntityToDtoV2(ModelMapper modelMapper, AdvertiseEntity advertiseEntity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TypeMap<AdvertiseEntity, AdvertiseV2> typeMap = modelMapper.typeMap(AdvertiseEntity.class, AdvertiseV2.class);
        typeMap.addMapping(AdvertiseEntity::getCategoryId, AdvertiseV2::setCategoryId);
        typeMap.addMapping(AdvertiseEntity::getStatusId, AdvertiseV2::setStatusId);
        return modelMapper.map(advertiseEntity, AdvertiseV2.class);
    }

    public static AdvertiseEntity convertDtoToEntity(ModelMapper modelMapper, AdvertiseV1 advertise) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TypeMap<AdvertiseV1, AdvertiseEntity> typeMap = modelMapper.typeMap(AdvertiseV1.class, AdvertiseEntity.class);
        typeMap.addMapping(AdvertiseV1::getCategoryId, AdvertiseEntity::setCategoryId);
        typeMap.addMapping(AdvertiseV1::getStatusId, AdvertiseEntity::setStatusId);
        return modelMapper.map(advertise, AdvertiseEntity.class);
    }

    public static List<AdvertiseV1> convertEntityToDtoV1(ModelMapper modelMapper, List<AdvertiseEntity> advertiseEntityList) {
        return advertiseEntityList
                .stream()
                .map(advertiseEntity -> modelMapper.map(advertiseEntity, AdvertiseV1.class))
                .collect(Collectors.toList());
    }

    public static List<AdvertiseV2> convertEntityToDtoV2(ModelMapper modelMapper, List<AdvertiseEntity> advertiseEntityList) {
        return advertiseEntityList
                .stream()
                .map(advertiseEntity -> modelMapper.map(advertiseEntity, AdvertiseV2.class))
                .collect(Collectors.toList());
    }
}
