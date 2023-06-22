package kr.hs.dgsw.towerofhanoi.domain.member.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.towerofhanoi.domain.member.dto.*;
import kr.hs.dgsw.towerofhanoi.domain.member.service.MemberService;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.mapper.RefreshTokenMapper;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid MemberInsertDTO memberInsertDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        MemberResponseDTO memberResponseDTO = memberService.insert(memberInsertDTO);

        return new ResponseEntity(memberResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDTO memberLoginDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        MemberLoginResponseDTO memberLoginResponseDTO = memberService.login(memberLoginDTO);

        return new ResponseEntity(memberLoginResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/issuedToken")
    public ResponseEntity issuedAccessTokenToken(@RequestBody @Valid RefreshTokenIssuedDTO refreshTokenIssuedDTO) {

        RefreshTokenIssuedResponseDTO refreshTokenIssuedResponseDTO = refreshTokenService.issued(refreshTokenIssuedDTO);

        return new ResponseEntity(refreshTokenIssuedResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String accessToken) {

        memberService.logout(accessToken);

        return new ResponseEntity(HttpStatus.OK);
    }
}
