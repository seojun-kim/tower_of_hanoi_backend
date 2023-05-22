package kr.hs.dgsw.towerofhanoi.domain.cleartime.service;

import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClearTimeService {

    public ClearTimeResponseDTO insert(ClearTimeInsertDTO clearTimeInsertDTO);
    public List<ClearTimeResponseDTO> findAll(Pageable pageable);
    public List<ClearTimeResponseDTO> selectByMember(Long memberId);
}
