package kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class RefreshTokenIssuedResponseDTO {

    private String accessToken;
}
