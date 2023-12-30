package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course_comment")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class CourseComment extends BaseEntity{
    @Column(name = "id_nd")
    private Long idNd;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_nd",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private NguoiDung nguoiDung;
    @Column(name = "id_course")
    private Long idCourse;
    @Column(name = "star")
    private String star;
    @Column(name = "comment")
    private String comment;
}
