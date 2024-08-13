package com.ms.ranking.controller;

import com.ms.ranking.controller.doc.RankingDoc;
import com.ms.ranking.dto.RankingDTO;
import com.ms.ranking.model.RankingDocument;
import com.ms.ranking.service.IRankingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class RankingController implements RankingDoc {
    private final IRankingService iRankingService;
    @Override
    public ResponseEntity<RankingDocument> create(RankingDTO rankingDTO) {
        return iRankingService.create(rankingDTO);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getByUserId(String id) {
        return this.iRankingService.getByUserId(id);
    }
    @Override
    public ResponseEntity<List<RankingDocument>> getByHotelId(String id) {
        return this.iRankingService.getByHotelId(id);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getAll() {
        return this.iRankingService.getAll();
    }
}
