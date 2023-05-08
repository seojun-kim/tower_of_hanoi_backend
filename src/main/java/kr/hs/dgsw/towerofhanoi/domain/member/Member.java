package kr.hs.dgsw.towerofhanoi.domain.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE member SET deleted_at = CURRENT_TIMESTAMP where member_id = ?")
@ToString
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_nid")
    private Long nid;

    @NotBlank(message = "아이디는 공백이 아니어야 합니다.")
    private String id;

    @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
    private String password;

    @CreatedDate
    private LocalDateTime createDate;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<ClearTime> clearTimeList = new ArrayList<>();

    @Builder
    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public void update(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
