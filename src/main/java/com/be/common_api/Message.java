package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "mesaages")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class Message extends BaseEntity{
    @Column(name = "message")
    private String message;
    //"text"   "image"   "file"
    @Column(name = "type")
    private String type;
    @Column(name = "linkFile")
    private String linkFile;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "createUserId")
    private Long createUserId;
    @Column(name = "createUserName")
    private String createUserName;
    @Column(name = "createUserImage")
    private String createUserImage;
}
