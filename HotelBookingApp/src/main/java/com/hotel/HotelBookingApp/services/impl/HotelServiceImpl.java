package com.hotel.HotelBookingApp.services.impl;

import com.hotel.HotelBookingApp.dto.HotelDto;
import com.hotel.HotelBookingApp.dto.ReservationDto;
import com.hotel.HotelBookingApp.dto.RoomDto;
import com.hotel.HotelBookingApp.model.Hotel;
import com.hotel.HotelBookingApp.repository.HotelRepository;
import com.hotel.HotelBookingApp.services.HotelService;
import com.hotel.HotelBookingApp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.hotel.HotelBookingApp.mapper.HotelMapper.mapToHotelDto;
import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository  hotelRepository;
    private ReservationService reservationService;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, ReservationService reservationService) {
        this.hotelRepository = hotelRepository;
        this.reservationService = reservationService;
    }


    @Override
    public List<HotelDto> findAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map((hotel) -> mapToHotelDto(hotel)).collect(Collectors.toList());
    }

    @Override
    public HotelDto findHotelById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        HotelDto hotelDto = mapToHotelDto(hotel);

        for(RoomDto roomDto : hotelDto.getRooms()){

            roomDto.setReservationDtos(reservationService.findByRoomIdAndHotelId(roomDto.getId(), hotelDto.getId()));
        }
        return hotelDto;
    }


    @Override
    public void setRoomAvailability(List<HotelDto> hotelDtos, LocalDate startDate, LocalDate endDate) {
        for(HotelDto hotelDto : hotelDtos)
        {
            hotelDto.setHasAvailableRooms(false);
            for(RoomDto roomDto : hotelDto.getRooms()) {
                List<ReservationDto> reservationDtos = reservationService.findByRoomIdAndHotelId(roomDto.getId(), hotelDto.getId());

                roomDto.setFree(true);
                for(ReservationDto reservationDto : reservationDtos){
                    if ((startDate.isAfter(reservationDto.getStartDate()) || startDate.isEqual(reservationDto.getStartDate()))
                            && (startDate.isBefore(reservationDto.getEndDate()) || startDate.isEqual(reservationDto.getEndDate()))) {
                        roomDto.setFree(false);
                        break;
                    }
                    if ((endDate.isAfter(reservationDto.getStartDate()) || endDate.isEqual(reservationDto.getStartDate()))
                            && (endDate.isBefore(reservationDto.getEndDate()) || endDate.isEqual(reservationDto.getEndDate()))) {
                        roomDto.setFree(false);
                        break;
                    }

                }
                if(roomDto.getFree())
                    hotelDto.setHasAvailableRooms(true);
            }
        }

    }

    @Override
    public List<HotelDto> removeHotelsOutsideOfRange(List<HotelDto> hotelDtos, double longitude, double latitude, int range)
    {
        List<HotelDto> hotelDtosResult = new ArrayList<>();
        for(HotelDto hotelDto : hotelDtos){
            double lat_meters = 111132.92 - 559.82 * cos(2 * hotelDto.getLatitude()) + 1.175 * cos(4 * hotelDto.getLatitude()) - 0.0023 * cos(6 * hotelDto.getLatitude());
            double lon_meters = 111412.84 * cos(hotelDto.getLongitude()) - 93.5 * cos(3 * hotelDto.getLongitude()) + 0.118 * cos(5 * hotelDto.getLongitude());

            double distance = sqrt(((longitude-lon_meters)*(longitude-lon_meters)) + ((latitude-lat_meters)*(latitude-lat_meters)));


            if(distance<(range*1000))
                hotelDtosResult.add(hotelDto);
        }
        return hotelDtosResult;
    }
}
