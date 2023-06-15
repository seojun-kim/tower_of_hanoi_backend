package kr.hs.dgsw.towerofhanoi.domain.refreshtoken;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RefreshToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_nid")
    private Long id;

    private String token;

    @JsonIgnore
    @OneToOne(mappedBy = "refreshToken")
    private Member member;

    @Builder
    public RefreshToken(String token) {
        this.token = token;
    }
}
