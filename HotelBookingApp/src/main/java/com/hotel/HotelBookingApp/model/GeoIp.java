package com.hotel.HotelBookingApp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeoIp {
    private String ipAddress;
    private String city;
    private String latitude;
    private String longitude;
}