package kr.hs.dgsw.towerofhanoi.domain.member.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class MemberLoginResponseDTO {

    private String accessToken;

    private String refreshToken;

    private Long memberId;

    private String username;
}
