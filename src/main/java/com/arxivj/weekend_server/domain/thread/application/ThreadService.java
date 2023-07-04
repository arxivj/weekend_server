package com.arxivj.weekend_server.domain.thread.application;

import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.thread.dao.ThreadDao;
import com.arxivj.weekend_server.domain.thread.dao.ThreadRepository;
import com.arxivj.weekend_server.domain.thread.domain.Threads;
import com.arxivj.weekend_server.domain.thread.dto.ThreadDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final ThreadDao threadDao;

    public ThreadDto.PostResponse createThread(ThreadDto.Request requestDto, Member member) {
        Threads thread = Threads.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .build();
        this.threadRepository.save(thread);
        return thread.toPostResponse();
    }

    public ThreadDto.GetResponse getThreadList(Pageable pageable){
        Page<Threads> threadList = threadRepository.findAll(pageable);
        int nowPage = threadList.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, threadList.getTotalPages());
        return ThreadDto.GetResponse.builder()
                .threadList(threadList)
                .nowPage(nowPage)
                .startPage(startPage)
                .endPage(endPage)
                .build();
    }

    public ThreadDto.PatchResponse updateThreadByMemberAndId(ThreadDto.Request requestDto, Member member, Long threadId) {
        Threads thread = threadDao.findThreadByMemberAndId(member, threadId);
        return updateThread(requestDto, thread);
    }

    public ThreadDto.PatchResponse updateThread(ThreadDto.Request requestDto, Threads thread) {
        Threads updatedThread = thread.toBuilder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();
        threadRepository.save(updatedThread);
        return updatedThread.toPatchResponse();
    }


    public void deleteThreadByMemberAndId(Member member, Long threadId) {
        Threads thread = threadDao.findThreadByMemberAndId(member, threadId);
        this.threadRepository.delete(thread);
    }

}
