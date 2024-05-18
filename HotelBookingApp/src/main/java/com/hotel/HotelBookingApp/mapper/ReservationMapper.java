package com.hotel.HotelBookingApp.mapper;

import com.hotel.HotelBookingApp.dto.ReservationDto;
import com.hotel.HotelBookingApp.model.Reservation;

public class ReservationMapper {
    public static Reservation mapToReservation(ReservationDto reservationDto) {
        Reservation reservation = Reservation.builder()
                .id(reservationDto.getId())
                .hotelId(reservationDto.getHotelId())
                .roomId(reservationDto.getRoomId())
                .startDate(reservationDto.getStartDate())
                .endDate(reservationDto.getEndDate())
                .build();
        return reservation;
    }
    public static ReservationDto mapToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = ReservationDto.builder()
                .id(reservation.getId())
                .hotelId(reservation.getHotelId())
                .roomId(reservation.getRoomId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
        return reservationDto;
    }


}

