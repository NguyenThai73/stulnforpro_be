package com.be.controller;

import com.be.common_api.Course;
import com.be.common_api.Document;
import com.be.dto.CourseDto;
import com.be.mapper.CourseMapper;
import com.be.repository.CourseRepository;
import com.be.service.CourseService;
import com.llq.springfilter.boot.Filter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/course")
@RestController
@Slf4j
@Api("course")
public class CourseController {
    private final CourseRepository repository;
    private final CourseService courseService;

    public CourseController(CourseService courseService, CourseRepository repository) {
        this.courseService = courseService;
        this.repository = repository;
    }

    @PostMapping("/post")
    public ResponseEntity<Long> save(@RequestBody @Validated Course course) {
        Course item =  repository.save(course);
        return ResponseEntity.ok(item.getId());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable("id") Long id) {
        CourseDto course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            CourseDto course = courseService.findById(id);
            course.setDeleted(true);
            courseService.update(course, id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<CourseDto>> pageQuery(@Filter Specification<Course> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CourseDto> coursePage = courseService.findByCondition(spec, pageable);
        return ResponseEntity.ok(coursePage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated Course course, @PathVariable("id") Long id) {
        course.setId(id);
        repository.save(course);
        return ResponseEntity.ok().build();
    }
}