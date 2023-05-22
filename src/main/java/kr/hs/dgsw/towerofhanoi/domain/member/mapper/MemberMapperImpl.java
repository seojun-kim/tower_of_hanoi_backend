package kr.hs.dgsw.towerofhanoi.domain.member.mapper;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberInsertDTOToMember(MemberInsertDTO memberInsertDTO) {
        return Member.builder()
                .id(memberInsertDTO.getId())
                .password(memberInsertDTO.getPassword())
                .build();
    }

    @Override
    public MemberResponseDTO memberToMemberResponseDTO(Member member) {
        return MemberResponseDTO.builder()
                .nid(member.getNid())
                .id(member.getId())
                .build();
    }
}
