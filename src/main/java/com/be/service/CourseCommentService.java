package com.be.service;

import com.be.common_api.CourseComment;
import com.be.dto.CourseCommentDto;
import com.be.dto.CourseDto;
import com.be.handler.Utils;
import com.be.mapper.CourseCommentMapper;
import com.be.repository.CourseCommentRepository;
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
public class CourseCommentService {
    private final CourseCommentRepository repository;
    private final CourseCommentMapper courseCommentMapper;

    public CourseCommentService(CourseCommentRepository repository, CourseCommentMapper courseCommentMapper) {
        this.repository = repository;
        this.courseCommentMapper = courseCommentMapper;
    }

    public CourseCommentDto save(CourseCommentDto courseCommentDto) {
        CourseComment entity = courseCommentMapper.toEntity(courseCommentDto);
        return courseCommentMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CourseCommentDto findById(Long id) {
        return courseCommentMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }
    public Page<CourseCommentDto> findByCondition(@Filter Specification<CourseComment> spec, Pageable pageable) {
        Page<CourseComment> entityPage = repository.findAll(spec, pageable);
        List<CourseComment> entities = entityPage.getContent();
        return new PageImpl<>(courseCommentMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public CourseCommentDto update(CourseCommentDto courseCommentDto, Long id) {
        CourseCommentDto data = findById(id);
        CourseComment entity = courseCommentMapper.toEntity(courseCommentDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(courseCommentMapper.toDto(entity));
    }
}