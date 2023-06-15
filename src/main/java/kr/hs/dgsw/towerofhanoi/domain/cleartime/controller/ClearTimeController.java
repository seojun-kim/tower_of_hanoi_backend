package kr.hs.dgsw.towerofhanoi.domain.cleartime.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.dto.ClearTimeResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.cleartime.service.ClearTimeService;
import kr.hs.dgsw.towerofhanoi.global.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/cleartime")
public class ClearTimeController {

    private final ClearTimeService clearTimeService;
    private final JwtUtils jwtUtils;

    @PostMapping("/add")
    public ResponseEntity add(@RequestHeader("Authorization") String token, @RequestBody @Valid ClearTimeInsertDTO clearTimeInsertDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Long memberId = jwtUtils.getMemberIdFormToken(token);
        ClearTimeResponseDTO clearTimeResponseDTO = clearTimeService.insert(memberId, clearTimeInsertDTO);

        return new ResponseEntity(clearTimeResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity list(@PageableDefault(size = 10, sort = "clearTime", direction = Sort.Direction.DESC) Pageable pageable) {

        List<ClearTimeResponseDTO> clearTimeResponseList = clearTimeService.findAll(pageable);
        return new ResponseEntity(clearTimeResponseList, HttpStatus.OK);
    }

    @GetMapping("/list/{stage}")
    public ResponseEntity listByStage(@PageableDefault(size = 10, sort = "clearTime", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable("stage")int stage) {

        List<ClearTimeResponseDTO> clearTimeResponseList = clearTimeService.selectByStage(stage, pageable);
        return new ResponseEntity(clearTimeResponseList, HttpStatus.OK);
    }
}
