package com.example.brs.entity.bus;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bus", indexes = @Index(name = "idx_bus_code", columnList = "code", unique = true))
public class Bus {
    @Id
    @Column(name = "bus_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Integer capacity;
    private String make;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    private Agency agency;
}
