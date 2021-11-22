package com.olx.utils;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AdvertiseConverterUtil {

    public static Advertise convertEntityToDto(ModelMapper modelMapper, AdvertiseEntity advertiseEntity) {
        return modelMapper.map(advertiseEntity, Advertise.class);
    }

    public static AdvertiseEntity convertDtoToEntity(ModelMapper modelMapper, Advertise advertise) {
        return modelMapper.map(advertise, AdvertiseEntity.class);
    }

    public static List<Advertise> convertEntityToDto(ModelMapper modelMapper, List<AdvertiseEntity> advertiseEntityList) {
        return advertiseEntityList
                .stream()
                .map(advertiseEntity -> modelMapper.map(advertiseEntity, Advertise.class))
                .collect(Collectors.toList());
    }

    public static List<AdvertiseEntity> convertDtoToEntity(ModelMapper modelMapper, List<Advertise> advertiseList) {
        return advertiseList
                .stream()
                .map(advertise -> modelMapper.map(advertise, AdvertiseEntity.class))
                .collect(Collectors.toList());
    }
}
