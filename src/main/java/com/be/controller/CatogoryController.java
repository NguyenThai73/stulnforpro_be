package com.be.controller;

import com.be.common_api.Catogory;
import com.be.dto.CatogoryDto;
import com.be.mapper.CatogoryMapper;
import com.be.service.CatogoryService;
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

@RequestMapping("/api/catogory")
@RestController
@Slf4j
@Api("catogory")
public class CatogoryController {
    private final CatogoryService catogoryService;

    public CatogoryController(CatogoryService catogoryService) {
        this.catogoryService = catogoryService;
    }

    @PostMapping("/post")
    public ResponseEntity<Long> save(@RequestBody @Validated CatogoryDto catogoryDto) {
        CatogoryDto item =  catogoryService.save(catogoryDto);
        return ResponseEntity.ok(item.getId());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CatogoryDto> findById(@PathVariable("id") Long id) {
        CatogoryDto catogory = catogoryService.findById(id);
        return ResponseEntity.ok(catogory);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(catogoryService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new FileSystemNotFoundException();
        });
        catogoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<CatogoryDto>> pageQuery(@Filter Specification<Catogory> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CatogoryDto> catogoryPage = catogoryService.findByCondition(spec, pageable);
        return ResponseEntity.ok(catogoryPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated CatogoryDto catogoryDto, @PathVariable("id") Long id) {
        catogoryDto.setId(id);
        catogoryService.update(catogoryDto, id);
        return ResponseEntity.ok().build();
    }
}