package com.be.mapper;

import com.be.common_api.Course;
import com.be.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDto, Course> {
}