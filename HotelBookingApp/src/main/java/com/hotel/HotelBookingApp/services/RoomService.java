package com.hotel.HotelBookingApp.services;

import com.hotel.HotelBookingApp.dto.HotelDto;
import com.hotel.HotelBookingApp.dto.RoomDto;
import com.hotel.HotelBookingApp.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<RoomDto> findAllRooms();
    List<RoomDto> findByHotelId(Long hotelId);

}
