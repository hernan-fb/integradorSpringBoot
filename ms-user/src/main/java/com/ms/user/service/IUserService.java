package com.ms.user.service;

import com.ms.user.dto.UserDTO;
import com.ms.user.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<UserEntity> save(UserDTO userDTO);
    ResponseEntity<UserEntity> updateById(String id, UserDTO userDTO);
    ResponseEntity<UserEntity> updateByDocument(String document, UserDTO userDTO);

    ResponseEntity<?> deleteById(String id);

    ResponseEntity<List<UserEntity>> getAll();

    ResponseEntity<UserEntity> getById(String id);

    ResponseEntity<UserEntity> getByDocument(String document);

    ResponseEntity<?> getReviewByUserId(String id);
}
