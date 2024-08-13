package com.ms.user.controller;

import com.ms.user.controller.doc.UserDoc;
import com.ms.user.dto.UserDTO;
import com.ms.user.model.UserEntity;
import com.ms.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import java.util.List;

@AllArgsConstructor
@RestController
public class UserController implements UserDoc {
    private final IUserService iUserService;
    @Override
    public ResponseEntity<UserEntity> create(UserDTO userDTO) {
        return iUserService.save(userDTO);
    }

    @Override
    public ResponseEntity<UserEntity> createForm2(UserEntity userEntity) {
        return ResponseEntity.ok(userEntity);
    }

    @Override
    public ResponseEntity<UserEntity> getById(String id) {
        return this.iUserService.getById(id);
    }

    @Override
    public ResponseEntity<List<UserEntity>> listUser() {
        return this.iUserService.getAll();
    }

    @Override
    public ResponseEntity<UserEntity> getByDocument(String document) {
        return this.iUserService.getByDocument(document);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        return this.iUserService.deleteById(id);
    }

    @Override
    public ResponseEntity<UserEntity> updateById(String id, UserDTO userDTO) {
        return this.iUserService.updateById(id,userDTO);
    }

    @Override
    public ResponseEntity<UserEntity> updateByDocument(String document, UserDTO userDTO) {
        return this.iUserService.updateByDocument(document,userDTO);
    }

    @Override
    public ResponseEntity<?> getReviewByUserId(String userId) {
        return this.iUserService.getReviewByUserId(userId);
    }
}
