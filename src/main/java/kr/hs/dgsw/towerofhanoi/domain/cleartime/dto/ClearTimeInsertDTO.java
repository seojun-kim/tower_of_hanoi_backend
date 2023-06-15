package kr.hs.dgsw.towerofhanoi.domain.cleartime.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class ClearTimeInsertDTO {

    @NotNull(message = "클리어 타임의 값은 null이 아니여야 합니다.")
    private int clearTime;

    @NotNull
    private int stage;

}
