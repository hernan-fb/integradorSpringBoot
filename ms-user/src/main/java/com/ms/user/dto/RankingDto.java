package com.ms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingDto {

    private String userId;

    private String hotelId;

    private int score;

    private String review;

    private HotelDto hotel;
}
