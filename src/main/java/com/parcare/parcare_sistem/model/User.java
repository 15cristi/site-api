package com.parcare.parcare_sistem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "ROLE_USER";


    @Column(name = "full_name")
    private String fullName;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

}
