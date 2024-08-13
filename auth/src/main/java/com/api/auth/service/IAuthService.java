package com.api.auth.service;

import com.api.auth.dto.AuthUserDto;
import com.api.auth.dto.NewUserDto;
import com.api.auth.dto.RequestDto;
import com.api.auth.dto.TokenDto;
import com.api.auth.model.AuthUser;

public interface IAuthService {
    AuthUser save(NewUserDto newUserDto);

    TokenDto login(AuthUserDto authUserDto);

    TokenDto validate(String token, RequestDto requestDto);
}
