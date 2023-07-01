package com.arxivj.weekend_server.domain.member.api;


import com.arxivj.weekend_server.domain.member.application.MemberSignInService;
import com.arxivj.weekend_server.domain.member.application.MemberSignUpService;
import com.arxivj.weekend_server.domain.member.dao.MemberFindDao;
import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.member.dto.MemberResponse;
import com.arxivj.weekend_server.domain.member.dto.SignInRequest;
import com.arxivj.weekend_server.domain.member.dto.SignUpRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
public class MemberApi {
    private final MemberSignUpService memberSignUpService;
    private final MemberSignInService memberSignInService;
    private final MemberFindDao memberFindDao;

    public MemberApi(MemberSignUpService memberSignUpService, MemberSignInService memberSignInService, MemberFindDao memberFindDao){
        this.memberSignUpService = memberSignUpService;
        this.memberSignInService = memberSignInService;
        this.memberFindDao = memberFindDao;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody @Valid final SignInRequest dto, HttpServletResponse response){
        memberSignInService.doSignIn(dto, response);
        System.out.println("test");
        return new ResponseEntity<>("Sign in successfully!", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody @Valid final SignUpRequest dto){
        return ResponseEntity.ok(memberSignUpService.doSignUp(dto));
    }

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable long id){
        return new MemberResponse(memberFindDao.findById(id));
    }



}
