package com.sinaukoding.absensi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
public class User extends BaseEntity<User>{

    private static final long serialVersionUID = 4604971173495883993L;

    public enum Role{
        ROLE_USER,
        ROLE_ADMIN
    }

    public User(String username) {
        this.username = username;
    }

    @Column(name = "role", columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column(name = "profile_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String profileName;

    @Column(name = "username", columnDefinition = "VARCHAR(50)", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "no_rekening")
    private String noRekening;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateofBirth;

    @Column(name = "domicile_address")
    private String domicileAddress;

    @Column(name = "pendidikan_terakhir")
    private String pendidikanTerakhir;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "nama_ibu")
    private String namaIbu;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "no_bpjs_ketenagakerjaan")
    private String nobpjsKetenagakerjaan;

    @Column(name = "no_bpjs_kesehatan")
    private String nobpjsKesehatan;

    @Column(name = "no_ktp")
    private String noKtp;

    @Column(name = "npwp")
    private String npwp;

    @Column(name = "phone")
    private String phone;

    @Column(name = "place_of_birth")
    private String placeofBirth;

    @Column(name = "religion")
    private String religion;

    @Column(name = "residence_address")
    private String residenceAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;
}
