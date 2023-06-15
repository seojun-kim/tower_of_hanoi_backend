package kr.hs.dgsw.towerofhanoi.domain.cleartime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_clear_time")
public class ClearTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clear_time_nid")
    private Long nid;

    @NotNull
    @Column(name = "clear_time")
    private int clearTime;

    @NotNull
    private int stage;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_nid")
    private Member member;

    @Builder
    public ClearTime(int clearTime, int stage, Member member) {
        this.clearTime = clearTime;
        this.stage = stage;

        this.member = member;
        member.getClearTimeList().add(this); //연관관계 편의
    }

}
