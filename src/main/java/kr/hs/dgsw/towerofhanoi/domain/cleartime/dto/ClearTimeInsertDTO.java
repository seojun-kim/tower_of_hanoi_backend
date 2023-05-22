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
    private int stage;

    @NotNull
    private Long memberNid;

    @Builder
    public ClearTimeInsertDTO(int clearTime, int stage,Long memberNid) {
        this.clearTime = clearTime;
        this.stage = stage;
        this.memberNid = memberNid;
    }
}
