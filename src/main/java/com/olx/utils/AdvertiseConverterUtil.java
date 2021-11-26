package com.olx.utils;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

public class AdvertiseConverterUtil {

    public static Advertise convertEntityToDto(ModelMapper modelMapper, AdvertiseEntity advertiseEntity) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TypeMap<AdvertiseEntity, Advertise> typeMap = modelMapper.typeMap(AdvertiseEntity.class, Advertise.class);
        typeMap.addMapping(AdvertiseEntity::getCategoryId, Advertise::setCategoryId);
        typeMap.addMapping(AdvertiseEntity::getStatusId, Advertise::setStatusId);
        return modelMapper.map(advertiseEntity, Advertise.class);
    }

    public static AdvertiseEntity convertDtoToEntity(ModelMapper modelMapper, Advertise advertise) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        TypeMap<Advertise, AdvertiseEntity> typeMap = modelMapper.typeMap(Advertise.class, AdvertiseEntity.class);
        typeMap.addMapping(Advertise::getCategoryId, AdvertiseEntity::setCategoryId);
        typeMap.addMapping(Advertise::getStatusId, AdvertiseEntity::setStatusId);
        return modelMapper.map(advertise, AdvertiseEntity.class);
    }

    public static List<Advertise> convertEntityToDto(ModelMapper modelMapper, List<AdvertiseEntity> advertiseEntityList) {
        return advertiseEntityList
                .stream()
                .map(advertiseEntity -> modelMapper.map(advertiseEntity, Advertise.class))
                .collect(Collectors.toList());
    }
}
