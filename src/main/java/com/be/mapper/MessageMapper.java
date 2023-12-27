package com.be.mapper;

import com.be.common_api.Message;
import com.be.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper extends EntityMapper<MessageDto, Message> {
}