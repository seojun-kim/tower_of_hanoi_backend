package kr.hs.dgsw.towerofhanoi.domain.refreshtoken.repository;

import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
}
