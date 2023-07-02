package com.arxivj.weekend_server.domain.thread.api;

import com.arxivj.weekend_server.domain.member.dao.MemberFindDao;
import com.arxivj.weekend_server.domain.member.dao.MemberRepository;
import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.model.Email;
import com.arxivj.weekend_server.domain.thread.application.ThreadService;
import com.arxivj.weekend_server.domain.thread.dto.ThreadDto;
import com.arxivj.weekend_server.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/thread")
@RequiredArgsConstructor
public class ThreadApi {

    private final MemberFindDao memberFindDao;
    private final ThreadService threadService;

    //Post
    @PostMapping
    public ResponseEntity<HttpStatus> postThread(@RequestHeader("Authorization") String authorization,
                                                 @RequestBody ThreadDto.Request requestDto) {
        //TODO: 디코딩 하기 전에 토큰 검증이 필요할까?
        Email email = JwtUtil.getEmailFromAuthorization(authorization);
        Member member = memberFindDao.findByEmail(email);
        threadService.createThread(requestDto, member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Get (전체 게시물)
    @GetMapping
    public ResponseEntity<HttpStatus> getThreads(Optional<Integer> page) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Get (정렬된 게시물들)
    @GetMapping("/{sort-type}")
    public ResponseEntity<HttpStatus> getSortedThreads(@PathVariable("sort-type") String sortType,
                                                       Optional<Integer> page) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Patch
    @PatchMapping("/{thread-id}")
    public ResponseEntity<HttpStatus> patchThread(@RequestHeader("Authorization") String authorization,
                                                  @PathVariable("thread-id") Long threadId) {
        Email email = JwtUtil.getEmailFromAuthorization(authorization);
        Member member = memberFindDao.findByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{thread-id}")
    public ResponseEntity<HttpStatus> deleteThread(@RequestHeader("Authorization") String authorization,
                                                   @PathVariable("thread-id") Long threadId) {
        Email email = JwtUtil.getEmailFromAuthorization(authorization);
        Member member = memberFindDao.findByEmail(email);
        threadService.deleteThreadByMemberId(member, threadId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
