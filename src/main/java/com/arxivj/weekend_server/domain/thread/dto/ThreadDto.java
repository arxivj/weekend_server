package com.arxivj.weekend_server.domain.thread.dto;

import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.thread.domain.Threads;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class ThreadDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        @NotNull
        private String title;
        @NotNull
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class GetResponse{
        private Page<Threads> threadList;
        private int nowPage;
        private int startPage;
        private int endPage;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetDetailResponse{
        private Long id;
        private String title;
        private String content;
        private long viewCount;
        private long replyCount;
        private Member member; //TODO: Threads List를 넘기는걸로 변경, createdAt과 modifiedAt은 어떻게..?
    }

//    @Getter
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    public static class GetSortedResponse{
//
//    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostResponse{
        private Long id;
        private String title;
        private String content;
        private long viewCount;
        private long replyCount;
        private Member member;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PatchResponse{
        private Long id;
        private String title;
        private String content;
        private long viewCount;
        private long replyCount;
        private Member member;
        private LocalDateTime modifiedAt;
    }




}
