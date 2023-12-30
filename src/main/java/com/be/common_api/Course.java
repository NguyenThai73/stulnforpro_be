package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class Course extends BaseEntity{
    private Long id;
    @Column(name = "id_dn")
    private Long idDn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_dn",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private NguoiDung nguoiDung;
    @Column(name = "type")
    private Short type;
    @Column(name = "title")
    private String title;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "url")
    private String url;
    @Column(name = "url_khai_giang")
    private String urlKhaiGiang;
    @Column(name = "views")
    private Short views;
    @Column(name = "status")
    private Short status;
}
