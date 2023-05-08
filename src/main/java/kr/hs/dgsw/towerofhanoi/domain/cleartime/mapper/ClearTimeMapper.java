package kr.hs.dgsw.towerofhanoi.domain.cleartime.mapper;

import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClearTimeMapper {

    ClearTime clearTimeInsertDTOToClearTime(ClearTimeInsertDTO clearTimeInsertDTO);
    ClearTimeResponseDTO clearTimeToClearTimeResponseDTO(ClearTime clearTime);
}
