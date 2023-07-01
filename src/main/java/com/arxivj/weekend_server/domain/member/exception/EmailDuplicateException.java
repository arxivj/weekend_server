package com.arxivj.weekend_server.domain.member.exception;


import com.arxivj.weekend_server.domain.model.Email;

public class EmailDuplicateException extends RuntimeException{
    public EmailDuplicateException(final Email email){
        super(email.getValue()+" is already exists");
    }

}
