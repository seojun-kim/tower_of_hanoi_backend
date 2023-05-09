package kr.hs.dgsw.towerofhanoi.domain.cleartime.service;

import kr.hs.dgsw.towerofhanoi.domain.cleartime.ClearTime;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.mapper.ClearTimeMapper;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.repository.ClearTimeRepository;
import kr.hs.dgsw.towerofhanoi.domain.member.Member;
import kr.hs.dgsw.towerofhanoi.domain.member.repository.MemberRepository;
import kr.hs.dgsw.towerofhanoi.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClearTimeServiceImpl implements ClearTimeService {

    private final ClearTimeRepository clearTimeRepository;
    private final MemberRepository memberRepository;
    private final ClearTimeMapper mapper;

    @Override
    public ClearTimeResponseDTO insert(ClearTimeInsertDTO clearTimeInsertDTO) {

        log.info("clearTime service insert 실행, clearTimeInsertDTO : {}", clearTimeInsertDTO);

        ClearTime clearTime = mapper.clearTimeInsertDTOToClearTime(clearTimeInsertDTO);
        clearTimeRepository.save(clearTime);

        return mapper.clearTimeToClearTimeResponseDTO(clearTime);
    }

    @Override
    public List<ClearTimeResponseDTO> selectByClearTime(Pageable pageable) {

        log.info("clearTime service selectByClearTime 실행");

        List<ClearTime> clearTimeList = clearTimeRepository.findByClearTime(pageable);
        ArrayList<ClearTimeResponseDTO> clearTimeResponseList = new ArrayList<>();

        for (ClearTime clearTime : clearTimeList) {
            clearTimeResponseList.add(mapper.clearTimeToClearTimeResponseDTO(clearTime));
        }

        return clearTimeResponseList;
    }

    @Override
    public List<ClearTimeResponseDTO> selectByMember(Long memberId) {

        log.info("clearTime service selectByMember 실행");

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("member"));

        List<ClearTime> clearTimeList = clearTimeRepository.findByMember(member);
        ArrayList<ClearTimeResponseDTO> clearTimeResponseList = new ArrayList<>();

        for (ClearTime clearTime : clearTimeList) {
            clearTimeResponseList.add(mapper.clearTimeToClearTimeResponseDTO(clearTime));
        }

        return clearTimeResponseList;
    }
}
