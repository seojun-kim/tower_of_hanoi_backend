package kr.hs.dgsw.towerofhanoi.domain.member.service;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberUpdateDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.mapper.MemberMapper;
import kr.hs.dgsw.towerofhanoi.domain.member.repository.MemberRepository;
import kr.hs.dgsw.towerofhanoi.global.error.DuplicateNameException;
import kr.hs.dgsw.towerofhanoi.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper mapper;

    @Override
    public MemberResponseDTO insert(MemberInsertDTO memberInsertDTO) {

        log.info("member service insert 실행, memberInsertDTO : {}", memberInsertDTO.toString());

        if(memberRepository.existsById(memberInsertDTO.getId())) {
            log.info("이미 존재하는 id : {}", memberInsertDTO.getId());
            throw new DuplicateNameException();
        }

        Member member = mapper.memberInsertDTOToMember(memberInsertDTO);
        memberRepository.save(member);

        return mapper.memberToMemberResponseDTO(member);
    }

    @Override
    public MemberResponseDTO update(MemberUpdateDTO memberUpdateDTO) {

        log.info("member service update 실행, memberUpdateDTO : {}", memberUpdateDTO.toString());

        Member member = memberRepository.findById(memberUpdateDTO.getNid()).orElseThrow(() -> new NotFoundException("회원"));

        if(memberRepository.existsById(memberUpdateDTO.getId()) &&
            !member.getId().equals(memberUpdateDTO.getId())) {

            log.info("이미 존재하는 id : {}", memberUpdateDTO.getId());
            throw new DuplicateNameException();
        }

        member.update(memberUpdateDTO.getId(), memberUpdateDTO.getPassword());

        return mapper.memberToMemberResponseDTO(member);
    }

    @Override
    public Long delete(Long memberId) {

        log.info("member service delete 실행, memberId : {}", memberId);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("member"));

        memberRepository.delete(member);

        return memberId;
    }
}
