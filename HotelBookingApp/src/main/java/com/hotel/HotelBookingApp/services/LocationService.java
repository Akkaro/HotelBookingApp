package com.hotel.HotelBookingApp.services;

import com.hotel.HotelBookingApp.model.GeoIp;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;

public interface LocationService {
    public GeoIp getLocation(String ip) throws IOException, GeoIp2Exception;
}
