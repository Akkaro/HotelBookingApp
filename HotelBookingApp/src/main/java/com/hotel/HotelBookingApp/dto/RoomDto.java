package com.hotel.HotelBookingApp.dto;

import com.hotel.HotelBookingApp.model.Hotel;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RoomDto {
    private Long id;
    private int roomNumber;
    private int type;
    private double price;
    private Hotel hotel;
    private List<ReservationDto> reservationDtos;
    private Boolean free;
}
