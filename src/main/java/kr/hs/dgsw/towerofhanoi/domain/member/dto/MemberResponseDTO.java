package kr.hs.dgsw.towerofhanoi.domain.member.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberResponseDTO {

    private Long nid;

    private String id;

    @Builder
    public MemberResponseDTO(Long nid, String id) {
        this.nid = nid;
        this.id = id;
    }
}
