package com.be.mapper;

import com.be.common_api.Document;
import com.be.dto.DocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends EntityMapper<DocumentDto, Document> {
}