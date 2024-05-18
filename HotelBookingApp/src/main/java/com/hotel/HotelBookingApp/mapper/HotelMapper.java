package com.hotel.HotelBookingApp.mapper;

import com.hotel.HotelBookingApp.dto.HotelDto;
import com.hotel.HotelBookingApp.model.Hotel;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

import static com.hotel.HotelBookingApp.mapper.RoomMapper.mapToRoom;
import static com.hotel.HotelBookingApp.mapper.RoomMapper.mapToRoomDto;

@Component
public class HotelMapper {

    public static Hotel mapToHotel(HotelDto hotelDto) {
        Hotel hotel = Hotel.builder()
                .id(hotelDto.getId())
                .name(hotelDto.getName())
                .latitude(hotelDto.getLatitude())
                .longitude(hotelDto.getLongitude())
                .rooms(hotelDto.getRooms().stream().map((room) -> mapToRoom(room)).collect(Collectors.toList()))
                .build();
        return hotel;
    }

    public static HotelDto mapToHotelDto(Hotel hotel) {
        HotelDto hotelDto = HotelDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .latitude(hotel.getLatitude())
                .longitude(hotel.getLongitude())
                .rooms(hotel.getRooms().stream().map((room) -> mapToRoomDto(room)).collect(Collectors.toList()))
                .build();
        return hotelDto;
    }
}
