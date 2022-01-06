package com.sinaukoding.absensi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@Setter
@Getter
@NoArgsConstructor
public class Employee extends BaseEntity<Employee> {

    private static final long serialVersionUID = 5256350295709435787L;

    public enum StatusEmployee {
        ACTIVE,
        INACTIVE
    }

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "nip")
    private String nip;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEmployee status = StatusEmployee.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
