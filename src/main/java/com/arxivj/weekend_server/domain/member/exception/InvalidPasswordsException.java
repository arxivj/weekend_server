package com.arxivj.weekend_server.domain.member.exception;

public class InvalidPasswordsException extends RuntimeException {
    public InvalidPasswordsException(){
        super("올바른 비밀번호를 입력해주세요!");
    }
}
