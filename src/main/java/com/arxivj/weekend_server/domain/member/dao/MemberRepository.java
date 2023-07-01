package com.arxivj.weekend_server.domain.member.dao;
import com.arxivj.weekend_server.domain.member.domain.Member;
import com.arxivj.weekend_server.domain.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
    boolean existsByEmail(Email email);

}
