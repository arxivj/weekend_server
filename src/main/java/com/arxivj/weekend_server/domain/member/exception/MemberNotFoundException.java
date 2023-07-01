package com.arxivj.weekend_server.domain.member.exception;


import jakarta.persistence.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(Long target){
        super(target + " is not found");
    }

}
