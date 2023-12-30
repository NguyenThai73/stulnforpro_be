package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "category")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class Catogory extends BaseEntity{
    @Column(name = "type")
    private Short type;
    @Column(name = "id_vv")
    private Long idSv;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_vv",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private NguoiDung nguoiDung;
    @Column(name = "nam_hoc")
    private String namHoc;
    @Column(name = "hoc_ky")
    private Short hocKy;
    @Column(name = "ly_do")
    private String lyDo;
    @Column(name = "attachment")
    private String attachment;
    @Column(name = "phan_hoi")
    private String phanHoi;
    @Column(name = "attachment_admin")
    private String attachmenAdmin;
    @Column(name = "status")
    private Short status;

}
