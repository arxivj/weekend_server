package com.arxivj.weekend_server.domain.member.dto;


import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.model.Email;
import com.arxivj.weekend_server.domain.model.Name;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SignUpRequest {

    @Valid
    private Email email;

    @Valid
    private Name name;

    private String password;

    SignUpRequest(@Valid Email email, @Valid Name name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }
    public Member toEntity(){
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

}
