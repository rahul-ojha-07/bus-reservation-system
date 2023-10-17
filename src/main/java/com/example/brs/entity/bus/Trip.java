package com.example.brs.entity.bus;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @Column(name = "trip_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double fare;

    @Column(name = "journey_time")
    private Integer journeyTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_stop_id")
    private Stop sourceStop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dest_stop_id")
    private Stop destStop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    private Agency agency;
}