package com.arxivj.weekend_server.domain.member.exception;
import com.arxivj.weekend_server.domain.model.Email;
import jakarta.persistence.EntityNotFoundException;

public class EmailNotFoundException extends EntityNotFoundException {

    public EmailNotFoundException(Email target){
        super(target.getValue() + "는 가입된 회원이 아닙니다.");
    }
}
