package kr.hs.dgsw.towerofhanoi.domain.member.service;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.MemberUpdateDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.repository.MemberRepository;
import kr.hs.dgsw.towerofhanoi.global.error.DuplicateNameException;
import kr.hs.dgsw.towerofhanoi.global.error.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    private String id = "seo";
    private String password = "jun";

    @Test
    public void 회원가입() throws Exception {
        //given
        MemberInsertDTO memberInsertDTO = MemberInsertDTO.builder()
                .id("asd")
                .password("asd")
                .build();

        //when
        MemberResponseDTO memberResponseDTO = memberService.insert(memberInsertDTO);

        //then
        assertThat(memberInsertDTO.getId()).isEqualTo(memberResponseDTO.getId());

    }

    @Test
    public void 회원가입_오류() throws Exception {
        //given
        MemberResponseDTO memberResponseDTO = createMember();

        MemberInsertDTO memberInsertDTO = MemberInsertDTO.builder()
                .id(memberResponseDTO.getId())
                .password(password)
                .build();

        //then
        assertThrows(DuplicateNameException.class, () -> {
            memberService.insert(memberInsertDTO);
        });

    }

    /**
     * 아이디와 비밀번호 둘 다 전과 다르게 변경한 경우
     */
    @Test
    public void 수정1() {
        //given
        MemberResponseDTO memberResponseDTO = createMember();

        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
                .nid(memberResponseDTO.getNid())
                .id("jun")
                .password("seo")
                .build();

        //when
        MemberResponseDTO update = memberService.update(memberUpdateDTO);

        //then
        assertThat(update.getId()).isEqualTo(memberUpdateDTO.getId());
    }

    /**
     * 비밀번호만 바꾼 경우
     */
    @Test
    public void 수정2() throws Exception {
        //given
        MemberResponseDTO memberResponseDTO = createMember();

        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
                .nid(memberResponseDTO.getNid())
                .id(memberResponseDTO.getId())
                .password("123")
                .build();

        //when
        MemberResponseDTO update = memberService.update(memberUpdateDTO);

        //then
        assertThat(memberUpdateDTO.getId()).isEqualTo(update.getId());

    }

    /**
     * 잘못된 nid를 보낸 경우
     */
    @Test
    public void 수정_오류1() throws Exception {
        //given
        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
                .nid(0L)
                .id(id)
                .password(password)
                .build();

        //then
        assertThrows(NotFoundException.class, () -> {
            memberService.update(memberUpdateDTO);
        });

    }

    /**
     * 수정할 id가 이미 있는 경우
     */
    @Test
    public void 수정_오류2() throws Exception {
        //given
        MemberResponseDTO memberResponseDTO1 = createMember();
        MemberResponseDTO memberResponseDTO2 = createMember(id + "1", password + "1");

        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
                .nid(memberResponseDTO1.getNid())
                .id(memberResponseDTO2.getId())
                .password(memberResponseDTO1.getId())
                .build();

        //then
        assertThrows(DuplicateNameException.class, () -> {
            memberService.update(memberUpdateDTO);
        });

    }

    @Test
    public void 삭제() throws Exception {
        //given
        MemberResponseDTO memberResponseDTO = createMember();

        //when
        Long memberId = memberService.delete(memberResponseDTO.getNid());

        //then
        assertThat(memberResponseDTO.getNid()).isEqualTo(memberId);

    }

    /**
     * 잘못된 nid를 보낸 경우
     */
    @Test
    public void 삭제_오류() throws Exception {
        //then
        assertThrows(NotFoundException.class, () -> {
            memberService.delete(0L);
        });
    }

    private MemberResponseDTO createMember() {

        MemberInsertDTO memberInsertDTO = MemberInsertDTO.builder()
                .id(id)
                .password(password)
                .build();

        return memberService.insert(memberInsertDTO);
    }

    private MemberResponseDTO createMember(String id, String password) {

        MemberInsertDTO memberInsertDTO = MemberInsertDTO.builder()
                .id(id)
                .password(password)
                .build();

        return memberService.insert(memberInsertDTO);
    }

}