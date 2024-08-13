package com.ms.ranking.controller.doc;

import com.ms.ranking.dto.RankingDTO;
import com.ms.ranking.model.RankingDocument;
import com.ms.ranking.service.IRankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name= "ranking", description="API exposed for management all rankings")
@RequestMapping("/api/v1/ranking")
public interface RankingDoc {
    @Operation(summary="create ranking", description="This operation is for the creation of a ranking")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="ranking created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @PostMapping
    ResponseEntity<RankingDocument> create(RankingDTO rankingDTO);

    @Operation(summary="get ranking by id user", description="This operation is for getting the ranking by user id")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping("user/{id}")
    public ResponseEntity<List<RankingDocument>> getByUserId(@PathVariable String id);

    @Operation(summary="get ranking by id document", description="This operation is for getting the ranking by document id")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping("hotel/{id}")
    ResponseEntity<List<RankingDocument>> getByHotelId(String id);

    @Operation(summary="get user by id", description="This operation is for getting the user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping
    ResponseEntity<List<RankingDocument>> getAll();

}
