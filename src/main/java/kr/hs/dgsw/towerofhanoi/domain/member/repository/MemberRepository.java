package kr.hs.dgsw.towerofhanoi.domain.member.repository;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public boolean existsById(String id); //중복 아디디 확인
}
