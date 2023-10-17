package com.example.brs.entity.bus;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "trip_schedule")
public class TripSchedule {
    @Id
    @Column(name = "trip_schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "available_seat")
    private Integer availableSeats;

    @Column(name = "trip_date")
    private String tripDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
