package com.arxivj.weekend_server.domain.thread.application;

import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.thread.dao.ThreadRepository;
import com.arxivj.weekend_server.domain.thread.domain.Threads;
import com.arxivj.weekend_server.domain.thread.dto.ThreadDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ThreadService {

    private final ThreadRepository threadRepository;

    public void createThread(ThreadDto.Request requestDto, Member member) {
        Threads thread = Threads.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .build();
        threadRepository.save(thread);
    }



}