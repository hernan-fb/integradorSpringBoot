package com.api.auth.service.impl;

import com.api.auth.dto.AuthUserDto;
import com.api.auth.dto.NewUserDto;
import com.api.auth.dto.RequestDto;
import com.api.auth.dto.TokenDto;
import com.api.auth.model.AuthUser;
import com.api.auth.repository.AuthRepository;
import com.api.auth.security.JwtProvider;
import com.api.auth.service.IAuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements IAuthService {
    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Override
    public AuthUser save(NewUserDto newUserDto) {
        Optional<AuthUser> user = this
                .authRepository
                .findByUserName(newUserDto.getUserName());

        if (user.isPresent()) {
            return null; //todo exception
        }
        String password = this
                .passwordEncoder
                .encode(newUserDto.getPassword());

        AuthUser authUser = AuthUser
                .builder()
                .userName(newUserDto.getUserName())
                .password(password)
                .role(newUserDto.getRole())
                .build();

        return this.authRepository.save(authUser);
    }

    @Override
    public TokenDto login(AuthUserDto authUserDto) {
        Optional<AuthUser> user = this
                .authRepository
                .findByUserName(authUserDto.getUserName());

        if (user.isEmpty()) {
            return null;
        }
        if (this.passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword())){
            return new TokenDto(this.jwtProvider.createToken(user.get()));
        }
        return null;
    }

    @Override
    public TokenDto validate(String token, RequestDto requestDto) {
        if (!this.jwtProvider.validate(token,requestDto)) {
            return  null;
        }
        String username = this.jwtProvider.getUserNameFromToken(token);

        if (!this.authRepository.findByUserName(username).isPresent()) {
            return null;
        }

        return new TokenDto(token);
    }
}
