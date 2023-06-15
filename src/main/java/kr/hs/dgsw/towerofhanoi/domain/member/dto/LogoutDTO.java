package kr.hs.dgsw.towerofhanoi.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class LogoutDTO {

    @NotBlank
    private String refreshToken;
}
