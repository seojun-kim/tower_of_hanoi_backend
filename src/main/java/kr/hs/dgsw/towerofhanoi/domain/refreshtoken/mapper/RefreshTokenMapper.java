package kr.hs.dgsw.towerofhanoi.domain.refreshtoken.mapper;

import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.RefreshToken;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenInsertDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenIssuedResponseDTO;
import kr.hs.dgsw.towerofhanoi.domain.refreshtoken.dto.RefreshTokenResponseDTO;

public interface RefreshTokenMapper {
    RefreshToken refreshTokenInsertDTOToRefreshToken(RefreshTokenInsertDTO refreshTokenInsertDTO);
    RefreshTokenResponseDTO refreshTokenToRefreshTokenResponseDTO(RefreshToken refreshToken, Long memberId);
    RefreshTokenIssuedResponseDTO accessTokenTokenIssuedResponseDTO(String accessToken);

    RefreshTokenInsertDTO createRefreshTokenInsertDTO(String refreshToken, Long memberId);
}
