package com.be.controller;

import com.be.common_api.Message;
import com.be.dto.MessageDto;
import com.be.mapper.MessageMapper;
import com.be.repository.MessageRepository;
import com.be.service.MessageService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/message")
@RestController
@Slf4j
@Api("message")
public class MessageController {
    private final MessageService messageService;
    private final MessageRepository repository;
    private final MessageMapper messageMapper;
    public MessageController(MessageRepository repository,MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper =messageMapper;
        this.repository =  repository;
    }
    @PostMapping("/post")
    public ResponseEntity<Void> save(@RequestBody @Validated MessageDto messageDto) {
        messageService.save(messageDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get/page")
    public ResponseEntity<Map<String, Object>> pageQuery(@Filter Specification<Message> spec, @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC,size = 10000) Pageable pageable) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Page<MessageDto> messagePage = messageService.findByCondition(spec, pageable);
            result.put("result", messagePage);
            result.put("success",true);
        } catch (Exception e) {
            result.put("result", e.getMessage());
            result.put("success",false);
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            Message message = messageService.findById(id);
            message.setDeleted(true);
            MessageDto messageDto = messageMapper.toDto(message);
            messageService.update(messageDto, id);
        }catch (Exception e){
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated Message message, @PathVariable("id") Long id) {
        try{
            message.setId(id);
            repository.save(message);
        }catch (Exception e){
        }

        return ResponseEntity.ok().build();
    }
}