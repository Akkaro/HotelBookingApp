package com.hotel.HotelBookingApp.controller;

import com.hotel.HotelBookingApp.dto.HotelDto;
import com.hotel.HotelBookingApp.model.GeoIp;
import com.hotel.HotelBookingApp.services.HotelService;
import com.hotel.HotelBookingApp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;

@Controller
public class HotelController {
    private HotelService hotelService;
    private ReservationService reservationService;
    private GeoIpController geoIpController;

    @Autowired
    public HotelController(HotelService hotelService, ReservationService reservationService, GeoIpController geoIpController) {
        this.hotelService = hotelService;
        this.reservationService = reservationService;
        this.geoIpController = geoIpController;
    }

    @GetMapping("/hotels")
    public String listHotels(Model model){
        List<HotelDto> hotels = hotelService.findAllHotels();
        model.addAttribute("hotels", hotels);
        return "hotels-list";
    }


    @GetMapping("/hotels/{hotelId}/details")
    public String hotelDetail(@PathVariable Long hotelId,
                              @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                              @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                              Model model) {
      HotelDto hotelDto = hotelService.findHotelById(hotelId);
        List<HotelDto> hotelDtos = new ArrayList<>();
        hotelDtos.add(hotelDto);
        hotelService.setRoomAvailability(hotelDtos, startDate, endDate);
        model.addAttribute("startDatePrev", startDate);
        model.addAttribute("endDatePrev", endDate);
        model.addAttribute("hotel", hotelDto);
        return "hotels-details";
    }


    @GetMapping("/hotels/search")
    public String searchHotels(
            @RequestParam(value = "searchRange", required = false) Integer searchRange,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) throws Exception {

        // Now you can use searchRange, startDate, endDate, latitude, and longitude in your method

        GeoIp geoIp = geoIpController.getLocation();
        double lat_meters = 111132.92 - 559.82 * cos(2 * Double.parseDouble(geoIp.getLatitude())) + 1.175 * cos(4 * Double.parseDouble(geoIp.getLatitude())) - 0.0023 * cos(6 * Double.parseDouble(geoIp.getLatitude()));
        double lon_meters = 111412.84 * cos(Double.parseDouble(geoIp.getLongitude())) - 93.5 * cos(3 * Double.parseDouble(geoIp.getLongitude())) + 0.118 * cos(5 * Double.parseDouble(geoIp.getLongitude()));


        List<HotelDto> hotels = hotelService.findAllHotels();
        hotelService.setRoomAvailability(hotels, startDate, endDate);
        List<HotelDto> finalHotelList = hotelService.removeHotelsOutsideOfRange(hotels, lon_meters, lat_meters, searchRange);
        model.addAttribute("hotels", finalHotelList);
        model.addAttribute("startDatePrev", startDate);
        model.addAttribute("endDatePrev", endDate);

        return "hotels-list";
    }

    @PostMapping("/hotels/reserveRoom")
    public String makeReservation(@RequestParam("hotelId") Long hotelId,
                                  @RequestParam("roomId") Long roomId,
                                  @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                  @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                  Model model) {
        reservationService.createReservation(hotelId, roomId, startDate, endDate);
        return hotelDetail(hotelId, startDate, endDate, model);
    }




}
