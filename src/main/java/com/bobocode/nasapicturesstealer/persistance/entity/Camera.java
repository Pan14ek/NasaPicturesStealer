package com.bobocode.nasapicturesstealer.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "cameras", schema = "public")
public class Camera {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nasa_id")
    private int nasaId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "camera")
    private List<Picture> pictures;

    @Version
    @Column(name = "version")
    private long version;
}
