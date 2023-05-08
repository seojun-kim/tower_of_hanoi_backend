package kr.hs.dgsw.towerofhanoi.domain.member.mapper;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberInsertDTOToMember(MemberInsertDTO memberInsertDTO);
    MemberResponseDTO memberToMemberResponseDTO(Member member);
}
