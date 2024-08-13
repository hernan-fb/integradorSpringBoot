package com.ms.hotel.repository;

import com.ms.hotel.model.HotelEntity;
import com.ms.hotel.record.HotelRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IHotelRepository extends JpaRepository<HotelEntity, UUID> {
}