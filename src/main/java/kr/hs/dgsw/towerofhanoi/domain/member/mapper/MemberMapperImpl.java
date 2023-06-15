package kr.hs.dgsw.towerofhanoi.domain.member.mapper;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberLoginResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapperImpl implements MemberMapper {

    private final PasswordEncoder encoder;

    @Override
    public Member memberInsertDTOToMember(MemberInsertDTO memberInsertDTO, Role role) {
        return Member.builder()
                .username(memberInsertDTO.getUsername())
                .password(encoder.encode(memberInsertDTO.getPassword()))
                .role(role)
                .build();
    }

    @Override
    public MemberResponseDTO memberToMemberResponseDTO(Member member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .build();
    }

    @Override
    public MemberLoginResponseDTO createMemberLoginResponseDTO(Member member, String accessToken, String refreshToken) {
        return MemberLoginResponseDTO.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
