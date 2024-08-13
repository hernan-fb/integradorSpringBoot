package com.ms.ranking.service.impl;

import com.ms.ranking.dto.RankingDTO;
import com.ms.ranking.model.RankingDocument;
import com.ms.ranking.repository.IRankingRepository;
import com.ms.ranking.service.IRankingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RankingServiceImpl implements IRankingService {
    private final IRankingRepository rankingRepository;

    @Override
    public ResponseEntity<RankingDocument> create(RankingDTO rankingDTO) {
            RankingDocument newDocument = RankingDocument.builder()
                    .score(rankingDTO.getScore())
                    .review(rankingDTO.getReview())
                    .userId(rankingDTO.getUserId())
                    .hotelId(rankingDTO.getHotelId())
                    .build();
        var ranking = rankingRepository.save(newDocument);

        return ResponseEntity.ok(ranking);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getAll() {
        return ResponseEntity.ok(rankingRepository.findAll());
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getByUserId(String userId) {
        var userFinded = rankingRepository.findByUserId(userId);
        return ResponseEntity.ok(userFinded);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getByHotelId(String hotelId) {
        var hotelFinded = rankingRepository.findByHotelId(hotelId);
        return ResponseEntity.ok(hotelFinded);
    }
}
