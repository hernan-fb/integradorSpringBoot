package com.api.auth.security;

import com.api.auth.dto.RequestDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
// import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-paths")
@Data
public class RouteValidation {

    private List<RequestDto> paths;
    public Boolean isAdmin(RequestDto requestDto){

        return paths.contains(requestDto);
        // paths.stream().anyMatch(p-> Pattern.matches(p.getUri(),requestDto.getUri() && p.getMethod().equals(requestDto.getMethod())))
    }
}
