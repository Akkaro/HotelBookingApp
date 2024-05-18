package com.hotel.HotelBookingApp.dto;


import com.hotel.HotelBookingApp.model.Room;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HotelDto {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private List<RoomDto> rooms;
    private Boolean hasAvailableRooms;
}
