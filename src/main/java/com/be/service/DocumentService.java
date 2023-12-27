package com.be.service;

import com.be.common_api.Document;
import com.be.dto.DocumentDto;
import com.be.handler.Utils;
import com.be.mapper.DocumentMapper;
import com.be.repository.DocumentRepository;
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
public class DocumentService {
    private final DocumentRepository repository;
    private final DocumentMapper documentMapper;

    public DocumentService(DocumentRepository repository, DocumentMapper documentMapper) {
        this.repository = repository;
        this.documentMapper = documentMapper;
    }

    public DocumentDto save(DocumentDto documentDto) {
        Document entity = documentMapper.toEntity(documentDto);
        return documentMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public DocumentDto findById(Long id) {
        return documentMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<DocumentDto> findByCondition(@Filter Specification<Document> spec, Pageable pageable) {
        Page<Document> entityPage = repository.findAll(spec, pageable);
        List<Document> entities = entityPage.getContent();
        return new PageImpl<>(documentMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public DocumentDto update(DocumentDto documentDto, Long id) {
        DocumentDto data = findById(id);
        Document entity = documentMapper.toEntity(documentDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(documentMapper.toDto(entity));
    }
}