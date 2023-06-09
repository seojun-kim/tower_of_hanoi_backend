package kr.hs.dgsw.towerofhanoi.domain.member.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class MemberResponseDTO {

    private Long id;

    private String username;

}
