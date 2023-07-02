package com.arxivj.weekend_server.domain.thread.dao;

import com.arxivj.weekend_server.domain.thread.domain.Threads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Threads, Long> {

}
