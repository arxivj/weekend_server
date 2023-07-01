package com.arxivj.weekend_server.domain.member.exception;


import jakarta.persistence.EntityNotFoundException;

public class EmailNotFoundException extends EntityNotFoundException {

    public EmailNotFoundException(String target){
        super(target + " is not found");
    }
}
