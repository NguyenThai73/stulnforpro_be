package com.be.mapper;

import com.be.common_api.CourseComment;
import com.be.dto.CourseCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseCommentMapper extends EntityMapper<CourseCommentDto, CourseComment> {
}