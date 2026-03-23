package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    private String phone;
    private String address;
    private String city;
    private String province;
    private String postalCode;

    // Size Profile Fields
    private String preferredNailSize;
    private String preferredNecklaceLength;
    private String preferredSunglassesSize;
    private String preferredStyle;

    private Boolean active = true;

    @CreationTimestamp
    private Timestamp createdAt;
}