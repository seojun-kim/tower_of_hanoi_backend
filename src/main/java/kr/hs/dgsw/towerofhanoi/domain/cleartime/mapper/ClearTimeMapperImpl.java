package kr.hs.dgsw.towerofhanoi.domain.cleartime.mapper;

import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.repository.MemberRepository;
import kr.hs.dgsw.towerofhanoi.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClearTimeMapperImpl implements ClearTimeMapper {

    private final MemberRepository memberRepository;

    @Override
    public ClearTime clearTimeInsertDTOToClearTime(ClearTimeInsertDTO clearTimeInsertDTO) {

        Member member = memberRepository.findById(clearTimeInsertDTO.getMemberNid()).orElseThrow(() -> new NotFoundException("member"));

        return ClearTime.builder()
                .clearTime(clearTimeInsertDTO.getClearTime())
                .stage(clearTimeInsertDTO.getStage())
                .member(member)
                .build();

    }

    @Override
    public ClearTimeResponseDTO clearTimeToClearTimeResponseDTO(ClearTime clearTime) {
        return ClearTimeResponseDTO.builder()
                .clearTime(clearTime.getClearTime())
                .stage(clearTime.getStage())
                .createdDate(clearTime.getCreateDate())
                .member(clearTime.getMember())
                .build();
    }
}
