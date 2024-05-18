package com.hotel.HotelBookingApp.repository;

import com.hotel.HotelBookingApp.model.Hotel;
import com.hotel.HotelBookingApp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.roomId = :roomId AND r.hotelId = :hotelId")
    List<Reservation> findByRoomId(@Param("roomId") Long roomId, @Param("hotelId") Long hotelId);
}
