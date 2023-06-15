package kr.hs.dgsw.towerofhanoi.domain.member.mapper;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberLoginResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.role.Role;

public interface MemberMapper {

    Member memberInsertDTOToMember(MemberInsertDTO memberInsertDTO, Role role);
    MemberResponseDTO memberToMemberResponseDTO(Member member);

    MemberLoginResponseDTO createMemberLoginResponseDTO(Member member, String accessToken, String refreshToken);
}
