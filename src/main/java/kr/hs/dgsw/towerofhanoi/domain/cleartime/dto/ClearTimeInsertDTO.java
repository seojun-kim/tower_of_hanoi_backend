package kr.hs.dgsw.towerofhanoi.domain.cleartime.dto;

import jakarta.validation.constraints.NotNull;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ClearTimeInsertDTO {

    @NotNull(message = "클리어 타임의 값은 null이 아니여야 합니다.")
    private int clearTime;

    @NotNull
    private Member member;

    @Builder
    public ClearTimeInsertDTO(int clearTime, Member member) {
        this.clearTime = clearTime;
        this.member = member;
    }
}
