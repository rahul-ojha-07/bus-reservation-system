package com.example.brs.entity.bus;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stop", indexes = @Index(name = "idx_stop_code", columnList = "code", unique = true))
public class Stop {
    @Id
    @Column(name = "stop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String details;
    private String name;
}
