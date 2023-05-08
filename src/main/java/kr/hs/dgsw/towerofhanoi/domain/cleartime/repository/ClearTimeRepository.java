package kr.hs.dgsw.towerofhanoi.domain.cleartime.repository;

import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ClearTimeRepository extends JpaRepository<ClearTime, Long> {

    @Query("select c from ClearTime c join fetch c.member order by c.cleat_time ASC")
    List<ClearTime> findByClearTime(Pageable pageable);
}
