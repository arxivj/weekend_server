package com.arxivj.weekend_server.domain.thread.domain;
import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.model.PreserveState;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Threads(Long id, String title, String content, long viewCount, long replyCount, PreserveState preserveState, Member member){
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
        this.preserveState = preserveState;
        this.member = member;
    }

    public void updatePreserveState(){
        this.preserveState.setIsDeleted(true);
        this.preserveState.setDeletedAt(LocalDateTime.now());
    }

}
