package com.hotel.HotelBookingApp.controller;

import com.hotel.HotelBookingApp.model.GeoIp;
import com.hotel.HotelBookingApp.services.LocationService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Controller
public class GeoIpController {
    private LocationService locationService;

    @Autowired
    public GeoIpController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/GeoIPTest")
    public GeoIp getLocation() throws IOException, GeoIp2Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        String ip = in.readLine();

        return locationService.getLocation(ip);
    }
}
