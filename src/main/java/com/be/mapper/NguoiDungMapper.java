package com.be.mapper;

import com.be.common_api.NguoiDung;
import com.be.dto.NguoiDungDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NguoiDungMapper extends EntityMapper<NguoiDungDto, NguoiDung> {
}