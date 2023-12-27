package com.be.controller;

import com.be.common_api.Document;
import com.be.dto.DocumentDto;
import com.be.mapper.DocumentMapper;
import com.be.service.DocumentService;
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

@RequestMapping("/api/document")
@RestController
@Slf4j
@Api("document")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/post")
    public ResponseEntity<Long> save(@RequestBody @Validated DocumentDto documentDto) {
        DocumentDto item = documentService.save(documentDto);
        return ResponseEntity.ok(item.getId());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DocumentDto> findById(@PathVariable("id") Long id) {
        DocumentDto document = documentService.findById(id);
        return ResponseEntity.ok(document);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(documentService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new FileSystemNotFoundException();
        });
        documentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<DocumentDto>> pageQuery(@Filter Specification<Document> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DocumentDto> documentPage = documentService.findByCondition(spec, pageable);
        return ResponseEntity.ok(documentPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated DocumentDto documentDto, @PathVariable("id") Long id) {
        documentDto.setId(id);
        documentService.update(documentDto, id);
        return ResponseEntity.ok().build();
    }
}