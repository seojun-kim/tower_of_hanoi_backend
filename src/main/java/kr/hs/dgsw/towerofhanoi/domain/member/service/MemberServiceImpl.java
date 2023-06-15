package kr.hs.dgsw.towerofhanoi.domain.member.service;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.*;
import kr.hs.dgsw.towerofhanoi.domain.member.mapper.MemberMapper;
import kr.hs.dgsw.towerofhanoi.domain.member.repository.MemberRepository;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.RefreshToken;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.mapper.RefreshTokenMapper;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.service.RefreshTokenService;
import kr.hs.dgsw.towerofhanoi.domain.role.Role;
import kr.hs.dgsw.towerofhanoi.domain.role.repository.RoleRepository;
import kr.hs.dgsw.towerofhanoi.global.error.DuplicateNameException;
import kr.hs.dgsw.towerofhanoi.global.error.LoginException;
import kr.hs.dgsw.towerofhanoi.global.error.NotFoundException;
import kr.hs.dgsw.towerofhanoi.global.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder encoder;
    private final MemberMapper memberMapper;
    private final RefreshTokenMapper refreshTokenMapper;
    private final JwtUtils jwtUtils;

    @Override
    public MemberResponseDTO insert(MemberInsertDTO memberInsertDTO) {

        log.info("member service insert 실행, memberInsertDTO : {}", memberInsertDTO.toString());

        if(memberRepository.existsByUsername(memberInsertDTO.getUsername())) {
            log.info("이미 존재하는 id : {}", memberInsertDTO.getUsername());
            throw new DuplicateNameException();
        }

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new NotFoundException("role"));

        Member member = memberMapper.memberInsertDTOToMember(memberInsertDTO, role);
        memberRepository.save(member);

        return memberMapper.memberToMemberResponseDTO(member);
    }

    @Override
    public MemberLoginResponseDTO login(MemberLoginDTO memberLoginDTO) {

        log.info("password : {}", memberLoginDTO.getPassword());

        Member member = memberRepository.findByUsername(memberLoginDTO.getUsername()).orElseThrow(() -> new LoginException(memberLoginDTO.getUsername()));
        if(!encoder.matches(memberLoginDTO.getPassword(), member.getPassword())) {
            throw new LoginException(memberLoginDTO.getUsername());
        }

        List<String> roles = member.getRoles().stream().map(Role::getName).collect(Collectors.toList());

        String accessToken = jwtUtils.createAccessToken(member.getId(), member.getUsername(), roles);
        String refreshToken = jwtUtils.createRefreshToken(member.getId(), member.getUsername(), roles);

        RefreshTokenInsertDTO refreshTokenInsertDTO = refreshTokenMapper.createRefreshTokenInsertDTO(refreshToken, member.getId());
        RefreshToken refreshTokenEntity = refreshTokenService.insert(refreshTokenInsertDTO);

        if(member.getRefreshToken() != null) {
            refreshTokenService.delete(member.getRefreshToken().getId());
        }
        member.refreshTokenIssued(refreshTokenEntity);

        return memberMapper.createMemberLoginResponseDTO(member, accessToken, refreshToken);
    }

    @Override
    public MemberResponseDTO update(MemberUpdateDTO memberUpdateDTO) {

        log.info("member service update 실행, memberUpdateDTO : {}", memberUpdateDTO.toString());

        Member member = memberRepository.findById(memberUpdateDTO.getId()).orElseThrow(() -> new NotFoundException("회원"));

        if(memberRepository.existsById(memberUpdateDTO.getId()) &&
            !member.getId().equals(memberUpdateDTO.getId())) {

            log.info("이미 존재하는 id : {}", memberUpdateDTO.getId());
            throw new DuplicateNameException();
        }

        member.update(memberUpdateDTO.getUsername(), memberUpdateDTO.getPassword());

        return memberMapper.memberToMemberResponseDTO(member);
    }

    @Override
    public Long delete(Long memberId) {

        log.info("member service delete 실행, memberId : {}", memberId);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("member"));

        memberRepository.delete(member);

        return memberId;
    }

    @Override
    public void logout(String accessToken) {

        log.info("member service logout 실행");

        Long memberId = jwtUtils.getMemberIdFormToken(accessToken);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("member"));
        member.refreshTokenIssued(null);
    }
}
