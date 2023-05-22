package kr.hs.dgsw.towerofhanoi.domain.cleartime.dto;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ClearTimeResponseDTO {

    private int clearTime;

    private int stage;

    private LocalDateTime createdDate;

    private Member member;

    @Builder
    public ClearTimeResponseDTO(int clearTime, int stage,LocalDateTime createdDate, Member member) {
        this.clearTime = clearTime;
        this.stage = stage;
        this.createdDate = createdDate;
        this.member = member;
    }
}
