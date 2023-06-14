package kr.hs.dgsw.towerofhanoi.domain.role.repository;

import kr.hs.dgsw.towerofhanoi.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
