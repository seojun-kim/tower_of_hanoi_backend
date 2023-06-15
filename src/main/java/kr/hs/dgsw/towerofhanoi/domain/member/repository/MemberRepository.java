package kr.hs.dgsw.towerofhanoi.domain.member.repository;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public boolean existsByUsername(String username); //중복 아디디 확인
    public Optional<Member> findByUsername(String username);
}
