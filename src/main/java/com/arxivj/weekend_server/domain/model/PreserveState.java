package com.arxivj.weekend_server.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class PreserveState {

    @ColumnDefault(value="false")
    private Boolean isDeleted;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

}
