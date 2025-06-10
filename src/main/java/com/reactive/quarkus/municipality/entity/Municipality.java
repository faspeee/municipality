package com.reactive.quarkus.municipality.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "municipality")
public final class Municipality {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "region_code")
    private String regionCode;
    @Column(name = "province_code")
    private String provinceCode;
    @Column(name = "municipality_code")
    private String municipalityCode;
    @Column(name = "municipality_sigle")
    private String municipalitySigle;
    @Column(name = "municipality_name")
    private String municipalityName;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "cadastral_code")
    private String cadastralCode;
    @Column(name = "territorial_unit_type")
    private String territorialUnitType;
    @Column(name = "capitals_municipality")
    private String capitalsMunicipality;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "altitude")
    private double altitude;

}
