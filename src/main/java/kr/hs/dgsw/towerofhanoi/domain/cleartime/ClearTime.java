package kr.hs.dgsw.towerofhanoi.domain.cleartime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_clear_time")
public class ClearTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clear_time_nid")
    private Long nid;

    @NotNull
    @Column(name = "clear_time")
    private int clearTime;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_nid")
    private Member member;

    @Builder
    public ClearTime(int clearTime, Member member) {
        this.clearTime = clearTime;
        this.member = member;

        this.member.getClearTimeList().add(this); //연관관계 편의
    }

}
