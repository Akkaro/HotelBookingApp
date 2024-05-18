package com.hotel.HotelBookingApp.mapper;

import com.hotel.HotelBookingApp.dto.RoomDto;
import com.hotel.HotelBookingApp.model.Hotel;
import com.hotel.HotelBookingApp.model.Room;

import java.util.stream.Collectors;

public class RoomMapper {
    public static RoomDto mapToRoomDto(Room room) {
        RoomDto roomDto = RoomDto.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .type(room.getType())
                .price(room.getPrice())
                .hotel(room.getHotel())
                .build();
        return roomDto;
    }

    public static Room mapToRoom(RoomDto roomDto) {
        Room room = Room.builder()
                .id(roomDto.getId())
                .roomNumber(roomDto.getRoomNumber())
                .type(roomDto.getType())
                .price(roomDto.getPrice())
                .hotel(roomDto.getHotel())
                .build();
        return room;
    }
}
