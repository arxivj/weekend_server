package com.arxivj.weekend_server.domain.member.application;


import com.arxivj.weekend_server.domain.member.dao.MemberRepository;
import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.member.dto.SignUpRequest;
import com.arxivj.weekend_server.domain.member.exception.EmailDuplicateException;
import com.arxivj.weekend_server.global.util.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@Transactional
public class MemberSignUpService {

    private final MemberRepository memberRepository;

    public MemberSignUpService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member doSignUp(final SignUpRequest dto) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new EmailDuplicateException(dto.getEmail());
        }
        String salt = PasswordEncoder.generateSalt();
        String password = PasswordEncoder.hashPassword(dto.getPassword(), salt);
        Member member = Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(password)
                .salt(salt)
                .build();
    //TODO: 회원가입시 SMTP 이메일 인증

        return memberRepository.save(member);
    }
}
