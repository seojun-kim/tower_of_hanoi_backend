package kr.hs.dgsw.towerofhanoi.domain.cleartime.mapper;

import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO;

public interface ClearTimeMapper {

    ClearTime clearTimeInsertDTOToClearTime(Long memberId, ClearTimeInsertDTO clearTimeInsertDTO);
    ClearTimeResponseDTO clearTimeToClearTimeResponseDTO(ClearTime clearTime);
}
