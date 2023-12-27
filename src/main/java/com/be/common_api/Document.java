package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "document")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class Document extends BaseEntity{
    @Column(name = "id_sv")
    private Long idSv;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_sv",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private NguoiDung nguoiDung;
    @Column(name = "title")
    private String title;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "filename")
    private String filename;

}
