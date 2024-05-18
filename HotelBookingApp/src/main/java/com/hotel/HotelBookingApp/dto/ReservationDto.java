package com.hotel.HotelBookingApp.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class ReservationDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long hotelId;
    private Long roomId;
}