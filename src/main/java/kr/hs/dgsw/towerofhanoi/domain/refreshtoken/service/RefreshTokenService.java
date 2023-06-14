package kr.hs.dgsw.towerofhanoi.domain.refreshtoken.service;

import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenResponseDTO;

public interface RefreshTokenService {

    RefreshTokenResponseDTO insert(RefreshTokenInsertDTO refreshTokenInsertDTO);
    public RefreshTokenIssuedResponseDTO issued(RefreshTokenIssuedDTO refreshTokenIssuedDTO);
}
