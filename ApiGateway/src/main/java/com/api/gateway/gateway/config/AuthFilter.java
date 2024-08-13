package com.api.gateway.gateway.config;

import com.api.gateway.gateway.dto.RequestDto;
import com.api.gateway.gateway.dto.TokenDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.http.server.reactive.ServerHttpResponse;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>  {
    private WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    public Mono<Void> onError(ServerWebExchange exchange,
                              HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    @Override
    public GatewayFilter apply(Config config) {
        // captura las peticiones http que vienen al gateway,
        // validamos el token, autenticación y demás.
        return ((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, HttpStatus.BAD_REQUEST);
            }
            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] chunks = tokenHeader.split(" "); // BEARER  token  ;
            if (chunks.length!=2 || chunks[0].equals("Bearer")) {
                return onError(exchange, HttpStatus.BAD_REQUEST);
            }
            return webClient
                    .build()
                    .post()
                    .uri("http://auth-service/auth/validate?token="+chunks[1])
                    .bodyValue(new RequestDto(exchange.getRequest().getPath().toString(),
                                              exchange.getRequest().getMethod().toString())
                    ).retrieve() //ejecutar
                    .bodyToMono(TokenDto.class)
                    .map(
                            data->{
                                data.getToken();
                                return  exchange;
                            }
                    ).flatMap(chain::filter);
        });
    }

    public static class Config {}
}
