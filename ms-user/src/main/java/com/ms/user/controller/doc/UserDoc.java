package com.ms.user.controller.doc;

import com.ms.user.dto.UserDTO;
import com.ms.user.model.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name= "user", description="API exposed for management all user")
@RequestMapping("/api/v1/user")
public interface UserDoc {
    @Operation(summary="create user", description="This operation is for the creation of an user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @PostMapping("/save")
    ResponseEntity<UserEntity> create(@Valid @RequestBody UserDTO userDTO);
    @PostMapping("/save2")
    ResponseEntity<UserEntity> createForm2(@RequestBody UserEntity userEntity);

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
    @GetMapping("/{id}")
    ResponseEntity<UserEntity> getById(@PathVariable String id);


    @Operation(summary="get all users", description="This operation is for getting the user")
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
    ResponseEntity<List<UserEntity>> listUser();

    @Operation(summary="get user by doc id", description="This operation is for getting the user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping("/document/{document}")
    ResponseEntity<UserEntity> getByDocument(@PathVariable String document);

    @Operation(summary="delete user by id", description="This operation is for getting the user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable String id);

    @Operation(summary="update user by id", description="This operation is for getting the user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @PutMapping("/{id}")
    ResponseEntity<UserEntity> updateById(@PathVariable String id, @Valid @RequestBody UserDTO userDTO);

    @Operation(summary="update user by doc id", description="This operation is for getting the user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @PutMapping("/{document}")
    ResponseEntity<UserEntity> updateByDocument(@PathVariable String document, @Valid @RequestBody UserDTO userDTO);

    @Operation(summary="get review by user id", description="This operation is for getting the user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @GetMapping("/review/{id}")
    ResponseEntity<?> getReviewByUserId(@PathVariable("id") String id);

}
// @RequestParam param es el que se pone como argumento del método
// y en la url va con el nombre del parámetro y signo de pregunta
// por ej.
// url.com/endpoint?parametro=numero
//@getMapping("endpoint/")
// api_url(@RequestParam String parametro)