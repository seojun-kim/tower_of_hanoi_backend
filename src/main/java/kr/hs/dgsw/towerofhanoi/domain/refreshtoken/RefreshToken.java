package kr.hs.dgsw.towerofhanoi.domain.refreshtoken;

import jakarta.persistence.*;
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

    @Builder
    public RefreshToken(String token) {
        this.token = token;
    }
}
