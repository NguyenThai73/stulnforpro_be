package com.be.service;

import com.be.common_api.Catogory;
import com.be.dto.CatogoryDto;
import com.be.handler.Utils;
import com.be.mapper.CatogoryMapper;
import com.be.repository.CatogoryRepository;
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
public class CatogoryService {
    private final CatogoryRepository repository;
    private final CatogoryMapper catogoryMapper;

    public CatogoryService(CatogoryRepository repository, CatogoryMapper catogoryMapper) {
        this.repository = repository;
        this.catogoryMapper = catogoryMapper;
    }

    public CatogoryDto save(CatogoryDto catogoryDto) {
        Catogory entity = catogoryMapper.toEntity(catogoryDto);
        return catogoryMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CatogoryDto findById(Long id) {
        return catogoryMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<CatogoryDto> findByCondition(@Filter Specification<Catogory> spec, Pageable pageable) {
        Page<Catogory> entityPage = repository.findAll(spec, pageable);
        List<Catogory> entities = entityPage.getContent();
        return new PageImpl<>(catogoryMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public CatogoryDto update(CatogoryDto catogoryDto, Long id) {
        CatogoryDto data = findById(id);
        System.out.printf("==================="+catogoryDto.getAttachment()+"========================");
        Catogory entity = catogoryMapper.toEntity(catogoryDto);
        entity.setAttachment(catogoryDto.getAttachment());
        BeanUtils.copyProperties(data, entity, Utils.getNullPropertyNames(entity));
        return save(catogoryMapper.toDto(entity));
    }
}