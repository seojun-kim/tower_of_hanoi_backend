package kr.hs.dgsw.towerofhanoi.domain.member.service;

import kr.hs.dgsw.towerofhanoi.domain.member.dto.*;

public interface MemberService {

    public MemberResponseDTO insert(MemberInsertDTO memberInsertDTO);
    public MemberLoginResponseDTO login(MemberLoginDTO memberLoginDTO);
    public MemberResponseDTO update(MemberUpdateDTO memberUpdateDTO);
    public Long delete(Long memberId);

    void logout(String accessToken);
}
