package kr.hs.dgsw.towerofhanoi.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberUpdateDTO {

    @NotNull
    private Long nid;

    @NotBlank(message = "아이디는 공백이 아니어야 합니다.")
    private String id;

    @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
    private String password;

    @Builder
    public MemberUpdateDTO(Long nid, String id, String password) {
        this.nid = nid;
        this.id = id;
        this.password = password;
    }
}
