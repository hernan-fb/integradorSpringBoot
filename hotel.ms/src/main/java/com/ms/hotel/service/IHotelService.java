package com.ms.hotel.service;

import com.ms.hotel.dto.HotelDto;
import com.ms.hotel.model.HotelEntity;
import com.ms.hotel.record.HotelRec;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IHotelService {
    ResponseEntity<HotelEntity> save(HotelDto hotelDto);

    ResponseEntity<HotelEntity> updateById(HotelDto hotelDto, UUID id);

    ResponseEntity<HotelEntity> updateByDocument(String document, HotelRec hotelRec);

    void deleteById(UUID id);

    ResponseEntity<List<HotelEntity>> getAll();

    ResponseEntity<HotelEntity> getById(UUID id);

    ResponseEntity<HotelEntity> getByDocument(String document);
}