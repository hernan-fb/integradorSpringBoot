package com.api.auth.controller;

import com.api.auth.dto.AuthUserDto;
import com.api.auth.dto.NewUserDto;
import com.api.auth.dto.RequestDto;
import com.api.auth.dto.TokenDto;
import com.api.auth.model.AuthUser;
import com.api.auth.service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService iAuthService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {
        TokenDto tokenDto = this.iAuthService.login(authUserDto);

        if (tokenDto==null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    public ResponseEntity<TokenDto> validate(@RequestParam String token,
                                             @RequestBody RequestDto requestDto) {
        TokenDto tokenDto = this.iAuthService.validate(token,requestDto);
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tokenDto);
    }

    public ResponseEntity<AuthUser> create(@RequestBody NewUserDto newUserDto) {
        AuthUser authUser = this.iAuthService.save(newUserDto);
        if (authUser==null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }
}
