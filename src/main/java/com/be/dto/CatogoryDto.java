package com.be.dto;

import com.be.common_api.NguoiDung;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class CatogoryDto extends BaseDto {
    private Short type;
    private Long idSv;
    private NguoiDung nguoiDung;
    @Size(max = 255)
    private String namHoc;
    private Short hocKy;
    private String lyDo;
    private String phanHoi;
    private Short status;

    public CatogoryDto() {
    }
}