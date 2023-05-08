package kr.hs.dgsw.towerofhanoi.domain.cleartime.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ClearTimeResponseDTO {

    private int clearTime;

    private LocalDateTime createdDate;

    @Builder
    public ClearTimeResponseDTO(int clearTime, LocalDateTime createdDate) {
        this.clearTime = clearTime;
        this.createdDate = createdDate;
    }
}
