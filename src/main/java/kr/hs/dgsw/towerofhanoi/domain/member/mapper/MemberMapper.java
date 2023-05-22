package kr.hs.dgsw.towerofhanoi.domain.member.mapper;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;

public interface MemberMapper {

    Member memberInsertDTOToMember(MemberInsertDTO memberInsertDTO);
    MemberResponseDTO memberToMemberResponseDTO(Member member);
}
