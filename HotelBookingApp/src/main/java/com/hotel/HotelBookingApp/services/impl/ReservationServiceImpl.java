package com.hotel.HotelBookingApp.services.impl;

import com.hotel.HotelBookingApp.dto.HotelDto;
import com.hotel.HotelBookingApp.dto.ReservationDto;
import com.hotel.HotelBookingApp.model.Hotel;
import com.hotel.HotelBookingApp.model.Reservation;
import com.hotel.HotelBookingApp.repository.HotelRepository;
import com.hotel.HotelBookingApp.repository.ReservationRepository;
import com.hotel.HotelBookingApp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.hotel.HotelBookingApp.mapper.HotelMapper.mapToHotelDto;
import static com.hotel.HotelBookingApp.mapper.ReservationMapper.mapToReservationDto;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationDto> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map((reservation) -> mapToReservationDto(reservation)).collect(Collectors.toList());
    }

    @Override
    public ReservationDto findReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        return mapToReservationDto(reservation);
    }

    @Override
    public List<ReservationDto> findByRoomIdAndHotelId(Long roomId, Long hotelId) {
        List<Reservation> reservations = reservationRepository.findByRoomId(roomId, hotelId);
        return reservations.stream().map((reservation) -> mapToReservationDto(reservation)).collect(Collectors.toList());
    }

    @Override
    public void createReservation(Long hotelId, Long roomId, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = new Reservation();
        reservation.setHotelId(hotelId);
        reservation.setRoomId(roomId);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        // Now, you can save this reservation object using your repository or EntityManager
        // For example, if you're using Spring Data JPA:
        reservationRepository.save(reservation);
    }
}
