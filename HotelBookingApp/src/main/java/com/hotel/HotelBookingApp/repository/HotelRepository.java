package com.hotel.HotelBookingApp.repository;

import com.hotel.HotelBookingApp.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
