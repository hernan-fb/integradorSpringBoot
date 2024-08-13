package com.ms.ranking.repository;

import com.ms.ranking.model.RankingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IRankingRepository extends MongoRepository<RankingDocument, String> {
    List<RankingDocument> findByUserId(String userId);

    List<RankingDocument> findByHotelId(String hotelId);
}
