package com.sinaukoding.absensi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "position")
@Setter
@Getter
@NoArgsConstructor
public class Position extends BaseEntity<Position> {

    private static final long serialVersionUID = -9199530377202661384L;

    @Column(name = "name")
    private String name;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
