package com.sinaukoding.absensi.entity;

import com.sinaukoding.absensi.AbsensiApplication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
@DynamicUpdate
@SuppressWarnings("unchecked")
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = -7227770549772803552L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @Column(name = "deleted_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedTime;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @PrePersist
    protected void onCreate(){
        setCreatedTime(new Date());
        setCreatedBy(AbsensiApplication.getCurrentUser() != null
                ? AbsensiApplication.getCurrentUser().getId()
                : 0);
    }

    @PreUpdate
    protected void onUpdate(){
        setUpdatedTime(new Date());
        setUpdatedBy(AbsensiApplication.getCurrentUser() != null
                ? AbsensiApplication.getCurrentUser().getId()
                : 0);
    }
}
