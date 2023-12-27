package com.be.mapper;

import com.be.common_api.Catogory;
import com.be.dto.CatogoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CatogoryMapper extends EntityMapper<CatogoryDto, Catogory> {
}