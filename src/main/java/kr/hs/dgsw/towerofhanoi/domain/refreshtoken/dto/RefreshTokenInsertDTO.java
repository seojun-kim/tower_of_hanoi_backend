package kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class RefreshTokenInsertDTO {

    private String token;
    private Long memberId;
}
