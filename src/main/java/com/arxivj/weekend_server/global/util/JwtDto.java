package com.arxivj.weekend_server.global.util;

import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
public class JwtDto {
    private HttpHeaders httpHeaders;
    private String refreshToken;

}
