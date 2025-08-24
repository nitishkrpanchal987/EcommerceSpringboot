package com.practice.ecommercePrac.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    @JsonIgnore
    private Blob image; // it's a part of JDBC API and is defined in java.sql
    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
