package com.hotel.HotelBookingApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    @Column(name = "room_type")
    private int type;
    private double price;
    //private boolean isAvailable;
    @ManyToOne
    @JoinColumn(name="hotel_id", nullable = false)
    private Hotel hotel;

}