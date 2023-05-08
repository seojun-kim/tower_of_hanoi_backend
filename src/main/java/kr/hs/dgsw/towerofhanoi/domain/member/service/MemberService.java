package kr.hs.dgsw.towerofhanoi.domain.member.service;

import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberUpdateDTO;

public interface MemberService {

    public MemberResponseDTO insert(MemberInsertDTO memberInsertDTO);
    public MemberResponseDTO update(MemberUpdateDTO memberUpdateDTO);
    public Long delete(Long memberId);
}
