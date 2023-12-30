package com.be.controller;

import com.be.common_api.Course;
import com.be.common_api.CourseComment;
import com.be.dto.CourseCommentDto;
import com.be.mapper.CourseCommentMapper;
import com.be.service.CourseCommentService;
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

import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/course-comment")
@RestController
@Slf4j
@Api("course-comment")
public class CourseCommentController {
    private final CourseCommentService courseCommentService;

    public CourseCommentController(CourseCommentService courseCommentService) {
        this.courseCommentService = courseCommentService;
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody @Validated CourseCommentDto courseCommentDto) {
       try {
           courseCommentService.save(courseCommentDto);
           return ResponseEntity.ok(true);
       }catch (Exception e){
           return ResponseEntity.ok(false);
       }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CourseCommentDto> findById(@PathVariable("id") Long id) {
        CourseCommentDto courseComment = courseCommentService.findById(id);
        return ResponseEntity.ok(courseComment);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(courseCommentService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new FileSystemNotFoundException();
        });
        courseCommentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<CourseCommentDto>> pageQuery(@Filter Specification<CourseComment> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CourseCommentDto> courseCommentPage = courseCommentService.findByCondition(spec, pageable);
        return ResponseEntity.ok(courseCommentPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated CourseCommentDto courseCommentDto, @PathVariable("id") Long id) {
        courseCommentDto.setId(id);
        courseCommentService.update(courseCommentDto, id);
        return ResponseEntity.ok().build();
    }
}