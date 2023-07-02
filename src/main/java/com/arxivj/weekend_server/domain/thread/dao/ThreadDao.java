package com.arxivj.weekend_server.domain.thread.dao;

import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.thread.domain.Threads;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ThreadDao {

    private final ThreadRepository threadRepository;
    public Threads findThreadByMemberAndId(Member member, Long threadId){
        return this.threadRepository.findByMemberAndId(member, threadId);
    }
}
