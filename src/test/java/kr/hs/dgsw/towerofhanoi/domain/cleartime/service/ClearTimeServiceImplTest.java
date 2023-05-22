package kr.hs.dgsw.towerofhanoi.domain.cleartime.service;

import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.repository.MemberRepository;
import kr.hs.dgsw.towerofhanoi.domain.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ClearTimeServiceImplTest {

    @Autowired ClearTimeService clearTimeService;
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("clear time insert 성공")
    public void insert_success() throws Exception {
        //given
        Member member = createMember();
        ClearTimeInsertDTO clearTimeInsertDTO = createClearTimeInsertDTO(member);

        //when
        ClearTimeResponseDTO clearTimeResponseDTO = clearTimeService.insert(clearTimeInsertDTO);

        //then
        assertThat(clearTimeInsertDTO.getClearTime()).isEqualTo(clearTimeResponseDTO.getClearTime());

    }

    @Test
    @DisplayName("find all 성공")
    public void findAll() throws Exception {
        //given
        Member member = createMember();
        ClearTimeResponseDTO clearTimeResponseDTO1 = createClearTimeResponseDTO(member);
        ClearTimeResponseDTO clearTimeResponseDTO2 = createClearTimeResponseDTO(member);
        Pageable pageable = Pageable.ofSize(5);

        //when
        List<ClearTimeResponseDTO> clearTimeResponseDTOList = clearTimeService.findAll(pageable);

        //then
        assertThat(clearTimeResponseDTOList.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("member로 찾기")
    public void selectByMember() throws Exception {
        //given
        Member member = createMember();
        ClearTimeResponseDTO clearTimeResponseDTO1 = createClearTimeResponseDTO(member);
        ClearTimeResponseDTO clearTimeResponseDTO2 = createClearTimeResponseDTO(member);

        //when
        List<ClearTimeResponseDTO> clearTimeResponseDTOList = clearTimeService.selectByMember(member.getNid());

        //then
        assertThat(clearTimeResponseDTOList.size()).isEqualTo(2);

    }

    private ClearTimeInsertDTO createClearTimeInsertDTO(Member member) {
        return ClearTimeInsertDTO.builder()
                .memberNid(member.getNid())
                .clearTime(10)
                .build();
    }

    private ClearTimeResponseDTO createClearTimeResponseDTO(Member member) {
        return clearTimeService.insert(createClearTimeInsertDTO(member));
    }

    private Member createMember() {

        MemberInsertDTO memberInsertDTO = MemberInsertDTO.builder()
                .id("kim")
                .password("jun")
                .build();

        MemberResponseDTO memberResponseDTO = memberService.insert(memberInsertDTO);

        return memberRepository.findById(memberResponseDTO.getNid()).get();
    }

}