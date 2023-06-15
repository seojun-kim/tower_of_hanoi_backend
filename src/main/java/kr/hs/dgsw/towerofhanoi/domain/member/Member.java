package kr.hs.dgsw.towerofhanoi.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.RefreshToken;
import kr.hs.dgsw.towerofhanoi.domain.role.Role;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE member SET deleted_at = CURRENT_TIMESTAMP where member_id = ?")
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_nid")
    private Long id;

    @NotBlank(message = "아이디는 공백이 아니어야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
    private String password;

    @CreatedDate
    private LocalDateTime createDate;

    private LocalDateTime deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<ClearTime> clearTimeList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "member_role",
            joinColumns = @JoinColumn(name = "member_nid"),
            inverseJoinColumns = @JoinColumn(name = "role_nid")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "refresh_token_nid")
    private RefreshToken refreshToken;

    @Builder
    public Member(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.roles.add(role);
    }

    public void update(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void refreshTokenIssued(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

}
