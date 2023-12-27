package com.be.service;

import com.be.common_api.Message;
import com.be.dto.MessageDto;
import com.be.handler.Utils;
import com.be.mapper.MessageMapper;
import com.be.repository.MessageRepository;
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
public class MessageService {
    private final MessageRepository repository;
    private final MessageMapper messageMapper;

    public MessageService(MessageRepository repository, MessageMapper messageMapper) {
        this.repository = repository;
        this.messageMapper = messageMapper;
    }

    public MessageDto save(MessageDto messageDto) {
        Message entity = messageMapper.toEntity(messageDto);
        return messageMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Message findById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        );
    }

    public Page<MessageDto> findByCondition(@Filter Specification<Message> spec, Pageable pageable) {
        Page<Message> entityPage = repository.findAll(spec, pageable);
        List<Message> entities = entityPage.getContent();
        return new PageImpl<>(messageMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public MessageDto update(MessageDto messageDto, Long id) {
        Message data = findById(id);
        Message entity = messageMapper.toEntity(messageDto);
        BeanUtils.copyProperties(data, entity, Utils.getNullPropertyNames(entity));
        return save(messageMapper.toDto(entity));
    }
}