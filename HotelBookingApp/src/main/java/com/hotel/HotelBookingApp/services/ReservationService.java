package com.hotel.HotelBookingApp.services;

import com.hotel.HotelBookingApp.dto.HotelDto;
import com.hotel.HotelBookingApp.dto.ReservationDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<ReservationDto> findAllReservations();
    ReservationDto findReservationById(Long reservationId);
    List<ReservationDto> findByRoomIdAndHotelId(Long roomId, Long hotelId);
    void createReservation(Long hotelId, Long roomId, LocalDate startDate, LocalDate endDate);
}
