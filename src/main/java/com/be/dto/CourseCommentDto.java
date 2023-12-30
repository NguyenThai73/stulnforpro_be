package com.be.dto;

import com.be.common_api.NguoiDung;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class CourseCommentDto extends BaseDto {
    private Long idNd;
    private NguoiDung nguoiDung;
    private Long idCourse;
    @Size(max = 255)
    private String star;
    private String comment;

    public CourseCommentDto() {
    }
}