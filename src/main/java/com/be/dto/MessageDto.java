package com.be.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class MessageDto extends BaseDto {
    @Size(max = 255)
    private String message;
    @Size(max = 255)
    private String type;
    @Size(max = 255)
    private String linkFile;
    @Size(max = 255)
    private String fileName;
    private Long createUserId;
    @Size(max = 255)
    private String createUserName;
    @Size(max = 255)
    private String createUserImage;

    public MessageDto() {
    }
}