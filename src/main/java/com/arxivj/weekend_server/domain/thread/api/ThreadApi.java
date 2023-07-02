package com.arxivj.weekend_server.domain.thread.api;

import com.arxivj.weekend_server.domain.member.dao.MemberFindDao;
import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.model.Email;
import com.arxivj.weekend_server.domain.thread.application.ThreadService;
import com.arxivj.weekend_server.domain.thread.dao.ThreadDao;
import com.arxivj.weekend_server.domain.thread.dao.ThreadRepository;
import com.arxivj.weekend_server.domain.thread.domain.Threads;
import com.arxivj.weekend_server.domain.thread.dto.ThreadDto;
import com.arxivj.weekend_server.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
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
    private final ThreadRepository threadRepository;
    private final ThreadDao threadDao;

    //Post
    @PostMapping
    public ResponseEntity<ThreadDto.PostResponse> postThread(@RequestHeader("Authorization") String authorization,
                                                             @RequestBody ThreadDto.Request requestDto) {
        //TODO: 디코딩 하기 전에 토큰 검증이 필요할까?
        Email email = JwtUtil.getEmailFromAuthorization(authorization);
        Member member = memberFindDao.findByEmail(email);
        ThreadDto.PostResponse response = threadService.createThread(requestDto, member);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //TODO: Get으로 게시물 리스트 요청 남음
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
    public ResponseEntity<ThreadDto.PatchResponse> patchThread(@RequestBody ThreadDto.Request requestDto,
                                                               @RequestHeader("Authorization") String authorization,
                                                               @PathVariable("thread-id") Long threadId) {
        Email email = JwtUtil.getEmailFromAuthorization(authorization);
        Member member = memberFindDao.findByEmail(email);
        ThreadDto.PatchResponse response = threadService.updateThreadByMemberAndId(requestDto, member, threadId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{thread-id}")
    public ResponseEntity<HttpStatus> deleteThread(@RequestHeader("Authorization") String authorization,
                                                   @PathVariable("thread-id") Long threadId) {
        Email email = JwtUtil.getEmailFromAuthorization(authorization);
        Member member = memberFindDao.findByEmail(email);
        threadService.deleteThreadByMemberAndId(member, threadId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
