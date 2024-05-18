package com.hotel.HotelBookingApp.services.impl;

import com.hotel.HotelBookingApp.model.GeoIp;
import com.hotel.HotelBookingApp.services.LocationService;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class LocationServiceImpl implements LocationService {

    private DatabaseReader dbReader;

    public LocationServiceImpl() throws IOException {
        File database = new File("C:/Personal stuff/Java/Siemens/HotelManagmentApp/GeoLite2-City_20240517/GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    @Override
    public GeoIp getLocation(String ip)
            throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String cityName = response.getCity().getName();
        String latitude =
                response.getLocation().getLatitude().toString();
        String longitude =
                response.getLocation().getLongitude().toString();
        return new GeoIp(ip, cityName, latitude, longitude);
    }
}
