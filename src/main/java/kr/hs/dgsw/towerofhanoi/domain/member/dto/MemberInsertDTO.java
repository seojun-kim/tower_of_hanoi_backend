package kr.hs.dgsw.towerofhanoi.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberInsertDTO {

    @NotBlank(message = "아이디는 공백이 아니어야 합니다.")
    private String id;

    @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
    private String password;

    @Builder
    public MemberInsertDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
