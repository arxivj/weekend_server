package com.arxivj.weekend_server.domain.thread.dto;

import com.arxivj.weekend_server.domain.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetDetailResponse{
        private Long id;
        private String title;
        private String content;
        private long viewCount;
        private long replyCount;
        private Member member; //TODO: Threads List를 넘기는걸로 변경
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
        //TODO : GetDetailResponse와 동일한 응답 데이터, 중복X
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PatchResponse{
        //TODO : GetDetailResponse와 동일한 페이지 데이터, 중복X
    }




}
