package com.hotel.HotelBookingApp.services.impl;

import com.hotel.HotelBookingApp.dto.HotelDto;
import com.hotel.HotelBookingApp.dto.RoomDto;
import com.hotel.HotelBookingApp.model.Hotel;
import com.hotel.HotelBookingApp.model.Room;
import com.hotel.HotelBookingApp.repository.HotelRepository;
import com.hotel.HotelBookingApp.repository.RoomRepository;
import com.hotel.HotelBookingApp.services.ReservationService;
import com.hotel.HotelBookingApp.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.hotel.HotelBookingApp.mapper.HotelMapper.mapToHotelDto;
import static com.hotel.HotelBookingApp.mapper.RoomMapper.mapToRoomDto;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;
    private ReservationService reservationService;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, ReservationService reservationService) {
        this.roomRepository = roomRepository;
        this.reservationService = reservationService;
    }
    @Override
    public List<RoomDto> findAllRooms() {
        List<Room> rooms = roomRepository.findAll();

        List<RoomDto> roomDtos = rooms.stream().map((room) -> mapToRoomDto(room)).collect(Collectors.toList());
        return roomDtos;
    }

    @Override
    public List<RoomDto> findByHotelId(Long hotelId) {
        return null;
    }


}
