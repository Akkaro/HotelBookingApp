package com.hotel.HotelBookingApp.repository;

import com.hotel.HotelBookingApp.model.Hotel;
import com.hotel.HotelBookingApp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
