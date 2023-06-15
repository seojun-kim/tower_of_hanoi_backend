package kr.hs.dgsw.towerofhanoi.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class MemberLoginDTO {

    @NotBlank(message = "아이디는 공백이 아니어야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
    private String password;
}
