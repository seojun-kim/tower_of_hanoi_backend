package kr.hs.dgsw.towerofhanoi.domain.cleartime.mapper;

import javax.annotation.processing.Generated;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime.ClearTimeBuilder;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO.ClearTimeResponseDTOBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-11T08:47:31+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class ClearTimeMapperImpl implements ClearTimeMapper {

    @Override
    public ClearTime clearTimeInsertDTOToClearTime(ClearTimeInsertDTO clearTimeInsertDTO) {
        if ( clearTimeInsertDTO == null ) {
            return null;
        }

        ClearTimeBuilder clearTime = ClearTime.builder();

        clearTime.clearTime( clearTimeInsertDTO.getClearTime() );
        clearTime.member( clearTimeInsertDTO.getMember() );

        return clearTime.build();
    }

    @Override
    public ClearTimeResponseDTO clearTimeToClearTimeResponseDTO(ClearTime clearTime) {
        if ( clearTime == null ) {
            return null;
        }

        ClearTimeResponseDTOBuilder clearTimeResponseDTO = ClearTimeResponseDTO.builder();

        clearTimeResponseDTO.clearTime( clearTime.getClearTime() );

        return clearTimeResponseDTO.build();
    }
}
