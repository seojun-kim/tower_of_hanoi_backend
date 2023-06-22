package kr.hs.dgsw.towerofhanoi.domain.cleartime.dto;

import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class ClearTimeResponseDTO {

    private String username;

    private int clearTime;

    private int stage;

    private String createdDate;

}
