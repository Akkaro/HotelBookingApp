package com.hotel.HotelBookingApp.services;

import com.hotel.HotelBookingApp.dto.HotelDto;
import com.hotel.HotelBookingApp.dto.RoomDto;

import java.time.LocalDate;
import java.util.List;

public interface HotelService {
    List<HotelDto> findAllHotels();
    HotelDto findHotelById(Long hotelId);
    void setRoomAvailability(List<HotelDto> hotelDtos, LocalDate startDate, LocalDate endDate);
    public List<HotelDto> removeHotelsOutsideOfRange(List<HotelDto> hotelDtos, double longitude, double latitude, int range);
}
