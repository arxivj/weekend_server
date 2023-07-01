package com.arxivj.weekend_server.domain.member.exception;

public class InvalidPasswordsException extends RuntimeException {
    public InvalidPasswordsException(){
        super("wrong password");
    }
}
