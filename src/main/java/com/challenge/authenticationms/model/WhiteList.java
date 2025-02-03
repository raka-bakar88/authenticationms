package com.challenge.authenticationms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "white_list")
@Data
public class WhiteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "identifier")
    private String identifier;

    @Column(nullable = false, name = "authorization_status")
    private boolean status;
}
