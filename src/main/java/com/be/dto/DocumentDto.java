package com.be.dto;

import com.be.common_api.NguoiDung;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class DocumentDto extends BaseDto {
    private Long idSv;
    private NguoiDung nguoiDung;
    @Size(max = 255)
    private String title;
    private String moTa;
    @Size(max = 255)
    private String filename;

    public DocumentDto() {
    }
}