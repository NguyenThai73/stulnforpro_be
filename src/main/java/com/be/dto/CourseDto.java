package com.be.dto;

import com.be.common_api.NguoiDung;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class CourseDto extends BaseDto {
    private Long idDn;
    private NguoiDung nguoiDung;
    private Short type;
    @Size(max = 255)
    private String title;
    private String moTa;
    private String url;
    private String urlKhaiGiang;
    private Short views;
    private Short status;

    public CourseDto() {
    }
}