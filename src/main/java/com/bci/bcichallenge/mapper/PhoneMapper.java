package com.bci.bcichallenge.mapper;

import com.bci.bcichallenge.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    @Mapping(target = "number", source = "phone.number")
    @Mapping(target = "citycode", source = "phone.citycode")
    @Mapping(target = "contrycode", source = "phone.contrycode")
    Phone toPhoneEntity(Phone phone);
}
