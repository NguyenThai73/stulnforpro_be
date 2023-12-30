package com.be.service;

import com.be.common_api.Course;
import com.be.dto.CourseDto;
import com.be.dto.DocumentDto;
import com.be.handler.Utils;
import com.be.mapper.CourseMapper;
import com.be.repository.CourseRepository;
import com.llq.springfilter.boot.Filter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CourseService {
    private final CourseRepository repository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository repository, CourseMapper courseMapper) {
        this.repository = repository;
        this.courseMapper = courseMapper;
    }

    public CourseDto save(CourseDto courseDto) {
        Course entity = courseMapper.toEntity(courseDto);
        return courseMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CourseDto findById(Long id) {
        return courseMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }
    public Page<CourseDto> findByCondition(@Filter Specification<Course> spec, Pageable pageable) {
        Page<Course> entityPage = repository.findAll(spec, pageable);
        List<Course> entities = entityPage.getContent();
        return new PageImpl<>(courseMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public CourseDto update(CourseDto courseDto, Long id) {
        CourseDto data = findById(id);
        Course entity = courseMapper.toEntity(courseDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(courseMapper.toDto(entity));
    }
}