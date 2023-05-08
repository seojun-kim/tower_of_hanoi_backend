package kr.hs.dgsw.towerofhanoi.domain.member.mapper;

import javax.annotation.processing.Generated;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.Member.MemberBuilder;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO.MemberResponseDTOBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T11:03:25+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberInsertDTOToMember(MemberInsertDTO memberInsertDTO) {
        if ( memberInsertDTO == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.id( memberInsertDTO.getId() );
        member.password( memberInsertDTO.getPassword() );

        return member.build();
    }

    @Override
    public MemberResponseDTO memberToMemberResponseDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDTOBuilder memberResponseDTO = MemberResponseDTO.builder();

        memberResponseDTO.nid( member.getNid() );
        memberResponseDTO.id( member.getId() );

        return memberResponseDTO.build();
    }
}
