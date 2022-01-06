package com.sinaukoding.absensi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "division")
@Setter
@Getter
@NoArgsConstructor
public class Division extends BaseEntity<Division> {

    private static final long serialVersionUID = -8600832394688679127L;

    @Column(name = "name")
    private String name;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
