package com.arxivj.weekend_server.domain.member.dto;

import com.arxivj.weekend_server.domain.model.Email;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SignInRequest {


    @Valid
    private Email email;

    private String password;

    SignInRequest(@Valid Email email, String password){
        this.email = email;
        this.password = password;
    }


}
