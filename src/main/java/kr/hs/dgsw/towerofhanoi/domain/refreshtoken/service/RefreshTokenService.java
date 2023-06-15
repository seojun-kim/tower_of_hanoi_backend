package kr.hs.dgsw.towerofhanoi.domain.refreshtoken.service;

import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.RefreshToken;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenResponseDTO;

public interface RefreshTokenService {

    RefreshToken insert(RefreshTokenInsertDTO refreshTokenInsertDTO);
    RefreshTokenIssuedResponseDTO issued(RefreshTokenIssuedDTO refreshTokenIssuedDTO);
    Long delete(Long refreshTokenId);
}
