package com.arxivj.weekend_server.domain.thread.domain;
import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.model.PreserveState;
import com.arxivj.weekend_server.domain.thread.dto.ThreadDto;
import com.arxivj.weekend_server.global.config.MutableBaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "threads")
@NoArgsConstructor
public class Threads extends MutableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ColumnDefault(value="0")
    private long viewCount;

    @ColumnDefault(value="0")
    private long replyCount;

    @Embedded
    private PreserveState preserveState;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member member;

    @Builder(toBuilder = true)
    public Threads(Long id, String title, String content, long viewCount, long replyCount, PreserveState preserveState, Member member){
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
        this.preserveState = preserveState == null ? new PreserveState() : preserveState;
        this.member = member;
    }

    public void updatePreserveState(){
        this.preserveState.setIsDeleted(true);
        this.preserveState.setDeletedAt(LocalDateTime.now());
    }


    // Entity의 상태를 이용하여 Dto를 생성하는 로직이라 여기 적음
    // 그러나 Dto 클래스에 적어도 무관. 그러면 Dto 변환 로직을 Dto 클래스에서 캡슐화 할 수 있음
    public ThreadDto.PostResponse toPostResponse() {
        return ThreadDto.PostResponse.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .viewCount(this.viewCount)
                .replyCount(this.replyCount)
                .member(this.member)
                .createdAt(this.modifiedAt)
                .build();
    }

    public ThreadDto.PatchResponse toPatchResponse() {
        return ThreadDto.PatchResponse.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .viewCount(this.viewCount)
                .replyCount(this.replyCount)
                .member(this.member)
                .modifiedAt(this.modifiedAt)
                .build();
    }
}
